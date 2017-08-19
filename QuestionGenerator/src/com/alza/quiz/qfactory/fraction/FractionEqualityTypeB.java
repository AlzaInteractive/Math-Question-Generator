package com.alza.quiz.qfactory.fraction;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.ResourceBundle;

import com.alza.quiz.model.MultipleChoiceQuiz;
import com.alza.quiz.model.Quiz;
import com.alza.quiz.model.QuizLevel;
import com.alza.quiz.qfactory.IQuestionFactory;
import com.alza.quiz.util.CommonFunctionAndValues;

public class FractionEqualityTypeB implements IQuestionFactory{
	private int numq = 4;
	
	private int[][] simpleMultiplier = {{2,3},{3,5},{4,7},{5,8},{3,7}};
	
	Locale loc;
	ResourceBundle bundle;
	public FractionEqualityTypeB(Locale loc){
		this.loc = loc;
		initStringFromLocale();
	}
	public FractionEqualityTypeB(){
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
			MultipleChoiceQuiz q = generateByOddEven(i);
			quizList.add(q);
		}
		return quizList;
	}
	private MultipleChoiceQuiz generateByOddEven(int i){
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
			q.setQuestion(CommonFunctionAndValues.MJXTAG+
					bundle.getString("if")+ 
					" \frac{x}{"+b+"} = \frac{"+c+"}{"+d+"} "+bundle.getString("fraction.thenxequal")+
					CommonFunctionAndValues.MJXTAG);
			q.setCorrectAnswer(String.valueOf(a));
			choices = buildChoices(a,b,c,d,"x");
		} else {
			q.setQuestion(
					CommonFunctionAndValues.MJXTAG+
					bundle.getString("if")+ 
					" \frac{"+a+"}{y} = \frac{"+c+"}{"+d+"} "+bundle.getString("fraction.thenyequal")+
					CommonFunctionAndValues.MJXTAG);
			//q.setQuestion("Jika pecahan "+a+"/y"+" = "+c+"/"+d+", maka nilai y sama dengan?");
			q.setCorrectAnswer(String.valueOf(b));
			choices = buildChoices(a,b,c,d,"y");
		}
		q.setChoices(choices);
		q.setLessonSubcategory(bundle.getString("fraction.equality"));
		q.setLessonClassifier(bundle.getString("mathelementary"));
		q.setLessonCategory(bundle.getString("fraction"));
		q.setSubCategoryOrder(1);
		q.setLocale(loc);
		return q;
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
	@Override
	public List<Quiz> generateQuizList(int numOfQuestion) {
		numq = numOfQuestion;
		return generateQuizList();
	}
}
