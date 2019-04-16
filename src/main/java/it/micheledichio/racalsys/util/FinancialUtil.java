package it.micheledichio.racalsys.util;

public class FinancialUtil {
	
	private final int TOTALMONTHSLOAN = 36;
	private final int PAYMENTSPERYEAR = 12;

	public Double getMonthlyRepayment(Double loanAmount, Double annualRate) {
		Double doubleEffectiveRate = getEffectiveRate(annualRate);
		return (doubleEffectiveRate * loanAmount) /
				(1 - Math.pow(1 + doubleEffectiveRate, -TOTALMONTHSLOAN));
	}

	public Double getTotalRepayment(Double monthlyRepayment) {
		return monthlyRepayment * TOTALMONTHSLOAN;
	}

	public Double getLoanRate(Double totalRepayment, Double loanAmount) {
		Double doubleInterest = getInterest(totalRepayment, loanAmount);
		return (2 * PAYMENTSPERYEAR * doubleInterest) / (loanAmount * (TOTALMONTHSLOAN + 1));
	}
	
	private Double getEffectiveRate(Double annualRate) {
		return Math.pow(1 + annualRate, 1d/12d) - 1;
	}
	
	private Double getInterest(Double totalRepayment, Double loanAmount) {
		return totalRepayment - loanAmount;
	}
}
