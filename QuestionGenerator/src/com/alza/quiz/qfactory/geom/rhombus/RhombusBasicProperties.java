package com.alza.quiz.qfactory.geom.rhombus;

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
import com.alza.quiz.model.geom.Rhombus;
import com.alza.quiz.qfactory.IQuestionFactory;
import com.alza.quiz.qfactory.geom.BasicPropertyOfShape2D;

public class RhombusBasicProperties implements IQuestionFactory {
	private int numq = 4;
	Locale loc;
	ResourceBundle bundle;

	public RhombusBasicProperties() {
		this.loc = new Locale("in", "ID");
		initStringFromLocale();
	}

	public RhombusBasicProperties(Locale loc) {
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
		Rhombus rh = (Rhombus) new Rhombus().createExample();
		rh.hideTextsAndMeasurements();
		rh.setShowDiagonalLine(true);
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
		int t = ThreadLocalRandom.current().nextInt(1, 11);
		int f = 12-t;
		int tAngle = t * 15;
		int fAngle = f * 15;
		double edge = ThreadLocalRandom.current().nextInt(5, 11);
		Rhombus rh = new Rhombus(edge, tAngle);
		rh.hideTextsAndMeasurements();
		rh.setShowVerticeLabel(true);
		MultipleChoiceGeomQuiz q = new MultipleChoiceGeomQuiz();
		q.setGeomShape(rh.getPaths());
		q.setCorrectAnswer(""+fAngle);
		q.setChoices(""+tAngle, 
				""+tAngle+fAngle, 
				""+fAngle, 
				""+(fAngle*2),
				""+(tAngle*2));
		String question = bundle.getString("geom.shape2d.question.rhombus.angle");
		question = question.replaceAll("#angleA", ""+tAngle);
		q.setQuestion(question);
		q.setDifficultyLevel(QuizLevel.MUDAH);
		q.setLessonSubcategory(bundle.getString("geom.shape2d"));
		q.setLessonClassifier(bundle.getString("mathelementary"));
		q.setLessonGrade(4);
		q.setSubCategoryOrder(5);
		q.setLocale(loc);
		q.setLessonCategory(bundle.getString("geom"));
		return q;
	}

	@Override
	public List<Quiz> generateQuizList(int numOfQuestion) {
		numq = numOfQuestion;
		return generateQuizList();
	}

}
