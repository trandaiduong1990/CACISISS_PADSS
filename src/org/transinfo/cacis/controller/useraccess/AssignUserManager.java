package org.transinfo.cacis.controller.useraccess;

import java.util.Collection;
import java.util.Date;

import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.useraccess.AssignUserDAO;
import org.transinfo.cacis.dataacess.dao.useraccess.UserSetupDAO;
import org.transinfo.cacis.dto.useraccess.AssignUserDto;
import org.transinfo.cacis.dto.useraccess.UserMasterDto;
import org.transinfo.cacis.dto.useraccess.UserSetupSearchDto;

/**
 * A Business Delegate that abstracts out the business specific calls and
 * exceptions . The web tier does not need to know about the business-specific
 * details
 **/
public class AssignUserManager {

	private AssignUserDAO objAssignUserDAO;

	public AssignUserManager() throws Exception {
		objAssignUserDAO = DAOFactory.getInstance().getAssignUserDAO();
	}

	public Collection search(AssignUserDto objSearchtDto, int pageNo)
			throws TPlusException {

		Collection searchLst = null;

		try {
			searchLst = objAssignUserDAO.search(objSearchtDto, pageNo);

		} catch (Exception e) {
			System.out.println("Error while search operation" + e);
			throw new TPlusException("Error in UserSetupForm search method"
					+ e.getMessage());
		}
		return searchLst;

	}

	public boolean add(AssignUserDto objDto) throws TPlusException {

		boolean success = false;
		try {
			success = objAssignUserDAO.add(objDto);
		} catch (Exception e) {
			throw new TPlusException("Error in Add method");
		}
		return success;
	}

	public boolean update(AssignUserDto objDto) throws TPlusException {
		boolean success = false;
		try {
			success = objAssignUserDAO.update(objDto);
		} catch (Exception e) {
			throw new TPlusException("Error in UserSetupForm update method" + e);
		}
		return success;
	}

	public AssignUserDto getAssignUserForm(String assignId) throws TPlusException {
		AssignUserDto objDto;
		try {
			objDto = objAssignUserDAO.getAssignUserForm(assignId);
		} catch (Exception e) {
			throw new TPlusException("Error in UserSetupForm delete method" + e);
		}
		return objDto;
	}
}