package org.transinfo.cacis.model.useraccess;

import org.apache.log4j.Logger;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.useraccess.AdminLoginDAO;
import org.transinfo.cacis.dataacess.daoimpl.oracle.BaseDAOImpl;
import org.transinfo.cacis.dto.useraccess.AdminLoginDto;
import org.transinfo.cacis.dto.useraccess.UserMasterDto;

public class ValidateAdminLogin extends BaseDAOImpl {

	private Logger logger = Logger.getLogger(ValidateAdminLogin.class);

	private AdminLoginDAO objAdminLoginDAO;
	private UserMasterDto userData;
	private AdminLoginDto loginData;

	/** Creates new ValidateAdminLogin */

	public ValidateAdminLogin() throws Exception {
		objAdminLoginDAO = DAOFactory.getInstance().getAdminLoginDAO();
	}

	public String doUserAuthentication(AdminLoginDto loginDto) {
		loginData = new AdminLoginDto();
		userData = new UserMasterDto();
		try {
			loginData = objAdminLoginDAO.loadSystemParam(loginDto);
			userData = objAdminLoginDAO.getUserData(loginDto);
			if (userData != null) {
				System.out.println("UserAuthentication Process..........");
				System.out.println("getMaxLoginFailed() ==>"
						+ loginData.getMaxLoginFailed());
				System.out.println("getPwdExpiryPeriod()==>"
						+ loginData.getPwdExpiryPeriod());

				System.out.println("Checking user existence...");
				if (!objAdminLoginDAO.isValidUser())
					return "INVALID_LOGIN_ATTEMPT";
				System.out.println("Checking Password Match...");
				if (!objAdminLoginDAO.isPasswordMatch(loginDto)) {
					System.out.println("Password Not Matched.");
					System.out
							.println("Checking whether need to lock the user or not (if exceeds attempts)? - LOCK USER : "
									+ loginData.getLockUser());
					if (loginData.getLockUser().trim().equalsIgnoreCase("Y")) {
						// update user record : login fail count + 1
						// if password attempts > sysParam.failedLoginAttempts :
						// lock the user
						System.out
								.println("Checking And Updating the Password Attempts...");
						if (objAdminLoginDAO
								.checkAndUpdatePasswordAttempts(loginDto)) {
							System.out
									.println("User Password Attempts are greater than the desired system attempts. - User Locked.");
							return "USER_LOCKED";
						}
					}
					return "INVALID_LOGIN_ATTEMPT";
				}
				System.out.println("Valid Password.");
				System.out
						.println("Checking the status of the user. - STATUS : "
								+ userData.getStatus());
				if (userData.getStatus().trim().equalsIgnoreCase("L"))
					return "USER_LOCKED";
				if (userData.getStatus().trim().equalsIgnoreCase("I"))
					return "USER_INACTIVE";
				if (userData.getStatus().trim().equalsIgnoreCase("C"))
					return "INVALID_LOGIN_ATTEMPT";

				System.out
						.println("Checking the station ip - USERSTATION IP (Database): "
								+ userData.getUserStationIp());
				System.out
						.println("Checking the station ip - STATION IP (Access): "
								+ loginDto.getRemoteIp().trim());
				if (!userData.getUserStationIp().trim().equalsIgnoreCase(
						"000.000.000.000")
						&& !userData
								.getUserStationIp()
								.trim()
								.equalsIgnoreCase(loginDto.getRemoteIp().trim())) {
					return "UNAUTHORIZED_USER";
				}

				System.out
						.println("Checking the first time logon - FIRST TIME LOGON : "
								+ userData.getFirstTimeLogin());

				if (userData.getFirstTimeLogin() == 'Y') {
					return "FIRSTTIMELOGIN";
				}

				System.out.println("Checking the password expiry...");
				if (objAdminLoginDAO.checkPasswordExpiry()) {
					return "PASSWORD_EXPIRED";
				}

				System.out.println("Valid User : Can login into the system");
				objAdminLoginDAO.loadUserFunctionList(loginDto);
				objAdminLoginDAO.resetPasswordAttempts(loginDto);
				//objAdminLoginDAO.loadProperties();
				//objAdminLoginDAO.initHSM();

				setUserData(userData);
				return "VALIDUSER";
			} else {
				return "NOTVALIDUSER";
			}

		} catch (Exception ex) {
			logger.error(ex);
			System.out.println("Exception User Authentication : "
					+ ex.toString());
			return "APPLICATION_ERROR";
		}
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

	public String validateBatchProcessAuthUser(AdminLoginDto loginDto) {

		loginData = new AdminLoginDto();
		userData = new UserMasterDto();
		try {
			loginData = objAdminLoginDAO.loadSystemParam(loginDto);
			userData = objAdminLoginDAO.getUserData(loginDto);
			if (userData != null) {
				System.out.println("UserAuthentication Process..........");
				System.out.println("getMaxLoginFailed() ==>"
						+ loginData.getMaxLoginFailed());
				System.out.println("getPwdExpiryPeriod()==>"
						+ loginData.getPwdExpiryPeriod());

				System.out.println("Checking user existence...");
				if (!objAdminLoginDAO.isValidUser())
					return "INVALID_LOGIN_ATTEMPT";
				System.out.println("Checking Password Match...");
				if (!objAdminLoginDAO.isPasswordMatch(loginDto)) {
					System.out.println("Password Not Matched.");
					System.out
							.println("Checking whether need to lock the user or not (if exceeds attempts)? - LOCK USER : "
									+ loginData.getLockUser());
					if (loginData.getLockUser().trim().equalsIgnoreCase("Y")) {
						System.out
								.println("Checking And Updating the Password Attempts...");
						if (objAdminLoginDAO
								.checkAndUpdatePasswordAttempts(loginDto)) {
							System.out
									.println("User Password Attempts are greater than the desired system attempts. - User Locked.");
							return "USER_LOCKED";
						}
					}
					return "INVALID_LOGIN_ATTEMPT";
				}
				System.out.println("Valid Password.");
				System.out
						.println("Checking the status of the user. - STATUS : "
								+ userData.getStatus());
				if (userData.getStatus().trim().equalsIgnoreCase("L"))
					return "USER_LOCKED";
				if (userData.getStatus().trim().equalsIgnoreCase("I"))
					return "USER_INACTIVE";
				if (userData.getStatus().trim().equalsIgnoreCase("C"))
					return "INVALID_LOGIN_ATTEMPT";

				System.out
						.println("Checking the station ip - USERSTATION IP (Database): "
								+ userData.getUserStationIp());
				System.out
						.println("Checking the station ip - STATION IP (Access): "
								+ loginDto.getRemoteIp().trim());
				if (!userData.getUserStationIp().trim().equalsIgnoreCase(
						"000.000.000.000")
						&& !userData
								.getUserStationIp()
								.trim()
								.equalsIgnoreCase(loginDto.getRemoteIp().trim())) {
					return "UNAUTHORIZED_USER";
				}

				System.out
						.println("Checking the first time logon - FIRST TIME LOGON : "
								+ userData.getFirstTimeLogin());

				if (userData.getFirstTimeLogin() == 'Y') {
					return "FIRSTTIMELOGIN";
				}

				System.out.println("Checking the password expiry...");
				if (objAdminLoginDAO.checkPasswordExpiry()) {
					return "PASSWORD_EXPIRED";
				}

				System.out.println("Valid User : Can login into the system");
				//objAdminLoginDAO.loadUserFunctionList(loginDto);
				//objAdminLoginDAO.resetPasswordAttempts(loginDto);

				//setUserData(userData);
				return "VALIDUSER";
			} else {
				return "NOTVALIDUSER";
			}

		} catch (Exception ex) {
			logger.error(ex);
			System.out.println("Exception User Authentication : "
					+ ex.toString());
			return "APPLICATION_ERROR";
		}

	}

	public static void main(String[] args) {

		AdminLoginDto objDto = new AdminLoginDto();
		objDto.setIssuerId("Issuer1");
		objDto.setUserId("ISSUERADMIN");
		objDto.setPassword("Password2");
		try {
			ValidateAdminLogin validateLogin = new ValidateAdminLogin();
			String rtnMsg = validateLogin.doUserAuthentication(objDto);
			System.out.println("Return Message==>" + rtnMsg);

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

}
