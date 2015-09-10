<%@ page language="java" %> 
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %> 
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@page import="org.transinfo.cacis.common.CommonDataBean" %>
<html:html>
<head>
<title><bean:message key ="systemparameter.screentitle"/></title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="inc/css/style.css" type="text/css">
</head>
<script>
function go(method) 
{
  document.forms[0].method.value=method;	
  document.forms[0].submit();
}
</script>
<body>
 <html:form action="systemparamprocess.do">
 
<%--<html:hidden property="issuerId" value="<%=(String)session.getAttribute("ISSUER")"/>--%>
 <html:hidden property="userId" value="<%=(String)session.getAttribute("USERID")%>"/> 
 <input type=hidden name="method"/>
 
  <table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr> 
      <td> 
        <table border="0" cellspacing="0" cellpadding="0">
        <tr> 
            <td class="titreSection" width="519"><bean:message key ="systemparameter.screentitle"/></td>
            <td class="titreSection" width="484">&nbsp; </td>
        </tr>
        <tr> 
            <td background="images/ligne.gif" colspan="3"> <img src="images/spacer.gif" width="1" height="1"></td>
        </tr>
        <tr> 
            <td class="titreSection" width="519">&nbsp;</td>
            <td class="titreSection" width="484"> 
              <table border="0" cellspacing="2" cellpadding="2" align="right">
              <tr> 
                <td class="texteMenuGauche"> 
                    <p class="titreSection">

                    &nbsp;&nbsp;</p>
                    <td class="texteMenuGauche">&nbsp;<a href="#" target="">Help</a></td>                        
                </td>
                <td>&nbsp;</td>                
              </tr>
            </table>
          </td>
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
          <td valign="top"> 
              <table border="0" cellpadding="0" cellspacing="0" align="left">
                <tr> 
                  <td> <img src="images/form_tab_start_on.gif" border="0" alt="" width="5" height="22"></td>
                  <td background="images/tbl_haut.gif" colspan="2"> 
                    <table border="0" cellpadding="0" cellspacing="0">
                      <tr> 
                        <td class="form_tab_on" background="images/form_tab_bg_on.gif" style="padding-left:15px"> 
						<bean:message key ="systemparameter.grouptitle"/>                        </td>
                        <td><img src="images/form_tab_end_on.gif" width="25" height="22"></td>
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
					    if(request.getAttribute("ACTION") == null || ((String)request.getAttribute("ACTION")).equals("add"))
					    {%>
        					<html:button property="submitbutton" onclick="go('add')" ><bean:message key ="common.save"/></html:button>
        					
        				 <% }else if(((String)request.getAttribute("ACTION")).equals("update"))		
        				    { %>
        					<html:button property="submitbutton" onclick="go('update')"><bean:message key ="common.save"/></html:button>             					
       						
        				    <%}%>		
  					  
                    </div>                  
			      </td>
                  <td background="images/tbl_d.gif"></td>
                </tr>
                <tr>
                  <td background="images/tbl_g.gif"></td>
                </tr>
                <tr> 
                  <td background="images/tbl_g.gif"></td>
                  <td colspan="2" valign="top" class="form_bgcolor" style="padding: 20px 20px 10px 20px;">
                  <table cellspacing=0 cellpadding=0 border=0>
                    <tbody>
                      <tr>
                        <td><table cellspacing=0 cellpadding=0 border=0>
                            <tbody>
                              <tr>
                                <td><img height=19 alt="" src="images/tab_g.gif" border=0></td>
                                <td class=group_title background=images/tab_fond.gif><bean:message key ="systemparameter.grouptitle"/></td>
                                <td><img height=19 alt="" src="images/tab_d.gif" width=8 border=0></td>
                              </tr>
                            </tbody>
                        </table></td>
                      </tr>
                      <tr>
                        <td bgcolor=#dce5ea><div style="FONT-SIZE: 1px">&nbsp;</div></td>
                      </tr>                      
                      
                     <tr>
                        <td><table border="0" cellpadding="0" cellspacing="0">
                            <tr>
                              <td class="desc_cell" nowrap width="162"><b><bean:message key ="systemparameter.mailserveraddress"/></b></td>
                              <td valign="top" width="242"><html:text property="mailServerAddress" size="20"/></td>
                              <td>&nbsp;</td>                              
                            </tr>                            
                        </table></td>
                      </tr> 
                     <tr>
                        <td><table border="0" cellpadding="0" cellspacing="0">
                            <tr>
                              <td class="desc_cell" nowrap width="162"><b><bean:message key ="systemparameter.mailserverport"/></b></td>
                              <td valign="top" width="242"><html:text property="mailServerPort" size="20"/></td>
                              <td>&nbsp;</td>                              
                            </tr>                            
                        </table></td>
                      </tr> 
                     <tr>
                        <td><table border="0" cellpadding="0" cellspacing="0">
                            <tr>
                              <td class="desc_cell" nowrap width="162"><b><bean:message key ="systemparameter.fromname"/></b></td>
                              <td valign="top" width="242"><html:text property="fromName"  size="20"/></td>
                              <td>&nbsp;</td>                              
                            </tr>                            
                        </table></td>
                      </tr> 
                     <tr>
                        <td><table border="0" cellpadding="0" cellspacing="0">
                            <tr>
                              <td class="desc_cell" nowrap width="162"><b><bean:message key ="systemparameter.frommail"/></b></td>
                              <td valign="top" width="242"><html:text property="fromMail"  size="20"/></td>
                              <td>&nbsp;</td>                              
                            </tr>                            
                        </table></td>
                      </tr> 
                     <tr>
                        <td><table border="0" cellpadding="0" cellspacing="0">
                            <tr>
                              <td class="desc_cell" nowrap width="162"><b><bean:message key ="systemparameter.voiceport"/></b></td>
                              <td valign="top" width="242"><html:text property="voicePort"  size="20"/></td>
                              <td>&nbsp;</td>                              
                            </tr>                            
                        </table></td>
                      </tr> 
                     <tr>
                        <td><table border="0" cellpadding="0" cellspacing="0">
                            <tr>
                              <td class="desc_cell" nowrap width="162"><b><bean:message key ="systemparameter.serverport"/></b></td>
                              <td valign="top" width="242"><html:text property="serverPort"  size="20"/></td>
                              <td>&nbsp;</td>                              
                            </tr>                            
                        </table></td>
                      </tr> 
                     <tr>
                        <td><table border="0" cellpadding="0" cellspacing="0">
                            <tr>
                              <td class="desc_cell" nowrap width="162"><b><bean:message key ="systemparameter.datatimeout"/></b></td>
                              <td valign="top" width="242"><html:text property="dataTimeout"  size="20"/></td>
                              <td>&nbsp;</td>                              
                            </tr>                            
                        </table></td>
                      </tr>  
                      <%--
                     <tr>
                        <td><table border="0" cellpadding="0" cellspacing="0">
                            <tr>
                              <td class="desc_cell" nowrap width="162"><b><bean:message key ="systemparameter.noworkerthread"/></b></td>
                              <td valign="top" width="242"><html:text property="noWorkerThread"  size="20"/></td>
                              <td>&nbsp;</td>                              
                            </tr>                            
                        </table></td>
                      </tr> 
                       --%>
                       <html:hidden property="noWorkerThread" value="100"/>
                     <tr>
                        <td><table border="0" cellpadding="0" cellspacing="0">
                            <tr>
                              <td class="desc_cell" nowrap width="162"><b><bean:message key ="systemparameter.debuglevel"/></b></td>
                              <td valign="top" width="242"><html:text property="debugLevel"  size="20"/></td>
                              <td>&nbsp;</td>                              
                            </tr>                            
                        </table></td>
                      </tr> 
                      <tr>
                        <td><table border="0" cellpadding="0" cellspacing="0">
                            <tr>
                              <td class="desc_cell" nowrap width="162"><b><bean:message key ="systemparameter.renewalTimeInterval"/></b></td>
                              <td valign="top" width="242"><html:text property="renewalTimeInterval" maxlength="2"  size="20"/></td>
                              <td>&nbsp;</td>                              
                            </tr>                            
                        </table></td>
                      </tr>
                      <tr>
                        <td><table border="0" cellpadding="0" cellspacing="0">
                            <tr>
                              <td class="desc_cell" nowrap width="162"><b><bean:message key ="systemparameter.cardChangeCloseInterval"/></b></td>
                              <td valign="top" width="242"><html:text property="changeCardCloseInterval" maxlength="2"  size="20"/></td>
                              <td>&nbsp;</td>                              
                            </tr>                            
                        </table></td>
                      </tr>
                      <tr>
                        <td><table border="0" cellpadding="0" cellspacing="0">
                            <tr>
                              <td class="desc_cell" nowrap width="162"><b><bean:message key ="systemparameter.cardCloseInterval"/></b></td>
                              <td valign="top" width="242"><html:text property="cardClose" maxlength="2"  size="20"/></td>
                              <td>&nbsp;</td>                              
                            </tr>                            
                        </table></td>
                      </tr> 
                      <tr>
                        <td><table border="0" cellpadding="0" cellspacing="0">
                            <tr>
                              <td class="desc_cell" nowrap width="162"><b><bean:message key ="systemparameter.cardexpiredateextend"/></b></td>
                              <td valign="top" width="242"><html:text property="expireDateExtend" maxlength="2"  size="20"/></td>
                              <td>&nbsp;</td>                              
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
			if(request.getAttribute("ACTION") == null || ((String)request.getAttribute("ACTION")).equals("add"))
			{%>
			  <html:button property="submitbutton" onclick="go('add')" ><bean:message key ="common.save"/></html:button>
			  
			<% }else if(((String)request.getAttribute("ACTION")).equals("update"))		
			   { %>
			    <html:button property="submitbutton" onclick="go('update')"><bean:message key ="common.save"/></html:button>             					
			    			
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