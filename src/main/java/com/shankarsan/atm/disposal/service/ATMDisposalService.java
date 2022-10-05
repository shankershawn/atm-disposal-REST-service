/**
 * 
 */
package com.shankarsan.atm.disposal.service;

import java.util.TreeSet;

import com.shankarsan.atm.disposal.dto.CashDisposalResponseDTO;
import com.shankarsan.atm.disposal.exception.BusinessException;

/**
 * @author shank
 *
 */
public interface ATMDisposalService {

	public CashDisposalResponseDTO getCashDisposalResponseDTO(Long amount, TreeSet<Long> denominationSet) throws BusinessException;
}
