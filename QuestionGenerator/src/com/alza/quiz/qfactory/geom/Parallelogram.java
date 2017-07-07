package com.alza.quiz.qfactory.geom;

public class Parallelogram implements Shapes2D{
	private double length;
	private double width;
	private double height;
	private double shear;
	
	public Parallelogram(){
		
	}
	public Parallelogram(double length, double width, double height, double shear){
		this.length = length;
		this.width = width;
		this.height = height;
		this.shear = shear;
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
	@Override
	public double getArea() {
		return height * width;
	}

	@Override
	public double getPerimeter() {
		return 2 * (length + width);
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
			return "rectangle";
		case "ID":
			return "persegi panjang";
		default:
			break;
		}
		return null;
	}
	
	

}
