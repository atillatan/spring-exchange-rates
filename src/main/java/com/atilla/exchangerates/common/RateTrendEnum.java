/**
 * 
 */
package com.atilla.exchangerates.common;

/**
 * @author atilla
 *
 */
public enum RateTrendEnum {
	DESCENDING, // when the exchange rates in the last five days are in strictly descending
				// order,
	ASCENDING, // when the exchange rates in the last five days are in strictly ascending order
	CONSTANT, // when the exchange rates in the last five days are the same
	UNDEFINED; // in other cases.
 
}