package it.micheledichio.racalsys.model;

import java.math.BigDecimal;

public class Quote {
	
	private BigDecimal requestedAmount;
	private BigDecimal rate;
	private BigDecimal monthlyRepayment;
	private BigDecimal totalRepayment;

	public Quote(BigDecimal requestedAmount, BigDecimal rate, BigDecimal monthlyRepayment, BigDecimal totalRepayment) {
		this.requestedAmount = requestedAmount;
		this.rate = rate;
		this.monthlyRepayment = monthlyRepayment;
		this.totalRepayment = totalRepayment;
	}

	public BigDecimal getRequestedAmount() {
		return requestedAmount;
	}

	public void setRequestedAmount(BigDecimal requestedAmount) {
		this.requestedAmount = requestedAmount;
	}

	public BigDecimal getRate() {
		return rate;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}

	public BigDecimal getMonthlyRepayment() {
		return monthlyRepayment;
	}

	public void setMonthlyRepayment(BigDecimal monthlyRepayment) {
		this.monthlyRepayment = monthlyRepayment;
	}

	public BigDecimal getTotalRepayment() {
		return totalRepayment;
	}

	public void setTotalRepayment(BigDecimal totalRepayment) {
		this.totalRepayment = totalRepayment;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((monthlyRepayment == null) ? 0 : monthlyRepayment.hashCode());
		result = prime * result + ((rate == null) ? 0 : rate.hashCode());
		result = prime * result + ((requestedAmount == null) ? 0 : requestedAmount.hashCode());
		result = prime * result + ((totalRepayment == null) ? 0 : totalRepayment.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		Quote other = (Quote) obj;
		return this.requestedAmount.equals(other.getRequestedAmount()) && this.rate.equals(other.getRate())
				&& this.monthlyRepayment.equals(other.getMonthlyRepayment())
				&& this.totalRepayment.equals(other.getTotalRepayment());
	}
	
}
