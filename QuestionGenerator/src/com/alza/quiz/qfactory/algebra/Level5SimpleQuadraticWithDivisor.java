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

public class Level5SimpleQuadraticWithDivisor implements IQuestionFactory{

	private int numOfQuestion = 5;
	private Map<Integer, ProblemSkeleton> qMap = new HashMap<Integer, Level5SimpleQuadraticWithDivisor.ProblemSkeleton>();
	private Locale loc;
	private ResourceBundle bundle;
	private ResourceBundle bundleAlgebra;
	
	public Level5SimpleQuadraticWithDivisor(Locale loc){
		this.loc = loc;
		initStringFromLocale();
	}
	public Level5SimpleQuadraticWithDivisor(){
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
		int unsignedRoot;
		int rightVal;
		//int constant;
		//int coeff;
		int divisor;
		String var;
		boolean even = false;
		final String[] VARSYM = {"x","y","z"};
		ProblemSkeleton(int idx) {			
			if (idx % 2 == 0) {
				even = true;
			}
			int mod;
			var = VARSYM[ThreadLocalRandom.current().nextInt(0, VARSYM.length)];			
			do {
				unsignedRoot = ThreadLocalRandom.current().nextInt(2, 12);
				if (even) {
					divisor = ThreadLocalRandom.current().nextInt(2, 6);
				} else {
					divisor = ThreadLocalRandom.current().nextInt(-5, -1);
				}
				//constant = ThreadLocalRandom.current().nextInt(2, 6);
				mod = (unsignedRoot) % divisor;				
				rightVal = unsignedRoot*unsignedRoot / divisor;					
				
			} while (unsignedRoot == divisor || mod != 0
						|| divisor == rightVal
						|| rightVal==1 || rightVal == -1 || rightVal==0);
			
		}
		
		int hash() {
			String s = unsignedRoot+" "+" "+divisor;
			return (CommonFunctionAndValues.hashSimple(s));
		}
								
		private String replaceAllSymbols(String s) {			
			s = s.replace("rightval", String.valueOf(this.rightVal));
			//s = s.replace("constant", String.valueOf(this.constant));
			s = s.replace("divisor", String.valueOf(this.divisor));
			s = s.replace("VAR", String.valueOf(var));
			return s;
		}
		
		public List<SolutionStep> generateSolutionSteps(){
			List<SolutionStep> steps = new ArrayList<>();		
			
			SolutionStep step1 = new SolutionStep();
			step1.setExplanation("Multiply to remove divisor");
			String exp = "\\frac{VAR^2}{divisor} \\times divisor = rightval \\times divisor";
			exp = replaceAllSymbols(exp);
			exp = CommonFunctionAndValues.enclosedWithMathJaxExp(exp);
			step1.setExpression(exp);			
			steps.add(step1);
									
			SolutionStep step2 = new SolutionStep();			
			exp = "VAR^2 = "+(this.unsignedRoot * this.unsignedRoot);			
			exp = replaceAllSymbols(exp);
			exp = CommonFunctionAndValues.enclosedWithMathJaxExp(exp);
			step2.setExpression(exp);
			step2.setExplanation("Simplify");
			steps.add(step2);
			
			SolutionStep step3 = new SolutionStep();
			step3.setExplanation("Take square root on both sides");
			exp = "VAR = ± \\sqrt"+(this.unsignedRoot*this.unsignedRoot);			
			exp = replaceAllSymbols(exp);
			exp = CommonFunctionAndValues.enclosedWithMathJaxExp(exp);
			step3.setExpression(exp);			
			steps.add(step3);
									
			SolutionStep step4 = new SolutionStep();			
			exp = "$$VAR = " +this.unsignedRoot +"$$ or $$VAR = "+-this.unsignedRoot+"$$";
			exp = replaceAllSymbols(exp);			
			step4.setExpression(exp);
			step4.setExplanation("Simplify, solved");
			steps.add(step4);
									
			return steps;
		}
		
		private String[] wrongChoices() {			
			String[] choices = {
					(divisor)+",-"+(this.unsignedRoot),
					(-divisor)+","+(this.unsignedRoot),
					(-rightVal)+","+(this.divisor),
					this.unsignedRoot+""};			
			return choices;
		}

		@Override
		public String generateQuestion() {
			String s = "VAR^2/divisor = rightval";
			/**
			if (even) {
				s = "coeffVAR^2 - constant = rightval";
			}*/
			s = replaceAllSymbols(s);
			return s;
		}

		@Override
		public String generateQuestionMathjax() {
			String s = "\\frac{VAR^2}{divisor} = rightval";			
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
			int choiceSize = 3;
			List<String> choices = Arrays.asList(wrongChoices());
			String[] finalChoices = new String[choiceSize];
			Collections.shuffle(choices);
			for (int i=0;i<choiceSize-1;i++) {
				finalChoices[i] = choices.get(i);
			}
			finalChoices[choiceSize-1] = generateAnswer();
			return finalChoices;
		}

		@Override
		public String generateAnswer() {
			return this.unsignedRoot+",-"+this.unsignedRoot;
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
