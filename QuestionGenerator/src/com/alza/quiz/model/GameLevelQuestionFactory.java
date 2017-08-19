package com.alza.quiz.model;

import com.alza.quiz.qfactory.IQuestionFactory;

public class GameLevelQuestionFactory{
	private int qCount;//question count
	private IQuestionFactory qFactory;
	
	public GameLevelQuestionFactory(int qCount, IQuestionFactory qFactory) {
		super();
		this.qCount = qCount;
		this.qFactory = qFactory;
	}
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
