<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/displaytag-12.tld" prefix="display" %>
<%@page import="org.transinfo.cacis.common.CommonDataBean" %>

<html:html>
<head>
<title><bean:message key ="cgf.screentitile.search"/></title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="inc/css/style.css" type="text/css">
</head>
<script src="inc/js/cacis.js"></script>
<script language="JavaScript">
  
    function addNew() { 
       document.forms[0].action="collectionageingprocess.do?method=addNew";
       document.forms[0].submit();
    }

    function go(action) {
      	document.forms[0].mode.value = action;
      	searchlist();
      }
</script>
<body bgcolor="ffffff">
 
<html:form action="collectionageingprocess.do">
	<html:hidden property="issuerId" value="<%=(String)session.getAttribute("ISSUER")%>"/>      
	<table border="0" cellspacing="0" cellpadding="0" style="border-collapse: collapse" bordercolor="#111111" width="100%">

  <tr>
    <td> 
      <table border="0" cellspacing="0" cellpadding="0" width="100%">
        <tr>
            <td class="titreSection"><bean:message key ="collectionageing.screentitle"/></td>
        </tr>
        <tr>
          <td background="images/ligne.gif" colspan="2"> <img src="images/spacer.gif" width="1" height="1"></td>
        </tr>
        <tr>
          <td class="titreSection"><table border="0" cellspacing="0" cellpadding="4">
            <tr>
              <td><html:button property="submitbutton" onclick ="addNew()"><bean:message key ="common.addnew"/></html:button><br></td>
            </tr>
          </table></td>
        </tr>
        <tr>
          <td background="images/ligne.gif" colspan="2"> <img src="images/spacer.gif" width="1" height="1"></td>
        </tr>
      </table>
    </td>
  </tr>
  <tr>
    <td valign="top">
      <table width="100%">

        <tr> 
          <td valign="top">  
 
   <logic:present name="SEARCHLIST" scope="request">
   <div style="margin-top: 10px; margin-bottom: -12px;"><%@ include
         file="/jsp/common/Buttons.jsp"%></div>
<br/>

    <display:table  id="commonObj" name="requestScope.SEARCHLIST" class="simple" width="70%">
	   	<display:column property="column1" title="Ageing Policy" class="label" />
	   	<display:column property="column2" title="Start Day" class="label" />
	   	<display:column property="column3" title="End Day" class="label" />
	   	<display:column property="column4" title="Last Updated Date" class="label" />
	   	<display:caption><font class="titreSection">Collection Ageing Records</font></display:caption>
	   	<font class="label"><display:column title="" align="center" width="80" >
			<form name ="listForm" action="collectionageingprocess.do">
				<html:hidden property="ageingPolicy" value="<%= ((CommonDataBean)pageContext.getAttribute("commonObj")).getColumn1()%>" />       
	            <html:hidden property="method" value ="change"/>
	            <html:submit value="Update"/>
			</form>
	   	</display:column>	
	   	</font>
    </display:table>
   </logic:present>
    </td>
        </tr>
        <tr> 
          <td valign="top">&nbsp; </td>
        </tr>
      </table>
    </td>
  </tr>
</table>
</html:form>   
</body>
</html:html>