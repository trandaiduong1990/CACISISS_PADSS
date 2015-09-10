<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/displaytag-12.tld" prefix="display" %>
<%@page import="org.transinfo.cacis.common.CommonDataBean" %>

<html:html>
<head>
<title><bean:message key ="cgf.screentitile.search"/></title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="inc/css/style.css" type="text/css">
</head>
<script src="inc/js/cacis.js"></script>
<script language="JavaScript">
  
    function addNew() {
       document.forms[0].action="delinquencynotificationetupprocess.do?method=addNew";
       document.forms[0].submit();
    }

    function searchlist(){
 		document.forms[0].submit();
 	}
    
    function go(action) {
      	document.forms[0].mode.value = action;
      	searchlist();
      }
</script>
<body bgcolor="ffffff">
 
<html:form action ="delinquencynotificationsetuplist.do">
	<html:hidden property="issuerId" value="<%=(String)session.getAttribute("ISSUER")%>"/>      
	<table border="0" cellspacing="0" cellpadding="0" style="border-collapse: collapse" bordercolor="#111111" width="100%">

  <tr>
    <td> 
      <table border="0" cellspacing="0" cellpadding="0" width="100%">
        <tr>
            <td class="titreSection"><bean:message key ="co.notifySearchTitle"/></td>
        </tr>
        <tr>
          <td background="images/ligne.gif" colspan="2"> <img src="images/spacer.gif" width="1" height="1"></td>
        </tr>
        <tr>
          <td class="titreSection"><table border="0" cellspacing="0" cellpadding="4">
            <tr>
              <td><html:button property="submitbutton" onclick ="addNew()"><bean:message key ="common.addnew"/></html:button><br></td>
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
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr> 
                <td> 
                    <table cellpadding="0" cellspacing="4">
                      <tr> 
                        <td class="ColumnFONT"><bean:message key ="co.policyName"/></td>
                        <td> 
                        	<html:select property="policyId" >
                        		<html:option value=""> </html:option>	
					         	<html:optionsCollection property="policyList" value="key" label="value" />
					        </html:select>
                        </td>
                      </tr>
                    </table>                
                 </td>
              </tr>
            </table>
          </td>
        </tr>
      </html:form>   
        <tr> 
          <td valign="top">  
 
   <logic:present name="SEARCHLIST" scope="request">
   <div style="margin-top: 10px; margin-bottom: -12px;"><%@ include
         file="/jsp/common/Buttons.jsp"%></div>
<br/>

    <display:table  id="commonObj" name="requestScope.SEARCHLIST" class="simple" width="70%">
	   	<display:column property="column1" title="Policy Name" class="label" />
	   	<display:column property="column2" title="Aging/Severity" class="label" />
	   	<display:column property="column3" title="Updated Date" class="label" />
	   	<display:column property="column4" title="Updated By" class="label" />
	   	<font class="label"><display:column title="" align="center" width="80" >
			<form name ="listForm" action="delinquencynotificationetupprocess.do">
				<html:hidden property="id" value="<%= ((CommonDataBean)pageContext.getAttribute("commonObj")).getColumn5()%>" />       
	            <html:hidden property="method" value ="change"/>
	            <html:submit value="Update"/>
			</form>
	   	</display:column>	
	   	</font>
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