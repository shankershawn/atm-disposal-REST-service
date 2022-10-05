package com.shankarsan.atm.disposal.service;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;

import com.shankarsan.atm.disposal.dto.BaseResponseDTO;
import com.shankarsan.atm.disposal.entity.BaseAuditEntity;
import com.shankarsan.atm.disposal.exception.BusinessException;

public interface BaseService {
	
	public default void processDateEffectivity(List<? extends BaseAuditEntity> baseAuditEntities, final BaseAuditEntity baseAuditEntity) throws BusinessException {
		Calendar calendar = null;
		boolean flag = false;
		if(!CollectionUtils.isEmpty(baseAuditEntities) && null != baseAuditEntity) {
			for(BaseAuditEntity baseEntityAudit2 : baseAuditEntities) {
				if(baseAuditEntity.getEffectiveStartDate().after(baseEntityAudit2.getEffectiveStartDate())) {
					flag = true;
					baseAuditEntity.setEffectiveEndDate(baseEntityAudit2.getEffectiveEndDate());
					if(baseEntityAudit2.getEffectiveEndDate().equals(baseAuditEntity.getEffectiveStartDate()) || 
							baseEntityAudit2.getEffectiveEndDate().after(baseAuditEntity.getEffectiveStartDate())) {
						calendar = Calendar.getInstance();
						calendar.setTime(baseAuditEntity.getEffectiveStartDate());
						calendar.add(Calendar.DATE, -1);
						baseEntityAudit2.setEffectiveEndDate(calendar.getTime());
						baseEntityAudit2.setLastUpdateDate(Calendar.getInstance().getTime());
					}
					break;
				}
			}
			if(!flag) {
				calendar = Calendar.getInstance();
				calendar.setTime(baseAuditEntities.get(baseAuditEntities.size() - 1).getEffectiveStartDate());
				calendar.add(Calendar.DATE, -1);
				baseAuditEntity.setEffectiveEndDate(calendar.getTime());
			}
		}
	}
	
	public default void endDateRecord(BaseAuditEntity baseAuditEntity, Date effectiveDate) throws BusinessException {
		if(null != baseAuditEntity && null != baseAuditEntity.getEffectiveEndDate() && null != effectiveDate) {
			if(baseAuditEntity.getEffectiveEndDate().after(effectiveDate)) {
				baseAuditEntity.setEffectiveEndDate(effectiveDate);
			}else {
				throw new BusinessException(new BaseResponseDTO("End Date cannot be after " + DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT).format(baseAuditEntity.getEffectiveEndDate()), 
						"End Date cannot be after " + DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT).format(baseAuditEntity.getEffectiveEndDate()),
						HttpStatus.BAD_REQUEST));
			}
		}else {
			throw new BusinessException(new BaseResponseDTO("Something went wrong", "null == baseEntityAudit || null == baseEntityAudit.getEffectiveEndDate() || null == effectiveEndDate", HttpStatus.BAD_REQUEST));
		}
	}
	
	public default <T extends BaseAuditEntity> void deleteRecord(T baseAuditEntity, JpaRepository<T, ?> jpaRepository) throws BusinessException {
		if(null != baseAuditEntity) {
			jpaRepository.delete(baseAuditEntity);
		}else {
			throw new BusinessException(new BaseResponseDTO("Something went wrong", "null == baseEntityAudit", HttpStatus.BAD_REQUEST));
		}
	}

}
