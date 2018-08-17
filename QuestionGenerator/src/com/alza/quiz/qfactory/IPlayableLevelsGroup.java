package com.alza.quiz.qfactory;

import java.util.List;
import java.util.Locale;

import com.alza.quiz.model.GameLevel;

public interface IPlayableLevelsGroup {
	public List<GameLevel> createGameLevels(Locale loc);
	public GameLevel getExamLevel(Locale loc);
	public GameLevel getGameLevel(int order, Locale loc);
}
