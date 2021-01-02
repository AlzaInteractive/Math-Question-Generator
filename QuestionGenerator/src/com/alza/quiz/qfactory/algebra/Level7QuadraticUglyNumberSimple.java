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

public class Level7QuadraticUglyNumberSimple implements IQuestionFactory {

	private int numOfQuestion = 5;
	private Map<Integer, ProblemSkeleton> qMap = new HashMap<Integer, Level7QuadraticUglyNumberSimple.ProblemSkeleton>();
	private Locale loc;
	private ResourceBundle bundle;
	private ResourceBundle bundleAlgebra;

	public Level7QuadraticUglyNumberSimple(Locale loc) {
		this.loc = loc;
		initStringFromLocale();
	}

	public Level7QuadraticUglyNumberSimple() {
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
		List<Integer> squares = new ArrayList<Integer>();
		ProblemSkeleton(int idx) {
			int rootLimit = 20;
			for (int i =1;i<=rootLimit;i++) {
				squares.add(i*i);
			}			
			var = VARSYM[ThreadLocalRandom.current().nextInt(0, VARSYM.length)];
			int bmod2a,b4acmod4asq;
			do {
				a = ThreadLocalRandom.current().nextInt(2, 9);
				a = a * CommonFunctionAndValues.getRandom(new int[] {1,-1});
				b = CommonFunctionAndValues.getRandom(new int[] {2,4,6,8});
				b = b * CommonFunctionAndValues.getRandom(new int[] {1,-1});
				c = a * CommonFunctionAndValues.getRandom(new int[] {2,3,4,5,6,7});
				c = c * CommonFunctionAndValues.getRandom(new int[] {1,-1});
				bmod2a = -b % (2*a);
				b4acmod4asq = b4ac() % (4*a*a);
			} while (b4ac() <= 1 || b4ac() > rootLimit * rootLimit || bmod2a !=0 || b4acmod4asq != 0);
			
		}
		
		int b4ac() {
			return  b*b - (4*a*c);
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
					(this.a) + "," + (-this.a), 
					(this.b) + "," + (-this.b),
					(this.a) + "," + (-this.b), 
					(this.b) + "," + (-this.c),
					(this.c) + "," + (-this.c), 
					(this.a) + "," + (-this.c),
			};
			return choices;
		}

		@Override
		public String generateQuestion() {
			String s = "avarVAR^2";
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
			int sqDiv = 1;
			int b24acdiv2a = b4ac()/(4*a*a);
			for (int i = squares.size()-1;i>=0;i--) {
				int curSq = squares.get(i).intValue(); 
				if (b24acdiv2a % curSq == 0) {
					sqDiv = squares.get(i).intValue();
					System.out.println(b24acdiv2a+" : "+sqDiv);
					break;
				}
			}
			 
			int leftSide = -b/(2*a);
			int insideRoot = b4ac() / (sqDiv*4*a*a);
			int outsideRoot = (int) Math.sqrt(sqDiv);
			String rightSide = outsideRoot + "√" + insideRoot;
			if (outsideRoot == 1) {
				rightSide = "√" + insideRoot;
			}					
			return String.valueOf(leftSide+"+"+rightSide+","+leftSide+"-"+rightSide);
			
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
