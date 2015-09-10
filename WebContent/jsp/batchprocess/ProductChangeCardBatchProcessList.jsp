<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/displaytag-12.tld" prefix="display" %>
<%@page import="org.transinfo.cacis.common.CommonDataBean" %>
<%@page import="java.util.ArrayList" %>

<html:html>
<head>
<title><bean:message key ="cardbatcprocess.productchangecards.screentitle"/></title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="inc/css/style.css" type="text/css">
</head>
<script src="inc/js/cacis.js"></script>
<script language="JavaScript">
  
    function batchProcess() {
    	  var r=confirm("<bean:message key ='areyousure'/>");
    	  if (r==true)
    	  {
    	    document.forms[0].action="productchangecardbatchprocess.do?method=process";
    		document.forms[0].submit();
    	  }
      }

    function go(param) {
        document.forms[0].action="productchangecardbatchprocess.do?method=List";
        document.getElementById("mode").value = param;
		document.forms[0].submit();
     }


    function onLoad(){
        //alert('test');
        //alert(document.getElementById("authUserId").value);
    	document.getElementById("authUserId").value = ""; 
    	document.getElementById("authPassword").value = ""; 
    }
</script>

<body bgcolor="ffffff" onload="onLoad();">
<html:form action ="productchangecardbatchprocess">

<html:hidden property="issuerId" value="<%=(String)session.getAttribute("ISSUER")%>"/>
<html:hidden property="userId" value="<%=(String)session.getAttribute("USERID")%>"/>

<bean:define id="batId" name="productChangeCardBatchprocessFrom" property="batchId"/>
<html:hidden property="batchId" value="<%=(String)batId%>"/>

<table border="0" cellspacing="0" cellpadding="0" style="border-collapse: collapse" bordercolor="#111111" width="100%">
  <tr>
    <td> 
      <table border="0" cellspacing="0" cellpadding="0" width="100%">
        <tr>
            <td class="titreSection"><bean:message key ="cardbatcprocess.productchangecards.screentitle"/></td>
        </tr>
        <tr>
          <td background="images/ligne.gif" colspan="2"> <img src="images/spacer.gif" width="1" height="1"></td>
        </tr>
        <tr>
          <td class="titreSection">
          	<table border="0" cellspacing="2" cellpadding="2" width="157">
              <tr> 
                <td class="texteMenuGauche"> 
                <logic:present name="productChangeCardBatchprocessFrom" property="appList"> 
					<bean:size id="size" name="productChangeCardBatchprocessFrom" property="appList"/>
					<logic:greaterThan name="size" value="0" >
	                	<html:button property="process" onclick ="batchProcess()"><bean:message key ="common.process"/></html:button>
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
<logic:present name="productChangeCardBatchprocessFrom" property="appList"> 
<bean:size id="size" name="productChangeCardBatchprocessFrom" property="appList"/>
<logic:greaterThan name="size" value="0" >
  <tr>
    <td valign="top">
     <br>
      <table width="100%">
        <tr>
          <td valign="top" class="ErrFONT"><font color="#FF0000"><html:errors /></font></td>
        </tr>
        <tr>
          <td valign="top">
          	<table>
          		<tr>
          			<td>
          				<bean:message key ="cardbatcprocess.batchId"/>
          			</td>
          			<td>
          				&nbsp;&nbsp;:
          			</td>
          			<td>
          				<bean:write name ="productChangeCardBatchprocessFrom" property="batchId" />
          			</td>
          		</tr>
          		<tr>
          			<td>
          				<bean:message key ="cardbatcprocess.noofapplicarion"/>
          			</td>
          			<td>
          				&nbsp;&nbsp;:
          			</td>
          			<td>
          				<bean:write name ="productChangeCardBatchprocessFrom" property="totalNoOfApps" />
          			</td>
          		</tr>
          	</table>
          </td>
        </tr>
        <tr>
          <td valign="top">
		  	<%@ include file="/jsp/common/Buttons.jsp" %>
		  </td>
        </tr>
        <tr> 
          <td valign="top">
			
			   <display:table  id="appProcess" name="productChangeCardBatchprocessFrom" property="appList" class="simple" width="80%">
				   <display:column property="column1" title="Card No" class="label" />
				   <display:column property="column2" title="Customer Name" class="label" />
				   <display:column property="column3" title="NRIC" class="label" />
				   <display:column property="column4" title="Exist Card product" class="label" />
				   <display:column property="column5" title="Change Card Product" class="label" />
				   <display:column property="column6" title="Updated Date" class="label" />
				   <display:caption><font class="titreSection">Product Change Cards Batch Process Records</font></display:caption>
			   </display:table>
     	  </td>
        </tr>
  		<tr> 
     		<td valign="top">&nbsp;</td>
  		</tr>
  		<tr> 
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
         </tr>
	</table>
   </td>
  </tr>
</logic:greaterThan>
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
</logic:present>
</table>

</html:form> 
</body>
</html:html>