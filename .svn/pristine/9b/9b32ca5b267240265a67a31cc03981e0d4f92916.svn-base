<!--
// =====================================================================================
// Function:	isBlank(strCheck)
// usage 	: Check whether string is empty.
// input		: strCheck		check value	
// output	: True or False
// =====================================================================================
function isBlank(strCheck){
	var	len = strCheck.length;
	var i ;
	for (i=0;i<len ;++i )
	{
	  if (strCheck.charAt(i) != " "){ 	
	return false}
	}	
	return true
}

// =====================================================================================
// Function:	checkEmpty(fieldName,object)
// usage 	: Check whether string is empty.
// input		: fieldName		check field name	
//				  object				check object
// output	: True or False
// Example : onChange="checkEmpty("Name", this)"
// =====================================================================================
function checkEmpty(fieldName,object){
	if (isBlank(object.value))
	{
		alert("Please enter " + fieldName );
		object.focus();
		//object.select();
		return false;
	}
	return true;
}

// =====================================================================================
// Function:	checkDecimals(fieldName,object)
// usage 	: Check whether field is valid decimal number.
// input		: fieldName		check field name	
//				  object			    check object
// output	: True or False
// Example : onChange="checkDecimals("Amount", this)"
// =====================================================================================
function checkDecimals(fieldName, object) {

decimals = 2;  // how many decimals are allowed?

	if (isNaN(object.value)) {
		alert("Please enter a valid number.  (" + fieldName + ")");
		object.select();
		object.focus();
		return	false;
		}
	else {
		timeshundred = parseFloat(object.value * Math.pow(10, decimals));
		integervalue = parseInt(parseFloat(object.value) * Math.pow(10, decimals));
		if (timeshundred != integervalue){
			alert ("Please enter a number not more than " + decimals + " decimal places. (" + fieldName + ")" );
			object.select();
			object.focus();
			return	false;
		}else if (object.value.length == 13){
			if ((object.value).charAt(10) != '.'){
			alert ("Please enter a Valid Number (9999999999.99) (" + fieldName + ")" );
			object.select();
			object.focus();
			return	false;
			}
		}
	}
	return	true;
}

// =====================================================================================
// Function:	checkOnlyNumber(fieldName,object)
// usage 	: Check whether field is valid number only (0123456789).
// input		: fieldName		check field name	
//				  object			    check object
// output	: True or False
// Example : onChange="checkOnlyNumber("Amount", this)"
// =====================================================================================
function checkOnlyNumber(fieldName, object) {
	string = object.value
	var valid = "0123456789"	// Valid value is only these number
	var temp;
	var err = 0;
	
	for (var i=0; i< string.length; i++) {
		temp = "" + string.substring(i, i+1);
		if (valid.indexOf(temp) == "-1")	err = 1;
	}
	if (err == 1){
		alert ("Please enter number only (" + fieldName + ")");
		object.select();
		object.focus();
		return false;
	}
	else	return true;
}
// =====================================================================================
// Function:	checkOblank(fieldName,object)
// usage 	: Check whether field is valid number only (0123456789).
// input		: fieldName		check field name	
//				  object			    check object
// output	: True or False
// Example : onChange="checkOblank("Amount", this)"
// =====================================================================================
function checkOblank(fieldName, object) {
	string = object.value
	var valid = "0123456789"	// Valid value is only these number
	var temp;
	var err = 0;
	if (isBlank(string) ){
	return true;
	} else {
		for (var i=0; i< string.length; i++) {
			temp = "" + string.substring(i, i+1);
			if (valid.indexOf(temp) == "-1")	err = 1;
		}
		if (err == 1){
			alert ("Please enter number only (" + fieldName + ")");
			object.select();
			object.focus();
			return false;
		}
		else	return true;
	}
}
// =====================================================================================
// Function:	checkCardNumber(object)
// usage 	: Check whether field is valid card number.
// input		: object			    check object
// output	: True or False
// Example : onChange="checkCardNumber(this)"
// =====================================================================================
function checkCardNumber(object) {
	var length = object.value.length;
	if  (checkOnlyNumber("Card Number", object))	
		if (length >= 15)	return true;
		else {
		alert("Please enter valid Card Number");
		object.select();
		object.focus();
		return false;
		}	
}
// =====================================================================================
// Function:	checkChoice(fieldName,object)
// usage 	: Check whether Radio already checked.
// input		:	fieldName	check field name
//					object			Radio object	
// output	: True or False
// Example : onChange="checkChoice("Type",this)"
// =====================================================================================
function checkChoice(fieldName,object){
	var	blnClick = 0;
	for (i = 0;i <=3; i++){
		if (object[i].checked){
			blnClick = 1;
			return true;
		}
	}
	if (blnClick == 0){
			alert("Please select one of " + fieldName );
			object[0].focus();
			return false;
	}	
}


