package com.atilla.exchangerates.common;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class QueryHistoryDTO {

	@JsonIgnore
	private Long id;

	private String date;
	@JsonProperty("base_currency")
	private String baseCurrency;
	@JsonProperty("target_currency")
	private String targetCurrency;
	@JsonProperty("query_date")
	private LocalDateTime createDate;
	@JsonProperty("exchange_rate")
	private BigDecimal exchangeRate;

	public QueryHistoryDTO() {
	}

	public QueryHistoryDTO(Long id, String date, String baseCurrency, String targetCurrency, LocalDateTime createDate,
			BigDecimal exchangeRate) {
		super();
		this.id = id;
		this.date = date;
		this.baseCurrency = baseCurrency;
		this.targetCurrency = targetCurrency;
		this.createDate = createDate;
		this.exchangeRate = exchangeRate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getBaseCurrency() {
		return baseCurrency;
	}

	public void setBaseCurrency(String baseCurrency) {
		this.baseCurrency = baseCurrency;
	}

	public String getTargetCurrency() {
		return targetCurrency;
	}

	public void setTargetCurrency(String targetCurrency) {
		this.targetCurrency = targetCurrency;
	}

	public LocalDateTime getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}

	public BigDecimal getExchangeRate() {
		return exchangeRate;
	}

	public void setExchangeRate(BigDecimal exchangeRate) {
		this.exchangeRate = exchangeRate;
	}

	@Override
	public String toString() {
		return "QueryHistorySTO [date=" + date + ", baseCurrency=" + baseCurrency + ", targetCurrency=" + targetCurrency
				+ ", createDate=" + createDate + ", exchangeRate=" + exchangeRate + "]";
	}

}
