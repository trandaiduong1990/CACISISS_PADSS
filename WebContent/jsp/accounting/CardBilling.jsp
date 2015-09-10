<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<html:html>
<head>
<title><bean:message key ="cardbilling.screentitle"/></title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="inc/css/style.css" type="text/css">
</head>
<script>
function go(method) 
{
  //document.forms[0].method.value=method;	
	document.forms[0].submit();
}
</script>
<body>
 <html:form  action="cardbillingprocess.do">
   <html:hidden property="issuerId" value="<%=(String)session.getAttribute("ISSUER")%>"/>
    <html:hidden property="userId" value="<%=(String)session.getAttribute("USERID")%>"/>
     <input type=hidden name="method"/>
 
  <table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr> 
      <td> 
        <table border="0" cellspacing="0" cellpadding="0">
        <tr> 
            <td class="titreSection" width="519"><bean:message key ="cardbilling.screentitle"/> </td>
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
          <tr> 
		   <td class="ErrFONT"><font color="#FF0000"><html:errors /></font></td>
		     </tr>
      </table>    </td>
  </tr>
  
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
						<bean:message key ="cardbilling.billingprocess"/>                      </td>
                        <td><img src="images/form_tab_end_on.gif" width="25" height="22"></td>
                      </tr>
                    </table>                  </td>
                  <td> <img src="images/tbl_haut_d.gif" border="0" alt="" width="5" height="22"></td>
                </tr>
                
                <tr>
                  <td background="images/tbl_g.gif"></td>
                </tr>
                <tr>
                  <td background="images/tbl_g.gif"></td>
                  <td colspan="2" valign="top" class="form_bgcolor" style="padding: 20px 20px 10px 20px;"><table border="0" cellpadding="0" cellspacing="0" width="264" height="109">
                    <tr>
                      <td nowrap>&nbsp;</td>
                      <td nowrap class="group_title"><bean:message key ="cardbilling.cyclenumber"/></td>
                      <td nowrap class="group_title"><bean:message key ="cardbilling.cycledate"/></td>
                    </tr>
                    <tr>
                      <td class="desc_cell" nowrap><bean:message key ="cardbilling.lastcyclecompleted"/> </td>
                      <td nowrap class="label"><bean:write name ="cardBillingForm" property="prevCycleNo"/></td>
                      <td nowrap class="label"><bean:write name ="cardBillingForm" property="prevCycleDate"/></td>
                    </tr>
         
                    <tr>
                      <td class="desc_cell"><bean:message key ="cardbilling.nextcycletopProcess"/></td>
                      <td class="label"><bean:write name ="cardBillingForm" property="nextCycleNo"/></td>
                      <td class="label"><bean:write name ="cardBillingForm" property="nextCycleDate"/></td>
                    </tr>
              <logic:present name="TODAYISBILLINGDATE">
                    <tr>
                      <td colspan="3" nowrap class="label"><bean:message key ="cardbilling.numberofaccounttoprocess"/>&nbsp;&nbsp;<strong><bean:write name ="cardBillingForm" property="numberOfAccounts"/></strong>&nbsp;&nbsp; 
                        <html:button property="submitbutton" onclick="go('process')"><bean:message key ="common.process"/></html:button></td>
                      </tr>
             </logic:present>
                    <tr>
                      <td colspan="3" nowrap class="style1"></td>
                    </tr>
        
                  </table></td>
                  <td background="images/tbl_d.gif"></td>
                </tr>
                                
                <tr> 
                  <td> <img src="images/tbl_bas_g.gif" border="0" alt="" width="5" height="5"></td>
                  <td background="images/tbl_bas.gif"></td>
                  <td background="images/tbl_bas.gif"></td>
                  <td> <img src="images/tbl_bas_d.gif" border="0" alt="" width="5" height="5"></td>
                </tr>
              </table>          </td>
        </tr>
      </table>    </td>
  </tr>
 </table>
 </html:form>     
    </body>
</html:html>     