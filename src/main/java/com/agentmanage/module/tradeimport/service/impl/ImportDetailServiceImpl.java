package com.agentmanage.module.tradeimport.service.impl;

import com.agentmanage.module.tradeimport.entity.ImportDetailPo;
import com.agentmanage.module.tradeimport.entity.ImportLogPo;
import com.agentmanage.module.tradeimport.mapper.ImportDetailMapper;
import com.agentmanage.module.tradeimport.service.IImportDetailService;
import com.agentmanage.plugin.page.PageAdapter;
import com.agentmanage.plugin.page.Pageable;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

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

    /**
     * 查询导入明细
     * @param logId
     * @return
     */
    @Override
    public List<ImportDetailPo> getList(Integer logId, Pageable pageable) {
        Assert.notNull(logId, "日志ID不允许为空");
        PageHelper.startPage(pageable.getPageNumber(), pageable.getPageSize());
        Page<ImportDetailPo> page = (Page<ImportDetailPo>) importDetailMapper.selectList(logId);
        PageAdapter<ImportDetailPo> pageAdapter = new PageAdapter<>(page, pageable);
        return pageAdapter.getPage();

    }
}
