package com.alza.quiz.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import com.alza.quiz.model.MultipleChoiceQuiz;
import com.alza.quiz.model.Quiz;
import com.alza.quiz.qfactory.IQuestionFactory;
import com.alza.quiz.qfactory.lcmgcd.GCDBasicScenarioQuestionFactory;
import com.alza.quiz.qfactory.lcmgcd.GCDPrimeFactorQuestionFactory;
import com.alza.quiz.qfactory.lcmgcd.GCDThreeNumQuestionFactory;
import com.alza.quiz.qfactory.lcmgcd.LCMBasicScenarioQuestionFactory;
import com.alza.quiz.qfactory.lcmgcd.LCMPrimeFactorQuestionFactory;
import com.alza.quiz.qfactory.lcmgcd.FindFactorsOfQuestionFactory;
import com.alza.quiz.qfactory.lcmgcd.FindMultiplesOfQuestionFactory;
import com.alza.quiz.qfactory.lcmgcd.FindPrimeFactorsOfQuestionFactory;
import com.alza.quiz.qfactory.lcmgcd.LCMThreeNumQuestionFactory;
import com.alza.quiz.qfactory.lcmgcd.GCDTwoNumQuestionFactory;
import com.alza.quiz.qfactory.lcmgcd.LCMTwoNumQuestionFactory;
import com.alza.quiz.qfactory.lcmgcd.LCMWhichDateRWPQuestionFactory;
import com.alza.quiz.qfactory.lcmgcd.LCMWhichDayRWPQuestionFactory;
import com.alza.quiz.qfactory.lcmgcd.LCMWhichHourRWPQuestionFactory;

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
		Locale loc = new Locale("en", "US");
		List<IQuestionFactory> lqf = new ArrayList<IQuestionFactory>();
		lqf.add(new FindMultiplesOfQuestionFactory(loc));
		lqf.add(new FindFactorsOfQuestionFactory(loc));
		lqf.add(new LCMTwoNumQuestionFactory(loc));
		lqf.add(new LCMThreeNumQuestionFactory(loc));
		lqf.add(new GCDTwoNumQuestionFactory(loc));
		lqf.add(new GCDThreeNumQuestionFactory(loc));
		lqf.add(new FindPrimeFactorsOfQuestionFactory(loc));
		lqf.add(new GCDPrimeFactorQuestionFactory(loc));
		lqf.add(new LCMPrimeFactorQuestionFactory(loc));
		lqf.add(new LCMBasicScenarioQuestionFactory(loc));
		lqf.add(new GCDBasicScenarioQuestionFactory(loc));
		lqf.add(new LCMWhichDayRWPQuestionFactory(loc));
		lqf.add(new LCMWhichDateRWPQuestionFactory(loc));
		lqf.add(new LCMWhichHourRWPQuestionFactory(loc));
		/**
		**/
		
		List<Quiz> ql = new ArrayList<Quiz>();
		for (IQuestionFactory qf : lqf) {
			ql.addAll(qf.generateQuizList());
			
		}
		//Collections.sort(ql);
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
