<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/displaytag-12.tld" prefix="display" %>
<%@page import="org.transinfo.cacis.common.CommonDataBean" %>
<%@page import="org.transinfo.cacis.formbean.useraccess.UserSetupSearchForm" %>
<%@page import="java.util.ArrayList" %>

<bean:define id ="formid" name ="assignUserForm1" />

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
<script>
function doSetup()
{
//alert("doSetup");
  	document.forms[0].action="assignuserprocess.do?method=addNew";
	document.forms[0].submit();
}

function searchlist(){
	//alert(document.forms.length);
	//alert(document.forms[0].name);
	//document.forms[0].submit();
	document.assignUserForm1.submit();
}

</script>

<body bgcolor="ffffff">

<html:form action ="assignuserlist.do">
<html:hidden property="issuerId" value="<%=(String)session.getAttribute("ISSUER")%>"/>
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
                        <td nowrap class="ColumnFONT"><bean:message key ="assignuser.role"/></td> 
                        <td>
							<html:select property="roleId" >
				      	       <html:option value=""></html:option>
				      	       <html:optionsCollection property="roleList" value="key" label="value" />
				      	     </html:select>
						</td>
                        <td width="15px"></td>
                        <td nowrap class="ColumnFONT"><bean:message key ="assignuser.user"/></td>		             
				      	 <td>
				      	     <html:select property="userId" >
				      	       <html:option value=""></html:option>
				      	       <html:optionsCollection property="userList" value="key" label="value" />
				      	     </html:select>
				      	 </td>
                      </tr>              
				      <tr>		      	    
				      	  <td nowrap class="ColumnFONT"><bean:message key ="assignuser.lower"/></td>		             
				      	   <td> 
				      	     <html:text property="lower" size="14" maxlength="14"/> 
				           </td>
				           <td width="15px"></td>
				           <td nowrap class="ColumnFONT"><bean:message key ="assignuser.upper"/></td>	
				           <td> 
				      	     <html:text property="upper" size="14" maxlength="14"/> 
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
   <display:column property="column1" title="Assign Id" class="label" />
   <display:column property="column2" title="Role" class="label" />
   <display:column property="column3" title="User" class="label" />
   <display:column property="column4" title="Status" class="label" />   
   <display:caption><font class="titreSection">Assign User Records</font></display:caption>
   <font class="label"><display:column title="Details" align="center" width="80" ></font>
  	
  	   <form name ="listForm" action="assignuserprocess.do">	 
	
              <html:hidden property="assignId" value="<%= ((CommonDataBean)pageContext.getAttribute("appProcess")).getColumn1()%>" />
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