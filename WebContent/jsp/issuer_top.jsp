<%
	String userId = (String)session.getAttribute("USERID");
	//String issuerId = (String)session.getAttribute("ACTIVE_ISSUERID");
	String issuerId = (String)session.getAttribute("ISSUER");
        //String userType = (String)((UserDataBean)session.getAttribute(userId)).getUserType();
        String topSelect = "ISS";
        if(request.getParameter("topSelect") != null) {
            topSelect = request.getParameter("topSelect");
        }
%>
<HTML><HEAD>
<META http-equiv=Content-Type content="text/html; charset=windows-1252">
<META http-equiv=Expire CONFILTERED="now">
<META http-equiv=Pragma CONFILTERED="no-cache">
<META http-equiv=Cache-Control CONFILTERED="no-cache">
<LINK href="css/style.css" type=text/css rel=StyleSheet>
<script language="JavaScript">

<% String servletPath = request.getContextPath(); %>
<!--
function Logout(){
    //parent.window.location.replace("index.html");
    //alert('test');
    //alert('<%=servletPath%>');
    //parent.window.location.replace("index.jsp");
    //parent.window.location.replace("/Cacisiss/adminloginsetup.do");
    parent.window.location.replace('<%=servletPath%>'+'/adminloginsetup.do');
}

<%if(topSelect.trim().equals("ISS")){%>
    parent.botFrame.location.href="issuer_main.html";
<%}%>
<%if(topSelect.trim().equals("PRO")){%>
    parent.botFrame.location.href="profile_main.html";
<%}%>



// -->
</script>
</HEAD>
<BODY class=BODY leftmargin="3">
<div id="Layer1" style="position:absolute; left:3px; top:47px; width:1013px; height:39px; z-index:1"> 
  <table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr> 
      <td width="52%" background="../images/tbl_haut.gif"> 
      <%if(topSelect.trim().equals("ISS")){%>
        <table border="0" cellspacing="0" cellpadding="0">
          <tr> 
            <td> <img src="../images/form_tab_start_off.gif" border="0" alt="" width="5" height="22"></td>
            <td background="../images/tbl_haut.gif"> 
              <table border="0" cellpadding="0" cellspacing="0">
                <tr> 
                  <td class="form_tab_off" background="../images/form_tab_bg_off.gif" style="padding-left:15px"> 
                     <a target='_self' href="issuer_top.jsp?topSelect=PRO">My Profile</a></td>
                  <td> <img height=22 alt="" src="../images/form_tab_btw_off_on.gif" width=25 border=0></td>
                  <td background="../images/form_tab_bg_on.gif" nowrap class="form_tab_on"> 
                   Issuer : <%=issuerId%>  </td>
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
                  <td class="form_tab_on" background="../images/form_tab_bg_on.gif" style="padding-left:15px"> 
                     My Profile</td>
                  <td> <img height=22 alt="" src="../images/form_tab_btw_on_off.gif" width=25 border=0></td>
                  <td background="../images/form_tab_bg_off.gif" nowrap class="form_tab_off"> 
                   <a target='_self' href="issuer_top.jsp?topSelect=ISS">Issuer : <%=issuerId%> </a> </td>
                  <td><img src="../images/form_tab_end_off.gif" width="25" height="22"></td>
                </tr>
              </table>
            </td>
          </tr>
        </table>        
        <%}%>
        
      </td>
      <td background="../images/tbl_haut.gif" width="48%">&nbsp;</td>
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
</BODY></HTML>
