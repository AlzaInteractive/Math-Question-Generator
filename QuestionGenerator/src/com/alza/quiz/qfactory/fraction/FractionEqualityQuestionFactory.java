package com.alza.quiz.qfactory.fraction;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import com.alza.common.math.Fraction;
import com.alza.quiz.model.MultipleChoiceQuiz;
import com.alza.quiz.model.Quiz;
import com.alza.quiz.model.QuizLevel;
import com.alza.quiz.qfactory.IQuestionFactory;
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
		for (int i=0;i<2;i++){
			MultipleChoiceQuiz q = generateTypeA(randA, randMulti, i);
			quizList.add(q);
		}
		for (int i=0;i<2;i++){
			MultipleChoiceQuiz q = generateTypeB(i);
			quizList.add(q);
		}
		return quizList;
	}
	private MultipleChoiceQuiz generateTypeB(int i){
		int min = 30;
		int max = 45;
		int a,b,c,d,multip;
		boolean nmult2,nmult3,nmult5,nmult7;
		do {
			a = CommonFunctionAndValues.getRandomInt(min, max);
			b = CommonFunctionAndValues.getRandomInt(min, max);
			multip = CommonFunctionAndValues.getRandomInt(2, 6);
			c = a * multip;
			d = b * multip;
			nmult2 = !(b % 2 == 0);
			nmult3 = !(b % 3 == 0);
			nmult5 = !(b % 5 == 0);
			nmult7 = !(b % 7 == 0);
		} while (nmult2&&nmult3&&nmult5&&nmult7);
		MultipleChoiceQuiz q = new MultipleChoiceQuiz();
		q.setDifficultyLevel(QuizLevel.SULIT);
		q.setLessonGrade(5);
		List<String> choices;
		if (i%2==0){
			q.setQuestion("Jika pecahan x/"+b+" = "+c+"/"+d+", maka nilai x sama dengan?");
			q.setCorrectAnswer(String.valueOf(a));
			choices = buildChoices(a,b,c,d,"x");
		} else {
			q.setQuestion("Jika pecahan "+a+"/y"+" = "+c+"/"+d+", maka nilai y sama dengan?");
			q.setCorrectAnswer(String.valueOf(b));
			choices = buildChoices(a,b,c,d,"y");
		}
		q.setChoices(choices);
		q.setLessonClassifier("Matematika SD");
		q.setLessonCategory("Pecahan");
		q.setLessonSubcategory("Kesamaan pecahan, mencari pembilang/penyebut agar dua pecahan menjadi sama");
		q.setSubCategoryOrder(1);
		return q;
	}

	private MultipleChoiceQuiz generateTypeA(int[] randA, int[] randMulti, int i) {
		int a,b,r,multip;
		do {
			a = randA[i];
			r = ThreadLocalRandom.current().nextInt(1, randA.length);
			b = randA[r];
			multip =  randMulti[i];
		} while (a==b||a>b);
		
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
		q.setLessonGrade(4);
		q.setChoices(choices);
		q.setCorrectAnswer(fAnswer.a+"/"+fAnswer.b);
		q.setQuestion("Pecahan yang sama nilainya dengan pecahan "+fQuest.a+"/"+fQuest.b);
		q.setLessonClassifier("Matematika SD");
		q.setLessonCategory("Pecahan");
		q.setLessonSubcategory("Kesamaan pecahan");
		q.setSubCategoryOrder(1);
		return q;
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
	private List<String> buildChoices(int a,int b,int c,int d, String waldo){
		int[] choices = new int[5];
		CommonFunctionAndValues.shuffleArray(simpleMultiplier);
		if (waldo=="x"){
			choices[0] = a;
		} else{
			choices[0] = b;
		}
		choices[1] = c * d /b;
		choices[2] = d * b /c;
		choices[3] = a * b /c;
		choices[4] = a * c /d;
		CommonFunctionAndValues.shuffleArray(choices);
		List<String> choicesInString = new ArrayList<String>();
		for (int s : choices) {
			choicesInString.add(String.valueOf(s));
		}
		return choicesInString;
	}

}
