package com.alza.quiz.qfactory.algebra;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.alza.quiz.model.ProblemPattern;
import com.alza.quiz.model.SolutionStep;
import com.alza.quiz.util.CommonFunctionAndValues;

public class Level1MultSolution {

	public static List<SolutionStep> getSolutionSteps(int a,int b,int c,String var, 
			ProblemPattern p, ResourceBundle bundle) {
		List<SolutionStep> steps = new ArrayList<>();		
		SolutionStep step1 = new SolutionStep();
		String exp = "aVAR ÷ a = c ÷ a";
		int ans = c / a; 
		if (p.question.equals("aVAR = -c")) { // aVAR = -c
			exp = "aVAR ÷ a = -c ÷ a";
			ans = -c / a;
		}
		exp = injectValsAndEnclosedWithMathJax(a, b, c, var, exp);			
		step1.setExpression(exp);
		//step1.setExplanation("Divide both sides by "+a+" to remove the multiplier");
		step1.setExplanation(bundle.getString("lv1divboth")+a+" "+bundle.getString("lv1rmvmult"));		
		steps.add(step1);
		SolutionStep step2 = new SolutionStep();
					
		exp = "VAR = "+ans;
		exp = injectValsAndEnclosedWithMathJax(a, b, c, var, exp);
		step2.setExpression(exp);
		//step2.setExplanation("Perform simple arithmetic equation on both sides, "+var+ " is solved");
		step2.setExplanation(bundle.getString("lv1simp")+var+" "+ bundle.getString("lv1solv"));
		steps.add(step2);
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
