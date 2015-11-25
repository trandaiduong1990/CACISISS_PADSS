<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/displaytag-12.tld" prefix="display" %>
<%@page import="org.transinfo.cacis.common.CommonDataBean" %>
<%@page import="java.util.ArrayList" %>

<html:html>
<head>
<title><bean:message key ="cardbatcprocess.newcards.screentitle"/></title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="inc/css/style.css" type="text/css">
</head>
<script src="inc/js/cacis.js"></script>
<script src="inc/js/jquery-1.11.3.min.js"></script>
<script type="text/javascript">
    function batchProcess(method) {
        //alert('test');
          //document.forms[0].method.value=method;
	   	  var r=confirm("<bean:message key ='areyousure'/>");
	   	  if (r==true)
	   	  {
	   	    document.forms[0].action="newcardbatchprocess.do?method=process";
	   		document.forms[0].submit();
	   	  }
      }
    
    $(document).ready(function() {
		$("#checkall").on("change", function() {
			$(".appIDCheckbox").prop("checked", this.checked);
		});
	});
	
    function batchAuthorized() {
	    var applIdArray = "";
    	$('.appIDCheckbox:checked').each(function() {
    		applIdArray += $(this).val() + ",";
        });
    	document.forms[0].applIdArray.value = applIdArray;
    	document.forms[0].action="newcardbatchprocess.do?method=authorized";
   		document.forms[0].submit();
    }
    
    function go(param) {
        document.forms[0].action="newcardbatchprocess.do?method=List";
        document.getElementById("mode").value = param; 
		document.forms[0].submit();
     }

    <% String servletPath = request.getContextPath(); %>

    function callLogon(){
        alert('llll');
        //parent.window.location.replace("/Cacisiss/adminloginsetup.do");
        window.parent.parent.location.replace('<%=servletPath%>'+'/adminloginsetup.do');
    }
    
    function branchChange(event) {
    	document.forms[0].action="newcardbatchprocess.do?method=changeBranch";
   		document.forms[0].submit();
    }
</script>

<body bgcolor="ffffff">
<html:form action ="newcardbatchprocess.do">

<html:hidden property="issuerId" value="<%=(String)session.getAttribute("ISSUER")%>"/>
<html:hidden property="userId" value="<%=(String)session.getAttribute("USERID")%>"/>

<bean:define id="batId" name="newCardBatchprocessFrom" property="batchId"/>
<html:hidden property="batchId" value="<%=(String)batId%>"/>

<bean:define id="applIdArray" name="newCardBatchprocessFrom" property="applIdArray"/>
<html:hidden property="applIdArray" value="<%=(String)applIdArray%>"/>

<table border="0" cellspacing="0" cellpadding="0" style="border-collapse: collapse" bordercolor="#111111" width="100%">
  <tr>
    <td> 
      <table border="0" cellspacing="0" cellpadding="0" width="100%">
        <tr>
            <td class="titreSection"><bean:message key ="cardbatcprocess.newcards.screentitle"/></td>
        </tr>
        <tr>
          <td background="images/ligne.gif" colspan="2"> <img src="images/spacer.gif" width="1" height="1"></td>
        </tr>
        <tr>
          <td class="titreSection">
          	<table border="0" cellspacing="2" cellpadding="2" width="157">
              <tr> 
                <td class="texteMenuGauche"> 
                <logic:present name="newCardBatchprocessFrom" property="appList"> 
					<bean:size id="size" name="newCardBatchprocessFrom" property="appList"/>
					<logic:greaterThan name="size" value="0" >
	                	<html:button property="authorized" onclick ="batchAuthorized()"><bean:message key ="common.authorized"/></html:button>
	                </logic:greaterThan>
                </logic:present>
                </td>
              </tr>
          	</table>
          </td>
        </tr>
        <tr>
          <td background="images/ligne.gif" colspan="2"> <img src="images/spacer.gif" width="1" height="1"></td>
        </tr>
      </table>
    </td>
  </tr>
