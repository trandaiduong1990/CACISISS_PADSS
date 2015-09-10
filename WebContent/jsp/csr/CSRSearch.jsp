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
<script type="text/javascript">
function searchlist(){
	document.forms[0].submit();
}

function quickOpen(){
	document.forms[1].submit();
}
</script>
<script src="inc/js/cacis.js"></script>
</head>

<% 

String servletPath = request.getContextPath();
String url = servletPath+"/csrlist.do";

%>

<body bgcolor="ffffff">
 <html:form   action ="csrlist.do">
  <html:hidden property="issuerId" value="<%=(String)session.getAttribute("ISSUER")%>"/>
     <html:hidden property="userId" value="<%=(String)session.getAttribute("USERID")%>"/>
      
<table border="0" cellspacing="0" cellpadding="0" style="border-collapse: collapse" bordercolor="#111111" width="100%">

  <tr>
    <td>
      <table border="0" cellspacing="0" cellpadding="0" width="100%">
        <tr>
            <td class="titreSection"><bean:message key ="customerrelation.csrsearchtitle"/></td>
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
          <td class="texteMenuGauche"><div align="right">&nbsp;<a href='javascript:helpLink("WRITEOFF")'>Help</a></div>
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
            <font color="#FF0000"><html:errors/></font>
              <tr>
                <td>
                    <table cellpadding="0" cellspacing="0">
                      <tr>
                        <td>
                        	<font class="label"><b><bean:message key ="customerrelation.csrsearchtitles"/></b></font>
						 </td>
                      </tr>
                      <tr>
                        <td>
                        	<font class="label"><bean:message key ="customerrelation.csrsearchlabel"/></font>
                          	<html:text property="searchFiled" onkeypress="return disableEnterKey(event)" />
						  	<html:button property="submitbutton" onclick="searchlist()"><bean:message key ="common.search"/></html:button>
						 </td>
                      </tr>
                    </table>
                </td>
              </html:form>       
              </tr>
              <tr>
              	<td>
              		<br>
              	</td>
              </tr>
              <tr>
                <td>
                  <table cellpadding="0" cellspacing="0">
                    <tbody>
                      <tr>
                        <td>
                        	<font class="label"><b><bean:message key ="customerrelation.csrsearchqpt"/></b></font>
						 </td>
                      </tr>
                    <tr>
                      <td valign=top>
                          <table cellspacing=0 width="100%" border=0 cellpadding="0">
                            <tbody>
                             <html:form action ="csrquickopen.do">
                            <tr>
                              <td>
                              	<font class="label">
                              		<bean:message key ="customerrelation.cardnumber"/>
                              	</font>
                              	<html:text property="quickCardNo" />
                              	
                              	<html:button property="submitbutton" onclick="quickOpen()"><bean:message key ="common.quickopen"/></html:button>
                              	<br>
                              	<font class="label">
                              	<bean:message key ="customerrelation.callrefno"/>
                              	</font>
                              	<html:text property="callRefNo" />
                              	
                              	<html:button property="submitbutton" onclick="quickOpen()"><bean:message key ="common.quickopen"/></html:button>
                              	<br>
                              </td>
                            </tr>
                            </html:form>
                            </tbody>
                          </table>
                      </td>
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
   <display:column property="column1" title="CardNumber" class="label" href ="<%=url%>"  paramId ="cardNo" paramProperty="column1" />
   <display:column property="column2" title="Name" class="label" />
    <display:column property="column3" title="NRICNO" class="label" />
    <display:column property="column4" title="Expire Date" class="label" />
     <display:column property="column5" title="Status" class="label" />
   <display:caption><font class="titreSection">Available Records</font></display:caption>
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