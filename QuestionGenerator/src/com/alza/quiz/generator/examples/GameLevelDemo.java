package com.alza.quiz.generator.examples;

import java.util.Comparator;
import java.util.List;
import java.util.Locale;

import com.alza.quiz.model.GameLevel;
import com.alza.quiz.model.MultipleChoiceQuiz;
import com.alza.quiz.model.Quiz;
import com.alza.quiz.model.SolutionStep;
import com.alza.quiz.qfactory.*;


public class GameLevelDemo {
	public static void main(String[] args) {
		IPlayableLevelsGroup lg = new AlgebraGameLevel();
		List<GameLevel> gls = lg.createGameLevels(new Locale("en","US"));
		Locale loc = new Locale("in","ID");
		//loc = new Locale("en","US");
		gls = lg.createGameLevels(loc);
		System.out.println("Game levels: "+gls.size());
		for (GameLevel gameLevel : gls) {
			System.out.println("---");
			printQuizzes(gameLevel,loc);
		}
	}
	private static void printQuizzes(GameLevel gl,Locale loc) {
		System.out.println("------------------------");
		System.out.println("Game Level: " + gl.getOrder()+" "+gl.getName()+": "+gl.getDesc());
		List<Quiz> lq = gl.generateQuiz();
		lq.sort(new Comparator<Quiz>(
				) {
			@Override
		    public int compare(Quiz o1, Quiz o2) {
		        return o1.getQuizLevel().compareTo(o2.getQuizLevel());
		    }
		});
		for (Quiz q : lq) {
			System.out.println("------------------------------");
			//System.out.println("Subcategory :" +q.getLessonSubcategory());
			System.out.println("Question : " + q.getQuestion());
			if (q instanceof MultipleChoiceQuiz){
				MultipleChoiceQuiz mq = (MultipleChoiceQuiz) q;
				System.out.println("Choices : "+ String.join(" , ", mq.getChoices()));
			}
			System.out.println("Answer : "+ q.getCorrectAnswer());
			System.out.println("Problem : "+ q.getProblemString());
			System.out.println("Solver : "+ q.getMicrosoftMathSolverURL(loc));
			if (q.getSolutionSteps().size()>0) {
				for (SolutionStep step: q.getSolutionSteps()) {
					System.out.println((step.getExpression()+" | "+step.getExplanation()));
				}
			}
			//System.out.println("Solver : "+ q.getWolframSolverURL());
		}
	}
}
