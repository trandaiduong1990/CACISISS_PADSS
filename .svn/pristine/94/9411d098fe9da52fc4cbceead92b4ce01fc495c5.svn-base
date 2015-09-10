package org.transinfo.cacis.dataacess.dao.settings;

import java.util.ArrayList;
import java.util.Collection;

import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.dao.BaseDAO;
import org.transinfo.cacis.dto.settings.CardProductDto;
import org.transinfo.cacis.dto.settings.CardProductFeeDto;
import org.transinfo.cacis.dto.settings.CardProductSearchDto;

@SuppressWarnings("unchecked")
public interface CardProductDAO extends BaseDAO {

	public Collection search(CardProductSearchDto objSearchDto, int pageNo)
			throws TPlusException;

	public boolean add(CardProductDto objCardProduct) throws TPlusException;

	public boolean update(CardProductDto objCardProduct) throws TPlusException;

	public boolean delete(CardProductDto objCardProduct) throws TPlusException;

	public CardProductDto getCardProductDto(String CardProductId)
			throws TPlusException;
	
	public CardProductFeeDto getCardProductFeeDto(String CardProductId)
			throws TPlusException;

	public ArrayList cardPromotionList() throws TPlusException;

	public int checkExistrecord(CardProductDto objCardProduct)
			throws TPlusException;

	public String getCardProductType(String CardProductId)
			throws TPlusException;

	public boolean hasCards(String productId) throws TPlusException;

	public boolean hasWithdrawalLimitRules(String cardProductId)
			throws TPlusException;

}
