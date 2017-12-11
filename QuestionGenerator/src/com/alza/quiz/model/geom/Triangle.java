package com.alza.quiz.model.geom;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Triangle implements Shapes2D{
	private double baseLine,height,shear;
	private boolean showHeightLine=true;
	private boolean showVerticeLabel=true;
	private boolean showHeightLength=true;
	private boolean showBaselineLength=true;
	public static final int SCALENE = 1;
	public static final int ISCOSCELES = 2;
	public static final int EQUILATERAL = 3;
	public Triangle() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * 
	 * @param baseLine 
	 * @param height, orthogonal
	 * @param shear
	 */
	public Triangle(double baseLine, double height, double shear) {
		super();
		this.baseLine = baseLine;
		this.height = height;
		this.shear = shear;
	}
	public Triangle(double baseLine, double height) {
		super();
		this.baseLine = baseLine;
		this.height = height;
		this.shear = baseLine/2;
	}
	
	public double getBaseLine() {
		return baseLine;
	}
	public void setBaseLine(double baseLine) {
		this.baseLine = baseLine;
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
	public boolean isShowHeightLine() {
		return showHeightLine;
	}
	public void setShowHeightLine(boolean showHeightLine) {
		this.showHeightLine = showHeightLine;
	}
	public boolean isShowVerticesLabel() {
		return showVerticeLabel;
	}
	public void setShowVerticesLabel(boolean showVerticesLabel) {
		this.showVerticeLabel = showVerticesLabel;
	}
	public boolean isShowHeightLength() {
		return showHeightLength;
	}
	public void setShowHeightLength(boolean showHeightLength) {
		this.showHeightLength = showHeightLength;
	}
	public boolean isShowBaselineLength() {
		return showBaselineLength;
	}
	public void setShowBaselineLength(boolean showBaselineLength) {
		this.showBaselineLength = showBaselineLength;
	}
	@Override
	public double getArea() {
		return baseLine * height / 2;
	}
	
	public double getLeftEdge() {
		return Math.sqrt((height*height)+(shear*shear));
	}
	
	public double getRightEdge() {
		return Math.sqrt((height*height)+((baseLine-shear)*(baseLine-shear)));
	}
	
	public int getType() {
		if (baseLine==getLeftEdge()||baseLine==getRightEdge()) {
			if (getLeftEdge()==getRightEdge()) {
				return EQUILATERAL;
			}
			return ISCOSCELES;
		}
		return SCALENE;
	}
	

	@Override
	public double getPerimeter() {
		return baseLine + getLeftEdge() + getRightEdge();
	}
	

	@Override
	public int getReflectionalSymmetryCount() {
		switch (getType()) {
		case EQUILATERAL:
			return 3;
		case ISCOSCELES:
			return 1;
		default:
			return 0;
		}
		
	}

	@Override
	public int getRotationalSymmetryCount() {
		switch (getType()) {
		case EQUILATERAL:
			return 3;
		default:
			return 0;
		}
	}
	@Override
	public String getName() {
		return "triangle";
	}

	@Override
	public Shapes2D createExample() {
		int bs = ThreadLocalRandom.current().nextInt(5, 26);
		int ht = ThreadLocalRandom.current().nextInt(5, 26);
		int sh = ThreadLocalRandom.current().nextInt(0, bs+1);
		return new Triangle(bs, ht, sh);
	}
	public String toString(){
		String s = "Triangle with "+this.baseLine+" base"+this.height+" height"+this.shear+" shear";
		return s;
	}
	@Override
	public List<Point2D> getVertices() {
		List<Point2D> points = new ArrayList<Point2D>();
		Point2D a,b,c;
		a = new Point2D(this.shear, 0);
		b = new Point2D(this.baseLine, this.height);
		c = new Point2D(0, this.height);
		points.add(a);points.add(b);points.add(c);
		return points;
	}
	@Override
	public int getEdgeCount() {
		return 3;
	}
	@Override
	public List<Path> getPaths() {
		List<Path> l = new ArrayList<Path>();
		l.add(Path.createLinePath(getVertices().get(0), getVertices().get(1)));
		l.add(Path.createLinePath(getVertices().get(1), getVertices().get(2)));
		l.add(Path.createLinePath(getVertices().get(2), getVertices().get(0)));
		
		if (showVerticeLabel) {
			l.add(Path.createTextPath(String.valueOf("A"), getVertices().get(0), Path.SHIFT_UP,Path.SHIFT_LEFT));
			l.add(Path.createTextPath(String.valueOf("B"), getVertices().get(1), Path.SHIFT_DOWN,Path.SHIFT_RIGHT));
			l.add(Path.createTextPath(String.valueOf("C"), getVertices().get(2), Path.SHIFT_DOWN,Path.SHIFT_LEFT));
		}
		if (showHeightLine) {
			l.add(Path.createLinePathDashed(getVertices().get(0), getVertices().get(2).move(shear, 0)));
		}
		if (showHeightLength) {
			l.add(Path.createTextPath(Geom.formatMeasurement(height), getVertices().get(0).move(0, height/2), Path.SHIFT_NONE,Path.SHIFT_LEFT));
		}
		if (showBaselineLength) {
			l.add(Path.createTextPath(Geom.formatMeasurement(baseLine), getVertices().get(2).move(baseLine/2, 0), Path.SHIFT_DOWN,Path.SHIFT_NONE));
		}
		
		return l;
	}

}
