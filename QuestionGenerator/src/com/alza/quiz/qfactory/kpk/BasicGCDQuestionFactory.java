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

public class BasicGCDQuestionFactory implements IQuestionFactory{
	private static int NUM_OF_QUESTIONS = 2;
	static int[][] bounds = {
		{12,29},{30,60}
	};
	
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
		for (int[] bound : bounds){
			int val1,val2,gcd,lcm;
			do {
				val1 = ThreadLocalRandom.current().nextInt(bound[0], bound[1]);
				val2 = ThreadLocalRandom.current().nextInt(bound[0], bound[1]);
				gcd = MathUtils.findGCD(val1,val2);
				lcm = MathUtils.findLCM(val1, val2);
			} while (val1==val2 || gcd<3);
			MultipleChoiceQuiz q = new MultipleChoiceQuiz();
			q.setQuestion("FPB dari bilangan " + val1 + " dan " + val2 + " adalah?");
			q.setCorrectAnswer(String.valueOf(gcd));
			q.addChoice(String.valueOf(gcd));
			q.addChoice(String.valueOf(lcm));
			q.addChoice(String.valueOf(val1/gcd));
			q.addChoice(String.valueOf(val2/gcd));
			q.addChoice(String.valueOf(val1+val2));
			q.addChoice(String.valueOf(val1*val2));
			
			q.setDifficultyLevel(QuizLevel.MUDAH);
			q.setLessonSubcategory("FPB dua bilangan");
			q.setLessonClassifier("Matematika SD");
			q.setLessonGrade(4);
			q.setSubCategoryOrder(2);
			q.setLessonCategory("KPK & FPB");
			lq.add(q);
		}
		for (int[] bound : bounds){
			int val1,val2,val3,gcd,lcm;
			do {
				val1 = ThreadLocalRandom.current().nextInt(bound[0], bound[1]);
				val2 = ThreadLocalRandom.current().nextInt(bound[0], bound[1]);
				val3 = ThreadLocalRandom.current().nextInt(bound[0], bound[1]);
				gcd = MathUtils.findGCD(val1,val2,val3);
				lcm = MathUtils.findLCM(val1, val2,val3);
			} while (val1==val2 || val1==val3 || val2==val3 || gcd<3);
			MultipleChoiceQuiz q = new MultipleChoiceQuiz();
			q.setQuestion("FPB dari bilangan " + val1 + ", "+val2+", dan " + val3 + " adalah?");
			q.setCorrectAnswer(String.valueOf(gcd));
			q.addChoice(String.valueOf(gcd));
			q.addChoice(String.valueOf(lcm/val1));
			q.addChoice(String.valueOf(lcm/val2));
			q.addChoice(String.valueOf(val1/gcd));
			q.addChoice(String.valueOf(val2/gcd));
			q.addChoice(String.valueOf(val3/gcd));
			q.addChoice(String.valueOf(val1+val2+val3));
			
			
			q.setDifficultyLevel(QuizLevel.SEDANG);
			q.setLessonSubcategory("FPB tiga bilangan");
			q.setLessonClassifier("Matematika SD");
			q.setLessonGrade(4);
			q.setSubCategoryOrder(2);
			q.setLessonCategory("KPK & FPB");
			lq.add(q);
		}
		return lq;
	}
	@Override
	public List<Quiz> generateQuizList(int numOfQuestion) {
		NUM_OF_QUESTIONS = numOfQuestion;
		return generateQuizList();
	}
}
