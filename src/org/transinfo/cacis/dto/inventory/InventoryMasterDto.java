package org.transinfo.cacis.dto.inventory;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@SuppressWarnings("serial")
public class InventoryMasterDto implements Serializable {
	private String cardProductId;
	private String supplierId;
	private int stockAvailable;
	private int lastQty;
	private String lastMode;
	private int replenishPoint;
	private String createdBy;
	private String lastUpdatedBy;
	private Date createdDate;
	private Date lastUpdatedDate;

	public InventoryMasterDto() {
		// TODO Auto-generated constructor stub
	}

	public String getCardProductId() {
		return cardProductId;
	}

	public void setCardProductId(String cardProductId) {
		this.cardProductId = cardProductId;
	}

	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}

	public int getStockAvailable() {
		return stockAvailable;
	}

	public void setStockAvailable(int stockAvailable) {
		this.stockAvailable = stockAvailable;
	}

	public int getLastQty() {
		return lastQty;
	}

	public void setLastQty(int lastQty) {
		this.lastQty = lastQty;
	}

	public String getLastMode() {
		return lastMode;
	}

	public void setLastMode(String lastMode) {
		this.lastMode = lastMode;
	}

	public int getReplenishPoint() {
		return replenishPoint;
	}

	public void setReplenishPoint(int replenishPoint) {
		this.replenishPoint = replenishPoint;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getLastUpdatedBy() {
		return lastUpdatedBy;
	}

	public void setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getLastUpdatedDate() {
		return lastUpdatedDate;
	}

	public void setLastUpdatedDate(Date lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}
	
}
