<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/displaytag-12.tld" prefix="display" %>

<html:html >
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
                  <td> <img src="images/form_tab_start_off.gif" border="0" alt="" width="5" height="22"></td>
                  <td colspan="2" background="images/tbl_haut.gif"><table border="0" cellpadding="0" cellspacing="0">
                    <tr>
                      <td class="form_tab_off" background="images/form_tab_bg_off.gif" style="padding-left:15px"><html:link action="csrprocess.do?method=csrSummary"><bean:message key ="customerrelation.summary" /></html:link></td>
                      <td><img height=22 alt="" src="images/form_tab_btw_off_on.gif" width=25 border=0></td>
                       <td background="images/form_tab_bg_off.gif" class="form_tab_off"><html:link action="csrprocess.do?method=customerInfo"><bean:message key ="customerrelation.customerinfo"/></html:link> </td>
                      <td><img height=22 alt="" src="images/form_tab_btw_on_off.gif" width=25 border=0></td>
                      <td background="images/form_tab_bg_off.gif" class="form_tab_off"><html:link action="csrprocess.do?method=cardDetails"><bean:message key ="customerrelation.carddetails"/> </html:link> </td>
                      <td><img height=22 alt="" src="images/form_tab_btw_off_off.gif" width=25 border=0></td>
                      <td background="images/form_tab_bg_on.gif" nowrap class="form_tab_on"><bean:message key ="customerrelation.accountdetails"/></td>
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
     <logic:present name="SEARCHLIST">         
        <logic:iterate id ="commonObj" name ="SEARCHLIST">
                
                <tr> 
                  <td background="images/tbl_g.gif"></td>
                  <td class="form_bgcolor" valign="top" style="padding: 20px 20px 10px 20px;"><table border="0" cellpadding="0" cellspacing="0">
                    <tr>
                      <td class="desc_cell" nowrap><bean:message key ="customerrelation.creditlimit"/></td>
                      <td nowrap class="label"><bean:write name ="commonObj"  property="column1" /></td>
                    </tr>
                    <tr>
                      <td class="desc_cell" nowrap><bean:message key ="customerrelation.cashlimit"/> </td>
                      <td nowrap class="label"><bean:write name ="commonObj"  property="column2" /></td>
                    </tr>
                    <tr>
                      <td colspan="2" height="5"></td>
                    </tr>
                    <tr>
                      <td class="desc_cell" nowrap><bean:message key ="customerrelation.previousblanace"/></td>
                      <td nowrap class="label"><bean:write name ="commonObj"  property="column3" /></td>
                    </tr>
                    <tr>
                      <td nowrap class="desc_cell"><bean:message key ="customerrelation.availablebalance"/> </td>
                      <td nowrap class="label"><bean:write name ="commonObj"  property="column11" /></td>
                    </tr>
                    <tr>
                      <td nowrap class="desc_cell"><bean:message key ="customerrelation.outstandingbalance"/> </td>
                      <td nowrap class="label"><bean:write name ="commonObj"  property="column4" /></td>
                    </tr>
                    <tr>
                      <td colspan="2" height="5"></td>
                    </tr>
                    <!-- 
                    <tr>
                      <td nowrap class="desc_cell"><bean:message key ="customerrelation.amountcredited"/> </td>
                      <td nowrap class="label"><bean:write name ="commonObj"  property="column5" /></td>
                    </tr>
                    <tr>
                      <td nowrap class="desc_cell"><bean:message key ="customerrelation.amountdebited"/> </td>
                      <td nowrap class="label"><bean:write name ="commonObj"  property="column6" /></td>
                    </tr>
                    <tr>
                      <td nowrap class="desc_cell"><bean:message key ="customerrelation.authorisationamount"/></td>
                      <td nowrap class="label"><bean:write name ="commonObj"  property="column7" /></td>
                    </tr>
                    <tr>
                      <td nowrap class="desc_cell"><bean:message key ="customerrelation.laststatementdate"/></td>
                      <td nowrap class="label"><bean:write name ="commonObj"  property="column8" /></td>
                    </tr>
                    <tr>
                      <td nowrap class="desc_cell"><bean:message key ="customerrelation.laststatementduedate"/> </td>
                      <td nowrap class="label"><bean:write name ="commonObj"  property="column9" /></td>
                    </tr>
                    <tr>
                      <td nowrap class="desc_cell"><bean:message key ="customerrelation.minimumpaymentdue"/> </td>
                      <td nowrap class="label"><bean:write name ="commonObj"  property="column10" /></td>
                    </tr>
                     -->
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

  </logic:iterate>
 	   </logic:present>
     
</html:form>
</body>
</html:html>