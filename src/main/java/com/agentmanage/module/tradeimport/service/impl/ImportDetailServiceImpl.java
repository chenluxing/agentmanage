package com.agentmanage.module.tradeimport.service.impl;

import com.agentmanage.module.tradeimport.entity.ImportDetailPo;
import com.agentmanage.module.tradeimport.mapper.ImportDetailMapper;
import com.agentmanage.module.tradeimport.service.IImportDetailService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 导入记录明细Service
 * on 2016/11/27.
 */
@Service
public class ImportDetailServiceImpl implements IImportDetailService {

    @Resource
    private ImportDetailMapper importDetailMapper;

    @Override
    @Transactional
    public void save(ImportDetailPo importDetail) {
        importDetailMapper.insert(importDetail);
    }

    @Override
    public List<ImportDetailPo> getList(Integer logId, Integer isOriginal) {
        return importDetailMapper.selectList(logId, isOriginal);
    }

    @Override
    public List<Map> getCalcList(Integer logId) {
        return importDetailMapper.selectCalcList(logId);
    }

    @Override
    public List<Map> getCalcParentList(Integer logId, Integer agentLevel) {
        return importDetailMapper.selectCalcParentList(logId, agentLevel);
    }
}
