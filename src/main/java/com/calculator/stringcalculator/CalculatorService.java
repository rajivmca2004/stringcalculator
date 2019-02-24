package com.calculator.stringcalculator;

import com.calculator.exception.CalculatorException;

public interface CalculatorService {
	
	public Integer calculate(String stringToken) throws CalculatorException;

}
