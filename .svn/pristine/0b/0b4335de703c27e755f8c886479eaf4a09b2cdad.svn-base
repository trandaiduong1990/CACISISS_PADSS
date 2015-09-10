package org.transinfo.cacis.report.databean;

import java.io.Serializable;
import java.util.ArrayList;

import org.transinfo.cacis.AdminConfig;
import org.transinfo.cacis.ErrorCodes;
import org.transinfo.cacis.util.EncryptUtility;
import org.transinfo.cacis.util.StringUtil;

public class UserDataBean extends CommonDataBean implements Serializable
{

    private String  strUserId         = "";
    private String  strPassword       = "";
    private String  strLastName       = "";
    private String  strFirstName      = "";
    private String  strRoleId     = "";
    private String  strEmailAddress      = "";
    private String  strDepartment     = "";
    private String  strPermanentAddress           = "";
    private String  strOtherAddress  = "";
    private String  strPhoneNumber       = "";
    private String  strHintQuestion      = "";
    private String  strHintAnswer      = "";
    private String  strPassword1              = "";
    private String  strPassword2              = "";
    private String  strLevelId         = ""; //Spr001
    private String  strIssuerId      = ""; //Spr001
    private String  strUserType      = ""; //Spr001
    private ArrayList marFunctionList = new ArrayList(); //Spr001
    private int  mnPasswordExpiry = 0; //Spr001
    private int   mnErrorMessage = 0; //Spr001
    private String  mszMerchantId    = ""; //Spr002
    private String  mszStationIP     = "";
    private ArrayList maszChildAcquirerList = new ArrayList();
    private ArrayList arlRoles = new ArrayList();

   /**
    * Access method for the Roles property.
    * @return the current value of the Roles property
    */
    public ArrayList getRoles()
    {
      try
      {
        if(arlRoles.size() == 0)
          populateRoleList();
      }
      catch(Exception e)
      {
        e.printStackTrace();
      }
      return arlRoles;
    }

   /**
    * Sets the value of the Roles property.
    * @param hsFunctions the new value of the Roles property
    */
    public void setRoles(ArrayList arlRoles)
    {
        this.arlRoles=arlRoles;
    }

    /**
    * Gets the value for strUserId
    * @param None
    * @return String
    * @exception None
    */
    public String getUserId()
    {
        return this.strUserId ;
    }

    /**
    * Gets the value for strPassword
    * @param None
    * @return String
    * @exception None
    */
    public String getPassword()
    {
        return this.strPassword ;
    }

    /**
    * Gets the value for strLastName
    * @param None
    * @return String
    * @exception None
    */
    public String getLastName()
    {
        return this.strLastName ;
    }

    /**
    * Gets the value for strFirstName
    * @param None
    * @return String
    * @exception None
    */
    public String getFirstName()
    {
        return this.strFirstName ;
    }

    /**
    * Gets the value for strRoleId
    * @param None
    * @return String
    * @exception None
    */
    public String getRoleId()
    {
        return this.strRoleId ;
    }
    /**
    * Gets the value for strPermanentAddress
    * @param None
    * @return String
    * @exception None
    */
    public String getPermanentAddress()
    {
        return this.strPermanentAddress ;
    }

    /**
    * Gets the value for strOtherAddress
    * @param None
    * @return String
    * @exception None
    */
    public String getOtherAddress()
    {
        return this.strOtherAddress ;
    }


    /**
    * Gets the value for strPhoneNumber
    * @param None
    * @return String
    * @exception None
    */
    public String getPhoneNumber()
    {
        return this.strPhoneNumber ;
    }


    /**
    * Gets the value for strHintQuestion
    * @param None
    * @return String
    * @exception None
    */
    public String getHintQuestion()
    {
        return this.strHintQuestion ;
    }

    /**
    * Gets the value for strHintAnswer
    * @param None
    * @return String
    * @exception None
    */
    public String getHintAnswer()
    {
        return this.strHintAnswer ;
    }

