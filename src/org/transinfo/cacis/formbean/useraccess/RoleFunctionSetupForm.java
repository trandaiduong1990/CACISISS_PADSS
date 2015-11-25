package org.transinfo.cacis.formbean.useraccess;

import java.util.HashMap;
import java.util.Map;

import org.apache.struts.validator.ValidatorForm;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.useraccess.RoleFunctionSetupDAO;


public class RoleFunctionSetupForm extends ValidatorForm{


        private String issuerId;
        private String roleId;
        private String userType;
        private String roleDesc;
        private String status;
        private String userId;
        private String lastUpdatedDate;

        private Map functionList;
        private Map selectedListSet = new HashMap();
        private Map statusList;
        private Map userTypeList;
        private String[] selFuncList;


    public RoleFunctionSetupForm()
    {
       // getPreListData();
    }

    /**
     * Get- and Set-methods for persistent variables. The default
     * behaviour does not make any checks against malformed data,
     * so these might require some manual additions.
     */

        public String getIssuerId() {
		return issuerId;
	}
	public void setIssuerId(String issuerId) {
		this.issuerId = issuerId;
	}

	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getRoleDesc() {
		return roleDesc;
	}
	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}

        public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

        public String getLastUpdatedDate() {
		return lastUpdatedDate;
	}
	public void setLastUpdatedDate(String lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}

        public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}

        public Map getSelectedListSet() {
		return selectedListSet;
	}

        public void setSelectedListSet(Map selectedListSet) {
		this.selectedListSet = selectedListSet;
		System.out.println("In MAP SIze="+selectedListSet.size());
	}

        public Map getFunctionList() {
		return functionList;
	}

        public void setFunctionList(Map functionList) {
		this.functionList = functionList;
		System.out.println("In MAP SIze="+functionList.size());
	}

        public Map getStatusList() {
		return statusList;
	}

        public void setStatusList(Map statusList) {
		this.statusList = statusList;
	}

        public String[] getSelFuncList() {
		return selFuncList;
	}

        public void setSelFuncList(String[] selFuncList) {
		this.selFuncList = selFuncList;
	}



        public Map getUserTypeList() {
			return userTypeList;
		}

		public void setUserTypeList(Map userTypeList) {
			this.userTypeList = userTypeList;
		}

		public void getPreListData()
        {
	        try{
	            RoleFunctionSetupDAO objRoleFuncDAO = DAOFactory.getInstance().getRoleFunctionSetupDAO();
	            if(functionList==null)  {
	                functionList = objRoleFuncDAO.getFunctionList(issuerId, roleId,userType);
                        System.out.println("functionList in form==>"+functionList.size());
	            }

                    if(statusList==null) {
                        statusList = objRoleFuncDAO.statusListData("CODE_AI");
                        System.out.println("statusList==>"+statusList.size());
                    }
                    
                    if(userTypeList==null) {
                    	userTypeList = objRoleFuncDAO.getUserType();
                    }

	        }catch(Exception e){
	            System.out.println("Error while getting  PreListData:"+e);
	        }
        }
}




