package org.transinfo.cacis.dataacess.daoimpl.oracle;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.transinfo.cacis.TPlusCodes;
import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.common.CommonDataBean;
import org.transinfo.cacis.dataacess.HibernetDAO.HibernetFactory;
import org.transinfo.cacis.dataacess.dao.BaseDAO;
import org.transinfo.cacis.dto.batchprocess.InstantCardDto;
import org.transinfo.cacis.dto.batchprocess.SearchInstantCardDto;
import org.transinfo.cacis.dto.cardproduction.ApplValidationDto;
import org.transinfo.cacis.dto.cardproduction.ApplicationDocProofDto;
import org.transinfo.cacis.dto.cardproduction.CityDto;
import org.transinfo.cacis.dto.cardproduction.CreditScoringDto;
import org.transinfo.cacis.dto.cardproduction.StateDto;
import org.transinfo.cacis.dto.cardproduction.TownShipDto;
import org.transinfo.cacis.dto.collectionmanagement.DelinquencyFeeSetupDto;
import org.transinfo.cacis.dto.collectionmanagement.DelinquencyNotificationSetupDto;
import org.transinfo.cacis.dto.collectionmanagement.DelinquencyNotificationSetupSearchDto;
import org.transinfo.cacis.dto.collectionmanagement.DelinquencyPolicyDto;
import org.transinfo.cacis.dto.common.CardProcessStatusDto;
import org.transinfo.cacis.dto.common.CardStatusDto;
import org.transinfo.cacis.dto.common.EducationListDto;
import org.transinfo.cacis.dto.common.JobStatusDto;
import org.transinfo.cacis.dto.common.JobTypeDto;
import org.transinfo.cacis.dto.csr.ResponseCodeDto;
import org.transinfo.cacis.dto.disputemanagement.DisputeClaimTypesDto;
import org.transinfo.cacis.dto.disputemanagement.DisputeDocumentsDto;
import org.transinfo.cacis.dto.disputemanagement.DisputeGroupDetailsDto;
import org.transinfo.cacis.dto.disputemanagement.DisputeGroupsDto;
import org.transinfo.cacis.dto.disputemanagement.DisputeMotosDto;
import org.transinfo.cacis.dto.disputemanagement.DisputeReasonDto;
import org.transinfo.cacis.dto.letters.LetterCategoryDto;
import org.transinfo.cacis.dto.letters.LetterTemplateDto;
import org.transinfo.cacis.dto.letters.LetterTypesListDto;
import org.transinfo.cacis.dto.riskmanagement.MerchantCategoryDto;
import org.transinfo.cacis.dto.riskmanagement.TranxCodeDto;
import org.transinfo.cacis.dto.settings.BranchDto;
import org.transinfo.cacis.dto.settings.CardProductDto;
import org.transinfo.cacis.dto.settings.CardProductTypeDto;
import org.transinfo.cacis.dto.settings.CardTypeDto;
import org.transinfo.cacis.dto.settings.CountryMasterDto;
import org.transinfo.cacis.dto.settings.CurrencyDto;
import org.transinfo.cacis.dto.settings.CustomerTypeDto;
import org.transinfo.cacis.dto.settings.CycleDto;
import org.transinfo.cacis.dto.settings.EMVProfileDto;
import org.transinfo.cacis.dto.settings.IssuerCurrencyDto;
import org.transinfo.cacis.dto.settings.IssuerDto;
import org.transinfo.cacis.dto.settings.TranxTypeDto;
import org.transinfo.cacis.dto.switching.SwitchTypeDto;
import org.transinfo.cacis.dto.useraccess.CodeMasterDto;
import org.transinfo.cacis.dto.useraccess.UserLevelDto;
import org.transinfo.cacis.dto.useraccess.UserMasterDto;
import org.transinfo.cacis.formbean.batchprocess.CardBatchForm;
import org.transinfo.cacis.formbean.collectionmanagement.DelinquencyFeeSetupForm;
import org.transinfo.cacis.formbean.collectionmanagement.DelinquencyNotificationSetupForm;
import org.transinfo.cacis.util.CacisissUtils;

@SuppressWarnings( { "static-access", "unchecked" })
public class BaseDAOImpl implements BaseDAO {

	private Logger logger = Logger.getLogger(BaseDAOImpl.class);

	public Map dateListData() throws TPlusException {
		Map dateMap = new TreeMap();
		for (int i = 1; i <= 31; i++) {
			dateMap.put(new Integer(i), new Integer(i).toString());
		}
		return dateMap;
	}

	public Map monthListData() throws TPlusException {
		Map monthMap = new TreeMap();
		monthMap.put(new Integer(1), "JAN");
		monthMap.put(new Integer(2), "FEB");
		monthMap.put(new Integer(3), "MAR");
		monthMap.put(new Integer(4), "APR");
		monthMap.put(new Integer(5), "MAY");
		monthMap.put(new Integer(6), "JUN");
		monthMap.put(new Integer(7), "JUL");
		monthMap.put(new Integer(8), "AUG");
		monthMap.put(new Integer(9), "SEPT");
		monthMap.put(new Integer(10), "OCT");
		monthMap.put(new Integer(11), "NOV");
		monthMap.put(new Integer(12), "DEC");
		return monthMap;
	}

	public Map yearListData() throws TPlusException {
		Map yearMap = new TreeMap();
		Calendar cal = Calendar.getInstance();
		for (int i = 1900; i <= cal.get(Calendar.YEAR); i++) {
			yearMap.put(new Integer(i).toString(), new Integer(i).toString());
		}

		return yearMap;
	}

	public Map genderListData() throws TPlusException {
		Map genderMap = new HashMap();
		genderMap.put("M", "MALE");
		genderMap.put("F", "FEMALE");
		return genderMap;
	}

	public Map maritalListData() throws TPlusException {
		Map maritalMap = new HashMap();
		maritalMap.put("S", "MARRIED");
		maritalMap.put("M", "SINGLE");
		maritalMap.put("D", "DIVORCE");
		return maritalMap;
	}

	public Map relationshipListData() throws TPlusException {

		Map relationMap = new TreeMap();
		relationMap.put(new Integer(1), "Father");
		relationMap.put(new Integer(2), "Mother");
		relationMap.put(new Integer(3), "Sister");
		relationMap.put(new Integer(4), "Brother");
		relationMap.put(new Integer(5), "Son");
		relationMap.put(new Integer(6), "Daughter");
		relationMap.put(new Integer(7), "Spouse");

		return relationMap;
	}

	public Map expiryYearListData() throws TPlusException {
		Map yearMap = new TreeMap();
		Calendar cal = Calendar.getInstance();
		for (int i = cal.get(Calendar.YEAR); i <= cal.get(Calendar.YEAR) + 30; i++) {
			yearMap.put(new Integer(i).toString(), new Integer(i).toString());
		}

		return yearMap;
	}

