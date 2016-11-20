package com.agentmanage.controller.common;

import com.agentmanage.controller.base.BaseController;
import com.agentmanage.plugin.encrypt.RSA;
import org.apache.commons.codec.binary.Base64;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

/**
 * Created by admin on 2016/8/1.
 */
@Controller
public class IndexController extends BaseController {

    private static final String PRIVATE_KEY_ATTRIBUTE_NAME = "RSA-PRIVATE";
    @Resource

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

    @RequestMapping("/index")
    public String getIndex(){
        return "main";
    }
}
