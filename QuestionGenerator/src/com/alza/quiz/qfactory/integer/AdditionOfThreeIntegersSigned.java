package com.alza.quiz.qfactory.integer;

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

public class AdditionOfThreeIntegersSigned implements IQuestionFactory{
	Locale loc;
	ResourceBundle bundle;
	public AdditionOfThreeIntegersSigned(Locale loc){
		this.loc = loc;
		initStringFromLocale();
	}
	public AdditionOfThreeIntegersSigned(){
		this.loc = new Locale("in", "ID");
		initStringFromLocale();
	}
	private void initStringFromLocale(){
		bundle = ResourceBundle.getBundle("lang.langbundle", loc);
	}
	int numOfQuestion = 3;
	int[][] bounds = {
			{0,25},{25,50},{50,100},{100,250}
	};
	boolean[][] signs = {
			{true,true,true},{true,true,false},{true,false,true},
			{true,false,false},{false,false,false}
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
			
			int a=0,b=0,c=0;
			int pos = (i % signs.length) % bounds.length;
			int farBound = bounds[pos][1];
			int nearBound = bounds[pos][0];
			do {
				// assign a,b,c
				a = ThreadLocalRandom.current().nextInt(-farBound, 
						farBound);
				b = ThreadLocalRandom.current().nextInt(-farBound, 
						farBound);
				c = ThreadLocalRandom.current().nextInt(-farBound, 
						farBound);
			} while (a==b||a==c||b==c
					||Math.abs(a)<nearBound
					||Math.abs(b)<nearBound
					||Math.abs(c)<nearBound);
			
			int idx;
			if (i<signs.length){
				idx = i;
			} else {
				idx = i % signs.length; 
			}
			boolean[] sign = signs[idx];
			//prepare question composition
			String question = ""+a;
			int correctAnswer = a;
			String bSigned;
			if (b < 0){
				bSigned = "("+b+")";
			} else bSigned=""+b;
			
			String cSigned;
			if (c < 0){
				cSigned = "("+c+")";
			} else cSigned=""+c;
			
			if (sign[1]){
				question = question +" + "+bSigned;
				correctAnswer = correctAnswer + b;
			} else {
				question = question +" - "+bSigned;
				correctAnswer = correctAnswer - b;
			}
			if (sign[2]){
				question = question +" + "+cSigned;
				correctAnswer = correctAnswer + c;
			} else {
				question = question +" - "+cSigned;
				correctAnswer = correctAnswer - c;
			}
			
			//System.out.println("index "+idx);
			
			MultipleChoiceQuiz q = new MultipleChoiceQuiz();
			q.setChoices(correctAnswer,a+b+c,a-b-c);
			q.setQuestion(question);
			q.setCorrectAnswer(String.valueOf(correctAnswer));
			q.setDifficultyLevel(QuizLevel.MUDAH);
			q.setLessonSubcategory(bundle.getString("integer.addthreenum"));
			q.setLessonClassifier(bundle.getString("mathelementary"));
			q.setLessonGrade(4);
			q.setSubCategoryOrder(3);
			q.setLocalGeneratorOrder(pos);
			q.setLocale(loc);
			q.setLessonCategory(bundle.getString("integer"));
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
