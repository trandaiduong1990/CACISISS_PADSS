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
     
   function showList(){
		document.forms[0].action= "creditscoringlist.do?method=List";
		document.forms[0].submit();
   }
   
   function checkUpdate() {
	   var table = document.getElementById("tableCreditScoring");
	   var rowCount = table.rows.length;
	   var save = document.getElementsByClassName("submitbutton");
	   var isCheck = false;
	   
	   for(var i=1; i<rowCount; i++) {
		   var row = table.rows[i];
		   var chkbox = row.cells[0].getElementsByTagName('input')[1];
		   if(null != chkbox && true == chkbox.checked) {
			   isCheck = true;
			   chkbox.value="B";
           } else if (null != chkbox && false == chkbox.checked) {
        	   chkbox.value="I";
           }
	   }
	   
	   if (isCheck == true) {
		   save[0].disabled = false;
		   save[1].disabled = false;
	   } else {
			save[0].disabled = true;
		   	save[1].disabled = true;
	   }  
   } 

</script>
<%@ include file="/jsp/common/delete.jsp"%>
</head>
<body bgcolor="ffffff" onload="checkUpdate()">
  <html:form action="creditscoringprocess.do">
  <input type="hidden" name="method"/>
  <html:hidden property="issuerId" value="<%=(String)session.getAttribute("ISSUER")%>"/>
  <html:hidden property="userId" value="<%=(String)session.getAttribute("USERID")%>"/>
  <html:hidden property="status"/>

  <table style="width:805px;" border="0" cellspacing="0" cellpadding="0">
    <tr> 
      <td> 
        <table border="0" cellspacing="0" cellpadding="0">
        <tr> <td class="titreSection" width="504"><bean:message key ="creditcsoring.titlenew"/></td>
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
									<bean:message key ="creditcsoring.title"/></td>
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
						    	if(request.getAttribute("ACTION") == null || ((String)request.getAttribute("ACTION")).equals("change"))
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
											    <th align="center" class="desc_cell">Enable</th>
											    <th align="center" class="desc_cell">Criteria</th> 
											    <th align="center" class="desc_cell">Value1</th>
											    <th align="center" class="desc_cell">Value2</th>
											    <th align="center" class="desc_cell">Value3</th>
											    <th align="center" class="desc_cell">Value4</th>
											    <th align="center" class="desc_cell">Value5</th>
											    <th align="center" class="desc_cell">Value6</th>
											    <th align="center" class="desc_cell">Value7</th>
											    <th align="center" class="desc_cell">Value8</th>
											    <th align="center" class="desc_cell">Value9</th>
											    <th align="center" class="desc_cell">Value10</th>
											</tr>
											<logic:present name="creditScoringSetupForm" property="creditScoringList">
											<bean:size id="size" name="creditScoringSetupForm" property="creditScoringList" />
											<logic:greaterThan name="size" value="0">
											<logic:iterate id="creditScoringList" name="creditScoringSetupForm" property="creditScoringList">
											<tr>
                        						<td align="center">
                        							<html:hidden name="creditScoringList" indexed="true" property="scoreId" />
                        							<html:checkbox name="creditScoringList" property="status" indexed="true" value="A" onchange="checkUpdate()" />
                        							<html:hidden name="creditScoringList" indexed="true" property="creditName" />
												</td>
	                        					<td align="center" class="label">
	                        						<bean:write name="creditScoringList" property="scoreId" />
	                        					</td>
	                        					<td align="center" class="label">
	                        						<bean:write name="creditScoringList" property="c1" />
												</td>
	                        					<td align="center" class="label">
	                        						<bean:write name="creditScoringList" property="c2" />
	                        					</td>
	                        					<td align="center" class="label">
                        							<bean:write name="creditScoringList" property="c3" />
												</td>
	                        					<td align="center" class="label">
	                        						<bean:write name="creditScoringList" property="c4" />
	                        					</td>
	                        					<td align="center" class="label">
	                        						<bean:write name="creditScoringList" property="c5" />
												</td>
	                        					<td align="center" class="label">
	                        						<bean:write name="creditScoringList" property="c6" />
	                        					</td>
	                        					<td align="center" class="label">
	                        						<bean:write name="creditScoringList" property="c7" />
	                        					</td>
	                        					<td align="center" class="label">
	                        						<bean:write name="creditScoringList" property="c8" />
	                        					</td>
	                        					<td align="center" class="label">
	                        						<bean:write name="creditScoringList" property="c9" />
	                        					</td>
	                        					<td align="center" class="label">
	                        						<bean:write name="creditScoringList" property="c10" />
	                        					</td>
                        					</tr>
                        					<tr>
                        						<td colspan="2" align="center" class="label">
                        							<bean:message key ="creditscoring.scoring"/>(<bean:write name="creditScoringList" property="minScore" />-<bean:write name="creditScoringList" property="maxScore" />)
	                        					</td>
	                        					<td>
	                        						<logic:greaterEqual name="creditScoringList" property="nFields" value="1">
	                        							<html:text property="c1s1" name="creditScoringList" maxlength="1" size="9" indexed="true" />
													</logic:greaterEqual>
												</td>
	                        					<td>
	                        						<logic:greaterEqual name="creditScoringList" property="nFields" value="2">
	                        							<html:text property="c2s2" name="creditScoringList" maxlength="1" size="9" indexed="true" />
													</logic:greaterEqual>
	                        					</td>
	                        					<td>
                        							<logic:greaterEqual name="creditScoringList" property="nFields" value="3">
	                        							<html:text property="c3s3" name="creditScoringList" maxlength="1" size="9" indexed="true" />
													</logic:greaterEqual>
												</td>
	                        					<td>
	                        						<logic:greaterEqual name="creditScoringList" property="nFields" value="4">
	                        							<html:text property="c4s4" name="creditScoringList" maxlength="1" size="9" indexed="true" />
													</logic:greaterEqual>
	                        					</td>
	                        					<td>
	                        						<logic:greaterEqual name="creditScoringList" property="nFields" value="5">
	                        							<html:text property="c5s5" name="creditScoringList" maxlength="1" size="9" indexed="true" />
													</logic:greaterEqual>
												</td>
	                        					<td>
	                        						<logic:greaterEqual name="creditScoringList" property="nFields" value="6">
	                        							<html:text property="c6s6" name="creditScoringList" maxlength="1" size="9" indexed="true" />
													</logic:greaterEqual>
	                        					</td>
	                        					<td>
	                        						<logic:greaterEqual name="creditScoringList" property="nFields" value="7">
	                        							<html:text property="c7s7" name="creditScoringList" maxlength="1" size="9" indexed="true" />
													</logic:greaterEqual>
	                        					</td>
	                        					<td>
	                        						<logic:greaterEqual name="creditScoringList" property="nFields" value="8">
	                        							<html:text property="c8s8" name="creditScoringList" maxlength="1" size="9" indexed="true" />
													</logic:greaterEqual>
	                        					</td>
	                        					<td>
	                        						<logic:greaterEqual name="creditScoringList" property="nFields" value="9">
	                        							<html:text property="c9s9" name="creditScoringList" maxlength="1" size="9" indexed="true" />
													</logic:greaterEqual>
	                        					</td>
	                        					<td>
	                        						<logic:greaterEqual name="creditScoringList" property="nFields" value="10" >
	                        							<html:text property="c10s10" name="creditScoringList" maxlength="1" size="9" indexed="true" />
													</logic:greaterEqual>
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
					    	if(request.getAttribute("ACTION") == null || ((String)request.getAttribute("ACTION")).equals("change"))
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