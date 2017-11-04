package com.alza.quiz.model.geom;

import java.util.List;

public interface Shapes3D {
	public double getSurfaceArea();
	public double getVolume();
	public List<Shapes2D> getFaces(); //sisi
	public List<Point3D> getVertices();
	public int getEdgeCount(); //rusuk
	public String getName();
	public Shapes3D createExample();
	public List<Path> getPaths();
}
