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

public class FractionRealWorldProblemDiscount implements IQuestionFactory{
	private static int numq = 6;
	List<String> sces;
	Locale loc;
	ResourceBundle bundle,scenarioBundle;
	private static final int PARAMLENGTH=10;
	public FractionRealWorldProblemDiscount(Locale loc){
		this.loc = loc;
		initStringFromLocale();
	}
	public FractionRealWorldProblemDiscount(){
		this.loc = new Locale("in", "ID");
		initStringFromLocale();
	}
	private void initStringFromLocale(){
		bundle = ResourceBundle.getBundle("lang.langbundle", loc);
		scenarioBundle = ResourceBundle.getBundle("lang.scenario", loc);
		sces = CommonFunctionAndValues.getStringCollection(scenarioBundle, "fraction.rwpdisc");
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
			q.setLessonSubcategory(bundle.getString("fraction.finddiscprice"));
			q.setDifficultyLevel(QuizLevel.SEDANG);
			
						
			int rnd = CommonFunctionAndValues.getRandomInt(0, sces.size());
			String sce = getRandomScenario(rnd);
			String param =  getParams(rnd);
			// prepare lower and upper bound 0020082070, 002 lobo 008 hibo, 20 low pct, hi pct
			int loPrice, hiPrice;
			int loDisc, hiDisc;
			loPrice = Integer.parseInt(param.substring(0, 3));
			hiPrice = Integer.parseInt(param.substring(3, 6));
			loDisc = Integer.parseInt(param.substring(6, 8));
			hiDisc = Integer.parseInt(param.substring(8, 10));
			//calculate ratio r1 : r2
			double oriprice, disc;
			do {
				oriprice = CommonFunctionAndValues.getRandomInt(loPrice, hiPrice+1);
				disc = CommonFunctionAndValues.getRandomInt(loDisc, hiDisc+1);
			} while (false); 
			double deltaPrice = oriprice * (double) disc / 100;
			double newPrice = oriprice - deltaPrice;
			double wrongPrice = oriprice + deltaPrice;
			double wrongval2 = oriprice - (deltaPrice/2);
			double wrongval3 = oriprice - (deltaPrice/3);
			String discPct = new Fraction((int)disc,100).getPercentageNoDecimal();
			String corrAns = CommonFunctionAndValues.DF2PLC.format(newPrice);
			sce = sce.replace("#oriprice?", String.valueOf(oriprice));
			sce = sce.replace("#pctdisc?", String.valueOf(discPct));
			sce = CommonFunctionAndValues.buildScenario(sce);
			q.setQuestion(sce);
			q.setCorrectAnswer(corrAns);
			q.setChoices(corrAns,
					CommonFunctionAndValues.DF2PLC.format(wrongval3),
					CommonFunctionAndValues.DF2PLC.format(wrongPrice),
					CommonFunctionAndValues.DF2PLC.format(wrongval2),
					CommonFunctionAndValues.DF2PLC.format(deltaPrice));
			q.setLocale(loc);
			ql.add(q);
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
