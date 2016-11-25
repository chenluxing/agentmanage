package com.agentmanage.module.agent.service.impl;

import com.agentmanage.exception.AmServiceException;
import com.agentmanage.module.agent.entity.AgentInfoPo;
import com.agentmanage.module.agent.mapper.AgentInfoMapper;
import com.agentmanage.module.agent.service.IAgentService;
import com.agentmanage.module.user.entity.User;
import com.agentmanage.module.user.service.IUserService;
import org.apache.commons.lang3.StringUtils;
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
        User user = userService.getByUserName(mobileNo);
        // 校验用户信息
        if (user != null) {
            throw new AmServiceException("该账户已经存在");
        }
        // 校验商户ID
        if (StringUtils.isNotEmpty(merchantId) && checkExistsMerchantId(merchantId, null)) {
            throw new AmServiceException("该商户ID已经存在");
        }
        user = userService.save(mobileNo, alipayNo);
        AgentInfoPo agentInfo = new AgentInfoPo(mobileNo, realName, merchantId, alipayNo, agentPercent,parentAgentId, user.getId());
        agentInfoMapper.insert(agentInfo);
    }

    /**
     * 校验商户ID是否已经存在
     * @param merchantId
     * @param agentId
     * @return
     */
    @Override
    public boolean checkExistsMerchantId(String merchantId, Integer agentId) {
        AgentInfoPo agentInfo = agentInfoMapper.selectByMerchantId(merchantId);
        if (agentInfo != null) {
            if (agentId != null && agentId.equals(agentInfo.getId())) {
                return false;
            }
            return true;
        }
        return false;
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
        AgentInfoPo agentInfo = agentInfoMapper.selectById(id);
        if (agentInfo != null) {
            agentInfo.setRealName(realName);
            agentInfo.setAlipayNo(alipayNo);
            agentInfo.setAgentPercent(agentPercent);
            // 商户ID仅允许更新一次
            if (StringUtils.isEmpty(agentInfo.getMerchantId()) && StringUtils.isNotEmpty(merchantId)) {
                // 校验商户ID是否已经存在
                if (!checkExistsMerchantId(merchantId, id)) {
                    agentInfo.setMerchantId(merchantId);
                }
            }
            agentInfoMapper.update(agentInfo);
        }
    }

    /**
     * 根据代理人ID查询代理人信息
     * @param agentId
     * @return
     */
    @Override
    public AgentInfoPo getById(Integer agentId) {
        return agentInfoMapper.selectById(agentId);
    }

    /**
     * 根据账户ID查询代理人信息
     * @param userId
     * @return
     */
    @Override
    public AgentInfoPo getByUserId(Integer userId) {
        return agentInfoMapper.selectByUserId(userId);
    }

    /**
     * 根据商户ID代理人信息
     * @param merchantId
     * @return
     */
    @Override
    public AgentInfoPo getByMerchantId(String merchantId) {
        return agentInfoMapper.selectByMerchantId(merchantId);
    }

    /**
     * 查询下级代理人信息
     * @param parentAgentId
     * @return
     */
    @Override
    public List<AgentInfoPo> getListByParentId(Integer parentAgentId) {
        return agentInfoMapper.selectChildListByAgentId(parentAgentId);
    }
}
