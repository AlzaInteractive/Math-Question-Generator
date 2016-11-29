package org.solahate.quiz.test;

import java.util.ArrayList;
import java.util.List;

import org.solahate.quiz.model.DifficultyLevel;
import org.solahate.quiz.model.Quiz;
import org.solahate.quiz.qfactory.KPKQuestionFactory;

public class Tester {
	public static void main(String[] args) {
		KPKQuestionFactory f= new KPKQuestionFactory();
		List<Quiz> lq = new ArrayList<Quiz>(); 
		for (int i = 0; i < 5; i++) {
			lq.add(f.generateQuiz(DifficultyLevel.EASY));
		}
		for (int i = 0; i < 5; i++) {
			lq.add(f.generateQuiz(DifficultyLevel.MODERATE));
		}
		for (Quiz q : lq) {
			System.out.println("------------------------------");
			System.out.println("Question : " + q.getQuestion());
			System.out.println("Choices : "+ String.join(" , ", q.getChoices()));
			System.out.println("Answer : "+ q.getCorrectAnswer());
		}
	}

}
