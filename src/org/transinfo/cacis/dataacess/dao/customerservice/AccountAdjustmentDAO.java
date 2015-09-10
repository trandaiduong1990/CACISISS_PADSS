package org.transinfo.cacis.dataacess.dao.customerservice;

import java.util.Collection;
import java.util.Map;

import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.dao.BaseDAO;
import org.transinfo.cacis.dto.cardproduction.CardsDto;
import org.transinfo.cacis.dto.cardproduction.CustomerAccountDto;
import org.transinfo.cacis.dto.customerservice.AccountAdjustmentDto;
import org.transinfo.cacis.dto.settings.CardProductDto;

@SuppressWarnings("unchecked")
public interface AccountAdjustmentDAO extends BaseDAO {

	public CardsDto getCardDto(String cardNo) throws TPlusException;
	
	public CardProductDto getCardProductDto(String cardProductId) throws TPlusException;

	//public CustomerInfoDto getCustomerInfoDto(String customerId) throws TPlusException;

	public AccountAdjustmentDto getAccountAdjustmentDto(String cardNo) throws TPlusException;
	
	public CustomerAccountDto getCustomerAccountDto(String accountId) throws TPlusException;

	public boolean add(AccountAdjustmentDto objAccountAdjustmentDto) throws TPlusException;

	public Collection processSearch(AccountAdjustmentDto objDto, int pageNo)
			throws TPlusException;

	public boolean accept(AccountAdjustmentDto objDto) throws TPlusException;

	public boolean reject(AccountAdjustmentDto objDto) throws TPlusException;

	public Map getProductListForChange(String issuerID, String cardNo) throws TPlusException;

}
