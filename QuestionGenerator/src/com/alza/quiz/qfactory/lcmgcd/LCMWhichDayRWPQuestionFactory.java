package com.alza.quiz.qfactory.lcmgcd;

import com.alza.quiz.model.Quiz;
import com.alza.quiz.util.CommonFunctionAndValues;
import com.alza.common.math.MathUtils;
import com.alza.quiz.model.QuizLevel;
import com.alza.quiz.model.MultipleChoiceQuiz;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by ewin.sutriandi@gmail.com on 24/12/16.
 */

public class LCMWhichDayRWPQuestionFactory extends LCMTwoNumQuestionFactory {
    private List<String> scenarios = new ArrayList<String>();
	Locale loc;
	ResourceBundle bundle;
	private ResourceBundle scenarioBundle;
	private List<String> sces;
	private static final int PARAMLENGTH=6;
	
	public LCMWhichDayRWPQuestionFactory(){
		this.loc = new Locale("in", "ID");
		initStringFromLocale();
	}
	
	public LCMWhichDayRWPQuestionFactory(Locale loc) {
		this.loc = loc;
		initStringFromLocale();
	}
	
	private void initStringFromLocale(){
		bundle = ResourceBundle.getBundle("lang.langbundle", loc);
		scenarioBundle = ResourceBundle.getBundle("lang.scenario-lcm", loc);
		sces = CommonFunctionAndValues.getStringCollection(scenarioBundle, "lcm.rwpday");
	}
    @Override
    public MultipleChoiceQuiz generateQuiz() {
        MultipleChoiceQuiz q;
        q = (MultipleChoiceQuiz) generateQuizList().get(0);
        return q;
    }
    public List<Quiz> generateQuizList(){
        List<Quiz> quizList = new ArrayList<>();
        int[][] numPair = CommonFunctionAndValues.simpleIntPairs;
        CommonFunctionAndValues.shuffleArray(numPair);
        Collections.shuffle(sces);
        for (int i=0;i<numq;i++){
        	int num = i % sces.size();
        	String question = sces.get(num);
        	question = question.substring(0,question.length()-(PARAMLENGTH));
			String param =  getParams(num);
			int loBo,hiBo,numVal,gcd,lcm,minGCD;
			loBo = Integer.parseInt(param.substring(0, 2));
			hiBo = Integer.parseInt(param.substring(2, 4));
			numVal = Integer.parseInt(param.substring(4, 5));
			minGCD = Integer.parseInt(param.substring(5, 6));
			int[] vals = new int[numVal];
			do {
				for (int v=0;v<numVal;v++) {
					vals[v] = ThreadLocalRandom.current().nextInt(loBo, hiBo)+1;
				}
				gcd = MathUtils.findGCD(vals);
				lcm = MathUtils.findLCM(vals);
			} while (gcd < minGCD || lcm == vals[0]);
			for (int v=1;v<=numVal;v++) {
            	String valSym = "#val"+v+"?";
            	question = question.replace(valSym, String.valueOf(vals[v-1]));
            }
            int refDayInInt = new Random().nextInt(6);
            String day = CommonFunctionAndValues.localDays(loc)[refDayInInt];
            question = question.replace("#refday?",day);
            int correctAnswer = lcm + refDayInInt;
            int rem = correctAnswer % 7;
            String correctAnswerInDay = CommonFunctionAndValues.localDays(loc)[rem];
            ArrayList<String> choices = new ArrayList<String>(Arrays.asList(CommonFunctionAndValues.localDays(loc)));
            MultipleChoiceQuiz q = new MultipleChoiceQuiz();
            q.enforceSortedChoices();
            q.setDifficultyLevel(quizLevel);
            q.setQuestion(question);
            q.setCorrectAnswer(correctAnswerInDay);
            q.setChoices(choices);
            q.setLessonCategory(bundle.getString("lcmgcd"));
			q.setLessonSubcategory(bundle.getString("lcmgcd.subcategory.lcm"));
			q.setLessonClassifier(bundle.getString("mathelementary"));
            q.setLessonGrade(4);
            q.setLocale(loc);
            quizList.add(q);
        }
        return  quizList;
    }

    @Override
    public MultipleChoiceQuiz generateQuiz(QuizLevel quizLevel) {
        return null;
    }

    void prepareScenario(){
        scenarios.add("#orang1? memiliki hobi #sport1? dan #sport2?. " +
                "#sport1? tiap #val1? hari sekali di pagi hari, dan #sport2? #val2? hari sekali di sore hari " +
                "Jika hari ini adalah hari #refday? dan #orang1? melakukan kedua hobinya. " +
                "Kapan ia akan melakukan keduanya lagi di hari yang sama?");
        scenarios.add("#orang1? mengikuti les #les1? dan #les2?. " +
                "Ia les #les1? tiap #val1? hari sekali pada pagi hari, dan #les2? #val2? hari sekali pada sore hari " +
                "Jika hari ini adalah hari #refday? dan #orang1? mengikuti keduanya. " +
                "Kapan ia akan melakukan keduanya lagi di hari yang sama?");

    }
    
    private String getParams(int rnd) {
		String s = sces.get(rnd);
		String params = s.substring(s.length()-PARAMLENGTH);
		//System.out.println(params);
		return params;
	}
	
	
}
