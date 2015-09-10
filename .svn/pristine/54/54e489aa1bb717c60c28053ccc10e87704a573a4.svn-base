<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/displaytag-12.tld" prefix="display" %>
<%@page import="org.transinfo.cacis.common.CommonDataBean" %>

<html:html>
<head>
<title><bean:message key ="cardpromotionsetupsearch.screentitile"/></title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="inc/css/style.css" type="text/css">
</head>
<script src="inc/js/cacis.js"></script>

<script language="JavaScript">
  
    function addNew() {
       document.forms[0].action="cardpromotionprocess.do?method=addNew";
       document.forms[0].submit();
       }

    function searchlist(){
 		document.forms[0].submit();
 	}
</script>
<body bgcolor="ffffff">
      <html:form  action ="cardpromotionlist">
<table border="0" cellspacing="0" cellpadding="0" style="border-collapse: collapse" bordercolor="#111111" width="100%">
  <tr>
    <td> 
      <table border="0" cellspacing="0" cellpadding="0" width="100%">
        <tr>
            <td class="titreSection"><bean:message key ="cardpromotionsetupsearch.screentitile"/> </td>
        </tr>
        <tr>
          <td background="images/ligne.gif" colspan="2"> <img src="images/spacer.gif" width="1" height="1"></td>
        </tr>
        <tr>
          <td class="titreSection"><table border="0" cellspacing="0" cellpadding="4">
            <tr>
              <td><html:button property="submitbutton" onclick = "addNew()"> <bean:message key ="common.addnew"/></html:button></td>
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
                        <td nowrap class="ColumnFONT"><bean:message key ="cardpromotionsetupsearch.promotiontype"/></td>
                        <td>  <html:select property="promotionId" >
					            <html:option value=""> </html:option>	
					              <html:optionsCollection property="cardPromotionList" value="key" label="value" />
					      </html:select>
                      </td>
                      </tr>
                      <tr><td> <%@ include file="/jsp/common/Buttons.jsp" %></td></tr>
                         
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
   <display:column property="column1" title="PromotionId" class="label" />
   <display:column property="column2" title="promotionType" class="label" />
   <display:column property="column3" title="Updated Date" class="label" />
   <display:caption><font class="titreSection">CardPromotion Setup Records</font></display:caption>
   <font class="label"><display:column title="Details" align="center" width="80" ></font>
  	  <form name ="listForm" action="cardpromotionprocess.do">	 
	
              <html:hidden property="promotionId" value="<%= ((CommonDataBean)pageContext.getAttribute("commonObj")).getColumn1()%>" />
              <html:hidden property="method" value ="change"/>
               <html:submit value="Update"/>
           >
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