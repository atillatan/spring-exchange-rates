package com.atilla.exchangerates.provider;

import com.atilla.exchangerates.common.CurrencyEnum;
import com.atilla.exchangerates.common.RateListDTO;

/**
 * @author Atilla Tanrikulu
 * 
 */
public interface RateProvider {

	RateListDTO callProviderApi(CurrencyEnum baseCurrency, String start_at, String end_at);
}
