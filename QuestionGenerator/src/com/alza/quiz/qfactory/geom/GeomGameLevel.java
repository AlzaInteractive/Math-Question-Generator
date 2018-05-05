package com.alza.quiz.qfactory.geom;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import com.alza.quiz.model.GameLevel;
import com.alza.quiz.qfactory.IPlayableLevelFactory;
import com.alza.quiz.qfactory.geom.circle.CircleFindAreaKnownDiameter;
import com.alza.quiz.qfactory.geom.circle.CircleFindAreaKnownRadius;
import com.alza.quiz.qfactory.geom.circle.CircleFindDiameterKnownArea;
import com.alza.quiz.qfactory.geom.circle.CircleFindDiameterKnownPerimeter;
import com.alza.quiz.qfactory.geom.circle.CircleFindPerimeterKnownDiameter;
import com.alza.quiz.qfactory.geom.circle.CircleFindPerimeterKnownRadius;
import com.alza.quiz.qfactory.geom.circle.CircleFindRadiusKnownArea;
import com.alza.quiz.qfactory.geom.circle.CircleFindRadiusKnownPerimeter;
import com.alza.quiz.qfactory.geom.kite.KiteBasicProperties;
import com.alza.quiz.qfactory.geom.kite.KiteFindArea;
import com.alza.quiz.qfactory.geom.kite.KiteFindPerimeter;
import com.alza.quiz.qfactory.geom.parallelogram.ParallelogramFindArea;
import com.alza.quiz.qfactory.geom.parallelogram.ParallelogramFindHeight;
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
import com.alza.quiz.qfactory.geom.trapezoid.TrapezoidFindHeight;
import com.alza.quiz.qfactory.geom.trapezoid.TrapezoidFindLength;
import com.alza.quiz.qfactory.geom.trapezoid.TrapezoidFindPerimeter;
import com.alza.quiz.qfactory.geom.trapezoid.TrapezoidFindPerimeterPyth;
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
public class GeomGameLevel implements IPlayableLevelFactory{
	public List<GameLevel> createGameLevels(Locale loc) {
		ResourceBundle bundle = ResourceBundle.getBundle("lang.langbundle", loc);

		List<GameLevel> lgl = new ArrayList<GameLevel>();
		String name, desc;
		GameLevel g;

		// identify 2d shapes
		name = bundle.getString("geom.shape2d.level.identify.title");
		desc = bundle.getString("geom.shape2d.level.identify.desc");
		g = GameLevel.createSingleQF(0, name, desc, new WhichGeom2DShapeQuestionFactory(loc), 8);
		lgl.add(g);

		// square
		name = bundle.getString("geom.shape2d.level.square.title");
		desc = bundle.getString("geom.shape2d.level.square.desc");
		g = GameLevel.createSingleQF(1, name, desc, new SquareBasicProperties(loc), 2);
		g.addQuestionFactory(new SquareFindAreaKnownLength(loc), 2);
		g.addQuestionFactory(new SquareFindPerimeterKnownLength(loc), 2);
		g.addQuestionFactory(new SquareFindLengthKnownArea(loc), 2);
		g.addQuestionFactory(new SquareFindLengthKnownPerimeter(loc), 2);
		lgl.add(g);

		// rectangle
		name = bundle.getString("geom.shape2d.level.rectangle.title");
		desc = bundle.getString("geom.shape2d.level.rectangle.desc");
		g = GameLevel.createSingleQF(2, name, desc, new RectangleBasicProperties(loc), 2);
		g.addQuestionFactory(new RectangleFindAreaKnownLengthWidth(loc), 2);
		g.addQuestionFactory(new RectangleFindPerimeterKnownLengthWidth(loc), 2);
		g.addQuestionFactory(new RectangleFindLengthKnownArea(loc), 2);
		g.addQuestionFactory(new RectangleFindLengthKnownPerimeter(loc), 2);
		g.addQuestionFactory(new RectangleFindDiagonalKnownLengthWidth(loc), 2);
		lgl.add(g);

		// triangle
		name = bundle.getString("geom.shape2d.level.triangle.title");
		desc = bundle.getString("geom.shape2d.level.triangle.desc");
		g = GameLevel.createSingleQF(3, name, desc, new TriangleBasicProperties(loc), 2);
		g.addQuestionFactory(new TriangleScaleneIscoscelesEquilateral(loc), 2);
		g.addQuestionFactory(new TriangleRightObtuseAcute(loc), 1);
		g.addQuestionFactory(new TriangleFindMissingAngle(loc), 1);
		g.addQuestionFactory(new TriangleFindAreaKnownBaseHeight(loc), 2);
		g.addQuestionFactory(new TriangleFindBaseLengthKnownArea(loc), 1);
		g.addQuestionFactory(new TriangleFindHeightLengthKnownArea(loc), 1);
		g.addQuestionFactory(new TrianglePhytagoreanFindArea(loc), 2);
		g.addQuestionFactory(new TrianglePhytagoreanFindRightEdge(loc), 2);
		lgl.add(g);

		// Parallelogram
		name = bundle.getString("geom.shape2d.level.parallelogram.title");
		desc = bundle.getString("geom.shape2d.level.parallelogram.desc");
		g = GameLevel.createSingleQF(4, name, desc, new ParallelogramFindArea(loc), 2);
		g.addQuestionFactory(new ParallelogramFindLength(loc), 2);
		g.addQuestionFactory(new ParallelogramFindHeight(loc), 2);
		g.addQuestionFactory(new ParallelogramFindPerimeter(loc), 2);
		lgl.add(g);

		// Trapezoid
		name = bundle.getString("geom.shape2d.level.trapezoid.title");
		desc = bundle.getString("geom.shape2d.level.trapezoid.desc");
		g = GameLevel.createSingleQF(5, name, desc, new TrapezoidFindArea(loc), 2);
		g.addQuestionFactory(new TrapezoidFindLength(loc), 1);
		g.addQuestionFactory(new TrapezoidFindHeight(loc), 1);
		g.addQuestionFactory(new TrapezoidFindPerimeter(loc), 1);
		g.addQuestionFactory(new TrapezoidFindPerimeterPyth(loc), 2);
		lgl.add(g);

		// Rhombus
		name = bundle.getString("geom.shape2d.level.rhombus.title");
		desc = bundle.getString("geom.shape2d.level.rhombus.desc");
		g = GameLevel.createSingleQF(6, name, desc, new RhombusBasicProperties(loc), 2);
		g.addQuestionFactory(new RhombusFindArea(loc), 2);
		g.addQuestionFactory(new RhombusFindPerimeter(loc), 2);
		//todo rhombus find diagonal
		lgl.add(g);

		//Kite
		name = bundle.getString("geom.shape2d.level.kite.title");
		desc = bundle.getString("geom.shape2d.level.kite.desc");
		g = GameLevel.createSingleQF(7, name, desc, new KiteBasicProperties(loc), 2);
		g.addQuestionFactory(new KiteFindArea(loc), 2);
		g.addQuestionFactory(new KiteFindPerimeter(loc), 2);
		lgl.add(g);

		//Circle
		name = bundle.getString("geom.shape2d.level.circle.title");
		desc = bundle.getString("geom.shape2d.level.circle.desc");
		g = GameLevel.createSingleQF(8, name, desc, new CircleFindAreaKnownRadius(loc), 1);
		g.addQuestionFactory(new CircleFindPerimeterKnownRadius(loc), 1);
		g.addQuestionFactory(new CircleFindAreaKnownDiameter(loc), 1);
		g.addQuestionFactory(new CircleFindPerimeterKnownDiameter(loc), 1);
		g.addQuestionFactory(new CircleFindRadiusKnownPerimeter(loc), 1);
		g.addQuestionFactory(new CircleFindRadiusKnownArea(loc), 1);
		g.addQuestionFactory(new CircleFindDiameterKnownPerimeter(loc), 1);
		g.addQuestionFactory(new CircleFindDiameterKnownArea(loc), 1);
		//g.addQuestionFactory(new KiteFindPerimeter(loc), 5);
		lgl.add(g);
		return lgl;

	}
	public GameLevel getGameLevel(int order, Locale loc){
		List<GameLevel> levels = createGameLevels(loc);
		for (GameLevel gameLevel : levels) {
			if (gameLevel.getOrder()==order) {
				return gameLevel;
			}
		}
		return null;
	}
	
