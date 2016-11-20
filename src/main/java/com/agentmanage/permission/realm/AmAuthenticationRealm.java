package com.agentmanage.permission.realm;

import com.agentmanage.permission.vo.AmAuthenticationToken;
import com.agentmanage.permission.vo.AmPrincipal;
import com.agentmanage.plugin.captcha.CaptchaUtil;
import com.agentmanage.utils.SecurityUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.pam.UnsupportedTokenException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Created by chenlx
 * on 2016/11/15.
 */
public class AmAuthenticationRealm extends AuthorizingRealm implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        try {
            AmAuthenticationToken amAuthenticationToken = (AmAuthenticationToken)token;
            String username = amAuthenticationToken.getUsername();
            String captchaId = amAuthenticationToken.getCaptchaId();
            String captcha = amAuthenticationToken.getCaptcha();
            String ip = amAuthenticationToken.getHost();
            if (!CaptchaUtil.isValid(captchaId, captcha)) {
                throw new UnsupportedTokenException("验证码错误");
            }
            if (StringUtils.isNotEmpty(username)){
                String password = new String(amAuthenticationToken.getPassword());
                String md5Password = SecurityUtil.md5(password);
                return new SimpleAuthenticationInfo(new AmPrincipal(111, username, username), password, getName());
            }
            throw new UnknownAccountException();
        } catch (Exception e){
            e.printStackTrace();
            if(e instanceof UnsupportedTokenException){
                throw e;
            }else if(e instanceof UnsupportedTokenException){
                throw e;
            }
            throw new AuthenticationException("登录异常");
        }
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        AmPrincipal principal = (AmPrincipal) principals.fromRealm(getName()).iterator().next();
        if (principal != null) {
            SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
            /*List<String> menuList = principal.getMenuList();
            if (CollectionUtils.isNotEmpty(menuList)) {
                authorizationInfo.addStringPermissions(menuList);
            }*/
            return authorizationInfo;
        }
        return null;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
