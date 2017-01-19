package com.alza.quiz.test;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import com.alza.common.math.Fraction;

public class FractionTest {
	static Fraction f1;
	static Fraction f2;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		f1 = new Fraction(2, 3);
		f2 = new Fraction(1, 3);
	}

	@Test
	public void testAddition() {
		Fraction f3 = f1.getResultWhenAddedWith(f2);
		assertEquals(1, f3.a);
		assertEquals(1, f3.b);
	}
	@Test
	public void testSubtraction() {
		Fraction f3 = f1.getResultWhenSubtractWith(f2);
		assertEquals(1, f3.a);
		assertEquals(3, f3.b);
	}
	@Test
	public void testEquality() {
		assertEquals(false, f1.equals(f2));
		Fraction f4 = new Fraction(5, 10);
		Fraction f5 = new Fraction(27, 54);
		assertEquals(true, f4.equals(f5));
	}
	@Test
	public void testMultiplication() {
		Fraction f3 = f1.getResultWhenMultipliedBy(f2);
		assertEquals(2, f3.a);
		assertEquals(9, f3.b);
	}
	@Test
	public void testDivision() {
		Fraction f3 = f1.getResultWhenDividedBy(f2);
		assertEquals(2, f3.a);
		assertEquals(1, f3.b);
	}
}
