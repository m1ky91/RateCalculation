package it.micheledichio.racalsys.test.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import org.junit.Test;

import it.micheledichio.racalsys.model.Lender;
import it.micheledichio.racalsys.model.Quote;

public class ModelTest {
	
	@Test
	public void testRelationBetweenEqualsAndHashCodeForLender() {
		Lender lenderOne = new Lender("Dave", new BigDecimal("0.074"), new BigDecimal("140"));
		Lender lenderTwo = new Lender("Dave", new BigDecimal("0.074"), new BigDecimal("140"));
		
		assertTrue(lenderOne.equals(lenderTwo));
		assertEquals(lenderOne.hashCode(), lenderTwo.hashCode());
	}
	
	@Test
	public void testRelationBetweenEqualsAndHashCodeForQuote() {
		Quote quoteOne = new Quote(new BigDecimal("1000"), new BigDecimal("7.0"), new BigDecimal("30.78"), new BigDecimal("1108.10"));
		Quote quoteTwo = new Quote(new BigDecimal("1000"), new BigDecimal("7.0"), new BigDecimal("30.78"), new BigDecimal("1108.10"));
		
		assertTrue(quoteOne.equals(quoteTwo));
		assertEquals(quoteOne.hashCode(), quoteTwo.hashCode());
	}

}
