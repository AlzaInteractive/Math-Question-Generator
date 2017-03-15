package com.alza.quiz.qfactory.kpk;

import com.alza.common.math.MathUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import com.alza.quiz.model.Quiz;
import com.alza.quiz.model.QuizLevel;
import com.alza.quiz.model.MultipleChoiceQuiz;
import com.alza.quiz.qfactory.IQuestionFactory;
import com.alza.quiz.qfactory.fraction.FindGreatestFractionQuestionFactory;

public class TwoNumKPKQuestionFactory implements IQuestionFactory {
	protected QuizLevel quizLevel = QuizLevel.MUDAH;
	protected int[] pairs;
	protected int correctAnswer;
	protected List<String> choices;

	public TwoNumKPKQuestionFactory(){

	}
	public MultipleChoiceQuiz generateQuiz() {
		return generateQuiz(QuizLevel.MUDAH);
	}
	@Override
	public MultipleChoiceQuiz generateQuiz(QuizLevel quizLevel) {
		this.quizLevel = quizLevel;
		generatePair();
		solvePair();
		choices = new ArrayList<>();
		addChoices(correctAnswer);
		generateChoices();
		MultipleChoiceQuiz q = new MultipleChoiceQuiz();
		q.setLessonGrade(4);
		q.setDifficultyLevel(quizLevel);
		q.setQuestion("Kelipatan Persekutuan Terkecil (KPK) dari bilangan " + pairs[0]+" & "+pairs[1]+" adalah?");
		q.setCorrectAnswer(String.valueOf(correctAnswer));
		q.setChoices(choices);
		q.setLessonClassifier("Matematika SD");
		q.setLessonCategory("KPK");
		q.setLessonSubcategory("KPK dua bilangan");
		q.setSubCategoryOrder(0);
		return q;
	}

	public List<Quiz> generateQuizList() {
		List<Quiz> quizList = new ArrayList<>();
		for (int i=0; i <2; i++){
			quizList.add(generateQuiz(QuizLevel.MUDAH));
		}
		/*for (int i=0; i <2; i++){
			quizList.add(generateQuiz(QuizLevel.SEDANG));
		}*/
		return quizList;
	}

	protected void generateChoices() {
		List<Integer> cAlts = new ArrayList<Integer>();
		cAlts.add(pairs[0]*2);
		cAlts.add(pairs[0]*3);
		cAlts.add(pairs[1]*2);
		cAlts.add(pairs[1]*3);
		cAlts.add(pairs[0]*pairs[1]);
		cAlts.add(correctAnswer * 2);
		//cAlts.add(correctAnswer * ThreadLocalRandom.current().nextInt(2, 6 + 1));
		cAlts.add(correctAnswer * 3);
		cAlts.add(correctAnswer + pairs[0]);
		cAlts.add(correctAnswer + pairs[1]);
		cAlts.add(correctAnswer + pairs[1] + pairs[0]);
		cAlts.add(MathUtils.findGCDDjikstra(pairs[0], pairs[1]));
		Collections.shuffle(cAlts);
		int i = 0;
		while(choices.size()<5&&i<12){
			addChoices(cAlts.get(i));
			i++;
		}
		Collections.shuffle(choices);
	}
	protected void addChoices(int choice){
		if (!choices.contains(String.valueOf(choice))){
			choices.add(String.valueOf(choice));
		}

	}
	protected void solvePair() {
		correctAnswer = MathUtils.findLCM(pairs[0], pairs[1]);
	}
	private void generatePair() {
		switch (quizLevel) {
		case SEDANG:
			generatePairsOfTwo();
			break;

		default:
			generatePairsOfTwo();
			break;
		}
		
	}
	private void generatePairsOfTwo() {
		int min=3,max=12; // MUDAH
		if (quizLevel.equals(QuizLevel.SEDANG)){
			min = 6;
			max = 20;
		}
		int firstNumber, secondNumber, gcd;
		boolean repeat; 
		do {
			firstNumber = ThreadLocalRandom.current().nextInt(min, max + 1);
			secondNumber = ThreadLocalRandom.current().nextInt(min, max + 1);
			gcd = MathUtils.findGCD(firstNumber,secondNumber);
			repeat = false;
			//System.out.println("Generated: "+firstNumber+" "+secondNumber+" "+(firstNumber==secondNumber));
			if (firstNumber > secondNumber){
				int temp;
				temp = firstNumber;
				firstNumber = secondNumber;
				secondNumber = temp;
			}
			if (firstNumber == secondNumber||
					firstNumber==10||
					secondNumber==10||
					gcd==1){
				repeat = true;
			}
			if (quizLevel == QuizLevel.SEDANG){
				if (secondNumber % firstNumber == 0){
					repeat = true;
				}
			}
		} while 
			(repeat);
		
		this.pairs = new int[2];
		this.pairs[0] = firstNumber;
		this.pairs[1] = secondNumber;
	}
	
}
