package stock_management;

public class SearchVO extends ProductDTO {
	
	private String searchCondition;
	private String searchKeyword;
	
	public String getSearchCondition() {
		return searchCondition;
	}
	public void setSearchCondition(String searchCondition) {
		this.searchCondition = searchCondition;
	}
	public String getSearchKeyword() {
		return searchKeyword;
	}
	public void setSearchKeyword(String searchKeyword) {
		this.searchKeyword = searchKeyword;
	}
	@Override
	public String toString() {	
		return "[검색 컬럼 : " + searchCondition + " , 검색 조건 : " + searchKeyword + "]";
	}

}
