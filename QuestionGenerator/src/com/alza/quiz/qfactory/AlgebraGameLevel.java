package com.alza.quiz.qfactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import com.alza.quiz.model.GameLevel;
import com.alza.quiz.qfactory.IPlayableLevelsGroup;
import com.alza.quiz.qfactory.algebra.Level0AddSub;
import com.alza.quiz.qfactory.algebra.Level0Mult;
import com.alza.quiz.qfactory.algebra.Level1AddSub;
import com.alza.quiz.qfactory.algebra.Level1Div;
import com.alza.quiz.qfactory.algebra.Level1Mult;
import com.alza.quiz.qfactory.algebra.Level2AddSub;
import com.alza.quiz.qfactory.algebra.Level2Div;
import com.alza.quiz.qfactory.algebra.Level2Mult;
import com.alza.quiz.qfactory.algebra.Level3MixedOperationDiv;
import com.alza.quiz.qfactory.algebra.Level3MixedOperationMult;
import com.alza.quiz.qfactory.algebra.Level4FractionXisNumerator;
import com.alza.quiz.qfactory.algebra.Level3MixedOperationA;
import com.alza.quiz.qfactory.algebra.Level3MixedOperationB;
import com.alza.quiz.qfactory.algebra.Level0Div;
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
public class AlgebraGameLevel implements IPlayableLevelsGroup{
	public List<GameLevel> createGameLevels(Locale loc) {
		ResourceBundle bundle = ResourceBundle.getBundle("lang.langbundle-algebra", loc);
		List<GameLevel> lgl = new ArrayList<GameLevel>();
		String name, desc;
		GameLevel g;

		// name = bundle.getString("algebra.level2.addsub");
		// desc = "";//bundle.getString("");
		// g = GameLevel.createSingleQF(0, name, desc, new Level2AddSub(loc), 10);
		// lgl.add(g);
		
		// name = bundle.getString("algebra.level1.mult");
		// desc = "";//bundle.getString("");
		// g = GameLevel.createSingleQF(0, name, desc, new Level2Mult(loc), 10);
		// lgl.add(g);
		
		// name = bundle.getString("algebra.level1.div");
		// desc = "";//bundle.getString("");
		// g = GameLevel.createSingleQF(0, name, desc, new Level2Div(loc), 10);
		// lgl.add(g);
		
		// name = bundle.getString("algebra.level3.mixop");
		// desc = "";//bundle.getString("");
		// g = GameLevel.createSingleQF(0, name, desc, new Level3MixedOperationDiv(loc), 10);
		// lgl.add(g);

		// name = bundle.getString("algebra.level.simplemultdiv");
		// desc = bundle.getString("lcmgcd.level.factor.desc");
		// g = GameLevel.createSingleQF(1, name, desc, new SimpleMultDiv(loc), 6);
		// lgl.add(g);
		
		name = bundle.getString("algebra.level3.mixop");
		desc = "";//bundle.getString("");
		g = GameLevel.createSingleQF(0, name, desc, new Level4FractionXisNumerator(loc), 5);
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
