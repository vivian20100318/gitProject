/**
 * @(#) Paginator.java Created on 2009-7-21
 *
 * Copyright (c) 2009 Aspire. All Rights Reserved
 */
package com.hjkj.web.controller;

import java.io.Serializable;
import java.util.List;

/**
 * The class <code>Paginator</code>
 * 
 * @author linjunsui
 * @version 1.0
 */
public class Paginator<E> implements Serializable{
    
    /**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

    /**
     * 缺省页面大小
     */
    public final static int DEFAULT_PAGE_SIZE = 10;

    /**
     * 总行�?
     */
    private int rowsAmount = 0;

    /**
     * 每页行数
     */
    private int pageSize = 10;

    /**
     * 当前页码
     */
    private int currentPage = 1;

    /**
     * 当前位置,首个记录的position�?0
     */
    private int position = 0;
    
    /**
     * The List of records
     */
    private List<E> records;

    /**
     * 构�?�函�?
     */
    public Paginator() {
        pageSize = DEFAULT_PAGE_SIZE;
    }

    /**
     * 带页面大小的构�?�函�?
     * 
     * @param pageSize
     */
    public Paginator(int pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * 获取任一页第�?条数据在数据库中的位�?
     */
    protected static int getStartOfPage(int pageNo, int pageSize) {
        int startIndex = (pageNo - 1) * pageSize;
        return startIndex;
    }

    /**
     * 总行数，由Server设置
     * @param i 行数
     */
    public void setRowsAmount(int i) {
        rowsAmount = i;
        if (i > 0) {
            // 如果当前页大于�?�页数设置当前页
            if (currentPage > getTotalPages()) {
                setCurrentPage(getTotalPages());
            }
        }
    }

    /**
     * setter of currentPage
     */
    public void setCurrentPage(int currentPageNumber) {
        if (currentPageNumber > 0){
            currentPage = currentPageNumber;
            this.position = (currentPage - 1) * this.pageSize;
        }
    }

    /**
     * Get currentPage, if currentpage > total page, return total page otherwise
     * return currentpage
     * 
     * @return
     */
    public int getCurrentPage() {
        return currentPage <= getTotalPages() ? currentPage : getTotalPages();
    }

    /**
     * whether there's next page
     * 
     * @return true if hasnext
     */
    public boolean hasNext() {
        return currentPage < getTotalPages();
    }

    /**
     * whether there's previous page
     * 
     * @return true if hasnext
     */
    public boolean hasPrevious() {
        return currentPage > 1;
    }

    /**
     * Get next page
     * @return
     */
    public int getNextPage() {
        return currentPage < getTotalPages() ? currentPage + 1 : currentPage;
    }

    /**
     * Getter of pagesize
     * 
     * @return
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * Get previous page
     * 
     * @return
     */
    public int getPreviousPage() {
        return currentPage > 1 ? currentPage - 1 : currentPage;
    }

    /**
     * Get totalpages
     * 
     * @return
     */
    public int getTotalPages() {
        return (rowsAmount + pageSize - 1) / pageSize;
    }

    /**
     * Getter of rows amount
     * 
     * @return
     */
    public int getRowsAmount() {
        return rowsAmount;
    }

    /**
     * Setter of page size
     * 
     * @param size
     *            : should always > 0, nothing happen when <= 0.
     */
    public void setPageSize(int size) {
        if (size > 0) {
            pageSize = size;
            position = (currentPage - 1) * this.pageSize;
        }
    }

    /**
     * Getter of position
     * 
     * @return
     */
    public int getPosition() {
        return position;
    }

    /**
     * Setter of position
     * @param position
     */
    public void setPosition(int position) {
        this.position = position;
    }

    /**
     * move to next page, set current page to next page
     */
    public void next() {
        setCurrentPage(getNextPage());
    }
    
    /**
     * move to previous page, set current page to previous page
     */
    public void previous() {
        setCurrentPage(getPreviousPage());
    }
    
    /**
     * move to first page, set current page to first page
     */
    public void first() {
        setCurrentPage(1);
    }
    
    /**
     * move to last page, set current page to last page
     */
    public void last() {
        setCurrentPage(getTotalPages());
    }

	public List<E> getRecords() {
		return records;
	}

	public void setRecords(List<E> records) {
		this.records = records;
	}
}