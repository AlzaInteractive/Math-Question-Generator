package com.alza.quiz.qfactory.fraction;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import com.alza.common.math.MathUtils;
import com.alza.quiz.model.MultipleChoiceQuiz;
import com.alza.quiz.model.Quiz;
import com.alza.quiz.model.QuizLevel;
import com.alza.quiz.qfactory.IQuestionFactory;
import com.alza.quiz.util.CommonFunctionAndValues;

public class FractionRealWorldProblemDescribingRatio implements IQuestionFactory{
	private static int numq = 6;
	List<String> sces;
	Locale loc;
	ResourceBundle bundle,scenarioBundle;
	private static final int PARAMLENGTH=8;
	public FractionRealWorldProblemDescribingRatio(Locale loc){
		this.loc = loc;
		initStringFromLocale();
	}
	public FractionRealWorldProblemDescribingRatio(){
		this.loc = new Locale("in", "ID");
		initStringFromLocale();
	}
	private void initStringFromLocale(){
		bundle = ResourceBundle.getBundle("lang.langbundle", loc);
		scenarioBundle = ResourceBundle.getBundle("lang.scenario-fraction", loc);
		sces = CommonFunctionAndValues.getStringCollection(scenarioBundle, "fraction.descratio");
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
			q.setLessonSubcategory(bundle.getString("fraction.describeratio"));
			q.setDifficultyLevel(QuizLevel.MUDAH);
			
						
			int rnd = CommonFunctionAndValues.getRandomInt(0, sces.size());
			String sce = getRandomScenario(rnd);
			String param =  getParams(rnd);
			// prepare lower and upper bound 0020082070, 002 lobo 008 hibo, 20 low pct, hi pct
			int loTot, hiTot;
			int leftVal, rightVal, leftRat,rightRat;
			int tot,limit,gcd;
			loTot = Integer.parseInt(param.substring(0, 3));
			hiTot = Integer.parseInt(param.substring(3, 6));
			limit = Integer.parseInt(param.substring(6, 8));
			
			do {
				tot = CommonFunctionAndValues.getRandomInt(loTot, hiTot+1);
				leftVal = CommonFunctionAndValues.getRandomInt(tot*limit/100, tot * (100-limit)/100);
				rightVal = tot - leftVal;
				gcd = MathUtils.findGCD(leftVal,rightVal);
			} while (gcd<2||leftVal==rightVal);
			leftRat = leftVal / gcd;
			rightRat = rightVal / gcd;
			
			String corrAns = leftRat+":"+rightRat;
			sce = sce.replace("#total?", String.valueOf(tot));
			sce = sce.replace("#pop1?", String.valueOf(leftVal));
			sce = sce.replace("#pop2?", String.valueOf(rightVal));
			sce = CommonFunctionAndValues.buildScenario(sce);
			q.setQuestion(sce);
			q.setCorrectAnswer(corrAns);
			q.setChoices(corrAns,
					leftVal+":"+rightVal,
					rightVal+":"+leftVal,
					rightRat+":"+leftRat,
					rightRat+":"+rightVal);
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
