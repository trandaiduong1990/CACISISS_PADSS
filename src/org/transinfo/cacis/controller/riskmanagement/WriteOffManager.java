package org.transinfo.cacis.controller.riskmanagement;

import java.util.Date;

import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.riskmanagement.WriteOffDAO;
import org.transinfo.cacis.dto.riskmanagement.WriteOffMasterDto;

/**
 * A Business Delegate that abstracts out the business specific calls and
 * exceptions . The web tier does not need to know about the business-specific
 * details
 */
public class WriteOffManager {

	private WriteOffDAO objDAO;

	public WriteOffManager() throws Exception {
		objDAO = DAOFactory.getInstance()
				.getWriteOffDAO();
	}

	public WriteOffMasterDto get(String issuerId) throws TPlusException {
		WriteOffMasterDto objDto = null;
		try {
			objDto = objDAO
					.getWriteOff(issuerId);
		} catch (Exception e) {
			throw new TPlusException("Error in WriteOff get method"
					+ e);
		}
		return objDto;
	}

	public boolean add(WriteOffMasterDto objDto)
			throws TPlusException {
		boolean success = false;
		Date today = new Date();
		objDto.setLastUpdatedDate(today);
		try {
			success = objDAO.add(objDto);
		} catch (Exception e) {
			throw new TPlusException("Error in WriteOff Add method"
					+ e);
		}
		return success;
	}

	public boolean update(WriteOffMasterDto objDto)
			throws TPlusException {
		boolean success = false;
		Date today = new Date();
		objDto.setLastUpdatedDate(today);
		try {
			success = objDAO.update(objDto);
		} catch (Exception e) {
			throw new TPlusException(
					"Error in WriteOff update method" + e);
		}
		return success;
	}

	public boolean validateExist(Object obj) throws TPlusException {
		boolean rtnMessage = false;
		WriteOffMasterDto objDto = (WriteOffMasterDto) obj;
		System.out.println(objDto.getIssuerId());
		if (objDAO.checkExistrecord(objDto) > 0) {
			rtnMessage = true;
		}
		return rtnMessage;
	}
}
