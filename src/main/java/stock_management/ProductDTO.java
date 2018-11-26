package stock_management;

import java.util.Formatter;

public class ProductDTO {
	private String productId;
	private String productName;
	private String productExp;
	private String basicStock;
	private String warehouseName;
	private String productDate;
	private String ioStock;
	private String ioStockNow;
	private String nowStock;
	private String IoStockDate;


	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductExp() {
		return productExp;
	}
	public void setProductExp(String productExp) {
		this.productExp = productExp;
	}
	public String getBasicStock() {
		return basicStock;
	}
	public void setBasicStock(String basicStock) {
		this.basicStock = basicStock;
	}
	public String getWarehouseName() {
		return warehouseName;
	}
	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
	}
	public String getProductDate() {
		return productDate;
	}
	public void setProductDate(String productDate) {
		this.productDate = productDate;
	}
	public String getIoStock() {
		return ioStock;
	}
	public void setIoStock(String ioStock) {
		this.ioStock = ioStock;
	}
	public String getIoStockNow() {
		return ioStockNow;
	}
	public void setIoStockNow(String ioStockNow) {
		this.ioStockNow = ioStockNow;
	}
	public String getNowStock() {
		return nowStock;
	}
	public void setNowStock(String nowStock) {
		this.nowStock = nowStock;
	}
	public String getIoStockDate() {
		return IoStockDate;
	}
	public void setIoStockDate(String ioStockDate) {
		IoStockDate = ioStockDate;
	}
	@Override
	public String toString() {
		Formatter fm = new Formatter();
		//String result = fm.format("%s\t %-10s\t %-30s\t\t %s\t %s\t %s\t", productId, productName, productExp, basicStock, warehouseName, productDate).toString();\
		String result = fm.format("%s\t %-10s\t %-30s\t\t\t %s\t", productId, productName, productExp, basicStock).toString();
		return result;
	}
	public String toString2() {
		Formatter fm = new Formatter();
		String result = fm.format("%s\t\t %s\t %s\t %s\t", ioStock, productId, ioStockNow, warehouseName).toString();
		return result;
	}
	public String toString3() {
		Formatter fm = new Formatter();
		String result = fm.format("%s\t %-10s\t %s    ", productId, nowStock, warehouseName).toString();
		return result;
	}
}
