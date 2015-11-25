package org.transinfo.cacis.formbean.useraccess;

import java.util.Date;
import java.util.Map;

import org.apache.struts.validator.ValidatorForm;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.useraccess.UserSetupDAO;
import org.transinfo.cacis.formbean.common.DateForm;

public class UserSetupForm extends ValidatorForm{

        private String issuerId;
	private String frmUserId;
        private String searchUserId; //This is just for a validation to show Activation link
        private String firstName;
	private String lastName;
	private String roleId;
	private String emailId;
	private String department;
	private String phone;
	private String frmPassword;
        private String confirmPassword; //this is just to validate pwd in ActivationSetup
	private String prevPassword1;
	private String prevPassword2;
	private Date pwdModifiedOn;
	private DateForm pwdModifiedOnForm = new DateForm();
	private int loginFailCount;
	private String hintQuestion;
	private String hintAnswer;
	private String status;
	private String otherAddress;
	private String permanentAddress;
	private char firstTimeLogin;
	private String ftlExpDate;
	private DateForm ftlExpiryDateForm = new DateForm();
	private String userStationIp;
	private String userType;
	private Date lastUpdatedDate;
	private DateForm lastUpdatedDateForm = new DateForm();
	private String userId;
        private String issuerName;
        private String adminUserId;
        private String adminUserName;

        private String branchId;
        

        private Map roleList;
        private Map userStatusList;
        private Map ftlOptionList;

        private Map branchList;
        private Map userTypeList;


        public UserSetupForm() {
            //getPreListData();
        }


        public String getIssuerId() {
		return issuerId;
	}
	public void setIssuerId(String issuerId) {
		this.issuerId = issuerId;
	}

	public String getFrmUserId() {
		return frmUserId;
	}
	public void setFrmUserId(String frmUserId) {
		this.frmUserId = frmUserId;
	}

        public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public char getFirstTimeLogin() {
		return firstTimeLogin;
	}
	public void setFirstTimeLogin(char firstTimeLogin) {
		this.firstTimeLogin = firstTimeLogin;
	}

        public String getFtlExpDate() {
            return ftlExpDate;
        }

        public void setFtlExpDate(String ftlExpDate) {
            this.ftlExpDate = ftlExpDate;
        }

