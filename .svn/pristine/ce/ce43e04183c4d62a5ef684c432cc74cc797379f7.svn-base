package org.transinfo.cacis.formbean.authorization;

import java.util.Map;

import org.apache.struts.validator.ValidatorActionForm;
import org.transinfo.cacis.common.constants.CommonCodes;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.authorization.BlackListCardDAO;

public class BlockMerchantSearchForm extends ValidatorActionForm{

	
    private String issuerId;
    private String searchMerchantId;
   

    
    public BlockMerchantSearchForm(){}

    
    public String getIssuerId() {
          return this.issuerId;
    }
    public void setIssuerId(String issuerId) {
          this.issuerId = issuerId;
    }
    public String getSearchMerchantId()
    {
          return this.searchMerchantId;
    }
    public void setSearchMerchantId(String searchMerchantId) {
          this.searchMerchantId = searchMerchantId;
    }
    /*
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
*/
}
