package com.agentmanage.module.tradeimport.service;

import com.agentmanage.module.tradeimport.entity.ImportLogPo;
import com.agentmanage.plugin.excel.vo.ExcelMessage;
import org.springframework.stereotype.Service;

import java.io.InputStream;
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
     * @param creatorId
     * @return
     */
    List<ImportLogPo> getList(Integer creatorId);

}
