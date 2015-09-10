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
  
    function addNew() {
       document.forms[0].action="creditlimitprofileprocess.do?method=addNew";
       document.forms[0].submit();
    }

    function searchlist(){
 		document.forms[0].submit();
 	}
    
    function view(sNo) 
    {
      document.forms[0].method.value="change";
      document.forms[0].sNo.value=sNo;
      document.forms[0].action ="creditlimitprofileprocess.do";
      document.forms[0].submit();
    }
</script>
<body bgcolor="ffffff">
 
<html:form action ="creditlimitprofilelist.do">
	<html:hidden property="issuerId" value="<%=(String)session.getAttribute("ISSUER")%>"/> 
	<input type="hidden" name="method">
	<input type="hidden" name="sNo" value ="">
	<html:hidden property="screen"/>
	     
	<table border="0" cellspacing="0" cellpadding="0" style="border-collapse: collapse" bordercolor="#111111" width="100%">

  <tr>
    <td> 
      <table border="0" cellspacing="0" cellpadding="0" width="100%">
        <tr>
            <td class="titreSection"><bean:message key ="creditlimitprofile.title"/></td>
        </tr>
        <tr>
          <td background="images/ligne.gif" colspan="2"> <img src="images/spacer.gif" width="1" height="1"></td>
        </tr>
        <tr>
          <td class="titreSection"><table border="0" cellspacing="0" cellpadding="4">
            <tr>
              <td><html:button property="submitbutton" onclick ="addNew()"><bean:message key ="common.addnew"/></html:button><br></td>
              <td><html:button property="submitbutton" onclick="searchlist()"><bean:message key ="common.search"/></html:button></td>
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
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr> 
                <td> 
                    <table cellpadding="0" cellspacing="4">
                      <tr> 
                        <td class="ColumnFONT"><bean:message key ="creditlimitprofile.score"/></td>
                        <td> 
                        	<html:select property="scoreId" >
                        		<html:option value=""> </html:option>	
					         	<html:optionsCollection property="creditLimitProfileList" value="key" label="value" />
					        </html:select>
                        </td>
                        <td style="width: 30px;"></td>
                        <td class="ColumnFONT"><bean:message key ="creditlimitprofile.productname"/></td>
                        <td> 
                        	<html:select property="cardProductId" >
                        		<html:option value=""> </html:option>	
					         	<html:optionsCollection property="cardProductList" value="key" label="value" />
					        </html:select>
                        </td>
                      </tr>
                                      
                      <tr>
                        <td>&nbsp;<%@ include file="/jsp/common/Buttons.jsp"%></td>
                        <td><div align="right"></div></td>
                      </tr>
                    </table>                
                 </td>
              </tr>
            </table>
          </td>
        </tr>
      </html:form>   
        <tr> 
          <td valign="top">  
   				<br><!-- to Show the List Data --> 
					<logic:present name="SEARCHLIST" scope="request">
						<bean:size id="size" name="SEARCHLIST" />
						<logic:greaterThan name="size" value="0">
							<tr>
								<td>
									<table border="0" cellspacing="0" cellpadding="0" width="80%">
										<tr>
											<td style="PADDING-RIGHT: 20px; PADDING-LEFT: 20px; PADDING-TOP: 10px">
												<b>
												    <bean:write name="creditLimitProfileSearchForm" property="totalCount" />
												</b> 
												<bean:message key="tranxenquiry.totcountmsg" />
											</td>
											<td></td>
										</tr>
										<tr>
											<td
												style="PADDING-RIGHT: 20px; PADDING-LEFT: 20px; PADDING-TOP: 10px">
												<%@ include file="/jsp/common/TranxButtons.jsp"%>
											</td>
											<td></td>
										</tr>
										<tr>
											<td style="PADDING-RIGHT: 20px; PADDING-LEFT: 20px; PADDING-BOTTOM: 10px; 
												PADDING-TOP: 10px">
												<table class="wrapper" id="lstData" border="0" cellspacing="1"
													cellpadding="2">
													<tr>
													   <td class="title" nowrap><bean:message
                                                                key="creditlimitprofile.score" /></td>
														<td class="title" nowrap><bean:message
																key="creditlimitprofile.productname" /></td>
														<td class="title" nowrap><bean:message
																key="creditlimitprofile.update" /></td>
														<td class="title" nowrap></td>
													</tr>
													<logic:iterate id="commObj" name="SEARCHLIST">
														<tr>
															<td class="DataTD" nowrap><bean:write name="commObj"
																	property="column3" /></td>
															<td class="DataTD" nowrap><bean:write name="commObj"
																	property="column2" /></td>
															<td class="DataTD" nowrap><bean:write name="commObj"
																	property="column4" /></td>
															<td class="title" nowrap>
																<input type="button" value="Update" onclick="javascript:view('<bean:write name="commObj"
																property="column1" />');">
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

</body>
</html:html>