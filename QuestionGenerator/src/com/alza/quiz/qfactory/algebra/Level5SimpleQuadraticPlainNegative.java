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

public class Level5SimpleQuadraticPlainNegative implements IQuestionFactory{

	private int numOfQuestion = 5;
	private Map<Integer, ProblemSkeleton> qMap = new HashMap<Integer, Level5SimpleQuadraticPlainNegative.ProblemSkeleton>();
	private Locale loc;
	private ResourceBundle bundle;
	private ResourceBundle bundleAlgebra;
	
	public Level5SimpleQuadraticPlainNegative(Locale loc){
		this.loc = loc;
		initStringFromLocale();
	}
	public Level5SimpleQuadraticPlainNegative(){
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
		int root;		
		String var;		
		final String[] VARSYM = {"x","y","z"};
		ProblemSkeleton(int idx) {
			var = VARSYM[ThreadLocalRandom.current().nextInt(0, VARSYM.length)];						
			root = ThreadLocalRandom.current().nextInt(3, 9);									
		}
		
		int hash() {
			String s = root+" ";
			return (CommonFunctionAndValues.hashSimple(s));
		}		
		
		
		private String replaceAllSymbols(String s) {
			s = s.replace("v1", String.valueOf(this.root*this.root));		
			s = s.replace("VAR", String.valueOf(var));
			return s;
		}
		
		public List<SolutionStep> generateSolutionSteps(){
			List<SolutionStep> steps = new ArrayList<>();		
			
			SolutionStep step1 = new SolutionStep();
			step1.setExplanation("Multiply by -1");
			String exp = "-VAR^2 \\times -1 = v1 \\times -1";		
			exp = replaceAllSymbols(exp);
			exp = CommonFunctionAndValues.enclosedWithMathJaxExp(exp);
			step1.setExpression(exp);			
			steps.add(step1);
									
			SolutionStep step2 = new SolutionStep();			
			exp = "VAR^2 = v1";
			exp = replaceAllSymbols(exp);
			exp = CommonFunctionAndValues.enclosedWithMathJaxExp(exp);
			step2.setExpression(exp);
			step2.setExplanation("Simplify");
			steps.add(step2);
			
			SolutionStep step3 = new SolutionStep();
			step3.setExplanation("Take square root on both sides");
			exp = "VAR = ± \\sqrtv1";		
			exp = replaceAllSymbols(exp);
			exp = CommonFunctionAndValues.enclosedWithMathJaxExp(exp);
			step3.setExpression(exp);			
			steps.add(step3);
									
			SolutionStep step4 = new SolutionStep();			
			exp = "$$VAR = " +this.root +"$$ or $$VAR = "+-this.root+"$$";
			exp = replaceAllSymbols(exp);			
			step4.setExpression(exp);
			step4.setExplanation("Simplify, solved");
			steps.add(step4);
									
			return steps;
		}

		@Override
		public String generateQuestion() {
			String s = "-VAR^2 = -v1";			
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
				
			String[] choices = {this.root+",-"+this.root,
					(this.root*2)+",-"+(this.root*2),
					this.root+""};
			
			return choices;
		}

		@Override
		public String generateAnswer() {			
			return String.valueOf(this.root+",-"+this.root);
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
