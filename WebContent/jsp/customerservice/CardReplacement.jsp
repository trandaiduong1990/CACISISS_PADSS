<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/displaytag-12.tld" prefix="display" %>
<html:html>
<head>
<title><bean:message key ="customerservice.cardreplacement"/></title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="inc/css/style.css" type="text/css">
<script src="inc/js/cacis.js"></script>
</head>
<script>
function go(method) 
{
  document.forms[0].method.value=method;
  document.forms[0].submit();	
}

function accRej(method) 
{
  document.forms[0].method.value=method;
  var r=confirm("<bean:message key ='areyousure'/>");
  if (r==true)
  {
	var instantReplacement = document.forms[0].instantReplacement.value;
	if(instantReplacement == 'Y') {
		document.forms[0].method.value = 'instantReplacement';
	} else {
		alert("call accept method");
	}
	document.forms[0].submit();
  }
}
///to call csrDispatchAction to show all the data as popup window
function viewAllInfo(urlToCall){
  	var left = (screen.width/2)-(400);
  	var top = (screen.height/2)-(250);
  	window.open(urlToCall, "CustServiceAllData",'resizable =yes, status=yes,scrollbars=yes,menubar=no height=500,width=800,location=no, top='+top+', left='+left);
  }
 ///to call different actions when click on cancel button
  function showList(url){
    document.forms[0].action = url;
    document.forms[0].submit();
   }
    
</script>
<body bgcolor="ffffff">
<html:form  action ="cardreplacementlist" >
<input type ="hidden" name ="method" />

 <!-- this for showing the search condition-->
 <logic:notPresent name="$CARDREPLACELIST$"> 
 
 <table border="0" cellspacing="0" cellpadding="0" style="border-collapse: collapse" bordercolor="#111111" width="100%">
  <tr>
    <td> 
      <table border="0" cellspacing="0" cellpadding="0" width="100%">
    
        <tr>
            <td class="titreSection"><bean:message key ="customerservice.cardreplacement"/></td>
        </tr>
       
        <tr>
          <td background="images/ligne.gif" colspan="2"> <img src="images/spacer.gif" width="1" height="1"></td>
        </tr>
        
        <tr>
          <td class="titreSection">
              <table border="0" cellspacing="2" cellpadding="2" width="157" align="left">
                <tr> 
                <td class="texteMenuGauche"> 
                  <html:button property="submitbutton" onclick="go('searchReplace')" ><bean:message key ="common.search"/></html:button> 
                 </td>
                
               </tr>
            </table>
            </td>
           <td class="texteMenuGauche"><div align="right"><a href='javascript:helpLink("WRITEOFF")'>Help</a></div></td>
        </tr>
        <tr>
          <td background="images/ligne.gif" colspan="2"> <img src="images/spacer.gif" width="1" height="1"></td>
        </tr>
      </table>
    </td>
  </tr>
  <tr>
    <td valign="top">
     <br>
      <table width="100%">
        <tr>
          <td valign="top" class="ErrFONT"><font color="#FF0000"><html:errors /></font></td>
        </tr>
        <tr> 
          <td valign="top">
         	  
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr> 
                <td> 
                    <table cellpadding="0" cellspacing="0">
              
                      <tr> 
                        <td> <font class="label"><b><bean:message key ="customerservice.cardnumber"/></b></font> 
                             <html:text property="cardNumber" maxlength ="16" size="20" onkeypress="return disableEnterKey(event)"/>
                        </td>
                      </tr>
                                           
                  </table>
                </td>
              </tr>
            </table>
      	  </td>
        </tr>
  		<tr> 
     		<td valign="top">&nbsp; </td>
  		</tr>
	</table>
   </td>
  </tr>
</table> 
</logic:notPresent> 
  
<logic:present name="$CARDREPLACELIST$">
<!--  hiddeen fields to insert the data in varioues tables-->
<html:hidden property="issuerId" value="<%=(String)session.getAttribute("ISSUER")%>"/>
<html:hidden property="userId" value="<%=(String)session.getAttribute("USERID")%>"/>
<html:hidden property="cardNumber"/>
<html:hidden property="customerId"/>
<html:hidden property="applicationId" />
<html:hidden property="branchId" /> 
<html:hidden property="cardProductId" /> 
<html:hidden property="cardHolderType"/>  
<html:hidden property="idNumber"/>
<html:hidden property="encryptedCardNo"/>
<html:hidden property="maskedCardNo"/>
   
