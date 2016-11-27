package com.agentmanage.plugin.excel;

import jxl.Cell;

/**
 * on 2016/9/13.
 *
 */
public interface ICellDefined<T> {

    /**
     * 获取当前定义的索引
     * @return
     */
    int getColumnIndex();

    /**
     * 获取属性名称
     * @return
     */
    String getPropName();

    /**
     * 获取表头名
     * @return
     */
    String getHeadName();

    /**
     * 获取展示值
     * @return
     */
    String getDisplayValue(Object value);

    /**
     * 获取单元格值
     * @param cell
     * @return
     * @throws CellException
     */
    T getCellValue(Cell cell) throws CellException;

}
