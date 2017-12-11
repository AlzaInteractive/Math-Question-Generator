package com.alza.quiz.model.geom;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Kite implements Shapes2D{
	private double diagonalHoriz;
	private double diagonalVert;
	private double shear;
	private boolean showVerticeLabel=true;
	private boolean showDiagonalLine=true;
	private boolean showDiagonalLength=true;
	public Kite(){
		
	}
	public Kite(double diagHoriz, double diagVert, double shear){
		this.diagonalHoriz = diagHoriz;
		this.diagonalVert = diagVert;
		this.shear = shear;
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
	public double getShear() {
		return shear;
	}
	public void setShear(double shear) {
		this.shear = shear;
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
		return 0;
	}
	@Override
	public int getRotationalSymmetryCount() {
		return 0;
	}
	@Override
	public String getName() {
		return "kite";
	}
	@Override
	public Shapes2D createExample() {
		int p,q,s;
		do {
			p = ThreadLocalRandom.current().nextInt(5, 26);
			q = ThreadLocalRandom.current().nextInt(5, 26);
			s = ThreadLocalRandom.current().nextInt(5, 26);
		} while (s>=q||s==q/2);
		Kite k = new Kite(p, q, s);
		return k;
	}
	@Override
	public List<Point2D> getVertices() {
		List<Point2D> points = new ArrayList<Point2D>();
		Point2D a,b,c,d;
		a = new Point2D(0, shear);
		b = new Point2D(diagonalHoriz/2, 0);
		c = new Point2D(diagonalHoriz, shear);
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
					new Point2D(diagonalHoriz/3, shear),
					Path.SHIFT_UP,Path.SHIFT_NONE));
			l.add(Path.createTextPath(Geom.formatMeasurement(shear), new Point2D(diagonalHoriz/2, shear/2),
					Path.SHIFT_NONE,Path.SHIFT_RIGHT));
			l.add(Path.createTextPath(Geom.formatMeasurement(diagonalVert-shear), 
					new Point2D(diagonalHoriz/2, shear + (diagonalVert-shear)/2),
					Path.SHIFT_DOWN,Path.SHIFT_NONE));
		}
		if (showVerticeLabel) {
			l.add(Path.createTextPath(String.valueOf("A"), getVertices().get(0), Path.SHIFT_UP,Path.SHIFT_LEFT));
			l.add(Path.createTextPath(String.valueOf("B"), getVertices().get(1), Path.SHIFT_UP,Path.SHIFT_LEFT));
			l.add(Path.createTextPath(String.valueOf("C"), getVertices().get(2), Path.SHIFT_UP,Path.SHIFT_RIGHT));
			l.add(Path.createTextPath(String.valueOf("D"), getVertices().get(3), Path.SHIFT_DOWN,Path.SHIFT_LEFT));
		}
		return l;
	}
	public String toString(){
		String s = "Kite with "+this.diagonalHoriz+" horizontal diagonal, "+this.diagonalVert+
				" vertical diagonal "+this.shear+" shear";
		return s;
	}
}
