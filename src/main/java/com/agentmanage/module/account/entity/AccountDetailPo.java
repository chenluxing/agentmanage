package com.agentmanage.module.account.entity;

import com.agentmanage.module.common.entity.BaseEntity;
import org.apache.ibatis.type.Alias;

import java.math.BigDecimal;

/**
 * 账户资金明细
 */
@Alias("com.agentmanage.module.account.entity.AccountDetailPo")
public class AccountDetailPo extends BaseEntity {

    private Integer accountId;              // 账户ID
    private BigDecimal tradeAmount;         // 交易金额
    private BigDecimal tradeAgentAmount;    // 交易佣金金额
    private BigDecimal totalAmount;         // 账户总
    private BigDecimal totalAgentAmount;    // 账户总佣金
    private Integer dataType;               // 数据来源
    private Integer dataId;                 // 数据来源ID
    private String remark;                  // 备注

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public BigDecimal getTradeAmount() {
        return tradeAmount;
    }

    public void setTradeAmount(BigDecimal tradeAmount) {
        this.tradeAmount = tradeAmount;
    }

    public BigDecimal getTradeAgentAmount() {
        return tradeAgentAmount;
    }

    public void setTradeAgentAmount(BigDecimal tradeAgentAmount) {
        this.tradeAgentAmount = tradeAgentAmount;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getTotalAgentAmount() {
        return totalAgentAmount;
    }

    public void setTotalAgentAmount(BigDecimal totalAgentAmount) {
        this.totalAgentAmount = totalAgentAmount;
    }

    public Integer getDataType() {
        return dataType;
    }

    public void setDataType(Integer dataType) {
        this.dataType = dataType;
    }

    public Integer getDataId() {
        return dataId;
    }

    public void setDataId(Integer dataId) {
        this.dataId = dataId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
