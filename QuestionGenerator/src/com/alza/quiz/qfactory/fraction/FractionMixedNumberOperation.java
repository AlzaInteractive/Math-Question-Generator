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

public class FractionMixedNumberOperation implements IQuestionFactory{
	private static int numq = 6;
	Locale loc;
	ResourceBundle bundle;
	public FractionMixedNumberOperation(Locale loc){
		this.loc = loc;
		initStringFromLocale();
	}
	public FractionMixedNumberOperation(){
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
			int cutoff = Math.round(numq/2);
			MultipleChoiceQuiz q = null;
			if (i>=cutoff){
				q = generateTypeMultiDivide(i);
				q.setDifficultyLevel(QuizLevel.SULIT);
			}
			else {
				q = generateTypeAddSubtract(i);
				q.setDifficultyLevel(QuizLevel.SULIT);
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

	private MultipleChoiceQuiz generateTypeMultiDivide(int i) {
		MultipleChoiceQuiz q = new MultipleChoiceQuiz();
		int denomL,denomR = CommonFunctionAndValues.getRandomInt(6, 13);
		int a1,a2;
		do {
			denomL = CommonFunctionAndValues.getRandomInt(5, 10);
			denomR = CommonFunctionAndValues.getRandomInt(5, 10);
			a1 = CommonFunctionAndValues.getRandomInt(5, 13);
			a2 = CommonFunctionAndValues.getRandomInt(5, 13);
		} while (denomL > a1 || denomR > a2 || denomL==denomR ||
				a1%denomL==0||a2%denomR==0);//ensure that none of the numerators can be divided by divisor
		Fraction f1 = new Fraction(a1, denomL);
		Fraction f2 = new Fraction(a2, denomR);
		Fraction result;
		if (i % 2 == 0){
			result = f1.getResultWhenMultipliedBy(f2);
			q.setQuestion(f1.getMixedFraction().toMathJaxString()+" X "+f2.getMixedFraction().toMathJaxString());
		} else{
			result = f1.getResultWhenDividedBy(f2);
			q.setQuestion(f1.getMixedFraction().toMathJaxString()+" : "+f2.getMixedFraction().toMathJaxString());
		}
		String correctAnswer;
		if (result.isProper()){
			correctAnswer = result.toString();
		} else correctAnswer = result.getMixedFraction().toString();
		q.setCorrectAnswer(correctAnswer);
		q.setChoices(buildChoices(result));
		q.addChoice(correctAnswer);
		q.setLessonSubcategory(bundle.getString("fraction.mixednumber"));
		return q;
	}
	private MultipleChoiceQuiz generateTypeAddSubtract(int i) {
		MultipleChoiceQuiz q = new MultipleChoiceQuiz();
		int denom = CommonFunctionAndValues.getRandomInt(6, 13);
		int a1,a2;
		do {
			a1 = CommonFunctionAndValues.getRandomInt(5, 23);
			a2 = CommonFunctionAndValues.getRandomInt(5, 23);
		} while (denom > a1 || denom > a2 || //ensure numerators > divisor
				a1==a2|| //ensure numerators are not equal
				((a1+a2)%denom==0|| //ensure the operation resulted in a mixed formed fraction
				a1%denom==0||a2%denom==0)); //ensure that none of the numerators can be divided by divisor
		Fraction f1 = new Fraction(a1, denom);
		Fraction f2 = new Fraction(a2, denom);
		Fraction result;
		if (i % 2 == 0){
			result = f1.getResultWhenAddedWith(f2);
			q.setQuestion(f1.getMixedFraction().toMathJaxString()+" + "+f2.getMixedFraction().toMathJaxString());
		} else{
			result = f1.getResultWhenSubtractWith(f2);
			q.setQuestion(f1.getMixedFraction().toMathJaxString()+" - "+f2.getMixedFraction().toMathJaxString());
		}
		String correctAnswer;
		if (result.isProper()){
			correctAnswer = result.toString();
		} else correctAnswer = result.getMixedFraction().toString();
		q.setCorrectAnswer(correctAnswer);
		q.setChoices(buildChoices(result));
		q.addChoice(correctAnswer);
		q.setLessonSubcategory(bundle.getString("fraction.mixednumber"));		
		return q;
	}
	
	private Set<String> buildChoices(Fraction f1){
		MixedFraction m = f1.getMixedFraction();
		MixedFraction[] choices = new MixedFraction[2];		
		choices[0] = f1.new MixedFraction(m.a, m.x, m.b);
		choices[1] = f1.new MixedFraction(m.x, m.b, m.a);
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
