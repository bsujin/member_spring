package kr.or.ddit.common.model;

public class PageSearchVo {
	private int page;
	private int pageSize;
//	private String userid;
//	private String usernm;
//	private String alias;
	private String keyword;
	private String searchType;


	public PageSearchVo() {}
	

	public int getPage() {
		
		return page == 0 ? 1 : page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getPageSize() {
		return pageSize == 0 ? 5 : pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getSearchType() {
		return searchType;
	}
	
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	
	public PageSearchVo(int page, int pageSize, String keyword, String searchType) {
		this.page = page;
		this.pageSize = pageSize;
		this.keyword = keyword;
		this.searchType = searchType;
	}
	public PageSearchVo(int page, int pageSize, String keyword) {
		this.page = page;
		this.pageSize = pageSize;
		this.keyword = keyword;
	}


	@Override
	public String toString() {
		return "PageSearchVo [page=" + page + ", pageSize=" + pageSize + ", keyword=" + keyword + ", searchType="
				+ searchType + "]";
	}

	
}
