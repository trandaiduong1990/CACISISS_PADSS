<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/displaytag-12.tld" prefix="display" %>

<html:html>
<html>
<head>
<title><bean:message key ="customerrelation.screentitle"/></title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="inc/css/style.css" type="text/css"></head>
<script>
	 function go(action) {
	  document.forms[0].method.value=action;
	  document.forms[0].submit();
	 }
 </script>
<body bgcolor="ffffff">
<html:form action="csrprocess">
 
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
                      <td><img height=22 alt="" src="images/form_tab_btw_off_on.gif" width=25 border=0></td>
                      <td background="images/form_tab_bg_on.gif" nowrap class="form_tab_on"><bean:message key ="customerrelation.customerinfo"/> </td>
                      <td><img height=22 alt="" src="images/form_tab_btw_on_off.gif" width=25 border=0></td>
                      <td background="images/form_tab_bg_off.gif" class="form_tab_off"><html:link action="csrprocess.do?method=cardDetails"><bean:message key ="customerrelation.carddetails"/> </html:link> </td>
                      <td><img height=22 alt="" src="images/form_tab_btw_off_off.gif" width=25 border=0></td>
                      <td background="images/form_tab_bg_off.gif" class="form_tab_off"><html:link action="csrprocess.do?method=accountInfo"><bean:message key ="customerrelation.accountdetails"/></html:link> </td>
                      <td><img height=22 alt="" src="images/form_tab_btw_off_off.gif" width=25 border=0></td>
                      <td background="images/form_tab_bg_off.gif" class="form_tab_off"><html:link action="csrprocess.do?method=cardActivities"><bean:message key ="customerrelation.cardactivity"/></html:link> </td>
                      <td><img height=22 alt="" src="images/form_tab_btw_off_off.gif" width=25 border=0></td>
                      <td background="images/form_tab_bg_off.gif" class="form_tab_off"><html:link action="csrprocess.do?method=tranxHistory"><bean:message key ="customerrelation.cardtranxhistory"/></html:link> </td>
                      <td><img src="images/form_tab_end_off.gif" width="25" height="22"></td>
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
              
                <logic:present name="custInfoObj">    
                
                <tr> 
                  <td background="images/tbl_g.gif"></td>
                  <td class="form_bgcolor" valign="top" style="padding: 20px 20px 10px 20px;"><table cellspacing=0 cellpadding=0 
                              border=0>
                    <tbody>
                      <tr>
                        <td><table cellspacing=0 cellpadding=0 border=0>
                            <tbody>
                              <tr>
                                <td><img height=19 alt="" src="images/tab_g.gif" width=8 border=0></td>
                                <td class=group_title background=images/tab_fond.gif><bean:message key ="customerrelation.customerdetails"/></td>
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
                              <td class="desc_cell" nowrap><bean:message key ="customerrelation.customername"/></td>
                              <td class="label"><bean:write name ="custInfoObj"  property="customerName" /></td>
                            </tr>
                            <tr>
                              <td class="desc_cell" nowrap><bean:message key ="customerrelation.nameoncard"/></td>
                              <td class="label"><bean:write name ="custInfoObj"  property="embossingName" /> </td>
                            </tr>
                            <tr>
                              <td class="desc_cell" nowrap height="2"><bean:message key ="customerrelation.customertype"/></td>
                              <td class="label"><bean:write name ="custInfoObj"  property="customerTypeId" /> </td>
                            </tr>
                            <tr>
                              <td class="desc_cell" nowrap height="2"><bean:message key ="customerrelation.dob"/></td>
                              <td height="2" class="label"><bean:write name ="custInfoObj"  property="strDob" /></td>
                            </tr>
                            <tr>
                              <td class="desc_cell" nowrap><bean:message key ="customerrelation.nircorpassport"/></td>
                              <td class="label"><bean:write name ="custInfoObj"  property="idNumber" /></td>
                            </tr>
                             <tr>
                              <td class="desc_cell" nowrap><bean:message key ="customerrelation.nircexpire"/></td>
                              <td class="label"><bean:write name ="custInfoObj"  property="strExpDate" /></td>
                            </tr>
                            
                            <tr>
                              <td nowrap class="desc_cell"><bean:message key ="customerrelation.gender"/></td>
                              <td class="label"><bean:write name ="custInfoObj"  property="gender" /></td>
                            </tr>
                              <tr>
                              <td nowrap class="desc_cell"><bean:message key ="customerrelation.nationality"/></td>
                              <td class="label"><bean:write name ="custInfoObj"  property="nationality" /></td>
                            </tr>                                                            
                           <tr>
                              <td nowrap class="desc_cell"><bean:message key ="customerrelation.maritalstatus"/></td>
                              <td class="label"><bean:write name ="custInfoObj"  property="maritalStatus" /></td>
                            </tr>
                           <tr>
                              <td nowrap class="desc_cell"><bean:message key ="customerrelation.billingto"/></td>
                              <td class="label"><bean:write name ="custInfoObj"  property="billingTo" /></td>
                            </tr>
                           <tr>
                              <td nowrap class="desc_cell"><bean:message key ="customerrelation.email"/></td>
                              <td class="label"><bean:write name ="custInfoObj"  property="email" /></td>
                            </tr>
                           <tr>
                              <td nowrap class="desc_cell"><bean:message key ="customerrelation.income"/></td>
                              <td class="label"><bean:write name ="custInfoObj"  property="income" /></td>
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
                                <td class=group_title background=images/tab_fond.gif><bean:message key ="customerrelation.companydetails"/></td>
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
                              <td class="desc_cell" nowrap><bean:message key ="customerrelation.companyname"/>  </td>
                              <td class="label"><bean:write name ="custInfoObj"  property="referenceCompany" /></td>
                            </tr>           
                             <tr>
                              <td class="desc_cell" nowrap><bean:message key ="customerrelation.address1"/>  </td>
                              <td class="label"><bean:write name ="custInfoObj"  property="companyAddress.address1" /></td>
                            </tr>
                            <tr>
                              <td class="desc_cell" nowrap><bean:message key ="customerrelation.address2"/> </td>
                              <td class="label"><bean:write name ="custInfoObj"  property="companyAddress.address2" /> </td>
                            </tr>
                            <tr>
                              <td nowrap class="desc_cell"><bean:message key ="customerrelation.country"/></td>
                              <td class="label"><bean:write name ="custInfoObj"  property="companyAddress.country" /></td>
                            </tr>
                            <tr>
                              <td class="desc_cell" nowrap height="2"><bean:message key ="customerrelation.state"/></td>
                              <td height="2" class="label"><bean:write name ="custInfoObj"  property="companyAddress.state" /></td>
                            </tr>
                            <tr>
                              <td class="desc_cell" nowrap><bean:message key ="customerrelation.city"/></td>
                              <td class="label"><bean:write name ="custInfoObj"  property="companyAddress.city" /></td>
                            </tr>
                            <tr>
                              <td class="desc_cell" nowrap><bean:message key ="customerrelation.township"/></td>
                              <td class="label"><bean:write name ="custInfoObj"  property="companyAddress.township" /></td>
                            </tr>
                            <tr>
                              <td nowrap class="desc_cell"><bean:message key ="customerrelation.postalcode"/> </td>
                              <td class="label"><bean:write name ="custInfoObj"  property="companyAddress.postalCode" /></td>
                            </tr>
                           <tr>
                              <td nowrap class="desc_cell"><bean:message key ="customerrelation.phone"/></td>
                              <td class="label"><bean:write name ="custInfoObj"  property="companyAddress.phone" /></td>
                            </tr>
                            <tr>
                              <td nowrap class="desc_cell"><bean:message key ="customerrelation.fax"/> </td>
                              <td class="label"><bean:write name ="custInfoObj"  property="companyAddress.fax" /></td>
                            </tr>
                        </table></td>
                      </tr>
                    </tbody>
                  </table></td>
                  <td background="images/tbl_d.gif"></td>
                </tr>
                <tr> 
                  <td background="images/tbl_g.gif"></td>
                  <td class="form_bgcolor" valign="top" style="padding: 20px 20px 10px 20px;"><table cellspacing=0 cellpadding=0 
                              border=0>
                    <tbody>
                      <tr>
                        <td><table cellspacing=0 cellpadding=0 border=0>
                            <tbody>
                              <tr>
                                <td><img height=19 alt="" src="images/tab_g.gif" width=8 border=0></td>
                                <td class=group_title background=images/tab_fond.gif><bean:message key ="customerrelation.billingaddress"/> </td>
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
                            <td class="label"><bean:write name ="custInfoObj"  property="homeAddress.address1" /></td>
                          </tr>
                          <tr>
                            <td class="desc_cell" nowrap><bean:message key ="customerrelation.address2"/> </td>
                            <td class="label"><bean:write name ="custInfoObj"  property="homeAddress.address2" /> </td>
                          </tr>  
                            <tr>
                              <td nowrap class="desc_cell"><bean:message key ="customerrelation.country"/></td>
                              <td class="label"><bean:write name ="custInfoObj"  property="homeAddress.country" /></td>
                            </tr>
                            <tr>
                              <td class="desc_cell" nowrap height="2"><bean:message key ="customerrelation.state"/></td>
                              <td height="2" class="label"><bean:write name ="custInfoObj"  property="homeAddress.state" /></td>
                            </tr>
                           <tr>
                              <td class="desc_cell" nowrap><bean:message key ="customerrelation.city"/></td>
                              <td class="label"><bean:write name ="custInfoObj"  property="homeAddress.city" /></td>
                            </tr>
                           <tr>
                              <td class="desc_cell" nowrap><bean:message key ="customerrelation.township"/></td>
                              <td class="label"><bean:write name ="custInfoObj"  property="homeAddress.township" /></td>
                            </tr>
                            <tr>
                              <td nowrap class="desc_cell"><bean:message key ="customerrelation.postalcode"/> </td>
                              <td class="label"><bean:write name ="custInfoObj"  property="homeAddress.postalCode" /></td>
                            </tr>
                               
                        </table></td>
                      </tr>
                    </tbody>
                  </table></td>
                  <td class="form_bgcolor" valign="top" style="padding: 20px 20px 10px 20px;">&nbsp;</td>
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