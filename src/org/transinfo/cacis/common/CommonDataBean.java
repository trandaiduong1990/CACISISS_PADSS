package org.transinfo.cacis.common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SuppressWarnings("serial")
public class CommonDataBean  implements Serializable
{
    private String strColumn1 	= 	"";
	private String strColumn2 	= 	"";
	private String strColumn3 	= 	"";
	private String strColumn4 	= 	"";
	private String strColumn5 	= 	"";
	private String strColumn6 	= 	"";
	private String strColumn7 	= 	"";
	private String strColumn8 	= 	"";
	private String strColumn9	=	"";
	private String strColumn10 	= 	"";
	private String strColumn11 	= 	"";
	private String strColumn12 	= 	"";
	private String strColumn13 	= 	"";
	private String strColumn14 	= 	"";
	private String strColumn15 	= 	"";
	private String strColumn16 	= 	"";
	private String strColumn17 	= 	"";
	private String strColumn18 	= 	"";
	private String strColumn19	=	"";
	private String strColumn20 	= 	"";
	private String strColumn21 	= 	"";
	private String strColumn22 	= 	"";
	private String strColumn23 	= 	"";
	private String strColumn24	= 	"";
	private String strColumn25	= 	"";
	
	public List<String> lstParam = new ArrayList<String>(Arrays.asList("Parameter1", "Parameter2", "Parameter3", "Parameter4", "Parameter5",
			"Parameter6", "Parameter7", "Parameter8", "Parameter9", "Parameter10", "Parameter11", "Parameter12", "Parameter13", "Parameter14",
			"Parameter15", "Parameter16", "Parameter17", "Parameter18", "Parameter19", "Parameter20"));
	
	/*private String strColumn1 	= 	"1";
	private String strColumn2 	= 	"2";
	private String strColumn3 	= 	"3";
	private String strColumn4 	= 	"4";
	private String strColumn5 	= 	"5";
	private String strColumn6 	= 	"6";
	private String strColumn7 	= 	"7";
	private String strColumn8 	= 	"8";
	private String strColumn9	=	"9";
	private String strColumn10 	= 	"10";
	private String strColumn11 	= 	"11";
	private String strColumn12 	= 	"12";
	private String strColumn13 	= 	"13";
	private String strColumn14 	= 	"14";
	private String strColumn15 	= 	"15";
	private String strColumn16 	= 	"16";
	private String strColumn17 	= 	"17";
	private String strColumn18 	= 	"18";
	private String strColumn19	=	"19";
	private String strColumn20 	= 	"20";*/
	
	private boolean bolChecked 	= 	false;

	public static int RECORDS_IN_PAGE=10;
	public static int RECORDS_IN_TRANX_PAGE=100;

	/**   
	 * Gets the value for strColumn1
	 * @param None
	 * @returns String
	 * @throws None
	 */
	public String getColumn1 ()
	{
		return strColumn1;
	}

	/**
	 * Sets the value for strColumn1
	 * @param String
	 * @returns None
	 * @throws None
	 */
	public void setColumn1 (String strInputValue)
	{
		strColumn1 = strInputValue;
	}

	/**
	 * Gets the value for strColumn2
	 * @param None
	 * @returns String
	 * @throws None
	 */
	public String getColumn2 ()
	{
		return strColumn2;
	}

	/**
	 * Sets the value for strColumn2
	 * @param String
	 * @returns None
	 * @throws None
	 */
	public void setColumn2 (String strInputValue)
	{
		strColumn2 = strInputValue;
	}

	/**
	 * Gets the value for strColumn3
	 * @param None
	 * @returns String
	 * @throws None
	 */
	public String getColumn3 ()
	{
		return strColumn3;
	}

	/**
	 * Sets the value for strColumn3
	 * @param String
	 * @returns None
	 * @throws None
	 */
	public void setColumn3 (String strInputValue)
	{
		strColumn3 = strInputValue;
	}

	/**
	 * Gets the value for strColumn4
	 * @param None
	 * @returns String
	 * @throws None
	 */
	public String getColumn4 ()
	{
		return strColumn4;
	}

	/**
	 * Sets the value for strColumn4
	 * @param String
	 * @returns None
	 * @throws None
	 */
	public void setColumn4 (String strInputValue)
	{
		strColumn4 = strInputValue;
	}

