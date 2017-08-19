package com.alza.quiz.qfactory.fraction;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Set;

import com.alza.common.math.Fraction;
import com.alza.common.math.MathUtils;
import com.alza.quiz.model.MultipleChoiceQuiz;
import com.alza.quiz.model.Quiz;
import com.alza.quiz.model.QuizLevel;
import com.alza.quiz.qfactory.IQuestionFactory;
import com.alza.quiz.util.CommonFunctionAndValues;

public class FractionMultiplication implements IQuestionFactory{
	private static int numq = 4;
	Locale loc;
	ResourceBundle bundle;
	public FractionMultiplication(Locale loc){
		this.loc = loc;
		initStringFromLocale();
	}
	public FractionMultiplication(){
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
			q = generateTypeA(i);
			q.setDifficultyLevel(QuizLevel.MUDAH);
			q.setLessonSubcategory(bundle.getString("fraction.multiplication"));
			q.setLessonClassifier(bundle.getString("mathelementary"));
			q.setLessonCategory(bundle.getString("fraction"));
			q.setSubCategoryOrder(1);
			q.setLessonGrade(5);
			q.setLocale(loc);
			quizList.add(q);
		}
		return quizList;
	}

	private MultipleChoiceQuiz generateTypeA(int i) {
		MultipleChoiceQuiz q = new MultipleChoiceQuiz();
		int denom = CommonFunctionAndValues.getRandomInt(11, 41);
		int a1,a2;
		int denomLeft,denomRight;
		int gcdL,gcdR;
		Fraction f1,f2;
		if (i % 2 == 0) {
			do {
				a1 = CommonFunctionAndValues.getRandomInt(5, 17);
				a2 = CommonFunctionAndValues.getRandomInt(5, 17);
			} while (!(denom > a1 && denom > a2 && (a1+a2)<=denom && a1>a2));
			f1 = new Fraction(a1, denom);
			f2 = new Fraction(a2, denom);
			
		} else  {
			do {
				denomLeft = CommonFunctionAndValues.getRandomInt(5, 34);;
				denomRight = CommonFunctionAndValues.getRandomInt(5, 34);
				a1 = CommonFunctionAndValues.getRandomInt(5, 17);
				a2 = CommonFunctionAndValues.getRandomInt(5, 17);
				gcdL = MathUtils.findGCD(a1,denomRight);
				gcdR = MathUtils.findGCD(a2,denomLeft);
			} while (denomLeft==denomRight
					||a1>denomLeft ||a2>denomRight
					||gcdL < 2 || gcdR<2 );
			f1 = new Fraction(a1, denomLeft);
			f2 = new Fraction(a2, denomRight);
		} 
		
		Fraction result;
		result = f1.getResultWhenMultipliedBy(f2);
		q.setQuestion(f1.toMathJaxString()+" X "+f2.toMathJaxString());
		q.setQuestion(CommonFunctionAndValues.enclosedWithMathJaxExp(q.getQuestion()));
		q.setCorrectAnswer(result.toString());
		q.setChoices(buildChoices(f1,f2,result));
		return q;
	}
	
	private Set<String> buildChoices(Fraction f1,Fraction f2,Fraction result){
		Fraction[] choices = new Fraction[6];
		choices[0] = result;
		boolean isMultiplication=false;
		isMultiplication = (result.equals(f1.getResultWhenMultipliedBy(f2)));
		if (isMultiplication){
			choices[1] = result.inverse();
			choices[2] = f1.getResultWhenMultipliedBy(f2.inverse());
			choices[3] = f2.getResultWhenMultipliedBy(f1.inverse());
			choices[4] = f1.inverse().getResultWhenMultipliedBy(f2.inverse());
			choices[5] = new Fraction(f1.a * f2.a, f1.b);
		} else {
			choices[1] = result.inverse();
			choices[2] = f1.getResultWhenDividedBy(f2.inverse());
			choices[3] = f2.getResultWhenDividedBy(f1.inverse());
			choices[4] = f1.inverse().getResultWhenDividedBy(f2.inverse());
			choices[5] = new Fraction(f1.a * f2.a, f1.b );
		}
		Set<String> choicesInString = new HashSet<String>();
		for (Fraction f : choices) {
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
