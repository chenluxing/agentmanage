package com.agentmanage.module.user.mapper;

import com.agentmanage.module.user.entity.User;

/**
 * 登录用户
 */
public interface UserMapper {

    /**
     * 新增用户
     * @param user
     */
    void insert(User user);

    /**
     * 更新用户
     * @param user
     */
    void update(User user);

    /**
     * 根据用户名查询用户
     * @param userName
     * @return
     */
    User selectByUserName(String userName);

}
