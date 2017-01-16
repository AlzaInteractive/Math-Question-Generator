package com.alza.quiz.qfactory.fraction;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

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
			int[] question = {a,b};
			int multip =  randMulti[i];
			int[] answer = {a*multip,b*multip};
			if (i % 2 == 0){
				int[] temp;
				temp = question;
				question=answer;
				answer=temp;
			}
			List<String> choices = buildChoices(question, answer);
			MultipleChoiceQuiz q = new MultipleChoiceQuiz();
			q.setDifficultyLevel(QuizLevel.MUDAH);
			q.setChoices(choices);
			q.setCorrectAnswer(answer[0]+"/"+answer[1]);
			q.setQuestion("Pecahan yang sama dengan nilainya dengan pecahan "+question[0]+"/"+question[1]);
			q.setLessonClassifier("Matematika SD");
			q.setLessonCategory("Pecahan");
			q.setLessonSubcategory("Kesamaan pecahan");
			quizList.add(q);
		}
		return quizList;
	}
	
	private List<String> buildChoices(int[] question, int[] answer){
		int[][] choices = new int[CHOICE_SIZE][2];
		CommonFunctionAndValues.shuffleArray(simpleMultiplier);
		for (int i=0; i<CHOICE_SIZE-1; i++){
			int a = question[0] * simpleMultiplier[i][0];
			int b = question[1] * simpleMultiplier[i][1];
			choices[i][0] = a;
			choices[i][1] = b;
		}
		choices[CHOICE_SIZE-1][0] = answer[0];
		choices[CHOICE_SIZE-1][1] = answer[1];
		CommonFunctionAndValues.shuffleArray(choices);
		List<String> choicesInString = new ArrayList<String>();
		for (int[] is : choices) {
			choicesInString.add(is[0]+"/"+is[1]);
		}
		return choicesInString;
	}

}
