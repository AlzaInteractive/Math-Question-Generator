package com.alza.quiz.qfactory.algebra;

import java.util.ArrayList;
import java.util.List;

import com.alza.quiz.model.ProblemPattern;
import com.alza.quiz.model.SolutionStep;
import com.alza.quiz.util.CommonFunctionAndValues;

public class Level3MixedOperationBSolution {

	public static List<SolutionStep> getSolutionSteps(int a,int b,int c,int d, String var, ProblemPattern p) {
		List<SolutionStep> steps = new ArrayList<>();
		//1 (aVAR - b) ÷ c = d", "(cd + b)/a
		if (p.question.equals("(aVAR - b) ÷ c = d")) {			
			int ans = (c*d + b)/a; 

			SolutionStep step1 = new SolutionStep();
			String exp = "(aVAR - b) ÷ c * c = d * c";
			exp = injectValsAndEnclosedWithMathJax(a, b, c, d, var, exp);						
			step1.setExpression(exp);
			step1.setExplanation("Multiply to remove divisor");
			steps.add(step1);

			SolutionStep step2 = new SolutionStep();
			exp = "aVAR - b = "+(d*c);
			exp = injectValsAndEnclosedWithMathJax(a,b,c,d, var, exp);
			step2.setExpression(exp);
			step2.setExplanation("Simplify");
			steps.add(step2);

			SolutionStep step3 = new SolutionStep();
			exp = "aVAR - b + b = "+(d*c)+" + b";
			exp = injectValsAndEnclosedWithMathJax(a,b,c,d,var, exp);
			step3.setExpression(exp);
			step3.setExplanation("Add to remove constant");
			steps.add(step3);

			SolutionStep step4 = new SolutionStep();
			exp = "aVAR = "+((d*c)+b);
			exp = injectValsAndEnclosedWithMathJax(a, b, c, d, var, exp);
			step4.setExpression(exp);
			step4.setExplanation("Simplify");
			steps.add(step4);

			SolutionStep step5 = new SolutionStep();
			exp = "aVAR ÷ a = "+((d*c)+b)+" ÷ a";
			exp = injectValsAndEnclosedWithMathJax(a,b,c,d,var, exp);
			step5.setExpression(exp);
			step5.setExplanation("Divide to remove multiplier/coefficient");
			steps.add(step5);

			SolutionStep step6 = new SolutionStep();
			exp = "VAR = "+ans;
			exp = injectValsAndEnclosedWithMathJax(a, b, c, d, var, exp);
			step6.setExpression(exp);
			step6.setExplanation("Simplify, solved");
			steps.add(step6);					

		} 
		//2 (aVAR + b) ÷ c = d", "(cd - b)/a
		else if (p.question.equals("(aVAR + b) ÷ c = d")) {
			int ans = (c*d - b)/a; 

			SolutionStep step1 = new SolutionStep();
			String exp = "(aVAR + b) ÷ c * c = d * c";
			exp = injectValsAndEnclosedWithMathJax(a, b, c, d, var, exp);						
			step1.setExpression(exp);
			step1.setExplanation("Multiply to remove divisor");
			steps.add(step1);

			SolutionStep step2 = new SolutionStep();
			exp = "aVAR + b = "+(d*c);
			exp = injectValsAndEnclosedWithMathJax(a,b,c,d, var, exp);
			step2.setExpression(exp);
			step2.setExplanation("Simplify");
			steps.add(step2);

			SolutionStep step3 = new SolutionStep();
			exp = "aVAR + b - b = "+(d*c)+" - b";
			exp = injectValsAndEnclosedWithMathJax(a,b,c,d,var, exp);
			step3.setExpression(exp);
			step3.setExplanation("Subtract to remove constant");
			steps.add(step3);

			SolutionStep step4 = new SolutionStep();
			exp = "aVAR = "+((d*c)-b);
			exp = injectValsAndEnclosedWithMathJax(a, b, c, d, var, exp);
			step4.setExpression(exp);
			step4.setExplanation("Simplify");
			steps.add(step4);

			SolutionStep step5 = new SolutionStep();
			exp = "aVAR ÷ a = "+((d*c)-b)+" ÷ a";
			exp = injectValsAndEnclosedWithMathJax(a,b,c,d,var, exp);
			step5.setExpression(exp);
			step5.setExplanation("Divide to remove multiplier/coefficient");
			steps.add(step5);

			SolutionStep step6 = new SolutionStep();
			exp = "VAR = "+ans;
			exp = injectValsAndEnclosedWithMathJax(a, b, c, d, var, exp);
			step6.setExpression(exp);
			step6.setExplanation("Simplify, solved");
			steps.add(step6);
		}
		//3 (b - (aVAR)) ÷ c = d", "(cd - b)/-a
		else if (p.question.equals("(b - (aVAR)) ÷ c = d")) {
			int ans = (d*c - b)/-a; 

			SolutionStep step1 = new SolutionStep();
			String exp = "(b - (aVAR)) ÷ c * c = d * c";
			exp = injectValsAndEnclosedWithMathJax(a, b, c, d, var, exp);						
			step1.setExpression(exp);
			step1.setExplanation("Multiply to remove divisor");
			steps.add(step1);

			SolutionStep step2 = new SolutionStep();
			exp = "b - aVAR = "+(d*c);
			exp = injectValsAndEnclosedWithMathJax(a,b,c,d, var, exp);
			step2.setExpression(exp);
			step2.setExplanation("Simplify");
			steps.add(step2);

			SolutionStep step3 = new SolutionStep();
			exp = "b - aVAR - b = "+(d*c)+" - b";
			exp = injectValsAndEnclosedWithMathJax(a,b,c,d,var, exp);
			step3.setExpression(exp);
			step3.setExplanation("Subtract to remove constant");
			steps.add(step3);

			SolutionStep step4 = new SolutionStep();
			exp = "-aVAR = "+((d*c)-b);
			exp = injectValsAndEnclosedWithMathJax(a, b, c, d, var, exp);
			step4.setExpression(exp);
			step4.setExplanation("Simplify");
			steps.add(step4);

			SolutionStep step5 = new SolutionStep();
			exp = "-aVAR ÷ a = "+((d*c)-b)+" ÷ a";
			exp = injectValsAndEnclosedWithMathJax(a,b,c,d,var, exp);
			step5.setExpression(exp);
			step5.setExplanation("Divide to remove multiplier/coefficient");
			steps.add(step5);

			SolutionStep step6 = new SolutionStep();
			exp = "-VAR = "+(-ans);
			exp = injectValsAndEnclosedWithMathJax(a, b, c, d, var, exp);
			step6.setExpression(exp);
			step6.setExplanation("Simplify");
			steps.add(step6);

			SolutionStep step7 = new SolutionStep();
			exp = "VAR = "+ans;
			exp = injectValsAndEnclosedWithMathJax(a, b, c, d, var, exp);
			step7.setExpression(exp);
			step7.setExplanation("Solved");
			steps.add(step7);
		}
		//4 (-aVAR - b) ÷ c = d", "(cd + b)/-a
		else if (p.question.equals("(-aVAR - b) ÷ c = d")) {
			int ans = (d*c + b)/-a; 

			SolutionStep step1 = new SolutionStep();
			String exp = "(-aVAR - b) ÷ c * c = d * c";
			exp = injectValsAndEnclosedWithMathJax(a, b, c, d, var, exp);						
			step1.setExpression(exp);
			step1.setExplanation("Multiply to remove divisor");
			steps.add(step1);

			SolutionStep step2 = new SolutionStep();
			exp = "-aVAR - b = "+(d*c);
			exp = injectValsAndEnclosedWithMathJax(a,b,c,d, var, exp);
			step2.setExpression(exp);
			step2.setExplanation("Simplify");
			steps.add(step2);

			SolutionStep step3 = new SolutionStep();
			exp = "-aVAR - b + b = "+(d*c)+" + b";
			exp = injectValsAndEnclosedWithMathJax(a,b,c,d,var, exp);
			step3.setExpression(exp);
			step3.setExplanation("Add to remove constant");
			steps.add(step3);

			SolutionStep step4 = new SolutionStep();
			exp = "-aVAR = "+((d*c)+b);
			exp = injectValsAndEnclosedWithMathJax(a, b, c, d, var, exp);
			step4.setExpression(exp);
			step4.setExplanation("Simplify");
			steps.add(step4);

			SolutionStep step5 = new SolutionStep();
			exp = "-aVAR ÷ a = "+((d*c)+b)+" ÷ a";
			exp = injectValsAndEnclosedWithMathJax(a,b,c,d,var, exp);
			step5.setExpression(exp);
			step5.setExplanation("Divide to remove multiplier/coefficient");
			steps.add(step5);

			SolutionStep step6 = new SolutionStep();
			exp = "-VAR = "+(-ans);
			exp = injectValsAndEnclosedWithMathJax(a, b, c, d, var, exp);
			step6.setExpression(exp);
			step6.setExplanation("Simplify");
			steps.add(step6);

			SolutionStep step7 = new SolutionStep();
			exp = "VAR = "+ans;
			exp = injectValsAndEnclosedWithMathJax(a, b, c, d, var, exp);
			step7.setExpression(exp);
			step7.setExplanation("Solved");
			steps.add(step7);
		}
		//5 (aVAR - b) ÷ -c = d", "(-cd + b)/a
		else if (p.question.equals("(aVAR - b) ÷ -c = d")) {			
			int ans = (-c*d + b)/a; 

			SolutionStep step1 = new SolutionStep();
			String exp = "(aVAR - b) ÷ -c * -c = d * -c";
			exp = injectValsAndEnclosedWithMathJax(a, b, c, d, var, exp);						
			step1.setExpression(exp);
			step1.setExplanation("Multiply to remove divisor");
			steps.add(step1);

			SolutionStep step2 = new SolutionStep();
			exp = "aVAR - b = "+(d*-c);
			exp = injectValsAndEnclosedWithMathJax(a,b,c,d, var, exp);
			step2.setExpression(exp);
			step2.setExplanation("Simplify");
			steps.add(step2);

			SolutionStep step3 = new SolutionStep();
			exp = "aVAR - b + b = "+(d*-c)+" + b";
			exp = injectValsAndEnclosedWithMathJax(a,b,c,d,var, exp);
			step3.setExpression(exp);
			step3.setExplanation("Add to remove constant");
			steps.add(step3);

			SolutionStep step4 = new SolutionStep();
			exp = "aVAR = "+((d*-c)+b);
			exp = injectValsAndEnclosedWithMathJax(a, b, c, d, var, exp);
			step4.setExpression(exp);
			step4.setExplanation("Simplify");
			steps.add(step4);

			SolutionStep step5 = new SolutionStep();
			exp = "aVAR ÷ a = "+((d*-c)+b)+" ÷ a";
			exp = injectValsAndEnclosedWithMathJax(a,b,c,d,var, exp);
			step5.setExpression(exp);
			step5.setExplanation("Divide to remove multiplier/coefficient");
			steps.add(step5);

			SolutionStep step6 = new SolutionStep();
			exp = "VAR = "+ans;
			exp = injectValsAndEnclosedWithMathJax(a, b, c, d, var, exp);
			step6.setExpression(exp);
			step6.setExplanation("Simplify, solved");
			steps.add(step6);					

		} 
		//6 (aVAR + b) ÷ c = -d", "(-cd - b)/a
		else if (p.question.equals("(aVAR + b) ÷ c = -d")) {
			int ans = (c*-d - b)/a; 

			SolutionStep step1 = new SolutionStep();
			String exp = "(aVAR + b) ÷ c * c = -d * c";
			exp = injectValsAndEnclosedWithMathJax(a, b, c, d, var, exp);						
			step1.setExpression(exp);
			step1.setExplanation("Multiply to remove divisor");
			steps.add(step1);

			SolutionStep step2 = new SolutionStep();
			exp = "aVAR + b = "+(-d*c);
			exp = injectValsAndEnclosedWithMathJax(a,b,c,d, var, exp);
			step2.setExpression(exp);
			step2.setExplanation("Simplify");
			steps.add(step2);

			SolutionStep step3 = new SolutionStep();
			exp = "aVAR + b - b = "+(-d*c)+" - b";
			exp = injectValsAndEnclosedWithMathJax(a,b,c,d,var, exp);
			step3.setExpression(exp);
			step3.setExplanation("Subtract to remove constant");
			steps.add(step3);

			SolutionStep step4 = new SolutionStep();
			exp = "aVAR = "+((-d*c)-b);
			exp = injectValsAndEnclosedWithMathJax(a, b, c, d, var, exp);
			step4.setExpression(exp);
			step4.setExplanation("Simplify");
			steps.add(step4);

			SolutionStep step5 = new SolutionStep();
			exp = "aVAR ÷ a = "+((-d*c)-b)+" ÷ a";
			exp = injectValsAndEnclosedWithMathJax(a,b,c,d,var, exp);
			step5.setExpression(exp);
			step5.setExplanation("Divide to remove multiplier/coefficient");
			steps.add(step5);

			SolutionStep step6 = new SolutionStep();
			exp = "VAR = "+ans;
			exp = injectValsAndEnclosedWithMathJax(a, b, c, d, var, exp);
			step6.setExpression(exp);
			step6.setExplanation("Simplify, solved");
			steps.add(step6);
		}
		//7 (b - (aVAR)) ÷ -c = d", "(cd + b)/a
		else if (p.question.equals("(b - (aVAR)) ÷ -c = d")) {
			int ans = (d*c + b)/a; 

			SolutionStep step1 = new SolutionStep();
			String exp = "(b - (aVAR)) ÷ -c * -c = d * -c";
			exp = injectValsAndEnclosedWithMathJax(a, b, c, d, var, exp);						
			step1.setExpression(exp);
			step1.setExplanation("Multiply to remove divisor");
			steps.add(step1);

			SolutionStep step2 = new SolutionStep();
			exp = "b - aVAR = "+(d*-c);
			exp = injectValsAndEnclosedWithMathJax(a,b,c,d, var, exp);
			step2.setExpression(exp);
			step2.setExplanation("Simplify");
			steps.add(step2);

			SolutionStep step3 = new SolutionStep();
			exp = "b - aVAR - b = "+(d*-c)+" - b";
			exp = injectValsAndEnclosedWithMathJax(a,b,c,d,var, exp);
			step3.setExpression(exp);
			step3.setExplanation("Subtract to remove constant");
			steps.add(step3);

			SolutionStep step4 = new SolutionStep();
			exp = "-aVAR = "+((d*-c)-b);
			exp = injectValsAndEnclosedWithMathJax(a, b, c, d, var, exp);
			step4.setExpression(exp);
			step4.setExplanation("Simplify");
			steps.add(step4);

			SolutionStep step5 = new SolutionStep();
			exp = "-aVAR ÷ a = "+((d*-c)-b)+" ÷ a";
			exp = injectValsAndEnclosedWithMathJax(a,b,c,d,var, exp);
			step5.setExpression(exp);
			step5.setExplanation("Divide to remove multiplier/coefficient");
			steps.add(step5);

			SolutionStep step6 = new SolutionStep();
			exp = "-VAR = "+(-ans);
			exp = injectValsAndEnclosedWithMathJax(a, b, c, d, var, exp);
			step6.setExpression(exp);
			step6.setExplanation("Simplify");
			steps.add(step6);

			SolutionStep step7 = new SolutionStep();
			exp = "VAR = "+ans;
			exp = injectValsAndEnclosedWithMathJax(a, b, c, d, var, exp);
			step7.setExpression(exp);
			step7.setExplanation("Solved");
			steps.add(step7);
		}
		//8 (-aVAR - b) ÷ c = -d", "(-cd + b)/-a
		else if (p.question.equals("(-aVAR - b) ÷ c = -d")) {
			int ans = (-d*c + b)/-a; 

			SolutionStep step1 = new SolutionStep();
			String exp = "(-aVAR - b) ÷ c * c = -d * c";
			exp = injectValsAndEnclosedWithMathJax(a, b, c, d, var, exp);						
			step1.setExpression(exp);
			step1.setExplanation("Multiply to remove divisor");
			steps.add(step1);

			SolutionStep step2 = new SolutionStep();
			exp = "-aVAR - b = "+(-d*c);
			exp = injectValsAndEnclosedWithMathJax(a,b,c,d, var, exp);
			step2.setExpression(exp);
			step2.setExplanation("Simplify");
			steps.add(step2);

			SolutionStep step3 = new SolutionStep();
			exp = "-aVAR - b + b = "+(-d*c)+" + b";
			exp = injectValsAndEnclosedWithMathJax(a,b,c,d,var, exp);
			step3.setExpression(exp);
			step3.setExplanation("Add to remove constant");
			steps.add(step3);

			SolutionStep step4 = new SolutionStep();
			exp = "-aVAR = "+((-d*c)+b);
			exp = injectValsAndEnclosedWithMathJax(a, b, c, d, var, exp);
			step4.setExpression(exp);
			step4.setExplanation("Simplify");
			steps.add(step4);

			SolutionStep step5 = new SolutionStep();
			exp = "-aVAR ÷ a = "+((-d*c)+b)+" ÷ a";
			exp = injectValsAndEnclosedWithMathJax(a,b,c,d,var, exp);
			step5.setExpression(exp);
			step5.setExplanation("Divide to remove multiplier/coefficient");
			steps.add(step5);

			SolutionStep step6 = new SolutionStep();
			exp = "-VAR = "+(-ans);
			exp = injectValsAndEnclosedWithMathJax(a, b, c, d, var, exp);
			step6.setExpression(exp);
			step6.setExplanation("Simplify");
			steps.add(step6);

			SolutionStep step7 = new SolutionStep();
			exp = "VAR = "+ans;
			exp = injectValsAndEnclosedWithMathJax(a, b, c, d, var, exp);
			step7.setExpression(exp);
			step7.setExplanation("Solved");
			steps.add(step7);
		}

		return steps;
	}

	private static String injectValsAndEnclosedWithMathJax(int a,int b,int c,int d,String var,String exp ) {		
		exp = exp.replace("a", String.valueOf(a));
		exp = exp.replace("b", String.valueOf(b));
		exp = exp.replace("c", String.valueOf(c));
		exp = exp.replace("d", String.valueOf(d));
		exp = exp.replaceAll("VAR", var);
		exp = CommonFunctionAndValues.enclosedWithMathJaxExp(exp);
		return exp;		
	}

}
