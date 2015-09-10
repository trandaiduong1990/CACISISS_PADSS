package org.transinfo.cacis.formbean.applicationforms;

import java.util.Collection;
import java.util.Map;

import org.apache.struts.validator.ValidatorForm;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.applicationforms.AddCardProcessDAO;

@SuppressWarnings({ "serial", "unchecked" })
public class AddCardProcessSearchForm extends ValidatorForm {
	private String customerName;
	private String nric;
	private Collection searchList;
	private String totalCount;

	private String pageNo;

	private Map responseCodes;

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getNric() {
		return nric;
	}

	public void setNric(String nric) {
		this.nric = nric;
	}

	public Collection getSearchList() {
		return searchList;
	}

	public void setSearchList(Collection searchList) {
		this.searchList = searchList;
	}

	public String getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(String totalCount) {
		this.totalCount = totalCount;
	}

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	public void getPreListData() {

		try {

			AddCardProcessDAO objAddCardProcessDAO = DAOFactory.getInstance()
					.getAddCardProcessDAO();

			if (responseCodes == null) {
				responseCodes = objAddCardProcessDAO.responseCodesList();
			} else {
				System.out.println("AddCardProcessList");
			}

		} catch (Exception e) {
			System.out
					.println("Error while getting PreList in AddCardProcessSearchForm:"
							+ e.getMessage());
		}
	}

	public Map getResponseCodes() {
		return responseCodes;
	}

	public void setResponseCodes(Map responseCodes) {
		this.responseCodes = responseCodes;
	}

}
