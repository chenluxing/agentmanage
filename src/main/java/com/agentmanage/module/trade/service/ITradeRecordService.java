package com.agentmanage.module.trade.service;

import com.agentmanage.module.trade.entity.TradeRecordVo;
import com.agentmanage.plugin.page.Pageable;

import java.math.BigDecimal;
import java.util.Date;
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
    void save(Integer agentId, BigDecimal tradeAmount, int tradeCount, Integer curUserId);

    /**
     * 新增交易记录
     * @param realName
     * @param tradeAmount
     * @param tradeCount
     */
    void save(String realName, BigDecimal tradeAmount, int tradeCount, Integer curUserId);

    /**
     * 迭代向上关联代理人增加交易记录
     * @param agentId
     * @param tradeAmount
     * @param tradeCount
     * @param curUserId
     */
    void saveToHead(Integer agentId, BigDecimal tradeAmount, int tradeCount, Integer curUserId);

    /**
     * 查询代理人交易明细
     * @param agentId
     * @return
     */
    List<TradeRecordVo> getVoListByAgentId(Integer agentId, Date beginDate, Date endDate, Pageable pageable);

    /**
     * 查询下级代理人交易明细
     * @param pageable
     * @return
     */
    List<TradeRecordVo> getVoListByParentAgentId(String realName, Date beginDate, Date endDate, Integer parentAgentId, Pageable pageable);

    /**
     * 查询所有代理人交易
     * @param realName
     * @param beginDate
     * @param endDate
     * @param pageable
     * @return
     */
    List<TradeRecordVo> getVoAll(String realName, Date beginDate, Date endDate, Pageable pageable);

    /**
     * 校验代理人
     * @param agentName
     * @return
     */
    boolean checkAgentName(String agentName);
}
