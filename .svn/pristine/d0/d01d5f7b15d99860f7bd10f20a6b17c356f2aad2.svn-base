package org.transinfo.cacis.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.transinfo.cacis.constants.ICacisiss;

@SuppressWarnings("unused")
public class StringUtil extends Object {

	private static SimpleDateFormat imagePathdobFormat = new SimpleDateFormat(
			"yyyyMMdd");

	public static boolean checkPassword(String password) {
		int status = 0;
		String str = password;
		if (str.length() > 7) {
			for (int i = 0; i < str.length() - 3; i++) {
				if (str.charAt(i) != str.charAt(i + 1)
						|| str.charAt(i) != str.charAt(i + 2)
						|| str.charAt(i) != str.charAt(i + 3))
					continue;
				status = 1;
				break;
			}

			return status != 1;
		} else {
			return false;
		}
	}

	/*
	 * This method is validate Length of password; ie Password must be between 8
	 * - 16 and no 3 contenious letters should not be same
	 */
	public static boolean checkLength3Seq(int minLength, int maxLength,
			String password) {
		boolean flag = false;
		int status = 0;
		String str = password;

		if (str.length() >= minLength && str.length() <= maxLength) {
			for (int i = 0; i < str.length() - 2; i++) {
				if (str.charAt(i) == str.charAt(i + 1)
						&& str.charAt(i) == str.charAt(i + 2)) {
					status = 1;
					break;
				}
			}
			if (status == 1) {
				flag = false;
			} else {
				flag = true;
			}
		} else {
			flag = false;
		}
		return flag;
	}

	/*
	 * This method is validate the password; ie atleast 1 character and 1 number
	 * must be in password and only password should contain only alphanumeric
	 */
	public static boolean checkPasswordAN(String password) {
		boolean flag = false;
		int c = 0;
		int n = 0;
		int nc = 0;
		int r = 0;
		for (int i = 0; i < password.length(); i++) {
			char b = password.charAt(i);
			if (Character.isDigit(b)) {
				c = 1;
			} else {
				n = 1;
			}
			if ((b < 'a' || b > 'z') && (b < 'A' || b > 'Z')
					&& (b < '0' || b > '9')) {
				nc = 1;
			}
		}
		if (c == 1 && n == 1 && nc == 0) {
			flag = true;
		}
		return flag;
	}

	public static String rmQuote(String str) {
		if (str == null || str.trim().equals(""))
			return "";
		int index = 0;
		StringBuffer sb = new StringBuffer(str);
		index = str.indexOf("'");
		while (index != -1) {
			if (index > 0) {
				str = new String(sb.insert(index, "'"));
			} else if (index == 0) {
				str = new String(sb.insert(0, "'"));
			}
			index = str.indexOf("'", index + 2);
		}
		return str;
	}

	public static String checkNullint(String str) {
		if (str == null || str.trim().equals("")) {
			str = "0";
			return str;
		} else {
			return str.trim();
		}
	}

	public static String checkNullstr(String str) {
		if (str == null || str.trim().equals("")) {
			str = " ";
			return str;
		} else {
			return str.trim();
		}
	}

	public static String checkNull(String str) {
		if (str == null || str.trim().equals("")) {
			str = "";
			return str;
		} else {
			return str.trim();
		}
	}

	public static String checkBox(String str) {
		if (str == null || str.trim().equals("")) {
			return "0";
		} else {
			return "1";
		}
	}

	public static String changeTextBox(String str) {
		if (str == null || str.trim().equals(""))
			str = "";
		if (str.trim().equals("on")) {
			return "Y";
		} else {
			return "N";
		}
	}

	public static boolean checkNulldate(String str) {
		boolean flag = false;
		if (str == null || str.trim().equals("")) {
			flag = true;
		}
		return flag;
	}

	public static String convertValue(String str) {
		String newStr = "";
		if (str == null || str.equals("")) {
			newStr = "";
			return str;
		} else {
			char tempChar;
			for (int i = 0; i < str.length(); i++) {
				tempChar = str.charAt(i);
				newStr = newStr + tempChar;
				if (tempChar == '\'') {
					newStr = newStr + tempChar;
				}
			}
			return newStr;
		}
	}

	/**
	 * This method functions pad the given char to the left of the string.
	 * 
	 * @param strValue
	 *            Any string.
	 * @param totLength
	 *            Total length of the string.
	 * @param padChar
	 *            Padding character.
	 * @return Formated string.
	 */
	public static String LPAD(String strValue, int totLength, String padChar) {
		String rtnValue = "";
		int strLen = (String.valueOf(strValue)).length();
		for (int i = 1; i <= (totLength - strLen); i++) {
			rtnValue += padChar;
		}
		return rtnValue + strValue;
	}

	/**
	 * This method functions pad the given char to the right of the string.
	 * 
	 * @param strValue
	 *            Any string.
	 * @param totLength
	 *            Total length of the string.
	 * @param padChar
	 *            Padding character.
	 * @return Formated string.
	 */
	public static String RPAD(String strValue, int totLength, String padChar) {
		String rtnValue = "";
		
		if(strValue == null)
			strValue = "";
		
		int strLen = (String.valueOf(strValue)).length();
		for (int i = 1; i <= (totLength - strLen); i++) {
			rtnValue += padChar;
		}
		return strValue + rtnValue;
	}

