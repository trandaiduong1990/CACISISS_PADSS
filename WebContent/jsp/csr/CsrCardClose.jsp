<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/displaytag-12.tld" prefix="display" %>

<html:html>
<head>
<title><bean:message key ="customerrelation.screentitle"/></title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="inc/css/style.css" type="text/css">
</head>

  <script>
	 function go(action) {
	  document.forms[0].method.value=action;
	  document.forms[0].submit();
	 }
	 </script>

<body bgcolor="ffffff">

<html:form action="csrcardclose.do">
   <!--  hiddeen fields to insert the data in varioues tables-->
  <input type="hidden" name="method"/>
  <html:hidden property="issuerId" value="<%=(String)session.getAttribute("ISSUER")%>"/>
  <html:hidden property="userId" value="<%=(String)session.getAttribute("USERID")%>"/>
      <html:hidden property="cardNumber" value="<%=(String)session.getAttribute("CARDNUMBER")%>"/>
         <html:hidden property="idNumber" value="<%=(String)session.getAttribute("NRICNO")%>"/>
  <table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr> 
      <td>&nbsp;</td>
      <td valign="top">
        <table width="100%" height="230">
          <tr> 
            <td valign="top" height="323"> 
              <table border="0" cellpadding="0" cellspacing="0" align="left">
                <tr> 
                  <td> <img src="images/form_tab_start_off.gif" border="0" alt="" width="5" height="22"></td>
                  <td colspan="2" background="images/tbl_haut.gif"><table border="0" cellpadding="0" cellspacing="0">
                    <tr>
                       <td class="form_tab_off" background="images/form_tab_bg_off.gif" style="padding-left:15px"><html:link action="csrprocess.do?method=csrSummary"><bean:message key ="customerrelation.summary" /></html:link></td>
                      <td><img width="25" height="22" border="0" src="images/form_tab_btw_on_off.gif" alt=""></td>
                      <td background="images/form_tab_bg_off.gif" nowrap class="form_tab_off"><html:link action="csrprocess.do?method=customerInfo"><bean:message key ="customerrelation.customerinfo"/></html:link> </td>
                      <td><img width="25" height="22" border="0" src="images/form_tab_btw_off_off.gif" alt=""></td>
                      <td background="images/form_tab_bg_off.gif" class="form_tab_off"><html:link  action="csrprocess.do?method=cardDetails"><bean:message key ="customerrelation.carddetails"/> </html:link> </td>
                      <td><img width="25" height="22" border="0" src="images/form_tab_btw_off_off.gif" alt=""></td>
                      <td background="images/form_tab_bg_off.gif" class="form_tab_off"><html:link action="csrprocess.do?method=accountInfo"><bean:message key ="customerrelation.accountdetails"/></html:link> </td>
                      <td><img width="25" height="22" border="0" src="images/form_tab_btw_off_off.gif" alt=""></td>
                      <td background="images/form_tab_bg_off.gif" class="form_tab_off"><html:link action="csrprocess.do?method=cardActivities"><bean:message key ="customerrelation.cardactivity"/></html:link> </td>
                      <td><img width="25" height="22" border="0" src="images/form_tab_btw_off_off.gif" alt=""></td>
                      <td background="images/form_tab_bg_off.gif" class="form_tab_off"><html:link action="csrprocess.do?method=tranxHistory"><bean:message key ="customerrelation.cardtranxhistory"/></html:link> </td>
                      <td><img width="25" height="22" src="images/form_tab_end_off.gif"></td>
                    </tr>
                  </table></td>
                  <td> <img src="images/tbl_haut_d.gif" border="0" alt="" width="5" height="22"></td>
                </tr>
                <tr> 
                  <td background="images/tbl_g.gif"></td>
                  <td colspan="2" valign="top" class="form_bgcolor" > 
                    <div align="right"></div>                  </td>
                  <td background="images/tbl_d.gif"></td>
                </tr>
                <tr> 
                  <td background="images/tbl_g.gif"></td>
                  <td colspan="2" valign="top" class="form_bgcolor" style="padding: 20px 20px 10px 20px;"><table cellspacing=0 cellpadding=0 
                              border=0>
                    <tbody>   <font color="#FF0000"> <html:errors/> </font>
                      <tr>
                        <td><table cellspacing=0 cellpadding=0 border=0>
                            <tbody>
                              <tr>
                                <td><img height=19 alt="" src="images/tab_g.gif" width=8 border=0></td>
                                <td class=group_title background=images/tab_fond.gif><bean:message key ="customerrelation.cardclose"/></td>
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
                             <tr>
                              <td class="desc_cell" nowrap><bean:message key ="customerrelation.address1"/>  </td>
                              <td class="label"><html:text  property="requestAddress.address1" size="20"/></td>
                            </tr>
                            <tr>
                              <td class="desc_cell" nowrap><bean:message key ="customerrelation.address2"/> </td>
                              <td class="label"><html:text   property="requestAddress.address2" size="20" /></td>
                            </tr>
                            <tr>
                              <td class="desc_cell" nowrap><bean:message key ="customerrelation.city"/></td>
                              <td class="label"><html:text   property="requestAddress.city" size="20"/></td>
                            </tr>
                            <tr>
                              <td class="desc_cell" nowrap height="2"><bean:message key ="customerrelation.state"/></td>
                              <td height="2" class="label"><html:text  property="requestAddress.state" size="20"/></td>
                            </tr>
                            <tr>
                              <td nowrap class="desc_cell"><bean:message key ="customerrelation.country"/></td>
                              <td class="label"> 
                                 <html:select  property = "requestAddress.country" name = "cardReplacementForm" >
                                     <html:option value="">SelectCountry</html:option>	
							     	 <html:optionsCollection name = "cardReplacementForm" property="countryList" value="key" label="value" /> 	
							     </html:select>
							  </td>
                            </tr>
                            <tr> 
                               <td class="desc_cell" nowrap><bean:message key ="customerrelation.postalcode"/></td>
                               <td  class="label"><html:text  property="requestAddress.postalCode" size="20" /></td>
                            </tr>
							<tr>
							   <td class="desc_cell"  nowrap><bean:message key ="customerrelation.phone"/></td>
							   <td  class="label"><html:text property="requestAddress.phone" size="20" /></td>
                            </tr>
                            <tr> 
                                <td class="desc_cell"  nowrap><bean:message key ="customerrelation.fax"/></td>
                                <td  class="label"><html:text  property="requestAddress.fax" size="20"/></td>
							</tr>
						    <tr> 
		                        <td class="desc_cell"  nowrap><bean:message key ="customerrelation.remarks"/></td>
		                        <td  class="label"> <html:text  property="remarks" /> </td>
		                    </tr>
		                       
		                   
                        </table></td>
                      </tr>
                    </tbody>
                  </table></td>
                  <td background="images/tbl_d.gif"></td>
                </tr>
                <tr>
                  <td background="images/tbl_g.gif"></td>
                  <td colspan="2" valign="top" class="form_bgcolor"><html:button property="submitbutton" onclick="go('cardCloseAdd')" > <bean:message key ="common.close"/></html:button></td>
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
