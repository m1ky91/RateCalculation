package it.micheledichio.racalsys;

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
		
		QuoteProducer quoteProducer = new QuoteProducer(argsHandler);
		Optional<Quote> quote = quoteProducer.produceQuote();
		
		assertTrue(quote != null);
	}
	
	@Test
	public void ifOptionalPresentQuoteAttributesAreNotNull() {
		ArgsHandler argsHandler = EasyMock.createMock(ArgsHandler.class);
		
		QuoteProducer quoteProducer = new QuoteProducer(argsHandler);
		Optional<Quote> quote = quoteProducer.produceQuote();
		
		assertTrue(quote.isPresent());
		assertTrue(quote.get().getRequestedAmount() != null);
		assertTrue(quote.get().getRate() != null);
		assertTrue(quote.get().getMonthlyRepayment() != null);
		assertTrue(quote.get().getTotalRepayment() != null);
	}

}
