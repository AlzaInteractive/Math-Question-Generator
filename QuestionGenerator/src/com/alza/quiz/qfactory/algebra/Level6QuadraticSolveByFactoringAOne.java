package com.alza.quiz.qfactory.algebra;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.concurrent.ThreadLocalRandom;

import com.alza.quiz.model.ISingleQuizPrimaryAttributeGenerator;
import com.alza.quiz.model.MultipleChoiceQuiz;
import com.alza.quiz.model.Quiz;
import com.alza.quiz.model.QuizLevel;
import com.alza.quiz.model.SolutionStep;
import com.alza.quiz.qfactory.IQuestionFactory;
import com.alza.quiz.util.CommonFunctionAndValues;

public class Level6QuadraticSolveByFactoringAOne implements IQuestionFactory {

	private int numOfQuestion = 5;
	private Map<Integer, ProblemSkeleton> qMap = new HashMap<Integer, Level6QuadraticSolveByFactoringAOne.ProblemSkeleton>();
	private Locale loc;
	private ResourceBundle bundle;
	private ResourceBundle bundleAlgebra,bundleAlgebraSteps;

	public Level6QuadraticSolveByFactoringAOne(Locale loc) {
		this.loc = loc;
		initStringFromLocale();
	}

	public Level6QuadraticSolveByFactoringAOne() {
		this.loc = new Locale("in", "ID");
		initStringFromLocale();
	}

	private void initStringFromLocale() {
		bundle = ResourceBundle.getBundle("lang.langbundle", loc);
		bundleAlgebra = ResourceBundle.getBundle("lang.langbundle-algebra", loc);
		bundleAlgebraSteps = ResourceBundle.getBundle("lang.algebra-steps", loc);
	}

	@Override
	public Quiz generateQuiz() {
		List<Quiz> quizList = generateQuizList();
		int rnd = new Random().nextInt(quizList.size());
		return quizList.get(rnd);
	}

	@Override
	public Quiz generateQuiz(QuizLevel quizLevel) {
		return generateQuiz();
	}

	@Override
	public List<Quiz> generateQuizList() {
		List<Quiz> lq = new ArrayList<Quiz>();
		for (int i = 0; i < this.numOfQuestion; i++) {
			ProblemSkeleton p = generateUniqueProblem(i);
			Quiz q = p.generateSingleQuiz();
			setQuizSecondaryAttributes(q);
			q.setSolutionSteps(p.generateSolutionSteps());
			lq.add(q);
		}
		return lq;
	}

	private ProblemSkeleton generateUniqueProblem(int idx) {
		ProblemSkeleton p;
		do {
			p = new ProblemSkeleton(idx);
		} while (qMap.containsKey(p.hash()));
		qMap.put(p.hash(), p);
		return p;
	}

	@Override
	public List<Quiz> generateQuizList(int numOfQuestion) {
		this.numOfQuestion = numOfQuestion;
		return generateQuizList();
	}

	private void setQuizSecondaryAttributes(Quiz q) {
		q.setDifficultyLevel(QuizLevel.MUDAH);
		q.setLessonSubcategory(bundleAlgebra.getString("algebra.level5.quadratic"));
		q.setLessonClassifier(bundle.getString("mathelementary"));
		q.setLessonGrade(5);
		q.setSubCategoryOrder(6);
		q.setLessonCategory(bundle.getString("algebra"));
		q.setLocale(loc);
	}

	protected class ProblemSkeleton implements ISingleQuizPrimaryAttributeGenerator {
		int a,b,c;	
		int num1;
		int num2;
		String var;		
		final String[] VARSYM = { "x", "y", "z" };
		ProblemSkeleton(int idx) {			
			
			var = VARSYM[ThreadLocalRandom.current().nextInt(0, VARSYM.length)];
			do {
				// default positive pair
				num1 = ThreadLocalRandom.current().nextInt(2, 9);
				num2 = ThreadLocalRandom.current().nextInt(2, 9);
				int mod = idx % 3;
				if (mod==1) {
					// positive and negative pair
					num1 = num1 * CommonFunctionAndValues.getRandom(new int[] {1,-1});
					if (num1 > 0) {
						num2 = num2 * -1;
					}
				} else if (mod==2) {
					// negative pair
					num1 = num1 * -1;
					num2 = num2 * -1;					
				}
				
			} while (num1 == num2 || num1 + num2 == 0);
			a = 1;
			b = num1 + num2;
			c = num1 * num2;
		}

		int hash() {
			String s = a + " " +b+" "+c;
			return (CommonFunctionAndValues.hashSimple(s));
		}

		private String replaceAllSymbols(String s) {
			s = s.replace("avar", String.valueOf(this.a));
			s = s.replace("bvar", String.valueOf(this.b));
			s = s.replace("cvar", String.valueOf(this.c));			
			s = s.replace("VAR", String.valueOf(var));
			return s;
		}
		
