package com.agentmanage.controller.module.trade;

import com.agentmanage.controller.base.BaseController;
import com.agentmanage.module.tradeimport.service.IImportLogService;
import com.agentmanage.plugin.page.Filter;
import com.agentmanage.plugin.page.Pageable;
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

    /**
     * 导入日志列表
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "/list", method = {RequestMethod.GET, RequestMethod.POST})
    public String list(Pageable pageable, ModelMap modelMap) {
        Filter filter = new Filter();
        filter.addParam("creatorId", getCurUser().getUserId());
        pageable.setFilter(filter);
        modelMap.addAttribute("page", importLogService.getList(pageable));
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
            importLogService.save(inputStream, getCurUser().getUserId());
        } catch (Exception ex) {
            logger.error("上传文件失败！", ex);
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (Exception ex) {
            }
        }
        return "redirect:list.html";
    }

}
