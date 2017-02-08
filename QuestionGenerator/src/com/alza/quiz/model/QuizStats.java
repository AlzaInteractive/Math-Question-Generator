package com.alza.quiz.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alza.common.math.Fraction;

/**
 * Created by ewin.sutriandi@gmail.com on 11/01/17.
 */

public class QuizStats implements Serializable{
	private static final long serialVersionUID = 7662137635312010916L;
	private Date timeTaken = new Date();
    private long time;
    private int score=0;
    private int numOfQuestion=0;
    private int numOfCorrectAnswer=0;
    private List<QuizLog> logs;

    public QuizStats(){
        logs = new ArrayList<QuizStats.QuizLog>();
    }

    public Date getTimeTaken() {
        return timeTaken;
    }

    public void setTimeTaken(Date timeTaken) {
        this.timeTaken = timeTaken;
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

    public List<QuizLog> getLogs() {
        return logs;
    }

    public void setLogs(List<QuizLog> logs) {
        this.logs = logs;
    }

    public long getTime() {
        return time;
    }

    private void addCount(boolean correct){
        numOfQuestion ++;
        if (correct){
            numOfCorrectAnswer++;
        }
    }
    public void addStats(int quizNum, boolean correct, Quiz quiz, long time, int score){
        this.score = this.score + score;
        addCount(correct);
        this.time = this.time + time;
        logs.add(new QuizLog(quizNum, correct, quiz, time, score));
    }
    public List<QuizStats.QuizSummary> getStatByCategory(){
    	Map<String , QuizStats.QuizSummary> statsByCategory = new HashMap<String, QuizSummary>();
    	for (QuizLog log: logs){
    		String key = log.getKeyByCategory();
    		QuizSummary v;
    		if (statsByCategory.containsKey(key)){
    			v = statsByCategory.get(key);
    		} else {
    			v= new QuizSummary(log.grade,log.category,log.subCategory);
    		}
    		v.total = v.total + 1;
    		if (log.correct) v.correct = v.correct+1;
    		statsByCategory.put(key, v);
    	}
    	ArrayList<QuizSummary> l = new ArrayList<QuizStats.QuizSummary>(statsByCategory.values());
    	Collections.sort(l);
    	return l;
    	
    }
    public class QuizLog{
    	int quizNum,score,grade;
    	boolean correct;
    	String category;
    	String subCategory;
    	public QuizLog(int quizNum, boolean correct, Quiz quiz, long time, int score){
    		this.quizNum = quizNum;
    		this.correct = correct;
    		this.grade = quiz.lessonGrade;
    		this.category = quiz.lessonCategory;
    		this.subCategory = quiz.lessonSubcategory;
    	}
    	public String getKeyByCategory(){
    		return "G"+grade+"Cat"+category+"SubCat"+subCategory;
    	}
    }
    public class QuizSummary implements Comparator<QuizSummary>,Comparable<QuizSummary>{
    	int grade;
    	String category;
    	String subCategory;
    	int correct;
    	int total;
    	public QuizSummary(int grade, String category, String subCategory){
    		this.grade = grade;
    		this.category = category;
    		this.subCategory = subCategory;
    	}
    	public String getPercentage(){
    		Fraction f = new Fraction(correct, total);
    		return f.getPercentage();
    	}
		@Override
		public int compareTo(QuizSummary o) {
			return this.grade - o.grade;
		}
		@Override
		public int compare(QuizSummary o1, QuizSummary o2) {
			return o1.compareTo(o2);
		}
    }
}
