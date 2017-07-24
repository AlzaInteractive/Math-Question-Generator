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
	public String getLocalName(String lang) {
		switch (lang) {
		case "EN":
			return "parallelogram";
		case "ID":
			return "persegi panjang";
		default:
			break;
		}
		return null;
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
	public double getOccupiedLength() {
		return length + shear;
	}
	@Override
	public double getOccupiedHeight() {
		return height;
	}
	public List<Path> getPaths(int pxWidth, int pxHeight, int margin) {
		if (margin >= pxWidth/2 || margin >= pxHeight/2){
			margin = 0;
		}
		System.out.println("margin "+margin);
		List<Path> l = new ArrayList<Path>();
		int scale=0;
		double maxHeightRatio = ((double) (pxHeight-margin*2)) / ((double) height);
		double maxLengthRatio = ((double) (pxWidth-margin*2)) / ((double) (length + shear));
		if (maxHeightRatio>maxLengthRatio){
			scale = (int) maxLengthRatio;
		} else {
			scale = (int) maxHeightRatio;
		}
		int pxL = (int) (length * scale);
		int pxH  = (int) (height * scale);
		int pxSh = (int) (shear * scale);
		System.out.println("Mapped LHS:  "+pxL+","+pxH+","+pxSh);
		int x1,x2,x3,x4;
		int y1,y2,y3,y4;
		x1 = ((pxWidth-pxL)/2)+pxSh; 
		x2 = x1 + pxL;
		x3 = x2-pxSh;
		x4 = x3-pxL;
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
		String s = "Parallelogram with "+this.length+" length, "+this.height+
				" height "+this.shear+" shear";
		return s;
	}
}
