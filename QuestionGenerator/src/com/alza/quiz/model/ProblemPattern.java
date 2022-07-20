package com.alza.quiz.model;

public class ProblemPattern {
	public String question;
	public String expression;
	public String[] choicePattern;
	public ProblemPattern(String question, String expression, String[] choicePattern) {
		this.question = question;
		this.expression = expression;
		this.choicePattern = choicePattern;
	}

}
