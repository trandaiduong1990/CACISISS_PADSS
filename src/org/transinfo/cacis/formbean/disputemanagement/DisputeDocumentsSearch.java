package org.transinfo.cacis.formbean.disputemanagement;

import java.util.Map;

import org.apache.struts.validator.ValidatorActionForm;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.disputemanagement.DisputeDocumentsDAO;

public class DisputeDocumentsSearch extends ValidatorActionForm{

	private String reasonCode;
	
    private Map reasonCodeList;

    public void getPreListData()
    {
        try{
        	DisputeDocumentsDAO objDisputeDocumentsDAO = DAOFactory.getInstance().getDisputeDocumentsDAO();
            if(reasonCodeList==null)  {
            	reasonCodeList = objDisputeDocumentsDAO.reasonCodeListData();
             }
        }catch(Exception e){
            System.out.println("Error while getting DisputeDocumentsDAO formbean PreListData:"+e);
        }
    }

	public String getReasonCode() {
		return reasonCode;
	}

	public void setReasonCode(String reasonCode) {
		this.reasonCode = reasonCode;
	}

	public Map getReasonCodeList() {
		return reasonCodeList;
	}

	public void setReasonCodeList(Map reasonCodeList) {
		this.reasonCodeList = reasonCodeList;
	}
    
}
