package com.alza.quiz.qfactory.geom.square;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.ResourceBundle;

import com.alza.quiz.model.MultipleChoiceGeomQuiz;
import com.alza.quiz.model.Quiz;
import com.alza.quiz.model.QuizLevel;
import com.alza.quiz.model.geom.Square;
import com.alza.quiz.qfactory.IQuestionFactory;
import com.alza.quiz.qfactory.geom.BasicPropertyOfShape2D;

public class SquareBasicProperties implements IQuestionFactory {
	private int numq = 4;
	Locale loc;
	ResourceBundle bundle;

	public SquareBasicProperties() {
		this.loc = new Locale("in", "ID");
		initStringFromLocale();
	}

	public SquareBasicProperties(Locale loc) {
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
		Square shp = (Square) new Square().createExample();
		shp.hideTextsAndMeasurements();
		BasicPropertyOfShape2D bp = new BasicPropertyOfShape2D(loc, shp);
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
		MultipleChoiceGeomQuiz q = new MultipleChoiceGeomQuiz();
		Square shp = (Square) new Square().createExample();
		shp.hideTextsAndMeasurements();
		q.setGeomShape(shp.getPaths());
		q.setCorrectAnswer("90");
		q.setChoices("45", "90", "180", "100");
		String question = bundle.getString("geom.shape2d.question.square.angle");
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
