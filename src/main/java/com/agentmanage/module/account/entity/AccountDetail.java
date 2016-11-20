package com.agentmanage.module.account.entity;

import com.agentmanage.module.common.entity.BaseEntity;

import java.math.BigDecimal;

/**
 * 账户资金明细
 */
public class AccountDetail extends BaseEntity {

    private Integer accountId;              // 账户ID
    private BigDecimal tradeAmount;         // 交易金额
    private BigDecimal tradeAgentAmount;    // 交易佣金金额
    private BigDecimal totalAmount;         // 账户总
    private BigDecimal totalAgentAmount;    // 账户总佣金
    private Integer dataType;               // 数据来源
    private Integer dataId;                 // 数据来源ID
    private String remark;                  // 备注

}
