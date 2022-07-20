package com.alza.quiz.qfactory.algebra;

import java.util.ArrayList;
import java.util.List;

import com.alza.quiz.model.SolutionStep;
import com.alza.quiz.qfactory.algebra.Level1AddSub.ProblemPattern;
import com.alza.quiz.util.CommonFunctionAndValues;

public class Level1AddSubSolution {

	public static List<SolutionStep> getSolutionSteps(int a,int b,String var, ProblemPattern p) {
		List<SolutionStep> steps = new ArrayList<>();		
		// "VAR + a = b", "b - a"		
		if (p.question.equals("VAR + a = b")) {
			SolutionStep step1 = new SolutionStep();
			String exp = "VAR + a - a = b - a";
			exp = injectValsAndEnclosedWithMathJax(a, b, var, exp);			
			step1.setExpression(exp);
			step1.setExplanation("Subtract "+a+" from both sides to remove + "+a);
			steps.add(step1);
			SolutionStep step2 = new SolutionStep();
			int ans = b - a;
			exp = "VAR = "+ans;
			exp = injectValsAndEnclosedWithMathJax(a, b, var, exp);
			step2.setExpression(exp);
			step2.setExplanation("Perform simple arithmetic equation on both sides, "+var+ " is solved");
			steps.add(step2);
		}
		// VAR - a = b
		else if (p.question.equals("VAR - a = b")) {
			SolutionStep step1 = new SolutionStep();
			String exp = "VAR - a + a = b + a";
			exp = injectValsAndEnclosedWithMathJax(a, b, var, exp);
			step1.setExpression(exp);
			step1.setExplanation("Add "+a+" to both sides to remove - "+a);
			steps.add(step1);
			SolutionStep step2 = new SolutionStep();
			int ans = b + a;
			exp = "VAR = "+ans;
			exp = injectValsAndEnclosedWithMathJax(a, b, var, exp);
			step2.setExpression(exp);
			step2.setExplanation("Perform simple arithmetic equation on both sides, "+var+ " is solved");
			steps.add(step2);
		}
		// "VAR + a = -b", "-b - a"		
		else if (p.question.equals("VAR + a = -b")) {
			SolutionStep step1 = new SolutionStep();
			String exp = "VAR + a - a = -b - a";
			exp = injectValsAndEnclosedWithMathJax(a, b, var, exp);			
			step1.setExpression(exp);
			step1.setExplanation("Subtract "+a+" from both sides to remove + "+a);
			steps.add(step1);
			SolutionStep step2 = new SolutionStep();
			int ans = -b - a;
			exp = "VAR = "+ans;
			exp = injectValsAndEnclosedWithMathJax(a, b, var, exp);
			step2.setExpression(exp);
			step2.setExplanation("Perform simple arithmetic equation on both sides, "+var+ " is solved");
			steps.add(step2);
		}		
		// VAR - a = -b
		else if (p.question.equals("VAR - a = -b")) {
			SolutionStep step1 = new SolutionStep();
			String exp = "VAR - a + a = -b + a";
			exp = injectValsAndEnclosedWithMathJax(a, b, var, exp);
			step1.setExpression(exp);
			step1.setExplanation("Add "+a+" to both sides to remove - "+a);
			steps.add(step1);
			SolutionStep step2 = new SolutionStep();
			int ans = -b + a;
			exp = "VAR = "+ans;
			exp = injectValsAndEnclosedWithMathJax(a, b, var, exp);
			step2.setExpression(exp);
			step2.setExplanation("Perform simple arithmetic equation on both sides, "+var+ " is solved");
			steps.add(step2);
		}
		return steps;
	}

	private static String injectValsAndEnclosedWithMathJax(int a,int b, String var, String exp ) {		
		exp = exp.replace("a", String.valueOf(a));
		exp = exp.replace("b", String.valueOf(b));
		exp = exp.replaceAll("VAR", var);
		exp = CommonFunctionAndValues.enclosedWithMathJaxExp(exp);
		return exp;		
	}

}
