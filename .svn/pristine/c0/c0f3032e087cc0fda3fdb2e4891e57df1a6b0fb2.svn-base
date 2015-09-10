package org.transinfo.cacis.dataacess.daoimpl.oracle.applicationforms;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.transinfo.cacis.TPlusCodes;
import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.common.CommonDataBean;
import org.transinfo.cacis.common.IdsGenartion;
import org.transinfo.cacis.common.constants.CommonCodes;
import org.transinfo.cacis.constants.ICacisiss;
import org.transinfo.cacis.dataacess.HibernetDAO.HibernetFactory;
import org.transinfo.cacis.dataacess.dao.applicationforms.AddCardProcessDAO;
import org.transinfo.cacis.dataacess.dao.customerservice.AddProductDAO;
import org.transinfo.cacis.dataacess.dao.customerservice.NonReconTransactionEnquiryDAO;
import org.transinfo.cacis.dataacess.dao.customerservice.TransactionEnquiryDAO;
import org.transinfo.cacis.dataacess.daoimpl.oracle.BaseDAOImpl;
import org.transinfo.cacis.dto.applicationforms.AddCardProcessSearchDto;
import org.transinfo.cacis.dto.applicationforms.AddCardProcessSetupDto;
import org.transinfo.cacis.dto.cardproduction.ApplicationProcessDto;
import org.transinfo.cacis.dto.cardproduction.CardsDto;
import org.transinfo.cacis.dto.cardproduction.CustomerAccountDto;
import org.transinfo.cacis.dto.csr.AddProductDto;
import org.transinfo.cacis.dto.csr.NonReconTransactionLogDto;
import org.transinfo.cacis.dto.csr.TransactionLogDto;
import org.transinfo.cacis.dto.csr.TranxRevertDto;
import org.transinfo.cacis.dto.customerservice.AddProductSearchDto;
import org.transinfo.cacis.dto.customerservice.NonReconTranxEnquirySearchDto;
import org.transinfo.cacis.dto.customerservice.TranxEnquirySearchDto;
import org.transinfo.cacis.dto.disputemanagement.DisputeManualReconDto;

