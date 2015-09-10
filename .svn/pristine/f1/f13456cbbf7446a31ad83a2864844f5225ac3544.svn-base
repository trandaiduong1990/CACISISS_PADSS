package org.transinfo.cacis.util;

import java.util.Date;

import org.jpos.iso.ISODate;

public class DisputeUtil {

	public static String getARN(String sequence) throws Exception
	{

		String ref = "";
		
		sequence = StringUtil.fillString(11, sequence);
		Date now = new Date(System.currentTimeMillis());

		String julianDate = ISODate.getJulianDate(now);
		ref = "7" + "470530" + julianDate + sequence;

		ref = ref + OnlineUtil.getCheckDigit(ref);

		return ref;
	}

}
