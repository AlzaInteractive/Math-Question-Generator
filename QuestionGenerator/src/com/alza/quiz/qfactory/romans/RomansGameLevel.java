package com.alza.quiz.qfactory.romans;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import com.alza.quiz.model.GameLevel;
import com.alza.quiz.qfactory.IPlayableLevelsGroup;

/**
 * 
 * @author ewien Game level factory for roman number conversion
 *
 */
public class RomansGameLevel implements IPlayableLevelsGroup {
	int[][] bounds = {
			{1,50,7},{50,100,7},
			{100,500,7},{500,1000,7},{1000,2000,7},{2000,3999,7}
	};
	public List<GameLevel> createGameLevels(Locale loc) {
		ResourceBundle bundle = ResourceBundle.getBundle("lang.langbundle", loc);
		List<GameLevel> lgl = new ArrayList<GameLevel>();
		String name, desc;
		GameLevel g;
		for (int i = 0; i < bounds.length; i++) {
			int lowBound = bounds[i][0];
			int hiBound = bounds[i][1];
			int numQ = bounds[i][2];
			name = bundle.getString("romans.category")+": "+lowBound+"-"+hiBound;
			desc = bundle.getString("romans.category")+": "+lowBound+"-"+hiBound;
			g = GameLevel.createSingleQF(i, name, desc, new RomanNumeralsQuestionFactory(loc,lowBound,hiBound), numQ);
			lgl.add(g);
		}
		return lgl;

	}
	public GameLevel getGameLevel(int order, Locale loc){
		List<GameLevel> levels = createGameLevels(loc);
		for (GameLevel gameLevel : levels) {
			if (gameLevel.getOrder()==order) {
				return gameLevel;
			}
		}
		return null;
	}
	@Override
	public GameLevel getExamLevel(Locale loc) {
		ResourceBundle	bundle = ResourceBundle.getBundle("lang.langbundle", loc);
		String name,desc;
		name = bundle.getString("exam");
		desc = bundle.getString("examdesc");
		GameLevel g = new GameLevel();
		g.setName(name);
		g.setDesc(desc);
		for (int i = 0; i < bounds.length; i++) {
			int lowBound = bounds[i][0];
			int hiBound = bounds[i][1];
			g.addQuestionFactory(new RomanNumeralsQuestionFactory(loc,lowBound,hiBound),2);
		}
		return g;
	}
}
