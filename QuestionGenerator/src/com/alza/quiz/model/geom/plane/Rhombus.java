package com.alza.quiz.model.geom.plane;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import com.alza.quiz.model.geom.Path;
import com.alza.quiz.model.geom.Point2D;
import com.alza.quiz.model.geom.Shapes2D;

public class Rhombus implements Shapes2D{
	private double diagonalHoriz;
	private double diagonalVert;
	private boolean showVerticeLabel=true;
	private boolean showDiagonalLine=true;
	private boolean showDiagonalLength=true;
	public Rhombus(){
		
	}
	public Rhombus(double p, double q){
		this.diagonalHoriz = p;
		this.diagonalVert = q;
	}
	public Rhombus(double edge, int angleVertical) {
		this.diagonalHoriz = 2 * edge * Math.sin(Math.toRadians(angleVertical/2));
		this.diagonalVert = 2 * edge * Math.cos(Math.toRadians(angleVertical/2));
	}
	@Override
	public void hideTextsAndMeasurements() {
		showVerticeLabel = false;
		showDiagonalLine = false;
		showDiagonalLength = false;
	}
	public double getDiagonalHoriz() {
		return diagonalHoriz;
	}
	public void setDiagonalHoriz(double diagonalHoriz) {
		this.diagonalHoriz = diagonalHoriz;
	}
	public double getDiagonalVert() {
		return diagonalVert;
	}
	public void setDiagonalVert(double diagonalVert) {
		this.diagonalVert = diagonalVert;
	}
	public boolean isShowVerticeLabel() {
		return showVerticeLabel;
	}
	public void setShowVerticeLabel(boolean showVerticeLabel) {
		this.showVerticeLabel = showVerticeLabel;
	}
	public boolean isShowDiagonalLine() {
		return showDiagonalLine;
	}
	public void setShowDiagonalLine(boolean showDiagonalLine) {
		this.showDiagonalLine = showDiagonalLine;
	}
	public boolean isShowDiagonalLength() {
		return showDiagonalLength;
	}
	public void setShowDiagonalLength(boolean showDiagonalLength) {
		this.showDiagonalLength = showDiagonalLength;
	}
	@Override
	public double getArea() {
		return diagonalHoriz * diagonalVert / 2;
	}

	@Override
	public double getPerimeter() {
		return 2 * Math.sqrt((diagonalHoriz * diagonalHoriz) + (diagonalVert * diagonalVert));
	}
	@Override
	public int getReflectionalSymmetryCount() {
		return 2;
	}
	@Override
	public int getRotationalSymmetryCount() {
		return 2;
	}
	@Override
	public String getName() {
		return "rhombus";
	}
	@Override
	public Shapes2D createExample() {
		double p,q;
		do {
			p = ThreadLocalRandom.current().nextInt(5, 10);
			q = ThreadLocalRandom.current().nextInt(5, 10);
		} while (p==q||p>(2*q)||q>(2*p));
		Rhombus r = new Rhombus(p, q);
		return r;
	}
	
	public String toString(){
		String s = "Rhombus with "+this.diagonalHoriz+" horizontal diagonal, "+this.diagonalVert+" vertical diagonal";
		return s;
	}
	@Override
	public List<Point2D> getVertices() {
		List<Point2D> points = new ArrayList<Point2D>();
		Point2D a,b,c,d;
		a = new Point2D(0, diagonalVert/2);
		b = new Point2D(diagonalHoriz/2, 0);
		c = new Point2D(diagonalHoriz, diagonalVert/2);
		d = new Point2D(diagonalHoriz/2, diagonalVert);
		points.add(a);points.add(b);points.add(c);points.add(d);
		return points;
	}
	@Override
	public int getEdgeCount() {
		return 4;
	}
	@Override
	public List<Path> getPaths() {
		List<Path> l = new ArrayList<Path>();
		l.add(Path.createLinePath(getVertices().get(0), getVertices().get(1)));
		l.add(Path.createLinePath(getVertices().get(1), getVertices().get(2)));
		l.add(Path.createLinePath(getVertices().get(2), getVertices().get(3)));
		l.add(Path.createLinePath(getVertices().get(3), getVertices().get(0)));
		if (showDiagonalLine) {
			l.add(Path.createLinePathDashed(getVertices().get(0), getVertices().get(2)));
			l.add(Path.createLinePathDashed(getVertices().get(1), getVertices().get(3)));
		}
		if (showDiagonalLength) {
			l.add(Path.createTextPath(Geom.formatMeasurement(diagonalHoriz), 
					new Point2D(diagonalHoriz/2.5, diagonalVert/2),
					Path.SHIFT_UP,Path.SHIFT_NONE));
			l.add(Path.createTextPath(Geom.formatMeasurement(diagonalVert), 
					new Point2D(diagonalHoriz/2, diagonalVert/2.5),
					Path.SHIFT_NONE,Path.SHIFT_RIGHT));
		}
		if (showVerticeLabel) {
			l.add(Path.createTextPath(String.valueOf("A"), getVertices().get(0), Path.SHIFT_UP,Path.SHIFT_LEFT));
			l.add(Path.createTextPath(String.valueOf("B"), getVertices().get(1), Path.SHIFT_UP,Path.SHIFT_LEFT));
			l.add(Path.createTextPath(String.valueOf("C"), getVertices().get(2), Path.SHIFT_UP,Path.SHIFT_RIGHT));
			l.add(Path.createTextPath(String.valueOf("D"), getVertices().get(3), Path.SHIFT_DOWN,Path.SHIFT_LEFT));
		}
		
		return l;
	}
}
