<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@page import="org.transinfo.cacis.common.CommonDataBean" %>
<html:html>
<HEAD>
<title><bean:message key ="adminloginsetup.screentitletab"/></title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="shortcut icon" href="images/icon/favicon.ico" />
<link rel="stylesheet" href="inc/css/style.css" type="text/css">
<script language=javascript>
function go(method)
{
  document.forms[0].method.value=method;
  document.forms[0].submit();
}
</script>
<script src="inc/js/cacis.js"></script>
</HEAD>
<BODY class=BODY>
<html:form action="adminloginprocess.do">
  <table>
  <tr><td valign="top" class="ErrFONT"><font color="#FF0000"><html:errors /></font></td></tr>
  </table>
<TABLE height="100%" cellSpacing=0 cellPadding=0 width="100%" border=0>
  <TBODY>
   <tr>
    <td vAlign="middle" align="center" height="332">
      <table cellspacing=0 cellpadding=0 border=0 height="136">
        <tbody>        
        <tr>
          <td height="173" >&nbsp;</td>
          <td bgcolor="#EBEFF3" style="border:solid 2px #6EA9EE" valign=top height="173">
            <table width="100%" border="0" cellspacing="5" cellpadding="5">
              <tr>
                <td class="ColumnFONT" ><bean:message key="adminloginsetup.screentitle"/> <br>
                  <hr>
                </td>
              </tr>
              <tr>
                <td height="114">
                  <table cellspacing=0 cellpadding=0 border=0 align="center">
                      <tbody>
                      <tr>
                        <td bgcolor=#dce5ea><div style="FONT-SIZE: 1px">&nbsp;</div></td>
                      </tr>
                     <tr>
		        <td class="desc_cell" nowrap ><b><bean:message key ="adminloginsetup.issuerid"/></b></td>
		        <td valign="top">
		      	<html:select property="issuerId" >
		      	  <html:option value=""></html:option>
		      	  <html:optionsCollection property="issuerList" value="key" label="value" />
		      	</html:select>
		        </td>
                     </tr>
                       <tr>
                         <td class="desc_cell" nowrap ><b><bean:message key ="adminloginsetup.userid"/></b></td>
                         <td valign="top">
                         	<!-- <html:text property="userId" value = "ASPSUPERADMIN" size="20" onkeypress="return disableEnterKey(event)"/> -->
                         	<html:text property="userId" size="20" onkeypress="return disableEnterKey(event)"/>
                         </td>
                         <td>&nbsp;</td>
                       </tr>
                       <tr>
                         <td class="desc_cell" nowrap ><b><bean:message key ="adminloginsetup.password"/></b></td>
                         <td valign="top">
                         	<!-- <html:password property="password" value = "PASSWORD4" size="20"/> -->
                         	<html:password property="password" size="20"/>
                         </td>
                         <td>&nbsp;</td>
                       </tr>
                     <tr>
		        <td class="desc_cell" nowrap ><b><bean:message key ="adminloginsetup.language"/></b></td>
		        <td valign="top">
		      	<html:select property="language" >
		      	  <html:option value="ENG">ENGLISH</html:option>		      	 
		      	</html:select>
		        </td>
                     </tr>                     
  
              		<tr>
               		  
              		  <td class="login_form_td"  colspan="2" align=right>
              		 
              		<%
                	if(request.getAttribute("ACTION") == null || ((String)request.getAttribute("ACTION")).equals("login"))
			{%>
        	   	    <html:button property="submitbutton" onclick="go('Login')" ><bean:message key ="common.login"/></html:button>
              		<% }%>
             		
               		</td>
              		<td background="images/tbl_d.gif"></td>
             	    </tr>
             	   
                      </tbody>
                    </html:form>
                  </table>
                </td>
              </tr>
              <tr>
                <td height="2" align="center" ><a href="#" class="texteMenuGauche">FORGOT
                  PASSWORD</a></td>
              </tr>
            </table>
          </td>
          <td height="173">&nbsp;</td>
        </tr>
        </tbody>
      </table>
    </TD>
  </TR></TBODY></TABLE>
</BODY></html:html>
<%

 HttpSession sess = request.getSession(false);
  if(sess !=null)
  {
  	sess.invalidate();
  }	
%> 
