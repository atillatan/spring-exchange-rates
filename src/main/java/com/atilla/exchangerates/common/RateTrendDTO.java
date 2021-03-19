/**
 * 
 */
package com.atilla.exchangerates.common;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author atilla
 * 
 *
 */
public class RateTrendDTO {
 
	// The REST service returns:
	// - The exchange rate of the requested date,
	// - The average of the five days before the requested date
	// (excluding Saturday and Sunday )
	// - and the exchange rate trend.
 
	@JsonProperty("exchange_rate")
	private BigDecimal exchangeRate;

	@JsonProperty("average_of_five_days")
	private BigDecimal averageOfFiveDays;

	@JsonProperty("exchange_rate_trend")
	private RateTrendEnum exchangeRateTrend;

	public RateTrendDTO(Long id, BigDecimal exchangeRate, BigDecimal averageOfFiveDays,
			RateTrendEnum exchangeRateTrend) {
		this.exchangeRate = exchangeRate;
		this.averageOfFiveDays = averageOfFiveDays;
		this.exchangeRateTrend = exchangeRateTrend;
	}

	public RateTrendDTO() {
	}

	public BigDecimal getExchangeRate() {
		return exchangeRate;
	}

	public void setExchangeRate(BigDecimal exchangeRate) {
		this.exchangeRate = exchangeRate;
	}

	public BigDecimal getAverageOfFiveDays() {
		return averageOfFiveDays;
	}

	public void setAverageOfFiveDays(BigDecimal averageOfFiveDays) {
		this.averageOfFiveDays = averageOfFiveDays;
	}

	public RateTrendEnum getExchangeRateTrend() {
		return exchangeRateTrend;
	}

	public void setExchangeRateTrend(RateTrendEnum exchangeRateTrend) {
		this.exchangeRateTrend = exchangeRateTrend;
	}

}
