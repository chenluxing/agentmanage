package com.agentmanage.plugin.excel;

import com.agentmanage.plugin.excel.vo.ExcelMessage;
import com.agentmanage.plugin.excel.vo.LineMessage;
import jxl.Cell;
import jxl.CellType;
import jxl.LabelCell;
import jxl.Sheet;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * excel导入业务抽象类
 * on 2016/10/12.
 */
public abstract class AbstractImportService {

    private static final Logger logger = LoggerFactory.getLogger(AbstractImportService.class);

    /**
     * 加载sheet数据
     * @param sheet
     * @param headRowNum
     * @param startRowNum
     * @param cellDefinedList
     * @param baseMap
     * @return
     * @throws Exception
     */
    protected ExcelMessage loadSheetData(Sheet sheet, int headRowNum, int startRowNum, List<ICellDefined> cellDefinedList, Map<String, Object> baseMap) throws Exception{
        ExcelMessage excelMessage = new ExcelMessage(ExcelMessage.Type.success);

        if (!validateHeader(sheet.getRow(headRowNum), cellDefinedList)){
            LineMessage lineMessage = new LineMessage(0, "模版文件格式错误，请下载正确的模版文件");
            excelMessage.addErrorLine(lineMessage);
            excelMessage.setType(ExcelMessage.Type.error);
            return excelMessage;
        }

        int rows = sheet.getRows();
        int maxCount = 2000 + startRowNum;
        if(rows > maxCount){
            LineMessage lineMessage = new LineMessage(0, "最多上传2000条保单数据");
            excelMessage.addErrorLine(lineMessage);
            excelMessage.setType(ExcelMessage.Type.error);
            return excelMessage;
        }

        for (int i = startRowNum; i < rows; i++){
            LineMessage lineMessage = null;
            Map<String, Object> dataMap = new HashMap<>();

            Cell[] cells = sheet.getRow(i);
            for (ICellDefined cellDefined : cellDefinedList) {
                // 获取当前单元格定义
                try {
                    Object cvalue = null;
                    if (cells.length <= cellDefined.getColumnIndex()){
                        cvalue = cellDefined.getCellValue(null);
                    } else {
                        cvalue = cellDefined.getCellValue(cells[cellDefined.getColumnIndex()]);
                    }

                    // 只保留非空数据
                    if (cvalue != null && StringUtils.isNotEmpty(cvalue.toString())){
                        dataMap.put(cellDefined.getPropName(), cvalue);
                        // 自定义数据规则校验
                        validateCellData(cellDefined.getPropName(), dataMap, baseMap);
                    }
                } catch (CellException ce) {
                    excelMessage.setType(ExcelMessage.Type.error);
                    if (lineMessage == null){
                        lineMessage = new LineMessage(i+1);
                    }
                    lineMessage.addMessage(ce.getMessage());
                }
            }
            try {
                validateRowData(dataMap, baseMap);
            } catch (CellException ce) {
                excelMessage.setType(ExcelMessage.Type.error);
                if (lineMessage == null){
                    lineMessage = new LineMessage(i+1);
                }
                lineMessage.addMessage(ce.getMessage());
            }
            // 如果全行数据解析为空，则不再继续解析
            if (MapUtils.isEmpty(dataMap)){
                break;
            }
            // 判断当前行是否存在错误消息
            if (lineMessage != null && CollectionUtils.isNotEmpty(lineMessage.getMessage())){
                excelMessage.setType(ExcelMessage.Type.error);
                excelMessage.addErrorLine(lineMessage);
            }else{
                excelMessage.addData(dataMap);
            }
        }
        return excelMessage;
    }

    /**
     * 单元格校验
     * @param key
     * @param dataMap
     * @param baseMap
     * @throws CellException
     */
    protected abstract void validateCellData(String key, Map<String, Object> dataMap, Map<String, Object> baseMap) throws CellException;

    /**
     * 行数据校验
     * @param dataMap
     * @param baseMap
     * @throws CellException
     */
    protected abstract void validateRowData(Map dataMap,Map baseMap) throws CellException;

    /**
     * 校验文件格式头部定义
     * @param cells
     * @param definedList
     * @return
     */
    public boolean validateHeader(Cell[] cells, List<ICellDefined> definedList){
        try {
            if (cells != null && cells.length > 0 && CollectionUtils.isNotEmpty(definedList)){
                for (ICellDefined defined : definedList){
                    if (defined.getColumnIndex() > cells.length){
                        return false;
                    } else {
                        String content = null;
                        Cell cell = cells[defined.getColumnIndex()];
                        CellType cellType = cell.getType();
                        if (cellType.equals(CellType.LABEL)){
                            LabelCell labelCell = (LabelCell)cell;
                            content = labelCell.getString();
                        } else {
                            content = cell.getContents();
                        }
                        if (StringUtils.isNotEmpty(content) && content.equals(defined.getHeadName())){
                            continue;
                        } else {
                            return false;
                        }
                    }
                }
                return true;
            }
        } catch (Exception e) {
            logger.error("文件格式校验错误：", e);
        }
        return false;
    }
}
