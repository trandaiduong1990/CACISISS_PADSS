package org.transinfo.cacis.controller.useraccess;

import java.util.Collection;
import java.util.Date;

import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.useraccess.UserSetupDAO;
import org.transinfo.cacis.dto.useraccess.UserMasterDto;
import org.transinfo.cacis.dto.useraccess.UserSetupSearchDto;

/**
 * A Business Delegate that abstracts out the business specific calls and
 * exceptions . The web tier does not need to know about the business-specific
 * details
 **/
public class UserSetupManager {

	private UserSetupDAO objUserSetupDAO;

	public UserSetupManager() throws Exception {
		objUserSetupDAO = DAOFactory.getInstance().getUserSetupDAO();
	}

	public Collection search(UserSetupSearchDto objSearchtDto, int pageNo)
			throws TPlusException {

		Collection searchLst = null;

		try {
			searchLst = objUserSetupDAO.search(objSearchtDto, pageNo);

		} catch (Exception e) {
			System.out.println("Error while search operation" + e);
			throw new TPlusException("Error in UserSetupForm search method"
					+ e.getMessage());
		}
		return searchLst;

	}

	public String getUserName(String issuerId, String userId)
			throws TPlusException {

		String userName = "";

		try {
			userName = objUserSetupDAO.getUserName(issuerId, userId);

		} catch (Exception e) {
			System.out.println("Error while search operation" + e);
			throw new TPlusException("Error in UserSetupForm search method"
					+ e.getMessage());
		}
		return userName;

	}

	public String getIssuerName(String issuerId) throws TPlusException {

		String issuerName = "";

		try {
			issuerName = objUserSetupDAO.getIssuerName(issuerId);

		} catch (Exception e) {
			System.out.println("Error while search operation" + e);
			throw new TPlusException("Error in UserSetupForm search method"
					+ e.getMessage());
		}
		return issuerName;

	}

	public UserMasterDto getUserMasterForm(String issuerId, String userId)
			throws TPlusException {
		boolean success = false;
		UserMasterDto userDto = null;
		try {
			userDto = objUserSetupDAO.getUserMasterForm(issuerId, userId);
		} catch (Exception e) {
			throw new TPlusException("Error in UserSetupForm get method" + e);
		}
		return userDto;
	}

	public boolean add(UserMasterDto objDto) throws TPlusException {

		boolean success = false;
		try {
			success = objUserSetupDAO.add(objDto);
		} catch (Exception e) {
			throw new TPlusException("Error in Add method");
		}
		return success;
	}

	public boolean update(UserMasterDto objDto) throws TPlusException {
		boolean success = false;
		try {
			success = objUserSetupDAO.update(objDto);
		} catch (Exception e) {
			throw new TPlusException("Error in UserSetupForm update method" + e);
		}
		return success;
	}

	public boolean delete(UserMasterDto objDto) throws TPlusException {
		boolean success = false;
		Date today = new Date();

		try {
			success = objUserSetupDAO.delete(objDto);
		} catch (Exception e) {
			throw new TPlusException("Error in UserSetupForm delete method" + e);
		}
		return success;
	}

	public static void main(String s[]) throws Exception {

		UserSetupManager userMgr = new UserSetupManager();
		UserMasterDto objDto = new UserMasterDto();
		UserSetupSearchDto searchDto = new UserSetupSearchDto();

		userMgr.search(searchDto, 0);
		// userMgr.add(objDto);
		// userMgr.delete(objDto);
	}

}