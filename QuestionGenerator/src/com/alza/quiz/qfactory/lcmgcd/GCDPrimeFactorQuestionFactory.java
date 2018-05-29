package com.alza.quiz.qfactory.lcmgcd;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.concurrent.ThreadLocalRandom;

import com.alza.common.math.MathUtils;
import com.alza.quiz.model.MultipleChoiceQuiz;
import com.alza.quiz.model.Quiz;
import com.alza.quiz.model.QuizLevel;
import com.alza.quiz.qfactory.IQuestionFactory;
import com.alza.quiz.util.StringUtils;

public class GCDPrimeFactorQuestionFactory implements IQuestionFactory{
	private static int numq = 2;
	
	Locale loc;
	ResourceBundle bundle;
	
	public GCDPrimeFactorQuestionFactory(){
		this.loc = new Locale("in", "ID");
		initStringFromLocale();
	}
	
	public GCDPrimeFactorQuestionFactory(Locale loc) {
		this.loc = loc;
		initStringFromLocale();
	}
	
	private void initStringFromLocale(){
		bundle = ResourceBundle.getBundle("lang.langbundle", loc);
	}
	
	@Override
	public Quiz generateQuiz() {
		List<Quiz> quizList = generateQuizList();
		int rnd = new Random().nextInt(quizList.size()); 
		return quizList.get(rnd);
	}

	@Override
	public Quiz generateQuiz(QuizLevel quizLevel) {
		return generateQuiz();
	}

	@Override
	public List<Quiz> generateQuizList() {
		List<Quiz> lq = new ArrayList<Quiz>();
		for (int i=0;i<numq;i++){
			int val1,val2,gcd,lcm;
			int loBo = 200;
			int hiBo = 1000;
			do {
				val1 = ThreadLocalRandom.current().nextInt(loBo, hiBo);
				val2 = ThreadLocalRandom.current().nextInt(loBo, hiBo);
				gcd = MathUtils.findGCD(val1,val2);
				lcm = MathUtils.findLCM(val1, val2);
			} while (val1==val2 || gcd<3);
			MultipleChoiceQuiz q = new MultipleChoiceQuiz();
			String question = bundle.getString("lcmgcd.question.gcdprimefactor");
			String prf1,prf2;
			prf1 = StringUtils.join(MathUtils.findPrimeFactors(val1), "x");
			prf2 = StringUtils.join(MathUtils.findPrimeFactors(val2), "x");
			question = question.replace("#val1?", String.valueOf(val1));
			question = question.replace("#val2?", String.valueOf(val2));
			question = question.replace("#primeF1?", String.valueOf(prf1));
			question = question.replace("#primeF2?", String.valueOf(prf2));
			//q.setQuestion("FPB dari bilangan " + val1 + " dan " + val2 + " adalah?");
			q.setQuestion(question);
			q.setCorrectAnswer(StringUtils.join(MathUtils.findPrimeFactors(gcd), "x"));
			q.addChoice(StringUtils.join(MathUtils.findPrimeFactors(lcm), "x"));
			q.addChoice(StringUtils.join(MathUtils.findPrimeFactors(val1+val2), "x"));
			q.addChoice(StringUtils.join(MathUtils.findPrimeFactors(gcd), "x"));
						
			q.setDifficultyLevel(QuizLevel.MUDAH);
			q.setLessonCategory(bundle.getString("lcmgcd"));
			q.setLessonSubcategory(bundle.getString("lcmgcd.subcategory.gcd"));
			q.setLessonClassifier(bundle.getString("mathelementary"));
			q.setLessonGrade(4);
			q.setSubCategoryOrder(2);
			lq.add(q);
		}
		return lq;
	}
	@Override
	public List<Quiz> generateQuizList(int numOfQuestion) {
		numq = numOfQuestion;
		return generateQuizList();
	}
}