function textCounter(field, maxlimit) {
	if (field.value.length > maxlimit) 
	field.value = field.value.substring(0, maxlimit);
}

function getNoOfCheckBoxes(thisForm)
{
	var count = 0;
	for(var i = 0; i < thisForm.elements.length; i++)
	{
		var elemType = thisForm.elements[i].type;

		if(elemType.toUpperCase() == 'CHECKBOX')
		{
			count++;
		}
	}

	return count;
}

//  Returns the number of check boxes checked on the screen (form)
function getCheckedBoxCount(strFormName)
{
    var intChkCounter = 0 ;
    var intNoOfElements = strFormName.elements.length ;

    for ( var intIndex = 0; intIndex < intNoOfElements; intIndex++ )
    {
        if(strFormName.elements[intIndex].type.toLowerCase() == "checkbox")
        {
            if (strFormName.elements[intIndex].checked)
                intChkCounter++ ;

        }
    }

    return(intChkCounter) ;

}

//  Returns the number of radio boxes checkedon the screen (form)
function getRadioBoxCount(strFormName)
{
    var intChkCounter = 0 ;
    var intNoOfElements = strFormName.elements.length ;

    for ( var intIndex = 0; intIndex < intNoOfElements; intIndex++ )
    {
        if(strFormName.elements[intIndex].type.toLowerCase() == "radio")
        {
            if (strFormName.elements[intIndex].checked)
                intChkCounter++ ;

        }
    }

    return(intChkCounter) ;

}

// Function to trim blank spaces on the
// either side of the input String

function trimString(strValue)
{
    strValue = strValue + "" ;
    for (var startIndex=0;strValue.length > startIndex && strValue.charAt(startIndex)==' ';startIndex++);
    for (var endIndex=strValue.length-1;endIndex > startIndex && strValue.charAt(endIndex)==' ';endIndex--);
    return strValue.substring(startIndex,endIndex+1);
}


// Function to check whether the given string is number.
//The second parameter checks for the decimal position.
function isNumber(strNumber, intDecimal, floatMaxValue)
{
    var strQtyMsg     = "";
    var strPriceMsg   = "";
    var strDecimalval = "";

    var intDecimalPos;

    strQtyMsg += "Please enter a valid Number";

    strPriceMsg =  "The number you have specified is not in the proper format.";
    //strPriceMsg += " Please enter a valid amount (Example 879.99 or 18699.00)";

    // Check if any non Alpha numeric characters like minus is given.
    strNumber = trimString(strNumber);

    //Check for AlphaNumeric Characters.
    if ((intDecimal == 0) && (!isAlphaNumericWithSpecialChar(strNumber, "")))
        return strQtyMsg;

    else if ((intDecimal != 0) && (!isAlphaNumericWithSpecialChar(strNumber, ".")))
        return strPriceMsg;

    // Check if Exponential char is given.
    if ((strNumber.indexOf("e") != -1) || (strNumber.indexOf("E") != -1))
        if (intDecimal == 0)
            return strQtyMsg;
        else
            return strPriceMsg;

    // Check if the given value is a number.
    if (isNaN(strNumber))
        if (intDecimal == 0)
            return strQtyMsg;
        else
            return strPriceMsg;

    // Check for the Decimal part if it is price field.
    if (intDecimal != 0)
    {
        intDecimalPos = strNumber.indexOf(".");

        if (intDecimalPos != -1)
        {
            strDecimalval = strNumber.substring(intDecimalPos + 1);

            if (strDecimalval.length > intDecimal)
                return strPriceMsg;
        }
    }

    var floatValue = parseFloat(strNumber);
    //Commented by Ankur Many fields may accept 0 also as
    // the input this special case needs to be handled individually
    // and can't be taken care generically

    //if (floatValue == 0)
    //    return "Please enter a valid value.";

    if ((intDecimal != 0) && (floatValue > floatMaxValue))
        return "Please enter a value less than or equal to " + floatMaxValue;

    return "";

}

