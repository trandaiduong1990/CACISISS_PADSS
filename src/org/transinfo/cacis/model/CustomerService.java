package org.transinfo.cacis.model;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.transinfo.cacis.TPlusCodes;
import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.common.constants.CommonCodes;
import org.transinfo.cacis.dataacess.HibernetDAO.HibernetFactory;
import org.transinfo.cacis.dto.accounting.CustomerStatement;
import org.transinfo.cacis.dto.cardproduction.ApplicationProcessDto;
import org.transinfo.cacis.dto.cardproduction.CardsDto;
import org.transinfo.cacis.dto.cardproduction.CustomerAccountDto;
import org.transinfo.cacis.formbean.customerservice.CustomerServiceDataBean;
import org.transinfo.cacis.util.DateUtil;

@SuppressWarnings("unchecked")
public class CustomerService {

	/**
	 * This method is to retrieve the data for the CusomerService Screens
	 */
	public CustomerService(){}

	public CustomerServiceDataBean getCustomerServiceData(String cardnumber) throws TPlusException {

		CustomerServiceDataBean objCustDataBean 	= 	new CustomerServiceDataBean();
		
		try {
			Session	session = HibernetFactory.currentSession();
			Query qry = session.createQuery("from CardsDto where encryptedCardNo='"+cardnumber+"'");
			List listCard = qry.list();
			if(listCard.isEmpty())
			{
				throw new TPlusException(TPlusCodes.APPLICATION_ERROR,"Error: No Card available");
			}
			
			for(Iterator it = listCard.iterator();it.hasNext();)
			{
				CardsDto objCards = (CardsDto)it.next();
				objCustDataBean.setCardType(objCards.getCardProductsDto().getCardType().getCardType());
				objCustDataBean.setCardProductName(objCards.getCardProductsDto().getCardProductName());
				BeanUtils.copyProperties(objCustDataBean, objCards);
				
				// get credit limit
				/*qry = session.createQuery("from CustomerAccountDto cu where cu.accountId= '"+objCards.getAccountId()+"' ");
				it = qry.list().iterator();
				if(it.hasNext())
				{
					CustomerAccountDto account = (CustomerAccountDto)it.next();
					//objCustDataBean.getCustomerAccount().add(account);
					objCustDataBean.setCreditLimit(String.valueOf(account.getCreditLimit()));

				}*/
				//CustomerAccountDto objCustomerAccountDto = (CustomerAccountDto) session.get(CustomerAccountDto.class, objCards.getAccountId());
				/*if(objCustomerAccountDto != null){
					objCustDataBean.setCreditLimit(String.valueOf(objCustomerAccountDto.getCreditLimit()));
				}*/

				CustomerStatement objCustomerStatement = null;
				String lastStatId = objCards.getLastStatementId();
				if(!"".equals(lastStatId) && lastStatId != null){
					objCustomerStatement = (CustomerStatement) session.get(CustomerStatement.class, lastStatId);
				}
				if(objCustomerStatement != null){
					if(objCustomerStatement.getPrevPayAmt() != null){
						objCustDataBean.setPrevPayAmt(String.valueOf(objCustomerStatement.getPrevPayAmt()));

						objCustDataBean.setPrevBal(String.valueOf((objCustomerStatement.getPrevStatAmt()-objCustomerStatement.getPrevPayAmt())));
					}else{
						objCustDataBean.setPrevPayAmt("0");
						objCustDataBean.setPrevBal("0");
					}
					objCustDataBean.setStatAmt(String.valueOf(objCustomerStatement.getStatAmt()));
					objCustDataBean.setStatMinAmt(String.valueOf(objCustomerStatement.getStatMinAmt()));
					objCustDataBean.setStatDueDate(DateUtil.convertDateToDateString(objCustomerStatement.getStatDueDate()));
					objCustDataBean.setOutStatAmt(String.valueOf(objCustomerStatement.getOutStandStatAmt()));
					objCustDataBean.setOutStatpurchaseAmt(String.valueOf(objCustomerStatement.getOutStandPurchaseAmt()));
					objCustDataBean.setOutStatCashAmt(String.valueOf(objCustomerStatement.getOutStandCashAmt()));
				}else{
					objCustDataBean.setPrevPayAmt("0");
					objCustDataBean.setPrevBal("0");
					objCustDataBean.setStatAmt("0");
					objCustDataBean.setStatMinAmt("0");
					objCustDataBean.setOutStatAmt("0");
					objCustDataBean.setOutStatpurchaseAmt("0");
					objCustDataBean.setOutStatCashAmt("0");
				}

				if(objCards.getCardHolderType()==CommonCodes.PRIMARYCARD_HOLDER)
				{
					qry = session.createQuery("from ApplicationProcessDto app where app.customerId= '"+objCards.getCustomerId()+"' ");
					it = qry.list().iterator();
					if(it.hasNext())
					{
						ApplicationProcessDto customer = (ApplicationProcessDto)it.next();
						BeanUtils.copyProperties(objCustDataBean, customer);

					}

				}
				else if(objCards.getCardHolderType()==CommonCodes.SUPPLEMENTARYCARD_HOLDER)
				{

					qry = session.createQuery("from ApplicationProcessDto app where app.customerId= '"+objCards.getCustomerId()+"' ");
					it = qry.list().iterator();
					if(it.hasNext())
					{
						ApplicationProcessDto customer = (ApplicationProcessDto)it.next();
						BeanUtils.copyProperties(objCustDataBean, customer);

					}

					// this is for getting account object
					qry = session.createQuery("from CustomerAccountDto cu where cu.accountId= '"+objCards.getAccountId()+"' ");
					it = qry.list().iterator();
					if(it.hasNext())
					{
						CustomerAccountDto account = (CustomerAccountDto)it.next();
						objCustDataBean.getCustomerAccount().add(account);

					}
					/*qry = session.createQuery("from SupplementaryCardHolderDto where customer_Id = '"+objCards.getCustomerId()+"' ");
									it = qry.list().iterator();
									if(it.hasNext())
									{
										SupplementaryCardHolderDto  supplementaryDto = (SupplementaryCardHolderDto)it.next();
									    BeanUtils.copyProperties(objCustDataBean, supplementaryDto);

									}*/
				}

			}

		}catch (Exception e){
			System.out.println("*** Error In SearchList Method ***"+e);
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,"Error: while retrieving searchList Info"+e);
		}
		finally
		{
			HibernetFactory.closeSession();
		}

