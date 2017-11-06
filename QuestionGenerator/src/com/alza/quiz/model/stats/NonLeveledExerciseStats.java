package com.alza.quiz.model.stats;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alza.common.math.Fraction;
import com.alza.quiz.model.Quiz;

/**
 * Created by ewin.sutriandi@gmail.com on 11/01/17.
 */

public class NonLeveledExerciseStats extends BasicExerciseStats implements Serializable{
	private static final long serialVersionUID = 7662137635312010916L;
	private List<QuizLog> logs;

	public NonLeveledExerciseStats(){
		logs = new ArrayList<NonLeveledExerciseStats.QuizLog>();
	}

	public List<QuizLog> getLogs() {
		return logs;
	}

	public void setLogs(List<QuizLog> logs) {
		this.logs = logs;
	}


	public void addStats(int quizNum, boolean correct, Quiz quiz, long time, int score){
		this.score = this.score + score;
		numOfCorrectAnswer++;
		timeElapsed = timeElapsed + time;
		logs.add(new QuizLog(quizNum, correct, quiz, time, score));
	}
	public List<NonLeveledExerciseStats.QuizSummary> getStatByCategory(){
		Map<String , NonLeveledExerciseStats.QuizSummary> statsByCategory = new HashMap<String, QuizSummary>();
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
		ArrayList<QuizSummary> l = new ArrayList<NonLeveledExerciseStats.QuizSummary>(statsByCategory.values());
		Collections.sort(l);
		return l;

	}
	public class QuizLog implements Serializable{
		private static final long serialVersionUID = 9019460154714191646L;
		public int quizNum,score,grade;
		public boolean correct;
		public String category;
		public String subCategory;
		public QuizLog(int quizNum, boolean correct, Quiz quiz, long time, int score){
			this.quizNum = quizNum;
			this.correct = correct;
			this.grade = quiz.getLessonGrade();
			this.category = quiz.getLessonCategory();
			this.subCategory = quiz.getLessonSubcategory();
		}
		public String getKeyByCategory(){
			return "G"+grade+"Cat"+category+"SubCat"+subCategory;
		}
	}
	public class QuizSummary implements Comparator<QuizSummary>,Comparable<QuizSummary>{
		public int grade;
		public String category;
		public String subCategory;
		public int correct;
		public int total;
		public QuizSummary(int grade, String category, String subCategory){
			this.grade = grade;
			this.category = category;
			this.subCategory = subCategory;
		}
		public String getPercentage(){
			Fraction f = new Fraction(correct, total);
			return f.getPercentageNoDecimal();
		}
		@Override
		public int compareTo(QuizSummary o) {
			if (this.grade != o.grade)
				return this.grade - o.grade;
			if (this.category != o.category)
				return this.category.compareTo(o.category);
			if (this.subCategory != o.subCategory)
				return this.subCategory.compareTo(o.subCategory);
			if (this.getPercentage() != o.getPercentage())
				return this.getPercentage().compareTo(o.getPercentage());
			return 0;
		}
		@Override
		public int compare(QuizSummary o1, QuizSummary o2) {
			return o1.compareTo(o2);
		}
	}

	public List<NonLeveledExerciseStats.QuizSummary> getStatByCategoryFromMultipleTest(List<NonLeveledExerciseStats> statses){
		List<QuizLog> logs = new ArrayList<NonLeveledExerciseStats.QuizLog>();
		for (NonLeveledExerciseStats stats : statses){
			logs.addAll(stats.getLogs());
		}
		Map<String , NonLeveledExerciseStats.QuizSummary> statsByCategory = new HashMap<String, QuizSummary>();
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
		ArrayList<QuizSummary> l = new ArrayList<NonLeveledExerciseStats.QuizSummary>(statsByCategory.values());
		Collections.sort(l);
		return l;
	}
}
