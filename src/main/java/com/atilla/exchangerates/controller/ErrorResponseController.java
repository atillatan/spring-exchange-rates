package com.atilla.exchangerates.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.atilla.exchangerates.exception.ErrorResponse;

@Controller
public class ErrorResponseController implements ErrorController {

	@RequestMapping("/error")
	public ResponseEntity<ErrorResponse> handleError() {

		ErrorResponse error = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "404 Not Found",
				"Request URL not found", System.currentTimeMillis());
		
		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public String getErrorPath() {
		return "/error";
	}
}