	/**
	 * Gets the value for strColumn5
	 * @param None
	 * @returns String
	 * @throws None
	 */
	public String getColumn5 ()
	{
		return strColumn5;
	}

	/**
	 * Sets the value for strColumn5
	 * @param String
	 * @returns None
	 * @throws None
	 */
	public void setColumn5 (String strInputValue)
	{
		strColumn5 = strInputValue;
	}

	/**
	 * Gets the value for strColumn6
	 * @param None
	 * @returns String
	 * @throws None
	 */
	public String getColumn6 ()
	{
		return strColumn6;
	}

	/**
	 * Sets the value for strColumn6
	 * @param String
	 * @returns None
	 * @throws None
	 */
	public void setColumn6 (String strInputValue)
	{
		strColumn6 = strInputValue;
	}

	/**
	 * Gets the value for strColumn7
	 * @param None
	 * @returns String
	 * @throws None
	 */
	public String getColumn7  ()
	{
		return strColumn7;
	}

	/**
	 * Sets the value for strColumn7
	 * @param String
	 * @returns None
	 * @throws None
	 */
	public void setColumn7 (String strInputValue)
	{
		strColumn7 = strInputValue;
	}

	/**
	 * Gets the value for strColumn8
	 * @param None
	 * @returns String
	 * @throws None
	 */
	public String getColumn8 ()
	{
		return strColumn8;
	}

	/**
	 * Sets the value for strColumn8
	 * @param String
	 * @returns None
	 * @throws None
	 */
	public void setColumn8 (String strInputValue)
	{
		strColumn8 = strInputValue;
	}

	/**
	 * Gets the value for strColumn9
	 * @param None
	 * @returns String
	 * @throws None
	 */
	public String getColumn9 ()
	{
		return strColumn9;
	}

	/**
	 * Sets the value for strColumn9
	 * @param String
	 * @returns None
	 * @throws None
	 */
	public void setColumn9 (String strInputValue)
	{
		strColumn9 = strInputValue;
	}

	/**
	 * Gets the value for strColumn10
	 * @param None
	 * @returns String
	 * @throws None
	 */
	public String getColumn10 ()
	{
		return strColumn10;
	}

	/**
	 * Sets the value for strColumn10
	 * @param String
	 * @returns None
	 * @throws None
	 */
	public void setColumn10 (String strInputValue)
	{
		strColumn10 = strInputValue;
	}

	/**
	 * Gets the value for strColumn11
	 * @param None
	 * @returns String
	 * @throws None
	 */
	public String getColumn11 ()
	{
		return strColumn11;
	}

	/**
	 * Sets the value for strColumn11
	 * @param String
	 * @returns None
	 * @throws None
	 */
	public void setColumn11 (String strInputValue)
	{
		strColumn11 = strInputValue;
	}

	/**
	 * Gets the value for strColumn12
	 * @param None
	 * @returns String
	 * @throws None
	 */
	public String getColumn12 ()
	{
		return strColumn12;
	}

	/**
	 * Sets the value for strColumn12
	 * @param String
	 * @returns None
	 * @throws None
	 */
	public void setColumn12 (String strInputValue)
	{
		strColumn12 = strInputValue;
	}

	/**
	 * Gets the value for strColumn13
	 * @param None
	 * @returns String
	 * @throws None
	 */
	public String getColumn13 ()
	{
		return strColumn13;
	}

	/**
	 * Sets the value for strColumn13
	 * @param String
	 * @returns None
	 * @throws None
	 */
	public void setColumn13 (String strInputValue)
	{
		strColumn13 = strInputValue;
	}

	/**
	 * Gets the value for strColumn14
	 * @param None
	 * @returns String
	 * @throws None
	 */
	public String getColumn14 ()
	{
		return strColumn14;
	}

	/**
	 * Sets the value for strColumn14
	 * @param String
	 * @returns None
	 * @throws None
	 */
	public void setColumn14 (String strInputValue)
	{
		strColumn14 = strInputValue;
	}

	/**
	 * Gets the value for strColumn15
	 * @param None
	 * @returns String
	 * @throws None
	 */
	public String getColumn15 ()
	{
		return strColumn15;
	}

	/**
	 * Sets the value for strColumn15
	 * @param String
	 * @returns None
	 * @throws None
	 */
	public void setColumn15 (String strInputValue)
	{
		strColumn15 = strInputValue;
	}

