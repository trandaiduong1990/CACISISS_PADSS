package org.transinfo.cacis.controller.log;

import java.util.Collection;

import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.log.UserActivitiesDAO;
import org.transinfo.cacis.dto.log.UserActivitiesDto;
import org.transinfo.cacis.dto.log.UserActivitiesSearchDto;

/**
 * A Business Delegate that abstracts out the business specific calls and
 * exceptions . The web tier does not need to know about the business-specific
 * details
 **/

@SuppressWarnings("unchecked")
public class UserActivitiesManager {

	private UserActivitiesDAO objUserActivitiesDAO;

	public UserActivitiesManager() throws Exception {
		objUserActivitiesDAO = DAOFactory.getInstance().getUserActivitiesDAO();
	}

	public Collection search(UserActivitiesSearchDto objSearchtDto, int pageNo)
			throws TPlusException {

		Collection searchLst = null;

		try {
			searchLst = objUserActivitiesDAO.search(objSearchtDto, pageNo);

		} catch (Exception e) {
			System.out.println("Error while search operation" + e);
			throw new TPlusException(
					"Error in manager UserActivities search method"
							+ e.getMessage());
		}
		return searchLst;

	}

	public boolean add(UserActivitiesDto objDto) throws TPlusException {

		boolean success = false;
		try {
			success = objUserActivitiesDAO.add(objDto);
		} catch (Exception e) {
			throw new TPlusException("Error in Add method");
		}
		return success;
	}

}