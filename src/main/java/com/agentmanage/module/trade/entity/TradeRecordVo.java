package com.agentmanage.module.trade.entity;

import org.apache.ibatis.type.Alias;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * on 2016/11/25.
 */
@Alias("com.agentmanage.module.trade.entity.TradeRecordVo")
public class TradeRecordVo implements Serializable {

    private Integer recordId;           // 记录ID
    private Integer agentId;            // 代理人ID
    private String merchantId;         // 商户ID
    private String agentName;           // 代理人姓名
    private String mobileNo;            // 手机号
    private int tradeCount;             // 交易订单数量
    private BigDecimal tradeAmount;     // 交易金额
    private BigDecimal tradeAgentPercent;    // 交易佣金比例（取生成记录时代理人的佣金比例）
    private BigDecimal agentAmount;     // 佣金金额
    private Integer creatorId;          // 创建者userId
    private Date gmtCreated;
    private Date gmtModified;

    public Integer getRecordId() {
        return recordId;
    }

    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
    }

    public Integer getAgentId() {
        return agentId;
    }

    public void setAgentId(Integer agentId) {
        this.agentId = agentId;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
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

    public BigDecimal getTradeAgentPercent() {
        return tradeAgentPercent;
    }

    public void setTradeAgentPercent(BigDecimal tradeAgentPercent) {
        this.tradeAgentPercent = tradeAgentPercent;
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

    public Date getGmtCreated() {
        return gmtCreated;
    }

    public void setGmtCreated(Date gmtCreated) {
        this.gmtCreated = gmtCreated;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }
}
