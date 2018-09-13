package com.somapait.common;

import java.io.Serializable;

public class CommonSelectItem implements Serializable {

	private static final long serialVersionUID = 6771221109764211702L;

	private String key;
	private Integer id;
	private String value;
	private String code;
	private String text;

	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}

	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String toString() {
		String text = getText();
		if (text == null) {
			text = super.toString();
		}
		return text;
	}

	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
}
