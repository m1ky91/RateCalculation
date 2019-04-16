package it.micheledichio.racalsys.model;

import java.math.BigDecimal;

public class Lender implements Comparable<Lender> {
	
	private String name;
	private BigDecimal rate;
	private BigDecimal available;

	public Lender(String name, BigDecimal rate, BigDecimal available) {
		this.name = name;
		this.rate = rate;
		this.available = available;
	}

	public String getName() {
		return name;
	}

	public BigDecimal getRate() {
		return rate;
	}

	public BigDecimal getAvailable() {
		return available;
	}

	@Override
	public int compareTo(Lender other) {
		if (this.rate.compareTo(other.getRate()) != 0) 
			return this.rate.compareTo(other.getRate());
		else
			return this.available.compareTo(other.getAvailable());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((available == null) ? 0 : available.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((rate == null) ? 0 : rate.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		Lender other = (Lender) obj;
		return this.name.equals(other.getName()) && this.rate.equals(other.getRate()) && this.available.equals(other.getAvailable());
	}

}
