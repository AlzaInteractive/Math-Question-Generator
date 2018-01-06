package com.alza.quiz.qfactory.geom;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import com.alza.quiz.model.GameLevel;
import com.alza.quiz.qfactory.geom.square.SquareBasicProperties;
import com.alza.quiz.qfactory.geom.square.SquareFindAreaKnownLength;
import com.alza.quiz.qfactory.geom.square.SquareFindLengthKnownArea;
import com.alza.quiz.qfactory.geom.square.SquareFindLengthKnownPerimeter;
import com.alza.quiz.qfactory.geom.square.SquareFindPerimeterKnownLength;
/**
 * 
 * @author ewien
 * Game level factory for geometry problems
 *
 */
public class GeomGameLevel {
	public static List<GameLevel> createGameLevels(Locale loc) {
		ResourceBundle	bundle = ResourceBundle.getBundle("lang.langbundle", loc);
		
		List<GameLevel> lgl = new ArrayList<GameLevel>();
		String name,desc;
		GameLevel g;
		
		//identify 2d shapes
		name = bundle.getString("geom.shape2d.level.identify.title");
		desc = bundle.getString("geom.shape2d.level.identify.desc");
		g = GameLevel.createSingleQF(0, name, desc, new WhichGeom2DShapeQuestionFactory(loc), 7);
		lgl.add(g);
		
		//square
		name = bundle.getString("geom.shape2d.level.square.title");
		desc = bundle.getString("geom.shape2d.level.square.desc");
		g = GameLevel.createSingleQF(0, name, desc, new SquareBasicProperties(loc), 6);
		g.addQuestionFactory(new SquareFindAreaKnownLength(loc),2);
		g.addQuestionFactory(new SquareFindPerimeterKnownLength(loc),2);
		g.addQuestionFactory(new SquareFindLengthKnownArea(loc),2);
		g.addQuestionFactory(new SquareFindLengthKnownPerimeter(loc),2);
		lgl.add(g);
		
		return lgl;
	}
	
}
