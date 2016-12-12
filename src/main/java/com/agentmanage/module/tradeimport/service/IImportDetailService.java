package com.agentmanage.module.tradeimport.service;

import com.agentmanage.module.tradeimport.entity.ImportDetailPo;
import com.agentmanage.plugin.page.Pageable;

import java.util.List;
import java.util.Map;

/**
 * 导入记录明细Service
 * on 2016/11/27.
 */
public interface IImportDetailService {

    /**
     * 新增导入记录明细
     * @param importDetail
     */
    void save(ImportDetailPo importDetail);

    /**
     * 查询导入明细
     * @param logId
     * @return
     */
    List<ImportDetailPo> getList(Integer logId, Pageable pageable);
}
