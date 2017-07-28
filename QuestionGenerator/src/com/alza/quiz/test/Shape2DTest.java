package com.alza.quiz.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import com.alza.quiz.model.MultipleChoiceQuiz;
import com.alza.quiz.model.Quiz;
import com.alza.quiz.qfactory.IQuestionFactory;
import com.alza.quiz.qfactory.Util;
import com.alza.quiz.qfactory.geom.Circle;
import com.alza.quiz.qfactory.geom.Kite;
import com.alza.quiz.qfactory.geom.Parallelogram;
import com.alza.quiz.qfactory.geom.Path;
import com.alza.quiz.qfactory.geom.Rectangle;
import com.alza.quiz.qfactory.geom.Rhombus;
import com.alza.quiz.qfactory.geom.Shapes2D;
import com.alza.quiz.qfactory.geom.Square;

public final class Shape2DTest {
	private static List<Shapes2D> lshps;
	public static void main(String[] args) {
		//shapeGeneratorTest();
		allGenerator();
	}

	private static void shapeGeneratorTest() {
		List<Shapes2D> l = generateShapes();
		for (Shapes2D shapes2d : l) {
			System.out.println(shapes2d.toString());
			List<Path> ps = shapes2d.getPaths(200, 100);
			for (Path path : ps) {
				System.out.println(path.toString());
			}
		}
	}
	
	private static List<Shapes2D> generateShapes(){
		lshps = new ArrayList<Shapes2D>();
		//lshps.add(new Square().createExample());
		//lshps.add(new Rectangle().createExample());
		//lshps.add(new Rhombus().createExample());
		//lshps.add(new Circle().createExample());
		//lshps.add(new Kite().createExample());
		lshps.add(new Parallelogram().createExample());
		return lshps;
	}
	
	private static void allGenerator(){
		List<IQuestionFactory> lqf = Util.getAllGeometryQuestionFactory(new Locale("en","US"));
		List<Quiz> ql = new ArrayList<Quiz>();
		for (IQuestionFactory iQuestionFactory : lqf) {
			ql.addAll(iQuestionFactory.generateQuizList());
		}
		Collections.sort(ql);
		for (Quiz q : ql) {
			System.out.println("------------------------------");
			System.out.println("Grade : "+q.getLessonGrade());
			System.out.println("Subcategory :" +q.getLessonSubcategory());
			System.out.println("Question : " + q.getQuestion());
			System.out.println("Level : "+q.getQuizLevel());
			if (q instanceof MultipleChoiceQuiz){
				MultipleChoiceQuiz mq = (MultipleChoiceQuiz) q;
				System.out.println("Choices : "+ String.join(" , ", mq.getChoices()));
			}
			System.out.println("Answer : "+ q.getCorrectAnswer());
		}
		System.out.println("Jumlah soal : "+ql.size());
	}

}
