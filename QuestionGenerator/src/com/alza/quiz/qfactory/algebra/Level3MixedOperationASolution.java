package com.alza.quiz.qfactory.algebra;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.alza.quiz.model.ProblemPattern;
import com.alza.quiz.model.SolutionStep;
import com.alza.quiz.util.CommonFunctionAndValues;

public class Level3MixedOperationASolution {

	public static List<SolutionStep> getSolutionSteps(
			int a,int b,int c,int d, String var, ProblemPattern p, ResourceBundle bundle) {
		List<SolutionStep> steps = new ArrayList<>();
		//1 "(aVAR ÷ b) - c = d", "(bc + bd)/a"
		if (p.question.equals("(aVAR ÷ b) - c = d")) {			
			int ans = (b*c + b*d)/a; 

			SolutionStep step1 = new SolutionStep();
			String exp = "(aVAR ÷ b) - c + c = d + c";
			exp = injectValsAndEnclosedWithMathJax(a, b, c, d, var, exp);						
			step1.setExpression(exp);
			step1.setExplanation(bundle.getString("lv3add")+c+" "+bundle.getString("lv3tormv")+"- "+c);
			steps.add(step1);

			SolutionStep step2 = new SolutionStep();
			exp = "aVAR ÷ b = "+(d+c);
			exp = injectValsAndEnclosedWithMathJax(a,b,c,d, var, exp);
			step2.setExpression(exp);
			step2.setExplanation(bundle.getString("globsimp"));
			steps.add(step2);

			SolutionStep step3 = new SolutionStep();
			exp = "aVAR ÷ b * b = "+(d+c)+" * b";
			exp = injectValsAndEnclosedWithMathJax(a,b,c,d,var, exp);
			step3.setExpression(exp);
			step3.setExplanation(bundle.getString("globmult"));
			steps.add(step3);

			SolutionStep step4 = new SolutionStep();
			exp = "aVAR = "+((d+c)*b);
			exp = injectValsAndEnclosedWithMathJax(a, b, c, d, var, exp);
			step4.setExpression(exp);
			step4.setExplanation(bundle.getString("globsimp"));
			steps.add(step4);

			SolutionStep step5 = new SolutionStep();
			exp = "aVAR ÷ a = "+((d+c)*b)+" ÷ a";
			exp = injectValsAndEnclosedWithMathJax(a,b,c,d,var, exp);
			step5.setExpression(exp);
			step5.setExplanation(bundle.getString("globdiv"));
			steps.add(step5);

			SolutionStep step6 = new SolutionStep();
			exp = "VAR = "+ans;
			exp = injectValsAndEnclosedWithMathJax(a, b, c, d, var, exp);
			step6.setExpression(exp);
			step6.setExplanation(bundle.getString("globsimpsolv"));
			steps.add(step6);					

		} 
		//2 (aVAR ÷ b) + c = d", "(bd - bc)/a
		else if (p.question.equals("(aVAR ÷ b) + c = d")) {
			int ans = (b*d - b*c)/a; 

			SolutionStep step1 = new SolutionStep();
			String exp = "(aVAR ÷ b) + c - c = d - c";
			exp = injectValsAndEnclosedWithMathJax(a, b, c, d, var, exp);						
			step1.setExpression(exp);
			step1.setExplanation(bundle.getString("lv3subtract")+c+" "+bundle.getString("lv3tormv")+"+ "+c);
			steps.add(step1);

			SolutionStep step2 = new SolutionStep();
			exp = "aVAR ÷ b = "+(d-c);
			exp = injectValsAndEnclosedWithMathJax(a,b,c,d, var, exp);
			step2.setExpression(exp);
			step2.setExplanation(bundle.getString("globsimp"));
			steps.add(step2);

			SolutionStep step3 = new SolutionStep();
			exp = "aVAR ÷ b * b = "+(d-c)+" * b";
			exp = injectValsAndEnclosedWithMathJax(a,b,c,d,var, exp);
			step3.setExpression(exp);
			step3.setExplanation(bundle.getString("globmult"));
			steps.add(step3);

			SolutionStep step4 = new SolutionStep();
			exp = "aVAR = "+((d-c)*b);
			exp = injectValsAndEnclosedWithMathJax(a, b, c, d, var, exp);
			step4.setExpression(exp);
			step4.setExplanation(bundle.getString("globsimp"));
			steps.add(step4);

			SolutionStep step5 = new SolutionStep();
			exp = "aVAR ÷ a = "+((d-c)*b)+" ÷ a";
			exp = injectValsAndEnclosedWithMathJax(a,b,c,d,var, exp);
			step5.setExpression(exp);
			step5.setExplanation(bundle.getString("globdiv"));
			steps.add(step5);

			SolutionStep step6 = new SolutionStep();
			exp = "VAR = "+ans;
			exp = injectValsAndEnclosedWithMathJax(a, b, c, d, var, exp);
			step6.setExpression(exp);
			step6.setExplanation(bundle.getString("globsimpsolv"));
			steps.add(step6);
		}
		//3 (aVAR ÷ b) + c = -d", "(-bd - bc)/a
		else if (p.question.equals("(aVAR ÷ b) + c = -d")) {
			int ans = (-b * d - b * c)/a; 

			SolutionStep step1 = new SolutionStep();
			String exp = "(aVAR ÷ b) + c - c = -d - c";
			exp = injectValsAndEnclosedWithMathJax(a, b, c, d, var, exp);						
			step1.setExpression(exp);
			//step1.setExplanation("Subtract by "+c+" to remove + "+c);
			step1.setExplanation(bundle.getString("lv3subtract")+c+" "+bundle.getString("lv3tormv")+"+ "+c);
			steps.add(step1);

			SolutionStep step2 = new SolutionStep();
			exp = "aVAR ÷ b = "+(-d-c);
			exp = injectValsAndEnclosedWithMathJax(a,b,c,d, var, exp);
			step2.setExpression(exp);
			step2.setExplanation(bundle.getString("globsimp"));
			steps.add(step2);

			SolutionStep step3 = new SolutionStep();
			exp = "aVAR ÷ b * b = "+(-d-c)+" * b";
			exp = injectValsAndEnclosedWithMathJax(a,b,c,d,var, exp);
			step3.setExpression(exp);
			step3.setExplanation(bundle.getString("globmult"));
			steps.add(step3);

			SolutionStep step4 = new SolutionStep();
			exp = "aVAR = "+((-d-c)*b);
			exp = injectValsAndEnclosedWithMathJax(a, b, c, d, var, exp);
			step4.setExpression(exp);
			step4.setExplanation(bundle.getString("globsimp"));
			steps.add(step4);

			SolutionStep step5 = new SolutionStep();
			exp = "aVAR ÷ a = "+((-d-c)*b)+" ÷ a";
			exp = injectValsAndEnclosedWithMathJax(a,b,c,d,var, exp);
			step5.setExpression(exp);
			step5.setExplanation(bundle.getString("globdiv"));
			steps.add(step5);

			SolutionStep step6 = new SolutionStep();
			exp = "VAR = "+ans;
			exp = injectValsAndEnclosedWithMathJax(a, b, c, d, var, exp);
			step6.setExpression(exp);
			step6.setExplanation(bundle.getString("globsimpsolv"));
			steps.add(step6);
		}
		//4 (aVAR ÷ b) - c = -d", "(-bd + bc)/a
		else if (p.question.equals("(aVAR ÷ b) - c = -d")) {
			int ans = (-b * d + b * c)/a; 

			SolutionStep step1 = new SolutionStep();
			String exp = "(aVAR ÷ b) - c + c = -d + c";
			exp = injectValsAndEnclosedWithMathJax(a, b, c, d, var, exp);						
			step1.setExpression(exp);
			//step1.setExplanation("Add "+c+" to remove - "+c);
			step1.setExplanation(bundle.getString("lv3add")+c+" "+bundle.getString("lv3tormv")+"- "+c);
			steps.add(step1);

			SolutionStep step2 = new SolutionStep();
			exp = "aVAR ÷ b = "+(-d+c);
			exp = injectValsAndEnclosedWithMathJax(a,b,c,d, var, exp);
			step2.setExpression(exp);
			step2.setExplanation(bundle.getString("globsimp"));
			steps.add(step2);

			SolutionStep step3 = new SolutionStep();
			exp = "aVAR ÷ b * b = "+(-d+c)+" * b";
			exp = injectValsAndEnclosedWithMathJax(a,b,c,d,var, exp);
			step3.setExpression(exp);
			step3.setExplanation(bundle.getString("globmult"));
			steps.add(step3);

			SolutionStep step4 = new SolutionStep();
			exp = "aVAR = "+((-d+c)*b);
			exp = injectValsAndEnclosedWithMathJax(a, b, c, d, var, exp);
			step4.setExpression(exp);
			step4.setExplanation(bundle.getString("globsimp"));
			steps.add(step4);

			SolutionStep step5 = new SolutionStep();
			exp = "aVAR ÷ a = "+((-d+c)*b)+" ÷ a";
			exp = injectValsAndEnclosedWithMathJax(a,b,c,d,var, exp);
			step5.setExpression(exp);
			step5.setExplanation(bundle.getString("globdiv"));
			steps.add(step5);

			SolutionStep step6 = new SolutionStep();
			exp = "VAR = "+ans;
			exp = injectValsAndEnclosedWithMathJax(a, b, c, d, var, exp);
			step6.setExpression(exp);
			step6.setExplanation(bundle.getString("globsimpsolv"));
			steps.add(step6);
		}
		//5 (-aVAR ÷ b) - c = d", "(bd + bc)/-a
		else if (p.question.equals("(-aVAR ÷ b) - c = d")) {			
			int ans = (b*c + b*d)/-a; 

			SolutionStep step1 = new SolutionStep();
			String exp = "(-aVAR ÷ b) - c + c = d + c";
			exp = injectValsAndEnclosedWithMathJax(a, b, c, d, var, exp);						
			step1.setExpression(exp);
			//step1.setExplanation("Add "+c+" to remove - "+c);
			step1.setExplanation(bundle.getString("lv3add")+c+" "+bundle.getString("lv3tormv")+"- "+c);
			steps.add(step1);

			SolutionStep step2 = new SolutionStep();
			exp = "-aVAR ÷ b = "+(d+c);
			exp = injectValsAndEnclosedWithMathJax(a,b,c,d, var, exp);
			step2.setExpression(exp);
			step2.setExplanation(bundle.getString("globsimp"));
			steps.add(step2);

			SolutionStep step3 = new SolutionStep();
			exp = "-aVAR ÷ b * b = "+(d+c)+" * b";
			exp = injectValsAndEnclosedWithMathJax(a,b,c,d,var, exp);
			step3.setExpression(exp);
			step3.setExplanation(bundle.getString("globmult"));
			steps.add(step3);

			SolutionStep step4 = new SolutionStep();
			exp = "-aVAR = "+((d+c)*b);
			exp = injectValsAndEnclosedWithMathJax(a, b, c, d, var, exp);
			step4.setExpression(exp);
			step4.setExplanation(bundle.getString("globsimp"));
			steps.add(step4);

			SolutionStep step5 = new SolutionStep();
			exp = "-aVAR ÷ a = "+((d+c)*b)+" ÷ a";
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

			SolutionStep step7 = new SolutionStep();
			exp = "VAR = "+ans;
			exp = injectValsAndEnclosedWithMathJax(a, b, c, d, var, exp);
			step7.setExpression(exp);
			step7.setExplanation(bundle.getString("globsolv"));
			steps.add(step7);

		} 

		//6 (-aVAR ÷ b) + c = d", "(bd - bc)/-a
		else if (p.question.equals("(-aVAR ÷ b) + c = d")) {			
			int ans = (b*d - b*c)/-a; 

			SolutionStep step1 = new SolutionStep();
			String exp = "(-aVAR ÷ b) + c - c = d - c";
			exp = injectValsAndEnclosedWithMathJax(a, b, c, d, var, exp);						
			step1.setExpression(exp);
			//step1.setExplanation("Subtract by "+c+" to remove + "+c);
			step1.setExplanation(bundle.getString("lv3add")+c+" "+bundle.getString("lv3tormv")+"- "+c);
			steps.add(step1);

			SolutionStep step2 = new SolutionStep();
			exp = "-aVAR ÷ b = "+(d-c);
			exp = injectValsAndEnclosedWithMathJax(a,b,c,d, var, exp);
			step2.setExpression(exp);
			step2.setExplanation(bundle.getString("globsimp"));
			steps.add(step2);

			SolutionStep step3 = new SolutionStep();
			exp = "-aVAR ÷ b * b = "+(d-c)+" * b";
			exp = injectValsAndEnclosedWithMathJax(a,b,c,d,var, exp);
			step3.setExpression(exp);
			step3.setExplanation(bundle.getString("globmult"));
			steps.add(step3);

			SolutionStep step4 = new SolutionStep();
			exp = "-aVAR = "+((d-c)*b);
			exp = injectValsAndEnclosedWithMathJax(a, b, c, d, var, exp);
			step4.setExpression(exp);
			step4.setExplanation(bundle.getString("globsimp"));
			steps.add(step4);

			SolutionStep step5 = new SolutionStep();
			exp = "-aVAR ÷ a = "+((d-c)*b)+" ÷ a";
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

			SolutionStep step7 = new SolutionStep();
			exp = "VAR = "+ans;
			exp = injectValsAndEnclosedWithMathJax(a, b, c, d, var, exp);
			step7.setExpression(exp);
			step7.setExplanation(bundle.getString("globsolv"));
			steps.add(step7);

		} 

		//7 (-aVAR ÷ b) + c = -d", "(-bd - bc)/-a
		else if (p.question.equals("(-aVAR ÷ b) + c = -d")) {			
			int ans = (-b*d - b*c)/-a; 

			SolutionStep step1 = new SolutionStep();
			String exp = "(-aVAR ÷ b) + c - c = -d - c";
			exp = injectValsAndEnclosedWithMathJax(a, b, c, d, var, exp);						
			step1.setExpression(exp);
			//step1.setExplanation("Subtract by "+c+" to remove + "+c);
			step1.setExplanation(bundle.getString("lv3subtract")+c+" "+bundle.getString("lv3tormv")+"+ "+c);
			steps.add(step1);

			SolutionStep step2 = new SolutionStep();
			exp = "-aVAR ÷ b = "+(-d-c);
			exp = injectValsAndEnclosedWithMathJax(a,b,c,d, var, exp);
			step2.setExpression(exp);
			step2.setExplanation(bundle.getString("globsimp"));
			steps.add(step2);

			SolutionStep step3 = new SolutionStep();
			exp = "-aVAR ÷ b * b = "+(-d-c)+" * b";
			exp = injectValsAndEnclosedWithMathJax(a,b,c,d,var, exp);
			step3.setExpression(exp);
			step3.setExplanation(bundle.getString("globmult"));
			steps.add(step3);

			SolutionStep step4 = new SolutionStep();
			exp = "-aVAR = "+((-d-c)*b);
			exp = injectValsAndEnclosedWithMathJax(a, b, c, d, var, exp);
			step4.setExpression(exp);
			step4.setExplanation(bundle.getString("globsimp"));
			steps.add(step4);

			SolutionStep step5 = new SolutionStep();
			exp = "-aVAR ÷ a = "+((-d-c)*b)+" ÷ a";
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

			SolutionStep step7 = new SolutionStep();
			exp = "VAR = "+ans;
			exp = injectValsAndEnclosedWithMathJax(a, b, c, d, var, exp);
			step7.setExpression(exp);
			step7.setExplanation(bundle.getString("globsolv"));
			steps.add(step7);

		}

		//8 (-aVAR ÷ b) - c = -d", "(-bd + bc)/-a
		else if (p.question.equals("(-aVAR ÷ b) - c = -d")) {			
			int ans = (b*c - b*d)/-a; 

			SolutionStep step1 = new SolutionStep();
			String exp = "(-aVAR ÷ b) - c + c = -d + c";
			exp = injectValsAndEnclosedWithMathJax(a, b, c, d, var, exp);						
			step1.setExpression(exp);
			//step1.setExplanation("Add "+c+" to remove - "+c);
			step1.setExplanation(bundle.getString("lv3add")+c+" "+bundle.getString("lv3tormv")+"- "+c);
			steps.add(step1);

			SolutionStep step2 = new SolutionStep();
			exp = "-aVAR ÷ b = "+(-d+c);
			exp = injectValsAndEnclosedWithMathJax(a,b,c,d, var, exp);
			step2.setExpression(exp);
			step2.setExplanation(bundle.getString("globsimp"));
			steps.add(step2);

			SolutionStep step3 = new SolutionStep();
			exp = "-aVAR ÷ b * b = "+(-d+c)+" * b";
			exp = injectValsAndEnclosedWithMathJax(a,b,c,d,var, exp);
			step3.setExpression(exp);
			step3.setExplanation(bundle.getString("globmult"));
			steps.add(step3);

			SolutionStep step4 = new SolutionStep();
			exp = "-aVAR = "+((-d+c)*b);
			exp = injectValsAndEnclosedWithMathJax(a, b, c, d, var, exp);
			step4.setExpression(exp);
			step4.setExplanation(bundle.getString("globsimp"));
			steps.add(step4);

			SolutionStep step5 = new SolutionStep();
			exp = "-aVAR ÷ a = "+((-d+c)*b)+" ÷ a";
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

			SolutionStep step7 = new SolutionStep();
			exp = "VAR = "+ans;
			exp = injectValsAndEnclosedWithMathJax(a, b, c, d, var, exp);
			step7.setExpression(exp);
			step7.setExplanation(bundle.getString("globsolv"));
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
