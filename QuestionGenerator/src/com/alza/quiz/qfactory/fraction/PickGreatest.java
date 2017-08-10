package com.alza.quiz.qfactory.fraction;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.concurrent.ThreadLocalRandom;

import com.alza.common.math.Fraction;
import com.alza.common.math.MathUtils;
import com.alza.quiz.model.MultipleChoiceQuiz;
import com.alza.quiz.model.Quiz;
import com.alza.quiz.model.QuizLevel;
import com.alza.quiz.qfactory.IQuestionFactory;
import com.alza.quiz.util.CommonFunctionAndValues;

public class PickGreatest implements IQuestionFactory {
	private static int numq = 4;
	Locale loc;
	ResourceBundle bundle;
	public PickGreatest(Locale loc){
		this.loc = loc;
		initStringFromLocale();
	}
	public PickGreatest(){
		this.loc = new Locale("in", "ID");
		initStringFromLocale();
	}
	private void initStringFromLocale(){
		bundle = ResourceBundle.getBundle("lang.langbundle", loc);
	}
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
		for (int i=0;i<numq;i++){
			MultipleChoiceQuiz q = new MultipleChoiceQuiz();
			if (i % 3 == 2){
				q = generateTypeC();
				q.setQuizLevel(QuizLevel.SULIT);
				q.setLessonGrade(4);
			} else if (i % 3 == 1 ){
				q = generateTypeB();
				q.setQuizLevel(QuizLevel.SEDANG);
				q.setLessonGrade(4);
			} else {
				q = generateTypeA();
				q.setQuizLevel(QuizLevel.MUDAH);
				q.setLessonGrade(4);
			}
			q.setQuestion(bundle.getString("fraction.pickgreatest"));
			q.setLessonSubcategory(bundle.getString("fraction.compare"));
			q.setLessonClassifier(bundle.getString("mathelementary"));
			q.setLessonCategory(bundle.getString("fraction"));
			q.setSubCategoryOrder(2);
			quizList.add(q);
		}
		return quizList;
	}
	public MultipleChoiceQuiz generateTypeC(){
		int choiceSize=2;
		Fraction[] fracs = new Fraction[choiceSize];
		for (int i=0; i<choiceSize ; i++){
			int a,b;
			do {
				a = ThreadLocalRandom.current().nextInt(5, 17);
				b = ThreadLocalRandom.current().nextInt(7, 19);
			} while (b<=a);
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
		long denom = ThreadLocalRandom.current().nextInt(3, 7);
		for (int i=0; i<choiceSize ; i++){
			int a,b;
			do {
				a = ThreadLocalRandom.current().nextInt(3, 13);
				b = ThreadLocalRandom.current().nextInt(3, 34);
			} while (a>=b || b % denom > 0);
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
			Fraction f = new Fraction(a, denom);
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

	@Override
	public List<Quiz> generateQuizList(int numOfQuestion) {
		numq = numOfQuestion;
		return generateQuizList();
	}

	
}
