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

public class FractionRealWorldProblemTaxTips implements IQuestionFactory{
	private static int numq = 6;
	List<String> sces;
	Locale loc;
	ResourceBundle bundle,scenarioBundle;
	private static final int PARAMLENGTH=12;
	public FractionRealWorldProblemTaxTips(Locale loc){
		this.loc = loc;
		initStringFromLocale();
	}
	public FractionRealWorldProblemTaxTips(){
		this.loc = new Locale("in", "ID");
		initStringFromLocale();
	}
	private void initStringFromLocale(){
		bundle = ResourceBundle.getBundle("lang.langbundle", loc);
		scenarioBundle = ResourceBundle.getBundle("lang.scenario", loc);
		sces = CommonFunctionAndValues.getStringCollection(scenarioBundle, "fraction.rwptax");
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
			
			q.setLessonClassifier(bundle.getString("mathelementary"));
			q.setLessonGrade(5);
			q.setLessonCategory(bundle.getString("fraction"));
			q.setLessonSubcategory(bundle.getString("fraction.findtaxprice"));
			q.setDifficultyLevel(QuizLevel.SEDANG);
			
			int rnd = CommonFunctionAndValues.getRandomInt(0, sces.size());
			String sce = getRandomScenario(rnd);
			String param =  getParams(rnd);
			// prepare lower and upper bound 0020082070, 002 lobo 008 hibo, 20 low pct, hi pct
			int loVal, hiVal;
			int loPct, hiPct;
			loVal = Integer.parseInt(param.substring(0, 4));
			hiVal = Integer.parseInt(param.substring(4, 8));
			loPct = Integer.parseInt(param.substring(8, 10));
			hiPct = Integer.parseInt(param.substring(10, 12));
			//calculate ratio r1 : r2
			double orival, taxPct;
			do {
				orival = CommonFunctionAndValues.getRandomInt(loVal, hiVal+1);
				taxPct = CommonFunctionAndValues.getRandomInt(loPct, hiPct+1);
			} while (false); 
			double delta = orival * taxPct / 100;
			double newVal = orival + delta;
			double wrongval = orival - delta;
			double wrongval2 = orival + (delta/2);
			String valDelta = new Fraction((int)taxPct,100).getPercentageNoDecimal();
			String corrAns = CommonFunctionAndValues.DF2PLC.format(newVal);
			sce = sce.replace("#orival?", String.valueOf(orival));
			sce = sce.replace("#taxpct?", String.valueOf(valDelta));
			sce = CommonFunctionAndValues.buildScenario(sce);
			q.setQuestion(sce);
			q.setCorrectAnswer(corrAns);
			q.setChoices(corrAns,
					CommonFunctionAndValues.DF2PLC.format(orival),
					CommonFunctionAndValues.DF2PLC.format(wrongval),
					CommonFunctionAndValues.DF2PLC.format(wrongval2),
					CommonFunctionAndValues.DF2PLC.format(delta));
			ql.add(q);
			q.setLocale(loc);
		}	
		return ql;
	}

	private String getParams(int rnd) {
		String s = sces.get(rnd);
		String params = s.substring(s.length()-PARAMLENGTH);
		//System.out.println(params);
		return params;
	}
	public String getRandomScenario(int rnd){
		String s = sces.get(rnd);
		String sce = s.substring(0,s.length()-(PARAMLENGTH));
		return sce;
	}
	@Override
	public List<Quiz> generateQuizList(int numOfQuestion) {
		numq = numOfQuestion;
		return generateQuizList();
	}
}
