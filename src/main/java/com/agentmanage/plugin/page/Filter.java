package com.agentmanage.plugin.page;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * 分页条件
 */
public class Filter extends HashMap<String, Object> {
	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = LoggerFactory.getLogger(Filter.class);

	/**
	 * 默认构造函数，初始化查询条件
	 */
	public Filter() {

	}

	public Filter(String param, String value) {
		this.put(param, value);
	}

	public Filter(Map<String, Object> params) {
		if (params != null && !params.isEmpty()) {
			this.putAll(params);
		}
	}

	/**
	 * 批量添加查询条件
	 * @param params 封装查询条件的Map
	 */
	public Filter addParams(Map<String, Object> params) {
		this.putAll(params);
		return this;
	}

	/**
	 * 获取全部的查询条件
	 *
	 * @return 全部的查询条件
	 */
	public Map<String, Object> getAllParams() {
		return this;
	}

	/**
	 * 添加一个查询条件
	 *
	 * @param paramName
	 *            查询条件名, 若条件名带"q_", 我们认为该查询项为查询返回值
	 * @param paramValue 查询条件值
	 */
	public Filter addParam(String paramName, Object paramValue) {
		if (isNotBlank(paramValue)) {
			this.put(paramName, paramValue);
		}
		return this;
	}

	/**
	 * 添加一个查询条件，如果查询条件值为空，则取默认值， 不对默认值进行为空判断
	 *
	 * @param paramName
	 *            查询条件名
	 * @param paramValue
	 *            查询条件值
	 * @param defaultValue 默认查询值
	 * @return 当前对象
	 */
	public Filter addParam(String paramName, Object paramValue, Object defaultValue) {
		if (isNotBlank(paramValue)) {
			this.put(paramName, paramValue);
		} else {
			this.put(paramName, defaultValue);
		}
		return this;
	}

	/**
	 * 判断参数值是否不为空
	 *
	 * @param paramValue
	 *            参数值
	 * @return 如果参数值为空，则返回FALSE，如果是String类型，如果是空字符串，也返回FALSE
	 */
	private boolean isNotBlank(Object paramValue) {
		if (paramValue instanceof String) {
			return null != paramValue && !"".equals(paramValue);
		} else {
			return null != paramValue;
		}
	}

	public Object getParam(String paramName) {
		return get(paramName);
	}
}
