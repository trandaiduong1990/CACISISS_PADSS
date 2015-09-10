package org.transinfo.cacis.formbean.disputemanagement;

import java.util.Map;

import org.apache.struts.validator.ValidatorActionForm;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.disputemanagement.DisputeDocumentsDAO;
import org.transinfo.cacis.formbean.common.DateForm;

public class DocumentUploadSearchForm extends ValidatorActionForm {

	private String issuerId;

	private String cardNumber;

	private String claimNumber;

	DateForm claimDateForm = new DateForm();

	private Map yearList;

	private Map monthList;

	private Map dayList;

	public String getIssuerId() {
		return issuerId;
	}

	public void setIssuerId(String issuerId) {
		this.issuerId = issuerId;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public DateForm getClaimDateForm() {
		return claimDateForm;
	}

	public void setClaimDateForm(DateForm claimDateForm) {
		this.claimDateForm = claimDateForm;
	}

	public String getClaimNumber() {
		return claimNumber;
	}

	public void setClaimNumber(String claimNumber) {
		this.claimNumber = claimNumber;
	}

	public Map getDayList() {
		return dayList;
	}

	public void setDayList(Map dayList) {
		this.dayList = dayList;
	}

	public Map getMonthList() {
		return monthList;
	}

	public void setMonthList(Map monthList) {
		this.monthList = monthList;
	}

	public Map getYearList() {
		return yearList;
	}

	public void setYearList(Map yearList) {
		this.yearList = yearList;
	}

	public void getPreListData() {
		try {
			DisputeDocumentsDAO objDisputeDocumentsDAO = DAOFactory
					.getInstance().getDisputeDocumentsDAO();
			if (yearList == null) {
				yearList = objDisputeDocumentsDAO.yearListData();
			}
			if (monthList == null) {
				monthList = objDisputeDocumentsDAO.monthListData();
			}
			if (dayList == null) {
				dayList = objDisputeDocumentsDAO.dateListData();
			}
		} catch (Exception e) {
			System.out
					.println("Error while getting DisputeDocumentsDAO formbean PreListData:"
							+ e);
		}
	}
}
