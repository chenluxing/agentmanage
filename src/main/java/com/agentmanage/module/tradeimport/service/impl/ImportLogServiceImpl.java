package com.agentmanage.module.tradeimport.service.impl;

import com.agentmanage.module.agent.entity.AgentInfoPo;
import com.agentmanage.module.agent.service.IAgentService;
import com.agentmanage.module.trade.entity.TradeRecordPo;
import com.agentmanage.module.trade.entity.TradeRecordVo;
import com.agentmanage.module.trade.service.ITradeRecordService;
import com.agentmanage.module.tradeimport.entity.ImportDetailPo;
import com.agentmanage.module.tradeimport.entity.ImportLogPo;
import com.agentmanage.module.tradeimport.mapper.ImportDetailMapper;
import com.agentmanage.module.tradeimport.mapper.ImportLogMapper;
import com.agentmanage.module.tradeimport.service.IImportDetailService;
import com.agentmanage.module.tradeimport.service.IImportLogService;
import com.agentmanage.plugin.excel.*;
import com.agentmanage.plugin.excel.vo.ExcelMessage;
import com.agentmanage.plugin.excel.vo.LineMessage;
import com.agentmanage.plugin.page.Filter;
import com.agentmanage.plugin.page.PageAdapter;
import com.agentmanage.plugin.page.Pageable;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import jxl.Sheet;
import jxl.Workbook;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.*;

/**
 * 导入记录Service
 * on 2016/11/27.
 */
@Service
public class ImportLogServiceImpl extends AbstractImportService implements IImportLogService {

    @Resource
    private ImportLogMapper importLogMapper;
    @Resource
    private IImportDetailService importDetailService;
    @Resource
    private IAgentService agentService;
    @Resource
    private ITradeRecordService tradeRecordService;

