package org.transinfo.cacis.controller.log;

import java.util.Collection;

import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.log.ErrorlogDAO;
import org.transinfo.cacis.dto.log.ErrorlogDto;
import org.transinfo.cacis.dto.log.ErrorlogSearchDto;

/**
 * A Business Delegate that abstracts out the business specific calls and
 * exceptions . The web tier does not need to know about the business-specific
 * details
 **/

@SuppressWarnings("unchecked")
public class ErrorlogManager {

	private ErrorlogDAO objErrorlogDAO;

	public ErrorlogManager() throws Exception {
		objErrorlogDAO = DAOFactory.getInstance().getErrorlogDAO();
	}

	public Collection search(ErrorlogSearchDto objSearchtDto, int pageNo)
			throws TPlusException {

		Collection searchLst = null;

		try {
			searchLst = objErrorlogDAO.search(objSearchtDto, pageNo);

		} catch (Exception e) {
			System.out.println("Error while search operation" + e);
			throw new TPlusException("Error in manager Errorlog search method"
					+ e.getMessage());
		}
		return searchLst;

	}

	public boolean add(ErrorlogDto objDto) throws TPlusException {

		boolean success = false;
		try {
			success = objErrorlogDAO.add(objDto);
		} catch (Exception e) {
			throw new TPlusException("Error in Add method");
		}
		return success;
	}

}