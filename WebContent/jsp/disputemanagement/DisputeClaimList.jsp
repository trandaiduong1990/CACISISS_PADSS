<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/displaytag-12.tld" prefix="display" %>
<%@page import="org.transinfo.cacis.common.CommonDataBean" %>
<html:html>
<head>
<title><bean:message key ="disputemanagement.searchscreentitle"/></title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="inc/css/style.css" type="text/css">
<script type="text/javascript">
function searchlist(){
	document.forms[0].submit();
}
</script>
</head>
<body bgcolor="ffffff">
<html:form action="claimformlist.do" >
<table border="0" cellspacing="0" cellpadding="0" style="border-collapse: collapse" bordercolor="#111111" width="100%">

  <tr>
    <td> 
      <table border="0" cellspacing="0" cellpadding="0" width="100%">
        <tr>
            <td class="titreSection"><bean:message key ="disputemanagement.searchscreentitle"/> </td>
        </tr>
        <tr>
          <td background="images/ligne.gif" colspan="2"> <img src="images/spacer.gif" width="1" height="1"></td>
        </tr>
        <tr>
          <td class="titreSection"><table border="0" cellspacing="0" cellpadding="4">
            <tr>
              <td>  <html:button property="submitbutton" onclick="searchlist()"><bean:message key ="common.search"/></html:button></td>
              <td>&nbsp;</td>
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
              <td class="ErrFONT"><font color="#FF0000"><html:errors/></font></td>
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
                        <td nowrap class="ColumnFONT"><bean:message key ="disputemanagement.searchcardnumber"/> </td>
                        <td><html:text property="cardNumber" /></td>
                      </tr>
                      <tr>
                        <td nowrap class="ColumnFONT"><bean:message key ="disputemanagement.searchtranxdate"/> </td>
                        <td><html:text property="tranxDate" /></td>
                      </tr>
                      <tr>
                        <td nowrap class="ColumnFONT"><bean:message key ="disputemanagement.searchamount"/></td>
                        <td><html:text property="amount" /></td>
                      </tr>
                    </table>                </td>
              </tr>
              <tr>  <td>&nbsp;<%@ include file="/jsp/common/Buttons.jsp"%></td></tr>
            </table>
          </td>
        </tr>
   </html:form>      
        <tr> 
          <td valign="top"> 
          
     <br><logic:present name="SEARCHLIST" scope="request">
  
    <display:table  id="commonObj" name="requestScope.SEARCHLIST"  
     class="simple" width="80%">
   <display:column property="column3" title="Merchant Name" class="label" />
   <display:column property="column4" title="Transaction Date" class="label" />
   <display:column property="column5" title="Transaction Amount" class="label" />
   <display:column property="column6" title="Transaction Currency" class="label" />
   <display:column property="column7" title="Settlement Amount" class="label" />
   <display:column property="column8" title="Settlement Currency" class="label" />
   <display:column property="column9" title="Ref No" class="label" />
   <display:caption><font class="titreSection">Claim Records</font></display:caption>
   <font class="label"><display:column title="Details" align="center" width="80" ></font>
  	  <form name ="listForm" action="claimformprocess.do">	 
	
              <html:hidden property="settlementId" value="<%= ((CommonDataBean)pageContext.getAttribute("commonObj")).getColumn1()%>" />
              <html:hidden property="cardNumber" value="<%= ((CommonDataBean)pageContext.getAttribute("commonObj")).getColumn2()%>" />
              <html:hidden property="method" value ="createClaim"/>
               <html:submit value="CreateClaim"/>
         
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