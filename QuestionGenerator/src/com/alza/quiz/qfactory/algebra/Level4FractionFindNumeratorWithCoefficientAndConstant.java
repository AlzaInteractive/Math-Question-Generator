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
import com.alza.quiz.qfactory.IQuestionFactory;
import com.alza.quiz.util.CommonFunctionAndValues;

public class Level4FractionFindNumeratorWithCoefficientAndConstant implements IQuestionFactory{

	private int numOfQuestion = 5;
	private Map<Integer, ProblemSkeleton> qMap = new HashMap<Integer, Level4FractionFindNumeratorWithCoefficientAndConstant.ProblemSkeleton>();
	private Locale loc;
	private ResourceBundle bundle;
	private ResourceBundle bundleAlgebra;
	
	public Level4FractionFindNumeratorWithCoefficientAndConstant(Locale loc){
		this.loc = loc;
		initStringFromLocale();
	}
	public Level4FractionFindNumeratorWithCoefficientAndConstant(){
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
			ProblemSkeleton p = generateUniqueProblem(i);
			Quiz q = p.generateSingleQuiz();
			setQuizSecondaryAttributes(q);
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
		int constant;
		int coefficient;
		int numerator;
		String var;
		boolean even = false;
		final String[] VARSYM = {"x","y","z"};
		ProblemSkeleton(int idx) {
			if (idx % 2 == 0) {
				even = true;
			}
			var = VARSYM[ThreadLocalRandom.current().nextInt(0, VARSYM.length)];
			int mod;
			do {
				a = ThreadLocalRandom.current().nextInt(2, 9);
				b = ThreadLocalRandom.current().nextInt(2, 9);
				constant = ThreadLocalRandom.current().nextInt(2, 5);
				coefficient = ThreadLocalRandom.current().nextInt(2, 5);
				if (even) {
					mod = (a+constant)*b % coefficient;
				} else {
					mod = (a-constant)*b % coefficient;
				}
							
			} while (a == b || mod != 0 || a == constant || b == constant || coefficient % b == 0);						
		}
		
		int hash() {
			String s = a+" "+b+" "+constant+" "+coefficient;
			return (CommonFunctionAndValues.hashSimple(s));
		}
		
		int generateAnswerInt() {
			int ans = (a-constant)*b / coefficient;
			if (even) {
				ans = (a+constant)*b / coefficient;
			}
			return ans;
		}
		
		int[] getChoices() {			
			int[] choices = {generateAnswerInt(),(a - constant) * coefficient/b,(a + constant) * coefficient/b};
			return choices;
		}
		
		private String replaceAllSymbols(String s) {
			s = s.replace("v1", String.valueOf(this.a));
			s = s.replace("v2", String.valueOf(this.b));
			s = s.replace("constant", String.valueOf(this.constant));
			s = s.replace("coeff", String.valueOf(this.coefficient));
			s = s.replace("VAR", String.valueOf(var));
			return s;
		}

		@Override
		public String generateQuestion() {
			String s = "v1 = coeffVAR / v2 + constant";
			if (even) {
				s = "v1 = coeffVAR / v2 - constant";
			}
			s = replaceAllSymbols(s);
			return s;
		}

		@Override
		public String generateQuestionMathjax() {
			String s = "v1 = \\frac{coeffVAR}{v2} + constant";
			if (even) {
				s = "v1 = \\frac{coeffVAR}{v2} - constant";
			}
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
			return String.valueOf(generateAnswerInt());
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
