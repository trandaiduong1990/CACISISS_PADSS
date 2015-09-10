<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/displaytag-12.tld" prefix="display" %>

<html:html>
<head>
<title><bean:message key ="cardcardembossingsearch.screentitle"/></title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="inc/css/style.css" type="text/css">
<script language="JavaScript" src="inc/js/cacis.js"></script>
<script>
function add(customerId){
	document.forms[0].method.value="add";
	document.forms[0].customerId.value=customerId;
	document.forms[0].action ="addProductProcess.do";
	document.forms[0].submit();
}

function go(action) {
	document.forms[0].mode.value=action;
	searchlist();
}
function searchlist(){
	document.forms[0].submit();
}
</script>

</head>

<body bgcolor="ffffff" >
<html:form action ="addProductList.do">
<input type="hidden" name="method" value ="save">
<input type="hidden" name="customerId" value ="">

<table border="0" cellspacing="0" cellpadding="0" style="border-collapse: collapse" bordercolor="#111111" width="100%">
  <tr>
    <td> 
      <table border="0" cellspacing="0" cellpadding="0" width="100%">
        <tr>
            <td class="titreSection"><bean:message key ="addProduct.screentitle"/></td>
        </tr>
        <tr>
          <td background="images/ligne.gif" colspan="2"> <img src="images/spacer.gif" width="1" height="1"></td>
        </tr>
        <tr>
          <td class="titreSection"><table border="0" cellspacing="0" cellpadding="4">
            <tr>
              <td>
              	<html:button property="submitbutton" onclick="searchlist()"><bean:message key ="common.search"/></html:button>
              </td>
              <td>&nbsp;</td>
            </tr>
          </table> <td class="texteMenuGauche"><div align="right"><a href='javascript:helpLink("WRITEOFF")'>Help</a></div></td>
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
              <td class="ErrFONT"><font color="#FF0000"><html:errors/></font></td>
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
                        <td nowrap class="ColumnFONT"><bean:message key ="addProduct.cardno"/> </td>
                        <td><html:text property="cardNo" size="20"/></td>
                        <td style="width: 30px;"></td>
                        <!-- <td nowrap class="ColumnFONT"><bean:message key ="addProduct.mobileNo"/> </td>
                        <td><html:text property="mobileNo" size="20"/></td>  --> 
                      </tr>
                      <tr>
                        <td nowrap class="ColumnFONT"><bean:message key ="addProduct.customerName"/></td>
                        <td><html:text property="customerName" size="20"/></td>
                        <td style="width: 30px;"></td>
                        <td nowrap class="ColumnFONT"><bean:message key ="addProduct.collateralAcctId"/></td>
                        <td><html:text property="collateralAcctId" size="20"/></td>
                      </tr>
                    </table>
                 </td>
              </tr>
            </table>
          </td>
        </tr>
        <tr> 
          <td valign="top">
		     <!-- to Show the List Data -->   
			<logic:present name="SEARCHLIST" scope="request"> 
		     	<bean:size id ="size" name ="SEARCHLIST"/>
		        	<logic:greaterThan  name ="size" value ="0"> 
			           <tr> 
			              <td>
			                <table border="0" cellspacing="0" cellpadding="0" width="60%">
			                <tr>
	 						     <td style="PADDING-RIGHT: 20px; PADDING-LEFT: 20px; PADDING-TOP: 10px">
							     	<b><bean:write name="addProductSearchForm" property="totalCount"/></b>
							     	<bean:message key ="addProduct.totcountmsg"/>
							     </td>
							     <td></td>	
			                 </tr>
			                 <tr>
							     <td style="PADDING-RIGHT: 20px; PADDING-LEFT: 20px; PADDING-TOP: 10px">
							     	<%@ include file="/jsp/common/TranxButtons.jsp"%>
							     </td>
							     <td></td>	
			                 </tr>
			                 <tr>
			                      <td style="PADDING-RIGHT: 20px; PADDING-LEFT: 20px; PADDING-BOTTOM: 10px; PADDING-TOP: 10px">
			                      <table class="wrapper" border="0" cellspacing="1" cellpadding="2">
	                                <tr>
										<td class="title" nowrap><bean:message key ="addProduct.cardno"/></td>
										<td class="title" nowrap><bean:message key ="addProduct.customerName"/></td>
	                                    <td class="title" nowrap><bean:message key ="addProduct.acctStatus"/> </td>
	                                    <td class="title" nowrap></td>
	                                 </tr>
		                               <logic:iterate id ="commObj" name ="SEARCHLIST">
		                               <bean:define id="sno" name="commObj" property="column2"/>
		                                  <tr>
		                                    <td class="DataTD" nowrap><bean:write name ="commObj"  property="column1" /></td>
		                                    <td class="DataTD" nowrap><bean:write name ="commObj"  property="column3" /></td>
		                                    <td class="DataTD" nowrap><bean:write name ="commObj" property="column4" /></td>
		                                    <td class="title" nowrap>
	                                    		<input type="button" value="View" onclick="<%= "javascript:add('"+sno+"');"%>">
	                                    	</td>
		                                  </tr>
		                               </logic:iterate>  
			                        </table>
			                       </td>
			                       </tr>
		                  	</table>
		                  </td>
			              <td background=images/tbl_d.gif></td>
					</tr>
				</logic:greaterThan>
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
</html:form>
</body>
</html:html>