// Function to check the given value contains any special characters
function isAlphaNumericWithSpecialChar(strValue, strExpChrs)
{
    var chrValue = '';
    for (var index = 0; index < strValue.length; ++index)
    {
        chrValue = strValue.charAt(index);
        if ((chrValue < 'a' || chrValue > 'z') &&
        (chrValue < 'A' || chrValue > 'Z') &&
        (chrValue < '0' || chrValue > '9'))
        {
			if ((strExpChrs.length > 0) && (strValue.charCodeAt(index) != "13") && (strValue.charCodeAt(index) != "160") && (strExpChrs.indexOf(chrValue) == -1)  )
            {

                return false;
			}
            if(strExpChrs.length == 0)
            {
                return false;
			}
        }
    }
    return true;
}


// Function to check the given value contains any special characters
function isAlphabets (strValue, strExpChrs)
{
    var chrValue = '';
    for (var index = 0; index < strValue.length; ++index)
    {
        chrValue = strValue.charAt(index);
        if ((chrValue < 'a' || chrValue > 'z') &&
        (chrValue < 'A' || chrValue > 'Z') )
        {
            if ((strExpChrs.length > 0) && (strValue.charCodeAt(index) != "13") && (strValue.charCodeAt(index) != "160") && (strExpChrs.indexOf(chrValue) == -1))
            {
                return false;
			}
            if(strExpChrs.length == 0)
            {
                return false;
			}
        }
    }
    return true;
}

// Function to check whether charValue is a special character

function isAlphaNumeric(chrValue)
{
    if ((chrValue < 'a' || chrValue > 'z') &&
        (chrValue < 'A' || chrValue > 'Z') &&
        (chrValue < '0' || chrValue > '9'))
    {

        return false;
    }
    else
        return true;

}

// =====================================================================================
// Function:	checkHour(fieldName,object)
// usage 	: Check value for time is HH:MM:SS.
//			HH < 24, MM < 59, SS < 59
// input	:	fieldName	check field name
//			object		check object	
// output	: True or False
// =====================================================================================
function checkHour(fieldName, object) {
	if (!isValidTime(object.value, "HH:MM:SS"))
	{
		alert("Please enter " + fieldName + " in HH24:MI:SS format");
		object.focus();
		return false;
	}
	return true;
}

/**************************>TIME VALIDATION<*****************************/

    //Function to test whether the input Date is in Specified Format
    function isValidTime(strTimeValue,timeFormat)
    {
        var intStart;
        var intEnd;
        var strSS;
        var strMI;
        var strHH;

        var strInputTime = trimString(strTimeValue);
        var strSelectedFormat = timeFormat;

        if (strSelectedFormat.length == strInputTime.length)
        {

            //Get the starting and ending index of "d" and get the date part entered by the user.

            intStart = strSelectedFormat.indexOf('S');
            intEnd = strSelectedFormat.lastIndexOf('S');
	
            strSS = strInputTime.substring(intStart,intEnd + 1);
            strSSFormat = strSelectedFormat.substring(intStart,intEnd + 1);

            //Get the starting and ending index of "m" and get the month part.
            intStart = strSelectedFormat.indexOf('M');
            intEnd = strSelectedFormat.lastIndexOf('M');

            strMI = strInputTime.substring(intStart,intEnd + 1);
            strMIFormat = strSelectedFormat.substring(intStart,intEnd + 1);

            //Get the starting and ending index of "m" and get the year part.
            intStart = strSelectedFormat.indexOf('H');
            intEnd = strSelectedFormat.lastIndexOf('H');

            strHH = strInputTime.substring(intStart,intEnd + 1);
            strHHFormat = strSelectedFormat.substring(intStart,intEnd + 1);

            //Condition to check for the delimiters
            if(checkDelimitersTime(strSelectedFormat, strSSFormat, strMIFormat, strHHFormat) !=
                checkDelimitersTime(strInputTime, strSS, strMI, strHH))
            {
                return false;
            }
            
	    if (isNaN(strMI))
            {
                alert("in NaN(strMI)");
                return false;
            }
            
            if (parseInt(strMI) > 59)
            {
                return false;
            }

            if (isNaN(strSS))
            {
                return false;
            }
            
            if(parseInt(strSS) > 59)
            {
            	return false;
            }

            if (isNaN(strHH))
            {
                return false;
            }
            
            if(parseInt(strHH) > 23)
            {
            	return false;
            }
	return true
	} else return false;
    }

    function checkDelimitersTime(strSelectedFormat, strSSFormat, strMIFormat, strHHFormat)
    {
       var strReplace;

       if(strHHFormat.length == 2) {
            strReplace = strSelectedFormat.replace(strHHFormat,"  ");
       } else { 
            strReplace = strSelectedFormat.replace(strHHFormat," ");
       }  

       if(strMIFormat.length == 2) {
            strReplace = strReplace.replace(strMIFormat,"  ");
       } else {
            strReplace = strReplace.replace(strMIFormat," ");
       }
       
       if(strDayFormat.length == 2) {
            strReplace = strReplace.replace(strSSFormat,"  ");
       } else {
            strReplace = strReplace.replace(strSSFormat," ");
       }

       return strReplace;
    }


