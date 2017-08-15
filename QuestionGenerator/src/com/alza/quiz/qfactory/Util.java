package com.alza.quiz.qfactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.alza.quiz.qfactory.fraction.*;
import com.alza.quiz.qfactory.geom.WhichGeom2DShapeQuestionFactory;
import com.alza.quiz.qfactory.geom.WhichGeom3DShapeQuestionFactory;
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
import com.alza.quiz.qfactory.kpk.BasicGCDQuestionFactory;
import com.alza.quiz.qfactory.kpk.BasicGCDScenarioQuestionFactory;
import com.alza.quiz.qfactory.kpk.FindFactorsOfQuestionFactory;
import com.alza.quiz.qfactory.kpk.FindMultipleQuestionFactory;
import com.alza.quiz.qfactory.kpk.TripeNumKPKQuestionFactory;
import com.alza.quiz.qfactory.kpk.TwoNumKPKQuestionFactory;
import com.alza.quiz.qfactory.kpk.BasicScenarioKPKQuestionFactory;
import com.alza.quiz.qfactory.kpk.WhichDateScenarioKPKQuestionFactory;
import com.alza.quiz.qfactory.kpk.WhichDayScenarioKPKQuestionFactory;
import com.alza.quiz.qfactory.kpk.WhichHourScenarioKPKQuestionFactory;
import com.alza.quiz.qfactory.romans.RomanNumeralsQuestionFactory;

public class Util {
	public static List<IQuestionFactory> getAllFractionQuestionFactory(){
		List<IQuestionFactory> lqf = new ArrayList<IQuestionFactory>();
		lqf.add(new FractionEqualityTypeB());
		lqf.add(new FractionPickGreatest());
		lqf.add(new FractionSimplify());
		lqf.add(new FractionAddSubtract());
		lqf.add(new FractionMultiplication());
		lqf.add(new FractionMixedFormOperation());
		lqf.add(new FractionConvertToDecimal());
		lqf.add(new FractionConvertToPercentage());
		lqf.add(new ScenarioBasedFractionQuestionFactory());
		lqf.add(new ScaleQuestionFactory());
		//lqf.add(new RatioQuestionFactory());
		return lqf;
	}
	public static List<IQuestionFactory> getAllLCMGCDQuestionFactory(){
		List<IQuestionFactory> lqf = new ArrayList<IQuestionFactory>();
		lqf.add(new FindMultipleQuestionFactory());
		lqf.add(new FindFactorsOfQuestionFactory());
		lqf.add(new TwoNumKPKQuestionFactory());
		lqf.add(new TripeNumKPKQuestionFactory());
		lqf.add(new BasicGCDQuestionFactory());
		lqf.add(new BasicScenarioKPKQuestionFactory());
		lqf.add(new BasicGCDScenarioQuestionFactory());
		lqf.add(new WhichDayScenarioKPKQuestionFactory());
		lqf.add(new WhichHourScenarioKPKQuestionFactory());
		lqf.add(new WhichDateScenarioKPKQuestionFactory());
		return lqf;
	}
	public static List<IQuestionFactory> getAllRomansQuestionFactory(){
		List<IQuestionFactory> lqf = new ArrayList<IQuestionFactory>();
		lqf.add(new RomanNumeralsQuestionFactory());
		return lqf;
	}
	
	public static List<IQuestionFactory> getAllGeometryQuestionFactory(Locale loc){
		List<IQuestionFactory> lqf = new ArrayList<IQuestionFactory>();
		lqf.add(new WhichGeom2DShapeQuestionFactory(loc));
		lqf.add(new WhichGeom3DShapeQuestionFactory(loc));
		return lqf;
	}
	public static List<IQuestionFactory> getAllGeometryQuestionFactory(){
		Locale loc = new Locale("in", "ID");
		return getAllGeometryQuestionFactory(loc);
	}
	public static List<IQuestionFactory> getAllIntegerQuestionFactory(Locale loc){
		System.out.println("get All Integer qfac :");
		List<IQuestionFactory> lqf = new ArrayList<IQuestionFactory>();
		lqf.add(new AdditionOfTwoIntegers(loc));
		lqf.add(new SubtractionOfTwoIntegers(loc));
		lqf.add(new AdditionOfThreeIntegersUnsigned(loc));
		lqf.add(new AdditionOfThreeIntegersSigned(loc));
		lqf.add(new MultiplicationOfTwoIntegers(loc));
		lqf.add(new DivisionOfTwoIntegers(loc));
		lqf.add(new MixedOperationOfIntegers(loc));
		lqf.add(new QuadraticOperation(loc));
		lqf.add(new SquareRoot(loc));
		lqf.add(new SquareRootPhytagorean(loc));
		lqf.add(new CubicOperation(loc));
		lqf.add(new CubeRoot(loc));
		lqf.add(new Rounding(loc));
		lqf.add(new Estimation(loc));
		return lqf;
	}
	public static List<IQuestionFactory> getAllIntegerQuestionFactory(){
		Locale loc = new Locale("in", "ID");
		return getAllIntegerQuestionFactory(loc);
	}
}
