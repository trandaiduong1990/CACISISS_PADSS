<%@ page language="java"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<html:html>
<head>
<title><bean:message key="cardproductsetup.screentitile" /></title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="inc/css/style.css" type="text/css">
<script language="JavaScript" src="inc/js/TranxEnquiryManualRecon.js"></script>

<script language="JavaScript">
	function addProduct() {
		/* var radios = document.getElementsByName('accountType');
		for (var i = 0, length = radios.length; i < length; i++) {
		    if (radios[i].checked && radios[i].value == "N") {
				showList();
		    }
		} */
		document.forms[0].method.value = 'addProduct';
		document.forms[0].submit();
	}
	function showListBox() {
		/* if(action == true) {
			document.getElementById("listBoxId").style.display = 'block';
		} else {
			document.getElementById("listBoxId").style.display = 'none';
		} */
		var radios = document.getElementsByName('accountType');
		for (var i = 0, length = radios.length; i < length; i++) {
		    if (radios[i].checked && radios[i].value == "N") {
		    	document.getElementById("listBoxId").style.display = 'none';
		    	break;
		    } 
		    if (radios[i].checked && radios[i].value == "E") {
		    	document.getElementById("listBoxId").style.display = 'block';
		    	break;
		    } 
	}
	}
</script>

</head>
<body bgcolor="ffffff" onload="javascript: showListBox();">
	<html:form action="addProductProcess.do">
		<input type="hidden" name="method" />
		<html:hidden property="issuerId"
			value="<%=(String) session.getAttribute("ISSUER")%>" />
		<html:hidden property="userId"
			value="<%=(String) session.getAttribute("USERID")%>" />
		 <bean:define id="customerId" name="addProductSetupForm" property="customerId"/>
		<html:hidden property="customerId" value="<%=(String)customerId%>"/>
		<bean:define id="customerName" name="addProductSetupForm" property="customerName"/>
		<html:hidden property="customerName" value="<%=(String)customerName%>"/>
		<bean:define id="nric" name="addProductSetupForm" property="nric"/>
		<html:hidden property="nric" value="<%=(String)nric%>"/>
		<bean:define id="creditLimit" name="addProductSetupForm" property="creditLimit"/>
		<html:hidden property="creditLimit" value="<%=(String)creditLimit%>"/>
		
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr> 
			      <td> 
			        <table border="0" cellspacing="0" cellpadding="0">
			        <tr> 
			            <td class="titreSection" width="504"><bean:message key ="addCardProcess.addProductForm"/></td>
			            <td class="titreSection" width="455">&nbsp; </td>
			        </tr>
			        <tr> 
			            <td background="images/ligne.gif" colspan="3"> <img src="images/spacer.gif" width="1" height="1"></td>
			        </tr>
			      </table>
			    </td>
			  </tr>
			  <tr>
			    <td valign="top" class="ErrFONT">
			    	<table width="100%" border="0" cellspacing="0" cellpadding="4">
				      <tr>
				        <td class="ErrFONT"><font color="#FF0000"><html:errors /></font></td>
				      </tr>
			    	</table>
			    </td>
			  </tr>
				<tr> 
      <td valign="top"><br>
      <table>
        <tr> 
            <td height="419" valign="top"> 
              <table border="0" cellpadding="0" cellspacing="0" align="left">
                <tr> 
                  <td width="5"> <img src="images/form_tab_start_on.gif" border="0" alt="" width="5" height="22"></td>
                  <td background="images/tbl_haut.gif" colspan="2"> 
                    <table border="0" cellpadding="0" cellspacing="0">
                      <tr> 
                        <td class="form_tab_on" background="images/form_tab_bg_on.gif" style="padding-left:15px"> 
                         <bean:message key ="addProduct.screentitle"/>                       </td>
                        <td><img src="images/form_tab_end_on.gif" border="0" alt="" width="25" height="22"></td>
                      </tr>
                    </table>
                  </td>
                  <td> <img src="images/tbl_haut_d.gif" border="0" alt="" width="5" height="22"></td>
                </tr>
                <tr> 
                  <td background="images/tbl_g.gif"></td>
                  <td class="form_bgcolor"  colspan="2"> 
                    <div align="right">
                    		<html:button property="submitbutton" onclick="addProduct()"> <bean:message key="common.update" /></html:button>
                    </div>
                  </td>
                  <td background="images/tbl_d.gif"></td>
                </tr>
                <tr bordercolor="#111111">
											<td background="images/tbl_g.gif"></td>
											<td colspan="2" valign="top" class="form_bgcolor"
												style="padding: 5px 5px 5px 5px;">
												<table width="100%" cellspacing=0 cellpadding=0 border=0>
													<tbody>
														<tr>
															<td width="65%" class="desc_cell"><bean:message key="addProduct.customerName"/>: &nbsp;
															<bean:write name="addProductSetupForm" property="customerName"></bean:write></td>
															<td width="35%" class="desc_cell"><bean:message key="addProduct.nric"/>: &nbsp;
															<bean:write name="addProductSetupForm" property="nric"></bean:write></td>
														</tr>
													</tbody>
												</table>
											</td>
											<td background="images/tbl_d.gif"></td>
										</tr>
                <tr> 
                  <td background="images/tbl_g.gif"></td>
                  <td class="form_bgcolor" colspan="2" style="padding: 0px 5px 0px 5px;">
                  		<table width="100%" cellspacing=0 cellpadding=0 border=0>
                        <tbody>
                          <tr>
                            <td><table cellspacing=0 cellpadding=0 border=0>
                                <tbody>
                                  <tr>
                                    <td><img height=19 alt="" src="images/tab_g.gif" width=8 border=0></td>
                                    <td class=group_title background=images/tab_fond.gif><bean:message key ="addProduct.cardsAvailable"/> </td>
                                    <td><img height=19 alt="" src="images/tab_d.gif" width=8 border=0></td>
                                  </tr>
                                </tbody>
                            </table></td>
                          </tr>
                          <tr>
                            <td bgcolor=#dce5ea><div style="FONT-SIZE: 1px">&nbsp;</div></td>
                          </tr>
                          <tr>
                          	<td valign="top" class="form_bgcolor">
								<table width="100%" class="wrapper" border="0" cellspacing="1" cellpadding="2">
									<tr>
										<td class="title" nowrap><bean:message key="addProduct.cardno" /></td>
										<td class="title" nowrap><bean:message key="addProduct.acctId"/></td>
										<td class="title" nowrap><bean:message key="addProduct.creditLimit"/></td>
										<td class="title" nowrap><bean:message key="addProduct.limitUsed"/></td>
										<td class="title" nowrap><bean:message key="addProduct.cardStatus"/></td>
									</tr>
									<logic:iterate id="commObj" name="SEARCHLIST">
										<bean:define id="sno" name="commObj" property="column2" />
										<tr>
											<td class="DataTD" nowrap><bean:write name="commObj" property="column3" /></td>
											<td class="DataTD" nowrap><bean:write name="commObj" property="column4" /></td>
											<td class="DataTD" nowrap><bean:write name="commObj" property="column5" /></td>
											<td class="DataTD" nowrap><bean:write name="commObj" property="column6" /></td>
											<td class="DataTD" nowrap><bean:write name="commObj" property="column7" /></td>								
										</tr>
									</logic:iterate>
								</table>
							</td>																
                          </tr>
                        </tbody>
                      </table>
                  </td>
                  <td background="images/tbl_d.gif"></td>
                </tr>
                <tr>
					<td background="images/tbl_g.gif"></td>
					<td colspan="2" valign="top" class="form_bgcolor"
						style="padding: 20px 5px 5px 5px;">
						<table style="BORDER-COLLAPSE: collapse;" width="100%" cellspacing=0 cellpadding=0 border=0>
							<tr>
								<td colspan="3"><table cellspacing=0 cellpadding=0 border=0>
										<tbody>
											<tr>
												<td><img height=19 alt="" src="images/tab_g.gif" width=8
													border=0></td>
												<td class=group_title background=images/tab_fond.gif><bean:message
														key="addProduct.addNewCard" /></td>
												<td><img height=19 alt="" src="images/tab_d.gif" width=8
													border=0></td>
											</tr>
										</tbody>
									</table>
								</td>
							</tr>
							<tr style="background: #dce5ea;">
								<td class="desc_cell" colspan="3"><bean:message key="addProduct.cardProduct" />
									<html:select property="cardProductId" style="width:170px">
						       			<html:optionsCollection property="cardProductList" value="key" label="value" />
						      		</html:select>
								</td>
							</tr>
							<tr>
								<td width="25%" class="desc_cell"><html:radio 
										property="accountType" value="E" onclick="showListBox()" /> <bean:message
										key="addProduct.existingAccount" /></td>
								<td width="50%" align="center" class="desc_cell">
									<div id="listBoxId" style="display: none;">
										<%-- <html:select property="accountId">
											<html:options collection="accountIdAndCardProductIdList" property="key"
												labelProperty="value" />
										</html:select> --%>
										<html:select property="accountId" style="width:170px">
								       		<html:optionsCollection property="accountIdAndCardProductIdList" value="key" label="value" />
								      	</html:select>
									</div>
								</td>
								<td width="25%" class="desc_cell"><html:radio
										property="accountType" value="N" onclick="showListBox()" /> <bean:message
										key="addProduct.newAccount" /></td>
							</tr>
						</table>
					</td>
					<td background="images/tbl_d.gif"></td>
				</tr>
                <tr> 
                  <td background="images/tbl_g.gif"></td>
                  <td class="form_bgcolor"  colspan="2"> 
                    <div align="right">
                    		<html:button property="submitbutton" onclick="addProduct()"> <bean:message key="common.update" /></html:button>
                    </div>
                  </td>
                  <td background="images/tbl_d.gif"></td>
                </tr>
                <tr> 
                  <td> <img src="images/tbl_bas_g.gif" border="0" alt="" width="5" height="5"></td>
                  <td width="170" background="images/tbl_bas.gif"></td>
                  <td width="356" background="images/tbl_bas.gif"></td>
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


