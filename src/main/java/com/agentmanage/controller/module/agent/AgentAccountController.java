package com.agentmanage.controller.module.agent;

import com.agentmanage.controller.base.BaseController;
import com.agentmanage.module.agent.service.IAgentAccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;

/**
 * 账户信息 on 2016/11/26.
 */
@Controller
@RequestMapping("/agent/account")
public class AgentAccountController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(AgentAccountController.class);

    @Resource
    private IAgentAccountService agentAccountService;

    /**
     * 代理人详情
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "/view", method = {RequestMethod.GET, RequestMethod.POST})
    public String view(Integer agentId, ModelMap modelMap) {
        modelMap.addAttribute("agentAccountVo", agentAccountService.getVoByAgentId(agentId));
        return "/account/view";
    }

}
