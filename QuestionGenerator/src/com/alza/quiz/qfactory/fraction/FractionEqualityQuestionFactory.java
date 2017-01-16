package com.alza.quiz.qfactory.fraction;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import com.alza.common.math.Fraction;
import com.alza.quiz.model.MultipleChoiceQuiz;
import com.alza.quiz.model.Quiz;
import com.alza.quiz.model.QuizLevel;
import com.alza.quiz.qfactory.kpk.IQuestionFactory;
import com.alza.quiz.util.CommonFunctionAndValues;

public class FractionEqualityQuestionFactory implements IQuestionFactory{
	
	
	private int[][] simpleMultiplier = {{2,3},{3,5},{4,7},{5,8},{3,7}};
	private static final int CHOICE_SIZE=5;
	
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
		int[] randA = CommonFunctionAndValues.simpleInt;
		CommonFunctionAndValues.shuffleArray(randA);
		int[] randMulti = CommonFunctionAndValues.simpleInt;
		CommonFunctionAndValues.shuffleArray(randMulti);
		List<Quiz> quizList = new ArrayList<Quiz>();
		for (int i=0;i<5;i++){
			int a = randA[i];
			int r = ThreadLocalRandom.current().nextInt(1, randA.length);
			int b = randA[r];
			int multip =  randMulti[i];
			Fraction fQuest,fAnswer;
			fQuest = new Fraction(a, b);
			fAnswer =  new Fraction(a*multip,b*multip);
			if (i % 2 == 0){
				Fraction temp;
				temp = fQuest;
				fQuest=fAnswer;
				fAnswer=temp;
			}
			List<String> choices = buildChoices(fQuest, fAnswer);
			MultipleChoiceQuiz q = new MultipleChoiceQuiz();
			q.setDifficultyLevel(QuizLevel.MUDAH);
			q.setChoices(choices);
			q.setCorrectAnswer(fAnswer.a+"/"+fAnswer.b);
			q.setQuestion("Pecahan yang sama dengan nilainya dengan pecahan "+fQuest.a+"/"+fQuest.b);
			q.setLessonClassifier("Matematika SD");
			q.setLessonCategory("Pecahan");
			q.setLessonSubcategory("Kesamaan pecahan");
			quizList.add(q);
		}
		return quizList;
	}
	
	private List<String> buildChoices(Fraction question, Fraction answer){
		Fraction[] choices = new Fraction[CHOICE_SIZE];
		CommonFunctionAndValues.shuffleArray(simpleMultiplier);
		for (int i=0; i<CHOICE_SIZE-1; i++){
			int a = question.a * simpleMultiplier[i][0];
			int b = question.b * simpleMultiplier[i][1];
			Fraction f = new Fraction(a, b);
			choices[i]= f;
		}
		choices[CHOICE_SIZE-1] = new Fraction(answer.a, answer.b);
		CommonFunctionAndValues.shuffleArray(choices);
		List<String> choicesInString = new ArrayList<String>();
		for (Fraction f : choices) {
			choicesInString.add(f.a+"/"+f.b);
		}
		return choicesInString;
	}

}
