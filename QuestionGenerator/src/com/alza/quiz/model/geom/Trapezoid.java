package com.alza.quiz.model.geom;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Trapezoid implements Shapes2D{
	private double topLength;
	private double bottomLength;
	private double height;
	private double shearLeft;
	private boolean showVerticeLabel=true;
	private boolean showDiagonalLine=false;
	private boolean showHeightLine=true;
	private boolean showTopLengthValue=true;
	private boolean showBottomLengthValue=true;
	private boolean showHeightValue=true;
	private boolean showShearLeftValue=true;
	private boolean showRightSlopeValue=true;
	private boolean showLeftSlopeValue=true;
	
	public Trapezoid(){
		
	}
	public Trapezoid(double topLength, double bottomLength, double height, double shearLeft){
		this.topLength = topLength;
		this.bottomLength = bottomLength;
		this.height = height;
		this.shearLeft = shearLeft;
	}
	@Override
	public void hideTextsAndMeasurements() {
		showVerticeLabel = false;
		showDiagonalLine = false;
		showHeightLine = false;
		showTopLengthValue = false;
		showBottomLengthValue = false;
		showHeightValue = false;
		showShearLeftValue = false;
		showRightSlopeValue = false;
		showLeftSlopeValue = false;
	}
	public double getTopLength() {
		return topLength;
	}

	public void setBottomLength(double length) {
		this.topLength = length;
	}

	public double getHeight() {
		return height;
	}
	public void setHeight(double height) {
		this.height = height;
	}
	public double getShearLeft() {
		return shearLeft;
	}
	public void setShearLeft(double shearLeft) {
		this.shearLeft = shearLeft;
	}
	public double getShearRight() {
		//double sr = Math.abs(bottomLength-topLength) - shearLeft;
		//return sr;
		return bottomLength - (topLength + shearLeft);
	}
	public double getLeftSlope(){
		double slope;
		double s = shearLeft;
		slope = Math.sqrt((s * s) + (height * height)) ; 
		return slope;
	}
	public double getRightSlope(){
		double slope;
		double s = getShearRight();
		slope = Math.sqrt((s * s) + (height * height)) ; 
		return slope;
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
	public boolean isShowTopLengthValue() {
		return showTopLengthValue;
	}
	public void setShowTopLengthValue(boolean showTopLengthValue) {
		this.showTopLengthValue = showTopLengthValue;
	}
	public boolean isShowBottomLengthValue() {
		return showBottomLengthValue;
	}
	public void setShowBottomLengthValue(boolean showBottomLengthValue) {
		this.showBottomLengthValue = showBottomLengthValue;
	}
	public boolean isShowHeightValue() {
		return showHeightValue;
	}
	public void setShowHeightValue(boolean showHeightValue) {
		this.showHeightValue = showHeightValue;
	}
	public boolean isShowShearLeftValue() {
		return showShearLeftValue;
	}
	public void setShowShearLeftValue(boolean showShearLeftValue) {
		this.showShearLeftValue = showShearLeftValue;
	}
	public boolean isShowRightSlopeValue() {
		return showRightSlopeValue;
	}
	public void setShowRightSlopeValue(boolean showRightSlopeValue) {
		this.showRightSlopeValue = showRightSlopeValue;
	}
	public boolean isShowLeftSlopeValue() {
		return showLeftSlopeValue;
	}
	public void setShowLeftSlopeValue(boolean showLeftSlopeValue) {
		this.showLeftSlopeValue = showLeftSlopeValue;
	}
	public double getBottomLength() {
		return bottomLength;
	}
	public void setTopLength(double topLength) {
		this.topLength = topLength;
	}
	@Override
	public double getArea() {
		return height * (topLength+bottomLength) * 0.5;
	}

	@Override
	public double getPerimeter() {
		return topLength + bottomLength + getLeftSlope() + getRightSlope();
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
		return "trapezoid";
	}
	@Override
	public Shapes2D createExample() {
		int tl,h,sl,bl;
		do {
			tl = ThreadLocalRandom.current().nextInt(5, 26);
			h = ThreadLocalRandom.current().nextInt(5, 10);
			bl = ThreadLocalRandom.current().nextInt(5, 26);
			
		} while (tl==bl);
		sl = ThreadLocalRandom.current().nextInt(0, Math.abs(bl-tl));
		if (bl<tl) {
			sl = sl * -1;
		}
		//System.out.println("new trapezoid:"+tl+" "+bl+" "+h+" "+sl);
		return new Trapezoid(tl, bl, h, sl);
	}
	
	@Override
	public List<Point2D> getVertices() {
		List<Point2D> points = new ArrayList<Point2D>();
		Point2D a,b,c,d;
		if (this.bottomLength>this.topLength) {
			a = new Point2D(this.shearLeft,0);
			b = new Point2D(this.topLength+this.shearLeft, 0);
			c = new Point2D(this.bottomLength, this.height);
			d = new Point2D(0, this.height);
		} else {
			a = new Point2D(0,0);
			b = new Point2D(this.topLength, 0);
			c = new Point2D(-this.shearLeft+this.bottomLength, this.height);
			d = new Point2D(-this.shearLeft, this.height);
		}
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
		
		double padding = Math.max(topLength, bottomLength)/25;
		if (showDiagonalLine) {
			l.add(Path.createLinePathDashed(getVertices().get(0), getVertices().get(2)));
			l.add(Path.createLinePathDashed(getVertices().get(1), getVertices().get(3)));
		}
		if (showHeightLine) {
			if (bottomLength > topLength){
				l.add(Path.createLinePathDashed(getVertices().get(0), new Point2D(shearLeft, height)));
			} else {
				l.add(Path.createLinePathDashed(getVertices().get(3), new Point2D(-shearLeft, 0)));
			}
			
		}
		if (showTopLengthValue) {
			l.add(Path.createTextPath(Geom.formatMeasurement(topLength), 
					Point2D.getMidPoint(getVertices().get(0), 
							getVertices().get(1)),
							Path.SHIFT_UP,Path.SHIFT_NONE)); //top length measurement
			
			
			
		}
		if (showBottomLengthValue){
			l.add(Path.createTextPath(Geom.formatMeasurement(bottomLength), 
					Point2D.getMidPoint(getVertices().get(2), 
							getVertices().get(3)),Path.SHIFT_DOWN,Path.SHIFT_NONE)); //bottom length measurement
		}
		if (showHeightValue) {
			if (bottomLength > topLength) {
				l.add(Path.createTextPath(Geom.formatMeasurement(this.height), 
						new Point2D(shearLeft, height/2),
						Path.SHIFT_NONE,Path.SHIFT_LEFT));
			} else {
				l.add(Path.createTextPath(Geom.formatMeasurement(this.height), 
						new Point2D(-shearLeft+padding, height/2),
						Path.SHIFT_NONE,Path.SHIFT_LEFT));
			}
		}
		if (showShearLeftValue) {
			double xpos = shearLeft/2;
			double ypos = height;
			Point2D pos = new Point2D(xpos, ypos);
			l.add(Path.createTextPath(
					Geom.formatMeasurement(this.shearLeft), 
					pos,
					Path.SHIFT_UP,Path.SHIFT_NONE));
			
		}
		if (showVerticeLabel) {
			l.add(Path.createTextPath(String.valueOf("A"), getVertices().get(0),Path.SHIFT_UP,Path.SHIFT_LEFT));
			l.add(Path.createTextPath(String.valueOf("B"), getVertices().get(1),Path.SHIFT_UP, Path.SHIFT_RIGHT));
			l.add(Path.createTextPath(String.valueOf("C"), getVertices().get(2),Path.SHIFT_DOWN, Path.SHIFT_RIGHT));
			l.add(Path.createTextPath(String.valueOf("D"), getVertices().get(3),Path.SHIFT_DOWN, Path.SHIFT_LEFT));
		}
		if (showLeftSlopeValue) {
			l.add(Path.createTextPath(Geom.formatMeasurement(getLeftSlope()), 
					Point2D.getMidPoint(getVertices().get(0), 
							getVertices().get(3)),Path.SHIFT_NONE,Path.SHIFT_LEFT));
		}
		if (showRightSlopeValue) {
			l.add(Path.createTextPath(Geom.formatMeasurement(getRightSlope()), 
					Point2D.getMidPoint(getVertices().get(1), 
							getVertices().get(2)),Path.SHIFT_NONE,Path.SHIFT_RIGHT));
		}
		
		return l;
	}
	public String toString(){
		String s = "Trapezoid with "+this.topLength+" top length, "+
				this.bottomLength+" bottom length, "+
				this.height+ " height "
				+this.shearLeft+" shear left "+getShearRight()+" shear right";
		return s;
	}
}
