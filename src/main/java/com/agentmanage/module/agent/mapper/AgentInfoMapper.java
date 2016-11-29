package com.agentmanage.module.agent.mapper;

import com.agentmanage.module.agent.entity.AgentInfoPo;
import com.agentmanage.plugin.page.Filter;

import java.util.List;

/**
 * Created by chenlx on 2016/11/20.
 */
public interface AgentInfoMapper {

    /**
     * 新增
     * @param agentInfo
     */
    void insert(AgentInfoPo agentInfo);

    /**
     * 更新
     * @param agentInfo
     */
    void update(AgentInfoPo agentInfo);

    /**
     * 根据ID查询代理人信息
     * @param id
     * @return
     */
    AgentInfoPo selectById(Integer id);

    /**
     * 根据登录账户ID查询代理人信息
     * @param userId
     * @return
     */
    AgentInfoPo selectByUserId(Integer userId);

    /**
     * 根据商户ID查询代理人信息
     * @param merchantId
     * @return
     */
    AgentInfoPo selectByMerchantId(String merchantId);

    /**
     * 查询下级代理人列表
     * @param filter
     * @return
     */
    List<AgentInfoPo> selectSubList(Filter filter);
}
