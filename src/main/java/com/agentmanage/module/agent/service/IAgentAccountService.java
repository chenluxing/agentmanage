package com.agentmanage.module.agent.service;

import com.agentmanage.module.agent.entity.AgentAccountPo;

import java.math.BigDecimal;

/**
 * 代理人账户Service
 * on 2016/11/22.
 */
public interface IAgentAccountService {

    /**
     * 新增代理人账户
     * @param agentId
     */
    void save(Integer agentId);

    /**
     * 变更账户金额
     * @param agentId
     * @param amount
     * @param agentAmount
     * @return
     */
    AgentAccountPo addAgentAmount(Integer agentId, BigDecimal amount, BigDecimal agentAmount);

    /**
     * 变更账户金额
     * @param agentId
     * @param amount
     * @param agentAmount
     * @return
     */
    AgentAccountPo addAgentAmount(Integer agentId, BigDecimal amount, BigDecimal agentAmount, Integer dataId);

    /**
     * 根据代理人ID获取代理人账户
     * @param agentId
     * @return
     */
    AgentAccountPo getByAgentId(Integer agentId);
}