/**************************>DATE VALIDATION<*****************************/
   
    function isValidExpireDateFormat(strValue)
    {
    	var reDate = /^(0[1-9]|1[0-2])\/(\d\d)$/;

    	if (reDate.test(strValue))
    	{
    		return true;
    	}
    	else
    	{
    		alert("Please enter valid Expire Day");
    		return false;
    	}
    	
    }

    //Function to test whether the input Date is in Specified Format
    function isValidDate(strDateValue,dateFormat)
    {
        var intStart;
        var intEnd;
        var strDay;
        var strMonth;
        var strYear;

        var strInputDate = trimString(strDateValue);
        var strSelectedFormat = dateFormat;

        if (strSelectedFormat.length == strInputDate.length)
        {

            //Get the starting and ending index of "d" and get the date part entered by the user.

            intStart = strSelectedFormat.indexOf('D');
            intEnd = strSelectedFormat.lastIndexOf('D');

            strDay = strInputDate.substring(intStart,intEnd + 1);
            strDayFormat = strSelectedFormat.substring(intStart,intEnd + 1);


            //Get the starting and ending index of "m" and get the month part.
            intStart = strSelectedFormat.indexOf('M');
            intEnd = strSelectedFormat.lastIndexOf('M');

            strMonth = strInputDate.substring(intStart,intEnd + 1);
            strMonthFormat = strSelectedFormat.substring(intStart,intEnd + 1);


            //Get the starting and ending index of "m" and get the year part.
            intStart = strSelectedFormat.indexOf('Y');
            intEnd = strSelectedFormat.lastIndexOf('Y');

            strYear = strInputDate.substring(intStart,intEnd + 1);
            strYearFormat = strSelectedFormat.substring(intStart,intEnd + 1);


            //Condition to check for the delimiters

            if(checkDelimiters(strSelectedFormat, strDayFormat, strMonthFormat, strYearFormat) !=
                checkDelimiters(strInputDate, strDay, strMonth, strYear))
            {
                //alert("Invalid Date entered.");
                return false;
            }

            if (strMonth.length > 2)
            {
                //alert("inside mon to num");
                strMonth = monthToNum(strMonth);
            }

            strMonth = trimString(strMonth);

            if(strMonth.length == 2)
            {
				if(!isNumber(strMonth,0,0) == "")
				{
					return false;
				}
            }


            if(strMonth.length < 2)
            {
            	return false;
            }

            if(strMonth > 12)
            {
            	return false;
            }
            // check if the date part entered is valid.
            if (isNaN(strDay))
            {
                //alert(">> Invalid Date entered.");
                return false;
            }
            strDay = trimString(strDay)

            if(!isNumber(strDay,0,0) == "")
            {
            	return false;
            }

	        if(strDay.length < 2)
            {
            	return false;
            }

            if (isNaN(strYear))
            {
                return false;
            }

            //change year here from 2 digits to four digits.. This logic has to be changed.

            if (strYear.length == 2)
            {
                if (strYear <= 60)
                    strYear = '20' + strYear;
                else
                    strYear = '19' + strYear;
            }
            else if(strYear.length == 4)
            {
                if( parseInt(strYear) ==0 )
				{
					return false;
                }
				if(!isNumber(trimString(strYear),0,0) == "")
				{
					return false;
				}
            }

            //alert("inside if no ->"+strDay+"/"+strMonth+"/"+strYear);
            // Validate the entered date ...
            if (!(checkdate(strDay,strMonth,strYear)))
            {
                //alert(">>>Invalid Date entered.");
                return false;
            }
            //alert("Validation Successful");
            return true;
        }
        else
        {
            //alert("****Invalid Date entered.");
            return false;
        }


    }


    //Function to display the Error Message

    function showMsg(strMsg)
    {
        alert(strMsg);
    }

    //Function to Validate Date

    function checkdate(strDay,strMon,strYr)

    {
        var dtDate = new Date(parseInt(strYr), parseInt(strMon-1), parseInt(strDay-0));

        //alert(dtDate);
        //alert(parseInt(strDay, 10)+"/"+parseInt(strMon,10)+"/");
        if (strYr < 2000)
            strYr += 1900 ;

        if (!((dtDate.getDate() == parseInt(strDay, 10)) && ((dtDate.getMonth()+ 1)== parseInt(strMon,10))))
        {
            return false;
        }
        return true;
    }

    // Function to convert month entered(Eg jan) into its corresponding integer value

    function monthToNum(strMon)
    {
         if (strMon=="")
            return false;

        if (strMon.length == 3)
            var strMon = strMon.toUpperCase();

        if (strMon== "JAN")
            return '1';
         else if (strMon == "FEB")
            return '2';
         else if (strMon == "MAR")
            return '3';
         else if (strMon == "APR")
            return '4';
         else if (strMon == "MAY")
            return '5';
         else if (strMon == "JUN")
            return '6';
         else if (strMon == "JUL")
            return '7';
         else if (strMon == "AUG")
            return '8';
         else if (strMon == "SEP")
            return '9';
         else if (strMon == "OCT")
            return '10';
         else if (strMon == "NOV")
            return '11';
         else if (strMon == "DEC")
            return '12';
         else
            return '0';
    }


    function checkDelimiters(strSelectedFormat, strDayFormat, strMonthFormat, strYearFormat)
    {
       var strReplace;

       if(strYearFormat.length == 2)
            strReplace = strSelectedFormat.replace(strYearFormat,"  ");
       else
            strReplace = strSelectedFormat.replace(strYearFormat,"    ");

       if(strMonthFormat.length == 2)
            strReplace = strReplace.replace(strMonthFormat,"  ");
       else
            strReplace = strReplace.replace(strMonthFormat,"   ");

       if(strDayFormat.length == 2)
            strReplace = strReplace.replace(strDayFormat,"  ");
       else
            strReplace = strReplace.replace(strDayFormat," ");

       return strReplace;
    }

