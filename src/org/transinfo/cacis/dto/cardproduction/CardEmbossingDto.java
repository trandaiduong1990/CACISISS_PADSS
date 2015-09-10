package org.transinfo.cacis.dto.cardproduction;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class CardEmbossingDto implements Serializable {

	private String cardEmbossId;
	private CardsDto card;
	private String issuerId;
	private ApplicationProcessDto customer;
	private String track1;
	private String track2;
	private int status;
	private Character newCardFor;
	private Integer downloadCount;
	private Date updatedDate = new Date();
	private String lastUpdatedBy;

	public CardEmbossingDto() {
	}

	public CardsDto getCard() {
		return card;
	}

	public void setCard(CardsDto card) {
		this.card = card;
	}

	public String getIssuerId() {
		return issuerId;
	}

	public void setIssuerId(String issuerId) {
		this.issuerId = issuerId;
	}

	public ApplicationProcessDto getCustomer() {
		return customer;
	}

	public void setCustomer(ApplicationProcessDto customer) {
		this.customer = customer;
	}

	public String getTrack1() {
		return track1;
	}

	public void setTrack1(String track1) {
		this.track1 = track1;
	}

	public String getTrack2() {
		return track2;
	}

	public void setTrack2(String track2) {
		this.track2 = track2;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String getLastUpdatedBy() {
		return lastUpdatedBy;
	}

	public void setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	public Character getNewCardFor() {
		return newCardFor;
	}

	public void setNewCardFor(Character newCardFor) {
		this.newCardFor = newCardFor;
	}

	public Integer getDownloadCount() {
		return downloadCount;
	}

	public void setDownloadCount(Integer downloadCount) {
		this.downloadCount = downloadCount;
	}

	public String getCardEmbossId() {
		return cardEmbossId;
	}

	public void setCardEmbossId(String cardEmbossId) {
		this.cardEmbossId = cardEmbossId;
	}

}
