<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<html:html>
<HEAD>
<TITLE>Welcome to the CACIS System</TITLE>
<META http-equiv=Content-Type content="text/html; charset=iso-8859-1">
</HEAD>
<script language="JavaScript">
function load_topframe() {
//this for displaying the cardnumber,customername and nric on this jsp after loading the frame 
 //if (frame == 2){
    // window.topFrame.location.href =  "Csr_Main_Top.jsp";
     window.topFrame.location.reload();
  // }
  }
</script>

  <FRAMESET frameBorder=no cols=185,* noresize marginheight="0" marginwidth="0" scrolling="no">
    <FRAME src="Csr_Menu.jsp" name="leftFrame" frameBorder=no scrolling=no noResize marginWidth=0 marginHeight=0 title="leftFrame">
    <frameset rows="36,*" cols="*" framespacing="0" frameborder="NO" border="0" onLoad="load_topframe()">
		 <frame src ="Csr_Main_Top.jsp" name="topFrame" scrolling="NO" noresize title="topFrame">
		  <html:frame action="csrprocess.do?method=csrSummary" frameName="mainFrame"/>
		
	</frameset>
  </FRAMESET><noframes></noframes>
</html:html>
