function dateValidationOnlist(fromName){
	
	if(!isValidDate(fromName.transDateFrom)){ return false; }
	if(!isValidDate(fromName.transDateTo)){ return false; }
	
	return true;
}