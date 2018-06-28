package com.alza.quiz.qfactory.lcmgcd;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import com.alza.common.math.MathUtils;
import com.alza.quiz.model.MultipleChoiceQuiz;
import com.alza.quiz.model.Quiz;
import com.alza.quiz.model.QuizLevel;
import com.alza.quiz.qfactory.IQuestionFactory;

public class LCMTwoNumQuestionFactory implements IQuestionFactory {
	protected QuizLevel quizLevel = QuizLevel.MUDAH;
	protected static int numq = 4;
	Locale loc;
	ResourceBundle bundle;
	
	public LCMTwoNumQuestionFactory(){
		this.loc = new Locale("in", "ID");
		initStringFromLocale();
	}
	
	public LCMTwoNumQuestionFactory(Locale loc) {
		this.loc = loc;
		initStringFromLocale();
	}
	
	private void initStringFromLocale(){
		bundle = ResourceBundle.getBundle("lang.langbundle", loc);
	}
	
	public MultipleChoiceQuiz generateQuiz() {
		return generateQuiz(QuizLevel.MUDAH);
	}
	@Override
	public MultipleChoiceQuiz generateQuiz(QuizLevel quizLevel) {
		this.quizLevel = quizLevel;
		int[] pairs = generatePairsOfTwo();
		String correctAnswer = String.valueOf(MathUtils.findLCM(pairs)); 
		generateChoices(pairs);
		MultipleChoiceQuiz q = new MultipleChoiceQuiz();
		q.addChoice(correctAnswer);
		q.setLessonGrade(4);
		q.setDifficultyLevel(quizLevel);
		q.setQuestion(bundle.getString("lcmgcd.question.lcmof") +" "+ pairs[0]+" & "+pairs[1]+" ?");
		q.setCorrectAnswer(String.valueOf(correctAnswer));
		q.setChoices(generateChoices(pairs));
		q.setLessonCategory(bundle.getString("lcmgcd"));
		q.setLessonSubcategory(bundle.getString("lcmgcd.subcategory.lcm"));
		q.setLessonClassifier(bundle.getString("mathelementary"));
		q.setSubCategoryOrder(2);
		q.setLocale(loc);
		return q;
	}

	public List<Quiz> generateQuizList() {
		List<Quiz> quizList = new ArrayList<>();
		for (int i=0; i <numq; i++){
			quizList.add(generateQuiz(QuizLevel.MUDAH));
		}
		
		return quizList;
	}

	protected Set<String> generateChoices(int[] pairs) {
		int lcm = MathUtils.findLCM(pairs);
		int gcd = MathUtils.findGCD(pairs);
		Set<String> choices = new HashSet<String>();
		choices.add(String.valueOf(pairs[0]*2));
		choices.add(String.valueOf(pairs[1]*2));
		choices.add(String.valueOf(pairs[0]*3));
		choices.add(String.valueOf(pairs[1]*3));
		choices.add(String.valueOf(lcm));
		choices.add(String.valueOf(gcd));
		return choices;	
	}

	private int[] generatePairsOfTwo() {
		int min=3,max=20; // MUDAH
		int firstNumber, secondNumber, gcd;
		do {
			firstNumber = ThreadLocalRandom.current().nextInt(min, max + 1);
			secondNumber = ThreadLocalRandom.current().nextInt(min, max + 1);
			gcd = MathUtils.findGCD(firstNumber,secondNumber);
			if (firstNumber > secondNumber){
				int temp;
				temp = firstNumber;
				firstNumber = secondNumber;
				secondNumber = temp;
			}
		} while 
			(firstNumber == secondNumber||
			firstNumber==10||secondNumber==10||
			secondNumber%firstNumber==0||
			gcd == 1);
		
		int[] pairs = new int[2];
		pairs[0] = firstNumber;
		pairs[1] = secondNumber;
		return pairs;
	}
	@Override
	public List<Quiz> generateQuizList(int numOfQuestion) {
		numq = numOfQuestion;
		return generateQuizList();
	}
	
}