<%-- <logic:present name="newCardBatchprocessFrom" property="appList"> 
<bean:size id="size" name="newCardBatchprocessFrom" property="appList"/>
<logic:greaterThan name="size" value="0" > --%>
  <tr>
    <td valign="top">
     <br>
      <table width="100%">
        <tr>
          <td valign="top" class="ErrFONT"><font color="#FF0000"><html:errors /></font></td>
        </tr>
        
        <tr>
        	<td><table>
        		<tr>
        			<td class="ColumnFONT" nowrap>Branch</td>
        			<td width="15px"></td>
        			<td>
        			<logic:present name="newCardBatchprocessFrom" property="branchList"> 
        			<bean:size id="size" name="newCardBatchprocessFrom" property="branchList"/>
					<logic:greaterThan name="size" value="1" > 
					<%-- <logic:greaterThan name="newCardBatchprocessFrom" property="branchList" value="2" > --%>
        				<html:select property="branchId" onchange="branchChange(this)">
                               <option value=""></option>
                                <html:optionsCollection property="branchList" value="key" label="value" />
	                                </html:select> 
	                 </logic:greaterThan>
	                 <logic:equal name="size" value="1" >
        				<html:select property="branchId" disabled="true">
                                <html:optionsCollection property="branchList" value="key" label="value" />
	                                </html:select> 
	                                </logic:equal>
	                                </logic:present>
        			</td>
        		</tr>
        	</table></td>
        </tr>
        <tr>
          <td valign="top">
		  	<%@ include file="/jsp/common/Buttons.jsp" %>
		  </td>
        </tr>
        <tr>
        	<td>
        		<logic:present name="newCardBatchprocessFrom" property="appList"> 
        			<bean:size id="size" name="newCardBatchprocessFrom" property="appList"/>
					<logic:greaterThan name="size" value="0" > 
					<%-- <logic:greaterThan name="newCardBatchprocessFrom" property="appList" value="0" > --%>
						<table border="0" cellpadding="2" cellspacing="2" width="60%" class="simple">
							<caption><font class="titreSection">New Cards Batch Process Records</font></caption>
							<thead>
								<tr>
								<th align="center" width="28"><input type="checkbox" id="checkall" ></th>
								<th align="left">Application Id</th>
								<th align="left">Customer Name</th>
								<th align="left">NRIC</th>
								<th align="left">Updated Date</th></tr></thead>
						<tbody>
						<logic:iterate id ="appProcess" name="newCardBatchprocessFrom" property ="appList">
	                        					<tr class="odd">
	                        						<td class="label" align="center"><input type="checkbox" class="appIDCheckbox" name="appProcess"
	                        							 value="<%= ((CommonDataBean)pageContext.getAttribute("appProcess")).getColumn1()%>" ></td>
		                        					<td class="label"><bean:write name ="appProcess"  property="column1" /></td>
		                        					<td class="label"><bean:write name ="appProcess"  property="column2" /></td>
		                        					<td class="label"><bean:write name ="appProcess"  property="column3" /></td>
		                        					<td class="label"><bean:write name ="appProcess"  property="column4" /></td>
		                        				</tr>
		                        			</logic:iterate>
		                        			</tbody>
		                        			</table>
	                </logic:greaterThan>
                </logic:present>
            </td>
        </tr>
        <%-- <tr> 
          <td valign="top">
			
			   <display:table  id="appProcess" name="newCardBatchprocessFrom" property="appList" class="simple" width="80%">
				   <display:column class="label"  >
				   		<input type="checkbox" class="appIDCheckbox" name="appId" value="<%= ((CommonDataBean)pageContext.getAttribute("appProcess")).getColumn1()%>" >
				   </display:column>
				   <display:column property="column1" title="Application Id" class="label" />
				   <display:column property="column2" title="Customer Name" class="label" />
				   <display:column property="column3" title="NRIC" class="label" />
				   <display:column property="column4" title="Updated Date" class="label" />
				   <display:caption><font class="titreSection">New Cards Batch Process Records</font></display:caption>
			   </display:table>
     	  </td>
        </tr> --%>
  		<tr> 
     		<td valign="top">&nbsp;</td>
  		</tr>
  		<%-- <tr> 
          <td colspan="1">
          		<table cellspacing=0 cellpadding=0 border=0>
                <tbody>
                  <tr>
                    <td><table cellspacing=0 cellpadding=0 border=0>
                        <tbody>
                          <tr>
                            <td><img height=19 alt="" src="images/tab_g.gif" width=8 border=0></td>
                            <td class=group_title background=images/tab_fond.gif><bean:message key ="cardbatcprocess.auth.title"/> </td>
                             <td><img height=19 alt="" src="images/tab_d.gif" width=8 border=0></td>
                           </tr>
                         </tbody>
                     </table></td>
                   </tr>
                   <tr>
                     <td bgcolor=#dce5ea><div style="FONT-SIZE: 1px">&nbsp;</div></td>
                   </tr>
                   <tr>
                    <td>
						<table style="BORDER-COLLAPSE: collapse" bordercolor=#111111 cellspacing=0 cellpadding=0 border=0>
						<tr>
	                         <td nowrap class="desc_cell" style="width: 70px;">
	                         	<bean:message key ="cardbatcprocess.auth.username"/> * 
	                         </td>
	                         <td>
	                         	<html:text property="authUserId" styleId="authUserId"/>
	                         </td>
						</tr>
						</table>
					</td>	
                   </tr>
                   <tr>
                    <td>
						<table style="BORDER-COLLAPSE: collapse" bordercolor=#111111 cellspacing=0 cellpadding=0 border=0>
						<tr>
	                         <td nowrap class="desc_cell" style="width: 70px;">
	                         	<bean:message key ="cardbatcprocess.auth.password"/> * 
	                         </td>
	                         <td>
	                         	<html:password property="authPassword" styleId="authPassword"/>
	                         </td>
						</tr>
						</table>
					</td>	
                   </tr>                          
                 </tbody>
               </table>
           </td>
         </tr> --%>
	</table>
   </td>
  </tr>
<%-- </logic:greaterThan>
<logic:lessEqual name="size" value="0">
	<tr>
		<td valign="top">
      		<table width="100%">
		        <tr>
		        	<td valign="top">
						<bean:message key ="cardbatcprocess.noapps"/>
					</td>
		        </tr>
			</table>
		</td>
	</tr>
</logic:lessEqual>
</logic:present> --%>
</table>

</html:form> 
</body>
</html:html>