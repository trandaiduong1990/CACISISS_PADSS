function isValidDecimal(obj, intpart, decpart){

	if(obj.value != ''){
		
		if(!decimalValidation(obj.value)){
			alert(obj.value + ' is NOT valid decimal value.');
			obj.focus();
		}else{
			if(!correctDecimalPlace(obj.value, intpart, decpart)){
				alert(obj.value + ' is NOT accepted value to this filed. ' + decimalAlert(intpart, decpart));
				obj.focus();
			}
		}
		
	}
}

function onSubmitValidation(obj, intpart, decpart){

	if(obj.value != ''){
		
		if(!decimalValidation(obj.value)){
			alert(obj.value + ' is NOT valid decimal value.');
			obj.focus();
			return false;
		}else{
			if(!correctDecimalPlace(obj.value, intpart, decpart)){
				alert(obj.value + ' is NOT accepted value to this filed. ' + decimalAlert(intpart, decpart));
				obj.focus();
				return false;
			}
		}
		
		return true;
		
	}
	
	return true;
}