/**
 * 
 */
package com.shankarsan.atm.disposal.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.shankarsan.atm.disposal.constants.CommonConstants;
import com.shankarsan.atm.disposal.dto.BaseResponseDTO;
import com.shankarsan.atm.disposal.dto.DenominationRequestDTO;
import com.shankarsan.atm.disposal.exception.BusinessException;
import com.shankarsan.atm.disposal.service.DenominationService;

/**
 * @author SHANKARSAN
 *
 */
@CrossOrigin
@RestController
public class DenominationController {
	
	@Autowired
	private DenominationService denominationService;
	
	@PutMapping(value = "/v1/atm/denominations", produces = MediaType.APPLICATION_JSON_VALUE)
	@PostMapping(value = "/v1/atm/denominations", produces = MediaType.APPLICATION_JSON_VALUE)
	public Object saveDenominations(@RequestBody @Valid DenominationRequestDTO denominationRequestDTO) throws BusinessException {
		return denominationService.saveDenominations(denominationRequestDTO);
	}
	
	@PatchMapping(value = "/v1/atm/denominations", produces = MediaType.APPLICATION_JSON_VALUE)
	public Object endDateDenomination(@RequestBody @Valid DenominationRequestDTO denominationRequestDTO) throws BusinessException {
		return denominationService.endDateDenominations(denominationRequestDTO);
	}
	
	@DeleteMapping(value = "/v1/atm/denominations", produces = MediaType.APPLICATION_JSON_VALUE)
	public Object deleteDenomination(@RequestBody @Valid DenominationRequestDTO denominationRequestDTO) throws BusinessException {
		return denominationService.deleteDenominations(denominationRequestDTO);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public Object handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
		final BaseResponseDTO baseResponseDTO = new BaseResponseDTO(null, null, HttpStatus.BAD_REQUEST);
		ex
			.getBindingResult()
			.getAllErrors()
			.stream()
			.map(err -> new StringBuilder(((FieldError) err).getField()).append(CommonConstants.COLON).append(((FieldError) err).getDefaultMessage()).toString())
			.forEach(err -> {baseResponseDTO.setErrorMessages(err); baseResponseDTO.setUserMessages(err);});
		return baseResponseDTO;
	}
}
