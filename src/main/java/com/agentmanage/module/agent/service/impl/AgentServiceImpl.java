package com.agentmanage.module.agent.service.impl;

import com.agentmanage.module.agent.entity.AgentInfoPo;
import com.agentmanage.module.agent.mapper.AgentInfoMapper;
import com.agentmanage.module.agent.service.IAgentService;
import com.agentmanage.module.user.entity.User;
import com.agentmanage.module.user.service.IUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

/**
 * 代理人业务实现类
 * on 2016/11/20.
 */
@Service
public class AgentServiceImpl implements IAgentService {

    @Resource
    private AgentInfoMapper agentInfoMapper;
    @Resource
    private IUserService userService;

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
    @Override
    @Transactional
    public void save(String mobileNo, String realName, String merchantId, String alipayNo, BigDecimal agentPercent,
              Integer parentAgentId, Integer userId) throws Exception{
        User user = userService.save(mobileNo, alipayNo);
        AgentInfoPo agentInfo = new AgentInfoPo(mobileNo, realName, merchantId, alipayNo, agentPercent,
                parentAgentId, user.getId());
        agentInfoMapper.insert(agentInfo);
    }

    /**
     * 更新代理人信息
     * @param id
     * @param realName
     * @param merchantId
     * @param alipayNo
     * @param agentPercent
     */
    @Override
    public void modify(Integer id, String realName, String merchantId, String alipayNo, BigDecimal agentPercent) {

    }

    /**
     * 根据账户ID查询代理人信息
     * @param userId
     * @return
     */
    @Override
    public AgentInfoPo getByUserId(Integer userId) {
        return null;
    }

    /**
     * 根据商户ID查询代理人信息
     * @param merchantId
     * @return
     */
    @Override
    public AgentInfoPo getByMerchantId(Integer merchantId) {
        return null;
    }

    /**
     * 根据手机号查询代理人信息
     * @param mobileNo
     * @return
     */
    @Override
    public AgentInfoPo getByMobileNo(Integer mobileNo) {
        return null;
    }

    /**
     * 查询下级代理人信息
     * @param parentAgentId
     * @return
     */
    @Override
    public List<AgentInfoPo> getListByParentId(Integer parentAgentId) {
        return null;
    }
}
