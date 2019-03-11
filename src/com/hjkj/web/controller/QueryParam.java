package com.hjkj.web.controller;

import java.io.Serializable;

import com.hjkj.business.util.WebUtils;

/**
 * The class <code>QueryParam</code>
 * 
 * @author lixin_b
 * @version 1.0
 */
public class QueryParam implements Serializable{

	private static final long serialVersionUID = -364589574479043473L;

	private String currentPage;      //第几�?
	private String pageSize;        //记录�?
	int start;
	int end;
	public QueryParam() {
		this.currentPage = "1";
		this.pageSize = String.valueOf(Paginator.DEFAULT_PAGE_SIZE);
	}

	public String getCurrentPage() {
		return currentPage;
	}
	
	public int getCurrentPageAsInt() {
		return WebUtils.toInt(currentPage, 1);
	}

	public String getPageSize() {
		return pageSize;
	}
	
	public int getPageSizeAsInt() {
		return WebUtils.toInt(pageSize, Paginator.DEFAULT_PAGE_SIZE);
	}

	public void setCurrentPage(String currentPage) {
		this.currentPage = currentPage;
	}

	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}
}
