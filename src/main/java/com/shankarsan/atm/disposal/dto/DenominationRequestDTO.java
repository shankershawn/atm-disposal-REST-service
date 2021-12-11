/**
 * 
 */
package com.shankarsan.atm.disposal.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author SHANKARSAN
 *
 */
public class DenominationRequestDTO extends BaseRequestDTO{

	@JsonProperty("rfe") private String denominationName;
	@JsonProperty("djq") private String denominationCurrencyCode;
	@JsonProperty("bsi") private Long denominationValue;
	/**
	 * @return the denominationName
	 */
	public String getDenominationName() {
		return denominationName;
	}
	/**
	 * @param denominationName the denominationName to set
	 */
	public void setDenominationName(String denominationName) {
		this.denominationName = denominationName;
	}
	/**
	 * @return the denominationCurrencyCode
	 */
	public String getDenominationCurrencyCode() {
		return denominationCurrencyCode;
	}
	/**
	 * @param denominationCurrencyCode the denominationCurrencyCode to set
	 */
	public void setDenominationCurrencyCode(String denominationCurrencyCode) {
		this.denominationCurrencyCode = denominationCurrencyCode;
	}
	/**
	 * @return the denominationValue
	 */
	public Long getDenominationValue() {
		return denominationValue;
	}
	/**
	 * @param denominationValue the denominationValue to set
	 */
	public void setDenominationValue(Long denominationValue) {
		this.denominationValue = denominationValue;
	}
	
}
