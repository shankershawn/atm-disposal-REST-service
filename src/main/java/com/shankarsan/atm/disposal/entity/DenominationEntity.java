package com.shankarsan.atm.disposal.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity(name = "DenominationEntity")
@Table(name = "T_ATM_DENOMINATIONS")
public class DenominationEntity extends BaseAuditEntity{
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DENOMINATIONS_SEQ")
	@SequenceGenerator(sequenceName = "S_ROW_ID", name = "DENOMINATIONS_SEQ", allocationSize = 1)
	private Long denominationId;
	private String denominationName;
	private String denominationCurrencyCode;
	private Long denominationValue;
	private String denominationType;
//	private String denominationLegislationCode;
	
//	public String getDenominationLegislationCode() {
//		return denominationLegislationCode;
//	}
//	public void setDenominationLegislationCode(String denominationLegislationCode) {
//		this.denominationLegislationCode = denominationLegislationCode;
//	}
	
	public String getDenominationType() {
		return denominationType;
	}
	public void setDenominationType(String denominationType) {
		this.denominationType = denominationType;
	}
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
	public Long getId() {
		return denominationId;
	}
	public void setId(Long id) {
		this.denominationId = id;
	}

}
