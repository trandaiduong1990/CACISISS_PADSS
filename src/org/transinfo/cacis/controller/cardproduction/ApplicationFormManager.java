package org.transinfo.cacis.controller.cardproduction;

import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

import org.apache.log4j.Logger;
import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.common.constants.CommonCodes;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.cardproduction.ApplicationFormDAO;
import org.transinfo.cacis.dto.cardproduction.ApplicationFormDto;
import org.transinfo.cacis.dto.cardproduction.ApplicationFormSearchDto;

/**
 * A Business Delegate that abstracts out the business specific calls and
 * exceptions . The web tier does not need to know about the business-specific
 * details
 **/
@SuppressWarnings( { "unchecked" })
public class ApplicationFormManager {

	private Logger logger = Logger.getLogger(ApplicationFormManager.class);

	private ApplicationFormDAO objDAO;

	public ApplicationFormManager() throws Exception {
		objDAO = DAOFactory.getInstance().getApplicationFormDAO();
	}

	public Collection search(ApplicationFormSearchDto objSearchDto, int pageNo)
			throws TPlusException {

		Collection searchLst = null;

		try {
			searchLst = objDAO.search(objSearchDto, pageNo);

		} catch (Exception e) {
			logger.error(e);
			System.out.println("ApplicationFormManager Controller" + e);
			throw new TPlusException(
					"Error in ApplicationFormManager search method");
		}
		return searchLst;

	}

	public ApplicationFormDto getApplicationForm(String applicaionId)
			throws TPlusException {
		ApplicationFormDto objApplicationForm = null;

		try {
			System.out.println("++++Manager+++++appID: ++"+applicaionId);
			objApplicationForm = objDAO.getApplicationForm(applicaionId);

		} catch (Exception e) {
			logger.error(e);
			System.out.println("ApplicationFormManager Controller"
					+ e.getMessage());
			throw new TPlusException(
					"Error in ApplicationFormManager getApplicationForm method"
							+ e);
		}
		return objApplicationForm;
	}

	/*public boolean add(ApplicationFormDto objDto) throws TPlusException {
		System.out.println("Appl Form Controller");
		boolean add = false;
		Date date = new Date();
		objDto.setApplicationId(IdsGenartion.GenerateApplicationId());
		System.out.println("Appl Id=" + objDto.getApplicationId());
		objDto.setApplicationStatus(CommonCodes.APPLICATIONSTATUS_NEW);
		objDto.setApplicationType(CommonCodes.APPLICATIONTYPE_NEWCARD);
		objDto.setOpenDate(date);

		// to check existing record
		ApplicationMasterDto objAppMasterDto = new ApplicationMasterDto();
		objAppMasterDto.setApplicationType(objDto.getApplicationType());
		objAppMasterDto.setIdNumber(objDto.getIdNumber());

		try {
			if (!validate(objAppMasterDto, objDAO.ADD)) {
				System.out.println("Record Already Exists");
				logger.warn("Record Already Exists");
			} else {
				System.out.println("Record Already Not Exists");
				add = objDAO.add(objDto);
			}
		} catch (Exception e) {
			logger.error(new Object(), e);
			throw new TPlusException(
					"Error in ApplicationFormManager add method" + e);
		}
		return add;
	}*/

	public boolean add(ApplicationFormDto objDto) throws TPlusException {
		System.out.println("Appl Form Controller");
		boolean add = false;
		
		try {

			add = objDAO.add(objDto);

		} catch (Exception e) {
			logger.error(new Object(), e);
			throw new TPlusException(
					"Error in ApplicationFormManager add method" + e);
		}
		return add;
	}

	/*public boolean update(ApplicationFormDto objDto) throws TPlusException {

		boolean update = false;
		try {

			if (!validate(objDto, objDAO.UPDATE)) {

				System.out.println("Record Not Exists");
			} else {
				update = objDAO.update(objDto);
			}
		} catch (Exception e) {
			logger.error(new Object(), e);
			throw new TPlusException(
					"Error in ApplicationFormManager update method" + e);
		}
		return update;
	}*/

	public boolean update(ApplicationFormDto objDto) throws TPlusException {

		boolean update = false;
		try {
			
			update = objDAO.update(objDto);
			
		} catch (Exception e) {
			logger.error(new Object(), e);
			throw new TPlusException(
					"Error in ApplicationFormManager update method" + e);
		}
		return update;
	}

	/*public boolean delete(ApplicationFormDto objDto) throws TPlusException {

		boolean delete = false;
		objDto.setApplicationStatus(CommonCodes.APPLICATIONSTATUS_CLOSED);
		try {

			if (!validate(objDto, objDAO.UPDATE)) {
				System.out.println("Record Not Exists");
			} else {

				delete = objDAO.delete(objDto);
			}

		} catch (Exception e) {
			logger.error(new Object(), e);
			throw new TPlusException(
					"Error in ApplicationFormManager delete method" + e);
		}
		return delete;
	}*/

