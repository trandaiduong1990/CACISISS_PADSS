package org.transinfo.cacis.controller.authorization;

import java.util.Collection;
import java.util.Date;

import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.authorization.SMSServiceDAO;
import org.transinfo.cacis.dto.authorization.SMSServiceDto;
import org.transinfo.cacis.dto.authorization.SMSServiceSearchDto;

/**
 * A Business Delegate that abstracts out the business specific calls and
 * exceptions . The web tier does not need to know about the business-specific
 * details
 */
public class SMSServiceManager {

	private SMSServiceDAO objSMSServiceDAO;

	public SMSServiceManager() throws Exception {
		objSMSServiceDAO = DAOFactory.getInstance().getSMSServiceDAO();
	}

	public Collection search(SMSServiceSearchDto objSMSServiceSearchDto,
			int pageNo) throws TPlusException {

		Collection searchLst = null;

		try {
			searchLst = objSMSServiceDAO.search(objSMSServiceSearchDto, pageNo);

		} catch (Exception e) {
			System.out.println("Error while search operation" + e);
			throw new TPlusException("Error in SMSServiceForm search method"
					+ e);
		}
		return searchLst;

	}

	public SMSServiceDto get(long cardNumber) throws TPlusException {
		SMSServiceDto objDto = null;
		try {
			objDto = objSMSServiceDAO.getSMSService(cardNumber);
		} catch (Exception e) {
			throw new TPlusException("Error in SMSService get method" + e);
		}
		return objDto;
	}

	public boolean update(SMSServiceDto objSMSServiceDto)
			throws TPlusException {
		boolean success = false;
		Date today = new Date();
		objSMSServiceDto.setLastUpdatedDate(today);
		try {
			success = objSMSServiceDAO.update(objSMSServiceDto);
		} catch (Exception e) {
			throw new TPlusException(
					"Error in WithdrawalLimitRules update method" + e);
		}
		return success;
	}

	public boolean add(SMSServiceDto objSMSServiceDto) throws TPlusException {

		boolean success = false;
		try {
			success = objSMSServiceDAO.add(objSMSServiceDto);
		} catch (Exception e) {
			throw new TPlusException("Error in Add method");
		}
		return success;
	}
	
	public boolean validateExist(Object obj) throws TPlusException {
		boolean rtnMessage = false;
		SMSServiceDto objSMSServiceDto = (SMSServiceDto) obj;
		System.out.println(objSMSServiceDto.getCardNumber());
		if (objSMSServiceDAO.checkExistRecord(objSMSServiceDto) > 0) {
			rtnMessage = true;
		}
		return rtnMessage;
	}
	
	public boolean validateDuplicate(Object obj, int mode) throws TPlusException {
		boolean rtnMessage = false;
		SMSServiceDto objSMSServiceDto = (SMSServiceDto) obj;
		System.out.println(objSMSServiceDto.getCardNumber());
		// ADD => 0
		// UPDATE => 1
		if ((mode == 0 || mode == 1) && objSMSServiceDAO.checkDuplicateRecord(objSMSServiceDto) > 0) {
			rtnMessage = true;
		}
		return rtnMessage;
	}	
}