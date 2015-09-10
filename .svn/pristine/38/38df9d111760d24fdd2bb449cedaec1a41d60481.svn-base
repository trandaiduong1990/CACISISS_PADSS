function dateValidationOnApplicationFormSubmit(fromName){
	
	if(!isValidDate(fromName.strDob)){ return false; }
	if(!isValidDate(fromName.strIdDate)){ return false; }
	if(!isValidDate(fromName.strExpDate)){ return false; }

	if(!isValidDate(fromName.strSupplDob)){ return false; }
	if(!isValidDate(fromName.strSupplIdDate)){ return false; }
	if(!isValidDate(fromName.strSupplIdExpire)){ return false; }
	
	return true;
}

function onFormSub(frmName){
	
	if(!onSubmitValidation(frmName.income, 8, 2)){ return false; }
	if(!onSubmitValidation(frmName.supplIncome, 8, 2)){ return false; }
	
	return true;
	
}

function stateUpdate(country, addressType){
	
	var modifyaddressType = "'"+addressType+"'";

	if(country == "MM"){
		
		removeInnerHtml(addressType+'_stateText');
		
		addSelectElement(addressType+'_stateText', addressType+'Address.state', 'updateCity(this, '+modifyaddressType+')');
				
		DWRUtils.getStates(function(data) {
		    dwr.util.addOptions(addressType+"Address.state", [""]);
		  	dwr.util.addOptions(addressType+"Address.state", data,"stateId","stateDes");
		  });
		
	}else{
		
		removeInnerHtml(addressType+'_stateText');
		removeInnerHtml(addressType+'_cityText');
		removeInnerHtml(addressType+'_townshipText');
		
		addTextElement(addressType+'_stateText', addressType+'Address.state');
		addTextElement(addressType+'_cityText', addressType+'Address.city');
		addTextElement(addressType+'_townshipText', addressType+'Address.township');
		
	}
	
}

function addTextElement(divId, eleId)
{
    var frm = document.getElementById(divId);
    var newEl = document.createElement("input");
    newEl.type = "text";
    newEl.name = eleId;
    frm.appendChild(newEl);        
}

function addSelectElement(divId, eleId, onChangeFun){
	
	var frm = document.getElementById(divId);
    var select_element = document.createElement("select");
    select_element.setAttribute("onchange", onChangeFun);
    select_element.setAttribute("id", eleId);
    select_element.setAttribute("name", eleId);
    frm.appendChild(select_element); 
	
}

function removeInnerHtml(divId){
	document.getElementById(divId).innerHTML = '';
}

function updateCity(state, addressType){
	
	var modifyaddressType = "'"+addressType+"'";

	removeInnerHtml(addressType+'_cityText');
	addSelectElement(addressType+'_cityText', addressType+'Address.city', 'updateTownship(this,'+modifyaddressType+')');
	
	dwr.util.removeAllOptions(addressType+"Address.city");
	
	DWRUtils.getCities(state.value, function(data) {
	    dwr.util.addOptions(addressType+"Address.city", [""]);
	  	dwr.util.addOptions(addressType+"Address.city", data,"cityId","cityDes");
	  });
	
}

function updateTownship(city, addressType){

	removeInnerHtml(addressType+'_townshipText');
	addSelectElement(addressType+'_townshipText', addressType+'Address.township', '');
	
	dwr.util.removeAllOptions(addressType+"Address.township");
	
	DWRUtils.getTownShips(city.value, function(data) {
	    dwr.util.addOptions(addressType+"Address.township", [""]);
	  	dwr.util.addOptions(addressType+"Address.township", data,"townshipId","townshipDes");
	  });
}