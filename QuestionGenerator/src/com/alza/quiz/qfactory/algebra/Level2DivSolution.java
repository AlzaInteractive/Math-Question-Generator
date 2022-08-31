package com.alza.quiz.qfactory.algebra;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.alza.quiz.model.ProblemPattern;
import com.alza.quiz.model.SolutionStep;
import com.alza.quiz.util.CommonFunctionAndValues;

public class Level2DivSolution {

	public static List<SolutionStep> getSolutionSteps(int a,int b,String var, 
			ProblemPattern p, ResourceBundle bundle) {
		List<SolutionStep> steps = new ArrayList<>();		
		SolutionStep step1 = new SolutionStep();
		String exp;
		int ans=0;
		if (p.question.equals("-VAR ÷ a = b")
				|| p.question.equals("-VAR ÷ a = -b")) {
			exp = "-VAR ÷ a * a = b * a";
			ans = -(b * a); 
			if (p.question.equals("-VAR ÷ a = -b")) { // aVAR = -c
				exp = "-VAR ÷ a * a = -b * a";
				ans = -ans;
			}
			exp = injectValsAndEnclosedWithMathJax(a, b, var, exp);			
			step1.setExpression(exp);
			step1.setExplanation("Multiply both sides by "+a+" to remove the divisor");
			step1.setExplanation(bundle.getString("lv2multboth")+a+" "+bundle.getString("lv2rmvdiv"));			
			steps.add(step1);									
		} else if (p.question.equals("-VAR ÷ -a = b")
				|| p.question.equals("-VAR ÷ -a = -b")) {
			exp = "-VAR ÷ -a * -a = b * -a";
			ans = b * a; 
			if (p.question.equals("-VAR ÷ -a = -b")) { // aVAR = -c
				exp = "-VAR ÷ -a * -a = -b * -a";
				ans = -ans;
			}
			exp = injectValsAndEnclosedWithMathJax(a, b, var, exp);			
			step1.setExpression(exp);
			step1.setExplanation( bundle.getString("lv2multboth")+"-"+a+" "+bundle.getString("lv2rmvdiv"));
			steps.add(step1);			
		}
		
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
		step3.setExplanation(bundle.getString("lv2multbyneg1"));
		steps.add(step3);
		
		SolutionStep step4 = new SolutionStep();
		exp = "VAR = "+ans;
		exp = injectValsAndEnclosedWithMathJax(a, b, var, exp);
		step4.setExpression(exp);
		step4.setExplanation(bundle.getString("lv2simpsolv"));
		steps.add(step4);
		
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
