/**
 * 
 */
package com.atilla.exchangerates.common;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author atilla
 *
 */

@Configuration
public class SpringConfiguration {

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

}
