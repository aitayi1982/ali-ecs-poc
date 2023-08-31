package smart.poc.pojo;

import java.io.Serializable;

public class PageInfo implements Serializable {

	private static final long serialVersionUID = -3258051645498733092L;

	private int currPage;
	private int nextPage;
	private int prePage;
	private int firstPage;
	private int lastPage;
	private int pageSize;
	private int totolPages;
	private boolean disablePreLink;
	private boolean disableNextLink;

	public int getCurrPage() {
		return currPage;
	}

	public void setCurrPage(int currPage) {
		this.currPage = currPage;
	}

	public int getNextPage() {
		return nextPage;
	}

	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}

	public int getPrePage() {
		return prePage;
	}

	public void setPrePage(int prePage) {
		this.prePage = prePage;
	}

	public int getFirstPage() {
		return firstPage;
	}

	public void setFirstPage(int firstPage) {
		this.firstPage = firstPage;
	}

	public int getLastPage() {
		return lastPage;
	}

	public void setLastPage(int lastPage) {
		this.lastPage = lastPage;
	}

	@Override
	public String toString() {
		return "PageInfo [currPage=" + currPage + ", nextPage=" + nextPage + ", prePage=" + prePage + ", firstPage="
				+ firstPage + ", lastPage=" + lastPage + ", pageSize=" + pageSize + "]";
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public boolean isDisablePreLink() {
		return disablePreLink;
	}

	public void setDisablePreLink(boolean disablePreLink) {
		this.disablePreLink = disablePreLink;
	}

	public boolean isDisableNextLink() {
		return disableNextLink;
	}

	public void setDisableNextLink(boolean disableNextLink) {
		this.disableNextLink = disableNextLink;
	}

	public int getTotolPages() {
		return totolPages;
	}

	public void setTotolPages(int totolPages) {
		this.totolPages = totolPages;
	}

}
