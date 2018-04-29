package com.alza.quiz.qfactory.romans;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import com.alza.quiz.model.GameLevel;

/**
 * 
 * @author ewien Game level factory for roman number conversion
 *
 */
public class RomansGameLevel {
	public static List<GameLevel> createGameLevels(Locale loc) {
		ResourceBundle bundle = ResourceBundle.getBundle("lang.langbundle", loc);

		List<GameLevel> lgl = new ArrayList<GameLevel>();
		String name, desc;
		GameLevel g;
		
		int[][] bounds = {
				{1,5},{5,10},{10,15},{15,20},
				{20,40},{40,50},{50,90},{90,100},
				{100,400},{400,500},{500,900},{900,1000},
				{1000,1400},{1400,1500},{1500,1900},
				{1900,2000},{2000,3000}
		};
		
		for (int i = 0; i < bounds.length; i++) {
			int lowBound = bounds[i][0];
			int hiBound = bounds[i][1];
			name = bundle.getString("romans.category")+": "+lowBound+"-"+hiBound;
			desc = bundle.getString("romans.category")+": "+lowBound+"-"+hiBound;
			g = GameLevel.createSingleQF(0, name, desc, new RomanNumeralsQuestionFactory(loc,lowBound,hiBound), 8);
			lgl.add(g);
		}
		
		return lgl;

	}
	public static GameLevel getGameLevel(int order, Locale loc){
		List<GameLevel> levels = createGameLevels(loc);
		for (GameLevel gameLevel : levels) {
			if (gameLevel.getOrder()==order) {
				return gameLevel;
			}
		}
		return null;
	}

}
