package com.alza.quiz.qfactory.geom.parallelogram;

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
import com.alza.quiz.model.geom.Parallelogram;
import com.alza.quiz.qfactory.IQuestionFactory;

public class ParallelogramFindPerimeter implements IQuestionFactory{
	private int numq = 2;
	Locale loc;
	ResourceBundle bundle;
	
	public ParallelogramFindPerimeter() {
		this.loc = new Locale("in", "ID");
		initStringFromLocale();
	}
	
	public ParallelogramFindPerimeter(Locale loc) {
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
			int length = ThreadLocalRandom.current().nextInt(shear+1,shear*3);
			Parallelogram shp = (Parallelogram) new Parallelogram(length,height,shear);
			String shapeName = bundle.getString("geom.shape2d."+shp.getName().toLowerCase());
			shp.hideTextsAndMeasurements();
			shp.setShowLengthValue(true);
			shp.setShowShearValue(true);
			shp.setShowHeightLine(true);
			shp.setShowHeightValue(true);
			GeomQuiz q = new GeomQuiz();
			q.setGeomShape(shp.getPaths());
			q.setCorrectAnswer(Geom.formatMeasurement(shp.getPerimeter()));
			String question = bundle.getString("geom.shape2d.question.findperimeter");
			question = question.replaceAll("#shape", shapeName);
			q.setQuestion(question);
			q.setDifficultyLevel(QuizLevel.MUDAH);
			q.setLessonSubcategory(bundle.getString("geom.shape2d.parallelogram"));
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
