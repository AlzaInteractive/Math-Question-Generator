package com.alza.quiz.qfactory.geom;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import com.alza.quiz.model.GameLevel;
import com.alza.quiz.qfactory.geom.kite.KiteBasicProperties;
import com.alza.quiz.qfactory.geom.kite.KiteFindArea;
import com.alza.quiz.qfactory.geom.kite.KiteFindPerimeter;
import com.alza.quiz.qfactory.geom.parallelogram.ParallelogramFindArea;
import com.alza.quiz.qfactory.geom.parallelogram.ParallelogramFindLength;
import com.alza.quiz.qfactory.geom.parallelogram.ParallelogramFindPerimeter;
import com.alza.quiz.qfactory.geom.rectangle.RectangleBasicProperties;
import com.alza.quiz.qfactory.geom.rectangle.RectangleFindAreaKnownLengthWidth;
import com.alza.quiz.qfactory.geom.rectangle.RectangleFindDiagonalKnownLengthWidth;
import com.alza.quiz.qfactory.geom.rectangle.RectangleFindLengthKnownArea;
import com.alza.quiz.qfactory.geom.rectangle.RectangleFindLengthKnownPerimeter;
import com.alza.quiz.qfactory.geom.rectangle.RectangleFindPerimeterKnownLengthWidth;
import com.alza.quiz.qfactory.geom.rhombus.RhombusBasicProperties;
import com.alza.quiz.qfactory.geom.rhombus.RhombusFindArea;
import com.alza.quiz.qfactory.geom.rhombus.RhombusFindPerimeter;
import com.alza.quiz.qfactory.geom.square.SquareBasicProperties;
import com.alza.quiz.qfactory.geom.square.SquareFindAreaKnownLength;
import com.alza.quiz.qfactory.geom.square.SquareFindLengthKnownArea;
import com.alza.quiz.qfactory.geom.square.SquareFindLengthKnownPerimeter;
import com.alza.quiz.qfactory.geom.square.SquareFindPerimeterKnownLength;
import com.alza.quiz.qfactory.geom.trapezoid.TrapezoidFindArea;
import com.alza.quiz.qfactory.geom.trapezoid.TrapezoidFindLength;
import com.alza.quiz.qfactory.geom.trapezoid.TrapezoidFindPerimeter;
import com.alza.quiz.qfactory.geom.trapezoid.TrapezoidFindPerimeterPhyt;
import com.alza.quiz.qfactory.geom.triangle.TriangleBasicProperties;
import com.alza.quiz.qfactory.geom.triangle.TriangleFindAreaKnownBaseHeight;
import com.alza.quiz.qfactory.geom.triangle.TriangleFindBaseLengthKnownArea;
import com.alza.quiz.qfactory.geom.triangle.TriangleFindHeightLengthKnownArea;
import com.alza.quiz.qfactory.geom.triangle.TriangleFindMissingAngle;
import com.alza.quiz.qfactory.geom.triangle.TrianglePhytagoreanFindArea;
import com.alza.quiz.qfactory.geom.triangle.TrianglePhytagoreanFindRightEdge;
import com.alza.quiz.qfactory.geom.triangle.TriangleRightObtuseAcute;
import com.alza.quiz.qfactory.geom.triangle.TriangleScaleneIscoscelesEquilateral;

/**
 * 
 * @author ewien Game level factory for geometry problems
 *
 */
