package com.agentmanage.module.account.entity;

import com.agentmanage.module.common.entity.BaseEntity;

import java.math.BigDecimal;

/**
 * 代理人账户信息
 */
public class AgentAccount extends BaseEntity {

    private Integer agentId;                // 代理人ID
    private BigDecimal totalAmount;         // 订单总额
    private BigDecimal totalAgentAmount;    // 佣金总额

    public Integer getAgentId() {
        return agentId;
    }

    public void setAgentId(Integer agentId) {
        this.agentId = agentId;
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
}
