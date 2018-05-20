package com.alza.quiz.qfactory.lcmgcd;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import com.alza.common.math.MathUtils;
import com.alza.quiz.model.MultipleChoiceQuiz;
import com.alza.quiz.model.Quiz;
import com.alza.quiz.model.QuizLevel;
import com.alza.quiz.util.CommonFunctionAndValues;

/**
 * Created by ewin.sutriandi@gmail.com on 24/12/16.
 */

public class BasicScenarioKPKQuestionFactory extends TwoNumKPKQuestionFactory {
    List<String> sces;
	Locale loc;
	ResourceBundle bundle,scenarioBundle;
	private static final int PARAMLENGTH=5;
	
	public BasicScenarioKPKQuestionFactory(Locale loc){
		this.loc = loc;
		initStringFromLocale();
	}
	public BasicScenarioKPKQuestionFactory(){
		this.loc = new Locale("in", "ID");
		initStringFromLocale();
	}
	private void initStringFromLocale(){
		bundle = ResourceBundle.getBundle("lang.langbundle", loc);
		scenarioBundle = ResourceBundle.getBundle("lang.scenario-lcm", loc);
		sces = CommonFunctionAndValues.getStringCollection(scenarioBundle, "lcm.basic2");
	}
        
    public List<Quiz> generateQuizList(){
        List<Quiz> quizList = new ArrayList<>();
        for (int i=0;i<NUM_OF_QUESTIONS;i++){
        	
        	int rnd = CommonFunctionAndValues.getRandomInt(0, sces.size());
			String sce = getRandomScenario(rnd);
			String param =  getParams(rnd);
			//int loBo,hiBo,offset;
			// loBo = Integer.parseInt(param.substring(0, 2));
			// hiBo = Integer.parseInt(param.substring(2, 4));
			int offset = Integer.parseInt(param.substring(4, 4));
        		
            String[] pairPeople = CommonFunctionAndValues.getPairofPeople();
            int[] pairs = CommonFunctionAndValues.getPairOfIntSimple();
            sce = CommonFunctionAndValues.buildScenario(sce,pairPeople,pairs);
            int correctAnswer = MathUtils.findLCM(pairs)-offset;
            
            MultipleChoiceQuiz q = new MultipleChoiceQuiz();
            q.setLessonGrade(4);
            q.setQuestion(sce);
            q.setCorrectAnswer(String.valueOf(correctAnswer));
            q.setChoices(generateChoices(pairs));
            q.setDifficultyLevel(QuizLevel.MUDAH);
			q.setLessonCategory(bundle.getString("lcmgcd.lcmgcd"));
			q.setLessonSubcategory(bundle.getString("lcmgcd.subcategory.lcm"));
			q.setLessonClassifier(bundle.getString("mathelementary"));
            quizList.add(q);
        }

        return quizList;
    }

    @Override
    public MultipleChoiceQuiz generateQuiz(QuizLevel quizLevel) {
        return null;
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
