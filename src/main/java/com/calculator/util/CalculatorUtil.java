package com.calculator.util;

public class CalculatorUtil {

	public static int mathCalculation(char operator, int value2, int value1) {
		switch (operator) {
		case '+':
			return value1 + value2;
		case '-':
			return value1 - value2;
		case '*':
			return value1 * value2;
		case '^':
			return (int) Math.pow(value1, value2);
		case '/':
			if (value2 == 0)
				throw new UnsupportedOperationException("Divide by zero error!!!");
			double output= Math.round(value1 / value2);
			return (int) output;
		}
		return 0;
	}

	public static boolean precedenceCheck(char currentOperator, char peekOperator) {
		if (peekOperator == '(' || peekOperator == ')')
			return false;
		if (( currentOperator == '^' ||currentOperator == '*' || currentOperator == '/') && (peekOperator == '+' || peekOperator == '-'))
			return false;
		else
			return true;
	}
}