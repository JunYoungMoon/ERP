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
			System.out.println("����������������������������������������������������������������������������������������������������������������������������������");
			System.out.println("��1.��ǰ ���� 2.����� ���� 3.����� ���� 4.����� ���� ���ϻ��� 5.���� ��� ���� 6.���� \t��");
			System.out.println("����������������������������������������������������������������������������������������������������������������������������������");
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
				System.out.println("���α׷��� �����մϴ�.");
				System.exit(0);
			}
		}
	}
}
