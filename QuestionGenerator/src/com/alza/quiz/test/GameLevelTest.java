package com.alza.quiz.test;

import java.util.List;
import java.util.Locale;

import com.alza.quiz.model.GameLevel;
import com.alza.quiz.model.MultipleChoiceQuiz;
import com.alza.quiz.model.Quiz;
import com.alza.quiz.qfactory.fraction.FractionGameLevel;

public class GameLevelTest {
	public static void main(String[] args) {
		List<GameLevel> gls = FractionGameLevel.createGameLevels(new Locale("en","US"));
		for (GameLevel gameLevel : gls) {
			publishQ(gameLevel);
		}
	}
	private static void publishQ(GameLevel gl) {
		System.out.println("------------------------");
		System.out.println("Game Level: " + gl.getOrder()+" "+gl.getName());
		List<Quiz> lq = gl.generateQuiz();
		for (Quiz q : lq) {
			System.out.println("------------------------------");
			System.out.println("Grade : "+q.getLessonGrade());
			System.out.println("Subcategory :" +q.getLessonSubcategory());
			System.out.println("Question : " + q.getQuestion());
			System.out.println("Level : "+q.getQuizLevel());
			if (q instanceof MultipleChoiceQuiz){
				MultipleChoiceQuiz mq = (MultipleChoiceQuiz) q;
				System.out.println("Choices : "+ String.join(" , ", mq.getChoices()));
			}
			System.out.println("Answer : "+ q.getCorrectAnswer());
		}
	}
}
