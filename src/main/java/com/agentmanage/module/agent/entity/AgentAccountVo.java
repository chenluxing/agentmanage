package com.agentmanage.module.agent.entity;

import org.apache.ibatis.type.Alias;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 账户信息Vo
 * on 2016/11/27.
 */
@Alias("com.agentmanage.module.agent.entity.AgentAccountVo")
public class AgentAccountVo {

    private Integer agentId;                // 代理人ID
    private String mobileNo;                // 手机号
    private String realName;                // 真实姓名
    private String alipayNo;                // 支付宝帐号
    private BigDecimal agentPercent;        // 代理利率
    private Integer accountId;              // 账户ID
    private BigDecimal totalAmount;         // 订单总额
    private BigDecimal totalAgentAmount;    // 佣金总额
    private Date gmtModified;                 // 账户信息更新时间

    public Integer getAgentId() {
        return agentId;
    }

    public void setAgentId(Integer agentId) {
        this.agentId = agentId;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getAlipayNo() {
        return alipayNo;
    }

    public void setAlipayNo(String alipayNo) {
        this.alipayNo = alipayNo;
    }

    public BigDecimal getAgentPercent() {
        return agentPercent;
    }

    public void setAgentPercent(BigDecimal agentPercent) {
        this.agentPercent = agentPercent;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getTotalAgentAmount() {
        return totalAgentAmount;
    }

    public void setTotalAgentAmount(BigDecimal totalAgentAmount) {
        this.totalAgentAmount = totalAgentAmount;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }
}
