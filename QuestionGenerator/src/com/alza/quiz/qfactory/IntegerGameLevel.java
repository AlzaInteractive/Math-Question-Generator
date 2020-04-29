package com.alza.quiz.qfactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import com.alza.quiz.model.GameLevel;
import com.alza.quiz.qfactory.IPlayableLevelsGroup;
import com.alza.quiz.qfactory.integer.AdditionOfThreeIntegersSigned;
import com.alza.quiz.qfactory.integer.AdditionOfThreeIntegersUnsigned;
import com.alza.quiz.qfactory.integer.AdditionOfTwoIntegers;
import com.alza.quiz.qfactory.integer.CubeRoot;
import com.alza.quiz.qfactory.integer.CubicOperation;
import com.alza.quiz.qfactory.integer.DivisionOfTwoIntegers;
import com.alza.quiz.qfactory.integer.Estimation;
import com.alza.quiz.qfactory.integer.MixedOperationOfIntegers;
import com.alza.quiz.qfactory.integer.MultiplicationOfTwoIntegers;
import com.alza.quiz.qfactory.integer.QuadraticOperation;
import com.alza.quiz.qfactory.integer.Rounding;
import com.alza.quiz.qfactory.integer.SquareRoot;
import com.alza.quiz.qfactory.integer.SquareRootPhytagorean;
import com.alza.quiz.qfactory.integer.SubtractionOfTwoIntegers;

/**
 * 
 * @author ewien Game level factory for lcmgcd problems
 *
 */
public class IntegerGameLevel implements IPlayableLevelsGroup{
	public List<GameLevel> createGameLevels(Locale loc) {
		ResourceBundle bundle = ResourceBundle.getBundle("lang.langbundle", loc);
		List<GameLevel> lgl = new ArrayList<GameLevel>();
		String name, desc;
		GameLevel g;

		name = bundle.getString("integer.addtwonum");
		desc = "";//bundle.getString("");
		g = GameLevel.createSingleQF(0, name, desc, new AdditionOfTwoIntegers(loc), 5);
		lgl.add(g);

		name = bundle.getString("integer.subtwonum");
		//desc = bundle.getString("lcmgcd.level.factor.desc");
		g = GameLevel.createSingleQF(1, name, desc, new SubtractionOfTwoIntegers(loc), 5);
		lgl.add(g);


		name = bundle.getString("integer.addthreenum");
		//desc = bundle.getString("lcmgcd.level.gcd.desc");
		g = GameLevel.createSingleQF(2, name, desc, new AdditionOfThreeIntegersSigned(loc), 5);
		lgl.add(g);
		
		name = bundle.getString("integer.addthreenumu");
		//desc = bundle.getString("lcmgcd.level.lcm.desc");
		g = GameLevel.createSingleQF(3, name, desc, new AdditionOfThreeIntegersUnsigned(loc), 5);
		lgl.add(g);

		name = bundle.getString("integer.multiptwonum");
		//desc = bundle.getString("lcmgcd.level.primefactor.desc");
		g = GameLevel.createSingleQF(4, name, desc, new MultiplicationOfTwoIntegers(loc), 7);

		lgl.add(g);

		name = bundle.getString("integer.divtwonum");
		//desc = bundle.getString("lcmgcd.level.primefactor.lcm.desc");
		g = GameLevel.createSingleQF(5, name, desc, new DivisionOfTwoIntegers(loc), 7);
		lgl.add(g);

		name = bundle.getString("integer.mixop");
		//desc = bundle.getString("lcmgcd.level.primefactor.gcd.desc");
		g = GameLevel.createSingleQF(6, name, desc, new MixedOperationOfIntegers(loc), 8);
		//todo rhombus find diagonal
		lgl.add(g);

		name = bundle.getString("integer.quadsquare");
		//desc = bundle.getString("lcmgcd.level.lcmrwp.desc");
		g = GameLevel.createSingleQF(7, name, desc, new QuadraticOperation(loc), 8);
		g.addQuestionFactory(new SquareRoot(loc),3 );
		lgl.add(g);

		name = bundle.getString("integer.squarerootphyt");
		//desc = bundle.getString("lcmgcd.level.primefactor.gcd.desc");
		g = GameLevel.createSingleQF(8, name, desc, new SquareRootPhytagorean(loc), 6);

		lgl.add(g);

		name = bundle.getString("integer.cubic");
		//desc = bundle.getString("lcmgcd.level.gcdrwp.desc");
		g = GameLevel.createSingleQF(9, name, desc, new CubicOperation(loc), 6);
		g.addQuestionFactory(new CubeRoot(loc),3 );
		lgl.add(g);

		name = bundle.getString("integer.rounding");
		g = GameLevel.createSingleQF(10, name, desc, new Rounding(loc), 6);

		lgl.add(g);		

		name = bundle.getString("integer.estimation");
		//desc = bundle.getString("lcmgcd.level.gcdrwp.desc");
		g = GameLevel.createSingleQF(11, name, desc, new Estimation(loc), 6);

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
		GameLevel g = GameLevel.createSingleQF(0, name, desc, new AdditionOfTwoIntegers(loc), 1);
		g.addQuestionFactory(new SubtractionOfTwoIntegers(loc), 1);
		g.addQuestionFactory(new AdditionOfThreeIntegersUnsigned(loc), 1);
		g.addQuestionFactory(new AdditionOfThreeIntegersSigned(loc), 1);
		g.addQuestionFactory(new MultiplicationOfTwoIntegers(loc), 1);
		g.addQuestionFactory(new DivisionOfTwoIntegers(loc), 1);
		g.addQuestionFactory(new MixedOperationOfIntegers(loc), 1);
		g.addQuestionFactory(new QuadraticOperation(loc), 1);
		g.addQuestionFactory(new SquareRoot(loc), 1);
		g.addQuestionFactory(new SquareRootPhytagorean(loc), 1);
		g.addQuestionFactory(new CubicOperation(loc), 1);
		g.addQuestionFactory(new CubeRoot(loc), 1);
		g.addQuestionFactory(new Rounding(loc), 1);
		g.addQuestionFactory(new Estimation(loc), 1);
		return g;
	}

}
