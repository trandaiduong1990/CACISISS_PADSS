package org.transinfo.cacis.dto.letters;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ApplicationProcessSearchDto implements Serializable {

	private String letterId;

	private long cardNumber;

	private String letterCategory;

	private String transDateFrom;
	private String transDateTo;

	private Date fromDate = new Date();
	private Date toDate = new Date();



	public ApplicationProcessSearchDto(){}

	public long getCardNumber() {
		return new Long(cardNumber).longValue();
	}

	public void setCardNumber(long cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getLetterId() {
		return letterId;
	}

	public void setLetterId(String letterId) {
		this.letterId = letterId;
	}

	public String getLetterCategory() {
		return letterCategory;
	}

	public void setLetterCategory(String letterCategory) {
		this.letterCategory = letterCategory;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {

		this.fromDate = fromDate;

		if (fromDate != null) {
			transDateFrom = fromDate.toString();

		}
	}


	public String getTransDateFrom() {
		return transDateFrom;
	}

	public void setTransDateFrom(String transDateFrom) {

		try{
		this.transDateFrom = transDateFrom;
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		fromDate = (Date)formatter.parse(transDateFrom);
		}catch(Exception e){System.out.println("Exception in Date Conversion="+e);}


	}

	public Date getToDate() {
		return this.toDate;
	}

	public void setToDate(Date toDateIn) {
		this.toDate = toDateIn;
		if (toDate != null) {
			transDateTo = toDateIn.toString();
		}
	}

	public String getTransDateTo() {
		return transDateTo;
	}

	public void setTransDateTo(String transDateTo) {
		try{
		this.transDateTo = transDateTo;
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		toDate = (Date)formatter.parse(transDateTo);
		}catch(Exception e){System.out.println("Exception in Date Conversion="+e);}
	}



}
