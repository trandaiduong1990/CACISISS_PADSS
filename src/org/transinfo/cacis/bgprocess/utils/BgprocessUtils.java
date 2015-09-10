package org.transinfo.cacis.bgprocess.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class BgprocessUtils {
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	public static Date getCutOffDate(Date givenDate) {

		Calendar cal = Calendar.getInstance();
		cal.setTime(givenDate);

		cal.add(Calendar.DATE, 1);

		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);

		return cal.getTime();

	}
	
	public static Date strintToDate(String strDate){
		
		Date eDate = null;
		
		try {
			eDate = sdf.parse(strDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return eDate;
	}
	
	public static Date getStartDate(Date givenDate) {

		Calendar cal = Calendar.getInstance();
		cal.setTime(givenDate);

		cal.add(Calendar.MONTH, 1);

		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);

		return cal.getTime();

	}

	public static Date getEndDate(Date givenDate) {

		Calendar cal = Calendar.getInstance();
		cal.setTime(givenDate);

		cal.add(Calendar.MONTH, 2);

		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);

		return cal.getTime();

	}

}
