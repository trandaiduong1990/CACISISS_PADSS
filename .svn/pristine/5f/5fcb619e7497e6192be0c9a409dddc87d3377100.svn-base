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
import org.transinfo.cacis.dataacess.dao.customerservice.FeeWaiverDAO;
import org.transinfo.cacis.dataacess.daoimpl.oracle.BaseDAOImpl;
import org.transinfo.cacis.dto.cardproduction.CardsDto;
import org.transinfo.cacis.dto.customerservice.FeeWaiver;
import org.transinfo.cacis.dto.transaction.CustomerFeeDto;
import org.transinfo.cacis.dto.transaction.FeeDebitGLDto;
import org.transinfo.cacis.util.DBUtil;
import org.transinfo.cacis.util.DateUtil;

@SuppressWarnings("unchecked")
public class FeeWaiverDAOImpl extends BaseDAOImpl implements FeeWaiverDAO {

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
			
			String feewaiverSNO = DBUtil.getSequenceNextValue("SEQ_FEE_WAIVER");
			
			CustomerFeeDto objCustomerFeeDto = (CustomerFeeDto) session.get(CustomerFeeDto.class, feeId);
			objCustomerFeeDto.setFeeSrc(feewaiverSNO);
			objCustomerFeeDto.setFeeWaived("Y");
			
			session.update(objCustomerFeeDto);
			
			CustomerFeeDto objNewCustomerFeeDto = new CustomerFeeDto();
			objNewCustomerFeeDto.setCardNo(objCustomerFeeDto.getCardNo());
			objNewCustomerFeeDto.setAccountId(objCustomerFeeDto.getAccountId());
			objNewCustomerFeeDto.setRefId(objCustomerFeeDto.getRefId());
			objNewCustomerFeeDto.setFeeAmt(objCustomerFeeDto.getFeeAmt());
			objNewCustomerFeeDto.setAmountSign("C");
			objNewCustomerFeeDto.setFeeDate(new Date());
			objNewCustomerFeeDto.setFeeSrc(feewaiverSNO);
			objNewCustomerFeeDto.setBilled("N");
			objNewCustomerFeeDto.setCreatedDate(new Date());
			objNewCustomerFeeDto.setCreatedBy(userId);
			objNewCustomerFeeDto.setTranxLogId(objCustomerFeeDto.getTranxLogId());
			objNewCustomerFeeDto.setFeeWaived("N");
			
			session.save(objNewCustomerFeeDto);
			
			FeeWaiver objFeeWaiver = new FeeWaiver();
			objFeeWaiver.setSno(feewaiverSNO);
			objFeeWaiver.setCardNo(String.valueOf(objCustomerFeeDto.getCardNo()));
			objFeeWaiver.setFeeType("FW");
			objFeeWaiver.setFeeRef(objNewCustomerFeeDto.getCustomerFeeId());
			objFeeWaiver.setAmount(String.valueOf(objCustomerFeeDto.getFeeAmt()));
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
	public List getFeeTranx(String cardNo) throws TPlusException {

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
			stbf.append(" select cf.customerFeeId, cf.cardNo, to_char(cf.FeeDate, 'yyyy-mm-dd'), f.feeDesc, cf.feeAmt, cf.feeWaived ");
			stbf.append(" from CustomerFeeDto cf, Fee f ");
			stbf.append(" where cf.refId=f.feeId and cf.billed='Y' and cf.amountSign='D' and cf.FeeDate <= to_date('"+strDate+"','dd/MM/yyyy HH24:mi:ss') ");
			stbf.append(" and cf.cardNo="+cardNo+" ");

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

}
