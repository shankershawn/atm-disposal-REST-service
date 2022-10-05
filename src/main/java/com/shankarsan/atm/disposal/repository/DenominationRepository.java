package com.shankarsan.atm.disposal.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.shankarsan.atm.disposal.entity.DenominationEntity;
import com.shankarsan.atm.disposal.exception.BusinessException;

public interface DenominationRepository extends JpaRepository<DenominationEntity, Long> {
	
	
	@Query(value = "SELECT e FROM DenominationEntity e WHERE e.denominationCurrencyCode = :denominationCurrencyCode AND e.denominationValue = :denominationValue AND e.denominationType = :denominationType order by e.effectiveStartDate desc")
	public List<DenominationEntity> getDenominations(String denominationCurrencyCode, Long denominationValue, String denominationType) throws BusinessException;
	
	@Query(value = "SELECT e FROM DenominationEntity e WHERE e.denominationCurrencyCode = :denominationCurrencyCode AND e.denominationValue = :denominationValue AND e.denominationType = :denominationType AND :effectiveDate BETWEEN e.effectiveStartDate AND e.effectiveEndDate")
	public DenominationEntity getDenomination(String denominationCurrencyCode, Long denominationValue, String denominationType, Date effectiveDate) throws BusinessException;

}