		return objCustDataBean;

	}

	public CustomerServiceDataBean getCustomerServiceData(long cardnumber) throws TPlusException {

		CustomerServiceDataBean objCustDataBean 	= 	new CustomerServiceDataBean();
		try {
			Session	session = HibernetFactory.currentSession();
			Query qry = session.createQuery("from CardsDto where cardNumber="+cardnumber+"");
			List listCard = qry.list();
			if(listCard.isEmpty())
			{
				throw new TPlusException(TPlusCodes.APPLICATION_ERROR,"Error: No Card available");
			}
			for(Iterator it = listCard.iterator();it.hasNext();)
			{
				CardsDto objCards = (CardsDto)it.next();
				objCustDataBean.setCardType(objCards.getCardProductsDto().getCardType().getCardType());
				objCustDataBean.setCardProductName(objCards.getCardProductsDto().getCardProductName());
				BeanUtils.copyProperties(objCustDataBean, objCards);
				
				// get credit limit
				/*qry = session.createQuery("from CustomerAccountDto cu where cu.accountId= '"+objCards.getAccountId()+"' ");
				it = qry.list().iterator();
				if(it.hasNext())
				{
					CustomerAccountDto account = (CustomerAccountDto)it.next();
					//objCustDataBean.getCustomerAccount().add(account);
					objCustDataBean.setCreditLimit(String.valueOf(account.getCreditLimit()));

				}*/
				//CustomerAccountDto objCustomerAccountDto = (CustomerAccountDto) session.get(CustomerAccountDto.class, objCards.getAccountId());
				/*if(objCustomerAccountDto != null){
					objCustDataBean.setCreditLimit(String.valueOf(objCustomerAccountDto.getCreditLimit()));
				}*/

				CustomerStatement objCustomerStatement = null;
				String lastStatId = objCards.getLastStatementId();
				if(!"".equals(lastStatId) && lastStatId != null){
					objCustomerStatement = (CustomerStatement) session.get(CustomerStatement.class, lastStatId);
				}
				if(objCustomerStatement != null){
					if(objCustomerStatement.getPrevPayAmt() != null){
						objCustDataBean.setPrevPayAmt(String.valueOf(objCustomerStatement.getPrevPayAmt()));

						objCustDataBean.setPrevBal(String.valueOf((objCustomerStatement.getPrevStatAmt()-objCustomerStatement.getPrevPayAmt())));
					}else{
						objCustDataBean.setPrevPayAmt("0");
						objCustDataBean.setPrevBal("0");
					}
					objCustDataBean.setStatAmt(String.valueOf(objCustomerStatement.getStatAmt()));
					objCustDataBean.setStatMinAmt(String.valueOf(objCustomerStatement.getStatMinAmt()));
					objCustDataBean.setStatDueDate(DateUtil.convertDateToDateString(objCustomerStatement.getStatDueDate()));
					objCustDataBean.setOutStatAmt(String.valueOf(objCustomerStatement.getOutStandStatAmt()));
					objCustDataBean.setOutStatpurchaseAmt(String.valueOf(objCustomerStatement.getOutStandPurchaseAmt()));
					objCustDataBean.setOutStatCashAmt(String.valueOf(objCustomerStatement.getOutStandCashAmt()));
				}else{
					objCustDataBean.setPrevPayAmt("0");
					objCustDataBean.setPrevBal("0");
					objCustDataBean.setStatAmt("0");
					objCustDataBean.setStatMinAmt("0");
					objCustDataBean.setOutStatAmt("0");
					objCustDataBean.setOutStatpurchaseAmt("0");
					objCustDataBean.setOutStatCashAmt("0");
				}

				if(objCards.getCardHolderType()==CommonCodes.PRIMARYCARD_HOLDER)
				{
					qry = session.createQuery("from ApplicationProcessDto app where app.customerId= '"+objCards.getCustomerId()+"' ");
					it = qry.list().iterator();
					if(it.hasNext())
					{
						ApplicationProcessDto customer = (ApplicationProcessDto)it.next();
						BeanUtils.copyProperties(objCustDataBean, customer);

					}

				}
				else if(objCards.getCardHolderType()==CommonCodes.SUPPLEMENTARYCARD_HOLDER)
				{

					qry = session.createQuery("from ApplicationProcessDto app where app.customerId= '"+objCards.getCustomerId()+"' ");
					it = qry.list().iterator();
					if(it.hasNext())
					{
						ApplicationProcessDto customer = (ApplicationProcessDto)it.next();
						BeanUtils.copyProperties(objCustDataBean, customer);

					}

					// this is for getting account object
					qry = session.createQuery("from CustomerAccountDto cu where cu.accountId= '"+objCards.getAccountId()+"' ");
					it = qry.list().iterator();
					if(it.hasNext())
					{
						CustomerAccountDto account = (CustomerAccountDto)it.next();
						objCustDataBean.getCustomerAccount().add(account);

					}
					/*qry = session.createQuery("from SupplementaryCardHolderDto where customer_Id = '"+objCards.getCustomerId()+"' ");
									it = qry.list().iterator();
									if(it.hasNext())
									{
										SupplementaryCardHolderDto  supplementaryDto = (SupplementaryCardHolderDto)it.next();
									    BeanUtils.copyProperties(objCustDataBean, supplementaryDto);

									}*/
				}

			}

		}catch (Exception e){
			System.out.println("*** Error In SearchList Method ***"+e);
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,"Error: while retrieving searchList Info"+e);
		}
		finally
		{
			HibernetFactory.closeSession();
		}

		return objCustDataBean;

	}

//	public static void main(String args[]) throws Exception{
//		CustomerService objTest = new CustomerService();
//		CustomerServiceDataBean objCustDataBean=objTest.getCustomerServiceData(new Long("1724570000000022").longValue());
//
//		System.out.println("Customer Name"+objCustDataBean.getSurName());
//		System.out.println("Customer NRIC"+objCustDataBean.getIdNumber());
//
//
//	}

}
