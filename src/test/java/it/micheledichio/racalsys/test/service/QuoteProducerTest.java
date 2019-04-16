package it.micheledichio.racalsys.test.service;

import static org.easymock.EasyMock.expect;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.TreeSet;

import org.easymock.EasyMockSupport;
import org.junit.Test;

import it.micheledichio.racalsys.model.Lender;
import it.micheledichio.racalsys.model.Quote;
import it.micheledichio.racalsys.service.LenderExtractor;
import it.micheledichio.racalsys.service.QuoteProducer;
import it.micheledichio.racalsys.util.ArgsHandler;

public class QuoteProducerTest {
	
	EasyMockSupport easyMockSupport = new EasyMockSupport();
	
	@Test
	public void aSetOfLendersEmptyDoesNotProduceAQuote() {
		ArgsHandler argsHandler = easyMockSupport.createMock(ArgsHandler.class);
		LenderExtractor lenderExtractor = easyMockSupport.createMock(LenderExtractor.class);
		
		QuoteProducer quoteProducer = new QuoteProducer(argsHandler, lenderExtractor);
		
		expect(argsHandler.getMarketFilename()).andReturn("market.csv");
		expect(lenderExtractor.extractOrderedLenders("market.csv")).andReturn(null);
		easyMockSupport.replayAll();
		
		Optional<Quote> quote = quoteProducer.produceQuote();
		assertFalse(quote.isPresent());
		
		easyMockSupport.verifyAll();
	}
	
	@Test
	public void aLoanAmountGreaterThanTheSumOfLendersFundsDoesNotProduceAQuote() {
		ArgsHandler argsHandler = easyMockSupport.createMock(ArgsHandler.class);
		LenderExtractor lenderExtractor = easyMockSupport.createMock(LenderExtractor.class);
		
		QuoteProducer quoteProducer = new QuoteProducer(argsHandler, lenderExtractor);
		
		TreeSet<Lender> lenders = new TreeSet<Lender>();
		lenders.add(new Lender("Dave", new BigDecimal("0.074"), new BigDecimal("140")));
		lenders.add(new Lender("Jane", new BigDecimal("0.069"), new BigDecimal("480")));
		
		expect(argsHandler.getMarketFilename()).andReturn("market.csv");
		expect(lenderExtractor.extractOrderedLenders("market.csv")).andReturn(lenders);
		expect(argsHandler.getLoanAmount()).andReturn("1500");
		easyMockSupport.replayAll();
		
		Optional<Quote> quote = quoteProducer.produceQuote();
		assertFalse(quote.isPresent());
		
		easyMockSupport.verifyAll();
	}
	
	@Test
	public void theLowestAccettableAmountProduceARightQuote() {
		ArgsHandler argsHandler = easyMockSupport.createMock(ArgsHandler.class);
		LenderExtractor lenderExtractor = easyMockSupport.createMock(LenderExtractor.class);
		
		QuoteProducer quoteProducer = new QuoteProducer(argsHandler, lenderExtractor);
		
		TreeSet<Lender> lenders = new TreeSet<Lender>();
		lenders.add(new Lender("Fred", new BigDecimal("0.071"), new BigDecimal("520")));
		lenders.add(new Lender("Jane", new BigDecimal("0.069"), new BigDecimal("480")));
		
		expect(argsHandler.getMarketFilename()).andReturn("market.csv");
		expect(lenderExtractor.extractOrderedLenders("market.csv")).andReturn(lenders);
		expect(argsHandler.getLoanAmount()).andReturn("1000");
		easyMockSupport.replayAll();
		
		Quote expectedQuote = new Quote(new BigDecimal("1000"), new BigDecimal("7.0"), new BigDecimal("30.78"), new BigDecimal("1108.10"));
		
		Optional<Quote> quote = quoteProducer.produceQuote();
		assertTrue(quote.isPresent());
		assertEquals(expectedQuote, quote.get());
		
		easyMockSupport.verifyAll();
	}
	
	@Test
	public void theHighestAccettableAmountProduceARightQuote() {
		ArgsHandler argsHandler = easyMockSupport.createMock(ArgsHandler.class);
		LenderExtractor lenderExtractor = easyMockSupport.createMock(LenderExtractor.class);
		
		QuoteProducer quoteProducer = new QuoteProducer(argsHandler, lenderExtractor);
		
		TreeSet<Lender> lenders = new TreeSet<Lender>();
		lenders.add(new Lender("Fred", new BigDecimal("0.071"), new BigDecimal("520")));
		lenders.add(new Lender("Jane", new BigDecimal("0.069"), new BigDecimal("480")));
		lenders.add(new Lender("Jane", new BigDecimal("0.075"), new BigDecimal("640")));
		
		expect(argsHandler.getMarketFilename()).andReturn("market.csv");
		expect(lenderExtractor.extractOrderedLenders("market.csv")).andReturn(lenders);
		expect(argsHandler.getLoanAmount()).andReturn("1600");
		easyMockSupport.replayAll();
		
		Quote expectedQuote = new Quote(new BigDecimal("1600"), new BigDecimal("7.2"), new BigDecimal("49.38"), new BigDecimal("1777.56"));
		
		Optional<Quote> quote = quoteProducer.produceQuote();
		assertTrue(quote.isPresent());
		assertEquals(expectedQuote, quote.get());
		
		easyMockSupport.verifyAll();
	}

}
