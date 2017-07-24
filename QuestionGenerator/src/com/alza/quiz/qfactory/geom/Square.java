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
	public String getLocalName(String lang) {
		switch (lang) {
		case "EN":
			return "square";
		case "ID":
			return "persegi";
		default:
			break;
		}
		return null;
	}
	@Override
	public Shapes2D createExample() {
		int i = ThreadLocalRandom.current().nextInt(5, 26);
		Square a = new Square(i);
		return a;
	}
	@Override
	public double getOccupiedLength() {
		return this.length;
	}
	@Override
	public double getOccupiedHeight() {
		return this.length;
	}
	@Override
	public List<Path> getPaths(int pxWidth, int pxHeight, int margin) {
		System.out.println(margin);
		if (margin >= pxWidth/2 || margin >= pxHeight/2){
			margin = 0;
		}
		List<Path> l = new ArrayList<Path>();
		int allocatedSpace=0;
		if (pxWidth<=pxHeight){
			allocatedSpace = pxWidth - (2 * margin);
		} else {
			allocatedSpace = pxHeight - (2 * margin);
		}
		System.out.println(allocatedSpace);
		int x1,x2,x3,x4;
		int y1,y2,y3,y4;
		x1 = (pxWidth-allocatedSpace)/2; 
		x2 = x1 + allocatedSpace;
		x3 = x2;
		x4 = x1;
		y1 = (pxHeight-allocatedSpace)/2; 
		y2 = y1;
		y3 = y1 + allocatedSpace; 
		y4 = y3;
		l.add(Path.createLinePath(new Point(x1, y1), new Point(x2, y2)));
		l.add(Path.createLinePath(new Point(x2, y2), new Point(x3, y3)));
		l.add(Path.createLinePath(new Point(x3, y3), new Point(x4, y4)));
		l.add(Path.createLinePath(new Point(x4, y4), new Point(x1, y1)));
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
		String s = "Square with "+this.length+" length";
		return s;
	}
}
