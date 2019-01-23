package com.alza.quiz.qfactory.algebra;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.concurrent.ThreadLocalRandom;

import com.alza.quiz.model.MultipleChoiceQuiz;
import com.alza.quiz.model.Quiz;
import com.alza.quiz.model.QuizLevel;
import com.alza.quiz.qfactory.IQuestionFactory;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class Level3LinearEquation implements IQuestionFactory{
	// private final static String MJXTAG ="$$"; 
	Locale loc;
	ResourceBundle bundle,bundleAlgebra;
	private String[] VARSYM = {"X","Y"};
	List<ProblemPattern> lProbs = new ArrayList<>();
	public Level3LinearEquation(Locale loc){
		this.loc = loc;
		initStringFromLocale();
	}
	public Level3LinearEquation(){
		this.loc = new Locale("in", "ID");
		initStringFromLocale();
	}
	private void initStringFromLocale(){
		bundle = ResourceBundle.getBundle("lang.langbundle", loc);
		bundleAlgebra = ResourceBundle.getBundle("lang.langbundle-algebra", loc);
		
	}
	int numOfQuestion = 5;
	int[][] bounds = {
			{2,8},{5,11}
	};

	public void generateProblemPattern() {
		String[] choicePattern = {"a + b", "a - b","b - a","-b - a"}; 
		ProblemPattern p1 = new ProblemPattern("a - VAR = b", "a - b", choicePattern);
		ProblemPattern p2 = new ProblemPattern("-a - VAR = b", "-a - b", choicePattern);
		ProblemPattern p3 = new ProblemPattern("a - VAR = -b", "a + b", choicePattern);
		ProblemPattern p4 = new ProblemPattern("-a - VAR = -b", "b - a", choicePattern);
		lProbs.add(p1);
		lProbs.add(p2);
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
			int idx; 
			idx = i % lProbs.size(); 
			int a=0,b=0;
			do {
				a = ThreadLocalRandom.current().nextInt(3,10);
				b = ThreadLocalRandom.current().nextInt(3,8);
			} while (a<=b);
			
			MultipleChoiceQuiz q = new MultipleChoiceQuiz();
			ProblemPattern p = lProbs.get(idx); 
			
			// prepare question
			String question = prepareQuestion(a, b, p); 
			q.setQuestion(question);
			// prepare answer
			String exp = p.expression;			
			int rslt = (int) runExpression(exp, a, b);
			q.setCorrectAnswer(String.valueOf(rslt));
			// prepare choices
			String[] choicePattern = p.choicePattern;
			for (String string : choicePattern) {
				double choice = runExpression(string, a, b);
				q.addChoice(String.valueOf((int)choice));
			}
			setQuizSecondaryAttributes(idx, q);
			lq.add(q);
		}
		
		return lq;
	}
	
	private void setQuizSecondaryAttributes(int idx, MultipleChoiceQuiz q) {
		q.setDifficultyLevel(QuizLevel.MUDAH);
		q.setLessonSubcategory(bundleAlgebra.getString("algebra.level2.addsub"));
		q.setLessonClassifier(bundle.getString("mathelementary"));
		q.setLessonGrade(5);
		q.setSubCategoryOrder(6);
		q.setLocalGeneratorOrder(idx);
		q.setLessonCategory(bundle.getString("algebra"));
		q.setLocale(loc);
	}
	private String prepareQuestion(int a, int b, ProblemPattern p) {
		String var = VARSYM[ThreadLocalRandom.current().nextInt(0, VARSYM.length)];
		String question = p.question;
		question = question.replace("a", String.valueOf(a));
		question = question.replace("b", String.valueOf(b));
		question = question.replaceAll("VAR", var);
		question = question + ",\n" + var + "=?";
		return question;
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
	
	
	private class ProblemPattern {
		String question;
		String expression;
		String[] choicePattern;
		public ProblemPattern(String question, String expression, String[] choicePattern) {
			this.question = question;
			this.expression = expression;
			this.choicePattern = choicePattern;
		}
	}
}
