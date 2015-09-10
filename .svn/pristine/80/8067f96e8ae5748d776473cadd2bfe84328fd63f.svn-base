<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ page import ="org.transinfo.cacis.formbean.customerservice.CustomerServiceDataBean" %>
<%@ page import ="org.transinfo.cacis.dto.cardproduction.*" %>
<%@ page import ="java.util.*" %>
<html:html>
<head>
<title><bean:message key ="customerservice.cardholderpayment"/></title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="inc/css/style.css" type="text/css">
<script src="inc/js/cacis.js"></script>
</head>
<script>
function go(action) 
{
  document.forms[0].method.value=action;

  if(action == 'cardHolderPayment'){
	  var r=confirm("<bean:message key ='areyousuretopay'/>");
	  if (r==true)
	  {
		document.forms[0].submit();
	  }
  }else{
  	document.forms[0].submit();
  }
}
///to call csrDispatchAction to show all the data as popup window
	function viewAllInfo(urlToCall){
	  window.open(urlToCall, "CustServiceAllData",'resizable =yes, status=yes,scrollbars=yes,menubar=no height=400,width=400,location=no');
	  }
  ///to call  actions when click on cancel button
  function showList(){
    document.forms[0].action ="cardpaymentprocess.do?method=checkSession";
    document.forms[0].submit();
    
   }	  
</script>

