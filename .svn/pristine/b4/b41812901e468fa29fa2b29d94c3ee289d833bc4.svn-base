package org.transinfo.cacis.dataacess.dao.customerservice;

import java.util.Collection;
import java.util.Map;

import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.dao.BaseDAO;
import org.transinfo.cacis.dto.cardproduction.CardsDto;
import org.transinfo.cacis.dto.csr.CustomerInfoDto;
import org.transinfo.cacis.dto.customerservice.CardChangeDto;

@SuppressWarnings("unchecked")
public interface CardChangeDAO extends BaseDAO {

	public CardsDto getCardDto(String cardNo) throws TPlusException;

	public CustomerInfoDto getCustomerInfoDto(String customerId)
			throws TPlusException;

	public CardChangeDto getCardChangeDto(String cardNo) throws TPlusException;

	public boolean add(CardChangeDto objCardChangeDto) throws TPlusException;

	public Collection processSearch(CardChangeDto objDto, int pageNo)
			throws TPlusException;

	public boolean accept(CardChangeDto objDto) throws TPlusException;

	public boolean reject(CardChangeDto objDto) throws TPlusException;

	public Map getProductListForChange(String issuerID, String cardNo) throws TPlusException;

}
