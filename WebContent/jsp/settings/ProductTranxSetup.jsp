<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<html:html>
<head>
<title><bean:message key ="customertypesetup.screentitle"/></title>
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
          document.forms[0].action= "producttranxlist.do?method=List";
          document.forms[0].submit();
    }
	
</script>
<%@ include file="/jsp/common/delete.jsp"%>
</head>
<body bgcolor="ffffff">
<html:form action="producttranxprocess.do">
<input type="hidden" name="method"/>
<html:hidden property="issuerId" value="<%=(String)session.getAttribute("ISSUER")%>"/>
<html:hidden property="userId" value="<%=(String)session.getAttribute("USERID")%>"/>
<% if(null != request.getAttribute("ACTION") && ((String)request.getAttribute("ACTION")).equals("update")) { %>
	<bean:define id="cardProductId" name="productTranxSetupForm" property="cardProductId"/>
	<html:hidden property="cardProductId" value="<%=(String)cardProductId%>"/>
	<bean:define id="channelId" name="productTranxSetupForm" property="channelId"/>
	<html:hidden property="channelId" value="<%=(String)channelId%>"/>
<% } %>
   <table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr> 
      <td> 
        <table border="0" cellspacing="0" cellpadding="0">
        <tr> <td class="titreSection" width="504"><bean:message key ="producttranxsetup.screentitle"/></td>
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
                          <bean:message key ="producttranxsetup.group"/></td>
                        <td><img src="images/form_tab_end_on.gif" border="0" alt="" width="25" height="22"></td>
                      </tr>
                    </table>                  </td>
                  <td> <img src="images/tbl_haut_d.gif" border="0" alt="" width="5" height="22"></td>
                </tr>
                <tr> 
                  <td background="images/tbl_g.gif"></td>
                  <td class="form_bgcolor"  colspan="2"> 
                    <div align="right">
                    	<% if(request.getAttribute("ACTION") == null || ((String)request.getAttribute("ACTION")).equals("add")) { %>
        					<html:button property="submitbutton" onclick="go('add')" ><bean:message key ="common.save"/></html:button>
        					<html:button property="submitbutton" onclick ="showList()"><bean:message key ="common.cancel"/></html:button>
        				<% } else if(((String)request.getAttribute("ACTION")).equals("update")) { %>
        					<html:button property="submitbutton" onclick="go('update')" ><bean:message key ="common.save"/></html:button>
        					<html:button property="submitbutton" onclick ="showList()"><bean:message key ="common.cancel"/></html:button>
        				<% } else if(((String)request.getAttribute("ACTION")).equals("cancel")) { %>
        					<html:button property="submitbutton" onclick ="showList()"><bean:message key ="common.cancel"/></html:button>
        				<% } %>
                    </div>                 
                  </td>
                  <td background="images/tbl_d.gif"></td>
                </tr>
                <tr>
                  <td background="images/tbl_g.gif"></td>
                  <td class="form_bgcolor"  colspan="2"><table border="0" cellspacing="0" cellpadding="0">
                    <tr>
                      <td style="padding: 15px 5px 5px 5px;" valign="top">
					  	<table border="0" cellpadding="0" cellspacing="0" >
							<tr>
								<% if(request.getAttribute("ACTION") == null || ((String)request.getAttribute("ACTION")).equals("add")) { %>
								<td class="desc_cell"><bean:message	key="producttranxsearch.product" /> </td>
								<td class="desc_cell"><html:select property="cardProductId">
										<html:optionsCollection property="cardProductList" value="key"
											label="value" />
									</html:select> </td>
								<td style="width: 15px;" class="desc_cell"></td>
								<td class="desc_cell"><bean:message key="producttranxsearch.channel" /> </td>
								<td class="desc_cell"><html:select property="channelId">
										<html:optionsCollection property="chanelList" value="key"
											label="value" />
									</html:select>
								</td>
								<% } else if(((String)request.getAttribute("ACTION")).equals("update") || ((String)request.getAttribute("ACTION")).equals("cancel")) { %>
								<td class="desc_cell"><bean:message	key="producttranxsearch.product" /> </td>
								<td><html:select property="cardProductId" styleClass="text_as_label" disabled="true">
										<html:optionsCollection property="cardProductList" value="key"
											label="value" />
									</html:select>
								</td>
								<td style="width: 15px;" class="desc_cell"></td>
								<td class="desc_cell"><bean:message key="producttranxsearch.channel" /> </td>
								<td><html:select property="channelId" styleClass="text_as_label" disabled="true">
										<html:optionsCollection property="chanelList" value="key"
											label="value" />
									</html:select>
								</td>
								<% } %>
							</tr>
							<tr>
								<td colspan="5">
										
									<table width="100%">
										<tr>
										<bean:size id="size" name="tranxTypeList" />
										<bean:define id="aHalfSize" value="<%= String.valueOf(size/2) %>"></bean:define>
											<% int index1=1; %>
											<td  valign="top" width="50%" align="center">
												<table>
													<logic:iterate id ="commObj" name ="tranxTypeList">
													<logic:greaterEqual name="aHalfSize" value="<%= String.valueOf(index1) %>">
													<tr><td style="FONT-SIZE: 11px;">
														<logic:equal name="productTranxSetupForm" property="tranxType" value="<%=commObj.toString() %>">
														<input type="checkbox" id="check" name="tranxType" value="<bean:write name="commObj" />" checked="checked" onclick="selectOnlyThis(this.id)"/>  
														<bean:write name="commObj" />
														</logic:equal>
														<logic:notEqual name="productTranxSetupForm" property="tranxType" value="<%=commObj.toString() %>">
														<input type="checkbox" id="check" name="tranxType" value="<bean:write name="commObj" />" onclick="selectOnlyThis(this.id)"/>  
														<bean:write name="commObj" />
														</logic:notEqual>
													</td></tr>
													</logic:greaterEqual>
													<% index1 ++; %>
													</logic:iterate>
												</table>
											</td>
											<td  valign="top" width="50%" align="center">
											<% int index2=1; %>
												<table>
													<logic:iterate id ="commObj" name ="tranxTypeList">
													<logic:lessThan name="aHalfSize" value="<%= String.valueOf(index2) %>">
													<tr><td style="FONT-SIZE: 11px;">
														<logic:equal name="productTranxSetupForm" property="tranxType" value="<%=commObj.toString() %>">
														<input type="checkbox" id="check" name="tranxType" value="<bean:write name="commObj" />" checked="checked" onclick="selectOnlyThis(this.id)"/>  
														<bean:write name="commObj" />
														</logic:equal>
														<logic:notEqual name="productTranxSetupForm" property="tranxType" value="<%=commObj.toString() %>">
														<input type="checkbox" id="check" name="tranxType" value="<bean:write name="commObj" />"  onclick="selectOnlyThis(this.id)"/>  
														<bean:write name="commObj" />
														</logic:notEqual>
													</td></tr>
													</logic:lessThan>
													<% index2 ++; %>
													</logic:iterate>
												</table>
											</td>
										</tr>
										
									</table>
								</td>
							</tr>
						</table>
					</td>
                 </tr>
                    
                  </table></td>
                  <td background="images/tbl_d.gif"></td>
                </tr>
                
                <tr> 
                  <td background="images/tbl_g.gif"></td>
                  <td class="form_bgcolor"  colspan="2"> 
                    <div align="right">
        				<% if(request.getAttribute("ACTION") == null || ((String)request.getAttribute("ACTION")).equals("add")) { %>
        					<html:button property="submitbutton" onclick="go('add')" ><bean:message key ="common.save"/></html:button>
        					<html:button property="submitbutton" onclick ="showList()"><bean:message key ="common.cancel"/></html:button>
        				<% } else if(((String)request.getAttribute("ACTION")).equals("update")) { %>
        					<html:button property="submitbutton" onclick="go('update')" ><bean:message key ="common.save"/></html:button>
        					<html:button property="submitbutton" onclick ="showList()"><bean:message key ="common.cancel"/></html:button>
        				<% } else if(((String)request.getAttribute("ACTION")).equals("cancel")) { %>
        					<html:button property="submitbutton" onclick ="showList()"><bean:message key ="common.cancel"/></html:button>
        				<% } %>
                    </div>                  
                  </td>
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
<script>

</script>
 </html:form>
</body>
</html:html>

