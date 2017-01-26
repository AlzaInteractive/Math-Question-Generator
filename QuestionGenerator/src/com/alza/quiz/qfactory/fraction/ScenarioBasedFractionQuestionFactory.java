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
		List<String> sce = new ArrayList<String>();
		sce.add("Bu guru menugaskan murid-murid membuat gerabah dari tanah liat. "
				+ "Tiga kelompok siswa diminta untuk berbagi seember tanah liat, "
				+ "masing-masing kemudian mengambil #frac1?, #frac2?, dan #frac3? bagian. "
				+ "Sisa tanah di dalam ember adalah"
				+ " .. bagian");
		String[] food = {"sebuah apel","sebuah semangka","sebuah melon","sebuah pepaya","sebuah sirsak","seloyang pizza"};
		String curFood = food[rnd];
		String sce2 = "#orang1? memiliki #food1?. "
				+ "ia memberikan #frac1? kepada #orang2?, "
				+ "#frac2? kepada #orang3?, dan "
				+ "#frac3? kepada #orang4?. "
				+ "Berapakah sisa #food1? #orang1? ";
		sce2 = sce2.replace("#food1?", curFood);
		sce.add(sce2);
		sce.add("Ibu membuat sepiring agar-agar dan meletakkannya di atas meja. "
				+ "#orang1?, #orang2?, dan #orang3? datang dan memakan "
				+ "masing masing #frac1?, #frac2?, dan #frac3? bagian. "
				+ "#orang4? datang terakhir dan mengambil sisanya. "
				+ "Bagian yang didapatkan #orang4? adalah ... bagian.");
		
		sce.add("Empat bersaudara #orang1?, #orang2?, #orang3?, dan #orang4? "
				+ "menemukan setoples kacang di ruang tamu. "
				+ "Mereka sepakat membaginya untuk menjadi bekal bermain. "
				+ "#orang2, #orang3?, dan #orang4? mengambil "
				+ "masing-masing #frac1?, #frac2?, dan #frac3?. "
				+ "Jika #orang1? mendapatkan sisanya, berapa bagiankah itu?");
		int rndsce = new Random().nextInt(sce.size());
		String scenario = sce.get(rndsce);
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
	private Quiz generateScenario2() {
		MultipleChoiceQuiz q = new MultipleChoiceQuiz();
		List<String> sce = new ArrayList<String>();
		sce.add("#elder1? memiliki tanah kebun 12 are. Ia menanami kebunnya dengan kubis, dan wortel  "
				+ "masing-masing #frac1? dan #frac2? bagian. Ia berencana menanami sisanya dengan bayam. "
				+ "berapakah luas tanah yang tersedia untuk bayam #elder1?");
				
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
