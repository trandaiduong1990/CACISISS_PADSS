package org.transinfo.cacis.dto.riskmanagement;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class RiskTranxSaleCashDto implements Serializable{

        private int id;
        private int tranxNo;
        private String tranxCode;
        private String issuerId;
        private Date updatedDate = new Date();
        private String userId;

        Map riskTranxMcc = new HashMap();
        Map listTranxMcc = new TreeMap();
        Map selectedMcc = new TreeMap();

        public RiskTranxSaleCashDto(){}

        public RiskTranxSaleCashDto(String issuerId, int id){
            this.issuerId = issuerId;
            this.id = id;
        }

	public int getId() {
		return id;
	}
        public void setId(int id) {
	       this.id = id;
	}

        public int getTranxNo() {
		return tranxNo;
	}
        public void setTranxNo(int tranxNo) {
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

        public Map getRiskTranxMcc() {
		return riskTranxMcc;
	}

        public void setRiskTranxMcc(Map riskTranxMcc) {
	      this.riskTranxMcc = riskTranxMcc;
	}

        public Map getListTranxMcc() {
		return listTranxMcc;
	}

        public void setListTranxMcc(Map listTranxMcc) {
	      this.listTranxMcc = listTranxMcc;
	}

        public Map getSelectedMcc() {
		return selectedMcc;
	}

        public void setSelectedMcc(Map selectedMcc) {
	      this.selectedMcc = selectedMcc;
	}



}
