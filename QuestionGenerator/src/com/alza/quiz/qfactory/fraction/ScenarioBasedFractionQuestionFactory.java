package com.alza.quiz.qfactory.fraction;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import com.alza.common.math.Fraction;
import com.alza.quiz.model.MultipleChoiceQuiz;
import com.alza.quiz.model.Quiz;
import com.alza.quiz.model.QuizLevel;
import com.alza.quiz.qfactory.IQuestionFactory;
import com.alza.quiz.util.CommonFunctionAndValues;

/**
 * Created by ewin.sutriandi@gmail.com on 24/01/16.
 */

public class ScenarioBasedFractionQuestionFactory implements IQuestionFactory {
    
    public ScenarioBasedFractionQuestionFactory(){
        super();
    }
    public Quiz generateQuiz() {
    	List<Quiz> ql = generateQuizList();
        int rnd = new Random().nextInt(ql.size());
        Quiz q;
        q = ql.get(rnd);
        return q;
    }
    @Override
    public Quiz generateQuiz(QuizLevel quizLevel) {
        return generateQuiz();
    }

    public List<Quiz> generateQuizList(){
        List<Quiz> quizList = new ArrayList<>();
        quizList.add(generateScenario1());
        for (Quiz q : quizList) {
        	q.setLessonClassifier("Matematika SD");
			q.setLessonCategory("Pecahan");
			q.setLessonSubcategory("Soal terapan");
			q.setDifficultyLevel(QuizLevel.TERAPAN);
		}
        return quizList;
    }

    

    private Quiz generateScenario1() {
		/*
		 * result = one whole subtract by 3 fraction
		 */
		int[][] fractionDenoms = {
				{2,4,8},{3,6,3},{3,4,6},{6,6,3},{4,4,8},{5,3,3}
		};
		int rnd = new Random().nextInt(fractionDenoms.length);
		int[] curDivisors = fractionDenoms[rnd];
		String scenario = "";
		if (rnd % 5 ==0){
			scenario ="Bu guru menugaskan murid-murid membuat gerabah dari tanah liat. "
					+ "Tiga kelompok siswa diminta untuk berbagi seember tanah liat, "
					+ "masing-masing kemudian mengambil #frac1?, #frac2?, dan #frac3? bagian. "
					+ "Sisa tanah di dalam ember adalah"
					+ " .. bagian";
		}
		else if (rnd % 3 == 0){
			String[] food = {"apel","semangka","melon","pepaya","sirsak"};
			String curFood = food[rnd];
			scenario = "#orang1? memiliki sebuah #food1?. "
					+ "ia memberikan #frac1? kepada #orang2?, "
					+ "#frac2? kepada #orang3?, dan "
					+ "#frac3? kepada #orang4?. "
					+ "Berapakah sisa #food1? #orang1? ";
			scenario = scenario.replace("#food1?", curFood);
		} else if (rnd % 2 == 0) {
			scenario = "Ibu membuat sepiring agar-agar dan meletakkannya di atas meja. "
					+ "#orang1?, #orang2?, dan #orang3? datang dan memakan "
					+ "masing masing #frac1?, #frac2?, dan #frac3? bagian. "
					+ "#orang4? datang terakhir dan mengambil sisanya. "
					+ "Bagian yang didapatkan #orang4? adalah ... bagian.";
		} else {
			scenario = "Empat bersaudara #orang1?, #orang2?, #orang3?, dan #orang4? "
					+ "menemukan setoples kacang di ruang tamu. "
					+ "Mereka sepakat membaginya untuk menjadi bekal bermain. "
					+ "#orang2, #orang3?, dan #orang4? mengambil "
					+ "masing-masing #frac1?, #frac2?, dan #frac3?. "
					+ "Jika #orang1? mendapatkan sisanya, berapa bagiankah itu?";
		}
		Fraction f1 = new Fraction(1, curDivisors[0]);
		Fraction f2 = new Fraction(1, curDivisors[1]);
		Fraction f3 = new Fraction(1, curDivisors[2]);
		
		scenario = scenario.replace("#frac1?", f1.toString());
		scenario = scenario.replace("#frac2?", f2.toString());
		scenario = scenario.replace("#frac3?", f3.toString());
		scenario = CommonFunctionAndValues.buildScenario(scenario);
		Fraction result = new Fraction(1,1);
		result.subtract(f1);
		result.subtract(f2);
		result.subtract(f3);
		Fraction[] fracs = {result,f1,f2,f3,
				f1.getResultWhenAddedWith(f2),
				f2.getResultWhenAddedWith(f3),
				f3.getResultWhenAddedWith(result)};
		MultipleChoiceQuiz q = new MultipleChoiceQuiz();
		q.setQuestion(scenario);
		q.setCorrectAnswer(result.toString());
		q.setChoices(buildChoices(fracs));
		return q;
	}
	
    private Set<String> buildChoices(Fraction[] fracs) {
		Set<String> choiceInString = new HashSet<String>();
		for (Fraction fraction : fracs) {
			choiceInString.add(fraction.toString());
		}
		return choiceInString;
	}
}
