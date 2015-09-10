package org.transinfo.cacis.controller.riskmanagement;

import java.util.Collection;

import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.riskmanagement.OriginalRequestDAO;
import org.transinfo.cacis.dto.riskmanagement.OriginalRequestDto;
import org.transinfo.cacis.dto.riskmanagement.OriginalRequestSearchDto;

/**
 * A Business Delegate that abstracts out the business specific calls and
 * exceptions . The web tier does not need to know about the business-specific
 * details
 */
public class OriginalRequestManager {

	private OriginalRequestDAO objOriReqDAO;

	public OriginalRequestManager() throws Exception {
		objOriReqDAO = DAOFactory.getInstance()
				.getOriginalRequestDAO();
	}

	public Collection search(OriginalRequestSearchDto objSearchDto,
			int pageNo) throws TPlusException {

		Collection searchLst = null;

		try {
			searchLst = objOriReqDAO.search(objSearchDto, pageNo);

		} catch (Exception e) {
			System.out.println("Error while search operation" + e);
			throw new TPlusException(
					"Error in OriginalRequest search method" + e);
		}
		return searchLst;

	}

	public OriginalRequestDto get(long settlementId) throws TPlusException {
		OriginalRequestDto objOriReqDto = null;
		try {
			objOriReqDto = objOriReqDAO
					.getOriginalRequest(settlementId);
		} catch (Exception e) {
			throw new TPlusException("Error in OriginalRequest get method"
					+ e);
		}
		return objOriReqDto;
	}

	public boolean update(OriginalRequestDto objOriReqDto)
			throws TPlusException {
		boolean success = false;
		try {
			success = objOriReqDAO.update(objOriReqDto);
		} catch (Exception e) {
			throw new TPlusException(
					"Error in OriginalRequest update method" + e);
		}
		return success;
	}

	public boolean validate(Object obj, int mode) throws TPlusException {
		boolean rtnMessage = false;
		System.out.println("===================== 002 =====================");
		OriginalRequestDto objOriReqDto = (OriginalRequestDto) obj;
		System.out.println("objOriReqDto.getSettlementId()="+objOriReqDto.getSettlementId());
		System.out.println("===================== 003 =====================");
		if ((mode == 1 || mode == 3)
				&& objOriReqDAO.checkExistrecord(objOriReqDto) > 0) {
			rtnMessage = true;
		}
		return rtnMessage;
	}
}
