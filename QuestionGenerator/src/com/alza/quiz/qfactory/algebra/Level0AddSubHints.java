package com.alza.quiz.qfactory.algebra;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import com.alza.quiz.model.ProblemPattern;
import com.alza.quiz.model.SolutionStep;
import com.alza.quiz.util.CommonFunctionAndValues;

public class Level0AddSubHints {

	public static List<String> getHints(int a,int b,String var, ProblemPattern p, ResourceBundle bundleAlgebra) {
		List<String> hints = new ArrayList<>();		
		// VAR = a + b		
		if (p.question.equals("VAR = a + b")) {			
			String hint = bundleAlgebra.getString("algebra.level0.addsub.hintp1");					
			hints.add(hint);

		}
		// VAR = a + b		
		else if (p.question.equals("VAR = a + b")) {			
			String hint = bundleAlgebra.getString("algebra.level0.addsub.hintp1");					
			hints.add(hint);

		}
		return hints;
	}

	private static String injectValsAndEnclosedWithMathJax(int a,int b, String var, String exp ) {		
		exp = exp.replace("a", String.valueOf(a));
		exp = exp.replace("b", String.valueOf(b));
		exp = exp.replaceAll("VAR", var);
		exp = CommonFunctionAndValues.enclosedWithMathJaxExp(exp);
		return exp;		
	}

}
