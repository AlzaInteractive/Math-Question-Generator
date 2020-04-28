package com.alza.quiz.qfactory.integer;

import java.math.BigDecimal;
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

public class Rounding implements IQuestionFactory{
	Locale loc;
	ResourceBundle bundle;
	public Rounding(Locale loc){
		this.loc = loc;
		initStringFromLocale();
	}
	public Rounding(){
		this.loc = new Locale("in", "ID");
		initStringFromLocale();
	}
	private void initStringFromLocale(){
		bundle = ResourceBundle.getBundle("lang.langbundle", loc);
	}
	int numOfQuestion = 2;
	int[][] bounds = {
			{25,100,-1},{100,2500,-2},{2500,10000,-3}
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
			int a=0;
			a = ThreadLocalRandom.current().nextInt(bounds[idx][0], 
						bounds[idx][1]);
			BigDecimal bda = new BigDecimal(a);
			MultipleChoiceQuiz q = new MultipleChoiceQuiz();
			String roundT="";
			switch (bounds[idx][2]) {
			case -1:
				roundT = bundle.getString("integer.nearestten");
				break;
			case -2:
				roundT = bundle.getString("integer.nearesthundred");
				break;
			case -3:
				roundT = bundle.getString("integer.nearestthousand");
				break;
			default:
				break;
			}
			int roundRand = ThreadLocalRandom.current().nextInt(0, 3);
			int roundMode=0;
			String question="";
			switch (roundRand) {
			case 0:
				question = bundle.getString("integer.roundupquest");
				roundMode = BigDecimal.ROUND_CEILING;
				break;
			case 1:
				question = bundle.getString("integer.rounddownquest");
				roundMode = BigDecimal.ROUND_FLOOR;
				break;
			case 2:
				question = bundle.getString("integer.roundnearesthalf");
				roundMode = BigDecimal.ROUND_HALF_UP;
				break;
			default:
				break;
			}
			
			BigDecimal rslt = bda.setScale(bounds[idx][2], roundMode);			
			q.setQuestion(question+" "+roundT+": "+a);
			q.setCorrectAnswer(String.valueOf(rslt.toBigInteger().intValue()));
			for (int j = -1; i >= -3; j-- ) {
				BigDecimal c = bda.setScale(j, roundMode);
				q.addChoice(c.toBigInteger().intValue());
			}
			q.setDifficultyLevel(QuizLevel.MUDAH);
			q.setLessonSubcategory(bundle.getString("integer.rounding"));
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
