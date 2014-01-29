package com.jonwelzel.ejb.persistence;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Locale;

import org.junit.Test;

public class RoundValuesTest {

	@Test
	public void test() {
		BigDecimal shouldBe = new BigDecimal(0.25);
		BigDecimal number = new BigDecimal(400);
		Integer dividend = 1600;
		MathContext mathContext = new MathContext(10, RoundingMode.HALF_UP);
		//		BigDecimal result = number.divide(new BigDecimal(dividend));
		BigDecimal result = new BigDecimal(1325.3126);
		System.out.println(result.round(mathContext));
		System.out.println(NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(result));
	}
}
