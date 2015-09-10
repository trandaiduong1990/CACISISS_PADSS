<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/displaytag-12.tld" prefix="display" %>
<%@page import ="java.util.*" %>

<%@page import="org.transinfo.cacis.common.CommonDataBean"%><html:html>
<head>
<title><bean:message key ="customerservice.csscreentitle"/></title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="inc/css/style.css" type="text/css">
<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/engine.js'></script>
<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/util.js'></script>
<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/interface/DWRUtils.js'></script>
<script src="inc/js/jquery-1.6.3.js"></script>
<script type="text/javascript">


function viewAllnonRecontransactions(urlToCall){
	  	var left = (screen.width/2)-(400);
	  	var top = (screen.height/2)-(250);
	 	window.open(urlToCall, "AllStatusRemarks",'resizable =yes, status=yes,scrollbars=yes,menubar=no height=500,width=800,location=no, top='+top+', left='+left);
	  }

function getCardDetails(cardNo,cardType){
	
	//alert(cardNo);
	//alert(cardType);

	//cardNo = '4665350000000015';
	
	if(cardType == 'CreditCard'){
		//alert('1');
		DWRUtils.getCardStat(cardNo, handleReceivedCardData);
	}else{
		//alert('2');
	}
	
}

function handleReceivedCardData(obj)
{
	//alert(obj.isCardExists);
	if(obj.isCardExists == 'FALSE'){

		//alert('no ndet');
		document.getElementById("details").style.display="none";

		document.getElementById("noDetails").style.display="block";
		dwr.util.setValue("noDetails", "No Details to this card.");
		
	}else{

		document.getElementById("noDetails").style.display="none";
		document.getElementById("details").style.display="block";

		dwr.util.setValue("statId", obj.statementId);
		dwr.util.setValue("cardNo", obj.cardNo);
		dwr.util.setValue("prevStatId", obj.prevStatementId);
		dwr.util.setValue("prevStatAmt", obj.prevStatementAmt);
		dwr.util.setValue("prevStatOutAmt", obj.prevStatementOutStandAmt);
		dwr.util.setValue("statAmt", obj.statementAmt);
		dwr.util.setValue("statMinAmt", obj.statementMinAmt);
		dwr.util.setValue("statDueDate", obj.statementDueDate);
		dwr.util.setValue("statFeeAmt", obj.statementFeeAmt);
		dwr.util.setValue("statIntAmt", obj.statementInterestAmt);
		dwr.util.setValue("statCreatedDate", obj.statementCreatedDate);
	
	}
}

function makeTextBoxAsLabel(){
	
	var boxes = document.getElementsByTagName("INPUT");
	for(var i = 0; i < boxes.length; i++){
		boxes[i].className='text_as_label';
	}

	var selects = document.getElementsByTagName("SELECT");
	for(var i = 0; i < selects.length; i++){
		selects[i].className='text_as_label';
	}

}

function disableTab4(){
	//alert('test');
	var el = document.customerScreenProcessForm.currencyCode;
	el.disabled = true;
	el = document.customerScreenProcessForm.cycleNo;
	el.disabled = true;
	el = document.customerScreenProcessForm.creditLimit;
	el.readOnly = true;
	el = document.customerScreenProcessForm.cashLimit;
	el.readOnly = true;
	el = document.customerScreenProcessForm.amtPerTranx;
	el.readOnly = true;
	el = document.customerScreenProcessForm.dailyLimitCount;
	el.readOnly = true;
	el = document.customerScreenProcessForm.dailyLimitAmt;
	el.readOnly = true;
	el = document.customerScreenProcessForm.monthlyLimitCount;
	el.readOnly = true;
	el = document.customerScreenProcessForm.monthlyLimitAmt;
	el.readOnly = true;
	//alert('test2');
}

function enableFileds(){
	//alert('enableFileds');
	var el = document.customerScreenProcessForm.currencyCode;
	el.disabled = false;
	el = document.customerScreenProcessForm.cycleNo;
	el.disabled = false;
	el = document.customerScreenProcessForm.creditLimit;
	el.readOnly = false;
	el = document.customerScreenProcessForm.cashLimit;
	el.readOnly = false;
	el = document.customerScreenProcessForm.amtPerTranx;
	el.readOnly = false;
	el = document.customerScreenProcessForm.dailyLimitCount;
	el.readOnly = false;
	el = document.customerScreenProcessForm.dailyLimitAmt;
	el.readOnly = false;
	el = document.customerScreenProcessForm.monthlyLimitCount;
	el.readOnly = false;
	el = document.customerScreenProcessForm.monthlyLimitAmt;
	el.readOnly = false;
	el = document.getElementById("editRemarks");
	el.style.display = "block";
}

function viewAllInfo(urlToCall){
  	var left = (screen.width/2)-(400);
  	var top = (screen.height/2)-(250);
 	window.open(urlToCall, "CustServiceAllData",'resizable =yes, status=yes,scrollbars=yes,menubar=no height=500,width=800,location=no, top='+top+', left='+left);
  }


function cardNoUpdate(value){
	//alert(value);
	DWRUtils.getCustomer(value, handleReceivedData);
}

function handleReceivedData(obj)
{
	//alert(obj.supplCustomerName);
	DWRUtil.setValue("supplCustomerName",obj.supplCustomerName);
	DWRUtil.setValue("supplSurName",obj.supplSurName);
	DWRUtil.setValue("supplEmbossingName",obj.supplEmbossingName);
	DWRUtil.setValue("strSupplDob",obj.strSupplDob);
	DWRUtil.setValue("supplPob",obj.supplPob);
	DWRUtil.setValue("supplGender",obj.supplGender);
	DWRUtil.setValue("supplMaritalStatus",obj.supplMaritalStatus);
	DWRUtil.setValue("supplIdNumber",obj.supplIdNumber);
	DWRUtil.setValue("strSupplIdDate",obj.strSupplIdDate);
	DWRUtil.setValue("strSupplIdExpire",obj.strSupplIdExpire);
	DWRUtil.setValue("supplIdPlace",obj.supplIdPlace);
	DWRUtil.setValue("supplNationality",obj.supplNationality);
	DWRUtil.setValue("supplEmail",obj.supplEmail);
	DWRUtil.setValue("supplIncome",obj.supplIncome);
	DWRUtil.setValue("relationShip",obj.relationShip);
	
	DWRUtil.setValue("supplementaryAddressaddress1",obj.supplementaryAddressaddress1);
	DWRUtil.setValue("supplementaryAddressaddress2",obj.supplementaryAddressaddress2);
	DWRUtil.setValue("supplementaryAddresscity",obj.supplementaryAddresscity);
	DWRUtil.setValue("supplementaryAddressstate",obj.supplementaryAddressstate);
	DWRUtil.setValue("supplementaryAddresscountry",obj.supplementaryAddresscountry);
	DWRUtil.setValue("supplementaryAddresspostalCode",obj.supplementaryAddresspostalCode);
	DWRUtil.setValue("applicationformphone",obj.applicationformphone);
	DWRUtil.setValue("supplementaryAddressfax",obj.supplementaryAddressfax);
}
</script>
</head>
<script>
 	function go(action) {
	 	//alert("the action "+action);
	 	
	 	var el = document.customerScreenProcessForm.currencyCode;
		el.disabled = false;
		el = document.customerScreenProcessForm.cycleNo;
		el.disabled = false;
	
 	  	document.forms[0].method.value = action;

 	  	document.forms[0].submit();
	 }
 	
 	function updateRadio(){
		//alert("Update Radio");
		 var name = dwr.util.getValue("selectedAppCardProducts");
		 var creditdiv = document.getElementById("credit");
		 var debitdiv = document.getElementById("debit");
		 var tab4=document.getElementById("tab4bg");
		 var tab4bgimg=document.getElementById("tab45dif");
		 var tab4url=document.getElementById("accDetails");
		  
		  //alert("Selected Card Product Radio : "+name);
		  if(name == 3){
			  //alert("debit");
			   
			  creditdiv.style.display='none';
			  //document.getElementById('credit').style.visibility ='show';
			  debitdiv.style.display ='block';
			  //document.getElementById("credit").style.visibility ='hidden';
			  tab4.style.display='none';
			  tab4bgimg.style.display='none';
			  tab4url.style.display='none';
			  
			
			  //disableTab4();
			
			      
			    }
		  else{
			  
			  //alert("credit");
			  creditdiv.style.display='block';
			  debitdiv.style.display ='none';
			  tab4.style.display='block';
			  tab4bgimg.style.display='block';
			  tab4url.style.display='block';
			  
			  tab4url.setAttribute('href','javascript:switchMenu(\'tab4\')');
			  tab4url.getElementById('accDetails').onclick = function(){return true;};
			  
			  
			  
			  
			  
			  //enableFileds();
		  }
 	}
	 
	function history(action) {
 	//alert("the action "+action);
 	  document.forms[0].method.value = action;
	  document.forms[0].submit();
	  
	 } 
	 
	function showList(){
          document.forms[0].action= "customerscreenlistpage.do";
          document.forms[0].submit();
       }