	/**
	 * Gets the value for strColumn16
	 * @param None
	 * @returns String
	 * @throws None
	 */
	public String getColumn16 ()
	{
		return strColumn16;
	}

	/**
	 * Sets the value for strColumn16
	 * @param String
	 * @returns None
	 * @throws None
	 */
	public void setColumn16 (String strInputValue)
	{
		strColumn16 = strInputValue;
	}

	/**
	 * Gets the value for strColumn17
	 * @param None
	 * @returns String
	 * @throws None
	 */
	public String getColumn17 ()
	{
		return strColumn17;
	}

	/**
	 * Sets the value for strColumn17
	 * @param String
	 * @returns None
	 * @throws None
	 */
	public void setColumn17 (String strInputValue)
	{
		strColumn17 = strInputValue;
	}

	/**
	 * Gets the value for strColumn18
	 * @param None
	 * @returns String
	 * @throws None
	 */
	public String getColumn18 ()
	{
		return strColumn18;
	}

	/**
	 * Sets the value for strColumn18
	 * @param String
	 * @returns None
	 * @throws None
	 */
	public void setColumn18 (String strInputValue)
	{
		strColumn18 = strInputValue;
	}

	/**
	 * Gets the value for strColumn19
	 * @param None
	 * @returns String
	 * @throws None
	 */
	public String getColumn19 ()
	{
		return strColumn19;
	}

	/**
	 * Sets the value for strColumn19
	 * @param String
	 * @returns None
	 * @throws None
	 */
	public void setColumn19 (String strInputValue)
	{
		strColumn19 = strInputValue;
	}

	/**
	 * Gets the value for strColumn20
	 * @param None
	 * @returns String
	 * @throws None
	 */
	public String getColumn20 ()
	{
		return strColumn20;
	}

	/**
	 * Sets the value for strColumn20
	 * @param String
	 * @returns None
	 * @throws None
	 */
	public void setColumn20 (String strInputValue)
	{
		
		strColumn20 = strInputValue;
	}

	
	/**
	 * Gets the value for strColumn21
	 * @param None
	 * @returns String
	 * @throws None
	 */
	public String getColumn21 ()
	{
		return strColumn21;
	}

	/**
	 * Sets the value for strColumn21
	 * @param String
	 * @returns None
	 * @throws None
	 */
	public void setColumn21(String strInputValue)
	{
	
		strColumn21 = strInputValue;
	}
	/**
	 * Gets the value for strColumn22
	 * @param None
	 * @returns String
	 * @throws None
	 */
	public String getColumn22 ()
	{
		return strColumn22;
	}

	/**
	 * Sets the value for strColumn22
	 * @param String
	 * @returns None
	 * @throws None
	 */
	public void setColumn22(String strInputValue)
	{
		strColumn22= strInputValue;
	}
	/**
	 * Gets the value for strColumn23
	 * @param None
	 * @returns String
	 * @throws None
	 */
	public String getColumn23()
	{
		return strColumn23;
	}

	/**
	 * Sets the value for strColumn23
	 * @param String
	 * @returns None
	 * @throws None
	 */
	public void setColumn23 (String strInputValue)
	{
		strColumn23= strInputValue;
	}
	/**
	 * Gets the value for strColumn24
	 * @param None
	 * @returns String
	 * @throws None
	 */
	public String getColumn24()
	{
		return strColumn24;
	}

	/**
	 * Sets the value for strColumn24
	 * @param String
	 * @returns None
	 * @throws None
	 */
	public void setColumn24(String strInputValue)
	{
		strColumn24 = strInputValue;
	}
	/**
	 * Gets the value for strColumn25
	 * @param None
	 * @returns String
	 * @throws None
	 */
	public String getColumn25 ()
	{
		return strColumn25;
	}

	/**
	 * Sets the value for strColumn25
	 * @param String
	 * @returns None
	 * @throws None
	 */
	public void setColumn25(String strInputValue)
	{
		strColumn25 = strInputValue;
	}

	/**
	 * Gets the value for bolChecked
	 * @param None
	 * @returns boolean
	 * @throws None
	 */
	public boolean getChecked ()
	{
		return bolChecked;
	}

	/**
	 * Sets the value for bolChecked
	 * @param boolean
	 * @returns None
	 * @throws None
	 */
	public void setChecked (boolean bolInputValue)
	{
		bolChecked = bolInputValue;
	}

}