package org.transinfo.cacis.dataacess.dao.settings;

import java.util.ArrayList;
import java.util.Collection;

import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.dao.BaseDAO;
import org.transinfo.cacis.dto.settings.CardProductDto;
import org.transinfo.cacis.dto.settings.CardProductFeeDto;
import org.transinfo.cacis.dto.settings.CustomerGroupFeeDto;
import org.transinfo.cacis.dto.settings.CustomerGroupFeeSearchDto;

@SuppressWarnings("unchecked")
public interface CustomerGroupFeeDAO extends BaseDAO {

	public Collection search(CustomerGroupFeeSearchDto objSearchDto, int pageNo)
			throws TPlusException;

	public boolean add(CustomerGroupFeeDto objCardProduct) throws TPlusException;

	public boolean update(CustomerGroupFeeDto objCardProduct) throws TPlusException;

	public boolean delete(CardProductDto objCardProduct) throws TPlusException;

	public CustomerGroupFeeDto getCustomeGroupFeeDto(String CardProductId)
			throws TPlusException;
	
	public CardProductFeeDto getCardProductFeeDto(String CardProductId)
			throws TPlusException;

	public ArrayList cardPromotionList() throws TPlusException;

	public int checkExistrecord(CustomerGroupFeeDto objCardProduct)
			throws TPlusException;

	public String getCardProductType(String CardProductId)
			throws TPlusException;

	public boolean hasCards(String productId) throws TPlusException;

	public boolean hasWithdrawalLimitRules(String cardProductId)
			throws TPlusException;

}
