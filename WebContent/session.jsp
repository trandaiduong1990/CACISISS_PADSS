<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@page import="org.transinfo.cacis.common.CommonDataBean" %>
<html:html>
<HEAD>
<title><bean:message key ="adminloginsetup.screentitle"/></title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="inc/css/style.css" type="text/css">
<script language="JavaScript">
<% String servletPath = request.getContextPath(); %>
<!--
function callLogon(){
    //parent.window.location.replace("/Cacisiss/adminloginsetup.do");
    //self.parent.location = '<%=servletPath%>'+'/adminloginsetup.do';
    window.parent.parent.location.replace('<%=servletPath%>'+'/adminloginsetup.do');
}
//-->
</script>
</HEAD>
<BODY class=BODY>
<html:form action="adminloginprocess.do">
  <table>
  <tr><td valign="top" class="ErrFONT"><font color="#FF0000"><html:errors /></font></td></tr>
  </table>
<table border="0" cellspacing="0" cellpadding="0">
    <tr> 
      <td class="titreSection" width="519"><bean:message key="Session.title"/></td>
      <td class="titreSection" width="484">&nbsp; </td>
    </tr>
    <tr> 
      <td background="images/ligne.gif" colspan="3"> <img src="images/spacer.gif" width="1" height="1"></td>
    </tr>
    <tr> 
      <td background="images/ligne.gif" colspan="3"> <img src="images/spacer.gif" width="1" height="1"></td>
    </tr>
  </table>
  <table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr>
    <td> </td>
  </tr>
  <tr>
      <td>
        <table width="100%" border="0" cellspacing="0" cellpadding="2">
          <tr> 
            <td class="textstylesreverse"> 
              <table width="95%" border="0" cellspacing="0" cellpadding="0" align="center">
                <tr> 
                  <td> 
                    <table border="0" cellspacing="0" cellpadding="0" width="171" align="center">
                      <tr> 
                        <td class="textm">&nbsp;</td>
                      </tr>
                      <tr> 
                        <td class="title"> 
                          <div align="center"><b><bean:message key="Session.msg1"/></b></div>
                        </td>
                      </tr>
                      <tr> 
                        <td class="headlines">&nbsp;</td>
                      </tr>
                      <tr> 
                        <td class="TOP"> 
                          <div align="center"><bean:message key="Session.msg2"/></div>
                        </td>
                      </tr>
                      <tr> 
                        <td class="headlines">&nbsp;</td>
                      </tr>
                      <tr> 
                        <td class="fontfocus"> 
                          <div align="center"><a href="javascript:callLogon()"><bean:message key="Session.logon"/></a></div>
                        </td>
                      </tr>
                    </table>
                  </td>
                </tr>
              </table>
            </td>
          </tr>
        </table>
    </td>
  </tr>
  <tr>
      <td>&nbsp; </td>
  </tr>
</table>
  </html:form>
</BODY></html:html>
<%

 HttpSession sess = request.getSession(false);
  if(sess !=null)
  {
  	sess.invalidate();
  }	
%> 
