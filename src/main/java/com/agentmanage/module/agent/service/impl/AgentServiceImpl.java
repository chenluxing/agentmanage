package com.agentmanage.module.agent.service.impl;

import com.agentmanage.exception.AmServiceException;
import com.agentmanage.module.agent.entity.AgentAccountPo;
import com.agentmanage.module.agent.entity.AgentInfoPo;
import com.agentmanage.module.agent.mapper.AgentAccountMapper;
import com.agentmanage.module.agent.mapper.AgentInfoMapper;
import com.agentmanage.module.agent.service.IAgentService;
import com.agentmanage.module.user.entity.User;
import com.agentmanage.module.user.service.IUserService;
import com.agentmanage.plugin.page.Filter;
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
     * @param alipayNo
     * @param agentPercent
     * @param parentAgentId
     * @param userId
     */
    @Override
    @Transactional
    public void save(String mobileNo, String realName, String alipayNo, BigDecimal agentPercent,
              Integer parentAgentId, Integer userId) throws Exception{
        User user = userService.getByUserName(mobileNo);
        // 校验用户信息
        if (user != null) {
            throw new AmServiceException("该账户已经存在");
        }
        // 校验同名代理人
        AgentInfoPo agentInfo = agentInfoMapper.selectByRealName(realName);
        if (agentInfo != null) {
            throw new AmServiceException("同名代理人已经存在");
        }

        String password = SecurityUtil.md5(alipayNo);
        user = userService.save(mobileNo, password);

        // 获取当前新增代理人的层级 = 父节点层级+1
        AgentInfoPo parentAgent = agentInfoMapper.selectById(parentAgentId);
        int level = parentAgent.getLevel() + 1;
        agentInfo = new AgentInfoPo(mobileNo, realName, alipayNo, agentPercent,parentAgentId, user.getId(), level);
        agentInfoMapper.insert(agentInfo);
        // 新增账户信息
        AgentAccountPo agentAccount = new AgentAccountPo(agentInfo.getId());
        agentAccountMapper.insert(agentAccount);
    }

    /**
     * 更新代理人信息
     * @param id
     * @param agentPercent
     */
    @Override
    @Transactional
    public void modify(Integer id, BigDecimal agentPercent) {
        AgentInfoPo agentInfo = agentInfoMapper.selectById(id);
        if (agentInfo != null) {
            agentInfo.setAgentPercent(agentPercent);
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
     * @param realName
     * @return
     */
    @Override
    public AgentInfoPo getByRealName(String realName) {
        return agentInfoMapper.selectByRealName(realName);
    }

    /**
     * 查询下级代理人信息
     * @param pageable
     * @return
     */
    @Override
    public List<AgentInfoPo> getSubList(String realName, String mobileNo, Integer parentAgentId, Pageable pageable) {
        PageHelper.startPage(pageable.getPageNumber(), pageable.getPageSize());
        Filter filter = new Filter();
        filter.addParam("realName", realName);
        filter.addParam("mobileNo", mobileNo);
        filter.addParam("parentAgentId", parentAgentId);
        Page<AgentInfoPo> page = (Page<AgentInfoPo>)agentInfoMapper.selectSubList(filter);
        PageAdapter<AgentInfoPo> pageAdapter = new PageAdapter<>(page, pageable);
        return pageAdapter.getPage();
    }

    /**
     * 查询全部代理人信息
     * @param pageable
     * @return
     */
    public List<AgentInfoPo> getAll(String realName, String mobileNo, Pageable pageable) {
        PageHelper.startPage(pageable.getPageNumber(), pageable.getPageSize());
        Filter filter = new Filter();
        filter.addParam("realName", realName);
        filter.addParam("mobileNo", mobileNo);
        Page<AgentInfoPo> page = (Page<AgentInfoPo>)agentInfoMapper.selectAll(filter);
        PageAdapter<AgentInfoPo> pageAdapter = new PageAdapter<>(page, pageable);
        return pageAdapter.getPage();
    }

    /**
     * 校验商户ID是否已经存在
     * @param realName
     * @param agentId
     * @return
     */
    @Override
    public boolean checkExistsRealName(String realName, Integer agentId) {
        AgentInfoPo agentInfo = agentInfoMapper.selectByRealName(realName);
        if (agentInfo != null) {
            if (agentId != null && agentId.equals(agentInfo.getId())) {
                return false;
            }
            return true;
        }
        return false;
    }

}
