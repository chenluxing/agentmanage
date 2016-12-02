package com.agentmanage.controller.module.users;

import com.agentmanage.controller.base.BaseController;
import com.agentmanage.module.user.service.IUserService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * 账户Controller
 * on 2016/12/1.
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @Resource
    private IUserService userService;

    /**
     * 跳转至修改密码页面
     * @return
     */
    @RequestMapping(value = "/toChangePassword", method = {RequestMethod.GET, RequestMethod.POST})
    public String toChangePassword() {
        return "/users/change_password";
    }

    /**
     * 修改密码
     * @return
     */
    @RequestMapping(value = "/changePassword", method = {RequestMethod.POST})
    public String changePassword(String password, String newPassword) {
        if (StringUtils.isNotEmpty(newPassword)) {
            userService.modifyPassword(getCurUser().getUserId(), password, newPassword);
        }
        return "/users/change_password";
    }

    /**
     * 校验密码
     * @return
     */
    @RequestMapping(value = "/checkPassword", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Boolean checkPassword(String password) {
        if (StringUtils.isNotEmpty(password)) {
            return userService.checkPassword(getCurUser().getUserId(), password);
        }
        return false;
    }
}
