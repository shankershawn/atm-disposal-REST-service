/**
 * 
 */
package com.shankarsan.atm.disposal.dto;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @author SHANKARSAN
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CashDisposalResponseDTO extends BaseResponseDTO {
	private Map<Long, Long> cashMap = null;

	public CashDisposalResponseDTO(Map<Long, Long> cashMap) {
		super();
		this.cashMap = cashMap;
	}

	/**
	 * @return the cashMap
	 */
	public Map<Long, Long> getCashMap() {
		return cashMap;
	}

	/**
	 * @param cashMap the cashMap to set
	 */
	public void setCashMap(Map<Long, Long> cashMap) {
		this.cashMap = cashMap;
	}
	
}
