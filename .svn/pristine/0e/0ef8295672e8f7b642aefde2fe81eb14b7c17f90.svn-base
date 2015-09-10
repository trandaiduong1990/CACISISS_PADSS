package org.transinfo.cacis.controller.accounting;

import java.util.ArrayList;
import java.util.List;

import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.accounting.CardHolderStatementDAO;
import org.transinfo.cacis.dto.accounting.CardHoderStatementDto;
import org.transinfo.cacis.dto.accounting.CurrPaySumDto;
import org.transinfo.cacis.dto.accounting.CustomerStatement;

@SuppressWarnings("unchecked")
public class CardHolderStatementManager {

	private CardHolderStatementDAO objDAO;

	public CardHolderStatementManager()throws Exception {
		objDAO = DAOFactory.getInstance().getCardHolderStatementDAO();

	}

	public int search(CardHoderStatementDto objDto) throws TPlusException {

		int res = 0;

		try {
			
			res = objDAO.search(objDto);

		} catch (Exception e) {
			System.out.println("Error in CardHolderStatementManager search method"+e.getMessage());
			throw new TPlusException("Error in CardHolderStatementManager search method:" +e);
		}
		
		return res;

	}

	public List getPostTranx(String cardNo) throws TPlusException {

		List postTranxList = null;

		try {
			
			postTranxList = objDAO.getPostTranx(cardNo);

		} catch (Exception e) {
			throw new TPlusException( "Error in CardHolderStatementManager getPostTranx method" + e);
		}
		
		return postTranxList;

	}

	public List getPaymentTranx(String cardNo) throws TPlusException {

		List payTranxList = new ArrayList();

		try {
			
			List payTranxListNRF = objDAO.getPaymentTranx(cardNo);

			List payTranxListRF = objDAO.getPaymentTranxRefund(cardNo);
			
			payTranxList.addAll(payTranxListNRF);
			payTranxList.addAll(payTranxListRF);

		} catch (Exception e) {
			throw new TPlusException( "Error in CardHolderStatementManager getPaymentTranx method" + e);
		}
		
		return payTranxList;

	}

	public List getFeeTranx(String cardNo) throws TPlusException {

		List feeTranxList = null;

		try {
			
			feeTranxList = objDAO.getFeeTranx(cardNo);

		} catch (Exception e) {
			throw new TPlusException( "Error in CardHolderStatementManager getFeeTranx method" + e);
		}
		
		return feeTranxList;

	}
	
	public Double getTotFeeAmt(String cardNo)
			throws TPlusException {

		Double amt = 0.0;

		try {
			amt = objDAO.getTotFeeAmt(cardNo);
		} catch (Exception e) {
			throw new TPlusException(
					"Error in CardHolderStatementManager getTotFeeAmt method"
							+ e);
		}

		return amt;
	}

	public CustomerStatement getCurrentStatement(String cardNo) throws TPlusException {

		CustomerStatement objCustomerStatement = null;

		try {
			
			objCustomerStatement = objDAO.getCurrentStatement(cardNo);

		} catch (Exception e) {
			throw new TPlusException( "Error in CardHolderStatementManager getCurrentStatement method" + e);
		}
		
		return objCustomerStatement;

	}

	public CurrPaySumDto getCurrPaySum(String cardNo) throws TPlusException {

		CurrPaySumDto objCurrPaySumDto = null;

		try {
			
			objCurrPaySumDto = objDAO.getCurrPaySum(cardNo);

		} catch (Exception e) {
			throw new TPlusException( "Error in CardHolderStatementManager getCurrPaySum method" + e);
		}
		
		return objCurrPaySumDto;

	}

	public List getStatementTranx(String statId) throws TPlusException {

		List statTranxList = null;

		try {
			
			statTranxList = objDAO.getStatementTranx(statId);

		} catch (Exception e) {
			throw new TPlusException( "Error in CardHolderStatementManager getStatementTranx method" + e);
		}
		
		return statTranxList;

	}

	public List getStatPayment(String statId) throws TPlusException {

		List statPayList = new ArrayList();

		try {
			
			List statPayListNRF = objDAO.getStatPayment(statId);

			List statPayListRF = objDAO.getStatPaymentRefund(statId);
			
			statPayList.addAll(statPayListNRF);
			statPayList.addAll(statPayListRF);

		} catch (Exception e) {
			throw new TPlusException( "Error in CardHolderStatementManager getStatPayment method" + e);
		}
		
		return statPayList;

	}

	public List getStatInterest(String statId) throws TPlusException {

		List statIntList = null;

		try {
			
			statIntList = objDAO.getStatInterest(statId);

		} catch (Exception e) {
			throw new TPlusException( "Error in CardHolderStatementManager getStatInterest method" + e);
		}
		
		return statIntList;

	}

	public List getStatFee(String statId) throws TPlusException {

		List statFeeList = null;

		try {
			
			statFeeList = objDAO.getStatFee(statId);

		} catch (Exception e) {
			throw new TPlusException( "Error in CardHolderStatementManager getStatFee method" + e);
		}
		
		return statFeeList;

	}

	public CustomerStatement getPreviousStatement(String cardNo) throws TPlusException {

		CustomerStatement objCustomerStatement = null;

		try {
			
			objCustomerStatement = objDAO.getPreviousStatement(cardNo);

		} catch (Exception e) {
			throw new TPlusException( "Error in CardHolderStatementManager getPreviousStatement method" + e);
		}
		
		return objCustomerStatement;

	}
	
}
