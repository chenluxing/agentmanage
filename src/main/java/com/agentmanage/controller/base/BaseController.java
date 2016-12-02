package com.agentmanage.controller.base;

import com.agentmanage.module.common.constants.GlobalConstants;
import com.agentmanage.module.user.vo.UserSession;
import com.agentmanage.plugin.editor.BigDecimalEditor;
import com.agentmanage.plugin.editor.DateEditor;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 基础Controller
 * on 2016/7/25.
 */
public class BaseController {

    private static final Logger log = LoggerFactory.getLogger(BaseController.class);

    public static final String HTTP_REQUEST = "http_request";
    private static ThreadLocal<Map<String, Object>> localParams = new ThreadLocal<>();

    /**
     * 请求地址
     */
    protected String contextPath;

    protected UserSession getCurUser() {
        return (UserSession)SecurityUtils.getSubject().getSession().getAttribute(GlobalConstants.SESSION_CUR_USER);
    }

    /**
     * 数据绑定
     * @param binder
     * @param request
     */
    @InitBinder
    protected void initBinder(WebDataBinder binder, HttpServletRequest request) {
        Map<String, Object> params = localParams.get();
        if (params == null) {
            params = new HashMap<>();
        }
        params.put(HTTP_REQUEST, request);
        localParams.set(params);

        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
        binder.registerCustomEditor(Date.class, new DateEditor(true));  // 如果不使用自定义的，可以使用spring的CustomDateEditor
//        binder.registerCustomEditor(BigDecimal.class, new BigDecimalEditor(true));
        this.contextPath = request.getServletPath();
    }

    /**
     * 异常处理跳转
     * @param ex
     * @return
     */
    @ExceptionHandler(Exception.class)
    public ModelAndView exceptionHandler(Exception ex) {
        log.error(ex.getMessage(), ex);
        ModelAndView view = new ModelAndView("/error");
        view.addObject("errMsg", ex.getMessage());
        return view;
    }

}
