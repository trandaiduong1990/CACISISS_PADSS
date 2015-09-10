<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/displaytag-12.tld" prefix="display" %>
<%@page import="org.transinfo.cacis.common.CommonDataBean" %>

<html:html>
<head>
<title><bean:message key ="salaryprofile.searchscreen"/></title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="inc/css/style.css" type="text/css">
</head>
<script src="inc/js/cacis.js"></script>
<script language="JavaScript">
  
    function addNew() {
       document.forms[0].action= "salaryprofileprocess.do?method=addNew";
       document.forms[0].submit();
    }

    function change() {
        document.forms[0].action= "salaryprofileprocess.do?method=change";
        document.forms[0].submit();
     }

    function go(method) 
    {
      document.forms[0].method.value=method;	
      document.forms[0].submit();
    }

    function searchlist(){
 		document.forms[0].submit();
 	}
</script>
<body bgcolor="ffffff">
<html:form  action ="salaryprofileprocess">
<html:hidden property="issuerId" value="<%=(String)session.getAttribute("ISSUER")%>"/>
<input type ="hidden" name ="method" />
<table border="0" cellspacing="0" cellpadding="0" style="border-collapse: collapse" bordercolor="#111111" width="100%">
  <tr>
    <td> 
      <table border="0" cellspacing="0" cellpadding="0" width="100%">
        <tr>
            <td class="titreSection"><bean:message key ="salaryprofile.searchscreen"/> </td>
        </tr>
        <tr>
          <td background="images/ligne.gif" colspan="2"> <img src="images/spacer.gif" width="1" height="1"></td>
        </tr>
        <tr>
          <td class="titreSection"><table border="0" cellspacing="0" cellpadding="4">
            <tr>
             <td><html:button property ="addnew" onclick ="addNew()"><bean:message key ="common.addnew"/></html:button><br></td>
              <td><html:button property="submitbutton" onclick="go('search')" ><bean:message key ="common.search"/></html:button></td>
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
                        <td nowrap class="ColumnFONT"><bean:message key ="salaryprofile.cardproduct"/></td>
                        <td> 
                        	<html:select property="cardProduct">
						       <html:option value=""> </html:option>	
						       <html:optionsCollection property="productList" value="key" label="value" />
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
          <td valign="top"> 
	      	<br>
	      	<logic:present name="SEARCHLIST" scope="request">
			    <display:table  id="commonObj" name="requestScope.SEARCHLIST" class="simple" width="80%">
				   <display:column property="column1" title="Credit Limit" class="label" />
				   <display:column property="column2" title="Cash Advance Limit" class="label" />
				   <display:column property="column3" title="Minimum Salary" class="label" />
				   <display:column property="column4" title="Maximum Salary" class="label" />
				   <display:caption><font class="titreSection">Salary Profile Records</font></display:caption>	
			    </display:table>
		   </logic:present>
		  </td>
        </tr>
        <tr> 
          <td valign="top">&nbsp; </td>
        </tr>
        <tr> 
        <logic:present name="SEARCHLIST" scope="request">
          <td valign="top" align="left">
	          <html:button property="update" onclick="change()" ><bean:message key ="common.update"/></html:button>
	      </td>
        </logic:present>
        </tr>
      </table>
    </td>
  </tr>
</table>
</html:form>    
</body>
</html:html>