package com.alza.quiz.test;

import java.util.List;
import java.util.Locale;

import com.alza.common.math.Fraction;
import com.alza.quiz.qfactory.fraction.FractionSolver;

public class SolverTest {
	public static void main(String[] args) {
		FractionSolver fs = new FractionSolver(new Locale("en", "US"));
		List<String> l = fs.simplify(new Fraction(4, 8));
		l = fs.add(new Fraction(2, 3), new Fraction(1, 5));
		l = fs.multiply(new Fraction(2, 3), new Fraction(1, 5));
		l = fs.divide(new Fraction(2, 3), new Fraction(1, 5));
		for (String string : l) {
			System.out.println(string);
		}
	}

}
