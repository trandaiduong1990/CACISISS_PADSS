<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ page import ="org.transinfo.cacis.formbean.customerservice.CustomerServiceDataBean" %>
<%@ page import ="org.transinfo.cacis.dto.cardproduction.*" %>
<%@ page import ="java.util.*" %>
<html:html>
<head>
<title><bean:message key ="customerservice.cardlimitadjust"/></title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="inc/css/style.css" type="text/css">
<script src="inc/js/cacis.js"></script>
<script src="inc/js/CardLimitAdjSetup.js"></script>
</head>
<script>
function go(method) 
{
	if(method == 'cardLimitAccept'){

		if(cardLimitAccept(document.forms[0])){
			document.forms[0].method.value=method;	
		    document.forms[0].submit();
		}
		
	}else{
	    document.forms[0].method.value=method;	
	    document.forms[0].submit();
	}
}
   //to call csrDispatchAction to show all the data as popup window
	function viewAllInfo(urlToCall){
	  	var left = (screen.width/2)-(400);
	  	var top = (screen.height/2)-(250);
	  window.open(urlToCall, "CustServiceAllData",'resizable =yes, status=yes,scrollbars=yes,menubar=no height=500,width=800,location=no, top='+top+', left='+left);
	  }
	//to show the CardActivitiesDetails in popup window
	function cardActivitiesInfo(urlToCall){
	  window.open(urlToCall, "CardActivitiesDetails",'resizable =yes, status=yes,scrollbars=yes,menubar=no height=400,width=400,location=no');
	  }
   //to show the CustomerHistoryDetails in popup window
	function customerHistory(urlToCall){
	  window.open(urlToCall, "CustomerHistory",'resizable =yes, status=yes,scrollbars=yes,menubar=no height=400,width=400,location=no');
	  }	  
///to call different actions when click on cancel button
  function showList(url){
    document.forms[0].action = url;
    document.forms[0].submit();
   } 	  
