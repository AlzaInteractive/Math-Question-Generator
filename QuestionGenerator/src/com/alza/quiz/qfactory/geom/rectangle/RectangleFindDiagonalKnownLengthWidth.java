package com.alza.quiz.qfactory.geom.rectangle;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.ResourceBundle;

import com.alza.common.math.MathUtils;
import com.alza.quiz.model.GeomQuiz;
import com.alza.quiz.model.Quiz;
import com.alza.quiz.model.QuizLevel;
import com.alza.quiz.model.geom.Geom;
import com.alza.quiz.model.geom.Rectangle;
import com.alza.quiz.qfactory.IQuestionFactory;

public class RectangleFindDiagonalKnownLengthWidth implements IQuestionFactory{
	private int numq = 2;
	Locale loc;
	ResourceBundle bundle;
	
	public RectangleFindDiagonalKnownLengthWidth() {
		this.loc = new Locale("in", "ID");
		initStringFromLocale();
	}
	
	public RectangleFindDiagonalKnownLengthWidth(Locale loc) {
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
			int[] phyt = MathUtils.generateRandomPhytagoreanTriples(3, 5);
			Rectangle rec = (Rectangle) new Rectangle(phyt[0],phyt[1]);
			rec.hideTextsAndMeasurements();
			rec.setShowDiagonalLine(true);;
			rec.setShowLengthValue(true);
			rec.setShowWidthValue(true);
			GeomQuiz q = new GeomQuiz();
			q.setGeomShape(rec.getPaths());
			q.setCorrectAnswer(Geom.formatMeasurement(rec.getDiagonalLength()));
			String question = bundle.getString("geom.shape2d.rectangle.question.finddiagonal");
			question = question.replaceAll("#shape", rec.getName());
			q.setQuestion(question);
			q.setDifficultyLevel(QuizLevel.MUDAH);
			q.setLessonSubcategory(bundle.getString("geom.shape2d.rectangle"));
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
