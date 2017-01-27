package com.alza.quiz.qfactory.fraction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import com.alza.common.math.Fraction;
import com.alza.quiz.model.MultipleChoiceQuiz;
import com.alza.quiz.model.Quiz;
import com.alza.quiz.model.QuizLevel;
import com.alza.quiz.qfactory.IQuestionFactory;
import com.alza.quiz.util.CommonFunctionAndValues;

public class FractionDecimalFormQuestionFactory implements IQuestionFactory {

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
		for (int i=0;i<6;i++){
			int a,denom;
			if (i>2){
				do {
					a = CommonFunctionAndValues.getRandomInt(2, 10);
					denom = CommonFunctionAndValues.getRandomInt(2, 11);
				} while (denom>=a || (a%denom==0));
			} else {
				do {
					a = CommonFunctionAndValues.getRandomInt(2, 23);
					denom = CommonFunctionAndValues.getRandomInt(2, 10);
				} while (denom<=a);
			}
			Fraction fQuest = new Fraction(a,denom);
			String correctAnswer = fQuest.getTwoDigitDecimalForm();
			Fraction f1 = fQuest.getResultWhenMultipliedBy(new Fraction(10,1));
			Fraction f2 = fQuest.getResultWhenMultipliedBy(new Fraction(1,10));
			Fraction f3 = fQuest.getResultWhenMultipliedBy(new Fraction(1,5));
			Fraction f4 = fQuest.getResultWhenMultipliedBy(new Fraction(2,1));
			List<Fraction> choices = new ArrayList<Fraction>();
			choices.add(f1);choices.add(f2);choices.add(f3);choices.add(f4);choices.add(fQuest);
			Collections.shuffle(choices);
			MultipleChoiceQuiz q = new MultipleChoiceQuiz();
			q.setDifficultyLevel(QuizLevel.MUDAH);
			q.setChoices(convertChoices(choices));
			q.setCorrectAnswer(correctAnswer);
			q.setQuestion("Bentuk desimal dari pecahan "+fQuest+" adalah");
			q.setLessonClassifier("Matematika SD");
			q.setLessonCategory("Pecahan");
			q.setLessonSubcategory("Bentuk desimal");
			q.setLessonGrade(5);
			quizList.add(q);
		}
		return quizList;
	}
	private List<String> convertChoices(List<Fraction> fracs){
		List<String> choicesInString = new ArrayList<String>();
		for (Fraction f : fracs) {
			choicesInString.add(f.getTwoDigitDecimalForm());
		}
		return choicesInString;
	}

}
