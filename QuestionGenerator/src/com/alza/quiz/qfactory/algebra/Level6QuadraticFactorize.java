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
import com.alza.quiz.qfactory.IQuestionFactory;
import com.alza.quiz.util.CommonFunctionAndValues;

public class Level6QuadraticFactorize implements IQuestionFactory {

	private int numOfQuestion = 5;
	private Map<Integer, ProblemSkeleton> qMap = new HashMap<Integer, Level6QuadraticFactorize.ProblemSkeleton>();
	private Locale loc;
	private ResourceBundle bundle;
	private ResourceBundle bundleAlgebra;

	public Level6QuadraticFactorize(Locale loc) {
		this.loc = loc;
		initStringFromLocale();
	}

	public Level6QuadraticFactorize() {
		this.loc = new Locale("in", "ID");
		initStringFromLocale();
	}

	private void initStringFromLocale() {
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
		for (int i = 0; i < this.numOfQuestion; i++) {
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
		int firstFactorConst;
		int secondFactorConst;
		String var;		
		final String[] VARSYM = { "x", "y", "z" };
		ProblemSkeleton(int idx) {			
			
			var = VARSYM[ThreadLocalRandom.current().nextInt(0, VARSYM.length)];
			do {
				firstFactorConst = ThreadLocalRandom.current().nextInt(2, 9);
				firstFactorConst = firstFactorConst * CommonFunctionAndValues.getRandom(new int[] {1,-1});
				secondFactorConst = ThreadLocalRandom.current().nextInt(2, 9);
				secondFactorConst = secondFactorConst * CommonFunctionAndValues.getRandom(new int[] {1,-1});
				
			} while (firstFactorConst == secondFactorConst || firstFactorConst + secondFactorConst == 0);
			a = 1;
			b = firstFactorConst + secondFactorConst;
			c = firstFactorConst * secondFactorConst;
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

		private String[] wrongChoices() {
			String[] choices = { 
					(this.firstFactorConst) + "," + (this.secondFactorConst), 
					(this.firstFactorConst) + "," + (-this.secondFactorConst),
					(-this.firstFactorConst) + "," + (this.secondFactorConst),
					(this.a) + "," + (this.b)};
			return choices;
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
			return (-this.firstFactorConst) + "," + (-this.secondFactorConst);
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
