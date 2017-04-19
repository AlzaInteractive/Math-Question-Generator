package com.alza.quiz.qfactory.fraction;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import com.alza.common.math.Fraction;
import com.alza.common.math.Fraction.MixedFraction;
import com.alza.quiz.model.MultipleChoiceQuiz;
import com.alza.quiz.model.Quiz;
import com.alza.quiz.model.QuizLevel;
import com.alza.quiz.qfactory.IQuestionFactory;
import com.alza.quiz.util.CommonFunctionAndValues;

public class MixedFractionQuestionFactory implements IQuestionFactory{
	private static int NUM_OF_QUESTIONS = 6;
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
			if (i>3){
				q = generateTypeMultiDivide(i);
				q.setDifficultyLevel(QuizLevel.SULIT);
			}
			else if (i>1){
				q = generateTypeAddSubtract(i);
				q.setDifficultyLevel(QuizLevel.SULIT);
			} else {
				q = generateTypeMixedForm(i);
				q.setDifficultyLevel(QuizLevel.SEDANG);
			}
			q.setLessonClassifier("Matematika SD");
			q.setLessonCategory("Pecahan");
			q.setLessonGrade(5);
			q.setSubCategoryOrder(2);
			quizList.add(q);
		}
		return quizList;
	}

	private MultipleChoiceQuiz generateTypeMultiDivide(int i) {
		MultipleChoiceQuiz q = new MultipleChoiceQuiz();
		int denom = CommonFunctionAndValues.getRandomInt(6, 13);
		int a1,a2;
		do {
			a1 = CommonFunctionAndValues.getRandomInt(5, 23);
			a2 = CommonFunctionAndValues.getRandomInt(5, 23);
		} while (denom > a1 || denom > a2 );
		Fraction f1 = new Fraction(a1, denom);
		Fraction f2 = new Fraction(a2, denom);
		Fraction result;
		if (i % 2 == 0){
			result = f1.getResultWhenMultipliedBy(f2);
			q.setQuestion("Hitung hasil perkalian dari pecahan "
					+f1.getMixedFraction()+" dan pecahan "+f2.getMixedFraction());
		} else{
			result = f1.getResultWhenDividedBy(f2);
			q.setQuestion("Hitung hasil dari pecahan "
					+f1.toString()+" dibagi pecahan "+f2.toString());
		}
		q.setCorrectAnswer(result.getMixedFraction().toString());
		q.setChoices(buildChoices(result));
		q.setLessonSubcategory("Pecahan campuran - perkalian dan pembagian");
		return q;
	}
	private MultipleChoiceQuiz generateTypeAddSubtract(int i) {
		MultipleChoiceQuiz q = new MultipleChoiceQuiz();
		int denom = CommonFunctionAndValues.getRandomInt(6, 13);
		int a1,a2;
		do {
			a1 = CommonFunctionAndValues.getRandomInt(5, 23);
			a2 = CommonFunctionAndValues.getRandomInt(5, 23);
		} while (denom > a1 || denom > a2 ||a1==a2||((a1+a2)%denom==0));
		Fraction f1 = new Fraction(a1, denom);
		Fraction f2 = new Fraction(a2, denom);
		Fraction result;
		if (i % 2 == 0){
			result = f1.getResultWhenAddedWith(f2);
			q.setQuestion("Hitung hasil penjumlahan dari pecahan "
					+f1.getMixedFraction()+" dan pecahan "+f2.getMixedFraction());
		} else{
			result = f1.getResultWhenSubtractWith(f2);
			q.setQuestion("Hitung hasil dari pecahan "
					+f1.getMixedFraction()+" dikurangi pecahan "+f2.getMixedFraction());
		}
		q.setCorrectAnswer(result.getMixedFraction().toString());
		q.setChoices(buildChoices(result));
		q.setLessonSubcategory("Pecahan campuran - penjumlahan dan pengurangan");
		return q;
	}
	private MultipleChoiceQuiz generateTypeMixedForm(int i) {
		MultipleChoiceQuiz q = new MultipleChoiceQuiz();
		int denomLeft;
		int a1;
		do {
			denomLeft = CommonFunctionAndValues.getRandomInt(5, 13);;			
			a1 = CommonFunctionAndValues.getRandomInt(5, 23);
		} while (denomLeft>=a1 || a1%denomLeft==0);
		Fraction f1 = new Fraction(a1, denomLeft);
		MixedFraction result;
			q.setQuestion("Bentuk pecahan campuran dari "
					+f1.toString()+" adalah? ");
		result = f1.getMixedFraction();
		q.setCorrectAnswer(result.toString());
		q.setChoices(buildChoices(f1));
		q.setLessonSubcategory("Pecahan campuran - bentuk");
		return q;
	}
	private Set<String> buildChoices(Fraction f1){
		MixedFraction m = f1.getMixedFraction();
		MixedFraction[] choices = new MixedFraction[6];
		choices[0] = m;
		choices[1] = f1.new MixedFraction(m.a, m.b, m.x);
		choices[2] = f1.new MixedFraction(m.a, m.x, m.b);
		choices[3] = f1.new MixedFraction(m.x, m.b, m.a);
		choices[4] = f1.new MixedFraction(m.b, m.a, m.x);
		choices[5] = f1.new MixedFraction(m.b, m.x, m.a);
		Set<String> choicesInString = new HashSet<String>();
		for (MixedFraction f : choices) {
			choicesInString.add(f.toString());
		}
		return choicesInString;
	}
	@Override
	public List<Quiz> generateQuizList(int numOfQuestion) {
		NUM_OF_QUESTIONS = numOfQuestion;
		return generateQuizList();
	}
}
