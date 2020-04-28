package com.alza.quiz.qfactory.integer;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.concurrent.ThreadLocalRandom;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import com.alza.quiz.model.MultipleChoiceQuiz;
import com.alza.quiz.model.Quiz;
import com.alza.quiz.model.QuizLevel;
import com.alza.quiz.qfactory.IQuestionFactory;

public class SquareRootPhytagorean implements IQuestionFactory{
	private final static String MJXTAG ="$$"; 
	Locale loc;
	ResourceBundle bundle;
	public SquareRootPhytagorean(Locale loc){
		this.loc = loc;
		initStringFromLocale();
	}
	public SquareRootPhytagorean(){
		this.loc = new Locale("in", "ID");
		initStringFromLocale();
	}
	private void initStringFromLocale(){
		bundle = ResourceBundle.getBundle("lang.langbundle", loc);
	}
	int numOfQuestion = 4;
	int[][] bounds = {
			{2,5},{2,5}
	};
	String[][] expression = {
			{"sqrt(a^2+b^2)","\\sqrt{a^2+b^2}"},
			{"sqrt(c^2-b^2)","\\sqrt{c^2-b^2}"},
			{"sqrt(c^2-a^2)","\\sqrt{c^2-a^2}"}
			
	};
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
		List<Quiz> lq = new ArrayList<Quiz>();
		for (int i= 0;i<numOfQuestion;i++){
			int idx;
			if (i<bounds.length){
				idx = i;
			} else {
				idx = i % bounds.length; 
			}
			//System.out.println("index "+idx);
			int a=0,b=0,c=0;
			int m,n;
			do {
				m = ThreadLocalRandom.current().nextInt(bounds[idx][0], 
						bounds[idx][1]);
				n = ThreadLocalRandom.current().nextInt(bounds[idx][0], 
						bounds[idx][1]);
				//( m2 – n2 )2 + (2 m n)2 = ( m2 + n2 )2
				a = (m*m) - (n*n);
				b = 2 * m * n;
				c = (m*m + n*n);
				
			} while (m<=n);
			MultipleChoiceQuiz q = new MultipleChoiceQuiz();
			
			Expression e = new ExpressionBuilder(expression[idx][0])
				.variables("a","b","c")
				.build()
				.setVariable("a", a)
				.setVariable("b", b)
				.setVariable("c", c);
			int rslt = (int) e.evaluate();
			
			String question = MJXTAG+expression[idx][1].replace("*", "x")+MJXTAG;
			question = question.replace("a", String.valueOf(a));
			question = question.replace("b", String.valueOf(b));
			question = question.replace("c", String.valueOf(c));
			//question = question.replace("c^2", String.valueOf(c*c));
			/**
			 * {"sqrt(a^2+b^2)","\\sqrt{a^2+b^2}"},
			{"sqrt(c^2-b^2)","\\sqrt{c^2-b^2}"},
			{"sqrt(c^2-a^2)","\\sqrt{c^2-a^2}"}
			 */
			int idx2 = ThreadLocalRandom.current().nextInt();
			if (idx2 % 3 == 0) {
				q.addChoice(rslt,c-1);
			} else if (idx2 % 3 == 1) {
				q.addChoice(rslt,c+1);
			} else {
				q.addChoice(rslt,c+2);
			}
			q.setQuestion(question);
			q.setCorrectAnswer(String.valueOf(rslt));
			q.setDifficultyLevel(QuizLevel.MUDAH);
			q.setLessonSubcategory(bundle.getString("integer.squarerootphyt"));
			q.setLessonClassifier(bundle.getString("mathelementary"));
			q.setLessonGrade(5);
			q.setSubCategoryOrder(8);
			q.setLocalGeneratorOrder(idx);
			q.setLessonCategory(bundle.getString("integer"));
			q.setLocale(loc);
			lq.add(q);
		}
		
		return lq;
	}

	@Override
	public List<Quiz> generateQuizList(int numOfQuestion) {
		this.numOfQuestion = numOfQuestion;
		return generateQuizList();
	}

}
