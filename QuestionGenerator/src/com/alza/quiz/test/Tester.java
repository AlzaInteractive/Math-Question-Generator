package com.alza.quiz.test;

import java.util.ArrayList;
import java.util.List;

import com.alza.quiz.model.QuizLevel;
import com.alza.quiz.model.MultipleChoiceQuiz;
import com.alza.quiz.model.Quiz;
import com.alza.quiz.qfactory.IQuestionFactory;
import com.alza.quiz.qfactory.fraction.FindGreatestFractionQuestionFactory;
import com.alza.quiz.qfactory.fraction.FractionAddSubtractQuestionFactory;
import com.alza.quiz.qfactory.fraction.FractionDecimalFormQuestionFactory;
import com.alza.quiz.qfactory.fraction.FractionEqualityQuestionFactory;
import com.alza.quiz.qfactory.fraction.FractionMultDivideQuestionFactory;
import com.alza.quiz.qfactory.fraction.MixedFractionQuestionFactory;
import com.alza.quiz.qfactory.fraction.ScenarioBasedFractionQuestionFactory;
import com.alza.quiz.qfactory.fraction.SimplifyFractionQuestionFactory;
import com.alza.quiz.qfactory.kpk.BasicKPKQuestionFactory;
import com.alza.quiz.qfactory.kpk.WhichDayScenarioKPKQuestionFactory;

public class Tester {
	public static void main(String[] args) {
		//basicGenerator();
		//whichDayGenerator();
		//fractionEquality();
		final long startTime = System.currentTimeMillis();
		int testCount = 1;
		for (int i = 0; i < testCount; i++) {
			//System.out.println(i);
			fractionEquality();
		}
		final long endTime = System.currentTimeMillis();
		System.out.println("Total running time (ms) =" +(endTime-startTime));
	}

	protected static void basicGenerator() {
		BasicKPKQuestionFactory f= new BasicKPKQuestionFactory();
		List<MultipleChoiceQuiz> lq = new ArrayList<MultipleChoiceQuiz>();
		for (int i = 0; i < 5; i++) {
			lq.add(f.generateQuiz(QuizLevel.MUDAH));
		}
		for (int i = 0; i < 5; i++) {
			lq.add(f.generateQuiz(QuizLevel.SEDANG));
		}
		for (MultipleChoiceQuiz q : lq) {
			System.out.println("------------------------------");
			System.out.println("Question : " + q.getQuestion());
			//System.out.println("Choices : "+ String.join(" , ", q.getChoices()));
			System.out.println("Answer : "+ q.getCorrectAnswer());
		}
	}
	protected static void whichDayGenerator(){
		IQuestionFactory qf = new WhichDayScenarioKPKQuestionFactory();
		List<Quiz> ql = new ArrayList<>();
		for (int i=1;i<=4;i++){
			Quiz q = qf.generateQuiz();
			ql.add(q);
		}
		for (Quiz q : ql){
			System.out.println("------------------------------");
			System.out.println("Question : " + q.getQuestion());
			System.out.println("Answer : "+ q.getCorrectAnswer());
		}
	}
	private static void fractionEquality(){
		List<IQuestionFactory> lqf = new ArrayList<IQuestionFactory>();
		lqf.add(new FractionEqualityQuestionFactory());
		lqf.add(new FindGreatestFractionQuestionFactory());
		lqf.add(new SimplifyFractionQuestionFactory());
		lqf.add(new FractionAddSubtractQuestionFactory());
		lqf.add(new FractionMultDivideQuestionFactory());
		lqf.add(new MixedFractionQuestionFactory());
		lqf.add(new FractionDecimalFormQuestionFactory());
		lqf.add(new ScenarioBasedFractionQuestionFactory());
		int i = 0;
		for (IQuestionFactory qf : lqf) {
			List<Quiz> ql = qf.generateQuizList();
			for (Quiz q : ql) {
				i++;
				System.out.println("------------------------------");
				System.out.println("Question : " + q.getQuestion());
				MultipleChoiceQuiz mq = (MultipleChoiceQuiz) q;
				System.out.println("Choices : "+ String.join(" , ", mq.getChoices()));
				System.out.println("Answer : "+ q.getCorrectAnswer());
			}
		}
		System.out.println(i);
	}
}
