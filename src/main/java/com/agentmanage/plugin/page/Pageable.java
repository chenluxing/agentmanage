package com.agentmanage.plugin.page;

import java.io.Serializable;

/**
 * 分页条件
 * on 2016/11/28.
 */
public class Pageable implements Serializable {

    private static final long serialVersionUID = -3930180379790344299L;

    // 默认页码
    private static final int DEFAULT_PAGE_NUMBER = 1;

    // 默认每页记录数
    private static final int DEFAULT_PAGE_SIZE = 10;

    // 最大每页记录数
    private static final int MAX_PAGE_SIZE = 100;

    // 每页记录数
    private Integer pageSize = DEFAULT_PAGE_SIZE;

    // 页码
    private Integer pageNumber = DEFAULT_PAGE_NUMBER;

    // 分页起始行（0为第一行）
    private Integer pageStart = 0;

    // 筛选
    private Filter filter = new Filter();

    /**
     * 初始化一个新创建的Pageable对象
     */
    public Pageable() {
    }

    /**
     * 初始化一个新创建的Pageable对象
     * @param pageNumber 页码
     * @param pageSize  每页记录数
     */
    public Pageable(Integer pageNumber, Integer pageSize) {
        if (pageNumber != null && pageNumber >= 1) {
            this.pageNumber = pageNumber;
        }
        if (pageSize != null && pageSize >= 1 && pageSize <= MAX_PAGE_SIZE) {
            this.pageSize = pageSize;
        }
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        if (pageSize == null || pageSize < 1 || pageSize > MAX_PAGE_SIZE) {
            pageSize = DEFAULT_PAGE_SIZE;
        }
        this.pageSize = pageSize;

        setPageNumber(pageNumber);
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        if (pageNumber == null || pageNumber < 1) {
            pageNumber = DEFAULT_PAGE_NUMBER;
        }
        this.pageNumber = pageNumber;
        this.pageStart = (pageNumber - 1) * pageSize;
    }

    public Integer getPageStart() {
        return pageStart;
    }

    public void setPageStart(Integer pageStart) {
        this.pageStart = pageStart;
    }

    public Filter getFilter() {
        return filter;
    }

    public void setFilter(Filter filter) {
        this.filter = filter;
    }
}