    /**
    * Gets the value for strPassword1
    * @param None
    * @return String
    * @exception None
    */
    public String getPassword1()
    {
        return this.strPassword1 ;
    }

    /**
    * Gets the value for strPassword2
    * @param None
    * @return String
    * @exception None
    */
    public String getPassword2()
    {
        return this.strPassword2 ;
    }


    /**
    * Sets the value for strUserId
    * @param UserId
    * @return None
    * @exception None
    */
    public void setUserId(String strUserId)
    {
        this.strUserId = strUserId ;
    }

    /**
    * Sets the value for strPassword
    * @param Password
    * @return None
    * @exception None
    */
    public void setPassword(String strPassword)
    {
        this.strPassword = strPassword ;
    }

    /**
    * Sets the value for strLastName
    * @param LastName
    * @return None
    * @exception None
    */
    public void setLastName(String strLastName)
    {
        this.strLastName = strLastName ;
    }

    /**
    * Sets the value for strFirstName
    * @param FirstName
    * @return None
    * @exception None
    */
    public void setFirstName(String strFirstName)
    {
        this.strFirstName = strFirstName ;
    }

    /**
    * Sets the value for strRoleId
    * @param RoleId
    * @return None
    * @exception None
    */
    public void setRoleId(String strRoleId)
    {
        this.strRoleId = strRoleId ;
    }

    /**
    * Sets the value for strPermanentAddress
    * @param PermanentAddress
    * @return None
    * @exception None
    */
    public void setPermanentAddress(String strPermanentAddress)
    {
        this.strPermanentAddress = strPermanentAddress ;
    }

    /**
    * Sets the value for strOtherAddress
    * @param OtherAddress
    * @return None
    * @exception None
    */
    public void setOtherAddress(String strOtherAddress)
    {
        this.strOtherAddress = strOtherAddress ;
    }

    /**
    * Sets the value for strPhoneNumber
    * @param PhoneNumber
    * @return None
    * @exception None
    */
    public void setPhoneNumber(String strPhoneNumber)
    {
        this.strPhoneNumber = strPhoneNumber ;
    }


    /**
    * Sets the value for strHintQuestion
    * @param HintQuestion
    * @return None
    * @exception None
    */
    public void setHintQuestion(String strHintQuestion)
    {
        this.strHintQuestion = strHintQuestion ;
    }

    /**
    * Sets the value for strHintAnswer
    * @param HintAnswer
    * @return None
    * @exception None
    */
    public void setHintAnswer(String strHintAnswer)
    {
        this.strHintAnswer = strHintAnswer ;
    }

    /**
    * Sets the value for strPassword1
    * @param Password1
    * @return None
    * @exception None
    */
    public void setPassword1(String strPassword1)
    {
        this.strPassword1 = strPassword1 ;
    }

    /**
    * Sets the value for strPassword2
    * @param Password2
    * @return None
    * @exception None
    */
    public void setPassword2(String strPassword2)
    {
        this.strPassword2 = strPassword2 ;
    }


    /**
    * Gets the value for strEmailAddress
    * @param None
    * @return String
    * @exception None
    */
    public String getEmailAddress()
    {
        return this.strEmailAddress ;
    }

    /**
    * Sets the value for strEmailAddress
    * @param strEmailAddress
    * @return None
    * @exception None
    */
    public void setEmailAddress(String strEmailAddress)
    {
            this.strEmailAddress = strEmailAddress ;
    }
     /**
    * Gets the value for strDepartment
    * @param None
    * @return String
    * @exception None
    */
    public String getDepartment()
    {
        return this.strDepartment ;
    }

    /**
    * Sets the value for strDepartment
    * @param strDepartment
    * @return None
    * @exception None
    */
    public void setDepartment(String strDepartment)
    {
            this.strDepartment = strDepartment ;
    }

//[Spr001
    public String getLevelId()
    {
      return strLevelId;
    }

