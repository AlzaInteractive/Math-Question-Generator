package com.alza.quiz.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class MultipleChoiceQuiz extends  Quiz{
	//protected String correctAnswer;
	protected boolean sortedChoices = false; 
	private List<String> choices = new ArrayList<String>();
	
	public void enforceSortedChoices() {
		sortedChoices = true;
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
		if (!sortedChoices) {
			Collections.shuffle(this.choices);
		}
		return choices;
	}
	public void setChoices(List<String> choices) {
		this.choices = new ArrayList<>();
		for (String i : choices) {
			addChoice(String.valueOf(i));
		}
		//Collections.shuffle(this.choices);
	}
	public void setChoices(String...  choices){
		this.choices = new ArrayList<>();
		for (String c:choices  ) {
			addChoice(c);
		}
		//Collections.shuffle(this.choices);
	}
	public void setChoices(Set<String>  choices){
		this.choices = new ArrayList<>();
		for (String c:choices  ) {
			addChoice(c);
		}
		//Collections.shuffle(this.choices);
	}
	public void setChoices(int... choices){
		this.choices = new ArrayList<>();
		for (int i : choices) {
			addChoice(String.valueOf(i));
		}
		//Collections.shuffle(this.choices);
	}
	public void setChoices(double... choices){
		this.choices = new ArrayList<>();
		for (double i : choices) {
			addChoice(String.valueOf(i));
		}
		//Collections.shuffle(this.choices);
	}
	
	public void addChoice(String choice){
		for (String s : choices){
			if (s.equalsIgnoreCase(choice)) return;
		}
		this.choices.add(choice);
	}
	
	public void addChoice(int...c){
		for (int o : c) {
			addChoice(String.valueOf(o));
		}
	}
	
	@Override
	public boolean isCorrect(Object answer) {
		//System.out.println(correctAnswer+" vs "+answer);
		if (correctAnswer.equals(answer)){
			return true;
		}
		return false;
	}

	public boolean isDuplicate(MultipleChoiceQuiz q){
		return q.question.equals(question);
	}
	
}
