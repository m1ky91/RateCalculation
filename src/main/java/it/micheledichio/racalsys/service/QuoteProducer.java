package it.micheledichio.racalsys.service;

import java.util.Optional;
import java.util.Set;

import it.micheledichio.racalsys.model.Lender;
import it.micheledichio.racalsys.model.Quote;
import it.micheledichio.racalsys.util.ArgsHandler;

public class QuoteProducer {
	
	private ArgsHandler argsHandler;
	private LenderExtractor lenderExtractor;

	public QuoteProducer(ArgsHandler argsHandler) {
		this.argsHandler = argsHandler;
		this.lenderExtractor = new LenderExtractor();
	}

	public QuoteProducer(ArgsHandler argsHandler, LenderExtractor lenderExtractor) {
		this.argsHandler = argsHandler;
		this.lenderExtractor = lenderExtractor;
	}

	public Optional<Quote> produceQuote() {
		Set<Lender> orderedLenders = lenderExtractor.extractOrderedLenders(argsHandler.getMarketFilename());
		Quote quote = null;
		if (orderedLenders == null) {
			return Optional.ofNullable(null);
		} else {
			return Optional.ofNullable(quote);
		}
	}

}
