package com.alza.quiz.qfactory.geom;

public interface Shapes2D {
	public double getArea();
	public double getPerimeter();
	public int getReflectionalSymmetryCount();
	public int getRotationalSymmetryCount();
	public double getOccupiedLength();
	public double getOccupiedHeight();
	public String getLocalName(String lang);
	public Shapes2D createExample();
}
