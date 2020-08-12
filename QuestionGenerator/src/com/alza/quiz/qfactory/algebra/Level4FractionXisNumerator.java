package com.alza.quiz.qfactory.algebra;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.concurrent.ThreadLocalRandom;

import com.alza.quiz.model.MultipleChoiceGeomQuiz;
import com.alza.quiz.model.MultipleChoiceQuiz;
import com.alza.quiz.model.Quiz;
import com.alza.quiz.model.QuizLevel;
import com.alza.quiz.qfactory.IQuestionFactory;
import com.alza.quiz.util.CommonFunctionAndValues;

public class Level4FractionXisNumerator implements IQuestionFactory{

	private int numOfQuestion = 5;
	private Map<Integer, Pattern> qMap = new HashMap<Integer, Level4FractionXisNumerator.Pattern>();
	private Locale loc;
	private ResourceBundle bundle;
	private ResourceBundle bundleAlgebra;
	
	public Level4FractionXisNumerator(Locale loc){
		this.loc = loc;
		initStringFromLocale();
	}
	public Level4FractionXisNumerator(){
		this.loc = new Locale("in", "ID");
		initStringFromLocale();
	}
	private void initStringFromLocale(){
		bundle = ResourceBundle.getBundle("lang.langbundle", loc);
		bundleAlgebra = ResourceBundle.getBundle("lang.langbundle-algebra", loc);
		
	}

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
		for (int i=0;i<this.numOfQuestion;i++) {
			int a,b,mod;
			Pattern p;
			do {
				a = ThreadLocalRandom.current().nextInt(2, 15);
				b = ThreadLocalRandom.current().nextInt(2, 15);
				if (a > b) {
					mod = a % b;
				} else {
					mod = b % a;
				}
				p = new Pattern(a, b);
			} while (qMap.containsKey(p.hash()) || a == b || mod != 0);
			qMap.put(p.hash(), p);
			lq.add(PatternToQuiz(p));
		}
		return lq;
	}

	@Override
	public List<Quiz> generateQuizList(int numOfQuestion) {
		this.numOfQuestion  = numOfQuestion;
		return generateQuizList();
	}
	
	private Quiz PatternToQuiz(Pattern p) {
		String[] VARSYM = {"X","Y"};
		String var = VARSYM[ThreadLocalRandom.current().nextInt(0, VARSYM.length)];		
		MultipleChoiceQuiz q = new MultipleChoiceQuiz();
		int product = p.a * p.b;
		String question="v1 = frac{VAR}{v2}";
		question = question.replace("v1", String.valueOf(p.a));
		question = question.replace("v2", String.valueOf(p.b));		
		question = question.replaceAll("VAR", var);
		question = question + "\n" + var + " = ?";
		q.setQuestion(question);
		q.setCorrectAnswer(String.valueOf(product));
		q.setChoices(p.getChoices());
		setQuizSecondaryAttributes(q);
		return q;
	}
	
	private void setQuizSecondaryAttributes(Quiz q) {
		q.setDifficultyLevel(QuizLevel.MUDAH);
		q.setLessonSubcategory(bundleAlgebra.getString("algebra.level3.mixop"));
		q.setLessonClassifier(bundle.getString("mathelementary"));
		q.setLessonGrade(5);
		q.setSubCategoryOrder(6);		
		q.setLessonCategory(bundle.getString("algebra"));
		q.setLocale(loc);
	}
	
	protected class Pattern {
		int a;
		int b;
		
		Pattern(int a, int b) {
			this.a = a;
			this.b = b;
		}
		
		int hash() {
			String s = a+" "+b;
			return (CommonFunctionAndValues.hashSimple(s));
		}
		
		int getX() {
			return a * b;
		}
		
		int[] getChoices() {
			int c;
			if (a>b) {
				c = a / b;
			} else {
				c = b / a;
			}
			int[] choices = {a*b,a+b,c};
			return choices;
		}
	}

}
