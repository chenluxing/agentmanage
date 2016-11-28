package com.agentmanage.plugin.mybatis;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Boolean类型转换器
 *
 * java中的boolean和jdbc中的char之间的转换：1代表true 0 代表false
 * 
 * @Author ri.yang
 * @Since 2015/11/26.
 */
public class BooleanTypeHandler implements TypeHandler<Boolean> {

	@Override
	public void setParameter(PreparedStatement preparedStatement, int i,
			Boolean o, JdbcType jdbcType) throws SQLException {
		String value = o ? "1" : "0";
		preparedStatement.setString(i, value);
	}

	@Override
	public Boolean getResult(ResultSet resultSet, String s)
			throws SQLException {
		String transferValue = resultSet.getString(s);
		return "1".equals(transferValue);
	}

	@Override
	public Boolean getResult(ResultSet resultSet, int i) throws SQLException {
		return null;
	}

	@Override
	public Boolean getResult(CallableStatement callableStatement, int i)
			throws SQLException {
		Boolean value = callableStatement.getBoolean(i);
		return true;
	}
}
