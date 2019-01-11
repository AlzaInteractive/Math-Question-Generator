package com.alza.quiz.qfactory.algebra;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.concurrent.ThreadLocalRandom;

import com.alza.quiz.model.Quiz;
import com.alza.quiz.model.QuizLevel;
import com.alza.quiz.model.SimpleQuiz;
import com.alza.quiz.qfactory.IQuestionFactory;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class SimpleMultDiv implements IQuestionFactory{
	// private final static String MJXTAG ="$$"; 
	Locale loc;
	ResourceBundle bundle;
	private String[] VARSYM = {"x","y","z","o","p","q"};
	public SimpleMultDiv(Locale loc){
		this.loc = loc;
		initStringFromLocale();
	}
	public SimpleMultDiv(){
		this.loc = new Locale("in", "ID");
		initStringFromLocale();
	}
	private void initStringFromLocale(){
		bundle = ResourceBundle.getBundle("lang.langbundle", loc);
	}
	int numOfQuestion = 5;
	int[][] bounds = {
			{2,8},{5,11}
	};
	String[][] expression = {
			{"var = a + b","a + b"},
			{"var = a - b","a - b"},
			{"var - a = b","a + b"}, 
			{"var + a = b","b - a"},
			{"a - var = b","a - b"},
			{"-a - var = b","-a -b"}
			// 1st for q, 2nd for builder, 3rd for 
	};
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
		for (int i= 0;i<numOfQuestion;i++){
			int idx; // index ekspresi yang akan digunakan sebagai soal, jika i > jumlah ekspresi, ambil selisihnya
			if (i<bounds.length){
				idx = i;
			} else {
				idx = i % bounds.length; 
			}
			String exp = expression[idx][0]; 
			//System.out.println("index "+idx);
			int a=0,b=0;
			do {
				a = ThreadLocalRandom.current().nextInt(bounds[idx][0], 
						bounds[idx][1]);
				b = ThreadLocalRandom.current().nextInt(bounds[idx][0], 
						bounds[idx][1]);
				
			} while (a<=b);
			SimpleQuiz q = new SimpleQuiz();
			
			Expression e = new ExpressionBuilder(exp)
				.variables("a","b")
				.build()
				.setVariable("a", a)
				.setVariable("b", b);
			
			int rslt = (int) e.evaluate();
			
			String var = VARSYM[ThreadLocalRandom.current().nextInt(0, VARSYM.length)];
			String question = expression[idx][1];
			question = question.replace("a", String.valueOf(a));
			question = question.replace("b", String.valueOf(b));
			question.replaceAll("var", var);
			
			question = question + ",\n " + var + "=?"; 
			
			q.setQuestion(question);
			q.setCorrectAnswer(String.valueOf(rslt));
			q.setDifficultyLevel(QuizLevel.MUDAH);
			q.setLessonSubcategory(bundle.getString("algebra.addsub"));
			q.setLessonClassifier(bundle.getString("mathelementary"));
			q.setLessonGrade(5);
			q.setSubCategoryOrder(6);
			q.setLocalGeneratorOrder(idx);
			q.setLessonCategory(bundle.getString("algebra"));
			q.setLocale(loc);
			lq.add(q);
		}
		
		return lq;
	}

	@Override
	public List<Quiz> generateQuizList(int numOfQuestion) {
		this.numOfQuestion = numOfQuestion;
		return generateQuizList();
	}
}