    public void setLevelId(String xszlevelId)
    {
      strLevelId = xszlevelId;
    }

    public String getIssuerId()
    {
      return strIssuerId;
    }

    public void setIssuerId(String xszIssuerId)
    {
      strIssuerId = xszIssuerId;
    }

    public String getMerchantId()
    {
      return mszMerchantId;
    }

    public void setMerchantId(String xszMerchantId)
    {
      if(xszMerchantId != null)
        mszMerchantId = xszMerchantId;
    }

    public String getUserType()
    {
      return strUserType;
    }

    public void setUserType(String xszUserType)
    {
      strUserType = xszUserType;
    }

    public ArrayList getUserFunction()
    {
      return marFunctionList;
    }

    public void setUserFunction(ArrayList xarFunctionList)
    {
      marFunctionList = xarFunctionList;
    }

    public int getPasswordExpiry()
    {
      return mnPasswordExpiry;
    }

    public void setPasswordExpiry(int xnPasswordExpiry)
    {
      mnPasswordExpiry = xnPasswordExpiry;
    }

    public String getStationIP()
    {
      return mszStationIP;
    }

    public void setStationIP(String xszStationIP)
    {
      mszStationIP =  xszStationIP;
    }

    public int getErrorMessage()
    {
      return mnErrorMessage;
    }

    public void setErrorMessage(int xnErrorMessage)
    {
      mnErrorMessage = xnErrorMessage;
    }

    /**
     * This method is list the List Screen.
     * @param UserDataBean.
     * @return ArrayList
     * @Exception Exception.
     */


     public ArrayList getAll(String xszSearchUID, String xszSearchAqID) throws Exception
    {
      ArrayList arlResultList = new ArrayList();
      try
      {
        //DBManager objDBMan 		= new DBManager(AdminConfig.poolName);
        String strScreenId    =       this.getScreenId();
        int intPage 		= 	this.getPage();
        int intMin 			= 	((intPage - 1) * AdminConfig.intPaginationConstant) + 1;
        int intMax 			= 	((intPage - 1) * AdminConfig.intPaginationConstant) + AdminConfig.intPaginationConstant;
        ArrayList arlNoRecord	= 	new ArrayList() ;
        StringBuffer sbfList = new StringBuffer();
        StringBuffer sbfFrom = new StringBuffer();

        sbfList.append(" SELECT UM.USER_ID,UM.FIRST_NAME,UM.LAST_NAME,RM.ROLE_DESCRIPTION,CM.CODE_DESCRIPTION AS STATUS, ");
        sbfList.append(" TO_CHAR(UM.LAST_UPDATED_DATE,'DD/MM/YYYY') AS LAST_UPDATED_DATE,UM.LAST_UPDATED_BY, UM.ISSUER_ID ");

        sbfFrom.append(" FROM USER_MASTER UM, ROLE_MASTER RM, CODE_MASTER CM  ");
        sbfFrom.append(" WHERE UM.STATUS = CM.CODE_ID AND CM.GROUP_ID='USERSTATUS' ");
        sbfFrom.append(" AND RM.LEVEL_ID='"+this.getLevelId()+"' ");
        sbfFrom.append(" AND UM.STATUS <> 'S' ");
        if(strScreenId.trim().equals("ACTIVATION")){
          sbfFrom.append(" AND UM.STATUS IN ('C','A') ");
        }
        if(strScreenId.trim().equals("LOCK")){
          sbfFrom.append(" AND UM.STATUS = 'A' ");
        }
        if(strScreenId.trim().equals("UNLOCK")){
          sbfFrom.append(" AND UM.STATUS = 'L' ");
        }
        sbfFrom.append(" AND RM.ROLE_ID = UM.ROLE_ID ");
        if(xszSearchUID != null && xszSearchUID.trim().length() != 0){
          sbfFrom.append(" AND UM.USER_ID LIKE '%"+ xszSearchUID +"%' ");
        }

        if(!getIssuerId().trim().equalsIgnoreCase("ASP"))
          sbfFrom.append(" AND UM.ISSUER_ID = '" + getIssuerId().trim() + "' ");
        else
          if(xszSearchAqID != null && xszSearchAqID.trim().length() > 0)
            sbfFrom.append(" AND UM.ISSUER_ID = '" + xszSearchAqID.trim() + "' ");

        sbfFrom.append(" ORDER BY UM.LAST_UPDATED_DATE DESC ");
/*
        arlResultList 	= objDBMan.executeStoredProc(sbfList, sbfFrom, intMin, intMax, 8, arlResultList);
        System.out.println("SQLExec:  " + sbfFrom);
        arlNoRecord		= (ArrayList)arlResultList.get(0);

        if (arlNoRecord.size() == 0) {
          this.setErrorMessage(ErrorCodes.NO_RECORDS_FOUND);
        }
*/
      }
      catch (Exception expGeneral) {
        expGeneral.printStackTrace();
        throw expGeneral;
      }
      return arlResultList;
    }


