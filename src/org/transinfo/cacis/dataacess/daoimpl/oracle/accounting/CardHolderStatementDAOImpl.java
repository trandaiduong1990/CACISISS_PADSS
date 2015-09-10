package org.transinfo.cacis.dataacess.daoimpl.oracle.accounting;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.transinfo.cacis.TPlusCodes;
import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.HibernetDAO.HibernetFactory;
import org.transinfo.cacis.dataacess.dao.accounting.CardHolderStatementDAO;
import org.transinfo.cacis.dataacess.daoimpl.oracle.BaseDAOImpl;
import org.transinfo.cacis.dto.accounting.CardHoderStatementDto;
import org.transinfo.cacis.dto.accounting.CurrPaySumDto;
import org.transinfo.cacis.dto.accounting.CustomerStatement;
import org.transinfo.cacis.dto.cardproduction.CardsDto;

@SuppressWarnings("unchecked")
public class CardHolderStatementDAOImpl extends BaseDAOImpl implements  CardHolderStatementDAO {

	public int search(CardHoderStatementDto objDto)  throws TPlusException {

		int res = 0;

		try	{

			Session session = HibernetFactory.currentSession();

			if(objDto.getCardNumber()!=null){

				StringBuffer stbf = new StringBuffer();
				stbf.append(" select count(*) from CustomerStatement cs where cs.cardNo=:cardNumber and cs.statGen='Y' ");

				List resultList = session.createQuery(stbf.toString()).setLong("cardNumber", Long.valueOf(objDto.getCardNumber()).longValue()).list();

				for (Iterator it = resultList.iterator(); it.hasNext();) {
					res = (Integer) it.next();
				}

			}

		}catch (Exception e)
		{
			System.out.println("Error in CardHolderStatementDAOImpl search method : "+e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,"Error: in CardHolderStatementDAOImpl search  method :"+e);
		}

		return  res ;
	}

	@Override
	public List getPostTranx(String cardNo) throws TPlusException {

		List objSearchCollection = new ArrayList();

		try	{

			//Session session = HibernetFactory.currentSession();

			StringBuffer stbf = new StringBuffer();
			//stbf.append(" select to_char(pt.postDate, 'yyyy-mm-dd'), tl.merchantName, tl.tranxCurrCovAmt, pt.tranxLogId, tl.recon, tl.clearAmount, tl.currConvFee ");
			stbf.append(" select to_char(pt.postDate, 'yyyy-mm-dd'), tl.merchantName, tl.tranxCurrCovAmt, pt.tranxLogId, tl.recon, cast(tl.clearAmount as double)+tl.currConvFee ");
			stbf.append(" from PostTranx pt, TransactionLogDto tl ");
			stbf.append(" where pt.tranxLogId = tl.tranxLogId and pt.billed='N' and pt.cardNo="+cardNo+" ");
			stbf.append(" order by pt.postDate desc ");

			/*Query qry = session.createQuery(stbf.toString());
			qry.setString("cardproductid", cardNo);*/

			objSearchCollection = getSearchListNoPageNo(stbf.toString());

		}catch (Exception e)
		{
			System.out.println("Error in CardHolderStatementDAOImpl getPostTranx method : "+e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,"Error: in CardHolderStatementDAOImpl getPostTranx  method :"+e);
		}

		return  objSearchCollection ;

	}

