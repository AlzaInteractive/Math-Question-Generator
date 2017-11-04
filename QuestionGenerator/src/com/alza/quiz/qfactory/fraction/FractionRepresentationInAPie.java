package com.alza.quiz.qfactory.fraction;

import java.util.ArrayList;
import java.util.List;

import com.alza.common.math.Fraction;
import com.alza.quiz.model.geom.Path;
import com.alza.quiz.model.geom.Point2D;

public class FractionRepresentationInAPie {
	private Fraction fraction;

	public FractionRepresentationInAPie(Fraction fraction) {
		super();
		this.fraction = fraction;
	}
	public FractionRepresentationInAPie(int numerator, int divisor) {
		super();
		this.fraction = new Fraction(numerator, divisor);
	}
	
	public List<Path> getPaths(){
		List<Path> paths = new ArrayList<Path>();
		int numerator = fraction.a;
		int divisor = fraction.b;
		double radius = 10;
		double margin = radius / 5;
		double xCenter,yCenter;
		Point2D center;
		if (numerator <= divisor) {
			xCenter = radius;
			yCenter = radius;
			center = new Point2D(xCenter,yCenter);
			paths = constructSinglePieElement(numerator, divisor,
					radius, center);
		} else {
			int wholePie = fraction.getMixedFraction().x;
			int numLeft = fraction.getMixedFraction().a;
			for (int i = 0; i < wholePie + 1; i++) {
				xCenter = radius + (i * margin ) + (i * 2 * radius);
				yCenter = radius;
				center = new Point2D(xCenter,yCenter);
				if (i<wholePie) {
					//construct sliced whole pie
					paths.addAll(constructSinglePieElement(divisor, divisor,
							radius, center));
				} else {
					//construct fractioned pie
					paths.addAll(constructSinglePieElement(numLeft, divisor,
							radius, center));
				}
			}
		}
		return paths;
	}
	private List<Path> constructSinglePieElement(int numerator, int divisor,
			double radius, Point2D center) {
		double angleSweep = 360 / divisor;			
		double angleStart = 0;
		List<Path> paths = new ArrayList<Path>();
		for (int i = 0; i < divisor; i++) {
			Path p; 
			if (i<numerator) {
				p = Path.createArcPathFilled(center, radius, radius, angleStart, angleSweep);
			} else {
				p = Path.createArcPath(center, radius, radius, angleStart, angleSweep);
			}
			p.arcUseCenter=true;
			paths.add(p);
			angleStart+=angleSweep;
		}
		return paths;
	}
}
