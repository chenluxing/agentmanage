package com.agentmanage.module.account.mapper;

import com.agentmanage.module.account.entity.AccountDetailPo;

import java.util.List;

/**
 * 账户明细Mapper
 * on 2016/11/22.
 */
public interface AccountDetailMapper {

    /**
     * 新增账户明细
     * @param accountDetail
     */
    void insert(AccountDetailPo accountDetail);

    /**
     * 查询账户明细
     * @param accountId
     * @return
     */
    List<AccountDetailPo> selectListByAccountIdId(Integer accountId);

}
