<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<%@ page import="org.transinfo.cacis.dto.useraccess.UserMasterDto,
                 java.util.ArrayList"%>
                 
<%
	String userId = (String)session.getAttribute("USERID");
	String activeIssuerId = (String)session.getAttribute("ISSUER");
	String logonIssuerId = (String)session.getAttribute("LOGON_ISSUERID");
        //String userType = (String)((UserMasterDto)session.getAttribute(userId)).getUserType();
        boolean isUserLogin = (boolean)((UserMasterDto)session.getAttribute(userId)).isUserLogin();
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> isUserLogin "+isUserLogin);
        String topSelect = "ASP";
        if(request.getParameter("topSelect") != null) {
            topSelect = request.getParameter("topSelect");
        }
        
	/*Object [] objArr = 	null;
	DBUtil dbutil = new DBUtil(AdminConfig.poolName); 
	ArrayList arlIssuers = dbutil.getIssuerList();*/
	
	if(request.getParameter("issuerId") != null){
		activeIssuerId = request.getParameter("issuerId");
	}else{
		activeIssuerId = logonIssuerId;
	}
	System.out.println("USERID="+userId+" ACTIVEISSUERID="+activeIssuerId);
	session.setAttribute("ACTIVE_ISSUERID",activeIssuerId);
	session.setAttribute("ISSUER",activeIssuerId);
	     
%>
<html:html><HEAD>
<META http-equiv=Content-Type content="text/html; charset=windows-1252">
<META http-equiv=Expire CONFILTERED="now">
<META http-equiv=Pragma CONFILTERED="no-cache">
<META http-equiv=Cache-Control CONFILTERED="no-cache">
<LINK href="../inc/css/style.css" type=text/css rel=StyleSheet>
<script language="JavaScript">

<% String servletPath = request.getContextPath(); %>

<!--
<%if(!isUserLogin){%>

	//parent.window.location.replace("/cacis/adminloginprocess.do");
	//parent.window.location.replace("/Cacisiss/adminloginprocess.do");
	parent.window.location.replace('<%=servletPath%>'+'/adminloginsetup.do');
<%}%>

function Logout()
{
	//var ser = '<%=servletPath%>';
	//var joinser = ser+'/adminloginsetup.do';
	//alert('test');
    //alert(ser);
    //alert(joinser);
    
    //parent.window.location.replace("/cacis/adminloginprocess.do");
    //parent.window.location.href="/cacis/adminloginsetup.do";
    //parent.window.location.href=joinser;
    parent.window.location.href='<%=servletPath%>'+'/adminloginsetup.do';
}


function OpenIssuer(issuer) {
	alert(issuer);
	if(issuer != "") location.href = "admin_top.jsp?topSelect=ISS&issuerId="+issuer;
}

<%if(topSelect.trim().equals("ASP")){%>
    parent.botFrame.location.href = "admin_main.html";
<%}%>
<%if(topSelect.trim().equals("ISS")){%>
    parent.botFrame.location.href = "issuer_main.html";
<%}%>
<%if(topSelect.trim().equals("PRO")){%>
    parent.botFrame.location.href = "profile_main.html";
<%}%>



// -->
</script>
</HEAD>

