package com.alza.quiz.qfactory.fraction;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import com.alza.quiz.model.MultipleChoiceQuiz;
import com.alza.quiz.model.Quiz;
import com.alza.quiz.model.QuizLevel;
import com.alza.quiz.qfactory.IQuestionFactory;
import com.alza.quiz.util.CommonFunctionAndValues;

public class FractionRealWorldProblemFindFromRatio implements IQuestionFactory{
	private static int numq = 6;
	List<String> sces;
	Locale loc;
	ResourceBundle bundle,scenarioBundle;
	private static final int PARAMLENGTH=12;
	public FractionRealWorldProblemFindFromRatio(Locale loc){
		this.loc = loc;
		initStringFromLocale();
	}
	public FractionRealWorldProblemFindFromRatio(){
		this.loc = new Locale("in", "ID");
		initStringFromLocale();
	}
	private void initStringFromLocale(){
		bundle = ResourceBundle.getBundle("lang.langbundle", loc);
		scenarioBundle = ResourceBundle.getBundle("lang.scenario", loc);
		sces = CommonFunctionAndValues.getStringCollection(scenarioBundle, "fraction.findfromratio");
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
			q.setLessonSubcategory(bundle.getString("fraction.findvalfromratio"));
			int rnd = CommonFunctionAndValues.getRandomInt(0, sces.size());
			String sce = getRandomScenario(rnd);
			String param =  getParams(rnd);
			// prepare lower and upper bound 0020082070, 002 lobo 008 hibo, 20 low pct, hi pct
			int loRat1, hiRat1;
			int loRat2, hiRat2;
			loRat1 = Integer.parseInt(param.substring(0, 3));
			hiRat1 = Integer.parseInt(param.substring(3, 6));
			loRat2 = Integer.parseInt(param.substring(6, 9));
			hiRat2 = Integer.parseInt(param.substring(9, 12));
			//calculate ratio r1 : r2
			int r1, r2;
			do {
				r1 = CommonFunctionAndValues.getRandomInt(loRat1, hiRat1+1);
				r2 = CommonFunctionAndValues.getRandomInt(loRat2, hiRat2+1);
			} while (false); 
			int multip = CommonFunctionAndValues.getRandomInt(2, 6);
			int rat2tot = multip * r2;
			String corrAns = String.valueOf(r1 * multip);
			sce = sce.replace("#rat1?", String.valueOf(r1));
			sce = sce.replace("#rat2?", String.valueOf(r2));
			sce = sce.replace("#rat2tot?", String.valueOf(rat2tot));
			sce = CommonFunctionAndValues.buildScenario(sce);
			q.setQuestion(sce);
			q.setCorrectAnswer(String.valueOf(corrAns));
			q.setChoices(r1,r2,r1*multip,Math.abs(r1-r2)*multip);
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
