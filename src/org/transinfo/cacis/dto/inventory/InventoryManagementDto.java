package org.transinfo.cacis.dto.inventory;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@SuppressWarnings("serial")
public class InventoryManagementDto implements Serializable {
	private String authorizedBy;
	private Date authorizedDate;
	private String batchId;
	private String branchId;
	private String canceledBy;
	private Date canceledDate;
	private String cardproductId;
	private String cardrangeFrom;
	private String cardrangeTo;
	private Date deliveryDate;
	private String dispatchBy;
	private Date dispatchDate;
	private String dispatchNote;
	private String otherShip;
	private String orderBy;
	private Date orderDate;
	private Date orderExpected;
	private String orderNo;
	private String orderNote;
	private int orderQty;
	private String receivedBy;
	private Date receivedDate;
	private String receivedNote;
	private int receivedQty;
	private String returnBy;
	private Date returnDate;
	private int returnQty;
	private String returnNote;
	private String shipBy;
	private String status;
	private String trackingNo;
	private String lastUpdatedBy;
	private Date lastUpdatedDate;

	public InventoryManagementDto() {
		// TODO Auto-generated constructor stub
	}

	public int getReturnQty() {
		return returnQty;
	}

	public void setReturnQty(int returnQty) {
		this.returnQty = returnQty;
	}

	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public String getAuthorizedBy() {
		return authorizedBy;
	}

	public void setAuthorizedBy(String authorizedBy) {
		this.authorizedBy = authorizedBy;
	}

	public Date getAuthorizedDate() {
		return authorizedDate;
	}

	public void setAuthorizedDate(Date authorizedDate) {
		this.authorizedDate = authorizedDate;
	}

	public String getBatchId() {
		return batchId;
	}

	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}

	public String getBranchId() {
		return branchId;
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

	public String getCanceledBy() {
		return canceledBy;
	}

	public void setCanceledBy(String canceledBy) {
		this.canceledBy = canceledBy;
	}

	public Date getCanceledDate() {
		return canceledDate;
	}

	public void setCanceledDate(Date canceledDate) {
		this.canceledDate = canceledDate;
	}

	public String getCardproductId() {
		return cardproductId;
	}

	public void setCardproductId(String cardproductId) {
		this.cardproductId = cardproductId;
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

	public String getDispatchBy() {
		return dispatchBy;
	}

	public void setDispatchBy(String dispatchBy) {
		this.dispatchBy = dispatchBy;
	}

	public Date getDispatchDate() {
		return dispatchDate;
	}

	public void setDispatchDate(Date dispatchDate) {
		this.dispatchDate = dispatchDate;
	}

	public String getDispatchNote() {
		return dispatchNote;
	}

	public void setDispatchNote(String dispatchNote) {
		this.dispatchNote = dispatchNote;
	}

	public String getOtherShip() {
		return otherShip;
	}

	public void setOtherShip(String otherShip) {
		this.otherShip = otherShip;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Date getOrderExpected() {
		return orderExpected;
	}

	public void setOrderExpected(Date orderExpected) {
		this.orderExpected = orderExpected;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getOrderNote() {
		return orderNote;
	}

	public void setOrderNote(String orderNote) {
		this.orderNote = orderNote;
	}

	public int getOrderQty() {
		return orderQty;
	}

	public void setOrderQty(int orderQty) {
		this.orderQty = orderQty;
	}

	public String getReceivedBy() {
		return receivedBy;
	}

	public void setReceivedBy(String receivedBy) {
		this.receivedBy = receivedBy;
	}

	public Date getReceivedDate() {
		return receivedDate;
	}

	public void setReceivedDate(Date receivedDate) {
		this.receivedDate = receivedDate;
	}

	public String getReceivedNote() {
		return receivedNote;
	}

	public void setReceivedNote(String receivedNote) {
		this.receivedNote = receivedNote;
	}

	public int getReceivedQty() {
		return receivedQty;
	}

	public void setReceivedQty(int receivedQty) {
		this.receivedQty = receivedQty;
	}

	public String getReturnBy() {
		return returnBy;
	}

	public void setReturnBy(String returnBy) {
		this.returnBy = returnBy;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	public String getReturnNote() {
		return returnNote;
	}

	public void setReturnNote(String returnNote) {
		this.returnNote = returnNote;
	}

	public String getShipBy() {
		return shipBy;
	}

	public void setShipBy(String shipBy) {
		this.shipBy = shipBy;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTrackingNo() {
		return trackingNo;
	}

	public void setTrackingNo(String trackingNo) {
		this.trackingNo = trackingNo;
	}

	public String getLastUpdatedBy() {
		return lastUpdatedBy;
	}

	public void setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	public Date getLastUpdatedDate() {
		return lastUpdatedDate;
	}

	public void setLastUpdatedDate(Date lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}

}
