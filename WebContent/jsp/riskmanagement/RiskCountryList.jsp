<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/displaytag-12.tld" prefix="display" %>
<%@page import="org.transinfo.cacis.common.CommonDataBean" %>
<%@page import="java.util.ArrayList" %>

<html:html>
<head>
<title><bean:message key ="riskcountrysearch.screentitle"/></title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="inc/css/style.css" type="text/css">
<script type="text/javascript">
function searchlist(){
	document.forms[0].submit();
}
</script>
</head>
<script src="inc/js/cacis.js"></script>
<script>
function doSetup()
{
//alert("doSetup");
document.forms[0].action="riskcountryprocess.do?method=addNew";
document.forms[0].submit();
}
</script>
<body bgcolor="ffffff">

<html:form  action ="riskcountrylist.do?method=search">
<table border="0" cellspacing="0" cellpadding="0" style="border-collapse: collapse" bordercolor="#111111" width="100%">
  <tr>
    <td> 
      <table border="0" cellspacing="0" cellpadding="0" width="100%">
        <tr>
            <td class="titreSection"><bean:message key ="riskcountrysearch.screentitle"/></td>
        </tr>
        <tr>
          <td background="images/ligne.gif" colspan="2"> <img src="images/spacer.gif" width="1" height="1"></td>
        </tr>
	<tr>
	  <td class="titreSection"><table border="0" cellspacing="0" cellpadding="4">
	   <tr>
	    <td><b><html:button property="submitbutton" onclick="doSetup()"><bean:message key ="common.addnew"/></html:button></b></td>	    
	    <td><html:button property="submitbutton" onclick="searchlist()">
								<bean:message key="common.search" />
							</html:button></td>
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
     <br>
      <table width="100%">
        <tr>
          <td valign="top" class="ErrFONT"><font color="#FF0000"><html:errors /></font></td>
        </tr>
        <table cellpadding="0" cellspacing="0"> 
        <td valign="top">
        <tr> 
	 <td> <font class="label"><b><bean:message key ="riskcountry.searchname"/></b></font></td> 
     	 <td> <html:text property="searchCardNo" size="20" onkeypress="return disableEnterKey(event)"/></td>
	</tr>
	           
	<%@ include file="/jsp/common/Buttons.jsp" %>
     	  
    </html:form>	
                            	  	    
 </table>  
	   	   	    
	  
<br><logic:present name="SEARCHLIST" scope="request"> 
   <display:table  id="appProcess" name="requestScope.SEARCHLIST"  
     class="simple" width="80%">   
   <display:column property="column2" title="Country Code" class="label" />   
   <display:column property="column3" title="Updated Date" class="label" /> 
   <display:column property="column4" title="Updated By" class="label" /> 
   <display:caption><font class="titreSection">Risk Country Records</font></display:caption>
   <font class="label"><display:column title="Details" align="center" width="80" ></font>
  	
  	   <form name ="listForm" action="riskcountryprocess.do">	 

              <html:hidden property="searchCardNo" value="<%= ((CommonDataBean)pageContext.getAttribute("appProcess")).getColumn1()%>" /> 
              <html:hidden property="countryCode" value="<%= ((CommonDataBean)pageContext.getAttribute("appProcess")).getColumn2()%>" />               
              <html:hidden property="method" value ="change"/>
              <html:submit value="Update"/>
               
	</form>
  </display:column>	
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

</body>
</html:html>