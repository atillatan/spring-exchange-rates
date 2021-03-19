/**
 * 
 */
package com.atilla.exchangerates.provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.atilla.exchangerates.common.CurrencyEnum;
import com.atilla.exchangerates.common.RateListDTO;

/**
 * @author Atilla Tanrikulu
 * 
 */
@Component
public class ExchangeRatesAPIProvider extends ThirdPartyProvider implements RateProvider {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Value(value = "${provider.exchangeratesapi.host:https://api.exchangeratesapi.io}")
	private String host;

	private RestTemplate restTemplate = new RestTemplate();

	public ExchangeRatesAPIProvider() {

	} 

	@Override
	public RateListDTO callProviderApi(CurrencyEnum baseCurrency, String start_at, String end_at) {

		StringBuilder sbParams = new StringBuilder();
		sbParams.append("/history?base=").append(baseCurrency.toString());
		sbParams.append("&start_at=").append(start_at);
		sbParams.append("&end_at=").append(end_at);

		RateListDTO rateList = null;

		try {
			logger.info("Call provider API:" + host + sbParams.toString());
			rateList = restTemplate.getForObject(host + sbParams.toString(), RateListDTO.class);
		} catch (Exception e) {
			logger.error("API call error", e);
			throw e;
		}

		logger.info("Response received from provider API:" + rateList.toString());

		return rateList;
	}
}