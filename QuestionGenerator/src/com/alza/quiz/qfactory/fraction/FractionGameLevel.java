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
		String name,desc;
		GameLevel g;
		//fraction representation
		name = bundle.getString("fraction.representation");
		desc = bundle.getString("fraction.representationdesc");
		g = GameLevel.createSingleQF(0, name, desc, new FractionRepresentation(loc), 5);
		lgl.add(g);
		//fraction simplest fraction
		name = bundle.getString("fraction.simplify");
		desc = bundle.getString("fraction.simplifydesc");
		g = GameLevel.createSingleQF(1, name, desc, new FractionSimplify(loc), 7);
		lgl.add(g);
		//fraction equality
		name = bundle.getString("fraction.equality");
		desc = bundle.getString("fraction.equalitydesc");
		g = GameLevel.createSingleQF(2, name,desc, new FractionEqualityTypeA(loc), 7);
		g.addQuestionFactory(new FractionEqualityTypeB(loc), 3);
		lgl.add(g);
		//fraction compare simple form
		name = bundle.getString("fraction.compare");
		desc = bundle.getString("fraction.comparedesc");
		g = GameLevel.createSingleQF(3, name, desc, new FractionCompareTwo(loc), 7);
		//g.addQuestionFactory(new FractionPickGreatest(), 3);
		lgl.add(g);
		//fraction addition and subtraction
		name = bundle.getString("fraction.addsubtract");
		desc = bundle.getString("fraction.addsubtractdesc");
		g = GameLevel.createSingleQF(4, name, desc, new FractionAddSubtract(loc), 7);
		lgl.add(g);		
		//fraction multiplication
		name = bundle.getString("fraction.multiplication");
		desc = bundle.getString("fraction.multiplicationdesc");
		g = GameLevel.createSingleQF(5, name, desc, new FractionMultiplication(loc), 7);
		lgl.add(g);
		//fraction division
		name = bundle.getString("fraction.division");
		desc = bundle.getString("fraction.divisiondesc");
		g = GameLevel.createSingleQF(6, name, desc, new FractionDivide(loc), 7);
		lgl.add(g);
		//decimal form
		name = bundle.getString("fraction.decimalform");
		desc = bundle.getString("fraction.decimalformdesc");
		g = GameLevel.createSingleQF(7, name, desc, new FractionConvertToDecimal(loc), 4);
		g.addQuestionFactory(new FractionConvertFromDecimal(loc), 4);
		lgl.add(g);
		//percentage form
		name = bundle.getString("fraction.percentageform");
		desc = bundle.getString("fraction.percentageformdesc");
		g = GameLevel.createSingleQF(8, name, desc, new FractionConvertToPercentage(loc), 4);
		g.addQuestionFactory(new FractionConvertFromPercentage(loc), 4);
		lgl.add(g);
		//mixed number form
		name = bundle.getString("fraction.mixednumber");
		desc = bundle.getString("fraction.mixednumberdesc");
		g = GameLevel.createSingleQF(9, name, desc, new FractionMixedNumberOperation(loc), 7);
		lgl.add(g);
		//compare various form
		name = bundle.getString("fraction.compareforms");
		desc = bundle.getString("fraction.compareformsdesc");
		g = GameLevel.createSingleQF(10, name, desc, new FractionPickGreatest(loc), 7);
		lgl.add(g);
		//Real world problems
		name = bundle.getString("fraction.rwp");
		desc = bundle.getString("fraction.rwpdesc");
		g = GameLevel.createSingleQF(11, name, desc,new FractionRealWorldProblemDescribingRatio(loc), 2);
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
