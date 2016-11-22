package com.agentmanage.module.user.service;

import com.agentmanage.module.user.entity.User;
import com.agentmanage.module.user.vo.UserSession;

/**
 * User Service
 * on 2016/11/22.
 */
public interface UserService {

    /**
     * 新增账户
     * @param userName
     * @param password
     */
    void save(String userName, String password);

    /**
     * 更新密码
     * @param userId
     * @param password
     */
    void update(Integer userId, String password);

    /**
     * 根据用户名查询用户信息
     * @param userName
     * @return
     */
    User getByUserName(String userName);

    /**
     * 用户登录
     * @param userName
     * @param password
     * @return
     */
    UserSession login(String userName, String password);
}
