package org.transinfo.cacis.dataacess.dao.customerservice;

import java.util.Collection;

import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.dao.BaseDAO;
import org.transinfo.cacis.dto.cardproduction.CustomerLimitsDto;
import org.transinfo.cacis.dto.customerservice.CardLevelLimitSearchDto;
import org.transinfo.cacis.dto.settings.CardProductLimitDto;

@SuppressWarnings("unchecked")
public interface CardLevelLimitDAO extends BaseDAO {

	public Collection search(CardLevelLimitSearchDto objSearchDto, int pageNo)
			throws TPlusException;
	
	public CustomerLimitsDto getCustomerLimitDto(String cardNo)
			throws TPlusException;

	public boolean update(CustomerLimitsDto objCustomerLimitsDto) 
			throws TPlusException;

	public CardProductLimitDto getCardProductLimitDto(String cardNo)
			throws TPlusException;

}
