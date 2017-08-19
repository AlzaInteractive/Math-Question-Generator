package com.alza.quiz.model;

import java.util.ArrayList;
import java.util.List;

import com.alza.quiz.qfactory.IQuestionFactory;
/**
 * 
 * @author ewien
 * Class representing an in-game level 
 * with a collection of question generator 
 * and its respective share of question count
 * 
 */
public class GameLevel {
	private int order;
	private String name;
	private List<GameLevelQuestionFactory> levelQF;
		
	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<GameLevelQuestionFactory> getLevelQF() {
		return levelQF;
	}

	public void setLevelQF(List<GameLevelQuestionFactory> levelQF) {
		this.levelQF = levelQF;
	}

	public List<Quiz> generateQuiz(){
		List<Quiz> ql = new ArrayList<Quiz>();
		for (GameLevelQuestionFactory glqf : levelQF) {
			ql.addAll(glqf.getqFactory().generateQuizList(glqf.getqCount()));
		}
		return ql;
	}
	
	public class GameLevelQuestionFactory{
		private int qCount;//question count
		private IQuestionFactory qFactory;
		public int getqCount() {
			return qCount;
		}
		public void setqCount(int qCount) {
			this.qCount = qCount;
		}
		public IQuestionFactory getqFactory() {
			return qFactory;
		}
		public void setqFactory(IQuestionFactory qFactory) {
			this.qFactory = qFactory;
		}
		
	}
}
