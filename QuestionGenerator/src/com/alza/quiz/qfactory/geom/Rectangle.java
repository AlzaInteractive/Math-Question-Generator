package com.alza.quiz.qfactory.geom;

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
	public String getLocalName(String lang) {
		switch (lang) {
		case "EN":
			return "rectangle";
		case "ID":
			return "persegi panjang";
		default:
			break;
		}
		return null;
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
	
	

}
