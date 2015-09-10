function callAmiFn(mode) {
	doAmiSubmit(mode, document.frmMain);
}

function callFn(mode, idkey) {
	document.frmMain.PKey.value = idkey;
	doSubmit(mode, document.frmMain);
}

function doSubmit(mode, frmName) {
	frmName.mode.value = mode;
	if (mode == "List") {
		frmName.hdOpCode.value = 3;
		document.frmMain.action = "CardEmbossServlet";
		document.frmMain.method = "post";	
		submitForm(frmName);
	} else if (mode == "Next") {
		frmName.hdOpCode.value = 2;
		document.frmMain.action = "CardEmbossServlet";
		document.frmMain.method = "post";	
		submitForm(frmName);
	} else if (mode == "Prev") {
		frmName.hdOpCode.value = 1;
		document.frmMain.action = "CardEmbossServlet";
		document.frmMain.method = "post";	
		submitForm(frmName);
	} else if (mode == "Search") {

		if (isBlank(frmName.cardNo.value)
				&& isBlank(frmName.cardEmbossStatus.value)
				&& isBlank(frmName.cardEmbossStartDate.value)
				&& isBlank(frmName.cardEmbossEndDate.value)) {
			alert("Atleast select one search criteria.");
			return false;
		}

		if (!isBlank(frmName.cardEmbossStartDate.value)
				&& !isValidDateFormat(frmName.cardEmbossStartDate.value)) {
			alert("Please enter start date in correct format");
			return false;
		}

		if (!isBlank(frmName.cardEmbossEndDate.value)
				&& !isValidDateFormat(frmName.cardEmbossEndDate.value)) {
			alert("Please enter end date in correct format");
			return false;
		}
		
		if(!validateFromToDate(frmName.cardEmbossStartDate.value,frmName.cardEmbossEndDate.value) ) 
		{
			alert("End Date must be greater than Start Date");
			return false;
		}

		frmName.hdOpCode.value = 0;
		document.frmMain.action = "CardEmbossServlet";
		document.frmMain.method = "post";	
		submitForm(frmName);
	} else if (mode == "Emboss") {

		if (frmName.selectedCardNos.value.length == 0) {
			alert('Please select card to Emboss');
			return false;
		}

		frmName.hdOpCode.value = 4;
		document.frmMain.action = "CardEmbossServlet";
		document.frmMain.method = "post";	
		submitForm(frmName);
		//load();
	} else if (mode == "EmbossRenew") {

		if (frmName.selectedCardNos.value.length == 0) {
			alert('Please select card to Emboss');
			return false;
		}

		frmName.hdOpCode.value = 6;
		document.frmMain.action = "CardEmbossServlet";
		document.frmMain.method = "post";	
		submitForm(frmName);
		//load();
	} else if (mode == "EmbossUnnamed") {

		frmName.hdOpCode.value = 5;
		document.frmMain.action = "CardEmbossServlet";
		document.frmMain.method = "post";	
		submitForm(frmName);
		//load();
	} else if (mode == "ReportUnnamed") {

		document.frmMain.REPORT_FORMAT.value = "0";
		document.frmMain.mode.value = "Down";
		document.frmMain.hdOpCode.value = 5;
		document.frmMain.action = "GenerateReportServlet";
		document.frmMain.method = "post";	
		submitForm(document.frmMain);
	} else if (mode == "EmbossFile") {

		frmName.hdOpCode.value = 15;
		document.frmMain.action = "CardEmbossServlet";
		document.frmMain.method = "post";	
		submitForm(frmName);
	} else if(mode == "RedownloadFile"){
		
		var ele = document.getElementById("reportErrorMessage");
		if(ele != null){
			ele.style.display = "none";
		}
		
    	if(getCheckedValue(frmName.selectedRefNo) == "")
    	{
    		alert("Atleast select one");
    		return false;
    	}
    	
    	if(!checkEmpty("User Name",frmName.userName)) return false;
		if(!checkEmpty("Password",frmName.userPassword)) return false;
    	
    	frmName.hdOpCode.value = 16;
    	submitForm(frmName);
    }
}

function downEmbossFile(path){
	alert(path);
	
	document.frmMain.mode.value = "EmbossFile";
	document.frmMain.embossFile.value = path;
	document.frmMain.hdOpCode.value = 15;
	document.frmMain.action = "CardEmbossServlet";
	document.frmMain.method = "post";	
	submitForm(document.frmMain);
	
	var ele = document.getElementById("embossDownloadFile");
	ele.style.display = "none";
}

function downloadUnnamed(mode, idkey, noOfCards)
{
	document.frmMain.PKey.value = idkey;
	document.frmMain.REPORT_FORMAT.value = "0";
	document.frmMain.noOfCards.value = noOfCards;
	document.frmMain.mode.value = "Down";
	document.frmMain.hdOpCode.value = 5;
	document.frmMain.action = "GenerateReportServlet";
	document.frmMain.method = "post";	
	submitForm(document.frmMain);
}

function download()
{
	
	var category = document.frmMain.category.value;
	if(category == "Named"){
		document.frmMain.REPORT_CODE.value = "CardEmbossNamed";
	}else if(category == "Unnamed"){
		document.frmMain.REPORT_CODE.value = "CardEmbossUnnamedEmbossedNow";
	}else if(category == "Renew"){
		document.frmMain.REPORT_CODE.value = "CardEmbossRenewed";
	}
	
	document.frmMain.REPORT_FORMAT.value = "0";
	document.frmMain.mode.value = "Down";
	document.frmMain.hdOpCode.value = 5;
	document.frmMain.action = "GenerateReportServlet";
	document.frmMain.method = "post";	
	submitForm(document.frmMain);
}