    public boolean validate()
    {
      boolean rtnMessage = true;
      String mode = this.getMode();
      /*DBUtil dbutil = null;
      DBManager objDBManager = null;
      try{
        objDBManager = new DBManager(AdminConfig.poolName);
        dbutil = new DBUtil(AdminConfig.poolName);
      }catch(Exception e){
        AdminConfig.writeDebug("Null Connection");
        return false;
      }

      String strSql = "SELECT USER_ID FROM USER_MASTER WHERE USER_ID='"+this.getUserId()+"' AND ISSUER_ID='"+this.getIssuerId()+"' ";
      AdminConfig.writeDebug(strSql);
      if(mode.trim().equals("Add") && dbutil.checkExistrecord(strSql))
      {
        rtnMessage = false;
        this.setErrorMessage(ErrorCodes.RECORD_ALREADY_EXIST);
      }
      if(mode.trim().equals("Change") || mode.trim().equals("ModInq")
         || mode.trim().equals("Inquiry") || mode.trim().equals("ActInq"))
      {
        if(!dbutil.checkExistrecord(strSql))
        {
          rtnMessage = false;
          this.setErrorMessage(ErrorCodes.RECORD_NOT_EXIST);
        }
      }*/
      return rtnMessage;
    }

    public void getUserData() throws Exception
    {
      StringBuffer strSql = new StringBuffer();
      try
      {
        strSql.append("SELECT USER_ID, FIRST_NAME, LAST_NAME, ROLE_ID, EMAIL_ID, DEPARTMENT, PHONE, ");
        strSql.append("HINT_QUESTION, HINT_ANSWER,USER_STATION_IP,OTHER_ADDRESS, PERMANENT_ADDRESS, ISSUER_ID, ");
        strSql.append("TO_CHAR(LAST_UPDATED_DATE,'DD/MM/YYYY')LAST_UPDATED_DATE,LAST_UPDATED_BY FROM USER_MASTER ");
        strSql.append(" WHERE USER_ID='"+this.getUserId()+"' AND ISSUER_ID='"+this.getIssuerId()+"' ");
        AdminConfig.writeDebug(strSql.toString());
        System.out.println(" " +strSql.toString());
        /*DBManager objDBManager = new DBManager(AdminConfig.poolName);
        objDBManager.executeSQL(strSql.toString());
        DBResultSet rs = objDBManager.getResultSet();

        if(rs.next())
        {
          this.setUserId(rs.getString("USER_ID"));
          this.setFirstName(rs.getString("FIRST_NAME"));
          this.setLastName(rs.getString("LAST_NAME"));
          this.setRoleId(rs.getString("ROLE_ID"));
          this.setEmailAddress(StringUtil.checkNull(rs.getString("EMAIL_ID")));
          this.setDepartment(StringUtil.checkNull(rs.getString("DEPARTMENT")));
          this.setPhoneNumber(StringUtil.checkNull(rs.getString("PHONE")));
          this.setHintQuestion(rs.getString("HINT_QUESTION"));
          this.setHintAnswer(EncryptUtility.decrPassword(rs.getString("HINT_ANSWER")));
          this.setStationIP(rs.getString("USER_STATION_IP"));
          this.setOtherAddress(StringUtil.checkNull(rs.getString("OTHER_ADDRESS")));
          this.setPermanentAddress(StringUtil.checkNull(rs.getString("PERMANENT_ADDRESS")));
          this.setIssuerId(StringUtil.checkNull(rs.getString("ISSUER_ID")));
          this.setLastUpdatedDate(rs.getString("LAST_UPDATED_DATE"));
          this.setLastUpdatedBy(rs.getString("LAST_UPDATED_BY"));
        }
        else
        {
          this.setErrorMessage(ErrorCodes.RECORD_NOT_EXIST);
        }*/
        populateRoleList();
      }
      catch(Exception e)
      {
        e.printStackTrace();
      }
    }

