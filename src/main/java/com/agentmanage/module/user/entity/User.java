package com.agentmanage.module.user.entity;

import com.agentmanage.module.common.entity.BaseEntity;
import org.apache.ibatis.type.Alias;

/**
 * 代理人基本信息
 * on 2016/11/20.
 */
@Alias("com.agentmanage.module.user.entity.User")
public class User extends BaseEntity {

    private String userName;        // 用户名
    private String password;        // 密码
    private String salt;            // 加密“佐料”

    public User() {}

    public User(String userName, String password, String salt) {
        this.userName = userName;
        this.password = password;
        this.salt = salt;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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
