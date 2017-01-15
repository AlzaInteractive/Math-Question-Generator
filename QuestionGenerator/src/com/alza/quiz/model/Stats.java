package com.alza.quiz.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by ewin.sutriandi@gmail.com on 11/01/17.
 */

public class Stats implements Serializable{
    private Date timeTaken = new Date();
    private long time;
    private int score=0;
    private int numOfQuestion=0;
    private int numOfCorrectAnswer=0;
    private String[] logs;

    public Stats(int size){
        logs = new String[size];
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

    public String[] getLogs() {
        return logs;
    }

    public void setLogs(String[] logs) {
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
        String status = "SALAH";
        if (correct){
            status = "BENAR";
        }
        String log = quizNum + " " + status + " tipe soal: "+
                quiz.getLessonSubcategory() + " waktu: "+time+ " detik";
        logs[quizNum-1] = log;
    }
}
