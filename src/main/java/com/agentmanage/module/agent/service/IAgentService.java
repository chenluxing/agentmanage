package com.agentmanage.module.agent.service;

import com.agentmanage.module.agent.entity.AgentInfoPo;

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
     * @param merchantId
     * @param alipayNo
     * @param agentPercent
     * @param parentAgentId
     * @param userId
     */
    void save(String mobileNo, String realName, String merchantId, String alipayNo, BigDecimal agentPercent,
              Integer parentAgentId, Integer userId) throws Exception;

    /**
     * 更新代理人信息
     * @param id
     * @param realName
     * @param merchantId
     * @param alipayNo
     * @param agentPercent
     */
    void modify(Integer id, String realName, String merchantId, String alipayNo, BigDecimal agentPercent);

    /**
     * 根据账户ID查询代理人信息
     * @param userId
     * @return
     */
    AgentInfoPo getByUserId(Integer userId);

    /**
     * 根据商户ID查询代理人信息
     * @param merchantId
     * @return
     */
    AgentInfoPo getByMerchantId(Integer merchantId);

    /**
     * 根据手机号查询代理人信息
     * @param mobileNo
     * @return
     */
    AgentInfoPo getByMobileNo(Integer mobileNo);

    /**
     * 查询下级代理人信息
     * @param parentAgentId
     * @return
     */
    List<AgentInfoPo> getListByParentId(Integer parentAgentId);
}
