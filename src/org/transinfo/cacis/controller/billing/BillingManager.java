package org.transinfo.cacis.controller.billing;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.billing.BillingDAO;
import org.transinfo.cacis.dto.settings.CardProductRateDto;

@SuppressWarnings("unchecked")
public class BillingManager {

	private static final Logger billLog = Logger.getLogger("BillLog");

	private BillingDAO objBillingDAO;

	public BillingManager() throws Exception {
		objBillingDAO = DAOFactory.getInstance().getBillingDAO();
	}

	public int getCycleNo(int dayOfMonth) throws TPlusException {
		int cycleNo = -1;

		try {
			cycleNo = objBillingDAO.getCycleNo(dayOfMonth);

		} catch (Exception e) {
			billLog.error(e);
			throw new TPlusException(
					"Error in BillingManager getCycleNo method" + e);
		}

		return cycleNo;
	}

	public List getAccountList(int cycleNo) throws TPlusException {
		List accountList = null;

		try {
			accountList = objBillingDAO.getAccountList(cycleNo);

		} catch (Exception e) {
			billLog.error(e);
			throw new TPlusException(
					"Error in BillingManager getAccountList method" + e);
		}

		return accountList;
	}

	public List getCreditCardListForAccount(String accountId)
			throws TPlusException {
		List creditCardList = null;

		try {
			creditCardList = objBillingDAO
					.getCreditCardListForAccount(accountId);

		} catch (Exception e) {
			billLog.error(e);
			throw new TPlusException(
					"Error in BillingManager getCreditCardListForAccount method"
							+ e);
		}

		return creditCardList;
	}

	public List getTranxRecordsForCards(String joinedCardNos, Date billingDate)
			throws TPlusException {
		List tranxRecordsList = null;

		try {
			tranxRecordsList = objBillingDAO.getTranxRecordsForCards(
					joinedCardNos, billingDate);

		} catch (Exception e) {
			billLog.error(e);
			throw new TPlusException(
					"Error in BillingManager getTranxRecordsForCards method"
							+ e);
		}

		return tranxRecordsList;
	}

	public List getFeeRecordsForCards(String joinedCardNos, Date billingDate)
			throws TPlusException {
		List feesRecordsList = null;

		try {
			feesRecordsList = objBillingDAO.getFeeRecordsForCards(
					joinedCardNos, billingDate);

		} catch (Exception e) {
			billLog.error(e);
			throw new TPlusException(
					"Error in BillingManager getFeeRecordsForCards method" + e);
		}

		return feesRecordsList;
	}

	public CardProductRateDto getCardProductRateDto(String cardNo)
			throws TPlusException {
		CardProductRateDto objCardProductRateDto = null;

		try {
			objCardProductRateDto = objBillingDAO.getCardProductRateDto(cardNo);

		} catch (Exception e) {
			billLog.error(e);
			throw new TPlusException(
					"Error in BillingManager getCardProductRateDto method" + e);
		}

		return objCardProductRateDto;
	}

	public boolean updateDatabase(String accountID, Map params)
			throws TPlusException {
		boolean resOfUpdate = false;

		try {
			resOfUpdate = objBillingDAO.updateDatabase(accountID, params);
		} catch (Exception e) {
			billLog.error(e);
			throw new TPlusException(
					"Error in BillingManager updateDatabase method" + e);
		}

		return resOfUpdate;
	}

	public boolean updateTranxFeeRecords(String tranxIds, String feeIds)
			throws TPlusException {
		boolean resOfUpdate = false;

		try {
			resOfUpdate = objBillingDAO.updateTranxFeeRecords(tranxIds, feeIds);
		} catch (Exception e) {
			billLog.error(e);
			throw new TPlusException(
					"Error in BillingManager updateTranxFeeRecords method" + e);
		}

		return resOfUpdate;
	}

}
