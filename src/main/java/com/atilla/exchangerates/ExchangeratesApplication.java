package com.atilla.exchangerates;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

/**
 * @author Atilla Tanrikulu
 * 
 */
@SpringBootApplication
public class ExchangeratesApplication {

	private static final Logger logger = LoggerFactory.getLogger(ExchangeratesApplication.class);

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(ExchangeratesApplication.class, args);
		logger.info("Server started");
	}

}
