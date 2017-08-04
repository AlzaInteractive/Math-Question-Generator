package com.alza.quiz.qfactory.geom;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Square implements Shapes2D{
	private double length;
	
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
		l.add(Path.createLinePath(getVertices().get(3), getVertices().get(1)));
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
