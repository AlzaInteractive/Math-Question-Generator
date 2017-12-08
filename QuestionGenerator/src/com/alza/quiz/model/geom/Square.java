package com.alza.quiz.model.geom;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Square implements Shapes2D{
	private double length;
	private boolean showVerticeLabel=true;
	private boolean showDiagonalLine=true;
	private boolean showLengthValue=true;
	
	public Square(){
		
	}
	public Square(double length){
		this.length = length;
	}
	
	public double getLength() {
		return length;
	}

	public void setLength(double length) {
		this.length = length;
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
	public boolean isShowLengthValue() {
		return showLengthValue;
	}
	public void setShowLengthValue(boolean showLengthValue) {
		this.showLengthValue = showLengthValue;
	}
	@Override
	public double getArea() {
		return length * length;
	}

	@Override
	public double getPerimeter() {
		return length * 4;
	}
	@Override
	public int getReflectionalSymmetryCount() {
		return 4;
	}
	@Override
	public int getRotationalSymmetryCount() {
		return 4;
	}
	@Override
	public String getName() {
		return "square";
	}
	@Override
	public Shapes2D createExample() {
		int i = ThreadLocalRandom.current().nextInt(5, 26);
		Square a = new Square(i);
		return a;
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
		if (showLengthValue) {
			l.add(Path.createTextPath(Geom.formatMeasurement(this.length), 
					Point2D.getMidPoint(getVertices().get(0), getVertices().get(1)),
					Path.SHIFT_UP,Path.SHIFT_NONE));
		}
		if (showVerticeLabel) {
			l.add(Path.createTextPath(String.valueOf("A"), 
						getVertices().get(0),
						Path.SHIFT_UP, Path.SHIFT_LEFT));
			l.add(Path.createTextPath(String.valueOf("B"),
					getVertices().get(1),
					Path.SHIFT_UP, Path.SHIFT_RIGHT));
			l.add(Path.createTextPath(String.valueOf("C"),
					getVertices().get(2),
					Path.SHIFT_DOWN, Path.SHIFT_RIGHT));
			l.add(Path.createTextPath(String.valueOf("D"),
					getVertices().get(3),
					Path.SHIFT_DOWN, Path.SHIFT_LEFT));
		}
		return l;
	}
	public String toString(){
		String s = "Square with "+this.length+" length";
		return s;
	}
	@Override
	public List<Point2D> getVertices() {
		List<Point2D> points = new ArrayList<Point2D>();
		Point2D a,b,c,d;
		a = new Point2D(0, 0);
		b = new Point2D(this.length, 0);
		c = new Point2D(this.length, this.length);
		d = new Point2D(0, this.length);
		points.add(a);points.add(b);points.add(c);points.add(d);
		return points;
	}
	@Override
	public int getEdgeCount() {
		return 4;
	}
}
