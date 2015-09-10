<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<%@page import="org.transinfo.cacis.common.CommonDataBean" %>

<html:html>
<head>
<title><bean:message key ="cgf.screentitile.setup"/></title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="inc/css/style.css" type="text/css">
<script language="JavaScript" src="inc/js/cacis.js"></script>
<script language="JavaScript" src="inc/js/decimalNo.js"></script>

<script language="JavaScript">

   function go(action) {
       document.forms[0].method.value=action;
       document.forms[0].submit();
   }

</script>
<%@ include file="/jsp/common/delete.jsp"%>
</head>
<body bgcolor="ffffff">
  <html:form action="letterapplmapprccess.do">
  <input type="hidden" name="method"/>
  <html:hidden property="issuerId" value="<%=(String)session.getAttribute("ISSUER")%>"/>
  <html:hidden property="userId" value="<%=(String)session.getAttribute("USERID")%>"/>

  <table style="width:805px;" border="0" cellspacing="0" cellpadding="0">
    <tr> 
      <td> 
        <table border="0" cellspacing="0" cellpadding="0">
        <tr> <td class="titreSection" width="504"><bean:message key ="letterapplmap.title"/></td>
          <td class="titreSection" width="455">&nbsp; </td>
        </tr>
        <tr> 
            <td background="images/ligne.gif" colspan="3"> <img src="images/spacer.gif" width="1" height="1"></td>
        </tr>
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
      <td valign="top"><br>
		<table>
			<tr> 
				<td valign="top"> 
					<table border="0" cellpadding="0" cellspacing="0" align="left">
					<tr> 
						<td> <img src="images/form_tab_start_on.gif" border="0" alt="" width="5" height="22"></td>
						<td background="images/tbl_haut.gif" colspan="2"> 
							<table border="0" cellpadding="0" cellspacing="0">
								<tr> 
									<td class="form_tab_on" background="images/form_tab_bg_on.gif" style="padding-left:15px"> 
									<bean:message key ="letterapplmap.title"/></td>
									<td><img src="images/form_tab_end_on.gif" width="25" height="22"></td>
								</tr>
							</table>                  
						</td>
						<td> <img src="images/tbl_haut_d.gif" border="0" alt="" width="5" height="22"></td>
					</tr>
					<tr> 
						<td background="images/tbl_g.gif"></td>
						<td class="form_bgcolor"  colspan="2"> 
							<div align="right">
        						<%
						    	if(request.getAttribute("ACTION") == null || ((String)request.getAttribute("ACTION")).equals("update") || ((String)request.getAttribute("ACTION")).equals("change"))
						    	{%>
	        					<html:button property="submitbutton" styleClass="submitbutton" onclick="go('update')" ><bean:message key ="common.save"/></html:button>
	        					<html:button property="submitbutton" onclick ="go('change')"><bean:message key ="common.cancel"/></html:button>
	        				 <% }else 
	        				    { %>  	
	        					<html:button property="submitbutton" onclick ="go('change')"><bean:message key ="common.cancel"/></html:button>
	        				    <%}%>	
							</div>                  
						</td>
						<td background="images/tbl_d.gif"></td>
					</tr>
					<tr>
						<td background="images/tbl_g.gif"></td>
						<td class="form_bgcolor"  colspan="3"><div id="demoReply" style="color: green;padding-left: 20px;"></div></td>
						<td background="images/tbl_d.gif">
						</td>
					</tr>
					<tr>
						<td background="images/tbl_g.gif"></td>
						<td class="form_bgcolor" colspan="2">
							<table border="0" cellspacing="0" cellpadding="0">
<%-- 								<tr>
                      				<td style="padding: 20px 20px 10px 20px;" valign="top"><table border="0" cellpadding="0" cellspacing="0">
                        				<tr>
                        					<td class="desc_cell" nowrap="" width="200"><b><bean:message key ="creditscoring.name"/></b></td>
                        					<td>
                        						<html:text property="scoreName" maxlength="20" size="30"/>
                        						<html:hidden property="scoreId"/>
                        					</td>
                        				</tr>
                        				
                        			</table></td>
                        		</tr> --%>
                        		<tr>
                        			<td style="padding: 20px 20px 10px 20px;" valign="top">
                        				<table border="0" cellpadding="0" cellspacing="2" id="tableCreditScoring">
                        					<tr>
											    <th align="center" class="desc_cell"><bean:message key ="letterapplmap.applmodule"/></th>
											    <th align="center" class="desc_cell"><bean:message key ="letterapplmap.applaction"/></th> 
											    <th align="center" class="desc_cell"><bean:message key ="letterapplmap.lettercode"/></th>
											    <th align="center" class="desc_cell"><bean:message key ="letterapplmap.status"/></th>
											    <th align="center" class="desc_cell"><bean:message key ="letterapplmap.udatedate"/></th>
											    <th align="center" class="desc_cell"><bean:message key ="letterapplmap.updateby"/></th>
											</tr>
											<logic:present name="letterApplMapSetupForm" property="letterApplMapList">
											<bean:size id="size" name="letterApplMapSetupForm" property="letterApplMapList" />
											<logic:greaterThan name="size" value="0">
											<logic:iterate id="letterApplMapList" name="letterApplMapSetupForm" property="letterApplMapList">
											<tr>
                        						<td align="center" class="label">
                        							<bean:write name="letterApplMapList" property="applModule" />
                        							<html:hidden name="letterApplMapList" property="applAction" />
												</td>
	                        					<td align="center" class="label">
	                        						<bean:write name="letterApplMapList" property="applAction" />
	                        					</td>
	                        					<td align="center">
	                        						<html:select style="width:100px;" name="letterApplMapList" property="letterTemplate" indexed="true">
					                              	  <html:option value=""> </html:option>	
													  <html:optionsCollection property="letterCodeList" value="key" label="value" />
													</html:select>
												</td>
	                        					<td align="center">
													<html:select property="status" name="letterApplMapList" style="width:100px;" indexed="true" >
					                              	  <html:option value=""> </html:option>		
													  <html:optionsCollection property="statusList" value="key" label="value" />
													</html:select>
	                        					</td>
	                        					<td align="center" class="label">
                        							<bean:write name="letterApplMapList" property="lastUpdatedBy" />
												</td>
	                        					<td align="center" class="label">
	                        						<bean:write name="letterApplMapList" property="lastUpdatedDate" />
	                        					</td>
                        					</tr>
                        					</logic:iterate>
                        					</logic:greaterThan>
											</logic:present>
                        				</table>
                        			</td>
                        		</tr>
							</table>
						</td>
						<td background="images/tbl_d.gif"></td>
					</tr>
	            <tr> 
	            	<td background="images/tbl_g.gif"></td>
	                <td class="form_bgcolor"  colspan="2"> 
	                    <div align="right"> 
	                    	<%
					    	if(request.getAttribute("ACTION") == null || ((String)request.getAttribute("ACTION")).equals("update") || ((String)request.getAttribute("ACTION")).equals("change"))
					    	{%>
        					<html:button property="submitbutton" styleClass="submitbutton" disabled="disabled" onclick="go('update')" ><bean:message key ="common.save"/></html:button>
        					<html:button property="submitbutton" onclick ="go('change')"><bean:message key ="common.cancel"/></html:button>
        				 <% }else 
        				    { %>  	
        					<html:button property="submitbutton" onclick ="go('change')"><bean:message key ="common.cancel"/></html:button>
        				    <%}%>	
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
</html:form>
</body>
</html:html>