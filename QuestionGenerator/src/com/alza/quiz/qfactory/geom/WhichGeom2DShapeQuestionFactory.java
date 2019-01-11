package com.alza.quiz.qfactory.geom;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.ResourceBundle;

import com.alza.quiz.model.MultipleChoiceGeomQuiz;
import com.alza.quiz.model.Quiz;
import com.alza.quiz.model.QuizLevel;
import com.alza.quiz.model.geom.Circle;
import com.alza.quiz.model.geom.Kite;
import com.alza.quiz.model.geom.Parallelogram;
import com.alza.quiz.model.geom.Rectangle;
import com.alza.quiz.model.geom.Rhombus;
import com.alza.quiz.model.geom.Shapes2D;
import com.alza.quiz.model.geom.Square;
import com.alza.quiz.model.geom.Trapezoid;
import com.alza.quiz.model.geom.Triangle;
import com.alza.quiz.qfactory.IQuestionFactory;

public class WhichGeom2DShapeQuestionFactory implements IQuestionFactory {
	Locale loc;
	ResourceBundle bundle;
	List<Shapes2D> shapes = new ArrayList<Shapes2D>(); 
	private int defnumq;
	public WhichGeom2DShapeQuestionFactory(Locale loc) {
		this.loc = loc;
		initStringFromLocale();
		prepareShape();
		defnumq = shapes.size();
	}
	public WhichGeom2DShapeQuestionFactory() {
		this.loc = new Locale("en", "US");
		initStringFromLocale();
		prepareShape();
	}
	private void prepareShape(){
		shapes.add(new Square());
		shapes.add(new Rectangle());
		shapes.add(new Triangle());
		shapes.add(new Circle());
		shapes.add(new Rhombus());
		shapes.add(new Kite());
		shapes.add(new Parallelogram());
		shapes.add(new Trapezoid());
		Collections.shuffle(shapes);
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
		List<Quiz> ql = new ArrayList<Quiz>();
		for (int i = 0; i < defnumq; i++) {
			int st = i % shapes.size();
			if (st==0) {
				st = defnumq;
			}
			Shapes2D s = shapes.get(st-1).createExample();
			s.hideTextsAndMeasurements();
			//System.out.println(s.toString());
			MultipleChoiceGeomQuiz q = new MultipleChoiceGeomQuiz();
			q.setGeomShape(s.getPaths());
			q.setQuestion(bundle.getString("geom.shape2d.question.identify"));
			q.setCorrectAnswer(bundle.getString("geom.shape2d."+s.getName()));
			q.setChoices(getChoices());
			q.enforceSortedChoices();
			q.setDifficultyLevel(QuizLevel.MUDAH);
			q.setLessonSubcategory(bundle.getString("geom.shape2d."+s.getName()));
			q.setLessonClassifier(bundle.getString("mathelementary"));
			q.setLessonGrade(4);
			q.setSubCategoryOrder(3);
			q.setLocale(loc);
			q.setLessonCategory(bundle.getString("geom.shape2d"));
			ql.add(q);
			
		}
		return ql;
	}

	@Override
	public List<Quiz> generateQuizList(int numOfQuestion) {
		this.defnumq = numOfQuestion;
		return generateQuizList();
	}
	
	private String[] getChoices(){
		String[] s = new String[shapes.size()];
		int i = 0;
		for (Shapes2D shapes2d : shapes) {
			s[i] = bundle.getString("geom.shape2d."+shapes2d.getName());
			i++;
		}
		return s;
	}

}
