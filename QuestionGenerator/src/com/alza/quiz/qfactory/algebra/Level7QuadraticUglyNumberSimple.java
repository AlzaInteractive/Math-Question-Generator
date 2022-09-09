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

import com.alza.common.math.MathUtils;
import com.alza.quiz.model.ISingleQuizPrimaryAttributeGenerator;
import com.alza.quiz.model.MultipleChoiceQuiz;
import com.alza.quiz.model.Quiz;
import com.alza.quiz.model.QuizLevel;
import com.alza.quiz.model.SolutionStep;
import com.alza.quiz.qfactory.IQuestionFactory;
import com.alza.quiz.util.CommonFunctionAndValues;

public class Level7QuadraticUglyNumberSimple implements IQuestionFactory {

	private int numOfQuestion = 5;
	private Map<Integer, ProblemSkeleton> qMap = new HashMap<Integer, Level7QuadraticUglyNumberSimple.ProblemSkeleton>();
	private Locale loc;
	private ResourceBundle bundle;
	private ResourceBundle bundleAlgebra,bundleAlgebraSteps;

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
		int firstFactorConst;
		int secondFactorConst;
		String var;		
		final String[] VARSYM = { "x", "y", "z" };
		List<Integer> squares = new ArrayList<Integer>();
		ProblemSkeleton(int idx) {
			int rootLimit = 30;
			for (int i =1;i<=rootLimit;i++) {
				squares.add(i*i);
			}			
			var = VARSYM[ThreadLocalRandom.current().nextInt(0, VARSYM.length)];
			int bmod2a,b4acmod4asq;
			do {
				a = ThreadLocalRandom.current().nextInt(2, 13);
				a = a * CommonFunctionAndValues.getRandom(new int[] {1,-1});
				//b = CommonFunctionAndValues.getRandom(new int[] {2,4,6,8,12});
				b = ThreadLocalRandom.current().nextInt(2, 13);
				b = b * CommonFunctionAndValues.getRandom(new int[] {1,-1});
				c = a * CommonFunctionAndValues.getRandom(new int[] {2,3,4,5,6,7,8,9});
				c = c * CommonFunctionAndValues.getRandom(new int[] {1,-1});
				bmod2a = -b % (2*a);
				b4acmod4asq = b4ac() % (4*a*a);
			} while (b4ac() <= 1 
					|| b4ac() > rootLimit * rootLimit 
					|| bmod2a !=0 
					|| b4acmod4asq != 0
					|| b4acinsquares()
					 );
			
		}
		
		int b4ac() {
			return  b*b - (4*a*c);
		}
		
		boolean b4acinsquares() {
			for (int i=0;i<squares.size();i++) {
				if (b4ac() == squares.get(i)) return true;
			}
			return false;
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
			int[] alior = answerLeftInsideOutsideRoot();			
			
			int leftSideWrong1 = alior[1];
			int leftSideWrong2 = alior[2];
			
			int insideRootWrong1 = Math.abs(alior[0]);
			if (insideRootWrong1==1) insideRootWrong1 = ThreadLocalRandom.current().nextInt(3,8);
			int insideRootWrong2 = Math.abs(alior[2]);
			if (insideRootWrong2==1) insideRootWrong2 = ThreadLocalRandom.current().nextInt(3,8);
			
			int outsideRootWrong1 = Math.abs(alior[0]);
			if (outsideRootWrong1==1) outsideRootWrong1 = ThreadLocalRandom.current().nextInt(3,8);
			int outsideRootWrong2 = Math.abs(alior[1]);
			if (outsideRootWrong2==1) outsideRootWrong2 = ThreadLocalRandom.current().nextInt(3,8);
			
			String rightSideWrong1 = outsideRootWrong1 + "√" + insideRootWrong1;
			String rightSideWrong2 = outsideRootWrong2 + "√" + insideRootWrong2;
			
			
			String[] choices = { 
					String.valueOf(leftSideWrong1+"±"+rightSideWrong1),
					String.valueOf(leftSideWrong2+"±"+rightSideWrong2),					
			};
			return choices;
		}
		
