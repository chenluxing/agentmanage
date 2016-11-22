package com.agentmanage.module.agent.entity;

import com.agentmanage.module.common.entity.BaseEntity;
import org.apache.ibatis.type.Alias;

import java.math.BigDecimal;

/**
 * 代理人信息
 */
@Alias("com.agentmanage.module.agent.entity.AgentInfoPo")
public class AgentInfoPo extends BaseEntity {

    private String mobileNo;                  // 手机号
    private String realName;                // 真实姓名
    private String merchantId;              // 商户ID
    private String alipayNo;                // 支付宝帐号
    private BigDecimal agentPercent;        // 代理利率
    private Integer parentAgentId;          // 关联上级代理人ID
    private Integer userId;                 // 关联登录帐户ID

    public AgentInfoPo() {}

    public AgentInfoPo(String mobileNo, String realName, String merchantId, String alipayNo, BigDecimal agentPercent,
                       Integer parentAgentId, Integer userId) {
        this.mobileNo = mobileNo;
        this.realName = realName;
        this.merchantId = merchantId;
        this.alipayNo = alipayNo;
        this.agentPercent = agentPercent;
        this.parentAgentId = parentAgentId;
        this.userId = userId;
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

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
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

    public Integer getParentAgentId() {
        return parentAgentId;
    }

    public void setParentAgentId(Integer parentAgentId) {
        this.parentAgentId = parentAgentId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
