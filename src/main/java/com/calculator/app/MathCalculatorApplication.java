package com.calculator.app;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.calculator.stringcalculator.CalculatorService;

@SpringBootApplication
public class MathCalculatorApplication {
	
	@Autowired
	private static CalculatorService calculatorService;

	public static void main(String[] args) {
		SpringApplication.run(MathCalculatorApplication.class, args);
		@SuppressWarnings("resource")
		Scanner in= new Scanner(System.in);
		String stringToken=in.nextLine();
		calculatorService.calculate(stringToken);
	}
}
