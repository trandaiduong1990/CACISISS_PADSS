<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/displaytag-12.tld" prefix="display" %>

<html:html>
 <head>
<title><bean:message key ="customerrelation.screentitle"/></title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="inc/css/style.css" rel="stylesheet" type="text/css" />
</head>

<body bgcolor="ffffff">
  <table border="0" cellspacing="0" cellpadding="0" style="border-collapse: collapse" bordercolor="#111111" width="100%">
  <tr>
    <td>
      <table border="0" cellspacing="0" cellpadding="0" width="100%">
        <tr>
            <td class="titreSection"><bean:message key ="customerrelation.opencallstitle"/></td>
        </tr>
        <tr>
          <td background="images/ligne.gif" colspan="2"> <img src="images/spacer.gif" width="1" height="1"></td>
        </tr>
        <tr>
          <td class="titreSection">
              <table border="0" cellspacing="2" cellpadding="2" width="157">
                <tr>
                <td class="texteMenuGauche">
                    <p class="titreSection">&nbsp;</p>
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
      <table width="100%">
         <tr>
          <td valign="top">
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td>
                    <table cellpadding="0" cellspacing="0">
                      <tr>
                       <td>  <font color="#FF0000"> <html:errors/> </font> </td>
                      </tr>
                    </table>  </td>
              </tr>
              <tr>
                <td>
                  <table cellpadding="0" cellspacing="0">
                    <tbody>
                    <tr>
                      <td valign=top>
                          <table cellspacing=0 width="100%" border=0 cellpadding="0">
                            <tbody>
                            </tbody>
                          </table>                      </td>
                    </tr>
                    </tbody>
                  </table>                </td>
              </tr>
            </table>          </td>
        </tr>
       
        <tr>
          <td valign="top">
              <table class="wrapper" border="0" cellspacing="1" cellpadding="2">
    <br><logic:present name="SEARCHLIST" scope="request">
  
    <display:table  id="commonObj" name="requestScope.SEARCHLIST"  
     class="simple" width="80%">
   <display:column property="column1" title="CardNumber" class="label"  />
    <display:column property="column2" title="Name" class="label"  />
   <display:column property="column3" title="ReferenceNo" class="label" href="csrlist.do"  paramId ="RefNo" paramProperty="column3" />
    <display:column property="column4" title="CallType" class="label" />
    <display:column property="column5" title="DateTime" class="label" />
     <display:caption><font class="titreSection">Open Calls Records</font></display:caption>
    </display:table>
   </logic:present>
          
			  </table>          </td>
        </tr>
      </table>
    </td>
  </tr>
</table>
 
</body>
</html:html>