function checkAll(checkname, exby, serialNosToEmboss) {
	var len = checkname.length;

	if (len == undefined) {
		if (exby.checked) {
			checkname.checked = true;
			serialNosToEmboss.value = amendCardNos(checkname.value,
					serialNosToEmboss.value);
		} else {
			serialNosToEmboss.value = removeCardNos(checkname.value,
					serialNosToEmboss.value);
			checkname.checked = false;
		}
	} else {
		if (exby.checked) {
			for (i = 0; i < len; i++) {
				checkname[i].checked = true;
				serialNosToEmboss.value = amendCardNos(checkname[i].value,
						serialNosToEmboss.value);
			}
		} else {
			var selectedValues = serialNosToEmboss.value;
			for (i = 0; i < len; i++) {
				selectedValues = removeCardNos(checkname[i].value,
						selectedValues);
				checkname[i].checked = false;
			}
			serialNosToEmboss.value = selectedValues;
		}
	}
	//alert(serialNosToEmboss.value);
}

function checkCommon(checkname, exby, serialNosToEmboss, checkedValue) {
	//alert('test');
	//alert(checkname);
	//alert(exby);
	//alert(serialNosToEmboss);
	//alert(checkedValue);
	var len = checkname.length;
	//alert(len);
	var allCheck = true;

	if (exby.checked) {
		serialNosToEmboss.value = amendCardNos(checkedValue,
				serialNosToEmboss.value);
	} else {
		serialNosToEmboss.value = removeCardNos(checkedValue,
				serialNosToEmboss.value);
	}

	if (len == undefined) {
		document.feeWaiverForm.checkbox.checked = exby.checked ? true : false;
	} else {
		for (i = 0; i < len; i++) {
			if (!checkname[i].checked) {
				allCheck = false;
				break;
			}
		}

		document.feeWaiverForm.checkbox.checked = allCheck ? true : false;

	}
	alert(serialNosToEmboss.value);
}

function submitForm(frmName) {
	frmName.submit();
}

function amendCardNos(cardNo, variable) {

	if (variable.length > 0) {
		if (variable.indexOf(''+cardNo) == -1) {
			variable += ',' + cardNo;
		}
	} else {
		variable = ''+cardNo;
	}

	return variable;
}

function removeCardNos(cardNo, variable) {
	if (variable.indexOf(',' + cardNo + ',') != -1) {
		variable = variable.replace(',' + cardNo + ',', ",");
	} else if (variable.indexOf(cardNo + ',') != -1) {
		variable = variable.replace(cardNo + ',', "");
	} else if (variable.indexOf(',' + cardNo) != -1) {
		variable = variable.replace(',' + cardNo, "");
	} else if (variable.indexOf(cardNo) != -1) {
		variable = variable.replace(cardNo, "");
	}

	return variable;
}

function getCheckedValue(radioObj) {
	if(!radioObj)
		return "";
	var radioLength = radioObj.length;
	if(radioLength == undefined)
		if(radioObj.checked)
			return radioObj.value;
		else
			return "";
	for(var i = 0; i < radioLength; i++) {
		if(radioObj[i].checked) {
			return radioObj[i].value;
		}
	}
	return "";
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

function load() {
	
	 YAHOO.namespace("example.container");
	 
	 DisableEnableLinks(true);
    
	// Initialize the temporary Panel to display while waiting for external content to load 
	YAHOO.example.container.wait =  
	        new YAHOO.widget.Panel("wait",   
	            { width:"270px",  
	              fixedcenter:true,  
	              close:false,  
	              draggable:false,  
	              zindex:4, 
	              modal:true, 
	              visible:false 
	            }  
	        );

	YAHOO.example.container.wait.setHeader("Please wait untill Card Embossing finish..."); 
	YAHOO.example.container.wait.setBody('<img src="images/loading.gif" />'); 
	YAHOO.example.container.wait.render(document.body);

	YAHOO.example.container.wait.show(); 
	
	}


function DisableEnableLinks(trueOrFalse){

	// disable or enable leftFrame
	var myIFrame2 = window.parent.leftFrame;
	objLinksFromFrames2 = myIFrame2.document.links;
	linkDE(objLinksFromFrames2, trueOrFalse);
	
	// disable or enable topFrame
	var myIFrame3 = window.top.topFrame;
	objLinksFromFrames3 = myIFrame3.document.links;
	linkDE(objLinksFromFrames3, trueOrFalse);
	  
}


function linkDE(objLinks, xHow){
	
	for(i=0;i<objLinks.length;i++){
	    objLinks[i].disabled = xHow;
	    //link with onclick
	    if(objLinks[i].onclick && xHow){  
	        objLinks[i].onclick = new Function("return false;" + objLinks[i].onclick.toString().getFuncBody());
	    }
	    //link without onclick
	    else if(xHow){  
	      objLinks[i].onclick = function(){return false;}
	    }
	    //remove return false with link without onclick
	    else if(!xHow && objLinks[i].onclick.toString().indexOf("function(){return false;}") != -1){            
	      objLinks[i].onclick = null;
	    }
	    //remove return false link with onclick
	    else if(!xHow && objLinks[i].onclick.toString().indexOf("return false;") != -1){  
	    	objLinks[i].onclick = null;
	      //strClick = objLinks[i].onclick.toString().getFuncBody().replace("return false;","");
	      //objLinks[i].onclick = new Function(strClick);
	    }
	  }
}

String.prototype.getFuncBody = function(){ 
	  var str=this.toString(); 
	  str=str.replace(/[^{]+{/,"");
	  str=str.substring(0,str.length-1);   
	  str = str.replace(/\n/gi,"");
	  if(!str.match(/\(.*\)/gi))str += ")";
	  return str; 
	};