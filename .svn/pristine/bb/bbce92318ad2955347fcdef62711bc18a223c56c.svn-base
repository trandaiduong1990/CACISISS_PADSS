package org.transinfo.cacis.dataacess.dao.customerservice;

import java.util.Collection;
import java.util.List;

import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.dao.BaseDAO;
import org.transinfo.cacis.dto.batchprocess.CardATMLinkDto;
import org.transinfo.cacis.dto.cardproduction.ApplicationProcessDto;
import org.transinfo.cacis.dto.cardproduction.CustomerLimitsDto;
import org.transinfo.cacis.dto.customerservice.CustomerScreenProcessDto;
import org.transinfo.cacis.dto.customerservice.CustomerScreenSearchDto;

@SuppressWarnings("unchecked")
public interface CustomerScreenProcessDAO extends BaseDAO {

	public Collection search(CustomerScreenSearchDto objSearchDto, int pageNo)
	throws TPlusException;

	public Collection getAllCardsByAccountID(String accountId)
	throws TPlusException;

	public CustomerScreenProcessDto getApplicationProcessDto(String customerId)
	throws TPlusException;

	public CustomerLimitsDto getCustomerLimitsDto(String cardNo)
	throws TPlusException;

	public CardATMLinkDto getCardATMLinkDto(String cardNo)
	throws TPlusException;

	public List getSuppCards(String cardNo, String accountNo)
	throws TPlusException;

	public Collection customerHistory(String custIdNumber)
	throws TPlusException;

	public Collection customerHistoryNew(String custIdNumber)
	throws TPlusException;

	public Collection getCardDetails(String appID) 
	throws TPlusException;

	public boolean update(CardATMLinkDto objCardATMLinkDto) 
	throws TPlusException;
	
	public boolean updateCU(ApplicationProcessDto objAppProcessDto) 
	throws TPlusException;
	
	public Double getTotNonReconAmt(String accountId)
	throws TPlusException;
	
	public List getOtherCards(String cardNo, String accountNo) throws TPlusException;

}
