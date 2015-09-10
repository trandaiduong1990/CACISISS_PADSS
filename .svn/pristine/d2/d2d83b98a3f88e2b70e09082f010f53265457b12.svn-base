package org.transinfo.cacis.controller.settings;

import java.util.Collection;

import org.apache.log4j.Logger;
import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.common.constants.CommonCodes;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.settings.CycleDAO;
import org.transinfo.cacis.dto.settings.CycleDto;
import org.transinfo.cacis.dto.settings.CycleSearchDto;

/**
 * A Business Delegate that abstracts out the business specific calls and
 * exceptions . The web tier does not need to know about the business-specific
 * details
 **/
@SuppressWarnings( { "static-access", "unchecked" })
public class CycleManager {

	private Logger logger = Logger.getLogger(CycleManager.class);

	private CycleDAO objDAO;

	public CycleManager() throws Exception {
		objDAO = DAOFactory.getInstance().getCycleDAO();

	}

	/*
	 * This method is used for getting the cycleList
	 */
	public Collection search(CycleSearchDto objSearchDto, int pageNo)
			throws TPlusException {

		Collection searchList = null;
		try {
			searchList = objDAO.search(objSearchDto, pageNo);

		} catch (Exception e) {
			logger.error(e);
			throw new TPlusException("Error in CycleManager search method" + e);
		}
		return searchList;
	}

	/*
	 * This method is used for getting the particular Record to update
	 */
	public CycleDto getCycleDto(int cycleNo) throws TPlusException {
		CycleDto objDto = null;

		try {
			objDto = objDAO.getCycleDto(cycleNo);

		} catch (Exception e) {
			logger.error(e);
			throw new TPlusException("Error in CycleManager getCycleDto method"
					+ e);
		}
		return objDto;
	}

	/*
	 * This method is used for insert the particular Record to Cycles table
	 */
	public boolean add(CycleDto objDto) throws TPlusException {

		boolean boolAdd = false;
		objDto.setStatus(CommonCodes.STATUS_ACTIVE);
		try {

			// if (!validate(objDto, objDAO.ADD)) {
			// System.out.println("\n\n Record Already Exists");
			// } else {

			boolAdd = objDAO.add(objDto);
			// }

		} catch (Exception e) {
			logger.error(e);
			throw new TPlusException("Error in CycleManager add method" + e);
		}
		return boolAdd;
	}

	/*
	 * This method is used for updating the particular Record in cust_type table
	 */
	public boolean update(CycleDto objDto) throws TPlusException {

		boolean boolUpdate = false;
		objDto.setStatus(CommonCodes.STATUS_ACTIVE);
		try {
			/*
			 * if(!validate(objDto,objDAO.UPDATE)) {
			 * System.out.println("\n\n Record  not Existed"); }else{
			 */
			boolUpdate = objDAO.update(objDto);
			// }

		} catch (Exception e) {
			logger.error(e);
			throw new TPlusException("Error in CycleManager update method" + e);
		}
		return boolUpdate;
	}

	/*
	 * This method is used for deleting the particular Record in cust_type table
	 */
	public boolean delete(CycleDto objDto) throws TPlusException {

		boolean boolDelete = false;
		try {
			// setting the status value
			objDto.setStatus(CommonCodes.STATUS_INACTIVE);

			/*
			 * if(!validate(objDto,objDAO.UPDATE)) {
			 * System.out.println("\n\n Record  not Existed"); }else{
			 */

			boolDelete = objDAO.delete(objDto);

			// }

		} catch (Exception e) {
			logger.error(e);
			throw new TPlusException("Error in CycleManager delete method" + e);
		}
		return boolDelete;
	}

	public boolean hasCustomerAccounts(String cycleNo) throws TPlusException {
		boolean hasCus = false;

		try {

			hasCus = objDAO.hasCustomerAccounts(cycleNo);

		} catch (Exception e) {
			logger.error(e);
			throw new TPlusException(
					"Error in CycleManager hasCustomerAccounts method" + e);
		}

		return hasCus;
	}

	/*
	 * this method is used for calling the checkexistrecord method in Impl class
	 */
	public boolean validate(Object obj, int mode) throws TPlusException {
		boolean rtnMessage = true;

		CycleDto objDto = (CycleDto) obj;

		if (mode == 0 && objDAO.checkExistrecord(objDto) > 0) {
			rtnMessage = false;
		}
		if (mode == 1 && objDAO.checkExistrecord(objDto) == 0) {
			rtnMessage = false;
		}

		return rtnMessage;
	}

	public static void main(String s[]) throws Exception {
		/*
		 * 
		 * 
		 * // CycleSetup Test CycleManager objManager = new CycleManager();
		 * CycleDto objDto = new CycleDto(); objDto.setIssuerId("Issuer1");
		 * objDto.setCycleNo(1); objDto.setDayOfMonth(10); //
		 * objDto.setLastBillingDate(new Date()); //
		 * objDto.setLastUpdatedDate(new Date()); //
		 * objDto.setLastUpdatedBy("IssuerAdmin"); // boolean b =
		 * objManager.add(objDto); boolean b = objManager.update(objDto); //
		 * boolean b = objManager.delete(objDto);
		 * 
		 * // Cycle Search CycleSearchDto objSearchDto = new CycleSearchDto();
		 * objSearchDto.setIssuerId("Issuer1"); objManager.search(objSearchDto,
		 * 1);
		 * 
		 * // Fro Geting CycleDto
		 * 
		 * CycleDto objDto = objManager.getCycleDto(1);
		 * System.out.println("CycleNo:" +objDto.getCycleNo());
		 * System.out.println("DayOfmonth" +objDto.getDayOfMonth());
		 * System.out.println("IssuerId" +objDto.getIssuerId());
		 */
	}

	public boolean validateCycleNo(String cycleNo) throws TPlusException {
		boolean rtnMessage = true;

		if (objDAO.checkExistrecordCycleNo(cycleNo) > 0) {
			rtnMessage = false;
		}

		return rtnMessage;
	}

	public boolean validateDayOfMonth(String dayOfMonth) throws TPlusException {
		boolean rtnMessage = true;

		if (objDAO.checkExistrecordDayOfMonth(dayOfMonth) > 0) {
			rtnMessage = false;
		}

		return rtnMessage;
	}

}