</script>
<body bgcolor="ffffff">
   <html:form action="cardlimitlist.do">
 <input type="hidden" name="method"/>
 
   <!-- this for showing the search condition-->
 <logic:notPresent name="$CARDREPLACELIST$">
 
  <table border="0" cellspacing="0" cellpadding="0" style="border-collapse: collapse" bordercolor="#111111" width="100%">
  <tr>
    <td> 
      <table border="0" cellspacing="0" cellpadding="0" width="100%">
        <tr>
            <td class="titreSection"><bean:message key ="customerservice.cardlimitadjust"/></td>
        </tr>
        <tr>
          <td background="images/ligne.gif" colspan="2"> <img src="images/spacer.gif" width="1" height="1"></td>
        </tr>
        <tr>
          <td class="titreSection">
              <table border="0" cellspacing="2" cellpadding="2" width="157" align="left">
                <tr> 
                <td class="texteMenuGauche"> 
                  <html:button property="submitbutton" onclick="go('searchLimitAdj')" ><bean:message key ="common.search"/></html:button> 
                 </td>
                
               </tr>
            </table>
           
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
     <!-- this is for setting   the values to hidden fileds if logic present-->
     
     <html:hidden property="issuerId" value="<%=(String)session.getAttribute("ISSUER")%>"/>
      <html:hidden property="userId" value="<%=(String)session.getAttribute("USERID")%>"/>
        <html:hidden property="cardNumber"/>
         <html:hidden property="idNumber"/>
         <html:hidden property="accountId"/>
         
    <!-- this for showing  the all information-->
  <logic:iterate id ="custServObj" name ="$CARDREPLACELIST$">
   
     <table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr> 
      <td> 
        <table border="0" cellspacing="0" cellpadding="0">
        <tr> 
            <td class="titreSection" width="519"><bean:message key ="customerservice.cardlimitadjust"/></td>
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
                        <td background="images/form_tab_bg_off.gif" class="form_tab_off"><a href="javascript:viewAllInfo('csrprocess.do?method=csrSummary&CustServCardNo=<bean:write name ="custServObj"  property="cardNumber" />')"><bean:message key ="customerservice.viewtotalinformation"/></a></td>
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
 		 				
 			           <html:button property="submitbutton" onclick="go('cardLimitAdd')"><bean:message key ="common.save"/></html:button>
 			           <html:button property="submitbutton" onclick="showList('cardlimitlist.do?method=checkSession')"><bean:message key ="common.cancel"/></html:button>
 			          
        	   <% }else if(((String)request.getAttribute("ACTION")).equals("adjust"))	
        				    { %>
                       <html:button property="submitbutton" onclick="go('cardLimitAccept')"><bean:message key ="common.accept"/></html:button>
                       <html:button property="submitbutton" onclick="go('cardLimitReject')"><bean:message key ="common.reject"/></html:button>
                       <a href="javascript:customerHistory('cardlimitlist.do?method=customerHistory&NricNo=<bean:write name ="custServObj"  property="idNumber" />')"><bean:message key ="common.history"/></a>
                       <a href="javascript:cardActivitiesInfo('cardlimitlist.do?method=cardActivities&CustServCardNo=<bean:write name ="custServObj"  property="cardNumber" />')"><bean:message key ="common.cardactivity"/></a>
                       <html:button property="submitbutton" onclick="showList('cardlimitprocess.do?method=checkSession')"><bean:message key ="common.cancel"/></html:button>
 			          
       	       <%}else if(((String)request.getAttribute("ACTION")).equals("cancel")){//closing the scriplet%>
          	      <html:button property="submitbutton" onclick="showList('cardlimitprocess.do?method=checkSession')"><bean:message key ="common.cancel"/></html:button>
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
                              <td class="label"><bean:write name ="custServObj"  property="cardNumber" /></td>
                            </tr>
                            <tr> 
                              <td class="desc_cell" nowrap height="18" ><bean:message key ="customerservice.cardtype"/></td>
                              <td class="label"><bean:write name ="custServObj"  property="cardType" /></td>
                            </tr>
                            <tr> 
                              <td class="desc_cell" nowrap><bean:message key ="customerservice.cardproductname"/></td>
                              <td class="label"><bean:write name ="custServObj"  property="cardProductName" /></td>
                            </tr>
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
            
                <tr> 
                  <td background="images/tbl_g.gif"></td>
                  <td valign="top" class="form_bgcolor" style="padding: 20px 20px 10px 20px;">
                  <table width="335"   border=0 cellpadding=0 cellspacing=0>
                    <tbody>
                      <tr>
                        <td width="335"><table cellspacing=0 cellpadding=0 border=0>
                            <tbody>
                              <tr>
                                <td><img height=19 alt="" src="images/tab_g.gif" width=8 border=0></td>
                                <td class=group_title background=images/tab_fond.gif><bean:message key ="customerservice.accountdetails"/></td>
                                <td><img height=19 alt="" src="images/tab_d.gif" width=8 border=0></td>
                              </tr>
                            </tbody>
                        </table></td>
                      </tr>
                      <tr>
                        <td bgcolor=#dce5ea><div 
                                style="FONT-SIZE: 1px">&nbsp;</div></td>
                      </tr>
                        <!-- this for showing the account info address --> 
                      <tr>
                        <td><table border="0" cellpadding="0" cellspacing="0" width="264">
                               <%--
			                 CustomerServiceDataBean objService = (CustomerServiceDataBean)((ArrayList)session.getAttribute("$CARDREPLACELIST$")).get(0); 
			                  Set custAccount = objService.getCustomerAccount();
			                  for(Iterator it =custAccount.iterator();it.hasNext();){
			 			       CustomerAccountDto objcustAccountDto = new CustomerAccountDto();
			 			       objcustAccountDto = (CustomerAccountDto)it.next();
			 			       request.setAttribute("accountobj",objcustAccountDto);
			                  }
			 	           --%>  
                           	 <tr> 
				                  <td class="desc_cell" nowrap ><bean:message key ="customerservice.currentcreditlimit"/></td>
				                  <td class="label"><bean:write name ="cardReplacementForm"  property="creditLimit" /> </td>
				             </tr>
				             
				              <%if(((String)request.getAttribute("ACTION")).equals("adjust")){%>	
				             <tr> 
				                  <td class="desc_cell" nowrap><b><bean:message key ="customerservice.cardlimit"/></b></td>
				                  <td class="label"><html:text   property="creditLimit" /> </td>
				             </tr>
				             <!-- <tr> 
				                  <td class="desc_cell" nowrap><b><bean:message key ="customerservice.cashlimit"/></b></td>
				                  <td class="label"><html:text property="cashLimit" /></td>
				             </tr>
				              -->
				             <tr> 
				                  <td class="desc_cell" nowrap><b><bean:message key ="customerservice.originallimitbackdate"/></b></td>
				                  <td class="label"><html:text property="orgBackDate" />(DD/MM/YYYY)</td>
				             </tr>
				             <%}%>                 
                           
										 
                        </table></td>
                                              
                      </tr>
                    </tbody>
                  </table></td>
                  
                                   
               <td class="form_bgcolor" valign="top" style="padding: 20px 20px 10px 20px;"><table cellspacing=0 cellpadding=0 
                              border=0>
                    <tbody>
                      <tr>
                        <td><table cellspacing=0 cellpadding=0 border=0>
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
                        <td height="109"><table border="0" cellpadding="0" cellspacing="0" width="264" height="109">
                              <tr> 
                                 <td class="desc_cell" nowrap ><bean:message key ="customerservice.address1"/></td>
                                 <td class="label"><bean:write name ="cardReplacementForm"  property="requestAddress.address1" /></td>
                            </tr>
							<tr> 
                                <td class="desc_cell" nowrap ><bean:message key ="customerservice.address2"/></td>
                                <td class="label"><bean:write name ="cardReplacementForm"  property="requestAddress.address2" /></td>
                            </tr>
                            <tr> 
                                <td class="desc_cell" nowrap ><bean:message key ="customerservice.city"/></td>
                                <td class="label"><bean:write name ="cardReplacementForm"    property="requestAddress.city"/></td>
                            </tr>
							<tr> 
                               <td class="desc_cell" nowrap ><bean:message key ="customerservice.state"/></td>
                               <td class="label"><bean:write name ="cardReplacementForm"   property="requestAddress.state" /></td>
                            </tr>
                            <tr> 
                               <td class="desc_cell" nowrap ><bean:message key ="customerservice.country"/></td>
                               <td class="label">
                                    <html:select  property = "requestAddress.country" disabled="true">
                                       <html:option value="">SelectCountry</html:option>	
							   	       <html:optionsCollection property="countryList" value="key" label="value" /> 	
							       </html:select></td>
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
							 
						   <tr> 
			                    <td class="desc_cell" nowrap><bean:message key ="customerservice.remarks"/></td>
			                    <td class="label"><html:textarea property="remarks" rows="3"></html:textarea></td>
			                </tr>
                        </table></td>
                       </tr>                             
                 </tbody>
                  </table></td>
                
                  <td background="images/tbl_d.gif"></td> 
                </tr>                
                <tr> 
                  <td background="images/tbl_g.gif"></td>
                  <td class="form_bgcolor"  colspan="2"> 
                    <div align="right">
                      
             <%
		         if(request.getAttribute("ACTION") == null || ((String)request.getAttribute("ACTION")).equals("search"))
		            {%>		
 		 				
 			      <html:button property="submitbutton" onclick="go('cardLimitAdd')"><bean:message key ="common.save"/></html:button>
 			      <html:button property="submitbutton" onclick="showList('cardlimitlist.do?method=checkSession')"><bean:message key ="common.cancel"/></html:button>
 			     <% }else if(((String)request.getAttribute("ACTION")).equals("adjust"))	
        				    { %>
               <html:button property="submitbutton" onclick="go('cardLimitAccept')"><bean:message key ="common.accept"/></html:button>
               <html:button property="submitbutton" onclick="go('cardLimitReject')"><bean:message key ="common.reject"/></html:button>
               <a href="javascript:customerHistory('cardlimitlist.do?method=customerHistory&NricNo=<bean:write name ="custServObj"  property="idNumber" />')"><bean:message key ="common.history"/></a>
               <a href="javascript:cardActivitiesInfo('cardlimitlist.do?method=cardActivities&CustServCardNo=<bean:write name ="custServObj"  property="cardNumber" />')"><bean:message key ="common.cardactivity"/></a>
                <html:button property="submitbutton" onclick="showList('cardlimitprocess.do?method=checkSession')"><bean:message key ="common.cancel"/></html:button>
       	     <%}else if(((String)request.getAttribute("ACTION")).equals("cancel")){//closing the scriplet%>
          	      <html:button property="submitbutton" onclick="showList('cardlimitprocess.do?method=checkSession')"><bean:message key ="common.cancel"/></html:button>
         	  <%} %>
                    </div>				  </td>
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
     </logic:iterate>   
 </logic:present>   
    </html:form>     
    </body>
</html:html>        