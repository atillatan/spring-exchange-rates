package com.atilla.exchangerates.common;

import java.math.BigDecimal;
import java.util.EnumMap;
import java.util.Map;

/**
 * @author Atilla Tanrikulu
 * 
 * 
 */

public class RateDTO {
  
	private CurrencyEnum baseCurrency;
	private EnumMap<CurrencyEnum, BigDecimal> rates;
	private String date; 

	public RateDTO() {
	}

	public RateDTO(Long id, CurrencyEnum baseCurrency, String date, EnumMap<CurrencyEnum, BigDecimal> rates) {
		super();
		this.baseCurrency = baseCurrency;
		this.date = date;
		this.rates = rates;
	}

	public CurrencyEnum getBaseCurrency() {
		return baseCurrency;
	}

	public void setBaseCurrency(CurrencyEnum baseCurrency) {
		this.baseCurrency = baseCurrency;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Map<CurrencyEnum, BigDecimal> getRates() {
		return rates;
	}

	public void setRates(EnumMap<CurrencyEnum, BigDecimal> rates) {
		this.rates = rates;
	}

	@Override
	public String toString() {
		return "RateDTO [baseCurrency=" + baseCurrency + ", rates=" + rates + ", date=" + date + "]";
	}

}
