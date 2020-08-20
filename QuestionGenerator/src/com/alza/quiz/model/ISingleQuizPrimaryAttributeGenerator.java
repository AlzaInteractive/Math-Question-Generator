package com.alza.quiz.model;

public interface ISingleQuizPrimaryAttributeGenerator {
	public String generateQuestion();
	public String generateQuestionMathjax();
	public String generateQuestionWolfram();
	public String[] generateChoices();
	public String generateAnswer();
	public Quiz generateSingleQuiz();
	
}
