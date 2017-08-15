package com.alza.quiz.qfactory.fraction;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import com.alza.quiz.model.Quiz;
import com.alza.quiz.model.QuizLevel;
import com.alza.quiz.model.SimpleQuiz;
import com.alza.quiz.qfactory.IQuestionFactory;
import com.alza.quiz.util.CommonFunctionAndValues;

public class FractionRealWorldProblemScale implements IQuestionFactory{
	private static int numq = 6;
	List<String> sces;
	Locale loc;
	ResourceBundle bundle,scenarioBundle;
	private static final int PARAMLENGTH=22;
	public FractionRealWorldProblemScale(Locale loc){
		this.loc = loc;
		initStringFromLocale();
	}
	public FractionRealWorldProblemScale(){
		this.loc = new Locale("in", "ID");
		initStringFromLocale();
	}
	private void initStringFromLocale(){
		bundle = ResourceBundle.getBundle("lang.langbundle", loc);
		scenarioBundle = ResourceBundle.getBundle("lang.scenario", loc);
		sces = CommonFunctionAndValues.getStringCollection(scenarioBundle, "fraction.rwpscale");
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
			SimpleQuiz q = new SimpleQuiz();
			
			q.setLessonClassifier(bundle.getString("mathelementary"));
			q.setLessonGrade(5);
			q.setLessonCategory(bundle.getString("fraction"));
			q.setLessonSubcategory(bundle.getString("fraction.findactfromscale"));
			q.setDifficultyLevel(QuizLevel.SEDANG);
			
			int rnd = CommonFunctionAndValues.getRandomInt(0, sces.size());
			String sce = getRandomScenario(rnd);
			String param =  getParams(rnd);
			// prepare lower and upper bound 0020082070, 002 lobo 008 hibo, 20 low pct, hi pct
			int loVal, hiVal, multip, scale;
			double loLen, hiLen, modLen, reaLen;
			int corr;
			loVal = Integer.parseInt(param.substring(0, 6));
			hiVal = Integer.parseInt(param.substring(6, 12));
			loLen = Integer.parseInt(param.substring(12, 14));
			hiLen = Integer.parseInt(param.substring(14, 16));
			corr = Integer.parseInt(param.substring(16, 22)); // correction caused by conversion in unit of measure
			
			do {
				multip = CommonFunctionAndValues.getRandomInt(2,  (hiVal/loVal)+1);
				modLen = CommonFunctionAndValues.getRandomInt((int)loLen, (int) (hiLen+1));
			} while (false); 
			scale = multip * loVal;
			reaLen = modLen * scale / corr;
			String corrAns = CommonFunctionAndValues.DF2PLC.format(reaLen);
			sce = sce.replace("#modlength?", String.valueOf(modLen));
			sce = sce.replace("#scale?", String.valueOf(scale));
			sce = CommonFunctionAndValues.buildScenario(sce);
			q.setQuestion(sce);
			q.setCorrectAnswer(corrAns);
			/**
			q.setChoices(corrAns,
					CommonFunctionAndValues.DF2PLC.format(reaLen/10),
					CommonFunctionAndValues.DF2PLC.format(reaLen*10),
					CommonFunctionAndValues.DF2PLC.format(reaLen*multip),
					CommonFunctionAndValues.DF2PLC.format(reaLen/multip));
					*/
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
