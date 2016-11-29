package com.agentmanage.permission.filter;

import com.agentmanage.permission.vo.AmAuthenticationToken;
import com.agentmanage.utils.SecurityUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by chenlx
 * on 2016/11/15.
 */
public class AmAuthenticationFilter extends FormAuthenticationFilter {

    private static final String DEFAULT_EN_PASSWORD_PARAM = "enPassword";   // 默认"加密密码"参数名称
    private static final String DEFAULT_CAPTCHA_ID_PARAM = "captchaId";     // 默认"验证ID"参数名称
    private static final String DEFAULT_CAPTCHA_PARAM = "captcha";          // 默认"验证码"参数名称
    private static final String DEFAULT_PASSWORD = "0";
    private static final String DEFAULT_FAIL_COUNT = "failCount";           // 登录失败次数

    @Override
    protected boolean isLoginSubmission(ServletRequest request, ServletResponse response) {
        return true;
    }

    @Override
    protected AmAuthenticationToken createToken(ServletRequest servletRequest, ServletResponse servletResponse) {
        String username = getUsername(servletRequest);
        String password = getPassword(servletRequest);
        String captchaId = getCaptchaId(servletRequest);
        String captcha = getCaptcha(servletRequest);
        boolean rememberMe = isRememberMe(servletRequest);
        String host = getHost(servletRequest);
        Integer failCount = getFailCount(servletRequest);
        return new AmAuthenticationToken(username, password, captchaId, captcha, rememberMe, host, failCount);
    }

    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject,
        ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        Session session = subject.getSession();
        Map<Object, Object> attributes = new HashMap<>();
        Collection<Object> keys = session.getAttributeKeys();
        for (Object key : keys) {
            attributes.put(key, session.getAttribute(key));
        }
        session.stop();
        session = subject.getSession();
        for (Map.Entry<Object, Object> entry : attributes.entrySet()) {
            session.setAttribute(entry.getKey(), entry.getValue());
        }
        session.removeAttribute(WebUtils.SAVED_REQUEST_KEY);    // 清除session记录的request请求，使session失效后登录跳转到首页
        return super.onLoginSuccess(token, subject, servletRequest, servletResponse);
    }

    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e,
        ServletRequest request, ServletResponse response) {
        request.setAttribute("errMsg", e.getMessage());
        return super.onLoginFailure(token, e, request, response);
    }

    @Override
    public String getUsername(ServletRequest request) {
        return WebUtils.getCleanParam(request, DEFAULT_USERNAME_PARAM);
    }

    @Override
    protected String getPassword(ServletRequest servletRequest) {
        String password = servletRequest.getParameter(DEFAULT_EN_PASSWORD_PARAM);
        if (StringUtils.isBlank(password)){
            return DEFAULT_PASSWORD;
        }
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String enPassword = WebUtils.getCleanParam(request, DEFAULT_EN_PASSWORD_PARAM);
        return SecurityUtil.RSADecrypt(enPassword, request);
    }

    protected String getCaptchaId(ServletRequest servletRequest) {
        String captchaId = WebUtils.getCleanParam(servletRequest, DEFAULT_CAPTCHA_ID_PARAM);
        if (captchaId == null) {
            captchaId = ((HttpServletRequest) servletRequest).getSession().getId();
        }
        return captchaId;
    }

    protected String getCaptcha(ServletRequest servletRequest) {
        return WebUtils.getCleanParam(servletRequest, DEFAULT_CAPTCHA_PARAM);
    }

    /**
     * 获取登录失败次数
     * @param servletRequest
     * @return
     */
    public Integer getFailCount(ServletRequest servletRequest){
        String countStr = servletRequest.getParameter(DEFAULT_FAIL_COUNT);
        return StringUtils.isBlank(countStr) ? null : Integer.parseInt(countStr);
    }
}
