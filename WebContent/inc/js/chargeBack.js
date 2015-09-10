function go(action) {
    document.forms[0].method.value=action;
    document.forms[0].action= "chargeBackProcess.do";

	var r=confirm('Are sure to Continue this Operation?');
    if (r==true){
    	document.forms[0].submit();
    }
}
     
function showList(){
	document.forms[0].action= "chargeBackList.do";
	document.forms[0].submit();
}