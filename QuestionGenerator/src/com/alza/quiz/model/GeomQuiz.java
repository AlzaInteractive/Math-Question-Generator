package com.alza.quiz.model;

import java.util.List;

import com.alza.quiz.qfactory.geom.model.Path;

public class GeomQuiz extends Quiz {
	private List<Path> geomPaths;        
    public List<Path> getGeomShape() {
		return geomPaths;
	}
	public void setGeomShape(List<Path> geomPaths) {
		this.geomPaths = geomPaths;
	}
}
