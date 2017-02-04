package com.alza.quiz.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.alza.common.math.MathUtils;

public class SimpleMathTester {

	@Test
	public void test() {
		int gcd = MathUtils.findGCD(4,8,12,24,6);
		assertEquals(2, gcd);
	}

}
