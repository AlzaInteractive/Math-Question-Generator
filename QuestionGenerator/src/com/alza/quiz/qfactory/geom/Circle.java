package com.alza.quiz.qfactory.geom;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Circle implements Shapes2D{
	private double radius;
	
	public Circle(){
		
	}
	public Circle(double radius){
		this.radius = radius;
	}
	
	public double getLength() {
		return radius;
	}

	public void setLength(double radius) {
		this.radius = radius;
	}

	@Override
	public double getArea() {
		return Math.PI * radius * radius;
	}

	@Override
	public double getPerimeter() {
		return Math.PI * radius * 2;
	}
	@Override
	public int getReflectionalSymmetryCount() {
		return -1;
	}
	@Override
	public int getRotationalSymmetryCount() {
		return -1;
	}
	@Override
	public String getName() {
		return "circle";
	}
	@Override
	public Shapes2D createExample() {
		int r = ThreadLocalRandom.current().nextInt(5, 26);
		return new Circle(r);
	}
	public String toString(){
		String s = "Circle with "+this.radius+" radius";
		return s;
	}
	@Override
	public List<Point2D> getVertices() {
		return null;
	}
	@Override
	public int getEdgeCount() {
		return 0;
	}
	@Override
	public List<Path> getPaths() {
		List<Path> l = new ArrayList<Path>();
		l.add(Path.createCirclePath(new Point2D(radius, radius), radius));
		return l;
	}
}
