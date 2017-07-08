package com.alza.quiz.qfactory.geom;

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
	
	

}
