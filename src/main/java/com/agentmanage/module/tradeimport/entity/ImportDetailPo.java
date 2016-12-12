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

    private String agentName;           // 姓名
    private BigDecimal tradeAmount;     // 交易金额
    private Integer tradeCount;         // 交易数量
    private Integer agentId;            // 代理人ID
    private Integer logId;              // 日志ID
    private byte isOriginal;            // 是否原始数据0是 1否

    public ImportDetailPo(){}

    public ImportDetailPo(String agentName, BigDecimal tradeAmount, Integer tradeCount, Integer agentId, Integer logId, byte isOriginal) {
        this.agentName = agentName;
        this.tradeAmount = tradeAmount;
        this.tradeCount = tradeCount;
        this.agentId = agentId;
        this.logId = logId;
        this.isOriginal = isOriginal;
    }

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
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
