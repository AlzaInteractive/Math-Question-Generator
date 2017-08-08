package com.alza.quiz.model;

import com.alza.quiz.qfactory.geom.model.Shapes2D;

/**
 * Created by galuh on 24/12/16.
 */

public class GeomQuiz extends Quiz {
	Shapes2D geomShape;        
    public Shapes2D getGeomShape() {
		return geomShape;
	}
	public void setGeomShape(Shapes2D geomShape) {
		this.geomShape = geomShape;
	}
}
