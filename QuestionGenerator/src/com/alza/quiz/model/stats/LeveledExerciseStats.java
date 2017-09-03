package com.alza.quiz.model.stats;

import java.io.Serializable;

public class LeveledExerciseStats extends BasicExerciseStats implements Serializable {

	private static final long serialVersionUID = 2810324083750199224L;
	private int levelIndex;
	
    
	public LeveledExerciseStats() {
		
	}
    public LeveledExerciseStats(int levelIndex, String lessonCategory,
			int numOfQuestion) {
		super(lessonCategory, numOfQuestion);
		this.levelIndex = levelIndex;
		
	}
	public int getLevelIndex() {
		return levelIndex;
	}
	public void setLevelIndex(int levelIndex) {
		this.levelIndex = levelIndex;
	}
	
	
	public Medals getCorrectnessPerforManceMedals(){
		Medals m=null;
		double perf = this.numOfCorrectAnswer/this.numOfQuestion;
		if (perf > 0.99) {
			m = Medals.GOLD; 
		} else if (perf > 0.90) {
			m = Medals.SILVER;
		} else if (perf > 0.80) {
			m = Medals.BRONZE;
		} else if (perf > 0.70) {
			m = Medals.NONE;
		}
		return m;
	}
	public Medals getTimePerforManceMedals(){
		Medals m=null;
		double perf = timeElapsed/numOfCorrectAnswer;
		if (getCorrectnessPerforManceMedals()!=null) {
			if (perf < 30) {
				m = Medals.GOLD; 
			} else if (perf < 60) {
				m = Medals.SILVER;
			} else if (perf < 120) {
				m = Medals.BRONZE;
			} else if (perf < 180) {
				m = Medals.NONE;
			}
		}
		return m;
	}
    
}
