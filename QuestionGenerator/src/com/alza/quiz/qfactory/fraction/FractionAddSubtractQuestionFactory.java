package com.alza.quiz.qfactory.fraction;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import com.alza.common.math.Fraction;
import com.alza.quiz.model.MultipleChoiceQuiz;
import com.alza.quiz.model.Quiz;
import com.alza.quiz.model.QuizLevel;
import com.alza.quiz.qfactory.IQuestionFactory;
import com.alza.quiz.util.CommonFunctionAndValues;

public class FractionAddSubtractQuestionFactory implements IQuestionFactory{
	private static final int NUM_OF_QUESTIONS = 3;
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
		List<Quiz> quizList= new ArrayList<Quiz>();
		for (int i=0; i<NUM_OF_QUESTIONS; i++){
			MultipleChoiceQuiz q = null;
			if (i % 3 == 2){
				q = generateTypeC(i);
				q.setDifficultyLevel(QuizLevel.SULIT);
				q.setLessonSubcategory("Penjumlahan dan pengurangan (penyebut acak)");
				q.setLessonGrade(5);
			} else if (i % 3 == 1){
				q = generateTypeB(i);
				q.setDifficultyLevel(QuizLevel.SEDANG);
				q.setLessonSubcategory("Penjumlahan dan pengurangan (penyebut memiliki fpb>1)");
				q.setLessonGrade(5);
			} else {
				q = generateTypeA(i);
				q.setDifficultyLevel(QuizLevel.MUDAH);
				q.setLessonSubcategory("Penjumlahan dan pengurangan (penyebut sama)");
				q.setLessonGrade(4);
			}
			q.setLessonClassifier("Matematika SD");
			q.setLessonCategory("Pecahan");
			quizList.add(q);
		}
		return quizList;
	}

	private MultipleChoiceQuiz generateTypeA(int i) {
		MultipleChoiceQuiz q = new MultipleChoiceQuiz();
		int denom = CommonFunctionAndValues.getRandomInt(11, 41);
		int a1,a2;
		do {
			a1 = CommonFunctionAndValues.getRandomInt(5, 17);
			a2 = CommonFunctionAndValues.getRandomInt(5, 17);
		} while (!(denom > a1 && denom > a2 && (a1+a2)<=denom && a1>a2));
		Fraction f1 = new Fraction(a1, denom);
		Fraction f2 = new Fraction(a2, denom);
		Fraction result;
		if (i % 2 == 0){
			result = f1.getResultWhenAddedWith(f2);
			q.setQuestion("Hitung hasil penjumlahan dari pecahan "
					+f1.toString()+" dan pecahan "+f2.toString());
		} else{
			result = f1.getResultWhenSubtractWith(f2);
			q.setQuestion("Hitung hasil dari pecahan "
					+f1.toString()+" dikurangi pecahan "+f2.toString());
		}
		q.setCorrectAnswer(result.toString());
		q.setChoices(buildChoices(f1,f2,result));
		return q;
	}
	private MultipleChoiceQuiz generateTypeB(int i) {
		MultipleChoiceQuiz q = new MultipleChoiceQuiz();
		int denom = CommonFunctionAndValues.getRandomInt(11, 41);
		int a1,a2;
		do {
			a1 = CommonFunctionAndValues.getRandomInt(5, 17);
			a2 = CommonFunctionAndValues.getRandomInt(5, 17);
		} while (!(denom > a1 && denom > a2 && (a1+a2)<=denom && a1>a2));
		Fraction f1 = new Fraction(a1*2, denom*2);
		Fraction f2 = new Fraction(a2*3, denom*3);
		Fraction result;
		if (i % 2 == 0){
			result = f1.getResultWhenAddedWith(f2);
			q.setQuestion("Hitung hasil penjumlahan dari pecahan "
					+f1.toString()+" dan pecahan "+f2.toString());
		} else{
			result = f1.getResultWhenSubtractWith(f2);
			q.setQuestion("Hitung hasil dari pecahan "
					+f1.toString()+" dikurangi pecahan "+f2.toString());
		}
		q.setCorrectAnswer(result.toString());
		q.setChoices(buildChoices(f1,f2,result));
		return q;
	}
	private MultipleChoiceQuiz generateTypeC(int i) {
		MultipleChoiceQuiz q = new MultipleChoiceQuiz();
		int denomLeft,denomRight;
		int a1,a2;
		do {
			denomLeft = CommonFunctionAndValues.getPrime101stRandom();
			denomRight = CommonFunctionAndValues.getRandomInt(5, 34);;
			a1 = CommonFunctionAndValues.getRandomInt(5, 17);
			a2 = CommonFunctionAndValues.getRandomInt(5, 17);
		} while (denomLeft==denomRight||denomLeft<a1||denomRight<a2);

		Fraction f1 = new Fraction(a1, denomLeft);
		Fraction f2 = new Fraction(a2, denomRight);
		Fraction result;
		if (i % 2 == 0){
			result = f1.getResultWhenAddedWith(f2);
			q.setQuestion("Hitung hasil penjumlahan dari pecahan "
					+f1.toString()+" dan pecahan "+f2.toString());
		} else {
			result = f1.getResultWhenSubtractWith(f2);
			q.setQuestion("Hitung hasil dari pecahan "
					+f1.toString()+" dikurangi pecahan "+f2.toString());
		}
		q.setCorrectAnswer(result.toString());
		q.setChoices(buildChoices(f1,f2,result));
		return q;
	}
	private Set<String> buildChoices(Fraction f1,Fraction f2,Fraction result){
		Fraction[] choices = new Fraction[6];
		choices[0] = result;
		boolean isAddition=false;
		isAddition = (result.equals(f1.getResultWhenAddedWith(f2)));
		if (isAddition){
			choices[1] = result.inverse();
			choices[2] = f1.getResultWhenAddedWith(f2.inverse());
			choices[3] = f2.getResultWhenAddedWith(f1.inverse());
			choices[4] = f1.inverse().getResultWhenAddedWith(f2.inverse());
			choices[5] = new Fraction(f1.a + f2.a, f1.b + f2.b);
		} else {
			choices[1] = result.inverse();
			choices[2] = f1.getResultWhenAddedWith(f2.inverse());
			choices[3] = f2.getResultWhenAddedWith(f1.inverse());
			choices[4] = f1.inverse().getResultWhenAddedWith(f2.inverse());
			choices[5] = new Fraction(f1.a - f2.a, f1.b - f2.b);
		}
		Set<String> choicesInString = new HashSet<String>();
		for (Fraction f : choices) {
			choicesInString.add(f.toString());
		}
		return choicesInString;
	}

}
