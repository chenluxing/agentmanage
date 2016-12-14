package com.agentmanage.module.trade.mapper;

import com.agentmanage.module.trade.entity.TradeRecordPo;
import com.agentmanage.module.trade.entity.TradeRecordVo;

import java.util.List;
import java.util.Map;

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
     * 查询交易记录
     * @param param
     * @return
     */
    List<TradeRecordVo> selectTradeRecordVoList(Map<String, Object> param);
}
