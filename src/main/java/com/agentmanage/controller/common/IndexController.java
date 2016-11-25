package com.agentmanage.controller.common;

import com.agentmanage.controller.base.BaseController;
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
 * 首页Controller
 * on 2016/8/1.
 */
@Controller
public class IndexController extends BaseController {

    @RequestMapping("/index")
    public String getIndex(){
        return "main";
    }

}
