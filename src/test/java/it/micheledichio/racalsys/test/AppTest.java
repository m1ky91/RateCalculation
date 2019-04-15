package it.micheledichio.racalsys.test;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.easymock.EasyMock;
import org.junit.Test;

import it.micheledichio.racalsys.model.Quote;
import it.micheledichio.racalsys.service.QuoteProducer;
import it.micheledichio.racalsys.util.ArgsHandler;

public class AppTest {

	@Test
	public void argsHandledCorrectly() {
		String[] args = {"market.csv", "1000"};
		
		ArgsHandler argsHandler = new ArgsHandler(args);
		
		assertTrue(argsHandler.argsValid());
	}
	
	@Test
	public void quoteProducerProducesAnOptional() {
		ArgsHandler argsHandler = EasyMock.createMock(ArgsHandler.class);
		
		expect(argsHandler.getMarketFilename()).andReturn("market.csv");
		replay(argsHandler);
		
		QuoteProducer quoteProducer = new QuoteProducer(argsHandler);
		Optional<Quote> quote = quoteProducer.produceQuote();
		
		assertTrue(quote != null);
		
		verify(argsHandler);
	}

}
