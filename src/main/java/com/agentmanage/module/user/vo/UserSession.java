package com.agentmanage.module.user.vo;

import com.agentmanage.module.agent.entity.AgentInfoPo;
import com.agentmanage.module.user.entity.User;

import java.io.Serializable;

/**
 * 登录用户会话信息
 * on 2016/11/22.
 */
public class UserSession implements Serializable {

    private Integer userId;
    private Integer agentId;
    private String userName;
    private String realName;
    private String mobileNo;
    private Integer level;

    public UserSession(){}

    public UserSession(User user, AgentInfoPo agentInfo) {
        this.userId = user.getId();
        this.userName = user.getUserName();
        this.agentId = agentInfo.getId();
        this.realName = agentInfo.getRealName();
        this.mobileNo = agentInfo.getMobileNo();
        this.level = agentInfo.getLevel();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getAgentId() {
        return agentId;
    }

    public void setAgentId(Integer agentId) {
        this.agentId = agentId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
}
