package com.alza.quiz.qfactory.kpk;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.concurrent.ThreadLocalRandom;

import com.alza.common.math.MathUtils;
import com.alza.quiz.model.MultipleChoiceQuiz;
import com.alza.quiz.model.Quiz;
import com.alza.quiz.model.QuizLevel;
import com.alza.quiz.qfactory.IQuestionFactory;

public class ThreeNumKPKQuestionFactory implements IQuestionFactory{
	private static int numq = 2;
	Locale loc;
	ResourceBundle bundle;
	
	public ThreeNumKPKQuestionFactory(){
		this.loc = new Locale("in", "ID");
		initStringFromLocale();
	}
	
	public ThreeNumKPKQuestionFactory(Locale loc) {
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
		int minBase = 5;
		int maxBase = 16;
		int val1,val2,val3;
		List<Quiz> lq = new ArrayList<Quiz>();
		for (int i = 0; i < numq; i++) {
			do {
				val1 = ThreadLocalRandom.current().nextInt(minBase, maxBase);
				val2 = ThreadLocalRandom.current().nextInt(minBase, maxBase);
				val3 = ThreadLocalRandom.current().nextInt(minBase, maxBase);
			} while (val1 == val2 || val1 == val3 || val2 == val3 // no duplicate value
					|| MathUtils.findGCD(val1, val2, val3) == 1); // gcd > 1
			int lcm = MathUtils.findLCM(val1, val2, val3);
			MultipleChoiceQuiz q = new MultipleChoiceQuiz();
			//q.setQuestion("KPK dari bilangan " + val1 + ", " + val2 + ", dan "
			//		+ val3 + " adalah?");
			q.setQuestion(bundle.getString("lcmgcd.question.lcmof") +" "+ val1+"  "+val2+"  "+val3+" ?");
			q.setCorrectAnswer(String.valueOf(lcm));
			//choices
			q.addChoice(String.valueOf(lcm));
			q.addChoice(String.valueOf(MathUtils.findLCM(val1, val2)));
			q.addChoice(String.valueOf(MathUtils.findLCM(val2, val3)));
			q.addChoice(String.valueOf(MathUtils.findLCM(val1, val3)));
			q.addChoice(String.valueOf(MathUtils.findGCD(val1, val2, val3)));
			q.addChoice(String.valueOf(val1 + val2 + val3));
			q.addChoice(String.valueOf(val1 * val2 * val3));
			//
			q.setDifficultyLevel(QuizLevel.SEDANG);
			q.setLessonCategory(bundle.getString("lcmgcd"));
			q.setLessonSubcategory(bundle.getString("lcmgcd.subcategory.lcm"));
			q.setLessonClassifier(bundle.getString("mathelementary"));
			q.setLessonGrade(4);
			q.setSubCategoryOrder(2);
			lq.add(q);
		}
		return lq;
	}
	@Override
	public List<Quiz> generateQuizList(int numOfQuestion) {
		numq = numOfQuestion;
		return generateQuizList();
	}
	
}
