package com.alza.quiz.qfactory.romans;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Set;

import com.alza.common.math.MathUtils;
import com.alza.quiz.model.MultipleChoiceQuiz;
import com.alza.quiz.model.Quiz;
import com.alza.quiz.model.QuizLevel;
import com.alza.quiz.qfactory.IQuestionFactory;
import com.alza.quiz.util.CommonFunctionAndValues;

public class RomanNumeralsQuestionFactory implements IQuestionFactory{
	Locale loc;
	ResourceBundle bundle;
	int lowerBound,upperBound;
	private int defnumq;
		
	public RomanNumeralsQuestionFactory(Locale loc,int loBound, int upBound) {
		this.lowerBound = loBound;
		this.upperBound = upBound;
		this.loc = loc;
		initStringFromLocale();
	}
	public RomanNumeralsQuestionFactory(int loBound, int upBound) {
		this.lowerBound = loBound;
		this.upperBound = upBound;
		this.loc = new Locale("en", "US");
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
	public List<Quiz> generateQuizList(int numOfQuestion) {
		this.defnumq = numOfQuestion;
		return generateQuizList();
	}
	
	@Override
	public List<Quiz> generateQuizList() {
		List<Quiz> quizList = new ArrayList<Quiz>();
			for (int i=0;i<defnumq;i++){
				MultipleChoiceQuiz q = new MultipleChoiceQuiz();
				int num = CommonFunctionAndValues.getRandomInt(lowerBound, upperBound);
				String romans = MathUtils.toRomanNumeral(num);
				int[] bound = {lowerBound,upperBound};
				if (i % 2 == 0){// to romans
					q.setQuestion(bundle.getString("romans.question.toroman")+": "+num);
					q.setCorrectAnswer(romans);
					q.setChoices(populateChoices(bound , num, true));
					q.setLessonSubcategory(bundle.getString("romans.subcategory.toromans"));
				} else { // from romans
					q.setQuestion(bundle.getString("romans.question.fromroman")+": "+romans);
					q.setCorrectAnswer(String.valueOf(num));
					q.setChoices(populateChoices(bound, num, false));
					q.setLessonSubcategory(bundle.getString("romans.subcategory.fromromans"));
				}
				q.setDifficultyLevel(QuizLevel.MUDAH);
				q.setLessonSubcategory(bundle.getString("romans"));
				q.setLessonClassifier(bundle.getString("mathelementary"));
				q.setLessonGrade(4);
				q.setSubCategoryOrder(1);
				q.setLocale(loc);
				q.setLessonCategory(bundle.getString("romans.category"));
				quizList.add(q);
			}
		return quizList;
	}
	
	private Set<String> populateChoices(int[] bound,int num, boolean toRomans){
		List<String> string = new ArrayList<String>();
		for (int i=0;i<10;i++){
			int c = CommonFunctionAndValues.getRandomInt(bound[0], bound[1]);
			if (toRomans){
				string.add(MathUtils.toRomanNumeral(c));
			} else {
				string.add(String.valueOf(c));
			}
		}
		Collections.shuffle(string);
		Set<String> choices = new HashSet<String>();
		for (int j=0;j<4;j++){
			choices.add(string.get(j));
		}
		if (toRomans){
			choices.add(MathUtils.toRomanNumeral(num));
		} else {
			choices.add(String.valueOf(num));
		}
		return choices;
	}


}
