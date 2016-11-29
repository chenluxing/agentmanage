package com.agentmanage.controller.module.agent;

import com.agentmanage.controller.base.BaseController;
import com.agentmanage.exception.AmServiceException;
import com.agentmanage.module.agent.entity.AgentInfoPo;
import com.agentmanage.module.agent.service.IAgentAccountService;
import com.agentmanage.module.agent.service.IAgentService;
import com.agentmanage.plugin.page.Filter;
import com.agentmanage.plugin.page.Pageable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

    /**
     * 代理人列表
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "/list", method = {RequestMethod.GET, RequestMethod.POST})
    public String list(Pageable pageable, ModelMap modelMap) {
        Integer curAgentId = getCurUser().getAgentId();
        Filter filter = new Filter();
        filter.addParam("parentAgentId", curAgentId);
        pageable.setFilter(filter);
        modelMap.addAttribute("page", agentService.getSubList(pageable));
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
}