/*********************>DATE VALIDATION END<********************************/


//This function is used to validate an email address
function isValidEmail(str)
{
    // are regular expressions supported?
    if(str.length > 0)
    {
        var strMessage = "The email address is not in a correct format Eg:aaaa@asd.com";
        var strFirst = str.substring(0,1);

        if (!isAlphaNumeric(strFirst))
        {
          alert("The first letter must be an alphaNumeric character(a-z,A-Z,0-9)");
            return false;
        }

        var supported = 0;
        if (window.RegExp)
        {
            var tempStr = "a";
            var tempReg = new RegExp(tempStr);
            if (tempReg.test(tempStr))
                supported = 1;
        }
        if (!supported)
            return (str.indexOf(".") > 2) && (str.indexOf("@") > 0);

        if (str.indexOf(' ') != -1)
        {
        	return false;
        }
        var r1 = new RegExp("(@.*@)|(\\.\\.)|(@\\.)|(^\\.)");
        var r2 = new RegExp("^.+\\@(\\[?)[a-zA-Z0-9\\-\\.]+\\.([a-zA-Z]{2,3}|[0-9]{1,3})(\\]?)$");
        if(!r1.test(str) && r2.test(str))
            return (!r1.test(str) && r2.test(str));
       alert(strMessage);
        return (!r1.test(str) && r2.test(str));
		
    }
    return true;
}

