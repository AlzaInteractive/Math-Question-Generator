package com.alza.quiz.qfactory.fraction;

import java.util.ArrayList;
import java.util.List;

import com.alza.quiz.model.Quiz;
import com.alza.quiz.model.QuizLevel;
import com.alza.quiz.qfactory.IQuestionFactory;
import com.alza.quiz.util.CommonFunctionAndValues;

public class RatioQuestionFactory implements IQuestionFactory{
	private static enum TYPE  {
		ratioInPct, simpleRatio, findPartFromTotal
	}
	
	@Override
	public Quiz generateQuiz() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Quiz generateQuiz(QuizLevel quizLevel) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Quiz> generateQuizList() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public String getRandomScaleScenario(TYPE type){
		List<String> sceFindScale = new ArrayList<String>();
		List<String> sceFindRealLength = new ArrayList<String>();
		List<String> sceFindModelLength = new ArrayList<String>();
		sceFindScale.add("#Orang1? memiliki kandang ayam dirumahnya. "
				+ "Ia memiliki #total? ekor ayam. #pct1? ayam miliknya adalah ayam pedaging, "
				+ "sementara sisanya adalah ayam petelur. "
				+ "Berapakah jumlah ayam petelur yang ia miliki?");
		sceFindScale.add("Luas lahan pekarangan rumah #orang1? adalah #total? meter persegi."
				+ "#pct1? digunakan untuk bercock tanam, "
				+ "sisanya dibiarkan kosong untuk tempat bermain anaknya. "
				+ "Berapakah meter persegi luas lahan bermain yang tersedia untuk anak #orang1?");
		sceFindScale.add("Anak laki-laki di sekolah #orang1? berjumlah #total? anak "
				+ "#pct1? gemar bermain bola, sisanya gemar bermain basket"
				+ "Berapakah jumlah anak yang gemar bermain basket?");
		sceFindModelLength.add("Jumlah anak yang suka wortel dibandingkan dengan yang suka"
				+ " brokoli adalah #rat1? berbading #rat2?. Jika jumlah anak adalah #total? "
				+ " berapakah jumlah anak yang suka brokoli?");
		sceFindModelLength.add("#orang1 memiliki #total? ekor kelinci. "
				+ "Jumlah kelinci berwarna putih berbanding warna lainnya "
				+ "adalah #rat1? berbanding rat2? "
				+ "Berapakah jumlah kelinci putih #orang1??");
		sceFindModelLength.add("Berapakah jumlah guru perempuan di sekolah #orang1 "
				+ "jika perbandingan "
				+ " antara jumlah guru laki-laki dengan perempuan adalah #rat1? berbanding #rat2?"
				+ " dan jumlah semua guru adalah #total?.");
		sceFindRealLength.add("#part1 siswa mengikuti ekstrakurikuler pramuka "
				+ "#part2 siswa mengikuti ekstrakurikuler PMR "
				+ "sementara #part3 siswa mengikuti ekstrakurikuler atletik. "
				+ "Perbandingan antara siswa yang mengikuti pramuka, PMR dan atletik adalah?");
		sceFindRealLength.add("Jumlah siswa yang menyukai olahraga bulutangkis adalah #part1?, "
				+ "sementara sepakbola adalah #part2?, dan voli adalah #part3?. "
				+ "Perbandingan jumlah siswa penggemar bulutangkis, sepakbola dan voli"
				+ "dapat dinyatakan dengan nilai perbandingan?");
		sceFindRealLength.add("Berapakah perbandingan jumlah ikan nila, ikan gurame dan ikan mas "
				+ "pada kolam #orang1 jika jumlah ikan tersebut masing-masing "
				+ "#part1?, #part2? dan #part3??");
		
		int rndRatioInPct = CommonFunctionAndValues.getRandomInt(0, sceFindScale.size());
		int rndSimpleRatio = CommonFunctionAndValues.getRandomInt(0, sceFindModelLength.size());
		int rndFindPartFromTotal = CommonFunctionAndValues.getRandomInt(0, sceFindRealLength.size());
		String s="";
		switch (type) {
		case ratioInPct:
			s = sceFindScale.get(rndRatioInPct);
			break;
		case simpleRatio:
			s = sceFindModelLength.get(rndSimpleRatio);
			break;
		case findPartFromTotal:
			s = sceFindRealLength.get(rndFindPartFromTotal);
			break;
		default:
			break;
		}
		return s;
	}
}