<!--  this for dipalying the list data (CustomerServiceDataBean from session) -->
     
<logic:iterate id ="custServObj" name ="$CARDREPLACELIST$"> 
       
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr> 
      <td> 
        <table border="0" cellspacing="0" cellpadding="0">
        <tr> 
            <td class="titreSection" width="519"><bean:message key ="customerservice.cardreplacement"/></td>
            <td class="titreSection" width="484">&nbsp; </td>
        </tr>
        <tr> 
            <td background="images/ligne.gif" colspan="3"> <img src="images/spacer.gif" width="1" height="1"></td>
        </tr>
        <tr> 
            <td class="titreSection" width="519">&nbsp;</td>
            <td class="titreSection" width="484">&nbsp;</td>
        </tr>
        <tr> 
            <td background="images/ligne.gif" colspan="3"> <img src="images/spacer.gif" width="1" height="1"></td>
        </tr>
      </table>
    </td>
  </tr>
<tr><td valign="top" class="ErrFONT"><font color="#FF0000"><html:errors /></font></td></tr>
<tr> 
      <td valign="top"><br>
      <table>
        <tr> 
          <td width="482" valign="top"> 
              <table width="482" border="0" align="left" cellpadding="0" cellspacing="0">
                <tr> 
                  <td> <img src="images/form_tab_start_on.gif" border="0" alt="" width="5" height="22"></td>
                  <td background="images/tbl_haut.gif" colspan="2"> 
                    <table border="0" cellpadding="0" cellspacing="0">
                      <tr> 
                        <td class="form_tab_on" background="images/form_tab_bg_on.gif" style="padding-left:15px"> 
						<bean:message key ="customerservice.cardinfo"/>                    </td>
                        <td><img src="images/form_tab_btw_on_off.gif" width="25" height="22"></td>
                        <td background="images/form_tab_bg_off.gif" class="form_tab_off"><a href="javascript:viewAllInfo('csrprocess.do?method=csrSummary&CustServCardNo=<bean:write name ="custServObj"  property="cardNumber" />&maskedCardNo=<bean:write name ="custServObj"  property="maskedCardNo" />')"><bean:message key ="customerservice.viewtotalinformation"/></a></td>
                        <td><img src="images/form_tab_end_off.gif" width="25" height="22"></td>
                      </tr>
                    </table>                  </td>
                  <td> <img src="images/tbl_haut_d.gif" border="0" alt="" width="5" height="22"></td>
                </tr>
                <tr> 
                  <td background="images/tbl_g.gif"></td>
                  <td class="form_bgcolor"  colspan="2"> 
                    <div align="right"> 
                       <%
					   if(request.getAttribute("ACTION") == null || ((String)request.getAttribute("ACTION")).equals("search"))
					    {%>
                 
                         <html:button property="submitbutton" onclick="go('add')" > <bean:message key ="common.save"/></html:button>
                         <html:button property="submitbutton" onclick="showList('cardreplacementlist.do?method=checkSession')"><bean:message key ="common.cancel"/></html:button>
        		             		        
       		         <% }else if(((String)request.getAttribute("ACTION")).equals("replace")) { %>
       		         	 
       		         	 <html:button property="accept" onclick="accRej('accept')"><bean:message key ="common.accept"/></html:button>
       					 <html:button property="reject" onclick="accRej('reject')"><bean:message key ="common.reject"/></html:button>
       					 <html:button property="submitbutton" onclick="showList('cardrepprocesslist.do?method=processSearch')"><bean:message key ="common.cancel"/></html:button>
       				   <%}else if(((String)request.getAttribute("ACTION")).equals("cancel")){//closing the scriplet%>
          	             <html:button property="submitbutton" onclick="showList('cardrepprocesslist.do?method=checkSession')"><bean:message key ="common.cancel"/></html:button>
         	           <%} %>
                    </div>			      </td>
                  <td background="images/tbl_d.gif"></td>
                </tr>
                <tr>
                  <td background="images/tbl_g.gif"></td>
                </tr>
                <tr>
                  <td background="images/tbl_g.gif"></td>
                  <td valign="top" class="form_bgcolor" style="padding: 20px 20px 10px 20px;"><table cellspacing=0 cellpadding=0 
                              border=0>
                    <tbody>
                      <tr>
                        <td><table cellspacing=0 cellpadding=0 border=0>
                            <tbody>
                              <tr>
                                <td><img height=19 alt="" src="images/tab_g.gif" width=8 border=0></td>
                                <td class=group_title background=images/tab_fond.gif><bean:message key ="customerservice.personalinfo"/></td>
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
                        <td height="21"><table border="0" cellpadding="0" cellspacing="0" width="264" height="72">
                           
                              <tr> 
					             <td class="desc_cell" nowrap height="18"><bean:message key ="customerservice.customername"/></td>
					             <td class="label"><bean:write name ="custServObj"  property="customerName" /> </td>
					          </tr>
					           <tr> 
					              <td class="desc_cell" nowrap width="132" height="18" ><bean:message key ="customerservice.nricnumber"/></td>
					              <td class="label"><bean:write name ="custServObj"  property="idNumber" /></td>
					          </tr>
					          <tr> 
					             <td class="desc_cell" nowrap width="132" height="18"><bean:message key ="customerservice.nricexpiredate"/></td>
					             <td class="label"><bean:write name ="custServObj"  property="strExpDate" /></td>
					          </tr>
					          <tr> 
				                 <td class="desc_cell" nowrap width="132" height="18"><bean:message key ="customerservice.dateofbirth"/></td>
				                 <td class="label"><bean:write name ="custServObj"  property="strDob" /></td>
				             </tr>  
                                                     
                        </table></td>
                      </tr>
                    </tbody>
                  </table></td>
                  <td valign="top" class="form_bgcolor" style="padding: 20px 20px 10px 20px;"><table width="264" 
                              border=0 cellpadding=0 cellspacing=0>
                    <tbody>
                      <tr>
                        <td><table cellspacing=0 cellpadding=0 border=0>
                            <tbody>
                              <tr>
                                <td><img height=19 alt="" src="images/tab_g.gif" width=8 border=0></td>
                                <td class=group_title background=images/tab_fond.gif><bean:message key ="customerservice.cardinfo"/></td>
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
                        <td height="96"><table border="0" cellpadding="0" cellspacing="0" width="264" height="96">
                            
                              <tr> 
                              <td class="desc_cell" nowrap  height="18" ><bean:message key ="customerservice.cardnumber"/></td>
                              <td class="label"><bean:write name ="custServObj"  property="maskedCardNo" /></td>
                            </tr>
                            <tr> 
                              <td class="desc_cell" nowrap height="18" ><bean:message key ="customerservice.cardtype"/></td>
                              <td class="label"><bean:write name ="custServObj"  property="cardType" /></td>
                            </tr>
                            <tr> 
                              <td class="desc_cell" nowrap><bean:message key ="customerservice.cardproductname"/></td>
                              <td class="label"><bean:write name ="custServObj"  property="cardProductName" /></td>
                            </tr>
                            <!-- 
                             <tr> 
                              <td class="desc_cell" nowrap ><bean:message key ="customerservice.cardlimit"/></td>
                              <td class="label"><bean:write name ="custServObj"  property="creditLimit" /></td>
                            </tr>
                             -->
							<tr> 
                              <td class="desc_cell" nowrapheight="18" ><bean:message key ="customerservice.cardstatus"/></td>
                              <td class="label"><bean:write name ="custServObj"  property="cardStatus" /></td>
                            </tr>
                           
                        </table></td>
                      </tr>
                    </tbody>
                  </table></td>
                  <td background="images/tbl_d.gif"></td>
                </tr>
         </logic:iterate>      
                <tr> 
                  <td background="images/tbl_g.gif"></td>
                  <td valign="top" class="form_bgcolor" style="padding: 20px 20px 10px 20px;"><table width="335" 
                              border=0 cellpadding=0 cellspacing=0>
                    <tbody>
                      <tr>
                        <td width="335"><table cellspacing=0 cellpadding=0 border=0>
                            <tbody>
                              <tr>
                                <td><img height=19 alt="" src="images/tab_g.gif" width=8 border=0></td>
                                <td class=group_title background=images/tab_fond.gif><bean:message key ="customerservice.addressinfo"/></td>
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
                        <td height="21"><table border="0" cellpadding="0" cellspacing="0" width="264" height="109">
                           <!-- if action =search then dipalying the billing addres in text fields to edit the addressfields of CardReplacementForm bean  and showing add and cancel buttons-->
				  
                         <%
					    if(request.getAttribute("ACTION") == null || ((String)request.getAttribute("ACTION")).equals("search"))
					    {%>
					       <tr> 
                               <td class="desc_cell" nowrap ><bean:message key ="customerservice.address1"/></td>
                               <td class="label"><bean:write name ="cardReplacementForm"  property="requestAddress.address1" /></td>
                            </tr>
							<tr> 
                              <td class="desc_cell" nowrap ><bean:message key ="customerservice.address2"/></td>
                             <td class="label"><bean:write name ="cardReplacementForm"  property="requestAddress.address2" /></td>
                            </tr>
                                            
                            <tr> 
                               <td class="desc_cell" nowrap ><bean:message key ="customerservice.country"/></td>
                              <td class="label">
                                <html:select  property = "requestAddress.country" disabled="true" >
                               <html:option value="">SelectCountry</html:option>	
							      	 <html:optionsCollection property="countryList" value="key" label="value" /> 	
							    </html:select>
							   </td>
                             </tr>
							<tr> 
                              <td class="desc_cell" nowrap ><bean:message key ="customerservice.state"/></td>
                              <td class="label"><bean:write name ="cardReplacementForm"  property="requestAddress.state" /></td>
                            </tr>
                             <tr> 
                               <td class="desc_cell" nowrap ><bean:message key ="customerservice.city"/></td>
                               <td class="label"><bean:write name ="cardReplacementForm"  property="requestAddress.city" /></td>
                            </tr>
                            <tr> 
                                <td class="desc_cell" nowrap ><bean:message key ="customerservice.township"/></td>
                                <td class="label"><bean:write name ="cardReplacementForm"    property="requestAddress.township"/></td>
                            </tr>
                             <tr> 
                                  <td class="desc_cell" nowrap ><bean:message key ="customerservice.postalcode"/></td>
                                  <td class="label"><bean:write name ="cardReplacementForm"  property="requestAddress.postalCode" /></td>
                            </tr>
							<tr>
							     <td class="desc_cell" nowrap ><bean:message key ="customerservice.phone"/></td>
							     <td class="label"><bean:write name ="cardReplacementForm"  property="requestAddress.phone" /></td>
                            </tr>
                            <tr> 
                                <td class="desc_cell" nowrap ><bean:message key ="customerservice.fax"/></td>
                                <td class="label"><bean:write name ="cardReplacementForm"  property="requestAddress.fax" /></td>
							</tr>
					      <!--<tr> 
					       	  <td class="desc_cell" nowrap ><bean:message key ="customerservice.reason"/></td>
					          <td class="label">
					            <html:select property="reasonCode" >
					              <html:option value=""></html:option>
					              <html:optionsCollection property="cardStatusList" value="key" label="value" />
					 	      </html:select>
					      	 </td>
			               </tr>-->
							<tr> 
			                    <td class="desc_cell" nowrap><bean:message key ="customerservice.remarks"/></td>
			                    <td class="label"><html:textarea property="remarks" rows="3"></html:textarea></td>
			                </tr>
			   <!--if action = replace then dipalying billing address  on form  with accept, reject and cancel buttons-->	  	
				  	  <% }else if(((String)request.getAttribute("ACTION")).equals("replace") || ((String)request.getAttribute("ACTION")).equals("cancel")) {%>
                           
                             <tr> 
                                 <td class="desc_cell" nowrap ><bean:message key ="customerservice.address1"/></td>
                                 <td class="label"><bean:write name ="cardReplacementForm"  property="requestAddress.address1" /></td>
                            </tr>
							<tr> 
                                <td class="desc_cell" nowrap ><bean:message key ="customerservice.address2"/></td>
                                <td class="label"><bean:write name ="cardReplacementForm"  property="requestAddress.address2" /></td>
                            </tr>
                            <tr> 
                              <td class="desc_cell" nowrap ><bean:message key ="customerservice.country"/></td>
                              <td class="label">
                                    <html:select  property = "requestAddress.country" disabled="true">
                                       <html:option value=""> </html:option>	
							   	       <html:optionsCollection property="countryList" value="key" label="value" /> 	
							       </html:select></td>
							</tr>
							<tr> 
                               <td class="desc_cell" nowrap ><bean:message key ="customerservice.state"/></td>
                               <td class="label"><bean:write name ="cardReplacementForm"   property="requestAddress.state" /></td>
                            </tr>
                            <tr> 
                                <td class="desc_cell" nowrap ><bean:message key ="customerservice.city"/></td>
                                <td class="label"><bean:write name ="cardReplacementForm"    property="requestAddress.city"/></td>
                            </tr>
                            <tr> 
                                <td class="desc_cell" nowrap ><bean:message key ="customerservice.township"/></td>
                                <td class="label"><bean:write name ="cardReplacementForm"    property="requestAddress.township"/></td>
                            </tr>
                            <tr> 
                              <td class="desc_cell" nowrap width="132"><bean:message key ="customerservice.postalcode"/></td>
                              <td class="label"><bean:write name ="cardReplacementForm"  property="requestAddress.postalCode"/></td>
                            </tr>
							<tr>
							   <td class="desc_cell"><bean:message key ="customerservice.phone"/></td>
							   <td class="label"><bean:write name ="cardReplacementForm"  property="requestAddress.phone"  /></td>
                            </tr>
                            <tr> 
                                <td class="desc_cell"><bean:message key ="customerservice.fax"/></td>
                                <td class="label"><bean:write name ="cardReplacementForm"   property="requestAddress.fax"/></td>
							</tr>
							 <!-- <tr> 
		       	                <td class="desc_cell"><b><bean:message key ="customerservice.reason"/></td>
		                         <td class="label">
		                       <html:select property="reasonCode" disabled="true">
		                           <html:option value=""></html:option>
		                           <html:optionsCollection property="cardStatusList" value="key" label="value" />
		 	                  </html:select>
		      	            </td>
                         </tr> -->
						   <tr> 
                               <td class="desc_cell"><bean:message key ="customerservice.remarks"/></td>
                               <td class="label"><bean:write name ="cardReplacementForm" property="remarks" /></td>
                          </tr>
                          <html:hidden property="remarks"/>  
                          <% if((request.getAttribute("ACTION") != null) && (((String)request.getAttribute("ACTION")).equals("replace") || ((String)request.getAttribute("ACTION")).equals("cancel"))) {%>
	                      <tr>
	                      	<td style="height: 20px;">
	                      		
	                      	</td>
	                      </tr>
	                      <tr>
                        	<td width="335">
                        	<table cellspacing=0 cellpadding=0 border=0>
                            <tbody>
                              <tr>
                                <td><img height=19 alt="" src="images/tab_g.gif" width=8 border=0></td>
                                <td class=group_title background=images/tab_fond.gif><bean:message key ="customerservice.replacefees"/></td>
                                <td><img height=19 alt="" src="images/tab_d.gif" width=8 border=0></td>
                              </tr>
                            </tbody>
                        	</table>
                        	</td>
                      	  </tr>
	                      <tr>
		                      <td class="desc_cell"><bean:message key ="customerservice.replacefeeamt"/></td>
		                      <td class="label"><bean:write name ="cardReplacementForm" property="replacementFees" /></td>
	                      </tr>
	                      <tr>
		                      <td class="label" colspan="2">
		                      	<html:radio property="feeApplicable" value="Y" /> 
								<bean:message key="customerservice.replacefeescharge" />
		                      </td>
	                      </tr>
	                      <tr>
		                      <td class="label" colspan="2">
		                      	<html:radio property="feeApplicable" value="N" /> 
								<bean:message key="customerservice.replacefeesnocharge" />
		                      </td>
	                      </tr>
	                      <tr>
	                      	<td style="height: 20px;">
	                      		
	                      	</td>
	                      </tr>
	                      <tr>
		                      <td class="desc_cell"><bean:message key ="applicationform.immediateprocess"/> * </td>
                              <td nowrap class="label">
                             	<html:radio property ="immeidateProcess" value ="Y"><bean:message key ="applicationform.immediateprocess.yes"/></html:radio>
                               	<html:radio property ="immeidateProcess" value ="N"><bean:message key ="applicationform.immediateprocess.no"/></html:radio>
                              </td>
	                      </tr>
	                      <% } %>
                  
                    <%}//closing the scriplet%>
                        </table></td>
                      </tr>
                    </tbody>
                  </table></td>
                 <%	if(request.getAttribute("ACTION") == null || ((String)request.getAttribute("ACTION")).equals("search"))	{%>
                  <td valign="top" class="form_bgcolor" style="padding: 20px 20px 10px 20px;"><table width="335" 
                              border=0 cellpadding=0 cellspacing=0>
                    <tbody>
                      <tr>
                        <td width="335"><table cellspacing=0 cellpadding=0 border=0>
                            <tbody>
                              <tr>
                                <td><img height=19 alt="" src="images/tab_g.gif" width=8 border=0></td>
                                <td class=group_title background=images/tab_fond.gif><bean:message key ="customerservice.instantreplacement"/></td>
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
                        <td height="21"><table border="0" cellpadding="0" cellspacing="0" >
					       <tr> 
                               <td class="desc_cell" nowrap ><bean:message key ="customerservice.instantreplacement"/></td>
                               <td class="label"><html:checkbox property="instantReplacement" value="Y"></html:checkbox></td>
                            </tr>
                            
                            </table></td>
                      </tr>
                    </tbody>
                  </table></td>
                  <% } else if((request.getAttribute("ACTION") != null) && (((String)request.getAttribute("ACTION")).equals("replace") || ((String)request.getAttribute("ACTION")).equals("cancel"))) {%>
	                    <logic:equal name="cardReplacementForm" property="instantReplacement" value="Y">
	                         <td valign="top" class="form_bgcolor" style="padding: 20px 20px 10px 20px;"><table width="335" 
                              border=0 cellpadding=0 cellspacing=0>
			                    <tbody>
			                      <tr>
			                        <td width="335"><table cellspacing=0 cellpadding=0 border=0>
			                            <tbody>
			                              <tr>
			                                <td><img height=19 alt="" src="images/tab_g.gif" width=8 border=0></td>
			                                <td class=group_title background=images/tab_fond.gif><bean:message key ="customerservice.instantreplacement"/></td>
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
			                        <td><table border="0" cellpadding="0" cellspacing="0" >
								       <tr> 
			                               <td class="desc_cell" nowrap ><bean:message key ="customerservice.instantreplacement"/></td>
			                               <td class="label"><html:checkbox property="instantReplacement" value="Y" disabled="true"></html:checkbox></td>
			                            </tr>
			                            <tr>
			                            	<td class="desc_cell" nowrap ><bean:message key ="customerservice.replacementcardno"/></td>
			                               <td class="label"><html:text property="replacementCardNo" maxlength ="16" size="20"/></td>
			                            </tr>
			                            </table></td>
			                      </tr>
			                    </tbody>
			                  </table></td>
	                    </logic:equal>
	                    <logic:notEqual name="cardReplacementForm" property="instantReplacement" value="Y">
	                    	<td valign="top" class="form_bgcolor" style="padding: 20px 20px 10px 20px;">&nbsp;
	                    		<bean:define id="instantReplacement" name="cardReplacementForm" property="instantReplacement"/>
								<html:hidden property="instantReplacement" value="<%=(String)instantReplacement%>"/>
	                    	</td>
	                    </logic:notEqual>
	                    
	              <% } else { %> 
                  	<td valign="top" class="form_bgcolor" style="padding: 20px 20px 10px 20px;">&nbsp;</td>
                  <% } %>
                  <td background="images/tbl_d.gif"></td>                                   
                </tr>                
                <tr> 
                  <td background="images/tbl_g.gif"></td>
                  <td class="form_bgcolor"  colspan="2"> 
                    <div align="right">
                       <%
					   if(request.getAttribute("ACTION") == null || ((String)request.getAttribute("ACTION")).equals("search")){%>
                         <html:button property="submitbutton" onclick="go('add')" > <bean:message key ="common.save"/></html:button>
        		         <html:button property="submitbutton" onclick="showList('cardreplacementlist.do?method=checkSession')"><bean:message key ="common.cancel"/></html:button>
       		         <% }else if(((String)request.getAttribute("ACTION")).equals("replace")) { %>
       		         	 <html:button property="accept" onclick="accRej('accept')"><bean:message key ="common.accept"/></html:button>
       					 <html:button property="reject" onclick="accRej('reject')"><bean:message key ="common.reject"/></html:button>
       					 <html:button property="submitbutton" onclick="showList('cardrepprocesslist.do?method=checkSession')"><bean:message key ="common.cancel"/></html:button>
       				  <%}else if(((String)request.getAttribute("ACTION")).equals("cancel")){%>
          	             <html:button property="submitbutton" onclick="showList('cardrepprocesslist.do?method=checkSession')"><bean:message key ="common.cancel"/></html:button>
         	           <%} %>
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
 </logic:present>   
    </html:form>     
    </body>
</html:html>