package com.atilla.exchangerates.provider;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author Atilla Tanrikulu
 * 
 */
@Component
public class SystemTimeProviderImpl implements TimeProvider {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public LocalDateTime now() {
		logger.info("Time Provider: System LocalDateTime");
		return LocalDateTime.now();
	}
}
