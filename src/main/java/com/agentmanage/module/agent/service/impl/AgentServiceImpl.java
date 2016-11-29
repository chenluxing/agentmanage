package com.agentmanage.module.agent.service.impl;

import com.agentmanage.exception.AmServiceException;
import com.agentmanage.module.agent.entity.AgentAccountPo;
import com.agentmanage.module.agent.entity.AgentInfoPo;
import com.agentmanage.module.agent.mapper.AgentAccountMapper;
import com.agentmanage.module.agent.mapper.AgentInfoMapper;
import com.agentmanage.module.agent.service.IAgentService;
import com.agentmanage.module.user.entity.User;
import com.agentmanage.module.user.service.IUserService;
import com.agentmanage.plugin.page.PageAdapter;
import com.agentmanage.plugin.page.Pageable;
import com.agentmanage.utils.SecurityUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
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
    private AgentAccountMapper agentAccountMapper;
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
        String password = SecurityUtil.md5(alipayNo);
        user = userService.save(mobileNo, password);

        // 获取当前新增代理人的层级 = 父节点层级+1
        AgentInfoPo parentAgent = agentInfoMapper.selectById(parentAgentId);
        int level = parentAgent.getLevel() + 1;
        AgentInfoPo agentInfo = new AgentInfoPo(mobileNo, realName, merchantId, alipayNo, agentPercent,parentAgentId, user.getId(), level);
        agentInfoMapper.insert(agentInfo);
        // 新增账户信息
        AgentAccountPo agentAccount = new AgentAccountPo(agentInfo.getId());
        agentAccountMapper.insert(agentAccount);
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
     * @param merchantId
     * @param agentPercent
     */
    @Override
    @Transactional
    public void modify(Integer id, String merchantId, BigDecimal agentPercent) {
        AgentInfoPo agentInfo = agentInfoMapper.selectById(id);
        if (agentInfo != null) {
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
     * @param pageable
     * @return
     */
    @Override
    public List<AgentInfoPo> getSubList(Pageable pageable) {
        PageHelper.startPage(pageable.getPageNumber(), pageable.getPageSize());
        Page<AgentInfoPo> page = (Page<AgentInfoPo>)agentInfoMapper.selectSubList(pageable.getFilter());
        PageAdapter<AgentInfoPo> pageAdapter = new PageAdapter<>(page, pageable);
        return pageAdapter.getPage();
    }
}
