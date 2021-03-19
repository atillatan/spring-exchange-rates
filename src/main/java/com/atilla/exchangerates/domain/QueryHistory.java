/**
 * 
 */
package com.atilla.exchangerates.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Requirement:
 * 
 * All successful queries should be persisted in the DB. The customer can get
 * historical information using two API’s, one for the daily information and
 * other for the monthly information. ◦ daily:
 * /api/exchange-rate/history/daily/{yyyy}/{MM}/{dd} ◦ monthly:
 * /api/exchange-rate/history/monthly/{yyyy}/{MM}
 * 
 * @author atilla
 *
 */

@Entity
@Table(name = "QUERY_HISTORY")
public class QueryHistory {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "QUERY_HISTORY_seq_gen")
	@SequenceGenerator(name = "QUERY_HISTORYV_seq_gen", sequenceName = "QUERY_HISTORY_id_seq")
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;

	private String date;
	private String baseCurrency;
	private String targetCurrency;
	private LocalDateTime createDate;
	private BigDecimal exchangeRate;

	public QueryHistory() {
	}

	public QueryHistory(Long id, String date, String baseCurrency, String targetCurrency, LocalDateTime createDate, BigDecimal exchangeRate) {
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
		return "QueryHistory [id=" + id + ", date=" + date + ", baseCurrency=" + baseCurrency + ", targetCurrency="
				+ targetCurrency + ", createDate=" + createDate + ", exchangeRate=" + exchangeRate + "]";
	}

 
	

}
