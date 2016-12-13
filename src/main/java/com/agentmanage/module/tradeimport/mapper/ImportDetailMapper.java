package com.agentmanage.module.tradeimport.mapper;

import com.agentmanage.module.tradeimport.entity.ImportDetailPo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 导入记录明细DAO
 * on 2016/11/27.
 */
public interface ImportDetailMapper {

    /**
     * 新增导入日志明细
     * @param importDetail
     */
    void insert(ImportDetailPo importDetail);

    /**
     * 查询导入明细
     * @param logId
     * @param isOriginal
     * @return
     */
    List<ImportDetailPo> selectList(@Param("logId") Integer logId, @Param("isOriginal") Integer isOriginal);

    /**
     * 按代理人统计导入数据
     * @param logId
     * @return
     */
    List<Map> selectCalcList(@Param("logId") Integer logId);

    /**
     * 按父级代理人统计导入数据
     * @param logId
     * @param agentLevel
     * @return
     */
    List<Map> selectCalcParentList(@Param("logId") Integer logId, @Param("agentLevel") Integer agentLevel);
}
