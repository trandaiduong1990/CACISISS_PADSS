package org.transinfo.cacis.formbean.riskmanagement;

import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

import org.apache.struts.validator.ValidatorForm;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.riskmanagement.RiskTranxSaleCashDAO;

public class RiskTranxSaleCashForm extends ValidatorForm{

        private String id;
        private String tranxNo;
        private String tranxCode;
        private String issuerId;
        private Date updatedDate = new Date();
        private String userId;

        private Map mccList;
        Map listTranxMcc;
        Map selectedMcc = new  TreeMap();
        private String[] selMccList;

        
        public RiskTranxSaleCashForm(){
           // getPreListData();
        }

	public String getId() {
		return id;
	}
        public void setId(String id) {
	       this.id = id;
	       System.out.println("issuerId==>"+issuerId);
	}

        public String getTranxNo() {
		return tranxNo;
	}
        public void setTranxNo(String tranxNo) {
               this.tranxNo = tranxNo;
	}

        public String getTranxCode() {
		return tranxCode;
	}
        public void setTranxCode(String tranxCode) {
	      this.tranxCode = tranxCode;
	}

        public String getIssuerId() {
		return issuerId;
	}

        public void setIssuerId(String issuerId) {
	      this.issuerId = issuerId;
	      System.out.println("issuerId==>"+issuerId);
	}

        public Date getUpdatedDate() {
		return updatedDate;
	}
        public void setUpdatedDate(Date updatedDate) {
	      this.updatedDate = updatedDate;
	}

        public String getUserId() {
		return userId;
	}

        public void setUserId(String userId) {
	      this.userId = userId;
	}

        public Map getMccList() {
			if(mccList==null)
			System.out.println("&&&&&&&&&&&&&&&&&mccList iS Null");
                return mccList;
	}
	public void setMccList(Map mccList) {
		this.mccList = mccList;
	}

        public String[] getSelMccList() {
		return selMccList;
	}

        public void setSelMccList(String[] selMccList) {
		this.selMccList = selMccList;
	}


        public Map getListTranxMcc() {
		return listTranxMcc;
	}

        public void setListTranxMcc(Map listTranxMcc) {
	      this.listTranxMcc = listTranxMcc;
	}

        public Map getSelectedMcc() {
			if(selectedMcc==null)
			System.out.println("&&&&&&&&&&&&&&&&&selectedMcc iS Null");

		return selectedMcc;
	}

        public void setSelectedMcc(Map selectedMcc) {
	      this.selectedMcc = selectedMcc;
	}




       public void getPreListData()
       {

        try
        {

            System.out.println("issuerId==>"+issuerId+" Id"+id);
            RiskTranxSaleCashDAO objRiskTranxDAO = DAOFactory.getInstance().getRiskTranxSaleCashDAO();
            //if(mccList==null)
            //{
                mccList = objRiskTranxDAO.getMcc(issuerId,id);
            //}

        }catch(Exception e){
            System.out.println("Error while getting  PreListData:"+e);
        }
    }
       
       public void getSelectedListData()
       {

        try
        {

            System.out.println("issuerId==>"+issuerId+" Id"+id);
            RiskTranxSaleCashDAO objRiskTranxDAO = DAOFactory.getInstance().getRiskTranxSaleCashDAO();
            //if(selectedMcc==null || selectedMcc.isEmpty())
            //{
            	selectedMcc = objRiskTranxDAO.getSelectedMcc(issuerId,id);
            //}

        }catch(Exception e){
            System.out.println("Error while getting  SelectedListData:"+e);
        }
    }


}




