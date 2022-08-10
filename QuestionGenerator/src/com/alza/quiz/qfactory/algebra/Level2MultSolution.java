package com.alza.quiz.qfactory.algebra;

import java.util.ArrayList;
import java.util.List;

import com.alza.quiz.model.ProblemPattern;
import com.alza.quiz.model.SolutionStep;
import com.alza.quiz.util.CommonFunctionAndValues;

public class Level2MultSolution {

	public static List<SolutionStep> getSolutionSteps(int a,int b,int c,String var, ProblemPattern p) {
		List<SolutionStep> steps = new ArrayList<>();		
		SolutionStep step1 = new SolutionStep();
		String exp = "-aVAR ÷ a = c ÷ a";
		int ans = -c / a; 
		if (p.question.equals("-aVAR = -c")) { // aVAR = -c
			exp = "-aVAR ÷ a = -c ÷ a";
			ans = -ans;
		}
		exp = injectValsAndEnclosedWithMathJax(a, b, c, var, exp);			
		step1.setExpression(exp);
		step1.setExplanation("Divide both sides by "+a+" to remove the multiplier");
		steps.add(step1);
		
		SolutionStep step2 = new SolutionStep();
		exp = "-VAR = "+(-ans);
		exp = injectValsAndEnclosedWithMathJax(a, b, c, var, exp);
		step2.setExpression(exp);
		step2.setExplanation("Simplify");
		steps.add(step2);
		
		SolutionStep step3 = new SolutionStep();
		exp = "-VAR * -1 = "+(-ans)+ " * -1";
		exp = injectValsAndEnclosedWithMathJax(a, b, c, var, exp);
		step3.setExpression(exp);
		step3.setExplanation("Multiply by -1 to remove minus sign");
		steps.add(step3);
		
		SolutionStep step4 = new SolutionStep();
		exp = "VAR = "+ans;
		exp = injectValsAndEnclosedWithMathJax(a, b, c, var, exp);
		step4.setExpression(exp);
		step4.setExplanation("Simplify, solved");
		steps.add(step4);
		return steps;
	}

	private static String injectValsAndEnclosedWithMathJax(int a,int b,int c,String var,String exp ) {		
		exp = exp.replace("a", String.valueOf(a));
		exp = exp.replace("b", String.valueOf(b));
		exp = exp.replace("c", String.valueOf(c));
		exp = exp.replaceAll("VAR", var);
		exp = CommonFunctionAndValues.enclosedWithMathJaxExp(exp);
		return exp;		
	}

}
