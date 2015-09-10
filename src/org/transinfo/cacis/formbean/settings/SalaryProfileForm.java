package org.transinfo.cacis.formbean.settings;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorForm;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.settings.SalaryProfileDAO;
import org.transinfo.cacis.formbean.common.SalaryForm;

@SuppressWarnings( { "serial", "unchecked" })
public class SalaryProfileForm extends ValidatorForm {

	private String cardProduct;
	private SalaryForm salarPprofile1 = new SalaryForm();
	private SalaryForm salarPprofile2 = new SalaryForm();
	private SalaryForm salarPprofile3 = new SalaryForm();
	private SalaryForm salarPprofile4 = new SalaryForm();
	private String userId;

	private Map productList;

	public void getPreListData(String issuerId) {

		try {

			SalaryProfileDAO objDAO = DAOFactory.getInstance().getSalaryProfileDAO();
			
			if (productList == null || productList.isEmpty()) {
				productList = objDAO.cardProductListData(issuerId);
			}

		} catch (Exception e) {
			System.out.println("Error while getting  PreListData:"
					+ e.getMessage());
		}

	}

	public void reset(ActionMapping mapping, HttpServletRequest request) {
		cardProduct = null;
	}

	public String getCardProduct() {
		return cardProduct;
	}

	public void setCardProduct(String cardProduct) {
		this.cardProduct = cardProduct;
	}

	public SalaryForm getSalarPprofile1() {
		return salarPprofile1;
	}

	public void setSalarPprofile1(SalaryForm salarPprofile1) {
		this.salarPprofile1 = salarPprofile1;
	}

	public SalaryForm getSalarPprofile2() {
		return salarPprofile2;
	}

	public void setSalarPprofile2(SalaryForm salarPprofile2) {
		this.salarPprofile2 = salarPprofile2;
	}

	public SalaryForm getSalarPprofile3() {
		return salarPprofile3;
	}

	public void setSalarPprofile3(SalaryForm salarPprofile3) {
		this.salarPprofile3 = salarPprofile3;
	}

	public SalaryForm getSalarPprofile4() {
		return salarPprofile4;
	}

	public void setSalarPprofile4(SalaryForm salarPprofile4) {
		this.salarPprofile4 = salarPprofile4;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Map getProductList() {
		return productList;
	}

	public void setProductList(Map productList) {
		this.productList = productList;
	}
}
