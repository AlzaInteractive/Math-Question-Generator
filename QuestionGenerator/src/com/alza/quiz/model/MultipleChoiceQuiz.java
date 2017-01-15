package com.alza.quiz.model;

import java.util.ArrayList;
import java.util.List;

public class MultipleChoiceQuiz extends  Quiz{
	private String correctAnswer;
	private List<String> choices;
	
	public QuizLevel getQuizLevel() {
		return quizLevel;
	}
	public void setDifficultyLevel(QuizLevel quizLevel) {
		this.quizLevel = quizLevel;
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
	public void setChoices(String[]  choices){
		this.choices = new ArrayList<>();
		for (String c:choices  ) {
			this.choices.add(c);
		}
	}

	@Override
	public boolean isCorrect(Object answer) {
		System.out.println(correctAnswer+" vs "+answer);
		if (correctAnswer.equals(answer)){
			return true;
		}
		return false;
	}

	public boolean isDuplicate(MultipleChoiceQuiz q){
		return q.question.equals(question);
	}
	
}
