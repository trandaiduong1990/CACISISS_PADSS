package org.transinfo.cacis.formbean.key;

import java.util.Date;
import java.util.Map;

import org.apache.struts.validator.ValidatorForm;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.key.KeyIndexDAO;
import org.transinfo.cacis.util.KeyIndexUtils;

@SuppressWarnings({ "unchecked", "serial" })
public class KeyIndexForm extends ValidatorForm {

	private String issuerId;
	private String cardProductId;
	private String keyType;
	private String keyIndex;
	private String status;
	private Date updatedDate = new Date();
	private String userId;
	private String transactionChannel;

	private Map cardProductList;
	private Map statusList;
	private Map keyTypeList;
	private Map tranxChannelList;
	
	private String cardProductName;

	public KeyIndexForm() {
		// getPreListData();
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

	public String getKeyIndex() {
		return keyIndex;
	}

	public void setKeyIndex(String keyIndex) {
		this.keyIndex = keyIndex;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Map getStatusList() {
		return statusList;
	}

	public void setStatusList(Map statusList) {
		this.statusList = statusList;
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

	public Map getCardProductList() {
		return cardProductList;
	}

	public void setCardProductList(Map cardProductList) {
		this.cardProductList = cardProductList;
	}

	/**
	 * @return the keyTypeList
	 */
	public Map getKeyTypeList() {
		return keyTypeList;
	}

	/**
	 * @param keyTypeList the keyTypeList to set
	 */
	public void setKeyTypeList(Map keyTypeList) {
		this.keyTypeList = keyTypeList;
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

	/**
	 * @return the cardProductName
	 */
	public String getCardProductName() {
		return cardProductName;
	}

	/**
	 * @param cardProductName the cardProductName to set
	 */
	public void setCardProductName(String cardProductName) {
		this.cardProductName = cardProductName;
	}

	public String getTransactionChannel() {
		return transactionChannel;
	}

	public void setTransactionChannel(String transactionChannel) {
		this.transactionChannel = transactionChannel;
	}

	public Map getTranxChannelList() {
		return tranxChannelList;
	}

	public void setTranxChannelList(Map tranxChannelList) {
		this.tranxChannelList = tranxChannelList;
	}

}
