<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<html:html>

<HEAD>
<META http-equiv=Content-Type content="text/html; charset=iso-8859-1">
<META http-equiv=Expire CONFILTERED="now">
<META http-equiv=Pragma CONFILTERED="no-cache">
<META http-equiv=Cache-Control CONFILTERED="no-cache">

<style type="text/css">
<!--
h2 {
	font-family: "Courier New", Courier, monospace;
	font-weight: bold;
	color: #DEE3ED;
}
.label {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 10px;
	font-weight: bold;
	color: #ACC9F9;
	text-decoration: underline;
}
.display {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 12px;
	font-weight: bold;
	color: #DBE8FD;
}
.logo {
	border: 1px solid #FFFFFF;
}
-->
</style>

</HEAD>
 <script>
	
	 function newCall(varSession) {
	 
	  if(varSession == "null"){
	    window.parent.location.href = "/cacis/csrsearch.do"
	    // window.parent.location.href = "CSRSearch.jsp";
	   //top.botFrame.mainFrame.location.href =  "CallRecord.jsp";
	  }else{
	    document.forms[0].method.value="previousCallData";
	    document.forms[0].submit();
	  }
    
     /* document.forms[0].method.value=action;
      document.forms[0].action="csrsearch";
     top.botFrame.mainFrame.location.href =  "CallRecord.jsp";*/
   }
	 </script>

<BODY bottomMargin=0 bgColor=#1c5b94 leftMargin=0 topMargin=0 rightMargin=0 marginwidth="0" marginheight="0">
<html:form action="csrcallrecord.do"  target='mainFrame'>
<input type="hidden" name="method"/>
 <table width="100%" border="0">
  <tr>
    <td colspan="2"> </td>
  </tr>
   <tr>
    <td class="logo" width="18%" align="center" valign="middle" nowrap><h2><bean:message key ="customerrelation.screentitle"/></h2></td>
    <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td>&nbsp;</td>
        <td><div align="right"><input type ="button" onclick="<%="newCall('" + (String)session.getAttribute("NEWCALLREFNO") + "')" %>"  value="<bean:message key ="common.newcall"/>"> </div></td>
         </tr>  
                                                                                
    </table></td>
  </tr>
</table>
</html:form>
</BODY>
</html:html>
