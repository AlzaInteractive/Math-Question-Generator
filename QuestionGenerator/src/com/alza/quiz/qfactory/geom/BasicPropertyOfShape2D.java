package com.alza.quiz.qfactory.geom;

import java.util.Locale;
import java.util.ResourceBundle;

import com.alza.quiz.model.GeomQuiz;
import com.alza.quiz.model.Quiz;
import com.alza.quiz.model.QuizLevel;
import com.alza.quiz.model.geom.Shapes2D;

public class BasicPropertyOfShape2D {
	Locale loc;
	ResourceBundle bundle;
	Shapes2D shape;
	public BasicPropertyOfShape2D(Shapes2D shape) {
		this.loc = new Locale("in", "ID");
		initStringFromLocale();
		this.shape = shape;
	}
	
	public BasicPropertyOfShape2D(Locale loc, Shapes2D shape) {
		super();
		this.loc = loc;
		initStringFromLocale();
		this.shape = shape;
		shape.hideTextsAndMeasurements();
	}

	private void initStringFromLocale(){
		bundle = ResourceBundle.getBundle("lang.langbundle", loc);
	}
	
	public Quiz numberOfEdges(){
		GeomQuiz q = new GeomQuiz();
		q.setGeomShape(shape.getPaths());
		q.setCorrectAnswer(String.valueOf(shape.getEdgeCount()));
		String question = bundle.getString("geom.shape2d.question.edgecount");
		question = question.replace("#shape", shape.getName());
		q.setQuestion(question);
		q.setDifficultyLevel(QuizLevel.MUDAH);
		q.setLessonSubcategory(bundle.getString("geom.shape2d."+shape.getName().toLowerCase()));
		q.setLessonClassifier(bundle.getString("mathelementary"));
		q.setLessonGrade(4);
		q.setSubCategoryOrder(5);
		q.setLocale(loc);
		q.setLessonCategory(bundle.getString("geom.shape2d"));
		return q;
	}
	
	public Quiz numberOfReflectionalSymmetry(){
		GeomQuiz q = new GeomQuiz();
		q.setGeomShape(shape.getPaths());
		q.setCorrectAnswer(String.valueOf(shape.getReflectionalSymmetryCount()));
		String question = bundle.getString("geom.shape2d.question.reflectionalsymmetrycount");
		question = question.replace("#shape", shape.getName());
		q.setQuestion(question);
		q.setDifficultyLevel(QuizLevel.MUDAH);
		q.setLessonSubcategory(bundle.getString("geom.shape2d."+shape.getName().toLowerCase()));
		q.setLessonClassifier(bundle.getString("mathelementary"));
		q.setLessonGrade(4);
		q.setSubCategoryOrder(5);
		q.setLocale(loc);
		q.setLessonCategory(bundle.getString("geom.shape2d"));
		return q;
	}
	
	public Quiz numberOfRotationalSymmetry(){
		GeomQuiz q = new GeomQuiz();
		q.setCorrectAnswer(String.valueOf(shape.getRotationalSymmetryCount()));
		String question = bundle.getString("geom.shape2d.question.rotationalsymmetrycount");
		question = question.replace("#shape", shape.getName());
		q.setQuestion(question);
		q.setDifficultyLevel(QuizLevel.MUDAH);
		q.setLessonSubcategory(bundle.getString("geom.shape2d."+shape.getName().toLowerCase()));
		q.setLessonClassifier(bundle.getString("mathelementary"));
		q.setLessonGrade(4);
		q.setSubCategoryOrder(5);
		q.setLocale(loc);
		q.setLessonCategory(bundle.getString("geom.shape2d"));
		return q;
	}

}
