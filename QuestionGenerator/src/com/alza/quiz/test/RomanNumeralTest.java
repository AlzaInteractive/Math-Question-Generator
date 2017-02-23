package com.alza.quiz.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.alza.common.math.MathUtils;

public class RomanNumeralTest {
	@Test
	public void testRomans(){
		assertEquals("","II", MathUtils.toRomanNumeral(2));
		assertEquals("","IV", MathUtils.toRomanNumeral(4));
		assertEquals("","V", MathUtils.toRomanNumeral(5));
		assertEquals("","IX", MathUtils.toRomanNumeral(9));
		assertEquals("","XLIII", MathUtils.toRomanNumeral(43));
		assertEquals("","XCIX", MathUtils.toRomanNumeral(99));
		assertEquals("","IX", MathUtils.toRomanNumeral(9));
		assertEquals("","LIII", MathUtils.toRomanNumeral(53));
		assertEquals("","CXXIII", MathUtils.toRomanNumeral(123));
		assertEquals("","CXLIX", MathUtils.toRomanNumeral(149));
		assertEquals("","CXXXIX", MathUtils.toRomanNumeral(139));
		assertEquals("","MCDXCVI", MathUtils.toRomanNumeral(1496));
	}
}
