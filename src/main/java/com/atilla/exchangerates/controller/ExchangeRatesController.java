/**
 * 
 */
package com.atilla.exchangerates.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atilla.exchangerates.common.AppConstants;
import com.atilla.exchangerates.common.CurrencyEnum;
import com.atilla.exchangerates.common.RateTrendDTO;
import com.atilla.exchangerates.service.ExchangeRatesService;

/**
 * Requirements and Basic Features:
 * 
 * Allow users to query the exchange rate using two currencies and a date using
 * the API. Only dates between 2000-01-01 and yesterday are allowed. The API of
 * https://exchangeratesapi.io/ should be used as data source to implement our
 * REST service. Only the currencies supported by https://exchangeratesapi.io/
 * can be used.
 * 
 * Return an error in case of incorrect input parameters The error should be
 * returned using the format of http://jsonapi.org (status, title and
 * description are required, other fields are optional)
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
@RestController
@RequestMapping(AppConstants.API_BASE_PATH + ExchangeRatesController.CONTROLLER_PATH)
public class ExchangeRatesController {

	private static final Logger logger = LoggerFactory.getLogger(ExchangeRatesController.class);

	public static final String CONTROLLER_PATH = "/exchange-rate";

	private final ExchangeRatesService exchangeRatesService;

	@Autowired
	public ExchangeRatesController(ExchangeRatesService exchangeRatesService) {
		this.exchangeRatesService = exchangeRatesService;
	}

	/**
	 * The service receives three parameters: two currencies and a date using the
	 * URL path. The format of the URL path is
	 * /api/exchange-rate/{date}/{baseCurrency}/{targetCurrency}
	 * 
	 * The REST service returns: The exchange rate of the requested date. the
	 * average of the five days before the requested date (excluding Saturday and
	 * Sunday ) and the exchange rate trend.
	 * 
	 * @param date
	 * @param baseCurrency
	 * @param targetCurrency
	 * @return
	 */
	@GetMapping("/{date}/{baseCurrency}/{targetCurrency}")
	public RateTrendDTO exchangeRate(@PathVariable("date") String date,
			@PathVariable("baseCurrency") CurrencyEnum baseCurrency,
			@PathVariable("targetCurrency") CurrencyEnum targetCurrency) {

		logger.info(String.format("Incoming request -> date: %s, baseCurrency:%s, targetCurrency:%s", date,
				baseCurrency, targetCurrency));

		return this.exchangeRatesService.exchangeRate(date, baseCurrency, targetCurrency);
	}

}
