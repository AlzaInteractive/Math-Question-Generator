package com.alza.quiz.qfactory.fraction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import com.alza.common.math.Fraction;
import com.alza.quiz.model.MultipleChoiceQuiz;
import com.alza.quiz.model.Quiz;
import com.alza.quiz.model.QuizLevel;
import com.alza.quiz.qfactory.IQuestionFactory;
import com.alza.quiz.util.CommonFunctionAndValues;

public class SimplifyFractionQuestionFactory implements IQuestionFactory {

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
		for (int i=0;i<2;i++){
			int multi = ThreadLocalRandom.current().nextInt(2, 7);
			int a,b;
			do {
				a = ThreadLocalRandom.current().nextInt(2, 13);
				b = ThreadLocalRandom.current().nextInt(3, 17);
			} while (a>=b);
			
			int c = a * multi;
			int d = b * multi;
			Fraction fQuest = new Fraction(c,d);
			Fraction fAns = fQuest.getSimplestForm();
			List<Fraction> choices = new ArrayList<Fraction>();
			choices.add(fAns);
			choices.add(new Fraction(c/2, d/2));
			choices.add(new Fraction(c/3, d/3));
			if (c>10 && d>10){
				choices.add(new Fraction(c/4, d/4));
				choices.add(new Fraction(c/5, d/5));
			}
			Collections.shuffle(choices);
			MultipleChoiceQuiz q = new MultipleChoiceQuiz();
			q.setDifficultyLevel(QuizLevel.MUDAH);
			q.setChoices(convertChoices(choices));
			q.setCorrectAnswer(fAns.a+"/"+fAns.b);
			q.setQuestion("Pilihlah bentuk paling sederhana dari pecahan "+c+"/"+d);
			q.setLessonClassifier("Matematika SD");
			q.setLessonCategory("Pecahan");
			q.setLessonSubcategory("Menyederhanakan pecahan");
			q.setLessonGrade(4);
			q.setSubCategoryOrder(3);
			quizList.add(q);
		}
		return quizList;
	}
	private List<String> convertChoices(List<Fraction> fracs){
		List<String> choicesInString = new ArrayList<String>();
		for (Fraction f : fracs) {
			choicesInString.add(f.toString());
		}
		return choicesInString;
	}

}
