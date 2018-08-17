package com.alza.quiz.qfactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import com.alza.quiz.model.GameLevel;
import com.alza.quiz.qfactory.lcmgcd.FindFactorsOfQuestionFactory;
import com.alza.quiz.qfactory.lcmgcd.FindMultiplesOfQuestionFactory;
import com.alza.quiz.qfactory.lcmgcd.FindPrimeFactorsOfQuestionFactory;
import com.alza.quiz.qfactory.lcmgcd.GCDBasicScenarioQuestionFactory;
import com.alza.quiz.qfactory.lcmgcd.GCDPrimeFactorQuestionFactory;
import com.alza.quiz.qfactory.lcmgcd.GCDThreeNumQuestionFactory;
import com.alza.quiz.qfactory.lcmgcd.GCDTwoNumQuestionFactory;
import com.alza.quiz.qfactory.lcmgcd.LCMBasicScenarioQuestionFactory;
import com.alza.quiz.qfactory.lcmgcd.LCMPrimeFactorQuestionFactory;
import com.alza.quiz.qfactory.lcmgcd.LCMThreeNumQuestionFactory;
import com.alza.quiz.qfactory.lcmgcd.LCMTwoNumQuestionFactory;
import com.alza.quiz.qfactory.lcmgcd.LCMWhichDateRWPQuestionFactory;
import com.alza.quiz.qfactory.lcmgcd.LCMWhichDayRWPQuestionFactory;
import com.alza.quiz.qfactory.lcmgcd.LCMWhichHourRWPQuestionFactory;

/**
 * 
 * @author ewien Game level factory for lcmgcd problems
 *
 */
public class LCMGCDGameLevel implements IPlayableLevelsGroup{
	public List<GameLevel> createGameLevels(Locale loc) {
		ResourceBundle bundle = ResourceBundle.getBundle("lang.langbundle", loc);

		List<GameLevel> lgl = new ArrayList<GameLevel>();
		String name, desc;
		GameLevel g;

		// find multiples of
		name = bundle.getString("lcmgcd.level.multiple");
		desc = bundle.getString("lcmgcd.level.multiple.desc");
		g = GameLevel.createSingleQF(0, name, desc, new FindMultiplesOfQuestionFactory(loc), 5);
		lgl.add(g);

		// find factors of
		name = bundle.getString("lcmgcd.level.factor");
		desc = bundle.getString("lcmgcd.level.factor.desc");
		g = GameLevel.createSingleQF(1, name, desc, new FindFactorsOfQuestionFactory(loc), 5);
		lgl.add(g);

		// LCM
		name = bundle.getString("lcmgcd.level.lcm");
		desc = bundle.getString("lcmgcd.level.lcm.desc");
		g = GameLevel.createSingleQF(2, name, desc, new LCMTwoNumQuestionFactory(loc), 3);
		g.addQuestionFactory(new LCMThreeNumQuestionFactory(loc), 3);
		lgl.add(g);

		// GCD
		name = bundle.getString("lcmgcd.level.gcd");
		desc = bundle.getString("lcmgcd.level.gcd.desc");
		g = GameLevel.createSingleQF(3, name, desc, new GCDTwoNumQuestionFactory(loc), 3);
		g.addQuestionFactory(new GCDThreeNumQuestionFactory(loc), 3);
		lgl.add(g);

		// Prime factor
		name = bundle.getString("lcmgcd.level.primefactor");
		desc = bundle.getString("lcmgcd.level.primefactor.desc");
		g = GameLevel.createSingleQF(4, name, desc, new FindPrimeFactorsOfQuestionFactory(loc), 5);
		
		lgl.add(g);

		// LCM using prime factor
		name = bundle.getString("lcmgcd.level.primefactor.lcm");
		desc = bundle.getString("lcmgcd.level.primefactor.lcm.desc");
		g = GameLevel.createSingleQF(5, name, desc, new LCMPrimeFactorQuestionFactory(loc), 5);
		lgl.add(g);

		// GCD using prime factor
		name = bundle.getString("lcmgcd.level.primefactor.gcd");
		desc = bundle.getString("lcmgcd.level.primefactor.gcd.desc");
		g = GameLevel.createSingleQF(6, name, desc, new GCDPrimeFactorQuestionFactory(loc), 5);
		//todo rhombus find diagonal
		lgl.add(g);

		// LCM Real world problems
		name = bundle.getString("lcmgcd.level.lcmrwp");
		desc = bundle.getString("lcmgcd.level.lcmrwp.desc");
		g = GameLevel.createSingleQF(7, name, desc, new LCMBasicScenarioQuestionFactory(loc), 2);
		g.addQuestionFactory(new LCMWhichDayRWPQuestionFactory(loc), 2);
		g.addQuestionFactory(new LCMWhichHourRWPQuestionFactory(loc), 2);
		g.addQuestionFactory(new LCMWhichDateRWPQuestionFactory(loc), 2);
		lgl.add(g);

		//Circle
		name = bundle.getString("lcmgcd.level.gcdrwp");
		desc = bundle.getString("lcmgcd.level.gcdrwp.desc");
		g = GameLevel.createSingleQF(8, name, desc, new GCDBasicScenarioQuestionFactory(loc), 6);
		lgl.add(g);
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
	
	public GameLevel getExamLevel(Locale loc){
		ResourceBundle	bundle = ResourceBundle.getBundle("lang.langbundle", loc);
		String name,desc;
		name = bundle.getString("exam");
		desc = bundle.getString("examdesc");
		GameLevel g = GameLevel.createSingleQF(0, name, desc, new LCMTwoNumQuestionFactory(loc), 1);
		g.addQuestionFactory(new LCMThreeNumQuestionFactory(loc), 1);
		g.addQuestionFactory(new LCMPrimeFactorQuestionFactory(loc), 1);
		g.addQuestionFactory(new GCDTwoNumQuestionFactory(loc), 1);
		g.addQuestionFactory(new GCDThreeNumQuestionFactory(loc), 1);
		g.addQuestionFactory(new GCDPrimeFactorQuestionFactory(loc), 1);
		g.addQuestionFactory(new LCMBasicScenarioQuestionFactory(loc), 1);
		g.addQuestionFactory(new LCMWhichDayRWPQuestionFactory(loc), 1);
		g.addQuestionFactory(new LCMWhichDateRWPQuestionFactory(loc), 1);
		g.addQuestionFactory(new LCMWhichHourRWPQuestionFactory(loc), 1);
		g.addQuestionFactory(new GCDBasicScenarioQuestionFactory(loc), 4);
		return g;
	}

}
