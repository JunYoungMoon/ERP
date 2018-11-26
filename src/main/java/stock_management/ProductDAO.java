package stock_management;

import java.awt.Transparency;
import java.io.File;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Colour;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;


public class ProductDAO {

	Connection conn = null;

	public Connection getConection() {		

		try {
			String user = "hr"; 
			String pw = "hr";
			String url = "jdbc:oracle:thin:@localhost:1521:xe";

			Class.forName("oracle.jdbc.driver.OracleDriver");        
			conn = DriverManager.getConnection(url, user, pw);

		} catch (ClassNotFoundException cnfe) {
			System.out.println("DB ����̹� �ε� ���� :"+cnfe.toString());
		} catch (SQLException sqle) {
			System.out.println("DB ���ӽ��� : "+sqle.toString());
		} catch (Exception e) {
			System.out.println("Unkonwn error");
			e.printStackTrace();
		}	    	    
		return conn;
	}

	public void insertPro(ProductDTO dto) {
		getConection();

		String sql = "insert into product (product_id, product_name, product_exp, basic_stoke, warehouse_name, product_date) "+
				"values(?, ?, ?, ?, ?, ?)";

		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getProductId());
			pstmt.setString(2, dto.getProductName());
			pstmt.setString(3, dto.getProductExp());
			pstmt.setString(4, dto.getBasicStock());
			pstmt.setString(5, dto.getWarehouseName());
			pstmt.setString(6, dto.getProductDate());
			int cnt = pstmt.executeUpdate();
			System.out.println(cnt + " ���� �ԷµǾ����ϴ�.");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

