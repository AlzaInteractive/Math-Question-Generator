package com.alza.quiz.qfactory.geom;

import java.util.List;

public interface Shapes2D {
	public double getArea();
	public double getPerimeter();
	public int getReflectionalSymmetryCount();
	public int getRotationalSymmetryCount();
	public double getOccupiedLength();
	public double getOccupiedHeight();
	public String getLocalName(String lang);
	public Shapes2D createExample();
	public List<Path> getPaths(int width, int height, int margin);
	public List<Path> getPaths(int width, int height);
}