    @Override
    @Transactional
    public ExcelMessage save(InputStream inputStream, Integer curUserId) {
        ExcelMessage excelMessage = new ExcelMessage(ExcelMessage.Type.success);
        try {
            Workbook workbook = Workbook.getWorkbook(inputStream);
            Sheet[] sheets = workbook.getSheets();
            if (sheets != null && sheets.length > 0){
                Map<String, Object> baseMap = new HashMap<>();
                baseMap.put("totalTradeAmount", BigDecimal.ZERO);
                baseMap.put("totalTradeCount", 0);
                baseMap.put("usefulCount", 0);
                excelMessage = loadSheetData(sheets[0], 0, 1, getCellDefinedList(), baseMap);
                if(excelMessage.getType().equals(ExcelMessage.Type.success) && CollectionUtils.isNotEmpty(excelMessage.getDatas())) {
                    BigDecimal totalTradeAmount = (BigDecimal)baseMap.get("totalTradeAmount");
                    Integer totalTradeCount = (Integer)baseMap.get("totalTradeCount");
                    Integer usefulCount = (Integer) baseMap.get("usefulCount");
                    ImportLogPo importLogPo = new ImportLogPo(excelMessage.getDatas().size(), usefulCount, totalTradeAmount, totalTradeCount, curUserId);
                    importLogMapper.insert(importLogPo);

                    List<Object> dataList =  excelMessage.getDatas();
                    for (Object obj : dataList) {
                        Map data = (Map)obj;
                        Double tradeAmount_d = MapUtils.getDouble(data, "tradeAmount");
                        if (tradeAmount_d != null && tradeAmount_d != 0) {
                            BigDecimal tradeAmount = new BigDecimal(tradeAmount_d).setScale(2, BigDecimal.ROUND_HALF_UP);
                            String agentName = MapUtils.getString(data, "agentName");
                            Integer tradeCount = MapUtils.getInteger(data, "tradeCount");
                            Integer agentId = MapUtils.getInteger(data, "agentId");
                            Integer parentAgentId = MapUtils.getInteger(data, "parentAgentId");
                            Integer agentLevel = MapUtils.getInteger(data, "agentLevel");
                            ImportDetailPo detailPo = new ImportDetailPo(agentName, tradeAmount, tradeCount, agentId, parentAgentId, agentLevel, importLogPo.getId(), (byte)0);
                            importDetailService.save(detailPo);
                        }
                    }
                    generateTradeData(importLogPo.getId(), curUserId);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            LineMessage lineMessage = new LineMessage(0, "文件解析异常" + ex.getMessage());
            excelMessage.addErrorLine(lineMessage);
            excelMessage.setType(ExcelMessage.Type.error);
        }
        return excelMessage;
    }

    @Transactional
    public void generateTradeData(Integer logId, Integer curUserId) {
        List<Map> dataList = importDetailService.getCalcList(logId);
        Integer maxLevel = null;
        for (Map data : dataList) {
            Double tradeAmount_d = MapUtils.getDouble(data, "sum_trade_amount");
            if (tradeAmount_d != null && tradeAmount_d != 0) {
                BigDecimal tradeAmount = new BigDecimal(tradeAmount_d).setScale(2, BigDecimal.ROUND_HALF_UP);
                Integer tradeCount = MapUtils.getInteger(data, "sum_trade_count");
                Integer agentId = MapUtils.getInteger(data, "agent_id");
                Integer parentAgentId = MapUtils.getInteger(data, "parent_agent_id");
                Integer agentLevel = MapUtils.getInteger(data, "agent_level");
                ImportDetailPo detailPo = new ImportDetailPo(null, tradeAmount, tradeCount, agentId, parentAgentId, agentLevel, logId, (byte)1);
                importDetailService.save(detailPo);
                tradeRecordService.save(agentId, tradeAmount, tradeCount, curUserId);
                if (maxLevel == null || agentLevel.compareTo(maxLevel) > 0) {
                    maxLevel = agentLevel;
                }
            }
        }
        // 循环计算上级数据
        generateTradeData(logId, maxLevel, curUserId);
    }

    public void generateTradeData(Integer logId, Integer level, Integer curUserId) {
        if (level != null && level > 0) {
            List<Map> dataList = importDetailService.getCalcParentList(logId, level);
            for (Map data : dataList) {
                Double tradeAmount_d = MapUtils.getDouble(data, "sum_trade_amount");
                if (tradeAmount_d != null && tradeAmount_d != 0) {
                    BigDecimal tradeAmount = new BigDecimal(tradeAmount_d).setScale(2, BigDecimal.ROUND_HALF_UP);
                    Integer tradeCount = MapUtils.getInteger(data, "sum_trade_count");
                    Integer agentId = MapUtils.getInteger(data, "parent_agent_id");
                    AgentInfoPo agentInfo = agentService.getById(agentId);
                    ImportDetailPo detailPo = new ImportDetailPo(null, tradeAmount, tradeCount, agentId, agentInfo.getParentAgentId(), agentInfo.getLevel(), logId, (byte)1);
                    importDetailService.save(detailPo);
                    tradeRecordService.save(agentId, tradeAmount, tradeCount, curUserId);
                }
            }
            level--;
            generateTradeData(logId, level, curUserId);
        }else {
            return;
        }
    }


    /**
     * 查询导入记录
     * @param pageable
     * @return
     */
    @Override
    public List<ImportLogPo> getList(Date beginDate, Date endDate, Integer creatorId, Pageable pageable) {
        Assert.notNull(creatorId, "当前用户ID不允许为空");
        PageHelper.startPage(pageable.getPageNumber(), pageable.getPageSize());
        Filter filter = new Filter();
        filter.addParam("beginDate", beginDate);
        filter.addParam("endDate", endDate);
        filter.addParam("creatorId", creatorId);
        pageable.setFilter(filter);
        Page<ImportLogPo> page = (Page<ImportLogPo>) importLogMapper.selectList(pageable.getFilter());
        PageAdapter<ImportLogPo> pageAdapter = new PageAdapter<>(page, pageable);
        return pageAdapter.getPage();
    }

    @Override
    protected void validateCellData(String key, Map<String, Object> dataMap, Map<String, Object> baseMap) throws CellException {
        if ("agentName".equals(key)) {
            AgentInfoPo agentInfo = agentService.getByRealName(MapUtils.getString(dataMap, "agentName"));
            if (agentInfo != null) {
                dataMap.put("agentId", agentInfo.getId());
                dataMap.put("parentAgentId", agentInfo.getParentAgentId());
                dataMap.put("agentLevel", agentInfo.getLevel());
            } else {
                throw new CellException("代理人不存在");
            }
        }
    }

    @Override
    protected void validateRowData(Map dataMap, Map baseMap) throws CellException {
        Double tradeAmount = MapUtils.getDouble(dataMap, "tradeAmount");
        if (tradeAmount != null && tradeAmount != 0) {
            BigDecimal totalTradeAmount = (BigDecimal)baseMap.get("totalTradeAmount");
            totalTradeAmount = totalTradeAmount.add(new BigDecimal(tradeAmount));
            baseMap.put("totalTradeAmount", totalTradeAmount);
            Integer usefulCount = (Integer)baseMap.get("usefulCount");
            usefulCount++;
            baseMap.put("usefulCount", usefulCount);
        }
        Integer tradeCount = MapUtils.getInteger(dataMap, "tradeCount");
        if (tradeCount != null && tradeCount != 0) {
            Integer totalTradeCount = (Integer)baseMap.get("totalTradeCount");
            totalTradeCount = totalTradeCount + tradeCount;
            baseMap.put("totalTradeCount", totalTradeCount);
        }
    }

    private List<ICellDefined> getCellDefinedList() {
        List<ICellDefined> list = new ArrayList<>();
        CellStringDefined agentName = new CellStringDefined(0, "", "姓名", "agentName", true);
        CellBigDecimalDefined tradeAmount = new CellBigDecimalDefined(1, "", "交易金额", "tradeAmount", false);
        CellIntegerDefined tradeCount = new CellIntegerDefined(2, "", "交易笔数", "tradeCount", false, 0, null);
        list.add(agentName);
        list.add(tradeCount);
        list.add(tradeAmount);
        return list;
    }
}
