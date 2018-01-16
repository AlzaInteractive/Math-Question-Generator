package com.alza.quiz.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.alza.quiz.model.geom.Triangle;
import com.alza.quiz.model.geom.Triangle.EdgeLengthRatio;

public class TriangleTest {
	
	@Test
	public void testEquilateral() {
		Triangle t = new Triangle();
		for (int i = 0; i < 100; i++) {
			Triangle tr = (Triangle) t.createExample(EdgeLengthRatio.equilateral);
			System.out.println(i);
			assertEquals(EdgeLengthRatio.equilateral, tr.getType());
		}
	}
	@Test
	public void testIscosceles() {
		Triangle t = new Triangle();
		for (int i = 0; i < 100; i++) {
			Triangle tr = (Triangle) t.createExample(EdgeLengthRatio.iscosceles);
			System.out.println(i);
			assertEquals(EdgeLengthRatio.iscosceles, tr.getType());
		}
	}

}
