package com.alza.quiz.qfactory.geom.triangle;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.concurrent.ThreadLocalRandom;

import com.alza.quiz.model.MultipleChoiceGeomQuiz;
import com.alza.quiz.model.Quiz;
import com.alza.quiz.model.QuizLevel;
import com.alza.quiz.model.geom.Geom;
import com.alza.quiz.model.geom.Triangle;
import com.alza.quiz.qfactory.IQuestionFactory;

public class TriangleRightObtuseAcute implements IQuestionFactory {
	private int numq = 3;
	Locale loc;
	ResourceBundle bundle;

	public TriangleRightObtuseAcute() {
		this.loc = new Locale("in", "ID");
		initStringFromLocale();
	}

	public TriangleRightObtuseAcute(Locale loc) {
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
			Triangle shp;
			//prepare steps of 12 * 15 degrees
			int a,b,c;
			a = ThreadLocalRandom.current().nextInt(1,9);
			b = ThreadLocalRandom.current().nextInt(a+1,12);
			c = 12-b;
			//calculate angle 
			double angleA,angleB,angleC;
			angleA = 15 * a;
			angleB = 15 * (b-a);
			angleC = 15 * (c);
			//prepare triangle
			double totAngle = angleA+angleB+angleC;
			System.out.println(a+" "+b+" "+c);
			System.out.println(angleA+" "+angleB+" "+angleC+" "+totAngle);
			shp = new Triangle(1,angleA,angleB,angleC);
			shp.hideTextsAndMeasurements();
			shp.setShowVerticeLabel(true);
			MultipleChoiceGeomQuiz q = new MultipleChoiceGeomQuiz();
			q.setGeomShape(shp.getPaths());
			q.setCorrectAnswer(shp.getAngleType().toString());
			String question = bundle.getString("geom.shape2d.question.triangle.angle.obtuseacuteright");
			question = question.replace("#angleA", Geom.formatMeasurement(angleA));
			question = question.replace("#angleB", Geom.formatMeasurement(angleB));
			q.setChoices(Triangle.AngleType.acute.toString(),
					Triangle.AngleType.obtuse.toString(),
					Triangle.AngleType.right.toString());
			q.setQuestion(question);
			q.setDifficultyLevel(QuizLevel.MUDAH);
			q.setLessonSubcategory(bundle.getString("geom.shape2d.triangle"));
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
