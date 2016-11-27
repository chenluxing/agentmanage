package com.agentmanage.plugin.excel;

import jxl.Cell;
import org.apache.commons.lang3.StringUtils;

/**
 * on 2016/9/12.
 */
public class CellIntegerDefined extends CellBaseDefined implements ICellDefined<Integer> {

    private Integer minValue;
    private Integer maxValue;

    public CellIntegerDefined(){}
    public CellIntegerDefined(int columnIndex, String groupName, String headName, String propName, boolean isRequired){
        super(columnIndex, groupName, headName, propName, isRequired);
    }

    public CellIntegerDefined(int columnIndex, String groupName, String headName, String propName, boolean isRequired, Integer minValue, Integer maxValue){
        super(columnIndex, groupName, headName, propName, isRequired);
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    /**
     * 获取日期类型的单元格数据
     * @param cell
     * @return
     */
    @Override
    public Integer getCellValue(Cell cell) throws CellException{
        String result = null;
        if (cell != null){
            result = StringUtils.trim(cell.getContents());
        }
        Integer rtnResult = null;
        if (StringUtils.isNotEmpty(result)){
            // 值转换，字符串转Integer
            try {
                rtnResult = Integer.valueOf(result);
            } catch (Exception e) {
            }
            if (minValue != null && rtnResult != null && minValue.compareTo(rtnResult) > 0){
                throw  new CellException(getFullName(), CellException.ErrorType.ERROR);
            }else if(maxValue != null && rtnResult != null && maxValue.compareTo(rtnResult) < 0){
                throw  new CellException(getFullName(), CellException.ErrorType.ERROR);
            }
            // 如果转换后的结果没有值，但是获取到单元格有值
            if (rtnResult == null && StringUtils.isNotEmpty(result)){
                throw new CellException(getFullName(), CellException.ErrorType.NOT_REG);
            }
            if (rtnResult == null && isRequired()){
                throw new CellException(getFullName(), CellException.ErrorType.NOT_CONVERT);
            }
        } else if(isRequired()) {   // 必填校验
            throw new CellException(getFullName(), CellException.ErrorType.NOT_NULL);
        }
        return rtnResult;
    }

}
