package com.alza.quiz.qfactory.integer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import com.alza.quiz.model.Quiz;
import com.alza.quiz.model.QuizLevel;
import com.alza.quiz.model.SimpleQuiz;
import com.alza.quiz.qfactory.IQuestionFactory;

public class AdditionBetweenTwoNumbers implements IQuestionFactory{
	int numOfQuestion = 4;
	int[][] bounds = {
			{10,50},{50,100},{100,1000},{1000,5000}
	};
	@Override
	public Quiz generateQuiz() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Quiz generateQuiz(QuizLevel quizLevel) {
		// TODO Auto-generated method stub
		return null;
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
			int a=0,b=0;
			do {
				a = ThreadLocalRandom.current().nextInt(bounds[idx][0], 
						bounds[idx][1]);
				b = ThreadLocalRandom.current().nextInt(bounds[idx][0], 
						bounds[idx][1]);
			} while (a+b<bounds[idx][0]||a+b>bounds[idx][0]||a==b);
			SimpleQuiz q = new SimpleQuiz();
			int rslt = a+b;
			q.setQuestion("");
			q.setCorrectAnswer(String.valueOf(rslt));
			lq.add(q);
		}
		
		return null;
	}

	@Override
	public List<Quiz> generateQuizList(int numOfQuestion) {
		// TODO Auto-generated method stub
		return null;
	}

}
