package com.alza.quiz.qfactory.lcmgcd;

import java.util.ArrayList;
import java.util.Collections;
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
import com.alza.quiz.util.CommonFunctionAndValues;

public class GCDBasicScenarioQuestionFactory implements IQuestionFactory{
	List<String> sces;
	Locale loc;
	ResourceBundle bundle,scenarioBundle;
	private int numq=8;
	private static final int PARAMLENGTH=6;
	
	public GCDBasicScenarioQuestionFactory(Locale loc){
		this.loc = loc;
		initStringFromLocale();
	}
	public GCDBasicScenarioQuestionFactory(){
		this.loc = new Locale("in", "ID");
		initStringFromLocale();
	}
	private void initStringFromLocale(){
		bundle = ResourceBundle.getBundle("lang.langbundle", loc);
		scenarioBundle = ResourceBundle.getBundle("lang.scenario-gcd", loc);
		sces = CommonFunctionAndValues.getStringCollection(scenarioBundle, "gcd.basic");
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
		Collections.shuffle(sces);
		for (int i=1;i<numq;i++){
			String question = getRandomScenario(i);
			String param =  getParams(i);
			
			int loBo,hiBo,numVal,minGCD;
			loBo = Integer.parseInt(param.substring(0, 2));
			hiBo = Integer.parseInt(param.substring(2, 4));
			numVal = Integer.parseInt(param.substring(4, 5));
			minGCD = Integer.parseInt(param.substring(5, 6));
			int gcd,lcm;
			int[] vals = new int[numVal];
			do {
				for (int j=0 ; j < vals.length; j++){
					vals[j] = ThreadLocalRandom.current().nextInt(loBo, hiBo);
				}
				gcd = MathUtils.findGCD(vals);
			} while (gcd < minGCD ||
					vals[0]==vals[1]);
			
			lcm = MathUtils.findLCM(vals);
			
			question = question.replace("#val1?", String.valueOf(vals[0]));
			question = question.replace("#val2?", String.valueOf(vals[1]));
			if (vals.length>=3) question = question.replace("#val3?", String.valueOf(vals[2]));
			if (vals.length>=4) question = question.replace("#val4?", String.valueOf(vals[3]));
			if (vals.length>=5) question = question.replace("#val5?", String.valueOf(vals[4]));
			question = CommonFunctionAndValues.buildScenario(question);
			
			MultipleChoiceQuiz q = new MultipleChoiceQuiz();
			q.setQuestion(question);
			q.setCorrectAnswer(String.valueOf(gcd));
			q.addChoice(vals);
			q.addChoice(lcm);
			q.addChoice(String.valueOf(gcd));
			
			q.setDifficultyLevel(QuizLevel.SEDANG);
			q.setLessonCategory(bundle.getString("lcmgcd"));
			q.setLessonSubcategory(bundle.getString("lcmgcd.subcategory.gcd"));
			q.setLessonClassifier(bundle.getString("mathelementary"));
			q.setLessonGrade(5);
			q.setSubCategoryOrder(5);
			lq.add(q);
		}
		return lq;
	}
	
	@Override
	public List<Quiz> generateQuizList(int numOfQuestion) {
		numq = numOfQuestion;
		return generateQuizList();
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

}
