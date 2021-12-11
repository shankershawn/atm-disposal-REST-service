/**
 * 
 */
package com.shankarsan.atm.disposal.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.shankarsan.atm.disposal.dto.BaseResponseDTO;
import com.shankarsan.atm.disposal.dto.CashDisposalResponseDTO;
import com.shankarsan.atm.disposal.dto.DenominationRequestDTO;

/**
 * @author SHANKARSAN
 *
 */
@CrossOrigin
@RestController
public class ATMDisposalController {
	
	@PostMapping(value = "/v1/atm/denominations", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BaseResponseDTO> addDenomination(@RequestBody DenominationRequestDTO denominationRequestDTOō){
		//return new ResponseEntity<BaseResponseDTO>(new CashDisposalResponseDTO(1, "Error occurred"), HttpStatus.UNAUTHORIZED);
		BaseResponseDTO baseResponseDTO = new CashDisposalResponseDTO(1, "Error occurred");
		return new ResponseEntity<BaseResponseDTO>(baseResponseDTO, baseResponseDTO.getHttpStatus());
	}
}
