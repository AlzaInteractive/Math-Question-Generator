package com.alza.quiz.qfactory;

import java.util.ArrayList;
import java.util.List;

import com.alza.quiz.qfactory.fraction.*;
import com.alza.quiz.qfactory.kpk.BasicKPKQuestionFactory;
import com.alza.quiz.qfactory.kpk.BasicScenarioKPKQuestionFactory;
import com.alza.quiz.qfactory.kpk.WhichDateScenarioKPKQuestionFactory;
import com.alza.quiz.qfactory.kpk.WhichDayScenarioKPKQuestionFactory;
import com.alza.quiz.qfactory.kpk.WhichHourScenarioKPKQuestionFactory;
import com.alza.quiz.qfactory.romans.RomanNumeralsQuestionFactory;

public class Util {
	public static List<IQuestionFactory> getAllFractionQuestionFactory(){
		List<IQuestionFactory> lqf = new ArrayList<IQuestionFactory>();
		lqf.add(new FractionEqualityQuestionFactory());
		lqf.add(new FindGreatestFractionQuestionFactory());
		lqf.add(new SimplifyFractionQuestionFactory());
		lqf.add(new FractionAddSubtractQuestionFactory());
		lqf.add(new FractionMultDivideQuestionFactory());
		lqf.add(new MixedFractionQuestionFactory());
		lqf.add(new FractionDecimalFormQuestionFactory());
		lqf.add(new FractionPercentageFormQuestionFactory());
		lqf.add(new ScenarioBasedFractionQuestionFactory());
		lqf.add(new ScaleQuestionFactory());
		lqf.add(new RatioQuestionFactory());
		return lqf;
	}
	public static List<IQuestionFactory> getAllKPKQuestionFactory(){
		List<IQuestionFactory> lqf = new ArrayList<IQuestionFactory>();
		lqf.add(new BasicKPKQuestionFactory());
		lqf.add(new BasicScenarioKPKQuestionFactory());
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
}