@SuppressWarnings("unchecked")
public class AddCardProcessDAOImpl extends BaseDAOImpl implements
		AddCardProcessDAO {
	@Override
	public AddCardProcessSearchDto search(
			AddCardProcessSearchDto objAddCardProcessSearchDto, int pageNo)
			throws TPlusException {

		Collection objSearchCollection = null;
		int totalCount = 0;

		StringBuffer sbf = new StringBuffer();
		StringBuffer sbfSelect = new StringBuffer();
		StringBuffer sbfCount = new StringBuffer();

		try {

			sbfSelect.append("select ");
			sbfSelect.append("aptDto.sno, aptDto.customerName, ");
			sbfSelect.append("aptDto.nric, cpDto.cardProductName, ");
			sbfSelect.append("aptDto.acctType ");

			sbfCount.append("select ");
			sbfCount.append("count(aptDto.sno) ");

			sbf.append("from AddProductDto aptDto, CardProductDto cpDto ");
			sbf.append("where aptDto.newCardProduct = cpDto.cardProductId ");
			sbf.append("and aptDto.status = 0 ");

			if (objAddCardProcessSearchDto.getCustomerName() != null
					&& !objAddCardProcessSearchDto.getCustomerName().equals("")) {
				sbf.append("and aptDto.customerName = '"
						+ objAddCardProcessSearchDto.getCustomerName() + "' ");
			}
			if (objAddCardProcessSearchDto.getNric() != null
					&& !objAddCardProcessSearchDto.getNric().equals("")) {
				sbf.append("and aptDto.nric = '"
						+ objAddCardProcessSearchDto.getNric() + "' ");
			}

			sbf.append("order by aptDto.customerName desc");

			objSearchCollection = getSearchList(
					(sbfSelect.append(sbf)).toString(), pageNo);
			objAddCardProcessSearchDto.setSearchList(objSearchCollection);

			totalCount = getSearchTotalCount((sbfCount.append(sbf)).toString());
			objAddCardProcessSearchDto.setTotalCount(totalCount);

		} catch (Exception e) {
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error:in AddCardProcessDAOImpl search method " + e);
		}
		return objAddCardProcessSearchDto;
	}

	public AddCardProcessSetupDto getAddProductForm(
			AddCardProcessSetupDto objAddCardProcessSetupDto)
			throws TPlusException {
		Collection objSearchCollection = null;
		int totalCount = 0;

		StringBuffer sbf = new StringBuffer();

		try {

			sbf.append("select aptDto.customerName, aptDto.nric, ");
			sbf.append("aptDto.newCardProduct, aptDto.acctType, aptDto.customerId, ");
			
			if(objAddCardProcessSetupDto.getAccountType().equals("E")) {
				sbf.append("caDto.creditLimit ");
				sbf.append("from AddProductDto aptDto, CustomerAccountDto caDto ");
				sbf.append("where aptDto.accountId = caDto.accountId ");
			} else {
				sbf.append("0 as creditLimit ");
				sbf.append("from AddProductDto aptDto ");
				sbf.append("where 1=1 ");
			}
			
			
			sbf.append("and aptDto.sno = " + objAddCardProcessSetupDto.getSno());
			objSearchCollection = getSearchList(sbf.toString(), 0);

			// get earch column in query
			ArrayList a = (ArrayList) objSearchCollection;
			CommonDataBean bean = (CommonDataBean) a.get(0);
			String customerName = bean.getColumn1();
			String nric = bean.getColumn2();
			String newCardProduct = bean.getColumn3();
			String acctType = bean.getColumn4();
			String customerId = bean.getColumn5();
			String creditLimit = bean.getColumn6();

			// set into objAddCardProcessSetupDto
			objAddCardProcessSetupDto.setCustomerName(customerName);
			objAddCardProcessSetupDto.setNric(nric);
			objAddCardProcessSetupDto.setCardProductId(newCardProduct);
			objAddCardProcessSetupDto.setAccountType(acctType);
			objAddCardProcessSetupDto.setCustomerId(customerId);
			objAddCardProcessSetupDto.setCreditLimit(creditLimit);

		} catch (Exception e) {
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error:in AddCardProcessDAOImpl getAddProductForm method "
							+ e);
		}
		return objAddCardProcessSetupDto;

	}

	@Override
	public boolean reject(AddCardProcessSetupDto objAddCardProcessSetupDto)
			throws TPlusException {
		boolean checkUpdate = false;
		Transaction tx = null;
		int count;
		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			// updating AddProductDto
			String sql = "UPDATE AddProductDto SET status=:status WHERE sno=:sno";
			count = session
					.createQuery(sql)
					.setLong("status", 2)
					.setInteger("sno", Integer.parseInt(objAddCardProcessSetupDto.getSno()))
					.executeUpdate();
			if (count > 0) {
				checkUpdate = true;
			}
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error:in AddCardProcessDAOImpl update method " + e);
		} finally {
			HibernetFactory.closeSession();
		}
		return checkUpdate;
	}

	@Override
	public boolean accept(
			AddCardProcessSetupDto objAddCardProcessSetupDto)
			throws TPlusException {
		boolean checkExcute = false;
		boolean checkUpdate = false;
		Transaction tx = null;
		int count;
		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			
			if(objAddCardProcessSetupDto.getAccountType().equals("N")) {
			
			CustomerAccountDto accountDto = new CustomerAccountDto();
			String accountId = IdsGenartion.GenerateAccountId();
			accountDto.setAccountId(accountId);
			// set Customer
			ApplicationProcessDto objApplicationProcessDto = (ApplicationProcessDto) session
					.get(ApplicationProcessDto.class,
							objAddCardProcessSetupDto.getCustomerId());
			accountDto.setAppProcessDto(objApplicationProcessDto);
			// accountDto.setCustomerId(objAddCardProcessSetupDto.getCustomerId());
			accountDto.setAccountName(objAddCardProcessSetupDto
					.getCustomerName());
			accountDto.setCreditLimit(Float
					.parseFloat(objAddCardProcessSetupDto.getCreditLimit()));
			accountDto.setCurrencyCode(840);
			accountDto.setCycleNo(Integer.parseInt(objAddCardProcessSetupDto
					.getCycle()));
			accountDto.setCashLimit(0);
			accountDto.setPreviousBalance(0);
			accountDto.setAmountCredited(0);
			accountDto.setAmountDebited(0);
			accountDto.setLimitUsed(0);
			accountDto.setAuthorizationAmt(0);
			session.save(accountDto);
			
			// updating AddProductDto
						String sql = "UPDATE AddProductDto SET status=:status, accountId=:accountId WHERE sno=:sno";
						count = session
								.createQuery(sql)
								.setLong("status", 1)
								.setString("accountId", accountId)
								.setInteger("sno", Integer.parseInt(objAddCardProcessSetupDto.getSno()))
								.executeUpdate();
						if(count > 0)
							checkUpdate = true;
			} else {
				// updating AddProductDto
				String sql = "UPDATE AddProductDto SET status=:status WHERE sno=:sno";
				count = session
						.createQuery(sql)
						.setLong("status", 1)
						.setInteger("sno", Integer.parseInt(objAddCardProcessSetupDto.getSno()))
						.executeUpdate();
				if(count > 0)
					checkUpdate = true;
			}
			
			tx.commit();
			checkExcute = true;
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error:in AddCardProcessDAOImpl insertCustomerAccount method "
							+ e);
		} finally {
			HibernetFactory.closeSession();
		}
		return checkExcute && checkUpdate;
	}
}
