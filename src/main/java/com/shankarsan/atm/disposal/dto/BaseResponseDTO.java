/**
 * 
 */
package com.shankarsan.atm.disposal.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @author SHANKARSAN
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseResponseDTO {
	
	private List<String> userMesaages;
	private List<String> errorMessages;
	@JsonIgnore
	private HttpStatus httpStatus;
	
	public BaseResponseDTO() {
		
	}
	
	public BaseResponseDTO(String userMessage, String errorMessage, HttpStatus httpStatus) {
		setHttpStatus(httpStatus);
		setErrorMessages(errorMessage);
		setUserMessages(userMessage);
	}
	
	public List<String> getUserMesaages() {
		if(null == userMesaages) {
			userMesaages = new ArrayList<>();
		}
		return userMesaages;
	}

	/**
	 * @param httpStatus the httpStatus to set
	 */
	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}
	/**
	 * @return the httpStatus
	 */
	public HttpStatus getHttpStatus() {
		return httpStatus;
	}
	/**
	 * @return the errorMessage
	 */
	public List<String> getErrorMessages() {
		if(null == errorMessages) {
			errorMessages = new ArrayList<>();
		}
		return errorMessages;
	}
	/**
	 * @param errorMessage the errorMessage to set
	 */
	public void setErrorMessages(String errorMessage) {
		if(!StringUtils.isEmpty(errorMessage)) {
			getErrorMessages().add(errorMessage);
		}
	}
	
	public void setUserMessages(String userMessage) {
		if(!StringUtils.isEmpty(userMessage)) {
			getUserMesaages().add(userMessage);
		}
	}

}
