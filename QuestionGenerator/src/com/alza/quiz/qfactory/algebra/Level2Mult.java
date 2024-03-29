package com.alza.quiz.qfactory.algebra;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.concurrent.ThreadLocalRandom;

import com.alza.quiz.model.MultipleChoiceQuiz;
import com.alza.quiz.model.ProblemPattern;
import com.alza.quiz.model.Quiz;
import com.alza.quiz.model.QuizLevel;
import com.alza.quiz.qfactory.IQuestionFactory;
import com.alza.quiz.util.CommonFunctionAndValues;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class Level2Mult implements IQuestionFactory{
	// private final static String MJXTAG ="$$"; 
	Locale loc;
	ResourceBundle bundle,bundleAlgebra,bundleAlgebraSteps;
	private String[] VARSYM = {"x","y"};
	List<ProblemPattern> lProbs = new ArrayList<>();
	public Level2Mult(Locale loc){
		this.loc = loc;
		initStringFromLocale();
	}
	public Level2Mult(){
		this.loc = new Locale("in", "ID");
		initStringFromLocale();
	}
	private void initStringFromLocale(){
		bundle = ResourceBundle.getBundle("lang.langbundle", loc);
		bundleAlgebra = ResourceBundle.getBundle("lang.langbundle-algebra", loc);
		bundleAlgebraSteps = ResourceBundle.getBundle("lang.algebra-steps", loc);
	}
	int numOfQuestion = 5;
	int[][] bounds = {
			{2,8},{5,11}
	};

	public void generateProblemPattern() {
		String[] choicePattern = {"b", "-b","a","-a + b"}; 
		ProblemPattern p3 = new ProblemPattern("-aVAR = c", "-b", choicePattern);
		ProblemPattern p4 = new ProblemPattern("-aVAR = -c", "b", choicePattern);
		
		// lProbs.add(p1);
		// lProbs.add(p2);
		lProbs.add(p3);
		lProbs.add(p4);
		
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
		generateProblemPattern();
		List<Quiz> lq = new ArrayList<Quiz>();
		for (int i= 0;i<numOfQuestion;i++){
			MultipleChoiceQuiz q = setQuestion(i);
			lq.add(q);
		}
		
		return lq;
	}
	private MultipleChoiceQuiz setQuestion(int i) {
		int idx; 
		idx = i % lProbs.size(); 
		int a=0,b=0;
		do {
			a = ThreadLocalRandom.current().nextInt(3,10);
			b = ThreadLocalRandom.current().nextInt(3,8);
		} while (a<=b);
		
		MultipleChoiceQuiz q = new MultipleChoiceQuiz();
		ProblemPattern p = lProbs.get(idx); 
		
		setQuestion(a, b, q, p);
		setAnswer(a, b, q, p);
		setChoices(a, b, q, p);
		setQuizSecondaryAttributes(idx, q);
		return q;
	}
	
	private void setChoices(int a, int b, MultipleChoiceQuiz q, ProblemPattern p) {
		// prepare choices
		String[] choicePattern = p.choicePattern;
		for (String string : choicePattern) {
			double choice = runExpression(string, a, b);
			q.addChoice(String.valueOf((int)choice));
		}
	}
	
	private void setAnswer(int a, int b, MultipleChoiceQuiz q, ProblemPattern p) {
		// prepare answer
		String exp = p.expression;			
		int rslt = (int) runExpression(exp, a, b);
		q.setCorrectAnswer(String.valueOf(rslt));
	}
	
	private void setQuestion(int a, int b, MultipleChoiceQuiz q, ProblemPattern p) {
		int c = a * b;
		String var = VARSYM[ThreadLocalRandom.current().nextInt(0, VARSYM.length)];
		String qLine1 = p.question;
		qLine1 = qLine1.replace("a", String.valueOf(a));
		qLine1 = qLine1.replace("b", String.valueOf(b));
		qLine1 = qLine1.replace("c", String.valueOf(c));
		qLine1 = qLine1.replaceAll("VAR", var);
		q.setProblemString(qLine1);
		qLine1 = CommonFunctionAndValues.enclosedWithMathJaxExp(qLine1);
		String qLine2 = CommonFunctionAndValues.enclosedWithMathJaxExp(var + " = ?");
		q.setQuestion(qLine1+" "+qLine2);
		q.setSolutionSteps(Level2MultSolution.getSolutionSteps(a, b, c, var, p,bundleAlgebraSteps));
	}
	
	private void setQuizSecondaryAttributes(int idx, MultipleChoiceQuiz q) {
		q.setDifficultyLevel(QuizLevel.MUDAH);
		q.setLessonSubcategory(bundleAlgebra.getString("algebra.level1.mult"));
		q.setLessonClassifier(bundle.getString("mathelementary"));
		q.setLessonGrade(5);
		q.setSubCategoryOrder(6);
		q.setLocalGeneratorOrder(idx);
		q.setLessonCategory(bundle.getString("algebra"));
		q.setLocale(loc);
	}
	
	@Override
	public List<Quiz> generateQuizList(int numOfQuestion) {
		this.numOfQuestion = numOfQuestion;
		return generateQuizList();
	}
	
		
	private double runExpression(String exp, int a, int b) {
		Expression e = new ExpressionBuilder(exp)
				.variables("a","b")
				.build()
				.setVariable("a", a)
				.setVariable("b", b);
		return e.evaluate();
	}

}
