package com.agentmanage.plugin.mybatis;

import com.agentmanage.module.common.entity.enums.BaseEnum;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EnumTypeHandler implements TypeHandler<BaseEnum> {
	private final static Logger logger = LoggerFactory
			.getLogger(EnumTypeHandler.class);

	@Override
	public void setParameter(PreparedStatement ps, int i, BaseEnum parameter,
			JdbcType jdbcType) throws SQLException {
		if (parameter == null) {
			ps.setObject(i, null);
		} else {
			logger.debug("name : " + parameter.getName() + ", value : " + parameter.getValue());
			ps.setObject(i, parameter.getValue());
		}
	}

	@Override
	public BaseEnum getResult(ResultSet rs, String columnName)
			throws SQLException {
		Object obj = rs.getObject(columnName);
		if (obj == null) {
			return null;
		}
		Integer value = rs.getInt(columnName);
//		return EnumPool.getEnums(columnName).get(value);
		return null;
	}

	@Override
	public BaseEnum getResult(ResultSet rs, int i) throws SQLException {
		return null;
	}

	@Override
	public BaseEnum getResult(CallableStatement cs, int columnIndex)
			throws SQLException {
		return null;
	}

}
