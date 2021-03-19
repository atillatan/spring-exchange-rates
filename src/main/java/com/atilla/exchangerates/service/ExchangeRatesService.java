/**
 * 
 */
package com.atilla.exchangerates.service;

import com.atilla.exchangerates.common.CurrencyEnum;
import com.atilla.exchangerates.common.RateTrendDTO;

/**
 * @author atilla
 *
 */
public interface ExchangeRatesService {

	RateTrendDTO exchangeRate(String date, CurrencyEnum baseCurrency, CurrencyEnum targetCurrency);

}
 