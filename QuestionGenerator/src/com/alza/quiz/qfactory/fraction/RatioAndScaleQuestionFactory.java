package com.alza.quiz.qfactory.fraction;

import java.util.ArrayList;
import java.util.List;

import com.alza.common.math.Fraction;
import com.alza.quiz.model.MultipleChoiceQuiz;
import com.alza.quiz.model.Quiz;
import com.alza.quiz.model.QuizLevel;
import com.alza.quiz.qfactory.IQuestionFactory;
import com.alza.quiz.util.CommonFunctionAndValues;

public class RatioAndScaleQuestionFactory implements IQuestionFactory {
	private static enum TYPE {
		findScale,findModelLength,findRealLength
	};
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
	
	private MultipleChoiceQuiz generateTypeA(int i) {
		MultipleChoiceQuiz q = new MultipleChoiceQuiz();
		// for home layout scale (less than 10 metres)
		int[][] miniScales= {
				{1,100},{1,150},{1,200},{1,250}	
		};
		// for district layout scale
		int[][] medScales= {
				{1,10000},{1,15000},{1,20000},{1,25000}	
		};
		// for large map scale
		int[][] largeMapScales= {
				{1,1000000},{1,1500000},{1,2000000},{1,2500000}	
		};
		int denom = CommonFunctionAndValues.getRandomInt(11, 41);
		int a1,a2;
		do {
			a1 = CommonFunctionAndValues.getRandomInt(5, 17);
			a2 = CommonFunctionAndValues.getRandomInt(5, 17);
		} while (!(denom > a1 && denom > a2 && (a1+a2)<=denom && a1>a2));
		Fraction f1 = new Fraction(a1, denom);
		Fraction f2 = new Fraction(a2, denom);
		Fraction result;
		if (i % 2 == 0){
			result = f1.getResultWhenMultipliedBy(f2);
			q.setQuestion("Hitung hasil perkalian dari pecahan "
					+f1.toString()+" dan pecahan "+f2.toString());
		} else{
			result = f1.getResultWhenDividedBy(f2);
			q.setQuestion("Hitung hasil dari pecahan "
					+f1.toString()+" dibagi pecahan "+f2.toString());
		}
		q.setCorrectAnswer(result.toString());
//		q.setChoices(buildChoices(f1,f2,result));
		return q;
	}
	public String getRandomScaleScenario(TYPE type){
		List<String> sceFindScale = new ArrayList<String>();
		List<String> sceFindRealLength = new ArrayList<String>();
		List<String> sceFindModelLength = new ArrayList<String>();
		sceFindScale.add("Pada sebuah denah, lebar sebuah ruangan adalah #num1? cm, "
				+ "sedangkan aslinya adalah #num2 meter."
				+ "Berapakah skala denah tersebut?");
		sceFindScale.add("Kamar #orang1? memiliki ukuran panjang #num2? meter. "
				+ "Pada denah yang ia buat, panjangnya menjadi #num1 cm"
				+ "Skala denah yang dibuat #orang1? adalah?");
		sceFindScale.add("Tinggi rumah pada gambar konstruksi adalah #num1? cm. "
				+ "Tinggi aslinya adalah #num2? meter"
				+ "Skala denah pada gambar adalah?");
		sceFindModelLength.add("Lebar kolam renang rumah #orang1? adalah #num2? meter. "
				+ "Pada denah dengan skala #scale1? berapakah lebar kolam tersebut?");
		sceFindModelLength.add("Teras rumah #orang1? berbentuk persegi dengan panjang #num2? meter. "
				+ "Pada denah dengan skala #scale1? berapakah panjang teras tersebut?");
		sceFindModelLength.add("Lebar garasi pada rumah #elder1? adalah #num2? meter"
				+ "Pada denah dengan skala #scale1? berapakah lebar garasi tersebut?");
		sceFindRealLength.add("Pada sebuah model rumah, lebar kamar adalah #num1 cm. "
				+ "Jika skala model tersebut adalah #scale1?, "
				+ "berapakah lebar aslinya dalam satuan meter?");
		sceFindRealLength.add("Rancangan dapur #orang1? memiliki lebar #num1 cm "
				+ "pada denah dengan skala #scale1?, "
				+ "berapakah lebar aslinya dalam satuan meter?");
		sceFindRealLength.add("#orang1? memiliki replika truk dengan panjang #num1 cm. "
				+ "Jika replika tersebut memiliki skala #scale1?, "
				+ "berapakah ukuran aslinya dalam satuan meter?");
		sceFindModelLength.add("Sebuah rumah memiliki kamar dengan lebar #num1 meter. "
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
}
