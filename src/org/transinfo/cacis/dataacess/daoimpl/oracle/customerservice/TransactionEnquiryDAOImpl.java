package org.transinfo.cacis.dataacess.daoimpl.oracle.customerservice;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.util.Collection;
import java.util.Date;

import oracle.jdbc.driver.OracleTypes;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.transinfo.cacis.TPlusCodes;
import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.common.constants.CommonCodes;
import org.transinfo.cacis.constants.ICacisiss;
import org.transinfo.cacis.dataacess.HibernetDAO.HibernetFactory;
import org.transinfo.cacis.dataacess.dao.customerservice.TransactionEnquiryDAO;
import org.transinfo.cacis.dataacess.daoimpl.oracle.BaseDAOImpl;
import org.transinfo.cacis.dto.cardproduction.CardsDto;
import org.transinfo.cacis.dto.csr.TransactionLogDto;
import org.transinfo.cacis.dto.csr.TranxRevertDto;
import org.transinfo.cacis.dto.customerservice.AccountAdjustmentDto;
import org.transinfo.cacis.dto.customerservice.TranxEnquiryNonReconTranxlogSearchDto;
import org.transinfo.cacis.dto.customerservice.TranxEnquirySearchDto;
import org.transinfo.cacis.dto.disputemanagement.DisputeManualReconDto;

