package com.agentmanage.plugin.editor;

import org.apache.commons.lang3.StringUtils;

import java.beans.PropertyEditorSupport;
import java.math.BigDecimal;

/**
 */
public class BigDecimalEditor extends PropertyEditorSupport {
	/**
	 * 是否将空转换为默认值
	 */
	private boolean emptyAsDefault;
	/**
	 * 默认值
	 */
	public static final BigDecimal DEFAULT_VALUE = BigDecimal.ZERO;

	public BigDecimalEditor(boolean emptyAsDefault) {
		this.emptyAsDefault = emptyAsDefault;
	}

	@Override
	public void setAsText(String text) {
		if (StringUtils.isEmpty(text)) {
			setValue(DEFAULT_VALUE);
		} else {
			String value = text.trim();
			if (emptyAsDefault && "".equals(value)) {
				setValue(DEFAULT_VALUE);
			} else {
//				setValue(Convert.asType(BigDecimal.class, text));
			}
		}
	}
}
