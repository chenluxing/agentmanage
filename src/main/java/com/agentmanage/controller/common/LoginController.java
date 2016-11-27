package com.agentmanage.controller.common;

import com.agentmanage.controller.base.BaseController;
import com.agentmanage.module.common.constants.GlobalConstants;
import com.agentmanage.module.user.service.IUserService;
import com.agentmanage.plugin.encrypt.RSA;
import org.apache.commons.codec.binary.Base64;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

/**
 * 登录Controller
 * on 2016/8/1.
 */
@Controller
public class LoginController extends BaseController {

    private static final String PRIVATE_KEY_ATTRIBUTE_NAME = "RSA-PRIVATE";

    @Resource
    private IUserService userService;

    /**
     * 登录跳转
     * @param request
     * @return
     */
    @RequestMapping("/login")
    public String toLogin(HttpServletRequest request){
        KeyPair keyPair = RSA.generateKeyPair();
        RSAPublicKey publicKey = (RSAPublicKey)keyPair.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey)keyPair.getPrivate();
        HttpSession session = request.getSession();
        session.setAttribute(PRIVATE_KEY_ATTRIBUTE_NAME, privateKey);
        String modulus = Base64.encodeBase64String(publicKey.getModulus().toByteArray());
        String exponent = Base64.encodeBase64String(publicKey.getPublicExponent().toByteArray());
        request.setAttribute("modulus", modulus);
        request.setAttribute("exponent", exponent);
        request.setAttribute("captchaId", session.getId());
        return "login";
    }

    /**
     * 登录
     * @param request
     * @return
     */
    @RequestMapping("/loginIn")
    public String loginIn(HttpServletRequest request){
        if (SecurityUtils.getSubject().isAuthenticated()){
            return "/main";
        }else{
            return "redirect:/login.html";
        }
    }

    /**
     * 校验登录用户名
     * @return
     */
    @RequestMapping("/validateUserName")
    @ResponseBody
    public boolean validateUserName(String userName){
        return userService.getByUserName(userName) != null ? true : false;
    }

    /**
     * 退出
     * @param request
     * @return
     */
    @RequestMapping(value = "/logout")
    public String logout(HttpServletRequest request) {
        SecurityUtils.getSubject().logout();
        SecurityUtils.getSubject().getSession().removeAttribute(GlobalConstants.SESSION_CUR_USER);
        return toLogin(request);
    }
}
