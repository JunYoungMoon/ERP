package stock_management;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ProductMgmt {
	ProductDAO dao = new ProductDAO();
	Scanner sc = new Scanner(System.in);
	//ProductMgmt mgmt = new ProductMgmt();

	public void insertProduct() {
		System.out.println("��ǰ key�� �Է��ϼ���.");
		String product_id = sc.nextLine();
		System.out.println("��ǰ���� �Է��ϼ���.");
		String product_name = sc.nextLine();
		System.out.println("��ǰ ������ �����ϼ���.");
		String product_exp = sc.nextLine();
		System.out.println("��� �Է��ϼ���.");
		String basic_stoke = sc.nextLine();
		System.out.println("â�� �����ϼ���.");		
		String warehouse_name = sc.nextLine();

		Date today = new Date();
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");

		ProductDTO dto = new ProductDTO();

		dto.setProductId(product_id);
		dto.setProductName(product_name);
		dto.setProductExp(product_exp);
		dto.setBasicStock(basic_stoke);
		dto.setWarehouseName(warehouse_name);
		dto.setProductDate(sdf1.format(today));
		dao.insertPro(dto);
		//dao.insertEmpProc(dto);
	}

	public void productView() {		
		/*SearchVO vo = new SearchVO();
		vo.setSearchCondition("product_date");
		vo.setSearchKeyword("20180103");*/
		List<ProductDTO> list = dao.getProductView();
		for(ProductDTO dto : list)
			System.out.println("��"+dto + "��");
		System.out.println("������������������������������������������������������������������������������������������������������������������������������������������������������������������");
	}

	public void iostockView() {
		List<ProductDTO> list = dao.getIoStockView();
		for(ProductDTO dto : list)
			System.out.println("��"+dto.toString2() + "��");
		System.out.println("����������������������������������������������������������������������������������");
	}

	public void stockView() {
		List<ProductDTO> list = dao.getStockView();
		for(ProductDTO dto : list)
			System.out.println("��"+dto.toString3() + "��");
		System.out.println("��������������������������������������������������������������");
	}



	public void iostockUpdate() {
		System.out.println("����������������������������������");
		System.out.println("��    ��ǰŰ�Է�\t��");
		System.out.println("����������������������������������");
		String iostock_product_id = sc.nextLine();	

		List<ProductDTO> list = dao.getStockView();
		for(ProductDTO dto : list)
			System.out.println("��"+dto.toString3() + "��");
		System.out.println("��������������������������������������������������������������");

		System.out.println("����������������������������������");
		System.out.println("��   ������� �Է�\t��");
		System.out.println("����������������������������������");
		String iostock_iostock_now = sc.nextLine();

		System.out.println("����������������������������������");
		System.out.println("��    â�� �Է�\t��");
		System.out.println("����������������������������������");
		String iostock_warehouse_name = sc.nextLine();


		ProductDTO dto = new ProductDTO();
		dto.setProductId(iostock_product_id);
		dto.setIoStockNow(iostock_iostock_now);
		dto.setWarehouseName(iostock_warehouse_name);

		//dao.UpdateEmp(dto);
		dao.updateEmpProc(dto);
	}

}
