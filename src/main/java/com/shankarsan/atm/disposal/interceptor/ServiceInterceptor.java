package com.shankarsan.atm.disposal.interceptor;

import java.util.Calendar;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

import com.shankarsan.atm.disposal.dto.BaseRequestDTO;
import com.shankarsan.atm.disposal.dto.BaseResponseDTO;
import com.shankarsan.atm.disposal.exception.BusinessException;

@Configuration
@Aspect
public class ServiceInterceptor {
	
	private Logger _logger = LoggerFactory.getLogger(this.getClass());
	
	@Around(value = "execution(* com.shankarsan.atm.disposal.service.*.*(..))")
	public Object processAuditFields(ProceedingJoinPoint proceedingJoinPoint) throws BusinessException {
		
		Object baseRequestDTO = null;
		Object baseResponseDTO = null;
		try {
			baseRequestDTO = proceedingJoinPoint.getArgs()[0];
			if(!(baseRequestDTO instanceof BaseRequestDTO)) {
				throw new BusinessException(new BaseResponseDTO("Something went wrong", "Unauthorized request object used", HttpStatus.UNAUTHORIZED));
			}
			((BaseRequestDTO)baseRequestDTO).setCreatedBy("ADMIN");
			((BaseRequestDTO)baseRequestDTO).setCreationDate(Calendar.getInstance().getTime());
			((BaseRequestDTO)baseRequestDTO).setLastUpdatedBy("ADMIN");
			((BaseRequestDTO)baseRequestDTO).setLastUpdateDate(Calendar.getInstance().getTime());
			_logger.trace("Entering " + proceedingJoinPoint.getSignature().getName() + " method of " + proceedingJoinPoint.getThis().getClass().getName());
			baseResponseDTO =proceedingJoinPoint.proceed();
			_logger.trace("Exiting " + proceedingJoinPoint.getSignature().getName() + " method of " + proceedingJoinPoint.getClass().getName());
		}catch(BusinessException be) {
			_logger.error(be.getErrorMessages());
			throw be;
		}catch(Throwable t) {
			_logger.error(t.getMessage());
			throw new BusinessException(new BaseResponseDTO("Something went wrong", t.getMessage(), HttpStatus.NOT_FOUND));
		}
		return baseResponseDTO;
	}
}
