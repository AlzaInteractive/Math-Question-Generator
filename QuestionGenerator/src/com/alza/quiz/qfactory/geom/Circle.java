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
	@Override
	public double getOccupiedLength() {
		return 2 * radius;
	}
	@Override
	public double getOccupiedHeight() {
		return 2 * radius;
	}
	
	@Override
	public List<Path> getPaths(int width, int height, int margin) {
		if (margin >= width/2 || margin >= height/2){
			margin = 0;
		}
		System.out.println("margin "+margin);
		List<Path> l = new ArrayList<Path>();
		int allocatedSpace=0;
		if (width<=height){
			allocatedSpace = width - (2 * margin);
		} else {
			allocatedSpace = height - (2 * margin);
		}
		System.out.println("allocated space "+allocatedSpace);
		l.add(Path.createCirclePath(new Point(width/2, height/2), allocatedSpace/2));
		return l;
	}
	@Override
	public List<Path> getPaths(int width, int height) {
		int margin;
		if (width >= height) {
			margin = height / 5;
		} else margin = width / 5;
		return getPaths(width, height, margin);
	}
	public String toString(){
		String s = "Circle with "+this.radius+" radius";
		return s;
	}
}
