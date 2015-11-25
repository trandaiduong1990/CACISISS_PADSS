<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/displaytag-12.tld" prefix="display" %>
<%@page import="org.transinfo.cacis.common.CommonDataBean" %>
<%@page import="org.transinfo.cacis.formbean.useraccess.UserSetupSearchForm" %>
<%@page import="java.util.ArrayList" %>

<bean:define id ="formid" name ="userSetupSearchForm" />

<html:html>
<head>
<title><bean:message key ="usersetupsearch.screentitle"/></title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="inc/css/style.css" type="text/css">
</head>
<script src="inc/js/cacis.js"></script>
<script src="inc/js/jquery-1.11.3.min.js"></script>
<script>
function doSetup()
{
//alert("doSetup");
  	document.forms[0].action="usersetupprocess.do?method=addNew";
	document.forms[0].submit();
}

function searchlist(){
	//alert(document.forms.length);
	//alert(document.forms[0].name);
	//document.forms[0].submit();
	document.userSetupSearchForm.submit();
}

function updateForm(){
	//alert(document.forms.length);
	//alert(document.forms[0].name);
	//alert(document.forms[1].name);
	document.listForm.submit();
}

function userTypeChange(event) {
	var userType = "";
	if(typeof event != 'undefined' && event != null && event != "") {
		userType = event.value;
	}
	$.ajax({
        type: "POST",
        url: "usersetuplistpage.do?method=setRole",
        data: "userType=" + userType,
        success: function(response){
            // we have the response
           $('#roleIdDiv').html(response);
        },
        error: function(e){
            alert('Error: ' + e);
        }
    });
}

$(document).ready(function() {
	userTypeChange();
});
</script>

<body bgcolor="ffffff">

<html:form action ="usersetuplistpage.do">
<html:hidden property="issuerId" value="<%=(String)session.getAttribute("ISSUER")%>"/>
<html:hidden property="userId" value="<%=(String)session.getAttribute("USERID")%>"/> 	  
<%-- <html:hidden property="userType" value="<%= ((UserSetupSearchForm)pageContext.getAttribute("formid")).getUserType()%>"/>  --%>
<table border="0" cellspacing="0" cellpadding="0" style="border-collapse: collapse" bordercolor="#111111" width="100%">
  <tr>
    <td> 
      <table border="0" cellspacing="0" cellpadding="0" width="100%">
        <tr>
        <td class="titreSection"><bean:message key ="usersetupsearch.screentitle"/></td>
        </tr>
        <tr>
          <td background="images/ligne.gif" colspan="2"> <img src="images/spacer.gif" width="1" height="1"></td>
        </tr>
        <tr>
	  <td class="titreSection"><table border="0" cellspacing="0" cellpadding="4">
	   <tr>
	     <td><b><html:button property="addNew" onclick="doSetup()"><bean:message key ="common.addnew"/></html:button></b></td>	    
	     <td><html:button property="search" onclick="searchlist()"><bean:message key ="common.search"/></html:button></td>
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
                    <table cellpadding="0" cellspacing="4">
                      <tr>                         
                        <td nowrap class="ColumnFONT"><bean:message key ="usersetup.searchname1"/></td> 
                        <td><html:text property="searchUserId" size="30" maxlength="30" onkeypress="return disableEnterKey(event)"/></td>
                      </tr>              
                      <tr>                            
                         <td nowrap class="ColumnFONT"><bean:message key ="usersetup.usertype"/></td>		             
		      	  <td>
		      	     <html:select property="userType" onchange="userTypeChange(this)" >
		      	       <html:option value=""></html:option>
		      	       <html:optionsCollection property="userTypeList" value="key" label="value" />
		      	     </html:select>
		      	  </td>
		      </tr>
                      <tr>                            
                         <td nowrap class="ColumnFONT"><bean:message key ="usersetup.searchname2"/></td>		             
		      	  <td>
		      	  	<div id="roleIdDiv"></div>
		      	     <%-- <html:select property="searchRoleId" >
		      	       <html:option value=""></html:option>
		      	       <html:optionsCollection property="roleList" value="key" label="value" />
		      	     </html:select> --%>
		      	  </td>
		      </tr>
		      <tr>		      	    
		      	  <td nowrap class="ColumnFONT"><bean:message key ="usersetup.searchname3"/></td>		             
		      	   <td> 
		      	     <html:select property="searchStatus" >
		      	       <html:option value=""></html:option>
		      	       <html:optionsCollection property="statusList" value="key" label="value" />
		      	     </html:select>  
		           </td>
		       </tr>		         
                    <tr>  
                    <td valign="top">			  	    
	    	    <%@ include file="/jsp/common/Buttons.jsp" %>                                                                                
                  </table>
                </td>
              </tr>
            </table>           	   	 
</html:form>

<br><logic:present name="SEARCHLIST" scope="request"> 
   <display:table  id="appProcess" name="requestScope.SEARCHLIST"  
     class="simple" width="80%">
   <display:column property="column1" title="User Id" class="label" />
   <display:column property="column2" title="Name" class="label" />
   <display:column property="column3" title="Role" class="label" />
   <display:column property="column4" title="Status" class="label" />   
   <display:column property="column5" title="Updated Date" class="label" />   
   <display:caption><font class="titreSection">User Setup Records</font></display:caption>
   <font class="label"><display:column title="Details" align="center" width="80" ></font>
  	
  	   <form name ="listForm" action="usersetupprocess.do">	 
	
              <html:hidden property="frmUserId" value="<%= ((CommonDataBean)pageContext.getAttribute("appProcess")).getColumn1()%>" />
              <html:hidden property="userType" value="<%= ((CommonDataBean)pageContext.getAttribute("appProcess")).getColumn6()%>"/> 
              <%-- <html:hidden property="userType" value="<%= ((UserSetupSearchForm)pageContext.getAttribute("formid")).getUserType()%>"/> --%> 
              <html:hidden property="issuerId" value="<%=(String)session.getAttribute("ISSUER")%>"/>              
              <html:hidden property="searchUserId" value="<%=request.getParameter("searchUserId")%>"/>
              <html:hidden property="firstName" value="<%=((CommonDataBean)pageContext.getAttribute("appProcess")).getColumn2()%>"/> 	    	
              <html:hidden property="method" value ="change"/>
              <html:submit value="Update"/>
              
	</form>
   </display:column>	
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