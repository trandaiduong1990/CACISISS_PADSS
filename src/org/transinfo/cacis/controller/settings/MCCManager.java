package org.transinfo.cacis.controller.settings;

import java.util.Collection;

import org.apache.log4j.Logger;
import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.common.constants.CommonCodes;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.settings.MCCDAO;
import org.transinfo.cacis.dto.settings.MCCDto;
import org.transinfo.cacis.dto.settings.MCCMasterDto;
import org.transinfo.cacis.dto.settings.MCCSearchDto;

/**
 * A Business Delegate that abstracts out the business specific calls and
 * exceptions . The web tier does not need to know about the business-specific
 * details
 **/
@SuppressWarnings("unchecked")
public class MCCManager {

	private Logger logger = Logger.getLogger(MCCManager.class);

	private MCCDAO objDAO;

	public MCCManager() throws Exception {
		objDAO = DAOFactory.getInstance().getMCCDAO();

	}

	/*
	 * This method is used for getting the mccLisr
	 */
	public Collection search(MCCSearchDto objSearchDto, int pageNo)
			throws TPlusException {

		Collection searchList = null;
		try {
			searchList = objDAO.search(objSearchDto, pageNo);

		} catch (Exception e) {
			logger.error(e);
			throw new TPlusException("Error in MCCManager search method" + e);
		}
		return searchList;
	}

	public Collection searchMccMaster(MCCMasterDto objMasterDto, int pageNo)
			throws TPlusException {

		Collection searchLst = null;

		try {
			searchLst = objDAO.searchMccMaster(objMasterDto, pageNo);
		} catch (Exception e) {
			throw new TPlusException("Error in MCCManager saearch method" + e);
		}
		
		return searchLst;

	}

	/*
	 * This method is used for getting the particular Record to update
	 */
	public MCCDto getMCCDto(String merchantId) throws TPlusException {
		MCCDto objDto = null;

		try {
			objDto = objDAO.getMCCDto(merchantId);

		} catch (Exception e) {
			logger.error(e);
			throw new TPlusException("Error in MCCManager getMCCDto method" + e);
		}
		return objDto;
	}

	/*
	 * This method is used for insert the particular Record to mcc table
	 */
	/*public boolean add(MCCDto objDto) throws TPlusException {

		boolean boolAdd = false;
		objDto.setStatus(CommonCodes.STATUS_ACTIVE);
		try {

			if (!validate(objDto, objDAO.ADD)) {
				System.out.println("\n\n Record Already Exists");
			} else {

				boolAdd = objDAO.add(objDto);
			}

		} catch (Exception e) {
			logger.error(e);
			throw new TPlusException("Error in MCCManager add method" + e);
		}
		return boolAdd;
	}*/
	
	public boolean add(MCCDto objDto) throws TPlusException {

		boolean boolAdd = false;
		objDto.setStatus(CommonCodes.STATUS_ACTIVE);
		try {

			boolAdd = objDAO.add(objDto);

		} catch (Exception e) {
			logger.error(e);
			throw new TPlusException("Error in MCCManager add method" + e);
		}
		return boolAdd;
	}

	/*
	 * This method is used for updating the particular Record in mcc table
	 */
	public boolean update(MCCDto objDto) throws TPlusException {

		boolean boolUpdate = false;
		objDto.setStatus(CommonCodes.STATUS_ACTIVE);
		try {
			/*
			 * if(!validate(objDto,objDAO.UPDATE)) {
			 * System.out.println("\n\n Record  not Existed"); }else{
			 */
			//boolUpdate = objDAO.update(objDto);
			boolUpdate = objDAO.updateByExecute(objDto);
			// }

		} catch (Exception e) {
			logger.error(e);
			throw new TPlusException("Error in MCCManager update method" + e);
		}
		return boolUpdate;
	}

	/*
	 * This method is used for deleting the particular Record in mcc table
	 */
	public boolean delete(MCCDto objDto) throws TPlusException {

		boolean boolDelete = false;
		// setting the status value
		objDto.setStatus(CommonCodes.STATUS_INACTIVE);
		try {
			/*
			 * if(!validate(objDto,objDAO.UPDATE)) {
			 * System.out.println("\n\n Record  not Existed"); }else{
			 */

			boolDelete = objDAO.delete(objDto);

			// }

		} catch (Exception e) {
			logger.error(e);
			throw new TPlusException("Error in MCCManager delete method" + e);
		}
		return boolDelete;
	}

	/*
	 * this method is used for calling the checkexistrecord method in Impl class
	 */
	public boolean validate(Object obj, int mode) throws TPlusException {
		boolean rtnMessage = true;

		MCCDto objDto = (MCCDto) obj;

		if (mode == 0 && objDAO.checkExistrecord(objDto) > 0) {
			rtnMessage = false;
		}
		if (mode == 1 && objDAO.checkExistrecord(objDto) == 0) {
			rtnMessage = false;
		}

		return rtnMessage;
	}

	public boolean hasWithdrawalLimitRules(String mccId) throws TPlusException {
		boolean hasCus = false;

		try {

			hasCus = objDAO.hasWithdrawalLimitRules(mccId);

		} catch (Exception e) {
			logger.error(e);
			throw new TPlusException(
					"Error in MCCManager hasWithdrawalLimitRules method" + e);
		}

		return hasCus;
	}

	public boolean hasRiskTranxConfig(String mccId) throws TPlusException {
		boolean hasCus = false;

		try {

			hasCus = objDAO.hasRiskTranxConfig(mccId);

		} catch (Exception e) {
			logger.error(e);
			throw new TPlusException(
					"Error in MCCManager hasRiskTranxConfig method" + e);
		}

		return hasCus;
	}

	public static void main(String s[]) throws Exception {
		/*
		 * 
		 * //MccSetup Test MCCManager objManager = new MCCManager();
		 * 
		 * MCCDto objDto = new MCCDto(); objDto.setMerchantId("2");
		 * objDto.setIssuerId("Issuer1"); objDto.setMerchantType("merchant1");
		 * objDto.setFloorLimit(32); objDto.setCurrencyCode("23");
		 * objDto.setLastUpdatedDate(new Date());
		 * objDto.setLastUpdatedBy("IssuerAdmin"); boolean b =
		 * objManager.add(objDto); // boolean b = objManager.update(objDto); //
		 * boolean b = objManager.delete(objDto);
		 * 
		 * //for MCC Search MCCSearchDto objSearchDto = new MCCSearchDto();
		 * objSearchDto.setIssuerId("Issuer1"); objSearchDto.setMerchantId("2");
		 * // objManager.search(objSearchDto); //for MccDto Getting
		 * 
		 * MCCDto objDto = objManager.getMCCDto("2");
		 * System.out.println("MCC ID:" +objDto.getMerchantId());
		 * System.out.println("MCC Type:" +objDto.getMerchantType());
		 * System.out.println("Issue Id:" +objDto.getIssuerId());
		 * System.out.println("FloorLimit:" +objDto.getFloorLimit()); //
		 * System.out.println("LastUpDated Date:" +objDto.getLastUpdatedDate());
		 */
	}

}