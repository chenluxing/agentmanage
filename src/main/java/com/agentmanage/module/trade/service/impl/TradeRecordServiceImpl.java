package com.agentmanage.module.trade.service.impl;

import com.agentmanage.exception.AmServiceException;
import com.agentmanage.module.agent.service.IAgentAccountService;
import com.agentmanage.module.trade.entity.TradeRecordPo;
import com.agentmanage.module.trade.entity.TradeRecordVo;
import com.agentmanage.module.trade.mapper.TradeRecordMapper;
import com.agentmanage.module.trade.service.ITradeRecordService;
import com.agentmanage.module.agent.entity.AgentInfoPo;
import com.agentmanage.module.agent.service.IAgentService;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 交易记录Service实现类
 * on 2016/11/22.
 */
@Service
public class TradeRecordServiceImpl implements ITradeRecordService {

    @Resource
    private TradeRecordMapper tradeRecordMapper;
    @Resource
    private IAgentService agentService;
    @Resource
    private IAgentAccountService agentAccountService;

    /**
     * 新增交易记录
     * @param agentId
     * @param tradeAmount
     * @param tradeCount
     */
    @Override
    public void save(Integer agentId, BigDecimal tradeAmount, int tradeCount) {
        AgentInfoPo agentInfo = agentService.getByUserId(agentId);
        if (agentInfo != null) {
            BigDecimal agentAmount = tradeAmount.multiply(agentInfo.getAgentPercent()).setScale(2, BigDecimal.ROUND_HALF_UP);
            TradeRecordPo tradeRecord = new TradeRecordPo(agentId, tradeCount, tradeAmount, agentInfo.getAgentPercent(), agentAmount);
            tradeRecordMapper.insert(tradeRecord);

            agentAccountService.addAgentAmount(agentId, tradeAmount, agentAmount, tradeRecord.getId());

        }
    }

    /**
     * 新增交易记录
     * @param merchantId
     * @param tradeAmount
     * @param tradeCount
     */
    @Override
    public void save(String merchantId, BigDecimal tradeAmount, int tradeCount) {
        AgentInfoPo agentInfo = agentService.getByMerchantId(merchantId);
        if (agentInfo != null) {
            save(agentInfo.getId(), tradeAmount, tradeCount);
        } else {
            throw new AmServiceException("商户ID对应的代理人信息不存在");
        }
    }

    /**
     * 查询代理人交易明细
     * @param agentId
     * @return
     */
    @Override
    public List<TradeRecordVo> getVoListByAgentId(Integer agentId) {
        Assert.notNull(agentId, "代理人ID不允许为空");
        Map<String, Object> param = new HashMap<>();
        param.put("agentId", agentId);
        return getVoListByParam(param);
    }

    /**
     * 查询下级代理人交易明细
     * @param parentAgentId
     * @return
     */
    @Override
    public List<TradeRecordVo> getVoListByParentAgentId(Integer parentAgentId) {
        Assert.notNull(parentAgentId, "上级代理人ID不允许为空");
        Map<String, Object> param = new HashMap<>();
        param.put("parentAgentId", parentAgentId);
        return getVoListByParam(param);
    }

    /**
     * 条件查询代理人信息
     * @param param
     * @return
     */
    private List<TradeRecordVo> getVoListByParam(Map<String, Object> param) {
        return tradeRecordMapper.selectTradeRecordVoList(param);
    }
}
