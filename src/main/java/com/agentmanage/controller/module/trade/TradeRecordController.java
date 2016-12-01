package com.agentmanage.controller.module.trade;

import com.agentmanage.controller.base.BaseController;
import com.agentmanage.module.agent.entity.AgentInfoPo;
import com.agentmanage.module.agent.service.IAgentService;
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
import java.math.BigDecimal;
import java.util.Date;

/**
 * 交易记录Controller
 * on 2016/11/26.
 */
@Controller
@RequestMapping("/trade/record")
public class TradeRecordController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(TradeRecordController.class);

    @Resource
    private ITradeRecordService tradeRecordService;
    @Resource
    private IAgentService agentService;

    /**
     * 交易记录列表
     * @param pageable
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "/list", method = {RequestMethod.GET, RequestMethod.POST})
    public String list(String realName, String merchantId, Date beginDate, Date endDate ,Pageable pageable, ModelMap modelMap) {
        if (beginDate == null) {
            beginDate = DateUtil.addMonth(DateUtil.getCurrentDate(), -1);
        }
        if (endDate == null) {
            endDate = DateUtil.getCurrentDate();
        }
        beginDate = DateUtil.zerolizedTime(beginDate);
        endDate = DateUtil.getEndTime(endDate);
        Integer agentId = getCurUser().getAgentId();

        modelMap.addAttribute("page", tradeRecordService.getVoListByParentAgentId(realName, merchantId, beginDate, endDate, agentId, pageable));
        modelMap.addAttribute("realName", realName);
        modelMap.addAttribute("merchantId", merchantId);
        modelMap.addAttribute("beginDate", beginDate);
        modelMap.addAttribute("endDate", endDate);
        return "/trade/record/list";
    }

    /**
     * 跳转至代理人页面
     * @return
     */
    @RequestMapping(value = "/toAdd", method = {RequestMethod.GET, RequestMethod.POST})
    public String toAdd() {
        return "/trade/record/add";
    }

    /**
     * 手工新增交易记录
     * @param merchantId
     * @param tradeAmount
     * @param tradeCount
     * @return
     */
    @RequestMapping(value = "/add", method = {RequestMethod.POST})
    public String add(String merchantId, BigDecimal tradeAmount, Integer tradeCount) {
        AgentInfoPo agentInfo = agentService.getByMerchantId(merchantId);
        if (agentInfo != null) {
            tradeRecordService.saveToHead(agentInfo.getId(), tradeAmount, tradeCount, getCurUser().getUserId());
        } else {
            throw new RuntimeException("该商户ID的代理人信息不存在");
        }
        return "redirect:list.html";
    }

}
