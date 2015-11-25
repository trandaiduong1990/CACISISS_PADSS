package org.transinfo.cacis.formbean.inventory;

import java.util.Collection;
import java.util.Map;

import org.apache.struts.validator.ValidatorForm;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.collectionmanagement.CollectionAgentDAO;
import org.transinfo.cacis.dataacess.dao.collectionmanagement.DelinquencyNotificationSetupDAO;
import org.transinfo.cacis.dataacess.dao.inventory.InventoryManagementDAO;
import org.transinfo.cacis.dataacess.dao.inventory.InventoryMasterDAO;

/**
 * @author Clark
 *
 */
@SuppressWarnings("serial")
public class InventorySetupForm extends ValidatorForm {
	private String issuerId;
	private String orderNo;
	private String branchId;
	private String orderExpectedDate;
	private String cardproductId;
	private String orderQty;
	private String orderNote;
	private String orderdate;
	private String batchId;
	private String authorizedDate;
	private String authorizedBy;
	private String delieverydate;
	private String shipBy;
	private String otherShip;
	private String trackingNo;
	private String cardrangeFrom;
	private String cardrangeTo;
	private String dispatchNote;
	private String receiveddate;
	private String receivedBy;
	private String receivedQty;
	private String receivedNote;
	private String returnBy;
	private String returndate;
	private String returnNote;
	private String returnQty;
	
	private Map orderNoList;
	private Map branchIdList;
	private Map cardProductIdList;
	private Map shipByList;
	
	private String status;

	
	public String getOtherShip() {
		return otherShip;
	}

	public void setOtherShip(String otherShip) {
		this.otherShip = otherShip;
	}

	public String getReturnQty() {
		return returnQty;
	}

	public void setReturnQty(String returnQty) {
		this.returnQty = returnQty;
	}

	public String getReturnNote() {
		return returnNote;
	}

	public void setReturnNote(String returnNote) {
		this.returnNote = returnNote;
	}

	public String getReceiveddate() {
		return receiveddate;
	}

	public void setReceiveddate(String receiveddate) {
		this.receiveddate = receiveddate;
	}

	public String getReceivedBy() {
		return receivedBy;
	}

	public void setReceivedBy(String receivedBy) {
		this.receivedBy = receivedBy;
	}

	public String getReceivedQty() {
		return receivedQty;
	}

	public void setReceivedQty(String receivedQty) {
		this.receivedQty = receivedQty;
	}

	public String getReceivedNote() {
		return receivedNote;
	}

	public void setReceivedNote(String receivedNote) {
		this.receivedNote = receivedNote;
	}

	public String getDelieverydate() {
		return delieverydate;
	}

	public void setDelieverydate(String delieverydate) {
		this.delieverydate = delieverydate;
	}

	public String getShipBy() {
		return shipBy;
	}

	public void setShipBy(String shipBy) {
		this.shipBy = shipBy;
	}


	public String getTrackingNo() {
		return trackingNo;
	}

	public void setTrackingNo(String trackingNo) {
		this.trackingNo = trackingNo;
	}

	public String getCardrangeFrom() {
		return cardrangeFrom;
	}

	public void setCardrangeFrom(String cardrangeFrom) {
		this.cardrangeFrom = cardrangeFrom;
	}

	public String getCardrangeTo() {
		return cardrangeTo;
	}

	public void setCardrangeTo(String cardrangeTo) {
		this.cardrangeTo = cardrangeTo;
	}

	public String getDispatchNote() {
		return dispatchNote;
	}

	public void setDispatchNote(String dispatchNote) {
		this.dispatchNote = dispatchNote;
	}

	public Map getShipByList() {
		return shipByList;
	}

	public void setShipByList(Map shipByList) {
		this.shipByList = shipByList;
	}

	public String getBatchId() {
		return batchId;
	}

	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}

	public String getAuthorizedDate() {
		return authorizedDate;
	}

	public void setAuthorizedDate(String authorizedDate) {
		this.authorizedDate = authorizedDate;
	}

	public String getAuthorizedBy() {
		return authorizedBy;
	}

	public void setAuthorizedBy(String authorizedBy) {
		this.authorizedBy = authorizedBy;
	}

	public String getOrderdate() {
		return orderdate;
	}

	public void setOrderdate(String orderdate) {
		this.orderdate = orderdate;
	}

	public String getIssuerId() {
		return issuerId;
	}

	public void setIssuerId(String issuerId) {
		this.issuerId = issuerId;
	}

	public String getBranchId() {
		return branchId;
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

	public String getOrderExpectedDate() {
		return orderExpectedDate;
	}

	public void setOrderExpectedDate(String orderExpectedDate) {
		this.orderExpectedDate = orderExpectedDate;
	}

	public String getCardproductId() {
		return cardproductId;
	}

	public void setCardproductId(String cardproductId) {
		this.cardproductId = cardproductId;
	}

	public String getOrderQty() {
		return orderQty;
	}

	public void setOrderQty(String orderQty) {
		this.orderQty = orderQty;
	}

	public String getOrderNote() {
		return orderNote;
	}

	public void setOrderNote(String orderNote) {
		this.orderNote = orderNote;
	}

	public Map getBranchIdList() {
		return branchIdList;
	}

	public void setBranchIdList(Map branchIdList) {
		this.branchIdList = branchIdList;
	}

	public Map getCardProductIdList() {
		return cardProductIdList;
	}

	public void setCardProductIdList(Map cardProductIdList) {
		this.cardProductIdList = cardProductIdList;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public Map getOrderNoList() {
		return orderNoList;
	}

	public void setOrderNoList(Map orderNoList) {
		this.orderNoList = orderNoList;
	}

	public String getReturnBy() {
		return returnBy;
	}

	public void setReturnBy(String returnBy) {
		this.returnBy = returnBy;
	}

	public String getReturndate() {
		return returndate;
	}

	public void setReturndate(String returndate) {
		this.returndate = returndate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void getPreListData() {
		try {
			InventoryManagementDAO objDAO = DAOFactory.getInstance().getInventoryManagementDAO();

			if (orderNoList == null) {
				orderNoList = objDAO.getOrderNoList(status);
			}
			if (cardProductIdList == null) {
				cardProductIdList = objDAO.cardProductListData(issuerId);
			}
			if (branchIdList == null) {
				branchIdList = objDAO.branchListData(issuerId);
			}
			if (shipByList == null) {
				shipByList = objDAO.shipByListData();
			}
		} catch (Exception e) {
			System.out
					.println("Error while getting PreList in CustomerTypeSearchForm:"
							+ e.getMessage());
		}
	}

}