    public void addUser(String xszUserId)
    {
      StringBuffer strSql = null;
      String strStationIP=this.getStationIP();
      System.out.println("ipaddress during add" +strStationIP);
      if (strStationIP.trim().length()==0)
      {
      strStationIP="000.000.000.000";
      System.out.println("empty stationip");
      }
      try
      {
        strSql = new StringBuffer();
        strSql.append("INSERT INTO USER_MASTER (USER_ID, FIRST_NAME, LAST_NAME, ROLE_ID, EMAIL_ID, DEPARTMENT, PHONE, ");
        strSql.append("HINT_QUESTION, HINT_ANSWER,USER_STATION_IP,OTHER_ADDRESS, PERMANENT_ADDRESS, LAST_UPDATED_DATE, LAST_UPDATED_BY, ");
        strSql.append("PASSWORD,STATUS, ISSUER_ID, USER_TYPE) ");
        strSql.append("VALUES ('"+this.getUserId()+"',");
        strSql.append("'"+this.getFirstName()+"',");
        strSql.append("'"+this.getLastName()+"',");
        strSql.append("'"+this.getRoleId()+"' ,");
        strSql.append("'"+StringUtil.checkNullstr(this.getEmailAddress())+"',");
        strSql.append("'"+StringUtil.checkNullstr(this.getDepartment())+"',");
        strSql.append("'"+StringUtil.checkNullstr(this.getPhoneNumber())+"',");
        strSql.append("'"+this.getHintQuestion()+"',");
        strSql.append("'"+EncryptUtility.encrPassword(this.getHintAnswer())+"',");
        strSql.append("'"+strStationIP+"',");
        strSql.append("'"+StringUtil.checkNullstr(this.getOtherAddress())+"',");
        strSql.append("'"+StringUtil.checkNullstr(this.getPermanentAddress())+"',");
        strSql.append("sysdate,");
        strSql.append("'"+xszUserId+"',' ','C',");
        strSql.append("'"+this.getIssuerId()+"',");
        strSql.append("'" + this.getUserType() + "')");
        AdminConfig.writeDebug(strSql.toString());
        /*DBManager objDBManager = new DBManager(AdminConfig.poolName);
        int insert = objDBManager.executeUpdate(strSql.toString());
        if(insert > 0){
          setErrorMessage(ErrorCodes.ADD_SUCCESS);
        }else{
          setErrorMessage(ErrorCodes.ADD_FAIL);
        }*/
        populateRoleList();
      }
      catch(Exception e)
      {
        e.printStackTrace();
      }
    }

