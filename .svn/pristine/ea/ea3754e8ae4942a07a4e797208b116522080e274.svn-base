package org.transinfo.cacis.dataacess.dao.customerservice;

import java.util.List;

import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.dao.BaseDAO;
import org.transinfo.cacis.dto.cardproduction.CardsDto;
import org.transinfo.cacis.dto.transaction.CustomerFeeDto;

@SuppressWarnings("unchecked")
public interface PayWaiverDAO extends BaseDAO {

	public CardsDto getCardDto(String cardNo) throws TPlusException;

	public List getIntTranx(String cardNo) throws TPlusException;
	
	public CustomerFeeDto getCustomerFee(String feeId) throws TPlusException;

	public boolean updateDB(String feeId, String userId) throws TPlusException;
	
	public List getDebitFeeTranx(String cardNo) throws TPlusException;
	
	public boolean updateDBDebit(String feeId, String userId) throws TPlusException;

}
