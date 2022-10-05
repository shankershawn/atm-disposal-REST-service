package com.shankarsan.atm.disposal.service;

import com.shankarsan.atm.disposal.dto.BaseResponseDTO;
import com.shankarsan.atm.disposal.dto.DenominationRequestDTO;
import com.shankarsan.atm.disposal.exception.BusinessException;

public interface DenominationService extends BaseService {

	public BaseResponseDTO saveDenominations(DenominationRequestDTO denominationRequestDTO) throws BusinessException;
	public BaseResponseDTO endDateDenominations(DenominationRequestDTO denominationRequestDTO) throws BusinessException;
	public void getDenominations(DenominationRequestDTO denominationRequestDTO) throws BusinessException;
	public BaseResponseDTO deleteDenominations(DenominationRequestDTO denominationRequestDTO) throws BusinessException;
}