public class GeomGameLevel {
	public static List<GameLevel> createGameLevels(Locale loc) {
		ResourceBundle bundle = ResourceBundle.getBundle("lang.langbundle", loc);

		List<GameLevel> lgl = new ArrayList<GameLevel>();
		String name, desc;
		GameLevel g;

		// identify 2d shapes
		name = bundle.getString("geom.shape2d.level.identify.title");
		desc = bundle.getString("geom.shape2d.level.identify.desc");
		g = GameLevel.createSingleQF(0, name, desc, new WhichGeom2DShapeQuestionFactory(loc), 7);
		lgl.add(g);

		// square
		name = bundle.getString("geom.shape2d.level.square.title");
		desc = bundle.getString("geom.shape2d.level.square.desc");
		g = GameLevel.createSingleQF(0, name, desc, new SquareBasicProperties(loc), 4);
		g.addQuestionFactory(new SquareFindAreaKnownLength(loc), 2);
		g.addQuestionFactory(new SquareFindPerimeterKnownLength(loc), 2);
		g.addQuestionFactory(new SquareFindLengthKnownArea(loc), 2);
		g.addQuestionFactory(new SquareFindLengthKnownPerimeter(loc), 2);
		lgl.add(g);

		// rectangle
		name = bundle.getString("geom.shape2d.level.rectangle.title");
		desc = bundle.getString("geom.shape2d.level.rectangle.desc");
		g = GameLevel.createSingleQF(0, name, desc, new RectangleBasicProperties(loc), 4);
		g.addQuestionFactory(new RectangleFindAreaKnownLengthWidth(loc), 2);
		g.addQuestionFactory(new RectangleFindPerimeterKnownLengthWidth(loc), 2);
		g.addQuestionFactory(new RectangleFindLengthKnownArea(loc), 2);
		g.addQuestionFactory(new RectangleFindLengthKnownPerimeter(loc), 2);
		g.addQuestionFactory(new RectangleFindDiagonalKnownLengthWidth(loc), 2);
		lgl.add(g);

		// triangle
		name = bundle.getString("geom.shape2d.level.triangle.title");
		desc = bundle.getString("geom.shape2d.level.triangle.desc");
		g = GameLevel.createSingleQF(0, name, desc, new TriangleBasicProperties(loc), 0);
		// g.addQuestionFactory(new TriangleScaleneIscoscelesEquilateral(loc), 3);
		// g.addQuestionFactory(new TriangleRightObtuseAcute(loc), 1);
		// g.addQuestionFactory(new TriangleFindMissingAngle(loc), 1);
		// g.addQuestionFactory(new TriangleFindAreaKnownBaseHeight(loc), 5);
		// g.addQuestionFactory(new TriangleFindBaseLengthKnownArea(loc), 5);
		// g.addQuestionFactory(new TriangleFindHeightLengthKnownArea(loc), 5);
		// g.addQuestionFactory(new TrianglePhytagoreanFindArea(loc), 5);
		g.addQuestionFactory(new TrianglePhytagoreanFindRightEdge(loc), 5);
		lgl.add(g);

		// Parallelogram
		name = bundle.getString("geom.shape2d.level.parallelogram.title");
		desc = bundle.getString("geom.shape2d.level.parallelogram.desc");
		g = GameLevel.createSingleQF(0, name, desc, new ParallelogramFindArea(loc), 0);
		g.addQuestionFactory(new ParallelogramFindLength(loc), 0);
		g.addQuestionFactory(new ParallelogramFindPerimeter(loc), 5);
		lgl.add(g);

		// Trapezoid
		name = bundle.getString("geom.shape2d.level.trapezoid.title");
		desc = bundle.getString("geom.shape2d.level.trapezoid.desc");
		g = GameLevel.createSingleQF(0, name, desc, new TrapezoidFindArea(loc), 0);
		g.addQuestionFactory(new TrapezoidFindLength(loc), 5);
		g.addQuestionFactory(new TrapezoidFindPerimeter(loc), 5);
		g.addQuestionFactory(new TrapezoidFindPerimeterPhyt(loc), 5);
		lgl.add(g);

		// Rhombus
		name = bundle.getString("geom.shape2d.level.rhombus.title");
		desc = bundle.getString("geom.shape2d.level.rhombus.desc");
		g = GameLevel.createSingleQF(0, name, desc, new RhombusBasicProperties(loc), 0);
		g.addQuestionFactory(new RhombusFindArea(loc), 0);
		g.addQuestionFactory(new RhombusFindPerimeter(loc), 5);
		lgl.add(g);
		
		//Kite
		name = bundle.getString("geom.shape2d.level.kite.title");
		desc = bundle.getString("geom.shape2d.level.kite.desc");
		g = GameLevel.createSingleQF(0, name, desc, new KiteBasicProperties(loc), 0);
		g.addQuestionFactory(new KiteFindArea(loc), 0);
		g.addQuestionFactory(new KiteFindPerimeter(loc), 5);
		lgl.add(g);
		
		

		return lgl;

	}

}
