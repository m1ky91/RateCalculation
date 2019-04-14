package it.micheledichio.racalsys.model;

import java.math.BigDecimal;

public class Quote {
	
	private BigDecimal requestedAmount;
	private BigDecimal rate;
	private BigDecimal monthlyRepayment;
	private BigDecimal totalRepayment;

	public Quote() {}

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
	
}
