<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/displaytag-12.tld" prefix="display" %>
<%@page import="org.transinfo.cacis.common.CommonDataBean" %>
<%@page import="java.util.ArrayList" %>

<html:html>
<head>
<title><bean:message key ="customerservice.ccprocesssearch"/></title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="inc/css/style.css" type="text/css">
</head>
<script src="inc/js/cacis.js"></script>
<script>
function go(method) 
{
  //document.forms[0].method.value=method;
  document.forms[0].method.value="processSearch";

  if(method == "NEXT" || method == "PREV"){
	  document.forms[0].mode.value=method;
  }
  
  document.forms[0].submit();	
}
</script>

<body bgcolor="ffffff">
<html:form  action ="cardchangeprocess">
<input type ="hidden" name ="method" />
<table border="0" cellspacing="0" cellpadding="0" style="border-collapse: collapse" bordercolor="#111111" width="100%">
  <tr>
    <td> 
      <table border="0" cellspacing="0" cellpadding="0" width="100%">
        <tr>
            <td class="titreSection"><bean:message key ="customerservice.ccprocesssearch"/></td>
        </tr>
        <tr>
          <td background="images/ligne.gif" colspan="2"> <img src="images/spacer.gif" width="1" height="1"></td>
        </tr>
        <tr>
          <td class="titreSection">
              <table border="0" cellspacing="2" cellpadding="2" width="157">
                <tr> 
                <td class="texteMenuGauche"> <html:button property="submitbutton" onclick="go('processSearch')" ><bean:message key ="common.search"/></html:button>   
                 </td>
              </tr>
            </table>
          </td>
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
        <tr> 
          <td valign="top">
             <table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr> 
                <td> 
                    <table cellpadding="0" cellspacing="0">
                      <tr> 
                         <td> <font class="label"><b><bean:message key ="customerservice.cardnumber"/></b></font>
                         </td>
                         <td> 
                              <html:text property="cardNo" maxlength ="16" size="20"/>
                         </td>
                      </tr>
                      <tr> 
                         <td> <font class="label"><b><bean:message key ="customerservice.cscustomername"/></b></font>
                         </td>
                         <td> 
                              <html:text property="customerName" maxlength ="16" size="20"/>
                         </td>
                      </tr>
                      <tr> 
                         <td> <font class="label"><b><bean:message key ="customerservice.nricnumber"/></b></font>
                         </td>
                         <td> 
                              <html:text property="nric" size="20"/>
                         </td>
                      </tr>
                    <%@ include file="/jsp/common/Buttons.jsp" %>
                 </table>
                </td>
              </tr>
            </table>
  </html:form> 

<br><logic:present name="SEARCHLIST" scope="request"> 
  <display:table  id="cardChangeProcess" name="requestScope.SEARCHLIST"  
     class="simple" width="80%">
   <display:column property="column1" title="Card Number" class="label" />
   <display:column property="column2" title="Customer Name" class="label" />
   <display:column property="column3" title="NRIC No." class="label" />
   <display:column property="column4" title="Applied Date" class="label" />
   <display:caption><font class="titreSection">Card Change Process Records</font></display:caption>
   <font class="label"><display:column title="Details" align="center" width="80" ></font>
  	   <form name ="listForm" action="cardchangeprocess.do">	 
  	   
              <html:hidden property="cardNo" value="<%= ((CommonDataBean)pageContext.getAttribute("cardChangeProcess")).getColumn1()%>" />
              <html:hidden property="method" value ="details"/>
              <html:submit><bean:message key ="common.details"/> </html:submit>
          
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