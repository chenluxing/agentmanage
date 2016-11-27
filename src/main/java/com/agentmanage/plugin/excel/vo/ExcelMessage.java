package com.agentmanage.plugin.excel.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * Excel解析结果类
 * on 2016/9/1.
 */
public class ExcelMessage {

    // 类型
    private Type type;
    // 错误行号信息
    private List<LineMessage> errorLines;
    // 成功结果数据
    private List<Object> datas;
    // 其它信息 例如统计信息等
    private Object otherInfo;

    public ExcelMessage(){
        errorLines = new ArrayList<>();
        datas = new ArrayList<>();
    }

    public ExcelMessage(Type type){
        this.setType(type);
        errorLines = new ArrayList<>();
        datas = new ArrayList<>();
    }

    /**
     * 添加错误行号信息
     * @param lineMessage
     */
    public void addErrorLine(LineMessage lineMessage){
        if (errorLines != null){
            errorLines.add(lineMessage);
        }
    }

    /**
     * 添加结果数据
     * @param data
     */
    public void addData(Object data){
        if (datas != null){
            datas.add(data);
        }
    }

    public List<LineMessage> getErrorLines() {
        return errorLines;
    }

    public void setErrorLines(List<LineMessage> errorLines) {
        this.errorLines = errorLines;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public List<Object> getDatas() {
        return datas;
    }

    public void setDatas(List<Object> datas) {
        this.datas = datas;
    }

    public Object getOtherInfo() {
        return otherInfo;
    }

    public void setOtherInfo(Object otherInfo) {
        this.otherInfo = otherInfo;
    }

    /**
     * 类型
     */
    public enum Type {

        /**
         * 成功
         */
        success,

        /**
         * 警告
         */
        warn,

        /**
         * 错误
         */
        error
    }
}