/********START DATE >= CURR DATE and ENDDATE >= STARTDATE****/
function isGreaterThanDate(StartDate,EndDate,dateFormat)
{
	var intStart;
	var intEnd;
	var strStartDay;
	var strEndDay;
	var strStartMonth;
	var strEndMonth;
	var strStartYear;
	var strEndYear;

	var strSelectedFormat = dateFormat;
	var strStartDate = trimString(StartDate);
	var strEndDate = trimString(EndDate);

	intStart = strSelectedFormat.indexOf('D');
	intEnd = strSelectedFormat.lastIndexOf('D');

	if(StartDate.length > 0)
    	strStartDay = strStartDate.substring(intStart,intEnd + 1);

	strEndDay = strEndDate.substring(intStart,intEnd + 1);


	//Get the starting and ending index of "m" and get the month part.
	intStart = strSelectedFormat.indexOf('M');
	intEnd = strSelectedFormat.lastIndexOf('M');

	if(StartDate.length > 0)
    	strStartMonth = strStartDate.substring(intStart,intEnd + 1);
	strEndMonth   = strEndDate.substring(intStart,intEnd + 1);

	//Get the starting and ending index of "m" and get the year part.
	intStart = strSelectedFormat.indexOf('Y');
	intEnd = strSelectedFormat.lastIndexOf('Y');

	if(StartDate.length > 0)
    	strStartYear = strStartDate.substring(intStart,intEnd + 1);

	if(EndDate.length > 0)
		strEndYear   = strEndDate.substring(intStart,intEnd + 1);


	//Added By Radhika on Oct 10th
	if(StartDate.length > 0)
	{
		if (strStartMonth.length > 2)
		{
			strStartMonth = monthToNum(strStartMonth);
		}
	}
	if(EndDate.length > 0)
	{
		if (strEndMonth.length > 2)
		{
			strEndMonth = monthToNum(strEndMonth);
		}
	}
	if(strStartYear == '08')
	{
		strStartYear = '8';
	}
	if(strStartYear == '09')
	{
		strStartYear = '9';
	}
	if(strEndYear == '08')
	{
		strEndYear = '8';
	}
	if(strEndYear == '09')
	{
		strEndYear = '9';
	}
	//End on Oct 10th

    var dt1;
    var dt2;
    if(StartDate.length > 0)
    {
        dt1 = new Date(parseInt(strStartYear), parseInt(strStartMonth-1), parseInt(strStartDay-0));
    }
    else    //if strStartDate length = 0 means take current date
    {
        //Getting Current Date
        dt1 = CURRENT_DATE;
        var curYear = dt1.getYear();

		if (curYear < 200) curYear += 1900 ;

		// Added at Offshore on 11-Oct-2001
		// If the year format is of YY format then
		if((intEnd-intStart) == 1)
		{
			curYear = '' + curYear;
			curYear = curYear.substring(2,curYear.length);
		}
		// End of Addition at Offshore on 11-Oct-2001

		var dayStart = dt1.getDate();
		var monStart = dt1.getMonth();
		var yrStart  = curYear;
    	dt1    = new Date(yrStart, monStart , dayStart);
    }

    dt2 = new Date(parseInt(strEndYear), parseInt(strEndMonth-1), parseInt(strEndDay-0));

    if(dt1.getTime() > dt2.getTime())
    {
        return false;
    }
    return true;
}
/********START DATE >= CURR DATE END****/

/*  To Clear the Controls in the Screen.
    strFormName : Form Control which has to be cleared.
*/
function clearHTMLScreen(strFormName)
{
    var strEleType = "" ;
    var intNoOfElements = strFormName.elements.length ;

    for ( var intIndex = 0; intIndex < intNoOfElements; intIndex++ )
    {
        strEleType = strFormName.elements[intIndex].type.toUpperCase();
        if (strEleType == "TEXT" || strEleType == "TEXTAREA" || strEleType == "READONLY" || strEleType == "PASSWORD" || strEleType == "FILE")
            strFormName.elements[intIndex].value = "";

        else if(strEleType == "CHECKBOX" || strEleType == "RADIO")
            strFormName.elements[intIndex].checked = false;

        else if(strEleType == "SELECT-ONE" || strEleType == "SELECT-MULTIPLE")
            strFormName.elements[intIndex].selectedIndex = " ";
    }

	for (var i = 0; i < strFormName.elements.length; i++)
	{
		strElementType = strFormName.elements[i].type.toUpperCase() ;

		if ( strElementType != 'CHECKBOX' && strElementType != 'RADIO'
			&& strElementType != 'HIDDEN')
		{
			if (!strFormName.elements[i].disabled)
			{
				strFormName.elements[i].focus() ;
				break ;
			}
		}
	} ;

    return ;
}


function roundOff(value, precision)
{
        value = "" + value //convert value to string
        precision = parseInt(precision);

        var whole = "" + Math.round(value * Math.pow(10, precision));

        var decPoint = whole.length - precision;

        if(decPoint != 0)
        {
                result = whole.substring(0, decPoint);
                result += ".";
                result += whole.substring(decPoint, whole.length);
        }
        else
        {
                result = whole;
        }
        return result;
}

/**
 *This method takes the form and validates if a single quote exists in any
 *of the text field elements
 *@param var strValue
*/

