function cardLimitAccept(frmName){
	
	if(frmName.orgBackDate.value != ''){
		if(checkCBSForm(frmName)){
			alert('This Credit change will be revert back to original limit after ' + frmName.orgBackDate.value);
			return true;
		}else{
			return false;
		}
	}else{
		alert('This Credit change will be taken as Permanent change');
		return true;
	}
		
	return true;
	
}

function checkCBSForm(frmName){
	var dateFormat = "DD/MM/YYYY";
	if(!isValidDate(frmName.orgBackDate,dateFormat)){
		alert("Please enter valid date : "+dateFormat);
		frmName.orgBackDate.focus();
		frmName.orgBackDate.select();
		return false;	
	}
	
	return true;
}