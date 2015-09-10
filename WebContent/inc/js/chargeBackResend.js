function go(action) {
    document.forms[0].method.value=action;
    document.forms[0].action= "chargeBackResendProcess.do";

	var r=confirm('Are sure to Continue this Operation?');
    if (r==true){
    	if(action == 'chargeBackResend'){
    		enableSelectBoxes();
    	}
    	document.forms[0].submit();
    }
}
     
function showList(){
	document.forms[0].action= "chargeBackResendList.do";
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
     
function showView(){
	document.forms[0].method.value="viewChargeBack";
	document.forms[0].action= "chargeBackResendProcess.do";
	document.forms[0].submit();
}

function makeReadOnly(){
	var boxes = document.getElementsByTagName("INPUT");
	for(var i = 0; i < boxes.length; i++){
		if(boxes[i].type == 'checkbox' || boxes[i].type == 'radio'){
			boxes[i].readOnly=false;
		}else{
			boxes[i].readOnly=true;
		}
	}
	
	var memText = document.getElementById("memMsgText");
	memText.readOnly=true;
	
	var selects = document.getElementsByTagName("SELECT");
	for(var i = 0; i < selects.length; i++){
		selects[i].disabled = true;
	}
}

function enableSelectBoxes(){
	var selects = document.getElementsByTagName("SELECT");
	for(var i = 0; i < selects.length; i++){
		selects[i].disabled = false;
	}
}

function enableDisableGroup(exby){
	var group = document.getElementById("disGroup");
	var groupReason = document.getElementById("disReason");
	if (exby.checked) {
		group.disabled = false;
		groupReason.disabled = false;
	}else{
		group.disabled = true;
		groupReason.disabled = true;
	}
}

function enableDisableGroupReason(exby){
	var groupReason = document.getElementById("disReason");
	if (exby.checked) {
		groupReason.disabled = false;
	}else{
		groupReason.disabled = true;
	}
}

function enableDisableAmt(exby){
	var amt = document.getElementById("disAmt");
	if (exby.checked) {
		amt.readOnly=false;
	}else{
		amt.readOnly=true;
	}
}

function enableDisableCurrency(exby){
	var curr = document.getElementById("disCurr");
	if (exby.checked) {
		curr.disabled = false;
	}else{
		curr.disabled = true;
	}
}

function enableDisableMemText(exby){
	var memText = document.getElementById("memMsgText");
	if (exby.checked) {
		memText.readOnly=false;
	}else{
		memText.readOnly=true;
	}
}

function enableDisableArn(exby){
	var disArn = document.getElementById("disArn");
	if (exby.checked) {
		disArn.readOnly=false;
	}else{
		disArn.readOnly=true;
	}
}