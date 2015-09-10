package org.transinfo.cacis.dataacess.daoimpl.oracle.customerservice;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.transinfo.cacis.TPlusCodes;
import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.HibernetDAO.HibernetFactory;
import org.transinfo.cacis.dataacess.dao.customerservice.PayWaiverDAO;
import org.transinfo.cacis.dataacess.daoimpl.oracle.BaseDAOImpl;
import org.transinfo.cacis.dto.accounting.CustomerPayment;
import org.transinfo.cacis.dto.cardproduction.CardsDto;
import org.transinfo.cacis.dto.customerservice.FeeWaiver;
import org.transinfo.cacis.dto.transaction.CustomerFeeDto;
import org.transinfo.cacis.dto.transaction.FeeDebitGLDto;
import org.transinfo.cacis.util.DBUtil;
import org.transinfo.cacis.util.DateUtil;

@SuppressWarnings("unchecked")
public class PayWaiverDAOImpl extends BaseDAOImpl implements PayWaiverDAO {

	public CardsDto getCardDto(String cardNo) throws TPlusException {

		CardsDto objDto = null;
		//Transaction tx = null;

		try {

			Session session = HibernetFactory.currentSession();
			//tx = session.beginTransaction();
			objDto = (CardsDto) session.get(CardsDto.class, Long.valueOf(cardNo).longValue());

			if(objDto != null){
				objDto.getCardProductsDto().getCardProductType().getCardProductType();
				objDto.getCardProductsDto().getCardType().getCardType();
			}

			//tx.commit();
		}catch (Exception e) {
			/*if (tx != null) {
				tx.rollback();
			}*/
			System.out
			.println("Error while getting CardsDto data in getCardDto method"
					+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while getting CardsDto data in getCardDto method"
					+ e);
		} finally {
			HibernetFactory.closeSession();
		}

		return objDto;
	}

	@Override
	public List getDebitFeeTranx(String cardNo) throws TPlusException {

		List objSearchCollection = new ArrayList();

		try	{

			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.MONTH, 2);

			String strDate = DateUtil.getStrDate("dd/MM/yyyy HH:mm:ss", cal.getTime());

			/*Session session = HibernetFactory.currentSession();

			Query query = session.createQuery("from CustomerFeeDto cf where cf.billed='Y' and cf.FeeDate <= :maxModDate");
			//query.setParameter("maxModDate", cal);
			query.setTimestamp("maxModDate", cal.getTime());
			objSearchCollection = query.list();*/

			StringBuffer stbf = new StringBuffer();
			stbf.append(" select fd.feeDebitGLId, fd.cardNo, to_char(fd.dateTime, 'yyyy-mm-dd'), fd.trnxTypeDes, fd.amount, fd.feeWaived ");
			stbf.append(" from FeeDebitGLDto fd ");
			stbf.append(" where fd.billed='Y' and fd.glType='D' and fd.dateTime <= to_date('"+strDate+"','dd/MM/yyyy HH24:mi:ss') and fd.cardNo='"+cardNo+"' ");

			/*Query qry = session.createQuery(stbf.toString());
			qry.setString("cardproductid", cardNo);*/

			objSearchCollection = getSearchListNoPageNo(stbf.toString());

		}catch (Exception e)
		{
			System.out.println("Error in CardHolderStatementDAOImpl getPaymentTranx method : "+e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,"Error: in CardHolderStatementDAOImpl getPaymentTranx  method :"+e);
		}