    public void updateUser(String xszUserId)
    {
      StringBuffer strSql = null;
      //DBManager objDBManager = new DBManager(AdminConfig.poolName);
      String strStationIP=this.getStationIP();
      if (strStationIP.trim()=="")
      strStationIP="000.000.000.000";
      try
      {
        strSql = new StringBuffer();
        strSql.append("UPDATE USER_MASTER SET ");
        strSql.append("FIRST_NAME='"+this.getFirstName()+"',");
        strSql.append("LAST_NAME='"+this.getLastName()+"',");
        strSql.append("ROLE_ID= '"+this.getRoleId()+"',");
        strSql.append("EMAIL_ID='"+StringUtil.checkNullstr(this.getEmailAddress())+"',");
        strSql.append("DEPARTMENT='"+StringUtil.checkNullstr(this.getDepartment())+"',");
        strSql.append("PHONE='"+StringUtil.checkNullstr(this.getPhoneNumber())+"',");
        strSql.append("HINT_QUESTION='"+this.getHintQuestion()+"',");
        strSql.append("HINT_ANSWER='"+EncryptUtility.encrPassword(this.getHintAnswer())+"', ");
        strSql.append("USER_STATION_IP='"+strStationIP+"', ");
        strSql.append("OTHER_ADDRESS='"+StringUtil.checkNullstr(this.getOtherAddress())+"', ");
        strSql.append("PERMANENT_ADDRESS='"+StringUtil.checkNullstr(this.getPermanentAddress())+"', ");
        strSql.append("ISSUER_ID='"+StringUtil.checkNullstr(this.getIssuerId()+"', "));
        strSql.append("LAST_UPDATED_DATE = SYSDATE, ");
        strSql.append("LAST_UPDATED_BY ='"+xszUserId+"' ");
        strSql.append("WHERE USER_ID ='"+this.getUserId()+"' ");
        strSql.append("AND ISSUER_ID ='"+this.getIssuerId()+"' ");
        AdminConfig.writeDebug(strSql.toString());
        /*int update = objDBManager.executeUpdate(strSql.toString());
        if(update > 0){
          setErrorMessage(ErrorCodes.CHANGE_SUCCESS);
        }else{
          setErrorMessage(ErrorCodes.CHANGE_FAIL);
        }*/
        populateRoleList();
      }
      catch(Exception e)
      {
        e.printStackTrace();
      }
    }

    public StringBuffer deleteUser(String User,String IssuerId)
    {
        StringBuffer strSql = new StringBuffer();
        strSql.append("DELETE USER_MASTER WHERE USER_ID ='"+User+"' and issuer_Id='"+IssuerId+"'");
        return strSql;
    }

    public void Afterdelete(boolean delete)
    {
        try{
        if(delete)
        setErrorMessage(ErrorCodes.DELETE_SUCCESS);
        else
        setErrorMessage(ErrorCodes.DELETE_FAIL);
        populateRoleList();
        }catch (Exception expAppln){
           expAppln.printStackTrace();
        }
    }

    private void populateRoleList() throws Exception
    {
      ArrayList arlList = new ArrayList();
      Object [] objArr = null;
      try
      {
        String strMapedFunctionIds = "";
        StringBuffer strSql = new StringBuffer();
        strSql.append(" SELECT ROLE_ID,ROLE_DESCRIPTION FROM ROLE_MASTER ");
        strSql.append("  WHERE LEVEL_ID ='"+this.getLevelId()+"' ");
        strSql.append("  AND STATUS ='A'");
        AdminConfig.writeDebug(strSql.toString());
        /*DBManager objDBManager = new DBManager(AdminConfig.poolName);
        objDBManager.executeSQL(strSql.toString());
        DBResultSet rs = objDBManager.getResultSet();
        while(rs.next()){
          objArr = new Object [2];
          objArr [0] = rs.getString("ROLE_ID");
          objArr [1] = rs.getString("ROLE_DESCRIPTION");
          arlList.add(objArr);
        }*/
        this.setRoles(arlList);
      }
      catch(Exception e)
      {
        AdminConfig.writeDebug("Exception : "+e.toString());
      }
    }

