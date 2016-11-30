package com.agentmanage.permission.realm;

import com.agentmanage.exception.AmServiceException;
import com.agentmanage.module.common.constants.GlobalConstants;
import com.agentmanage.module.user.service.IUserService;
import com.agentmanage.module.user.vo.UserSession;
import com.agentmanage.permission.vo.AmAuthenticationToken;
import com.agentmanage.permission.vo.AmPrincipal;
import com.agentmanage.plugin.captcha.CaptchaUtil;
import com.agentmanage.utils.SecurityUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
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

    private IUserService userService;

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) {
        try {
            AmAuthenticationToken amAuthenticationToken = (AmAuthenticationToken)token;
            String username = amAuthenticationToken.getUsername();
            String captchaId = amAuthenticationToken.getCaptchaId();
            String captcha = amAuthenticationToken.getCaptcha();
            String ip = amAuthenticationToken.getHost();
            Integer failCount = amAuthenticationToken.getFailCount();
            if (failCount != null && failCount >= 3 && !CaptchaUtil.isValid(captchaId, captcha)) {
                throw new UnsupportedTokenException("验证码错误");
            }

            if (StringUtils.isNotEmpty(username)){
                String password = new String(amAuthenticationToken.getPassword());
                String md5Password = SecurityUtil.md5(password);
                UserSession userSession = getUserService().login(username, md5Password);
                SecurityUtils.getSubject().getSession().setAttribute(GlobalConstants.SESSION_CUR_USER, userSession);
                return new SimpleAuthenticationInfo(new AmPrincipal(userSession.getUserId(), userSession.getUserName(), userSession.getRealName()), password, getName());
            }
            throw new UnknownAccountException();
        } catch (Exception e){
            e.printStackTrace();
            if (e instanceof AmServiceException){
                throw new AuthenticationException(((AmServiceException) e).getMessage());
            }else if(e instanceof UnsupportedTokenException){
                throw e;
            }else if(e instanceof UnsupportedTokenException){
                throw e;
            }
            throw new AuthenticationException("");
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

    public IUserService getUserService() {
        return (IUserService)applicationContext.getBean("userServiceImpl");
    }

}
