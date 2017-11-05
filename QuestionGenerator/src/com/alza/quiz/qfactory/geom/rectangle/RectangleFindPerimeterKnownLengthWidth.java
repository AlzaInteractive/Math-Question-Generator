package com.alza.quiz.qfactory.geom.rectangle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.ResourceBundle;

import com.alza.quiz.model.MultipleChoiceQuiz;
import com.alza.quiz.model.Quiz;
import com.alza.quiz.model.QuizLevel;
import com.alza.quiz.model.geom.Rectangle;
import com.alza.quiz.qfactory.IQuestionFactory;

public class RectangleFindPerimeterKnownLengthWidth implements IQuestionFactory{
	private int numq = 2;
	Locale loc;
	ResourceBundle bundle;
	
	public RectangleFindPerimeterKnownLengthWidth() {
		this.loc = new Locale("in", "ID");
		initStringFromLocale();
	}
	
	public RectangleFindPerimeterKnownLengthWidth(Locale loc) {
		super();
		this.loc = loc;
		initStringFromLocale();
	}

	private void initStringFromLocale(){
		bundle = ResourceBundle.getBundle("lang.langbundle", loc);
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
		List<Quiz> quizList = new ArrayList<Quiz>();
		for (int i=0;i<numq;i++){
			Rectangle rectangle = (Rectangle) new Rectangle().createExample();
			List<Double> choices = new ArrayList<Double>();
			choices.add(rectangle.getPerimeter());
			choices.add(rectangle.getArea());
			choices.add((double)rectangle.getLength() * 2);
			choices.add((double)rectangle.getWidth() * 2);
			Collections.shuffle(choices);
			MultipleChoiceQuiz q = new MultipleChoiceQuiz();
			q.setChoices(convertChoices(choices));
			q.setCorrectAnswer(String.valueOf(rectangle.getPerimeter()));
			String question = bundle.getString("geom2d.question.rectangle.findperimeterfromedge");
			q.setQuestion(question+" "+rectangle.getLength()+" & "+rectangle.getWidth());
			q.setDifficultyLevel(QuizLevel.MUDAH);
			q.setLessonSubcategory(bundle.getString("geom2d.rectangle"));
			q.setLessonClassifier(bundle.getString("mathelementary"));
			q.setLessonGrade(4);
			q.setSubCategoryOrder(5);
			q.setLocale(loc);
			q.setLessonCategory(bundle.getString("geom2d"));
			quizList.add(q);
		}
		return quizList;
	}
	private List<String> convertChoices(List<Double> ds){
		List<String> choicesInString = new ArrayList<String>();
		for (Double double1 : ds) {
			choicesInString.add(double1.toString());
		}
		return choicesInString;
	}
	 @Override
		public List<Quiz> generateQuizList(int numOfQuestion) {
			numq = numOfQuestion;
			return generateQuizList();
		}


}
