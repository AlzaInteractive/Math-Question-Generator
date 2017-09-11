package com.alza.quiz.qfactory.fraction;

import java.util.ArrayList;
import java.util.List;

import com.alza.common.math.Fraction;
import com.alza.quiz.qfactory.geom.model.Path;
import com.alza.quiz.qfactory.geom.model.Point2D;

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
		int numerator = fraction.a;
		int divisor = fraction.b;
		double angleSweep = 360 / divisor;
		double radius = 1;
		Point2D center = new Point2D(radius,radius);
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
