<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/displaytag-12.tld" prefix="display" %>

<html:html>
<head>
<title><bean:message key ="applicationprocesshistory.screentitle"/></title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="inc/css/style.css" type="text/css">
</head>

<body bgcolor="ffffff">

<table border="0" cellspacing="0" cellpadding="0" style="border-collapse: collapse" bordercolor="#111111" width="100%">
  <tr>
    <td> 
      <table border="0" cellspacing="0" cellpadding="0" width="100%">
        <tr>
            <td class="titreSection"><bean:message key ="applicationprocesshistory.screentitle"/></td>
        </tr>
        <tr>
          <td background="images/ligne.gif" colspan="2"> <img src="images/spacer.gif" width="1" height="1"></td>
        </tr>
        <tr>
          <td class="titreSection">
              <table border="0" cellspacing="2" cellpadding="2" width="157">
                <tr> 
                <td class="texteMenuGauche"> 
                    <p class="titreSection">

                    &nbsp;&nbsp;</p>
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
           <html:form  action ="appprocess.do">
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr> 
                <td> 
                    <table cellpadding="0" cellspacing="0">
                                    
                  </table>
                </td>
              </tr>
            </table>

  <logic:present name="CUSTOMERHISTORYLIST" scope="request">
  
    <display:table  id="commonObj" name="requestScope.CUSTOMERHISTORYLIST"  
     class="simple" width="80%">
	    <display:column property="column1" title="ApplicationId" />
	   <display:column property="column2" title="ApplicationStatus" />
	   <display:column property="column3" title="CardStatus" />
	   <display:column property="column4" title="CardNumber" />
	   <display:column property="column5" title="NRIC" />
	   <display:column property="column6" title="CreditLimit" />
	   <display:column property="column7" title="CardType" />
	   <display:column property="column8" title="CardProductName" />
	   <display:column property="column9" title="CardProductType" />
	   <display:column property="column10" title="ActionDate" />
   <display:caption><font class="titreSection">CustomerHistory Details </font></display:caption>
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