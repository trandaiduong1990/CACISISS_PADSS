<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@page import ="java.util.*" %>
<html:html>
<head>
<title><bean:message key ="applicationform.screentitle"/></title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="inc/css/style.css" type="text/css">
<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/engine.js'></script>
<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/util.js'></script>
<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/interface/DWRUtils.js'></script>
<script language="JavaScript" src="/inc/js/tabdata.js"></script>
<script language="JavaScript" src="inc/js/jquery-1.6.3.js"></script>
<script language="JavaScript" src="inc/js/jquery.min.js"></script>
<script language="JavaScript" src="inc/js/cacis.js"></script>
<script language="JavaScript" src="inc/js/decimalNo.js"></script>
<script language="JavaScript" src="inc/js/ApplicationForm.js"></script>
<script language="JavaScript" src="inc/js/browserDetect.js"></script>
<%String cardProductName=""; %>
</head>
<script>
 	function go(action) {
 	  document.forms[0].method.value = action;
 	  if(action == "addNew"){
 	 	  	document.forms[0].submit();
 	  }else{
 	 	  if(onFormSub(document.forms[0])){
	 	 	  if(dateValidationOnApplicationFormSubmit(document.forms[0])){
	 	 	  	document.forms[0].submit();
	 	 	  }
 	 	  }
 	  }
	 }
	 
	function showList(){
          document.forms[0].action= "applicationformlist.do?method=List";
          document.forms[0].submit();
       }
        
</script>
<script type="text/javascript">
//<!--
function switchMenu(obj) {
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
	document.getElementById('tab3end').src="images/form_tab_end_off.gif";
	
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
		document.getElementById('tab3end').src="images/form_tab_end_on.gif";
	}
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
function pageLoad() {
	collapseAll($('tab1','tab2','tab3'));
}



function updateRadio(){
	//alert("Update Radio");
	 var name = dwr.util.getValue("selectedAppCardProducts");
	 var creditdiv = document.getElementById("credit");
	 var debitdiv = document.getElementById("debit");
	  
	  //alert("Selected Card Product Radio : "+name);
	  if(name == 3){
		  //alert("debit");
		  creditdiv.style.display='none';
		  //document.getElementById('credit').style.visibility ='show';
		  debitdiv.style.display ='block';
		  //document.getElementById("credit").style.visibility ='hidden';
		  
		 
		      
		    }
	  else{
		  //alert("credit");
		  creditdiv.style.display='block';
		  debitdiv.style.display ='none';
		  //document.getElementById('credit').style.visibility ='hidden';
		  
	  }
	
}
//-->

var embossLength = 0;

function update() {
	//alert("Update");
  var name = dwr.util.getValue("selectedAppCardProducts");
  
  //alert("Selected Card Product Radio : "+name);
  
  
  
  if(name != false){
	DWRUtils.decideCardProductType(name, function(data) {
	//alert("data : "+data);
	var mySplitResult = data.split("#");
	var length = '(Max. '+mySplitResult[1]+')';
	
	//alert(embossLength);
	// assign to global variable
	embossLength = parseInt(mySplitResult[1]);
	//alert(length);
	//alert(embossLength);
	//alert(mySplitResult[0]);
	field = document.getElementById('embossingName');
	field.maxLength=embossLength;
	
	    /*if(mySplitResult[0] == 'Dhide'){
	        dwr.util.setValue("demoReply", "<bean:message key ='alert.noneedsuppdetailsdebitcard'/>");
	        document.getElementById('suppDetails').onclick = function(){return false;};
	        //document.anchors["suppDetails"].removeAttribute("href");
	        //document.anchors('suppDetails').removeAttribute("href");
	    	if(BrowserDetect.browser == 'Explorer'){
	    		document.anchors('suppDetails').removeAttribute("href");
	    	}else{
	    		document.anchors["suppDetails"].removeAttribute("href");
	    	}
	        dwr.util.setValue("demoReply1", length);
	    }else */
	   if(mySplitResult[0] == 'Ahide'){
	        dwr.util.setValue("demoReply", "<bean:message key ='alert.noneedsuppdetailsatmcard'/>");
	        document.getElementById('suppDetails').onclick = function(){return false;};
	        //document.anchors["suppDetails"].removeAttribute("href");
	        //document.anchors('suppDetails').removeAttribute("href");
	        if(BrowserDetect.browser == 'Explorer'){
	    		document.anchors('suppDetails').removeAttribute("href");
	    	}else{
	    		document.anchors["suppDetails"].removeAttribute("href");
	    	}
	        dwr.util.setValue("demoReply1", length);
	    }else{
	    	dwr.util.setValue("demoReply", "");
	        //document.anchors["suppDetails"].setAttribute('href','javascript:switchMenu(\'tab3\')');
	        if(BrowserDetect.browser == 'Explorer'){
	    		document.anchors('suppDetails').setAttribute('href','javascript:switchMenu(\'tab3\')');
	    	}else{
	    		document.anchors["suppDetails"].setAttribute('href','javascript:switchMenu(\'tab3\')');
	    	}
	        document.getElementById('suppDetails').onclick = function(){return true;};
	        dwr.util.setValue("demoReply1", length);
	        dwr.util.setValue("demoReply11", length);
	    }
	  });
  }
}

function countryUpdate(type) {

	var country = dwr.util.getValue(type+"Address.country");
	stateUpdate(country, type);
	
}

function updateMM(){

	var hac = dwr.util.getValue("homeAddress.country");

	if(hac == ''){

		dwr.util.setValue("homeAddress.country", 'MM');
		stateUpdate('MM', 'home');
		
		dwr.util.setValue("companyAddress.country", 'MM');
		stateUpdate('MM', 'company');
		
		dwr.util.setValue("supplementaryAddress.country", 'MM');
		stateUpdate('MM', 'supplementary');
		
	}
	
}

function countCharacters(id,myelement)  
{  
	field = document.getElementById(myelement);  
	//alert(field);
	if(embossLength == 0){
		alert("<bean:message key ='alert.productselect'/>");
		field.value = field.value.substring(0, embossLength);
	}else{
	//alert('test');
	counter = document.getElementById(id);  
	//alert(counter);
	fieldfield_length = field.value.length;  
	//alert(fieldfield_length); 
	//alert(embossLength);
	if (parseInt(fieldfield_length) <= embossLength)
		{     
		// Here we Calculate remaining characters    
		remaining_characters = embossLength-fieldfield_length;  
		//alert(remaining_characters);   
		// Now Update the counter on the page   
		counter.innerHTML = '(Left. '+remaining_characters+')';;  
	  }else{
		  field.value = field.value.substring(0, embossLength);
	  }  
	}
 }