	public String getHintAnswer() {
		return hintAnswer;
	}
	public void setHintAnswer(String hintAnswer) {
		this.hintAnswer = hintAnswer;
	}
	public String getHintQuestion() {
		return hintQuestion;
	}
	public void setHintQuestion(String hintQuestion) {
		this.hintQuestion = hintQuestion;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

	public Date getLastUpdatedDate() {
		return lastUpdatedDate = new Date();
	}
	public void setLastUpdatedDate(Date lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
		if (lastUpdatedDate != null) {
			this.lastUpdatedDateForm = new DateForm(lastUpdatedDate);
		}
	}
	public DateForm getLastUpdatedDateForm() {
		return lastUpdatedDateForm;
	}
	public void setLastUpdatedDateForm(DateForm lastUpdatedDateForm) {
		this.lastUpdatedDateForm = lastUpdatedDateForm;
		lastUpdatedDate = lastUpdatedDateForm.toDate();
	}
	public int getLoginFailCount() {
		return loginFailCount;
	}
	public void setLoginFailCount(int loginFailCount) {
		this.loginFailCount = loginFailCount;
	}
	public String getOtherAddress() {
		return otherAddress;
	}
	public void setOtherAddress(String otherAddress) {
		this.otherAddress = otherAddress;
	}
	public String getFrmPassword() {
		return frmPassword;
	}
	public void setFrmPassword(String frmPassword) {
		this.frmPassword = frmPassword;
	}
	public String getPermanentAddress() {
		return permanentAddress;
	}
	public void setPermanentAddress(String permanentAddress) {
		this.permanentAddress = permanentAddress;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPrevPassword1() {
		return prevPassword1;
	}
	public void setPrevPassword1(String prevPassword1) {
		this.prevPassword1 = prevPassword1;
	}
	public String getPrevPassword2() {
		return prevPassword2;
	}
	public void setPrevPassword2(String prevPassword2) {
		this.prevPassword2 = prevPassword2;
	}
	public Date getPwdModifiedOn() {
		return pwdModifiedOn = new Date();
	}
	public void setPwdModifiedOn(Date pwdModifiedOn) {
		this.pwdModifiedOn = pwdModifiedOn;
		if (pwdModifiedOn != null) {
			this.pwdModifiedOnForm = new DateForm(pwdModifiedOn);
		}
	}
	public DateForm getPwdModifiedOnForm() {
		return pwdModifiedOnForm;
	}
	public void setPwdModifiedOnForm(DateForm pwdModifiedOnForm) {
		this.pwdModifiedOnForm = pwdModifiedOnForm;
		pwdModifiedOn = pwdModifiedOnForm.toDate();
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	public String getUserStationIp() {
		return userStationIp;
	}
	public void setUserStationIp(String userStationIp) {
		this.userStationIp = userStationIp;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}

        public Map getRoleList() {
		return roleList;
	}
	public void setRoleList(Map roleList) {
		this.roleList = roleList;
	}


        public void getPreListData()
       {
        try{
            System.out.println("issuerId,userType"+issuerId+"  "+userType);
            UserSetupDAO objUserSetupDAO = DAOFactory.getInstance().getUserSetupDAO();
            roleList = objUserSetupDAO.getRoleId(issuerId,userType);
            
            if(userStatusList==null)  {
                userStatusList = objUserSetupDAO.statusListData("USERSTATUS");
                System.out.println("userStatusList==>"+userStatusList.size());
            }
            if(ftlOptionList==null)  {
                ftlOptionList = objUserSetupDAO.statusListData("YESNO");
                System.out.println("ftlOptionList==>"+ftlOptionList.size());
            }
            if(branchList==null)  {
            	branchList = objUserSetupDAO.branchListData(issuerId);
                System.out.println("branchList==>"+branchList.size());
            }
            if(userTypeList == null) {
            	userTypeList = objUserSetupDAO.getUserType();
            }

        }catch(Exception e){
            System.out.println("Error while getting  PreListData:"+e);
        }
    }

    /** Getter for property confirmPassword.
     * @return Value of property confirmPassword.
     */
    public String getConfirmPassword() {
        return confirmPassword;
    }

    /** Setter for property confirmPassword.
     * @param confirmPassword New value of property confirmPassword.
     */
    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    /** Getter for property searchUserId.
     * @return Value of property searchUserId.
     */
    public String getSearchUserId() {
        return searchUserId;
    }

    /** Setter for property searchUserId.
     * @param searchUserId New value of property searchUserId.
     */
    public void setSearchUserId(String searchUserId) {
        this.searchUserId = searchUserId;
    }

    /** Getter for property userStatusList.
     * @return Value of property userStatusList.
     */
    public Map getUserStatusList() {
        return userStatusList;
    }

    /** Setter for property userStatusList.
     * @param userStatusList New value of property userStatusList.
     */
    public void setUserStatusList(Map userStatusList) {
        this.userStatusList = userStatusList;
    }

    /** Getter for property ftlOptionList.
     * @return Value of property ftlOptionList.
     */
    public Map getFtlOptionList() {
        return ftlOptionList;
    }

    /** Setter for property ftlOptionList.
     * @param ftlOptionList New value of property ftlOptionList.
     */
    public void setFtlOptionList(Map ftlOptionList) {
        this.ftlOptionList = ftlOptionList;
    }

    /** Getter for property issuerName.
     * @return Value of property issuerName.
     */
    public String getIssuerName() {
        return issuerName;
    }

    /** Setter for property issuerName.
     * @param issuerName New value of property issuerName.
     */
    public void setIssuerName(String issuerName) {
        this.issuerName = issuerName;
    }

    /** Getter for property adminUserName.
     * @return Value of property adminUserName.
     */
    public String getAdminUserName() {
        return adminUserName;
    }
    
    /** Setter for property adminUserName.
     * @param adminUserName New value of property adminUserName.
     */
    public void setAdminUserName(String adminUserName) {
        this.adminUserName = adminUserName;
    }
    
    /** Getter for property adminUserId.
     * @return Value of property adminUserId.
     */
    public String getAdminUserId() {
        return adminUserId;
    }
    
    /** Setter for property adminUserId.
     * @param adminUserId New value of property adminUserId.
     */
    public void setAdminUserId(String adminUserId) {
        this.adminUserId = adminUserId;
    }


	public String getBranchId() {
		return branchId;
	}


	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}


	public Map getBranchList() {
		return branchList;
	}


	public void setBranchList(Map branchList) {
		this.branchList = branchList;
	}


	public Map getUserTypeList() {
		return userTypeList;
	}


	public void setUserTypeList(Map userTypeList) {
		this.userTypeList = userTypeList;
	}
    
}




