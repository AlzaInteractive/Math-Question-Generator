package com.alza.quiz.qfactory.fraction;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import com.alza.common.math.Fraction;
import com.alza.common.math.MathUtils;
import com.alza.quiz.model.MultipleChoiceQuiz;
import com.alza.quiz.model.Quiz;
import com.alza.quiz.model.QuizLevel;
import com.alza.quiz.qfactory.kpk.IQuestionFactory;
import com.alza.quiz.util.CommonFunctionAndValues;

public class FindGreatestFractionQuestionFactory implements IQuestionFactory {
	private static final int NUM_OF_QUESTIONS = 6;
	@Override
	public Quiz generateQuiz() {

		return null;
	}

	@Override
	public Quiz generateQuiz(QuizLevel quizLevel) {

		return null;
	}

	@Override
	public List<Quiz> generateQuizList() {
		List<Quiz> quizList = new ArrayList<Quiz>();
		for (int i=0;i<NUM_OF_QUESTIONS;i++){
			MultipleChoiceQuiz q = new MultipleChoiceQuiz();
			if (i > 3){
				q = generateTypeC();
			} else if (i > 1){
				q = generateTypeB();
			} else {
				q = generateTypeA();
			}
			q.setQuestion("Pilihlah pecahan dengan nilai paling besar.");
			q.setLessonClassifier("Matematika SD");
			q.setLessonCategory("Pecahan");
			q.setLessonSubcategory("Membandingkan nilai pecahan");
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
		long denom = 0;
		while (denom < 4){
			long l = System.nanoTime();
			denom =  l % 13;
		}
		for (int i=0; i<choiceSize ; i++){
			int multip = ThreadLocalRandom.current().nextInt(2, 7);
			int a = ThreadLocalRandom.current().nextInt(5, 63);
			int b = (int) denom * multip;
			while (b<=a){
				a = ThreadLocalRandom.current().nextInt(5, 63);
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
		int choiceSize=5;
		Fraction[] fracs = new Fraction[choiceSize];
		int denom = ThreadLocalRandom.current().nextInt(5, 37);
		for (int i=0; i<choiceSize ; i++){
			int a = ThreadLocalRandom.current().nextInt(5, 63);
			while (denom<=a){
				a = ThreadLocalRandom.current().nextInt(5, 63);
			}
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
