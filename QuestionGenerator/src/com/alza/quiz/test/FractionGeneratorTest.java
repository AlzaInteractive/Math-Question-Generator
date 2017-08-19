package com.alza.quiz.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.alza.quiz.model.QuizLevel;
import com.alza.quiz.model.MultipleChoiceQuiz;
import com.alza.quiz.model.Quiz;
import com.alza.quiz.qfactory.IQuestionFactory;
import com.alza.quiz.qfactory.fraction.FractionDescribingRatio;
import com.alza.quiz.qfactory.fraction.FractionRealWorldProblemDiscount;
import com.alza.quiz.qfactory.fraction.FractionRealWorldProblemFindFromPercentage;
import com.alza.quiz.qfactory.fraction.FractionPickGreatest;
import com.alza.quiz.qfactory.fraction.FractionAddSubtract;
import com.alza.quiz.qfactory.fraction.FractionConvertToDecimal;
import com.alza.quiz.qfactory.fraction.FractionEqualityTypeB;
import com.alza.quiz.qfactory.fraction.FractionMultiplication;
import com.alza.quiz.qfactory.fraction.FractionConvertToPercentage;
import com.alza.quiz.qfactory.fraction.FractionMixedFormOperation;
import com.alza.quiz.qfactory.fraction.FractionRealWorldProblemFindFromRatio;
import com.alza.quiz.qfactory.fraction.FractionRealWorldProblemLeftover;
import com.alza.quiz.qfactory.fraction.FractionRealWorldProblemProportions;
import com.alza.quiz.qfactory.fraction.FractionRealWorldProblemScale;
import com.alza.quiz.qfactory.fraction.FractionRealWorldProblemTaxTips;
import com.alza.quiz.qfactory.fraction.FractionSimplify;
import com.alza.quiz.qfactory.kpk.TwoNumKPKQuestionFactory;
import com.alza.quiz.qfactory.kpk.WhichDayScenarioKPKQuestionFactory;
import com.alza.quiz.qfactory.kpk.WhichHourScenarioKPKQuestionFactory;

public class FractionGeneratorTest {
	public static void main(String[] args) {
		//basicGenerator();
		//whichDayGenerator();
		//fractionEquality();
		final long startTime = System.currentTimeMillis();
		int testCount = 1;
		for (int i = 0; i < testCount; i++) {
			//System.out.println(i);
			fractionEquality();
		}
		final long endTime = System.currentTimeMillis();
		System.out.println("Total running time (ms) = " +(endTime-startTime));
	}

	protected static void basicGenerator() {
		TwoNumKPKQuestionFactory f= new TwoNumKPKQuestionFactory();
		List<MultipleChoiceQuiz> lq = new ArrayList<MultipleChoiceQuiz>();
		for (int i = 0; i < 5; i++) {
			lq.add(f.generateQuiz(QuizLevel.MUDAH));
		}
		for (int i = 0; i < 5; i++) {
			lq.add(f.generateQuiz(QuizLevel.SEDANG));
		}
		for (MultipleChoiceQuiz q : lq) {
			System.out.println("------------------------------");
			System.out.println("Question : " + q.getQuestion());
			System.out.println("Answer : "+ q.getCorrectAnswer());
		}
	}
	protected static void whichDayGenerator(){
		IQuestionFactory qf = new WhichDayScenarioKPKQuestionFactory();
		List<Quiz> ql = new ArrayList<>();
		for (int i=1;i<=4;i++){
			Quiz q = qf.generateQuiz();
			ql.add(q);
		}
		for (Quiz q : ql){
			System.out.println("------------------------------");
			System.out.println("Question : " + q.getQuestion());
			System.out.println("Answer : "+ q.getCorrectAnswer());
		}
	}
	private static void fractionEquality(){
		List<IQuestionFactory> lqf = new ArrayList<IQuestionFactory>();
		lqf.add(new FractionEqualityTypeB());
		lqf.add(new FractionPickGreatest());
		/**
		lqf.add(new FractionSimplify());
		lqf.add(new FractionAddSubtract());
		lqf.add(new FractionMultiplication());
		lqf.add(new FractionMixedFormOperation());
		lqf.add(new FractionConvertToDecimal());
		lqf.add(new FractionConvertToPercentage());
		lqf.add(new ScenarioBasedFractionQuestionFactory());
		lqf.add(new FractionRealWorldProblemFindFromPercentage());
		lqf.add(new FractionRealWorldProblemFindFromRatio());
		lqf.add(new FractionRealWorldProblemDiscount());
		lqf.add(new FractionRealWorldProblemTaxTips()); 
		lqf.add(new FractionRealWorldProblemScale());
		lqf.add(new FractionDescribingRatio());
		lqf.add(new FractionRealWorldProblemProportions());
		lqf.add(new FractionRealWorldProblemLeftover()); **/
		
		
		List<Quiz> ql = new ArrayList<Quiz>();
		for (IQuestionFactory qf : lqf) {
			ql.addAll(qf.generateQuizList());
			
		}
		Collections.sort(ql);
		for (Quiz q : ql) {
			System.out.println("------------------------------");
			System.out.println("Grade : "+q.getLessonGrade());
			System.out.println("Subcategory :" +q.getLessonSubcategory());
			System.out.println("Question : " + q.getQuestion());
			if (q instanceof MultipleChoiceQuiz) {
				MultipleChoiceQuiz mq = (MultipleChoiceQuiz) q;
				System.out.println("Choices : "+ String.join(" , ", mq.getChoices()));
			}
			System.out.println("Answer : "+ q.getCorrectAnswer());
		}
		System.out.println("Jumlah soal : "+ql.size());
	}
}
