package it.micheledichio.racalsys.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;
import java.util.TreeSet;

import it.micheledichio.racalsys.model.Lender;
import it.micheledichio.racalsys.model.Quote;
import it.micheledichio.racalsys.util.ArgsHandler;
import it.micheledichio.racalsys.util.FinancialUtil;

public class QuoteProducer {
	
	private FinancialUtil financialUtil = new FinancialUtil();
	private BigDecimal lendersFunds = BigDecimal.ZERO;
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
		TreeSet<Lender> orderedLenders = lenderExtractor.extractOrderedLenders(argsHandler.getMarketFilename());
		Quote quote = null;
		
		if (orderedLenders == null) {
			return Optional.ofNullable(null);
		} else {
			quote = defineQuote(orderedLenders, new BigDecimal(argsHandler.getLoanAmount()));
			return Optional.ofNullable(quote);
		}
	}
	
	private Quote defineQuote(TreeSet<Lender> orderedLenders, BigDecimal loanAmount) {
		Quote finalQuote = new Quote(loanAmount, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO);		
		orderedLenders.stream().forEach(l -> lendersFunds = lendersFunds.add(l.getAvailable()));
		BigDecimal amount = loanAmount;
		
		if (lendersFunds.compareTo(loanAmount) < 0) {
			return null;
		} else {
			Double monthlyRepayment = 0d;
			Double totalRepayment = 0d;
			
			while (loanAmount.compareTo(BigDecimal.ZERO) > 0) {
				Lender lender = orderedLenders.first();
				BigDecimal providedSum = BigDecimal.ZERO;
				if (loanAmount.compareTo(lender.getAvailable()) >= 0)
					providedSum = lender.getAvailable();
				else
					providedSum = lender.getAvailable().subtract((lender.getAvailable().subtract(loanAmount)));
				loanAmount = loanAmount.subtract(providedSum);
				Double lenderMonthlyRepayment = financialUtil.getMonthlyRepayment(providedSum.doubleValue(), lender.getRate().doubleValue());
				monthlyRepayment += lenderMonthlyRepayment;
				Double lenderTotalRepayment = financialUtil.getTotalRepayment(lenderMonthlyRepayment);
				totalRepayment += lenderTotalRepayment; 
				orderedLenders.remove(lender);
			}
			
			Double loanRate = financialUtil.getLoanRate(totalRepayment, amount.doubleValue());
			finalQuote.setRequestedAmount(amount);
			finalQuote.setMonthlyRepayment(new BigDecimal(monthlyRepayment).setScale(2, RoundingMode.HALF_EVEN));
			finalQuote.setTotalRepayment(new BigDecimal(totalRepayment).setScale(2, RoundingMode.HALF_EVEN));
			finalQuote.setRate(new BigDecimal(loanRate * 100).setScale(1, RoundingMode.HALF_EVEN));
			return finalQuote;
		}	
	}

}
