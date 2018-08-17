package com.alza.quiz.generator.examples;

import java.util.List;
import java.util.Locale;

import com.alza.quiz.model.GameLevel;
import com.alza.quiz.model.MultipleChoiceQuiz;
import com.alza.quiz.model.Quiz;
import com.alza.quiz.qfactory.*;


public class GameLevelDemo {
	public static void main(String[] args) {
		IPlayableLevelsGroup lg = new FractionGameLevel();
		List<GameLevel> gls = lg.createGameLevels(new Locale("en","US"));
		// gls = lg.createGameLevels(new Locale("in","ID"));
		for (GameLevel gameLevel : gls) {
			printQuizzes(gameLevel);
		}
	}
	private static void printQuizzes(GameLevel gl) {
		System.out.println("------------------------");
		System.out.println("Game Level: " + gl.getOrder()+" "+gl.getName()+": "+gl.getDesc());
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
