package com.alza.quiz.model.geom;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import com.alza.common.math.MathUtils;

public class Triangle implements Shapes2D{
	private double baseLine,height,shear;
	private boolean showHeightLine=true;
	private boolean showVerticeLabel=true;
	private boolean showHeightLength=true;
	private boolean showBaselineLength=true;
	private boolean showLeftEdgeLength=true;
	private boolean showRightEdgeLength=true;
	
	public Triangle() {
		
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
	public Triangle(double sideAB, double angleA, double angleB, double angleC) {
		double ratio = sideAB / Math.sin(Math.toRadians(angleC));
		//double sideBC = ratio * Math.sin(Math.toRadians(angleA));
		double sideBC = ratio * Math.sin(Math.toRadians(angleA));
		this.baseLine = sideBC;
		this.height = sideAB * Math.sin(Math.toRadians(angleB));
		this.shear = baseLine - sideAB * Math.cos(Math.toRadians(angleB));
	}
	
	@Override
	public void hideTextsAndMeasurements() {
		showVerticeLabel = false;
		showHeightLine = false;
		showHeightLength = false;
		showBaselineLength = false;
		showLeftEdgeLength = false;
		showRightEdgeLength = false;
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
	public boolean isShowVerticeLabel() {
		return showVerticeLabel;
	}
	public void setShowVerticeLabel(boolean showVerticeLabel) {
		this.showVerticeLabel = showVerticeLabel;
	}
	public boolean isShowLeftEdgeLength() {
		return showLeftEdgeLength;
	}
	public void setShowLeftEdgeLength(boolean showLeftEdgeLength) {
		this.showLeftEdgeLength = showLeftEdgeLength;
	}
	public boolean isShowRightEdgeLength() {
		return showRightEdgeLength;
	}
	public void setShowRightEdgeLength(boolean showRightEdgeLength) {
		this.showRightEdgeLength = showRightEdgeLength;
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
	
	public EdgeLengthRatio getType() {
		double d = 0.000001;	
		if (Math.abs(baseLine-getLeftEdge())<d
				&& Math.abs(baseLine-getRightEdge())<d) {
			return EdgeLengthRatio.equilateral;
		} else if (Math.abs(baseLine-getLeftEdge())<d
				||Math.abs(baseLine-getRightEdge())<d
				||Math.abs(getLeftEdge()-getRightEdge())<d) {
			return EdgeLengthRatio.iscosceles;
		}
		return EdgeLengthRatio.scalene;
	}
	

	@Override
	public double getPerimeter() {
		return baseLine + getLeftEdge() + getRightEdge();
	}
	

	@Override
	public int getReflectionalSymmetryCount() {
		switch (getType()) {
		case equilateral:
			return 3;
		case iscosceles:
			return 1;
		default:
			return 0;
		}
		
	}
	public AngleType getAngleType() {
		double d = 0.001;
		double[] edges = {baseLine,getLeftEdge(),getRightEdge()};
		Arrays.sort(edges);
		double c = edges[2] * edges[2];
		double b = edges[1] * edges[1];
		double a = edges[0] * edges[0];
		double x = c - (a+b);
		if (x > d) {
			return AngleType.obtuse;
		}
		else if (x < -d) {
			return AngleType.acute;
		}
		else {
			return AngleType.right;
		}
	}
	@Override
	public int getRotationalSymmetryCount() {
		switch (getType()) {
		case equilateral:
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
	public Shapes2D createExample(EdgeLengthRatio type) {
		Shapes2D tr = null;
		if(type==EdgeLengthRatio.equilateral) {
			double bs = ThreadLocalRandom.current().nextInt(5, 26);
			tr = new Triangle(bs, 60, 60, 60);
		} else if (type==EdgeLengthRatio.iscosceles) {
			int[] pyth = MathUtils.generateRandomPhytagoreanTriples(3, 5);
			double bs = 2 * pyth[1];
			double shear = pyth[1];
			double height = pyth[0];
			tr = new Triangle(bs, height, shear);
		} else {
			tr = createExample();
		}
		return tr;
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
		if (showLeftEdgeLength) {
			l.add(Path.createTextPath(Geom.formatMeasurement(getLeftEdge()),
					Point2D.getMidPoint(getVertices().get(0),getVertices().get(2)),Path.SHIFT_UP,Path.SHIFT_LEFT));
		}
		if (showRightEdgeLength) {
			l.add(Path.createTextPath(Geom.formatMeasurement(getRightEdge()),
					Point2D.getMidPoint(getVertices().get(0),getVertices().get(1)),Path.SHIFT_UP,Path.SHIFT_RIGHT));
		}
		return l;
	}
	public enum AngleType{
		acute, obtuse, right
	}
	public enum EdgeLengthRatio{
		equilateral, iscosceles,scalene 
	}
}
