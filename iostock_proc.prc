CREATE OR REPLACE PROCEDURE iostock_proc(p_iostock_product_id     NUMBER
                                        ,p_iostock_iostock_now    NUMBER
                                        ,p_iostock_warehouse_name VARCHAR2
                                        ,p_out                    OUT NUMBER) IS

    v_cnt     NUMBER := 0;
    cnt       NUMBER := 0;
    v_message VARCHAR2(500);
BEGIN
    --����� ���� ����
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

    --���� ��� ���� ����        
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

    -- �Է� ���� ���� ��� ���� 0���� ������ ���� ����� now_stock�� -���ش� ������ ���� �� �Ҷ��� ������ ��� ����� ����� �־�� �Ѵ�.
    -- -����� > -���� �� : ����� ���� �� ���� ���� ��� �Ҽ� ����. 
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

    --���࿡ ��� ������ �Է����� �ʾ����� insert
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
        --��������� �ִٸ� update
        UPDATE stock
        SET    now_stock = now_stock + p_iostock_iostock_now
        WHERE  product_id = p_iostock_product_id;
    END IF;

EXCEPTION
    WHEN invalid_number THEN
        dbms_output.put_line('����� �Է¹��� �� �ֽ��ϴ�');
    WHEN OTHERS THEN
        dbms_output.put_line(SQLERRM);
        RAISE;
    
END iostock_proc;
/
