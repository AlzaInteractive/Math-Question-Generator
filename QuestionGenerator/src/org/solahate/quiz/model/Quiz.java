package org.solahate.quiz.model;

import java.util.List;

public class Quiz {
	private DifficultyLevel difficultyLevel;
	private String question;
	private String correctAnswer;
	private List<String> choices;
	
	public DifficultyLevel getDifficultyLevel() {
		return difficultyLevel;
	}
	public void setDifficultyLevel(DifficultyLevel difficultyLevel) {
		this.difficultyLevel = difficultyLevel;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getCorrectAnswer() {
		return correctAnswer;
	}
	public void setCorrectAnswer(String correctAnswer) {
		this.correctAnswer = correctAnswer;
	}
	public List<String> getChoices() {
		return choices;
	}
	public void setChoices(List<String> choices) {
		this.choices = choices;
	}
	public boolean isDuplicate(Quiz q){
		return q.question.equals(question);
	}
	
}
