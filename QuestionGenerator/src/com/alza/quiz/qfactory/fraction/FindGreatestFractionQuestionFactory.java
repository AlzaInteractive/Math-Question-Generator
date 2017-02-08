package com.alza.quiz.qfactory.fraction;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import com.alza.common.math.Fraction;
import com.alza.common.math.MathUtils;
import com.alza.quiz.model.MultipleChoiceQuiz;
import com.alza.quiz.model.Quiz;
import com.alza.quiz.model.QuizLevel;
import com.alza.quiz.qfactory.IQuestionFactory;
import com.alza.quiz.util.CommonFunctionAndValues;

public class FindGreatestFractionQuestionFactory implements IQuestionFactory {
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
		List<Quiz> quizList = new ArrayList<Quiz>();
		for (int i=0;i<NUM_OF_QUESTIONS;i++){
			MultipleChoiceQuiz q = new MultipleChoiceQuiz();
			if (i % 3 == 2){
				q = generateTypeC();
				q.setLessonSubcategory("Membandingkan nilai pecahan (penyebut acak)");
				q.setQuizLevel(QuizLevel.SEDANG);
				q.setLessonGrade(4);
			} else if (i % 3 == 1 ){
				q = generateTypeB();
				q.setLessonSubcategory("Membandingkan nilai pecahan (penyebut memiliki fpb >1)");
				q.setQuizLevel(QuizLevel.SEDANG);
				q.setLessonGrade(4);
			} else {
				q = generateTypeA();
				q.setLessonSubcategory("Membandingkan nilai pecahan (penyebut sama)");
				q.setQuizLevel(QuizLevel.MUDAH);
				q.setLessonGrade(4);
			}
			q.setQuestion("Pilihlah pecahan dengan nilai paling besar.");
			q.setLessonClassifier("Matematika SD");
			q.setLessonCategory("Pecahan");
			quizList.add(q);
		}
		return quizList;
	}
	public MultipleChoiceQuiz generateTypeC(){
		int choiceSize=2;
		Fraction[] fracs = new Fraction[choiceSize];
		for (int i=0; i<choiceSize ; i++){
			int a = ThreadLocalRandom.current().nextInt(5, 21);
			int b = ThreadLocalRandom.current().nextInt(7, 25);
			while (b<=a){
				b = ThreadLocalRandom.current().nextInt(7, 25);
			}
			Fraction f = new Fraction(a, b);
			fracs[i] = f;
		}
		CommonFunctionAndValues.shuffleArray(fracs);
		Fraction fAnswer = MathUtils.findGreatest(fracs);
		MultipleChoiceQuiz q = new MultipleChoiceQuiz();
		q.setChoices(convertChoices(fracs));
		q.setCorrectAnswer(fAnswer.a+"/"+fAnswer.b);
		return q;
	}
	public MultipleChoiceQuiz generateTypeB(){
		int choiceSize=3;
		Fraction[] fracs = new Fraction[choiceSize];
		long denom = ThreadLocalRandom.current().nextInt(4, 12);
		
		for (int i=0; i<choiceSize ; i++){
			int multip = ThreadLocalRandom.current().nextInt(2, 6);
			int a = ThreadLocalRandom.current().nextInt(5, 25);
			int b = (int) denom * multip;
			while (b<=a){
				a = ThreadLocalRandom.current().nextInt(5, 17);
			}
			Fraction f = new Fraction(a, b);
			fracs[i] = f;
		}
		CommonFunctionAndValues.shuffleArray(fracs);
		Fraction fAnswer = MathUtils.findGreatest(fracs);
		MultipleChoiceQuiz q = new MultipleChoiceQuiz();
		q.setChoices(convertChoices(fracs));
		q.setCorrectAnswer(fAnswer.a+"/"+fAnswer.b);
		return q;
	}
	public MultipleChoiceQuiz generateTypeA(){
		int minDenom = 5;
		int maxDenom = 25;
		int minA = 3;
		int maxA = 16; 
		int choiceSize=5;
		Fraction[] fracs = new Fraction[choiceSize];
		int denom = ThreadLocalRandom.current().nextInt(minDenom, maxDenom);
		for (int i=0; i<choiceSize ; i++){
			int a;
			do {
				a = ThreadLocalRandom.current().nextInt(minA, maxA);
			}
			while (denom<=a);
			Fraction f = new Fraction(a, (int) denom);
			fracs[i] = f;
		}
		CommonFunctionAndValues.shuffleArray(fracs);
		Fraction fAnswer = MathUtils.findGreatest(fracs);
		MultipleChoiceQuiz q = new MultipleChoiceQuiz();
		q.setChoices(convertChoices(fracs));
		q.setCorrectAnswer(fAnswer.a+"/"+fAnswer.b);
		return q;
	}
	private List<String> convertChoices(Fraction[] fracs){
		List<String> choicesInString = new ArrayList<String>();
		for (Fraction f : fracs) {
			choicesInString.add(f.a+"/"+f.b);
		}
		return choicesInString;
	}
}
