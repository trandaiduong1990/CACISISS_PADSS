package org.transinfo.cacis.formbean.key;

import java.util.Map;

import org.apache.struts.validator.ValidatorActionForm;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.key.KeyIndexDAO;
import org.transinfo.cacis.util.KeyIndexUtils;

@SuppressWarnings( { "serial", "unchecked" })
public class KeyIndexSearchForm extends ValidatorActionForm {

	private String issuerId;
	private String cardProductId;
	private String keyType;
	private String transactionalChannel;
	private String status;

	private Map cardProductList;
	private Map keyTypeList;
	private Map tranxChannelList;
	private Map statusList;

	public KeyIndexSearchForm() {
	}

	public String getIssuerId() {
		return issuerId;
	}

	public void setIssuerId(String issuerId) {
		this.issuerId = issuerId;
	}

	public String getCardProductId() {
		return cardProductId;
	}

	public void setCardProductId(String cardProductId) {
		this.cardProductId = cardProductId;
	}

	public String getKeyType() {
		return keyType;
	}

	public void setKeyType(String keyType) {
		this.keyType = keyType;
	}

	public String getTransactionalChannel() {
		return transactionalChannel;
	}

	public void setTransactionalChannel(String transactionalChannel) {
		this.transactionalChannel = transactionalChannel;
	}

	public void getPreListData() {
		
		try {
			KeyIndexUtils objIndexUtils = new KeyIndexUtils();
			KeyIndexDAO objKeyIndexDAO = DAOFactory.getInstance().getKeyIndexDAO();
			if (cardProductList == null) {
				cardProductList = objKeyIndexDAO.cardProductListData(issuerId);
				System.out.println("cardProductList==>" + cardProductList.size());
			}

			if (statusList == null) {
				statusList = objKeyIndexDAO.statusListData("CODE_AI");
				System.out.println("statusList==>" + statusList.size());
			}
			
			if (keyTypeList == null) {
				keyTypeList = objIndexUtils.listOfKeyType();
				System.out.println("keyTypeList==>" + keyTypeList.size());
			}
			
			if (tranxChannelList == null) {
				tranxChannelList = objIndexUtils.listOfTranxChannel();
				System.out.println("tranxChannelList==>" + tranxChannelList.size());
			}

		} catch (Exception e) {
			System.out.println("Error while getting  PreListData:" + e);
		}
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Map getCardProductList() {
		return cardProductList;
	}

	public void setCardProductList(Map cardProductList) {
		this.cardProductList = cardProductList;
	}

	public Map getKeyTypeList() {
		return keyTypeList;
	}

	public void setKeyTypeList(Map keyTypeList) {
		this.keyTypeList = keyTypeList;
	}

	public Map getTranxChannelList() {
		return tranxChannelList;
	}

	public void setTranxChannelList(Map tranxChannelList) {
		this.tranxChannelList = tranxChannelList;
	}

	public Map getStatusList() {
		return statusList;
	}

	public void setStatusList(Map statusList) {
		this.statusList = statusList;
	}
}
