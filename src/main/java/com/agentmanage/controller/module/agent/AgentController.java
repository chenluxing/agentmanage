package com.agentmanage.controller.module.agent;

import com.agentmanage.controller.base.BaseController;
import com.agentmanage.exception.AmServiceException;
import com.agentmanage.module.agent.entity.AgentInfoPo;
import com.agentmanage.module.agent.service.IAgentService;
import com.agentmanage.module.user.service.IUserService;
import com.agentmanage.plugin.page.Filter;
import com.agentmanage.plugin.page.Pageable;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * 代理人Controller
 * on 2016/11/26.
 */
@Controller
@RequestMapping("/agent")
public class AgentController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(AgentController.class);

    @Resource
    private IAgentService agentService;
    @Resource
    private IUserService userService;

    /**
     * 代理人列表
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "/list", method = {RequestMethod.GET, RequestMethod.POST})
    public String list(String realName, String mobileNo, String merchantId, Pageable pageable, ModelMap modelMap) {
        Integer curAgentId = getCurUser().getAgentId();
        modelMap.addAttribute("page", agentService.getSubList(realName, mobileNo, merchantId, curAgentId, pageable));

        modelMap.addAttribute("realName", realName);
        modelMap.addAttribute("mobileNo", mobileNo);
        modelMap.addAttribute("merchantId", merchantId);
        return "/agent/list";
    }

    /**
     * 跳转至新增代理人页面
     * @return
     */
    @RequestMapping(value = "/toAdd", method = {RequestMethod.GET, RequestMethod.POST})
    public String toAdd() {
        return "/agent/add";
    }

    /**
     * 新增代理人
     * @param mobileNo
     * @param realName
     * @param merchantId
     * @param alipayNo
     * @param agentPercent
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "/add", method = {RequestMethod.POST})
    public String add(String mobileNo, String realName, String merchantId, String alipayNo, BigDecimal agentPercent, ModelMap modelMap) {
        try {
            agentService.save(mobileNo, realName, merchantId, alipayNo, agentPercent, getCurUser().getAgentId(), getCurUser().getUserId());
        } catch (Exception ex) {
            logger.error("新增代理人异常：", ex);
            String errMsg = "新增代理人异常";
            if (ex instanceof AmServiceException) {
                errMsg = ex.getMessage();
            }
            modelMap.addAttribute("errMsg", errMsg);
            return "/error";
        }
        return "redirect:list.html";
    }

    /**
     * 跳转至编辑代理人页面
     * @param agentId
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "/toEdit", method = {RequestMethod.GET, RequestMethod.POST})
    public String toEdit(Integer agentId, ModelMap modelMap) {
        AgentInfoPo agentInfo = agentService.getById(agentId);
        modelMap.put("agentInfo", agentInfo);
        return "/agent/edit";
    }

    /**
     * 编辑代理人
     * @param agentId
     * @param merchantId
     * @param agentPercent
     * @return
     */
    @RequestMapping(value = "/edit", method = {RequestMethod.POST})
    public String edit(Integer agentId, String merchantId, BigDecimal agentPercent) {
        agentService.modify(agentId, merchantId, agentPercent);
        return "redirect:list.html";
    }

    /**
     * 校验商户ID
     * @return
     */
    @RequestMapping(value = "/checkMerchantId", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Boolean checkMerchantId(String merchantId, Integer agentId) {
        if (StringUtils.isNotEmpty(merchantId)) {
            return !agentService.checkExistsMerchantId(merchantId, agentId);
        }
        return false;
    }

    /**
     * 校验商户ID
     * @return
     */
    @RequestMapping(value = "/checkMobile", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Boolean checkMobile(String mobileNo, Integer agentId) {
        if (StringUtils.isNotEmpty(mobileNo)) {
            return !userService.checkUserName(String.valueOf(mobileNo));
        }
        return false;
    }

}
