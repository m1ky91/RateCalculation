package it.micheledichio.racalsys;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.micheledichio.racalsys.model.Quote;
import it.micheledichio.racalsys.service.QuoteProducer;
import it.micheledichio.racalsys.util.ArgsHandler;

public class App {

	public static void main(String[] args) {
		Logger logger = LoggerFactory.getLogger(App.class);
		
		StringBuilder argsInLine = new StringBuilder();
		for (String arg : args) 
			argsInLine.append(arg + ",");
		
		if (argsInLine != null && argsInLine.length() > 0)
			logger.debug("Application started with args: " + argsInLine.substring(0, argsInLine.length() - 1));
		else
			logger.debug("Application started without args");
		
		ArgsHandler argsHandler = new ArgsHandler(args);
		
		if (argsHandler.argsValid()) {
			logger.debug("Args valid. The system is ready to produce a quote.");
			QuoteProducer quoteProducer = new QuoteProducer(argsHandler);
			Optional<Quote> quote = quoteProducer.produceQuote();
			
			// If am using Java 8, then I can't use ifPresentOrElse() method introduced in Java 9
			if (quote.isPresent()) {
				logger.info("Requested amount: £" + quote.get().getRequestedAmount());
				logger.info("Rate: " + quote.get().getRate() + "%");
				logger.info("Monthly repayment: £" + quote.get().getMonthlyRepayment());
				logger.info("Total repayment: £" + quote.get().getTotalRepayment());
			} else {
				logger.info("Sorry, it is not possible to provide a quote at this time.");
			}
		} else {
			logger.debug("Invalid args.");
		}
	}

}
