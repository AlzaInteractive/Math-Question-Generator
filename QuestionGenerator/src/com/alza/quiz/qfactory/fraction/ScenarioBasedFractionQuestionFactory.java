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
        quizList.add(generateScenario2());
        for (Quiz q : quizList) {
        	q.setLessonClassifier("Matematika SD");
			q.setLessonCategory("Pecahan");
			q.setLessonSubcategory("Soal terapan");
			q.setDifficultyLevel(QuizLevel.SULIT);
			q.setSubCategoryOrder(11);
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
		q.setLessonGrade(4);
		return q;
	}
	private Quiz generateScenario2() {
		MultipleChoiceQuiz q = new MultipleChoiceQuiz();
		List<String> sce = new ArrayList<String>();
		int[][] randVals = {
				{2,3,36},{2,4,24},{3,4,72},{4,6,48},{5,3,30},{7,6,42},{8,4,40},{5,8,40},{6,9,36}
		}; 
		int indVal = new Random().nextInt(randVals.length);
		sce.add("#elder1? memiliki tanah kebun #num1? are. Ia menanami kebunnya dengan kubis dan wortel  "
				+ "masing-masing #frac1? dan #frac2? bagian. Ia berencana menanami sisanya dengan bayam. "
				+ "Berapakah luas tanah yang tersedia untuk bayam #elder1?");
		sce.add("#orang1?, #orang2?, dan #orang3? bermain ke pantai sembari mencari kerang. "
				+ "Saat pulang mereka membawa seember penuh berisi #num1? kerang. "
				+ "Jika #orang1? mengumpulkan #frac1? bagian, #orang2? #frac2? bagian, "
				+ "berapakah banyak kerang yang dikumpulkan #orang3??");
		sce.add("Salah satu teman sekelas #orang1?, #orang2? dan #orang3? sakit sehingga tidak masuk sekolah. "
				+ "Mereka bertiga kemudian mengumpulkan uang sebesar #num1? ribu rupiah untuk membelikan buah. "
				+ "Dari nilai tersebut #orang1? dan #orang2? menyumbang #frac1? dan #frac2? bagian. "
				+ "Berapakah nilai uang yang disumbang #orang3? (dalam ribuan)");
		sce.add("Di rumah #orang1? ada #num1? ekor kelinci. #frac1?-nya berwarna putih "
				+ "dan #frac2?-nya berwarna hitam. "
				+ "Jika sisanya berwarna cokelat, berapakah jumlah kelinci cokelat di rumah #orang1??");
		sce.add("Bu guru menyita sementara kelereng siswa laki-laki yang bermain saat jam pelajaran. "
				+ "Ia mengumpulkan #num1? biji kelereng dari kelas 4,5 dan 6. "
				+ "#frac1? bagian didapatkan dari kelas 4, #frac2? bagian dari kelas 5. "
				+ "Berapakah jumlah kelereng yang disita dari kelas 6?");
		int sceVal = new Random().nextInt(sce.size());
		int[] vals = randVals[indVal];
		String scenario = sce.get(sceVal);
		
		Fraction f1 = new Fraction(1, vals[0]);
		Fraction f2 = new Fraction(1, vals[1]);
		Fraction fAns = new Fraction(1, 1).getResultWhenSubtractWith(f1).getResultWhenSubtractWith(f2);
		Fraction fRes = fAns.getResultWhenMultipliedBy(vals[2]);
		//System.out.println(fAns+" ");
		int ans = fRes.getOneDigitInteger();
		scenario = scenario.replace("#frac1?", f1.toString());
		scenario = scenario.replace("#frac2?", f2.toString());
		scenario = scenario.replace("#num1?", String.valueOf(vals[2]));
		scenario = CommonFunctionAndValues.buildScenario(scenario);
		Fraction[] fracs = {fAns,f1,f2,
				f1.getResultWhenAddedWith(f2),
				f2.getResultWhenSubtractWith(f1),
				f1.getResultWhenSubtractWith(f2),};
		q.setQuestion(scenario);
		q.setCorrectAnswer(String.valueOf(ans));
		q.setChoices(buildChoices(fracs,vals[2]));
		q.setLessonGrade(4);
		return q;
	}
    private Set<String> buildChoices(Fraction[] fracs) {
		Set<String> choiceInString = new HashSet<String>();
		for (Fraction fraction : fracs) {
			choiceInString.add(fraction.toString());
		}
		return choiceInString;
	}
    private Set<String> buildChoices(Fraction[] fracs, int multiplier) {
		Set<String> choiceInString = new HashSet<String>();
		for (Fraction fraction : fracs) {
			fraction.multiplyBy(multiplier);
			choiceInString.add(String.valueOf(fraction.getOneDigitInteger()));
		}
		return choiceInString;
	}
}