function checkCommon(exby){

	var res = 'block';
	
	if (exby.checked) {
		//alert('checked');
		res = 'block';
	} else {
		//alert('Unchecked');
		res = 'none';
	}
	
	var ele = document.getElementById("suppPartOne");
	if(ele != null){
		ele.style.display = res;
	}

	var ele2 = document.getElementById("suppPartTwo");
	if(ele2 != null){
		ele2.style.display = res;
	}

	var ele3 = document.getElementById("suppPartThree");
	if(ele3 != null){
		ele3.style.display = res;
	}

	var ele4 = document.getElementById("suppStyle");
	if(ele4 != null){
		ele4.style.display = res;
	}
}

function onLoadCheckBox(theForm){
	//alert('test');
	//alert(document.applicationSetupForm.supplCardRequired.checked);
	var res = 'block';
	if(document.applicationSetupForm.supplCardRequired.checked == false){
		//alert('Unchecked');
		res = 'none';
	} else {
		//alert('checked');
		res = 'block';
	}

	var ele = document.getElementById("suppPartOne");
	if(ele != null){
		ele.style.display = res;
	}

	var ele2 = document.getElementById("suppPartTwo");
	if(ele2 != null){
		ele2.style.display = res;
	}

	var ele3 = document.getElementById("suppPartThree");
	if(ele3 != null){
		ele3.style.display = res;
	}

	var ele4 = document.getElementById("suppStyle");
	if(ele4 != null){
		ele4.style.display = res;
	}
	
}

function accRejDelete(method) 
{
  document.forms[0].method.value=method;
  var r=confirm("<bean:message key ='areyousure'/>");
  if (r==true)
  {
	document.forms[0].submit();
  }
}

function key(e) {
    if (e.keyCode == 13) {
        alert('test');
    	return false;
    }
}

function KeyPress() 
{
//alert(window.event.keyCode)
if (window.event.keyCode == 13)
window.event.keyCode =0;
}

</script>

