package com.alza.quiz.qfactory.geom;

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
	public String getLocalName(String lang) {
		switch (lang) {
		case "EN":
			return "circle";
		case "ID":
			return "lingkaran";
		default:
			break;
		}
		return null;
	}
	
	

}
