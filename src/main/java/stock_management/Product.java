package stock_management;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import jxl.write.WriteException;

public class Product {

	public static void main(String[] args) throws WriteException, IOException {
		ProductMgmt pro = new ProductMgmt();
		ProductDAO pdao = new ProductDAO();
		
		Scanner sc = new Scanner(System.in);

		while(true) {
			System.out.println("┌───────────────────────────────────────────────────────────────┐");
			System.out.println("│1.상품 정보 2.입출고 내역 3.입출고 수정 4.입출고 내역 파일생성 5.현재 재고 정보 6.종료 \t│");
			System.out.println("└───────────────────────────────────────────────────────────────┘");
			int menu = sc.nextInt();

			switch (menu) {			
			case 1:
				pro.productView();
				break;		
			case 2:
				pro.iostockView();
				break;
			case 3:
				pro.iostockUpdate();
				break;
			case 4:
				pdao.excelExport();				
				break;
			case 5:
				pro.stockView();				
				break;
			case 6:
				System.out.println("프로그램을 종료합니다.");
				System.exit(0);
			}
		}
	}
}
