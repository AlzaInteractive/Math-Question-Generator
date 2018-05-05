package com.alza.quiz.qfactory.geom.trapezoid;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.concurrent.ThreadLocalRandom;

import com.alza.common.math.MathUtils;
import com.alza.quiz.model.GeomQuiz;
import com.alza.quiz.model.Quiz;
import com.alza.quiz.model.QuizLevel;
import com.alza.quiz.model.geom.Geom;
import com.alza.quiz.model.geom.Trapezoid;
import com.alza.quiz.qfactory.IQuestionFactory;

public class TrapezoidFindPerimeterPyth implements IQuestionFactory{
	private int numq = 2;
	Locale loc;
	ResourceBundle bundle;
	
	public TrapezoidFindPerimeterPyth() {
		this.loc = new Locale("in", "ID");
		initStringFromLocale();
	}
	
	public TrapezoidFindPerimeterPyth(Locale loc) {
		super();
		this.loc = loc;
		initStringFromLocale();
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
		List<Quiz> quizList = new ArrayList<Quiz>();
		for (int i=0;i<numq;i++){
			int[] phyt = MathUtils.generateRandomPythagoreanTriples(4, 5);
			int shear = phyt[0];
			int height = phyt[1];
			int bottomLength = ThreadLocalRandom.current().nextInt(shear*2+5,shear*3+6);
			int topLength = bottomLength - (2 * shear);
			Trapezoid shp = (Trapezoid) new Trapezoid(topLength,bottomLength,height,shear);
			String shapeName = bundle.getString("geom.shape2d."+shp.getName().toLowerCase());
			shp.hideTextsAndMeasurements();
			shp.setShowBottomLengthValue(true);
			shp.setShowTopLengthValue(true);
			shp.setShowShearLeftValue(true);
			shp.setShowHeightLine(true);
			shp.setShowHeightValue(true);
			GeomQuiz q = new GeomQuiz();
			q.setGeomShape(shp.getPaths());
			q.setCorrectAnswer(Geom.formatMeasurement(shp.getPerimeter()));
			String question = bundle.getString("geom.shape2d.question.findperimeter");
			question = question.replaceAll("#shape", shapeName);
			q.setQuestion(question);
			q.setDifficultyLevel(QuizLevel.MUDAH);
			q.setLessonSubcategory(bundle.getString("geom.shape2d.trapezoid"));
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
