package com.agentmanage.plugin.page;

import com.github.pagehelper.Page;

/**
 * mybatis分页插件的适配器 封装部分返回数据
 */
public class PageAdapter<E> {
    private com.agentmanage.plugin.page.Page<E> page;

    public PageAdapter(Page<E> page, Pageable pageable) {
        this.page = new com.agentmanage.plugin.page.Page<E>();
        this.page.addAll(page);
        this.page.setTotal(page.getTotal());
        this.page.setPageable(pageable);
    }

    public com.agentmanage.plugin.page.Page<E> getPage() {
        return this.page;
    }
}
