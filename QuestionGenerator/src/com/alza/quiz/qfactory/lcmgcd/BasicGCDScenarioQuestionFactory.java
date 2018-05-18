package com.alza.quiz.qfactory.lcmgcd;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import com.alza.common.math.MathUtils;
import com.alza.quiz.model.MultipleChoiceQuiz;
import com.alza.quiz.model.Quiz;
import com.alza.quiz.model.QuizLevel;
import com.alza.quiz.qfactory.IQuestionFactory;
import com.alza.quiz.util.CommonFunctionAndValues;

public class BasicGCDScenarioQuestionFactory implements IQuestionFactory{
	private static int NUM_OF_QUESTIONS = 2;
	private static List<GCDScenario> scenarios = new ArrayList<BasicGCDScenarioQuestionFactory.GCDScenario>();
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
		generateScenario();
		List<Quiz> lq = new ArrayList<Quiz>();
		for (int i=1;i<NUM_OF_QUESTIONS;i++){
			GCDScenario s = scenarios.get(i);
			String question = s.scenario;
			int gcd,lcm;
			int[] vals = new int[s.numVal];
			do {
				for (int j=0 ; j < vals.length; j++){
					vals[j] = ThreadLocalRandom.current().nextInt(s.minVal, s.maxVal);
				}
				//System.out.println(Arrays.toString(vals));
				gcd = MathUtils.findGCD(vals);
			} while (gcd < s.minGCD ||
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
			q.setLessonSubcategory("Soal cerita sederhana");
			q.setLessonClassifier("Matematika SD");
			q.setLessonGrade(5);
			q.setSubCategoryOrder(5);
			q.setLessonCategory("KPK & FPB");
			lq.add(q);
		}
		return lq;
	}
	
	private void generateScenario() {
		String s1,s2,s3,s4,s5,s6,s7;
		s1 = "#orang1? memiliki lahan untuk ditanami #val1? bibit tomat dan #val2? bibit cabai."
				+ " Ia ingin menanam dua jenis bibit dalam tiap barisan gundukan."
				+ " Jika ia ingin setiap baris memiliki kombinasi tanaman tomat dan cabai dalam jumlah yang sama,"
				+ " dan seluruh bibit harus habis terpakai, berapakah jumlah gundukan maksimal yang bisa ia buat?";
		s2 = "Pakde membelikan #val1? butir permen mint dan #val2? butir permen kopi untuk #orang1?. "
				+ " #orang1? ingin menghadiahkan seluruh permen ke teman-temannya."
				+ " Agar adil ia ingin setiap temannya mendapatkan jumlah permen mint dan kopi yang sama."
				+ " Cari berapa jumlah maksimal teman yang dapat ia bagikan permen tersebut.";
		s3 = "Kelas #orang1? memiliki #val1? siswa dan #val2? siswi."
				+ " Bu guru ingin membagi kelompok dimana tiap kelompok memiliki jumlah siswa dan siswi yang sama"
				+ " Berapa jumlah kelompok maksimal yang dapat dibentuk pada kelas tersebut?";
		s4 = "Ibu #orang1? ahli membuat kue."
				+ " Hari ini ia membuat #val1? pastel, #val2? risoles, dan #val3? bakwan udang."
				+ " Ia ingin membuat paket bungkusan kue dengan seluruh kue yang ada."
				+ " Berapa jumlah maksimal bungkusan yang dapat ia buat jika setiap bungkus"
				+ " harus memiliki kombinasi kue dengan jumlah yang sama?";
		s5 = "#orang1? mendapati kulkas berisi #val1? butir kolang-kaling, #val2? potongan cincau,"
				+ " #val3? potongan daging buah nangka, dan #val4? potongan nata de coco."
				+ " Ia ingin membuat es campur dengan seluruh bahan tersebut."
				+ " Berapa jumlah mangkok es campur maksimal yang bisa dibuat bila"
				+ " seluruh bahan harus habis dan setiap mangkok mendapat jatah isian yang sama?";
		s6 = "Jambore Nasional diikuti oleh #val1? siswa yang berasal dari Indonesia bagian barat,"
				+ " #val2? siswa dari Indonesia tengah dan #val3? siswa dari Indonesia timur."
				+ " Untuk membuat seluruh peserta berbaur dan berinteraksi dengan peserta dari daerah lain,"
				+ " kakak-kakak pembina kemudian membagi tim dimana setiap tim berisi peserta dari tiap daerah."
				+ " Berapa jumlah maksimal tim yang dapat dibuat jika seluruh peserta harus memiliki tim"
				+ " dan setiap tim memiliki peserta dari tiap daerah dengan jumlah yang sama?";
		s7 = "#orang1? akan merayakan ulang tahunnya besok sore. "
				+ " Kakaknya sedang mempersiapkan hiasan balon untuk acara tersebut dengan"
				+ " membuat ikatan balon dengan warna merah, kuning, hijau, dan biru."
				+ " Bila jumlah balon berwarna tersebut masing-masing #val1?, #val2?, #val3?, dan #val4?,"
				+ " dan kakaknya ingin agar setiap ikatan memiliki kombinasi warna yang identik"
				+ " dan seluruh balon habis terpakai,"
				+ " berapakah jumlah ikatan maksimal yang dapat dibuat dari seluruh balon tersebut?";
		
		scenarios.add(new GCDScenario(40,81,2,6,s1));
		scenarios.add(new GCDScenario(80,121,2,12,s2));
		scenarios.add(new GCDScenario(20,41,2,4,s3));
		scenarios.add(new GCDScenario(40,91,3,15,s4));
		scenarios.add(new GCDScenario(30,51,4,12,s5));
		scenarios.add(new GCDScenario(40,65,3,15,s6));
		scenarios.add(new GCDScenario(80,121,4,12,s7));
		Collections.shuffle(scenarios);
	}
	
	class GCDScenario{
		int minVal,maxVal,numVal,minGCD;
		String scenario;
		public GCDScenario(int minVal, int maxVal, int numVal, int minGCD,
				String scenario) {
			super();
			this.minVal = minVal;
			this.maxVal = maxVal;
			this.numVal = numVal;
			this.minGCD = minGCD;
			this.scenario = scenario;
		}
		
	}
	@Override
	public List<Quiz> generateQuizList(int numOfQuestion) {
		NUM_OF_QUESTIONS = numOfQuestion;
		return generateQuizList();
	}

}