		return  objSearchCollection ;

	}

	public boolean updateDB(String feeId, String userId) throws TPlusException {

		boolean boolAdd = false;
		Transaction tx = null;

		try {

			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();

			CustomerPayment objCustomerPayment = (CustomerPayment) session.get(CustomerPayment.class, feeId);
			objCustomerPayment.setBilled("G");
			objCustomerPayment.setUpdateBy(userId);
			objCustomerPayment.setUpdateDate(new Date());

			double payAmt = objCustomerPayment.getPayAmt().doubleValue();
			Long cardNo = objCustomerPayment.getCardNo();

			session.update(objCustomerPayment);

			String custIntID = DBUtil.getSequenceNextValue("SEQ_CUSTOMER_PAYMENT");

			CustomerPayment objNewCustomerPayment = new CustomerPayment();
			objNewCustomerPayment.setPaymentId(custIntID);
			objNewCustomerPayment.setCardNo(objCustomerPayment.getCardNo());
			objNewCustomerPayment.setAccountId(objCustomerPayment.getAccountId());
			objNewCustomerPayment.setPayMode(objCustomerPayment.getPayMode());
			objNewCustomerPayment.setPayAmt(objCustomerPayment.getPayAmt());
			objNewCustomerPayment.setPaySrc(objCustomerPayment.getPaySrc());
			objNewCustomerPayment.setAmtSign("D");
			objNewCustomerPayment.setPayDate(new Date());
			objNewCustomerPayment.setBilled("G");
			objNewCustomerPayment.setUpdateDate(new Date());
			objNewCustomerPayment.setUpdateBy(userId);
			objNewCustomerPayment.setFullpay(objCustomerPayment.getFullpay());
			objNewCustomerPayment.setPayRefNo(objCustomerPayment.getPayRefNo());

			session.save(objNewCustomerPayment);

			CardsDto objCardsDto = (CardsDto) session.get(CardsDto.class, cardNo.longValue());

			String sql = "UPDATE CustomerAccountDto SET limitUsed =limitUsed+"+payAmt+", saleUsed=saleUsed+"+payAmt+" WHERE accountId=:accountId";
			int count = session.createQuery(sql)
			.setString("accountId", objCardsDto.getAccountId())
			.executeUpdate();

			if(count > 0){
				tx.commit();
				boolAdd = true;
			}

		}

		catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			System.out.println("Error in CardChangeDAOImpl add method : "
					+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in CardChangeDAOImpl add  method :" + e);
		} finally {
			HibernetFactory.closeSession();
		}
		return boolAdd;

	}

	public boolean updateDBDebit(String feeId, String userId) throws TPlusException {

		boolean boolAdd = false;
		Transaction tx = null;

		try {

			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();

			String feewaiverSNO = DBUtil.getSequenceNextValue("SEQ_FEE_WAIVER");

			FeeDebitGLDto objFeeDebitGLDto = (FeeDebitGLDto) session.get(FeeDebitGLDto.class, feeId);
			objFeeDebitGLDto.setRefNo(feewaiverSNO);
			objFeeDebitGLDto.setFeeWaived("Y");

			session.update(objFeeDebitGLDto);

			FeeDebitGLDto objNewDebitGLDto = new FeeDebitGLDto();
			objNewDebitGLDto.setDateTime(new Date());
			objNewDebitGLDto.setCardNo(objFeeDebitGLDto.getCardNo());
			objNewDebitGLDto.setTrnxType(objFeeDebitGLDto.getTrnxType());
			objNewDebitGLDto.setGlType("C");
			objNewDebitGLDto.setAmount(objFeeDebitGLDto.getAmount());
			objNewDebitGLDto.setBalance(objFeeDebitGLDto.getBalance());
			objNewDebitGLDto.setRefNo(feewaiverSNO);
			objNewDebitGLDto.setBilled("N");
			objNewDebitGLDto.setFeeWaived("N");

			session.save(objNewDebitGLDto);

			FeeWaiver objFeeWaiver = new FeeWaiver();
			objFeeWaiver.setSno(feewaiverSNO);
			objFeeWaiver.setCardNo(objFeeDebitGLDto.getCardNo());
			objFeeWaiver.setFeeType("FW");
			objFeeWaiver.setFeeRef(objNewDebitGLDto.getFeeDebitGLId());
			objFeeWaiver.setAmount(objFeeDebitGLDto.getAmount());
			objFeeWaiver.setCreatedBY(userId);
			objFeeWaiver.setCreatedDate(new Date());

			session.save(objFeeWaiver);

			tx.commit();
			boolAdd = true;

		}

		catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			System.out.println("Error in CardChangeDAOImpl add method : "
					+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in CardChangeDAOImpl add  method :" + e);
		} finally {
			HibernetFactory.closeSession();
		}
		return boolAdd;

	}

	@Override
	public CustomerFeeDto getCustomerFee(String feeId) throws TPlusException {

		CustomerFeeDto objDto = null;

		try {

			Session session = HibernetFactory.currentSession();
			objDto = (CustomerFeeDto) session.get(CustomerFeeDto.class, feeId);

		} catch (Exception e) {
			System.out.println("Error in CardProductDAOImpl getCardProductDto method : " + e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR, "Error: in CardProductDAOImpl getCardProductDto  method :" + e);
		} finally {
			HibernetFactory.closeSession();
		}

		return objDto;

	}

	@Override
	public List getIntTranx(String cardNo) throws TPlusException {

		List objSearchCollection = new ArrayList();

		try	{

			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.MONTH, 2);

			//String strDate = DateUtil.getStrDate("dd/MM/yyyy HH:mm:ss", cal.getTime());

			StringBuffer stbf = new StringBuffer();
			stbf.append(" select cp.paymentId, cp.cardNo, to_char(cp.payDate, 'yyyy-mm-dd'), p.payDesc, cp.payAmt ");
			stbf.append(" from CustomerPayment cp, Payment p ");
			stbf.append(" where cp.payMode=p.payMode and cp.billed='N' ");
			stbf.append(" and cp.cardNo="+cardNo+" ");

			objSearchCollection = getSearchListNoPageNo(stbf.toString());

		}catch (Exception e)
		{
			System.out.println("Error in CardHolderStatementDAOImpl getPaymentTranx method : "+e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,"Error: in CardHolderStatementDAOImpl getPaymentTranx  method :"+e);
		}

		return  objSearchCollection ;

	}

}