    public void activateUser() throws Exception
    {
      try
      {

       StringBuffer strQuery = new StringBuffer();
       String firstloginrem="";
       strQuery.append("SELECT TO_CHAR((PARAM_VALUE+SYSDATE),'dd/MM/yyyy') AS logindays ");
       strQuery.append("FROM CONFIG_MASTER WHERE PARAM_NAME= 'FTL_VALIDITY_DAYS' AND ISSUER_ID='"+this.getIssuerId()+"'");
        AdminConfig.writeDebug( strQuery.toString());
        /*DBManager objDBManager = new DBManager(AdminConfig.poolName);
        objDBManager.executeSQL( strQuery.toString());
        DBResultSet rs1= objDBManager.getResultSet();
         while(rs1.next())
        {
        firstloginrem = rs1.getString("logindays");
        System.out.println("loginremdays" +firstloginrem);
        }
        StringBuffer sbfQuery = new StringBuffer();
        sbfQuery.append("SELECT PASSWORD,PREV_PASSWORD_1,PREV_PASSWORD_2 ");
        sbfQuery.append(" FROM  USER_MASTER ");
        sbfQuery.append(" WHERE USER_ID = '" + this.getUserId() + "'  AND ISSUER_ID= '"+this.getIssuerId()+"'");
        AdminConfig.writeDebug(sbfQuery.toString());

        objDBManager.executeSQL(sbfQuery.toString());
        DBResultSet objDBResultSet = objDBManager.getResultSet();

        if (objDBResultSet.next())
        {
          String encPassword = EncryptUtility.encrPassword(this.getPassword());
          if (encPassword.equals(objDBResultSet.getString("PASSWORD")) ||
              encPassword.equals(objDBResultSet.getString("PREV_PASSWORD_1")) ||
              encPassword.equals(objDBResultSet.getString("PREV_PASSWORD_2"))) {
            this.setErrorMessage(ErrorCodes.BAD_PASSWORD);
          }else {
            DBManager objDBMan = new DBManager(AdminConfig.poolName);
            StringBuffer sbfSQL = new StringBuffer();
            sbfSQL.append("UPDATE USER_MASTER SET ");
            sbfSQL.append(" STATUS = 'A', ");
            sbfSQL.append(" PREV_PASSWORD_2 = PREV_PASSWORD_1, ");
            sbfSQL.append(" PREV_PASSWORD_1 = PASSWORD, ");
            sbfSQL.append(" PWD_MODIFIED_ON = SYSDATE, ");
            sbfSQL.append(" LOGIN_FAIL_COUNT = 0, FTL_EXPIRY_DATE=TO_DATE('"+firstloginrem+"','DD/MM/YY'),");
            sbfSQL.append(" LAST_UPDATED_DATE = SYSDATE, ");
            sbfSQL.append(" FIRST_TIME_LOGIN = 'Y', ");
            
            sbfSQL.append(" LAST_UPDATED_BY ='"+this.getLastUpdatedBy()+"', ");
            sbfSQL.append(" PASSWORD ='"+ encPassword+"' ");
            sbfSQL.append(" WHERE      USER_ID ='"+ this.getUserId()+"' AND ISSUER_ID= '"+this.getIssuerId()+"'");
            AdminConfig.writeDebug(sbfSQL.toString());
            int intUpdateCount = objDBMan.executeUpdate(sbfSQL.toString());
                    //Execute the query
            if(intUpdateCount > 0) {
              setErrorMessage(ErrorCodes.CHANGE_SUCCESS);
            }else{
              setErrorMessage(ErrorCodes.CHANGE_FAIL);
            }
          }
        }*/
      }
      catch (Exception expAppln)
      {
        expAppln.printStackTrace();
      }
    }


