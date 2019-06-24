package com.alza.quiz.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.alza.common.math.MathUtils;

public class RomanNumeralTest {
	@Test
	public void testToRomans(){
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
	@Test
	public void testFromRomans(){
		
		try {
			assertEquals("",2, MathUtils.fromRoman("II"));
			assertEquals("",4, MathUtils.fromRoman("IV"));
			assertEquals("",5, MathUtils.fromRoman("V"));
			assertEquals("",9, MathUtils.fromRoman("IX"));
			assertEquals("",53, MathUtils.fromRoman("LIII"));
			assertEquals("",149, MathUtils.fromRoman("CXLIX"));
			assertEquals("",139, MathUtils.fromRoman("CXXXIX"));
			assertEquals("",1496, MathUtils.fromRoman("MCDXCVI"));
			assertEquals("",3999, MathUtils.fromRoman("MMMCMXCIX"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
