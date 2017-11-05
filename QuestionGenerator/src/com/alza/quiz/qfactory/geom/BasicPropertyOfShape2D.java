package com.alza.quiz.qfactory.geom;

import java.util.Locale;
import java.util.ResourceBundle;

import com.alza.quiz.model.Quiz;
import com.alza.quiz.model.QuizLevel;
import com.alza.quiz.model.SimpleQuiz;
import com.alza.quiz.model.geom.Shapes2D;
import com.alza.quiz.model.geom.Square;

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
	}

	private void initStringFromLocale(){
		bundle = ResourceBundle.getBundle("lang.langbundle", loc);
	}
	
	public Quiz numberOfEdges(){
		SimpleQuiz q = new SimpleQuiz();
		q.setCorrectAnswer(String.valueOf(new Square().getEdgeCount()));
		String question = bundle.getString("geom2d.question.numofedge");
		q.setQuestion(question+" "+shape.getName());
		q.setDifficultyLevel(QuizLevel.MUDAH);
		q.setLessonSubcategory(bundle.getString("geom2d."+shape.getName().toLowerCase()));
		q.setLessonClassifier(bundle.getString("mathelementary"));
		q.setLessonGrade(4);
		q.setSubCategoryOrder(5);
		q.setLocale(loc);
		q.setLessonCategory(bundle.getString("geom2d"));
		return q;
	}
	
	public Quiz numberOfReflectionalSymmetry(){
		SimpleQuiz q = new SimpleQuiz();
		q.setCorrectAnswer(String.valueOf(shape.getReflectionalSymmetryCount()));
		String question = bundle.getString("geom2d.question.numofreflectionalsymmetry");
		q.setQuestion(question);
		q.setDifficultyLevel(QuizLevel.MUDAH);
		q.setLessonSubcategory(bundle.getString("geom2d."+shape.getName().toLowerCase()));
		q.setLessonClassifier(bundle.getString("mathelementary"));
		q.setLessonGrade(4);
		q.setSubCategoryOrder(5);
		q.setLocale(loc);
		q.setLessonCategory(bundle.getString("geom2d"));
		return q;
	}
	
	public Quiz numberOfRotationalSymmetry(){
		SimpleQuiz q = new SimpleQuiz();
		q.setCorrectAnswer(String.valueOf(shape.getRotationalSymmetryCount()));
		String question = bundle.getString("geom2d.question.numofrotationalsymmetry");
		q.setQuestion(question);
		q.setDifficultyLevel(QuizLevel.MUDAH);
		q.setLessonSubcategory(bundle.getString("geom2d."+shape.getName().toLowerCase()));
		q.setLessonClassifier(bundle.getString("mathelementary"));
		q.setLessonGrade(4);
		q.setSubCategoryOrder(5);
		q.setLocale(loc);
		q.setLessonCategory(bundle.getString("geom2d"));
		return q;
	}

}