@SuppressWarnings("unchecked")
public class TransactionEnquiryDAOImpl extends BaseDAOImpl implements
		TransactionEnquiryDAO {

	public TranxEnquirySearchDto search(
			TranxEnquirySearchDto objTranxEnquirySearchDto, int pageNo)
			throws TPlusException {

		Collection objSearchCollection = null;
		int totalCount = 0;

		StringBuffer sbf = new StringBuffer();
		StringBuffer sbfSelect = new StringBuffer();
		StringBuffer sbfCount = new StringBuffer();

		try {

			sbfSelect.append("select ");
			sbfSelect.append("tldto.tranxLogId, ");
			sbfSelect.append("to_char(tldto.dateTime, '" + ICacisiss.IDateStuff.DATETIME_FORMAT_DISPLAY + "'), ");
			sbfSelect.append("tldto.cardNumber, tldto.tranxCode, tldto.responseCode, ");
			sbfSelect.append("tldto.cardHolderTranxAmt, tldto.merchantId, ");
			sbfSelect.append("tldto.approvalCode, tldto.referenceNo, ");
			sbfSelect.append("tldto.recon,tldto.cardHolderTranxCurr,resdto.reason, tldto.clearAmount, tldto.tranxCurrCovAmt ");
			//sbfSelect.append(",tldto.currConvFee, to_number(tldto.clearAmount)+tldto.currConvFee ");
			sbfSelect.append(",tldto.currConvFee, tldto.billedAmt, tldto.currencyCode ");

			sbfCount.append("select ");
			sbfCount.append("count(tldto.tranxLogId) ");

			sbf.append("from TransactionLogDto tldto, CardsDto cdto, ResponseCodeDto resdto ");
			sbf.append("where tldto.cardNumber = cdto.cardNumber ");
			sbf.append("and resdto.responseCode = tldto.responseCode ");

			if (objTranxEnquirySearchDto.getCardNo() != null
					&& !objTranxEnquirySearchDto.getCardNo().equals("")) {
				sbf.append("and tldto.cardNumber = "
						+ objTranxEnquirySearchDto.getCardNo() + " ");
			}

			if (objTranxEnquirySearchDto.getTerminalId() != null
					&& !objTranxEnquirySearchDto.getTerminalId().equals("")) {
				sbf.append("and tldto.terminalId = '"
						+ objTranxEnquirySearchDto.getTerminalId() + "' ");
			}

			if (objTranxEnquirySearchDto.getAuthCode() != null
					&& !objTranxEnquirySearchDto.getAuthCode().equals("")) {
				sbf.append("and tldto.approvalCode = '"
						+ objTranxEnquirySearchDto.getAuthCode() + "' ");
			}

			if (objTranxEnquirySearchDto.getRefNo() != null
					&& !objTranxEnquirySearchDto.getRefNo().equals("")) {
				sbf.append("and tldto.referenceNo = '"
						+ objTranxEnquirySearchDto.getRefNo() + "' ");
			}

			if (objTranxEnquirySearchDto.getResponseCode() != null
					&& !objTranxEnquirySearchDto.getResponseCode().equals("")) {
				sbf.append("and tldto.responseCode = '"
						+ objTranxEnquirySearchDto.getResponseCode() + "' ");
			}

			if (objTranxEnquirySearchDto.getRecon() != null
					&& !objTranxEnquirySearchDto.getRecon().equals("")) {
				sbf.append("and tldto.recon = '"
						+ objTranxEnquirySearchDto.getRecon() + "' ");
				sbf.append("and cdto.cardProductsDto.cardProductType.cardProductTypeId <> '3' ");
			}

			if (objTranxEnquirySearchDto.getStartDate() != null
					&& !objTranxEnquirySearchDto.getStartDate().equals("")) {
				sbf.append("and tldto.dateTime >= TO_DATE('"
						+ objTranxEnquirySearchDto.getStartDate() + " "
						+ ICacisiss.IDateStuff.START_TIME_PART + "', '"
						+ ICacisiss.IDateStuff.DATETIME_FORMAT_VALIDATION
						+ "') ");
			}

			if (objTranxEnquirySearchDto.getEndDate() != null
					&& !objTranxEnquirySearchDto.getEndDate().equals("")) {
				sbf.append("and tldto.dateTime <= TO_DATE('"
						+ objTranxEnquirySearchDto.getEndDate() + " "
						+ ICacisiss.IDateStuff.END_TIME_PART + "', '"
						+ ICacisiss.IDateStuff.DATETIME_FORMAT_VALIDATION
						+ "') ");
			}

			sbf.append("order by tldto.tranxLogId desc");

			objSearchCollection = getSearchTranxList((sbfSelect.append(sbf))
					.toString(), pageNo);
			objTranxEnquirySearchDto.setSearchList(objSearchCollection);

			totalCount = getSearchTotalCount((sbfCount.append(sbf)).toString());
			objTranxEnquirySearchDto.setTotalCount(totalCount);

		} catch (Exception e) {
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error:in TransactionEnquiryDAOImpl search method" + e);
		}

		return objTranxEnquirySearchDto;
	}
	

	public TranxEnquiryNonReconTranxlogSearchDto searchNonReconTranx(
			TranxEnquiryNonReconTranxlogSearchDto objTranxEnquirySearchDto, int pageNo)
			throws TPlusException {

		Collection objSearchCollection = null;
		int totalCount = 0;

		StringBuffer sbf = new StringBuffer();
		StringBuffer sbfSelect = new StringBuffer();
		StringBuffer sbfCount = new StringBuffer();

		try {

			sbfSelect.append("select ");
			sbfSelect.append("tldto.tranxLogId, ");
			sbfSelect.append("to_char(tldto.dateTime, '" + ICacisiss.IDateStuff.DATETIME_FORMAT_DISPLAY + "'), ");
			sbfSelect.append("tldto.cardNumber, tldto.tranxCode, tldto.responseCode, ");
			sbfSelect.append("tldto.cardHolderTranxAmt, tldto.merchantId, ");
			sbfSelect.append("tldto.approvalCode, tldto.referenceNo, ");
			sbfSelect.append("tldto.recon,tldto.cardHolderTranxCurr,resdto.reason, tldto.clearAmount, tldto.tranxCurrCovAmt ");

			sbfCount.append("select ");
			sbfCount.append("count(tldto.tranxLogId) ");

			sbf.append("from TransactionLogDto tldto, ResponseCodeDto resdto ");
			sbf.append("where resdto.responseCode = tldto.responseCode ");
			sbf.append("and tldto.cardNumber in (select cdto.cardNumber from CardsDto cdto where cdto.accountId='"+objTranxEnquirySearchDto.getAccountId()+"') ");
			sbf.append("and tldto.responseCode='00' ");
			sbf.append("and tldto.deleted='N' ");
			sbf.append("and tldto.recon='N' ");
			sbf.append("and tldto.amount>0 ");
			sbf.append("and tldto.tranxCode in ('CASH','SALE') ");

			sbf.append("order by tldto.tranxLogId desc");

			objSearchCollection = getSearchTranxList((sbfSelect.append(sbf)).toString(), pageNo);
			objTranxEnquirySearchDto.setSearchList(objSearchCollection);

			totalCount = getSearchTotalCount((sbfCount.append(sbf)).toString());
			objTranxEnquirySearchDto.setTotalCount(totalCount);

		} catch (Exception e) {
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,"Error:in TransactionEnquiryDAOImpl search method" + e);
		}

		return objTranxEnquirySearchDto;
	}

	public TransactionLogDto getTransactionDto(String tranxId)
			throws TPlusException {

		TransactionLogDto objTransactionLogDto = null;

		try {

			Session session = HibernetFactory.currentSession();

			objTransactionLogDto = (TransactionLogDto) session.get(
					TransactionLogDto.class, Long.valueOf(tranxId));

		} catch (Exception e) {
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"TransactionEnquiryDAOImpl getTransactionDto method :" + e);
		} finally {
			HibernetFactory.closeSession();
		}

		return objTransactionLogDto;
	}

	public DisputeManualReconDto getDisputeManualReconDto(String tranxId)
			throws TPlusException {

		DisputeManualReconDto objDisputeManualReconDto = null;

		try {

			Session session = HibernetFactory.currentSession();

			objDisputeManualReconDto = (DisputeManualReconDto) session.get(
					DisputeManualReconDto.class, Long.valueOf(tranxId));

		} catch (Exception e) {
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"TransactionEnquiryDAOImpl getDisputeManualReconDto method :"
							+ e);
		} finally {
			HibernetFactory.closeSession();
		}

		return objDisputeManualReconDto;
	}

	public boolean saveManualRecon(
			DisputeManualReconDto objDisputeManualReconDto)
			throws TPlusException {

		boolean boolInsert = false;
		Transaction tx = null;
		int count = 0;

		try {

			Session session = HibernetFactory.currentSession();

			tx = session.beginTransaction();
			session.save(objDisputeManualReconDto);

			String cardsql = "UPDATE TransactionLogDto SET recon=:reconStatus, reconDate=:rDate WHERE tranxLogId=:transactionId";
			count = session.createQuery(cardsql)
							.setLong("transactionId", objDisputeManualReconDto.getTranxlogId())
							.setTimestamp("rDate", new Date())
							.setString("reconStatus", CommonCodes.RECON_MANUAL)
							.executeUpdate();

			if (count > 0) {
				tx.commit();
			}

			boolInsert = true;

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"TransactionEnquiryDAOImpl saveManualRecon  method :" + e);
		} finally {
			HibernetFactory.closeSession();
		}

		return boolInsert;
	}
	
	public boolean TranxRevert(String tranxId, String remarks, String user)
	throws TPlusException {

		boolean boolInsert = false;
		Transaction tx = null;
		int count = 0;

		try {

			Session session = HibernetFactory.currentSession();

			tx = session.beginTransaction();

			TransactionLogDto objTransactionLogDto = (TransactionLogDto) session.get(TransactionLogDto.class, Long.valueOf(tranxId));
			
			CardsDto objCardsDto = (CardsDto) session.get(CardsDto.class, objTransactionLogDto.getCardNumber());
			//CustomerAccountDto objCustomerAccountDto = (CustomerAccountDto) session.get(CustomerAccountDto.class, objCardsDto.getAccountId());
			//CustomerAccountDto objCustomerAccountDto = objCardsDto.getCustAccountDto();
			
			TranxRevertDto objTranxRevertDto = new TranxRevertDto();
			objTranxRevertDto.setTranxLogId(tranxId);
			objTranxRevertDto.setDoneBy(user);
			objTranxRevertDto.setRemarks(remarks);
			objTranxRevertDto.setDoneWhen(new Date());
			objTranxRevertDto.setCardNo(String.valueOf(objTransactionLogDto.getCardNumber()));
			
			session.save(objTranxRevertDto);
			
			double revertAmt = Double.valueOf(objTransactionLogDto.getTranxCurrCovAmt()).doubleValue();
			BigDecimal bigrevertAmt = BigDecimal.valueOf(revertAmt);
			bigrevertAmt = bigrevertAmt.setScale(2, BigDecimal.ROUND_HALF_EVEN);
			
			Connection con = session.connection();
			CallableStatement cs = con.prepareCall("call CARDLIMITUSED(?,?,?,?,?,?,?,?,?)");

			cs.setLong(1, objCardsDto.getCardNumber());
			cs.setString(2, "-");
			cs.setString(3, objTransactionLogDto.getTranxCode());
			cs.setDouble(4, bigrevertAmt.doubleValue());
			cs.registerOutParameter(5, OracleTypes.DOUBLE);
			cs.registerOutParameter(6, OracleTypes.DOUBLE);
			cs.registerOutParameter(7, OracleTypes.DOUBLE);
			cs.registerOutParameter(8, OracleTypes.DOUBLE);
			cs.registerOutParameter(9, OracleTypes.DOUBLE);
			cs.execute();
			
			Double accPurUsed = cs.getDouble(5);
			Double accCashUsed = cs.getDouble(6);
			Double cardPurUsed = cs.getDouble(7);
			Double cardCashUsed = cs.getDouble(8);
			Double limitUsed = cs.getDouble(9);
			
			// update cards
			objCardsDto.setCashUsed(cardCashUsed);
			objCardsDto.setPurchaseUsed(cardPurUsed);

			session.update(objCardsDto);

			float preLimitUsed = objCardsDto.getCustAccountDto().getLimitUsed();
			BigDecimal bigPreLimitUsed = BigDecimal.valueOf(Double.valueOf(String.valueOf(preLimitUsed)));
			bigPreLimitUsed = bigPreLimitUsed.setScale(2, BigDecimal.ROUND_HALF_EVEN);
			
			double purchaseUsed = objCardsDto.getCustAccountDto().getSaleUsed().doubleValue();
			BigDecimal bigPrePurchaseUsed = BigDecimal.valueOf(purchaseUsed);
			bigPrePurchaseUsed = bigPrePurchaseUsed.setScale(2, BigDecimal.ROUND_HALF_EVEN);

			double cashUsed = objCardsDto.getCustAccountDto().getCashUsed().doubleValue();
			BigDecimal bigPreCashUsed = BigDecimal.valueOf(cashUsed);
			bigPreCashUsed = bigPreCashUsed.setScale(2, BigDecimal.ROUND_HALF_EVEN);

			AccountAdjustmentDto objAccountAdjustmentDto = new AccountAdjustmentDto();
			objAccountAdjustmentDto.setCard(objCardsDto);
			objAccountAdjustmentDto.setCardNo(objCardsDto.getCardNumber());
			objAccountAdjustmentDto.setAdjustmentAmount(String.valueOf(bigrevertAmt.doubleValue()));
			objAccountAdjustmentDto.setCreditOrDebit("C");
			objAccountAdjustmentDto.setEffectiveDate(new Date());
			objAccountAdjustmentDto.setReason("Tranx Revert");
			objAccountAdjustmentDto.setAmtSrc("Admin");
			objAccountAdjustmentDto.setChgType("REV");
			objAccountAdjustmentDto.setUpdatedDate(new Date());
			objAccountAdjustmentDto.setUserId(user);
			objAccountAdjustmentDto.setPreLimitUsed(bigPreLimitUsed.doubleValue());
			objAccountAdjustmentDto.setLimitUsed(limitUsed.doubleValue());
			objAccountAdjustmentDto.setPrePurchaseUsed(bigPrePurchaseUsed.doubleValue());
			objAccountAdjustmentDto.setPreCashUsed(bigPreCashUsed.doubleValue());
			objAccountAdjustmentDto.setPurchaseUsed(accPurUsed);
			objAccountAdjustmentDto.setCashUsed(accCashUsed);

			session.save(objAccountAdjustmentDto);
			
			/*double limitUsed = objCustomerAccountDto.getLimitUsed();
			double settAmt = Double.valueOf(objTransactionLogDto.getSettAmt());
			double updatedlimitused= limitUsed-settAmt;
			
			double cashUsed = objCustomerAccountDto.getCashUsed();
			double saleUsed = objCustomerAccountDto.getSaleUsed();
			
			double updatedtranxused= 0;
			
			String query = "";
			String field="";
			if("CASH".equals(objTransactionLogDto.getTranxCode())){
				query = " cashUsed=:cashUsed ";
				field="cashUsed";
				updatedtranxused = cashUsed-settAmt;
			}else{
				query = " saleUsed=:saleUsed ";
				field="saleUsed";
				updatedtranxused = saleUsed-settAmt;
			}*/
			
			String cardsql = "UPDATE TransactionLogDto SET deleted=:deletedStatus WHERE tranxLogId=:transactionId";
			count = session.createQuery(cardsql)
							.setLong("transactionId", Long.valueOf(tranxId))
							.setString("deletedStatus", CommonCodes.TRANX_REVERT)
							.executeUpdate();
			
			/*cardsql = "UPDATE CustomerAccountDto SET limitUsed=:limitused,"+query+" WHERE accountId=:accountId";
			count = session.createQuery(cardsql)
							.setString("accountId", objCardsDto.getAccountId())
							.setDouble("limitused", updatedlimitused)
							.setDouble(field, updatedtranxused)
							.executeUpdate();*/
			
			String sql = "UPDATE CustomerAccountDto SET limitUsed="+limitUsed.doubleValue()+", saleUsed="+accPurUsed.doubleValue()+", cashUsed="+accCashUsed.doubleValue()+" WHERE accountId=:accountId";
			count = session.createQuery(sql)
			.setString("accountId", objCardsDto.getAccountId())
			.executeUpdate();

			if (count > 0) {
				tx.commit();
			}

			boolInsert = true;

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"TransactionEnquiryDAOImpl TranxRevert  method :" + e);
		} finally {
			HibernetFactory.closeSession();
		}

		return boolInsert;
	}

}
