package com.alza.quiz.qfactory.fraction;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import com.alza.common.math.Fraction;
import com.alza.common.math.MathUtils;
import com.alza.quiz.model.MultipleChoiceQuiz;
import com.alza.quiz.model.Quiz;
import com.alza.quiz.model.QuizLevel;
import com.alza.quiz.qfactory.IQuestionFactory;
import com.alza.quiz.util.CommonFunctionAndValues;

public class FractionRealWorldProblemProportions implements IQuestionFactory{
	private static int numq = 6;
	List<String> sces;
	Locale loc;
	ResourceBundle bundle,scenarioBundle;
	private static final int PARAMLENGTH=8;
	public FractionRealWorldProblemProportions(Locale loc){
		this.loc = loc;
		initStringFromLocale();
	}
	public FractionRealWorldProblemProportions(){
		this.loc = new Locale("in", "ID");
		initStringFromLocale();
	}
	private void initStringFromLocale(){
		bundle = ResourceBundle.getBundle("lang.langbundle", loc);
		scenarioBundle = ResourceBundle.getBundle("lang.scenario-fraction", loc);
		sces = CommonFunctionAndValues.getStringCollection(scenarioBundle, "fraction.proportion");
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
			Quiz q = createSingleQuestion();						
			setSecondaryAttributes(q);								
			ql.add(q);
		}	
		return ql;
	}
	private Quiz createSingleQuestion() {
		MultipleChoiceQuiz q = new MultipleChoiceQuiz();
		int rnd = CommonFunctionAndValues.getRandomInt(0, sces.size());
		String sce = getRandomScenario(rnd);
		String param =  getParams(rnd);
		// prepare lower and upper bound 0020082070, 002 lobo 008 hibo, 20 low pct, hi pct
		int loTot, hiTot;
		int leftVal, rightVal;
		int tot,limit,gcd;
		loTot = Integer.parseInt(param.substring(0, 3));
		hiTot = Integer.parseInt(param.substring(3, 6));
		limit = Integer.parseInt(param.substring(6, 8));		
		do {
			tot = CommonFunctionAndValues.getRandomInt(loTot, hiTot+1);
			leftVal = CommonFunctionAndValues.getRandomInt(tot*limit/100, tot * (100-limit)/100);
			rightVal = tot - leftVal;
			gcd = MathUtils.findGCD(leftVal,rightVal);
		} while (gcd<2);
		
		String corrAns = String.valueOf(rightVal);
		sce = sce.replace("#tot?", String.valueOf(tot));
		Fraction f1 = new Fraction(leftVal,tot);
		sce = sce.replace("#frac?", String.valueOf(f1.getSimplestForm().toMathJaxString()));
		sce = CommonFunctionAndValues.buildScenario(sce);
		q.setQuestion(sce);
		q.setCorrectAnswer(corrAns);
		q.setChoices(corrAns,String.valueOf(leftVal),String.valueOf(Math.abs(rightVal-leftVal)));
		return q;
	}
	private void setSecondaryAttributes(Quiz q) {
		q.setLessonClassifier(bundle.getString("mathelementary"));
		q.setLessonGrade(5);
		q.setLessonCategory(bundle.getString("fraction"));
		q.setLessonSubcategory(bundle.getString("fraction.proportion"));
		q.setDifficultyLevel(QuizLevel.MUDAH);
		q.setLocale(loc);
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
