package com.alza.quiz.qfactory.geom;

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
		} while (l==h);
		do {
			s = ThreadLocalRandom.current().nextInt(5, 10);
		} while (s+3>=l);
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
}
