package com.alza.quiz.qfactory.fraction;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.alza.common.math.Fraction;
import com.alza.common.math.MathUtils;
import com.alza.quiz.model.MultipleChoiceQuiz;
import com.alza.quiz.model.Quiz;
import com.alza.quiz.model.QuizLevel;
import com.alza.quiz.qfactory.IQuestionFactory;
import com.alza.quiz.util.CommonFunctionAndValues;

public class RatioQuestionFactory implements IQuestionFactory{
	private static enum TYPE  {
		findFromPct, findFromRatio, describeRatio
	}
	
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
			MultipleChoiceQuiz q = new MultipleChoiceQuiz();
			if (i % 3 == 1){
				q = generateTypeC();
				q.setDifficultyLevel(QuizLevel.SEDANG);
				q.setLessonSubcategory("Bentuk persen, mencari jumlah jika persentase dan total diketahui");
			} else if (i % 3 == 2){
				q = generateTypeB();
				q.setDifficultyLevel(QuizLevel.SEDANG);
				q.setLessonSubcategory("Perbandingan, mencari jumlah jika nilai perbandingan dan total diketahui");
			} else {
				q = generateTypeA();
				q.setDifficultyLevel(QuizLevel.MUDAH);
				q.setLessonSubcategory("Mendeskripsikan perbandingan");
			}
			q.setLessonClassifier("Matematika SD");
			q.setLessonCategory("Pecahan");
			q.setLessonGrade(5);
			ql.add(q);
		}
		
		return ql;
	}
	
	private MultipleChoiceQuiz generateTypeC() {
		MultipleChoiceQuiz q = new MultipleChoiceQuiz();
		TYPE t = TYPE.findFromPct;
		String sce = getRandomScenario(t);
		int total, partOfTotal, partOfTotal2;
		partOfTotal = CommonFunctionAndValues.getRandomInt(24, 51);
		partOfTotal2 = CommonFunctionAndValues.getRandomInt(9, 51);
		total = partOfTotal + partOfTotal2;
		Fraction f = new Fraction(partOfTotal, total);
		String pct = f.getPercentage();
		sce = sce.replace("#total?", String.valueOf(total));
		sce = sce.replace("#pct1?", pct);
		sce = CommonFunctionAndValues.buildScenario(sce);
		q.setQuestion(sce);
		q.setCorrectAnswer(String.valueOf(partOfTotal2));
		q.setChoices(total,partOfTotal,partOfTotal2,partOfTotal*2,partOfTotal2*2);
		
		return q;
	}
	
	private MultipleChoiceQuiz generateTypeB() {
		String sce;
		MultipleChoiceQuiz q = new MultipleChoiceQuiz();
		TYPE t = TYPE.findFromRatio;
		int rat1,rat2,tot,tot1,tot2,multip,min,max;
		min = 2; max = 13;
		do {
			rat1 = CommonFunctionAndValues.getRandomInt(min, max);
			rat2 = CommonFunctionAndValues.getRandomInt(min, max);
		} while (MathUtils.findGCDDjikstra(rat1, rat2)>1);
		multip = CommonFunctionAndValues.getRandomInt(min, max);
		tot1 = rat1 * multip;
		tot2 = rat2 * multip;
		tot = tot1+tot2;
		sce = getRandomScenario(t);
		sce = sce.replace("#total?", String.valueOf(tot));
		sce = sce.replace("#rat1?", String.valueOf(rat1));
		sce = sce.replace("#rat2?", String.valueOf(rat1));
		sce = CommonFunctionAndValues.buildScenario(sce);
		q.setQuestion(sce);
		q.setCorrectAnswer(String.valueOf(tot1));
		q.setChoices(tot1,tot,rat1,tot2,multip);
		return q;
	}
	
	private MultipleChoiceQuiz generateTypeA() {
		MultipleChoiceQuiz q = new MultipleChoiceQuiz();
		TYPE t = TYPE.describeRatio;
		String sce = getRandomScenario(t);
		int a, b, c, min, max, gcd;
		min = 10; max =51;
		do {
			a = CommonFunctionAndValues.getRandomInt(min, max);
			b = CommonFunctionAndValues.getRandomInt(min, max);
			c = CommonFunctionAndValues.getRandomInt(min, max);
			gcd = MathUtils.findGCD(a,b,c);
		} while (gcd==1 || a==b || b==c || a==c);
		sce = sce.replace("#part1?", String.valueOf(a));
		sce = sce.replace("#part2?", String.valueOf(b));
		sce = sce.replace("#part3?", String.valueOf(c));
		sce = CommonFunctionAndValues.buildScenario(sce);
		q.setQuestion(sce);
		q.setCorrectAnswer(a / gcd+":"+b/gcd+":"+c/gcd);
		q.setChoices(a / gcd+":"+b/gcd+":"+c/gcd,
				b / gcd+":"+a/gcd+":"+c/gcd,
				(a+b) / gcd+":"+(b+c)/gcd+":"+(c+a)/gcd,
				c / gcd+":"+a/gcd+":"+b/gcd);
		
		return q;
	}
	
	public String getRandomScenario(TYPE type){
		List<String> sceFindFromPct = new ArrayList<String>();
		List<String> sceFindRatio = new ArrayList<String>();
		List<String> sceFindFromRatio = new ArrayList<String>();
		sceFindFromPct.add("#Orang1? memiliki kandang ayam dirumahnya. "
				+ "Ia memiliki #total? ekor ayam. #pct1? ayam miliknya adalah ayam pedaging, "
				+ "sementara sisanya adalah ayam petelur. "
				+ "Berapakah jumlah ayam petelur yang ia miliki?");
		sceFindFromPct.add("Luas lahan pekarangan rumah #orang1? adalah #total? meter persegi. "
				+ "#pct1? digunakan untuk bercock tanam, "
				+ "sisanya dibiarkan kosong untuk tempat bermain anaknya. "
				+ "Berapakah meter persegi luas lahan bermain yang tersedia untuk anak #orang1?");
		sceFindFromPct.add("Anak laki-laki di sekolah #orang1? berjumlah #total? anak "
				+ "#pct1? gemar bermain bola, sisanya gemar bermain basket "
				+ "Berapakah jumlah anak yang gemar bermain basket?");
		sceFindFromRatio.add("Jumlah anak yang suka wortel dibandingkan dengan yang suka"
				+ " brokoli adalah #rat1? berbading #rat2?. Jika jumlah anak adalah #total? "
				+ " berapakah jumlah anak yang suka brokoli?");
		sceFindFromRatio.add("#orang1 memiliki #total? ekor kelinci. "
				+ "Jumlah kelinci berwarna putih berbanding warna lainnya "
				+ "adalah #rat1? berbanding #rat2? "
				+ "Berapakah jumlah kelinci putih #orang1??");
		sceFindFromRatio.add("Berapakah jumlah guru perempuan di sekolah #orang1? "
				+ "jika perbandingan "
				+ " antara jumlah guru laki-laki dengan perempuan adalah #rat1? berbanding #rat2?"
				+ " dan jumlah semua guru adalah #total?.");
		sceFindRatio.add("#part1? siswa mengikuti ekstrakurikuler Pramuka "
				+ "#part2? siswa mengikuti ekstrakurikuler PMR "
				+ "sementara #part3? siswa mengikuti ekstrakurikuler atletik. "
				+ "Perbandingan antara siswa yang mengikuti pramuka, PMR dan atletik adalah?");
		sceFindRatio.add("Jumlah siswa yang menyukai olahraga bulutangkis adalah #part1?, "
				+ "sementara sepakbola adalah #part2?, dan voli adalah #part3?. "
				+ "Perbandingan jumlah siswa penggemar bulutangkis, sepakbola dan voli "
				+ "dapat dinyatakan dengan nilai perbandingan?");
		sceFindRatio.add("Berapakah perbandingan jumlah ikan nila, ikan gurame dan ikan mas "
				+ "pada kolam #orang1? jika jumlah ikan tersebut masing-masing "
				+ "#part1?, #part2? dan #part3??");
		
		int rndFindFromPct = CommonFunctionAndValues.getRandomInt(0, sceFindFromPct.size());
		int rndFindFromRatio = CommonFunctionAndValues.getRandomInt(0, sceFindFromRatio.size());
		int rndDescRatio = CommonFunctionAndValues.getRandomInt(0, sceFindRatio.size());
		String s="";
		switch (type) {
		case findFromPct:
			s = sceFindFromPct.get(rndFindFromPct);
			break;
		case findFromRatio:
			s = sceFindFromRatio.get(rndFindFromRatio);
			break;
		case describeRatio:
			s = sceFindRatio.get(rndDescRatio);
			break;
		default:
			break;
		}
		return s;
	}
	
	private Set<String> buildChoices(String... args){
		Set<String> choices = new HashSet<String>(); 
		for (String string : args) {
			choices.add(string);
		}
		return choices;
	}
}
