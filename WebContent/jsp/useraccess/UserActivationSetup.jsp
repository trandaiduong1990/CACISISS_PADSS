<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<html:html>
<head>
<title><bean:message key ="useractivationsetup.screentitle"/></title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="inc/css/style.css" type="text/css">
<script language="JavaScript" src="js/Checking.js"></script>
<script language="JavaScript" src="js/Transfer.js"></script>
<script language="JavaScript" src="js/IssuerUserSetup.js"></script>
<script language="JavaScript">
function go(method)
{
  document.forms[0].method.value=method;
  document.forms[0].submit();
}

function doCancel()
{
 document.forms[0].action="usersetuplistpage.do?userType=ISSADMIN&method=List";
 document.forms[0].submit();
}
</script>
</head>
<body bgcolor="ffffff" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
 <html:form action="usersetupprocess.do">
 <html:hidden property="issuerId" value="<%=(String)session.getAttribute("ISSUER")%>"/>
 <html:hidden property="userId" value="<%=(String)session.getAttribute("USERID")%>"/> 
 <input type=hidden name="method"/>
 <html:hidden property="userType"/>
 <html:hidden property="frmUserId"/>

  <table border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td>
        <table border="0" cellspacing="0" cellpadding="0">
        <tr> 
            <td class="titreSection" width="600"><bean:message key ="rolefunctionsetup.screentitle"/></td>
            <td class="titreSection" width="550">&nbsp; </td>
        </tr>
        <tr> 
            <td background="images/ligne.gif" colspan="3"> <img src="images/spacer.gif" width="1" height="1"></td>
        </tr>
	<tr>
	  <td class="titreSection"><table border="0" cellspacing="0" cellpadding="4">
	   <tr>
	    <td><b><html:button property="submitbutton" onclick="go('addNew')"><bean:message key ="common.addnew"/></html:button></b></td>	    	    
	   </tr>
	</table></td>
          <td class="texteMenuGauche"> 
              <p class="titreSection"></p>
          </td>                
              <td class="texteMenuGauche">&nbsp;<a href="#" target="">Help</a></td>                        
        </tr>	
        <tr> 
            <td background="images/ligne.gif" colspan="3"> <img src="images/spacer.gif" width="1" height="1"></td>
        </tr>
      </table>
      </td>
    </tr>

    <br>
    <tr>
      <td valign="top" class="ErrFONT"><font color="#FF0000"><html:errors /></font></td>
    </tr>

    <tr>
      <td valign="top"><br>
        <table width="430">
          <tr>
            <td valign="top">
              <table border="0" cellpadding="0" cellspacing="0" align="left">
                <tr>
                  <td> <img src="images/form_tab_start_off.gif" border="0" alt="" width="5" height="22"></td>
                  <td background="images/tbl_haut.gif" colspan="2">
                    <table border="0" cellpadding="0" cellspacing="0">
                      <tr>
                        <td class="form_tab_off" background="images/form_tab_bg_off.gif" style="padding-left:15px">
                        <html:link onclick="go('change')" href="#"><bean:message key ="usersetup.usersetupgrouptitle"/></html:link></td>
                        <td> <img height=22 alt="" src="images/form_tab_btw_off_on.gif" width=25 border=0></td>
                        <td background="images/form_tab_bg_on.gif" nowrap class="form_tab_on"><bean:message key ="usersetup.activation"/></td>
                        <td> <img src="images/form_tab_end_on.gif" border="0" alt="" width="25" height="22"></td>
                      </tr>
                    </table>
                  </td>
                  <td> <img src="images/tbl_haut_d.gif" border="0" alt="" width="5" height="22"></td>
                </tr>
                <tr> 
                  <td background="images/tbl_g.gif"></td>
                  <td class="form_bgcolor"  colspan="2"> 
                    <div align="right"> 
                    			
					 <%
					    if(request.getAttribute("ACTION") == null || ((String)request.getAttribute("ACTION")).equals("update"))
					    {%>
        					<html:button property="submitbutton" onclick="go('pwdUpdate')"><bean:message key ="common.activate"/></html:button>       	
        					<html:button property="submitbutton" onclick="doCancel()" ><bean:message key ="common.cancel"/></html:button>
        					
        				 <% }else if(((String)request.getAttribute("ACTION")).equals("cancel"))		        				   
        				 { %>  	
        					<html:button property="submitbutton" onclick="doCancel()" ><bean:message key ="common.cancel"/></html:button>
        				    <%}%>		
  					  
                    </div>                  
	         </td>
                  <td background="images/tbl_d.gif"></td>
                </tr>                
                
                <tr>
                  <td background="images/tbl_g.gif"></td>
                  <td class="form_bgcolor"  colspan="2">
                    <table border="0" cellspacing="0" cellpadding="0" width="100%">
                      <tr>
                        <td style="padding: 20px 20px 10px 20px;" valign="top">
                          <table style="BORDER-COLLAPSE: collapse"
                                bordercolor=#111111 cellspacing=0 cellpadding=0 border=0>
                            <tr>
                              <td class=desc_cell noWrap><bean:message key="usersetup.issuerid"/></td>
                              <td class="label"><bean:write name="userSetupForm" property="issuerId"/></td>
                              <td class=desc_cell noWrap><bean:message key="usersetup.issuername"/></td>
                              <td class="label"><bean:write name="userSetupForm" property="issuerName"/></td>
                            </tr>
                            <tbody>
                            <tr>
                              <td class=desc_cell noWrap><bean:message key="usersetup.userid"/></td>
                              <td class="label"><bean:write name="userSetupForm" property="frmUserId"/></td>
                              <td class=desc_cell noWrap><bean:message key="usersetup.username"/></td>
                              <td class="label"><bean:write name="userSetupForm" property="adminUserName"/></td>
                            </tr>
                            </tbody>
                          </table>
                        </td>
                      </tr>
                      <tr>
                        <td style="padding: 20px 20px 10px 20px;" valign="top">
                          <table cellspacing=0 cellpadding=0
                              border=0>
                            <tbody>
                            <tr>
                              <td>
                                <table cellspacing=0 cellpadding=0 border=0>
                                  <tbody>
                                  <tr>
                                    <td><img height=19 alt="" src="images/tab_g.gif" width=8 border=0></td>
                                    <td class=group_title background=images/tab_fond.gif><bean:message key ="usersetup.activation"/></td>
                                    <td><img height=19 alt="" src="images/tab_d.gif" width=8 border=0></td>
                                  </tr>
                                  </tbody>
                                </table>
                              </td>
                            </tr>
                            <tr>
                              <td bgcolor=#dce5ea>
                                <div
                                style="FONT-SIZE: 1px">&nbsp;</div>
                              </td>
                            </tr>
                            <tr>
                              <td>
                                <table style="BORDER-COLLAPSE: collapse"
                                bordercolor=#111111 cellspacing=0 cellpadding=0 border=0>
                                  <tr>
                                    <td class=desc_cell noWrap><bean:message key="useractivationsetup.newpassword"/></td>
                                    <td>
                                      <html:password property="frmPassword" maxlength="50" size="40"/>
                                    </td>
                                  </tr>
                                  <tbody>
                                  <tr>
                                    <td class=desc_cell noWrap><bean:message key="useractivationsetup.confirmpassword"/></td>
                                    <td>
                                      <html:password property="confirmPassword" maxlength="50" size="40"/>
                                    </td>
                                  </tr>
                                  </tbody>
                                </table>
                              </td>
                            </tr>
                            </tbody>
                          </table>
                        </td>
                      </tr>
                    </table>
                  </td>
                  <td background="images/tbl_d.gif"></td>
                </tr>
                <tr>
                  <td background="images/tbl_g.gif"></td>
                  <td class="form_bgcolor" colspan="2">&nbsp; </td>
                  <td background="images/tbl_d.gif"></td>
                </tr>
                <tr> 
                  <td background="images/tbl_g.gif"></td>
                  <td class="form_bgcolor"  colspan="2"> 
                    <div align="right"> 
                    			
					 <%
					    if(request.getAttribute("ACTION") == null || ((String)request.getAttribute("ACTION")).equals("update"))
					    {%>
        					<html:button property="submitbutton" onclick="go('pwdUpdate')"><bean:message key ="common.activate"/></html:button>       	
        					<html:button property="submitbutton" onclick="doCancel()" ><bean:message key ="common.cancel"/></html:button>
        					
        				 <% }else if(((String)request.getAttribute("ACTION")).equals("cancel"))		        				   
        				 { %>  	
        					<html:button property="submitbutton" onclick="doCancel()" ><bean:message key ="common.cancel"/></html:button>
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