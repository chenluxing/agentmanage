package com.agentmanage.plugin.excel.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * 行号错误信息
 * on 2016/9/1.
 */
public class LineMessage {

    // 错误行号
    private int line;
    // 错误信息结果集合
    private List<String> message;

    public LineMessage(int line, String msg){
        message = new ArrayList<>();
        this.line = line;
        message.add(msg);
    }

    public LineMessage(int line){
        message = new ArrayList<>();
        this.line = line;
    }

    public LineMessage(){
        message = new ArrayList<>();
    }

    /**
     * 添加错误信息
     * @param msg
     */
    public void addMessage(String msg){
        if (message != null){
            message.add(msg);
        }
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public List<String> getMessage() {
        return message;
    }

    public void setMessage(List<String> message) {
        this.message = message;
    }
}
