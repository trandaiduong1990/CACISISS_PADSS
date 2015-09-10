<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<html:html>
<head>
<title><bean:message key ="customerservice.cardactivate"/></title>
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
///to call csrDispatchAction to show all the data as popup window
  function viewAllInfo(urlToCall){
	  	var left = (screen.width/2)-(400);
	  	var top = (screen.height/2)-(250);
	 	window.open(urlToCall, "CustServiceAllData",'resizable =yes, status=yes,scrollbars=yes,menubar=no height=500,width=800,location=no, top='+top+', left='+left);
	  }
   ///to call  actions when click on cancel button
  function showList(){
    document.forms[0].action ="cardactivateprocess.do?method=checkSession";
    document.forms[0].submit();
    
   }
</script>
<body bgcolor="ffffff">
   <html:form action="accountchangeprocess.do">
 <input type="hidden" name="method"/>
 
   <!-- this for showing the search condition-->
 <logic:notPresent name="$CARDREPLACELIST$">
 
  <table border="0" cellspacing="0" cellpadding="0" style="border-collapse: collapse" bordercolor="#111111" width="100%">
  <tr>
    <td> 
      <table border="0" cellspacing="0" cellpadding="0" width="100%">
        <tr>
            <td class="titreSection"><bean:message key ="accountChange.screentitle"/></td>
        </tr>
        <tr>
          <td background="images/ligne.gif" colspan="2"> <img src="images/spacer.gif" width="1" height="1"></td>
        </tr>
        <tr>
          <td class="titreSection">
              <table border="0" cellspacing="2" cellpadding="2" width="157" align="left">
                <tr> 
                <td class="texteMenuGauche"> 
                  <html:button property="submitbutton" onclick="go('search')"><bean:message key ="common.search"/></html:button>
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
                        <td> <font class="label"><b><bean:message key ="accountChange.cardno"/></b></font> 
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
   
     <table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr> 
      <td> 
        <table border="0" cellspacing="0" cellpadding="0">
        <tr> 
            <td class="titreSection" width="519"><bean:message key ="accountChange.screentitle"/></td>
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
						<bean:message key ="accountChange.screentitle2"/>                    </td>
                        <td><img src="images/form_tab_end_on.gif" width="25" height="22"></td>
                      </tr>
                    </table>                  </td>
                  <td> <img src="images/tbl_haut_d.gif" border="0" alt="" width="5" height="22"></td>
                </tr>
                <tr> 
                  <td background="images/tbl_g.gif"></td>
                  <td class="form_bgcolor"  colspan="2"> 
                    <div align="right">
           		<logic:equal value="Y" property="butEnable" name="accountChangeForm">
           			<html:button property="accountChange" onclick="go('accountChange')"><bean:message key ="common.update"/></html:button>
           		</logic:equal>
                    </div>
                  </td>
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
                                <td class=group_title background=images/tab_fond.gif><bean:message key ="accountChange.screentitle2"/></td>
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
                        	<table border="0" cellpadding="0" cellspacing="0" height="72">
                           <tr bordercolor="#111111">
                                        <td class="desc_cell"><bean:message key ="accountChange.cardno"/></td>
                                        <td class="desc_cell"><bean:write name="accountChangeForm" property="cardNumber"></bean:write></td>
                                      </tr>
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
                                        <td nowrap >
                                        	<html:text property="autoPayAccount" size="20" maxlength="20"/>
                                        	<html:checkbox property ="autoPayAccounton" />
                                        	<html:hidden property="autoPayAccounton" value="false"/>
                                        </td>
                                      </tr>
                                      <tr bordercolor="#111111">
                                        <td class="desc_cell" nowrap="nowrap"><bean:message key ="applicationform.autopaymentdisable"/></td>
                                        <td nowrap >
                                        	<html:checkbox property ="autoPayDisable" />
                                        	<html:hidden property="autoPayDisable" value="false"/>
                                        </td>
                                      </tr>
                                      <tr bordercolor="#111111">
	                                    <td nowrap class="desc_cell" colspan="2"><strong>
	                                      <html:checkbox property ="atmLink" />
	                                      <html:hidden property="atmLink" value="false"/>
	                                     <bean:message key ="accountChange.atmlinkdetails"/></strong></td>
	                                    </tr>
                                      <tr bordercolor="#111111">
                                        <td class="desc_cell"><bean:message key ="applicationform.savingaccount"/></td>
                                        <td><html:text property="savingAccount" size="20" maxlength="20"/></td>
                                      </tr>
                                      <tr bordercolor="#111111">
                                        <td class="desc_cell"><bean:message key ="applicationform.checkingaccount"/></td>
                                        <td><html:text property="currentAccount" size="20" maxlength="20"/></td>
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
                                                     
                        </table></td>
                      </tr>
                    </tbody>
                  </table></td>
                  <td valign="top" class="form_bgcolor" style="padding: 20px 20px 10px 20px;"><table width="264" 
                              border=0 cellpadding=0 cellspacing=0>
                    <tbody>
                      
                      
                    </tbody>
                  </table></td>
                  <td background="images/tbl_d.gif"></td>
                </tr>
                    
                <tr> 
                  <td background="images/tbl_g.gif"></td>
                  <td class="form_bgcolor"  colspan="2"> 
                    <div align="right">
           		<logic:equal value="Y" property="butEnable" name="accountChangeForm">
           			<html:button property="accountChange" onclick="go('accountChange')"><bean:message key ="common.update"/></html:button>
           		</logic:equal>
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
 </logic:present>   
    </html:form>     
    </body>
</html:html>        