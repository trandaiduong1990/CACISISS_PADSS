<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/displaytag-12.tld" prefix="display" %>
<%@page import="org.transinfo.cacis.common.CommonDataBean" %>

<html:html>
<head>
<title><bean:message key ="salaryprofile.setupscreen"/></title>
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

    function go(method) 
    {
      document.forms[0].method.value=method;
      document.forms[0].submit();	
    }
    
    function showList(){
        document.forms[0].action= "salaryprofileprocess.do?method=search";
        document.forms[0].submit();
     } 
</script>
<body bgcolor="ffffff">
<html:form  action ="salaryprofileprocess">
<html:hidden property="issuerId" value="<%=(String)session.getAttribute("ISSUER")%>"/>
<html:hidden property="userId" value="<%=(String)session.getAttribute("USERID")%>"/>
<input type ="hidden" name ="method" />
<table border="0" cellspacing="0" cellpadding="0" style="border-collapse: collapse" bordercolor="#111111" width="100%">
  <tr>
    <td> 
      <table border="0" cellspacing="0" cellpadding="0" width="100%">
        <tr>
            <td class="titreSection"><bean:message key ="salaryprofile.setupscreen"/> </td>
        </tr>
        <tr>
          <td background="images/ligne.gif" colspan="2"> <img src="images/spacer.gif" width="1" height="1"></td>
        </tr>
        <tr>
          <td class="titreSection"><table border="0" cellspacing="0" cellpadding="4">
            <tr>
             <td><html:button property ="addnew" onclick ="addNew()"><bean:message key ="common.addnew"/></html:button><br></td>
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
                        <% if((request.getAttribute("ACTION") != null) && (((String)request.getAttribute("ACTION")).equals("change") || ((String)request.getAttribute("ACTION")).equals("update"))){ %>
                        	<html:select property="cardProduct" disabled="true">
						       <html:option value=""> </html:option>	
						       <html:optionsCollection property="productList" value="key" label="value" />
						    </html:select>
						    <html:hidden property="cardProduct" name="salaryProfileForm" />
						<% }else{ %>
							<html:select property="cardProduct">
						       <html:option value=""> </html:option>	
						       <html:optionsCollection property="productList" value="key" label="value" />
						    </html:select>
						<% } %>
						</td>
                      </tr>
                    </table>
                </td>
              </tr>
            </table>
          </td>
        </tr> 
        <tr> 
          <td valign="top">&nbsp;
       		<bean:size id="size" name="salaryProfileForm" property="productList"/>
       		<logic:lessEqual name="size" value="0" >
       			<div style="color: green;padding-bottom: 10px;">
       				All Products Configured. You can update those values.
       			</div>
       		</logic:lessEqual>
          </td>
        </tr>
        <tr> 
          <td valign="top">
          	<table width="80%">
          		<tr>
          			<th align="left"><bean:message key ="salaryprofile.minsalary"/></th>
          			<th align="left"><bean:message key ="salaryprofile.maxsalary"/></th>
          			<th align="left"><bean:message key ="salaryprofile.creditlimit"/></th>
          			<th align="left"><bean:message key ="salaryprofile.cashadvlimit"/></th>
          		</tr>
          		<tr>
          			<td><html:text property="salarPprofile1.minSalary" maxlength="10"/></td>
          			<td><html:text property="salarPprofile1.maxSalary" maxlength="10"/></td>
          			<td><html:text property="salarPprofile1.creditLimit" maxlength="10"/></td>
          			<td><html:text property="salarPprofile1.cashAdvancedLimit" maxlength="10"/></td>
          		</tr>
          		<tr>
          			<td><html:text property="salarPprofile2.minSalary" maxlength="10"/></td>
          			<td><html:text property="salarPprofile2.maxSalary" maxlength="10"/></td>
          			<td><html:text property="salarPprofile2.creditLimit" maxlength="10"/></td>
          			<td><html:text property="salarPprofile2.cashAdvancedLimit" maxlength="10"/></td>
          		</tr>
          		<tr>
          			<td><html:text property="salarPprofile3.minSalary" maxlength="10"/></td>
          			<td><html:text property="salarPprofile3.maxSalary" maxlength="10"/></td>
          			<td><html:text property="salarPprofile3.creditLimit" maxlength="10"/></td>
          			<td><html:text property="salarPprofile3.cashAdvancedLimit" maxlength="10"/></td>
          		</tr>
          		<tr>
          			<td><html:text property="salarPprofile4.minSalary" maxlength="10"/></td>
          			<td><html:text property="salarPprofile4.maxSalary" maxlength="10"/></td>
          			<td><html:text property="salarPprofile4.creditLimit" maxlength="10"/></td>
          			<td><html:text property="salarPprofile4.cashAdvancedLimit" maxlength="10"/></td>
          		</tr>
          	</table>
          </td>
        </tr>
        <tr> 
          <td valign="top">&nbsp; </td>
        </tr>
        <tr> 
          <td valign="top" align="left">
	          <% if(request.getAttribute("ACTION") == null || ((String)request.getAttribute("ACTION")).equals("add")) {%>
				<html:button property="submitbutton" onclick="go('add')" ><bean:message key ="common.save"/></html:button>
      		  <% }else if(((String)request.getAttribute("ACTION")).equals("change") || ((String)request.getAttribute("ACTION")).equals("update")) { %>
				<html:button property="submitbutton" onclick="go('update')"><bean:message key ="common.save"/></html:button>
				<html:button property="submitbutton" onclick ="showList()"><bean:message key ="common.cancel"/></html:button>
      		 <% }else if(((String)request.getAttribute("ACTION")).equals("cancel")) { %>  	
      			<html:button property="submitbutton" onclick ="showList()"><bean:message key ="common.cancel"/></html:button>
      		 <%}%>
	      </td>
        </tr>
      </table>
    </td>
  </tr>
</table>
</html:form>    
</body>
</html:html>