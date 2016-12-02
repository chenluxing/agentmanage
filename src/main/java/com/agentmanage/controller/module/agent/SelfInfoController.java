package com.agentmanage.controller.module.agent;

import com.agentmanage.controller.base.BaseController;
import com.agentmanage.module.agent.service.IAgentAccountService;
import com.agentmanage.module.trade.service.ITradeRecordService;
import com.agentmanage.plugin.page.Pageable;
import com.agentmanage.utils.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import java.util.Date;

/**
 * 我的信息
 * on 2016/11/26.
 */
@Controller
@RequestMapping("/self")
public class SelfInfoController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(SelfInfoController.class);

    @Resource
    private IAgentAccountService agentAccountService;
    @Resource
    private ITradeRecordService tradeRecordService;
    /**
     * 我的账户
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "/account/view", method = {RequestMethod.GET, RequestMethod.POST})
    public String selfView(Integer agentId, ModelMap modelMap) {
        modelMap.addAttribute("agentAccountVo", agentAccountService.getVoByAgentId(getCurUser().getAgentId()));
        return "/self/account_view";
    }

    /**
     * 我的交易记录
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "/trade/record", method = {RequestMethod.GET, RequestMethod.POST})
    public String selfView(Date beginDate, Date endDate ,Pageable pageable, ModelMap modelMap) {
        if (beginDate == null) {
            beginDate = DateUtil.addMonth(DateUtil.getCurrentDate(), -1);
        }
        if (endDate == null) {
            endDate = DateUtil.getCurrentDate();
        }
        beginDate = DateUtil.zerolizedTime(beginDate);
        endDate = DateUtil.getEndTime(endDate);
        modelMap.addAttribute("page", tradeRecordService.getVoListByAgentId(getCurUser().getAgentId(), beginDate, endDate, pageable));

        // 返回查询条件
        modelMap.addAttribute("beginDate", beginDate);
        modelMap.addAttribute("endDate", endDate);
        return "/self/trade_record_list";
    }

}
