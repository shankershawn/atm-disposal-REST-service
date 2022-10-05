/**
 * 
 */
package com.shankarsan.atm.disposal.exception;

import java.util.stream.Collectors;

import com.shankarsan.atm.disposal.dto.BaseResponseDTO;

/**
 * @author shank
 *
 */
@SuppressWarnings("serial")
public class BusinessException extends Throwable {
	private BaseResponseDTO baseResponseDTO;
	public String getErrorMessages() {
		return getBaseResponseDTO().getErrorMessages().stream().collect(Collectors.joining(","));
	}
	public BusinessException(BaseResponseDTO baseResponseDTO) {
		this.baseResponseDTO = baseResponseDTO;
	}
	public BaseResponseDTO getBaseResponseDTO() {
		return baseResponseDTO;
	}
	public void setBaseResponseDTO(BaseResponseDTO baseResponseDTO) {
		this.baseResponseDTO = baseResponseDTO;
	}

}
