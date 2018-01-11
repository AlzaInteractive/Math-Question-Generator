package com.alza.quiz.qfactory.geom.triangle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.concurrent.ThreadLocalRandom;

import com.alza.quiz.model.Quiz;
import com.alza.quiz.model.QuizLevel;
import com.alza.quiz.model.geom.Triangle;
import com.alza.quiz.model.geom.Triangle.EdgeLengthRatio;
import com.alza.quiz.qfactory.IQuestionFactory;
import com.alza.quiz.qfactory.geom.BasicPropertyOfShape2D;

public class TriangleBasicProperties implements IQuestionFactory {
	private int numq = 2;
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
		Triangle tr = new Triangle();
		int type = ThreadLocalRandom.current().nextInt(0, 2);
		
		if (type==0) {
			tr = (Triangle) tr.createExample(EdgeLengthRatio.equilateral);
			tr.setShowBaselineLength(true);
			tr.setShowLeftEdgeLength(true);
			tr.setShowRightEdgeLength(true);
		}
			
		else {
			tr = (Triangle) tr.createExample(EdgeLengthRatio.iscosceles);
			tr.setShowLeftEdgeLength(true);
			tr.setShowRightEdgeLength(true);
		}
			
		
		BasicPropertyOfShape2D bp = new BasicPropertyOfShape2D(loc, tr);
		System.out.println(tr.getBaseLine()+" : "+tr.getHeight());
		quizListSrc.add(bp.numberOfEdges());
		quizListSrc.add(bp.numberOfReflectionalSymmetry());
		quizListSrc.add(bp.numberOfRotationalSymmetry());
		//quizListSrc.add(totalAngle());
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

	@Override
	public List<Quiz> generateQuizList(int numOfQuestion) {
		numq = numOfQuestion;
		return generateQuizList();
	}

}