	public boolean delete(ApplicationFormDto objDto) throws TPlusException {

		boolean delete = false;
		objDto.setApplicationStatus(CommonCodes.APPLICATIONSTATUS_CLOSED);
		try {

			delete = objDAO.delete(objDto);
			
		} catch (Exception e) {
			logger.error(new Object(), e);
			throw new TPlusException(
					"Error in ApplicationFormManager delete method" + e);
		}
		return delete;
	}

	public boolean validate(Object obj, int mode) throws TPlusException {
		boolean rtnMessage = true;

		// ApplicationFormDto objApplication = (ApplicationFormDto)obj;

		if (mode == 0 && objDAO.checkExistrecord(obj) > 0) {
			rtnMessage = false;
		}
		if (mode == 1 && objDAO.checkExistrecord(obj) == 0) {
			rtnMessage = false;
		}

		return rtnMessage;
	}

	public boolean isDuplicateNRC(Object obj) throws TPlusException {
		boolean rtnMessage = false;

		/*if (objDAO.isDuplicateNRC(obj) > 0) {
			rtnMessage = true;
		}*/
		
		rtnMessage = objDAO.isDuplicateNRCNewMethod(obj);

		return rtnMessage;
	}

	public boolean validateUpdate(Object obj) throws TPlusException {
		boolean rtnMessage = true;

		if (objDAO.checkExistrecordOnUpdate(obj) > 0) {
			rtnMessage = false;
		}

		return rtnMessage;
	}

	public Map getProductList(String issuerId) throws TPlusException {
		Map cardProductList = new TreeMap();
		try {
			cardProductList = objDAO.cardProductListData(issuerId);
		} catch (Exception e) {
			logger.error(e);
			throw new TPlusException(
					"Error in ApplicationFormManager getProductList method" + e);
		}
		return cardProductList;
	}

	public static void main(String s[]) throws Exception {

		ApplicationFormManager objManager = new ApplicationFormManager();
		// ApplicationSetupForm app = new ApplicationSetupForm();
		// ApplicationFormDto objDto = new ApplicationFormDto();
		// objManager.countryListData(app);

		objManager.getApplicationForm("3");

		// adding

		/*
		 * objDto.setCardProductId("3");
		 * objDto.setCardProductName("MasterGold");
		 * objDto.setCardProductTypeId(1); objDto.setCardTypeId("1");
		 * objDto.setBin("123"); objDto.setDueDays(12);
		 * objDto.setExpireTime(02); objDto.setFloorLimit(211);
		 * objDto.setIssuerId("Issuer1"); objDto.setMaxCardLimitPerSal(22);
		 * objDto.setMaxCashLimit(12); objDto.setMaxCashLimitPerCrl(12);
		 * objDto.setMaxSuppLimit(21); objDto.setMinimumSalary(100);
		 * objDto.setRiskLastUpdatedBy("stayam"); objDto.setServiceCode("432");
		 * objDto.setStatus("A"); objDto.setTempCashLimitRaise(12);
		 * objDto.setTempCreditLimitRaise(21); objDto.setUpdatedDate(new
		 * Date()); objDto.setLastUpdatedBy("satyam");
		 * 
		 * String promotions[]= {"1"};
		 * 
		 * for(int i=0; i<promotions.length;i++) {
		 * objDto.getCardPromotions().put(promotions[i],new
		 * CardProductPromotionDto(new Date(),new
		 * Date(),objDto.getLastUpdatedBy())); }
		 */

		// objManager.add(objDto);
		// objManager.update(objDto);

		// Getting cardProduct
		// objDto.setCardProductId("3");
		// objDto= objManager.getCardProduct("3");
		// System.out.println("card product size="+objDto.getSelectedPromotions().length);

		// for search
		/*
		 * CardProductSearchDto objSearchDto = new CardProductSearchDto();
		 * objSearchDto.setCardProductId("3"); Collection listcol =
		 * objManager.search(objSearchDto);
		 * System.out.println("size="+cpd.getSelectedPromotions().length);
		 * System.out.println("the list size"+listcol.size());
		 */

	}

	public Map getStateList() throws TPlusException {
		Map stateList = new TreeMap();
		try {
			stateList = objDAO.stateList();
		} catch (Exception e) {
			logger.error(e);
			throw new TPlusException("Error in ApplicationFormManager getStateList method" + e);
		}
		return stateList;
	}

	public Map getCityList(String state) throws TPlusException {
		Map cityList = new TreeMap();
		try {
			cityList = objDAO.cityList(state);
		} catch (Exception e) {
			logger.error(e);
			throw new TPlusException("Error in ApplicationFormManager getCityList method" + e);
		}
		return cityList;
	}

	public Map getTownshipList(String city) throws TPlusException {
		Map townshipList = new TreeMap();
		try {
			townshipList = objDAO.townshipList(city);
		} catch (Exception e) {
			logger.error(e);
			throw new TPlusException("Error in ApplicationFormManager getTownshipList method" + e);
		}
		return townshipList;
	}

}