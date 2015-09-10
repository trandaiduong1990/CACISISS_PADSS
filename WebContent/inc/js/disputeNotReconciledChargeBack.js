function go(action) {
	document.forms[0].method.value=action;
	document.forms[0].action= "disNotReconsiledChargeBackProcess.do";
	
	var r=confirm('Are sure to raise Charge Back?');
    if (r==true){
    	document.forms[0].submit();
    }
}
     
function showList(){
	document.forms[0].method.value="view";
	document.forms[0].action ="disNotReconsiledTranxProcess.do";
	document.forms[0].submit();
}

function groupReasonUpdate(group){

	DWRUtils.changeDisputeGroup(group.value, function(data) {
	    dwr.util.removeAllOptions("disReason");
	    dwr.util.addOptions("disReason", [""]);
	  	dwr.util.addOptions("disReason", data,"reasonCode","reason");
	  });
	
}

function onLoadUpdate(){
	var groupId = document.getElementById('disGroup');
	groupReasonUpdate(groupId);
}


function countCharacters(id,myelement){
	var fieldLength = 50;
	
	field = document.getElementById(myelement);
	fieldfield_length = field.value.length;
	
	counter = document.getElementById(id);
	
	if (parseInt(fieldfield_length) <= fieldLength){
		remaining_characters = fieldLength-fieldfield_length; 
		counter.innerHTML = '(Left. '+remaining_characters+')';;  
	}else{
		field.value = field.value.substring(0, fieldLength);
	}
 }