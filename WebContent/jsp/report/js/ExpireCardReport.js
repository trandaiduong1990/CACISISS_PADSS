function setmode(hdOpCode,mode,report_code, report_format) 
{

  document.getElementById("reportErrorMessage").innerHtml = '';
	
  document.frmMain.hdOpCode.value = hdOpCode;
  document.frmMain.mode.value = mode;
  document.frmMain.REPORT_CODE.value = report_code;
  document.frmMain.REPORT_FORMAT.value = report_format;
  if(checkForm(document.frmMain,report_code)){
		frmMain.submit();	
	}

}

function checkForm(frmName,reportcode){
	//var dateFormat = "DD/MM/YY";
	if(!isValidExpireDateFormat(frmName.EXP_DATE.value)){
		//alert("Please enter valid date : "+dateFormat);
		frmName.EXP_DATE.focus();
		frmName.EXP_DATE.select();
		return false;	
	}
	return true;
}


