package com.agentmanage.module.tradeimport.mapper;

import com.agentmanage.module.tradeimport.entity.ImportLogPo;
import com.agentmanage.plugin.page.Filter;

import java.util.List;

/**
 * 导入日志DAO
 * on 2016/11/27.
 */
public interface ImportLogMapper {

    /**
     * 新增导入日志
     * @param importLog
     */
    void insert(ImportLogPo importLog);

    /**
     * 查询导入记录
     * @param filter
     * @return
     */
    List<ImportLogPo> selectList(Filter filter);

}
