CREATE OR REPLACE PROCEDURE iostock_proc(p_iostock_product_id     NUMBER
                                        ,p_iostock_iostock_now    NUMBER
                                        ,p_iostock_warehouse_name VARCHAR2
                                        ,p_out                    OUT NUMBER) IS

    v_cnt     NUMBER := 0;
    cnt       NUMBER := 0;
    v_message VARCHAR2(500);
BEGIN
    --입출고 정보 내역
    p_out := 1;
    INSERT INTO iostock
        (iostock_id
        ,product_id
        ,iostock_now
        ,warehouse_name
        ,iostock_date)
    VALUES
        (iostock_id.nextval
        ,p_iostock_product_id
        ,p_iostock_iostock_now
        ,p_iostock_warehouse_name
        ,SYSDATE);

    --기존 재고 정보 여부        
    SELECT COUNT(*)
    INTO   v_cnt
    FROM   stock
    WHERE  product_id = p_iostock_product_id;

    BEGIN
        SELECT now_stock
        INTO   cnt
        FROM   stock
        WHERE  product_id = p_iostock_product_id;
    EXCEPTION
        WHEN no_data_found THEN
            cnt := 0;
    END;

    -- 입력 받은 변경 재고 값이 0보다 작으면 현재 재고인 now_stock를 -해준다 음수의 값을 비교 할때는 현재의 재고도 양수로 만들어 주어야 한다.
    -- -현재고 > -들어온 값 : 현재고 보다 더 많은 값을 출고 할수 없다. 
    /*IF (p_iostock_iostock_now < 0) THEN
        cnt := cnt * (-1);
        IF cnt > p_iostock_iostock_now THEN
            p_out := -1;
            RAISE invalid_number;
        END IF;
    END IF;*/
    -- IF (p_iostock_iostock_now < 0) THEN       
    IF cnt + p_iostock_iostock_now < 0 THEN
        p_out := -1;
        RAISE invalid_number;
    END IF;
    -- END IF;

    --만약에 재고 정보를 입력하지 않았으면 insert
    IF v_cnt = 0 THEN
        BEGIN
            INSERT INTO stock
                (product_id
                ,warehouse_name
                ,now_stock)
            VALUES
                (p_iostock_product_id
                ,p_iostock_warehouse_name
                ,p_iostock_iostock_now);
        EXCEPTION
            WHEN OTHERS THEN
                v_message := SQLERRM;
        END;
    ELSE
        --재고정보가 있다면 update
        UPDATE stock
        SET    now_stock = now_stock + p_iostock_iostock_now
        WHERE  product_id = p_iostock_product_id;
    END IF;

EXCEPTION
    WHEN invalid_number THEN
        dbms_output.put_line('양수만 입력받을 수 있습니다');
    WHEN OTHERS THEN
        dbms_output.put_line(SQLERRM);
        RAISE;
    
END iostock_proc;
/
