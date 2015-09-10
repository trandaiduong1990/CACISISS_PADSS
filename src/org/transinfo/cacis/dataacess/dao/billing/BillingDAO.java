package org.transinfo.cacis.dataacess.dao.billing;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.dao.BaseDAO;
import org.transinfo.cacis.dto.settings.CardProductRateDto;

@SuppressWarnings("unchecked")
public interface BillingDAO extends BaseDAO {

	public int getCycleNo(int dayOfMonth) throws TPlusException;

	public List getAccountList(int cycleNo) throws TPlusException;

	public List getCreditCardListForAccount(String accountId)
			throws TPlusException;

	public List getTranxRecordsForCards(String joinedCardNos, Date billingDate)
			throws TPlusException;

	public List getFeeRecordsForCards(String joinedCardNos, Date billingDate)
			throws TPlusException;

	public CardProductRateDto getCardProductRateDto(String cardNo)
			throws TPlusException;

	public boolean updateDatabase(String accountID, Map params)
			throws TPlusException;

	public boolean updateTranxFeeRecords(String tranxIds, String feeIds)
			throws TPlusException;

}
