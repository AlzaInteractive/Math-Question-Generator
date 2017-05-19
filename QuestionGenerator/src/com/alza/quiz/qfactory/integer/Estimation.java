package com.alza.quiz.qfactory.integer;

import java.math.BigDecimal;
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

public class Estimation implements IQuestionFactory{
	Locale loc;
	ResourceBundle bundle;
	public Estimation(Locale loc){
		this.loc = loc;
		initStringFromLocale();
	}
	public Estimation(){
		this.loc = new Locale("in", "ID");
		initStringFromLocale();
	}
	private void initStringFromLocale(){
		bundle = ResourceBundle.getBundle("lang.langbundle", loc);
	}
	int numOfQuestion = 2;
	int[][] bounds = {
			{30,100,-1},{100,1000,-2},{1000,9999,-3}
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
			int idx;
			if (i<bounds.length){
				idx = i;
			} else {
				idx = i % bounds.length; 
			}
			//System.out.println("index "+idx);
			int a=0,b=0;
			a = ThreadLocalRandom.current().nextInt(bounds[idx][0], 
						bounds[idx][1]);
			b = ThreadLocalRandom.current().nextInt(bounds[idx][0], 
					bounds[idx][1]);
			BigDecimal bda = new BigDecimal(a);
			BigDecimal bdb = new BigDecimal(b);
			SimpleQuiz q = new SimpleQuiz();
			String roundT="";
			if (a<100){
				roundT = bundle.getString("integer.nearestten");
			} else if (a<1000){
				roundT = bundle.getString("integer.nearesthundred");
			} else {
				roundT = bundle.getString("integer.nearestthousand");
			}
			int roundRand = ThreadLocalRandom.current().nextInt(0, 3);
			int roundMode=0;
			String question="";
			switch (roundRand) {
			case 0:
				question = bundle.getString("integer.highestimate");
				roundMode = BigDecimal.ROUND_CEILING;
				break;
			case 1:
				question = bundle.getString("integer.lowestimate");
				roundMode = BigDecimal.ROUND_FLOOR;
				break;
			case 2:
				question = bundle.getString("integer.bestestimate");
				roundMode = BigDecimal.ROUND_HALF_UP;
				break;
			default:
				break;
			}
			
			BigDecimal estA = bda.setScale(bounds[idx][2], roundMode);
			BigDecimal estB = bdb.setScale(bounds[idx][2], roundMode);
			int rslt= estA.toBigInteger().intValue() * estB.toBigInteger().intValue();
			q.setQuestion(question+" "+roundT+" : "+a+" x "+b);
			q.setCorrectAnswer(String.valueOf(rslt));
			q.setDifficultyLevel(QuizLevel.MUDAH);
			q.setLessonSubcategory(bundle.getString("integer.estimation"));
			q.setLessonClassifier(bundle.getString("mathelementary"));
			q.setLessonGrade(4);
			q.setSubCategoryOrder(5);
			q.setLocalGeneratorOrder(idx);
			q.setLessonCategory(bundle.getString("integer"));
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
