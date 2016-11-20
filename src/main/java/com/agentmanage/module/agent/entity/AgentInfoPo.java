package com.agentmanage.module.agent.entity;

import com.agentmanage.module.common.entity.BaseEntity;
import org.springframework.data.annotation.TypeAlias;

import java.math.BigDecimal;

/**
 * 代理人信息
 */
@TypeAlias("com.agentmanage.module.agent.entity.AgentInfoPo")
public class AgentInfoPo extends BaseEntity {

    private Integer agentId;                // 关联代理人ID
    private String realName;                // 真实姓名
    private String merchantId;              // 商户ID
    private String alipayNo;                // 支付宝帐号
    private BigDecimal agentPercent;        // 代理利率
    private Integer parentAgentId;          // 关联上级代理人ID

    public Integer getAgentId() {
        return agentId;
    }

    public void setAgentId(Integer agentId) {
        this.agentId = agentId;
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
}
