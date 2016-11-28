package com.agentmanage.module.trade.service.impl;

import com.agentmanage.exception.AmServiceException;
import com.agentmanage.module.agent.service.IAgentAccountService;
import com.agentmanage.module.trade.entity.TradeRecordPo;
import com.agentmanage.module.trade.entity.TradeRecordVo;
import com.agentmanage.module.trade.mapper.TradeRecordMapper;
import com.agentmanage.module.trade.service.ITradeRecordService;
import com.agentmanage.module.agent.entity.AgentInfoPo;
import com.agentmanage.module.agent.service.IAgentService;
import com.agentmanage.plugin.page.PageAdapter;
import com.agentmanage.plugin.page.Pageable;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 交易记录Service实现类
 * on 2016/11/22.
 */
@Service
public class TradeRecordServiceImpl implements ITradeRecordService {

    @Resource
    private TradeRecordMapper tradeRecordMapper;
    @Resource
    private IAgentService agentService;
    @Resource
    private IAgentAccountService agentAccountService;

    /**
     * 新增交易记录
     * @param agentId
     * @param tradeAmount
     * @param tradeCount
     */
    @Override
    @Transactional
    public void save(Integer agentId, BigDecimal tradeAmount, int tradeCount, Integer curUserId) {
        AgentInfoPo agentInfo = agentService.getById(agentId);
        if (agentInfo != null) {
            // 新增交易记录
            BigDecimal agentAmount = tradeAmount.multiply(agentInfo.getAgentPercent()).setScale(2, BigDecimal.ROUND_HALF_UP);
            TradeRecordPo tradeRecord = new TradeRecordPo(agentId, tradeCount, tradeAmount, agentInfo.getAgentPercent(), agentAmount, curUserId);
            tradeRecordMapper.insert(tradeRecord);

            // 更新账户总金额，并记录账户金额变更
            agentAccountService.addAgentAmount(agentId, tradeAmount, agentAmount, tradeRecord.getId());
        }
    }

    /**
     * 新增交易记录
     * @param merchantId
     * @param tradeAmount
     * @param tradeCount
     */
    @Override
    @Transactional
    public void save(String merchantId, BigDecimal tradeAmount, int tradeCount, Integer curUserId) {
        AgentInfoPo agentInfo = agentService.getByMerchantId(merchantId);
        if (agentInfo != null) {
            save(agentInfo.getId(), tradeAmount, tradeCount, curUserId);
        } else {
            throw new AmServiceException("商户ID对应的代理人信息不存在");
        }
    }

    /**
     * 迭代向上关联代理人增加交易记录
     * @param agentId
     * @param tradeAmount
     * @param tradeCount
     * @param curUserId
     */
    @Override
    @Transactional
    public void saveToHead(Integer agentId, BigDecimal tradeAmount, int tradeCount, Integer curUserId) {
        AgentInfoPo agentInfo = agentService.getById(agentId);
        if (agentInfo != null) {
            save(agentInfo.getId(), tradeAmount, tradeCount, curUserId);
        } else {
            throw new AmServiceException("商户ID对应的代理人信息不存在");
        }
        if (agentInfo.getLevel() > 0){
            save(agentInfo.getParentAgentId(), tradeAmount, tradeCount, curUserId);
        } else {
            return;
        }
    }

    /**
     * 查询代理人交易明细
     * @param agentId
     * @return
     */
    @Override
    public List<TradeRecordVo> getVoListByAgentId(Integer agentId) {
        Assert.notNull(agentId, "代理人ID不允许为空");
        Map<String, Object> param = new HashMap<>();
        param.put("agentId", agentId);
        return getVoListByParam(param);
    }

    /**
     * 查询下级代理人交易明细
     * @param pageable
     * @return
     */
    @Override
    public com.agentmanage.plugin.page.Page<TradeRecordVo> getVoListByParentAgentId(Pageable pageable) {
        PageHelper.startPage(pageable.getPageNumber(), pageable.getPageSize());
        Page<TradeRecordVo> page = (Page<TradeRecordVo>) getVoListByParam(pageable.getFilter());
        PageAdapter<TradeRecordVo> pageAdapter = new PageAdapter<>(page, pageable);
        return pageAdapter.getPage();
    }

    /**
     * 条件查询代理人信息
     * @param param
     * @return
     */
    private List<TradeRecordVo> getVoListByParam(Map<String, Object> param) {
        return tradeRecordMapper.selectTradeRecordVoList(param);
    }
}
