package com.shankarsan.atm.disposal.interceptor;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.shankarsan.atm.disposal.dto.BaseResponseDTO;
import com.shankarsan.atm.disposal.exception.BusinessException;

@Configuration
@Aspect
public class ControllerInterceptor {
	
	private Logger _logger = LoggerFactory.getLogger(this.getClass());

	@Around(value = "execution(* com.shankarsan.atm.disposal.controller.*.*(..))")
	public ResponseEntity<BaseResponseDTO> interceptController(ProceedingJoinPoint proceedingJoinPoint) {
		Object baseResponseDTO = null;
		try {
			_logger.trace("Entering " + proceedingJoinPoint.getSignature().getName() + " method of " + proceedingJoinPoint.getClass().getName());
			baseResponseDTO = (BaseResponseDTO) proceedingJoinPoint.proceed();
			_logger.trace("Exiting " + proceedingJoinPoint.getSignature().getName() + " method of " + proceedingJoinPoint.getClass().getName());
			if(null == baseResponseDTO) {
				throw new BusinessException(new BaseResponseDTO("Something went wrong", "baseResponseDTO is null", HttpStatus.NOT_FOUND));
			}
			if(!(baseResponseDTO instanceof BaseResponseDTO)) {
				throw new BusinessException(new BaseResponseDTO("Something went wrong", "Unauthorized response object used", HttpStatus.UNAUTHORIZED));
			}
		}catch(BusinessException e) {
			_logger.error(e.getErrorMessages());
			baseResponseDTO = e.getBaseResponseDTO();
		}catch(Throwable t) {
			_logger.error(t.getMessage());
			baseResponseDTO = new BaseResponseDTO("Something went wrong", t.getMessage(), HttpStatus.NOT_FOUND);
		}
		if(null == baseResponseDTO) {
			baseResponseDTO = new BaseResponseDTO("Something went wrong", "baseResponseDTO is null", HttpStatus.NOT_FOUND);
		}
		if(null == ((BaseResponseDTO)baseResponseDTO).getHttpStatus()) {
			((BaseResponseDTO)baseResponseDTO).setHttpStatus(HttpStatus.OK);
		}
		return new ResponseEntity<BaseResponseDTO>((BaseResponseDTO)baseResponseDTO, ((BaseResponseDTO)baseResponseDTO).getHttpStatus());
	}
}
