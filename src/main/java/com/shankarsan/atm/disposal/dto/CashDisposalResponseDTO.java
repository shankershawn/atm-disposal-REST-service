/**
 * 
 */
package com.shankarsan.atm.disposal.dto;

import java.math.BigDecimal;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @author SHANKARSAN
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CashDisposalResponseDTO extends BaseResponseDTO {
	private Map<Long, BigDecimal> cashMap = null;

	public CashDisposalResponseDTO(int errorCode, String errorMessage) {
		super.errorCode = errorCode;
		super.errorMessage = errorMessage;
	}

	/**
	 * @return the cashMap
	 */
	public Map<Long, BigDecimal> getCashMap() {
		return cashMap;
	}

	/**
	 * @param cashMap the cashMap to set
	 */
	public void setCashMap(Map<Long, BigDecimal> cashMap) {
		this.cashMap = cashMap;
	}
	
}
