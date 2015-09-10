package org.transinfo.cacis.controller.disputemanagement;

import java.util.Date;

import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.disputemanagement.DisputeConfigDAO;
import org.transinfo.cacis.dto.disputemanagement.DisputeConfigMasterDto;

/**
 * A Business Delegate that abstracts out the business specific calls and
 * exceptions . The web tier does not need to know about the business-specific
 * details
 */
public class DisputeConfigManager {

	private DisputeConfigDAO objDAO;

	public DisputeConfigManager() throws Exception {
		objDAO = DAOFactory.getInstance()
				.getDisputeConfigDAO();
	}

	public DisputeConfigMasterDto get(String issuerId) throws TPlusException {
		DisputeConfigMasterDto objDto = null;
		try {
			objDto = objDAO
					.getDisputeConfig(issuerId);
		} catch (Exception e) {
			throw new TPlusException("Error in DisputeConfig get method"
					+ e);
		}
		return objDto;
	}

	public boolean add(DisputeConfigMasterDto objDto)
			throws TPlusException {
		boolean success = false;
		Date today = new Date();
		objDto.setLastUpdatedDate(today);
		try {
			success = objDAO.add(objDto);
		} catch (Exception e) {
			throw new TPlusException("Error in DisputeConfig Add method"
					+ e);
		}
		return success;
	}

	public boolean update(DisputeConfigMasterDto objDto)
			throws TPlusException {
		boolean success = false;
		Date today = new Date();
		objDto.setLastUpdatedDate(today);
		try {
			success = objDAO.update(objDto);
		} catch (Exception e) {
			throw new TPlusException(
					"Error in DisputeConfig update method" + e);
		}
		return success;
	}

	public boolean validateExist(Object obj) throws TPlusException {
		boolean rtnMessage = false;
		DisputeConfigMasterDto objDto = (DisputeConfigMasterDto) obj;
		System.out.println(objDto.getIssuerId());
		if (objDAO.checkExistrecord(objDto) > 0) {
			rtnMessage = true;
		}
		return rtnMessage;
	}

}
