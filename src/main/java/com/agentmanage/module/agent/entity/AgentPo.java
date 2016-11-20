package com.agentmanage.module.agent.entity;

import com.agentmanage.module.common.entity.BaseEntity;
import org.springframework.data.annotation.TypeAlias;

/**
 * 代理人基本信息
 * on 2016/11/20.
 */
@TypeAlias("com.agentmanage.module.agent.entity.AgentPo")
public class AgentPo extends BaseEntity {

    private String mobileNo;        // 手机号
    private String password;        // 密码
    private String salt;            // 加密“佐料”

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }
}