function isValidInput(thisForm)
{
	for(var i = 0; i < thisForm.elements.length; i++)
	{
		var elemType = thisForm.elements[i].type;
		var strValue = "";
		//	Changed by Anil cause textarea doesn';t have a value property and hence text areas can handle
		//	both single as well as double quotes
		if(elemType.toUpperCase() == 'TEXT' )
		{
			strValue = thisForm.elements[i].value;
			if (!isNotChinese(strValue))
			{
				alert("This text field contains invalid characters");
				thisForm.elements[i].focus();
				return false;
			}
		} else  if(elemType.toUpperCase() == 'TEXTAREA' )
		{
			strValue = thisForm.elements[i].value;
			if (!isNotChineseTextArea(strValue))
			{
				alert("This text field contains invalid characters");
				thisForm.elements[i].focus();
				return false;
			}
		}
	}
	return true;
}

/**
 *This method takes the string and validates if it has a single quote
 *@param var strValue
*/
function isValidString(strValue)
{
	if(strValue != "" && strValue.indexOf("'") != -1)
	{
			return false;
	}
	return true;
}

function isGreaterThanEqualDate(StartDate,EndDate)
{
	var intStart;
	var intEnd;
	var strStartDay;
	var strEndDay;
	var strStartMonth;
	var strEndMonth;
	var strStartYear;
	var strEndYear;

	var strSelectedFormat = DATE_FORMAT;
	var strStartDate = trimString(StartDate);
	var strEndDate = trimString(EndDate);

	intStart = strSelectedFormat.indexOf('D');
	intEnd = strSelectedFormat.lastIndexOf('D');

	if(StartDate.length > 0)
    	strStartDay = strStartDate.substring(intStart,intEnd + 1);

	strEndDay = strEndDate.substring(intStart,intEnd + 1);


	//Get the starting and ending index of "m" and get the month part.
	intStart = strSelectedFormat.indexOf('M');
	intEnd = strSelectedFormat.lastIndexOf('M');

	if(StartDate.length > 0)
    	strStartMonth = strStartDate.substring(intStart,intEnd + 1);
	strEndMonth   = strEndDate.substring(intStart,intEnd + 1);

	//Get the starting and ending index of "m" and get the year part.
	intStart = strSelectedFormat.indexOf('Y');
	intEnd = strSelectedFormat.lastIndexOf('Y');

	if(StartDate.length > 0)
    	strStartYear = strStartDate.substring(intStart,intEnd + 1);

	if(EndDate.length > 0)
		strEndYear   = strEndDate.substring(intStart,intEnd + 1);


	//Added By Radhika on Oct 10th
	if(StartDate.length > 0)
	{
		if (strStartMonth.length > 2)
		{
			strStartMonth = monthToNum(strStartMonth);
		}
	}
	if(EndDate.length > 0)
	{
		if (strEndMonth.length > 2)
		{
			strEndMonth = monthToNum(strEndMonth);
		}
	}
	if(strStartYear == '08')
	{
		strStartYear = '8';
	}
	if(strStartYear == '09')
	{
		strStartYear = '9';
	}
	if(strEndYear == '08')
	{
		strEndYear = '8';
	}
	if(strEndYear == '09')
	{
		strEndYear = '9';
	}
	//End on Oct 10th

    var dt1;
    var dt2;
    if(StartDate.length > 0)
    {
        dt1 = new Date(parseInt(strStartYear), parseInt(strStartMonth-1), parseInt(strStartDay-0));
    }
    else    //if strStartDate length = 0 means take current date
    {
        //Getting Current Date
        dt1 = CURRENT_DATE;
        var curYear = dt1.getYear();

		if (curYear < 200) curYear += 1900 ;

		// Added at Offshore on 11-Oct-2001
		// If the year format is of YY format then
		if((intEnd-intStart) == 1)
		{
			curYear = '' + curYear;
			curYear = curYear.substring(2,curYear.length);
		}
		// End of Addition at Offshore on 11-Oct-2001

		var dayStart = dt1.getDate();
		var monStart = dt1.getMonth();
		var yrStart  = curYear;
    	dt1    = new Date(yrStart, monStart , dayStart);
    }

    dt2 = new Date(parseInt(strEndYear), parseInt(strEndMonth-1), parseInt(strEndDay-0));

    if(dt1.getTime() >= dt2.getTime())

    {
        return false;
    }
    return true;
}
/********START DATE >= CURR DATE END****/

function deCheckOthers(chkname,frmName)
{
	var val = chkname.checked ;
	var intNoOfElements = frmName.elements.length ;

	for(i = 0; i < intNoOfElements; i++)
	{
		eleType = frmName.elements[i].type ;
		if(eleType.toLowerCase()=="checkbox")
		{
			frmName.elements[i].checked=false;
		}
	}

	chkname.checked=val;

	return true;
}

