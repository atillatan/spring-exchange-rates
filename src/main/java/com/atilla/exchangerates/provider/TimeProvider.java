/**
 * 
 */
package com.atilla.exchangerates.provider;

import java.time.LocalDateTime;

/**
 * @author atilla
 *
 */
public interface TimeProvider {
	LocalDateTime now();
} 