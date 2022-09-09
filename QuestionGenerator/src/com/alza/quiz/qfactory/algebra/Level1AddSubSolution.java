package com.alza.quiz.qfactory.algebra;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.alza.quiz.model.ProblemPattern;
import com.alza.quiz.model.SolutionStep;
import com.alza.quiz.util.CommonFunctionAndValues;

public class Level1AddSubSolution {

	public static List<SolutionStep> getSolutionSteps(int a,int b,String var, 
			ProblemPattern p, ResourceBundle bundle) {
		List<SolutionStep> steps = new ArrayList<>();		
		// "VAR + a = b", "b - a"
		SolutionStep step1= new SolutionStep(),step2 = new SolutionStep();
		String exp;
		int ans = 0;
		if (p.question.equals("VAR + a = b")) {
			step1 = new SolutionStep();
			exp = "VAR + a - a = b - a";
			exp = injectValsAndEnclosedWithMathJax(a, b, var, exp);			
			step1.setExpression(exp);
			//step1.setExplanation("Subtract "+a+" from both sides to remove + "+a);
			step1.setExplanation(bundle.getString("lv1subtract")+a+" "+bundle.getString("lv1frombothtoremove")+"+ "+a);
			ans = b - a;
			
		}
		// VAR - a = b
		else if (p.question.equals("VAR - a = b")) {
			step1 = new SolutionStep();
			exp = "VAR - a + a = b + a";
			exp = injectValsAndEnclosedWithMathJax(a, b, var, exp);
			step1.setExpression(exp);
			//step1.setExplanation("Add "+a+" to both sides to remove - "+a);
			step1.setExplanation(bundle.getString("lv1add")+a+" "+bundle.getString("lv1tobothtoremove")+a);
			ans = b + a;			
		}
		// "VAR + a = -b", "-b - a"		
		else if (p.question.equals("VAR + a = -b")) {
			step1 = new SolutionStep();
			exp = "VAR + a - a = -b - a";
			exp = injectValsAndEnclosedWithMathJax(a, b, var, exp);			
			step1.setExpression(exp);
			//step1.setExplanation("Subtract "+a+" from both sides to remove + "+a);
			step1.setExplanation(bundle.getString("lv1subtract")+a+" "+bundle.getString("lv1frombothtoremove")+"+ "+a);			
			ans = -b - a;			
		}		
		// VAR - a = -b
		else if (p.question.equals("VAR - a = -b")) {
			step1 = new SolutionStep();
			exp = "VAR - a + a = -b + a";
			exp = injectValsAndEnclosedWithMathJax(a, b, var, exp);
			step1.setExpression(exp);
			//step1.setExplanation("Add "+a+" to both sides to remove - "+a);
			step1.setExplanation(bundle.getString("lv1add")+a+" "+bundle.getString("lv1tobothtoremove")+a);			
			ans = -b + a;			
		}
		steps.add(step1);
		exp = "VAR = "+ans;
		exp = injectValsAndEnclosedWithMathJax(a, b, var, exp);
		step2.setExpression(exp);
		//step2.setExplanation("Perform simple arithmetic equation on both sides, "+var+ " is solved");
		step2.setExplanation(bundle.getString("lv1simp")+"$$"+var+"$$ "+ bundle.getString("lv1solv"));			
		steps.add(step2);
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
