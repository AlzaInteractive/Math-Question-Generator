package com.alza.quiz.qfactory.fraction;

import java.util.ArrayList;
import java.util.Collections;
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

public class FractionSimplify implements IQuestionFactory {
	private int numq = 2;
	Locale loc;
	ResourceBundle bundle;
	
	public FractionSimplify() {
		this.loc = new Locale("in", "ID");
		initStringFromLocale();
	}
	
	public FractionSimplify(Locale loc) {
		super();
		this.loc = loc;
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
			int multi = ThreadLocalRandom.current().nextInt(2, 7);
			int a,b;
			do {
				a = ThreadLocalRandom.current().nextInt(2, 13);
				b = ThreadLocalRandom.current().nextInt(3, 17);
			} while (a>=b);
			
			int c = a * multi;
			int d = b * multi;
			Fraction fQuest = new Fraction(c,d);
			Fraction fAns = fQuest.getSimplestForm();
			List<Fraction> choices = new ArrayList<Fraction>();
			choices.add(fAns);
			choices.add(new Fraction(c/2, d/2));
			choices.add(new Fraction(c/3, d/3));
			if (c>10 && d>10){
				choices.add(new Fraction(c/4, d/4));
				choices.add(new Fraction(c/5, d/5));
			}
			Collections.shuffle(choices);
			MultipleChoiceQuiz q = new MultipleChoiceQuiz();
			q.setChoices(convertChoices(choices));
			q.setCorrectAnswer(fAns.a+"/"+fAns.b);
			String question = bundle.getString("fraction.simplifyquestion");
			q.setQuestion(question+" \frac{"+c+"}{"+d+"}");
			q.setQuestion(CommonFunctionAndValues.enclosedWithMathJaxExp(q.getQuestion()));
			q.setDifficultyLevel(QuizLevel.MUDAH);
			q.setLessonSubcategory(bundle.getString("fraction.simplify"));
			q.setLessonClassifier(bundle.getString("mathelementary"));
			q.setLessonGrade(4);
			q.setSubCategoryOrder(5);
			q.setLocale(loc);
			q.setLessonCategory(bundle.getString("fraction"));
			quizList.add(q);
		}
		return quizList;
	}
	private List<String> convertChoices(List<Fraction> fracs){
		List<String> choicesInString = new ArrayList<String>();
		for (Fraction f : fracs) {
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
