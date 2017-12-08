package com.alza.quiz.model.geom;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Parallelogram implements Shapes2D{
	private double length;
	private double height;
	private double shear;
	private boolean showVerticeLabel=true;
	private boolean showDiagonalLine=false;
	private boolean showHeightLine=true;
	private boolean showLengthValue=true;
	private boolean showShearValue=true;
	private boolean showHeightValue=true;
	
	public Parallelogram(){
		
	}
	public Parallelogram(double length, double height, double shear){
		this.length = length;
		this.height = height;
		this.shear = shear;
	}
	
	public double getLength() {
		return length;
	}

	public void setLength(double length) {
		this.length = length;
	}

	public double getHeight() {
		return height;
	}
	public void setHeight(double height) {
		this.height = height;
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
	public boolean isShowHeightLine() {
		return showHeightLine;
	}
	public void setShowHeightLine(boolean showHeightLine) {
		this.showHeightLine = showHeightLine;
	}
	public boolean isShowLengthValue() {
		return showLengthValue;
	}
	public void setShowLengthValue(boolean showLengthValue) {
		this.showLengthValue = showLengthValue;
	}
	public boolean isShowShearValue() {
		return showShearValue;
	}
	public void setShowShearValue(boolean showShearValue) {
		this.showShearValue = showShearValue;
	}
	public boolean isShowHeightValue() {
		return showHeightValue;
	}
	public void setShowHeightValue(boolean showHeightValue) {
		this.showHeightValue = showHeightValue;
	}
	public double getSlope(){
		double slope;
		slope = Math.sqrt(((length-shear) * (length-shear)) + (height * height)) ; 
		return slope;
	}
	@Override
	public double getArea() {
		return height * length;
	}

	@Override
	public double getPerimeter() {
		return 2 * (length + getSlope());
	}
	@Override
	public int getReflectionalSymmetryCount() {
		return 0;
	}
	@Override
	public int getRotationalSymmetryCount() {
		return 2;
	}
	@Override
	public String getName() {
		return "parallelogram";
	}
	@Override
	public Shapes2D createExample() {
		int l,h,s;
		do {
			l = ThreadLocalRandom.current().nextInt(5, 26);
			h = ThreadLocalRandom.current().nextInt(5, 26);
			s = ThreadLocalRandom.current().nextInt(5, 10);
		} while (l==h || s>=l/3);
		
		return new Parallelogram(l, h, s);
	}
	
	@Override
	public List<Point2D> getVertices() {
		List<Point2D> points = new ArrayList<Point2D>();
		Point2D a,b,c,d;
		a = new Point2D(this.shear,0);
		b = new Point2D(this.length+this.shear, 0);
		c = new Point2D(this.length, this.height);
		d = new Point2D(0, this.height);
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
		
		double padding = length/25;
		if (showDiagonalLine) {
			l.add(Path.createLinePathDashed(getVertices().get(0), getVertices().get(2)));
			l.add(Path.createLinePathDashed(getVertices().get(1), getVertices().get(3)));
		}
		if (showHeightLine) {
			l.add(Path.createLinePathDashed(new Point2D(shear, 0), new Point2D(shear, height)));
		}
		if (showLengthValue) {
			l.add(Path.createTextPath(Geom.formatMeasurement(this.length), 
					new Point2D(length/2+shear, 0),Path.SHIFT_UP,Path.SHIFT_NONE));
		}
		if (showHeightValue) {
			l.add(Path.createTextPath(Geom.formatMeasurement(this.height), 
					new Point2D(shear, height/2),Path.SHIFT_NONE,Path.SHIFT_LEFT));
		}
		if (showShearValue) {
			l.add(Path.createTextPath(Geom.formatMeasurement(this.shear), 
					new Point2D(shear/2, height),Path.SHIFT_UP,Path.SHIFT_NONE));
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
		String s = "Parallelogram with "+this.length+" length, "+this.height+
				" height "+this.shear+" shear";
		return s;
	}
}
