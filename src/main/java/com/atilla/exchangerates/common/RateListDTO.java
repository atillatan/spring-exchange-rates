package com.atilla.exchangerates.common;

import java.math.BigDecimal;
import java.util.EnumMap;
import java.util.HashMap;

public class RateListDTO {

	private String start_at;

	private String end_at;
 
	private CurrencyEnum base;

	private HashMap<String, EnumMap<CurrencyEnum, BigDecimal>> rates;

	public RateListDTO(HashMap<String, EnumMap<CurrencyEnum, BigDecimal>> rates) {
		this.rates = rates;
	}

	public RateListDTO() {
	} 

	public String getStart_at() {
		return start_at;
	}

	public void setStart_at(String start_at) {
		this.start_at = start_at;
	}

	public String getEnd_at() {
		return end_at;
	}

	public void setEnd_at(String end_at) {
		this.end_at = end_at;
	}

	public CurrencyEnum getBase() {
		return base;
	}

	public void setBase(CurrencyEnum base) {
		this.base = base;
	}

	public HashMap<String, EnumMap<CurrencyEnum, BigDecimal>> getRates() {
		return rates;
	}

	public void setRates(HashMap<String, EnumMap<CurrencyEnum, BigDecimal>> rates) {
		this.rates = rates;
	}

}
