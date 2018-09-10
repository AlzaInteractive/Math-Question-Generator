package com.alza.quiz.generator.examples;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.alza.common.math.Fraction;
import com.alza.quiz.qfactory.fraction.FractionSolver;

public class SolverDemo {
	public static void main(String[] args) {
		FractionSolver fs = new FractionSolver();
		fs = new FractionSolver(new Locale("en", "US"));
		List<List<String>> lls = new ArrayList<List<String>>();
		lls.add(fs.simplifyWithoutMentioningGCD(new Fraction(0, 8)));
		lls.add(fs.simplifyWithoutMentioningGCD(new Fraction(12, 14)));
		lls.add(fs.simplifyWithoutMentioningGCD(new Fraction(8, 4)));
		lls.add(fs.simplifyWithoutMentioningGCD(new Fraction(3, 4)));
		lls.add(fs.add(new Fraction(2, 3), new Fraction(1, 5)));
		lls.add(fs.add(new Fraction(2, 3), new Fraction(1, 0)));
		lls.add(fs.subtract(new Fraction(1, 6), new Fraction(1, 5)));
		lls.add(fs.subtract(new Fraction(1, 6), new Fraction(0, 5)));
		lls.add(fs.multiply(new Fraction(2, 3), new Fraction(1, 5)));
		lls.add(fs.multiply(new Fraction(2, 0), new Fraction(1, 5)));
		lls.add(fs.divide(new Fraction(2, 3), new Fraction(1, 5)));
		lls.add(fs.divide(new Fraction(0, 3), new Fraction(1, 5)));
		for (List<String> list : lls) {
			System.out.println("--------------");
			for (String string : list) {
				System.out.println(string);
			}
		}	
	}
}
