package com.atilla.exchangerates;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Random;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import com.atilla.exchangerates.common.RateTrendDTO;
import com.atilla.exchangerates.controller.ExchangeRatesController;
import com.atilla.exchangerates.domain.QueryHistory;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class ExchangeratesApplicationTests {

	@Autowired
	private ExchangeRatesController controller;

	@Autowired(required = true)
	private TestRestTemplate restTemplate;

	@LocalServerPort
	private int port;

	@Test
	public void contextLoads() throws Exception {
		assertThat(controller).isNotNull();
	}

	@Test
	public void getExchangeRateTest() throws Exception {

		String url = "http://localhost:" + port + "/api/exchange-rate/" + "2012-12-23/USD/EUR";

		RateTrendDTO rateTrendDTO = this.restTemplate.getForObject(url, RateTrendDTO.class);

		assertTrue(rateTrendDTO != null);
	}

	@Test
	public void getExchangeRateRandomDateTest() throws Exception {

		String randomDate = generateDate();

		String url = "http://localhost:" + port + "/api/exchange-rate/" + randomDate + "/USD/EUR";

		RateTrendDTO rateTrendDTO = this.restTemplate.getForObject(url, RateTrendDTO.class);

		assertTrue(rateTrendDTO != null);
	}

	@Test
	public void getQueryHistoryDailyTest() throws Exception {

		this.restTemplate.getForObject("http://localhost:" + port + "/api/exchange-rate/2021-03-19/USD/EUR",
				RateTrendDTO.class);

		String url = "http://localhost:" + port + "/api/exchange-rate/history/daily/2021/03/19";

		QueryHistory[] queryHistory = this.restTemplate.getForObject(url, QueryHistory[].class);

		assertTrue(queryHistory != null);
	}
	
	
	@Test
	public void getQueryHistoryMonthlyTest() throws Exception {

		this.restTemplate.getForObject("http://localhost:" + port + "/api/exchange-rate/2021-03-19/USD/EUR",
				RateTrendDTO.class);

		String url = "http://localhost:" + port + "/api/exchange-rate/history/monthly/2021/03";

		QueryHistory[] queryHistory = this.restTemplate.getForObject(url, QueryHistory[].class);

		assertTrue(queryHistory != null);
	}

	private String generateDate() {
		Random rand = new Random();

		String randomMonth = String.valueOf(rand.nextInt(12));
		if (randomMonth.length() == 1)
			randomMonth = "0" + randomMonth;

		String randomDay = String.valueOf(rand.nextInt(28));
		if (randomDay.length() == 1)
			randomDay = "0" + randomDay;

		String randomDate = String.format("%s-%s-%s", rand.nextInt(2021 - 1999) + 1999, randomMonth, randomDay);
		return randomDate;
	}

}