package org.solahate.quiz.qfactory;

import org.solahate.quiz.model.DifficultyLevel;
import org.solahate.quiz.model.Quiz;

public interface IQuestionFactory {
	public Quiz generateQuiz();
	public Quiz generateQuiz(DifficultyLevel difficultyLevel);

}
