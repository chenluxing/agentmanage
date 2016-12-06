package com.agentmanage.controller.module.trade;

import com.agentmanage.controller.base.BaseController;
import com.agentmanage.module.tradeimport.service.IImportDetailService;
import com.agentmanage.module.tradeimport.service.IImportLogService;
import com.agentmanage.plugin.excel.vo.ExcelMessage;
import com.agentmanage.plugin.page.Pageable;
import com.agentmanage.utils.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.InputStream;
import java.util.Date;

/**
 * 交易记录Controller
 * on 2016/11/26.
 */
@Controller
@RequestMapping("/trade/import")
public class TradeImportController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(TradeImportController.class);

    @Resource
    private IImportLogService importLogService;
    @Resource
    private IImportDetailService importDetailService;

    /**
     * 导入日志列表
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "/list", method = {RequestMethod.GET, RequestMethod.POST})
    public String list(Date beginDate, Date endDate, Pageable pageable, ModelMap modelMap) {
        if (beginDate == null) {
            beginDate = DateUtil.addMonth(DateUtil.getCurrentDate(), -1);
        }
        if (endDate == null) {
            endDate = DateUtil.getCurrentDate();
        }
        beginDate = DateUtil.zerolizedTime(beginDate);
        endDate = DateUtil.getEndTime(endDate);
        modelMap.addAttribute("page", importLogService.getList(beginDate, endDate, getCurUser().getUserId(), pageable));

        modelMap.addAttribute("beginDate", beginDate);
        modelMap.addAttribute("endDate", endDate);
        return "/trade/import/list";
    }

    /**
     * 跳转导入页面
     * @return
     */
    @RequestMapping(value = "/toAdd", method = {RequestMethod.GET, RequestMethod.POST})
    public String toAdd() {
        return "/trade/import/add";
    }

    /**
     * 上传导入记录
     * @param file
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "/add", method = {RequestMethod.GET, RequestMethod.POST})
    public String add(@RequestParam("file")MultipartFile file, ModelMap modelMap) {
        InputStream inputStream = null;
        try {
            inputStream = file.getInputStream();
            ExcelMessage excelMessage = importLogService.save(inputStream, getCurUser().getUserId());
            if (ExcelMessage.Type.error.equals(excelMessage.getType())) {
                modelMap.addAttribute("errorLines", excelMessage.getErrorLines());
                return "/trade/import/add";
            } else {
                return "redirect:list.html";
            }
        } catch (Exception ex) {
            logger.error("上传文件失败！", ex);
            return "error";
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (Exception ex) {
            }
        }
    }

    /**
     * 导入日志列表
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "/detail/list", method = {RequestMethod.GET, RequestMethod.POST})
    public String detailList(Integer logId, Pageable pageable, ModelMap modelMap) {
        modelMap.addAttribute("page", importDetailService.getList(logId, pageable));
        modelMap.addAttribute("logId", logId);
        return "/trade/import/detail_list";
    }
}
