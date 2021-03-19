/**
 * 
 */
package com.atilla.exchangerates.exception;

import java.security.InvalidParameterException;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.atilla.exchangerates.service.ExchangeRatesServiceImpl;

/**
 * @author atilla
 *
 */
@ControllerAdvice
@RequestMapping(produces = "application/vnd.error+json")
public class ExceptionControllerAdvice extends ResponseEntityExceptionHandler {

	private final Logger logger = LoggerFactory.getLogger(ExchangeRatesServiceImpl.class);
 
	@ExceptionHandler(InvalidParameterException.class)
	public ResponseEntity<ErrorResponse> error(final InvalidParameterException exception) {
		return error(exception, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<ErrorResponse> error(final IllegalArgumentException exception) {
		return error(exception, HttpStatus.NOT_ACCEPTABLE);
	}
  
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> error(final Exception exception) {
		return error(exception, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	private ResponseEntity<ErrorResponse> error(final Exception exception, final HttpStatus httpStatus) {

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("Error", exception.getMessage());

		final String message = Optional.of(exception.getMessage()).orElse(exception.getClass().getSimpleName());

		// create ErrorResponse
		ErrorResponse error = new ErrorResponse(httpStatus.value(), "Error ocurred", message,
				System.currentTimeMillis());

		logger.error(exception.getMessage(), exception);

		// return ResponseEntity
		return new ResponseEntity<>(error, httpStatus);
	}

}