	public GameLevel getExamLevel(Locale loc){
		ResourceBundle	bundle = ResourceBundle.getBundle("lang.langbundle", loc);
		String name,desc;
		name = bundle.getString("exam");
		desc = bundle.getString("examdesc");
		GameLevel g = GameLevel.createSingleQF(0, name, desc, new WhichGeom2DShapeQuestionFactory(loc), 2);
		g.addQuestionFactory(new SquareBasicProperties(loc), 1);
		g.addQuestionFactory(new SquareFindAreaKnownLength(loc), 1);
		g.addQuestionFactory(new SquareFindPerimeterKnownLength(loc), 1);
		g.addQuestionFactory(new RectangleBasicProperties(loc), 1);
		g.addQuestionFactory(new RectangleFindAreaKnownLengthWidth(loc), 1);
		g.addQuestionFactory(new RectangleFindPerimeterKnownLengthWidth(loc), 1);
		g.addQuestionFactory(new TriangleBasicProperties(loc), 1);
		g.addQuestionFactory(new TriangleFindAreaKnownBaseHeight(loc), 2);
		g.addQuestionFactory(new TriangleFindMissingAngle(loc), 1);
		g.addQuestionFactory(new TrianglePhytagoreanFindRightEdge(loc), 1);
		g.addQuestionFactory(new TriangleRightObtuseAcute(loc), 1);
		g.addQuestionFactory(new TriangleScaleneIscoscelesEquilateral(loc), 1);
		g.addQuestionFactory(new CircleFindAreaKnownRadius(loc), 1);
		g.addQuestionFactory(new CircleFindPerimeterKnownRadius(loc), 1);
		g.addQuestionFactory(new CircleFindDiameterKnownPerimeter(loc), 1);
		g.addQuestionFactory(new CircleFindRadiusKnownArea(loc), 1);
		g.addQuestionFactory(new ParallelogramFindArea(loc), 1);
		g.addQuestionFactory(new ParallelogramFindPerimeter(loc), 1);
		g.addQuestionFactory(new ParallelogramFindHeight(loc), 1);
		g.addQuestionFactory(new TrapezoidFindArea(loc), 1);
		g.addQuestionFactory(new TrapezoidFindPerimeter(loc), 1);
		g.addQuestionFactory(new TrapezoidFindHeight(loc), 1);
		g.addQuestionFactory(new RhombusBasicProperties(loc), 1);
		g.addQuestionFactory(new RhombusFindPerimeter(loc), 1);
		g.addQuestionFactory(new RhombusFindArea(loc), 1);
		g.addQuestionFactory(new KiteBasicProperties(loc), 1);
		g.addQuestionFactory(new KiteFindArea(loc), 1);
		g.addQuestionFactory(new KiteFindPerimeter(loc), 1);
		return g;
	}

}
