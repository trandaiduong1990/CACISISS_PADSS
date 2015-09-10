<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/displaytag-12.tld" prefix="display" %>

<html:html locale="true">
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
                  <td> <img width="5" height="22" border="0" src="images/form_tab_start_on.gif" alt=""></td>
                  <td colspan="2" background="images/tbl_haut.gif"><table border="0" cellpadding="0" cellspacing="0">
                    <tr>
                      <td class="form_tab_on" background="images/form_tab_bg_on.gif" style="padding-left:15px"><bean:message key ="customerrelation.summary"/></td>
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
                  <td> <img width="5" height="22" border="0" src="images/tbl_haut_d.gif" alt=""></td>
                </tr>
                <tr>
                  <td background="images/tbl_g.gif"></td>
                  <td colspan="2" valign="top" class="form_bgcolor" >
                    <div align="right"></div>                  </td>
                  <td background="images/tbl_d.gif"></td>
                </tr>
    <logic:present name="SEARCHLIST">         
       <logic:iterate id ="commonObj" name ="SEARCHLIST">
                
                <tr>
                  <td background="images/tbl_g.gif"></td>
                  <td class="form_bgcolor" valign="top" style="padding: 20px 20px 10px 20px;"><table cellspacing=0 cellpadding=0
                              border=0>
                    <tbody>
                      <tr>
                        <td><table cellspacing=0 cellpadding=0 border=0>
                            <tbody>
                              <tr>
                                <td><img width="8" height="19" border="0" src="images/tab_g.gif" alt=""></td>
                                <td class=group_title background=images/tab_fond.gif><bean:message key ="customerrelation.customerdetails"/></td>
                                <td><img width="8" height="19" border="0" src="images/tab_d.gif" alt=""></td>
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
                              <td class="label"><bean:write name ="commonObj"  property="column1" /></td>
                            </tr>
                            <tr>
                              <td class="desc_cell" nowrap><bean:message key ="customerrelation.nameoncard"/></td>
                              <td class="label"><bean:write name ="commonObj"  property="column2" /> </td>
                            </tr>
                            <tr>
                              <td class="desc_cell" nowrap><bean:message key ="customerrelation.nircorpassport"/></td>
                              <td class="label"><bean:write name ="commonObj"  property="column3" /></td>
                            </tr>
                             <tr>
                              <td class="desc_cell" nowrap><bean:message key ="customerrelation.nircexpire"/></td>
                              <td class="label"><bean:write name ="commonObj"  property="column4" /></td>
                            </tr>
                            <tr>
                              <td class="desc_cell" nowrap height="2"><bean:message key ="customerrelation.dob"/></td>
                              <td height="2" class="label"><bean:write name ="commonObj"  property="column5" /></td>
                            </tr>
                            <tr>
                              <td nowrap class="desc_cell"><bean:message key ="customerrelation.companyname"/></td>
                              <td class="label"><bean:write name ="commonObj"  property="column6" /></td>
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
                                <td><img width="8" height="19" border="0" src="images/tab_g.gif" alt=""></td>
                                <td class=group_title background=images/tab_fond.gif><bean:message key ="customerrelation.carddetails"/></td>
                                <td><img width="8" height="19" border="0" src="images/tab_d.gif" alt=""></td>
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
                              <td class="desc_cell" nowrap><bean:message key ="customerrelation.cardnumber"/> </td>
                              <td class="label"><bean:write name ="commonObj"  property="column22" /></td>
                            </tr>
                            <tr>
                              <td class="desc_cell" nowrap><bean:message key ="customerrelation.cardproductname"/> </td>
                              <td class="label"><bean:write name ="commonObj"  property="column8" /> </td>
                            </tr>
                            <tr>
                              <td class="desc_cell" nowrap><bean:message key ="customerrelation.creditlimit"/></td>
                              <td class="label"><bean:write name ="commonObj"  property="column9" /></td>
                            </tr>
                            <tr>
                              <td class="desc_cell" nowrap height="2"><bean:message key ="customerrelation.cardexpire"/></td>
                              <td height="2" class="label"><bean:write name ="commonObj"  property="column10" /></td>
                            </tr>
                            <tr>
                              <td nowrap class="desc_cell"><bean:message key ="customerrelation.cardstatus"/></td>
                              <td class="label"><bean:write name ="commonObj"  property="column11" /></td>
                            </tr>
                            <tr>
                              <td nowrap class="desc_cell"><bean:message key ="customerrelation.cardholdertype"/> </td>
                              <td class="label"><bean:write name ="commonObj"  property="column12" /></td>
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
                                <td><img width="8" height="19" border="0" src="images/tab_g.gif" alt=""></td>
                                <td class=group_title background=images/tab_fond.gif><bean:message key ="customerrelation.billingaddress"/> </td>
                                <td><img width="8" height="19" border="0" src="images/tab_d.gif" alt=""></td>
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
                              <td class="label"><bean:write name ="commonObj"  property="column13" /></td>
                            </tr>
                            <tr>
                              <td class="desc_cell" nowrap><bean:message key ="customerrelation.address2"/> </td>
                              <td class="label"><bean:write name ="commonObj"  property="column14" /> </td>
                            </tr>
                            <tr>
                              <td nowrap class="desc_cell"><bean:message key ="customerrelation.country"/></td>
                              <td class="label"><bean:write name ="commonObj"  property="column17" /></td>
                            </tr>
                            <tr>
                              <td class="desc_cell" nowrap height="2"><bean:message key ="customerrelation.state"/></td>
                              <td height="2" class="label"><bean:write name ="commonObj"  property="column16" /></td>
                            </tr>
                            <tr>
                              <td class="desc_cell" nowrap><bean:message key ="customerrelation.city"/></td>
                              <td class="label"><bean:write name ="commonObj"  property="column15" /></td>
                            </tr>
                            <tr>
                              <td class="desc_cell" nowrap><bean:message key ="customerrelation.township"/></td>
                              <td class="label"><bean:write name ="commonObj"  property="column21" /></td>
                            </tr>
                            <tr>
                              <td nowrap class="desc_cell"><bean:message key ="customerrelation.postalcode"/> </td>
                              <td class="label"><bean:write name ="commonObj"  property="column18" /></td>
                            </tr>
                           
                        </table></td>
                      </tr>
                    </tbody>
                  </table></td>
                  <td class="form_bgcolor" valign="top" style="padding: 20px 20px 10px 20px;">&nbsp;</td>
                  <td background="images/tbl_d.gif"></td>
                </tr>
                <tr>
                  <td> <img width="5" height="5" border="0" src="images/tbl_bas_g.gif" alt=""></td>
                  <td background="images/tbl_bas.gif"></td>
                  <td background="images/tbl_bas.gif"></td>
                  <td> <img width="5" height="5" border="0" src="images/tbl_bas_d.gif" alt=""></td>
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
