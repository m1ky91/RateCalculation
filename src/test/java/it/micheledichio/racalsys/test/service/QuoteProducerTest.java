package it.micheledichio.racalsys.test.service;

import static org.easymock.EasyMock.expect;
import static org.junit.Assert.assertFalse;

import java.util.Optional;

import org.easymock.EasyMockSupport;
import org.junit.Test;

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

}
