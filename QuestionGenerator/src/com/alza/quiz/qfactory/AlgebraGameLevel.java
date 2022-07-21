package com.alza.quiz.qfactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import com.alza.quiz.model.GameLevel;
import com.alza.quiz.qfactory.algebra.Level1AddSub;
import com.alza.quiz.qfactory.algebra.Level1Div;
import com.alza.quiz.qfactory.algebra.Level1Mult;
import com.alza.quiz.qfactory.algebra.Level2AddSub;
import com.alza.quiz.qfactory.algebra.Level2Div;
import com.alza.quiz.qfactory.algebra.Level2Mult;
import com.alza.quiz.qfactory.algebra.Level3MixedOperationA;
import com.alza.quiz.qfactory.algebra.Level5SimpleQuadraticCombo;
import com.alza.quiz.qfactory.algebra.Level5SimpleQuadraticNegativePlainUgly;
import com.alza.quiz.qfactory.algebra.Level5SimpleQuadraticPlain;
import com.alza.quiz.qfactory.algebra.Level5SimpleQuadraticPlainNegative;
import com.alza.quiz.qfactory.algebra.Level5SimpleQuadraticPlainUgly;
import com.alza.quiz.qfactory.algebra.Level5SimpleQuadraticWithCoeff;
import com.alza.quiz.qfactory.algebra.Level5SimpleQuadraticWithCoeffConst;
import com.alza.quiz.qfactory.algebra.Level5SimpleQuadraticWithCoeffDiv;
import com.alza.quiz.qfactory.algebra.Level5SimpleQuadraticWithConstDiv;
import com.alza.quiz.qfactory.algebra.Level5SimpleQuadraticWithConstant;
import com.alza.quiz.qfactory.algebra.Level5SimpleQuadraticWithConstantZeroOnRight;
import com.alza.quiz.qfactory.algebra.Level5SimpleQuadraticWithDivisor;
import com.alza.quiz.qfactory.algebra.Level6QuadraticFactorANotOne;
import com.alza.quiz.qfactory.algebra.Level6QuadraticFactorAOne;
import com.alza.quiz.qfactory.algebra.Level6QuadraticUseFactoring;
import com.alza.quiz.qfactory.algebra.Level6QuadraticFactorizeALargerThanOne;
import com.alza.quiz.qfactory.algebra.Level6QuadraticFactorizeCombo;
import com.alza.quiz.qfactory.algebra.Level6QuadraticFactorizeNegativeCoeff;
import com.alza.quiz.qfactory.algebra.Level6QuadraticSolveByFactoringANotOne;
import com.alza.quiz.qfactory.algebra.Level6QuadraticSolveByFactoringAOne;
import com.alza.quiz.qfactory.algebra.Level7QuadraticUglyNumberSimple;
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
 * @author ewien Game level factory for algebra problems
 *
 */
public class AlgebraGameLevel implements IPlayableLevelsGroup{
	public List<GameLevel> createGameLevels(Locale loc) {
		ResourceBundle bundle = ResourceBundle.getBundle("lang.langbundle-algebra", loc);
		List<GameLevel> lgl = new ArrayList<GameLevel>();
		String name, desc;
		GameLevel g;
		g = GameLevel.createSingleQF(0, "Exp", new Level1AddSub(loc), 0);
		//g.addQuestionFactory(new Level1Mult(loc), 4);
		//g.addQuestionFactory(new Level1Div(loc), 4);
		//g.addQuestionFactory(new Level2AddSub(loc), 4);
		//g.addQuestionFactory(new Level2Div(loc), 4);
		//g.addQuestionFactory(new Level2Mult(loc), 4);
		g.addQuestionFactory(new Level3MixedOperationA(loc), 8);
		/*		
		name = bundle.getString("algebra.level3.mixop");
		desc = "";//bundle.getString("");
		// Level 5.1
		g = GameLevel.createSingleQF(0, name, desc, new Level5SimpleQuadraticPlain(loc), 0);
		
		 
		g.addQuestionFactory(new Level5SimpleQuadraticPlainNegative(loc), 1);
		g.addQuestionFactory(new Level5SimpleQuadraticPlainUgly(loc), 1);
		g.addQuestionFactory(new Level5SimpleQuadraticNegativePlainUgly(), 1);
		
		//level 5.2
		g.addQuestionFactory(new Level5SimpleQuadraticWithConstant(loc), 2);
		g.addQuestionFactory(new Level5SimpleQuadraticWithConstantZeroOnRight(loc), 1);		
		g.addQuestionFactory(new Level5SimpleQuadraticWithCoeff(loc), 1);
		g.addQuestionFactory(new Level5SimpleQuadraticWithDivisor(loc), 1);

		//level 5.3 		
		g.addQuestionFactory(new Level5SimpleQuadraticWithCoeffConst(loc), 2);
		g.addQuestionFactory(new Level5SimpleQuadraticWithConstDiv(loc), 1);
		g.addQuestionFactory(new Level5SimpleQuadraticWithCoeffDiv(loc), 1);		
		g.addQuestionFactory(new Level5SimpleQuadraticCombo(loc), 1);
				
		//Level 6.1				
						
		g.addQuestionFactory(new Level6QuadraticFactorAOne(loc), 3);
		g.addQuestionFactory(new Level6QuadraticFactorANotOne(loc), 3);
		
		// Level 6.2
		
		g.addQuestionFactory(new Level6QuadraticSolveByFactoringAOne(loc), 3);							
		g.addQuestionFactory(new Level6QuadraticFactorizeNegativeCoeff(loc), 2);
		
		// level 6.3
						
		g.addQuestionFactory(new Level6QuadraticFactorizeCombo(loc), 3);		
		g.addQuestionFactory(new Level6QuadraticSolveByFactoringANotOne(loc), 2);
		
		
		//Level 7
		g.addQuestionFactory(new Level7QuadraticUglyNumberSimple(loc), 4);		
		*/
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
