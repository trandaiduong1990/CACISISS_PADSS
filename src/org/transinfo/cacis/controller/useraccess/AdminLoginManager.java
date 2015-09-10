package org.transinfo.cacis.controller.useraccess;

import org.apache.log4j.Logger;
import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dto.useraccess.AdminLoginDto;
import org.transinfo.cacis.model.useraccess.ValidateAdminLogin;

/**
 * A Business Delegate that abstracts out the business specific calls and
 * exceptions . The web tier does not need to know about the business-specific
 * details
 **/
public class AdminLoginManager {

	private Logger logger = Logger.getLogger(AdminLoginManager.class);

	private org.transinfo.cacis.dto.useraccess.UserMasterDto userData;

	public AdminLoginManager() throws Exception {
	}

	public String validateUser(AdminLoginDto loginDto) throws TPlusException {
		String rtnMessage;
		ValidateAdminLogin objModel = null;
		try {

			objModel = new ValidateAdminLogin();
			rtnMessage = objModel.doUserAuthentication(loginDto);
			setUserData(objModel.getUserData());

		} catch (Exception e) {
			logger.error(e);
			System.out.println("Error while validateUser operation" + e);
			throw new TPlusException(
					"Error in AdminLoginForm validateUser method"
							+ e.getMessage());
		}
		return rtnMessage;

	}

	public String validateBatchProcessAuthUser(AdminLoginDto loginDto)
			throws TPlusException {
		String rtnMessage;
		ValidateAdminLogin objModel = null;
		try {

			objModel = new ValidateAdminLogin();
			rtnMessage = objModel.validateBatchProcessAuthUser(loginDto);
			// setUserData(objModel.getUserData());

		} catch (Exception e) {
			logger.error(e);
			System.out
					.println("Error while validateBatchProcessAuthUser operation"
							+ e);
			throw new TPlusException(
					"Error in AdminLoginForm validateBatchProcessAuthUser method"
							+ e.getMessage());
		}
		return rtnMessage;

	}

	/**
	 * Getter for property userData.
	 * 
	 * @return Value of property userData.
	 */
	public org.transinfo.cacis.dto.useraccess.UserMasterDto getUserData() {
		return userData;
	}

	/**
	 * Setter for property userData.
	 * 
	 * @param userData
	 *            New value of property userData.
	 */
	public void setUserData(
			org.transinfo.cacis.dto.useraccess.UserMasterDto userData) {
		this.userData = userData;
	}

	public static void main(String s[]) throws Exception {

		AdminLoginManager adminMgr = new AdminLoginManager();
		AdminLoginDto loginDto = new AdminLoginDto();
		loginDto.setIssuerId("Issuer1");
		loginDto.setUserId("ISSUERADMIN");
		loginDto.setPassword("Password2");
		try {
			String rtnMsg = adminMgr.validateUser(loginDto);
			System.out.println("Return Message==>" + rtnMsg);
			System.out.println("user Data="
					+ adminMgr.getUserData().getEmailId());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

}