package org.transinfo.cacis.dataacess.daoimpl.oracle.useraccess;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.transinfo.cacis.TPlusCodes;
import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.common.CommonDataBean;
import org.transinfo.cacis.dataacess.HibernetDAO.HibernetFactory;
import org.transinfo.cacis.dataacess.dao.useraccess.AdminLoginDAO;
import org.transinfo.cacis.dataacess.daoimpl.oracle.BaseDAOImpl;
import org.transinfo.cacis.dto.authorization.LoginParamDto;
import org.transinfo.cacis.dto.useraccess.AdminLoginDto;
import org.transinfo.cacis.dto.useraccess.UserMasterDto;
import org.transinfo.cacis.util.AdminParamsLoad;
import org.transinfo.cacis.util.EncryptUtility;

@SuppressWarnings( { "unchecked", "unused" })
public class AdminLoginDAOImpl extends BaseDAOImpl implements AdminLoginDAO {
	// This two objects declared here for using in this class.
	public UserMasterDto userData = null;
	public LoginParamDto loginData = null;

	// This method gets the record matching with issuerid and userid
	public UserMasterDto getUserData(AdminLoginDto loginDto)
			throws TPlusException {

		UserMasterDto userMaster = null;
		Transaction tx = null;

		StringBuffer sbf = new StringBuffer();

		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			userMaster = (UserMasterDto) session.get(UserMasterDto.class, new UserMasterDto.Id(loginDto.getIssuerId(), loginDto.getUserId()));

			if (userMaster != null) {
				userData = userMaster;
			}

			tx.commit();

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}

