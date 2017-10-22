package com.alza.quiz.qfactory.fraction;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.concurrent.ThreadLocalRandom;

import com.alza.common.math.Fraction;
import com.alza.quiz.model.MultipleChoiceGeomQuiz;
import com.alza.quiz.model.Quiz;
import com.alza.quiz.model.QuizLevel;
import com.alza.quiz.qfactory.IQuestionFactory;

public class FractionRepresentation implements IQuestionFactory{
	Locale loc;
	ResourceBundle bundle;
	public FractionRepresentation(Locale loc){
		this.loc = loc;
		initStringFromLocale();
	}
	public FractionRepresentation(){
		this.loc = new Locale("in", "ID");
		initStringFromLocale();
	}
	private void initStringFromLocale(){
		bundle = ResourceBundle.getBundle("lang.langbundle", loc);
	}
	int numOfQuestion = 3;
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
		List<Quiz> lq = new ArrayList<Quiz>();
		for (int i= 0;i<numOfQuestion;i++){
			int divisor;
			do {
				divisor = ThreadLocalRandom.current().nextInt(2, 13);
			} while (divisor % 2 == 1);
			int numerator;
			if (i % 2 == 0){
				numerator = ThreadLocalRandom.current().nextInt(1, divisor);
			} else {
				numerator = ThreadLocalRandom.current().nextInt(divisor+1, 2 * divisor);
			}
			int remainder = divisor - numerator;
			Fraction f = new Fraction(numerator, divisor);		
			MultipleChoiceGeomQuiz q = new MultipleChoiceGeomQuiz();
			FractionRepresentationInAPie fPie = new FractionRepresentationInAPie(numerator, divisor);
			q.setQuestion(bundle.getString("fraction.representationquestion"));
			q.setCorrectAnswer(f.toString());
			q.setChoices(buildChoices(numerator, divisor, remainder));
			q.setGeomShape(fPie.getPaths());
			q.setDifficultyLevel(QuizLevel.MUDAH);
			q.setLessonSubcategory(bundle.getString("fraction.representation"));
			q.setLessonClassifier(bundle.getString("mathelementary"));
			q.setLessonGrade(4);
			q.setSubCategoryOrder(3);
			q.setLocale(loc);
			q.setLessonCategory(bundle.getString("fraction"));
			q.setLocale(loc);
			lq.add(q);
		}
		return lq;
	}

	@Override
	public List<Quiz> generateQuizList(int numOfQuestion) {
		this.numOfQuestion = numOfQuestion;
		return generateQuizList();
	}
	

	private List<String> buildChoices(int numerator, int divisor, int remainder) {
		List<String> choices = new ArrayList<String>();
		choices.add(new Fraction(numerator,divisor).toString());
		choices.add(new Fraction(numerator,remainder).toString());
		//choices.add(new Fraction(remainder,divisor).toString());
		//choices.add(new Fraction(remainder,numerator).toString());
		choices.add(new Fraction(remainder,numerator).toString());
		choices.add(new Fraction(divisor,numerator).toString());
		choices.add(new Fraction(divisor,remainder).toString());
		return choices;
	}
	
}
