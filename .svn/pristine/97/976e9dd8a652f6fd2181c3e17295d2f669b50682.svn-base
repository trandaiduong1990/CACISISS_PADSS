/**
 * 
 */
package org.transinfo.cacis.util;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.validator.Field;
import org.apache.commons.validator.GenericValidator;
import org.apache.commons.validator.Validator;
import org.apache.commons.validator.ValidatorAction;
import org.apache.commons.validator.util.ValidatorUtils;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.validator.Resources;

/**
 * @author CTTI-IT-96
 * 
 */
public class CustomStrutsValidations {

	public static boolean validateMobile(Object bean, ValidatorAction action,
			Field field, ActionMessages errors, Validator validator,
			HttpServletRequest request) {

		String value = null;
		if (isString(bean)) {
			value = (String) bean;
		} else {
			value = ValidatorUtils.getValueAsString(bean, field.getProperty());
		}

		if (!GenericValidator.isBlankOrNull(value)) {
			if (value.startsWith("0")) {
				if (value.length() != 11) {
					errors.add(field.getKey(), Resources.getActionMessage(
							request, action, field));
					return false;
				} else {
					try {
						Long.valueOf(value);
					} catch (Exception e) {
						errors.add(field.getKey(), Resources.getActionMessage(
								request, action, field));
						return false;
					}
				}
			} else if (value.startsWith("94")) {
				if (value.length() != 12) {
					errors.add(field.getKey(), Resources.getActionMessage(
							request, action, field));
					return false;
				} else {
					try {
						Long.valueOf(value.substring(1));
					} catch (Exception e) {
						errors.add(field.getKey(), Resources.getActionMessage(
								request, action, field));
						return false;
					}
				}
			} else {
				errors.add(field.getKey(), Resources.getActionMessage(request,
						action, field));
				return false;
			}
		}
		return true;
	}

	protected static boolean isString(Object o) {
		return (o == null) ? true : String.class.isInstance(o);
	}

	public static boolean validateBinNo(Object bean,
			ValidatorAction action, Field field, ActionMessages errors,
			Validator validator, HttpServletRequest request) {

		String value = ValidatorUtils.getValueAsString(bean, field.getProperty());

		String sProperty2 = field.getVarValue("secondProperty");
		String value2 = ValidatorUtils.getValueAsString(bean, sProperty2);
		
		if (!GenericValidator.isBlankOrNull(value) && !GenericValidator.isBlankOrNull(value2)) {

			try {
				int binLength = Integer.parseInt(value2.trim());
				
				if (value.trim().length() != binLength) {
					errors.add(field.getKey(), Resources.getActionMessage(
							request, action, field));

					return false;
				}
			} catch (Exception e) {
				errors.add(field.getKey(), Resources.getActionMessage(
						request, action, field));
				return false;
			}
		}

		return true;
	}
	
	public static boolean validateEmbossNameLength(Object bean,
			ValidatorAction action, Field field, ActionMessages errors,
			Validator validator, HttpServletRequest request) {

		String value = ValidatorUtils.getValueAsString(bean, field.getProperty());

		String sProperty2 = field.getVarValue("secondProperty");
		String value2 = ValidatorUtils.getValueAsString(bean, sProperty2);
		
		if (!GenericValidator.isBlankOrNull(value) && !GenericValidator.isBlankOrNull(value2)) {

			try {
				DBUtil dbUtils = new DBUtil();
				int embossNameLength = dbUtils.getCardProductEmbossNameLength(value2);
				
				if (value.trim().length() > embossNameLength) {
					errors.add(field.getKey(), Resources.getActionMessage(
							request, action, field));

					return false;
				}
			} catch (Exception e) {
				errors.add(field.getKey(), Resources.getActionMessage(
						request, action, field));
				return false;
			}
		}

		return true;
	}
	
	public static boolean validateATMLink(Object bean,
			ValidatorAction action, Field field, ActionMessages errors,
			Validator validator, HttpServletRequest request) {

		String value = ValidatorUtils.getValueAsString(bean, field.getProperty());

		String sProperty2 = field.getVarValue("secondProperty");
		String value2 = ValidatorUtils.getValueAsString(bean, sProperty2);
		
		if (!GenericValidator.isBlankOrNull(value2)) {

			try {
				DBUtil dbUtils = new DBUtil();
				String cardProductType = dbUtils.getCardProductType(value2);
				
				if((!"".equals(cardProductType)) && ("ATMCard".equals(cardProductType) || "DebitCard".equals(cardProductType))){
					if("false".equals(value)){
						errors.add(field.getKey(), Resources.getActionMessage(request, action, field));
						return false;
					}
				}
			} catch (Exception e) {
				errors.add(field.getKey(), Resources.getActionMessage(request, action, field));
				return false;
			}
		}

		return true;
	}
	
