package com.alza.quiz.qfactory.geom.plane.circle;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.ResourceBundle;

import com.alza.quiz.model.GeomQuiz;
import com.alza.quiz.model.Quiz;
import com.alza.quiz.model.QuizLevel;
import com.alza.quiz.model.geom.plane.Circle;
import com.alza.quiz.model.geom.plane.Geom;
import com.alza.quiz.qfactory.IQuestionFactory;

public class CircleFindAreaKnownDiameter implements IQuestionFactory {
	private int numq = 2;
	Locale loc;
	ResourceBundle bundle;

	public CircleFindAreaKnownDiameter() {
		this.loc = new Locale("in", "ID");
		initStringFromLocale();
	}

	public CircleFindAreaKnownDiameter(Locale loc) {
		super();
		this.loc = loc;
		initStringFromLocale();
	}

	private void initStringFromLocale() {
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
		for (int i = 0; i < numq; i++) {
			Circle shp = (Circle) new Circle().createExample();
			shp.hideTextsAndMeasurements();
			shp.setShowDiameterLine(true);
			shp.setShowDiameterLength(true);
			GeomQuiz q = new GeomQuiz();
			q.setGeomShape(shp.getPaths());
			q.setCorrectAnswer(Geom.formatMeasurement(shp.getArea()));
			String question = bundle.getString("geom.shape2d.question.findarea");
			question = question.replaceAll("#shape", shp.getName());
			q.setQuestion(question);
			q.setDifficultyLevel(QuizLevel.MUDAH);
			q.setLessonSubcategory(bundle.getString("geom.shape2d.circle"));
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
