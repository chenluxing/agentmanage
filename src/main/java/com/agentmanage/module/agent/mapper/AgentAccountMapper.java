package com.agentmanage.module.agent.mapper;

import com.agentmanage.module.agent.entity.AgentAccountPo;

/**
 * 代理人账户Mapper
 * on 2016/11/22.
 */
public interface AgentAccountMapper {

    /**
     * 新增账户信息
     * @param agentAccount
     */
    void insert(AgentAccountPo agentAccount);

    /**
     * 更新账户余额信息
     * @param agentAccount
     */
    void update(AgentAccountPo agentAccount);

    /**
     * 根据代理人ID查询账户信息
     * @param agentId
     * @return
     */
    AgentAccountPo selectByAgentId(Integer agentId);
}
