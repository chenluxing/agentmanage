package com.agentmanage.plugin.excel;

import org.apache.commons.lang3.StringUtils;

/**
 * on 2016/9/13.
 */
public class CellBaseDefined {

    // 列序号
    protected int columnIndex;
    // 分组名称
    protected String groupName;
    // 表头名
    protected String headName;
    // 属性名
    protected String propName;
    // 是否必填
    protected boolean isRequired = false;

    public CellBaseDefined(){}
    public CellBaseDefined(int columnIndex, String groupName, String headName, String propName, boolean isRequired){
        this.columnIndex = columnIndex;
        this.groupName = groupName;
        this.headName = headName;
        this.propName = propName;
        this.isRequired = isRequired;
    }

    public String getFullName(){
        StringBuilder sb = new StringBuilder("");
        if (StringUtils.isNotEmpty(groupName)){
            sb.append(groupName);
        }
        if (StringUtils.isNotEmpty(headName)){
            if(sb.length() > 0){
                sb.append(" > ");
            }
            sb.append(headName);
        }
        return sb.toString();
    }

    /**
     * 获取展示值
     * @return
     */
    public String getDisplayValue(Object value){
        return null;
    }

    public int getColumnIndex() {
        return columnIndex;
    }

    public void setColumnIndex(int columnIndex) {
        this.columnIndex = columnIndex;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getHeadName() {
        return headName;
    }

    public void setHeadName(String headName) {
        this.headName = headName;
    }

    public String getPropName() {
        return propName;
    }

    public void setPropName(String propName) {
        this.propName = propName;
    }

    public boolean isRequired() {
        return isRequired;
    }

    public void setIsRequired(boolean isRequired) {
        this.isRequired = isRequired;
    }
}
