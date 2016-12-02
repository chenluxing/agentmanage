package com.agentmanage.module.tradeimport.entity;

import com.agentmanage.module.common.entity.BaseEntity;
import org.apache.ibatis.type.Alias;

import java.math.BigDecimal;

/**
 * 导入记录明细
 * on 2016/11/27.
 */
@Alias("com.agentmanage.module.tradeimport.entity.ImportDetailPo")
public class ImportDetailPo extends BaseEntity {

    private String merchantName;        // 商户姓名
    private String merchantId;          // 商户ID
    private BigDecimal tradeAmount;     // 交易金额
    private Integer tradeCount;         // 交易数量
    private Integer agentId;            // 代理人ID
    private Integer parentAgentId;      // 上级代理人ID
    private Integer agentLevel;              // 代理人层级
    private Integer logId;              // 日志ID
    private byte isOriginal;            // 是否原始数据0是 1否

    public ImportDetailPo(){}

    public ImportDetailPo(String merchantName, String merchantId, BigDecimal tradeAmount, Integer tradeCount, Integer agentId, Integer parentAgentId, Integer agentLevel, Integer logId, byte isOriginal) {
        this.merchantName = merchantName;
        this.merchantId = merchantId;
        this.tradeAmount = tradeAmount;
        this.tradeCount = tradeCount;
        this.agentId = agentId;
        this.parentAgentId = parentAgentId;
        this.agentLevel = agentLevel;
        this.logId = logId;
        this.isOriginal = isOriginal;
    }

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

    public BigDecimal getTradeAmount() {
        return tradeAmount;
    }

    public void setTradeAmount(BigDecimal tradeAmount) {
        this.tradeAmount = tradeAmount;
    }

    public Integer getTradeCount() {
        return tradeCount;
    }

    public void setTradeCount(Integer tradeCount) {
        this.tradeCount = tradeCount;
    }

    public Integer getAgentId() {
        return agentId;
    }

    public void setAgentId(Integer agentId) {
        this.agentId = agentId;
    }

    public Integer getParentAgentId() {
        return parentAgentId;
    }

    public void setParentAgentId(Integer parentAgentId) {
        this.parentAgentId = parentAgentId;
    }

    public Integer getAgentLevel() {
        return agentLevel;
    }

    public void setAgentLevel(Integer agentLevel) {
        this.agentLevel = agentLevel;
    }

    public Integer getLogId() {
        return logId;
    }

    public void setLogId(Integer logId) {
        this.logId = logId;
    }

    public byte getIsOriginal() {
        return isOriginal;
    }

    public void setIsOriginal(byte isOriginal) {
        this.isOriginal = isOriginal;
    }
}
