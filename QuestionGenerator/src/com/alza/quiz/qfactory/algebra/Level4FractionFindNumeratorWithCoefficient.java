package com.alza.quiz.qfactory.algebra;

import java.util.ArrayList;
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

public class Level4FractionFindNumeratorWithCoefficient implements IQuestionFactory{

	private int numOfQuestion = 5;
	private Map<Integer, ProblemSkeleton> qMap = new HashMap<Integer, Level4FractionFindNumeratorWithCoefficient.ProblemSkeleton>();
	private Locale loc;
	private ResourceBundle bundle;
	private ResourceBundle bundleAlgebra;
	
	public Level4FractionFindNumeratorWithCoefficient(Locale loc){
		this.loc = loc;
		initStringFromLocale();
	}
	public Level4FractionFindNumeratorWithCoefficient(){
		this.loc = new Locale("in", "ID");
		initStringFromLocale();
	}
	private void initStringFromLocale(){
		bundle = ResourceBundle.getBundle("lang.langbundle", loc);
		bundleAlgebra = ResourceBundle.getBundle("lang.langbundle-algebra", loc);
		
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
		for (int i=0;i<this.numOfQuestion;i++) {
			ProblemSkeleton p = generateUniqueProblem();
			Quiz q = p.generateSingleQuiz();
			setQuizSecondaryAttributes(q);
			q.setSolutionSteps(p.generateSolutionSteps());
			lq.add(q);
		}
		return lq;
	}
			
	private ProblemSkeleton generateUniqueProblem() {		
		ProblemSkeleton p;
		do {			
			p = new ProblemSkeleton();
		} while (qMap.containsKey(p.hash()));
		qMap.put(p.hash(), p);
		return p;
	}

	@Override
	public List<Quiz> generateQuizList(int numOfQuestion) {
		this.numOfQuestion  = numOfQuestion;
		return generateQuizList();
	}
			
	private void setQuizSecondaryAttributes(Quiz q) {
		q.setDifficultyLevel(QuizLevel.MUDAH);
		q.setLessonSubcategory(bundleAlgebra.getString("algebra.level4.fraction"));
		q.setLessonClassifier(bundle.getString("mathelementary"));
		q.setLessonGrade(5);
		q.setSubCategoryOrder(6);		
		q.setLessonCategory(bundle.getString("algebra"));
		q.setLocale(loc);
	}
	
	protected class ProblemSkeleton implements ISingleQuizPrimaryAttributeGenerator{
		int a;
		int b;
		int coeff;
		String var;
		final String[] VARSYM = {"x","y","z"};
		ProblemSkeleton() {
			var = VARSYM[ThreadLocalRandom.current().nextInt(0, VARSYM.length)];
			int mod;
			do {
				a = ThreadLocalRandom.current().nextInt(2, 10);
				b = ThreadLocalRandom.current().nextInt(2, 10);
				coeff = ThreadLocalRandom.current().nextInt(2, 5);
				mod = a * b % coeff;				
			} while (a == b || mod != 0 || a == coeff || b == coeff);						
		}
		
		int hash() {
			String s = a+" "+b+" "+coeff;
			return (CommonFunctionAndValues.hashSimple(s));
		}
		
		int[] getChoices() {			
			int[] choices = {a*coeff/b,a+b,a*b/coeff};
			return choices;
		}
		
		private String replaceAllSymbols(String s) {
			s = s.replace("v1", String.valueOf(this.a));
			s = s.replace("v2", String.valueOf(this.b));
			s = s.replace("coeff", String.valueOf(this.coeff));
			s = s.replace("VAR", String.valueOf(var));
			return s;
		}
		
		public List<SolutionStep> generateSolutionSteps(){
			List<SolutionStep> steps = new ArrayList<>();								 		
						 		
			SolutionStep step1 = new SolutionStep();
			String exp = "v1 \\times v2 = \\frac{coeffVAR}{v2} \\times v2";
			exp = replaceAllSymbols(exp);
			exp = CommonFunctionAndValues.enclosedWithMathJaxExp(exp);
			step1.setExpression(exp);
			step1.setExplanation("Multiply to remove divisor");
			steps.add(step1);
			
			SolutionStep step2 = new SolutionStep();
			
			exp = (this.a*this.b) +" = coeffVAR";
			exp = replaceAllSymbols(exp);
			exp = CommonFunctionAndValues.enclosedWithMathJaxExp(exp);
			step2.setExpression(exp);
			step2.setExplanation("Simplify");
			steps.add(step2);
			
			SolutionStep step3 = new SolutionStep();			
			exp = (this.a*this.b) +" ÷ coeff = coeffVAR ÷ coeff";
			exp = replaceAllSymbols(exp);
			exp = CommonFunctionAndValues.enclosedWithMathJaxExp(exp);
			step3.setExpression(exp);
			step3.setExplanation("Divide to remove multiplier/coefficient");
			steps.add(step3);
			
			SolutionStep step4 = new SolutionStep();
			exp = generateAnswer() +" = VAR";
			exp = replaceAllSymbols(exp);
			exp = CommonFunctionAndValues.enclosedWithMathJaxExp(exp);
			step4.setExpression(exp);
			step4.setExplanation("Simplify, solved");
			steps.add(step4);
									
			return steps;
		}

		@Override
		public String generateQuestion() {
			String s = "v1 = coeffVAR / v2";
			s = replaceAllSymbols(s);
			return s;
		}

		@Override
		public String generateQuestionMathjax() {
			String s = "v1 = \\frac{coeffVAR}{v2}";
			s = replaceAllSymbols(s);
			s = CommonFunctionAndValues.enclosedWithMathJaxExp(s);
			String s2 = var +"=?";
			s2 = CommonFunctionAndValues.enclosedWithMathJaxExp(s2);
			s = s +" "+ s2;
			return s;
		}

		@Override
		public String generateQuestionWolfram() {	
			return generateQuestion();
		}

		@Override
		public String[] generateChoices() {
			int[] intCs = getChoices();			
			String[] choices = new String[intCs.length];
			for (int i=0;i<intCs.length;i++) {
				choices[i] = String.valueOf(intCs[i]); 
			}
			return choices;
		}

		@Override
		public String generateAnswer() {
			return String.valueOf(a*b/coeff);
		}

		@Override
		public Quiz generateSingleQuiz() {
			MultipleChoiceQuiz q = new MultipleChoiceQuiz();
			q.setQuestion(generateQuestionMathjax());
			q.setProblemString(generateQuestionWolfram());
			q.setCorrectAnswer(generateAnswer());
			q.setChoices(getChoices());			
			return q;
		}		
		
	}

}
