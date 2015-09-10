package org.transinfo.cacis.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DecimalNumberValidator {
	private Pattern pattern;
	private Matcher matcher;

	public DecimalNumberValidator(int digits, int decimal) {
		String DECIMAL_NUMBER_PATTERN = "^\\d{1,"+digits+"}(\\.\\d{1,"+decimal+"})?$";
		pattern = Pattern.compile(DECIMAL_NUMBER_PATTERN);
	}

	public boolean validate(final String decimalNo) {
		matcher = pattern.matcher(decimalNo);
		return matcher.matches();
	}
}
