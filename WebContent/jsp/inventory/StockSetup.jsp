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

    function searchlist(){
 		document.forms[0].submit();
 	}
    
    function update(cardProductId){
    	document.forms[0].method.value="change";
    	document.forms[0].cardProductId.value=cardProductId;
    	document.forms[0].action ="stocksetuplist.do";
    	document.forms[0].submit();
    }
</script>
<body bgcolor="ffffff">
 
<html:form action ="stocksetuplist.do">
<input type="hidden" name="method" value ="">
<input type="hidden" name="cardProductId" value ="">
	<table border="0" cellspacing="0" cellpadding="0" style="border-collapse: collapse" bordercolor="#111111" width="100%">

  <tr>
    <td> 
      <table border="0" cellspacing="0" cellpadding="0" width="100%">
        <tr>
            <td class="titreSection"><bean:message key ="stocksetup.titleSection"/></td>
        </tr>
        <tr>
          <td background="images/ligne.gif" colspan="2"> <img src="images/spacer.gif" width="1" height="1"></td>
        </tr>
        <tr>
          <td class="titreSection"><table border="0" cellspacing="0" cellpadding="4">
            <tr>
              <td height="20px"></td>
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
          
           <logic:present name="stockSetupForm" property="stockList">
				<bean:size id="size" name="stockSetupForm" property="stockList"/>
					<logic:greaterThan name="size" value="0" > 
						<div style="margin-top: 10px; margin-bottom: -12px;"><%@ include
		         					file="/jsp/common/Buttons.jsp"%></div>
								<br/>
								<!-- <form name ="listForm" action="stocksetuplist.do"> -->
						<table border="0" cellpadding="2" cellspacing="2" width="90%" class="simple">
							<caption><font class="titreSection">Inventory Master Records</font></caption>
							<thead>
								<tr>
								<th align="left">Card Product</th>
								<th align="left">Replenish Point/Cards</th>
								<th align="left">Stock Available</th>
								<th align="left">Supplier</th>
								<th align="left">Mode</th>
								<th align="left">QTy</th>
								<th align="left">Current Available</th>
								<th align="left"></th></tr></thead>
						<tbody>
						
						<logic:iterate id ="stockList" name="stockSetupForm" property ="stockList">
	                        					<tr class="odd">
	                        						<td class="label"><bean:write name="stockList" property="column1"/></td>
		                        					<td class="label"><html:text property="column2" name="stockList" indexed="true" size="5"></html:text></td>
		                        					<td class="label"><bean:write name ="stockList"  property="column3" /></td>
		                        					<td class="label">
		                        						<html:select name="stockList" property="column4" indexed="true" >
											      	       <html:option value=""></html:option>
											      	       <html:optionsCollection property="supplierList" value="key" label="value" />
											      	     </html:select>
		                        					</td>
		                        					<td class="label">
		                        						<html:select name="stockList" property="column7" indexed="true" >
											      	       <html:option value=""></html:option>
											      	       <html:option value="A">Add</html:option>
											      	       <html:option value="D">Delete</html:option>
											      	     </html:select>
		                        					</td>
		                        					<td class="label"><html:text property="column8" name="stockList" indexed="true" size="5"></html:text></td>
		                        					<td class="label"><bean:write name="stockList" property="column5"/></td>
		                        					<td class="label">
		                        						<bean:define id="cardProductId" name="stockList" property="column6"/>
											            <input type="button" value="UPDATE" onclick="<%= "javascript:update('"+cardProductId+"');"%>">
													</td>
		                        				</tr>
		                        			</logic:iterate>
		                        			</tbody>
		                        			</table>
		                        			<!-- </form> -->
	                </logic:greaterThan>
                </logic:present>
 <%-- 
   <logic:present name="SEARCHLIST" scope="request">
   <div style="margin-top: 10px; margin-bottom: -12px;"><%@ include
         file="/jsp/common/Buttons.jsp"%></div>
<br/>

    <display:table  id="commonObj" name="requestScope.SEARCHLIST" class="simple" width="90%">
	   	<display:column property="column1" title="Card Product" class="label" />
	   	<display:column title="Replenish Point/Cards" class="label">
	   		<html:text styleId="11" name="commonObj" property="column2" size="10" maxlength="12"/> 
	   	</display:column>
	   	<display:column property="column3" title="Stock Available" class="label" />
	   	<display:column title="Supplier" class="label">
	   		<html:select property="column4" name="commonObj" onchange="branchChange(this)">
                 <option value=""></option>
                 <html:optionsCollection property="supplierList" value="key" label="value" />
	        </html:select> 
	   	</display:column>
	   	<display:column property="column5" title="Mode" class="label" />
	   	<display:column property="column6" title="QTy" class="label" />
	   	<display:column property="column7" title="Current Available" class="label" />
	   	<display:caption><font class="titreSection">Stock Setup Records</font></display:caption>
	   	<font class="label"><display:column title="" align="center" width="80" >
			<form name ="listForm" action="stocksetuplist.do">
				<html:hidden property="cardProductId" value="<%= ((CommonDataBean)pageContext.getAttribute("commonObj")).getColumn8()%>" />       
				<html:hidden property="cardProductName" value="<%= ((CommonDataBean)pageContext.getAttribute("commonObj")).getColumn1()%>" />       
				<html:hidden property="replenishPoint" value="<%= ((CommonDataBean)pageContext.getAttribute("commonObj")).getColumn2()%>" />       
				<html:hidden property="supplierId" value="<%= ((CommonDataBean)pageContext.getAttribute("commonObj")).getColumn4()%>" />       
				<html:hidden property="lastMode" value="<%= ((CommonDataBean)pageContext.getAttribute("commonObj")).getColumn5()%>" />       
				<html:hidden property="lastQty" value="<%= ((CommonDataBean)pageContext.getAttribute("commonObj")).getColumn6()%>" />       
	            <html:hidden property="method" value ="change"/>
	            <html:submit value="Update"/>
			</form>
	   	</display:column>	
	   	</font>
    </display:table>
   </logic:present>
    --%>
   
    </td>
        </tr>
        <tr> 
          <td valign="top">&nbsp; </td>
        </tr>
      </table>
    </td>
  </tr>
</table>
</html:form>
</body>
</html:html>