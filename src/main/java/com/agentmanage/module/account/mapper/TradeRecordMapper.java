package com.agentmanage.module.account.mapper;

import com.agentmanage.module.account.entity.TradeRecordPo;

import java.util.List;

/**
 * 交易明细Mapper
 * on 2016/11/22.
 */
public interface TradeRecordMapper {

    /**
     * 新增交易记录
     * @param tradeRecord
     */
    void insert(TradeRecordPo tradeRecord);

    /**
     * 根据商户ID查询交易记录
     * @param merchantId
     * @return
     */
    List<TradeRecordPo> selectListByMerchantId(String merchantId);
}
