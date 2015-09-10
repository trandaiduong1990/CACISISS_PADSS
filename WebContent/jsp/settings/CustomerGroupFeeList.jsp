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
  
    function addNew() {
       ///document.forms[0].method.value=action;
       document.forms[0].action="customergroupfeeprocess.do?method=addNew";
       document.forms[0].submit();
      /// document.forms[0].submit();
    }

    function searchlist(){
 		document.forms[0].submit();
 	}
</script>
<body bgcolor="ffffff">
 
 <html:form  action ="customergroupfeelist.do">
  <html:hidden property="issuerId" value="<%=(String)session.getAttribute("ISSUER")%>"/>      
<table border="0" cellspacing="0" cellpadding="0" style="border-collapse: collapse" bordercolor="#111111" width="100%">

  <tr>
    <td> 
      <table border="0" cellspacing="0" cellpadding="0" width="100%">
        <tr>
            <td class="titreSection"><bean:message key ="cgf.screentitile.search"/></td>
        </tr>
        <tr>
          <td background="images/ligne.gif" colspan="2"> <img src="images/spacer.gif" width="1" height="1"></td>
        </tr>
        <tr>
          <td class="titreSection"><table border="0" cellspacing="0" cellpadding="4">
            <tr>
              <td><html:button property="submitbutton" onclick ="addNew()"><bean:message key ="common.addnew"/></html:button><br></td>
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
                        <td class="ColumnFONT"><bean:message key ="cardproductsetup.cardproductname"/></td>
                        <td> <html:select property="cardProductId" >
					         <html:option value=""> </html:option>	
					         <html:optionsCollection property="cardProductList" value="key" label="value" />
					       
					        </html:select>
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
  
    <display:table  id="commonObj" name="requestScope.SEARCHLIST" class="simple" width="80%">
   <display:column property="column1" title="Group Id" class="label" />
   <display:column property="column2" title="Card ProductName" class="label" />
   <display:column property="column3" title="Customer Group" class="label" />
    <display:column property="column4" title="Updated Date" class="label" />
   <display:column property="column5" title="Annual Fee" class="label" />
   <display:column property="column6" title="Join Fee" class="label" />
   <display:caption><font class="titreSection">Customer Group Fee Setup Records</font></display:caption>
   <font class="label"><display:column title="Details" align="center" width="80" ></font>
  	  <form name ="listForm" action="customergroupfeeprocess.do">
              <html:hidden property="id" value="<%= ((CommonDataBean)pageContext.getAttribute("commonObj")).getColumn1()%>" />       
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