function dateValidationOnFormSubmit(startDate, endDate){
	//alert(startDate);
	//alert(endDate);
	if (!isBlank(startDate.value)
			&& !isValidDateFormat(startDate.value)) {
		alert("Please enter start date in correct format");
		return false;
	}

	if (!isBlank(endDate.value)
			&& !isValidDateFormat(endDate.value)) {
		alert("Please enter end date in correct format");
		return false;
	}
	
	if(!validateFromToDate(startDate.value,endDate.value) ) 
	{
		alert("End Date must be greater than Start Date");
		return false;
	}
	
	return true;
	
}