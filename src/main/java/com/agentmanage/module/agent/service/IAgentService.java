package com.agentmanage.module.agent.service;

import com.agentmanage.module.agent.entity.AgentInfoPo;
import com.agentmanage.plugin.page.Pageable;

import java.math.BigDecimal;
import java.util.List;

/**
 * 代理人业务接口
 * on 2016/11/20.
 */
public interface IAgentService {

    /**
     * 新增代理人
     * @param mobileNo
     * @param realName
     * @param alipayNo
     * @param agentPercent
     * @param parentAgentId
     * @param userId
     */
    void save(String mobileNo, String realName, String alipayNo, BigDecimal agentPercent,
              Integer parentAgentId, Integer userId) throws Exception;

    /**
     * 更新代理人信息
     * @param id
     * @param agentPercent
     */
    void modify(Integer id, BigDecimal agentPercent);

    /**
     * 根据代理人ID查询代理人信息
     * @param agentId
     * @return
     */
    AgentInfoPo getById(Integer agentId);

    /**
     * 根据账户ID查询代理人信息
     * @param userId
     * @return
     */
    AgentInfoPo getByUserId(Integer userId);

    /**
     * 根据商户ID代理人信息
     * @param realName
     * @return
     */
    AgentInfoPo getByRealName(String realName);

    /**
     * 查询下级代理人信息
     * @param pageable
     * @return
     */
    List<AgentInfoPo> getSubList(String realName, String mobileNo, Integer parentAgentId, Pageable pageable);

    /**
     * 校验商户ID是否已经存在
     * @param realName
     * @param agentId
     * @return
     */
    boolean checkExistsRealName(String realName, Integer agentId);

}
