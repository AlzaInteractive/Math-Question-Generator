package com.alza.quiz.model.geom;

import java.util.List;

public interface Shapes2D {
	public double getArea();
	public double getPerimeter();
	public List<Point2D> getVertices();//titik sudut
	public int getEdgeCount();//sisi
	public int getReflectionalSymmetryCount();
	public int getRotationalSymmetryCount();
	//public double getOccupiedLength();
	//public double getOccupiedHeight();
	public String getName();
	public Shapes2D createExample();
	public List<Path> getPaths();
	//public List<Path> getPaths(int width, int height, int margin);
	//public List<Path> getPaths(int width, int height);
}
