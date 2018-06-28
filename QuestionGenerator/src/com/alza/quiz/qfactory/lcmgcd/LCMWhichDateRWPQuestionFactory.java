package com.alza.quiz.qfactory.lcmgcd;


import com.alza.quiz.model.Quiz;
import com.alza.quiz.model.QuizLevel;
import com.alza.quiz.util.CommonFunctionAndValues;
import com.alza.common.math.MathUtils;
import com.alza.quiz.model.MultipleChoiceQuiz;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by ewin.sutriandi@gmail.com on 24/12/16.
 */

public class LCMWhichDateRWPQuestionFactory extends LCMTwoNumQuestionFactory {
    private Date[] refDates;
	Locale loc;
	ResourceBundle bundle;
	private ResourceBundle scenarioBundle;
	private List<String> sces;
	private static final int PARAMLENGTH=6;
	
	public LCMWhichDateRWPQuestionFactory(){
		this.loc = new Locale("in", "ID");
		initStringFromLocale();
	}
	
	public LCMWhichDateRWPQuestionFactory(Locale loc) {
		this.loc = loc;
		initStringFromLocale();
	}
	
	private void initStringFromLocale(){
		bundle = ResourceBundle.getBundle("lang.langbundle", loc);
		scenarioBundle = ResourceBundle.getBundle("lang.scenario-lcm", loc);
		sces = CommonFunctionAndValues.getStringCollection(scenarioBundle, "lcm.rwpdate");
	}

    public MultipleChoiceQuiz generateQuiz() {
        MultipleChoiceQuiz q;
        q = (MultipleChoiceQuiz) generateQuizList().get(0);
        return q;
    }

    public List<Quiz> generateQuizList(){
        //prepareScenario();
        List<Quiz> quizList = new ArrayList<>();
        constructRefDates();
        Collections.shuffle(sces);
        for (int i=0;i<numq;i++){
            //prepare question
        	int num = i % sces.size();
        	String question = sces.get(num);
        	question = question.substring(0,question.length()-(PARAMLENGTH));
			String param =  getParams(num);
			int loBo,hiBo,numVal,gcd,lcm, minGCD;
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
            Date refDate = refDates[i%refDates.length];
            Calendar c = Calendar.getInstance();
            c.setTime(refDate);
            question = question.replace("#refdate?",CommonFunctionAndValues.dateToString(refDate,loc));
            for (int v=1;v<=numVal;v++) {
            	String valSym = "#val"+v+"?";
            	question = question.replace(valSym, String.valueOf(vals[v-1]));
            }
            //prepare answer
            c.add(Calendar.DATE,lcm);
            Date answerInDate = c.getTime();
            String answerInString = CommonFunctionAndValues.dateToString(answerInDate,loc);
            //prepare choices
            Set<String> choices = prepareChoices(answerInDate);
            //Prepare Quiz
            MultipleChoiceQuiz q = new MultipleChoiceQuiz();
            q.setDifficultyLevel(QuizLevel.SULIT);
            q.setQuestion(question);
            q.setChoices(choices);
            q.setCorrectAnswer(answerInString);
            q.setLessonGrade(5);
			q.setLessonCategory(bundle.getString("lcmgcd"));
			q.setLessonSubcategory(bundle.getString("lcmgcd.subcategory.lcm"));
			q.setLessonClassifier(bundle.getString("mathelementary"));
			q.setLocale(loc);
            quizList.add(q);
        }
        return quizList;
    }

    private Set<String> prepareChoices(Date correctAnswer){
    	Set<String> choices = new HashSet<String>();
        int[] choicesInInt = CommonFunctionAndValues.simpleInt;
        CommonFunctionAndValues.shuffleArray(choicesInInt);
        for (int i=0;i<5;i++){
            Calendar c = Calendar.getInstance();
            c.setTime(correctAnswer);
            if (i % 2 == 0){
                c.add(Calendar.DATE,choicesInInt[i]);
            } else {
                c.add(Calendar.DATE,choicesInInt[i]*-1);
            }
            String s = CommonFunctionAndValues.dateToString(c.getTime(),loc);
            choices.add(s);
        }
        choices.add(CommonFunctionAndValues.dateToString(correctAnswer,loc));
        return choices;
    }

    private void constructRefDates(){
        //these are special dates, that's why it's not randomized
        Calendar c = Calendar.getInstance();
        c.set(c.get(Calendar.YEAR),6,31);
        Date d1 = c.getTime();
        c.set(c.get(Calendar.YEAR),10,10);
        Date d2 = c.getTime();
        c.set(c.get(Calendar.YEAR),11,29);
        Date d3 = c.getTime();
        c.set(c.get(Calendar.YEAR),4,14);
        Date d4 = c.getTime();
        c.set(c.get(Calendar.YEAR),9,7);
        Date d5 = c.getTime();
        c.set(c.get(Calendar.YEAR),20,20);
        Date d6 = c.getTime();
        c.set(c.get(Calendar.YEAR),1,27);
        Date d7 = c.getTime();
        c.set(c.get(Calendar.YEAR),7,30);
        Date d8 = c.getTime();
        c.set(c.get(Calendar.YEAR),0,1);
        Date d9 = c.getTime();
        c.set(c.get(Calendar.YEAR),11,31);
        Date d10 = c.getTime();
        refDates = new Date[]{d1,d2,d3,d4,d5,d6,d7,d8,d9,d10};
        //it's not randomized but still feel that at least they're shuffled
        CommonFunctionAndValues.shuffleArray(refDates);
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
