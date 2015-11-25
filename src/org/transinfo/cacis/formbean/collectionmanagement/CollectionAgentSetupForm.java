package org.transinfo.cacis.formbean.collectionmanagement;

import java.util.Map;

import org.apache.struts.validator.ValidatorForm;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.collectionmanagement.CollectionAccountDetailsDAO;
import org.transinfo.cacis.dataacess.dao.collectionmanagement.CollectionAgentDAO;

@SuppressWarnings("serial")
public class CollectionAgentSetupForm extends ValidatorForm {
	private String issuerId;
	private String agentId;
	private String agentName;
	private String address1;
	private String address2;
	private String address3;
	private String phoneNo;
	private String emailId;
	private String contactName;
	private String contactPhoneNo;
	private String agentType;
	private Map agentTypeList;
	public String getIssuerId() {
		return issuerId;
	}
	public void setIssuerId(String issuerId) {
		this.issuerId = issuerId;
	}
	public Map getAgentTypeList() {
		return agentTypeList;
	}
	public void setAgentTypeList(Map agentTypeList) {
		this.agentTypeList = agentTypeList;
	}
	public String getAgentType() {
		return agentType;
	}
	public void setAgentType(String agentType) {
		this.agentType = agentType;
	}
	public String getAgentId() {
		return agentId;
	}
	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}
	public String getAgentName() {
		return agentName;
	}
	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public String getAddress3() {
		return address3;
	}
	public void setAddress3(String address3) {
		this.address3 = address3;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getContactName() {
		return contactName;
	}
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	public String getContactPhoneNo() {
		return contactPhoneNo;
	}
	public void setContactPhoneNo(String contactPhoneNo) {
		this.contactPhoneNo = contactPhoneNo;
	}
	
	public void getPreListData() {

		try {
			CollectionAgentDAO objDAO = DAOFactory.getInstance()
					.getCollectionAgentDAO();

			if(agentTypeList == null) {
				agentTypeList = objDAO.getAgentType();
			}
		} catch (Exception e) {
			System.out
					.println("Error while getting PreList in CollectionAgentSetupForm:"
							+ e.getMessage());
		}
	}
}
