package com.agentmanage.module.trade.entity;

import com.agentmanage.module.common.entity.BaseEntity;
import org.apache.ibatis.type.Alias;

import java.math.BigDecimal;

/**
 * 交易记录
 */
@Alias("com.agentmanage.module.trade.entity.TradeRecordPo")
public class TradeRecordPo extends BaseEntity {

    private Integer agentId;            // 商户ID
    private int tradeCount;             // 交易订单数量
    private BigDecimal tradeAmount;     // 交易金额
    private BigDecimal agentPercent;    // 交易佣金比例（取生成记录时代理人的佣金比例）
    private BigDecimal agentAmount;     // 佣金金额
    private Integer creatorId;          // 创建者UserId

    public TradeRecordPo() {}

    public TradeRecordPo(Integer agentId, int tradeCount, BigDecimal tradeAmount, BigDecimal agentPercent, BigDecimal agentAmount, Integer creatorId) {
        this.agentId = agentId;
        this.tradeCount = tradeCount;
        this.tradeAmount = tradeAmount;
        this.agentPercent = agentPercent;
        this.agentAmount = agentAmount;
        this.creatorId = creatorId;
    }

    public Integer getAgentId() {
        return agentId;
    }

    public void setAgentId(Integer agentId) {
        this.agentId = agentId;
    }

    public int getTradeCount() {
        return tradeCount;
    }

    public void setTradeCount(int tradeCount) {
        this.tradeCount = tradeCount;
    }

    public BigDecimal getTradeAmount() {
        return tradeAmount;
    }

    public void setTradeAmount(BigDecimal tradeAmount) {
        this.tradeAmount = tradeAmount;
    }

    public BigDecimal getAgentPercent() {
        return agentPercent;
    }

    public void setAgentPercent(BigDecimal agentPercent) {
        this.agentPercent = agentPercent;
    }

    public BigDecimal getAgentAmount() {
        return agentAmount;
    }

    public void setAgentAmount(BigDecimal agentAmount) {
        this.agentAmount = agentAmount;
    }

    public Integer getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Integer creatorId) {
        this.creatorId = creatorId;
    }
}