	public List<ProductDTO> getStockView() {
		getConection();

		ProductDTO dto = null;
		List<ProductDTO> list = new ArrayList<>();

		String sql = "select product_id, now_stock, warehouse_name from stock";

		System.out.println("\t     ����� ����");
		System.out.println("��������������������������������������������������������������");
		System.out.println("����ǰŰ\t �����\t\t â��    ��");
		System.out.println("��������������������������������������������������������������");

		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();

			while(rs.next()) {
				dto = new ProductDTO();				
				dto.setProductId(rs.getString("product_id"));
				dto.setNowStock(rs.getString("now_stock"));
				dto.setWarehouseName(rs.getString("warehouse_name"));

				list.add(dto);
			}        		
		}catch(SQLException e) {
			e.printStackTrace();
		}	    
		return list;
	}

	public List<ProductDTO> getIoStockView() {
		getConection();

		ProductDTO dto = null;
		List<ProductDTO> list = new ArrayList<>();

		String sql = "select iostock_id, product_id, iostock_now, warehouse_name from iostock";

		System.out.println();
		System.out.println("\t\t����� ����");
		System.out.println("����������������������������������������������������������������������������������");
		System.out.println("������� ��ȣ\t ��ǰŰ\t �����\t â��\t��");
		System.out.println("����������������������������������������������������������������������������������");

		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();

			while(rs.next()) {
				dto = new ProductDTO();
				dto.setIoStock(rs.getString("iostock_id"));
				dto.setProductId(rs.getString("product_id"));
				dto.setIoStockNow(rs.getString("iostock_now"));
				dto.setWarehouseName(rs.getString("warehouse_name"));
				/*dto.setWarehouseName(rs.getString("warehouse_name"));
	    		dto.setProductDate(rs.getString("product_date"));*/
				list.add(dto);
			}        		
		}catch(SQLException e) {
			e.printStackTrace();
		}	    
		return list;
	}

	public List<ProductDTO> getProductView() {

		getConection();
		//System.out.println(vo);

		ProductDTO dto = null;
		List<ProductDTO> list = new ArrayList<>();
		//String whereClause = "where 1=1";

		/*	    if(vo.getSearchCondition().equals("first_name"))
	    	whereClause += " and first_name like '%"+vo.getSearchKeyword()+ "%'";

	    if(vo.getSearchCondition().equals("product_date"))
	    	whereClause += " and product_date > '"+vo.getSearchKeyword()+ "'";

	    String sql = "select product_id, product_name, product_exp, basic_stoke, warehouse_name, product_date from product "
	    		+ whereClause + " order by 1 desc";*/

		String sql = "select product_id, product_name, product_exp, basic_stock from product";

		//System.out.println(sql);
		System.out.println();
		System.out.println("\t\t\t\t   ��ǰ ����");
		System.out.println("������������������������������������������������������������������������������������������������������������������������������������������������������������������");
		System.out.println("����ǰŰ\t ��ǰ��\t\t ��ǰ����\t\t\t\t\t\t �������\t��");
		System.out.println("������������������������������������������������������������������������������������������������������������������������������������������������������������������");

		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();

			while(rs.next()) {
				dto = new ProductDTO();
				dto.setProductId(rs.getString("product_id"));
				dto.setProductName(rs.getString("product_name"));
				dto.setProductExp(rs.getString("product_exp"));
				dto.setBasicStock(rs.getString("basic_stock"));
				/*dto.setWarehouseName(rs.getString("warehouse_name"));
	    		dto.setProductDate(rs.getString("product_date"));*/
				list.add(dto);
			}        		
		}catch(SQLException e) {
			e.printStackTrace();
		}	    
		return list;
	}
	public void updateEmpProc(ProductDTO dto) {

		getConection();

		try {
			CallableStatement cstmt = conn.prepareCall("{call iostock_proc(?,?,?,?)}");
			cstmt.setString(1, dto.getProductId());
			cstmt.setString(2, dto.getIoStockNow());
			cstmt.setString(3, dto.getWarehouseName());
			cstmt.registerOutParameter(4,java.sql.Types.INTEGER);


			int r = cstmt.executeUpdate();
			int check = cstmt.getInt(4);
			if(check < 0)
			{
				System.out.println("�ý��ۿ���");
				return;
			}

			System.out.println(r + "�� ���� �Ǿ����ϴ�.");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void excelExport() throws IOException, WriteException {
		ProductDAO dao = new ProductDAO();

		WritableWorkbook workbook = Workbook.createWorkbook(new File("iostocklog.xls"));
		WritableSheet sheet = workbook.createSheet("product1", 0);
		WritableCellFormat wcf = new WritableCellFormat();
		wcf.setAlignment(Alignment.CENTRE);
		wcf.setBackground(Colour.GOLD);
		WritableFont arial10Bold = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD);
		wcf.setFont(arial10Bold);
		sheet.setColumnView(0, 20);
		sheet.setColumnView(1, 20);
		sheet.setColumnView(2, 20);
		sheet.setColumnView(3, 20);
		sheet.setColumnView(4, 20);

		sheet.addCell(new Label(0, 0, "����� ��ȣ", wcf));
		sheet.addCell(new Label(1, 0, "��ǰŰ", wcf));
		sheet.addCell(new Label(2, 0, "�����", wcf));
		sheet.addCell(new Label(3, 0, "â��", wcf));	
		sheet.addCell(new Label(4, 0, "���� ��¥", wcf));	


		List<ProductDTO> list = dao.getIoStockViewExcel();
		int j = 1;
		for (ProductDTO emp : list) {
			Label lblIoStock = new Label(0, j, emp.getIoStock());
			Label lblProductId = new Label(1, j, emp.getProductId());
			Label lblIoStockNow = new Label(2, j, emp.getIoStockNow());
			Label lblWarehouseName = new Label(3, j, emp.getWarehouseName());
			Label lblIoStockDate = new Label(4, j, emp.getIoStock());

			sheet.addCell(lblIoStock);
			sheet.addCell(lblProductId);
			sheet.addCell(lblIoStockNow);
			sheet.addCell(lblWarehouseName);
			sheet.addCell(lblIoStockDate);
			j++;
		}
		workbook.write();
		workbook.close();
		System.out.println("����������������������Excel ���� ���� �Ϸᦡ��������������������");
		System.out.println();
	}

	public List<ProductDTO> getIoStockViewExcel() {
		getConection();

		ProductDTO dto = null;
		List<ProductDTO> list = new ArrayList<>();

		String sql = "select iostock_id, product_id, iostock_now, warehouse_name from iostock";

		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();

			while(rs.next()) {
				dto = new ProductDTO();
				dto.setIoStock(rs.getString("iostock_id"));
				dto.setProductId(rs.getString("product_id"));
				dto.setIoStockNow(rs.getString("iostock_now"));
				dto.setWarehouseName(rs.getString("warehouse_name"));
				list.add(dto);
			}        		
		}catch(SQLException e) {
			e.printStackTrace();
		}	    
		return list;
	}
}