	public Map educationListData() throws TPlusException {

		Map educationList = new TreeMap();
		Transaction tx = null;
		
		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			Query qry = session.createQuery("From EducationListDto");
			List resList = qry.list();
			for (Iterator it = resList.iterator(); it.hasNext();) {
				EducationListDto objDto = new EducationListDto();
				objDto = (EducationListDto) it.next();
				educationList.put(objDto.getId(), objDto.getDescription());
			}
			tx.commit();

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			logger.error(e);
			System.out
			.println("while retrieving educationListData in BaseDAOIMpl"
					+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while retrieving educationListData in BaseDAOIMpl"
					+ e);
		} finally {

			HibernetFactory.closeSession();
		}
		return educationList;
	}

	public Map jobTypeListData() throws TPlusException {

		Map jobTypeList = new TreeMap();
		Transaction tx = null;
		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			Query qry = session.createQuery("From JobTypeDto");
			List resList = qry.list();
			for (Iterator it = resList.iterator(); it.hasNext();) {
				JobTypeDto objDto = new JobTypeDto();
				objDto = (JobTypeDto) it.next();
				jobTypeList.put(objDto.getId(), objDto.getDescription());
			}
			tx.commit();

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			logger.error(e);
			System.out
			.println("while retrieving jobTypeListData in BaseDAOIMpl"
					+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while retrieving jobTypeListData in BaseDAOIMpl"
					+ e);
		} finally {

			HibernetFactory.closeSession();
		}
		return jobTypeList;
	}

	public Map jobStatusListData() throws TPlusException {

		Map jobStatusList = new TreeMap();
		Transaction tx = null;
		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			Query qry = session.createQuery("From JobStatusDto");
			List resList = qry.list();
			for (Iterator it = resList.iterator(); it.hasNext();) {
				JobStatusDto objDto = new JobStatusDto();
				objDto = (JobStatusDto) it.next();
				jobStatusList.put(objDto.getId(), objDto.getDescription());
			}
			tx.commit();

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			logger.error(e);
			System.out
			.println("while retrieving jobStatusListData in BaseDAOIMpl"
					+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while retrieving jobStatusListData in BaseDAOIMpl"
					+ e);
		} finally {

			HibernetFactory.closeSession();
		}
		return jobStatusList;
	}

	public Map countryListData() throws TPlusException {

		Map countryList = new TreeMap();
		Transaction tx = null;
		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			Query qry = session.createQuery("From CountryMasterDto");
			List listCountries = qry.list();
			for (Iterator it = listCountries.iterator(); it.hasNext();) {
				CountryMasterDto objDto = new CountryMasterDto();
				objDto = (CountryMasterDto) it.next();
				countryList.put(objDto.getCountryCode(), objDto
						.getCountryName());
			}
			tx.commit();

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			logger.error(e);
			System.out
			.println("while retrieving CountryListData in BaseDAOIMpl"
					+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while retrieving CountryListData in BaseDAOIMpl"
					+ e);
		} finally {

			HibernetFactory.closeSession();
		}
		return countryList;
	}

	public Map currencyListData() throws TPlusException {

		Map currencyList = new TreeMap();
		Transaction tx = null;

		try {

			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			Query qry = session.createQuery("From CurrencyDto");
			List listCurrency = qry.list();
			for (Iterator it = listCurrency.iterator(); it.hasNext();) {
				CurrencyDto objDto = new CurrencyDto();
				objDto = (CurrencyDto) it.next();
				currencyList.put(objDto.getCurrencyCode(), objDto.getCurrencySymbol()+" - "+objDto.getCurrencyName());

			}
			tx.commit();

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			logger.error(e);
			System.out
			.println("while retrieving currencyListData in BaseDAOIMpl"
					+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while retrieving currencyListData in BaseDAOIMpl"
					+ e);
		} finally {

			HibernetFactory.closeSession();
		}

		return currencyList;
	}

	public Map issuerCurrencyListData() throws TPlusException {

		Map currencyList = new TreeMap();
		//Transaction tx = null;

		try {

			Session session = HibernetFactory.currentSession();
			//tx = session.beginTransaction();
			Query qry = session.createQuery("From IssuerCurrencyDto");
			List listCurrency = qry.list();
			for (Iterator it = listCurrency.iterator(); it.hasNext();) {
				IssuerCurrencyDto objDto = new IssuerCurrencyDto();
				objDto = (IssuerCurrencyDto) it.next();
				currencyList.put(objDto.getCurrencyCode(), objDto.getCurrencySymbol()+" - "+objDto.getCurrencyName());

			}
			//tx.commit();

		} catch (Exception e) {
			/*if (tx != null) {
				tx.rollback();
			}*/
			logger.error(e);
			System.out
			.println("while retrieving currencyListData in BaseDAOIMpl"
					+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while retrieving currencyListData in BaseDAOIMpl"
					+ e);
		} finally {

			HibernetFactory.closeSession();
		}

		return currencyList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.transinfo.cacis.dataacess.dao.BaseDAO#branchListData() this for
	 * for getting the branch list from Branch table
	 */
	public Map branchListData(String issuerId) throws TPlusException {

		Map branchList = new TreeMap();
		Transaction tx = null;
		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			Query qry = session.createQuery("From BranchDto where issuerId ='"
					+ issuerId + "' and status='A'");
			List listBranches = qry.list();
			for (Iterator it = listBranches.iterator(); it.hasNext();) {
				BranchDto objDto = new BranchDto();
				objDto = (BranchDto) it.next();

				branchList.put(objDto.getBranchId(), objDto.getBranchName());

			}
			tx.commit();

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			logger.error(e);
			System.out.println("while retrieving branchListData in BaseDAOIMpl"
					+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while retrieving branchListData in BaseDAOIMpl" + e);
		} finally {

			HibernetFactory.closeSession();
		}
		return branchList;
	}

	public Map customerTypeListData(String issuerId) throws TPlusException {

		Map customerTypeList = new TreeMap();
		Transaction tx = null;

		try {

			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			Query qry = session
			.createQuery("From CustomerTypeDto where issuerId ='"
					+ issuerId + "' AND status = 'A' ");
			List listCusomerTypes = qry.list();
			for (Iterator it = listCusomerTypes.iterator(); it.hasNext();) {
				CustomerTypeDto objDto = new CustomerTypeDto();
				objDto = (CustomerTypeDto) it.next();
				customerTypeList.put(objDto.getCustomerTypeId(), objDto
						.getCustomerType());

			}
			tx.commit();

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			logger.error(e);
			System.out
			.println("while retrieving customerTypeListData in BaseDAOIMpl"
					+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while retrieving customerTypeListData in BaseDAOIMpl"
					+ e);
		} finally {

			HibernetFactory.closeSession();
		}
		return customerTypeList;
	}

	public Map cardTypeListData(String issuerId) throws TPlusException {

		Map cardTypeList = new TreeMap();
		Transaction tx = null;
		try {

			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			Query qry = session
			.createQuery("From CardTypeDto where issuerId ='"
					+ issuerId + "' and status='A' ");
			List listCardTypes = qry.list();
			for (Iterator it = listCardTypes.iterator(); it.hasNext();) {
				CardTypeDto objDto = new CardTypeDto();
				objDto = (CardTypeDto) it.next();
				cardTypeList.put(objDto.getCardTypeId(), objDto.getCardType());

			}
			tx.commit();

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			logger.error(e);
			System.out
			.println("while retrieving cardTypeListData in BaseDAOIMpl"
					+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while retrieving cardTypeListData in BaseDAOIMpl"
					+ e);
		} finally {

			HibernetFactory.closeSession();
		}
		return cardTypeList;
	}

	public Map cardProductTypeListData() throws TPlusException {

		Map cardProductTypeList = new TreeMap();
		Transaction tx = null;

		try {

			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			Query qry = session.createQuery("From CardProductTypeDto");
			List listCardProductTypes = qry.list();
			for (Iterator it = listCardProductTypes.iterator(); it.hasNext();) {
				CardProductTypeDto objDto = new CardProductTypeDto();
				objDto = (CardProductTypeDto) it.next();
				cardProductTypeList.put(
						new Long(objDto.getCardProductTypeId()), objDto
						.getCardProductType());

			}
			tx.commit();

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			logger.error(e);
			System.out
			.println("while retrieving cardProductTypeListData in BaseDAOIMpl"
					+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while retrieving cardProductTypeListData in BaseDAOIMpl"
					+ e);
		} finally {

			HibernetFactory.closeSession();
		}
		return cardProductTypeList;
	}

	public Map cardProductListData(String issuerId) throws TPlusException {

		Map cardProductList = new TreeMap();
		//Transaction tx = null;

		try {

			Session session = HibernetFactory.currentSession();
			//tx = session.beginTransaction();
			Query qry = session
			.createQuery("From CardProductDto where issuerId ='"
					+ issuerId + "' and status='A' ");
			List listCardTypes = qry.list();
			for (Iterator it = listCardTypes.iterator(); it.hasNext();) {
				CardProductDto objDto = new CardProductDto();
				objDto = (CardProductDto) it.next();
				cardProductList.put(objDto.getCardProductId(), objDto
						.getCardProductName());

			}
			//tx.commit();

		} catch (Exception e) {
			/*if (tx != null) {
				tx.rollback();
			}*/
			logger.error(e);
			System.out
			.println("while retrieving cardProductListData in BaseDAOIMpl"
					+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while retrieving cardProductListData in BaseDAOIMpl"
					+ e);
		} finally {

			HibernetFactory.closeSession();
		}
		return cardProductList;
	}

	// for applicationform_docproofs
	public Map documentProofList(String issuerId) throws TPlusException {

		Map documentProofList = new TreeMap();
		Transaction tx = null;
		try {

			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			Query qry = session
			.createQuery("From ApplicationDocProofDto adpd where adpd.issuerId ='"
					+ issuerId + "'");
			List listDocs = qry.list();
			for (Iterator it = listDocs.iterator(); it.hasNext();) {
				ApplicationDocProofDto objDto = new ApplicationDocProofDto();
				objDto = (ApplicationDocProofDto) it.next();
				documentProofList.put(objDto.getAppDocumentId(), objDto
						.getAppDocumentType());

			}
			tx.commit();

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			logger.error(e);
			System.out
			.println("while retrieving documentProofList in BaseDAOIMpl"
					+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while retrieving documentProofList in BaseDAOIMpl"
					+ e);
		} finally {
			HibernetFactory.closeSession();
		}
		return documentProofList;
	}

	/**
	 * This method is to retrieve the record for the search criteria
	 */

	public ArrayList getSearchList(String Sql, int PageNo)
	throws TPlusException {
		ArrayList arlResultData = new ArrayList();
		CommonDataBean objReport = new CommonDataBean();
		Class adminReportClass = objReport.getClass();
		Object[] arrObj = new Object[1];
		Class[] arrClass = new Class[1];
		arrClass[0] = new String("").getClass();
		Method method = null;
		// int colCount = 0;

		try {
			Session session = HibernetFactory.currentSession();
			Query qry = session.createQuery(Sql);
			System.out.println("PageNOOO=" + PageNo);
			qry.setFirstResult(PageNo * 10);
			qry.setMaxResults(objReport.RECORDS_IN_PAGE);
			List listCardTypes = qry.list();
			for (Iterator it = listCardTypes.iterator(); it.hasNext();) {

				int intCount = 0;
				Object[] temp = (Object[]) it.next();

				while (intCount < temp.length) {
					if (temp[intCount] == null) {
						arrObj[0] = (Object) temp[intCount];
					} else {

						arrObj[0] = ((Object) temp[intCount]).toString();
					}
					method = adminReportClass.getMethod("setColumn"
							+ (intCount + 1), arrClass);
					method.invoke(objReport, arrObj);
					intCount++;
				}
				arlResultData.add(objReport);
				objReport = new CommonDataBean();
			}
		} catch (Exception e) {
			logger.error(e);
			System.out.println("*** Error In SearchList Method ***" + e);
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while retrieving searchList Info" + e);
		} finally {
			HibernetFactory.closeSession();
		}

		return arlResultData;

	}

	public ArrayList getSearchTranxList(String Sql, int PageNo)
	throws TPlusException {
		ArrayList arlResultData = new ArrayList();
		CommonDataBean objReport = new CommonDataBean();
		Class adminReportClass = objReport.getClass();
		Object[] arrObj = new Object[1];
		Class[] arrClass = new Class[1];
		arrClass[0] = new String("").getClass();
		Method method = null;
		// int colCount = 0;

		try {
			Session session = HibernetFactory.currentSession();
			Query qry = session.createQuery(Sql);
			System.out.println("PageNOOO=" + PageNo);
			qry.setFirstResult(PageNo * 100);
			qry.setMaxResults(objReport.RECORDS_IN_TRANX_PAGE);
			List listCardTypes = qry.list();
			for (Iterator it = listCardTypes.iterator(); it.hasNext();) {

				int intCount = 0;
				Object[] temp = (Object[]) it.next();

				while (intCount < temp.length) {
					if (temp[intCount] == null) {
						arrObj[0] = (Object) temp[intCount];
					} else {

						arrObj[0] = ((Object) temp[intCount]).toString();
					}
					method = adminReportClass.getMethod("setColumn"
							+ (intCount + 1), arrClass);
					method.invoke(objReport, arrObj);
					intCount++;
				}
				arlResultData.add(objReport);
				objReport = new CommonDataBean();
			}
		} catch (Exception e) {
			logger.error(e);
			System.out.println("*** Error In SearchList Method ***" + e);
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while retrieving searchList Info" + e);
		} finally {
			HibernetFactory.closeSession();
		}

		return arlResultData;

	}
	
	public ArrayList getSearchListNoPageNo(String Sql)
	throws TPlusException {
		ArrayList arlResultData = new ArrayList();
		CommonDataBean objReport = new CommonDataBean();
		Class adminReportClass = objReport.getClass();
		Object[] arrObj = new Object[1];
		Class[] arrClass = new Class[1];
		arrClass[0] = new String("").getClass();
		Method method = null;
		// int colCount = 0;

		try {
			Session session = HibernetFactory.currentSession();
			Query qry = session.createQuery(Sql);
			List listCardTypes = qry.list();
			for (Iterator it = listCardTypes.iterator(); it.hasNext();) {

				int intCount = 0;
				Object[] temp = (Object[]) it.next();

				while (intCount < temp.length) {
					if (temp[intCount] == null) {
						arrObj[0] = (Object) temp[intCount];
					} else {

						arrObj[0] = ((Object) temp[intCount]).toString();
					}
					method = adminReportClass.getMethod("setColumn"+ (intCount + 1), arrClass);
					method.invoke(objReport, arrObj);
					intCount++;
				}
				arlResultData.add(objReport);
				objReport = new CommonDataBean();
			}
		} catch (Exception e) {
			logger.error(e);
			System.out.println("*** Error In SearchList Method ***" + e);
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while retrieving searchList Info" + e);
		} finally {
			HibernetFactory.closeSession();
		}

		return arlResultData;

	}

	public int getSearchTotalCount(String Sql) throws TPlusException {

		int totalCount = 0;

		try {
			Session session = HibernetFactory.currentSession();
			Query qry = session.createQuery(Sql);

			List list = qry.list();
			totalCount = ((Integer) list.get(0)).intValue();

		} catch (Exception e) {
			logger.error(e);
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while retrieving getSearchTotalCount Info" + e);
		} finally {
			HibernetFactory.closeSession();
		}

		return totalCount;

	}

	/**
	 * This method is to retrieve cardProcessStatusList for the search criteria
	 */

	public Map cardProcessStatusList() throws TPlusException {

		Map cardProcessList = new HashMap();
		try {

			Session session = HibernetFactory.currentSession();
			Transaction tx = session.beginTransaction();
			Query qry = session
			.createQuery("From CardProcessStatusDto cps where cps.statusId > 5 and cps.statusId < 8 ");
			List listDocs = qry.list();
			for (Iterator it = listDocs.iterator(); it.hasNext();) {
				CardProcessStatusDto objDto = new CardProcessStatusDto();
				objDto = (CardProcessStatusDto) it.next();
				cardProcessList.put(new Integer(objDto.getStatusId()), objDto
						.getDescriptin());
			}

			tx.commit();

		} catch (Exception e) {
			logger.error(e);
			System.out.println("Error" + e);
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while retrieving cardProcessListData Info"
					+ e.getMessage());
		} finally {
			HibernetFactory.closeSession();
		}
		return cardProcessList;
	}

	/**
	 * This method is to retrieve List from cardStatus table.
	 */

	public Map cardStatusListData(int fromStatusId) throws TPlusException {

		Map cardStatusList = new HashMap();
		try {

			Session session = HibernetFactory.currentSession();
			Transaction tx = session.beginTransaction();
			Query qry = session
			.createQuery("From CardStatusDto cps where cps.cardStatusId>"
					+ fromStatusId + " order by cps.description ");
			List listDocs = qry.list();
			for (Iterator it = listDocs.iterator(); it.hasNext();) {
				CardStatusDto objDto = new CardStatusDto();
				objDto = (CardStatusDto) it.next();
				cardStatusList.put(new Long(objDto.getCardStatusId()), objDto
						.getDescription());
			}

			tx.commit();

		} catch (Exception e) {
			logger.error(e);
			System.out.println("Error" + e);
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while retrieving cardProcessListData Info"
					+ e.getMessage());
		} finally {
			HibernetFactory.closeSession();
		}
		return cardStatusList;
	}

	/* ################new methods added################ */

	/*
	 * This method works same as getSearchList but can return maximum of 1000
	 * records, unlike getSearchList method which returns only 10 records
	 */

	public ArrayList getList(String Sql) throws TPlusException {
		ArrayList arlResultData = new ArrayList();
		CommonDataBean objReport = new CommonDataBean();
		Class adminReportClass = objReport.getClass();
		Object[] arrObj = new Object[1];
		Class[] arrClass = new Class[1];
		arrClass[0] = new String("").getClass();
		Method method = null;
		// int colCount = 0;

		try {
			Session session = HibernetFactory.currentSession();
			Query qry = session.createQuery(Sql);

			qry.setFirstResult(0);
			qry.setMaxResults(1000);
			List listCardTypes = qry.list();
			for (Iterator it = listCardTypes.iterator(); it.hasNext();) {

				int intCount = 0;
				Object[] temp = (Object[]) it.next();

				while (intCount < temp.length) {
					if (temp[intCount] == null) {
						arrObj[0] = (Object) temp[intCount];
					} else {

						arrObj[0] = ((Object) temp[intCount]).toString();
					}

					method = adminReportClass.getMethod("setColumn"
							+ (intCount + 1), arrClass);
					method.invoke(objReport, arrObj);
					intCount++;
				}
				arlResultData.add(objReport);
				objReport = new CommonDataBean();
			}
		} catch (Exception e) {
			logger.error(e);
			System.out.println("*** Error In SearchList Method ***" + e);
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while retrieving searchList Info" + e);
		} finally {
			HibernetFactory.closeSession();
		}

		return arlResultData;

	}

	public Map switchTypeList() throws TPlusException {

		Map switchTypeList = new HashMap();
		try {

			Session session = HibernetFactory.currentSession();
			Transaction tx = session.beginTransaction();
			Query qry = session.createQuery("From SwitchTypeDto st ");
			List listDocs = qry.list();
			for (Iterator it = listDocs.iterator(); it.hasNext();) {
				SwitchTypeDto objDto = new SwitchTypeDto();
				objDto = (SwitchTypeDto) it.next();
				switchTypeList.put(objDto.getSwitchType(), objDto
						.getSwitchDesc());
			}

			tx.commit();

		} catch (Exception e) {
			logger.error(e);
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while retrieving switchTypeList Info" + e);
		} finally {
			HibernetFactory.closeSession();
		}
		return switchTypeList;
	}

	/*
	 * This method retrieves statusList from CodeMasterDto based on the groupId
	 * passed
	 */

	public Map statusListData(String groupId) throws TPlusException {
		Map codeList = new TreeMap();
		try {
			Session session = HibernetFactory.currentSession();
			Transaction tx = session.beginTransaction();
			Query qry = session
			.createQuery("From CodeMasterDto cm where cm.id.groupId = '"
					+ groupId + "' ");

			List listDocs = qry.list();
			System.out.println("Class in Status ===>"
					+ listDocs.get(0).getClass().getName());

			for (Iterator it = listDocs.iterator(); it.hasNext();) {
				CodeMasterDto objCodeDto = new CodeMasterDto();
				objCodeDto = (CodeMasterDto) it.next();
				codeList.put(objCodeDto.id.getCodeId(), objCodeDto
						.getCodeDesc());
			}
			tx.commit();

		} catch (Exception e) {
			logger.error(e);
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while retrieving getStatus Info" + e);
		} finally {
			HibernetFactory.closeSession();
		}
		return codeList;
	}

	// Screen: Dispatch Letter
	// Cardholder Letter History
	public Map letterTypesList(String Category) throws TPlusException {
		Map letterTypesListData = new HashMap();
		try {
			Session session = HibernetFactory.currentSession();
			Transaction tx = session.beginTransaction();
			String strQry = "From LetterTypesListDto lt ";
			if (!Category.trim().equals("ALL"))
				strQry += "where lt.category = '" + Category + "' ";
			Query qry = session.createQuery(strQry);
			List listletterTypes = qry.list();
			for (Iterator it = listletterTypes.iterator(); it.hasNext();) {
				LetterTypesListDto objDto = new LetterTypesListDto();
				objDto = (LetterTypesListDto) it.next();
				letterTypesListData.put(objDto.getLetterTypesId(), objDto
						.getLetterTypesName());
			}
			tx.commit();
		} catch (Exception e) {
			logger.error(e);
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while retrieving letterTypesListData Info" + e);
		} finally {
			HibernetFactory.closeSession();
		}
		return letterTypesListData;
	}

	public Map merchantCategoryListData() throws TPlusException {
		Map merchantCategoryList = new HashMap();
		try {
			Session session = HibernetFactory.currentSession();
			Transaction tx = session.beginTransaction();
			Query qry = session.createQuery("From MerchantCategoryDto");
			List merchantCategory = qry.list();
			for (Iterator it = merchantCategory.iterator(); it.hasNext();) {
				MerchantCategoryDto objDto = new MerchantCategoryDto();
				objDto = (MerchantCategoryDto) it.next();
				merchantCategoryList.put(objDto.getMcc(), objDto
						.getDescription());
			}
			tx.commit();

		} catch (Exception e) {
			logger.error(e);
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while retrieving merchantCategoryListData Info" + e);
		} finally {
			HibernetFactory.closeSession();
		}
		return merchantCategoryList;
	}

	public Map tranxCodeListData() throws TPlusException {
		Map tranxCodeList = new HashMap();
		try {
			Session session = HibernetFactory.currentSession();
			Transaction tx = session.beginTransaction();
			Query qry = session.createQuery("From TranxCodeDto");
			List tranxCode = qry.list();
			for (Iterator it = tranxCode.iterator(); it.hasNext();) {
				TranxCodeDto objDto = new TranxCodeDto();
				objDto = (TranxCodeDto) it.next();
				tranxCodeList.put(objDto.getTranxCode(), objDto.getMti());
			}
			tx.commit();
		} catch (Exception e) {
			logger.error(e);
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while retrieving tranxCodeListData Info" + e);
		} finally {
			HibernetFactory.closeSession();
		}
		return tranxCodeList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.transinfo.cacis.dataacess.dao.BaseDAO#issuerListData() this for
	 * for getting the issuer list from Issuer table
	 */
	public Map issuerListData(String issuerType) throws TPlusException {

		Map issuerList = new TreeMap();
		Transaction tx = null;
		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			StringBuffer sql = new StringBuffer();
			System.out.println("Issuer Type=" + issuerType);
			sql.append("From IssuerDto");
			if (issuerType != null && issuerType.equals("ISSUERS")) {
				sql.append(" where issuerId != 'ASP'");
			}

			Query qry = session.createQuery(sql.toString());
			List listIssuers = qry.list();
			for (Iterator it = listIssuers.iterator(); it.hasNext();) {
				IssuerDto objDto = new IssuerDto();
				objDto = (IssuerDto) it.next();

				issuerList.put(objDto.getIssuerId(), objDto.getIssuerName());

			}
			tx.commit();

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			logger.error(e);
			System.out.println("while retrieving branchListData in BaseDAOIMpl"
					+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while retrieving issuerListData in BaseDAOIMpl" + e);
		} finally {

			HibernetFactory.closeSession();
		}
		return issuerList;
	}

	// Dispute management
	public Map claimTypeListData() throws TPlusException {
		Map calimTypeList = new TreeMap();
		Transaction tx = null;
		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			Query qry = session.createQuery("From DisputeClaimTypesDto");
			List typeList = qry.list();
			for (Iterator it = typeList.iterator(); it.hasNext();) {
				DisputeClaimTypesDto objDto = new DisputeClaimTypesDto();
				objDto = (DisputeClaimTypesDto) it.next();
				calimTypeList.put(objDto.getClaimTypeId(), objDto
						.getClaimType());
			}
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			logger.error(e);
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while retrieving claimTypeListData " + e);
		}

		finally {
			HibernetFactory.closeSession();
		}
		return calimTypeList;
	}

	// Dispute management
	public Map reasonCodeListData() throws TPlusException {
		Map claimReasonList = new TreeMap();
		Transaction tx = null;
		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			Query qry = session.createQuery("From DisputeReasonDto");
			List resonList = qry.list();
			for (Iterator it = resonList.iterator(); it.hasNext();) {
				DisputeReasonDto objDto = new DisputeReasonDto();
				objDto = (DisputeReasonDto) it.next();
				claimReasonList.put(new Integer(objDto.getCode()), objDto
						.getDescription());
			}
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			logger.error(e);
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while retrieving claimReasonListData " + e);
		}

		finally {
			HibernetFactory.closeSession();
		}
		return claimReasonList;
	}

	public Map nonMandatoryDocumentsListData(String issuerId, int reasonCode)
	throws TPlusException {
		Map nonMandatoryDocumentsList = new HashMap();
		Transaction tx = null;
		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			Query qry = session
			.createQuery("from DisputeDocumentsDto dd where dd.id.issuerId ='"
					+ issuerId
					+ "' and dd.documentType = 'N' and dd.id.reasonCode = "
					+ reasonCode);
			List listDocuments = qry.list();
			for (Iterator it = listDocuments.iterator(); it.hasNext();) {
				DisputeDocumentsDto objDto = new DisputeDocumentsDto();
				objDto = (DisputeDocumentsDto) it.next();
				nonMandatoryDocumentsList.put(objDto.getId().getDocumentId(),
						objDto.getDocumentName());
			}
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			logger.error(e);
			System.out
			.println("while retrieving nonMandatoryDocumentsListData in BaseDAOIMpl"
					+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while retrieving nonMandatoryDocumentsListData in BaseDAOIMpl"
					+ e);
		} finally {
			HibernetFactory.closeSession();
		}
		return nonMandatoryDocumentsList;
	}

	public Map mandatoryDocumentsListData(String issuerId, int reasonCode)
	throws TPlusException {
		Map mandatoryDocumentsList = new HashMap();
		Transaction tx = null;
		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			Query qry = session
			.createQuery("from DisputeDocumentsDto dd where dd.id.issuerId ='"
					+ issuerId
					+ "' and dd.documentType = 'M' and dd.id.reasonCode = "
					+ reasonCode);
			List listDocuments = qry.list();
			for (Iterator it = listDocuments.iterator(); it.hasNext();) {
				DisputeDocumentsDto objDto = new DisputeDocumentsDto();
				objDto = (DisputeDocumentsDto) it.next();
				mandatoryDocumentsList.put(objDto.getId().getDocumentId(),
						objDto.getDocumentName());
			}
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			logger.error(e);
			System.out
			.println("while retrieving mandatoryDocumentsListData in BaseDAOIMpl"
					+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while retrieving mandatoryDocumentsListData in BaseDAOIMpl"
					+ e);
		} finally {
			HibernetFactory.closeSession();
		}
		return mandatoryDocumentsList;
	}

	public Map requestDocumentsNameListData(String issuerId, String claimNo)
	throws TPlusException {
		Map DocumentsNameList = new HashMap();
		Transaction tx = null;
		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			String strSql = "select dd.id.documentId, dd.documentName ";
			strSql += "FROM DisputeRequestDocumentsDto drd, DisputeDocumentsDto dd, DisputeClaimFormDto dcf ";
			strSql += "where dd.id.issuerId = '" + issuerId + "' ";
			strSql += "and dcf.claimReasonCode = dd.id.reasonCode ";
			strSql += "and drd.id.documentId = dd.id.documentId ";
			strSql += "and drd.id.claimNumber = '" + claimNo + "' ";
			strSql += "and drd.id.claimNumber = dcf.claimNumber ";
			strSql += "and drd.docsUploaded = 'N' ";
			Query qry = session.createQuery(strSql);
			List listDocuments = qry.list();
			for (Iterator it = listDocuments.iterator(); it.hasNext();) {
				Object[] arrObj = new Object[2];
				Object[] temp = (Object[]) it.next();
				if (temp[0] == null || temp[1] == null) {
					arrObj[0] = (Object) temp[0];
					arrObj[1] = (Object) temp[1];
				} else {
					arrObj[0] = ((Object) temp[0]).toString();
					arrObj[1] = ((Object) temp[1]).toString();
				}
				DocumentsNameList.put(arrObj[0], arrObj[1]);
			}
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			logger.error(e);
			System.out
			.println("while retrieving requestDocumentsNameListData in BaseDAOIMpl"
					+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while retrieving requestDocumentsNameListData in BaseDAOIMpl"
					+ e);
		} finally {
			HibernetFactory.closeSession();
		}
		return DocumentsNameList;
	}

	public Map responseDocumentsNameListData(String issuerId, String claimNo)
	throws TPlusException {
		Map DocumentsNameList = new HashMap();
		Transaction tx = null;
		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			String strSql = "select dd.id.documentId, dd.documentName ";
			strSql += "FROM DisputeResponseDocumentsDto drd, DisputeDocumentsDto dd, DisputeClaimFormDto dcf ";
			strSql += "where dd.id.issuerId = '" + issuerId + "' ";
			strSql += "and dcf.claimReasonCode = dd.id.reasonCode ";
			strSql += "and drd.id.documentId = dd.id.documentId ";
			strSql += "and drd.id.claimNumber = '" + claimNo + "' ";
			strSql += "and drd.id.claimNumber = dcf.claimNumber ";
			strSql += "and drd.docsUploaded = 'N' ";
			Query qry = session.createQuery(strSql);
			List listDocuments = qry.list();
			for (Iterator it = listDocuments.iterator(); it.hasNext();) {
				Object[] arrObj = new Object[2];
				Object[] temp = (Object[]) it.next();
				if (temp[0] == null || temp[1] == null) {
					arrObj[0] = (Object) temp[0];
					arrObj[1] = (Object) temp[1];
				} else {
					arrObj[0] = ((Object) temp[0]).toString();
					arrObj[1] = ((Object) temp[1]).toString();
				}
				DocumentsNameList.put(arrObj[0], arrObj[1]);
			}
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			logger.error(e);
			System.out
			.println("while retrieving responseDocumentsNameListData in BaseDAOIMpl"
					+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while retrieving responseDocumentsNameListData in BaseDAOIMpl"
					+ e);
		} finally {
			HibernetFactory.closeSession();
		}
		return DocumentsNameList;
	}

	public Map getUserList(String issuerid) throws TPlusException {

		Map users = new HashMap();
		try {
			System.out.println("issuerid==>" + issuerid);
			Session session = HibernetFactory.currentSession();
			Transaction tx = session.beginTransaction();
			Query qry = session
			.createQuery("From UserMasterDto um where um.id.issuerId ='"
					+ issuerid + "'  ");
			List listDocs = qry.list();

			for (Iterator it = listDocs.iterator(); it.hasNext();) {
				UserMasterDto objUserMaster = new UserMasterDto();
				objUserMaster = (UserMasterDto) it.next();
				users.put(objUserMaster.id.getUserId(), objUserMaster
						.getFirstName());
			}
			tx.commit();

		} catch (Exception e) {
			logger.error(e);
			System.out.println("Error in getUserList method" + e);
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while retrieving getUserList Info "
					+ e.getMessage());
		} finally {
			HibernetFactory.closeSession();
		}
		return users;
	}

	public Map cycleNoListData() throws TPlusException {

		Map cycleNoList = new TreeMap();
		Transaction tx = null;

		try {

			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			Query qry = session
			.createQuery("From CycleDto cycdto where cycdto.status='A' ");
			List listCurrency = qry.list();
			for (Iterator it = listCurrency.iterator(); it.hasNext();) {
				CycleDto objDto = new CycleDto();
				objDto = (CycleDto) it.next();
				//cycleNoList.put(objDto.getCycleNo(), objDto.getCycleNo() + " - " + objDto.getDayOfMonth());
				cycleNoList.put(objDto.getCycleNo(), objDto.getDayOfMonth());

			}
			tx.commit();

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			logger.error(e);
			System.out.println("while retrieving cycleNoList in BaseDAOIMpl"
					+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while retrieving cycleNoList in BaseDAOIMpl" + e);
		} finally {

			HibernetFactory.closeSession();
		}

		return cycleNoList;
	}

	public Map cardPINProcessStatusList() throws TPlusException {

		Map cardProcessList = new HashMap();
		try {

			Session session = HibernetFactory.currentSession();
			Transaction tx = session.beginTransaction();
			Query qry = session
			.createQuery("From CardProcessStatusDto cps where cps.statusId > 7 and cps.statusId < 10  ");
			List listDocs = qry.list();
			for (Iterator it = listDocs.iterator(); it.hasNext();) {
				CardProcessStatusDto objDto = new CardProcessStatusDto();
				objDto = (CardProcessStatusDto) it.next();
				cardProcessList.put(new Integer(objDto.getStatusId()), objDto
						.getDescriptin());
			}

			tx.commit();

		} catch (Exception e) {
			logger.error(e);
			System.out.println("Error" + e);
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while retrieving cardProcessListData Info"
					+ e.getMessage());
		} finally {
			HibernetFactory.closeSession();
		}
		return cardProcessList;
	}

	public Map cardDeliverProcessStatusList() throws TPlusException {

		Map cardProcessList = new HashMap();
		try {

			Session session = HibernetFactory.currentSession();
			Transaction tx = session.beginTransaction();
			Query qry = session
			.createQuery("From CardProcessStatusDto cps where cps.statusId > 9 and cps.statusId < 12 ");
			List listDocs = qry.list();
			for (Iterator it = listDocs.iterator(); it.hasNext();) {
				CardProcessStatusDto objDto = new CardProcessStatusDto();
				objDto = (CardProcessStatusDto) it.next();
				cardProcessList.put(new Integer(objDto.getStatusId()), objDto
						.getDescriptin());
			}

			tx.commit();

		} catch (Exception e) {
			logger.error(e);
			System.out.println("Error" + e);
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while retrieving cardProcessListData Info"
					+ e.getMessage());
		} finally {
			HibernetFactory.closeSession();
		}
		return cardProcessList;
	}

	public Map disputeGroupListData() throws TPlusException {

		Map disputeGropList = new HashMap();

		try {

			Session session = HibernetFactory.currentSession();
			Transaction tx = session.beginTransaction();

			Query qry = session
			.createQuery("From DisputeGroupsDto dgdto order by dgdto.groupId ");
			List listDocs = qry.list();

			for (Iterator it = listDocs.iterator(); it.hasNext();) {
				DisputeGroupsDto objDto = new DisputeGroupsDto();
				objDto = (DisputeGroupsDto) it.next();
				disputeGropList.put(objDto.getGroupId(), objDto.getGroupId()
						+ " - " + objDto.getGroupName());
			}

			disputeGropList = CacisissUtils.sortByComparator(disputeGropList);

			tx.commit();

		} catch (Exception e) {
			logger.error(e);
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while retrieving cardProcessListData Info"
					+ e.getMessage());
		} finally {
			HibernetFactory.closeSession();
		}

		return disputeGropList;
	}

	public Map disputeGroupListData(String cardNo) throws TPlusException {

		Map disputeGropList = new HashMap();

		try {
			
			String scheme = "";
			if(cardNo.startsWith("4")){
				scheme = "VI";
			}else if(cardNo.startsWith("3")){
				scheme = "JC";
			}

			Session session = HibernetFactory.currentSession();
			Transaction tx = session.beginTransaction();

			Query qry = session
			.createQuery("From DisputeGroupsDto dgdto where dgdto.scheme='"+scheme+"' order by dgdto.groupId ");
			List listDocs = qry.list();

			for (Iterator it = listDocs.iterator(); it.hasNext();) {
				DisputeGroupsDto objDto = new DisputeGroupsDto();
				objDto = (DisputeGroupsDto) it.next();
				disputeGropList.put(objDto.getGroupId(), objDto.getGroupId()
						+ " - " + objDto.getGroupName());
			}

			disputeGropList = CacisissUtils.sortByComparator(disputeGropList);

			tx.commit();

		} catch (Exception e) {
			logger.error(e);
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while retrieving cardProcessListData Info"
					+ e.getMessage());
		} finally {
			HibernetFactory.closeSession();
		}

		return disputeGropList;
	}

	public Map disputeMotoListData() throws TPlusException {

		Map disputeMotoList = new HashMap();

		try {

			Session session = HibernetFactory.currentSession();
			//Transaction tx = session.beginTransaction();

			Query qry = session
			.createQuery("From DisputeMotosDto dmdto order by dmdto.motoId ");
			List listDocs = qry.list();

			for (Iterator it = listDocs.iterator(); it.hasNext();) {
				DisputeMotosDto objDto = new DisputeMotosDto();
				objDto = (DisputeMotosDto) it.next();
				disputeMotoList.put(objDto.getMotoId(), objDto.getMotoCode() + " - " + objDto.getMotoDescription());
			}

			//disputeMotoList = CacisissUtils.sortByComparator(disputeMotoList);

			//tx.commit();

		} catch (Exception e) {
			logger.error(e);
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while retrieving cardProcessListData Info"
					+ e.getMessage());
		} finally {
			HibernetFactory.closeSession();
		}

		return disputeMotoList;
	}

	public Map disputeGroupReasonListData(String groupId) throws TPlusException {

		Map disputeGroupReasonList = new HashMap();
		StringBuffer sbf = new StringBuffer();

		try {

			Session session = HibernetFactory.currentSession();
			Transaction tx = session.beginTransaction();

			sbf.append("from DisputeGroupDetailsDto ddto ");

			if (groupId != null && !"".equals(groupId)) {
				sbf
				.append("where ddto.disputeGroup.groupId='" + groupId
						+ "' ");
			}

			// sbf.append("order by ddto.disputeGroup.groupId ");
			sbf.append("order by ddto.chargeBackReason ");

			Query qry = session.createQuery(sbf.toString());
			List listDocs = qry.list();

			for (Iterator it = listDocs.iterator(); it.hasNext();) {
				DisputeGroupDetailsDto objDto = new DisputeGroupDetailsDto();
				objDto = (DisputeGroupDetailsDto) it.next();
				disputeGroupReasonList.put(objDto.getReasonCode(), objDto
						.getChargeBackReason()
						+ " - "
						+ objDto.getReasonCode()
						+ " - "
						+ objDto.getDisputeGroup().getGroupId());
			}

			// disputeGroupReasonList =
			// CacisissUtils.sortByComparator(disputeGroupReasonList);

			tx.commit();

		} catch (Exception e) {
			logger.error(e);
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while retrieving cardProcessListData Info"
					+ e.getMessage());
		} finally {
			HibernetFactory.closeSession();
		}

		return disputeGroupReasonList;
	}

	public Map responseCodesList() throws TPlusException {

		Map responseCodeList = new HashMap();

		try {

			Session session = HibernetFactory.currentSession();

			Query qry = session
			.createQuery("From ResponseCodeDto rcdto order by reason ");
			List listDocs = qry.list();

			for (Iterator it = listDocs.iterator(); it.hasNext();) {
				ResponseCodeDto objDto = new ResponseCodeDto();
				objDto = (ResponseCodeDto) it.next();
				responseCodeList.put(objDto.getResponseCode(), objDto
						.getResponseCode()
						+ " - " + objDto.getReason());
			}

			responseCodeList = CacisissUtils.sortByComparator(responseCodeList);

		} catch (Exception e) {
			logger.error(e);
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while retrieving cardProcessListData Info"
					+ e.getMessage());
		} finally {
			HibernetFactory.closeSession();
		}

		return responseCodeList;
	}

	public Map stateList() throws TPlusException {

		Map stateList = new TreeMap();
		Transaction tx = null;

		try {

			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			Query qry = session.createQuery("from StateDto ");

			List listCardTypes = qry.list();

			for (Iterator it = listCardTypes.iterator(); it.hasNext();) {
				StateDto objStateDto = new StateDto();
				objStateDto = (StateDto) it.next();
				stateList.put(objStateDto.getStateId(), objStateDto.getStateDes());
			}

			tx.commit();

		} catch (Exception e) {

			if (tx != null) {
				tx.rollback();
			}
			logger.error(e);
			System.out.println("while retrieving stateList in BaseDAOIMpl " + e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR, "Error: while retrieving stateList in BaseDAOIMpl " + e);

		} finally {

			HibernetFactory.closeSession();
		}

		return stateList;
	}

	public Map cityList(String state) throws TPlusException {

		Map cityList = new TreeMap();
		Transaction tx = null;

		try {

			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			Query qry = session.createQuery("from CityDto dto where dto.state.stateId='"+state+"' ");

			List listCardTypes = qry.list();

			for (Iterator it = listCardTypes.iterator(); it.hasNext();) {
				CityDto objCityDto = new CityDto();
				objCityDto = (CityDto) it.next();
				cityList.put(objCityDto.getCityId(), objCityDto.getCityDes());
			}

			tx.commit();

		} catch (Exception e) {

			if (tx != null) {
				tx.rollback();
			}
			logger.error(e);
			System.out.println("while retrieving cityList in BaseDAOIMpl " + e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR, "Error: while retrieving cityList in BaseDAOIMpl " + e);

		} finally {

			HibernetFactory.closeSession();
		}

		return cityList;
	}

	public Map townshipList(String city) throws TPlusException {

		Map townshipList = new TreeMap();
		Transaction tx = null;

		try {

			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			Query qry = session.createQuery("from TownShipDto dto where dto.city.cityId='"+city+"' ");

			List listCardTypes = qry.list();

			for (Iterator it = listCardTypes.iterator(); it.hasNext();) {
				TownShipDto objTownShipDto = new TownShipDto();
				objTownShipDto = (TownShipDto) it.next();
				townshipList.put(objTownShipDto.getTownshipId(), objTownShipDto.getTownshipDes());
			}

			tx.commit();

		} catch (Exception e) {

			if (tx != null) {
				tx.rollback();
			}
			logger.error(e);
			System.out.println("while retrieving townshipList in BaseDAOIMpl " + e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR, "Error: while retrieving townshipList in BaseDAOIMpl " + e);

		} finally {

			HibernetFactory.closeSession();
		}

		return townshipList;
	}

	public ArrayList tranxTypeList(String tranxGroup) throws TPlusException {

		ArrayList tranxTypeList = new ArrayList();
		Transaction tx = null;

		try {

			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			Query qry = session.createQuery("from TranxTypeDto dto where dto.tranxGroup = 'T' ");

			List listTranxTypes = qry.list();

			for (Iterator it = listTranxTypes.iterator(); it.hasNext();) {
				TranxTypeDto objTranxTypeDto = new TranxTypeDto();
				objTranxTypeDto = (TranxTypeDto) it.next();
				tranxTypeList.add(objTranxTypeDto.getTranxType());
			}

			tx.commit();

		} catch (Exception e) {

			if (tx != null) {
				tx.rollback();
			}
			logger.error(e);
			System.out.println("while retrieving tranxTypeList in BaseDAOIMpl " + e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR, "Error: while retrieving tranxTypeList in BaseDAOIMpl " + e);

		} finally {

			HibernetFactory.closeSession();
		}

		return tranxTypeList;
	}
	
	@Override
	public Map codeMasterList() throws TPlusException {
		
		Map codeMasters = new HashMap();
		Transaction tx = null;

		try {

			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			Query qry = session.createQuery("from CodeMasterDto dto where dto.id.groupId = 'EMVPROFILEGRP'");

			List lstCodeMaster = qry.list();

			for (Iterator it = lstCodeMaster.iterator(); it.hasNext();) {
				CodeMasterDto objCodeMaster = new CodeMasterDto();
				objCodeMaster = (CodeMasterDto) it.next();
				codeMasters.put(objCodeMaster.id.getCodeId(), objCodeMaster.getCodeDesc());
			}

			tx.commit();

		} catch (Exception e) {

			if (tx != null) {
				tx.rollback();
			}
			logger.error(e);
			System.out.println("while retrieving cityList in BaseDAOIMpl " + e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR, "Error: while retrieving codeMaster in BaseDAOIMpl " + e);

		} finally {

			HibernetFactory.closeSession();
		}

		return codeMasters;
	}
	
	@Override
	public Map empProfileNameListData() throws TPlusException {
		
		Map empProfileNameList = new HashMap();
		Transaction tx = null;

		try {

			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			Query qry = session.createQuery("from EMVProfileDto dto where dto.status = 'A'");

			List lstEMVProflie = qry.list();

			for (Iterator it = lstEMVProflie.iterator(); it.hasNext();) {
				EMVProfileDto objEMVProfile = new EMVProfileDto();
				objEMVProfile = (EMVProfileDto) it.next();
				empProfileNameList.put(objEMVProfile.getEmvProfileName(), objEMVProfile.getEmvProfileName());
			}

			tx.commit();

		} catch (Exception e) {

			if (tx != null) {
				tx.rollback();
			}
			logger.error(e);
			System.out.println("while retrieving cityList in BaseDAOIMpl " + e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR, "Error: while retrieving emvProfileListData in BaseDAOIMpl " + e);

		} finally {

			HibernetFactory.closeSession();
		}

		return empProfileNameList;
	}

	@Override
	public Map getApplNameList() throws TPlusException {

		Map applNameList = new HashMap();
		Transaction tx = null;

		try {

			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			Query qry = session.createQuery("from ApplValidationDto dto where dto.status = 'A'");

			List lstAppVal = qry.list();

			for (Iterator it = lstAppVal.iterator(); it.hasNext();) {
				ApplValidationDto objApplValidationDto = new ApplValidationDto();
				objApplValidationDto = (ApplValidationDto) it.next();
				applNameList.put(objApplValidationDto.getApplValName(), objApplValidationDto.getApplValName());
			}

			tx.commit();

		} catch (Exception e) {

			if (tx != null) {
				tx.rollback();
			}
			logger.error(e);
			System.out.println("while retrieving getApplNameList in BaseDAOIMpl " + e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR, "Error: while retrieving getApplNameList in BaseDAOIMpl " + e);

		} finally {

			HibernetFactory.closeSession();
		}

		return applNameList;
		
	}

	@Override
	public Map getCodeMasterListByGroup(String groupName) throws TPlusException {
		
		Map criteriaList = new HashMap();
		Transaction tx = null;

		try {

			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			Query qry = session.createQuery(" from CodeMasterDto dto where dto.id.groupId = '"  + groupName + "'");

			List lstCodeMaster = qry.list();

			for (Iterator it = lstCodeMaster.iterator(); it.hasNext();) {
				CodeMasterDto objCodeMaster = new CodeMasterDto();
				objCodeMaster = (CodeMasterDto) it.next();
				criteriaList.put(objCodeMaster.id.getCodeId(), objCodeMaster.getCodeDesc());
			}

			tx.commit();

		} catch (Exception e) {

			if (tx != null) {
				tx.rollback();
			}
			logger.error(e);
			System.out.println("while retrieving cityList in BaseDAOIMpl " + e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR, "Error: while retrieving codeMaster in BaseDAOIMpl " + e);

		} finally {

			HibernetFactory.closeSession();
		}

		return criteriaList;
	}

	@Override
	public Map getScoreNameList() throws TPlusException {

		Map scoreNameList = new HashMap();
		Transaction tx = null;

		try {

			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			Query qry = session.createQuery("from CreditScoringDto dto where dto.status = 'A'");

			List lstAppVal = qry.list();

			for (Iterator it = lstAppVal.iterator(); it.hasNext();) {
				CreditScoringDto objCreditScoringDto = new CreditScoringDto();
				objCreditScoringDto = (CreditScoringDto) it.next();
				scoreNameList.put(objCreditScoringDto.getScoreId(), objCreditScoringDto.getScoreName());
			}

			tx.commit();

		} catch (Exception e) {

			if (tx != null) {
				tx.rollback();
			}
			logger.error(e);
			System.out.println("while retrieving getScoreNameList in BaseDAOIMpl " + e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR, "Error: while retrieving getScoreNameList in BaseDAOIMpl " + e);

		} finally {

			HibernetFactory.closeSession();
		}

		return scoreNameList;
	}

	@Override
	public Map getScreditNameList() throws TPlusException {

		Map scoreNameList = new HashMap();
		Transaction tx = null;

		try {

			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			Integer count = (Integer) session.createQuery("select count(*) from CreditScoringDto").uniqueResult();
			List lstAppVal = new ArrayList();
			if (count > 0) {
				Query qry = session.createQuery("from CreditScoringDto dto where dto.status = 'A'");
				lstAppVal = qry.list();
			} else {
				lstAppVal = null;
			}

			for (Iterator it = lstAppVal.iterator(); it.hasNext();) {
				CreditScoringDto objCreditScoringDto = new CreditScoringDto();
				objCreditScoringDto = (CreditScoringDto) it.next();
				scoreNameList.put(objCreditScoringDto.getCreditName(), objCreditScoringDto.getCreditName());
			}

			tx.commit();

		} catch (Exception e) {

			if (tx != null) {
				tx.rollback();
			}
			logger.error(e);
			System.out.println("while retrieving getScoreNameList in BaseDAOIMpl " + e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR, "Error: while retrieving getScoreNameList in BaseDAOIMpl " + e);

		} finally {

			HibernetFactory.closeSession();
		}

		return scoreNameList;
	}

	@Override
	public Map getCardProductNameList() throws TPlusException {

		Map cardProductNameList = new HashMap();
		Transaction tx = null;

		try {

			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			Query qry = session.createQuery("from CardProductDto dto where dto.status = 'A'");

			List lstAppVal = qry.list();

			for (Iterator it = lstAppVal.iterator(); it.hasNext();) {
				CardProductDto objCardProductDto = new CardProductDto();
				objCardProductDto = (CardProductDto) it.next();
				cardProductNameList.put(objCardProductDto.getCardProductId(), objCardProductDto.getCardProductName());
			}

			tx.commit();

		} catch (Exception e) {

			if (tx != null) {
				tx.rollback();
			}
			logger.error(e);
			System.out.println("while retrieving getCardProductNameList in BaseDAOIMpl " + e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR, "Error: while retrieving getCardProductNameList in BaseDAOIMpl " + e);

		} finally {

			HibernetFactory.closeSession();
		}

		return cardProductNameList;
	}

	@Override
	public Map getLetterCodeList() throws TPlusException {

		Map letterCodeList = new HashMap();
		Transaction tx = null;

		try {

			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			Query qry = session.createQuery("from LetterTemplateDto dto where dto.status = 'A'");

			List lstAppVal = qry.list();

			for (Iterator it = lstAppVal.iterator(); it.hasNext();) {
				LetterTemplateDto objLetterTemplateDto = new LetterTemplateDto();
				objLetterTemplateDto = (LetterTemplateDto) it.next();
				letterCodeList.put(objLetterTemplateDto.getLetterId(), objLetterTemplateDto.getDescription());
			}

			tx.commit();

		} catch (Exception e) {

			if (tx != null) {
				tx.rollback();
			}
			logger.error(e);
			System.out.println("while retrieving getLetterCodeList in BaseDAOIMpl " + e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR, "Error: while retrieving getLetterCodeList in BaseDAOIMpl " + e);

		} finally {

			HibernetFactory.closeSession();
		}

		return letterCodeList;
	}

	@Override
	public Map getLetterCategoryList(String issuerId) throws TPlusException {

		Map letterCodeList = new HashMap();
		Transaction tx = null;

		try {

			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			Query qry = session.createQuery("from LetterCategoryDto dto where dto.issuerId =:issuerid ").setString("issuerid", issuerId);

			List lstAppVal = qry.list();

			for (Iterator it = lstAppVal.iterator(); it.hasNext();) {
				LetterCategoryDto objLetterCategoryDto = new LetterCategoryDto();
				objLetterCategoryDto = (LetterCategoryDto) it.next();
				letterCodeList.put(objLetterCategoryDto.getId(), objLetterCategoryDto.getDescription());
			}

			tx.commit();

		} catch (Exception e) {

			if (tx != null) {
				tx.rollback();
			}
			logger.error(e);
			System.out.println("while retrieving getLetterCategoryList in BaseDAOIMpl " + e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR, "Error: while retrieving getLetterCategoryList in BaseDAOIMpl " + e);

		} finally {

			HibernetFactory.closeSession();
		}

		return letterCodeList;
	}
	
	@Override
	public Map delinquencyPolicyList(String issuerId) throws TPlusException {

		Map delinquencyPolicyList = new LinkedHashMap();
		Transaction tx = null;

		try {

			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			Query qry = session
					.createQuery("from DelinquencyPolicyDto where status = 'A' and issuerId='"
							+ issuerId + "' order by description");

			List policyList = qry.list();

			for (Iterator it = policyList.iterator(); it.hasNext();) {
				DelinquencyPolicyDto objDto = new DelinquencyPolicyDto();
				objDto = (DelinquencyPolicyDto) it.next();
				delinquencyPolicyList.put(objDto.getPolicyId(),
						objDto.getDescription());
			}

			tx.commit();

		} catch (Exception e) {

			if (tx != null) {
				tx.rollback();
			}
			logger.error(e);
			System.out
					.println("while retrieving delinquencyPolicyList in BaseDAOIMpl "
							+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while retrieving delinquencyPolicyList in BaseDAOIMpl "
							+ e);

		} finally {

			HibernetFactory.closeSession();
		}

		return delinquencyPolicyList;
	}
	
	@Override
	public Map cardProductList(String issuerId) throws TPlusException {

		Map cardProduct = new HashMap();
		Transaction tx = null;

		try {

			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			Query qry = session
					.createQuery("from CardProductDto where status = 'A' and issuerId='"
							+ issuerId + "'");

			List lstCodeMaster = qry.list();

			for (Iterator it = lstCodeMaster.iterator(); it.hasNext();) {
				CardProductDto objCardProduct = new CardProductDto();
				objCardProduct = (CardProductDto) it.next();
				cardProduct.put(objCardProduct.getCardProductId(),
						objCardProduct.getCardProductName());
			}

			tx.commit();

		} catch (Exception e) {

			if (tx != null) {
				tx.rollback();
			}
			logger.error(e);
			System.out.println("while retrieving cityList in BaseDAOIMpl "
					+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while retrieving codeMaster in BaseDAOIMpl " + e);

		} finally {

			HibernetFactory.closeSession();
		}

		return cardProduct;
	}

	@Override
	public Map branchList(String issuerId) throws TPlusException {

		Map branchList = new HashMap();
		Transaction tx = null;

		try {

			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			Query qry = session
					.createQuery("from BranchDto where status = 'A' and issuerId='"
							+ issuerId + "'");

			List lstCodeMaster = qry.list();

			for (Iterator it = lstCodeMaster.iterator(); it.hasNext();) {
				BranchDto objBranch = new BranchDto();
				objBranch = (BranchDto) it.next();
				branchList.put(objBranch.getBranchId(),
						objBranch.getBranchName());
			}

			tx.commit();

		} catch (Exception e) {

			if (tx != null) {
				tx.rollback();
			}
			logger.error(e);
			System.out.println("while retrieving cityList in BaseDAOIMpl "
					+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while retrieving codeMaster in BaseDAOIMpl " + e);

		} finally {

			HibernetFactory.closeSession();
		}

		return branchList;
	}

	@Override
	public boolean create(InstantCardDto objCardBatchDto) throws TPlusException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Collection search(SearchInstantCardDto objDto, int pageNo)
			throws TPlusException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InstantCardDto getCardBatchDetail(String batchId)
			throws TPlusException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CodeMasterDto getStatusDesc(String status) throws TPlusException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(InstantCardDto objDto) throws TPlusException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(InstantCardDto objDto) throws TPlusException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public CardProductDto getCardProductName(String id) throws TPlusException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BranchDto getBranchName(String id) throws TPlusException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean process(InstantCardDto objDto) throws TPlusException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean authorize(InstantCardDto objDto) throws TPlusException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int checkExitsUser(CardBatchForm objForm) throws TPlusException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int checkBatchAuthUser(CardBatchForm objForm) throws TPlusException {
		// TODO Auto-generated method stub
		return 0;
	}

	public boolean create(DelinquencyPolicyDto objCardBatchDto)
			throws TPlusException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int checkExitsRecord(DelinquencyPolicyDto objPolicy)
			throws TPlusException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public DelinquencyPolicyDto getPolicyDetail(String id)
			throws TPlusException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(DelinquencyPolicyDto objDto) throws TPlusException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int checkExitsRecord(DelinquencyFeeSetupDto objPolicy)
			throws TPlusException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean create(DelinquencyFeeSetupDto objDto) throws TPlusException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean checkOverlap(DelinquencyFeeSetupForm objForm, Integer no)
			throws TPlusException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String agingBeginEndRange(DelinquencyFeeSetupForm objForm)
			throws TPlusException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DelinquencyFeeSetupDto getFeeDetail(String id) throws TPlusException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(DelinquencyFeeSetupDto objDto) throws TPlusException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean checkUpdateUser(DelinquencyFeeSetupForm objForm)
			throws TPlusException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getPolicyName(DelinquencyFeeSetupForm objForm)
			throws TPlusException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection search(DelinquencyNotificationSetupSearchDto objDto,
			int pageNo) throws TPlusException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean checkExitsRecord(DelinquencyNotificationSetupForm objForm,
			Integer no) throws TPlusException {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean create(DelinquencyNotificationSetupDto objDto)
			throws TPlusException {
		// TODO Auto-generated method stub
		return false;
	}

	public String getPolicyName(String policyName) throws TPlusException {
		// TODO Auto-generated method stub
		return policyName;
	}

	public DelinquencyNotificationSetupDto getNotificationDetail(String id)
			throws TPlusException {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean update(DelinquencyNotificationSetupDto objDto)
			throws TPlusException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Map getUserType() throws TPlusException {
		Map userTypeList = new TreeMap();
		Transaction tx = null;

		try {

			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			Query qry = session
			.createQuery("From UserLevelDto ");
			List listUserLevel = qry.list();
			for (Iterator it = listUserLevel.iterator(); it.hasNext();) {
				UserLevelDto objDto = new UserLevelDto();
				objDto = (UserLevelDto) it.next();
				userTypeList.put(objDto.getUserType(), objDto
						.getUserTypeDesc());

			}
			tx.commit();

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			logger.error(e);
			System.out
			.println("while retrieving getUserType in BaseDAOIMpl"
					+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while retrieving getUserType in BaseDAOIMpl"
					+ e);
		} finally {

			HibernetFactory.closeSession();
		}
		return userTypeList;
	}

}
