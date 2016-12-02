package com.agentmanage.module.user.service;

import com.agentmanage.exception.AmServiceException;
import com.agentmanage.module.user.entity.User;
import com.agentmanage.module.user.vo.UserSession;

/**
 * User Service
 * on 2016/11/22.
 */
public interface IUserService {

    /**
     * 新增账户
     * @param userName
     * @param password
     */
    User save(String userName, String password) throws Exception;

    /**
     * 更新密码
     * @param userId
     * @param password
     */
    void modifyPassword(Integer userId, String password, String newPassword);

    /**
     * 根据用户ID查询用户信息
     * @param userId
     * @return
     */
    User getById(Integer userId);

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
    UserSession login(String userName, String password) throws AmServiceException;

    /**
     * 校验密码
     * @param userId
     * @param password
     * @return
     */
    boolean checkPassword(Integer userId, String password);

    /**
     * 校验密码
     * @param userName
     * @return
     */
    boolean checkUserName(String userName);
}
