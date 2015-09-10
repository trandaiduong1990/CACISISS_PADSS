<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<html:html>
<head>
<title><bean:message key ="cardtypesetup.screentitle"/></title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="inc/css/style.css" type="text/css">

<script language="JavaScript">
   function go(action) {
       document.forms[0].method.value=action;
       document.forms[0].submit();
    }
  
     function showList(){
          document.forms[0].action= "cardtypelist.do?method=List";
          document.forms[0].submit();
       } 
</script>
<%@ include file="/jsp/common/delete.jsp"%>
</head>
<body bgcolor="ffffff">
 <html:form action="cardtypeprocess.do">
<input type="hidden" name="method"/>
<html:hidden property="issuerId" value="<%=(String)session.getAttribute("ISSUER")%>"/>
<html:hidden property="userId" value="<%=(String)session.getAttribute("USERID")%>"/>
  <table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr> 
      <td> 
        <table border="0" cellspacing="0" cellpadding="0">
        <tr> 
            <td class="titreSection" width="504"><bean:message key ="cardtypesetup.screentitle"/></td>
            <td class="titreSection" width="455">&nbsp; </td>
        </tr>
        <tr> 
            <td background="images/ligne.gif" colspan="3"> <img src="images/spacer.gif" width="1" height="1"></td>
        </tr>
        <tr> 
            <td colspan="2" class="titreSection"><table border="0" cellspacing="0" cellpadding="4">
              <tr>
               <td><html:button property="submitbutton"  onclick ="go('addNew')"><bean:message key ="common.addnew"/></html:button><br></td>
                </tr>
            </table>
            <td class="texteMenuGauche"><div align="right">&nbsp;<a href='javascript:helpLink("WRITEOFF")'>Help</a></div>
            </td>
          </tr>
        <tr> 
            <td background="images/ligne.gif" colspan="3"> <img src="images/spacer.gif" width="1" height="1"></td>
        </tr>
      </table>
    </td>
  </tr>
       
        <tr>
          <td valign="top" class="ErrFONT"><table width="100%" border="0" cellspacing="0" cellpadding="4">
            <tr>
              <td class="ErrFONT"><font color="#FF0000"><html:errors /></font></td>
            </tr>
          </table></td>
        </tr>
  <tr> 
      <td height="444" valign="top"><br>
      <table>
        <tr> 
            <td height="166" valign="top"> 
              <table border="0" cellpadding="0" cellspacing="0" align="left">
                <tr> 
                  <td> <img src="images/form_tab_start_on.gif" border="0" alt="" width="5" height="22"></td>
                  <td background="images/tbl_haut.gif" colspan="2"> 
                    <table border="0" cellpadding="0" cellspacing="0">
                      <tr> 
                        <td class="form_tab_on" background="images/form_tab_bg_on.gif" style="padding-left:15px"> 
                         <bean:message key ="cardtypesetup.group1"/></td>
                        <td><img src="images/form_tab_end_on.gif" border="0" alt="" width="25" height="22"></td>
                      </tr>
                    </table>                  </td>
                  <td> <img src="images/tbl_haut_d.gif" border="0" alt="" width="5" height="22"></td>
                </tr>
                <tr> 
                  <td background="images/tbl_g.gif"></td>
                  <td class="form_bgcolor"  colspan="2"> 
                    <div align="right">
                       <%
					    if(request.getAttribute("ACTION") == null || ((String)request.getAttribute("ACTION")).equals("add"))
					    {%>
        					<html:button property="submitbutton" onclick="go('add')" ><bean:message key ="common.save"/></html:button>
        					<html:button property="submitbutton" onclick ="showList()"><bean:message key ="common.cancel"/></html:button>
        					
        				 <% }else if(((String)request.getAttribute("ACTION")).equals("change") || ((String)request.getAttribute("ACTION")).equals("update"))		
        				    { %>
        					<html:button property="submitbutton" onclick="go('update')"><bean:message key ="common.save"/></html:button>
       						<html:button property="submitbutton" onclick="deleteAction('delete')"><bean:message key ="common.delete"/></html:button>
       						<html:button property="submitbutton" onclick ="showList()"><bean:message key ="common.cancel"/></html:button>
        				   <% }else if(((String)request.getAttribute("ACTION")).equals("cancel"))		
        				    { %>  	
        					<html:button property="submitbutton" onclick ="showList()"><bean:message key ="common.cancel"/></html:button>
        				    <%}%>	
                    </div>                  </td>
                  <td background="images/tbl_d.gif"></td>
                </tr>
                <tr>
                  <td background="images/tbl_g.gif"></td>
                  <td class="form_bgcolor"  colspan="2"><table border="0" cellspacing="0" cellpadding="0">
                    <tr>
                      <td style="padding: 20px 20px 10px 20px;" valign="top"><table border="0" cellpadding="0" cellspacing="0">
                        <tr>
                          <td class="desc_cell" nowrap><b><bean:message key ="cardtypesetup.cardtypeid"/> * </b></td>
                          <td>
	                          <logic:present scope="request" name="ACTION">
	                          	<logic:equal name="ACTION" value="change">
	                          		<html:hidden property="cardTypeId" name="cardTypeSetupForm" />
	                          		<bean:write name="cardTypeSetupForm" property="cardTypeId"/> 
	                          	</logic:equal>
	                          	<logic:equal name="ACTION" value="update">
	                          		<html:hidden property="cardTypeId" name="cardTypeSetupForm" />
	                          		<bean:write name="cardTypeSetupForm" property="cardTypeId"/> 
	                          	</logic:equal>
	                          	<logic:equal name="ACTION" value="add">
	                          		<html:text property="cardTypeId"  maxlength="6" size="10" />
	                          	</logic:equal>
	                          </logic:present>
	                          <logic:present scope="request" name="ACTION">
	                          	<logic:equal name="ACTION" value="cancel">
	                          		<html:hidden property="cardTypeId" name="cardTypeSetupForm" />
	                          		<bean:write name="cardTypeSetupForm" property="cardTypeId"/> 
	                          	</logic:equal>
	                          </logic:present>
	                          <logic:notPresent scope="request" name="ACTION">
	                          		<html:text property="cardTypeId"  maxlength="6" size="10" />
	                          </logic:notPresent>
                          </td>
                        </tr>
                        <tr>
                          <td class="desc_cell" nowrap><b><bean:message key ="cardtypesetup.cardtype"/> * </b></td>
                          <td ><html:text property="cardType"  maxlength="20" size="25"/></td>
                        </tr>
                       <!-- <tr>
                          <td class="desc_cell" nowrap><bean:message key ="cardtypesetup.masterkey"/> </td>
                          <td ><html:text property="masterKey" maxlength="16" size="20"/></td>
                        </tr>
                        <tr>
                          <td class="desc_cell" nowrap><bean:message key ="cardtypesetup.pek"/></td>
                          <td ><html:text property="pek"  maxlength="16" size="20"/></td>
                        </tr>
                        <tr>
                          <td class="desc_cell" nowrap><bean:message key ="cardtypesetup.kek"/></td>
                          <td><html:text property="kek" maxlength="16" size="20"/></td>
                        </tr>  -->
                      </table></td>
                      </tr>
                    
                  </table></td>
                  <td background="images/tbl_d.gif"></td>
                </tr>
                
                <tr> 
                  <td background="images/tbl_g.gif"></td>
                  <td class="form_bgcolor"  colspan="2"> 
                    <div align="right">
                      <%
					    if(request.getAttribute("ACTION") == null || ((String)request.getAttribute("ACTION")).equals("add"))
					    {%>
        					<html:button property="submitbutton" onclick="go('add')" ><bean:message key ="common.save"/></html:button>
        					<html:button property="submitbutton" onclick ="showList()"><bean:message key ="common.cancel"/></html:button>
        				 <% }else if(((String)request.getAttribute("ACTION")).equals("change") || ((String)request.getAttribute("ACTION")).equals("update"))		
        				    { %>
        					<html:button property="submitbutton" onclick="go('update')"><bean:message key ="common.save"/></html:button>
       						<html:button property="submitbutton" onclick="deleteAction('delete')"><bean:message key ="common.delete"/></html:button>
       						<html:button property="submitbutton" onclick ="showList()"><bean:message key ="common.cancel"/></html:button>
        				   <% }else if(((String)request.getAttribute("ACTION")).equals("cancel"))		
        				    { %>
        				    <html:button property="submitbutton" onclick ="showList()"><bean:message key ="common.cancel"/></html:button>  	
        					
        				    <%}%>	
                    </div>                  </td>
                  <td background="images/tbl_d.gif"></td>
                </tr>
                <tr> 
                  <td> <img src="images/tbl_bas_g.gif" border="0" alt="" width="5" height="5"></td>
                  <td background="images/tbl_bas.gif"></td>
                  <td background="images/tbl_bas.gif"></td>
                  <td> <img src="images/tbl_bas_d.gif" border="0" alt="" width="5" height="5"></td>
                </tr>
              </table>
          </td>
        </tr>
      </table>
    </td>
  </tr>
</table>
</html:form>
</body>
</html:html>