package com.agentmanage.module.agent.service.impl;

import com.agentmanage.exception.AmServiceException;
import com.agentmanage.module.agent.entity.AccountDetailPo;
import com.agentmanage.module.agent.entity.AgentAccountPo;
import com.agentmanage.module.agent.mapper.AccountDetailMapper;
import com.agentmanage.module.agent.mapper.AgentAccountMapper;
import com.agentmanage.module.agent.service.IAgentAccountService;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * 代理人账户Service实现类
 * on 2016/11/22.
 */
@Resource
public class AgentAccountServiceImpl implements IAgentAccountService {

    @Resource
    private AgentAccountMapper agentAccountMapper;
    @Resource
    private AccountDetailMapper accountDetailMapper;

    /**
     * 新增代理人账户
     * @param agentId
     */
    @Override
    public void save(Integer agentId) {
        AgentAccountPo agentAccount = new AgentAccountPo(agentId);
        agentAccountMapper.insert(agentAccount);
    }

    /**
     * 变更账户金额
     * @param agentId
     * @param amount
     * @param agentAmount
     * @return
     */
    @Override
    public AgentAccountPo addAgentAmount(Integer agentId, BigDecimal amount, BigDecimal agentAmount) {
        return addAgentAmount(agentId, amount, agentAmount, null);
    }

    /**
     * 变更账户金额
     * @param agentId
     * @param amount
     * @param agentAmount
     * @return
     */
    @Override
    public AgentAccountPo addAgentAmount(Integer agentId, BigDecimal amount, BigDecimal agentAmount, Integer dataId) {
        AgentAccountPo agentAccount = agentAccountMapper.selectByAgentId(agentId);
        if (agentAccount != null) {
            // 更新账户总资产
            agentAccount.setTotalAmount(agentAccount.getTotalAmount().add(amount));
            agentAccount.setTotalAgentAmount(agentAccount.getTotalAgentAmount().add(agentAmount));
            agentAccountMapper.update(agentAccount);

            // 新增账户资金变更记录
            AccountDetailPo accountDetail = new AccountDetailPo(agentAccount.getId(), amount, agentAmount, agentAccount.getTotalAmount(),
                    agentAccount.getTotalAgentAmount(), dataId);
            accountDetailMapper.insert(accountDetail);
            return agentAccount;
        }
        throw new AmServiceException("资金账户信息不存在:" + agentId);
    }

    /**
     * 根据代理人ID获取代理人账户
     * @param agentId
     * @return
     */
    @Override
    public AgentAccountPo getByAgentId(Integer agentId) {
        return agentAccountMapper.selectByAgentId(agentId);
    }

}
