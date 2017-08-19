package com.alza.quiz.qfactory.fraction;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import com.alza.quiz.model.GameLevel;
/**
 * 
 * @author ewien
 * Game level factory for fraction problems
 *
 */
public class FractionGameLevel {
	public static List<GameLevel> createGameLevels(Locale loc) {
		ResourceBundle	bundle = ResourceBundle.getBundle("lang.langbundle", loc);
		
		List<GameLevel> lgl = new ArrayList<GameLevel>();
		String name;
		GameLevel g;
		//fraction representation
		name = bundle.getString("fraction.representation");
		g = GameLevel.createSingleQF(0, name, new FractionRepresentation(loc), 5);
		lgl.add(g);
		//fraction simplest fraction
		name = bundle.getString("fraction.simplify");
		g = GameLevel.createSingleQF(1, name, new FractionSimplify(loc), 7);
		lgl.add(g);
		//fraction equality
		name = bundle.getString("fraction.equality");
		g = GameLevel.createSingleQF(2, name, new FractionEqualityTypeA(loc), 7);
		g.addQuestionFactory(new FractionEqualityTypeB(loc), 3);
		lgl.add(g);
		//fraction compare simple form
		name = bundle.getString("fraction.compare");
		g = GameLevel.createSingleQF(3, name, new FractionCompareTwo(loc), 4);
		//g.addQuestionFactory(new FractionPickGreatest(), 3);
		lgl.add(g);
		//fraction addition and subtraction
		name = bundle.getString("fraction.addsubtract");
		g = GameLevel.createSingleQF(4, name, new FractionAddSubtract(loc), 7);
		lgl.add(g);		
		//fraction multiplication
		name = bundle.getString("fraction.multiplication");
		g = GameLevel.createSingleQF(5, name, new FractionMultiplication(loc), 7);
		lgl.add(g);
		//fraction division
		name = bundle.getString("fraction.division");
		g = GameLevel.createSingleQF(6, name, new FractionDivide(loc), 7);
		lgl.add(g);
		//decimal form
		name = bundle.getString("fraction.decimalform");
		g = GameLevel.createSingleQF(7, name, new FractionConvertToDecimal(loc), 4);
		g.addQuestionFactory(new FractionConvertFromDecimal(loc), 4);
		lgl.add(g);
		//percentage form
		name = bundle.getString("fraction.percentageform");
		g = GameLevel.createSingleQF(8, name, new FractionConvertToPercentage(loc), 4);
		g.addQuestionFactory(new FractionConvertFromPercentage(loc), 4);
		lgl.add(g);
		//mixed number form
		name = bundle.getString("fraction.mixednumber");
		g = GameLevel.createSingleQF(9, name, new FractionMixedNumberOperation(loc), 7);
		lgl.add(g);
		//compare various form
		name = bundle.getString("fraction.compareforms");
		g = GameLevel.createSingleQF(10, name, new FractionPickGreatest(loc), 7);
		lgl.add(g);
		//Real world problems
		name = bundle.getString("fraction.rwp");
		g = GameLevel.createSingleQF(11, name, new FractionRealWorldProblemDescribingRatio(loc), 2);
		g.addQuestionFactory(new FractionRealWorldProblemFindFromRatio(loc), 1);
		g.addQuestionFactory(new FractionRealWorldProblemFindFromPercentage(loc), 2);
		g.addQuestionFactory(new FractionRealWorldProblemLeftover(loc), 1);
		g.addQuestionFactory(new FractionRealWorldProblemProportions(loc), 2);
		g.addQuestionFactory(new FractionRealWorldProblemScale(loc), 2);
		g.addQuestionFactory(new FractionRealWorldProblemDiscount(loc), 1);
		g.addQuestionFactory(new FractionRealWorldProblemTaxTips(loc), 1);
		lgl.add(g);
		return lgl;
	}
	
}
