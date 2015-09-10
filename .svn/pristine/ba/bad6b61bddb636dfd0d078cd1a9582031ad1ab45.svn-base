package org.transinfo.cacis.formbean.authorization;

import java.util.Date;
import java.util.HashMap;
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

public class BlockMerchantForm extends ValidatorForm{

    /**
     * Persistent Instance variables. This data is directly
     * mapped to the columns of database table.
     */
    private String issuerId;
    private String searchMerchantId;
    private String blockStatus;
    private String reason;
    private String merchantName;
    private Date lastUpdatedDate;
    private String userId;

    private Map blockStatusList;

    public BlockMerchantForm()
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

   

    public void setIssuerId(String issuerId) {
	this.issuerId = issuerId;
    }

    public String getSearchMerchantId() {
        System.out.println("In BlockMerchantForm getSearchMerchantId "+searchMerchantId);
	return searchMerchantId;
    }
    
    public void setSearchMerchantId(String searchMerchantId) {
        System.out.println("In BlockMerchantForm setSearchMerchantId "+searchMerchantId);
        this.searchMerchantId = searchMerchantId;
    }

   

    public Date getLastUpdatedDate() {
	return lastUpdatedDate;
    }

    public String getUserId() {
	return userId;
    }

   

    public void setLastUpdatedDate(Date lastUpdatedDate) {
	this.lastUpdatedDate = lastUpdatedDate;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Map getBlockStatusList() {
        return blockStatusList;
    }

    public void setBlockStatusList(Map blockStatusList) {
        this.blockStatusList = blockStatusList;
    }

    public void getPreListData()
    {
    	blockStatusList =new HashMap();
    	
	        try{
	            //BlackListCardDAO objBlackListCardDAO = DAOFactory.getInstance().getBlackListDAO();
	            //if(blockStatusList==null)  {
	            	blockStatusList.put("B","Block");
	            	//blockStatusList.put("U","UnBlock");
	                //blockStatusList = objBlackListCardDAO.cardStatusListData(CommonCodes.CARD_GREATER);
	            //}

	        }catch(Exception e){
	            System.out.println("Error while getting  PreListData:"+e);
	        }
	        
    }

	public String getBlockStatus() {
		return blockStatus;
	}

	public void setBlockStatus(String blockStatus) {
		this.blockStatus = blockStatus;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}
	
	  
}




