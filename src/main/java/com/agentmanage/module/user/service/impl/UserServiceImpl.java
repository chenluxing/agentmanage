package com.agentmanage.module.user.service.impl;

import com.agentmanage.module.user.entity.User;
import com.agentmanage.module.user.service.UserService;
import com.agentmanage.module.user.vo.UserSession;
import org.springframework.stereotype.Service;

/**
 * User Service实现类
 * on 2016/11/22.
 */
@Service
public class UserServiceImpl implements UserService {

    /**
     * 新增账户
     * @param userName
     * @param password
     */
    @Override
    public void save(String userName, String password) {

    }

    /**
     * 更新密码
     * @param userId
     * @param password
     */
    @Override
    public void update(Integer userId, String password) {

    }

    /**
     * 根据用户名查询用户信息
     * @param userName
     * @return
     */
    @Override
    public User getByUserName(String userName) {
        return null;
    }

    /**
     * 用户登录
     * @param userName
     * @param password
     * @return
     */
    @Override
    public UserSession login(String userName, String password) {
        return null;
    }
}