	/**
	 * This method alters the given string.
	 * 
	 * @param strOriginal
	 *            Original string.
	 * @param intlength
	 *            Total length.
	 * @param padChar
	 *            Padding character.
	 * @param justify
	 *            Justification.
	 * @return Formated string.
	 */
	public static String alterString(String strOriginal, int intlength,
			String padChar, String justify) {
		try {
			if (strOriginal.length() == intlength) {
				return strOriginal;
			} else if (justify.trim().equals("L")) {
				return LPAD(strOriginal, intlength, padChar);
			} else if (justify.trim().equals("R")) {
				return RPAD(strOriginal, intlength, padChar);
			}
		} catch (Exception exp) {
		}

		return strOriginal;
	}

	/**
	 * This method replaces all occurance of a substring with the given
	 * substring in a string
	 * 
	 * @param strString
	 *            The parent string
	 * @param strSrchString
	 *            The substring which needs to be replaced
	 * @param strRplString
	 *            The new substring which will replace all occurences of
	 *            strsrchString
	 * @return String Parent string with all occurences of strsrchString
	 *         replaced by strRplString
	 */
	public static String replaceString(String strString, String strSrchString,
			String strRplString) {

		if (strString == null)
			return "";

		String strOutString = "";
		int intIndex = 0;
		int intPrevIndex = 0;
		int intSrcStrLength = strSrchString.length();

		do {
			intIndex = strString.indexOf(strSrchString, intPrevIndex);

			if (intIndex == -1) {
				strOutString += strString.substring(intPrevIndex);
				break;
			}

			strOutString += strString.substring(intPrevIndex, intIndex)
					+ strRplString;
			intPrevIndex = intIndex + intSrcStrLength;

		} while (true);

		return strOutString;
	}

	public static String changeExpDateToTrack(String strExpDate) {
		String rtnValue = "";

		rtnValue = strExpDate.substring(2, 4) + strExpDate.substring(0, 2);

		return rtnValue;
	}

	public static String imagePathGet(String embossName, Date dob) {
		String imagepath = "";

		try {
			imagepath = AdminParamsLoad.embossImagespath + embossName + "_"
					+ imagePathdobFormat.format(dob) + "."
					+ AdminParamsLoad.embossImageFormat;
		} catch (Exception e) {
		}

		return imagepath;
	}
	
	public static String formatExpDate(String expDate){
		String res = "";
		
		res = expDate.substring(0, 2) + ICacisiss.IEmboss.EXP_DATE_SEPARATOR + expDate.substring(2, 4);
		
		return res;
	}
	
	public static String getFormattedCardNo(String cardNo){
		StringBuffer resBuff = new StringBuffer();
		
		int cardNoLength = cardNo.trim().length();
		int splitCount = ICacisiss.IEmboss.CARD_NUMBER_SPLIT_COUNT;
		
		int noOfLoops = 0;
		
		int modVal = (cardNoLength % splitCount);
		if(modVal == 0){
			noOfLoops = (cardNoLength/splitCount)-1;
		}else{
			noOfLoops = ((cardNoLength - modVal)/splitCount);
		}
		
		String subStringVal = "";
		for(int i = 0; i < noOfLoops; i++){
			subStringVal = cardNo.substring(i*splitCount, (i+1)*splitCount);
			resBuff.append(subStringVal);
			resBuff.append(ICacisiss.IEmboss.CARD_NUMBER_SEPARATOR);
		}
		
		subStringVal = cardNo.substring(noOfLoops*splitCount);
		resBuff.append(subStringVal);
		
		return resBuff.toString();
	}
	
	public static String confirmString(String strIns){
		String res = "";
		
		if(strIns == null){
			res = "";
		}else{
			res = strIns.trim();
		}
		
		return res;
	}
	
	public static double getDoubleAmt(String strAmt){
		double res = 0;
		
		if(!"".equals(strAmt)){
			String formatedAmt = strAmt.substring(0, 10) + "." + strAmt.substring(10, 12);
			
			try{
				res = Double.parseDouble(formatedAmt);
			}catch (Exception e) {
				res = 0;
			}
		}
		
		return res;
	}


	public static String getRequireString(String value, int requiredLen) {

		String res = "";

		if (value.length() > requiredLen) {
			res = value.substring(0, requiredLen);
		} else {
			res = value;
		}

		return res;
	}

	public static String getSpace(int noOfSpaces) {

		String res = "";
		for (int i = 0; i < noOfSpaces; i++) {
			res += " ";
		}

		return res;
	}

	public static String fillString(int len, String str) {
		String st = str;
		while (st.length() < len)
			st = "0" + st;
		
		return st;
	}

	public static String getMaskedCardNo(String originalCardNo) {
		String res = "";

		try {

			StringBuffer sbf = new StringBuffer();
			sbf.append(originalCardNo.substring(0, 6));
			sbf.append("XXXX");
			sbf.append(originalCardNo.substring(10));

			res = sbf.toString();

		} catch (Exception e) {
			res = "XXXXXXXXXXXXXXXX";
		}

		return res;
	}

}