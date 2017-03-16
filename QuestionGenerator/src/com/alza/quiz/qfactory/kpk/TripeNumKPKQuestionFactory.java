package com.alza.quiz.qfactory.kpk;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import com.alza.common.math.MathUtils;
import com.alza.quiz.model.MultipleChoiceQuiz;
import com.alza.quiz.model.Quiz;
import com.alza.quiz.model.QuizLevel;
import com.alza.quiz.qfactory.IQuestionFactory;

public class TripeNumKPKQuestionFactory implements IQuestionFactory{

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
		for (int i = 0; i < 2; i++) {
			do {
				val1 = ThreadLocalRandom.current().nextInt(minBase, maxBase);
				val2 = ThreadLocalRandom.current().nextInt(minBase, maxBase);
				val3 = ThreadLocalRandom.current().nextInt(minBase, maxBase);
			} while (val1 == val2 || val1 == val3 || val2 == val3 // no duplicate value
					|| MathUtils.findGCD(val1, val2, val3) == 1); // gcd > 1
			int lcm = MathUtils.findLCM(val1, val2, val3);
			MultipleChoiceQuiz q = new MultipleChoiceQuiz();
			q.setQuestion("KPK dari bilangan " + val1 + ", " + val2 + ", dan "
					+ val3 + " adalah?");
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
			q.setLessonSubcategory("KPK tiga bilangan");
			q.setLessonClassifier("Matematika SD");
			q.setLessonGrade(4);
			q.setSubCategoryOrder(2);
			q.setLessonCategory("KPK & FPB");
			lq.add(q);
		}
		return lq;
	}
	
}
