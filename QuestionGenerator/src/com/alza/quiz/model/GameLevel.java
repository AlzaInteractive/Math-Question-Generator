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
	private String desc;
	private List<GameLevelQuestionFactory> levelQF = new ArrayList<GameLevelQuestionFactory>();
	
	public static GameLevel createSingleQF(int order, String name, IQuestionFactory qf, int questionCount) {
		GameLevel gl = new GameLevel();
		gl.setOrder(order);
		gl.setName(name);
		gl.getLevelQF().add(new GameLevelQuestionFactory(questionCount, qf));
		return gl;
	}
	public static GameLevel createSingleQF(int order, String name, String desc, IQuestionFactory qf, int questionCount) {
		GameLevel gl = new GameLevel();
		gl.setOrder(order);
		gl.setName(name);
		gl.setDesc(desc);
		gl.getLevelQF().add(new GameLevelQuestionFactory(questionCount, qf));
		return gl;
	}
		
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
	
	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public List<GameLevelQuestionFactory> getLevelQF() {
		return levelQF;
	}

	public void setLevelQF(List<GameLevelQuestionFactory> levelQF) {
		this.levelQF = levelQF;
	}
	
	public void addQuestionFactory(GameLevelQuestionFactory glqf){
		levelQF.add(glqf);
	}
	public void addQuestionFactory(IQuestionFactory qf, int qCount){
		levelQF.add(new GameLevelQuestionFactory(qCount, qf));
	}

	public List<Quiz> generateQuiz(){
		List<Quiz> ql = new ArrayList<Quiz>();
		for (GameLevelQuestionFactory glqf : levelQF) {
			ql.addAll(glqf.getqFactory().generateQuizList(glqf.getqCount()));
		}
		return ql;
	}
	
	
}