<body bgcolor="ffffff">
  <html:form action="cardpaymentprocess.do">
    <input type="hidden" name="method"/>
 
   <!-- this for showing the search condition-->
 <logic:notPresent name="$CARDDATALIST$">
 
  <table border="0" cellspacing="0" cellpadding="0" style="border-collapse: collapse" bordercolor="#111111" width="100%">
  <tr>
    <td> 
      <table border="0" cellspacing="0" cellpadding="0" width="100%">
        <tr>
            <td class="titreSection"><bean:message key ="customerservice.cardholderpayment"/></td>
        </tr>
        <tr>
          <td background="images/ligne.gif" colspan="2"> <img src="images/spacer.gif" width="1" height="1"></td>
        </tr>
        <tr>
          <td class="titreSection">
              <table border="0" cellspacing="2" cellpadding="2" width="157" align="left">
                <tr> 
                <td class="texteMenuGauche"> 
                  <html:button property="submitbutton" onclick="go('search')" ><bean:message key ="common.search"/></html:button> 
                 </td>
                
               </tr>
            </table>
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
    
 <logic:present name="$CARDDATALIST$">
     <!-- this is for setting   the values to hidden fileds if logic present-->
     
     <html:hidden property="issuerId" value="<%=(String)session.getAttribute("ISSUER")%>"/>
      <html:hidden property="userId" value="<%=(String)session.getAttribute("USERID")%>"/>
        <html:hidden property="cardNumber"/>
        
    <!-- this for showing  the all information-->
  <logic:iterate id ="custServObj" name ="$CARDDATALIST$">
   
     <table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr> 
      <td> 
        <table border="0" cellspacing="0" cellpadding="0">
        <tr> 
            <td class="titreSection" width="519"><bean:message key ="customerservice.cardholderpayment"/></td>
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
                 <logic:notEqual value="NOSAVE" name="cardHolderPaymentForm" property="butaction">
                  	<html:button property="submitbutton" onclick="go('cardHolderPayment')"><bean:message key ="common.save"/></html:button>&nbsp;
                  </logic:notEqual>
                   <html:button property="submitbutton" onclick="showList()"><bean:message key ="common.cancel"/></html:button>                  
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
                        <td height="21"><table border="0" cellpadding="0" cellspacing="0" width="264" height="109">
                               <%--
                     CustomerServiceDataBean objService = (CustomerServiceDataBean)((ArrayList)session.getAttribute("$CARDDATALIST$")).get(0); 
                     CustomerAccountDto objcustAccountDto =null;  
                     Set custAccount = objService.getCustomerAccount();
                  for(Iterator it =custAccount.iterator();it.hasNext();){
 			          objcustAccountDto = new CustomerAccountDto();
 			         objcustAccountDto = (CustomerAccountDto)it.next();
 			         request.setAttribute("accountobj",objcustAccountDto);
                  }
 	            --%>
 	                <!-- to store currenycode in settlement table -->
 	              
                 <html:hidden name="cardHolderPaymentForm" property="currencyCode" />
			      
			   <tr> 
			        <td class="desc_cell" nowrap ><bean:message key ="customerservice.cardlimit"/></td>
			        <td class="label"><bean:write name ="cardHolderPaymentForm"  property="creditLimit" /> </td>
			  </tr>
			  <tr> 
			         <td class="desc_cell" nowrap ><bean:message key ="customerservice.previousblanace"/></td>
			         <td class="label"><bean:write name ="custServObj"  property="prevBal" /></td>
			  </tr> 
			  <tr> 
			         <td class="desc_cell" nowrap ><bean:message key ="customerservice.previouspayment"/></td>
			         <td class="label"><bean:write name ="custServObj"  property="prevPayAmt" /></td>
			  </tr> 
			  <tr> 
			         <td class="desc_cell" nowrap ><bean:message key ="customerservice.statamt"/></td>
			         <td class="label"><bean:write name ="custServObj"  property="statAmt" /></td>
			  </tr> 
			  <tr> 
			         <td class="desc_cell" nowrap ><bean:message key ="customerservice.statminamt"/></td>
			         <td class="label"><bean:write name ="custServObj"  property="statMinAmt" /></td>
			  </tr>  
			  <tr> 
			         <td class="desc_cell" nowrap ><bean:message key ="customerservice.outstatamt"/></td>
			         <td class="label"><bean:write name ="custServObj"  property="outStatAmt" /></td>
			  </tr>  
			  <tr> 
			         <td class="desc_cell" nowrap ><bean:message key ="customerservice.outstatpurchaeamt"/></td>
			         <td class="label"><bean:write name ="custServObj"  property="outStatpurchaseAmt" /></td>
			  </tr>  
			  <tr> 
			         <td class="desc_cell" nowrap ><bean:message key ="customerservice.outstatcashamt"/></td>
			         <td class="label"><bean:write name ="custServObj"  property="outStatCashAmt" /></td>
			  </tr> 
			  <tr> 
			         <td class="desc_cell" nowrap ><bean:message key ="customerservice.duedate"/></td>
			         <td class="label"><bean:write name ="custServObj"  property="statDueDate" /></td>
			  </tr> 
			  <tr> 
			         <td class="desc_cell" nowrap ><bean:message key ="customerservice.cardpayment"/></td>
			         <td class="label"><html:text   property="amountCurr" /> </td>
			  </tr>       
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
                                 <td class="label"><bean:write name ="custServObj"  property="requestAddress.address1" /></td>
                            </tr>
							<tr> 
                                <td class="desc_cell" nowrap ><bean:message key ="customerservice.address2"/></td>
                                <td class="label"><bean:write name ="custServObj"  property="requestAddress.address2" /></td>
                            </tr>
                            <tr> 
                                <td class="desc_cell" nowrap ><bean:message key ="customerservice.city"/></td>
                                <td class="label"><bean:write name ="custServObj"    property="requestAddress.city"/></td>
                            </tr>
							<tr> 
                               <td class="desc_cell" nowrap ><bean:message key ="customerservice.state"/></td>
                               <td class="label"><bean:write name ="custServObj"   property="requestAddress.state" /></td>
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
                                <td class="desc_cell" nowrap ><bean:message key ="customerservice.postalcode"/></td>
                                <td class="label"><bean:write name ="custServObj"  property="requestAddress.postalCode"/></td>
                            </tr>
							<tr>
							   <td class="desc_cell"><bean:message key ="customerservice.phone"/></td>
							   <td class="label"><bean:write name ="custServObj"  property="requestAddress.phone"  /></td>
                            </tr>
                            <tr> 
                                <td class="desc_cell"><bean:message key ="customerservice.fax"/></td>
                                <td class="label"><bean:write name ="custServObj"   property="requestAddress.fax"/></td>
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
                 <logic:notEqual value="NOSAVE" name="cardHolderPaymentForm" property="butaction">
                 	<html:button property="submitbutton" onclick="go('cardHolderPayment')"><bean:message key ="common.save"/></html:button>&nbsp;
                 </logic:notEqual>
                  <html:button property="submitbutton" onclick="showList()"><bean:message key ="common.cancel"/></html:button>  
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