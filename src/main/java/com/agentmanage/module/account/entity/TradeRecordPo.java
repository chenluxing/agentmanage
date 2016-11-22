package com.agentmanage.module.account.entity;

import com.agentmanage.module.common.entity.BaseEntity;
import org.apache.ibatis.type.Alias;

import java.math.BigDecimal;

/**
 * 交易记录
 */
@Alias("com.agentmanage.module.account.entity.TradeRecordPo")
public class TradeRecordPo extends BaseEntity {

    private String merchantId;          // 商户ID
    private String merchantName;        // 商户姓名
    private int tradeCount;             // 交易订单数量
    private BigDecimal tradeAmount;     // 交易金额
    private BigDecimal agentPercent;    // 交易佣金比例（取生成记录时代理人的佣金比例）
    private BigDecimal agentAmount;     // 佣金金额

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
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
}
