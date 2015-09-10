function submitForm(frmName) {
	frmName.submit();
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

function dateValidationOnlist(fromName){
	
	if(!isValidDate(fromName.startDate)){ return false; }
	if(!isValidDate(fromName.endDate)){ return false; }
	
	return true;
}