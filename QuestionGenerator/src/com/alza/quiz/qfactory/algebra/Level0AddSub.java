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

public class Level0AddSub implements IQuestionFactory{
	// private final static String MJXTAG ="$$"; 
	Locale loc;
	ResourceBundle bundle,bundleAlgebra;
	private String[] VARSYM = {"x","y"};
	List<ProblemPattern> lProbs = new ArrayList<>();
	public Level0AddSub(Locale loc){
		this.loc = loc;
		initStringFromLocale();
	}
	public Level0AddSub(){
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
		ProblemPattern p1 = new ProblemPattern("VAR = a + b", "a + b", choicePattern);
		ProblemPattern p2 = new ProblemPattern("VAR = a - b", "a - b", choicePattern);
		ProblemPattern p3 = new ProblemPattern("VAR = -a + b", "b - a", choicePattern);
		ProblemPattern p4 = new ProblemPattern("VAR = -a - b", "-a - b", choicePattern);
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
			createSingleQuiz(lq, i);
		}		
		return lq;
	}
	
	private void createSingleQuiz(List<Quiz> lq, int i) {
		int idx; 
		idx = i % lProbs.size(); 
		int a=0,b=0;
		do {
			a = ThreadLocalRandom.current().nextInt(3,10);
			b = ThreadLocalRandom.current().nextInt(3,8);
		} while (a<=b);
		
		MultipleChoiceQuiz q = new MultipleChoiceQuiz();
		ProblemPattern p = lProbs.get(idx); 			
		setQuizPrimaryAttribute(a, b, p, q); 						
		setAnswer(a, b, q, p);
		setChoices(a, b, q, p);
		setQuizSecondaryAttributes(idx, q);
		lq.add(q);
	}
	
	private void setAnswer(int a, int b, MultipleChoiceQuiz q, ProblemPattern p) {
		String exp = p.expression;			
		int rslt = (int) runExpression(exp, a, b);
		q.setCorrectAnswer(String.valueOf(rslt));
	}
	
	private void setChoices(int a, int b, MultipleChoiceQuiz q, ProblemPattern p) {
		String[] choicePattern = p.choicePattern;
		for (String string : choicePattern) {
			double choice = runExpression(string, a, b);
			q.addChoice(String.valueOf((int)choice));
		}
	}
	
	private void setQuizSecondaryAttributes(int idx, MultipleChoiceQuiz q) {
		q.setDifficultyLevel(QuizLevel.MUDAH);
		q.setLessonSubcategory(bundleAlgebra.getString("algebra.level0.addsub"));
		q.setLessonClassifier(bundle.getString("mathelementary"));
		q.setLessonGrade(5);
		q.setSubCategoryOrder(6);
		q.setLocalGeneratorOrder(idx);
		q.setLessonCategory(bundle.getString("algebra"));
		q.setLocale(loc);
	}
	
	private void setQuizPrimaryAttribute(int a,int b, ProblemPattern p, Quiz q) {
		String var = VARSYM[ThreadLocalRandom.current().nextInt(0, VARSYM.length)];
		String qLine1 = p.question;
		qLine1 = qLine1.replace("a", String.valueOf(a));
		qLine1 = qLine1.replace("b", String.valueOf(b));
		qLine1 = qLine1.replaceAll("VAR", var);
		q.setProblemString(qLine1);
		qLine1 = CommonFunctionAndValues.enclosedWithMathJaxExp(qLine1);
		String qLine2 = CommonFunctionAndValues.enclosedWithMathJaxExp(var + " = ?");
		q.setQuestion(qLine1+" "+qLine2);
		
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
