package org.transinfo.cacis.util;

import java.io.FileInputStream;
import java.util.Enumeration;
import java.util.Properties;
import java.util.PropertyResourceBundle;

@SuppressWarnings( { "unused", "unchecked", "static-access" })
public class PropUtil {
	private static String bundleName = "";
	private static Properties propConfig = null;

	public PropUtil(String bundleName) {
		this.bundleName = bundleName;
	}

	public static String getProperty(String key) {
		String value = null;
		try {
			PropertyResourceBundle cassisProps = (PropertyResourceBundle) PropertyResourceBundle.getBundle(bundleName);
			Enumeration ePropNames = cassisProps.getKeys();
			value = (String) cassisProps.getString(key);
		} catch (Exception e) {
			System.out.println("Error: while getting key-value :: "
					+ e.toString());
			value = null;
		}
		return value;
	}

	public PropUtil(FileInputStream fis) {
		try {
			propConfig = new java.util.Properties();
			propConfig.load(fis);
		} catch (java.io.IOException exception) {
			System.out.println("Error: while initialising the PropUtil(FileInputStream fis) :: " + exception.toString());
		}
	}

	public static String getParamValue(String strParamName) {
		String strParamValue = "";
		try {
			strParamValue = propConfig.getProperty(strParamName);
			if (strParamValue != null) {
				strParamValue.trim();
			}
			return strParamValue;
		} catch (NoSuchFieldError nsfeExp) {
			return null;
		} catch (Exception e) {
			return null;
		}
	}
}