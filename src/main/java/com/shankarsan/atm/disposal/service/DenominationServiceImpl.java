/**
 * 
 */
package com.shankarsan.atm.disposal.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.shankarsan.atm.disposal.constants.CommonConstants;
import com.shankarsan.atm.disposal.dto.BaseResponseDTO;
import com.shankarsan.atm.disposal.dto.DenominationRequestDTO;
import com.shankarsan.atm.disposal.entity.DenominationEntity;
import com.shankarsan.atm.disposal.exception.BusinessException;
import com.shankarsan.atm.disposal.repository.DenominationRepository;

/**
 * @author shank
 *
 */
@Service
public class DenominationServiceImpl implements DenominationService {
	
	@Autowired private DenominationRepository denominationRepository;

	@Override
	public BaseResponseDTO saveDenominations(DenominationRequestDTO denominationRequestDTO) throws BusinessException {
		DenominationEntity denominationEntity = null;
		SimpleDateFormat simpleDateFormat = null;
		List<DenominationEntity> denominations = null;
		try {
			if(null != denominationRequestDTO) {
				simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
				if(denominationRequestDTO.getEffectiveEndDate().before(simpleDateFormat.parse(CommonConstants.END_OF_TIME))) {
					throw new BusinessException(new BaseResponseDTO("End date cannot be before end of time.", "End date cannot be before end of time.", HttpStatus.BAD_REQUEST));
				}
				denominationEntity = new DenominationEntity();
				BeanUtils.copyProperties(denominationRequestDTO, denominationEntity);
				denominations = denominationRepository
						.getDenominations(
								denominationEntity.getDenominationCurrencyCode(),
								denominationEntity.getDenominationValue(),
								denominationEntity.getDenominationType());
				this.processDateEffectivity(denominations, denominationEntity);
				denominations.stream().forEach(e -> denominationRepository.save(e));
				denominationEntity = denominationRepository.save(denominationEntity);
			}else {
				throw new BusinessException(new BaseResponseDTO("Something went wrong", "denominationRequestDTO is null", HttpStatus.BAD_REQUEST));
			}
		} catch (BusinessException e) {
			throw e;
		} catch (BeansException | ParseException e1) {
			throw new BusinessException(new BaseResponseDTO("Something went wrong", e1.getMessage(), HttpStatus.NOT_FOUND));
		}
		return new BaseResponseDTO();
	}

	@Override
	public void getDenominations(DenominationRequestDTO denominationRequestDTO) throws BusinessException {
		// TODO Auto-generated method stub

	}

	@Override
	public BaseResponseDTO deleteDenominations(DenominationRequestDTO denominationRequestDTO) throws BusinessException {
		DenominationEntity denominationEntity = null;
		try {
			if(null != denominationRequestDTO) {
				denominationEntity = denominationRepository.getDenomination(denominationRequestDTO.getDenominationCurrencyCode(),
						denominationRequestDTO.getDenominationValue(), denominationRequestDTO.getDenominationType(), denominationRequestDTO.getEffectiveDate());
				this.deleteRecord(denominationEntity, denominationRepository);
			}
		}catch(BusinessException e) {
			throw e;
		}catch(Exception e1) {
			throw new BusinessException(new BaseResponseDTO("Something went wrong", e1.getMessage(), HttpStatus.NOT_FOUND));
		}
		return new BaseResponseDTO();
	}

	@Override
	public BaseResponseDTO endDateDenominations(DenominationRequestDTO denominationRequestDTO) throws BusinessException {
		DenominationEntity denominationEntity = null;
		try {
			if(null != denominationRequestDTO) {
				denominationEntity = denominationRepository.getDenomination(denominationRequestDTO.getDenominationCurrencyCode(),
						denominationRequestDTO.getDenominationValue(), denominationRequestDTO.getDenominationType(), denominationRequestDTO.getEffectiveDate());
				this.endDateRecord(denominationEntity, denominationRequestDTO.getEffectiveDate());
				denominationEntity = denominationRepository.save(denominationEntity);
			}else {
				throw new BusinessException(new BaseResponseDTO("Something went wrong", "denominationRequestDTO is null", HttpStatus.BAD_REQUEST));
			}
		} catch (BusinessException e) {
			throw e;
		} catch (Throwable t) {
			throw new BusinessException(new BaseResponseDTO("Something went wrong", t.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR));
		}
		return new BaseResponseDTO();
	}

}
