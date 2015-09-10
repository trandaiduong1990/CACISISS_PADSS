<%@ page language="java"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ page
	import="org.transinfo.cacis.formbean.customerservice.CustomerServiceDataBean"%>
<%@ page import="org.transinfo.cacis.dto.cardproduction.*"%>
<%@ page import="java.util.*"%>
<html:html>
<head>
<title><bean:message key="customerservice.cardholderstatement" /></title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="inc/css/style.css" type="text/css">
<script src="inc/js/cacis.js"></script>
</head>
<script>
function go(action) 
{
 
  document.forms[0].method.value=action;	
  document.forms[0].submit();
}
///to call csrDispatchAction to show all the data as popup window
	function viewAllInfo(urlToCall){
	  window.open(urlToCall, "CustServiceAllData",'resizable =yes, status=yes,scrollbars=yes,menubar=no height=400,width=400,location=no');
	  }
  ///to call  actions when click on cancel button
  function showList(){
    document.forms[0].action ="cardpaymentprocess.do?method=checkSession";
    document.forms[0].submit();
    
   }	  
</script>

<body bgcolor="ffffff">
<html:form action="cardholderstatement.do">
	<input type="hidden" name="method" />

	<!-- this for showing the search condition-->

	<table border="0" cellspacing="0" cellpadding="0"
		style="border-collapse: collapse" bordercolor="#111111" width="100%">
		<tr>
			<td>
			<table border="0" cellspacing="0" cellpadding="0" width="100%">
				<tr>
					<td class="titreSection"><bean:message
						key="customerservice.cardholderstatement" /></td>
				</tr>
				<tr>
					<td background="images/ligne.gif" colspan="2"><img
						src="images/spacer.gif" width="1" height="1"></td>
				</tr>
				<tr>
					<td class="titreSection">
					<table border="0" cellspacing="2" cellpadding="2" width="157"
						align="left">
						<tr>
							<td class="texteMenuGauche"><html:button
								property="submitbutton" onclick="go('search')">
								<bean:message key="common.search" />
							</html:button></td>

						</tr>
					</table>
					<td class="texteMenuGauche">
					<div align="right"><a href='javascript:helpLink("WRITEOFF")'>Help</a></div>
					</td>
				</tr>
				<tr>
					<td background="images/ligne.gif" colspan="2"><img
						src="images/spacer.gif" width="1" height="1"></td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td valign="top"><br>
			<table width="100%">
				<tr>
					<td valign="top" class="ErrFONT"><font color="#FF0000"><html:errors /></font></td>
				</tr>
				<tr>
					<td valign="top">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td>
							<table cellpadding="0" cellspacing="0">

								<tr>
									<td><font class="label"><b><bean:message
										key="customerservice.cardnumber" /></b></font> <html:text
										property="cardNumber" maxlength="16" size="20"
										onkeypress="return disableEnterKey(event)" /></td>
								</tr>

							</table>
							</td>
						</tr>
					</table>
					</td>
				</tr>
				<tr>
					<td valign="top">&nbsp;</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>


	<logic:present name="$CARDDATALIST$">
		<!-- this is for setting   the values to hidden fileds if logic present-->

		<html:hidden property="issuerId"
			value="<%=(String)session.getAttribute("ISSUER")%>" />
		<html:hidden property="userId"
			value="<%=(String)session.getAttribute("USERID")%>" />
		<html:hidden property="cardNumber" />

		<table width="100%" border="0" cellspacing="0" cellpadding="0">

			<tr>
				<td>
				<table border="0" cellspacing="0" cellpadding="0" width="60%">
					<tr>
						<td style="PADDING-RIGHT: 20px; PADDING-LEFT: 20px; PADDING-TOP: 10px">
							
							<div style="float: left;padding-right: 20px;">
							<logic:equal name="cardHolderStatementForm" property="current" value="Y">
								<logic:equal name="cardHolderStatementForm" property="enableLink" value="CU">
									<div style="font: bold; font-family: serif; color: green;">
										Current Statement
									</div>
								</logic:equal>
								<logic:notEqual name="cardHolderStatementForm" property="enableLink" value="CU">
									<div style="font: bold; font-family: serif; color: green;">
										<html:link action="cardholderstatement.do?method=current" >Current Statement</html:link>
									</div>
								</logic:notEqual>
							</logic:equal>
							</div>
							
							<div style="float: left;padding-right: 20px;">
							<logic:equal name="cardHolderStatementForm" property="previous" value="Y">
								<logic:equal name="cardHolderStatementForm" property="enableLink" value="PR">
									<div style="font: bold; font-family: serif; color: green;">
										Previous Statement
									</div>
								</logic:equal>
								<logic:notEqual name="cardHolderStatementForm" property="enableLink" value="PR">
									<div style="font: bold; font-family: serif; color: green;">
										<html:link action="cardholderstatement.do?method=previous" >Previous Statement</html:link>
									</div>
								</logic:notEqual>
							</logic:equal>
							</div>
							
							<div style="float: left;padding-right: 20px;">
							<logic:equal name="cardHolderStatementForm" property="unbilled" value="Y">
								<logic:equal name="cardHolderStatementForm" property="enableLink" value="UN">
									<div style="font: bold; font-family: serif; color: green;">
										Unbilled Transactions
									</div>
								</logic:equal>
								<logic:notEqual name="cardHolderStatementForm" property="enableLink" value="UN">
									<div style="font: bold; font-family: serif; color: green;">
										<html:link action="cardholderstatement.do?method=unbilled" >Unbilled Transactions</html:link>
									</div>
								</logic:notEqual>
							</logic:equal>
							</div>
							
						</td>
					</tr>
					<tr>
						<td>
							<div style="height: 15px;padding-left: 20px;padding-top: 20px;padding-bottom: 5px;font-weight: bold;font-size: 13px;">
								STATEMENT SUMMARY
							</div>
						</td>
					</tr>
					<tr>
						<td style="PADDING-RIGHT: 20px; PADDING-LEFT: 20px; PADDING-TOP: 10px">
						<table class="wrapper" border="0" cellspacing="1" cellpadding="2">
							<tr>
								<td class="title" nowrap>Statement Balance</td>
								<td class="title" nowrap>Min Payment</td>
								<td class="title" nowrap>Outstanding Amount Due</td>
								<td class="title" nowrap>Outstanding Min Payment Due</td>
								<td class="title" nowrap>Payment Due Date</td>
							</tr>
							<tr>
								<td nowrap><bean:write name="cardHolderStatementForm" property="customerStatement.statAmt" /></td>
								<td nowrap><bean:write name="cardHolderStatementForm" property="customerStatement.statMinAmt" /></td>
								<td nowrap><bean:write name="cardHolderStatementForm" property="outAmtDue" /></td>
								<td nowrap><bean:write name="cardHolderStatementForm" property="outMinPayDue" /></td>
								<td nowrap><bean:write name="cardHolderStatementForm" property="customerStatement.statDueDate" /></td>
							</tr>
						</table>
						</td>
					</tr>
					<tr>
						<td>
							<div style="height: 15px;padding-left: 20px;padding-top: 20px;padding-bottom: 5px;font-weight: bold;font-size: 13px;">
								CURRENT STATEMENT HISTORY
							</div>
						</td>
					</tr>
					<tr>
						<td style="PADDING-RIGHT: 20px; PADDING-LEFT: 20px; PADDING-TOP: 10px">
						<table class="wrapper" border="0" cellspacing="1" cellpadding="2">
							<tr>
								<td class="title" nowrap>Description</td>
								<td class="title" nowrap>Amount</td>
							</tr>
							<tr>
								<td nowrap>Previous Balance</td>
								<td nowrap><bean:write name="cardHolderStatementForm" property="customerStatement.prevStatAmt" /></td>
							</tr>
							<tr>
								<td nowrap>Payment</td>
								<td nowrap><bean:write name="cardHolderStatementForm" property="customerStatement.prevPayAmt" /></td>
							</tr>
						</table>
						</td>
					</tr>
					<tr>
						<td>
							<div style="height: 20px;"></div>
						</td>
					</tr>
					
					<bean:size id ="size" property="statTranx" name ="cardHolderStatementForm"/>
		        	<logic:greaterThan  name ="size" value ="0">
					<tr>
						<td style="PADDING-RIGHT: 20px; PADDING-LEFT: 20px; PADDING-TOP: 10px">
						<div style="font: bold; font-family: serif; color: gray;">
							Transactions
						</div>
						</td>
					</tr>
					<tr>
						<td style="PADDING-RIGHT: 20px; PADDING-LEFT: 20px; PADDING-BOTTOM: 10px; PADDING-TOP: 10px">
						<table class="wrapper" border="0" cellspacing="1" cellpadding="2">
							<tr>
								<td class="title" nowrap>Tranx Date</td>
								<td class="title" nowrap>Description</td>
								<td class="title" nowrap>Amount</td>
								<td class="title" nowrap>Ref Id</td>
							</tr>
							<logic:iterate name="cardHolderStatementForm" property="statTranx" id="TranxDetail">
								<tr>
									<td class="DataTD" nowrap><bean:write name="TranxDetail" property="column1" /></td>
									<td class="DataTD" nowrap><bean:write name="TranxDetail" property="column2" /></td>
									<td class="DataTD" nowrap><bean:write name="TranxDetail" property="column3" /></td>
									<td class="DataTD" nowrap><bean:write name="TranxDetail" property="column4" /></td>
								</tr>
							</logic:iterate>
						</table>
						</td>
					</tr>
					</logic:greaterThan>
					
					<bean:size id ="size" property="statPay" name ="cardHolderStatementForm"/>
		        	<logic:greaterThan  name ="size" value ="0">
					<tr>
						<td style="PADDING-RIGHT: 20px; PADDING-LEFT: 20px; PADDING-TOP: 10px">
						<div style="font: bold; font-family: serif; color: gray;">
							Payments
						</div>
						</td>
					</tr>
					<tr>
						<td style="PADDING-RIGHT: 20px; PADDING-LEFT: 20px; PADDING-BOTTOM: 10px; PADDING-TOP: 10px">
						<table class="wrapper" border="0" cellspacing="1" cellpadding="2">
							<tr>
								<td class="title" nowrap>Tranx Date</td>
								<td class="title" nowrap>Description</td>
								<td class="title" nowrap>Amount</td>
								<td class="title" nowrap>Sign</td>
								<td class="title" nowrap>Merchant</td>
							</tr>
							<logic:iterate name="cardHolderStatementForm" property="statPay" id="TranxDetail">
								<tr>
									<td class="DataTD" nowrap><bean:write name="TranxDetail" property="column1" /></td>
									<td class="DataTD" nowrap><bean:write name="TranxDetail" property="column2" /></td>
									<td class="DataTD" nowrap><bean:write name="TranxDetail" property="column3" /></td>
									<td class="DataTD" nowrap><bean:write name="TranxDetail" property="column4" /></td>
									<td class="DataTD" nowrap><bean:write name="TranxDetail" property="column5" /></td>
								</tr>
							</logic:iterate>
						</table>
						</td>
					</tr>
					</logic:greaterThan>
					
					<bean:size id ="size" property="statInt" name ="cardHolderStatementForm"/>
		        	<logic:greaterThan  name ="size" value ="0">
					<tr>
						<td style="PADDING-RIGHT: 20px; PADDING-LEFT: 20px; PADDING-TOP: 10px">
						<div style="font: bold; font-family: serif; color: gray;">
							Interests
						</div>
						</td>
					</tr>
					<tr>
						<td style="PADDING-RIGHT: 20px; PADDING-LEFT: 20px; PADDING-BOTTOM: 10px; PADDING-TOP: 10px">
						<table class="wrapper" border="0" cellspacing="1" cellpadding="2">
							<tr>
								<td class="title" nowrap>Tranx Date</td>
								<td class="title" nowrap>Description</td>
								<td class="title" nowrap>Amount</td>
							</tr>
							<logic:iterate name="cardHolderStatementForm" property="statInt" id="TranxDetail">
								<tr>
									<td class="DataTD" nowrap><bean:write name="TranxDetail" property="column1" /></td>
									<td class="DataTD" nowrap><bean:write name="TranxDetail" property="column2" /></td>
									<td class="DataTD" nowrap><bean:write name="TranxDetail" property="column3" /></td>
								</tr>
							</logic:iterate>
						</table>
						</td>
					</tr>
					</logic:greaterThan>
					
					<bean:size id ="size" property="statFee" name ="cardHolderStatementForm"/>
		        	<logic:greaterThan  name ="size" value ="0">
					<tr>
						<td style="PADDING-RIGHT: 20px; PADDING-LEFT: 20px; PADDING-TOP: 10px">
						<div style="font: bold; font-family: serif; color: gray;">
							Fees
						</div>
						</td>
					</tr>
					<tr>
						<td style="PADDING-RIGHT: 20px; PADDING-LEFT: 20px; PADDING-BOTTOM: 10px; PADDING-TOP: 10px">
						<table class="wrapper" border="0" cellspacing="1" cellpadding="2">
							<tr>
								<td class="title" nowrap>Tranx Date</td>
								<td class="title" nowrap>Description</td>
								<td class="title" nowrap>Amount</td>
								<td class="title" nowrap>Ref Id</td>
							</tr>
							<logic:iterate name="cardHolderStatementForm" property="statFee" id="TranxDetail">
								<tr>
									<td class="DataTD" nowrap><bean:write name="TranxDetail" property="column1" /></td>
									<td class="DataTD" nowrap><bean:write name="TranxDetail" property="column2" /></td>
									<td class="DataTD" nowrap><bean:write name="TranxDetail" property="column3" /></td>
									<td class="DataTD" nowrap><bean:write name="TranxDetail" property="column4" /></td>
								</tr>
							</logic:iterate>
						</table>
						</td>
					</tr>
					</logic:greaterThan>
					
				</table>
				</td>
				<td background=images/tbl_d.gif></td>
			</tr>

		</table>
	</logic:present>
</html:form>
</body>
</html:html>
