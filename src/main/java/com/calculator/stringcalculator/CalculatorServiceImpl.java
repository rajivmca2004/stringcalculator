package com.calculator.stringcalculator;

import static com.calculator.util.CalculatorUtil.mathCalculation;
import static com.calculator.util.CalculatorUtil.precedenceCheck;

import java.util.Stack;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.calculator.exception.CalculatorException;
import com.calculator.util.CalcEnum;

@Service
public class CalculatorServiceImpl implements CalculatorService {

	static Logger log = LoggerFactory.getLogger(CalculatorServiceImpl.class);

	@Override
	public Integer calculate(String stringToken) {
		Integer result;

		try {
			result = calculateExpression(stringToken);
		} catch (CalculatorException ce) {
			log.error(CalcEnum.INVALID_EXPRESSION.getValue());
			throw new CalculatorException(ce);
		}
		return result;
	}

	private Integer calculateExpression(String stringToken) {
		Integer value1, value2;
		// Operands
		Stack<Integer> values = new Stack<Integer>();

		// Operators
		Stack<Character> operator = new Stack<Character>();

		char[] expr = stringToken.toCharArray();
		int exprLength = expr.length;

		if (exprLength >= 1 && exprLength <= 100) {

			for (int i = 0; i < expr.length; i++) {

				if (expr[i] == ' ') {
					continue;
				}

				if (expr[i] >= '0' && expr[i] <= '9') {
					i = addToValueStack(values, expr, i);

				} else if (expr[i] == '(') {
					operator.push(expr[i]);
				} else if (expr[i] == ')') {
					evaluateParentheses(values, operator);
					operator.pop();
				} else if (expr[i] == '^' ||expr[i] == '+' || expr[i] == '-' || expr[i] == '*' || expr[i] == '/') 
	            { 
	                evaluateOperatorsPushtoValues(values, operator, expr, i);
	                operator.push(expr[i]); 
	            } 
				else {
					log.error(CalcEnum.INVALID_EXPRESSION.getValue());
					throw new CalculatorException();
				}
			}
		} else {
			log.error(CalcEnum.INVALID_EXPRESSION.getValue());
		}
		 while (!operator.empty()) {
			 	value2=values.pop();
				value1=values.pop();
	            values.push(mathCalculation(operator.pop(), value2, value1)); 
		 }
	  
		return values.pop();
	}

	private void evaluateOperatorsPushtoValues(Stack<Integer> values, Stack<Character> operator, char[] expr, int i) {
		Integer value1;
		Integer value2;
		while (!operator.empty() && precedenceCheck(expr[i], operator.peek())) {
			value2=values.pop();
			value1=values.pop();
		  values.push(mathCalculation(operator.pop(), value2, value1)); 
		}
	}

	private void evaluateParentheses(Stack<Integer> values, Stack<Character> operator) {
		Integer value1;
		Integer value2;
		while (operator.peek() != '(') {
			value2=values.pop();
			value1=values.pop();
			values.push(mathCalculation(operator.pop(), value2, value1));
		}
	}

	private int addToValueStack(Stack<Integer> values, char[] expr, int i) {
		StringBuffer sb = new StringBuffer();
		while (i < expr.length && expr[i] >= '0' && expr[i] <= '9') {
			if(expr[i+1] >= '0' && expr[i+1] <= '9') {
				log.error(CalcEnum.INVALID_EXPRESSION.getValue());
				throw new CalculatorException();
			}
			sb.append(expr[i++]);
		}
		i--;
		values.push(Integer.parseInt(sb.toString()));
		return i;
	}

}
