package com.agentmanage.module.user.service.impl;

import com.agentmanage.exception.AmServiceException;
import com.agentmanage.module.agent.entity.AgentInfoPo;
import com.agentmanage.module.agent.service.IAgentService;
import com.agentmanage.module.user.entity.User;
import com.agentmanage.module.user.mapper.UserMapper;
import com.agentmanage.module.user.service.IUserService;
import com.agentmanage.module.user.vo.UserSession;
import com.agentmanage.utils.SecurityUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * User Service实现类
 * on 2016/11/22.
 */
@Service
public class UserServiceImpl implements IUserService {

    @Resource
    private UserMapper userMapper;
    @Resource
    private IAgentService agentService;

    /**
     * 新增账户
     * @param userName
     * @param password
     */
    @Override
    public User save(String userName, String password) throws Exception{
        if (!validateConstantUser(userName)) {
            String salt = SecurityUtil.generateSalt();
            String passwordMd5 = SecurityUtil.generateUserPassword(salt, password);
            User user = new User(userName, passwordMd5, salt);
            userMapper.insert(user);
            return user;
        }
        throw new AmServiceException("存在同名账户");
    }

    public boolean validateConstantUser(String userName) {
        User user = userMapper.selectByUserName(userName);
        if (user != null) {
            return true;
        }
        return false;
    }

    /**
     * 更新密码
     * @param userId
     * @param password
     */
    @Override
    public void update(Integer userId, String password) {
        User user = userMapper.selectById(userId);
        if (user != null) {
            String passwordMd5 = SecurityUtil.generateUserPassword(user.getSalt(), password);
            user.setPassword(passwordMd5);
            userMapper.update(user);
        }
    }

    /**
     * 根据用户名查询用户信息
     * @param userName
     * @return
     */
    @Override
    public User getByUserName(String userName) {
        return userMapper.selectByUserName(userName);
    }

    /**
     * 用户登录
     * @param userName
     * @param password
     * @return
     */
    @Override
    public UserSession login(String userName, String password) throws AmServiceException {
        User user = userMapper.selectByUserName(userName);
        if (user != null) {
            String tempPassword = SecurityUtil.generateUserPassword(user.getSalt(), password);
            if (tempPassword.equals(user.getPassword())) {
                AgentInfoPo agentInfo = agentService.getByUserId(user.getId());
                return new UserSession(user, agentInfo);
            }
            throw new AmServiceException("账户密码不匹配");
        }
        throw new AmServiceException("账户信息不存在");
    }
}
