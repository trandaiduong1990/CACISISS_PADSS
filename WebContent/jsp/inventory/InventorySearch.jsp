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

    function search(action){
    	document.forms[0].method.value=action;
   		/* document.forms[0].action ="inventorysetup.do"; */
   		document.forms[0].submit();
 	}
    
    function addNew(){
    	document.forms[0].method.value='addNew';
   		document.forms[0].action ="inventorysetup.do";
   		document.forms[0].submit();
    }
</script>
<body bgcolor="ffffff">
 
<html:form action ="inventorysearch.do">
<input type="hidden" name="method" value ="">
	<table border="0" cellspacing="0" cellpadding="0" style="border-collapse: collapse" bordercolor="#111111" width="100%">

  <tr>
    <td> 
      <table border="0" cellspacing="0" cellpadding="0" width="100%">
        <tr>
            <td class="titreSection"><bean:message key ="inventory.orderstock"/></td>
        </tr>
        <tr>
          <td background="images/ligne.gif" colspan="2"> <img src="images/spacer.gif" width="1" height="1"></td>
        </tr>
        <tr>
          <td class="titreSection"><table border="0" cellspacing="0" cellpadding="4">
            <tr>
            	<% if(((String)request.getAttribute("ACTION")).equals("order"))	{ %>
              		<td><html:button property="submitbutton" onclick ="addNew()"><bean:message key ="common.addnew"/></html:button><br></td>
              		<td><html:button property="submitbutton" onclick="search('searchOrder')"><bean:message key ="common.search"/></html:button></td>
            	<% } else if(((String)request.getAttribute("ACTION")).equals("authorized")) { %>
            		<td><html:button property="submitbutton" onclick="search('searchAuthorized')"><bean:message key ="common.search"/></html:button></td>
            	<% } else if(((String)request.getAttribute("ACTION")).equals("dispatch")) { %>
            		<td><html:button property="submitbutton" onclick="search('searchDispatch')"><bean:message key ="common.search"/></html:button></td>
            	<% } else if(((String)request.getAttribute("ACTION")).equals("received")) { %>
            		<td><html:button property="submitbutton" onclick="search('searchReceived')"><bean:message key ="common.search"/></html:button></td>
            	<% } else if(((String)request.getAttribute("ACTION")).equals("return")) { %>
            		<td><html:button property="submitbutton" onclick="search('searchReturn')"><bean:message key ="common.search"/></html:button></td>
            	<% } %>
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
                        <td class="ColumnFONT"><bean:message key ="inventory.orderno"/></td>
				          <td> 
				             <html:select property="orderNo" name="inventorySearchForm">
				             	<html:option value=""> </html:option>	
								<html:optionsCollection property="orderNoList" value="key" label="value" />
							</html:select>
				          </td>
                      </tr>
                    </table>                
                 </td>
              </tr>
            </table>
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