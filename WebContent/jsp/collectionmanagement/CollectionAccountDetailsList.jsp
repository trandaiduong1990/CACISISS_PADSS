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
 
<html:form action ="collectionaccountdetailslist.do">
	<table border="0" cellspacing="0" cellpadding="0" style="border-collapse: collapse" bordercolor="#111111" width="100%">

  <tr>
    <td> 
      <table border="0" cellspacing="0" cellpadding="0" width="100%">
        <tr>
            <td class="titreSection"><bean:message key ="codetail.titleSection"/></td>
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
                        <td class="ColumnFONT"><bean:message key ="codetail.cardnumber"/></td>
                        <td><html:text property="cardNo" size="20" maxlength="30"/> </td>	
                        <td width="15px"></td>
                        <td class="ColumnFONT"><bean:message key ="codetail.customername"/></td>
                        <td><html:text property="customerName" size="20" maxlength="30"/> </td>		
                      </tr>
                      <tr> 
                        <td class="ColumnFONT"><bean:message key ="codetail.status"/></td>
                        <td>
                        	<html:select property="status" >
				      	       <html:option value=""></html:option>
				      	       <html:optionsCollection property="statusList" value="key" label="value" />
				      	     </html:select>
				      	</td>	
                        <td width="15px"></td>
                        <td class="ColumnFONT"><bean:message key ="codetail.agents"/></td>
                        <td>
                        	<html:select property="agentId" style="width: 50px !important; min-width: 50px; max-width: 50px;">
				      	       <html:option value=""></html:option>
				      	       <html:optionsCollection property="agentList" value="key" label="value" />
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
	   	<display:column property="column1" title="Card Number" class="label" />
	   	<display:column property="column2" title="Customer Name" class="label" />
	   	<display:column property="column3" title="Credit Limit" class="label" />
	   	<display:column property="column4" title="Due Amount" class="label" />
	   	<display:column property="column5" title="Created Date" class="label" />
	   	<display:caption><font class="titreSection">Collection Account Details Records</font></display:caption>
	   	<font class="label"><display:column title="" align="center" width="80" >
			<form name ="listForm" action="collectionaccountdetailsprocess.do">
				<html:hidden property="colectId" value="<%= ((CommonDataBean)pageContext.getAttribute("commonObj")).getColumn6()%>" />       
				<html:hidden property="customerName" value="<%= ((CommonDataBean)pageContext.getAttribute("commonObj")).getColumn2()%>" />       
				<html:hidden property="creditLimit" value="<%= ((CommonDataBean)pageContext.getAttribute("commonObj")).getColumn3()%>" />       
				<html:hidden property="dueAmt" value="<%= ((CommonDataBean)pageContext.getAttribute("commonObj")).getColumn4()%>" />       
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