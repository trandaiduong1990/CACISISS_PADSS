package org.transinfo.cacis.formbean.authorization;

import java.util.Date;
import java.util.Map;

import org.apache.struts.validator.ValidatorForm;
import org.transinfo.cacis.common.constants.CommonCodes;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.authorization.BlackListCardDAO;


/**
 * BlackListCardFormDto Value Object.
 * This class is value object representing database table BLACKLISTCARD
 * This class is intented to be used together with associated Dao object.
 */

public class BlackListCardForm extends ValidatorForm{

    /**
     * Persistent Instance variables. This data is directly
     * mapped to the columns of database table.
     */
    private String issuerId;
    private String cardNumber;
    private String cardStatusId;
    private Date lastUpdatedDate;
    private String userId;

    private Map cardStatusList;

    public BlackListCardForm()
    {
        //getPreListData();
    }

    /**
     * Get- and Set-methods for persistent variables. The default
     * behaviour does not make any checks against malformed data,
     * so these might require some manual additions.
     */

    public String getIssuerId() {
	return issuerId;
    }

    public String getCardNumber() {
        System.out.println("In form get "+cardNumber);
	return cardNumber;
    }

    public void setIssuerId(String issuerId) {
	this.issuerId = issuerId;
    }

    public void setCardNumber(String cardNumber) {
        System.out.println("In form "+cardNumber);
	this.cardNumber = cardNumber;
    }

    public String getCardStatusId() {
        return cardStatusId;
    }

    public Date getLastUpdatedDate() {
	return lastUpdatedDate;
    }

    public String getUserId() {
	return userId;
    }

    public void setCardStatusId(String cardStatusId) {
        this.cardStatusId = cardStatusId;
    }

    public void setLastUpdatedDate(Date lastUpdatedDate) {
	this.lastUpdatedDate = lastUpdatedDate;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Map getCardStatusList() {
        return cardStatusList;
    }

    public void setCardStatusList(Map cardStatusList) {
        this.cardStatusList = cardStatusList;
    }

    public void getPreListData()
    {
	        try{
	            BlackListCardDAO objBlackListCardDAO = DAOFactory.getInstance().getBlackListDAO();
	            if(cardStatusList==null)  {
	                cardStatusList = objBlackListCardDAO.cardStatusListData(CommonCodes.CARD_GREATER);
	            }

	        }catch(Exception e){
	            System.out.println("Error while getting  PreListData:"+e);
	        }
    }

}




