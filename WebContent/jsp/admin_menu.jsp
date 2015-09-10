<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<html:html>
<HEAD>
<LINK href="../inc/css/stylemenu.css" type=text/css rel=StyleSheet>
<LINK href="../inc/css/udm-style.css" type=text/css rel=StyleSheet>
<META http-equiv=Content-Type content="text/html; charset=ISO-8859-1">
<SCRIPT src="../inc/js/udm-custom.js" type=text/javascript></SCRIPT>
<SCRIPT src="../inc/js/udm-control.js" type=text/javascript></SCRIPT>
<SCRIPT src="../inc/js/udm-dom.js" type=text/javascript></SCRIPT>

</HEAD>
<%
System.out.println("User Id="+(String)session.getAttribute("USERID"));
org.transinfo.cacis.dto.useraccess.UserMasterDto objUserAccess = (org.transinfo.cacis.dto.useraccess.UserMasterDto) session.getAttribute((String)session.getAttribute("USERID"));
System.out.println("1");
%>
<BODY bottomMargin=0 bgColor=#1c5b94 leftMargin=0 topMargin=0 rightMargin=0 marginwidth="0" marginheight="0">

<UL class=udm id=udm>

  <li class=onclick><a href="#"><div align="center"><strong><<<<&nbsp;&nbsp; MENU &nbsp;&nbsp; >>>></strong></div></a></li>
  <p>

<%if(objUserAccess.hasScreen("TRANSACTION_TYPE_SETUP") || objUserAccess.hasScreen("PURSE_TYPE_SETUP")){%>
  <LI class=onclick> <A href="#" target=mainFrame>&nbsp;&nbsp;<b>Configuration</b></A>
	  <UL><!-- -->
        <%if(objUserAccess.hasScreen("TRANSACTION_TYPE_SETUP")){%>
		<LI class=onclick><A href="TransactionTypeSetupServlet?hdOpCode=3&mode=List" target=mainFrame>&nbsp;&nbsp;&nbsp;&nbsp;Transaction Type</A> </LI>
        <% } 
        if(objUserAccess.hasScreen("PURSE_TYPE_SETUP")){%>
                <LI class=onclick><A href="PurseTypeSetupServlet?hdOpCode=3&mode=List" target=mainFrame>&nbsp;&nbsp;&nbsp;&nbsp;Purse Type</A> </LI>		
        <% } %>
	  </UL>
  </LI>
<% } %>

<%if(objUserAccess.hasScreen("ASP_ADMIN_ROLE") || objUserAccess.hasScreen("ASP_ADMIN_USER")){%>
  <li class=onclick><a href="#">&nbsp;&nbsp;<b>ASP Admin User</b></a>
    <ul><!-- -->
        <%if(objUserAccess.hasScreen("ASP_ADMIN_ROLE")){%>
            <li class=onclick><a class="links_menu" target='mainFrame'  href="../rolefunctionlist.do?method=search&userType=ASPADMIN">&nbsp;&nbsp;Role
            Function Setup</a></li>
        <% } 
        if(objUserAccess.hasScreen("ASP_ADMIN_USER")){%>
            <li class=onclick><a class="links_menu" target='mainFrame'  href="../usersetuplistpage.do?method=List&userType=ASPADMIN">&nbsp;&nbsp;User
            Setup</a></li>    
        <% } %>
    </ul>
  </li>
<% } %>
<%if(objUserAccess.hasScreen("ASP_USER_ROLE") || objUserAccess.hasScreen("ASP_USER_USER")){%>
  <li class=onclick><a href="#">&nbsp;&nbsp;<b>ASP User</b></a>
    <ul><!-- -->
        <%if(objUserAccess.hasScreen("ASP_USER_ROLE")){%>
            <li class=onclick><a class="links_menu" target='mainFrame'  href="../rolefunctionlist.do?method=search&userType=ASPUSER">&nbsp;&nbsp;Role
            Function Setup</a></li>
             
        <% } 
        if(objUserAccess.hasScreen("ASP_USER_USER")){%>
            <li class=onclick><a class="links_menu" target='mainFrame'  href="../usersetuplistpage.do?method=List&userType=ASPUSER">&nbsp;&nbsp;User
            Setup</a></li>    
        <% } %>
    </ul>
  </li>
<% } %>

<%if(objUserAccess.hasScreen("ISSUER_MASTER")){%>
  <li class=onclick><a href="#">&nbsp;&nbsp;<b>Issuer Management</b></a>
    <ul><!-- -->
        <%if(objUserAccess.hasScreen("ISSUER_MASTER")){%>
	<li class=onclick><a class="links_menu" target='mainFrame'  href="../issuerlist.do?method=List">&nbsp;&nbsp;Issuer Setup</a></li>
        <% } %>
    </ul>
  </li>
<% } %>

  

</UL>
</BODY>
</html:html>
