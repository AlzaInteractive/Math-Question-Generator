package com.alza.quiz.model;

/**
 * Created by galuh on 24/12/16.
 */

public abstract class Quiz {
    QuizLevel quizLevel;
    String question;
    String correctAnswer;
    String lessonClassifier;
    String lessonCategory;
    String lessonSubcategory;

    public boolean isCorrect(Object answer){
        return false;
    }

    public QuizLevel getQuizLevel() {
        return quizLevel;
    }

    public String getQuestion() {
        return question;
    }
    public String getCorrectAnswer(){
        return correctAnswer;
    }

    public String getLessonClassifier() {
        return lessonClassifier;
    }

    public void setLessonClassifier(String lessonClassifier) {
        this.lessonClassifier = lessonClassifier;
    }

    public String getLessonCategory() {
        return lessonCategory;
    }

    public void setLessonCategory(String lessonCategory) {
        this.lessonCategory = lessonCategory;
    }

    public String getLessonSubcategory() {
        return lessonSubcategory;
    }

    public void setLessonSubcategory(String lessonSubcategory) {
        this.lessonSubcategory = lessonSubcategory;
    }
}
