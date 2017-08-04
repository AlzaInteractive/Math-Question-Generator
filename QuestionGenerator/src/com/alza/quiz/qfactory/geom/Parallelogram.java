package com.alza.quiz.qfactory.geom;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Parallelogram implements Shapes2D{
	private double length;
	private double height;
	private double shear;
	
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
		l.add(Path.createLinePath(getVertices().get(3), getVertices().get(1)));
		return l;
	}
	public String toString(){
		String s = "Parallelogram with "+this.length+" length, "+this.height+
				" height "+this.shear+" shear";
		return s;
	}
}
