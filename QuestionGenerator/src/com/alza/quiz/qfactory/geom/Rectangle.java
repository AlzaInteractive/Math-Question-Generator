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
	@Override
	public double getOccupiedLength() {
		return this.length;
	}
	@Override
	public double getOccupiedHeight() {
		return this.width;
	}
	public List<Path> getPaths(int pxWidth, int pxHeight, int margin) {
		System.out.println("margin "+margin);
		if (margin >= pxWidth/2 || margin >= pxHeight/2){
			margin = 0;
		}
		List<Path> l = new ArrayList<Path>();
		int scale=0;
		double maxHeightRatio = ((double) (pxHeight-margin*2)) / ((double) getOccupiedHeight());
		double maxLengthRatio = ((double) (pxWidth-margin*2)) / ((double) getOccupiedLength());
		if (maxHeightRatio>maxLengthRatio){
			scale = (int) maxLengthRatio;
		} else {
			scale = (int) maxHeightRatio;
		}
		System.out.println("scale  "+scale);
		int pxL = (int) (length * scale);
		int pxH  = (int) (width * scale);
		System.out.println("Mapped lw:  "+pxL+","+pxH);
		int x1,x2,x3,x4;
		int y1,y2,y3,y4;
		x1 = (pxWidth-pxL)/2; 
		x2 = x1 + pxL;
		x3 = x2;
		x4 = x1;
		y1 = (pxHeight - pxH) / 2; 
		y2 = y1;
		y3 = y1 + pxH;
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
		String s = "Rectangle with "+this.length+" length, "+this.width+" width";
		return s;
	}
}
