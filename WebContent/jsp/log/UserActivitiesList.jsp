<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/displaytag-12.tld" prefix="display" %>
<%@page import="org.transinfo.cacis.common.CommonDataBean" %>
<%@page import="java.util.ArrayList" %>

<html:html>
<head>
<title><bean:message key ="useractivitiessearch.screentitle"/></title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="inc/css/style.css" type="text/css">
<script src="inc/js/cacis.js"></script>
<script src="inc/js/setup.js"></script>

<script type="text/javascript">
function searchlist(){
	if(dateValidationOnFormSubmit(document.userActivitiesSearchForm.startDate, document.userActivitiesSearchForm.endDate)){
        //alert('2');
       	document.forms[0].submit();
    }
}

function go(action){
	document.forms[0].mode.value=action;
 	//document.forms[0].action ="keyindexlist.do?method=search";
	if(dateValidationOnFormSubmit(document.userActivitiesSearchForm.startDate, document.userActivitiesSearchForm.endDate)){
        //alert('2');
       	document.forms[0].submit();
    }
}
</script>

</head>
<script src="inc/js/cacis.js"></script>
<body bgcolor="ffffff">

<html:form  action ="useractivitieslist">
<html:hidden property="issuerId" value="<%=(String)session.getAttribute("ISSUER")%>"/>
<input type=hidden name="method"/>
<table border="0" cellspacing="0" cellpadding="0" style="border-collapse: collapse" bordercolor="#111111" width="100%">
  <tr>
    <td> 
      <table border="0" cellspacing="0" cellpadding="0" width="100%">
        <tr>
            <td class="titreSection"><bean:message key ="useractivitiessearch.screentitle"/></td>
        </tr>
        <tr>
          <td background="images/ligne.gif" colspan="2"> <img src="images/spacer.gif" width="1" height="1"></td>
        </tr>
        <tr>
	  <td class="titreSection"><table border="0" cellspacing="0" cellpadding="4">
	  <tr>	    
	    <td><b><html:button property="submitbutton" onclick="searchlist()"><bean:message key ="common.search"/></html:button></b></td>
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
                    <table cellpadding="0" cellspacing="0">
		      <tr>		      	    
		      	  <td class="label"><bean:message key ="useractivities.searchname1"/></td>		             
		      	   <td> 
		      	     <html:select property="userId" >
		      	       <html:option value=""></html:option>
		      	       <html:optionsCollection property="userList" value="key" label="value" />
		      	     </html:select>  
		           </td>
		      </tr>		                             
                      <tr>                         
                        <td> <font class="label"><bean:message key ="useractivities.searchname2"/></font></td> 
                        <td> <html:text property="startDate" size="20" onblur="javascript:isValidDate(document.userActivitiesSearchForm.startDate)"/></td>
                      </tr>
                      <tr>                         
                        <td> <font class="label"><bean:message key ="useractivities.searchname3"/></font></td> 
                        <td> <html:text property="endDate" size="20" onblur="javascript:isValidDate(document.userActivitiesSearchForm.endDate)"/></td>
                      </tr>  
		                                    
             <%@ include file="/jsp/common/Buttons.jsp" %>                          
           </html:form> 
          </table>
          </td>
         </tr>
        </table>


<br><logic:present name="SEARCHLIST" scope="request"> 
   <display:table  id="appProcess" name="requestScope.SEARCHLIST"  
     class="simple" width="80%">
   <display:column property="column1" title="DateTime" class="label" />
   <display:column property="column2" title="Activity" class="label" />
   <display:column property="column3" title="StationIp" class="label" />   
   <display:caption><font class="titreSection">User Activities Records</font></display:caption>
 	
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