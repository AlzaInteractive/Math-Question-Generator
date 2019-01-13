package com.alza.quiz.qfactory.geom.plane.kite;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.concurrent.ThreadLocalRandom;

import com.alza.quiz.model.MultipleChoiceGeomQuiz;
import com.alza.quiz.model.Quiz;
import com.alza.quiz.model.QuizLevel;
import com.alza.quiz.model.geom.plane.Geom;
import com.alza.quiz.model.geom.plane.Kite;
import com.alza.quiz.qfactory.IQuestionFactory;
import com.alza.quiz.qfactory.geom.BasicPropertyOfShape2D;

public class KiteBasicProperties implements IQuestionFactory {
	private int numq = 4;
	Locale loc;
	ResourceBundle bundle;

	public KiteBasicProperties() {
		this.loc = new Locale("in", "ID");
		initStringFromLocale();
	}

	public KiteBasicProperties(Locale loc) {
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
		List<Quiz> quizListSrc = new ArrayList<Quiz>();
		Kite rh = (Kite) new Kite().createExample();
		rh.hideTextsAndMeasurements();
		rh.setShowDiagonalLine(true);
		rh.setShowVerticeLabel(true);
		BasicPropertyOfShape2D bp = new BasicPropertyOfShape2D(loc, rh);
		quizListSrc.add(bp.numberOfEdges());
		quizListSrc.add(bp.numberOfReflectionalSymmetry());
		quizListSrc.add(bp.numberOfRotationalSymmetry());
		quizListSrc.add(verticeAngle());
		Collections.shuffle(quizListSrc);
		List<Quiz> ql = new ArrayList<Quiz>();
		if (numq>quizListSrc.size()) {
			numq = quizListSrc.size();
		}
		for (int i = 0; i < numq; i++) {
			ql.add(quizListSrc.get(i));
		}
		return ql;
	}

	private Quiz verticeAngle() {
		double b,d,a;
		do {
			b = ThreadLocalRandom.current().nextInt(1, 9);
			d = ThreadLocalRandom.current().nextInt(1, 9);
		} while (b==d);
		a = (24-(b+d))/2;
		int topAngle = 15 * (int) b;
		int bottomAngle = 15 * (int)d;
		double sideAngle = 15 * a;
		double edge = ThreadLocalRandom.current().nextInt(5, 11);
		Kite kt = new Kite(edge, topAngle, bottomAngle);
		kt.hideTextsAndMeasurements();
		kt.setShowVerticeLabel(true);
		kt.setShowDiagonalLine(true);
		MultipleChoiceGeomQuiz q = new MultipleChoiceGeomQuiz();
		q.setGeomShape(kt.getPaths());
		q.setCorrectAnswer(Geom.formatMeasurement(sideAngle));
		q.setChoices(""+topAngle, 
				""+topAngle+bottomAngle, 
				""+bottomAngle, 
				""+(bottomAngle*2),
				""+(topAngle*2));
		String question = bundle.getString("geom.shape2d.question.kite.angle");
		question = question.replaceAll("#angleB", ""+topAngle);
		question = question.replaceAll("#angleD", ""+bottomAngle);
		q.setQuestion(question);
		q.setDifficultyLevel(QuizLevel.MUDAH);
		q.setLessonSubcategory(bundle.getString("geom.shape2d.kite"));
		q.setLessonClassifier(bundle.getString("mathelementary"));
		q.setLessonGrade(4);
		q.setSubCategoryOrder(5);
		q.setLocale(loc);
		q.setLessonCategory(bundle.getString("geom.shape2d"));
		return q;
	}

	@Override
	public List<Quiz> generateQuizList(int numOfQuestion) {
		numq = numOfQuestion;
		return generateQuizList();
	}

}