			System.out.println("Error in get method" + e);
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while retrieving the get" + e);
		} finally {
			HibernetFactory.closeSession();
		}
		return userMaster;
	}

	public AdminLoginDto loadSystemParam(AdminLoginDto loginDto)
			throws TPlusException {
		LoginParamDto loginParam = new LoginParamDto();
		AdminLoginDto adminLogin = new AdminLoginDto();
		Transaction tx = null;

		StringBuffer sbf = new StringBuffer();

		try {
			System.out.println("Passed IssuerId from Impl==>"
					+ loginDto.getIssuerId());
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();

			loginParam = (LoginParamDto) session.get(LoginParamDto.class,
					loginDto.getIssuerId());

			if (loginParam != null) {
				loginData = new LoginParamDto();
				loginData = loginParam;
			}

			tx.commit();

			// **** SETTING ALL THE PROPERTIES OF LoginParamDto to AdminLoginDto
			// *****//
			if (loginParam != null) {
				adminLogin.setIssuerId(loginParam.getIssuerId());
				adminLogin.setPwdExpiryPeriod(loginParam.getPwdExpiryPeriod());
				adminLogin.setMaxLoginFailed(loginParam.getMaxLoginFailed());
				adminLogin.setFtlValidityDays(loginParam.getFtlValidityDays());
				adminLogin.setLockUser(loginParam.getLockUser());
				adminLogin.setUpdatedDate(loginParam.getUpdatedDate());
				adminLogin.setUserId(loginParam.getUserId());
				adminLogin.setPwdExpiryRemainderDays(loginParam
						.getPwdExpiryRemainderDays()); // this field not yet
				// added to table
				System.out.println("%%%%%%Fetched pwdExpiry from Impl==>"
						+ adminLogin.getPwdExpiryPeriod());
			}

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}

			System.out.println("Error in Impl get method  " + e);
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while retrieving the get" + e);
		} finally {
			HibernetFactory.closeSession();
		}
		return adminLogin;

	}

	public boolean isValidUser() throws TPlusException {
		boolean isValidUser = true;
		boolean isUserExist = false;
		try {
			if (userData != null)
				isUserExist = true;
			if (!isUserExist) {
				System.out.println("INVALID_LOGIN_ATTEMPT");
				return isValidUser = false;
			}
			/*
			 * if(!this.isPasswordMatch()){
			 * System.out.println("INVALID_LOGIN_ATTEMPT"); return isValidUser =
			 * false; }
			 */

		} catch (Exception ex) {
			System.out.println("Exception User Authentication : "
					+ ex.toString());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in isValidUser" + ex);

		}
		System.out.println("VALIDUSER");
		return isValidUser;
	}

	public boolean isPasswordMatch(AdminLoginDto loginDto)
			throws TPlusException {
		boolean blnPasswordMatch = false;

		try {
			System.out.println("User Password==>" + userData.getPassword());
			System.out.println("Login Password==>" + loginDto.getPassword());

			String strPassword = EncryptUtility.encrPassword(loginDto
					.getPassword());
			System.out.println("Encrypt Login Password==>" + strPassword);

			if (strPassword.trim().equals(userData.getPassword())) {
				blnPasswordMatch = true;
			}
		} catch (Exception ex) {
			System.out.println("Exception while checking the password : "
					+ ex.toString());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in isPasswordMatch " + ex);
		}
		return blnPasswordMatch;
	}

	public boolean checkAndUpdatePasswordAttempts(AdminLoginDto loginDto)
			throws TPlusException {
		boolean locked = false;

		StringBuffer sbf = new StringBuffer();
		try {
			int intUserLoginFailCount = userData.getLoginFailCount() + 1;
			int intMaxNoOfLoginFailedAttempts = loginData.getMaxLoginFailed();

			System.out.println("intUserLoginFailCount==>"
					+ intUserLoginFailCount);
			System.out.println("intMaxNoOfLoginFailedAttempts==>"
					+ intMaxNoOfLoginFailedAttempts);

			Transaction tx = null;
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();

			userData.setLoginFailCount(intUserLoginFailCount);

			String currUserStatus = userData.getStatus();
			if (intUserLoginFailCount > intMaxNoOfLoginFailedAttempts) {
				userData.setStatus("L");
				locked = true;

			}
			userData.setLastUpdatedDate(new Date());
			userData.setLastUpdatedBy(loginDto.getUserId());
			userData.id.setUserId(loginDto.getUserId());
			userData.id.setIssuerId(loginDto.getIssuerId());
			// userData.setStatus("A");
			if (currUserStatus.equals("A")) {
				session.update(userData);
				session.flush();
				tx.commit();

			}

		} catch (Exception ex) {
			System.out
					.println("Exception while updating the User's Password Attempts : "
							+ ex.toString());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in checkAndUpdatePasswordAttempts " + ex);
		} finally {
			HibernetFactory.closeSession();
		}
		return locked;
	}

	public boolean checkPasswordExpiry() throws TPlusException {
		boolean expired = false;
		try {
			int passwordExpiryPeriod = loginData.getPwdExpiryPeriod();
			// ************HERE THE BELOW COLUMNS DOES NOT EXIST IN
			// TABLES***********//

			int passwordModifiedDays = userData.getPasswordModifiedDays();
			int passwordRemainderDays = loginData.getPwdExpiryRemainderDays();

			System.out
					.println("passwordExpiryPeriod : " + passwordExpiryPeriod);
			System.out
					.println("passwordModifiedDays : " + passwordModifiedDays);
			System.out.println("passwordRemainderDays: "
					+ passwordRemainderDays);

			// password expiry remaining days...
			int daysRemaining = passwordExpiryPeriod - passwordModifiedDays;
			System.out.println("Remaining Password Validity Days : "
					+ daysRemaining);

			// if password remainder days is greater than or equal to the
			// password expiry days...
			if (daysRemaining <= passwordRemainderDays)
				userData.setPasswordExpiry(String.valueOf(daysRemaining));

			// Removed by Satya
			/*
			 * if(daysRemaining < 0) { expired = true; }
			 */
		} catch (Exception ex) {
			System.out
					.println("Exception while updating the User's Password Attempts : "
							+ ex.toString());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in checkPasswordExpiry " + ex);
		}
		return expired;
	}

	public void resetPasswordAttempts(AdminLoginDto loginDto)
			throws TPlusException {
		StringBuffer sbf = new StringBuffer();
		try {
			Transaction tx = null;
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();

			userData.setLoginFailCount(0);
			userData.setLastUpdatedDate(new Date());
			userData.setLastUpdatedBy(loginDto.getUserId());
			userData.id.setUserId(loginDto.getUserId());
			userData.id.setIssuerId(loginDto.getIssuerId());
			userData.setStatus("A");
			session.update(userData);
			session.flush();
			tx.commit();

		} catch (Exception ex) {
			System.out
					.println("Exception while updating the User's Password Attempts : "
							+ ex.toString());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in resetPasswordAttempts " + ex);
		} finally {
			HibernetFactory.closeSession();
		}
	}

	public void loadUserFunctionList(AdminLoginDto loginDto) throws TPlusException {
		
		HashMap objScreenFunctionList = new HashMap();
		Collection objSearchCollection = null;
		ArrayList arlst = new ArrayList();
		StringBuffer sbf = new StringBuffer();
		
		try {
			
			sbf.append("select fsm.id.screenId, fsm.id.functionId ");
			sbf.append(" FROM UserMasterDto um, RoleMasterDto rm, ");
			sbf.append(" RoleFunctionSetDto rfs, FunctionSetMasterDto fsm  ");
			sbf.append(" WHERE rm.id.issuerId = um.id.issuerId ");
			sbf.append(" AND rm.id.issuerId = rfs.id.issuerId ");
			sbf.append(" AND rm.id.roleId = um.roleId ");
			sbf.append(" AND rm.id.roleId = rfs.id.roleId ");
			sbf.append(" AND rfs.id.functionId = fsm.id.functionId ");
			sbf.append(" AND um.id.issuerId = '" + loginDto.getIssuerId() + "' ");
			sbf.append(" AND um.id.userId = '" + loginDto.getUserId() + "' ");

			System.out.println("Query..." + sbf.toString());
			objSearchCollection = getList(sbf.toString());
			arlst = (ArrayList) objSearchCollection;
			System.out.println("ArrayList =" + arlst.size());
			
			for (int i = 0; i < arlst.size(); i++) {

				CommonDataBean objBean = (CommonDataBean) arlst.get(i);
				
				System.out.println("Bean Values Column1==>" + objBean.getColumn1());
				System.out.println("Bean Values Column2==>" + objBean.getColumn2());
				
				String strScreenId = objBean.getColumn1();
				String strFunctionId = objBean.getColumn2();
				
				if (objScreenFunctionList.containsKey(strScreenId)) {
					ArrayList objFunctionList = (ArrayList) objScreenFunctionList.get(strScreenId);
					objFunctionList.add(strFunctionId);
					objScreenFunctionList.put(strScreenId, objFunctionList);
				} else {
					ArrayList objFunctionList = new ArrayList();
					objFunctionList.add(strFunctionId);
					objScreenFunctionList.put(strScreenId, objFunctionList);
				}

			}

			System.out.println("2");
			this.userData.setScreenFunctionList(objScreenFunctionList);
			System.out.println(userData.getScreenFunctionList().size());

		} catch (Exception ex) {
			System.out.println("Exception While Getting the function list : " + ex.toString());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR, "Error: in loadUserFunctionList " + ex);
		}

	}

	public void logActivity(AdminLoginDto loginDto) throws TPlusException {
		/*
		 * try{ Session session = HibernetFactory.currentSession();
		 * System.out.println("Log Logig User Activity."); StringBuffer sbf =
		 * new StringBuffer();sbf.append(
		 * " insert into  UserActivitiesDto ua (ua.issuerId, ua.userId, ua.stationIp, ua.dateTime) "
		 * );sbf.append(
		 * " select al.issuerId, al.userId, al.remoteIp, al.updatedDate  ");
		 * sbf.append(" from AdminLoginDto al where al.issuerId = '"+loginDto.
		 * getIssuerId()+"' ");
		 * sbf.append(" and  al.userId = '"+loginDto.getUserId()+"' ");
		 * System.out.println("Query..."+sbf.toString()); Query qry =
		 * session.createQuery(sbf.toString()); qry.executeUpdate();
		 * }catch(Exception ex){
		 * System.out.println("Exception while inserting the User Activity : " +
		 * ex.toString()); throw new
		 * TPlusException(TPlusCodes.APPLICATION_ERROR,
		 * "Error: in logActivity "+ex); }finally {
		 * HibernetFactory.closeSession(); }
		 */
	}

	public void loadProperties() throws TPlusException {
		try {
			AdminParamsLoad.loadProperties();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void initHSM() throws TPlusException {
		try {
			AdminParamsLoad.initHSM();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
