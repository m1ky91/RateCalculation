package it.micheledichio.racalsys.model;

import java.math.BigDecimal;

public class Lender implements Comparable<Lender> {
	
	private String name;
	private BigDecimal rate;
	private BigDecimal available;
	
	public Lender() {}

	public Lender(String name, BigDecimal rate, BigDecimal available) {
		this.name = name;
		this.rate = rate;
		this.available = available;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getRate() {
		return rate;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}

	public BigDecimal getAvailable() {
		return available;
	}

	public void setAvailable(BigDecimal available) {
		this.available = available;
	}

	@Override
	public int compareTo(Lender other) {
		return this.rate.compareTo(other.getRate());
	}

}
