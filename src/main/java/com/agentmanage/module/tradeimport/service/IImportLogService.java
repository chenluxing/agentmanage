package com.agentmanage.module.tradeimport.service;

import com.agentmanage.module.tradeimport.entity.ImportLogPo;
import com.agentmanage.plugin.excel.vo.ExcelMessage;
import com.agentmanage.plugin.page.Pageable;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

/**
 * 导入记录Service
 * on 2016/11/27.
 */
public interface IImportLogService {


    /**
     * 新增导入记录
     * @param inputStream
     */
    ExcelMessage save(InputStream inputStream, Integer curUserId);

    /**
     * 查询导入记录
     * @param pageable
     * @return
     */
    List<ImportLogPo> getList(Date beginDate, Date endDate, Integer creatorId, Pageable pageable);

}
