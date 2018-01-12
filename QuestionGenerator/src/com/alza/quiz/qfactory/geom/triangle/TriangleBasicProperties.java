package com.alza.quiz.qfactory.geom.triangle;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.ResourceBundle;

import com.alza.quiz.model.Quiz;
import com.alza.quiz.model.QuizLevel;
import com.alza.quiz.model.geom.Triangle;
import com.alza.quiz.model.geom.Triangle.EdgeLengthRatio;
import com.alza.quiz.qfactory.IQuestionFactory;
import com.alza.quiz.qfactory.geom.BasicPropertyOfShape2D;
import com.alza.quiz.util.CommonFunctionAndValues;

public class TriangleBasicProperties implements IQuestionFactory {
	private int numq = 3;
	Locale loc;
	ResourceBundle bundle;

	public TriangleBasicProperties() {
		this.loc = new Locale("in", "ID");
		initStringFromLocale();
	}

	public TriangleBasicProperties(Locale loc) {
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
		Triangle tr1,tr2,tr3;
		Triangle tr = new Triangle();
		//int type = ThreadLocalRandom.current().nextInt(0, 3);
		tr1 = (Triangle) tr.createExample(EdgeLengthRatio.equilateral);
		tr2 = (Triangle) tr.createExample(EdgeLengthRatio.iscosceles);
		tr3 = (Triangle) tr.createExample(EdgeLengthRatio.scalene);
		
		Triangle[] trs = {tr1,tr2,tr3};
		CommonFunctionAndValues.shuffleArray(trs);
		for (int i = 0; i < trs.length; i++) {
			Triangle t = trs[i];
			t.hideTextsAndMeasurements();
			
			BasicPropertyOfShape2D bp = new BasicPropertyOfShape2D(loc, t);
			if (i==0) {
				quizListSrc.add(bp.numberOfEdges());
			} else {
				t.setShowBaselineLength(true);
				t.setShowLeftEdgeLength(true);
				t.setShowRightEdgeLength(true);
				if (i==1) {
					quizListSrc.add(bp.numberOfReflectionalSymmetry());
				} else {
					quizListSrc.add(bp.numberOfRotationalSymmetry());
				}
			}
		}	
//		Collections.shuffle(quizListSrc);
		List<Quiz> ql = new ArrayList<Quiz>();
		if (numq>quizListSrc.size()) {
			numq = quizListSrc.size();
		}
		for (int i = 0; i < numq; i++) {
			ql.add(quizListSrc.get(i));
		}
		return ql;
	}

	@Override
	public List<Quiz> generateQuizList(int numOfQuestion) {
		numq = numOfQuestion;
		return generateQuizList();
	}

}
