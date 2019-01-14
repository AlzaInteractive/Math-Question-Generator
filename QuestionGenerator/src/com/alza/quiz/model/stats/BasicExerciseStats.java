package com.alza.quiz.model.stats;

import java.util.Date;

public class BasicExerciseStats{
	protected String lessonCategory;
	protected Date timeTaken = new Date();
    protected long timeElapsed; //in seconds
    protected int score=0;
    protected int numOfQuestion=0;
    protected int numOfCorrectAnswer=0;
    
	public BasicExerciseStats() {
		
	}
    public BasicExerciseStats(String lessonCategory,
			int numOfQuestion) {
		super();
		this.lessonCategory = lessonCategory;
		this.numOfQuestion = numOfQuestion;
	}
	
	public String getLessonCategory() {
		return lessonCategory;
	}
	public void setLessonCategory(String lessonCategory) {
		this.lessonCategory = lessonCategory;
	}
	public Date getTimeTaken() {
		return timeTaken;
	}
	public void setTimeTaken(Date timeTaken) {
		this.timeTaken = timeTaken;
	}
	public long getTimeElapsed() {
		return timeElapsed;
	}
	public void setTimeElapsed(long timeElapsed) {
		this.timeElapsed = timeElapsed;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public int getNumOfQuestion() {
		return numOfQuestion;
	}
	public void setNumOfQuestion(int numOfQuestion) {
		this.numOfQuestion = numOfQuestion;
	}
	public int getNumOfCorrectAnswer() {
		return numOfCorrectAnswer;
	}
	public void setNumOfCorrectAnswer(int numOfCorrectAnswer) {
		this.numOfCorrectAnswer = numOfCorrectAnswer;
	}
	
	public int getCorrectPercentage() {
		double corrPct = ((double)numOfCorrectAnswer * 100)/(double)numOfQuestion;
		return (int)corrPct;
	}
	
	public void addStats(boolean correct, long time, int score){
        this.score = this.score + score;
        if (correct) {
			this.numOfCorrectAnswer++;
		}
        this.timeElapsed = this.timeElapsed + time;
    }
	
}
