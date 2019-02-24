package com.calculator.stringcalculator;

import org.junit.Assert;
import org.junit.Test;

import com.calculator.exception.CalculatorException;

public class CalculatorServiceImplTest {

	private CalculatorServiceImpl calculatorService = new CalculatorServiceImpl();

	@Test
	public void testCalculateWithValidExpression() {
		int result = calculatorService.calculate("7+(6*5+3-4/2)");
		Assert.assertEquals(Integer.parseInt("38"), result);
	}

	@Test (expected=CalculatorException.class)
	public void testCalculateWithInvalidExpression() {
		int result = calculatorService.calculate("7+(67(56*2))");
	}
}