function resetScreen(frmName)
{
	frmName.reset();
}


var strValidPasswordChar			= "'~!@#$%^&*():,.|{}<>/[]+-=_?\;";

/**
*   This function is used to validate password
*   @param  strPassword 	String
*   @return String
*/

function validatePassword(password)
{
	Capchar = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	smallchar = "abcdefghijklmnopqrstuvwxyz";
	arabumber = "0123456789";
	strangchar = "<>,.?':;]}[{=+-_`~!@#$%^&*()|";

	booleanCapchar = false;
	booleansmallchar = false;
	booleanarabumber = false;
	booleanstrangchar = false;

	if(trimString(password) == "")
	{
		return 'Please Enter the Password';
	}

	groupcounter = 0;
	if (password.length<8)
	{
		alert("Password should not be less than eight characters");
		return false;
	}
   
	for (i=0;i<password.length;i++)
	{
		if (Capchar.indexOf(password.charAt(i))!=-1)
		booleanCapchar = true;
		if (smallchar.indexOf(password.charAt(i))!=-1)
		booleansmallchar = true;
		if (arabumber.indexOf(password.charAt(i))!=-1)
		booleanarabumber = true;
		if (strangchar.indexOf(password.charAt(i))!=-1)
		booleanstrangchar = true;
	}
   
	if (booleanCapchar)
	groupcounter++;
	if (booleansmallchar)
	groupcounter++;
	if (booleanarabumber)
	groupcounter++;
	if (booleanstrangchar)
	groupcounter++;
   
   	if(groupcounter<3)
	{
		alert("Please enter a valid password");
		return false;
	}
 return true;
}

function verifyIP (IPvalue) {
	errorString = "";
	theName = "IPaddress";

	var ipPattern = /^(\d{1,3})\.(\d{1,3})\.(\d{1,3})\.(\d{1,3})$/;
	var ipArray = IPvalue.match(ipPattern);

	if (IPvalue == "0.0.0.0")
	errorString = errorString + theName + ': '+IPvalue+' is a special IP address and cannot be used here.';
	else if (IPvalue == "255.255.255.255")
	errorString = errorString + theName + ': '+IPvalue+' is a special IP address and cannot be used here.';
	if (ipArray == null)
	errorString = errorString + theName + ': '+IPvalue+' is not a valid IP address.';
	else {
	for (i = 0; i < 4; i++) {
	thisSegment = ipArray[i];
	if (thisSegment > 255) {
	errorString = errorString + theName + ': '+IPvalue+' is not a valid IP address.';
	i = 4;
	}
	if ((i == 0) && (thisSegment > 255)) {
	errorString = errorString + theName + ': '+IPvalue+' is a special IP address and cannot be used here.';
	i = 4;
		  }
	   }
	}
	extensionLength = 3;
	if (errorString == "")
	return true;
	else
	return false;
}

function checkOnlyHexa(string) {
	var valid = "0123456789ABCDEFabcdef";
	var temp;
	var err = 0;
	for (var i=0; i< string.length; i++) {
		temp = "" + string.substring(i, i+1);
		if (valid.indexOf(temp) == "-1")	err = 1;
	}
	if (err == 1){
		return false;
	}
	else return true;
}

//Added by satyam checking the file extensions
//check if the type is supported.
function FileExtensionCheck(fileName) 
{ 
//var fileName = document.formName.FILE1.value;
var stringArray = fileName.split ('.');
var len = stringArray.length-1;
//var fileExtension = stringArray[len].toUpperCase(); 
var fileExtension = stringArray[len] 
var filePrefix = stringArray[0];
  
switch(fileExtension) {

	case 'jpeg': return true;
	case 'JPEG': return true;
	case 'GIF' : return true;
	case 'gif' : return true;
	default   : alert ('The type of your file is not supported.\n'+
 'Only .jpeg, .gif files are possible.');
 return false;
      }
}
//for Excell file check
function FileCheck(fileName) 
{ 
//var fileName = document.formName.FILE1.value;
var stringArray = fileName.split ('.');
var len = stringArray.length-1;
//var fileExtension = stringArray[len].toUpperCase(); 
var fileExtension = stringArray[len] 
var filePrefix = stringArray[0];
  
switch(fileExtension) {
	case 'xls': return true;
	case 'XLS': return true;
	default   : alert ('The type of your file is not supported.\n'+
 'Only .xls files are possible.');
 return false;
      }

}

-->