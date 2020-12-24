package com.alza.quiz.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import com.alza.common.math.MathUtils;

/**
 * Created by galuh on 24/12/16.
 */

public abstract class Quiz implements Comparable<Quiz>, Comparator<Quiz>{
    QuizLevel quizLevel;
    Locale locale;
    int subCategoryOrder = 99;
    String question;
    String problemString;
    String correctAnswer;
    String lessonClassifier;
    String lessonCategory;
    String lessonSubcategory;
    List<String> hints = new ArrayList<>();
    int localGeneratorOrder;
    int lessonGrade;
    public boolean isCorrect(Object answer){
        return correctAnswer.equals(answer);
    }
    
    public String getQuizLevel() {
    	ResourceBundle bundle = ResourceBundle.getBundle("lang.langbundle", locale);
		switch (quizLevel) {
		case MUDAH:
			return bundle.getString("level.easy");
		case SEDANG:
			return bundle.getString("level.medium");
		case SULIT:
			return bundle.getString("level.hard");
		case GILA:
			return bundle.getString("level.insane");
		default:
			return "";
		}
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
    public void setDifficultyLevel(QuizLevel quizLevel) {
		this.quizLevel = quizLevel;
	}

    public String getLessonSubcategory() {
        return lessonSubcategory;
    }

    public void setLessonSubcategory(String lessonSubcategory) {
        this.lessonSubcategory = lessonSubcategory;
    }

	public int getSubCategoryOrder() {
		return subCategoryOrder;
	}

	public void setSubCategoryOrder(int subCategoryOrder) {
		this.subCategoryOrder = subCategoryOrder;
	}

	public int getLocalGeneratorOrder() {
		return localGeneratorOrder;
	}

	public void setLocalGeneratorOrder(int localGeneratorOrder) {
		this.localGeneratorOrder = localGeneratorOrder;
	}

	public int getLessonGrade() {
		return lessonGrade;
	}

	public void setLessonGrade(int lessonGrade) {
		this.lessonGrade = lessonGrade;
	}

	public Locale getLocale() {
		return locale;
	}

	public void setLocale(Locale loc) {
		this.locale = loc;
	}

	public void setQuizLevel(QuizLevel quizLevel) {
		this.quizLevel = quizLevel;
	}
	
	public void setQuestion(String question) {
		this.question = question;
	}

	public String getProblemString() {
		return problemString;
	}

	public void setProblemString(String problemString) {
		this.problemString = problemString;
	}

	public void setCorrectAnswer(String correctAnswer) {
		this.correctAnswer = correctAnswer;
	}
	
	public List<String> getHints() {
		return hints;
	}

	public void setHints(List<String> hints) {
		this.hints = hints;
	}
	
	public String getWolframSolverURL() {
		return MathUtils.getWolframAlphaSolverURL(this.problemString);
	}
	
	public String getSymbolabsSolverURL() {
		return MathUtils.getSymbolabsSolverURL(this.problemString);
	}
	
	public String getMicrosoftMathSolverURL() {
		return MathUtils.getMicrosoftMathSolverURL(this.problemString);
	}
	
	public String getMicrosoftMathSolverURL(Locale loc) {
		return MathUtils.getMicrosoftMathSolverURL(this.problemString,loc);
	}

	public int compareTo(Quiz o){
		if (this.lessonGrade!=o.lessonGrade){
			return this.lessonGrade - o.lessonGrade;
		} else if (this.quizLevel!=o.quizLevel) {
			return this.quizLevel.compareTo(o.quizLevel);
		} else if (this.subCategoryOrder!=o.subCategoryOrder){
			return this.subCategoryOrder - o.subCategoryOrder;
		} else if (this.localGeneratorOrder != o.localGeneratorOrder){
			return this.localGeneratorOrder - o.localGeneratorOrder;
		}
		return 0;
	}
	
	public int compare(Quiz o1, Quiz o2){
		return o1.compareTo(o2);
	}
	
	
}
