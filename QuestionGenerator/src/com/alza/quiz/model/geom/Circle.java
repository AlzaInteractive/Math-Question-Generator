package com.alza.quiz.model.geom;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Circle implements Shapes2D{
	private double radius;
	private boolean showRadiusLine = true;
	private boolean showCenterLabel = true;
	private boolean showRadiusLength = true;
	private boolean showDiameterLine = true;
	private boolean showDiameterLength = true;
	
	
	public Circle(){
		
	}
	public Circle(double radius){
		this.radius = radius;
	}
	@Override
	public void hideTextsAndMeasurements() {
		showRadiusLine = false;
		showCenterLabel = false;
		showRadiusLength = false;
		showDiameterLength = false;
		showDiameterLine = false;
	}
	
	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}
	
	public double getDiameter() {
		return radius * 2;
	}
	
	public boolean isShowRadiusLine() {
		return showRadiusLine;
	}
	public void setShowRadiusLine(boolean showRadiusLine) {
		this.showRadiusLine = showRadiusLine;
	}
	public boolean isShowCenterLabel() {
		return showCenterLabel;
	}
	public void setShowCenterLabel(boolean showCenterLabel) {
		this.showCenterLabel = showCenterLabel;
	}
	public boolean isShowRadiusLength() {
		return showRadiusLength;
	}
	public void setShowRadiusLength(boolean showRadiusLength) {
		this.showRadiusLength = showRadiusLength;
	}
	public boolean isShowDiameterLine() {
		return showDiameterLine;
	}
	public void setShowDiameterLine(boolean showDiameterLine) {
		this.showDiameterLine = showDiameterLine;
	}
	public boolean isShowDiameterLength() {
		return showDiameterLength;
	}
	public void setShowDiameterLength(boolean showDiameterLength) {
		this.showDiameterLength = showDiameterLength;
	}
	@Override
	public double getArea() {
		return Geom.PI * radius * radius;
	}

	@Override
	public double getPerimeter() {
		return Geom.PI * radius * 2;
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
		int r = ThreadLocalRandom.current().nextInt(5, 10);
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
		if (showCenterLabel) {
			//System.out.println("show label");
			l.add(Path.createTextPath("O", new Point2D(radius, radius),
					Path.SHIFT_UP,Path.SHIFT_NONE));
		}
		if (showRadiusLine) {
			//System.out.println("show radius line");
			l.add(Path.createLinePathDashed(
					new Point2D(radius, radius), 
					new Point2D(2*radius, radius)));
		}
		if (showRadiusLength) {
			//System.out.println("show radius length");
			l.add(Path.createTextPath(Geom.formatMeasurement(radius), 
					new Point2D(radius + (radius/2), radius),
						Path.SHIFT_UP,Path.SHIFT_NONE));
		}
		if (showDiameterLine) {
			//System.out.println("show radius line");
			l.add(Path.createLinePathDashed(
					new Point2D(0, radius), 
					new Point2D(2*radius, radius)));
		}
		if (showDiameterLength) {
			//System.out.println("show radius length");
			l.add(Path.createTextPath(Geom.formatMeasurement(getDiameter()), 
					new Point2D(radius, radius),
						Path.SHIFT_UP,Path.SHIFT_NONE));
		}
		
		
		return l;
	}
	
	
	
}
