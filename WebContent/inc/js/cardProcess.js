function dateValidationOnlist(fromName){
	
	if(!isValidDate(fromName.startDate)){ return false; }
	if(!isValidDate(fromName.endDate)){ return false; }
	
	return true;
}