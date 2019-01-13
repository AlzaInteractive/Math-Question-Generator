package com.alza.quiz.model.geom.plane;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import com.alza.quiz.model.geom.Path;
import com.alza.quiz.model.geom.Point2D;
import com.alza.quiz.model.geom.Shapes2D;

public class Rectangle implements Shapes2D{
	private double length;
	private double width;
	private boolean showVerticeLabel=true;
	private boolean showDiagonalLine=true;
	private boolean showLengthValue=true;
	private boolean showWidthValue=true;
	public Rectangle(){
		
	}
	public Rectangle(double length, double width){
		this.length = length;
		this.width = width;
	}
	@Override
	public void hideTextsAndMeasurements() {
		showVerticeLabel = false;
		showDiagonalLine = false;
		showLengthValue = false;
		showWidthValue = false;
	}
	
	public double getLength() {
		return length;
	}

	public void setLength(double length) {
		this.length = length;
	}

	public double getWidth() {
		return width;
	}
	public void setWidth(double width) {
		this.width = width;
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
	public boolean isShowWidthValue() {
		return showWidthValue;
	}
	public void setShowWidthValue(boolean showWidthValue) {
		this.showWidthValue = showWidthValue;
	}
	@Override
	public double getArea() {
		return length * width;
	}

	@Override
	public double getPerimeter() {
		return 2 * (length + width);
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
		return "rectangle";
	}
	public double getDiagonalLength() {
		return Math.sqrt((length*length)+(width*width));
	}
	@Override
	public Shapes2D createExample() {
		int w,l;
		do {
			w = ThreadLocalRandom.current().nextInt(5, 10);
			l = ThreadLocalRandom.current().nextInt(5, 10);
		} while (w==l);
		Rectangle r = new Rectangle(l, w);
		return r;
	}
	
	public String toString(){
		String s = "Rectangle with "+this.length+" length, "+this.width+" width";
		return s;
	}
	@Override
	public List<Point2D> getVertices() {
		List<Point2D> points = new ArrayList<Point2D>();
		Point2D a,b,c,d;
		a = new Point2D(0, 0);
		b = new Point2D(this.length, 0);
		c = new Point2D(this.length, this.width);
		d = new Point2D(0, this.width);
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
		if (showLengthValue) {
			l.add(Path.createTextPath(Geom.formatMeasurement(this.length), 
					Point2D.getMidPoint(getVertices().get(0), getVertices().get(1)),
					Path.SHIFT_UP,Path.SHIFT_NONE));
		}
		if (showWidthValue) {
			l.add(Path.createTextPath(Geom.formatMeasurement(this.width), 
					Point2D.getMidPoint(getVertices().get(1), getVertices().get(2)),
					Path.SHIFT_NONE,Path.SHIFT_RIGHT));
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
}
