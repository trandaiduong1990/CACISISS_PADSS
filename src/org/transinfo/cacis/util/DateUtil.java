package org.transinfo.cacis.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@SuppressWarnings("deprecation")
public class DateUtil extends Object {

	private static SimpleDateFormat standardDateFormat = new SimpleDateFormat("dd/MM/yyyy");
	private static SimpleDateFormat expiryDateFormat = new SimpleDateFormat("MMyy");
	private static SimpleDateFormat tranxDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	private static SimpleDateFormat icvvDateFormat = new SimpleDateFormat("yyMM");
	private static SimpleDateFormat arnDateFormat = new SimpleDateFormat("yddd");
	private static SimpleDateFormat standardDateFormatWithHyphen = new SimpleDateFormat("dd-MM-yyyy");

	public static Date convertDateStringToDate(String dateStr) {
		try {
			return standardDateFormat.parse(dateStr);
		} catch (ParseException pe) {
			return null;
		}
	}

	public static Date convertDateStringToDate(String date, String month,
			String year) {
		try {
			String dateString = date + "/" + month + "/" + year;
			Date objDate = standardDateFormat.parse(dateString);
			return objDate;
		} catch (ParseException pe) {
			return null;
		}
	}

	public static String convertDateToDateString(Date date) {
		return standardDateFormat.format(date);
	}

	public static int[] parseDate(Date date) {
		int dateArr[] = new int[3];
		dateArr[0] = date.getDate();
		dateArr[1] = date.getMonth();
		dateArr[2] = 1900 + date.getYear();

		return dateArr;
	}

	public static String getDateFormat() {

		String strOutDt = new SimpleDateFormat("MMddmmSS").format(new Date());
		return strOutDt;
	}

	public static String getCardIdDateFormat() {

		String strOutDt = new SimpleDateFormat("ddMMyyyy").format(new Date());
		return strOutDt;
	}

	public static String getDisMngDateFormat() {

		String strOutDt = new SimpleDateFormat("ddMM").format(new Date());
		return strOutDt;
	}

	public static String getDocumentIdFormat() {
		// Created for IdsGenartion.java to get the Document Id
		// example: Mon Jul 31 11:10:15 GMT+08:00 2006
		// Year - yy => 06
		// Month - MM => 07
		// Day - dd => 31
		// Hours in 24 - kk => 11
		// Minutes - mm => 10
		// Seconds - ss => 15
		String strOutDt = new SimpleDateFormat("yyMMddkkmmss")
				.format(new Date());
		return strOutDt;
	}

	/*
	 * String strTmp = "09/21/04"; Date dtTmp = new
	 * SimpleDateFormat("MM/dd/yy").parse(strTmp); String strOutDt = new
	 * SimpleDateFormat("yyyyMMddhhmmss").format(dtTmp);
	 */
	public static void main(String s[]) {

		//String strOutDt = new SimpleDateFormat("ddMMMyy").format(new Date());
		System.out.println(arnDate(new Date()));
	}

	public static String getCurrentDate() {
		String cDate = "";

		Date now = new Date();
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

		cDate = df.format(now);

		return cDate;
	}

	public static Date addMonths(final Date date, final int months) {
		Date calculatedDate = null;

		if (date != null) {
			final Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.add(Calendar.MONTH, months);
			calculatedDate = new Date(calendar.getTime().getTime());
		}

		return calculatedDate;
	}

	public static Date convertExpiryDateStringToDate(String dateStr) {
		try {
			return expiryDateFormat.parse(dateStr);
		} catch (ParseException pe) {
			return null;
		}
	}

	public static String convertIssueDateToDateString(Date date) {
		return expiryDateFormat.format(date);
	}

	public static String convertIssueDateToiCvvFormat(Date date) {
		return icvvDateFormat.format(date);
	}
	
	public static Date convertTranxDateStringToDate(String dateStr) {
		try {
			return tranxDateFormat.parse(dateStr);
		} catch (ParseException pe) {
			return null;
		}
	}

	public static String convertTranxDateToDateString(Date date) {
		return tranxDateFormat.format(date);
	}

	public static String getStrDate(String format, Date date) {
		return (new SimpleDateFormat(format).format(date)).toUpperCase();
	}

	public static String arnDate(Date date) {
		return arnDateFormat.format(date);
	}
	
	public static String convertDateToDateStringWithHyphen(Date date) {
		return standardDateFormatWithHyphen.format(date);
	}
	
	public static Date convertDateStringToDateWithHyphen(String dateStr) {
		try {
			return standardDateFormatWithHyphen.parse(dateStr);
		} catch (ParseException pe) {
			return null;
		}
	}
}