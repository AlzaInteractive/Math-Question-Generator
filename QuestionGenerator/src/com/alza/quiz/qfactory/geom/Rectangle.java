package com.alza.quiz.qfactory.geom;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Rectangle implements Shapes2D{
	private double length;
	private double width;
	
	public Rectangle(){
		
	}
	public Rectangle(double length, double width){
		this.length = length;
		this.width = width;
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
	@Override
	public Shapes2D createExample() {
		int w,l;
		do {
			w = ThreadLocalRandom.current().nextInt(5, 26);
			l = ThreadLocalRandom.current().nextInt(5, 26);
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
		l.add(Path.createLinePath(getVertices().get(3), getVertices().get(1)));
		return l;
	}
}
