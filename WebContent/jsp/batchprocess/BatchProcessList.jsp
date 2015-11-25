<%@page import="java.util.Date"%>
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
<script src="inc/js/jquery-1.11.3.min.js"></script>
<script language="JavaScript">
   
	function go(action) {
		var batchIdArray = "";
		$('.batchIdCb:checked').each(function() {
			batchIdArray += $(this).val() + ",";
		});
		document.forms[0].batchIdArray.value = batchIdArray;
		document.forms[0].action = "cardbatchprocess.do?method=process";
		document.forms[0].submit();

	}

	function showList() {
		document.forms[0].action = "collectionageinglist.do";
		document.forms[0].submit();
	}

	var applFormId = "";
	function displayApplForm(event) {
		var id = event.value;
		if (applFormId != "") {
			$("#" + applFormId).hide();
		}
		if (applFormId != "applFormId" + id) {
			applFormId = "applFormId" + id;
			$("#" + applFormId).show();
		} else {
			$("input:radio").attr("checked", false);
			applFormId = "";
		}
	}

	$(document).ready(function() {
		$("#checkall").on("change", function() {
			$(".batchIdCb").prop("checked", this.checked);
		});
	});
	
	function report() {
		document.frmMain.hdOpCode.value = 4;
		document.frmMain.mode.value = 'Down';
		document.frmMain.REPORT_CODE.value = 'BATCHPROCESS';
		document.frmMain.REPORT_FORMAT.value = 0;
		frmMain.submit();	
	}
