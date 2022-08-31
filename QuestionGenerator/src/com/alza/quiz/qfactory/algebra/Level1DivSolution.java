package com.alza.quiz.qfactory.algebra;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.alza.quiz.model.ProblemPattern;
import com.alza.quiz.model.SolutionStep;
import com.alza.quiz.util.CommonFunctionAndValues;

public class Level1DivSolution {

	public static List<SolutionStep> getSolutionSteps(int a,int b,String var, 
			ProblemPattern p, ResourceBundle bundle) {
		List<SolutionStep> steps = new ArrayList<>();		
		SolutionStep step1 = new SolutionStep(), step2 = new SolutionStep();
		String exp;
		int ans = 0;
		if (p.question.equals("VAR ÷ a = b")
				|| p.question.equals("VAR ÷ a = -b")) {
			exp = "VAR ÷ a * a = b * a";
			ans = b * a; 
			if (p.question.equals("VAR ÷ a = -b")) { // aVAR = -c
				exp = "VAR ÷ a * a = -b * a";
				ans = -b * a;
			}
			exp = injectValsAndEnclosedWithMathJax(a, b, var, exp);			
			step1.setExpression(exp);
			step1.setExplanation(bundle.getString("lv1multboth")+a+" "+bundle.getString("lv1rmvdiv"));
			
		} else if (p.question.equals("VAR ÷ -a = b")
				|| p.question.equals("VAR ÷ -a = -b")) {
			exp = "VAR ÷ -a * -a = b * -a";
			ans = b * -a; 
			if (p.question.equals("VAR ÷ -a = -b")) { // aVAR = -c
				exp = "VAR ÷ -a * -a = -b * -a";
				ans = -b * -a;
			}
			exp = injectValsAndEnclosedWithMathJax(a, b, var, exp);			
			step1.setExpression(exp);
			step1.setExplanation(bundle.getString("lv1multboth")+"-"+a+" "+bundle.getString("lv1rmvdiv"));
		}
		steps.add(step1);
		exp = "VAR = "+ans;
		exp = injectValsAndEnclosedWithMathJax(a, b, var, exp);
		step2.setExpression(exp);
		//step2.setExplanation("Perform simple arithmetic equation on both sides, "+var+ " is solved");
		step2.setExplanation(bundle.getString("lv1simp")+var+" "+ bundle.getString("lv1solv"));
		steps.add(step2);		
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
