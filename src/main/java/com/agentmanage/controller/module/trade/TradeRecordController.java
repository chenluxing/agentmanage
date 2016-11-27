package com.agentmanage.controller.module.trade;

import com.agentmanage.controller.base.BaseController;
import com.agentmanage.module.trade.service.ITradeRecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.math.BigDecimal;

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

    /**
     * 交易记录列表
     * @param agentId
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "/list", method = {RequestMethod.GET, RequestMethod.POST})
    public String list(Integer agentId, ModelMap modelMap) {
        agentId = getCurUser().getAgentId();
        modelMap.addAttribute("records", tradeRecordService.getVoListByParentAgentId(agentId));
        return "/trade/record/list";
    }

    @RequestMapping(value = "/toAdd", method = {RequestMethod.GET, RequestMethod.POST})
    public String toAdd() {
        return "/trade/record/add";
    }

    @RequestMapping(value = "/add", method = {RequestMethod.POST})
    public String add(String merchantId, BigDecimal tradeAmount, Integer tradeCount) {
        tradeRecordService.save(merchantId, tradeAmount, tradeCount, getCurUser().getUserId());
        return "redirect:list.html";
    }

}
