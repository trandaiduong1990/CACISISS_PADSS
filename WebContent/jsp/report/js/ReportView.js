function setmode(hdOpCode,mode,report_code, report_format) 
{

//alert("report_code : " +report_code );
  document.getElementById("reportErrorMessage").innerHtml = '';
	
  document.frmMain.hdOpCode.value = hdOpCode;
  document.frmMain.mode.value = mode;
  document.frmMain.REPORT_CODE.value = report_code;
  document.frmMain.REPORT_FORMAT.value = report_format;
 //alert("b4 if");
  if(report_code == "CBS"){
	  //alert("CBS");
    if(checkCBSForm(document.frmMain)){
    	//alert("CBS if submit");
		frmMain.submit();	
	}
  } 
  if(report_code == "CARDSWRITEOFF"){	
  frmMain.submit();
  }	
else {
  	if(checkForm(document.frmMain,report_code)){
		frmMain.submit();
  	}
  }
}

//MM/YYYY validation
function checkCardsWriteOffForm(frmName){
	//alert("Check Duration Format");
	var dateFormat = "MM/YYYY";
	if(!isValidDate(frmName.TRANS_DATE_TO.value,dateFormat)){
		alert("Please enter valid duration format : "+dateFormat);
		frmName.TRANS_DATE_TO.focus();
		frmName.TRANS_DATE_TO.select();
		return false;
	}
	//alert("Correct Duration  Format");
	return true;
}

// MM/YY < current date validation
function checkCurrentDate(frmName){
	//alert("checkCurrentDate");
	var e = document.getElementById("TRANS_DATE_TO");
	var strDuration = e.options[e.selectedIndex].value;
	alert("Selected Value" + strDuration );
	var d = new Date();
    var currentMonth = d.getMonth()+ 1;
    //var currentDay = d.getDate(); 
    var currentYear = d.getFullYear();
   // alert("current"+currentMonth+"-"+currentYear);
    
    //var duration=frmName.TRANS_DATE_TO.value.split("/");
    var duration=strDuration.split("/");
    var durationYear=duration[1];
    var durationMonth=duration[0];
    //alert("duration"+durationYear+"-"+durationMonth);
    
    if((currentYear < durationYear)){
    	alert("Sorry Report available only until current Month : ");
    	frmName.TRANS_DATE_TO.focus();
		frmName.TRANS_DATE_TO.select();
    	return false;
    }
    
    if(currentYear > durationYear){
    	//alert("COrrect ");
    	
    	return true;
    	
    }
    if(currentYear == durationYear){
    	if(currentMonth >= durationMonth){
    		//alert("COrrect ");
    		return true;
    		
    	}
    	else{
    	alert("Sorry Report available only until current Month : ");
    	frmName.TRANS_DATE_TO.focus();
		frmName.TRANS_DATE_TO.select();
    	return false;
    	}
    }
    
    
}

function checkCBSForm(frmName){
	var dateFormat = "DD/MM/YYYY";
	if(!isValidDate(frmName.LAST_BILLING_DATE.value,dateFormat)){
		alert("Please enter valid date : "+dateFormat);
		frmName.LAST_BILLING_DATE.focus();
		frmName.LAST_BILLING_DATE.select();
		return false;	
	}
	if(!isValidDate(frmName.CURRENT_DATE.value,dateFormat)){
		alert("Please enter valid date : "+dateFormat);
		frmName.CURRENT_DATE.focus();
		frmName.CURRENT_DATE.select();
		return false;	
	}
	if(!isGreaterThanDate(frmName.LAST_BILLING_DATE.value,frmName.CURRENT_DATE.value,dateFormat)){
		alert("To Date must be greater than From Date");
		frmName.CURRENT_DATE.focus();
		frmName.CURRENT_DATE.select();
		return false;
	}
	
	return timeDiff(frmName.LAST_BILLING_DATE.value, frmName.CURRENT_DATE.value);
	
	return true;
}

function checkForm(frmName,reportcode){
	//alert('2');
	var dateFormat = "DD/MM/YYYY";
	if(!isValidDate(frmName.TRANS_DATE_FROM.value,dateFormat)){
		alert("Please enter valid date : "+dateFormat);
		frmName.TRANS_DATE_FROM.focus();
		frmName.TRANS_DATE_FROM.select();
		return false;	
	}
	//alert('3');
	if(!isValidDate(frmName.TRANS_DATE_TO.value,dateFormat)){
		alert("Please enter valid date : "+dateFormat);
		frmName.TRANS_DATE_TO.focus();
		frmName.TRANS_DATE_TO.select();
		return false;	
	}
	//alert('4');
	if(!isGreaterThanDate(frmName.TRANS_DATE_FROM.value,frmName.TRANS_DATE_TO.value,dateFormat)){
		alert("To Date must be greater than From Date");
		frmName.TRANS_DATE_TO.focus();
		frmName.TRANS_DATE_TO.select();
		return false;
	}
	//alert('5');
	if(reportcode == "TR" || reportcode == "BLR"){
		if(!checkHour("Tranx Time From",frmName.TRANS_TIME_FROM)) return false;
		if(!checkHour("Tranx Time To",frmName.TRANS_TIME_TO)) return false;
	}
	//alert('6');
	//return timeDiff(frmName.TRANS_DATE_FROM.value, frmName.TRANS_DATE_TO.value);

	//alert('6');
	return true;
}


