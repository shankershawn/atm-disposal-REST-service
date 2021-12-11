/**
 * 
 */
package com.shankarsan.atm.disposal.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author SHANKARSAN
 *
 */
public class BaseRequestDTO {

	@JsonProperty("qgw") protected Date effectiveStartDate;
	@JsonProperty("lfn") protected Date effectiveEndDate;
	/**
	 * @return the effectiveStartDate
	 */
	protected Date getEffectiveStartDate() {
		return effectiveStartDate;
	}
	/**
	 * @param effectiveStartDate the effectiveStartDate to set
	 */
	protected void setEffectiveStartDate(Date effectiveStartDate) {
		this.effectiveStartDate = effectiveStartDate;
	}
	/**
	 * @return the effectiveEndDate
	 */
	protected Date getEffectiveEndDate() {
		return effectiveEndDate;
	}
	/**
	 * @param effectiveEndDate the effectiveEndDate to set
	 */
	protected void setEffectiveEndDate(Date effectiveEndDate) {
		this.effectiveEndDate = effectiveEndDate;
	}
}
