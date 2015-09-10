package org.transinfo.cacis.util;

import java.util.regex.Pattern;

import org.apache.log4j.Logger;

public class RegExUtil {
	
	private static Logger logger = Logger.getLogger(RegExUtil.class);
	
	private static String pattern = "^([a-zA-Z_0-9/)(]+)?$";
	
	public static boolean isValidNRC(String nrc) throws Exception{
		
		boolean res = false;
		
		try{
			res = Pattern.matches(pattern, nrc);
		}catch (Exception e) {
			logger.error(e);
			throw e;
		}
		
		return res;
		
	}

	public static void main(String[] args) throws Exception {
		System.out.println(isValidNRC("12n12/()"));
	}

}
