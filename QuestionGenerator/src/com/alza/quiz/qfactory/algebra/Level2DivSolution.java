package com.alza.quiz.qfactory.algebra;

import java.util.ArrayList;
import java.util.List;

import com.alza.quiz.model.ProblemPattern;
import com.alza.quiz.model.SolutionStep;
import com.alza.quiz.util.CommonFunctionAndValues;

public class Level2DivSolution {

	public static List<SolutionStep> getSolutionSteps(int a,int b,String var, ProblemPattern p) {
		List<SolutionStep> steps = new ArrayList<>();		
		SolutionStep step1 = new SolutionStep();
		if (p.question.equals("-VAR ÷ a = b")
				|| p.question.equals("-VAR ÷ a = -b")) {
			String exp = "-VAR ÷ a * a = b * a";
			int ans = -(b * a); 
			if (p.question.equals("-VAR ÷ a = -b")) { // aVAR = -c
				exp = "-VAR ÷ a * a = -b * a";
				ans = -ans;
			}
			exp = injectValsAndEnclosedWithMathJax(a, b, var, exp);			
			step1.setExpression(exp);
			step1.setExplanation("Multiply both sides by "+a+" to remove the divisor");
			steps.add(step1);
			
			SolutionStep step2 = new SolutionStep();
			exp = "-VAR = "+(-ans);
			exp = injectValsAndEnclosedWithMathJax(a, b, var, exp);
			step2.setExpression(exp);
			step2.setExplanation("Simplify");
			steps.add(step2);
			
			SolutionStep step3 = new SolutionStep();
			exp = "-VAR * -1 = "+(-ans)+ " * -1";
			exp = injectValsAndEnclosedWithMathJax(a, b, var, exp);
			step3.setExpression(exp);
			step3.setExplanation("Multiply by -1 to remove minus sign");
			steps.add(step3);
			
			SolutionStep step4 = new SolutionStep();
			exp = "VAR = "+ans;
			exp = injectValsAndEnclosedWithMathJax(a, b, var, exp);
			step4.setExpression(exp);
			step4.setExplanation("Simplify, solved");
			steps.add(step4);
			
		} else if (p.question.equals("-VAR ÷ -a = b")
				|| p.question.equals("-VAR ÷ -a = -b")) {
			String exp = "-VAR ÷ -a * -a = b * -a";
			int ans = b * a; 
			if (p.question.equals("-VAR ÷ -a = -b")) { // aVAR = -c
				exp = "-VAR ÷ -a * -a = -b * -a";
				ans = -ans;
			}
			exp = injectValsAndEnclosedWithMathJax(a, b, var, exp);			
			step1.setExpression(exp);
			step1.setExplanation("Multiply both sides by -"+a+" to remove the divisor");
			steps.add(step1);
			
			SolutionStep step2 = new SolutionStep();
			exp = "-VAR = "+(-ans);
			exp = injectValsAndEnclosedWithMathJax(a, b, var, exp);
			step2.setExpression(exp);
			step2.setExplanation("Simplify");
			steps.add(step2);
			
			SolutionStep step3 = new SolutionStep();
			exp = "-VAR * -1 = "+(-ans)+ " * -1";
			exp = injectValsAndEnclosedWithMathJax(a, b, var, exp);
			step3.setExpression(exp);
			step3.setExplanation("Multiply by -1 to remove minus sign");
			steps.add(step3);
			
			SolutionStep step4 = new SolutionStep();
			exp = "VAR = "+ans;
			exp = injectValsAndEnclosedWithMathJax(a, b, var, exp);
			step4.setExpression(exp);
			step4.setExplanation("Simplify, solved");
			steps.add(step4);
		}
		
		return steps;
	}

	private static String injectValsAndEnclosedWithMathJax(int a,int b,String var,String exp ) {		
		exp = exp.replace("a", String.valueOf(a));
		exp = exp.replace("b", String.valueOf(b));
		//exp = exp.replace("c", String.valueOf(c));
		exp = exp.replaceAll("VAR", var);
		exp = CommonFunctionAndValues.enclosedWithMathJaxExp(exp);
		return exp;		
	}

}
