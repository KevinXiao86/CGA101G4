package com.taiwan.beans;

import java.util.List;

public class Page <E> {
	
	//將每頁顯示數量定義為常量
	public static final Integer PAGE_SIZE = 4; 
	
	//當前頁碼
	private Integer pageNo;
	
	//總頁碼
	private Integer pageTotal;
	
	//當前頁顯示數量
	private Integer pageSize = PAGE_SIZE;
	
	//總紀錄數
	private Integer pageTotalCount;
	
	//當前頁數據
	//將集合裡面的元素設計成泛型,這樣的擴展性比較高
	private List<E> items;
	
	//分頁條的請求地址
	private String url;
	

	public Page() {
		super();
	}

	public Page(Integer pageNo, Integer pageTotal, Integer pageSize, Integer pageTotalCount, List<E> items) {
		super();
		this.pageNo = pageNo;
		this.pageTotal = pageTotal;
		this.pageSize = pageSize;
		this.pageTotalCount = pageTotalCount;
		this.items = items;
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		
		if (pageNo < 1) {
			pageNo = 1;
		}
		if (pageNo > pageTotal) {
			pageNo = pageTotal;
		}
		
		this.pageNo = pageNo;
	}

	public Integer getPageTotal() {
		return pageTotal;
	}

	public void setPageTotal(Integer pageTotal) {
		this.pageTotal = pageTotal;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getPageTotalCount() {
		return pageTotalCount;
	}

	public void setPageTotalCount(Integer pageTotalCount) {
		this.pageTotalCount = pageTotalCount;
	}

	public List<E> getItems() {
		return items;
	}

	public void setItems(List<E> items) {
		this.items = items;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "Page [pageNo=" + pageNo + ", pageTotal=" + pageTotal + ", pageSize=" + pageSize + ", pageTotalCount="
				+ pageTotalCount + ", items=" + items + ", url=" + url + "]";
	}
	

	
	
}
