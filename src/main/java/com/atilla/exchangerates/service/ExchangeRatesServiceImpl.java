package com.atilla.exchangerates.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.InvalidParameterException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atilla.exchangerates.common.CurrencyEnum;
import com.atilla.exchangerates.common.RateListDTO;
import com.atilla.exchangerates.common.RateTrendDTO;
import com.atilla.exchangerates.common.RateTrendEnum;
import com.atilla.exchangerates.domain.QueryHistory;
import com.atilla.exchangerates.provider.RateProvider;
import com.atilla.exchangerates.provider.TimeProvider;
import com.atilla.exchangerates.repository.QueryHistoryRepository;

/**
 * @author atilla
 *
 */
@Service
@Transactional
public class ExchangeRatesServiceImpl implements ExchangeRatesService {

	private final Logger logger = LoggerFactory.getLogger(ExchangeRatesServiceImpl.class);

	@Value(value = "${error.invaliddate:Only dates between 2000-01-01 and yesterday are allowed.}")
	private String ERROR_INVALID_DATE;

	private RateProvider rateProvider;
	private final QueryHistoryRepository queryHistoryRepository;
	private TimeProvider timeProvider;

	@Autowired
	public ExchangeRatesServiceImpl(QueryHistoryRepository queryHistoryRepository, TimeProvider timeProvider,
			RateProvider rateProvider) {
		this.rateProvider = rateProvider;
		this.queryHistoryRepository = queryHistoryRepository;
		this.timeProvider = timeProvider;
	} 

	@Override
	public RateTrendDTO exchangeRate(String date, CurrencyEnum baseCurrency, CurrencyEnum targetCurrency) {

		Calendar calendarParam = checkDate(date);

		RateTrendDTO rateTrendDTO = new RateTrendDTO();

		try {
			// excluding weekends
			// provider service has a feature for excluding weekends. If we subtract 6 days
			// we get the last 5 days without weekend
			calendarParam.add(Calendar.DATE, -6);

			StringBuilder sbStartAt = new StringBuilder();
			sbStartAt.append(calendarParam.get(Calendar.YEAR)).append("-");
			sbStartAt.append(calendarParam.get(Calendar.MONTH) + 1).append("-");
			sbStartAt.append(calendarParam.get(Calendar.DAY_OF_MONTH));

			RateListDTO rateList = rateProvider.callProviderApi(baseCurrency, sbStartAt.toString(), date);

			HashMap<String, EnumMap<CurrencyEnum, BigDecimal>> rates = rateList.getRates();
			EnumMap<CurrencyEnum, BigDecimal> currencyMap = rates.get(date);
			BigDecimal todayRate = currencyMap.get(targetCurrency);
			rateTrendDTO.setExchangeRate(todayRate);

			// calculate averageFiveDays of 5 days and set it on the DTO
			rateTrendDTO.setAverageOfFiveDays(calculateAverageRate(targetCurrency, rateList.getRates()));

			// calculate exchange rate trend, exchangeRateTrend, and set it on the DTO
			rateTrendDTO.setExchangeRateTrend(calculateExchangeRateTrend(targetCurrency, rateList.getRates()));

			// All successful queries should be persisted in the DB.
			QueryHistory queryHistory = new QueryHistory();
			queryHistory.setDate(date.toString());
			queryHistory.setBaseCurrency(baseCurrency.toString());
			queryHistory.setTargetCurrency(targetCurrency.toString());
			queryHistory.setCreateDate(timeProvider.now());
			queryHistory.setExchangeRate(rateTrendDTO.getExchangeRate());
			queryHistoryRepository.save(queryHistory);

		} catch (Exception e) {
			logger.error("API call error", e);
			throw e;
		}

		return rateTrendDTO;
	}

	private Calendar checkDate(String date) {

		// Validation Requirements:
		// 1. Allow users to query the exchange rate using two currencies and a date
		// using the API.
		// 2. Only dates between 2000-01-01 and yesterday are allowed.
		// 3. Only the currencies supported by https://exchangeratesapi.io/ can be used.

		if (date == null || date.length() != 10)
			throw new InvalidParameterException(ERROR_INVALID_DATE);

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		LocalDate localDate;

		try {
			localDate = LocalDate.parse(date, formatter);
		} catch (Exception e1) {
			logger.error("API call error", e1);
			throw new InvalidParameterException(ERROR_INVALID_DATE);
		}

		if (localDate == null) {
			throw new InvalidParameterException(ERROR_INVALID_DATE);
		}

		Calendar calendarParam = Calendar.getInstance();
		calendarParam.set(localDate.getYear(), localDate.getMonthValue() - 1, localDate.getDayOfMonth());

		Calendar calendarYesterday = Calendar.getInstance();
		calendarYesterday.add(Calendar.DATE, -1);

		// Only dates between 2000-01-01 and yesterday are allowed.
		if ((calendarParam.compareTo(calendarYesterday) > 0) || ((calendarParam.get(Calendar.YEAR)) < 2000)) {
			throw new IllegalArgumentException(ERROR_INVALID_DATE);
		}
		return calendarParam;
	}

	/**
	 * The exchange rate trend is determined using following definition: 
	 * descending: when the exchange rates in the last five days are in strictly
	 * descending order,
	 * 
	 * ascending: when the exchange rates in the last five days are in strictly
	 * ascending order
	 * 
	 * constant: when the exchange rates in the last five days are the same
	 * 
	 * undefined: in other cases.
	 * 
	 * @param targetCurrency
	 * @param ratesMap
	 * @return
	 */
	private RateTrendEnum calculateExchangeRateTrend(CurrencyEnum targetCurrency,
			HashMap<String, EnumMap<CurrencyEnum, BigDecimal>> ratesMap) {

		TreeMap<String, EnumMap<CurrencyEnum, BigDecimal>> tempMap = new TreeMap<String, EnumMap<CurrencyEnum, BigDecimal>>();

		for (String elem : ratesMap.keySet()) {
			tempMap.put(elem.replaceAll("-", ""), ratesMap.get(elem));
		}

		ArrayList<EnumMap<CurrencyEnum, BigDecimal>> rates = new ArrayList<EnumMap<CurrencyEnum, BigDecimal>>(
				tempMap.values());

		for (int i = -1; i <= 1; i++) {
			for (int j = 0; j < rates.size(); j++) {

				EnumMap<CurrencyEnum, BigDecimal> m1 = (EnumMap<CurrencyEnum, BigDecimal>) rates.get(j);
				EnumMap<CurrencyEnum, BigDecimal> m2 = (EnumMap<CurrencyEnum, BigDecimal>) rates.get(j + 1);

				if (m1.get(targetCurrency).compareTo(m2.get(targetCurrency)) == i) {
					continue;
				}
				if (i == -1) {
					return RateTrendEnum.ASCENDING;
				} else if (i == 0) {
					return RateTrendEnum.CONSTANT;
				} else {
					return RateTrendEnum.DESCENDING;
				}
			}
		}
		return RateTrendEnum.UNDEFINED;
	}

	private BigDecimal calculateAverageRate(CurrencyEnum targetCurrency,
			HashMap<String, EnumMap<CurrencyEnum, BigDecimal>> ratesMap) {

		BigDecimal sumRates = BigDecimal.ZERO;

		for (EnumMap<CurrencyEnum, BigDecimal> rates : ratesMap.values()) {
			BigDecimal val = rates.get(targetCurrency);
			sumRates = sumRates.add(val);
		}

		return sumRates.divide(BigDecimal.valueOf(5), RoundingMode.UP);
	}

}
