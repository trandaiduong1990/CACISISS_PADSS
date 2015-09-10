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

function searchlist(){
	
	if(isFilledAtleastOneTextBox()){

		var objStartDate = document.getElementsByName("startDate")[0];
		var startDate = objStartDate.value;

		var objEndDate = document.getElementsByName("endDate")[0];
		var endDate = objEndDate.value;


		var isFormSubmit = false;

		if(startDate != '' && endDate != ''){
			if(!validateFromToDate(startDate,endDate)){
				alert("End Date must be greater than Start Date");
				objEndDate.focus();
				isFormSubmit = false;
			}else{
				isFormSubmit = true;
			}
		}else{
			if(startDate == '' && endDate == ''){
				isFormSubmit = true;
			}else{
				if(startDate != '' && endDate == ''){
					var r=confirm('End Date also will be SAME as Start Date. \nAre you sure?');
				    if (r==true){
				    	isFormSubmit = true;
				    }else{
				    	objEndDate.focus();
				    }
				}else{
					var r=confirm('Start Date also will be SAME as End Date. \nAre you sure?');
				    if (r==true){
				    	isFormSubmit = true;
				    }else{
				    	objStartDate.focus();
				    }
				}
			}
		}

		if(isFormSubmit){
			if(dateValidationOnlist(document.forms[0])){
				document.forms[0].submit();
			}
		}
	}else{
		alert('Please give atleast ONE search criteria.');
	}
}


function isFilledAtleastOneTextBox(){
	
	var boxes = document.getElementsByTagName("INPUT");
	
	var isFilledAtleastOneFiled = false;

	for(var i = 0; i < boxes.length; i++){
		var input = boxes[i];

		if(input.type != 'text'){ 
			continue;
		}else{
			if(input.value != ''){
				isFilledAtleastOneFiled = true;
				break;
			}
		}
	}

	if(!isFilledAtleastOneFiled){
		
		var selects = document.getElementsByTagName("SELECT");

		for(var i = 0; i < selects.length; i++){
			var objSelect = selects[i];

			if(objSelect.value != ''){
				isFilledAtleastOneFiled = true;
				break;
			}
		}
		
	}

	return isFilledAtleastOneFiled;
}