function disableEnterKey(e)
{
	var key;      
	if(window.event)
		key = window.event.keyCode; //IE
	else
		key = e.which; //firefox      

	return (key != 13);
}

function isValidDateFormat(strValue)
{
	//alert('test2');
	var reDate = /^(0[1-9]|[12][0-9]|3[01])\/(0[1-9]|1[0-2])\/((19|20)\d\d)$/;

	if (reDate.test(strValue))
	{
		return true;
	}
	else
	{
		return false;
	}

}

function validateFromToDate(frmDate,toDate)
{
	xMonth = parseFloat(frmDate.split("/")[1])-1;
	var x = new Date();
	x.setFullYear(frmDate.split("/")[2],xMonth,frmDate.split("/")[0]);

	yMonth = parseFloat(toDate.split("/")[1])-1;
	var y = new Date();
	y.setFullYear(toDate.split("/")[2],yMonth,toDate.split("/")[0]);

	//var stDate = new Date(frmDate);
	//var enDate = new Date(toDate);
	//var compDate = enDate - stDate;

	if(x >= y)
	{
		return false;
	}

	return true;
}

function isBlank(strCheck){
	var	len = strCheck.length;
	var i ;
	for (i=0;i<len ;++i )
	{
		if (strCheck.charAt(i) != " ")
		{ 	
			return false;
		}
	}	
	return true;
}

function onflyDateValidate(obj, strColum)
{
	if(obj.value != ""){
		if(!isValidDateFormat(obj.value))
		{
			alert("Please enter " + strColum + " in correct format");
			obj.focus();
			return false;
		}
	}
}

function isValidDate(dateObj) {

	//Checks for the following valid date formats:
	//MM/DD/YY   MM/DD/YYYY   MM-DD-YY   MM-DD-YYYY
	//Also separates date into month, day, and year variables
	//var datePat = /^(\d{1,2})(\/|-)(\d{1,2})\2(\d{2}|\d{4})$/;
	//To require a 4 digit year entry, use this line instead:
	//alert('test');

	var dateStr = dateObj.value;
	//alert(dateStr);

	if(dateStr != ""){

		var datePat = /^(\d{1,2})(\/)(\d{1,2})\2(\d{4})$/;
		var matchArray = dateStr.match(datePat);

		if (matchArray == null) {
			alert("Date is not in a valid format.");
			dateObj.focus();
			return false;
		}

		month = matchArray[3]; // parse date into variables
		day = matchArray[1];
		year = matchArray[4];

		if (month < 1 || month > 12) { // check month range
			alert("Month must be between 1 and 12.");
			dateObj.focus();
			return false;
		}

		if (day < 1 || day > 31) {
			alert("Day must be between 1 and 31.");
			dateObj.focus();
			return false;
		}

		if ((month==4 || month==6 || month==9 || month==11) && day==31) {
			alert("Month "+month+" doesn't have 31 days!");
			dateObj.focus();
			return false;
		}

		if (month == 2) { // check for february 29th
			var isleap = (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0));
			if (day>29 || (day==29 && !isleap)) {
				alert("February " + year + " doesn't have " + day + " days!");
				dateObj.focus();
				return false;
			}
		}

		return true;  // date is valid

	}

	return true;

}

function decimalValidation(strValue)
{
	var reg = new RegExp('^\\d{1,}(\\.\\d{1,})?$');

	if (reg.test(strValue))
	{
		return true;
	}
	else
	{
		return false;
	}

}

function correctDecimalPlace(strValue, intpart, decpart)
{
	var reg = new RegExp('^\\d{1,'+intpart+'}(\\.\\d{1,'+decpart+'})?$');

	if (reg.test(strValue))
	{
		return true;
	}
	else
	{
		return false;
	}

}

function decimalAlert(intpart, decpart){
	/*

	var result = "(#";

	for(var i = 0; i < intpart; i++){
		result = result.concat("0");
	}

	result = result.concat(".");

	for(var j = 0; j < decpart; j++){
		result = result.concat("0");
	}

	result = result.concat(")");

	return result;

	 */
	var result = "("+intpart+","+decpart+")";

	return result;

}

function validateFromToDate(frmDate,toDate)
{
	xMonth = parseFloat(frmDate.split("/")[1])-1;
	var x = new Date();
	x.setFullYear(frmDate.split("/")[2],xMonth,frmDate.split("/")[0]);

	yMonth = parseFloat(toDate.split("/")[1])-1;
	var y = new Date();
	y.setFullYear(toDate.split("/")[2],yMonth,toDate.split("/")[0]);

	//var stDate = new Date(frmDate);
	//var enDate = new Date(toDate);
	//var compDate = enDate - stDate;

	if(x > y)
	{
		return false;
	}

	return true;
}

function validInteger(obj)
{
	//alert(obj.value);
	var reg = new RegExp('^\\d+$');

	if (!reg.test(obj.value))
	{
		alert(obj.value + ' is NOT valid integer value.');
		obj.focus();
		return false;
	}

	return true;

}


function emailCheck(emailObj) {
	//alert('tesss');

	var str = emailObj.value;


	if(str != ""){

		var at="@";
		var dot=".";
		var lat=str.indexOf(at);
		var lstr=str.length;
		var ldot=str.indexOf(dot);
		if (str.indexOf(at)==-1){
			alert("Invalid E-mail");
			return false;
		}

		if (str.indexOf(at)==-1 || str.indexOf(at)==0 || str.indexOf(at)==lstr){
			alert("Invalid E-mail");
			return false;
		}

		if (str.indexOf(dot)==-1 || str.indexOf(dot)==0 || str.indexOf(dot)==lstr){
			alert("Invalid E-mail");
			return false;
		}

		if (str.indexOf(at,(lat+1))!=-1){
			alert("Invalid E-mail");
			return false;
		}

		if (str.substring(lat-1,lat)==dot || str.substring(lat+1,lat+2)==dot){
			alert("Invalid E-mail");
			return false;
		}

		if (str.indexOf(dot,(lat+2))==-1){
			alert("Invalid E-mail");
			return false;
		}

		if (str.indexOf(" ")!=-1){
			alert("Invalid E-mail");
			return false;
		}

	}

	return true;
}


function checkEmail(emailObj) {

	var email = emailObj;
	var filter = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;


	if(email.value != ""){

		if (!filter.test(email.value)) {
			alert('Please provide a valid email address');
			email.focus;
			return false;

		}
	}

	return true;

}