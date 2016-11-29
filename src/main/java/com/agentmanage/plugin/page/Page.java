package com.agentmanage.plugin.page;

import java.util.ArrayList;

/**
 * 分页
 *
 */
public class Page<T> extends ArrayList<T> {

    // 总记录数
    private long total = 0L;

    // 分页信息
    private Pageable pageable = new Pageable();

    public Page() {
    }

    /**
     * @param total    总记录数
     * @param pageable 分页信息
     */
    public Page(long total, Pageable pageable) {
        this.total = total;
        this.pageable = pageable;
    }

    /**
     * 获取页码
     */
    public int getPageNumber() {
        return pageable.getPageNumber();
    }

    /**
     * 获取每页记录数
     */
    public int getPageSize() {
        return pageable.getPageSize();
    }

    /**
     * 获取筛选
     */
    public Filter getFilter() {
        return pageable.getFilter();
    }

    /**
     * 获取总页数
     */
    public int getTotalPages() {
        return (int) Math.ceil((double) getTotal() / (double) getPageSize());
    }

    /**
     * 获取总记录数
     */
    public long getTotal() {
        return total;
    }

    /**
     * 设置总记录数
     *
     * @param total 总记录数
     */
    public void setTotal(long total) {
        this.total = total;
    }

    /**
     * 获取分页信息
     *
     * @return 分页信息
     */
    public Pageable getPageable() {
        return pageable;
    }

    /**
     * 设置分页信息
     *
     * @return 分页信息
     */
    public void setPageable(Pageable pageable) {
        this.pageable = pageable;
    }

    @Override
    public String toString() {
        return "Page{" + "total=" + total + ", pageable=" + pageable + '}';
    }
}