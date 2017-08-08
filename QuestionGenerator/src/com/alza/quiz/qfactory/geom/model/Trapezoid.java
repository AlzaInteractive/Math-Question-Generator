package com.alza.quiz.qfactory.geom.model;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Trapezoid implements Shapes2D{
	private double topLength;
	private double bottomLength;
	private double height;
	private double shearLeft;
	
	public Trapezoid(){
		
	}
	public Trapezoid(double topLength, double bottomLength, double height, double shearLeft){
		this.topLength = topLength;
		this.bottomLength = bottomLength;
		this.height = height;
		this.shearLeft = shearLeft;
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
