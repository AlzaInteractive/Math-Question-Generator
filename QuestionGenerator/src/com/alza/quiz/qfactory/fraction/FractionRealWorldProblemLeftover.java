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
import com.alza.quiz.model.SimpleQuiz;
import com.alza.quiz.qfactory.IQuestionFactory;
import com.alza.quiz.util.CommonFunctionAndValues;

public class FractionRealWorldProblemLeftover implements IQuestionFactory{
	private static int numq = 6;
	List<String> sces;
	Locale loc;
	ResourceBundle bundle,scenarioBundle;
	private static final int PARAMLENGTH=0;
	public FractionRealWorldProblemLeftover(Locale loc){
		this.loc = loc;
		initStringFromLocale();
	}
	public FractionRealWorldProblemLeftover(){
		this.loc = new Locale("in", "ID");
		initStringFromLocale();
	}
	private void initStringFromLocale(){
		bundle = ResourceBundle.getBundle("lang.langbundle", loc);
		scenarioBundle = ResourceBundle.getBundle("lang.scenario", loc);
		sces = CommonFunctionAndValues.getStringCollection(scenarioBundle, "fraction.leftover");
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
			q.setLessonSubcategory(bundle.getString("fraction.rwp"));
			q.setDifficultyLevel(QuizLevel.MUDAH);
			
						
			int rnd = CommonFunctionAndValues.getRandomInt(0, sces.size());
			String sce = getRandomScenario(rnd);
			// prepare lower and upper bound 0020082070, 002 lobo 008 hibo, 20 low pct, hi pct
			int denom1,denom2,denom3, gcd;
			Fraction frac1,frac2,frac3,tot, leftover;
			
			do {
				denom1 = CommonFunctionAndValues.getRandomInt(2,11);
				denom2 = CommonFunctionAndValues.getRandomInt(2,11);
				denom3 = CommonFunctionAndValues.getRandomInt(2,11);
				frac1 = new Fraction(1, denom1);
				frac2 = new Fraction(1, denom2);
				frac3 = new Fraction(1, denom3);
				tot = frac1.getResultWhenAddedWith(frac2).getResultWhenAddedWith(frac3);
				gcd = MathUtils.findGCD(denom1,denom2,denom3);
				System.out.println("gcd "+gcd);
			} while (gcd<2||!tot.isLessThan(new Fraction(1, 1)));
			
			leftover = new Fraction(1, 1).getResultWhenSubtractWith(tot);
			
			String corrAns = leftover.toString();
			sce = sce.replace("#frac1?", frac1.toMathJaxString());
			sce = sce.replace("#frac2?", frac2.toMathJaxString());
			sce = sce.replace("#frac3?", frac3.toMathJaxString());
			sce = CommonFunctionAndValues.buildScenario(sce);
			//sce = CommonFunctionAndValues.enclosedWithMathJaxExp(sce);
			q.setQuestion(sce);
			q.setCorrectAnswer(corrAns);
			q.setChoices(buildChoices(frac1, frac2, frac3, leftover));
			q.setLocale(loc);
			ql.add(q);
		}	
		return ql;
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
	private List<String> buildChoices(Fraction f1, Fraction f2, Fraction f3, Fraction lo){
		List<String> cs = new ArrayList<String>();
		Fraction one = new Fraction(1, 1);
		cs.add(lo.toString());
		cs.add(one.getResultWhenSubtractWith(f1).toString());
		cs.add(one.getResultWhenSubtractWith(f2).toString());
		cs.add(one.getResultWhenSubtractWith(f3).toString());
		cs.add(f1.getResultWhenAddedWith(f2).getResultWhenAddedWith(f3).toString());
		return cs;
	}
}
