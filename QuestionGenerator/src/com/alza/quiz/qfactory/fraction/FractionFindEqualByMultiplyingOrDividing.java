package com.alza.quiz.qfactory.fraction;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.concurrent.ThreadLocalRandom;

import com.alza.common.math.Fraction;
import com.alza.quiz.model.MultipleChoiceQuiz;
import com.alza.quiz.model.Quiz;
import com.alza.quiz.model.QuizLevel;
import com.alza.quiz.qfactory.IQuestionFactory;
import com.alza.quiz.util.CommonFunctionAndValues;

public class FractionFindEqualByMultiplyingOrDividing implements IQuestionFactory{
	private int numq = 4;
	
	private int[][] simpleMultiplier = {{2,3},{3,5},{4,7},{5,8},{3,7}};
	private static final int CHOICE_SIZE=3;
	
	Locale loc;
	ResourceBundle bundle;
	public FractionFindEqualByMultiplyingOrDividing(Locale loc){
		this.loc = loc;
		initStringFromLocale();
	}
	public FractionFindEqualByMultiplyingOrDividing(){
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
		int slotSize = numq / 2;
		for (int i=0;i<numq;i++){
			boolean byMultiplying = (i<slotSize);
			MultipleChoiceQuiz q = generateEqualityQuiz(byMultiplying);
			quizList.add(q);
		}
		return quizList;
	}
	/**
	 * 
	 * @param byMultiplying, determine whether answer can be found by multiplying or dividing
	 * @return
	 */
	private MultipleChoiceQuiz generateEqualityQuiz(boolean byMultiplying) {
		int a,b,multip;
		do {
			a = ThreadLocalRandom.current().nextInt(1, 5); // randomize numerator
			b = ThreadLocalRandom.current().nextInt(2, 11); // randomize denominator
			multip =  ThreadLocalRandom.current().nextInt(2, 4); // prepare multiplier
		} while (a>=b); // make sure that it's a proper fraction, repeat if it's not one
		
		Fraction fQuest,fAnswer;
		fQuest = new Fraction(a, b);
		fAnswer =  new Fraction(a*multip,b*multip);
		
		if (!byMultiplying){
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
		
		if (byMultiplying) {
			q.setQuestion(bundle.getString("fraction.findequalbymultiplying")+": "+fQuest.toMathJaxString());
		} else {
			q.setQuestion(bundle.getString("fraction.findequalbydividing")+": "+fQuest.toMathJaxString());
		}
		
		//q.setQuestion(CommonFunctionAndValues.enclosedWithMathJaxExp(q.getQuestion()));
		q.setLessonSubcategory(bundle.getString("fraction.equality"));
		q.setLessonClassifier(bundle.getString("mathelementary"));
		q.setLessonCategory(bundle.getString("fraction"));
		q.setSubCategoryOrder(1);
		q.setLocale(loc);
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
	
	@Override
	public List<Quiz> generateQuizList(int numOfQuestion) {
		numq = numOfQuestion;
		return generateQuizList();
	}
}
