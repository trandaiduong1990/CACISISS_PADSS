<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/displaytag-12.tld" prefix="display" %>
<%@page import="org.transinfo.cacis.common.CommonDataBean" %>
<%@page import="java.util.ArrayList" %>


<html:html>
<head>
<title><bean:message key ="applicationformsearch.screentitle"/></title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="inc/css/style.css" type="text/css">
</head>
<script src="inc/js/cacis.js"></script>
<script language="JavaScript">
  
    function addNew() {
     document.forms[0].action="applicationformprocess.do?method=addNew";
   	 //document.forms[0].action="GenerateReportServlet.do?hdOpCode=4&mode=Down&REPORT_CODE=BPNC";
     document.forms[0].submit();
   	 
    }

    function searchlist(){
    	document.forms[0].submit();
    }

    function go(action) {
    	document.forms[0].mode.value=action;
     	document.forms[0].action ="applicationformlist.do";
    	document.forms[0].submit();
    }
    
</script>
<body bgcolor="ffffff">
 <html:form action ="applicationformlist">
 <html:hidden property="branchId" value="<%=(String)session.getAttribute("LOGEDUSERBRANCH")%>"/>
 <table border="0" cellspacing="0" cellpadding="0" style="border-collapse: collapse" bordercolor="#111111" width="100%">
  <tr>
    <td> 
      <table border="0" cellspacing="0" cellpadding="0" width="100%">
        <tr>
            <td class="titreSection"><bean:message key ="applicationformsearch.screentitle"/></td>
        </tr>
        <tr>
          <td background="images/ligne.gif" colspan="2"> <img src="images/spacer.gif" width="1" height="1"></td>
        </tr>
        <tr>
          <td class="titreSection">
              <table border="0" cellspacing="2" cellpadding="2" width="157">
                <tr> 
                <td class="texteMenuGauche"> 
                  
                <html:button property="submitbutton" onclick ="addNew()"><bean:message key ="common.addnew"/></html:button>
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
                        <td nowrap class="ColumnFONT"><bean:message key ="applicationformsearch.customername"/> </td>
                        <td> <html:text property="customerName" size="20"/></td>
                      </tr>
                      <tr>
                        <td nowrap class="ColumnFONT"><bean:message key ="applicationformsearch.nricnumber"/></td>
                        <td><html:text property="idNumber" size="20"/></td>
                      </tr>
             
                   <%@ include file="/jsp/common/Buttons.jsp" %>
                        
                  </table>       </td>
              </tr>
        </table>
 </html:form> 
  <br><logic:present name="SEARCHLIST" scope="request">
  
    <display:table  id="commonObj" name="requestScope.SEARCHLIST"  
     class="simple" width="80%" sort="list">
   <display:column property="column1" title="ApplicationId" class="label" />
    <display:column property="column2" title="CustomerName" />
    <display:column property="column3" title="Updated Date" />
    <display:caption><font class="titreSection">ApplicationForm Records</font></display:caption>
   <font class="label"><display:column title="Details" align="center" width="80" ></font>
  	  <form name ="listForm" action="applicationformprocess.do">	 
	 
              <html:hidden property="applicationId" value="<%=((CommonDataBean)pageContext.getAttribute("commonObj")).getColumn1()%>" />
              <html:hidden  property="issuerId" value="<%=(String)session.getAttribute("ISSUER")%>"/>
              <html:hidden property="method" value ="change"/>
              <% if(((CommonDataBean)pageContext.getAttribute("commonObj")).getColumn4().equals("0") || ((CommonDataBean)pageContext.getAttribute("commonObj")).getColumn4().equals("5")){ %>
              	<html:submit value="Update"/>
              <% }else{%>
            	<html:submit value="Details"/>   
              <% } %>
         
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