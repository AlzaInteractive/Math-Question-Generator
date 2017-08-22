package com.alza.quiz.model.stats;

import java.io.Serializable;
import java.util.Date;

import com.alza.quiz.model.Quiz;

public class LeveledExerciseStats implements Serializable {

	private static final long serialVersionUID = 2810324083750199224L;
	private int levelIndex;
	private String lessonCategory;
	private Date timeTaken = new Date();
    private long timeElapsed; //in seconds
    private int score=0;
    private int numOfQuestion=0;
    private int numOfCorrectAnswer=0;
	public int getLevelIndex() {
		return levelIndex;
	}
	public void setLevelIndex(int levelIndex) {
		this.levelIndex = levelIndex;
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
	private void addCount(boolean correct){
        numOfQuestion ++;
        if (correct){
            numOfCorrectAnswer++;
        }
    }
	public void addStats(boolean correct, Quiz quiz, long time, int score){
        this.score = this.score + score;
        addCount(correct);
        this.timeElapsed = this.timeElapsed + time;
    }
	
	public Medals getCorrectnessPerforManceMedals(){
		Medals m=null;
		double perf = this.numOfCorrectAnswer/this.numOfQuestion;
		if (perf > 0.99) {
			m = Medals.PLATINUM; 
		} else if (perf > 0.90) {
			m = Medals.GOLD;
		} else if (perf > 0.80) {
			m = Medals.SILVER;
		} else if (perf > 0.70) {
			m = Medals.BRONZE;
		}
		return m;
	}
	public Medals getTimePerforManceMedals(){
		Medals m=null;
		double perf = this.timeElapsed/this.numOfCorrectAnswer;
		if (getCorrectnessPerforManceMedals()!=null) {
			if (perf < 30) {
				m = Medals.PLATINUM; 
			} else if (perf < 60) {
				m = Medals.GOLD;
			} else if (perf < 120) {
				m = Medals.SILVER;
			} else if (perf < 180) {
				m = Medals.BRONZE;
			}
		}
		return m;
	}
    
}