</script>

<script type="text/javascript">

function switchMenu(obj) {
	//alert('testttttt');
	pageLoad();
	var el = document.getElementById(obj);
	if ( el.style.display != "none" ) {
		el.style.display = 'none';
	}
	else {
		el.style.display = '';
	}

	tabEnable(obj);
}

function tabEnable(obj){

	// off all the tabs
	document.getElementById('tab1start').src="images/form_tab_start_off.gif";
	document.getElementById('tab1bg').style.backgroundImage="url('images/form_tab_bg_off.gif')";
	document.getElementById('tab12dif').src="images/form_tab_btw_off_off.gif";
	document.getElementById('tab2bg').style.backgroundImage="url('images/form_tab_bg_off.gif')";
	document.getElementById('tab23dif').src="images/form_tab_btw_off_off.gif";
	document.getElementById('tab3bg').style.backgroundImage="url('images/form_tab_bg_off.gif')";
	document.getElementById('tab34dif').src="images/form_tab_btw_off_off.gif";
	document.getElementById('tab4bg').style.backgroundImage="url('images/form_tab_bg_off.gif')";
	document.getElementById('tab45dif').src="images/form_tab_btw_off_off.gif";
	document.getElementById('tab5bg').style.backgroundImage="url('images/form_tab_bg_off.gif')";
	document.getElementById('tab5end').src="images/form_tab_end_off.gif";
	
	if(obj == 'tab1'){
		document.getElementById('tab1start').src="images/form_tab_start_on.gif";
		document.getElementById('tab1bg').style.backgroundImage="url('images/form_tab_bg_on.gif')";
		document.getElementById('tab12dif').src="images/form_tab_btw_on_off.gif";
	}else if(obj == 'tab2'){
		document.getElementById('tab12dif').src="images/form_tab_btw_off_on.gif";
		document.getElementById('tab2bg').style.backgroundImage="url('images/form_tab_bg_on.gif')";
		document.getElementById('tab23dif').src="images/form_tab_btw_on_off.gif";
	}else if(obj == 'tab3'){
		document.getElementById('tab23dif').src="images/form_tab_btw_off_on.gif";
		document.getElementById('tab3bg').style.backgroundImage="url('images/form_tab_bg_on.gif')";
		document.getElementById('tab34dif').src="images/form_tab_btw_on_off.gif";
	}else if(obj == 'tab4'){
		document.getElementById('tab34dif').src="images/form_tab_btw_off_on.gif";
		document.getElementById('tab4bg').style.backgroundImage="url('images/form_tab_bg_on.gif')";
		document.getElementById('tab45dif').src="images/form_tab_btw_on_off.gif";
	}else if(obj == 'tab5'){
		document.getElementById('tab45dif').src="images/form_tab_btw_off_on.gif";
		document.getElementById('tab5bg').style.backgroundImage="url('images/form_tab_bg_on.gif')";
		document.getElementById('tab5end').src="images/form_tab_end_on.gif";
	}


	document.getElementById("details").style.display="none";
	document.getElementById("noDetails").style.display="none";
}

function $() {
	var elements = new Array();
	for (var i = 0; i < arguments.length; i++) {
		var element = arguments[i];
		if (typeof element == 'string')
			element = document.getElementById(element);
		if (arguments.length == 1)
			return element;
		elements.push(element);
	}
	return elements;
}

function collapseAll(objs) {
	var i;
	for (i=0;i<objs.length;i++ ) {
		objs[i].style.display = 'none';
	}
}

function cardProductSelection(event) {
	alert('cardProductSelection');
	if("undefined" != typeof(event) )event.returnValue = false; 
    return false;
}

function pageLoad() {
	collapseAll($('tab1','tab2','tab3','tab4','tab5'));
}
function showInitTab(){
	<logic:present name="CUSTOMERHISTORYLIST" scope="request">  
		switchMenu('tab5');
	</logic:present>
	<logic:notPresent name="CUSTOMERHISTORYLIST" scope="request">  
		switchMenu('tab1');
	</logic:notPresent>
}

</script>

<%
org.transinfo.cacis.dto.useraccess.UserMasterDto objUserAccess = (org.transinfo.cacis.dto.useraccess.UserMasterDto) session.getAttribute((String)session.getAttribute("USERID"));
%>

<body onLoad="showInitTab();updateRadio();">
<html:form action="customerscreenprocess.do">
<input type="hidden" name="method"/>
<html:hidden property="issuerId" value="<%=(String)session.getAttribute("ISSUER")%>"/>
<html:hidden property="userId" value="<%=(String)session.getAttribute("USERID")%>"/>
<html:hidden property ="homeAddress.addressId" />
<html:hidden property ="companyAddress.addressId" />
<html:hidden property ="supplementaryAddress.addressId" />
<html:hidden property="applicationId" />
<html:hidden property="accountOnSystem" />
<html:hidden property="customerId" />
<html:hidden property="cardNumber" />
<html:hidden property="subCardsCount" />
<html:hidden property="status" />