		public List<SolutionStep> generateSolutionSteps(){
			List<SolutionStep> steps = new ArrayList<>();		
				
			SolutionStep step1 = new SolutionStep();			
			String exp = "$$a=avar$$, $$b=bvar$$, $$c=cvar$$";				
			exp = replaceAllSymbols(exp);
			step1.setExpression(exp);
			step1.setExplanation(bundleAlgebraSteps.getString("lv6detabc"));
			steps.add(step1);
												
			SolutionStep step2 = new SolutionStep();			
			exp = "$$"+num1+"+"+num2+"="+(this.b)+"$$ and "
					+ "$$"+num1+"\\times"+num2+"="+(this.c)+"$$";								
			step2.setExpression(exp);
			step2.setExplanation(bundleAlgebraSteps.getString("lv6pair1"));
			steps.add(step2);
			
			SolutionStep step3 = new SolutionStep();			
			exp = generateFactoredForm() +"=0 ";
			exp = replaceAllSymbols(exp);
			exp = CommonFunctionAndValues.enclosedWithMathJaxExp(exp);
			step3.setExpression(exp);			
			step3.setExplanation(bundleAlgebraSteps.getString("lv6rwr1"));
			steps.add(step3);
			
			SolutionStep step4 = new SolutionStep();			
			exp = "$$"+generateFirstFactorIsZero()+"$$ or $$"
					+generateSecondFactorIsZero()+"$$";						
			exp = replaceAllSymbols(exp);			
			step4.setExpression(exp);
			step4.setExplanation(bundleAlgebraSteps.getString("lv6stsfy"));
			steps.add(step4);
			
			SolutionStep step5 = new SolutionStep();			
			exp = "$$VAR="+(-num1)+"$$ or $$VAR="+(-num2)+"$$";						
			exp = replaceAllSymbols(exp);			
			step5.setExpression(exp);
			step5.setExplanation(bundleAlgebraSteps.getString("globsolvfor")+" $$"+this.var+"$$");
			steps.add(step5);			
									
			return steps;
		}

		private String[] wrongChoices() {			
			int ans1 = num1 * -1;
			int ans2 = num2 * -1;
			
			String[] choices = { 
					num1+","+num2, 
					num1+","+ans2,
					ans1+","+num2,					
					};
			return choices;
		}
		
		private String generateFactoredForm() {
			String firstFactor,secondFactor;
			if (num1>0) {
				firstFactor = "("+var+"+"+num1+")";
			} else {
				firstFactor = "("+var+num1+")";
			}
			if (num2>0) {
				secondFactor = "("+var+"+"+num2+")";
			} else {
				secondFactor = "("+var+num2+")";
			}
			return firstFactor+secondFactor;
		}
		
		private String generateFirstFactorIsZero() {
			String firstFactor;
			if (num1>0) {
				firstFactor = "("+var+"+"+num1+")";
			} else {
				firstFactor = "("+var+num1+")";
			}			
			return firstFactor+"=0";
		}
		
		private String generateSecondFactorIsZero() {
			String secondFactor;
			if (num2>0) {
				secondFactor = "("+var+"+"+num2+")";
			} else {
				secondFactor = "("+var+num2+")";
			}			
			return secondFactor+"=0";
		}
		
		private String generateQuadraticForm() {
			String s = "VAR^2";
			if (b > 0) {
				s += "+bvarVAR";
			} else {
				s += "bvarVAR";
			}
			if (c > 0) {
				s += "+cvar";
			} else {
				s += "cvar";
			}
			return s;
		}
		
		@Override
		public String generateQuestion() {
			String s = "VAR^2";
			if (b > 0) {
				s += "+bvarVAR";
			} else {
				s += "bvarVAR";
			}
			if (c > 0) {
				s += "+cvar";
			} else {
				s += "cvar";
			}
			s += "=0";
			s = replaceAllSymbols(s);
			return s;
		}

		@Override
		public String generateQuestionMathjax() {
			String s = generateQuestion();
			s = replaceAllSymbols(s);
			s = CommonFunctionAndValues.enclosedWithMathJaxExp(s);			
			String s2 = var + "=?";
			s2 = CommonFunctionAndValues.enclosedWithMathJaxExp(s2);
			s = s + " " + s2;
			return s;
		}

		@Override
		public String generateQuestionWolfram() {
			return generateQuestion();
		}

		@Override
		public String[] generateChoices() {
			int choiceSize = 3;
			List<String> choices = Arrays.asList(wrongChoices());
			String[] finalChoices = new String[choiceSize];
			Collections.shuffle(choices);
			for (int i = 0; i < choiceSize - 1; i++) {
				finalChoices[i] = choices.get(i);
			}
			finalChoices[choiceSize - 1] = generateAnswer();
			return finalChoices;
		}

		@Override
		public String generateAnswer() {			
			return (num1*-1)+","+(num2*-1);
		}

		@Override
		public Quiz generateSingleQuiz() {
			MultipleChoiceQuiz q = new MultipleChoiceQuiz();
			q.setQuestion(generateQuestionMathjax());
			q.setProblemString(generateQuestionWolfram());
			q.setCorrectAnswer(generateAnswer());
			q.setChoices(generateChoices());
			return q;
		}

	}

}
