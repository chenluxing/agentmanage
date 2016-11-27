package com.agentmanage.module.tradeimport.entity;

import com.agentmanage.module.common.entity.BaseEntity;
import org.apache.ibatis.type.Alias;

import java.math.BigDecimal;

/**
 * 交易导入记录
 * on 2016/11/27.
 */
@Alias("com.agentmanage.module.tradeimport.entity.ImportLogPo")
public class ImportLogPo extends BaseEntity {

    private Integer dataCount;              // 导入文件数据总量
    private Integer usefulCount;            // 有效数据量
    private BigDecimal totalTradeAmount;    // 导入交易金额汇总
    private Integer totalTradeCount;        // 导入交易总数
    private Integer creatorId;              // 创建者ID

    public ImportLogPo(){}

    public ImportLogPo(Integer dataCount, Integer usefulCount, BigDecimal totalTradeAmount, Integer totalTradeCount, Integer creatorId) {
        this.dataCount = dataCount;
        this.usefulCount = usefulCount;
        this.totalTradeAmount = totalTradeAmount;
        this.totalTradeCount = totalTradeCount;
        this.creatorId = creatorId;
    }

    public Integer getDataCount() {
        return dataCount;
    }

    public void setDataCount(Integer dataCount) {
        this.dataCount = dataCount;
    }

    public Integer getUsefulCount() {
        return usefulCount;
    }

    public void setUsefulCount(Integer usefulCount) {
        this.usefulCount = usefulCount;
    }

    public BigDecimal getTotalTradeAmount() {
        return totalTradeAmount;
    }

    public void setTotalTradeAmount(BigDecimal totalTradeAmount) {
        this.totalTradeAmount = totalTradeAmount;
    }

    public Integer getTotalTradeCount() {
        return totalTradeCount;
    }

    public void setTotalTradeCount(Integer totalTradeCount) {
        this.totalTradeCount = totalTradeCount;
    }

    public Integer getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Integer creatorId) {
        this.creatorId = creatorId;
    }
}
