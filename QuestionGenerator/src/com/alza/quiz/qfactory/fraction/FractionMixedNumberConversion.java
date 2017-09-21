package com.alza.quiz.qfactory.fraction;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Set;

import com.alza.common.math.Fraction;
import com.alza.common.math.Fraction.MixedFraction;
import com.alza.quiz.model.MultipleChoiceQuiz;
import com.alza.quiz.model.Quiz;
import com.alza.quiz.model.QuizLevel;
import com.alza.quiz.qfactory.IQuestionFactory;
import com.alza.quiz.util.CommonFunctionAndValues;

public class FractionMixedNumberConversion implements IQuestionFactory{
	private static int numq = 6;
	Locale loc;
	ResourceBundle bundle;
	public FractionMixedNumberConversion(Locale loc){
		this.loc = loc;
		initStringFromLocale();
	}
	public FractionMixedNumberConversion(){
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
		List<Quiz> quizList= new ArrayList<Quiz>();
		for (int i=0; i<numq; i++){
			MultipleChoiceQuiz q = null;
			boolean isOdd = i % 2 > 0; 
			if (isOdd){
				q = generateFromMixedForm();
				q.setDifficultyLevel(QuizLevel.MUDAH);
			}
			else {
				q = generateToMixedForm();
				q.setDifficultyLevel(QuizLevel.MUDAH);
			} 
			//q.setQuestion(CommonFunctionAndValues.enclosedWithMathJaxExp(q.getQuestion()));
			q.setLessonClassifier(bundle.getString("mathelementary"));
			q.setLessonCategory(bundle.getString("fraction"));
			q.setLessonGrade(5);
			q.setSubCategoryOrder(2);
			q.setLocale(loc);
			quizList.add(q);
		}
		return quizList;
	}

	
	
	private MultipleChoiceQuiz generateFromMixedForm() {
		MultipleChoiceQuiz q = new MultipleChoiceQuiz();
		int denomLeft;
		int a1;
		do {
			denomLeft = CommonFunctionAndValues.getRandomInt(5, 13);;			
			a1 = CommonFunctionAndValues.getRandomInt(5, 23);
		} while (denomLeft>=a1 || a1%denomLeft==0);
		Fraction f1 = new Fraction(a1, denomLeft);
		Fraction result;
			q.setQuestion(bundle.getString("fraction.mixednumber.tofractionform")
					+f1.getMixedFraction().toMathJaxString());
		result = f1;
		q.setCorrectAnswer(result.toString());
		q.setChoices(buildChoices(f1));
		q.setLessonSubcategory(bundle.getString("fraction.mixednumber"));
		
		return q;
	}
	private MultipleChoiceQuiz generateToMixedForm() {
		MultipleChoiceQuiz q = new MultipleChoiceQuiz();
		int denomLeft;
		int a1;
		do {
			denomLeft = CommonFunctionAndValues.getRandomInt(5, 13);;			
			a1 = CommonFunctionAndValues.getRandomInt(5, 23);
		} while (denomLeft>=a1 || a1%denomLeft==0);
		Fraction f1 = new Fraction(a1, denomLeft);
		MixedFraction result;
			q.setQuestion(bundle.getString("fraction.mixednumber.fractionformof")
					+f1.toMathJaxString());
		result = f1.getMixedFraction();
		q.setCorrectAnswer(result.toString());
		q.setChoices(buildChoices(f1));
		q.setLessonSubcategory(bundle.getString("fraction.mixednumber"));
		
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
		numq = numOfQuestion;
		return generateQuizList();
	}
}