		public List<SolutionStep> generateSolutionSteps(){
			List<SolutionStep> steps = new ArrayList<>();
					
			String exp;	
			SolutionStep step1 = new SolutionStep();			
			exp = "$$a=avar$$, $$b=bvar$$, $$c=cvar$$";				
			exp = replaceAllSymbols(exp);
			step1.setExpression(exp);
			step1.setExplanation(bundleAlgebraSteps.getString("lv6detabc"));
			steps.add(step1);
												
			SolutionStep step2 = new SolutionStep();			
			exp = "$$"+this.var+" = "+getValsInABCFormula()+"$$";											
			step2.setExpression(exp);
			step2.setExplanation(bundleAlgebraSteps.getString("lv7useform")
					+ " $$x = \\frac {-b \\pm \\sqrt {b^2 - 4ac}}{2a}$$");
			steps.add(step2);
			
			SolutionStep step3 = new SolutionStep();			
			exp = this.var+" = "+getSimpValsInABCFormula();						
			exp = CommonFunctionAndValues.enclosedWithMathJaxExp(exp);
			step3.setExpression(exp);
			step3.setExplanation(bundleAlgebraSteps.getString("globsimp"));			
			steps.add(step3);
			
			SolutionStep step4 = new SolutionStep();			
			exp = getFinalForm();									
			step4.setExpression(exp);
			step4.setExplanation(bundleAlgebraSteps.getString("globsolv"));			
			steps.add(step4);
			
			return steps;
		}
		
		public String getValsInABCFormula() {
			String s;
			String minB = "-"+this.b;
			if (b<0) {
				minB = "-("+this.b+")";
			}
			String fourAC = "4\\times "+this.a+" \\times "+this.c;
			String b24AC = this.b+"^2 - "+fourAC;
			if (this.b < 0) {
				b24AC = "("+this.b+")^2 - "+fourAC;
			}
			String twoA = "2 \\times "+this.a;
			String sqb24ac = "\\sqrt{"+b24AC+"}";
			String minBPM4AC = minB +" \\pm "+sqb24ac;
			s = "\\frac{"+minBPM4AC+"}{"+twoA+"}";			
			return s;
		}
		
		public String getSimpValsInABCFormula() {
			String s;			
			String minB = "-"+this.b;
			if (b<0) {
				minB = "-("+this.b+")";
			}
			String b2fourAC = String.valueOf((this.b*this.b)-(4 * this.a * this.c));
			String twoA = String.valueOf(2 * this.a);
			String sqb24ac = "\\sqrt{"+b2fourAC+"}";
			String minBPM4AC = minB +" \\pm "+sqb24ac;
			s = "\\frac{"+minBPM4AC+"}{"+twoA+"}";
			return s;
		}
		
		public String getFinalForm() {
			int[] alior = answerLeftInsideOutsideRoot();			
			int leftSide = alior[0];
			int insideRoot = alior[1];
			int outsideRoot = alior[2];
			String rightSide = outsideRoot + "\\sqrt" + insideRoot;
			if (outsideRoot == 1) {
				rightSide = "\\sqrt" + insideRoot;
			}
			return String.valueOf("$$"+this.var+" = "+leftSide+" \\pm "+rightSide+"$$");			
			
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
		
		private int[] answerLeftInsideOutsideRoot() {
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
			int[] alior = {leftSide,insideRoot,outsideRoot};
			return alior;
		}
		

		@Override
		public String generateAnswer() {
			int[] alior = answerLeftInsideOutsideRoot();			
			int leftSide = alior[0];
			int insideRoot = alior[1];
			int outsideRoot = alior[2];
			String rightSide = outsideRoot + "√" + insideRoot;
			if (outsideRoot == 1) {
				rightSide = "√" + insideRoot;
			}
			return String.valueOf(leftSide+"±"+rightSide);
			
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