<input type ="hidden" name="nricNo" value="<bean:write name ="customerScreenProcessForm"  property="csn" />" />
   <table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr> 
      <td> 
        <table border="0" cellspacing="0" cellpadding="0">
        <tr> 
            <td class="titreSection" width="519"><bean:message key ="customerservice.csscreentitle"/> </td>
            <td class="titreSection" width="484">&nbsp; </td>
        </tr>
        <tr> 
            <td background="images/ligne.gif" colspan="3"> <img src="images/spacer.gif" width="1" height="1"></td>
        </tr>
        <tr> 
            <td class="titreSection" width="519">&nbsp;</td>
             <td class="texteMenuGauche"><div align="right"><a href='javascript:helpLink("WRITEOFF")'>Help</a></div></td>
        </tr>
        <tr> 
            <td background="images/ligne.gif" colspan="3"> <img src="images/spacer.gif" width="1" height="1"></td>
        </tr>
      </table>
    </td>
  </tr>
  <tr><td valign="top" class="ErrFONT"><font color="#FF0000"><html:errors/></font></td></tr>
  <tr> 
      <td valign="top"><br>
      <table>
        <tr> 
          <td width="848" valign="top"> 
              <table width="100%" border="0" align="left" cellpadding="0" cellspacing="0">
                <tr> 
                  <td><img id="tab1start" src="images/form_tab_start_off.gif" border="0" alt="" width="5" height="22"></td>
                  <td background="images/tbl_haut.gif" colspan="2"> 
                    <table border="0" cellpadding="0" cellspacing="0">
                      <tr> 
                        <td id="tab1bg" background="images/form_tab_bg_off.gif" class="form_tab_off" style="padding-left:15px">
                        	<a href="javascript:switchMenu('tab1')"><bean:message key ="applicationform.tab1"/></a>
                        </td>
                        <td><img id="tab12dif" src="images/form_tab_btw_off_off.gif" width="25" height="22"></td>
                        <td id="tab2bg" background="images/form_tab_bg_off.gif" class="form_tab_off">
                        	<a href="javascript:switchMenu('tab2')"><bean:message key ="applicationform.tab2"/></a>
                        </td>
                        <td><img id="tab23dif" src="images/form_tab_btw_off_off.gif" width="25" height="22"></td>
                        <td id="tab3bg" background="images/form_tab_bg_off.gif" class="form_tab_off">
                        	<logic:equal name="customerScreenProcessForm" property="subCardsCount" value="1">
                        		<a href="javascript:switchMenu('tab3')"><bean:message key ="customerscreen.tab3"/></a>
                        	</logic:equal>
                        	<logic:equal name="customerScreenProcessForm" property="subCardsCount" value="2">
                        		<bean:message key ="customerscreen.tab3"/>
                        	</logic:equal>
                        </td>
                        <td><img id="tab34dif" src="images/form_tab_btw_off_off.gif" width="25" height="22"></td>
                         <td id="tab4bg" background="images/form_tab_bg_off.gif" class="form_tab_off">
	          				<logic:equal name="customerScreenProcessForm" property="accountOnSystem" value="Y">
                   				<a href="javascript:switchMenu('tab4')"><bean:message key ="applicationform.tab4"/></a>
                  			</logic:equal>
                  			<logic:equal name="customerScreenProcessForm" property="accountOnSystem" value="N">
                   				<bean:message key ="applicationform.tab4"/>
                  			</logic:equal>
                         </td>
                        <td><img id="tab45dif" src="images/form_tab_btw_off_off.gif" width="25" height="22"></td>
                        <td id="tab5bg" background="images/form_tab_bg_off.gif" class="form_tab_off">
                        	<a href="javascript:history('customerHistory')"><bean:message key ="applicationform.tab5"/></a>
                        </td>
                        <td><img id="tab5end" src="images/form_tab_end_off.gif" width="25" height="22"></td>
                      </tr>
                    </table>
                  </td>
                  <td><img src="images/tbl_haut_d.gif" border="0" alt="" width="5" height="22"></td>
                </tr>
                <tr> 
                  <td background="images/tbl_g.gif"></td>
                  <td class="form_bgcolor"  colspan="2"> 
                    <div align="right">
                    	<logic:equal value="A" property="status" name="customerScreenProcessForm">
                    	<% if(objUserAccess.hasFunction("CUSTOMER_SCREEN", "CUSTOMER_SCREEN_SETUP_ADD")){ %>
					    <html:submit onclick="go('csupdate')"><bean:message key ="common.save"/></html:submit>
					    <% } %>
					    </logic:equal>
        				<html:cancel onclick ="showList()"><bean:message key ="common.cancel"/></html:cancel>
                    </div>
                   </td>
                  <td background="images/tbl_d.gif"></td>
                </tr>
                <tr>
                  <td background="images/tbl_g.gif"></td>
                </tr>
                <tr>
                  <td height="153" background="images/tbl_g.gif"></td>
                  <td colspan="2" valign="top" class="form_bgcolor">
				  <!-- tab1 -->
				  <div id="tab1">
				  <table border="0" cellspacing="0" cellpadding="0">
                    <tr>
                      <td colspan="2" style="padding: 20px 20px 10px 20px;">
                      <table border="0" cellpadding="0" cellspacing="0" width="264" height="36">
                      	<tr style="padding-bottom: 10px;">
                      		<td class="desc_cell">
                      			<bean:message key ="customerscreen.cardnumber"/>
                      		</td>
                      		<td class="desc_cell">
                      			<bean:write name="customerScreenProcessForm" property="cardNumber" />
                      		</td>
                      	</tr>
                      	<tr style="padding-bottom: 10px;">
                      		<td class="desc_cell">
                      			<bean:message key ="customerservice.status"/>
                      		</td>
                      		<td class="desc_cell">
                      			<logic:equal value="A" property="status" name="customerScreenProcessForm">
                              	<bean:message key ="common.active"/>
                              </logic:equal>
                              <logic:equal value="C" property="status" name="customerScreenProcessForm">
                              	<bean:message key ="common.closed"/>
                              </logic:equal>
                      		</td>
                      	</tr>
                        <tr>
                          <td width="132" height="18" nowrap class="desc_cell"><bean:message key ="applicationform.branchname"/></td>
                          <td class="label">
                              <html:select property="branchId" >
      	                            <html:option value=""></html:option>	
                                    <html:optionsCollection property="branchList" value="key" label="value" />
  	                            </html:select>  
  	                        </td>
  	                          <td>&nbsp;&nbsp;</td>
                          <td height="18" nowrap class="desc_cell"><bean:message key ="applicationform.customertype"/></td>
                          <td class="label">
                          <html:select property="customerTypeId" >
      	                        <html:option value=""></html:option>	
      	                        <html:optionsCollection property="customerTypeList" value="key" label="value" />
  	                     </html:select></td>
                        </tr>
                         <tr><td colspan="4">&nbsp;&nbsp;</td></tr>
                        <tr>
                          <td height="18" nowrap class="desc_cell"><bean:message key ="applicationform.cardproduct"/></td>
                          <td colspan="3" class="label"><table border="0" cellspacing="0" cellpadding="0">
                      		<logic:iterate id="appcardprods" name ="customerScreenProcessForm" property="appCardProductList">
	                         <%
	                          Map.Entry   objMapEntry =(Map.Entry)pageContext.getAttribute("appcardprods");
	                          String cardProductName = (String) objMapEntry.getValue();
	                         %>
	                          <%if(cardProductName.startsWith("V")){ %>
	                            <tr>
	                              <td nowrap class="label">
	                            	<html:radio property="selectedAppCardProducts" value="key" idName="appcardprods" onclick="return false;" />
									<bean:write name="appcardprods" property="value"/>
								 </td>
						     </tr>
						     <%}else if(cardProductName.startsWith("M")){ %>
	                            <tr>
	                              <td nowrap class="label">
	                            	<html:radio property="selectedAppCardProducts" value="key" idName="appcardprods" onclick="return false;" />
									<bean:write name="appcardprods" property="value"/>
								 </td>
						      </tr>
						      <%}else if(cardProductName.startsWith("A")){ %>
	                            <tr>
	                              <td nowrap class="label">
	                            	<html:radio property="selectedAppCardProducts" value="key" idName="appcardprods" onclick="return false;" />
									<bean:write name="appcardprods" property="value"/>
						      	 </td>
						      </tr>
						      <%}else if(cardProductName.startsWith("C")){ %>
	                            <tr>
	                              <td nowrap class="label">
	                            	<html:radio property="selectedAppCardProducts" value="key" idName="appcardprods" onclick="return false;" />
									<bean:write name="appcardprods" property="value"/>
						      	 </td>
						      </tr>
                              <%} %>
                              		                        
							<%if(cardProductName.startsWith("J")){ %>
	                        <tr>
	                              <td nowrap class="label">
									 <html:radio property="selectedAppCardProducts" value="key" idName="appcardprods" onclick="return false;" />
									 <bean:write name="appcardprods" property="value"/>
								 </td>			
						    </tr>		      
	                        <%} %>
	                        
                			</logic:iterate>    
                          </table></td>
                          </tr>
                        
                      </table></td>
                      </tr>
                    <tr>
                      <td valign="top" style="padding: 20px 20px 10px 20px;"><table cellspacing=0 cellpadding=0 
                              border=0>
                          <tbody>
                            <tr>
                              <td><table cellspacing=0 cellpadding=0 border=0>
                                  <tbody>
                                    <tr>
                                      <td><img height=19 alt="" src="images/tab_g.gif" width=8 border=0></td>
                                      <td class=group_title background=images/tab_fond.gif><bean:message key ="applicationform.applicatntdetails"/></td>
                                      <td><img height=19 alt="" src="images/tab_d.gif" width=8 border=0></td>
                                    </tr>
                                  </tbody>
                              </table></td>
                            </tr>
                            <tr>
                              <td bgcolor=#dce5ea><div 
                                style="FONT-SIZE: 1px">&nbsp;</div></td>
                            </tr>
                            <tr>
                              <td height="21"><table border="0" cellpadding="0" cellspacing="0" width="264" height="144">
                                  <tr bordercolor="#111111">
                                    <td width="132" class="desc_cell"><bean:message key ="applicationform.csn"/></td>
                                    <td><html:text property="csn" maxlength="20" size="20"/></td>
                                  </tr>
                                  <tr bordercolor="#111111">
                                    <td width="132" class="desc_cell"><bean:message key ="applicationform.customername"/></td>
                                    <td><html:text property="customerName" size="20"/></td>
                                  </tr>
                                  <tr bordercolor="#111111">
                                    <td class="desc_cell"><bean:message key ="applicationform.surname"/></td>
                                    <td><html:text property="surName" size="20"/></td>
                                  </tr>
                                  <tr bordercolor="#111111">
                                    <td class="desc_cell"><bean:message key ="applicationform.embossingName"/></td>
                                    <td><html:text property="embossingName" size="20"/></td>
                                  </tr>
                                  <tr bordercolor="#111111">
                                    <td class="desc_cell"><bean:message key ="applicationform.dob"/></td>
                                    <td><html:text property="strDob" size="20"/></td>
                                  </tr>
                                  <tr bordercolor="#111111">
                                    <td class="desc_cell"><bean:message key ="applicationform.pob"/></td>
                                    <td><html:text property="pob" size="20"/></td>
                                  </tr>
                                  
                                  <tr bordercolor="#111111">
                                    <td class="desc_cell"><bean:message key ="applicationform.idnumber"/> </td>
                                    <td><html:text property="idNumber" size="20"/></td>
                                  </tr>
                                  <tr bordercolor="#111111">
                                    <td class="desc_cell"><bean:message key ="applicationform.iddate"/></td>
                                    <td><html:text property="strIdDate" size="20"/></td>
                                  </tr>
                                  <tr bordercolor="#111111">
                                    <td class="desc_cell"><bean:message key ="applicationform.expdate"/> </td>
                                    <td><html:text property="strExpDate" size="20"/></td>
                                  </tr>
                                  <tr bordercolor="#111111">
                                    <td class="desc_cell"><bean:message key ="applicationform.idplace"/></td>
                                    <td><html:text property="idPlace" size="20"/></td>
                                  </tr>
                                  <tr bordercolor="#111111">
                                    <td class="desc_cell"><bean:message key ="applicationform.nationality"/> </td>
                                    <td><html:text property="nationality" size="20"/></td>
                                  </tr>
                                  <tr bordercolor="#111111">
                                    <td class="desc_cell"><bean:message key ="applicationform.billingaddress"/></td>
                                    <td nowrap class="label"><html:radio property ="billingTo" value ="H"><bean:message key ="applicationform.billaddresidential"/></html:radio>
                                      <html:radio property ="billingTo" value ="C"><bean:message key ="applicationform.billaddoffice"/></html:radio>
                                     </td>
                                  </tr>
                             </table></td>
                            </tr>
                          </tbody>
                      </table></td>
                      <td valign="top" style="padding: 20px 20px 10px 20px;"><table width="335" 
                              border=0 cellpadding=0 cellspacing=0>
                          <tbody>
                            <tr>
                              <td width="335"><table cellspacing=0 cellpadding=0 border=0>
                                  <tbody>
                                    <tr>
                                      <td><img height=19 alt="" src="images/tab_g.gif" width=8 border=0></td>
                                      <td class=group_title background=images/tab_fond.gif><bean:message key ="applicationform.residentialaddress"/></td>
                                      <td><img height=19 alt="" src="images/tab_d.gif" width=8 border=0></td>
                                    </tr>
                                  </tbody>
                              </table></td>
                            </tr>
                            <tr>
                              <td bgcolor=#dce5ea><div 
                                style="FONT-SIZE: 1px">&nbsp;</div></td>
                            </tr>
                            <tr>
                              <td height="159"><table border="0" cellpadding="0" cellspacing="0" width="264" height="109">
                                  <tr>
                                    <td class="desc_cell" nowrap><bean:message key ="applicationform.address1"/></td>
                                    <td class="label"><html:text property="homeAddress.address1" size="20"/></td>
                                  </tr>
                                  <tr>
                                    <td class="desc_cell" nowrap><bean:message key ="applicationform.address2"/> </td>
                                    <td class="label"><html:text property="homeAddress.address2" size="20"/></td>
                                  </tr>
                                  <tr>
                                    <td class="desc_cell" nowrap><bean:message key ="applicationform.country"/></td>
                                    <td class="label">
                                    <html:select property="homeAddress.country" >
								   	   <html:option value=""></html:option>	
								      	<html:optionsCollection property="countryList" value="key" label="value" />
								    </html:select></td>
                                  </tr>
                                  <tr>
                                    <td class="desc_cell" nowrap><bean:message key ="applicationform.state"/></td>
                                    <td class="label"><html:text property="homeAddress.state" size="20"/></td>
                                  </tr>
                                  <tr>
                                    <td class="desc_cell" nowrap><bean:message key ="applicationform.city"/></td>
                                    <td class="label"><html:text property="homeAddress.city" size="20"/></td>
                                  </tr>
                                  <tr>
                                    <td class="desc_cell" nowrap><bean:message key ="applicationform.township"/></td>
                                    <td class="label"><html:text property="homeAddress.township" size="20"/></td>
                                  </tr>
                                  <tr>
                                    <td class="desc_cell" nowrap width="132"><bean:message key ="applicationform.postalcode"/> </td>
                                    <td class="label"><html:text property="homeAddress.postalCode" size="20"/></td>
                                  </tr>
                                  <tr>
                                    <td class="desc_cell"><bean:message key ="applicationform.phone"/></td>
                                    <td class="label"><html:text property="homeAddress.phone" size="20"/></td>
                                  </tr>
                                  <tr>
                                    <td class="desc_cell"><bean:message key ="applicationform.fax"/></td>
                                    <td class="label"><html:text property="homeAddress.fax" size="20"/></td>
                                  </tr>
                                  <tr>
                                    <td class="desc_cell"><bean:message key ="applicationform.homeTp"/></td>
                                    <td><html:text property="homeAddress.homeTp" size="20" maxlength="12"/></td>
                                  </tr>
                              </table></td>
                            </tr>
                          </tbody>
                      </table></td>
                    </tr>
                    <logic:present name="customerScreenProcessForm" property="appDocProofList" >
			          	<bean:size id="size" name="customerScreenProcessForm" property="appDocProofList"/>
			          	<logic:greaterThan name="size" value="0" >
		                    <tr>
		                      <td colspan="2" valign="top" style="padding: 20px 20px 10px 20px;">
		                      <table cellspacing=0 cellpadding=0           border=0>
		                        <tbody>
		                          <tr>
		                            <td><table cellspacing=0 cellpadding=0 border=0>
		                                <tbody>
		                                  <tr>
		                                    <td><img height=19 alt="" src="images/tab_g.gif" width=8 border=0></td>
		                                    <td class=group_title background=images/tab_fond.gif><bean:message key ="applicationform.remarks"/></td>
		                                    <td><img height=19 alt="" src="images/tab_d.gif" width=8 border=0></td>
		                                  </tr>
		                                </tbody>
		                            </table></td>
		                          </tr>
		                          <tr>
		                            <td bgcolor=#dce5ea><div 
		                                style="FONT-SIZE: 1px">&nbsp;</div></td>
		                          </tr>
		                          <tr>
		                            <td height="21"><table width="100%" border="0" cellspacing="0" cellpadding="0">
		                              <tr>
		                                <td><table border="0" cellpadding="0" cellspacing="0" width="264" height="36">
		
		                                  <tr>
		                                    <td width="132" height="18" nowrap class="desc_cell"><bean:message key ="applicationform.docssubmitted"/></td>
		                                    <td class="label"><table border="0" cellspacing="0" cellpadding="0">
		                                        <tr>
		                                          <td nowrap class="label">
		                                  <logic:iterate id="appdocproof" name ="customerScreenProcessForm" property="appDocProofList">
												    <html:multibox property="selectedAppDocuments">
												     <bean:write name="appdocproof" property ="key"/>
												    </html:multibox>
												    <bean:write name="appdocproof" property ="value"/>
										  </logic:iterate>
												 </td>
												</tr>
		                                    </table></td>
		                                  </tr>
		                                  <tr>
		                                    <td height="18" valign="top" nowrap class="desc_cell"><bean:message key ="applicationform.remarks"/></td>
		                                    <td class="label"><html:textarea property="remarks" cols="80" rows="4" /></td>
		                                  </tr>
		                                </table></td>
		                              </tr>
		                              
		                            </table></td>
		                          </tr>
		                        </tbody>
		                      </table></td>
		                      </tr>
	                	</logic:greaterThan>
	                </logic:present>
                  </table>
				  </div>
				  <!-- tab1 end -->
				  
				  <!-- tab2 -->
				  <div id="tab2">
                    <table border="0" cellspacing="0" cellpadding="0">
                      <tr>
                        <td rowspan="2" valign="top" style="padding: 20px 20px 10px 20px;"><table cellspacing=0 cellpadding=0 
                              border=0>
                          <tbody>
                            <tr>
                              <td><table cellspacing=0 cellpadding=0 border=0>
                                  <tbody>
                                    <tr>
                                      <td><img height=19 alt="" src="images/tab_g.gif" width=8 border=0></td>
                                      <td class=group_title background=images/tab_fond.gif><bean:message key ="applicationform.employmentdetails"/></td>
                                      <td><img height=19 alt="" src="images/tab_d.gif" width=8 border=0></td>
                                    </tr>
                                  </tbody>
                              </table></td>
                            </tr>
                            <tr>
                              <td bgcolor=#dce5ea><div 
                                style="FONT-SIZE: 1px">&nbsp;</div></td>
                            </tr>
                            <tr>
                              <td height="21"><table border="0" cellpadding="0" cellspacing="0" width="264" height="54">
                                  <tr bordercolor="#111111">
                                    <td class="desc_cell"><bean:message key ="applicationform.jobstatus"/></td>
                                    <td><html:select property="jobStatus" >
                                          <html:option value=""></html:option>
                                          <html:optionsCollection property="jobStatusList" value="key" label="value" />   
                                         </html:select>                     </td>
                                  </tr>
                                  <tr bordercolor="#111111">
                                    <td class="desc_cell"><bean:message key ="applicationform.jobstatusothers"/></td>
                                    <td><html:text property="jobStatusOthers" size="20"/></td>
                                  </tr>
                                  <tr bordercolor="#111111">
                                    <td class="desc_cell"><bean:message key ="applicationform.jobtype"/></td>
                                    <td><html:select property="jobType" >
                                          <html:option value=""></html:option>
                                      	 <html:optionsCollection property="jobTypeList" value="key" label="value" />
								      </html:select>                                   </td>
                                  </tr>
                                  <tr bordercolor="#111111">
                                    <td class="desc_cell"><bean:message key ="applicationform.jobtypeothers"/></td>
                                    <td><html:text property="jobTypeOthers" size="20"/></td>
                                  </tr>
                                  <tr bordercolor="#111111">
                                    <td class="desc_cell"><bean:message key ="applicationform.income"/> </td>
                                    <td><html:text property="income" size="20"/></td>
                                  </tr>
                                  <tr bordercolor="#111111">
                                    <td class="desc_cell"><bean:message key ="applicationform.companyname"/> </td>
                                    <td><html:text property ="companyName" size ="20"/></td>
                                  </tr>
                                   <tr>
                                    <td class="desc_cell" nowrap ><bean:message key ="applicationform.address1"/></td>
                                    <td><html:text property="companyAddress.address1" size="20"/></td>
                                  </tr>
                                  <tr>
                                    <td class="desc_cell" nowrap ><bean:message key ="applicationform.address2"/> </td>
                                    <td><html:text property="companyAddress.address2" size="20"/></td>
                                  </tr>
                                  <tr>
                                    <td class="desc_cell" nowrap ><bean:message key ="applicationform.country"/></td>
                                    <td>
                                    <html:select property="companyAddress.country" >
								   	   <html:option value=""></html:option>	
								      	<html:optionsCollection property="countryList" value="key" label="value" />
								    </html:select></td>
                                  </tr>
                                  <tr>
                                    <td class="desc_cell" nowrap ><bean:message key ="applicationform.state"/></td>
                                    <td><html:text property="companyAddress.state" size="20"/></td>
                                  </tr>
                                  <tr>
                                    <td class="desc_cell" nowrap ><bean:message key ="applicationform.city"/></td>
                                    <td><html:text property="companyAddress.city" size="20"/></td>
                                  </tr>
                                  <tr>
                                    <td class="desc_cell" nowrap ><bean:message key ="applicationform.township"/></td>
                                    <td><html:text property="companyAddress.township" size="20"/></td>
                                  </tr>
                                  <tr>
                                    <td class="desc_cell" nowrap width="132"><bean:message key ="applicationform.postalcode"/> </td>
                                    <td><html:text property="companyAddress.postalCode" size="20"/></td>
                                  </tr>
                                  <tr>
                                    <td class="desc_cell"><bean:message key ="applicationform.phone"/></td>
                                    <td><html:text property="companyAddress.phone" size="20"/></td>
                                  </tr>
                                  <tr>
                                    <td class="desc_cell"><bean:message key ="applicationform.fax"/></td>
                                    <td><html:text property="companyAddress.fax" size="20"/></td>
                                  </tr>
                                
                              </table></td>
                            </tr>
                          </tbody>
                        </table></td>
                        <td valign="top" style="padding: 20px 20px 10px 20px;"><table cellspacing=0 cellpadding=0 
                              border=0>
                          <tbody>
                            <tr>
                              <td><table cellspacing=0 cellpadding=0 border=0>
                                  <tbody>
                                    <tr>
                                      <td><img height=19 alt="" src="images/tab_g.gif" width=8 border=0></td>
                                      <td class=group_title background=images/tab_fond.gif><bean:message key ="applicationform.personaldetails"/></td>
                                      <td><img height=19 alt="" src="images/tab_d.gif" width=8 border=0></td>
                                    </tr>
                                  </tbody>
                              </table></td>
                            </tr>
                            <tr>
                              <td bgcolor=#dce5ea><div 
                                style="FONT-SIZE: 1px">&nbsp;</div></td>
                            </tr>
                            <tr>
                              <td height="21"><table border="0" cellpadding="0" cellspacing="0" width="264" height="54">
                                  <tr bordercolor="#111111">
                                    <td width="132" class="desc_cell"><bean:message key ="applicationform.gender"/></td>
                                    <td>
                                     <html:select property="gender" >
                                          <html:option value=""></html:option>
								          <html:optionsCollection property="genderList" value="key" label="value" />
								       </html:select> 
							         </td>
								   </tr>
                                  <tr bordercolor="#111111">
                                    <td class="desc_cell"><bean:message key ="applicationform.maritalstatus"/></td>
                                    <td> <html:select property="maritalStatus" >
                                          <html:option value=""></html:option>
                                          <html:optionsCollection property="maritalStatusList" value="key" label="value" />
								       </html:select></td>
                                  </tr>
                                  <tr bordercolor="#111111">
                                    <td class="desc_cell"><bean:message key ="applicationform.numberofdependents"/></td>
                                    <td><html:text property="dependents" size="20"/></td>
                                  </tr>
                                  <tr bordercolor="#111111">
                                    <td class="desc_cell"><bean:message key ="applicationform.education"/></td>
                                    <td><html:select property="education" >
                                          <html:option value=""></html:option>
                                          <html:optionsCollection property="educationList" value="key" label="value" />   
                                        </html:select>                               </td>
                                  </tr>
                                  <tr bordercolor="#111111">
                                    <td class="desc_cell"><bean:message key ="applicationform.email"/></td>
                                    <td><html:text property="email" size="40"/></td>
                                  </tr>
                              </table></td>
                            </tr>
                          </tbody>
                        </table></td>
                      </tr>
                      <tr>
                        <td valign="top" style="padding: 20px 20px 10px 20px;"><table cellspacing=0 cellpadding=0 
                              border=0>
                          <tbody>
                            <tr>
                              <td><table cellspacing=0 cellpadding=0 border=0>
                                  <tbody>
                                    <tr>
                                      <td><img height=19 alt="" src="images/tab_g.gif" width=8 border=0></td>
                                      <td class=group_title background=images/tab_fond.gif><bean:message key ="applicationform.familydetails"/></td>
                                      <td><img height=19 alt="" src="images/tab_d.gif" width=8 border=0></td>
                                    </tr>
                                  </tbody>
                              </table></td>
                            </tr>
                            <tr>
                              <td bgcolor=#dce5ea><div 
                                style="FONT-SIZE: 1px">&nbsp;</div></td>
                            </tr>
                            <tr>
                              <td height="20"><table border="0" cellpadding="0" cellspacing="0" width="264" height="108">

                                  <tr bordercolor="#111111">
                                    <td colspan="2" nowrap class="desc_cell"><strong><bean:message key ="applicationform.livingrelativesname"/></strong></td>
                                    </tr>
                                  <tr bordercolor="#111111">
                                    <td width="132" class="desc_cell"><bean:message key ="applicationform.referencename"/></td>
                                    <td><html:text property="referenceName" size="20"/></td>
                                  </tr>
                                  <tr bordercolor="#111111">
                                    <td class="desc_cell"><bean:message key ="applicationform.referencecompany"/></td>
                                    <td><html:text property="referenceCompany" size="20"/></td>
                                  </tr>
                                  <tr bordercolor="#111111">
                                    <td class="desc_cell"><bean:message key ="applicationform.referencephone"/></td>
                                    <td><html:text property="referencePhone" size="20"/></td>
                                  </tr>
                                 
                                  <tr bordercolor="#111111">
                                    <td class="desc_cell"><bean:message key ="applicationform.referencefax"/></td>
                                    <td><html:text property="referenceFax" size="20"/></td>
                                  </tr>
                                  
                              </table></td>
                            </tr>
                          </tbody>
                        </table></td>
                      </tr>
                      <tr>
                        <td colspan="2" valign="top" style="padding: 20px 20px 10px 20px;"><table cellspacing=0 cellpadding=0 
                              border=0>
                          <tbody>
                            <tr>
                              <td><table cellspacing=0 cellpadding=0 border=0>
                                  <tbody>
                                    <tr>
                                      <td><img height=19 alt="" src="images/tab_g.gif" width=8 border=0></td>
                                      <td class=group_title background=images/tab_fond.gif><bean:message key ="applicationform.atmlinkdetails"/></td>
                                      <td><img height=19 alt="" src="images/tab_d.gif" width=8 border=0></td>
                                    </tr>
                                  </tbody>
                              </table></td>
                            </tr>
                            <tr>
                              <td bgcolor=#dce5ea><div 
                                style="FONT-SIZE: 1px">&nbsp;</div></td>
                            </tr>
                            <tr>
                              <td height="21"><table width="100%" border="0" cellpadding="0" cellspacing="0">
                                  <tr bordercolor="#111111">
                                    <td nowrap class="desc_cell"><strong>
                                     <bean:message key ="applicationform.paymentaccount"/></strong></td>
                                  </tr>
                                  <tr bordercolor="#111111">
                                    <td height="25" nowrap>
                                    <!--Credit DIV HERE -->
                                    <!--  <div id="credit" style="display: none;">  -->
                                    <div id="credit">
                                    
                                    
                                    <table border="0" cellspacing="0" cellpadding="0">
                                      <tr bordercolor="#111111">
                                        <td class="desc_cell"><bean:message key ="applicationform.collectrolamt"/></td>
                                        <td><html:text property="collectoralAmt" size="20" maxlength="20"/></td>
                                      </tr>
                                      <tr bordercolor="#111111">
                                        <td class="desc_cell"><bean:message key ="applicationform.collectrolac"/></td>
                                        <td><html:text property="collectoralAccount" size="20" maxlength="20"/></td>
                                      </tr>
                                      <tr bordercolor="#111111">
                                        <td class="desc_cell"><bean:message key ="applicationform.autopaymentaccount"/></td>
                                        <td><html:text property="autoPayAccount" size="20" maxlength="20"/><html:checkbox property ="autoPayAccountOn" /></td>
                                      </tr>
                                      
                                      <tr bordercolor="#111111">
                                        <td class="desc_cell" nowrap="nowrap"><bean:message key ="applicationform.autopaymentdisable"/></td>
                                        <td nowrap >
                                        	<html:checkbox property ="autoPayDisable" />
                                        	<html:hidden property="autoPayDisable" value="false"/>
                                        </td>
                                      </tr>
                                    </table></td>
                                  </tr>
                                             
                                   <!--Credit DIV END HERE --> 
                                  
                            </div>
                             <%--} else{ --%>
		                            <tr>
		                              <td bgcolor=#dce5ea><div 
		                                style="FONT-SIZE: 1px">&nbsp;</div></td>
		                            </tr>
                                  <tr bordercolor="#111111">
                                    <td nowrap class="desc_cell"><strong>
                                    
                                      <!--DEBIT DIV HERE --> 
		                            <!-- <div id="debit" style="display: none;">   -->
		                            <div id="debit">
                                    
                                    <table border="0" cellspacing="0" cellpadding="0">
                                      <html:checkbox property ="atmLink" />
                                     <bean:message key ="applicationform.atmlinkrequird"/></strong></td>
                                    </tr>
                                  <tr bordercolor="#111111">
                                    <td height="25" nowrap>
                                      <tr bordercolor="#111111">
                                        <td class="desc_cell"><bean:message key ="applicationform.savingaccount"/></td>
                                        <td><html:text property="savingAccount" size="20"/></td>
                                      </tr>
                                      <tr bordercolor="#111111">
                                        <td class="desc_cell"><bean:message key ="applicationform.checkingaccount"/></td>
                                        <td><html:text property="checkingAccount" size="20"/></td>
                                      </tr>
                                      <tr>
                                      	<td nowrap class="desc_cell" colspan="2">
                                      		<strong>
                                     			<bean:message key ="applicationform.defaultaccount"/>
                                     		</strong>
                                     	</td>
                                      </tr>
                                      <tr>
                                      	<td nowrap class="desc_cell" colspan="2">
                                      		<html:radio property="defaultAccount" value="S" /> 
											<bean:message key="applicationform.savingaccount" />
											
											<html:radio property="defaultAccount" value="C" /> 
											<bean:message key="applicationform.checkingaccount" />
                                     	</td>
                                      </tr>
                                       <!-- DIV END HERE --> 
								 <%--} --%> 
                                    </div>
                                    </table></td>
                                  </tr>

                              </table></td>
                            </tr>
                          </tbody>
                        </table></td>
                      </tr>
                    </table>   
					</div>
				  <!-- tab end -->
				  
				  <!-- tab 3 -->                 
				  <div id="tab3">
                    <table width="100%" border="0" cellspacing="0" cellpadding="0">
	                  	<tr>
	                  		<td style="padding: 20px 20px 10px 20px;">
	                  			<table cellspacing=0 cellpadding=0 border=0>
		                  			<tbody>
		                  				<tr>
		                  					<td style="width: 15px;"></td>
		                  					<td class="desc_cell" nowrap>
			                  					<b><bean:message key="customerscreen.cardnumber"/></b>
			                  				</td>
			                  				<td style="width: 5px;"></td>
			                  				<td class="desc_cell" nowrap>
			                  					<b><bean:message key="customerscreen.expiredate"/></b>
			                  				</td>
		                  				</tr>
			                  			<logic:iterate id="suppCard" name ="customerScreenProcessForm" property="suppCards">
			                  			<tr>
			                  				<td style="width: 15px;">
									            <bean:define id="cNo">
									            	<bean:write name ="suppCard"  property="cardNumber" />
									            </bean:define>
									            <input type="radio" name="cardNumber" onclick="cardNoUpdate(<%=(String)cNo%>);" />
			                  				</td>
			                  				<td class="desc_cell" nowrap>
			                  					<bean:write name ="suppCard"  property="cardNumber" />
			                  				</td>
			                  				<td style="width: 5px;"></td>
			                  				<td class="desc_cell" nowrap>
			                  					<bean:write name ="suppCard"  property="cardExpDate" />
			                  				</td>
			                  			</tr>
			                  			</logic:iterate>
		                  			</tbody>
	                  			</table>
	                  		</td>
	                  	</tr>
                      <tr>
                        <td width="48%" valign="top" style="padding: 20px 20px 10px 20px;"><table cellspacing=0 cellpadding=0 
                              border=0>
                          <tbody>
                            <tr>
                              <td><table cellspacing=0 cellpadding=0 border=0>
                                  <tbody>
                                    <tr>
                                      <td><img height=19 alt="" src="images/tab_g.gif" width=8 border=0></td>
                                      <td class=group_title background=images/tab_fond.gif><bean:message key ="customerscreen.tab3.appdetails"/></td>
                                      <td><img height=19 alt="" src="images/tab_d.gif" width=8 border=0></td>
                                    </tr>
                                  </tbody>
                              </table></td>
                            </tr>
                            <tr>
                              <td bgcolor=#dce5ea><div 
                                style="FONT-SIZE: 1px">&nbsp;</div></td>
                            </tr>
                            <tr>
                              <td height="21"><table border="0" cellpadding="0" cellspacing="0" width="264" height="144">
                                 <tr bordercolor="#111111">
                                    <td width="132" class="desc_cell"><bean:message key ="applicationform.customername"/></td>
                                    <td><html:text property="supplCustomerName" styleId="supplCustomerName" size="20"/></td>
                                  </tr>
                                  <tr bordercolor="#111111">
                                    <td class="desc_cell"><bean:message key ="applicationform.surname"/></td>
                                    <td><html:text property="supplSurName" styleId="supplSurName" size="20"/></td>
                                  </tr>
                                  <tr bordercolor="#111111">
                                    <td class="desc_cell"><bean:message key ="applicationform.embossingName"/></td>
                                    <td><html:text property="supplEmbossingName" styleId="supplEmbossingName" size="20"/></td>
                                  </tr>
                                  <tr bordercolor="#111111">
                                    <td class="desc_cell"><bean:message key ="applicationform.dob"/></td>
                                    <td><html:text property="strSupplDob" styleId="strSupplDob" size="20"/></td>
                                  </tr>
                                  <tr bordercolor="#111111">
                                    <td class="desc_cell"><bean:message key ="applicationform.pob"/></td>
                                    <td><html:text property="supplPob" styleId="supplPob" size="20"/></td>
                                  </tr>
                                  <tr bordercolor="#111111">
                                    <td width="132" class="desc_cell"><bean:message key ="applicationform.gender"/></td>
                                    <td><html:select property="supplGender" styleId="supplGender" >
								         <html:option value=""></html:option>
								          <html:optionsCollection property="genderList" value="key" label="value" />
								       </html:select>              </td>
								   </tr>
                                  <tr bordercolor="#111111">
                                    <td class="desc_cell"><bean:message key ="applicationform.maritalstatus"/></td>
                                    <td><html:select property="supplMaritalStatus" styleId="supplMaritalStatus" >
                                          <html:option value=""></html:option>
                                          <html:optionsCollection property="maritalStatusList" value="key" label="value" />
								      </html:select></td>
                                  </tr>
                                  <tr bordercolor="#111111">
                                    <td class="desc_cell"><bean:message key ="applicationform.idnumber"/> </td>
                                    <td><html:text property="supplIdNumber" styleId="supplIdNumber" size="20"/></td>
                                  </tr>
                                  <tr bordercolor="#111111">
                                    <td class="desc_cell"><bean:message key ="applicationform.iddate"/></td>
                                    <td><html:text property="strSupplIdDate" styleId="strSupplIdDate" size="20"/></td>
                                  </tr>
                                  <tr bordercolor="#111111">
                                    <td class="desc_cell"><bean:message key ="applicationform.expdate"/> </td>
                                    <td><html:text property="strSupplIdExpire" styleId="strSupplIdExpire" size="20"/></td>
                                  </tr>
                                  <tr bordercolor="#111111">
                                    <td class="desc_cell"><bean:message key ="applicationform.idplace"/></td>
                                    <td><html:text property="supplIdPlace" styleId="supplIdPlace" size="20"/></td>
                                  </tr>
                                  
                                  <tr bordercolor="#111111">
                                    <td class="desc_cell"><bean:message key ="applicationform.nationality"/> </td>
                                    <td><html:text property="supplNationality" styleId="supplNationality" size="20"/></td>
                                   </tr>
                                   <tr bordercolor="#111111">
                                    <td class="desc_cell"><bean:message key ="applicationform.email"/></td>
                                    <td><html:text property="supplEmail" styleId="supplEmail" size="20"/></td>
                                  </tr>
                                  <tr bordercolor="#111111">
                                    <td class="desc_cell"><bean:message key ="applicationform.income"/></td>
                                    <td><html:text property="supplIncome" styleId="supplIncome" size="20"/></td>
                                  </tr>
                                  <tr bordercolor="#111111">
                                    <td nowrap class="desc_cell"><bean:message key ="applicationform.relationtocardholder"/></td>
                                    <td><html:select property="relationShip" styleId="relationShip" >
                                        <html:option value=""></html:option>
                                         <html:optionsCollection property="relationshipList" value="key" label="value" />
								   </html:select>
                                    </td>
                                  </tr>
                                  
                              </table></td>
                            </tr>
                          </tbody>
                        </table></td>
                          <!-- Supplemetnatry cardholder address -->
                          
                         <td valign="top" style="padding: 20px 20px 10px 20px;"><table width="335" 
                              border=0 cellpadding=0 cellspacing=0>
                          <tbody>
                            <tr>
                              <td width="335"><table cellspacing=0 cellpadding=0 border=0>
                                  <tbody>
                                    <tr>
                                      <td><img height=19 alt="" src="images/tab_g.gif" width=8 border=0></td>
                                      <td class=group_title background=images/tab_fond.gif><bean:message key ="customerscreen.tab3.adddetails"/></td>
                                      <td><img height=19 alt="" src="images/tab_d.gif" width=8 border=0></td>
                                    </tr>
                                  </tbody>
                              </table></td>
                            </tr>
                            <tr>
                              <td bgcolor=#dce5ea><div 
                                style="FONT-SIZE: 1px">&nbsp;</div></td>
                            </tr>
                            <tr>
                              <td height="159"><table border="0" cellpadding="0" cellspacing="0" width="264" height="109">
                                  <tr>
                                    <td class="desc_cell" nowrap><bean:message key ="applicationform.address1"/></td>
                                    <td><html:text property="supplementaryAddress.address1" styleId="supplementaryAddressaddress1" size="20"/></td>
                                  </tr>
                                  <tr>
                                    <td class="desc_cell" nowrap><bean:message key ="applicationform.address2"/> </td>
                                    <td><html:text property="supplementaryAddress.address2" styleId="supplementaryAddressaddress2" size="20"/></td>
                                  </tr>
                                  <tr>
                                    <td class="desc_cell" nowrap><bean:message key ="applicationform.country"/></td>
                                    <td>
                                    <html:select property="supplementaryAddress.country" styleId="supplementaryAddresscountry" >
								   	   <html:option value=""></html:option>	
								      	<html:optionsCollection property="countryList" value="key" label="value" />
								    </html:select></td>
                                  </tr>
                                  <tr>
                                    <td class="desc_cell" nowrap><bean:message key ="applicationform.state"/></td>
                                    <td><html:text property="supplementaryAddress.state" styleId="supplementaryAddressstate" size="20"/></td>
                                  </tr>
                                  <tr>
                                    <td class="desc_cell" nowrap><bean:message key ="applicationform.city"/></td>
                                    <td><html:text property="supplementaryAddress.city" styleId="supplementaryAddresscity" size="20"/></td>
                                  </tr>
                                  <tr>
                                    <td class="desc_cell" nowrap><bean:message key ="applicationform.township"/></td>
                                    <td><html:text property="supplementaryAddress.township" styleId="supplementaryAddresscity" size="20"/></td>
                                  </tr>
                                  <tr>
                                    <td class="desc_cell" nowrap width="132"><bean:message key ="applicationform.postalcode"/> </td>
                                    <td><html:text property="supplementaryAddress.postalCode" styleId="supplementaryAddresspostalCode" size="20"/></td>
                                  </tr>
                                  <tr>
                                    <td class="desc_cell"><bean:message key ="applicationform.phone"/></td>
                                    <td><html:text property="supplementaryAddress.phone" styleId="applicationformphone" size="20"/></td>
                                  </tr>
                                  <tr>
                                    <td class="desc_cell"><bean:message key ="applicationform.fax"/></td>
                                    <td><html:text property="supplementaryAddress.fax" styleId="supplementaryAddressfax" size="20"/></td>
                                  </tr>
                           
                              </table></td>
                            </tr>
                          </tbody>
                      </table></td>
                      
                      </tr>
                    </table>
					</div>
					<!-- tab 3 end -->
					
					<!-- tab 4 -->
					<div id="tab4">
                    <!-- Account Details Address -->
                    <table border="0" cellspacing="0" cellpadding="0">
                      <tr>
                        <td valign="top" style="padding: 20px 20px 10px 20px;">
                        	<table cellspacing=0 cellpadding=0 border=0>
                            <tbody>
                              <tr>
                                <td>
                                <table cellspacing=0 cellpadding=0 border=0>
                                    <tbody>
                                      <tr>
                                        <td><img height=19 alt="" src="images/tab_g.gif" width=8 border=0></td>
                                        <td class=group_title background=images/tab_fond.gif><bean:message key ="applicationprocess.accountdetails1"/></td>
                                        <td><img height=19 alt="" src="images/tab_d.gif" width=8 border=0></td>
                                      </tr>
                                    </tbody>
                                </table></td>
                              </tr>
                              <tr>
                                <td bgcolor=#dce5ea><div style="FONT-SIZE: 1px">&nbsp;
                                </div></td>
                              </tr>
                              <tr>
                                <td><table border="0" cellpadding="0" cellspacing="0" width="264">
                                    <tr bordercolor="#111111">
                                      <td class="desc_cell"><bean:message key ="applicationprocess.currency"/></td>
                                      <td>
                                      <html:select property="currencyCode" >
									       <html:optionsCollection property="currencyList" value="key" label="value" />
									  </html:select></td>
									</tr>
                                    <tr bordercolor="#111111">
                                      <td class="desc_cell"><bean:message key ="applicationprocess.creditlimit"/></td>
                                      <td><html:text property="creditLimit" size="20"/></td>
                                    </tr>
                                    <tr bordercolor="#111111">
                                      <td class="desc_cell"><bean:message key ="applicationprocess.limitused"/></td>
                                      <td><html:text property="limitUsed" size="20"/></td>
                                    </tr>
                                    <tr bordercolor="#111111">
                                      <td class="desc_cell"><bean:message key ="customerrelation.avabalance"/></td>
                                      <td><html:text property="availableBalance" size="20"/></td>
                                    </tr>
                                    <tr bordercolor="#111111">
                                      <td class="desc_cell"><bean:message key ="applicationprocess.saleused"/></td>
                                      <td><html:text property="purchasedUsed" size="20"/></td>
                                    </tr>
                                    <tr bordercolor="#111111">
                                      <td class="desc_cell"><bean:message key ="applicationprocess.cashused"/></td>
                                      <td><html:text property="cashUsed" size="20"/></td>
                                    </tr>
                                    <tr bordercolor="#111111">
                                      <td style="height: 30px;" colspan="2"></td>
                                    </tr>
                                    <tr bordercolor="#111111">
                                      <td class="desc_cell"><bean:message key ="applicationprocess.nonreconamt"/></td>
                                      <td>
                                      	<a href="javascript:viewAllnonRecontransactions('tranxEnquiryListNonReconTranxlog.do?accountId=<bean:write name ="customerScreenProcessForm" property="accountId" />')">
                                      	<bean:write name="customerScreenProcessForm" property="nonReconTranxAmt"/>
                                      	</a>
                                      </td>
                                    </tr>
                                    <tr bordercolor="#111111">
                                      <td colspan="2" >
                                      	<a href="javascript:viewAllnonRecontransactions('tranxEnquiryListNonReconTranxlog.do?accountId=<bean:write name ="customerScreenProcessForm" property="accountId" />')">
                                      	view transactions
                                      	</a>
                                      </td>
                                    </tr>
                                </table></td>
                              </tr>
                            </tbody>
                        </table>
                        </td>
                        <td valign="top" style="padding: 20px 20px 10px 20px;" colspan="2">
                        	<table cellspacing=0 cellpadding=0 border=0>
                            <tbody>
                              <tr>
                                <td>
                                <table cellspacing=0 cellpadding=0 border=0>
                                    <tbody>
                                      <tr>
                                        <td><img height=19 alt="" src="images/tab_g.gif" width=8 border=0></td>
                                        <td class=group_title background=images/tab_fond.gif><bean:message key ="applicationprocess.accountdetails2"/></td>
                                        <td><img height=19 alt="" src="images/tab_d.gif" width=8 border=0></td>
                                      </tr>
                                    </tbody>
                                </table></td>
                              </tr>
                              <tr>
                                <td bgcolor=#dce5ea><div 
                                style="FONT-SIZE: 1px">&nbsp;</div></td>
                              </tr>
                              <tr>
                                <td><table border="0" cellpadding="0" cellspacing="0" width="264" >
                                    
                                    <!-- 
                                    <tr bordercolor="#111111">
                                      <td class="desc_cell"><bean:message key ="applicationprocess.amountpertranx"/></td>
                                      <td><html:text property="amtPerTranx" size="20"/></td>
                                    </tr>
                                    <tr bordercolor="#111111">
                                      <td class="desc_cell"><bean:message key ="applicationprocess.dailylimitcount"/></td>
                                      <td><html:text property="dailyLimitCount" size="20"/></td>
                                    </tr>
                                    <tr bordercolor="#111111">
                                      <td class="desc_cell"><bean:message key ="applicationprocess.dailylimitamount"/></td>
                                      <td><html:text property="dailyLimitAmt" size="20"/></td>
                                    </tr>
                                    <tr bordercolor="#111111">
                                      <td class="desc_cell"><bean:message key ="applicationprocess.monthlylimitcount"/></td>
                                      <td><html:text property="monthlyLimitCount" size="20"/></td>
                                    </tr>
                                    <tr bordercolor="#111111">
                                      <td class="desc_cell"><bean:message key ="applicationprocess.monthlylimitamount"/></td>
                                      <td><html:text property="monthlyLimitAmt" size="20"/></td>
                                    </tr>
                                     -->
                                    <tr bordercolor="#111111">
                                      <td class="desc_cell"><bean:message key ="applicationprocess.cycle"/></td>
                                      <td>
                                      <html:select property="cycleNo" >	
			      	                        <html:optionsCollection property="cycleNoList" value="key" label="value" />
			  	                     </html:select>
                                     </td>
                                    </tr>
                                    
                                </table></td>
                              </tr>
                            </tbody>
                        </table>
                        </td>
                      </tr>
                      <tr>
                      	<td colspan="3" valign="top" style="padding: 20px 20px 10px 20px;">
                      		<logic:present name="SEARCHLIST" scope="request"> 
			   				<table class="wrapper" style="BORDER-COLLAPSE: collapse" bordercolor=#111111 cellspacing=0 cellpadding=0 border=0>
								<tr>
									<td nowrap class="title"><bean:message key ="customerscreen.cardnumber"/></td>
									<!-- <td nowrap class="title"><bean:message key ="customerscreen.expiredate"/></td>  -->
									<td nowrap class="title"><bean:message key ="customerscreen.cardstatus"/></td>
									<!-- <td nowrap class="title"><bean:message key ="customerscreen.ecom"/></td>  -->
									<td nowrap class="title"><bean:message key ="customerscreen.status"/></td>
									<td nowrap class="title"><bean:message key ="customerscreen.chtdes"/></td>
									<td nowrap class="title"><bean:message key ="customerscreen.cused"/></td>
									<td nowrap class="title"><bean:message key ="customerscreen.pused"/></td>
									<td nowrap class="title"><bean:message key ="customerscreen.pavademo"/></td>
									<td nowrap class="title"><bean:message key ="customerscreen.pavaint"/></td>
									<td nowrap class="title"><bean:message key ="customerscreen.cavademo"/></td>
									<td nowrap class="title"><bean:message key ="customerscreen.cavaint"/></td>
								</tr>
								<logic:iterate id ="commObj" name ="SEARCHLIST">
								<tr>
									<td nowrap class="DataTD"><bean:write name="commObj" property="cardNumber"/> </td>
									<!-- <td nowrap class="DataTD"><bean:write name="commObj" property="expDate"/> </td>  -->
									<td nowrap class="DataTD"><bean:write name="commObj" property="statDes"/> </td>
									<!-- <td nowrap class="DataTD"><bean:write name="commObj" property="ecom"/> </td>  -->
									<td nowrap class="DataTD"><bean:write name="commObj" property="status"/> </td>
									<td nowrap class="DataTD"><bean:write name="commObj" property="chtDes"/> </td>
									<td nowrap class="DataTD"><bean:write name="commObj" property="cashUsed"/> </td>
									<td nowrap class="DataTD"><bean:write name="commObj" property="purchaseUsed"/> </td>
									<td nowrap class="DataTD"><bean:write name="commObj" property="purchaseAvaDome"/> </td>
									<td nowrap class="DataTD"><bean:write name="commObj" property="purchaseAvaInt"/> </td>
									<td nowrap class="DataTD"><bean:write name="commObj" property="cashAvaDemo"/> </td>
									<td nowrap class="DataTD"><bean:write name="commObj" property="cashAvaInt"/> </td>
								</tr>
								</logic:iterate>
							</table>
							</logic:present>
                      	</td>
                      </tr>
                      
                    </table>
					</div>
					<!-- tab 4 end -->
					
					<!-- tab 5 -->
					<div id="tab5">
                    <table width="100%" border="0" cellpadding="0" cellspacing="0">
                      <tr>
                        <td valign="top" style="padding: 20px 20px 10px 20px;">
                    <logic:present name="CUSTOMERHISTORYLIST" scope="request">  
                        <table cellspacing=0 cellpadding=0   border=0>
                            <tbody>
                              <tr>
                                <td><table cellspacing=0 cellpadding=0 border=0>
                       
                                    <tbody>
                                      <tr>
                                        <td><img height=19 alt="" src="images/tab_g.gif" width=8 border=0></td>
                                        <td class=group_title background=images/tab_fond.gif><bean:message key ="applicationprocess.history"/></td>
                                        <td><img height=19 alt="" src="images/tab_d.gif" width=8 border=0></td>
                                      </tr>
                                    </tbody>
                                </table></td>
                              </tr>
                              <tr>
                                <td bgcolor=#dce5ea><div 
                                style="FONT-SIZE: 1px">&nbsp;</div></td>
                              </tr>
      
                              <tr>
                                <td height="19">
  
	   <form name ="listForm">
  <logic:iterate id ="commObj" name ="CUSTOMERHISTORYLIST">
  	
	   <bean:define id="sno" name="commObj" property="column1"/>
	   <bean:define id="cardType" name="commObj" property="column3"/>
	   
	   <tr>
         <td class="DataTD" nowrap><bean:write name ="commObj" property="column1" /></td>
         <td class="DataTD" nowrap><bean:write name ="commObj" property="column2" /></td>
         <td class="DataTD" nowrap><bean:write name ="commObj" property="column3" /></td>
         <td class="DataTD" nowrap><html:button property="" value="view" onclick='<%= "javascript:getCardDetails('"+sno+"','"+cardType+"');"%>' /> </td>
         
        </tr>
  </logic:iterate>
  
	   </form>
  
                  </td>  
                     </tr>
                      </tbody>
                        </table>
      </logic:present>            
                        </td>

                      </tr>
                    </table>
					</div>
					<!-- tab 5 end -->
					
					<div id="noDetails" style="padding-left: 20px;display: none;"> </div>
					
					<div id="details" style="padding-left: 20px;display: none;">
					<table>
						<tr>
							<td class="DataTD">Statement ID :: </td> <td><div id="statId"></div></td>
						</tr>
						<tr>
							<td class="DataTD">Card No :: </td> <td><div id="cardNo"></div></td>
						</tr>
						<tr>
							<td class="DataTD">Previous Statement ID ::</td> <td><div id="prevStatId"></div></td>
						</tr>
						<tr>
							<td class="DataTD">Previous Statement Amt ::</td> <td><div id="prevStatAmt"></div></td>
						</tr>
						<tr>
							<td class="DataTD">Previous Statement Outstanding Amt ::</td> <td><div id="prevStatOutAmt"></div></td>
						</tr>
						<tr>
							<td class="DataTD">Statement Amt ::</td> <td><div id="statAmt"></div></td>
						</tr>
						<tr>
							<td class="DataTD">Statement Min Amt ::</td> <td><div id="statMinAmt"></div></td>
						</tr>
						<tr>
							<td class="DataTD">Statement Due Date ::</td> <td><div id="statDueDate"></div></td>
						</tr>
						<tr>
							<td class="DataTD">Statement Fee Amt ::</td> <td><div id="statFeeAmt"></div></td>
						</tr>
						<tr>
							<td class="DataTD">Statement Interest Amt ::</td> <td><div id="statIntAmt"></div></td>
						</tr>
						<tr>
							<td class="DataTD">Statement Created Date ::</td> <td><div id="statCreatedDate"></div></td>
						</tr>
					</table>
					</div>
                   
                  </td>
                  <td background="images/tbl_d.gif"></td>
                </tr>
                
                                
                <tr> 
                  <td background="images/tbl_g.gif"></td>
                  <td class="form_bgcolor"  colspan="2" > 
                  <div align="right">
                    	<logic:equal value="A" property="status" name="customerScreenProcessForm">
                    	<% if(objUserAccess.hasFunction("CUSTOMER_SCREEN", "CUSTOMER_SCREEN_SETUP_ADD")){ %>
					    <html:submit onclick="go('csupdate')"><bean:message key ="common.save"/></html:submit>
					    <% } %>
					    </logic:equal>
        				<html:cancel onclick ="showList()"><bean:message key ="common.cancel"/></html:cancel>
                    </div>
                  </td>
                  <td background="images/tbl_d.gif"></td>
                </tr>
                <tr> 
                  <td> <img src="images/tbl_bas_g.gif" border="0" alt="" width="5" height="5"></td>
                  <td background="images/tbl_bas.gif"></td>
                  <td background="images/tbl_bas.gif"></td>
                  <td> <img src="images/tbl_bas_d.gif" border="0" alt="" width="5" height="5"></td>
                </tr>
              </table>
          </td>
        </tr>
      </table>
    </td>
  </tr>
 </table>
</html:form>
 </body>
</html:html>
  	 