<body onLoad="switchMenu('tab1');onLoadCheckBox(this);update();updateMM();updateRadio();" onkeypress="return disableEnterKey(event);">
  <html:form action="applicationformprocess.do">
  <input type="hidden" name="method"/>
  <html:hidden property="issuerId" value="<%=(String)session.getAttribute("ISSUER")%>"/>
  <html:hidden property="userId" value="<%=(String)session.getAttribute("USERID")%>"/>
   <html:hidden property="applicationId" />
   <html:hidden property="applicationStatus" />
   
   <table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr> 
      <td> 
        <table border="0" cellspacing="0" cellpadding="0">
               	
        <tr> 
            <td class="titreSection" width="519"><bean:message key ="applicationform.screentitle"/> </td>
            <td class="titreSection" width="484">&nbsp; </td>
        </tr>
        
        <tr> 
            <td background="images/ligne.gif" colspan="3"> <img src="images/spacer.gif" width="1" height="1"></td>
        </tr>
        <tr> 
            <td class="titreSection" width="519">&nbsp;<html:button property="submitbutton"  onclick ="go('addNew')"><bean:message key ="common.addnew"/></html:button></td>
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
          <td width="482" valign="top"> 
              <table width="482" border="0" align="left" cellpadding="0" cellspacing="0">
                <tr> 
                  <td><img id="tab1start" src="images/form_tab_start_off.gif" border="0" alt="" width="5" height="22"></td>
                  <td background="images/tbl_haut.gif" colspan="2"> 
                    <table border="0" cellpadding="0" cellspacing="0">
                      <tr> 
                        <td id="tab1bg" background="images/form_tab_bg_off.gif" class="form_tab_off" style="padding-left:15px">
                        	<a href="javascript:switchMenu('tab1')" ><bean:message key ="applicationform.tab1"/></a>
                        </td>
                        <td><img id="tab12dif" src="images/form_tab_btw_off_off.gif" width="25" height="22"></td>
                        <td id="tab2bg" background="images/form_tab_bg_off.gif" class="form_tab_off">
                        	<a href="javascript:switchMenu('tab2')"><bean:message key ="applicationform.tab2"/></a>
                        </td>
                        <td><img id="tab23dif" src="images/form_tab_btw_off_off.gif" width="25" height="22"></td>
                        <td id="tab3bg" background="images/form_tab_bg_off.gif" class="form_tab_off">
                        	<a name="suppDetails" id="suppDetails" href="javascript:switchMenu('tab3')"><bean:message key ="applicationform.tab3"/></a>
                        </td>
                        <td><img id="tab3end" src="images/form_tab_end_off.gif" width="25" height="22"></td>
                      </tr>
                    </table>                  </td>
                  <td> <img src="images/tbl_haut_d.gif" border="0" alt="" width="5" height="22"></td>
                </tr>
                <tr> 
                  <td background="images/tbl_g.gif"></td>
                  <td class="form_bgcolor"  colspan="2">
                    <div align="right" > 
                    	 <% if(request.getAttribute("ACTION") == null){ %>
                    		 <html:button property="submitbutton" onclick="go('add')" ><bean:message key ="common.save"/></html:button>
                    		 <html:button property="submitbutton" onclick ="showList()"><bean:message key ="common.cancel"/></html:button>
                    	<% }else { %>
	                    	<% if(request.getAttribute("ACTION") != null && ((String)request.getAttribute("ACTION")).equals("add")) { %>
	        					<html:button property="submitbutton" onclick="go('add')" ><bean:message key ="common.save"/></html:button>
	        					<html:button property="submitbutton" onclick ="showList()"><bean:message key ="common.cancel"/></html:button>
	        				 <% }else if(((String)request.getAttribute("ACTION")).equals("change") || ((String)request.getAttribute("ACTION")).equals("update")) { %>
	        				    <logic:equal name="applicationSetupForm" property="applicationStatus" value="0">
		        					<html:button property="submitbutton" onclick="go('update')"><bean:message key ="common.save"/></html:button>
		        					<html:button property="fdelete" onclick="accRejDelete('delete')"><bean:message key ="common.delete"/></html:button>
		                        </logic:equal>
		                        <logic:equal name="applicationSetupForm" property="applicationStatus" value="5">
		        					<html:button property="submitbutton" onclick="go('update')"><bean:message key ="common.save"/></html:button>
		        					<html:button property="fdelete" onclick="accRejDelete('delete')"><bean:message key ="common.delete"/></html:button>
		                        </logic:equal>
	        					<html:button property="submitbutton" onclick ="showList()"><bean:message key ="common.cancel"/></html:button>
	       				     <% }else if(((String)request.getAttribute("ACTION")).equals("cancel")) { %>  	
	        					<html:button property="submitbutton" onclick ="showList()"><bean:message key ="common.cancel"/></html:button>
	        				 <%}%>		
  					  	<%}%>
                    </div>			      </td>
                  <td background="images/tbl_d.gif"></td>
                </tr>
                <tr>
                  <td background="images/tbl_g.gif">
                  </td>
                </tr>
                 <tr>
                  <td height="153" background="images/tbl_g.gif"></td>
                  <td colspan="2" valign="top" class="form_bgcolor">
   <!-- tab 1 -->
   <div id="tab1">
                  <table width="100%" border="0" cellspacing="0" cellpadding="0">
                    <tr>
                      <td colspan="2" style="padding: 20px 20px 10px 20px;">  
                       
                      <table border="0" cellpadding="0" cellspacing="0" width="264" height="36">
                      <tr>
                      	<td colspan="5">
                  			<div id="demoReply" style="padding-bottom: 10px;width: 500px;color: green;height: 10px;"></div>
                      	</td>
                      </tr>
                        <tr>
                     
                          <td width="132" height="18" nowrap class="desc_cell"><bean:message key ="applicationform.branchname"/> * </td>
                          <td class="label">
                     
                              <html:select property="branchId" >
      	                            <html:option value=""></html:option>	
                                    <html:optionsCollection property="branchList" value="key" label="value" />
  	                            </html:select> 
  	                      </td>
  	                       <td>&nbsp;&nbsp;</td>
                          <td height="18" nowrap class="desc_cell"><bean:message key ="applicationform.customertype"/> * </td>
                          <td class="label">
                       
                          <html:select property="customerTypeId" >
      	                        <html:option value=""></html:option>	
      	                        <html:optionsCollection property="customerTypeList" value="key" label="value" />
  	                     </html:select>
  	                 
  	                     </td>
                        </tr>
                         <tr>
                         	<td>&nbsp;&nbsp;
                         	</td>
                         </tr>
                        <tr>
                          <td height="18" nowrap class="desc_cell"><bean:message key ="applicationform.cardproduct"/> * </td>
                          <td colspan="3" class="label">
                          	<table border="0" cellspacing="0" cellpadding="0">
		                    <logic:iterate id="appcardprods" name ="applicationSetupForm" property="appCardProductList">
		                         <%
		                          Map.Entry   objMapEntry =(Map.Entry)pageContext.getAttribute("appcardprods");
		                          //String cardProductName = (String) objMapEntry.getValue();
		                           cardProductName = (String) objMapEntry.getValue();

		                          System.out.println("?????????? CardProductName="+cardProductName);
		                         %>
		                          <%if(cardProductName.startsWith("V")){ %>
		                         <tr>
		                              <td nowrap class="label">
										 <html:radio property="selectedAppCardProducts" value="key" idName="appcardprods" onclick="update();updateRadio()" />
										 <bean:write name="appcardprods" property="value"/>
									 </td>		
							     </tr>	      
		                        <%} %>
							     <%if(cardProductName.startsWith("M")){ %>
		                         <tr>
		                              <td nowrap class="label">
		                              	 <html:radio property="selectedAppCardProducts" value="key" idName="appcardprods" onclick="update()" />
										 <bean:write name="appcardprods" property="value"/>
									 </td>			
							    </tr>	      
		                        <%} %>
								<%if(cardProductName.startsWith("A")){ %>
		                        <tr>
		                              <td nowrap class="label">
										 <html:radio property="selectedAppCardProducts" value="key" idName="appcardprods" onclick="update()" />
										 <bean:write name="appcardprods" property="value"/>
									 </td>			
							    </tr>		      
		                        <%} %>
		                        
								<%if(cardProductName.startsWith("C")){ %>
		                        <tr>
		                              <td nowrap class="label">
										 <html:radio property="selectedAppCardProducts" value="key" idName="appcardprods" onclick="update()" />
										 <bean:write name="appcardprods" property="value"/>
									 </td>			
							    </tr>		      
		                        <%} %>
		                        
								<%if(cardProductName.startsWith("J")){ %>
		                        <tr>
		                              <td nowrap class="label">
										 <html:radio property="selectedAppCardProducts" value="key" idName="appcardprods" onclick="update()" />
										 <bean:write name="appcardprods" property="value"/>
									 </td>			
							    </tr>		      
		                        <%} %>
		                        
		                	</logic:iterate>    
                          	</table>
                          </td>
                          </tr>
                       </table>
                       </td>
                     </tr>
                    <tr>
                     <td valign="top" style="padding: 20px 20px 10px 20px;">
                       <table cellspacing=0 cellpadding=0     border=0>
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
                              <td height="21">
                              <table border="0" cellpadding="0" cellspacing="0" width="297" height="144">
                              	  <tr bordercolor="#111111">
                                    <td width="132" class="desc_cell"><bean:message key ="applicationform.csn"/> * </td>
                                    <td><html:text property="csn" maxlength="20" size="20"/></td>
                                  </tr>
                                  <tr bordercolor="#111111">
                                    <td width="132" class="desc_cell"><bean:message key ="applicationform.customerinitial"/> * </td>
                                    <td><html:text property="customerInitial" maxlength="4" size="20"/></td>
                                  </tr>
                                  <tr bordercolor="#111111">
                                    <td width="132" class="desc_cell"><bean:message key ="applicationform.customername"/> * </td>
                                    <td><html:text property="customerName" maxlength="30" size="20"/></td>
                                  </tr>
                                  <tr bordercolor="#111111">
                                    <td class="desc_cell"><bean:message key ="applicationform.surname"/> * </td>
                                    <td><html:text property="surName" maxlength="30" size="20"/></td>
                                  </tr>
                                  <tr bordercolor="#111111" style="height: 40px;">
                                    <td class="desc_cell"><bean:message key ="applicationform.embossingName"/> * </td>
                                    <td><html:text property="embossingName" styleId="embossingName" maxlength="29" size="20" onkeyup="javascript:countCharacters('demoReply3','embossingName');"/></td>
                                    <td>
                                    	<div id="demoReply1" style="color: green;width: 70px;"></div>
                                    	<div id="demoReply3" style="color: green;width: 70px;"></div>
                                    </td>
                                  </tr>
                                  <tr bordercolor="#111111">
                                    <td class="desc_cell"><bean:message key ="applicationform.dob"/> * </td>
                                    <td><html:text property="strDob" size="20" maxlength="10" onblur="javascript:isValidDate(document.applicationSetupForm.strDob)"/></td>
                                  </tr>
                                  <tr bordercolor="#111111">
                                    <td class="desc_cell"><bean:message key ="applicationform.pob"/></td>
                                    <td><html:text property="pob" size="20" maxlength="30"/></td>
                                  </tr>
                                  
                                  <tr bordercolor="#111111">
                                    <td class="desc_cell"><bean:message key ="applicationform.idnumber"/> * </td>
                                    <td><html:text property="idNumber" size="20"/></td>
                                  </tr>
                                  <tr bordercolor="#111111">
                                    <td class="desc_cell"><bean:message key ="applicationform.iddate"/> * </td>
                                    <td><html:text property="strIdDate" size="20" maxlength="10" onblur="javascript:isValidDate(document.applicationSetupForm.strIdDate)"/></td>
                                  </tr>
                                  
                                  <tr bordercolor="#111111">
                                    <td class="desc_cell"><bean:message key ="applicationform.passport"/> </td>
                                    <td><html:text property="passport" size="20"/></td>
                                  </tr>
                                  <tr bordercolor="#111111">
                                    <td class="desc_cell"><bean:message key ="applicationform.expdate"/> </td>
                                    <td><html:text property="strExpDate" size="20" maxlength="10" onblur="javascript:isValidDate(document.applicationSetupForm.strExpDate)"/></td>
                                  </tr>
                                  <tr bordercolor="#111111">
                                    <td class="desc_cell"><bean:message key ="applicationform.idplace"/></td>
                                    <td><html:text property="idPlace" size="20" maxlength="30"/></td>
                                  </tr>
                                  <tr bordercolor="#111111">
                                    <td class="desc_cell"><bean:message key ="applicationform.nationality"/> * </td>
                                    <td><html:text property="nationality" size="20" maxlength="20"/></td>
                                  </tr>
                                  <tr bordercolor="#111111">
                                    <td class="desc_cell"><bean:message key ="applicationform.billingaddress"/> * </td>
                                    <td nowrap class="label">
                                    	<html:radio property ="billingTo" value ="H"><bean:message key ="applicationform.billaddresidential"/></html:radio>
                                      	<html:radio property ="billingTo" value ="C"><bean:message key ="applicationform.billaddoffice"/></html:radio>
                                     </td>
                                  </tr>
                                  <tr bordercolor="#111111">
                                    <td class="desc_cell"><bean:message key ="applicationform.immediateprocess"/> * </td>
                                    <td nowrap class="label">
                                    	<html:radio property ="immeidateProcess" value ="Y"><bean:message key ="applicationform.immediateprocess.yes"/></html:radio>
                                      	<html:radio property ="immeidateProcess" value ="N"><bean:message key ="applicationform.immediateprocess.no"/></html:radio>
                                     </td>
                                  </tr>
                             </table></td>
                            </tr>
                          </tbody>
                      </table>
               		 </td>
                     <td valign="top" style="padding: 20px 20px 10px 20px;">
                                       
                      <table width="335"   border=0 cellpadding=0 cellspacing=0>
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
                              <td height="159">
        
                              <table border="0" cellpadding="0" cellspacing="0" width="264" height="109">
                                  <tr>
                                    <td class="desc_cell" nowrap><bean:message key ="applicationform.address1"/> * </td>
                                    <td><html:text property="homeAddress.address1" size="20" maxlength="100"/></td>
                                  </tr>
                                  <tr>
                                    <td class="desc_cell" nowrap><bean:message key ="applicationform.address2"/> </td>
                                    <td><html:text property="homeAddress.address2" size="20" maxlength="100"/></td>
                                  </tr>
                                  <tr>
                                    <td class="desc_cell" nowrap><bean:message key ="applicationform.country"/> * </td>
                                    <td>
                                    <html:select property="homeAddress.country" onchange="countryUpdate('home')">
								   	   <html:option value=""></html:option>	
								      	<html:optionsCollection property="countryList" value="key" label="value" />
								    </html:select></td>
                                  </tr>
                                  <tr>
                                    <td class="desc_cell" nowrap><bean:message key ="applicationform.state"/></td>
                                    <td>
                                    	<div id="home_stateText">
                                    		<logic:equal name="applicationSetupForm" property="homeAddress.country" value="MM">
										   	    <html:select property="homeAddress.state" onchange="updateCity(this, 'home')">
											   	    <html:option value=""></html:option>	
											      	<html:optionsCollection property="stateList" value="key" label="value" />
											    </html:select>
											</logic:equal>
											<logic:notEqual  name="applicationSetupForm" property="homeAddress.country" value="MM">
                                    			<html:text property="homeAddress.state" size="20" maxlength="20"/>
											</logic:notEqual >
                                    	</div>
                                    </td>
                                  </tr>
                                  <tr>
                                    <td class="desc_cell" nowrap><bean:message key ="applicationform.city"/> * </td>
                                    <td>
                                    	<div id="home_cityText">
                                    		<logic:equal name="applicationSetupForm" property="homeAddress.country" value="MM">
										   	    <html:select property="homeAddress.city" onchange="updateTownship(this, 'home')">
											   	    <html:option value=""></html:option>	
											      	<html:optionsCollection property="cityList" value="key" label="value" />
											    </html:select>
											</logic:equal>
											<logic:notEqual  name="applicationSetupForm" property="homeAddress.country" value="MM">
                                    			<html:text property="homeAddress.city" size="20" maxlength="20"/>
											</logic:notEqual >
                                    	</div>
                                    </td>
                                  </tr>
                                  <tr>
                                    <td class="desc_cell" nowrap><bean:message key ="applicationform.township"/></td>
                                    <td>
                                    	<div id="home_townshipText">
                                    		<logic:equal name="applicationSetupForm" property="homeAddress.country" value="MM">
										   	    <html:select property="homeAddress.township" >
											   	    <html:option value=""></html:option>	
											      	<html:optionsCollection property="townshipList" value="key" label="value" />
											    </html:select>
											</logic:equal>
											<logic:notEqual  name="applicationSetupForm" property="homeAddress.country" value="MM">
                                    			<html:text property="homeAddress.township" size="20" maxlength="20"/>
											</logic:notEqual >
                                    	</div>
                                    </td>
                                  </tr>
                                  <tr>
                                    <td class="desc_cell" nowrap width="132"><bean:message key ="applicationform.postalcode"/></td>
                                    <td><html:text property="homeAddress.postalCode" size="20" maxlength="8"/></td>
                                  </tr>
                                  <tr>
                                    <td class="desc_cell"><bean:message key ="applicationform.phone"/> * </td>
                                    <td><html:text property="homeAddress.phone" size="20" maxlength="50"/></td>
                                  </tr>
                                  <tr>
                                    <td class="desc_cell"><bean:message key ="applicationform.fax"/></td>
                                    <td><html:text property="homeAddress.fax" size="20" maxlength="50"/></td>
                                  </tr>
                                  <tr>
                                    <td class="desc_cell"><bean:message key ="applicationform.homeTp"/></td>
                                    <td><html:text property="homeAddress.homeTp" size="20" maxlength="12"/></td>
                                  </tr>
                              </table> </td>
                            </tr>
                        </tbody>
                      </table>
                     </td>
                   </tr>
                   <logic:present name="applicationSetupForm" property="appDocProofList" >
		          	<bean:size id="size" name="applicationSetupForm" property="appDocProofList"/>
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
	                                <td>
	                                <table border="0" cellpadding="0" cellspacing="0" width="264" height="36">
	                                  <tr>
	                                    <td width="132" height="18" nowrap class="desc_cell"><bean:message key ="applicationform.docssubmitted"/></td>
	                                    <td class="label">
		                                    <table border="0" cellspacing="0" cellpadding="0">
		                                        <tr>
		                                          <td nowrap class="label">
					                                  <logic:iterate id="appdocproof" name ="applicationSetupForm" property="appDocProofList">
															    <html:multibox property="selectedAppDocuments">
															     <bean:write name="appdocproof" property ="key"/>
															    </html:multibox>
															    <bean:write name="appdocproof" property ="value"/>
													  </logic:iterate>
												 </td>
												</tr>
		                                    </table>
	                                    </td>
	                                  </tr>
	                                  <tr>
	                                    <td height="18" valign="top" nowrap class="desc_cell"><bean:message key ="applicationform.remarks"/></td>
	                                    <td class="label"><html:textarea property="remarks" cols="80" rows="4" /></td>
	                                  </tr>
	                                </table>
	                                </td>
	                              </tr>
	                            </table>
	                            </td>
	                          </tr>
	                        </tbody>
	                      </table>
	                      </td>
	                     </tr>
                	</logic:greaterThan>
                </logic:present> 
                  </table>
                 </div>
		 <!-- Tab 2 -->
		 <div id="tab2">
				  
                    <table width="100%" border="0" cellspacing="0" cellpadding="0">
                      <tr>
                        <td width="48%" rowspan="2" valign="top" style="padding: 20px 20px 10px 20px;"><table cellspacing=0 cellpadding=0 
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
                                    <td><html:text property="jobStatusOthers" size="20" maxlength="15"/></td>
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
                                    <td><html:text property="jobTypeOthers" size="20" maxlength="15"/></td>
                                  </tr>
                                  <tr bordercolor="#111111">
                                    <td class="desc_cell"><bean:message key ="applicationform.income"/> </td>
                                    <td><html:text property="income" size="20" maxlength="11" onblur="javascript:isValidDecimal(this, 8, 2)" /></td>
                                  </tr>
                                  <tr bordercolor="#111111">
                                    <td class="desc_cell"><bean:message key ="applicationform.companyname"/>**</td>
                                    <td><html:text property ="companyName" size ="20" maxlength="30"/></td>
                                  </tr>
                                   <tr>
                                    <td class="desc_cell" nowrap ><bean:message key ="applicationform.address1"/>**</td>
                                    <td><html:text property="companyAddress.address1" size="20" maxlength="100"/></td>
                                  </tr>
                                  <tr>
                                    <td class="desc_cell" nowrap ><bean:message key ="applicationform.address2"/> </td>
                                    <td><html:text property="companyAddress.address2" size="20" maxlength="100"/></td>
                                  </tr>
                                  <tr>
                                    <td class="desc_cell" nowrap><bean:message key ="applicationform.country"/>**</td>
                                    <td>
                                    <html:select property="companyAddress.country" onchange="countryUpdate('company')">
								   	   <html:option value=""></html:option>	
								      	<html:optionsCollection property="countryList" value="key" label="value" />
								    </html:select></td>
                                  </tr>
                                  <tr>
                                    <td class="desc_cell" nowrap><bean:message key ="applicationform.state"/></td>
                                    <td>
                                    	<div id="company_stateText">
                                    		<logic:equal name="applicationSetupForm" property="companyAddress.country" value="MM">
										   	    <html:select property="companyAddress.state" onchange="updateCity(this, 'company')">
											   	    <html:option value=""></html:option>	
											      	<html:optionsCollection property="cstateList" value="key" label="value" />
											    </html:select>
											</logic:equal>
											<logic:notEqual  name="applicationSetupForm" property="companyAddress.country" value="MM">
                                    			<html:text property="companyAddress.state" size="20" maxlength="20"/>
											</logic:notEqual >
                                    	</div>
                                    </td>
                                  </tr>
                                  <tr>
                                    <td class="desc_cell" nowrap><bean:message key ="applicationform.city"/>**</td>
                                    <td>
                                    	<div id="company_cityText">
                                    		<logic:equal name="applicationSetupForm" property="companyAddress.country" value="MM">
										   	    <html:select property="companyAddress.city" onchange="updateTownship(this, 'company')">
											   	    <html:option value=""></html:option>	
											      	<html:optionsCollection property="ccityList" value="key" label="value" />
											    </html:select>
											</logic:equal>
											<logic:notEqual  name="applicationSetupForm" property="companyAddress.country" value="MM">
                                    			<html:text property="companyAddress.city" size="20" maxlength="20"/>
											</logic:notEqual >
                                    	</div>
                                    </td>
                                  </tr>
                                  <tr>
                                    <td class="desc_cell" nowrap><bean:message key ="applicationform.township"/></td>
                                    <td>
                                    	<div id="company_townshipText">
                                    		<logic:equal name="applicationSetupForm" property="companyAddress.country" value="MM">
										   	    <html:select property="companyAddress.township" >
											   	    <html:option value=""></html:option>	
											      	<html:optionsCollection property="ctownshipList" value="key" label="value" />
											    </html:select>
											</logic:equal>
											<logic:notEqual  name="applicationSetupForm" property="companyAddress.country" value="MM">
                                    			<html:text property="companyAddress.township" size="20" maxlength="20"/>
											</logic:notEqual >
                                    	</div>
                                    </td>
                                  </tr>
                                  <tr>
                                    <td class="desc_cell" nowrap width="132"><bean:message key ="applicationform.postalcode"/> </td>
                                    <td><html:text property="companyAddress.postalCode" size="20" maxlength="8"/></td>
                                  </tr>
                                  <tr>
                                    <td class="desc_cell"><bean:message key ="applicationform.phone"/>**</td>
                                    <td><html:text property="companyAddress.phone" size="20" maxlength="50"/></td>
                                  </tr>
                                  <tr>
                                    <td class="desc_cell"><bean:message key ="applicationform.fax"/></td>
                                    <td><html:text property="companyAddress.fax" size="20" maxlength="50"/></td>
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
                              <td height="21">
     
                              <table border="0" cellpadding="0" cellspacing="0" width="274" height="54">
                                  <tr bordercolor="#111111">
                                    <td width="132" class="desc_cell"><bean:message key ="applicationform.gender"/> * </td>
                                    <td>
                                     <html:select property="gender" >
                                          <html:option value=""></html:option>
								          <html:optionsCollection property="genderList" value="key" label="value" />
								       </html:select> 
							         </td>
								   </tr>
                                  <tr bordercolor="#111111">
                                    <td class="desc_cell"><bean:message key ="applicationform.maritalstatus"/> * </td>
                                    <td> <html:select property="maritalStatus" >
                                          <html:option value=""></html:option>
                                          <html:optionsCollection property="maritalStatusList" value="key" label="value" />
								       </html:select></td>
                                  </tr>
                                  <tr bordercolor="#111111">
                                    <td class="desc_cell"><bean:message key ="applicationform.numberofdependents"/> * </td>
                                    <td><html:text property="dependents" size="20" maxlength="2"/></td>
                                  </tr>
                                  <tr bordercolor="#111111">
                                    <td class="desc_cell"><bean:message key ="applicationform.education"/> * </td>
                                    <td><html:select property="education" >
                                          <html:option value=""></html:option>
                                          <html:optionsCollection property="educationList" value="key" label="value" />   
                                        </html:select>                               </td>
                                  </tr>
                                  <tr bordercolor="#111111">
                                    <td class="desc_cell"><bean:message key ="applicationform.email"/></td>
                                    <td><html:text property="email" size="20" maxlength="40"/></td>
                                  </tr>
                              </table>
                      </td>
                            </tr>
                          </tbody>
                        </table>  </td>
                        
                        
                      </tr>
                      <tr>
                        <td width="52%" valign="top" style="padding: 20px 20px 10px 20px;"><table cellspacing=0 cellpadding=0 
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
                                    <td width="132" class="desc_cell"><bean:message key ="applicationform.referencename"/> * </td>
                                    <td><html:text property="referenceName" size="20" maxlength="40"/></td>
                                  </tr>
                                  
                                   <tr bordercolor="#111111">
                                    <td class="desc_cell"><bean:message key ="applicationform.referencecompany"/></td>
                                    <td><html:text property="referenceCompany" size="20" maxlength="40"/></td>
                                  </tr>
                                  
                                  <tr bordercolor="#111111">
                                    <td class="desc_cell"><bean:message key ="applicationform.referencephone"/></td>
                                    <td><html:text property="referencePhone" size="20" maxlength="40"/></td>
                                  </tr>
                                 
                                  <tr bordercolor="#111111">
                                    <td class="desc_cell"><bean:message key ="applicationform.referencefax"/></td>
                                    <!-- <td><html:text property="referenceFax" size="20" maxlength="40"/></td>  -->
                                    <td class="label"><html:textarea  property="referenceFax" rows="3"></html:textarea></td>
                                    
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
                              <td height="21">
                              
                              
                             	<table width="100%" border="0" cellpadding="0" cellspacing="0">  
                              	  <tr bordercolor="#111111">
                                    <td nowrap class="desc_cell"><strong>
                                     <bean:message key ="applicationform.paymentaccount"/></strong></td>
                                  </tr>
                                  
                                  
                               
                                
                                	<%--   <%if(cardProductName.contains("CREDIT")){
                                	  %> --%>
                                 	  
                                  <tr bordercolor="#111111">
                                  
                                    <td height="25" nowrap>
                                    <!--Credit DIV HERE -->
                                    <!--  <div id="credit" style="display: none;">  -->
                                    <div id="credit"> 
                                    <table border="0" cellspacing="0" cellpadding="0">
                                  
                                      <tr bordercolor="#111111">
                                      
                                        <td class="desc_cell">
                                         
                                        <bean:message key ="applicationform.collectrolamt"/></td>
                                        <td><html:text property="collectoralAmt" size="20" maxlength="20"/>
                                         </td>
                                      </tr>
                                      <tr bordercolor="#111111">
                                        <td class="desc_cell"><bean:message key ="applicationform.collectrolac"/></td>
                                        <td><html:text property="collectoralAccount" size="20" maxlength="20"/></td>
                                      </tr>
                                      <tr bordercolor="#111111">
                                        <td class="desc_cell">
                                       
                                        <bean:message key ="applicationform.autopaymentaccount"/></td>
                                        <td><html:text property="autoPayAccount" size="20" maxlength="20"/><html:checkbox property ="autoPayAccountOn" /></td>
                                      </tr>
                                    </table>
                                      
                                    </td>
                                   
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
                                        <td><html:text property="savingAccount" size="20" maxlength="20"/></td>
                                      </tr>
                                      <tr bordercolor="#111111">
                                        <td class="desc_cell"><bean:message key ="applicationform.checkingaccount"/></td>
                                        <td><html:text property="checkingAccount" size="20" maxlength="20"/></td>
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
                                    </table>
                                    
                                    </td>
                                  </tr>
								<%--} --%>
								 <!-- DIV END HERE -->  
                                    </div>
                              </table>
                              
                              </td>
                            </tr>
                             
                          </tbody>
                        </table></td>
                      </tr>
                    </table>  
		</div>
		<!-- Tab 3 -->
		<div id="tab3">
       <!-- supply table data -->
		                                
                    <table width="100%" border="0" cellspacing="0" cellpadding="0">
                      <tr>
                        <td width="48%" valign="top" style="padding: 20px 20px 10px 20px;">
                      
                        <table cellspacing=0 cellpadding=0          border=0>
                          <tbody>
                            <tr>
                              <td><table cellspacing=0 cellpadding=0 border=0>
                                  <tbody>
                                    <tr>
                                      <td><img height=19 alt="" src="images/tab_g.gif" width=8 border=0></td>
                                      <td class=group_title background=images/tab_fond.gif><bean:message key ="applicationform.supplementaydetails"/></td>
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
                            	<td height="21">
                            		<table border="0" cellpadding="0" cellspacing="0" width="264">
	                                  <tr bordercolor="#111111">
	                                    <td nowrap class="desc_cell"><bean:message key ="applicationform.supplcardrequired"/></td>
	                                    <td><html:checkbox property ="supplCardRequired" onclick="checkCommon(this)" /></td>
	                                  </tr>
                                  </table>
                            	</td>
                            </tr>
                            <tr>
                              <td height="21">
                              <div id="suppPartOne" style="display: none;">
                              <table border="0" cellpadding="0" cellspacing="0" width="264" height="144">
                                 <tr bordercolor="#111111">
                                    <td width="132" class="desc_cell"><bean:message key ="applicationform.customername"/></td>
                                    <td><html:text property="supplCustomerName" maxlength="30" size="20"/></td>
                                  </tr>
                                  <tr bordercolor="#111111">
                                    <td class="desc_cell"><bean:message key ="applicationform.surname"/></td>
                                    <td><html:text property="supplSurName" maxlength="30" size="20"/></td>
                                  </tr>
                                  <tr bordercolor="#111111">
                                    <td class="desc_cell"><bean:message key ="applicationform.embossingName"/></td>
                                    <td><html:text property="supplEmbossingName" maxlength="29" size="20"/></td>
                                    <td><div id="demoReply11" style="color: green;width: 70px;"></div></td>
                                  </tr>
                                  <tr bordercolor="#111111">
                                    <td class="desc_cell"><bean:message key ="applicationform.dob"/></td>
                                    <td><html:text property="strSupplDob" size="20" maxlength="10" onblur="javascript:isValidDate(document.applicationSetupForm.strSupplDob)"/></td>
                                  </tr>
                                  <tr bordercolor="#111111">
                                    <td class="desc_cell"><bean:message key ="applicationform.pob"/></td>
                                    <td><html:text property="supplPob" size="20" maxlength="30"/></td>
                                  </tr>
                                  <tr bordercolor="#111111">
                                    <td width="132" class="desc_cell"><bean:message key ="applicationform.gender"/></td>
                                    <td><html:select property="supplGender" >
								         <html:option value=""></html:option>
								          <html:optionsCollection property="genderList" value="key" label="value" />
								       </html:select>              </td>
								   </tr>
                                  <tr bordercolor="#111111">
                                    <td class="desc_cell"><bean:message key ="applicationform.maritalstatus"/></td>
                                    <td><html:select property="supplMaritalStatus" >
                                          <html:option value=""></html:option>
                                          <html:optionsCollection property="maritalStatusList" value="key" label="value" />
								      </html:select></td>
                                  </tr>
                                  <tr bordercolor="#111111">
                                    <td class="desc_cell"><bean:message key ="applicationform.idnumber"/> </td>
                                    <td><html:text property="supplIdNumber" size="20"/></td>
                                  </tr>
                                  <tr bordercolor="#111111">
                                    <td class="desc_cell"><bean:message key ="applicationform.iddate"/></td>
                                    <td><html:text property="strSupplIdDate" size="20" maxlength="10" onblur="javascript:isValidDate(document.applicationSetupForm.strSupplIdDate)"/></td>
                                  </tr>
                                  <tr bordercolor="#111111">
                                    <td class="desc_cell"><bean:message key ="applicationform.expdate"/> </td>
                                    <td><html:text property="strSupplIdExpire" size="20" maxlength="10" onblur="javascript:isValidDate(document.applicationSetupForm.strSupplIdExpire)"/></td>
                                  </tr>
                                  <tr bordercolor="#111111">
                                    <td class="desc_cell"><bean:message key ="applicationform.idplace"/></td>
                                    <td><html:text property="supplIdPlace" size="20" maxlength="30"/></td>
                                  </tr>
                                  
                                  <tr bordercolor="#111111">
                                    <td class="desc_cell"><bean:message key ="applicationform.nationality"/> </td>
                                    <td><html:text property="supplNationality" size="20" maxlength="20"/></td>
                                   </tr>
                                   <tr bordercolor="#111111">
                                    <td class="desc_cell"><bean:message key ="applicationform.email"/></td>
                                    <td><html:text property="supplEmail" size="20" maxlength="40"/></td>
                                  </tr>
                                  <tr bordercolor="#111111">
                                    <td class="desc_cell"><bean:message key ="applicationform.income"/></td>
                                    <td><html:text property="supplIncome" size="20" maxlength="11" onblur="javascript:isValidDecimal(this, 8, 2)" /></td>
                                  </tr>
                                  <tr bordercolor="#111111">
                                    <td nowrap class="desc_cell"><bean:message key ="applicationform.relationtocardholder"/></td>
                                    <td><html:select property="relationShip" >
                                        <html:option value=""></html:option>
                                         <html:optionsCollection property="relationshipList" value="key" label="value" />
								   </html:select>
                                    </td>
                                  </tr>
                              </table>
                              </div>
                              </td>
                            </tr>
                          </tbody>
                        </table>
                         
                        </td>
                        
                    <td valign="top" style="padding: 20px 20px 10px 20px;">
                    <table width="335"    border=0 cellpadding=0 cellspacing=0>
                          <tbody>
                            <tr>
                              <td width="335">
               					<div id="suppPartTwo" style="display: none;">
	                              	<table cellspacing=0 cellpadding=0 border=0>
	                                  <tbody>
	                                    <tr>
	                                      <td><img height=19 alt="" src="images/tab_g.gif" width=8 border=0></td>
	                                      <td class=group_title background=images/tab_fond.gif><bean:message key ="applicationform.supplementayaddress"/></td>
	                                      <td><img height=19 alt="" src="images/tab_d.gif" width=8 border=0></td>
	                                    </tr>
	                                  </tbody>
	                              	</table>
                              	</div>
                              </td>
                            </tr>
                            <tr>
                              <td bgcolor=#dce5ea>
                              	<div id="suppStyle"  style="FONT-SIZE: 1px;display: none;">&nbsp;</div>
                              </td>
                            </tr>
                            <tr>
                              <td height="159">
               				<div id="suppPartThree" style="display: none;">
                              <table border="0" cellpadding="0" cellspacing="0" width="264" height="109">
                                  <tr>
                                    <td class="desc_cell" nowrap><bean:message key ="applicationform.address1"/></td>
                                    <td><html:text property="supplementaryAddress.address1" size="20" maxlength="100"/></td>
                                  </tr>
                                  <tr>
                                    <td class="desc_cell" nowrap><bean:message key ="applicationform.address2"/> </td>
                                    <td><html:text property="supplementaryAddress.address2" size="20" maxlength="100"/></td>
                                  </tr>
                                  <tr>
                                    <td class="desc_cell" nowrap><bean:message key ="applicationform.country"/></td>
                                    <td>
                                    <html:select property="supplementaryAddress.country" onchange="countryUpdate('supplementary')">
								   	   <html:option value=""></html:option>	
								      	<html:optionsCollection property="countryList" value="key" label="value" />
								    </html:select></td>
                                  </tr>
                                  <tr>
                                    <td class="desc_cell" nowrap><bean:message key ="applicationform.state"/></td>
                                    <td>
                                    	<div id="supplementary_stateText">
                                    		<logic:equal name="applicationSetupForm" property="supplementaryAddress.country" value="MM">
										   	    <html:select property="supplementaryAddress.state" onchange="updateCity(this, 'supplementary')">
											   	    <html:option value=""></html:option>	
											      	<html:optionsCollection property="sstateList" value="key" label="value" />
											    </html:select>
											</logic:equal>
											<logic:notEqual  name="applicationSetupForm" property="supplementaryAddress.country" value="MM">
                                    			<html:text property="supplementaryAddress.state" size="20" maxlength="20"/>
											</logic:notEqual >
                                    	</div>
                                    </td>
                                  </tr>
                                  <tr>
                                    <td class="desc_cell" nowrap><bean:message key ="applicationform.city"/></td>
                                    <td>
                                    	<div id="supplementary_cityText">
                                    		<logic:equal name="applicationSetupForm" property="supplementaryAddress.country" value="MM">
										   	    <html:select property="supplementaryAddress.city" onchange="updateTownship(this, 'supplementary')">
											   	    <html:option value=""></html:option>	
											      	<html:optionsCollection property="scityList" value="key" label="value" />
											    </html:select>
											</logic:equal>
											<logic:notEqual  name="applicationSetupForm" property="supplementaryAddress.country" value="MM">
                                    			<html:text property="supplementaryAddress.city" size="20" maxlength="20"/>
											</logic:notEqual >
                                    	</div>
                                    </td>
                                  </tr>
                                  <tr>
                                    <td class="desc_cell" nowrap><bean:message key ="applicationform.township"/></td>
                                    <td>
                                    	<div id="supplementary_townshipText">
                                    		<logic:equal name="applicationSetupForm" property="supplementaryAddress.country" value="MM">
										   	    <html:select property="supplementaryAddress.township" >
											   	    <html:option value=""></html:option>	
											      	<html:optionsCollection property="stownshipList" value="key" label="value" />
											    </html:select>
											</logic:equal>
											<logic:notEqual  name="applicationSetupForm" property="supplementaryAddress.country" value="MM">
                                    			<html:text property="supplementaryAddress.township" size="20" maxlength="20"/>
											</logic:notEqual >
                                    	</div>
                                    </td>
                                  </tr>
                                  <tr>
                                    <td class="desc_cell" nowrap width="132"><bean:message key ="applicationform.postalcode"/> </td>
                                    <td><html:text property="supplementaryAddress.postalCode" size="20" maxlength="8"/></td>
                                  </tr>
                                  <tr>
                                    <td class="desc_cell"><bean:message key ="applicationform.phone"/></td>
                                    <td><html:text property="supplementaryAddress.phone" size="20" maxlength="12"/></td>
                                  </tr>
                                  <tr>
                                    <td class="desc_cell"><bean:message key ="applicationform.fax"/></td>
                                    <td><html:text property="supplementaryAddress.fax" size="20" maxlength="12"/></td>
                                  </tr>
                                  
                               </table>
                               </div>
                               </td>
                            </tr>
                          </tbody>
                      </table>
                      </td>
                       </tr>     
                  </table>
    <!-- div tag end -->
    </div>
                        
                  </td>
                  <td background="images/tbl_d.gif"></td>
                </tr>
                
                                
                <tr> 
                  <td background="images/tbl_g.gif"></td>
                  <td class="form_bgcolor"  colspan="2"> 
                  	<div align="left" style="margin-left: 20px;color: red;">* - Mandatory Fields<br> ** - Mandatory if select Billing Address as 'Office'</div>
                    <div align="right"> 
                    	 <% if(request.getAttribute("ACTION") == null){ %>
                    		 <html:button property="submitbutton" onclick="go('add')" ><bean:message key ="common.save"/></html:button>
                    		 <html:button property="submitbutton" onclick ="showList()"><bean:message key ="common.cancel"/></html:button>
                    	<% }else { %>
	                    	<% if(request.getAttribute("ACTION") != null && ((String)request.getAttribute("ACTION")).equals("add")) { %>
	        					<html:button property="submitbutton" onclick="go('add')" ><bean:message key ="common.save"/></html:button>
	        					<html:button property="submitbutton" onclick ="showList()"><bean:message key ="common.cancel"/></html:button>
	        				 <% }else if(((String)request.getAttribute("ACTION")).equals("change") || ((String)request.getAttribute("ACTION")).equals("update")) { %>
	        				    <logic:equal name="applicationSetupForm" property="applicationStatus" value="0">
		        					<html:button property="submitbutton" onclick="go('update')"><bean:message key ="common.save"/></html:button>
		        					<html:button property="fdelete" onclick="accRejDelete('delete')"><bean:message key ="common.delete"/></html:button>
		                        </logic:equal>
		                        <logic:equal name="applicationSetupForm" property="applicationStatus" value="5">
		        					<html:button property="submitbutton" onclick="go('update')"><bean:message key ="common.save"/></html:button>
		        					<html:button property="fdelete" onclick="accRejDelete('delete')"><bean:message key ="common.delete"/></html:button>
		                        </logic:equal>
	        					<html:button property="submitbutton" onclick ="showList()"><bean:message key ="common.cancel"/></html:button>
	       				     <% }else if(((String)request.getAttribute("ACTION")).equals("cancel")) { %>  	
	        					<html:button property="submitbutton" onclick ="showList()"><bean:message key ="common.cancel"/></html:button>
	        				 <%}%>		
  					  	<%}%>
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
  	 