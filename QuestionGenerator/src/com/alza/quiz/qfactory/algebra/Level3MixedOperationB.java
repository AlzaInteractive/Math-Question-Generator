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

public class Level3MixedOperationB implements IQuestionFactory{
	// private final static String MJXTAG ="$$"; 
	Locale loc;
	ResourceBundle bundle,bundleAlgebra;
	private String[] VARSYM = {"X","Y"};
	List<ProblemPattern> lProbs = new ArrayList<>();
	public Level3MixedOperationB(Locale loc){
		this.loc = loc;
		initStringFromLocale();
	}
	public Level3MixedOperationB(){
		this.loc = new Locale("in", "ID");
		initStringFromLocale();
	}
	private void initStringFromLocale(){
		bundle = ResourceBundle.getBundle("lang.langbundle", loc);
		bundleAlgebra = ResourceBundle.getBundle("lang.langbundle-algebra", loc);
		
	}
	private int numOfQuestion = 5;

	public void generateProblemPattern() {
		String[] choicePattern = {"(cd + b)/a", "(cd + b)/-a","(cd - b)/a","(cd - b)/-a"}; 
		ProblemPattern p1 = new ProblemPattern("(aVAR - b) ÷ c = d", "(cd + b)/a", choicePattern);
		ProblemPattern p2 = new ProblemPattern("(aVAR + b) ÷ c = d", "(cd - b)/a", choicePattern);
		ProblemPattern p3 = new ProblemPattern("(b - (aVAR)) ÷ c = d", "(cd - b)/-a", choicePattern);
		ProblemPattern p4 = new ProblemPattern("(-aVAR - b) ÷ c = d", "(cd + b)/-a", choicePattern);
		ProblemPattern p5 = new ProblemPattern("(aVAR - b) ÷ -c = d", "(-cd + b)/a", choicePattern);
		ProblemPattern p6 = new ProblemPattern("(aVAR + b) ÷ c = -d", "(-cd - b)/a", choicePattern);
		ProblemPattern p7 = new ProblemPattern("(b - (aVAR)) ÷ -c = d", "(cd + b)/a", choicePattern);
		ProblemPattern p8 = new ProblemPattern("(-aVAR - b) ÷ c = -d", "(-cd + b)/-a", choicePattern);
		lProbs.add(p1);
		lProbs.add(p2);
		lProbs.add(p3);
		lProbs.add(p4);
		lProbs.add(p5);
		lProbs.add(p6);
		lProbs.add(p7);
		lProbs.add(p8);
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
			ProblemPattern p = lProbs.get(idx);
			int a=0,b=0,c=0,d=0;
			boolean loop;
			do {
				a = ThreadLocalRandom.current().nextInt(2,17);
				b = ThreadLocalRandom.current().nextInt(2,5);
				c = ThreadLocalRandom.current().nextInt(2,9);
				d = ThreadLocalRandom.current().nextInt(2,9);
				boolean cond1 = (c*d)%a==0;
				boolean cond2 = b % a == 0;
				boolean cond3 = c != d && b > a;
				loop = !(cond1 && cond2 && cond3); 
			} while (loop);
			
			MultipleChoiceQuiz q = new MultipleChoiceQuiz();

			// prepare question
			String question = prepareQuestion(a, b, c, d, p); 
			q.setQuestion(question);
			// prepare answer
			String exp = p.expression;			
			int rslt = (int) runExpression(exp, a, b, c, d);
			q.setCorrectAnswer(String.valueOf(rslt));
			// prepare choices
			String[] choicePattern = p.choicePattern;
			for (String string : choicePattern) {
				double choice = runExpression(string, a, b, c, d);
				q.addChoice(String.valueOf((int)choice));
			}
			setQuizSecondaryAttributes(idx, q);
			lq.add(q);
		}
		
		return lq;
	}
	
	private void setQuizSecondaryAttributes(int idx, MultipleChoiceQuiz q) {
		q.setDifficultyLevel(QuizLevel.MUDAH);
		q.setLessonSubcategory(bundleAlgebra.getString("algebra.level3.mixop"));
		q.setLessonClassifier(bundle.getString("mathelementary"));
		q.setLessonGrade(5);
		q.setSubCategoryOrder(6);
		q.setLocalGeneratorOrder(idx);
		q.setLessonCategory(bundle.getString("algebra"));
		q.setLocale(loc);
	}
	
	private String prepareQuestion(int a, int b, int c, int d, ProblemPattern p) {
		String var = VARSYM[ThreadLocalRandom.current().nextInt(0, VARSYM.length)];
		String question = p.question;
		question = question.replace("a", String.valueOf(a));
		question = question.replace("b", String.valueOf(b));
		question = question.replace("c", String.valueOf(c));
		question = question.replace("d", String.valueOf(d));
		question = question.replaceAll("VAR", var);
		question = question + "\n" + var + " = ?";
		return question;
	}

	@Override
	public List<Quiz> generateQuizList(int numOfQuestion) {
		this.numOfQuestion = numOfQuestion;
		return generateQuizList();
	}
		
	private double runExpression(String exp, int a, int b, int c, int d) {
		Expression e = new ExpressionBuilder(exp)
				.variables("a","b","c","d")
				.build()
				.setVariable("a", a)
				.setVariable("b", b)
				.setVariable("c", c)
				.setVariable("d", d)
				;
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
