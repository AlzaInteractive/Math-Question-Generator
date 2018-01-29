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
import com.alza.quiz.model.geom.Triangle;
import com.alza.quiz.model.geom.Triangle.EdgeLengthRatio;
import com.alza.quiz.qfactory.IQuestionFactory;

public class TriangleScaleneIscoscelesEquilateral implements IQuestionFactory {
	private int numq = 3;
	Locale loc;
	ResourceBundle bundle;

	public TriangleScaleneIscoscelesEquilateral() {
		this.loc = new Locale("in", "ID");
		initStringFromLocale();
	}

	public TriangleScaleneIscoscelesEquilateral(Locale loc) {
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
			//randomize between types
			int a = ThreadLocalRandom.current().nextInt(0,3);
			//calculate angle
			EdgeLengthRatio type; 
			if(a==0) type = EdgeLengthRatio.equilateral;
			else if (a==1) type = EdgeLengthRatio.iscosceles;
			else type = EdgeLengthRatio.scalene;
			//prepare triangle
			shp = (Triangle) new Triangle().createExample(type);
			shp.hideTextsAndMeasurements();
			shp.setShowVerticeLabel(true);
			shp.setShowBaselineLength(true);
			shp.setShowLeftEdgeLength(true);
			shp.setShowRightEdgeLength(true);
			MultipleChoiceGeomQuiz q = new MultipleChoiceGeomQuiz();
			String iscosceles = bundle.getString("geom.shape2d.triangle.iscosceles".toLowerCase());
			String equilateral = bundle.getString("geom.shape2d.triangle.equilateral".toLowerCase());
			String scalene = bundle.getString("geom.shape2d.triangle.scalene".toLowerCase());
			q.setGeomShape(shp.getPaths());
			q.setCorrectAnswer((bundle.getString("geom.shape2d.triangle."+shp.getType()).toLowerCase()));
			String question = bundle.getString("geom.shape2d.question.triangle.angle.equiscoscalene");
			q.setChoices(iscosceles,equilateral,scalene);
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