	@Override
	public List getPaymentTranx(String cardNo) throws TPlusException {

		List objSearchCollection = new ArrayList();

		try	{

			//Session session = HibernetFactory.currentSession();

			StringBuffer stbf = new StringBuffer();
			stbf.append(" select to_char(cp.payDate, 'yyyy-mm-dd'), p.payDesc, cp.payAmt, cp.amtSign, '' ");
			stbf.append(" from CustomerPayment cp, Payment p ");
			stbf.append(" where p.payMode = cp.payMode and cp.billed='N' and cp.payMode not in ('MR','REV') and cp.cardNo="+cardNo+" ");

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

	@Override
	public List getPaymentTranxRefund(String cardNo) throws TPlusException {

		List objSearchCollection = new ArrayList();

		try	{

			//Session session = HibernetFactory.currentSession();

			StringBuffer stbf = new StringBuffer();
			stbf.append(" select to_char(cp.payDate, 'yyyy-mm-dd'), p.payDesc, cp.payAmt, cp.amtSign, concat(ntl.merchantName,' ' , ntl.merchantCountryCode) as merchantDet ");
			stbf.append(" from CustomerPayment cp, Payment p, NonReconTransactionLogDto ntl ");
			stbf.append(" where p.payMode = cp.payMode and cp.billed='N' and cp.payMode in ('MR','REV') and ntl.tranxNonReconId=cp.payRefNo and cp.cardNo="+cardNo+" ");

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
	
	public Double getTotFeeAmt(String cardNo) throws TPlusException{

		Double amt = 0.0;
		
		StringBuffer sbf = new StringBuffer();
		
		try {

			Session session = HibernetFactory.currentSession();
			sbf.append("select sum(cf.feeAmt) ");
			sbf.append("from CustomerFeeDto cf ");
			sbf.append("where cf.billed='N' and cf.cardNo="+cardNo+" ");

			Query qry = session.createQuery(sbf.toString());
			List listCards = qry.list();

			if (listCards.size() > 0) {
				
				if((Float) listCards.get(0) != null){
					amt = Double.valueOf((Float) listCards.get(0));
					
					BigDecimal bigAmt = BigDecimal.valueOf(amt);
					bigAmt = bigAmt.setScale(2, BigDecimal.ROUND_HALF_EVEN);
					
					
					amt = bigAmt.doubleValue();
					
				}
			}

		} catch (Exception e) {
			System.out.println("Error in while retrieving data in CustomerScreenProcessDAOImpl search method" + e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR, "Error: while retrieving data in CustomerScreenProcessDAOImpl in search method" + e);
		} finally {
			HibernetFactory.closeSession();
		}

		return amt;
	}

	@Override
	public List getFeeTranx(String cardNo) throws TPlusException {

		List objSearchCollection = new ArrayList();

		try	{

			//Session session = HibernetFactory.currentSession();

			StringBuffer stbf = new StringBuffer();
			stbf.append(" select to_char(cf.FeeDate, 'yyyy-mm-dd'), f.feeDesc, cf.feeAmt, cf.tranxLogId ");
			stbf.append(" from CustomerFeeDto cf, Fee f ");
			stbf.append(" where f.feeId = cf.refId and cf.billed='N' and cf.cardNo="+cardNo+" ");

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

	@Override
	public CustomerStatement getCurrentStatement(String cardNo) throws TPlusException {

		CustomerStatement objCustomerStatement = null;

		try	{

			Session session = HibernetFactory.currentSession();
			
			CardsDto objDto = (CardsDto) session.get(CardsDto.class, Long.valueOf(cardNo));
			objCustomerStatement = (CustomerStatement) session.get(CustomerStatement.class, objDto.getLastStatementId());

		}catch (Exception e)
		{
			System.out.println("Error in CardHolderStatementDAOImpl getCurrentStatement method : "+e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,"Error: in CardHolderStatementDAOImpl getCurrentStatement  method :"+e);
		}

		return objCustomerStatement;

	}

	@Override
	public CurrPaySumDto getCurrPaySum(String cardNo) throws TPlusException {

		CurrPaySumDto objCurrPaySumDto = null;

		try	{

			Session session = HibernetFactory.currentSession();
			
			objCurrPaySumDto = (CurrPaySumDto) session.get(CurrPaySumDto.class, Long.valueOf(cardNo));

		}catch (Exception e)
		{
			System.out.println("Error in CardHolderStatementDAOImpl getCurrPaySum method : "+e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,"Error: in CardHolderStatementDAOImpl getCurrPaySum  method :"+e);
		}

		return objCurrPaySumDto;

	}

	@Override
	public List getStatementTranx(String statId) throws TPlusException {

		List objSearchCollection = new ArrayList();

		try	{

			StringBuffer stbf = new StringBuffer();
			stbf.append(" select to_char(pt.postDate, 'yyyy-mm-dd'), tl.merchantName, tl.cardHolderTranxAmt, pt.tranxLogId ");
			stbf.append(" from StatementTranxDetails st, TransactionLogDto tl, PostTranx pt ");
			stbf.append(" where st.tranxLogId = tl.tranxLogId and pt.tranxLogId = st.tranxLogId and st.statId="+statId+" ");

			objSearchCollection = getSearchListNoPageNo(stbf.toString());

		}catch (Exception e)
		{
			System.out.println("Error in CardHolderStatementDAOImpl getStatementTranx method : "+e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,"Error: in CardHolderStatementDAOImpl getStatementTranx  method :"+e);
		}

		return  objSearchCollection ;

	}

	@Override
	public List getStatPayment(String statId) throws TPlusException {

		List objSearchCollection = new ArrayList();

		try	{

			StringBuffer stbf = new StringBuffer();
			stbf.append(" select to_char(cp.payDate, 'yyyy-mm-dd'), pm.payDesc, cp.payAmt, cp.amtSign, '' ");
			stbf.append(" from CustomerPayment cp, StatementPayDetails sp, Payment pm ");
			stbf.append(" where cp.paymentId = sp.paymentId and cp.payMode = pm.payMode and cp.payMode <> 'MR' and sp.statId="+statId+" ");

			objSearchCollection = getSearchListNoPageNo(stbf.toString());

		}catch (Exception e)
		{
			System.out.println("Error in CardHolderStatementDAOImpl getStatPayment method : "+e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,"Error: in CardHolderStatementDAOImpl getStatPayment  method :"+e);
		}

		return  objSearchCollection ;

	}

	@Override
	public List getStatPaymentRefund(String statId) throws TPlusException {

		List objSearchCollection = new ArrayList();

		try	{

			StringBuffer stbf = new StringBuffer();
			stbf.append(" select to_char(cp.payDate, 'yyyy-mm-dd'), pm.payDesc, cp.payAmt, cp.amtSign, concat(ntl.merchantName,' ' , ntl.merchantCountryCode) as merchantDet ");
			stbf.append(" from CustomerPayment cp, StatementPayDetails sp, Payment pm, NonReconTransactionLogDto ntl ");
			stbf.append(" where cp.paymentId = sp.paymentId and cp.payMode = pm.payMode and cp.payMode = 'MR' and ntl.tranxNonReconId=cp.payRefNo and sp.statId="+statId+" ");

			objSearchCollection = getSearchListNoPageNo(stbf.toString());

		}catch (Exception e)
		{
			System.out.println("Error in CardHolderStatementDAOImpl getStatPayment method : "+e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,"Error: in CardHolderStatementDAOImpl getStatPayment  method :"+e);
		}

		return  objSearchCollection ;

	}

	@Override
	public List getStatInterest(String statId) throws TPlusException {

		List objSearchCollection = new ArrayList();

		try	{

			StringBuffer stbf = new StringBuffer();
			stbf.append(" select to_char(ct.intDate, 'yyyy-mm-dd'), it.intDesc, ct.intAmt ");
			stbf.append(" from CustomerInterest ct, StatementIntDetails st, Interest it ");
			stbf.append(" where ct.interestId = st.interestId and ct.refId = it.intId and st.statId="+statId+" ");

			objSearchCollection = getSearchListNoPageNo(stbf.toString());

		}catch (Exception e)
		{
			System.out.println("Error in CardHolderStatementDAOImpl getStatPayment method : "+e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,"Error: in CardHolderStatementDAOImpl getStatPayment  method :"+e);
		}

		return  objSearchCollection ;

	}

	@Override
	public List getStatFee(String statId) throws TPlusException {

		List objSearchCollection = new ArrayList();

		try	{

			StringBuffer stbf = new StringBuffer();
			stbf.append(" select to_char(cf.FeeDate, 'yyyy-mm-dd'), fe.feeDesc, cf.feeAmt, cf.tranxLogId ");
			stbf.append(" from CustomerFeeDto cf, StatementFeeDetails sf, Fee fe ");
			stbf.append(" where cf.customerFeeId = sf.feeId and cf.refId = fe.feeId and sf.statId="+statId+" ");

			objSearchCollection = getSearchListNoPageNo(stbf.toString());

		}catch (Exception e)
		{
			System.out.println("Error in CardHolderStatementDAOImpl getStatFee method : "+e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,"Error: in CardHolderStatementDAOImpl getStatFee  method :"+e);
		}

		return  objSearchCollection ;

	}

	@Override
	public CustomerStatement getPreviousStatement(String cardNo) throws TPlusException {

		CustomerStatement objCustomerStatement = null;
		CustomerStatement objCustomerPrevStatement = null;

		try	{

			Session session = HibernetFactory.currentSession();
			
			CardsDto objDto = (CardsDto) session.get(CardsDto.class, Long.valueOf(cardNo));
			objCustomerStatement = (CustomerStatement) session.get(CustomerStatement.class, objDto.getLastStatementId());
			
			String prevStatId = objCustomerStatement.getPrevStatId();
			objCustomerPrevStatement = (CustomerStatement) session.get(CustomerStatement.class, prevStatId);

		}catch (Exception e)
		{
			System.out.println("Error in CardHolderStatementDAOImpl getCurrentStatement method : "+e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,"Error: in CardHolderStatementDAOImpl getCurrentStatement  method :"+e);
		}

		return objCustomerPrevStatement;

	}

}
