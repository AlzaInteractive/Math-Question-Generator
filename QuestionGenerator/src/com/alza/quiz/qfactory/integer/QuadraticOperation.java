package com.alza.quiz.qfactory.integer;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.concurrent.ThreadLocalRandom;

import com.alza.quiz.model.MultipleChoiceQuiz;
import com.alza.quiz.model.Quiz;
import com.alza.quiz.model.QuizLevel;
import com.alza.quiz.qfactory.IQuestionFactory;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class QuadraticOperation implements IQuestionFactory{
	private final static String MJXTAG ="$$"; 
	Locale loc;
	ResourceBundle bundle;
	public QuadraticOperation(Locale loc){
		this.loc = loc;
		initStringFromLocale();
	}
	public QuadraticOperation(){
		this.loc = new Locale("in", "ID");
		initStringFromLocale();
	}
	private void initStringFromLocale(){
		bundle = ResourceBundle.getBundle("lang.langbundle", loc);
	}
	int numOfQuestion = 3;
	int[][] bounds = {
			{5,10},{10,15},{15,20},{20,33}
	};
	String[][] expression = {
			{"a^2","a^2"}, 
			{"a^2 + b^2","a^2 + b^2"},
			{"a^2 - b^2","a^2 - b^2"},
			{"(a+b)^2","{(a+b)}^2"},
			{"(a-b)^2","{(a-b)}^2"},
			{"a^2 + b^2 + c^2","a^2 + b^2 +c^2"},
			{"a^2 + (b-c)^2","a^2 + {b-c}^2"},
			{"(a-b)^2 + c^2","{a-b}^2 + c^2"}
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
			do {
				a = ThreadLocalRandom.current().nextInt(bounds[idx][0], 
						bounds[idx][1]);
				b = ThreadLocalRandom.current().nextInt(bounds[idx][0], 
						bounds[idx][1]);
				c = ThreadLocalRandom.current().nextInt(bounds[idx][0], 
						bounds[idx][1]);
			} while (a<=b||a<=c||b<=c);
			MultipleChoiceQuiz q = new MultipleChoiceQuiz();
			
			Expression e = new ExpressionBuilder(expression[idx][0])
				.variables("a","b","c")
				.build()
				.setVariable("a", a)
				.setVariable("b", b)
				.setVariable("c", c);
			int rslt = (int) e.evaluate();
			/**
			 * {"a^2","a^2"}, 
			{"a^2 + b^2","a^2 + b^2"},
			{"a^2 - b^2","a^2 - b^2"},
			{"(a+b)^2","{(a+b)}^2"},
			{"(a-b)^2","{(a-b)}^2"},
			{"a^2 + b^2 + c^2","a^2 + b^2 +c^2"},
			{"a^2 + (b-c)^2","a^2 + {b-c}^2"},
			{"(a-b)^2 + c^2","{a-b}^2 + c^2"}
			 */
			if (idx == 0) {
				q.addChoice(rslt,a+a);
			} else if (idx == 1) {
				q.addChoice(rslt,(a+b)*(a+b));
			} else if (idx == 2) {
				q.addChoice(rslt,(a-b)*(a-b));
			} else if (idx == 3) {
				q.addChoice(rslt,a*a+b*b);
			} else if (idx == 4) {
				q.addChoice(rslt,a*a-b*b);
			} else if (idx == 5) {
				q.addChoice(rslt,(a+b+c)*(a+b+c));
			} else if (idx == 6) {
				q.addChoice(rslt,a*a + b*b - c*c);
			} else {
				q.addChoice(rslt,a*a - b*b + c*c);
			}
			String question = MJXTAG+expression[idx][1].replace("*", "x")+MJXTAG;
			question = question.replace("a", String.valueOf(a));
			question = question.replace("b", String.valueOf(b));
			question = question.replace("c", String.valueOf(c));
			q.setQuestion(question);
			q.setCorrectAnswer(String.valueOf(rslt));
			q.setDifficultyLevel(QuizLevel.MUDAH);
			q.setLessonSubcategory(bundle.getString("integer.quadratic"));
			q.setLessonClassifier(bundle.getString("mathelementary"));
			q.setLessonGrade(5);
			q.setSubCategoryOrder(6);
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
