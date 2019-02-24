package com.calculator.util;

public enum CalcEnum {
	INVALID_EXPRESSION("INVALID EXPRESSION");

	private String value;

	CalcEnum(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
