package com.calculator.exception;

import com.calculator.util.CalcEnum;

public class CalculatorException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public CalculatorException() {
		super(CalcEnum.INVALID_EXPRESSION.getValue());
	}
	
	public CalculatorException(Exception cause) {
		super(cause);
	}
}