    public StringBuffer lockUnlockUser(String xszUserId, String xszaArrChecked)
    {
        StringBuffer strSql = new StringBuffer();
        strSql.append(" UPDATE USER_MASTER SET ");
          if(this.getScreenId().equals("LOCK")){
            strSql.append("STATUS = 'L', ");
          }
          if(this.getScreenId().equals("UNLOCK")){
            strSql.append("STATUS = 'A', ");
            strSql.append("LOGIN_FAIL_COUNT=0, ");
          }
          strSql.append("LAST_UPDATED_DATE = SYSDATE, ");
          strSql.append("LAST_UPDATED_BY ='"+xszUserId+"' ");
          strSql.append(" WHERE USER_ID ='"+xszaArrChecked+"'");
        return strSql;
    }

    public ArrayList listChildOrgs()
    {
      try
      {
        //DBManager objDBManager = new DBManager(AdminConfig.poolName);
        StringBuffer sqlChildOrg = new StringBuffer();
        sqlChildOrg.append("Select distinct A.ISSUER_ID, A.ISSUER_NAME ");
        sqlChildOrg.append("from ISSUER_MASTER A ");
        if(!strIssuerId.equalsIgnoreCase("ASP"))
          sqlChildOrg.append("where A.ISSUER_ID = '" + strIssuerId + "' ");
        sqlChildOrg.append("order by A.ISSUER_NAME ");

        System.out.println(sqlChildOrg.toString());
        //objDBManager.executeSQL(sqlChildOrg.toString());
        /*DBResultSet objDBResultSet = objDBManager.getResultSet();
        int znAcqIndex = 0;
        maszChildAcquirerList.clear();
        while (objDBResultSet.next())
        {
          String zszAcqId = objDBResultSet.getString("ISSUER_ID");
          String zszAcqname = objDBResultSet.getString("ISSUER_NAME");
          ArrayList maszChildAcquirerInfo = new ArrayList();
          maszChildAcquirerInfo.add(0, zszAcqId);
          maszChildAcquirerInfo.add(1, zszAcqname);
          maszChildAcquirerList.add(maszChildAcquirerInfo);
        }*/
      }
      catch(Exception e)
      {
        e.printStackTrace();
      }
      return maszChildAcquirerList;
    }

    public void logActivity(String xszActivityMessage)
    {
      try
      {
        //DBManager zoDBManager = new DBManager(AdminConfig.poolName);
        StringBuffer zszSqlLog = new StringBuffer();
        zszSqlLog.append("INSERT INTO USERACTIVITIES (UserActivityID, ");
        zszSqlLog.append("DateTime, UserID,  StationIP, Activity, Issuer_Id) VALUES ");
        zszSqlLog.append("(SEQ_UserActivities.NEXTVAL, SYSDATE, '");
        zszSqlLog.append(getUserId() + "','" + getStationIP() + "','");
        zszSqlLog.append(xszActivityMessage + "','" + getIssuerId() + "')");
        //zoDBManager.executeUpdate(zszSqlLog.toString());
      }
      catch(Exception e)
      {
        System.out.println(e.toString());
      }
    }
//Spr001]


     public String getIssuerName(String IssuerId)
        {
        String sSQL=null;
        boolean val =true;
        String strIssuerName="";
        String strIssuer=new String(IssuerId);
        //DBManager objDBManager=new DBManager(AdminConfig.poolName);
        sSQL = "SELECT ISSUER_NAME FROM ISSUER_MASTER " +
                 "WHERE ISSUER_ID='" + IssuerId + "' ";
        System.out.println("ISSUER Name" + sSQL);
        try{

        /*objDBManager.executeSQL(sSQL.toString());
        DBResultSet  rs = objDBManager.getResultSet();
        while(rs.next()){
        strIssuerName = rs.getString ("ISSUER_NAME");
        }*/

        }catch(Exception ex){
        System.out.println("Database error while retrieving  ISSUER data." + ex.toString());
        ex.printStackTrace();

        }
        return strIssuerName;
        }

}
