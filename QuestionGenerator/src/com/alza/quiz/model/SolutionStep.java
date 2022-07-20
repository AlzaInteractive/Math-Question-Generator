package com.alza.quiz.model;

public class SolutionStep {
	private String expression;
	private String explanation;
	private String expressionInMarkdown;
	private String explanationInMarkdown;
	public String getExpression() {
		return expression;
	}
	public void setExpression(String expression) {
		this.expression = expression;
	}
	public String getExplanation() {
		return explanation;
	}
	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}
	public String getExpressionInMarkdown() {
		return expressionInMarkdown;
	}
	public void setExpressionInMarkdown(String expressionInMarkdown) {
		this.expressionInMarkdown = expressionInMarkdown;
	}
	public String getExplanationInMarkdown() {
		return explanationInMarkdown;
	}
	public void setExplanationInMarkdown(String explanationInMarkdown) {
		this.explanationInMarkdown = explanationInMarkdown;
	}
	
}
