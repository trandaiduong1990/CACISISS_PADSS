package org.transinfo.cacis.dataacess.dao.accounting;

import java.util.List;

import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.dao.BaseDAO;
import org.transinfo.cacis.dto.accounting.CardHoderStatementDto;
import org.transinfo.cacis.dto.accounting.CurrPaySumDto;
import org.transinfo.cacis.dto.accounting.CustomerStatement;

@SuppressWarnings("unchecked")
public interface CardHolderStatementDAO extends BaseDAO
{

	public int search(CardHoderStatementDto objDto)throws TPlusException;

	public List getPostTranx(String cardNo) throws TPlusException;

	public List getPaymentTranx(String cardNo) throws TPlusException;

	public List getFeeTranx(String cardNo) throws TPlusException;
	
	public Double getTotFeeAmt(String cardNo) throws TPlusException;
	
	public CustomerStatement getCurrentStatement(String cardNo) throws TPlusException;
	
	public CurrPaySumDto getCurrPaySum(String cardNo) throws TPlusException;
	
	public List getStatementTranx(String statId) throws TPlusException;
	
	public List getStatPayment(String statId) throws TPlusException;
	
	public List getStatInterest(String statId) throws TPlusException;
	
	public List getStatFee(String statId) throws TPlusException;
	
	public CustomerStatement getPreviousStatement(String cardNo) throws TPlusException;

	public List getPaymentTranxRefund(String cardNo) throws TPlusException;

	public List getStatPaymentRefund(String statId) throws TPlusException;
	
}