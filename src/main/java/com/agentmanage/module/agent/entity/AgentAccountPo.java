package com.agentmanage.module.agent.entity;

import com.agentmanage.module.common.entity.BaseEntity;
import org.apache.ibatis.type.Alias;

import java.math.BigDecimal;

/**
 * 代理人账户信息
 */
@Alias("com.agentmanage.module.agent.entity.AgentAccountPo")
public class AgentAccountPo extends BaseEntity {

    private Integer agentId;                // 代理人ID
    private BigDecimal totalAmount;         // 订单总额
    private BigDecimal totalAgentAmount;    // 佣金总额

    public AgentAccountPo(){}

    public AgentAccountPo(Integer agentId) {
        this.agentId = agentId;
        this.totalAmount = BigDecimal.ZERO;
        this.totalAgentAmount = BigDecimal.ZERO;
    }

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
