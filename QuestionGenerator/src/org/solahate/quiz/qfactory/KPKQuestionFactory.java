package org.solahate.quiz.qfactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.solahate.quiz.model.DifficultyLevel;
import org.solahate.quiz.model.Quiz;
import org.solahate.util.math.MathUtils;

public class KPKQuestionFactory implements IQuestionFactory {
	private DifficultyLevel difficultyLevel = DifficultyLevel.EASY;
	private int[] pairs;
	private int correctAnswer;
	private List<String> choices;
	@Override
	public Quiz generateQuiz() {
		return generateQuiz(DifficultyLevel.EASY);
	}
	@Override
	public Quiz generateQuiz(DifficultyLevel difficultyLevel) {
		this.difficultyLevel = difficultyLevel;
		generatePair();
		solvePair();
		generateChoices();
		Quiz q = new Quiz();
		q.setQuestion("Carilah KPK dari bilangan " + Arrays.toString(pairs));
		q.setCorrectAnswer(String.valueOf(correctAnswer));
		q.setChoices(choices);
		return q;
	}
	private void generateChoices() {
		// TODO Auto-generated method stub
		List<Integer> cAlts = new ArrayList<Integer>();
		cAlts.add(correctAnswer * 2);
		cAlts.add(correctAnswer * ThreadLocalRandom.current().nextInt(2, 6 + 1));
		cAlts.add(correctAnswer * 3);
		cAlts.add(correctAnswer + pairs[0]);
		cAlts.add(correctAnswer + pairs[1]);
		cAlts.add(correctAnswer + pairs[1] + pairs[0]);
		cAlts.add(MathUtils.findGCDDjikstra(pairs[0], pairs[1]));
		Collections.shuffle(cAlts);
		choices = new ArrayList<String>();
		choices.add(String.valueOf(correctAnswer));
		for (int i = 0; i < 4; i++) {
			choices.add(String.valueOf(cAlts.get(i)));
		}
		Collections.shuffle(choices);
	}
	private void solvePair() {
		correctAnswer = MathUtils.findLCM(pairs[0], pairs[1]);
	}
	private void generatePair() {
		switch (difficultyLevel) {
		case MODERATE:
			generatePairsOfTwo();
			break;

		default:
			generatePairsOfTwo();
			break;
		}
		
	}
	private void generatePairsOfTwo() {
		int min=3,max=9; // EASY
		if (difficultyLevel.equals(DifficultyLevel.MODERATE)){
			min = 6;
			max = 20;
		}
		int firstNumber, secondNumber;
		boolean repeat; 
		do {
			firstNumber = ThreadLocalRandom.current().nextInt(min, max + 1);
			secondNumber = ThreadLocalRandom.current().nextInt(min, max + 1);
			repeat = false;
			if (firstNumber == secondNumber){
				repeat = true;
			}
			if (difficultyLevel==DifficultyLevel.MODERATE){
				//make sure that higher number is not a multiple of lower number
				int upper,lower;
				if (firstNumber < secondNumber ){
					upper = secondNumber;
					lower = firstNumber;
				} else {
					upper = firstNumber;
					lower = secondNumber;
				}
				if (upper % lower ==0){
					repeat = true;
				}
			}
		} while 
			(repeat);
		
		this.pairs = new int[2];
		//swap to make sure first number always < second number
		if (firstNumber > secondNumber){
			int temp;
			temp = secondNumber;
			firstNumber = secondNumber;
			secondNumber = temp;
		}
		this.pairs[0] = firstNumber;
		this.pairs[1] = secondNumber;
	}
	
}
