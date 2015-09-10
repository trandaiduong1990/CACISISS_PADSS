<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/displaytag-12.tld" prefix="display" %>
<%@page import="org.transinfo.cacis.common.CommonDataBean" %>
<%@page import="java.util.ArrayList" %>


<html:html>
<head>
<title><bean:message key ="customerservice.csscreentitle"/></title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="inc/css/style.css" type="text/css">

<script type="text/javascript">

function go(method) 
{

  document.forms[0].mode.value=method;
  document.forms[0].method.value=method;	
  document.forms[0].submit();
}

function searchlist(){
	document.forms[0].submit();
}
</script>
</head>
<script src="inc/js/cacis.js"></script>

<body bgcolor="ffffff">
 <html:form  action ="customerscreenlist">
 <table border="0" cellspacing="0" cellpadding="0" style="border-collapse: collapse" bordercolor="#111111" width="100%">
  <tr>
    <td> 
      <table border="0" cellspacing="0" cellpadding="0" width="100%">
        <tr>
            <td class="titreSection"><bean:message key ="customerservice.csscreentitle"/></td>
        </tr>
        <tr>
          <td background="images/ligne.gif" colspan="2"> <img src="images/spacer.gif" width="1" height="1"></td>
        </tr>
        <tr>
          <td class="titreSection">
              <table border="0" cellspacing="2" cellpadding="2" width="157">
                <tr> 
                <td class="texteMenuGauche"> 
                        
                 <html:button property="submitbutton" onclick="searchlist()"><bean:message key ="common.search"/></html:button>
          
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
                    <table cellpadding="0" cellspacing="4">
                      <tr>
                        <td nowrap class="ColumnFONT"><bean:message key ="customerservice.cscustomername"/> </td>
                        <td> <html:text property="customerName" size="20"/></td>
                      </tr>
                      <tr>
                        <td nowrap class="ColumnFONT"><bean:message key ="customerservice.cscardno"/></td>
                        <td><html:text property="cardNo" size="20"/></td>
                      </tr>
                      <tr>
                        <td nowrap class="ColumnFONT"><bean:message key ="customerservice.csstartdate"/></td>
                        <td><html:text property="strFromDate" size="20" onblur="javascript:isValidDate(document.customerScreenForm.strFromDate)" /></td>
                      </tr>
                      <tr>
                        <td nowrap class="ColumnFONT"><bean:message key ="customerservice.csenddate"/></td>
                        <td><html:text property="strToDate" size="20" onblur="javascript:isValidDate(document.customerScreenForm.strToDate)" /></td>
                      </tr>
                      <tr>
                        <td nowrap class="ColumnFONT"><bean:message key ="customerservice.cscustomeremail"/> </td>
                        <td> <html:text property="email" size="20" onblur="javascript:checkEmail(document.customerScreenForm.email)" /></td>
                      </tr>
                      <tr>
                        <td nowrap class="ColumnFONT"><bean:message key ="customerservice.cscustomerdob"/> </td>
                        <td> <html:text property="dob" size="20" onblur="javascript:isValidDate(document.customerScreenForm.dob)" /></td>
                      </tr>
                      <tr>
                        <td nowrap class="ColumnFONT"><bean:message key ="customerservice.cscustomerautoacc"/> </td>
                        <td> <html:text property="autoAccNo" size="20"/></td>
                      </tr>
                  	</table>       
                </td>
             	</tr>
             	<tr>
             		<td style="width: 50px;">
                   		<%@ include file="/jsp/common/Buttons.jsp" %>
                   </td>
             	</tr>
        	</table>
 </html:form> 
<br>
<logic:present name="SEARCHLIST" scope="request">
  
   <display:table  id="commonObj" name="requestScope.SEARCHLIST" class="simple" width="80%">
   <display:column property="column1" title="Customer Id" class="label" />
   <display:column property="column2" title="CustomerName" />
   <display:column property="column3" title="Card Number" />
   <display:column property="column4" title="Status" />
   <display:caption><font class="titreSection">Customer Records</font></display:caption>
   <font class="label">
   		<display:column title="Details" align="center" width="80" ></font>
  	  
		  	  <form name ="listForm" action="customerscreenprocess.do">	 
		
		        <html:hidden property="customerId" value="<%=((CommonDataBean)pageContext.getAttribute("commonObj")).getColumn1()%>" />
		        <html:hidden property="cardNo" value="<%=((CommonDataBean)pageContext.getAttribute("commonObj")).getColumn3()%>" />
		        <html:hidden  property="issuerId" value="<%=(String)session.getAttribute("ISSUER")%>"/>
		        <html:hidden property="method" value ="change"/>
		        <html:submit value="Detail"/>
		         
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