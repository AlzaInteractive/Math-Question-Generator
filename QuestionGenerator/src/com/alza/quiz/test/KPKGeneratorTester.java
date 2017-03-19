package com.alza.quiz.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.alza.quiz.model.MultipleChoiceQuiz;
import com.alza.quiz.model.Quiz;
import com.alza.quiz.qfactory.IQuestionFactory;
import com.alza.quiz.qfactory.kpk.BasicGCDQuestionFactory;
import com.alza.quiz.qfactory.kpk.BasicGCDScenarioQuestionFactory;
import com.alza.quiz.qfactory.kpk.BasicScenarioKPKQuestionFactory;
import com.alza.quiz.qfactory.kpk.FindFactorsOfQuestionFactory;
import com.alza.quiz.qfactory.kpk.FindMultipleQuestionFactory;
import com.alza.quiz.qfactory.kpk.TripeNumKPKQuestionFactory;
import com.alza.quiz.qfactory.kpk.TwoNumKPKQuestionFactory;
import com.alza.quiz.qfactory.kpk.WhichDateScenarioKPKQuestionFactory;
import com.alza.quiz.qfactory.kpk.WhichDayScenarioKPKQuestionFactory;
import com.alza.quiz.qfactory.kpk.WhichHourScenarioKPKQuestionFactory;

public class KPKGeneratorTester {
	public static void main(String[] args) {

		final long startTime = System.currentTimeMillis();
		int testCount = 1;
		for (int i = 0; i < testCount; i++) {
			allGenerator();
		}
		final long endTime = System.currentTimeMillis();
		System.out.println("Total running time (ms) = " +(endTime-startTime));
	}

	
	private static void allGenerator(){
		List<IQuestionFactory> lqf = new ArrayList<IQuestionFactory>();
		lqf.add(new FindMultipleQuestionFactory());
		lqf.add(new FindFactorsOfQuestionFactory());
		lqf.add(new TwoNumKPKQuestionFactory());
		lqf.add(new TripeNumKPKQuestionFactory());
		lqf.add(new BasicGCDQuestionFactory());
		lqf.add(new BasicScenarioKPKQuestionFactory());
		lqf.add(new BasicGCDScenarioQuestionFactory());
		lqf.add(new WhichDayScenarioKPKQuestionFactory());
		lqf.add(new WhichHourScenarioKPKQuestionFactory());
		lqf.add(new WhichDateScenarioKPKQuestionFactory());
		
		
		List<Quiz> ql = new ArrayList<Quiz>();
		for (IQuestionFactory qf : lqf) {
			ql.addAll(qf.generateQuizList());
			
		}
		Collections.sort(ql);
		for (Quiz q : ql) {
			System.out.println("------------------------------");
			System.out.println("Grade : "+q.getLessonGrade());
			System.out.println("Subcategory : " +q.getLessonSubcategory());
			System.out.println("Question : " + q.getQuestion());
			MultipleChoiceQuiz mq = (MultipleChoiceQuiz) q;
			System.out.println("Choices : "+ String.join(" , ", mq.getChoices()));
			System.out.println("Answer : "+ q.getCorrectAnswer());
		}
		System.out.println("Jumlah soal : "+ql.size());
	}
}
