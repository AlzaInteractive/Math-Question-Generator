package com.alza.quiz.qfactory.fraction;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.alza.quiz.model.MultipleChoiceQuiz;
import com.alza.quiz.model.Quiz;
import com.alza.quiz.model.QuizLevel;
import com.alza.quiz.qfactory.IQuestionFactory;
import com.alza.quiz.util.CommonFunctionAndValues;

public class ScaleQuestionFactory implements IQuestionFactory {
	private static enum TYPE {
		findScale,findModelLength,findRealLength
	};
	@Override
	public Quiz generateQuiz() {
		return generateQuiz();
	}

	@Override
	public Quiz generateQuiz(QuizLevel quizLevel) {
		List<Quiz> ql = generateQuizList();
		int rnd = CommonFunctionAndValues.getRandomInt(0, ql.size());
		return ql.get(rnd);
	}

	@Override
	public List<Quiz> generateQuizList() {
		List<Quiz> ql = new ArrayList<Quiz>();
		for (int i=0;i<6;i++){
			ql.add(generateTypeA(i));
		}
		return ql;
	}
	
	private MultipleChoiceQuiz generateTypeA(int i) {
		MultipleChoiceQuiz q = new MultipleChoiceQuiz();
		// for home layout scale (less than 10 metres)
		int[][] miniScales= {
				{1,50},{1,100},{1,150},{1,200},{1,250}	
		};
/*		// for district layout scale
		int[][] medScales= {
				{1,10000},{1,15000},{1,20000},{1,25000}	
		};
		// for large map scale
		int[][] largeMapScales= {
				{1,1000000},{1,1500000},{1,2000000},{1,2500000}	
		};*/
		TYPE t=null;String sce;
		int rndScale = CommonFunctionAndValues.getRandomInt(0, miniScales.length);
		int[] scale = miniScales[rndScale];
		int num1 = CommonFunctionAndValues.getRandomInt(3, 7);
		double num2 = (double)(scale[1]) * (double) num1 /100; // div 100 to convert to meters
		String snum2 = new DecimalFormat("##.##").format(num2);
		if (i % 3 == 0){
			t = TYPE.findScale;
			sce = getRandomScaleScenario(TYPE.findScale);
			q.setCorrectAnswer(scale[0]+":"+scale[1]);
			q.setLessonSubcategory("Skala : Mencari skala perbandingan");

		} else if (i % 3 == 1){
			t = TYPE.findModelLength;
			sce = getRandomScaleScenario(TYPE.findModelLength);
			q.setCorrectAnswer(String.valueOf(num1));
			q.setLessonSubcategory("Skala : Mencari ukuran model dari skala");
			
		} else {
			t = TYPE.findRealLength;
			sce = getRandomScaleScenario(TYPE.findRealLength);
			q.setCorrectAnswer(String.valueOf(snum2));
			q.setLessonSubcategory("Skala : Mencari ukuran asli dari skala");
		}
		sce = sce.replace("#num1?", String.valueOf(num1));
		sce = sce.replace("#num2?", snum2);
		sce = sce.replace("#scale1?", scale[0]+":"+scale[1]);
		sce = CommonFunctionAndValues.buildScenario(sce);
		q.setQuestion(sce);
		q.setChoices(this.buildChoicesFromScale(miniScales,t, num1, num2));
		q.setLessonClassifier("Matematika SD");
		q.setLessonCategory("Pecahan");
		q.setDifficultyLevel(QuizLevel.SEDANG);
		q.setLessonGrade(5);
		q.setSubCategoryOrder(6);
		return q;
	}
	public String getRandomScaleScenario(TYPE type){
		List<String> sceFindScale = new ArrayList<String>();
		List<String> sceFindRealLength = new ArrayList<String>();
		List<String> sceFindModelLength = new ArrayList<String>();
		sceFindScale.add("Pada sebuah denah, lebar sebuah ruangan adalah #num1? cm, "
				+ "sedangkan aslinya adalah #num2? meter. "
				+ "Berapakah skala denah tersebut?");
		sceFindScale.add("Kamar #orang1? memiliki ukuran panjang #num2? meter. "
				+ "Pada denah yang ia buat, panjangnya menjadi #num1? cm. "
				+ "Skala denah yang dibuat #orang1? adalah?");
		sceFindScale.add("Tinggi rumah pada gambar konstruksi adalah #num1? cm. "
				+ "Tinggi aslinya adalah #num2? meter. "
				+ "Skala denah pada gambar adalah?");
		sceFindModelLength.add("Lebar kolam renang rumah #orang1? adalah #num2? meter. "
				+ "Pada denah dengan skala #scale1? berapakah lebar kolam tersebut "
				+ "dalam ukuran cm?");
		sceFindModelLength.add("Teras rumah #orang1? berbentuk persegi dengan panjang #num2? meter. "
				+ "Pada denah dengan skala #scale1? berapakah cm kah panjang teras tersebut?");
		sceFindModelLength.add("Lebar garasi pada rumah #elder1? adalah #num2? meter"
				+ "Pada denah dengan skala #scale1? berapakah cm kah lebar garasi tersebut?");
		sceFindRealLength.add("Pada sebuah model rumah, lebar kamar adalah #num1? cm. "
				+ "Jika skala model tersebut adalah #scale1?, "
				+ "berapakah lebar aslinya dalam satuan meter?");
		sceFindRealLength.add("Rancangan dapur #orang1? memiliki lebar #num1? cm "
				+ "pada denah dengan skala #scale1?, "
				+ "berapakah lebar aslinya dalam satuan meter?");
		sceFindRealLength.add("#orang1? memiliki replika truk dengan panjang #num1? cm. "
				+ "Jika replika tersebut memiliki skala #scale1?, "
				+ "berapakah ukuran aslinya dalam satuan meter?");
		sceFindModelLength.add("Sebuah rumah memiliki kamar dengan lebar #num2? meter. "
				+ "Jika skala denah rumah tersebut adalah #scale1?, "
				+ "berapakah lebarnya pada model dalam satuan cm?");
		int rndFScale = CommonFunctionAndValues.getRandomInt(0, sceFindScale.size());
		int rndFMLength = CommonFunctionAndValues.getRandomInt(0, sceFindModelLength.size());
		int rndRMLength = CommonFunctionAndValues.getRandomInt(0, sceFindRealLength.size());
		String s="";
		switch (type) {
		case findScale:
			s = sceFindScale.get(rndFScale);
			break;
		case findModelLength:
			s = sceFindModelLength.get(rndFMLength);
			break;
		case findRealLength:
			s = sceFindRealLength.get(rndRMLength);
			break;
		default:
			break;
		}
		return s;
	}
	public Set<String> buildChoicesFromScale(int[][] scales, TYPE type, int mLength, double rLength){
		Set<String> s = new HashSet<String>();
		for (int[] is : scales) {
			switch (type) {
			case findScale:
				//System.out.println("lewat 1");
				s.add(is[0]+":"+is[1]);
				break;
			case findModelLength:
				//System.out.println("lewat 2");
				double realLengthInCm = rLength * 100;
				double modelLength = realLengthInCm / is[1];
				String ml = new DecimalFormat("##.##").format(modelLength);
				s.add(String.valueOf(ml));
				break;
			case findRealLength:
				//System.out.println("lewat 3");
				double realLength =(double)is[1] * mLength;
				double realLengthInMeter = realLength / 100; 
				String rl = new DecimalFormat("##.##").format(realLengthInMeter);
				s.add(String.valueOf(rl));
				break;
			default:
				break;
			}
			
		}
		return s;
	}
}
