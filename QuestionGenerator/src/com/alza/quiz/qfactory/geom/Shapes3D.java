package com.alza.quiz.qfactory.geom;

import java.util.List;

public interface Shapes3D {
	public double getSurfaceArea();
	public double getVolume();
	public List<Shapes2D> getFaces(); //sisi
	public int getVerticesCount(); //titik sudut
	public int getEdgeCount(); //rusuk
	public double get2DOccupiedLength(double projRatio, double projAngle);
	public double get2DOccupiedHeight(double projRatio, double projAngle);
	public String getName();
	public Shapes3D createExample();
	public List<Path> getPaths(int canvasWidth, int canvasHeight, int margin, double projRatio, double projAngle);
	public List<Path> getPaths(int canvasWidth, int canvasHeight);
}