<BODY class=BODY leftmargin="3">
<html:form action="adminloginprocess.do">
<div id="Layer1" style="position:absolute; left:3px; top:47px; width:1013px; height:39px; z-index:1"> 
  <table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr> 
      <td width="76%" background="../images/tbl_haut.gif"> 
        <%if(topSelect.trim().equals("ISS")){%>
        <table border="0" cellspacing="0" cellpadding="0">
          <tr> 
            <td> <img src="../images/form_tab_start_off.gif" border="0" alt="" width="5" height="22"></td>
            <td background="../images/tbl_haut.gif"> 
              <table border="0" cellpadding="0" cellspacing="0">
                <tr> 
                  <td class="form_tab_off" background="../images/form_tab_bg_off.gif" style="padding-left:15px"> 
                     <a target='_self' href="admin_top.jsp?topSelect=PRO">My Profile</a></td>
                  <td> <img height=22 alt="" src="../images/form_tab_btw_off_off.gif" width=25 border=0></td>
                  <td background="../images/form_tab_bg_off.gif" nowrap class="form_tab_off"> 
                   <a target='_self' href="admin_top.jsp?topSelect=ASP">Administration </a> </td>
                  
                  <td> <img height=22 alt="" src="../images/form_tab_btw_off_on.gif" width=25 border=0></td>
                  <td background="../images/form_tab_bg_on.gif" nowrap class="form_tab_on"> 
                   Issuer : <%=activeIssuerId%>  </td>
                  <td><img src="../images/form_tab_end_on.gif" width="25" height="22"></td>
                  
                </tr>
                
              </table>
            </td>
          </tr>
        </table>
        <%}%>
        <%if(topSelect.trim().equals("PRO")){%>
        <table border="0" cellspacing="0" cellpadding="0">
          <tr> 
            <td> <img src="../images/form_tab_start_on.gif" border="0" alt="" width="5" height="22"></td>
            <td background="../images/tbl_haut.gif"> 
              <table border="0" cellpadding="0" cellspacing="0">
                <tr> 
                  <td class="form_tab_off" background="../images/form_tab_bg_on.gif" style="padding-left:15px"> 
                     My Profile</td>
                  <td> <img height=22 alt="" src="../images/form_tab_btw_on_off.gif" width=25 border=0></td>
                  <td background="../images/form_tab_bg_off.gif" nowrap class="form_tab_off"> 
                   <a target='_self' href="admin_top.jsp?topSelect=ASP">Administration </a> </td>
                  <td> <img height=22 alt="" src="../images/form_tab_btw_off_off.gif" width=25 border=0></td>
                  <td background="../images/form_tab_bg_off.gif" nowrap class="form_tab_off"> 
                   Issuer : 
			<select name="issuerId" onChange="OpenIssuer(this.value)">
			   <option value="">--- Select Issuer ---</option>
		       </select>                   
                   </td>
                  <td><img src="../images/form_tab_end_off.gif" width="25" height="22"></td>
                </tr>
              </table>
            </td>
          </tr>
        </table>       
        <%}%>
        
        <%if(topSelect.trim().equals("ASP")){%>
        <table border="0" cellspacing="0" cellpadding="0">
          <tr> 
            <td> <img src="../images/form_tab_start_off.gif" border="0" alt="" width="5" height="22"></td>
            <td background="../images/tbl_haut.gif"> 
              <table border="0" cellpadding="0" cellspacing="0">
                <tr> 
                  <td class="form_tab_off" background="../images/form_tab_bg_off.gif" style="padding-left:15px"> 
                     <a target='_self' href="admin_top.jsp?topSelect=PRO">My Profile </a></td>
                  <td> <img height=22 alt="" src="../images/form_tab_btw_off_on.gif" width=25 border=0></td>
                  <td background="../images/form_tab_bg_on.gif" nowrap class="form_tab_on"> 
                   Administration </td>
                  <td> <img height=22 alt="" src="../images/form_tab_btw_on_off.gif" width=25 border=0></td>
                  <td background="../images/form_tab_bg_off.gif" nowrap class="form_tab_off"> 
                   Issuer : 
     		   <html:select property="issuerId" onchange="OpenIssuer(this.value)" >
      	   	   <html:option value="">--- Select Issuer ---</html:option>	
       		   <html:optionsCollection property="issuerList" value="key" label="value" />
	   	   </html:select>
	           </td>
                  <td><img src="../images/form_tab_end_off.gif" width="25" height="22"></td>
                </tr>
              </table>
            </td>
          </tr>
        </table>       
        <%}%>
        
      </td>
      <td background="../images/tbl_haut.gif" width="24%">&nbsp;</td>
    </tr>
  </table>
</div>
<div id="Layer2" style="position:absolute; left:945px; top:42px; width:54px; height:17px; z-index:2"> 
  <a href="javascript:Logout()" class="login_form_txt">Logout</a> </div>
<div id="Layer3" style="position:absolute; left:5px; top:6px; width:285px; height:41px; z-index:3"> 
  <table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr> 
      <td class="desc_cell" nowrap width="24%"><font size="1">Logon User</font></td>
      <td class="label" nowrap width="76%"><%=userId%></td>
    </tr>
    <tr> 
      <td nowrap width="24%"></td>
      <td nowrap width="76%"></td>
    </tr>
  </table>
</div>
<DIV style="FLOAT: right"> </DIV>
</html:form>
</BODY></html:html>