	public static boolean validateATMLinkByCardNo(Object bean,
			ValidatorAction action, Field field, ActionMessages errors,
			Validator validator, HttpServletRequest request) {

		String value = ValidatorUtils.getValueAsString(bean, field.getProperty());

		String sProperty2 = field.getVarValue("secondProperty");
		String value2 = ValidatorUtils.getValueAsString(bean, sProperty2);
		
		if (!GenericValidator.isBlankOrNull(value2)) {

			try {
				//DBUtil dbUtils = new DBUtil();
				String cardProductType = value2.substring(0, 6);
				
				if((!"".equals(cardProductType)) && ("470532".equals(cardProductType))){
					if("false".equals(value)){
						errors.add(field.getKey(), Resources.getActionMessage(request, action, field));
						return false;
					}
				}
			} catch (Exception e) {
				errors.add(field.getKey(), Resources.getActionMessage(request, action, field));
				return false;
			}
		}

		return true;
	}
	
	public static boolean validateCreditAccountDetails(Object bean,
			ValidatorAction action, Field field, ActionMessages errors,
			Validator validator, HttpServletRequest request) {

		String value = ValidatorUtils.getValueAsString(bean, field.getProperty());

		String sProperty2 = field.getVarValue("secondProperty");
		String value2 = ValidatorUtils.getValueAsString(bean, sProperty2);
		
		if (!GenericValidator.isBlankOrNull(value2)) {

			try {
				DBUtil dbUtils = new DBUtil();
				String cardProductType = dbUtils.getCardProductType(value2);
				
				if("CreditCard".equals(cardProductType)){
					if("".equals(value)){
						errors.add(field.getKey(), Resources.getActionMessage(request, action, field));
						return false;
					}
				}
			} catch (Exception e) {
				errors.add(field.getKey(), Resources.getActionMessage(request, action, field));
				return false;
			}
		}

		return true;
	}
	
	public static boolean validatePrimaryCardNo(Object bean,
			ValidatorAction action, Field field, ActionMessages errors,
			Validator validator, HttpServletRequest request) {

		String value = ValidatorUtils.getValueAsString(bean, field.getProperty());
		
		if (!GenericValidator.isBlankOrNull(value)) {

			try {
				DBUtil dbUtils = new DBUtil();
				int cardHolderType = dbUtils.getCardDetails(value);
				
					if(cardHolderType != 1){
						errors.add(field.getKey(), Resources.getActionMessage(request, action, field));
						return false;
					}
					
			} catch (Exception e) {
				errors.add(field.getKey(), Resources.getActionMessage(request, action, field));
				return false;
			}
		}

		return true;
	}
	
	public static boolean validateCardType(Object bean,
			ValidatorAction action, Field field, ActionMessages errors,
			Validator validator, HttpServletRequest request) {

		String value = ValidatorUtils.getValueAsString(bean, field.getProperty());
		
		if (!GenericValidator.isBlankOrNull(value)) {

			try {
				DBUtil dbUtils = new DBUtil();
				String cardType = dbUtils.getCardProductTypeByCardNo(value);
				
					if(!"CreditCard".equalsIgnoreCase(cardType)){
						errors.add(field.getKey(), Resources.getActionMessage(request, action, field));
						return false;
					}
					
			} catch (Exception e) {
				errors.add(field.getKey(), Resources.getActionMessage(request, action, field));
				return false;
			}
		}

		return true;
	}
	
	public static boolean validateActivation(Object bean,
			ValidatorAction action, Field field, ActionMessages errors,
			Validator validator, HttpServletRequest request) {

		String value = ValidatorUtils.getValueAsString(bean, field.getProperty());

		String sProperty2 = field.getVarValue("secondProperty");
		String value2 = ValidatorUtils.getValueAsString(bean, sProperty2);
		
		String sProperty3 = field.getVarValue("thirdProperty");
		String value3 = ValidatorUtils.getValueAsString(bean, sProperty3);
		
		if (!GenericValidator.isBlankOrNull(value2) && "Y".equals(value2)) {

			try {
				
				if(GenericValidator.isBlankOrNull(value3)){
					if(!"T".equals(value)){
						errors.add(field.getKey(), Resources.getActionMessage(request, action, field));
						return false;
					}
				}
				
			} catch (Exception e) {
				errors.add(field.getKey(), Resources.getActionMessage(request, action, field));
				return false;
			}
		}

		return true;
	}
	
