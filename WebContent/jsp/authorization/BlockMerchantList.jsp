<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/displaytag-12.tld" prefix="display" %>
<%@page import="org.transinfo.cacis.common.CommonDataBean" %>
<%@page import="java.util.ArrayList" %>

<html:html>
<head>
<title><bean:message key ="blockMerchantsetup.screentitle"/></title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="inc/css/style.css" type="text/css">
</head>
<script src="inc/js/cacis.js"></script>
<script>
function addNew()
{
	//alert("addNew()");
  document.forms[0].action="blockmerchantprocess.do?method=addNew";
  document.forms[0].submit();
}

function searchlist(){
	//alert("search");
	//document.forms[0].action="blockmerchantlist.do";
	document.forms[0].submit();
}
</script>

<body bgcolor="ffffff">

<html:form  action ="blockmerchantlist">
<table border="0" cellspacing="0" cellpadding="0" style="border-collapse: collapse" bordercolor="#111111" width="100%">
  <tr>
    <td> 
      <table border="0" cellspacing="0" cellpadding="0" width="100%">
        <tr>
            <td class="titreSection"><bean:message key ="blockMerchantsetup.screentitle"/></td>
        </tr>
        <tr>
          <td background="images/ligne.gif" colspan="2"> <img src="images/spacer.gif" width="1" height="1"></td>
        </tr>
        <tr>
	  <td class="titreSection"><table border="0" cellspacing="0" cellpadding="4">
	   <tr>
	     <td><b><html:button property="submitbutton" onclick="addNew()"><bean:message key ="common.addnew"/></html:button></b></td>	    
	     <td><html:button property="submitbutton" onclick="searchlist()"><bean:message key ="common.search"/></html:button></td>
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
        <tr> 
          <td valign="top">
          		  	 
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr> 
                <td> 
                    <table cellpadding="0" cellspacing="0">
                      <tr> 
                        <td> <font class="label"><b><bean:message key ="blockMerchantsetup.searchName"/></b></font> 
                          <html:text property="searchMerchantId" size="20" onkeypress="return disableEnterKey(event)"/>                                                          
                         </td>                                                        
                        </tr>                           
                          <%@ include file="/jsp/common/Buttons.jsp" %>                           
                          </html:form>                             
                  </table>
                </td>
              </tr>
            </table>


<br><logic:present name="SEARCHLIST" scope="request"> 
   <display:table  id="appProcess" name="requestScope.SEARCHLIST"  
     class="simple" width="80%">
   <display:column property="column1" title="Merchant Name" class="label" />
   <display:column property="column2" title="Merchant Id" class="label" /> 
  <display:column property="column3" title="Status" class="label" /> 
   <display:column property="column4" title="Reason" class="label" />
   <display:caption><font class="titreSection">Block Merchant Records</font></display:caption>
   
   <font class="label"><display:column title="Update Status" align="center" width="80" ></font>
  	
  	   <form name ="listForm" action="blockmerchantprocess.do">	 

              <html:hidden property="searchMerchantId" value="<%= ((CommonDataBean)pageContext.getAttribute("appProcess")).getColumn2()%>" />
              <html:hidden property="merchantName" value="<%= ((CommonDataBean)pageContext.getAttribute("appProcess")).getColumn1()%>" />
              <html:hidden property="reason" value="<%= ((CommonDataBean)pageContext.getAttribute("appProcess")).getColumn4()%>" />
                <html:hidden property="blockStatus" value="<%= ((CommonDataBean)pageContext.getAttribute("appProcess")).getColumn3()%>" />  
             
              
              <html:hidden property="issuerId" value="<%=(String)session.getAttribute("ISSUER")%>"/>
              <html:hidden property="userId" value="<%=(String)session.getAttribute("USERID")%>"/>
              <html:hidden property="method" value ="delete"/>
              <html:submit value="Change Status" onclick="javascript:return confirm('Are you SURE you want to update the block status?');"/>
    
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