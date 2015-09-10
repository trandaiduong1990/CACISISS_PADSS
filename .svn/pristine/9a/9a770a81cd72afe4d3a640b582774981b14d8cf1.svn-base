package org.transinfo.cacis.bgprocess.utils;

import org.transinfo.cacis.constants.ICacisiss;
import org.transinfo.cacis.util.PropUtil;

@SuppressWarnings("static-access")
public class BgProcessParams {

	public static String getPropertyValue(String key) throws Exception {
		String value = "";

		try {

			PropUtil putil = new PropUtil(ICacisiss.IProperty.PROPERTY_FILE);
			value = putil.getProperty(key);

		} catch (Exception exp) {
			throw exp;
		}

		return value;
	}

}
