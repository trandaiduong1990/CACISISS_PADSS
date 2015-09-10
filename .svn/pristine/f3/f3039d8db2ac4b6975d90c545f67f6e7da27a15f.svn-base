package org.transinfo.cacis.formbean.riskmanagement;

import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

import org.apache.struts.validator.ValidatorForm;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.riskmanagement.RiskCountryDAO;

@SuppressWarnings({ "unchecked", "serial" })
public class RiskCountryForm extends ValidatorForm{

	private String cardNo;
	private String countryCode;
	private String status;
	private Date updatedDate = new Date();
	private String userId;
	private String frmAction;

	private Map countryList;
	private Map citiesList = new TreeMap();
	private Map statusList;
	private Map selectedCities = new TreeMap();
	private String[] selCities;

	public RiskCountryForm(){
		// getPreListData();
	}

	public String getCardNo() {
		return cardNo;
	}           
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getStatus() {
		return status;
	}                
	public void setStatus(String status) {
		this.status = status;
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

	public Map getCountryList() {
		return countryList;
	}    

	public void setCountryList(Map countryList) {
		this.countryList = countryList;
	}

	public Map getCitiesList() {
		return citiesList;
	}           
	public void setCitiesList(Map citiesList) {
		this.citiesList = citiesList;
	}  

	public Map getStatusList() {
		return statusList;
	}

	public void setStatusList(Map statusList) {
		this.statusList = statusList;
	}

	public Map getSelectedCities() {
		return selectedCities;
	}
	public void setSelectedCities(Map selectedCities) {
		this.selectedCities = selectedCities;
	}

	public String[] getSelCities() {
		return selCities;
	}

	public void setSelCities(String[] selCities) {
		this.selCities = selCities;
	}



	public void getPreListData()
	{

		try
		{

			RiskCountryDAO objRiskCntryDAO = DAOFactory.getInstance().getRiskCountryDAO();
			if(countryList==null)
			{
				countryList = new TreeMap();
				countryList = objRiskCntryDAO.countryListData();
			}

			if(statusList==null) {
				statusList = objRiskCntryDAO.statusListData("CODE_AI");
				System.out.println("statusList==>"+statusList.size());
			}

		}catch(Exception e){
			System.out.println("Error while getting  PreListData:"+e);
		}
	}

	/** Getter for property frmAction.
	 * @return Value of property frmAction.
	 */
	 public String getFrmAction() {
		return frmAction;
	}    

	/** Setter for property frmAction.
	 * @param frmAction New value of property frmAction.
	 */
	 public void setFrmAction(String frmAction) {
		 this.frmAction = frmAction;
	 }    

}




