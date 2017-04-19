package com.alza.quiz.qfactory;

import com.alza.quiz.model.Quiz;
import com.alza.quiz.model.QuizLevel;

import java.util.List;

public interface IQuestionFactory {
	public Quiz generateQuiz();
	public Quiz generateQuiz(QuizLevel quizLevel);
	public List<Quiz> generateQuizList();
	public List<Quiz> generateQuizList(int numOfQuestion);
	

}
