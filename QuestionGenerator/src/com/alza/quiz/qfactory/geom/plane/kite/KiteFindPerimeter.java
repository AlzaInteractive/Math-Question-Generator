package com.alza.quiz.qfactory.geom.plane.kite;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.concurrent.ThreadLocalRandom;

import com.alza.quiz.model.GeomQuiz;
import com.alza.quiz.model.Quiz;
import com.alza.quiz.model.QuizLevel;
import com.alza.quiz.model.geom.plane.Geom;
import com.alza.quiz.model.geom.plane.Kite;
import com.alza.quiz.qfactory.IQuestionFactory;

public class KiteFindPerimeter implements IQuestionFactory{
	private int numq = 2;
	Locale loc;
	ResourceBundle bundle;
	
	public KiteFindPerimeter() {
		this.loc = new Locale("in", "ID");
		initStringFromLocale();
	}
	
	public KiteFindPerimeter(Locale loc) {
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
			int b = ThreadLocalRandom.current().nextInt(1, 5);
			double topSlope = ThreadLocalRandom.current().nextInt(3, 10);
			double bottomSlope = ThreadLocalRandom.current().nextInt(3, 17);
			int angleB = b * 30;
			Kite shp = new Kite(topSlope, bottomSlope, angleB);
			shp.hideTextsAndMeasurements();
			shp.setShowDiagonalLine(true);
			shp.setShowTopSlopeLength(true);
			shp.setShowBottomSlopeLength(true);
			GeomQuiz q = new GeomQuiz();
			q.setGeomShape(shp.getPaths());
			q.setCorrectAnswer(Geom.formatMeasurement(shp.getPerimeter()));
			String question = bundle.getString("geom.shape2d.question.findperimeter");
			question = question.replaceAll("#shape", shp.getName());
			q.setQuestion(question);
			q.setDifficultyLevel(QuizLevel.MUDAH);
			q.setLessonSubcategory(bundle.getString("geom.shape2d.kite"));
			q.setLessonClassifier(bundle.getString("mathelementary"));
			q.setLessonGrade(4);
			q.setSubCategoryOrder(5);
			q.setLocale(loc);
			q.setLessonCategory(bundle.getString("geom.shape2d"));
			quizList.add(q);
		}
		return quizList;
	}
	 @Override
		public List<Quiz> generateQuizList(int numOfQuestion) {
			numq = numOfQuestion;
			return generateQuizList();
		}


}
