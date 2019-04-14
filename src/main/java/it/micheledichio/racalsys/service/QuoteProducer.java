package it.micheledichio.racalsys.service;

import java.math.BigDecimal;
import java.util.Optional;

import it.micheledichio.racalsys.model.Quote;
import it.micheledichio.racalsys.util.ArgsHandler;

public class QuoteProducer {
	
	private ArgsHandler argsHandler;

	public QuoteProducer(ArgsHandler argsHandler) {
		this.argsHandler = argsHandler;
	}

	public Optional<Quote> produceQuote() {
		Quote quote = new Quote(BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO);
		return Optional.ofNullable(quote);
	}

}
