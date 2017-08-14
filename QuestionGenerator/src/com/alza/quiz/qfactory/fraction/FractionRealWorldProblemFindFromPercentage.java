package com.alza.quiz.qfactory.fraction;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import com.alza.common.math.Fraction;
import com.alza.quiz.model.MultipleChoiceQuiz;
import com.alza.quiz.model.Quiz;
import com.alza.quiz.model.QuizLevel;
import com.alza.quiz.qfactory.IQuestionFactory;
import com.alza.quiz.util.CommonFunctionAndValues;

public class FractionRealWorldProblemFindFromPercentage implements IQuestionFactory{
	private static int numq = 6;
	List<String> sces;
	Locale loc;
	ResourceBundle bundle,scenarioBundle;
	private static final int PARAMLENGTH=10;
	public FractionRealWorldProblemFindFromPercentage(Locale loc){
		this.loc = loc;
		initStringFromLocale();
	}
	public FractionRealWorldProblemFindFromPercentage(){
		this.loc = new Locale("in", "ID");
		initStringFromLocale();
	}
	private void initStringFromLocale(){
		bundle = ResourceBundle.getBundle("lang.langbundle", loc);
		scenarioBundle = ResourceBundle.getBundle("lang.scenario", loc);
		sces = CommonFunctionAndValues.getStringCollection(scenarioBundle, "fraction.findfrompct");
	}

	@Override
	public Quiz generateQuiz() {
		return generateQuiz();
	}

	@Override
	public Quiz generateQuiz(QuizLevel quizLevel) {
		List<Quiz> ql = generateQuizList();
		int rnd = CommonFunctionAndValues.getRandomInt(0, ql.size());
		return ql.get(rnd);
	}

	@Override
	public List<Quiz> generateQuizList() {
		List<Quiz> ql = new ArrayList<Quiz>();
		for (int i=0;i<numq;i++){
			MultipleChoiceQuiz q = new MultipleChoiceQuiz();
			q.setDifficultyLevel(QuizLevel.SEDANG);
			q.setLessonSubcategory(bundle.getString("fraction.findvalifpctandsumknown"));
			int rnd = CommonFunctionAndValues.getRandomInt(0, sces.size());
			String sce = getRandomScenario(rnd);
			String param =  getParams(rnd);
			Fraction rat1;
			// prepare lower and upper bound 0020082070, 002 lobo 008 hibo, 20 low pct, hi pct
			double loT, hiT;
			int loPct, hiPct;
			loT = Integer.parseInt(param.substring(0, 3));
			hiT = Integer.parseInt(param.substring(3, 6));
			loPct = Integer.parseInt(param.substring(6, 8));
			hiPct = Integer.parseInt(param.substring(8, 10));
			//calculate total
			int total, tot1, tot2;
			total = CommonFunctionAndValues.getRandomInt((int)loT,(int)hiT);
			//calculate total for item 1, ensure it came between pct bound
			do {
				tot1 = CommonFunctionAndValues.getRandomInt(0, total);
				rat1 = new Fraction(tot1, total);  
			} while (!rat1.isBetween(new Fraction(loPct, 100), new Fraction(hiPct, 100))); 
			tot2 = total - tot1;
			String corrAns = String.valueOf(tot2);
			Fraction f = new Fraction(tot1, total);
			String pct = f.getPercentageNoDecimal();
			sce = sce.replace("#total?", String.valueOf(total));
			sce = sce.replace("#pct1?", pct);
			sce = CommonFunctionAndValues.buildScenario(sce);
			q.setQuestion(sce);
			q.setCorrectAnswer(String.valueOf(corrAns));
			q.setChoices(total,tot2,tot1,Math.abs(tot1-tot2));
			ql.add(q);
		}	
		return ql;
	}

	private String getParams(int rnd) {
		String s = sces.get(rnd);
		String params = s.substring(s.length()-PARAMLENGTH);
		return params;
	}
	public String getRandomScenario(int rnd){
		String s = sces.get(rnd);
		String sce = s.substring(0,s.length()-11);
		return sce;
	}
	@Override
	public List<Quiz> generateQuizList(int numOfQuestion) {
		numq = numOfQuestion;
		return generateQuizList();
	}
}
