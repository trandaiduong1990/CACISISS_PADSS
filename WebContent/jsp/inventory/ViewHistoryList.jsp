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
  
    function searchlist(){
 		document.forms[0].submit();
 	}
    
</script>
<body bgcolor="ffffff">
 
<html:form action ="viewhistory.do">
	<html:hidden property="issuerId" value="<%=(String)session.getAttribute("ISSUER")%>"/>      
	<table border="0" cellspacing="0" cellpadding="0" style="border-collapse: collapse" bordercolor="#111111" width="100%">

  <tr>
    <td> 
      <table border="0" cellspacing="0" cellpadding="0" width="100%">
        <tr>
            <td class="titreSection"><bean:message key ="vh.titreSection"/></td>
        </tr>
        <tr>
          <td background="images/ligne.gif" colspan="2"> <img src="images/spacer.gif" width="1" height="1"></td>
        </tr>
        <tr>
          <td class="titreSection"><table border="0" cellspacing="0" cellpadding="4">
            <tr>
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
                        <td class="ColumnFONT"><bean:message key ="vh.status"/></td>
                        <td> 
                        	<html:select property="status" >
                        		<html:option value=""> </html:option>	
                        		<html:option value="R">Completed</html:option>	
                        		<html:option value="C">Canceled</html:option>	
                        		<html:option value="B">Return</html:option>	
                        		<html:option value="A">All</html:option>	
					        </html:select>
                        </td>
                        <td style="width: 20px;"></td>
                         <td class="ColumnFONT"><bean:message key ="vh.branch"/></td>
                        <td> 
                        	<html:select property="branchId" >
                        		<html:option value=""> </html:option>	
					         	<html:optionsCollection property="branchIdList" value="key" label="value" />
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

    <display:table  id="commonObj" name="requestScope.SEARCHLIST" class="simple" width="90%">
	   	<display:column property="column1" title="Order No" class="label" />
	   	<display:column property="column2" title="Card Product" class="label" />
	   	<display:column property="column3" title="QTy" class="label" />
	   	<display:column property="column4" title="Branch" class="label" />
	   	<display:column property="column5" title="Status" class="label" />
	   	<display:column property="column6" title="Order Date" class="label" />
	   	<display:column property="column7" title="Dispatch Date" class="label" />
	   	<display:column property="column8" title="Received Date" class="label" />
	   	<display:caption><font class="titreSection">View History Records</font></display:caption>
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