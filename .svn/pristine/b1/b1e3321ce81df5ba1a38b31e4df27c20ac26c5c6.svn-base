/*
* Copyright (c) 2001-2002 transinfo Pte Ltd. Singapore. All Rights Reserved.
* This work contains trade secrets and confidential material of  transinfo Pte Ltd. Singapore.
* and its use of disclosure in whole or in part without express written permission of
* transinfo Pte Ltd. Singapore. is prohibited.
* File Name          : TPlusException.java
* Author             : I.T. Solutions (Singapore) Pte Ltd.
* Date of Creation   : June 19, 2001
* Description        : This is a customized exception calss for TPlus.
*
* Version Number     : 1.0
*					Modification History:
* Date 		 Version No.			Modified By  	 	  Modification Details.
*/

package org.transinfo.cacis.exception;

import org.transinfo.cacis.debug.DebugWriter;

/**
*
* This class is a customized exception class for the TPlus application.
*
*/

public class BaseException extends Exception
{
	private String strErrorCode; //The Error code
	private String strErrorMessage = "TPlus Exception."; //The Error Message
	private String strAuxErrorMessage = ""; //The Aux Error Msg.
    private static String strErrorType[] = {"ERROR","WARRING"};



	/**
	*
	* This constructor sets the error code creates the TPlusException.
	* @param strErrorCode  Error code for the TPlusExceptions.
	*
	*/
	public BaseException(String strErrorCode)
	{
		this.strErrorCode = strErrorCode;
	}

	/**
	*
	*  This constructor sets the error code, auxillary message and creates the TPlusException.
	*
	*  @param strErrorCode  Error code for the TPlusExceptions.
	*  @param strAuxMessage Addition message to overwrite the TPlus Exception message.
	*
	*/
	public BaseException(String strErrorCode, String strErrMessage)
	{

		this.strErrorCode  = strErrorCode;
		this.strErrorMessage = strErrMessage;
	}


	/**
	*
	*  This constructor sets the error code, auxillary message and creates the TPlusException.
	*
	*  @param strErrorCode  Error code for the TPlusExceptions.
	*  @param strErrMessage Error message to overwrite the TPlus Exception message.
	*  @param strAuxMessage Addition message to overwrite the TPlus Aux Exception message.
	*
	*/
	public BaseException(String strErrorCode,String strErrMessage, String strAuxMessage)
	{
		this.strErrorCode  = strErrorCode;
		this.strErrorMessage = strErrMessage;
		this.strAuxErrorMessage = getErrorMsg(strAuxMessage);
if (DebugWriter.boolDebugEnabled) DebugWriter.write("TPlusException:strErrorCode="+strErrorCode);
if (DebugWriter.boolDebugEnabled) DebugWriter.write("TPlusException:strErrorMessage="+strErrorMessage);
if (DebugWriter.boolDebugEnabled) DebugWriter.write("TPlusException:strAuxErrorMessage="+strAuxErrorMessage);


	}


	/**
	 *
	 *  This method returns the Exception Id.
	 *
	 *  @returns the Exception Id
	 *
	 */
	public String getMessageId()
	{
		return strErrorCode;
	}

	/**
	 *
	 *  This method returns the Exception Message. If no message set it will return the default.
	 *
	 *  @returns the Exception Message
	 *
	 */
	public String getMessage()
	{
		return strErrorMessage;
	}

	/**
	 *
	 *  This method returns the Auxillary Exception Message. If no message set it will return the default.
	 *
	 *  @returns the Auxillary Message
	 *
	 */
	public String getAuxillaryMessage()
	{
		return strAuxErrorMessage;
	}


	/**
	*  This method returns the string representation of the object.
	*/
	public String toString()
	{
		StringBuffer	sbrErrorMessage = new StringBuffer();
		sbrErrorMessage.append(strErrorCode);
		sbrErrorMessage.append(".  ");

		if (strErrorMessage != null)
		{
			sbrErrorMessage.append(strErrorMessage);

		}
		sbrErrorMessage.append(".  ");
		if (strAuxErrorMessage != null)
		{
			sbrErrorMessage.append(strAuxErrorMessage);

		}

		return sbrErrorMessage.toString();
	}


	/**
	 * This method is used to get the ErrorMsg from the Error String.
	 * @param strExp - The Error String
	 * @return String - The Error element.
	 */
	private static String getErrorMsg(String strExp)
	{
		int intIndex = strExp.indexOf("\"");
		try {
			if (intIndex >=0)
				return strExp.substring(intIndex+1, strExp.indexOf("\"",intIndex+1));
		}
		catch(Exception exp1)
		{
		}
		return strExp;

	}

	public static String getErrorType(int mode)
	{
		if(mode==1)
		return strErrorType[1];
		else
		return strErrorType[0];
	}


  }