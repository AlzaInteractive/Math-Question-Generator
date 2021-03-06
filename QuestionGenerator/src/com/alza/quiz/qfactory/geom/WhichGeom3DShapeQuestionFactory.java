package com.alza.quiz.qfactory.geom;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.ResourceBundle;

import com.alza.quiz.model.MultipleChoiceGeomQuiz;
import com.alza.quiz.model.Quiz;
import com.alza.quiz.model.QuizLevel;
import com.alza.quiz.model.geom.Shapes3D;
import com.alza.quiz.model.geom.solid.Cone;
import com.alza.quiz.model.geom.solid.Cube;
import com.alza.quiz.model.geom.solid.Cuboid;
import com.alza.quiz.model.geom.solid.Cylinder;
import com.alza.quiz.model.geom.solid.Pyramid;
import com.alza.quiz.model.geom.solid.Sphere;
import com.alza.quiz.model.geom.solid.TriangularPrism;
import com.alza.quiz.qfactory.IQuestionFactory;

public class WhichGeom3DShapeQuestionFactory implements IQuestionFactory {
	Locale loc;
	ResourceBundle bundle;
	List<Shapes3D> shapes = new ArrayList<Shapes3D>(); 
	private int defnumq;
	public WhichGeom3DShapeQuestionFactory(Locale loc) {
		this.loc = loc;
		initStringFromLocale();
		prepareShape();
		defnumq = shapes.size();
	}
	public WhichGeom3DShapeQuestionFactory() {
		this.loc = new Locale("en", "US");
		initStringFromLocale();
		prepareShape();
	}
	private void prepareShape(){
		shapes.add(new Cube());
		shapes.add(new Cuboid());
		shapes.add(new TriangularPrism());
		shapes.add(new Cylinder());
		shapes.add(new Pyramid());
		shapes.add(new Cone());
		shapes.add(new Sphere());
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
			Shapes3D s = shapes.get(st-1);
			//System.out.println(s.toString());
			MultipleChoiceGeomQuiz q = new MultipleChoiceGeomQuiz();
			q.setGeomShape(s.createExample().getPaths());
			q.setQuestion(bundle.getString("geom.whichshape"));
			q.setCorrectAnswer(bundle.getString("geom."+s.getName()));
			q.setChoices(getChoices());
			//
			q.setDifficultyLevel(QuizLevel.MUDAH);
			q.setLessonSubcategory(bundle.getString("geom.identify2dshape"));
			q.setLessonClassifier(bundle.getString("mathelementary"));
			q.setLessonGrade(4);
			q.setSubCategoryOrder(3);
			q.setLocale(loc);
			q.setLessonCategory(bundle.getString("geom.geom2d"));
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
		for (Shapes3D shapes2d : shapes) {
			s[i] = bundle.getString("geom."+shapes2d.getName());
			i++;
		}
		return s;
	}

}
