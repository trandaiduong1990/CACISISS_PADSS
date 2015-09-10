<%@ page contentType="text/html;charset=UTF-8"%>
<%
	response.setHeader("Pragma","no-cache"); //HTTP 1.0
	response.setDateHeader("Expires", 0); //prevents caching at the proxy server
	response.setHeader("Cache-Control", "no-cache, private, no-store, max-stale=0");
%>

<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<%
  String locale = "en_US";
  if(session.getAttribute("locale")!=null){
      locale = request.getParameter("locale");
      if(locale==null){
        locale = (String)session.getAttribute("locale");
      }
  }
  session.setAttribute("locale",locale);

   if(locale.equals("en_US")){
%>
      <fmt:setLocale value="en_US"/>
<%
    }else{
%>    
      <fmt:setLocale value="en_US"/>  
<% 
    }
%>

<HTML>
<HEAD>
<LINK href="../inc/css/stylemenu.css" type=text/css rel=StyleSheet>
<LINK href="../inc/css/udm-style.css" type=text/css rel=StyleSheet>
<META http-equiv=Content-Type content="text/html; charset=UTF-8">
<SCRIPT src="../inc/js/udm-custom.js" type=text/javascript></SCRIPT>
<SCRIPT src="../inc/js/udm-control.js" type=text/javascript></SCRIPT>
<SCRIPT src="../inc/js/udm-dom.js" type=text/javascript></SCRIPT>
</HEAD>
<BODY bottomMargin=0 bgColor=#1c5b94 leftMargin=0 topMargin=0 rightMargin=0 marginwidth="0" marginheight="0">

<UL class=udm id=udm>

	<li class=onclick><a href="#"><div align="center"><strong><<<<&nbsp;&nbsp; MENU &nbsp;&nbsp; >>>></strong></div></a></li>
  	<p>
  	
	<!-- <li class=onclick><a class="links_menu" target='mainFrame'  href="../usersetupprocess.do?method=change">&nbsp;&nbsp;Update Profile</a></li>  -->
	<li class=onclick><a class="links_menu" target='mainFrame'  href="../passwordchangepage.do">&nbsp;&nbsp;Change Password</a> </li>

</UL>

</BODY>
</HTML>