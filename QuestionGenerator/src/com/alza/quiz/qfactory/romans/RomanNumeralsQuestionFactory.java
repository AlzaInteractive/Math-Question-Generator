package com.alza.quiz.qfactory.romans;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import com.alza.common.math.MathUtils;
import com.alza.quiz.model.MultipleChoiceQuiz;
import com.alza.quiz.model.Quiz;
import com.alza.quiz.model.QuizLevel;
import com.alza.quiz.qfactory.IQuestionFactory;
import com.alza.quiz.util.CommonFunctionAndValues;

public class RomanNumeralsQuestionFactory implements IQuestionFactory{
	private int[][] bounds = {
			{1,5},{5,10},{10,15},{15,20},
			{20,40},{40,50},{50,90},{90,100},
			{100,400},{400,500},{500,900},{900,1000},
			{1000,1400},{1400,1500},{1500,1900},
			{1900,2000},{2000,3000}
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
		List<Quiz> quizList = new ArrayList<Quiz>();
		for (int[] bound : bounds){
			for (int i=0;i<2;i++){
				MultipleChoiceQuiz q = new MultipleChoiceQuiz();
				int num = CommonFunctionAndValues.getRandomInt(bound[0], bound[1]);
				String romans = MathUtils.toRomanNumeral(num);
				if (i % 2 == 0){// to romans
					q.setQuestion("Lambang Romawi dari angka "+num+" adalah?");
					q.setCorrectAnswer(romans);
					q.setLessonSubcategory("Konversi ke bilangan Romawi antara "+bound[0]+" s.d "+bound[1]);
					q.setChoices(populateChoices(bound, num, true));
				} else {
					q.setQuestion("Nilai dari bilangan Romawi "+romans+" adalah?");
					q.setCorrectAnswer(String.valueOf(num));
					q.setLessonSubcategory("Konversi dari bilangan Romawi bernilai antara "+bound[0]+" s.d "+bound[1]);
					q.setChoices(populateChoices(bound, num, false));
				}
				q.setDifficultyLevel(QuizLevel.MUDAH);
				q.setLessonClassifier("Matematika SD");
				q.setLessonGrade(4);
				q.setLessonCategory("Bilangan Romawi");
				quizList.add(q);
			}
		}
		return quizList;
	}
	
	private Set<String> populateChoices(int[] bound,int num, boolean toRomans){
		List<String> string = new ArrayList<String>();
		for (int i=0;i<10;i++){
			int c = CommonFunctionAndValues.getRandomInt(bound[0], bound[1]);
			if (toRomans){
				string.add(MathUtils.toRomanNumeral(c));
			} else {
				string.add(String.valueOf(c));
			}
		}
		Collections.shuffle(string);
		Set<String> choices = new HashSet<String>();
		for (int j=0;j<4;j++){
			choices.add(string.get(j));
		}
		if (toRomans){
			choices.add(MathUtils.toRomanNumeral(num));
		} else {
			choices.add(String.valueOf(num));
		}
		return choices;
	}

}