	public static boolean validateEmbossNameLengthByCard(Object bean,
			ValidatorAction action, Field field, ActionMessages errors,
			Validator validator, HttpServletRequest request) {

		String value = ValidatorUtils.getValueAsString(bean, field.getProperty());

		String sProperty2 = field.getVarValue("secondProperty");
		String value2 = ValidatorUtils.getValueAsString(bean, sProperty2);
		
		if (!GenericValidator.isBlankOrNull(value) && !GenericValidator.isBlankOrNull(value2)) {

			try {
				DBUtil dbUtils = new DBUtil();
				int embossNameLength = dbUtils.getCardProductEmbossNameLengthByCardNo(value2);
				
				if (value.trim().length() > embossNameLength) {
					errors.add(field.getKey(), Resources.getActionMessage(
							request, action, field));

					return false;
				}
			} catch (Exception e) {
				errors.add(field.getKey(), Resources.getActionMessage(
						request, action, field));
				return false;
			}
		}

		return true;
	}
	
	public static boolean validateIpAddress(Object bean,
			ValidatorAction action, Field field, ActionMessages errors,
			Validator validator, HttpServletRequest request) {

		String value = ValidatorUtils.getValueAsString(bean, field.getProperty());
		
		if (!GenericValidator.isBlankOrNull(value)) {

			try {
				IPAddressValidator objIpAddressValidator = new IPAddressValidator();
				boolean res = objIpAddressValidator.validate(value);
				if(!res){
					errors.add(field.getKey(), Resources.getActionMessage(
							request, action, field));

					return false;
				}
			} catch (Exception e) {
				errors.add(field.getKey(), Resources.getActionMessage(request, action, field));
				return false;
			}
		}

		return true;
	}
	
	public static boolean testMethod(Object bean,
			ValidatorAction action, Field field, ActionMessages errors,
			Validator validator, HttpServletRequest request) {

		String value = ValidatorUtils.getValueAsString(bean, field.getProperty());
		
		if (!GenericValidator.isBlankOrNull(value)) {

			try {
				
			} catch (Exception e) {
				errors.add(field.getKey(), Resources.getActionMessage(request, action, field));
				return false;
			}
		}

		return true;
	}
	
	public static boolean validateDecimalNumber(Object bean,
			ValidatorAction action, Field field, ActionMessages errors,
			Validator validator, HttpServletRequest request) {

		String value = ValidatorUtils.getValueAsString(bean, field.getProperty());

		String value2 = field.getVarValue("numberFormat");
		
		if (!GenericValidator.isBlankOrNull(value) && !GenericValidator.isBlankOrNull(value2)) {

			try {
				int decimalPart = Integer.valueOf(value2.split(",")[0]).intValue();
				int fractionPart = Integer.valueOf(value2.split(",")[1]).intValue();
				
				DecimalNumberValidator objDecimalNumberValidator = new DecimalNumberValidator(decimalPart, fractionPart);
				boolean validateRes = objDecimalNumberValidator.validate(value);
				
				if (!validateRes) {
					errors.add(field.getKey(), Resources.getActionMessage(
							request, action, field));

					return false;
				}
			} catch (Exception e) {
				errors.add(field.getKey(), Resources.getActionMessage(
						request, action, field));
				return false;
			}
		}

		return true;
	}
	
	public static boolean validateLength(Object bean,
			ValidatorAction action, Field field, ActionMessages errors,
			Validator validator, HttpServletRequest request) {

		String value = ValidatorUtils.getValueAsString(bean, field.getProperty());

		String value2 = field.getVarValue("length");
		
		if (!GenericValidator.isBlankOrNull(value) && !GenericValidator.isBlankOrNull(value2)) {

			try {
				
				if(value.length() != Integer.valueOf(value2).intValue()){
					errors.add(field.getKey(), Resources.getActionMessage(
							request, action, field));

					return false;
				}
			} catch (Exception e) {
				errors.add(field.getKey(), Resources.getActionMessage(
						request, action, field));
				return false;
			}
		}

		return true;
	}

}
