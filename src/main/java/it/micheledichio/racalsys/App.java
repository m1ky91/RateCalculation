package it.micheledichio.racalsys;

import java.util.Optional;

import it.micheledichio.racalsys.model.Quote;
import it.micheledichio.racalsys.service.QuoteProducer;
import it.micheledichio.racalsys.util.ArgsHandler;

public class App {

	public static void main(String[] args) {
		ArgsHandler argsHandler = new ArgsHandler(args);
		
		if (argsHandler.argsValid()) {
			QuoteProducer quoteProducer = new QuoteProducer(argsHandler);
			Optional<Quote> quote = quoteProducer.produceQuote();
			
			// If am using Java 8, then I can't use ifPresentOrElse() method introduced in Java 9
			if (quote.isPresent()) {
				System.out.println("Requested amount: £" + quote.get().getRequestedAmount());
				System.out.println("Rate: " + quote.get().getRate() + "%");
				System.out.println("Monthly repayment: £" + quote.get().getMonthlyRepayment());
				System.out.println("Total repayment: £" + quote.get().getTotalRepayment());
			} else {
				System.out.println("Sorry, it is not possible to provide a quote at this time.");
			}
		}			
	}

}
