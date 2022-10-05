/**
 * 
 */
package com.shankarsan.atm.disposal.service;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

import org.springframework.http.HttpStatus;

import com.shankarsan.atm.disposal.dto.BaseResponseDTO;
import com.shankarsan.atm.disposal.dto.CashDisposalResponseDTO;
import com.shankarsan.atm.disposal.exception.BusinessException;

/**
 * @author shank
 *
 */
public class ATMDisposalServiceImpl implements ATMDisposalService {
	
	/*public static void main(String[] args) {
		try {
			System.out.println(new ATMDisposalServiceImpl().getCashDisposalResponseDTO(2400L, new TreeSet<>(Comparator.reverseOrder())).getCashMap());
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/

	@Override
	public CashDisposalResponseDTO getCashDisposalResponseDTO(Long amount, TreeSet<Long> denominationSet) throws BusinessException {
		//denominationSet.addAll(Arrays.asList(100L, 200L, 500L, 2000L));
		CashDisposalResponseDTO cashDisposalResponseDTO = null;
		Map<Long, Long> cashMap = new TreeMap<>();
		if(getCashMap(amount, cashMap, denominationSet)) {
			cashDisposalResponseDTO = new CashDisposalResponseDTO(cashMap);
		}else {
			throw new BusinessException(new BaseResponseDTO("Entered amount is indispensable", "Entered amount is indispensable", HttpStatus.BAD_REQUEST));
		}
		return cashDisposalResponseDTO;
	}
	
	private boolean getCashMap(Long amount, Map<Long, Long> cashMap, TreeSet<Long> denominationSet) {
		boolean isValidPathFound = false;
		if(null != cashMap && null != denominationSet) {
			for(Long denomination : denominationSet) {
				if(amount >= denomination) {
					isValidPathFound = getCashMap(amount - denomination, cashMap, denominationSet);
					if(isValidPathFound) {
						cashMap.put(denomination, cashMap.getOrDefault(denomination, 0L) + 1L);
						break;
					}
				}
			}
			isValidPathFound = isValidPathFound || amount == 0;
		}
		return isValidPathFound;
	}

}