</script>
<%@ include file="/jsp/common/delete.jsp"%>
<style type="text/css">
.padLeft{padding-left: 4px}
</style>
</head>
<body bgcolor="ffffff">
<html:form action="cardbatchprocess.do">
<input type="hidden" name="method"/>
<html:hidden property="issuerId" value="<%=(String)session.getAttribute("ISSUER")%>"/>
<html:hidden property="userId" value="<%=(String)session.getAttribute("USERID")%>"/>
<bean:define id="batchIdArray" name="batchProcessFrom" property="batchIdArray"/>
<html:hidden property="batchIdArray" value="<%=(String)batchIdArray%>"/>
  <table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr> 
      <td> 
        <table border="0" cellspacing="0" cellpadding="0">
        <tr> 
            <td class="titreSection" width="504"><bean:message key ="batchprocess.screentitle"/></td>
            <td class="titreSection" width="455">&nbsp; </td>
        </tr>
        <tr> 
            <td background="images/ligne.gif" colspan="3"> <img src="images/spacer.gif" width="1" height="1"></td>
        </tr>
        <tr> 
            <td class="titreSection" width="519">&nbsp;</td>
            <td class="titreSection" width="484"> 
              <table border="0" cellspacing="2" cellpadding="2" align="right">
              <tr> 
                <td class="texteMenuGauche"> 
                    <p class="titreSection">

                    &nbsp;&nbsp;</p>
                    <td class="texteMenuGauche">&nbsp;<a href="#" target="">Help</a></td>                        
                </td>
                <td>&nbsp;</td>                
              </tr>
            </table>
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
                         <bean:message key ="batchprocess.grouptitle"/></td>
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
					    if(request.getAttribute("ACTION") == null || ((String)request.getAttribute("ACTION")).equals("process"))
					    {%>
        					<html:button property="submitbutton" onclick="go('add')" ><bean:message key ="common.save"/></html:button>
        				 <% }else if(((String)request.getAttribute("ACTION")).equals("report"))		
        				    { %>
        				    <form name="frmMain" method="post" action="./GenerateReportServlet.do">
								<input type="hidden" name="AC">
								<input type="hidden" name="mode">
								<input type="hidden" name="hdOpCode">
								<input type="hidden" name="REPORT_CODE">
								<input type="hidden" name="REPORT_FORMAT">
        						<html:button property="submitbutton" onclick="report()">Report</html:button>
       						</form>
        				   <% }else if(((String)request.getAttribute("ACTION")).equals("cancel"))		
        				    { %>  	
        				    <%}%>	
                    </div>                  </td>
                  <td background="images/tbl_d.gif"></td>
                </tr>
                <tr>
                	<td background="images/tbl_g.gif"></td>
                	<td align="center" class="form_bgcolor"  colspan="2"><table border="0" cellspacing="4" cellpadding="2">
                      <tr>
                      	<td class="desc_cell">Application Type</td>
                      	<td>
                      		<html:select property="applicationType" >
                        		<html:option value=""> </html:option>	
					         	<html:optionsCollection property="applTypeList" value="key" label="value" />
					        </html:select>
                      	</td>
                      	<td width="15px"></td>
                      	<td class="desc_cell">Status</td>
                      	<td>
                      		<html:select property="cardBatchStatus" >
                        		<html:option value=""> </html:option>	
					         	<html:optionsCollection property="cardBatchStatusList" value="key" label="value" />
					        </html:select>
                      	</td>
                      </tr>
                     </table></td>
                     <td background="images/tbl_d.gif"></td>
                </tr>
                <tr>
                  <td background="images/tbl_g.gif"></td>
                  <td class="form_bgcolor"  colspan="2"><table border="0" cellspacing="4" cellpadding="2">
                      <tr>
                      <td style="padding: 20px 20px 10px 20px;" valign="top"><table border="0" cellpadding="4" cellspacing="2">
                      	<tr>
                      		<td class="desc_cell"><input type="checkbox" id="checkall" ></td>
                      		<td class="desc_cell">Batch ID</td>
                      		<td class="desc_cell">No Application</td>
                      		<td class="desc_cell">Application Type</td>
                      		<td class="desc_cell">Authorized By</td>
                      		<td class="desc_cell">Authorized Date</td>
                      		<td class="desc_cell">Detail</td>
                      	</tr>
                      	<logic:greaterEqual name="batchProcessFrom" property="batchProcessList" value="0">
                      	<% int index=0; %>
                        <logic:iterate id ="batchProcessList" name="batchProcessFrom" property ="batchProcessList">
	                        <tr>
	                        	<td class="label"><input type="checkbox" class="batchIdCb"
	                        					 value="<bean:write name ="batchProcessList"  property="batchId" />"></td>
	                        	<td class="label">
	                        		<bean:write name ="batchProcessList"  property="batchId" />
	                        	</td>
	                        	<td class="label">
	                        		<bean:write name ="batchProcessList"  property="noApplications" />
	                        	</td>
	                        	<td class="label">
	                        		<bean:write name ="batchProcessList"  property="applicationType" />
	                        	</td>
	                        	<td class="label">
	                        		<bean:write name ="batchProcessList"  property="authorizedBy" />
	                        	</td>
	                        	<td class="label">
	                        		<bean:write name ="batchProcessList"  property="authorizedDate" />
	                        	</td>
	                        	<td class="label"><input type="radio" name="detail" value="<%=index %>" onclick="displayApplForm(this)"></td>
	                        </tr>
	                        <tr>
	                        	<td colspan="5">
	                        		<div id="applFormId<%=index%>" style="display: none;margin-left: 10px;">
	                        		<logic:greaterEqual name="batchProcessList" property="applList" value="0">
	                        			<table border="0" cellpadding="4" cellspacing="2"  width="100%">
	                        				<tr>
	                        					<td class="desc_cell">Appl Id</td>
	                        					<td class="desc_cell">Customer Name</td>
	                        					<td class="desc_cell">NRIC</td>
	                        					<td class="desc_cell">Updated Date</td>
	                        				</tr>
	                        				<logic:iterate id ="applList" name="batchProcessList" property ="applList">
	                        					<tr>
		                        					<td class="label"><bean:write name ="applList"  property="applicationId" /></td>
		                        					<td class="label"><bean:write name ="applList"  property="customerName" /></td>
		                        					<td class="label"><bean:write name ="applList"  property="idNumber" /></td>
		                        					<td class="label"><bean:write name ="applList"  property="updatedDate" /></td>
		                        				</tr>
		                        			</logic:iterate>
		                        			
		                        		</table>
	                        		</logic:greaterEqual>
	                        		</div>
	                        	</td>
	                        </tr>
	                        <%index++; %>
                        </logic:iterate>
                        </logic:greaterEqual>
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
        				 <% }else if(((String)request.getAttribute("ACTION")).equals("change") || ((String)request.getAttribute("ACTION")).equals("update"))		
        				    { %>
        					<html:button property="submitbutton" onclick="go('update')"><bean:message key ="common.save"/></html:button>
        				   <% }else if(((String)request.getAttribute("ACTION")).equals("cancel"))		
        				    { %>
        					
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