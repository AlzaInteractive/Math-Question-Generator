package com.alza.quiz.qfactory.kpk;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import com.alza.quiz.model.MultipleChoiceQuiz;
import com.alza.quiz.model.Quiz;
import com.alza.quiz.model.QuizLevel;
import com.alza.quiz.qfactory.IQuestionFactory;

public class FindMultipleQuestionFactory implements IQuestionFactory{

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
		List<Quiz> lq = new ArrayList<Quiz>();
		for (int i=0;i<2;i++){
			int base;
			do {
				base = ThreadLocalRandom.current().nextInt(minBase, maxBase);
			} while 
				((i % 2 == 0 && base % 2 ==0)||
						(i % 2 > 0 && base % 2 >0));
			MultipleChoiceQuiz q = new MultipleChoiceQuiz();
			q.setQuestion("Yang merupakan kelipatan dari "+base+" adalah?");
			q.setCorrectAnswer(base*2+","+base*3+","+base *4);
			q.addChoice(base*2+","+base*3+","+base *4);
			q.addChoice((base+1)+","+(base+2)+","+(base+3));
			q.addChoice((base+2)+","+(base+3)+","+(base+4));
			q.addChoice(((base+1)*2)+","+((base+1)*3)+","+((base+1) *4));
			q.addChoice(((base-1)*2)+","+((base-1)*3)+","+((base-1) *4));
			q.setDifficultyLevel(QuizLevel.MUDAH);
			q.setLessonSubcategory("Kelipatan bilangan");
			q.setLessonClassifier("Matematika SD");
			q.setLessonGrade(4);
			q.setSubCategoryOrder(0);
			q.setLessonCategory("KPK & FPB");
			lq.add(q);
		}
		return lq;
	}
	
}
