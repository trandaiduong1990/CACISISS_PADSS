<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/displaytag-12.tld" prefix="display" %>
<%@page import="org.transinfo.cacis.common.CommonDataBean" %>
<%@page import="java.util.ArrayList" %>

<html:html>
<head>
<title><bean:message key ="cardrenewal.screentitle"/></title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="inc/css/style.css" type="text/css">
</head>
<script src="inc/js/cacis.js"></script>
<script>
function cardsRenProcess(method) 
{
  //alert("befor  method value" +method);
  document.forms[0].method.value=method;
  document.forms[0].submit();	
}
</script>

<body bgcolor="ffffff">
 <html:form  action ="cardsrenewallist">
 <input type ="hidden" name ="method" />
  
   <!--  hiddeen fields to insert the data in varioues tables-->
 <html:hidden property="issuerId" value="<%=(String)session.getAttribute("ISSUER")%>"/>
 <html:hidden property="userId" value="<%=(String)session.getAttribute("USERID")%>"/>
 
<table border="0" cellspacing="0" cellpadding="0" style="border-collapse: collapse" bordercolor="#111111" width="100%">
  <tr>
    <td> 
      <table border="0" cellspacing="0" cellpadding="0" width="100%">
        <tr>
            <td class="titreSection"><bean:message key ="cardrenewal.screentitle"/></td>
        </tr>
        <tr>
          <td background="images/ligne.gif" colspan="2"> <img src="images/spacer.gif" width="1" height="1"></td>
        </tr>
        <tr>
          <td class="titreSection">
              <table border="0" cellspacing="2" cellpadding="2" width="157" align="right">
                <tr> 
                <td class="texteMenuGauche"> 
                    <p class="titreSection">
                     
                    &nbsp;&nbsp;</p>
                </td>
                <td>&nbsp;</td>
                <td class="texteMenuGauche">&nbsp;<a href="#" target="_blank">Help</a></td>
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
  
         <br><logic:present name="SEARCHLIST" scope="request">
                 <tr>
                 <td> 
                   <%@ include file="/jsp/common/Buttons.jsp" %>
               
                  <b><font color ="red"><bean:message key ="customerservice.cardstorenewal"/></font></b><%=((CommonDataBean)((ArrayList)request.getAttribute("SEARCHLIST")).get(0)).getColumn4() %>
                  <html:button property="submitbutton" onclick="cardsRenProcess('renewalProcess')" > <bean:message key ="common.renewal"/></html:button>
                 </td> 
               </tr>
            </table>
         </td>
         </tr>
    </table>
  
     <display:table  id="commonObj" name="requestScope.SEARCHLIST"  
     class="simple" width="80%">
    <display:column property="column1" title="Card Number" class="label" />
    <display:column property="column2" title="Expire Date(MMYY)" class="label" />
    <display:column property="column3" title="Updated Date" class="label" />
    <display:caption><font class="titreSection">Cards To Renewal List</font></display:caption>
   </display:table>
 
   	  </td>
        </tr>
          <tr> <td>
          <div align="right">
           
           </div>
            </td></tr>
   </logic:present>	
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