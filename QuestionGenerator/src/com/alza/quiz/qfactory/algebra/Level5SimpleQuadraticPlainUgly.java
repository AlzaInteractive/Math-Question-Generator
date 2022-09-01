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

public class Level5SimpleQuadraticPlainUgly implements IQuestionFactory{

	private int numOfQuestion = 5;
	private Map<Integer, ProblemSkeleton> qMap = new HashMap<Integer, Level5SimpleQuadraticPlainUgly.ProblemSkeleton>();
	private Locale loc;
	private ResourceBundle bundle;
	private ResourceBundle bundleAlgebra,bundleAlgebraSteps;
	
	public Level5SimpleQuadraticPlainUgly(Locale loc){
		this.loc = loc;
		initStringFromLocale();
	}
	public Level5SimpleQuadraticPlainUgly(){
		this.loc = new Locale("in", "ID");
		initStringFromLocale();
	}
	private void initStringFromLocale(){
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
		for (int i=0;i<this.numOfQuestion;i++) {
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
		this.numOfQuestion  = numOfQuestion;
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
	
	protected class ProblemSkeleton implements ISingleQuizPrimaryAttributeGenerator{		
		int square;
		String var;		
		final String[] VARSYM = {"x","y","z"};
		List<Integer> squares = new ArrayList<Integer>();
		ProblemSkeleton(int idx) {
			int rootLimit = 9;
			for (int i =1;i<=rootLimit;i++) {
				squares.add(i*i);
			}
			var = VARSYM[ThreadLocalRandom.current().nextInt(0, VARSYM.length)];
			do {
				square = ThreadLocalRandom.current().nextInt(3, rootLimit*rootLimit);
			} while (squares.contains(square));
												
		}
		
		int hash() {
			String s = square+" ";
			return (CommonFunctionAndValues.hashSimple(s));
		}		
		
		
		private String replaceAllSymbols(String s) {
			s = s.replace("v1", String.valueOf(square));		
			s = s.replace("VAR", String.valueOf(var));
			return s;
		}
		
		public List<SolutionStep> generateSolutionSteps(){
			List<SolutionStep> steps = new ArrayList<>();		
			
			SolutionStep step1 = new SolutionStep();
			step1.setExplanation(bundleAlgebraSteps.getString("globtkroot"));
			String exp = "VAR = ± \\sqrtv1";		
			exp = replaceAllSymbols(exp);
			exp = CommonFunctionAndValues.enclosedWithMathJaxExp(exp);
			step1.setExpression(exp);			
			steps.add(step1);
															
			if (getSquareDivisor() > 1) {
				SolutionStep stepx = new SolutionStep();
				stepx.setExplanation(bundleAlgebraSteps.getString("globsimpsq"));
				int outRoot = (int) Math.sqrt(getSquareDivisor());
				int insideRoot = this.square / getSquareDivisor();
				exp = "$$VAR = ± \\sqrt{"+getSquareDivisor()+" \\times "+insideRoot+"}";
				exp += " = ± "+outRoot+"\\sqrt"+insideRoot+"$$";
				exp = replaceAllSymbols(exp);				
				stepx.setExpression(exp);			
				steps.add(stepx);
			}
			
			SolutionStep step4 = new SolutionStep();			
			exp = generateSimplifiedRoot();
			exp = replaceAllSymbols(exp);			
			step4.setExpression(exp);
			step4.setExplanation(bundleAlgebraSteps.getString("globsolv"));
			steps.add(step4);
							
									
			return steps;
		}
		
		private String[] wrongChoices() {
			String[] choices = { 
					(this.square) + "," + (-this.square), 
					(this.square) + "," + "√"+(-this.square),
					"√"+(this.square) ,
					"0," + "√"+(this.square),
					"√"+(this.square)+",0",
					};
			return choices;
		}
		
		private int getSquareDivisor() {
			int sqDiv = 1;
			for (int i = squares.size()-1;i>=0;i--) {
				int curSq = squares.get(i).intValue(); 
				if (this.square % curSq == 0) {
					sqDiv = squares.get(i).intValue();
					break;
				}
			}									
			return sqDiv; 
		}
				
		
		public String generateSimplifiedRoot() {
			int sqDiv = 1;
			for (int i = squares.size()-1;i>=0;i--) {
				int curSq = squares.get(i).intValue(); 
				if (this.square % curSq == 0) {
					sqDiv = squares.get(i).intValue();
					break;
				}
			}
			if (sqDiv>1) {
				int insideRoot = square / sqDiv;
				int outsideRoot = (int) Math.sqrt(sqDiv);
				if (outsideRoot > 1) {
					return String.valueOf("$$"+outsideRoot+"\\sqrt"+insideRoot
							+"$$ or $$-"+outsideRoot+"\\sqrt"+insideRoot+"$$");
				}
			}

			return String.valueOf("$$\\sqrt"+this.square+"$$ or "
					+ "$$-"+"\\sqrt"+this.square+"$$");
		}

		@Override
		public String generateQuestion() {
			String s = "VAR^2 = v1";			
			s = replaceAllSymbols(s);
			return s;
		}

		@Override
		public String generateQuestionMathjax() {
			String s = generateQuestion();
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
			for (int i = squares.size()-1;i>=0;i--) {
				int curSq = squares.get(i).intValue(); 
				if (this.square % curSq == 0) {
					sqDiv = squares.get(i).intValue();
					break;
				}
			}
			int insideRoot = square / sqDiv;
			int outsideRoot = (int) Math.sqrt(sqDiv);
			if (outsideRoot > 1) {
				return String.valueOf(outsideRoot+"√"+insideRoot+",-"+outsideRoot+"√"+insideRoot);
			}
			return String.valueOf("√"+this.square+",-"+"√"+this.square);
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
