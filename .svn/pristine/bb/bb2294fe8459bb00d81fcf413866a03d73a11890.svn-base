package org.transinfo.cacis.dataacess.daoimpl.oracle.customerservice;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.transinfo.cacis.TPlusCodes;
import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.common.CommonDataBean;
import org.transinfo.cacis.common.constants.CommonCodes;
import org.transinfo.cacis.dataacess.HibernetDAO.HibernetFactory;
import org.transinfo.cacis.dataacess.dao.customerservice.AddProductDAO;
import org.transinfo.cacis.dataacess.daoimpl.oracle.BaseDAOImpl;
import org.transinfo.cacis.dto.csr.AddProductDto;
import org.transinfo.cacis.dto.customerservice.AddProductSearchDto;
import org.transinfo.cacis.dto.customerservice.AddProductSetupDto;

@SuppressWarnings("unchecked")
public class AddProductDAOImpl extends BaseDAOImpl implements
AddProductDAO {

	public AddProductSearchDto search(
			AddProductSearchDto objAddProductSearchDto, int pageNo)
					throws TPlusException {

		Collection objSearchCollection = null;
		int totalCount = 0;

		StringBuffer sbf = new StringBuffer();
		StringBuffer sbfSelect = new StringBuffer();
		StringBuffer sbfCount = new StringBuffer();

		try {

			sbfSelect.append("select ");
			sbfSelect.append("cDto.cardNumber, ");
			sbfSelect.append("apDto.customerId, apDto.customerName, ");
			sbfSelect.append("casDto.phone, caDto.acctStatus ");

			sbfCount.append("select ");
			sbfCount.append("count(*) ");

			sbf.append("from ApplicationProcessDto apDto, CardsDto cDto, CustomerAddressDto casDto, CustomerAccountDto caDto ");
			sbf.append("where apDto.customerId = casDto.appFormProcssDto.customerId ");
			sbf.append("and apDto.customerId = cDto.customerId ");
			sbf.append("and cDto.accountId = caDto.accountId ");
			sbf.append("and casDto.addressType = 'H' ");

			if (objAddProductSearchDto.getCardNo() != null
					&& !objAddProductSearchDto.getCardNo().equals("")) {
				sbf.append("and cDto.cardNumber = "
						+ objAddProductSearchDto.getCardNo() + " ");
			}

			if (objAddProductSearchDto.getMobileNo() != null
					&& !objAddProductSearchDto.getMobileNo().equals("")) {
				sbf.append("and casDto.phone = '"
						+ objAddProductSearchDto.getMobileNo() + "' ");
			}
			if (objAddProductSearchDto.getCustomerName() != null
					&& !objAddProductSearchDto.getCustomerName().equals("")) {
				sbf.append("and apDto.customerName = '"
						+ objAddProductSearchDto.getCustomerName() + "' ");
			}
			if (objAddProductSearchDto.getCollateralAcctId() != null
					&& !objAddProductSearchDto.getCollateralAcctId().equals("")) {
				sbf.append("and caDto.accountId = '"
						+ objAddProductSearchDto.getCollateralAcctId() + "' ");
			}

			sbf.append("order by apDto.customerName desc");

			objSearchCollection = getSearchTranxList((sbfSelect.append(sbf))
					.toString(), pageNo);
			objAddProductSearchDto.setSearchList(objSearchCollection);

			totalCount = getSearchTotalCount((sbfCount.append(sbf)).toString());
			objAddProductSearchDto.setTotalCount(totalCount);

		} catch (Exception e) {
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error:in AddProductSearchDto search method " + e);
		}
		return objAddProductSearchDto;
	}

	public AddProductSetupDto get(AddProductSetupDto objAddProductSetupDto, String customerId)
			throws TPlusException {

		Collection objSearchCollection = null;

		StringBuffer sbf = new StringBuffer();
		StringBuffer sbfSelect = new StringBuffer();
		StringBuffer sbfGetCardProduct = new StringBuffer();

		try {

			sbfSelect.append("select ");
			sbfSelect.append("apDto.customerName, apDto.idNumber, cDto.cardNumber,  ");
			sbfSelect.append("caDto.accountId, caDto.creditLimit, ");
			sbfSelect.append("caDto.limitUsed, caDto.acctStatus, ");
			sbfSelect.append("cpDto.cardProductName, cpDto.cardProductId, concat(caDto.accountId, '/', cpDto.cardProductId) ");

			sbf.append("from ApplicationProcessDto apDto, CardsDto cDto, CardProductDto cpDto, CustomerAccountDto caDto ");
			sbf.append("where cDto.customerId = '" + customerId + "' ");
			sbf.append("and apDto.customerId = cDto.customerId and cpDto.cardProductId = cDto.cardProductId ");
			sbf.append("and cDto.accountId = caDto.accountId ");

			sbfGetCardProduct.append("select cpDto.cardProductId, cpDto.cardProductName from CardProductDto cpDto ");
			sbfGetCardProduct.append("where cpDto.cardProductId not in (select cDto.cardProductId from  CardsDto cDto where cDto.customerId = '" + customerId + "') ");

			objSearchCollection = getSearchTranxList((sbfSelect.append(sbf))
					.toString(), 0);
			//get cardProduct Map
			ArrayList cardProduct = (ArrayList) getSearchList(sbfGetCardProduct.toString(), 0);
			Map cardProductList = new TreeMap();
			for(int i=0;i<cardProduct.size();i++) {
				CommonDataBean bean = (CommonDataBean) cardProduct.get(i);
				String cardProductId = bean.getColumn1();
				String cardProductName = bean.getColumn2();
				cardProductList.put(cardProductId, cardProductName);
			}

			//get distinct pair of AccountId and CardProductId
			Map pair = new HashMap();
			ArrayList searchList = (ArrayList) objSearchCollection;
			for(int i=0;i<searchList.size();i++) {
				CommonDataBean bean = (CommonDataBean) searchList.get(i);
				pair.put(bean.getColumn4(), bean.getColumn8());
			}
			//get Customer Informations
			ArrayList customerInfo = (ArrayList) objSearchCollection;
			CommonDataBean bean = (CommonDataBean) customerInfo.get(0);
			objAddProductSetupDto.setSearchList(objSearchCollection);
			objAddProductSetupDto.setCustomerName(bean.getColumn1());
			objAddProductSetupDto.setNric(bean.getColumn2());
			objAddProductSetupDto.setCreditLimit(bean.getColumn5());
			objAddProductSetupDto.setCardProductList(cardProductList);
			objAddProductSetupDto.setAccountIdAndCardProductIdList(pair);
		} catch (Exception e) {
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error:in AddProductSetupDto get method " + e);
		}
		return objAddProductSetupDto;
	}

	public boolean addProduct(AddProductSetupDto objAddProductSetupDto, String userID) throws TPlusException {
		boolean bolExecute = false;
		Transaction tx = null;
		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			AddProductDto addProductDto = new AddProductDto();
			if(objAddProductSetupDto.getAccountType().equals("N")) {
				addProductDto.setAcctType("N");
			} else {
				addProductDto.setAcctType("E");
				addProductDto.setAccountId(objAddProductSetupDto.getAccountId());
			}
			addProductDto.setStatus(CommonCodes.APPLICATIONSTATUS_NEW);
			addProductDto.setCustomerId(objAddProductSetupDto.getCustomerId());
			addProductDto.setNric(objAddProductSetupDto.getNric());
			addProductDto.setCustomerName(objAddProductSetupDto.getCustomerName());
			addProductDto.setLastUpdatedBy(userID);
			addProductDto.setLastUpdatedDate(new Date());
			addProductDto.setNewCardProduct(objAddProductSetupDto.getCardProductId());
			session.save(addProductDto);
			tx.commit();
			bolExecute = true;
		}catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in AddProductDAOImpl addProduct method" + e);
		} finally {
			HibernetFactory.closeSession();
		}
		return bolExecute;
	}
}
