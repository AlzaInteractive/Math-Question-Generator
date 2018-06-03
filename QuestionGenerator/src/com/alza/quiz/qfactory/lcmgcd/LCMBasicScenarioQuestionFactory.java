package com.alza.quiz.qfactory.lcmgcd;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.concurrent.ThreadLocalRandom;

import com.alza.common.math.MathUtils;
import com.alza.quiz.model.MultipleChoiceQuiz;
import com.alza.quiz.model.Quiz;
import com.alza.quiz.model.QuizLevel;
import com.alza.quiz.util.CommonFunctionAndValues;

/**
 * Created by ewin.sutriandi@gmail.com on 24/12/16.
 */

public class LCMBasicScenarioQuestionFactory extends LCMTwoNumQuestionFactory {
    List<String> sces;
	Locale loc;
	ResourceBundle bundle,scenarioBundle;
	private static final int PARAMLENGTH=7;
	
	public LCMBasicScenarioQuestionFactory(Locale loc){
		this.loc = loc;
		initStringFromLocale();
	}
	public LCMBasicScenarioQuestionFactory(){
		this.loc = new Locale("in", "ID");
		initStringFromLocale();
	}
	private void initStringFromLocale(){
		bundle = ResourceBundle.getBundle("lang.langbundle", loc);
		scenarioBundle = ResourceBundle.getBundle("lang.scenario-lcm", loc);
		sces = CommonFunctionAndValues.getStringCollection(scenarioBundle, "lcm.basic");
	}
        
    public List<Quiz> generateQuizList(){
        List<Quiz> quizList = new ArrayList<>();
        Collections.shuffle(sces);
        for (int i=0;i<numq;i++){
        	int pos = i % sces.size();
			String question = getSceScenario(pos);
			String param =  getParams(pos);
			int loBo,hiBo,offset,gcd,lcm,numVal,minGCD;
			loBo = Integer.parseInt(param.substring(0, 2));
			hiBo = Integer.parseInt(param.substring(2, 4));
			offset = Integer.parseInt(param.substring(4, 5));
			numVal = Integer.parseInt(param.substring(5, 6));
			minGCD = Integer.parseInt(param.substring(6, 7));
			int[] vals = new int[numVal];
			do {
				for (int j=0 ; j < vals.length; j++){
					vals[j] = ThreadLocalRandom.current().nextInt(loBo, hiBo);
				}
				gcd = MathUtils.findGCD(vals);
				lcm = MathUtils.findLCM(vals);
			} while (vals[0]==vals[1]||
					gcd < minGCD);
			
			question = question.replace("#val1?", String.valueOf(vals[0]));
			question = question.replace("#val2?", String.valueOf(vals[1]));
			if (vals.length>=3) question = question.replace("#val3?", String.valueOf(vals[2]));
			if (vals.length>=4) question = question.replace("#val4?", String.valueOf(vals[3]));
			if (vals.length>=5) question = question.replace("#val5?", String.valueOf(vals[4]));
            int correctAnswer = lcm-offset;
            
            MultipleChoiceQuiz q = new MultipleChoiceQuiz();
            q.setQuestion(question);
            q.setCorrectAnswer(String.valueOf(correctAnswer));
            
            q.addChoice(vals);
			q.addChoice(lcm);
			q.addChoice(gcd);
			q.addChoice(correctAnswer);
                        
			q.setLessonGrade(4);
			q.setDifficultyLevel(QuizLevel.MUDAH);
			q.setLessonCategory(bundle.getString("lcmgcd"));
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
	public String getSceScenario(int idx){
		String s = sces.get(idx);
		String sce = s.substring(0,s.length()-(PARAMLENGTH));
		return sce;
	}
}
