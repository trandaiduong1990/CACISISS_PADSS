<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/displaytag-12.tld" prefix="display" %>
<%@page import="org.transinfo.cacis.common.CommonDataBean" %>

<html:html>
<head>
<title><bean:message key ="cardlevellimit.title"/></title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="inc/css/style.css" type="text/css">
</head>
<script src="inc/js/cacis.js"></script>
<script language="JavaScript">
  
    function addNew() {
       ///document.forms[0].method.value=action;
       document.forms[0].action="cardlevellimitprocess.do?method=addNew";
       document.forms[0].submit();
      /// document.forms[0].submit();
    }

    function searchlist(){
 		document.forms[0].submit();
 	}
    

    function go(action) {
    	document.forms[0].mode.value=action;
     	document.forms[0].action ="cardlevellimitlist.do";
 	 	document.forms[0].submit();
    }
    
</script>
<body bgcolor="ffffff">
 
 <html:form  action ="cardlevellimitlist">

<html:hidden property="issuerId" value="<%=(String)session.getAttribute("ISSUER")%>"/>
<html:hidden property="cardHolderType"/>
 
<table border="0" cellspacing="0" cellpadding="0" style="border-collapse: collapse" bordercolor="#111111" width="100%">

  <tr>
    <td> 
      <table border="0" cellspacing="0" cellpadding="0" width="100%">
        <tr>
            <td class="titreSection"><bean:message key ="cardlevellimit.title"/></td>
        </tr>
        <tr>
          <td background="images/ligne.gif" colspan="2"> <img src="images/spacer.gif" width="1" height="1"></td>
        </tr>
        <tr>
          <td class="titreSection"><table border="0" cellspacing="0" cellpadding="4">
            <tr>
              <!-- <td><html:button property="submitbutton" onclick ="addNew()"><bean:message key ="common.addnew"/></html:button><br></td>  -->
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
                        <td class="ColumnFONT"><bean:message key ="cardlevellimit.cardno"/></td>
                        <td>
                        	<html:text property="searchCardNo" />
                        </td>
                      </tr>
                      <tr style="height: 20px;">
                      	<td></td>
                      </tr>
                      <tr>
                        <td class="ColumnFONT">
				             <%
				            	if(request.getAttribute("ACTION") != null && ((String)request.getAttribute("ACTION")).equals("add")) {
							 %>
							 	<html:button property="submitbutton" onclick="addNew()" ><bean:message key ="common.addnew"/></html:button>
				        	 <%}%>
                        </td>
                        <td style="font-size: 11px;font-family: verdana;">
                        	<html:hidden property="cardNo" name="cardlevellimitSearchForm" />
                        	<bean:write name="cardlevellimitSearchForm"  property="cardNo"/>
                        </td>
                      </tr>
                                      
                      <tr>
                        <td>&nbsp;<%@ include file="/jsp/common/Buttons.jsp"%></td>
                        <td><div align="right"></div></td>
                      </tr>
                    </table>                </td>
              </tr>
            </table>
          </td>
        </tr>
      </html:form>   
        <tr> 
          <td valign="top">  
 
   <br><logic:present name="SEARCHLIST" scope="request">
  
    <display:table  id="commonObj" name="requestScope.SEARCHLIST"  
     class="simple" width="80%">
   <display:column property="column1" title="Card No" class="label" />
   <display:column property="column7" title="Status" class="label" />
   <display:column property="column2" title="Daily Limit Count Domestic" class="label" />
   <display:column property="column3" title="Daily Limit Amt Domestic" class="label" />
   <display:column property="column4" title="Cash Limit Domestic" class="label" />
   <display:column property="column5" title="Purchase Limit Domestic" class="label" />
    <display:column property="column6" title="Updated Date" class="label" nowrap="" />
   <display:caption><font class="titreSection">Card Level Limit Records</font></display:caption>
   <font class="label"><display:column title="Details" align="center" width="80" ></font>
  	  <form name ="listForm" action="cardlevellimitprocess.do">	 
	
              <html:hidden property="cardNo" value="<%= ((CommonDataBean)pageContext.getAttribute("commonObj")).getColumn1()%>" />
              <html:hidden property="issuerId" value="<%=(String)session.getAttribute("ISSUER")%>"/>            
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