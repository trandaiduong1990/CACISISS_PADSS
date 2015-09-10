function onFormSub(frmName){

	if(!onSubmitValidation(frmName.rate, 7, 3)){ return false; }
	
	return true;
	
}