<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/displaytag-12.tld" prefix="display" %>
<%@page import = "java.util.*" %>
<%@page import = "org.transinfo.cacis.formbean.cardproduction.ApplicationFormSuccess" %>
<html:html>
<head>
<title><bean:message key ="applicationprocess.screentitle"/></title>
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
<script language="JavaScript">
<!--
//-->
</script>
</head>
<body bgcolor="ffffff">
<html:form action ="applicationprocesslist.do">
<table border="0" cellspacing="0" cellpadding="0" style="border-collapse: collapse" bordercolor="#111111" width="100%">
  <tr>
    <td> 
      <table border="0" cellspacing="0" cellpadding="0" width="100%">
        <tr>
            <td class="titreSection"><bean:message key ="applicationprocess.screentitle"/></td>
        </tr>
        <tr>
          <td background="images/ligne.gif" colspan="2"> <img src="images/spacer.gif" width="1" height="1"></td>
        </tr>
        <tr>
          <td class="titreSection"><table border="0" cellspacing="0" cellpadding="4">
            <tr>
              <td><html:button property="submitbutton" onclick="searchlist()"><bean:message key ="common.cancel"/></html:button></td>
              <td>&nbsp;</td>
            </tr>
          </table></td>
        </tr>
        <tr>
          <td background="images/ligne.gif" colspan="2"><img src="images/spacer.gif" width="1" height="1"></td>
        </tr>
      </table>
    </td>
  </tr>
  <tr>
    <td valign="top">
      <table width="100%">

        <tr>
          <td valign="top" class="ErrFONT"><table width="100%" border="0" cellspacing="0" cellpadding="4">
            <tr>
              <td class="ErrFONT"><font color="#FF0000"><html:errors /></font></td>
            </tr>
          </table></td>
        </tr>
  
        <tr> 
          <td valign="top">
     <logic:present name="APPSUCCESSPRIMCARDLIST" scope="request">   
      <%
      	  ArrayList  arl =(ArrayList)request.getAttribute("APPSUCCESSPRIMCARDLIST");
           ApplicationFormSuccess objAppSuccess =(ApplicationFormSuccess)arl.get(0);
     %> 
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr> 
                <td> 
                    <table cellpadding="0" cellspacing="4">
                      <tr>
                        <td nowrap class="ColumnFONT"><bean:message key ="applicationform.customername"/> </td>
                        <td><%=objAppSuccess.getCustomerName()%></td>
                        <td>&nbsp;&nbsp;&nbsp;</td>
                        <td nowrap class="ColumnFONT"><bean:message key ="applicationform.idnumber"/> </td>
                        <td><%=objAppSuccess.getIdNumber()%></td>
                      </tr>
                    </table>                </td>
              </tr>
            </table>
          </td>
        </tr>
        <tr> 
          <td valign="top">
    <br>
   <display:table  id="objAppSucces" name="requestScope.APPSUCCESSPRIMCARDLIST"  
     width="80%" class="simple" >
   <display:column property="cardNumber" title  = "CardNumber" />
   <display:column property="cardExpDate" title = "ExpireDate" />
   <display:column property="cardProduct" title = "CardProductName" />
   <display:column property="creditLimit" title = "CreditLimit" />
   <display:column property="currency" title="Currency" />
   <display:caption><font class="titreSection">Primary Card Generations Details </font></display:caption>
    </display:table>
   </logic:present>
   <br> 
  <logic:present name="APPSUCCESSSUPPCARDLIST" scope="request">
    <display:table  id="objAppSucces" name="requestScope.APPSUCCESSSUPPCARDLIST"  
    width="80%" class="simple">
   <display:column property="cardNumber" title  = "CardNumber" />
   <display:column property="cardExpDate" title = "ExpireDate" />
   <display:column property="cardProduct" title = "CardProductName" />
   <display:column property="creditLimit" title = "CreditLimit" />
   <display:column property="currency" title="Currency" />
   <display:caption><font class="titreSection">Supplementary Card Generation Details</font> </display:caption>
    </display:table>
         </td>
        </tr>
        <tr> 
          <td valign="top">&nbsp; </td>
        </tr>
      </table>
    </td>
  </tr>
  </logic:present> 
</table>
</html:form>
</body>
</html:html>
