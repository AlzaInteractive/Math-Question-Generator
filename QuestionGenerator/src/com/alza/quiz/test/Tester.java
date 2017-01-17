package com.alza.quiz.test;

import java.util.ArrayList;
import java.util.List;

import com.alza.quiz.model.QuizLevel;
import com.alza.quiz.model.MultipleChoiceQuiz;
import com.alza.quiz.model.Quiz;
import com.alza.quiz.qfactory.fraction.FindGreatestFractionQuestionFactory;
import com.alza.quiz.qfactory.fraction.SimplifyFractionQuestionFactory;
import com.alza.quiz.qfactory.kpk.BasicKPKQuestionFactory;
import com.alza.quiz.qfactory.kpk.IQuestionFactory;
import com.alza.quiz.qfactory.kpk.WhichDayScenarioKPKQuestionFactory;

public class Tester {
	public static void main(String[] args) {
		//basicGenerator();
		//whichDayGenerator();
		//fractionEquality();
		for (int i = 0; i < 1500; i++) {
			System.out.println(i);
			fractionEquality();
		}
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
			//System.out.println("Choices : "+ String.join(" , ", q.getChoices()));
			System.out.println("Answer : "+ q.getCorrectAnswer());
		}
	}
	private static void fractionEquality(){
		//IQuestionFactory qf = new FractionEqualityQuestionFactory();
		//IQuestionFactory qf = new FindGreatestFractionQuestionFactory();
		IQuestionFactory qf = new SimplifyFractionQuestionFactory();
		List<Quiz> ql = qf.generateQuizList();
		for (Quiz q : ql) {
			System.out.println("------------------------------");
			System.out.println("Question : " + q.getQuestion());
			MultipleChoiceQuiz mq = (MultipleChoiceQuiz) q;
			System.out.println("Choices : "+ String.join(" , ", mq.getChoices()));
			System.out.println("Answer : "+ q.getCorrectAnswer());
		}
	}
}
