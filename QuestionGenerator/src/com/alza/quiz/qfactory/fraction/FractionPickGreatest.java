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

public class FractionPickGreatest implements IQuestionFactory {
	private static int numq = 4;
	Locale loc;
	ResourceBundle bundle;
	public FractionPickGreatest(Locale loc){
		this.loc = loc;
		initStringFromLocale();
	}
	public FractionPickGreatest(){
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

			q = generateTypeC();
			q.setQuizLevel(QuizLevel.SULIT);
			q.setLessonGrade(4);

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
		int choiceSize=4;
		Fraction[] fracs = new Fraction[choiceSize];
		for (int i=0; i<choiceSize ; i++){
			int a,b;
			a = ThreadLocalRandom.current().nextInt(5, 17);
			b = ThreadLocalRandom.current().nextInt(7, 19);
			Fraction f = new Fraction(a, b);
			fracs[i] = f;
		}
		CommonFunctionAndValues.shuffleArray(fracs);
		Fraction fAnswer = MathUtils.findGreatest(fracs);
		MultipleChoiceQuiz q = new MultipleChoiceQuiz();
		q.setChoices(convertChoices(fracs,q,fAnswer));
		//q.setCorrectAnswer(fAnswer.a+"/"+fAnswer.b);
		return q;
	}

	private List<String> convertChoices(Fraction[] fracs, Quiz q, Fraction fAns){
		List<String> choicesInString = new ArrayList<String>();
		for (int i = 0; i < fracs.length; i++) {
			Fraction f = fracs[i];
			String s;
			switch (i) {
			case 0:
				s = f.a+"/"+f.b;
				if (f.equals(fAns)) {
					q.setCorrectAnswer(s);
				}
				break;
			case 1:
				s = f.getPercentage();
				if (f.equals(fAns)) {
					q.setCorrectAnswer(s);
				}
				break;
			case 2:
				s =f.getTwoDigitDecimalForm();
				if (f.equals(fAns)) {
					q.setCorrectAnswer(s);
				}
				break;
			case 3:
				s =f.getMixedFraction().toString();
				if (f.equals(fAns)) {
					q.setCorrectAnswer(s);
				}
				break;

			default:
				s="";
				break;
			}
			choicesInString.add(s);
		}
		return choicesInString;
	}

	@Override
	public List<Quiz> generateQuizList(int numOfQuestion) {
		numq = numOfQuestion;
		return generateQuizList();
	}


}
