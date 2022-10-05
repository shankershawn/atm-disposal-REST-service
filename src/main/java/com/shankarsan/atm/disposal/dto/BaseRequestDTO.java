/**
 * 
 */
package com.shankarsan.atm.disposal.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author SHANKARSAN
 *
 */
public class BaseRequestDTO {

	@JsonProperty("qgw") @NotNull private Date effectiveStartDate;
	@JsonProperty("lfn") @NotNull private Date effectiveEndDate;
	private String createdBy;
	private Date creationDate;
	private String lastUpdatedBy;
	private Date lastUpdateDate;
	/**
	 * @return the effectiveStartDate
	 */
	public Date getEffectiveStartDate() {
		return effectiveStartDate;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public String getLastUpdatedBy() {
		return lastUpdatedBy;
	}
	public void setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}
	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}
	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}
	/**
	 * @param effectiveStartDate the effectiveStartDate to set
	 */
	public void setEffectiveStartDate(Date effectiveStartDate) {
		this.effectiveStartDate = effectiveStartDate;
	}
	/**
	 * @return the effectiveEndDate
	 */
	public Date getEffectiveEndDate() {
		return effectiveEndDate;
	}
	/**
	 * @param effectiveEndDate the effectiveEndDate to set
	 */
	public void setEffectiveEndDate(Date effectiveEndDate) {
		this.effectiveEndDate = effectiveEndDate;
	}
}
