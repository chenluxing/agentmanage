package com.agentmanage.module.trade.service;

import com.agentmanage.module.trade.entity.TradeRecordVo;

import java.math.BigDecimal;
import java.util.List;

/**
 * 交易记录Service
 * on 2016/11/22.
 */
public interface ITradeRecordService {

    /**
     * 新增交易记录
     * @param agentId
     * @param tradeAmount
     * @param tradeCount
     */
    void save(Integer agentId, BigDecimal tradeAmount, int tradeCount);

    /**
     * 新增交易记录
     * @param merchantId
     * @param tradeAmount
     * @param tradeCount
     */
    void save(String merchantId, BigDecimal tradeAmount, int tradeCount);

    /**
     * 查询代理人交易明细
     * @param agentId
     * @return
     */
    List<TradeRecordVo> getVoListByAgentId(Integer agentId);

    /**
     * 查询下级代理人交易明细
     * @param parentAgentId
     * @return
     */
    List<TradeRecordVo> getVoListByParentAgentId(Integer parentAgentId);
}
