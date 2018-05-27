package com.alza.quiz.qfactory.lcmgcd;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.ResourceBundle;

import com.alza.common.math.MathUtils;
import com.alza.quiz.model.MultipleChoiceQuiz;
import com.alza.quiz.model.Quiz;
import com.alza.quiz.model.QuizLevel;
import com.alza.quiz.util.CommonFunctionAndValues;

/**
 * Created by ewin.sutriandi@gmail.com on 24/12/16.
 */

public class LCMWhichHourRWPQuestionFactory extends LCMTwoNumQuestionFactory {
    private List<String> dayScenario = new ArrayList<String>();
    private int[] minutes = {
    		30,45,60,90,120,150,180
    };
    int refdayInt;
    protected static int numq = 4;
	Locale loc;
	ResourceBundle bundle;
	private ResourceBundle scenarioBundle;
	private List<String> sces;
	private static final int PARAMLENGTH=0;
	
	public LCMWhichHourRWPQuestionFactory(){
		this.loc = new Locale("in", "ID");
		initStringFromLocale();
	}
	
	public LCMWhichHourRWPQuestionFactory(Locale loc) {
		this.loc = loc;
		initStringFromLocale();
	}
	
	private void initStringFromLocale(){
		bundle = ResourceBundle.getBundle("lang.langbundle", loc);
		scenarioBundle = ResourceBundle.getBundle("lang.scenario-lcm", loc);
		dayScenario = CommonFunctionAndValues.getStringCollection(scenarioBundle, "lcm.rwphr");
	}

    @Override
    public MultipleChoiceQuiz generateQuiz() {
        int rnd = new Random().nextInt(dayScenario.size());
        MultipleChoiceQuiz q;
        q = (MultipleChoiceQuiz) generateQuizList().get(rnd);
        return q;
    }
    public List<Quiz> generateQuizList(){
        List<Quiz> quizList = new ArrayList<>();
        Collections.shuffle(dayScenario);
        CommonFunctionAndValues.shuffleArray(minutes);
        for (int i=0;i<numq;i++){
        	int rnd1,rnd2,rndOffset;
        	do {
        		rnd1 = CommonFunctionAndValues.getRandomInt(0, minutes.length);
                rnd2 = CommonFunctionAndValues.getRandomInt(0, minutes.length);
                rndOffset = CommonFunctionAndValues.getRandomInt(0, minutes.length);
        	} while (rnd1 == rnd2);
        	String sce = dayScenario.get(i);
        	sce = sce.substring(0,sce.length()-(PARAMLENGTH));
        	int starthour = 360 + minutes[rndOffset];
        	if (sce.contains("nitestart?")){
        		starthour= 1200+minutes[rndOffset];
        	}
        	starthour = starthour % 1440;
            String refHour = CommonFunctionAndValues.minutesToHour(starthour,loc);
            sce = sce.replace("#starthour?",refHour);
            sce = sce.replace("#nitestart?",refHour);
            sce = sce.replace("#num1?",String.valueOf(minutes[rnd1]));
            sce = sce.replace("#num2?",String.valueOf(minutes[rnd2]));
            int correctAnswer = starthour + MathUtils.findLCM(minutes[rnd1],minutes[rnd2]);
            if (correctAnswer>=1440) {
            	correctAnswer = correctAnswer-1440;
            }
            String correctAnswerInHour = CommonFunctionAndValues.minutesToHour(correctAnswer,loc);
            String[] choices = buildChoices(correctAnswer, 
            		starthour+minutes[rndOffset],
            		starthour+minutes[0],
            		starthour+minutes[1],
            		starthour+minutes[2]
            		);
            MultipleChoiceQuiz q = new MultipleChoiceQuiz();
            q.setDifficultyLevel(QuizLevel.SEDANG);
            q.setQuestion(sce);
            q.setCorrectAnswer(correctAnswerInHour);
            q.setChoices(choices);
            q.setLessonCategory(bundle.getString("lcmgcd.lcmgcd"));
			q.setLessonSubcategory(bundle.getString("lcmgcd.subcategory.lcm"));
			q.setLessonClassifier(bundle.getString("mathelementary"));
            q.setLessonGrade(5);
            quizList.add(q);
        }
        return  quizList;
    }

    @Override
    public MultipleChoiceQuiz generateQuiz(QuizLevel quizLevel) {
        return null;
    }

    void prepareScenario(){
        dayScenario.add("Pesawat dari Lombok ke Jakarta berangkat tiap #num1? menit sekali, "
        		+ "sedangkan pesawat dari Lombok ke Surabaya berangkat tiap #num2? menit sekali. "
        		+ "Jika pada pukul #starthour? pesawat kedua rute tersebut siap lepas landas, "
        		+ "pukul berapa pesawat kedua rute bertemu lagi di landasan pada hari yang sama?");
        dayScenario.add("Bis tujuan kota A berangkat dari terminal tiap #num1? menit. " +
                "Bis tujuan kota B berangkat dari terminal yang sama tiap #num2? menit. " +
                "Jika hari ini pada pukul #starthour? bis kedua tujuan berangkat hampir bersamaan, " +
                "pada pukul berapa mereka akan berangkat bersamaan lagi?");
        dayScenario.add("Patas AC tujuan kota A berangkat dari terminal tiap #num1? menit, " +
                "sedangkan tujuan kota B berangkat dari terminal yang sama tiap #num2? menit. " +
                "Jika hari ini pada pukul #starthour? bis kedua tujuan berangkat hampir bersamaan, " +
                "pada pukul berapa mereka akan berangkat bersamaan lagi?");
        dayScenario.add("RW Delima dan RW Durian terletak berdekatan dan dibatasi jalan desa. " +
                "Setiap malam petugas ronda kedua RW secara bergiliran " +
                "melakukan patroli keliling di wilayah masing-masing. " +
                "Mengelilingi RW Delima memakan waktu #num1? menit sedangkan RW Durian #num2? menit." +
                "Jika pada pukul #nitestart? petugas kedua RW bertemu di jalan desa, " +
                "pada pukul berapa kemungkinan mereka akan bertemu lagi di tempat yang sama?");

    }
    
    private String[] buildChoices(int...ints){
    	String[] choices = new String[ints.length];
    	for (int i = 0; i < ints.length; i++) {
    		if (ints[i]>=1440){
    			ints[i] = ints[i]-1440;
    		}
			choices[i] = CommonFunctionAndValues.minutesToHour(ints[i],loc);
		}
    	return choices;
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
