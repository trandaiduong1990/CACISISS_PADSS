package org.transinfo.cacis.formbean.common;

import java.io.Serializable;
import java.util.Date;

import org.transinfo.cacis.util.DateUtil;

public class DateForm implements Serializable{

	private String day;

	private String month;

	private String year;

	public DateForm() {
	}

	public DateForm(Date date) {

		this.toString(date);
	}

	public String getDay() {
		// System.out.println("Date = " + day);
		return day;
	}

	public void setDay(String day) {
		// System.out.println("Date = " + day);
		this.day = day;
	}

	public String getMonth() {
		// System.out.println("Month = " + month);
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getYear() {
		// System.out.println("Year = " + year);
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public Date toDate() {
		return DateUtil.convertDateStringToDate(day, month, year);

	}

	public void toString(Date date) {
		int dateArr[] = DateUtil.parseDate(date);
		day = new Integer(dateArr[0]).toString();
		month = new Integer(dateArr[1] + 1).toString();
		year = new Integer(dateArr[2]).toString();
	}

	public static synchronized DateForm todayDateForm() {
		DateForm todayDate = new DateForm();
		Date today = new Date();
		todayDate.setDay(Integer.toString(today.getDate()));
		todayDate.setMonth(Integer.toString(today.getMonth()));
		todayDate.setYear(Integer.toString(today.getYear() + 1900));
		return todayDate;
	}

	public static void main(String s[]) {
		DateForm df = new DateForm();
		System.out.println(df.toDate());
		df.toString(df.toDate());
		System.out.println(df.day + " " + df.month + "  " + df.year);
	}

}
