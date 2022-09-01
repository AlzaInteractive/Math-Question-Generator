package com.alza.quiz.qfactory.algebra;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.alza.quiz.model.ProblemPattern;
import com.alza.quiz.model.SolutionStep;
import com.alza.quiz.util.CommonFunctionAndValues;

public class Level3MixedOperationBSolution {

	public static List<SolutionStep> getSolutionSteps(int a,int b,int c,int d, 
			String var, ProblemPattern p, ResourceBundle bundle) {
		List<SolutionStep> steps = new ArrayList<>();
		String exp;
		//1 (aVAR - b) ÷ c = d", "(cd + b)/a
		int ans = 0;
		if (p.question.equals("(aVAR - b) ÷ c = d")) {			
			ans = (c*d + b)/a; 

			SolutionStep step1 = new SolutionStep();
			exp = "(aVAR - b) ÷ c * c = d * c";
			exp = injectValsAndEnclosedWithMathJax(a, b, c, d, var, exp);						
			step1.setExpression(exp);
			step1.setExplanation(bundle.getString("globmult"));
			steps.add(step1);

			SolutionStep step2 = new SolutionStep();
			exp = "aVAR - b = "+(d*c);
			exp = injectValsAndEnclosedWithMathJax(a,b,c,d, var, exp);
			step2.setExpression(exp);
			step2.setExplanation(bundle.getString("globsimp"));
			steps.add(step2);

			SolutionStep step3 = new SolutionStep();
			exp = "aVAR - b + b = "+(d*c)+" + b";
			exp = injectValsAndEnclosedWithMathJax(a,b,c,d,var, exp);
			step3.setExpression(exp);
			step3.setExplanation(bundle.getString("globadd"));
			steps.add(step3);

			SolutionStep step4 = new SolutionStep();
			exp = "aVAR = "+((d*c)+b);
			exp = injectValsAndEnclosedWithMathJax(a, b, c, d, var, exp);
			step4.setExpression(exp);
			step4.setExplanation(bundle.getString("globsimp"));
			steps.add(step4);

			SolutionStep step5 = new SolutionStep();
			exp = "aVAR ÷ a = "+((d*c)+b)+" ÷ a";
			exp = injectValsAndEnclosedWithMathJax(a,b,c,d,var, exp);
			step5.setExpression(exp);
			step5.setExplanation(bundle.getString("globdiv"));
			steps.add(step5);							

		} 
		//2 (aVAR + b) ÷ c = d", "(cd - b)/a
		else if (p.question.equals("(aVAR + b) ÷ c = d")) {
			ans = (c*d - b)/a; 

			SolutionStep step1 = new SolutionStep();
			exp = "(aVAR + b) ÷ c * c = d * c";
			exp = injectValsAndEnclosedWithMathJax(a, b, c, d, var, exp);						
			step1.setExpression(exp);
			step1.setExplanation(bundle.getString("globmult"));
			steps.add(step1);

			SolutionStep step2 = new SolutionStep();
			exp = "aVAR + b = "+(d*c);
			exp = injectValsAndEnclosedWithMathJax(a,b,c,d, var, exp);
			step2.setExpression(exp);
			step2.setExplanation(bundle.getString("globsimp"));
			steps.add(step2);

			SolutionStep step3 = new SolutionStep();
			exp = "aVAR + b - b = "+(d*c)+" - b";
			exp = injectValsAndEnclosedWithMathJax(a,b,c,d,var, exp);
			step3.setExpression(exp);
			step3.setExplanation(bundle.getString("globsubtract"));
			steps.add(step3);

			SolutionStep step4 = new SolutionStep();
			exp = "aVAR = "+((d*c)-b);
			exp = injectValsAndEnclosedWithMathJax(a, b, c, d, var, exp);
			step4.setExpression(exp);
			step4.setExplanation(bundle.getString("globsimp"));
			steps.add(step4);

			SolutionStep step5 = new SolutionStep();
			exp = "aVAR ÷ a = "+((d*c)-b)+" ÷ a";
			exp = injectValsAndEnclosedWithMathJax(a,b,c,d,var, exp);
			step5.setExpression(exp);
			step5.setExplanation(bundle.getString("globdiv"));
			steps.add(step5);

		}
		//3 (b - (aVAR)) ÷ c = d", "(cd - b)/-a
		else if (p.question.equals("(b - (aVAR)) ÷ c = d")) {
			ans = (d*c - b)/-a; 

			SolutionStep step1 = new SolutionStep();
			exp = "(b - (aVAR)) ÷ c * c = d * c";
			exp = injectValsAndEnclosedWithMathJax(a, b, c, d, var, exp);						
			step1.setExpression(exp);
			step1.setExplanation(bundle.getString("globmult"));
			steps.add(step1);

			SolutionStep step2 = new SolutionStep();
			exp = "b - aVAR = "+(d*c);
			exp = injectValsAndEnclosedWithMathJax(a,b,c,d, var, exp);
			step2.setExpression(exp);
			step2.setExplanation(bundle.getString("globsimp"));
			steps.add(step2);

			SolutionStep step3 = new SolutionStep();
			exp = "b - aVAR - b = "+(d*c)+" - b";
			exp = injectValsAndEnclosedWithMathJax(a,b,c,d,var, exp);
			step3.setExpression(exp);
			step3.setExplanation(bundle.getString("globsubtract"));
			steps.add(step3);

			SolutionStep step4 = new SolutionStep();
			exp = "-aVAR = "+((d*c)-b);
			exp = injectValsAndEnclosedWithMathJax(a, b, c, d, var, exp);
			step4.setExpression(exp);
			step4.setExplanation(bundle.getString("globsimp"));
			steps.add(step4);

			SolutionStep step5 = new SolutionStep();
			exp = "-aVAR ÷ a = "+((d*c)-b)+" ÷ a";
			exp = injectValsAndEnclosedWithMathJax(a,b,c,d,var, exp);
			step5.setExpression(exp);
			step5.setExplanation(bundle.getString("globdiv"));
			steps.add(step5);

			SolutionStep step6 = new SolutionStep();
			exp = "-VAR = "+(-ans);
			exp = injectValsAndEnclosedWithMathJax(a, b, c, d, var, exp);
			step6.setExpression(exp);
			step6.setExplanation(bundle.getString("globsimp"));
			steps.add(step6);
			
		}
		//4 (-aVAR - b) ÷ c = d", "(cd + b)/-a
		else if (p.question.equals("(-aVAR - b) ÷ c = d")) {
			ans = (d*c + b)/-a; 

			SolutionStep step1 = new SolutionStep();
			exp = "(-aVAR - b) ÷ c * c = d * c";
			exp = injectValsAndEnclosedWithMathJax(a, b, c, d, var, exp);						
			step1.setExpression(exp);
			step1.setExplanation(bundle.getString("globmult"));
			steps.add(step1);

			SolutionStep step2 = new SolutionStep();
			exp = "-aVAR - b = "+(d*c);
			exp = injectValsAndEnclosedWithMathJax(a,b,c,d, var, exp);
			step2.setExpression(exp);
			step2.setExplanation(bundle.getString("globsimp"));
			steps.add(step2);

			SolutionStep step3 = new SolutionStep();
			exp = "-aVAR - b + b = "+(d*c)+" + b";
			exp = injectValsAndEnclosedWithMathJax(a,b,c,d,var, exp);
			step3.setExpression(exp);
			step3.setExplanation(bundle.getString("globadd"));
			steps.add(step3);

			SolutionStep step4 = new SolutionStep();
			exp = "-aVAR = "+((d*c)+b);
			exp = injectValsAndEnclosedWithMathJax(a, b, c, d, var, exp);
			step4.setExpression(exp);
			step4.setExplanation(bundle.getString("globsimp"));
			steps.add(step4);

			SolutionStep step5 = new SolutionStep();
			exp = "-aVAR ÷ a = "+((d*c)+b)+" ÷ a";
			exp = injectValsAndEnclosedWithMathJax(a,b,c,d,var, exp);
			step5.setExpression(exp);
			step5.setExplanation(bundle.getString("globdiv"));
			steps.add(step5);

			SolutionStep step6 = new SolutionStep();
			exp = "-VAR = "+(-ans);
			exp = injectValsAndEnclosedWithMathJax(a, b, c, d, var, exp);
			step6.setExpression(exp);
			step6.setExplanation(bundle.getString("globsimp"));
			steps.add(step6);

		}
		//5 (aVAR - b) ÷ -c = d", "(-cd + b)/a
		else if (p.question.equals("(aVAR - b) ÷ -c = d")) {			
			ans = (-c*d + b)/a; 

			SolutionStep step1 = new SolutionStep();
			exp = "(aVAR - b) ÷ -c * -c = d * -c";
			exp = injectValsAndEnclosedWithMathJax(a, b, c, d, var, exp);						
			step1.setExpression(exp);
			step1.setExplanation(bundle.getString("globmult"));
			steps.add(step1);

			SolutionStep step2 = new SolutionStep();
			exp = "aVAR - b = "+(d*-c);
			exp = injectValsAndEnclosedWithMathJax(a,b,c,d, var, exp);
			step2.setExpression(exp);
			step2.setExplanation(bundle.getString("globsimp"));
			steps.add(step2);

			SolutionStep step3 = new SolutionStep();
			exp = "aVAR - b + b = "+(d*-c)+" + b";
			exp = injectValsAndEnclosedWithMathJax(a,b,c,d,var, exp);
			step3.setExpression(exp);
			step3.setExplanation(bundle.getString("globadd"));
			steps.add(step3);

			SolutionStep step4 = new SolutionStep();
			exp = "aVAR = "+((d*-c)+b);
			exp = injectValsAndEnclosedWithMathJax(a, b, c, d, var, exp);
			step4.setExpression(exp);
			step4.setExplanation(bundle.getString("globsimp"));
			steps.add(step4);

			SolutionStep step5 = new SolutionStep();
			exp = "aVAR ÷ a = "+((d*-c)+b)+" ÷ a";
			exp = injectValsAndEnclosedWithMathJax(a,b,c,d,var, exp);
			step5.setExpression(exp);
			step5.setExplanation(bundle.getString("globdiv"));
			steps.add(step5);

		} 
		//6 (aVAR + b) ÷ c = -d", "(-cd - b)/a
		else if (p.question.equals("(aVAR + b) ÷ c = -d")) {
			ans = (c*-d - b)/a; 

			SolutionStep step1 = new SolutionStep();
			exp = "(aVAR + b) ÷ c * c = -d * c";
			exp = injectValsAndEnclosedWithMathJax(a, b, c, d, var, exp);						
			step1.setExpression(exp);
			step1.setExplanation(bundle.getString("globmult"));
			steps.add(step1);

			SolutionStep step2 = new SolutionStep();
			exp = "aVAR + b = "+(-d*c);
			exp = injectValsAndEnclosedWithMathJax(a,b,c,d, var, exp);
			step2.setExpression(exp);
			step2.setExplanation(bundle.getString("globsimp"));
			steps.add(step2);

			SolutionStep step3 = new SolutionStep();
			exp = "aVAR + b - b = "+(-d*c)+" - b";
			exp = injectValsAndEnclosedWithMathJax(a,b,c,d,var, exp);
			step3.setExpression(exp);
			step3.setExplanation(bundle.getString("globsubtract"));
			steps.add(step3);

			SolutionStep step4 = new SolutionStep();
			exp = "aVAR = "+((-d*c)-b);
			exp = injectValsAndEnclosedWithMathJax(a, b, c, d, var, exp);
			step4.setExpression(exp);
			step4.setExplanation(bundle.getString("globsimp"));
			steps.add(step4);

			SolutionStep step5 = new SolutionStep();
			exp = "aVAR ÷ a = "+((-d*c)-b)+" ÷ a";
			exp = injectValsAndEnclosedWithMathJax(a,b,c,d,var, exp);
			step5.setExpression(exp);
			step5.setExplanation(bundle.getString("globdiv"));
			steps.add(step5);
			
		}
		//7 (b - (aVAR)) ÷ -c = d", "(cd + b)/a
		else if (p.question.equals("(b - (aVAR)) ÷ -c = d")) {
			ans = (d*c + b)/a; 

			SolutionStep step1 = new SolutionStep();
			exp = "(b - (aVAR)) ÷ -c * -c = d * -c";
			exp = injectValsAndEnclosedWithMathJax(a, b, c, d, var, exp);						
			step1.setExpression(exp);
			step1.setExplanation(bundle.getString("globmult"));
			steps.add(step1);

			SolutionStep step2 = new SolutionStep();
			exp = "b - aVAR = "+(d*-c);
			exp = injectValsAndEnclosedWithMathJax(a,b,c,d, var, exp);
			step2.setExpression(exp);
			step2.setExplanation(bundle.getString("globsimp"));
			steps.add(step2);

			SolutionStep step3 = new SolutionStep();
			exp = "b - aVAR - b = "+(d*-c)+" - b";
			exp = injectValsAndEnclosedWithMathJax(a,b,c,d,var, exp);
			step3.setExpression(exp);
			step3.setExplanation(bundle.getString("globsubtract"));
			steps.add(step3);

			SolutionStep step4 = new SolutionStep();
			exp = "-aVAR = "+((d*-c)-b);
			exp = injectValsAndEnclosedWithMathJax(a, b, c, d, var, exp);
			step4.setExpression(exp);
			step4.setExplanation(bundle.getString("globsimp"));
			steps.add(step4);

			SolutionStep step5 = new SolutionStep();
			exp = "-aVAR ÷ a = "+((d*-c)-b)+" ÷ a";
			exp = injectValsAndEnclosedWithMathJax(a,b,c,d,var, exp);
			step5.setExpression(exp);
			step5.setExplanation(bundle.getString("globdiv"));
			steps.add(step5);

			SolutionStep step6 = new SolutionStep();
			exp = "-VAR = "+(-ans);
			exp = injectValsAndEnclosedWithMathJax(a, b, c, d, var, exp);
			step6.setExpression(exp);
			step6.setExplanation(bundle.getString("globsimp"));
			steps.add(step6);

		}
		//8 (-aVAR - b) ÷ c = -d", "(-cd + b)/-a
		else if (p.question.equals("(-aVAR - b) ÷ c = -d")) {
			ans = (-d*c + b)/-a; 

			SolutionStep step1 = new SolutionStep();
			exp = "(-aVAR - b) ÷ c * c = -d * c";
			exp = injectValsAndEnclosedWithMathJax(a, b, c, d, var, exp);						
			step1.setExpression(exp);
			step1.setExplanation(bundle.getString("globmult"));
			steps.add(step1);

			SolutionStep step2 = new SolutionStep();
			exp = "-aVAR - b = "+(-d*c);
			exp = injectValsAndEnclosedWithMathJax(a,b,c,d, var, exp);
			step2.setExpression(exp);
			step2.setExplanation(bundle.getString("globsimp"));
			steps.add(step2);

			SolutionStep step3 = new SolutionStep();
			exp = "-aVAR - b + b = "+(-d*c)+" + b";
			exp = injectValsAndEnclosedWithMathJax(a,b,c,d,var, exp);
			step3.setExpression(exp);
			step3.setExplanation(bundle.getString("globadd"));
			steps.add(step3);

			SolutionStep step4 = new SolutionStep();
			exp = "-aVAR = "+((-d*c)+b);
			exp = injectValsAndEnclosedWithMathJax(a, b, c, d, var, exp);
			step4.setExpression(exp);
			step4.setExplanation(bundle.getString("globsimp"));
			steps.add(step4);

			SolutionStep step5 = new SolutionStep();
			exp = "-aVAR ÷ a = "+((-d*c)+b)+" ÷ a";
			exp = injectValsAndEnclosedWithMathJax(a,b,c,d,var, exp);
			step5.setExpression(exp);
			step5.setExplanation(bundle.getString("globdiv"));
			steps.add(step5);

			SolutionStep step6 = new SolutionStep();
			exp = "-VAR = "+(-ans);
			exp = injectValsAndEnclosedWithMathJax(a, b, c, d, var, exp);
			step6.setExpression(exp);
			step6.setExplanation(bundle.getString("globsimp"));
			steps.add(step6);

		}
		
		SolutionStep step7 = new SolutionStep();
		exp = "VAR = "+ans;
		exp = injectValsAndEnclosedWithMathJax(a, b, c, d, var, exp);
		step7.setExpression(exp);
		step7.setExplanation(bundle.getString("globsolv"));
		steps.add(step7);

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
