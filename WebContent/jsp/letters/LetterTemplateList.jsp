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
       document.forms[0].action="lettertemplateprocess.do?method=addNew";
       document.forms[0].submit();
    }
    
    function printLetter() {
        document.forms[0].action="lettertemplateprocess.do?method=printLetter";
        document.forms[0].submit();
    }
    
    function searchlist(){
 		document.forms[0].submit();
 	}
    
    function go(action) {
    	document.forms[0].mode.value=action;
    	searchlist();
    }
</script>
<body bgcolor="ffffff">
 
<html:form action ="lettertemplatelist.do">
	<html:hidden property="issuerId" value="<%=(String)session.getAttribute("ISSUER")%>"/>      
	<table border="0" cellspacing="0" cellpadding="0" style="border-collapse: collapse" bordercolor="#111111" width="100%">

  <tr>
    <td> 
      <table border="0" cellspacing="0" cellpadding="0" width="100%">
        <tr>
            <td class="titreSection"><bean:message key ="lettertemplate.titleseach"/></td>
        </tr>
        <tr>
          <td background="images/ligne.gif" colspan="2"> <img src="images/spacer.gif" width="1" height="1"></td>
        </tr>
        <tr>
          <td class="titreSection"><table border="0" cellspacing="0" cellpadding="4">
            <tr>
              <td><html:button property="submitbutton" onclick ="addNew()"><bean:message key ="common.addnew"/></html:button><br></td>
              <td><html:button property="submitbutton" onclick="searchlist()"><bean:message key ="common.search"/></html:button></td>
              <td><html:button property="submitbutton" onclick="printLetter()"><bean:message key ="common.search"/></html:button></td>
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
                        <td class="ColumnFONT"><bean:message key ="lettertemplate.id"/></td>
                        <td>&nbsp;</td>
                        <td> 
                        	<html:select property="letterId" >
                        		<html:option value=""> </html:option>	
					         	<html:optionsCollection property="letterIdList" value="key" label="value" />
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
          	<!-- to Show the List Data -->   
			<logic:present name="SEARCHLIST" scope="request"> 
		     	<bean:size id ="size" name ="SEARCHLIST"/>
		        	<logic:greaterThan  name ="size" value ="0"> 
			           <tr> 
			              <td>
			                <table border="0" cellspacing="0" cellpadding="0" width="60%">
			                <tr>
							     <td style="PADDING-RIGHT: 20px; PADDING-LEFT: 20px; PADDING-TOP: 10px">
							     	<b><bean:write name="letterTemplateSearchForm" property="totalCount"/></b>
							     	<bean:message key ="tranxenquiry.totcountmsg"/>
							     </td>
							     <td></td>	
			                 </tr>
			                 <tr>
							     <td></td>	
			                 </tr>
			                 <tr>
			                      <td style="PADDING-RIGHT: 20px; PADDING-LEFT: 20px; PADDING-BOTTOM: 10px; PADDING-TOP: 10px">
			                      <table class="wrapper" border="0" cellspacing="1" cellpadding="2">
	                                <tr>
										<td class="title" nowrap><bean:message key ="lettertemplate.id"/></td>
										<td class="title" nowrap><bean:message key ="lettertemplate.desc"/></td>
	                                    <td class="title" nowrap><bean:message key ="lettertemplate.updatedate"/></td>
	                                    <td class="title" nowrap><bean:message key ="lettertemplate.updateby"/></td>
	                                    <td class="title" nowrap></td>
	                                 </tr>
		                               <logic:iterate id ="commObj" name ="SEARCHLIST">
										<bean:define id="sno" name="commObj" property="column1"/>
		                                  <tr>
		                                    <td class="DataTD" nowrap><bean:write name ="commObj"  property="column1" /></td>
		                                    <td class="DataTD" nowrap><bean:write name ="commObj" property="column2" /></td>
		                                    <td class="DataTD" nowrap><bean:write name ="commObj" property="column3" /></td>
		                                    <td class="DataTD" nowrap><bean:write name ="commObj" property="column4" /></td>
	                                    	<td class="title" nowrap>
	                                    		<form name ="listForm" action="lettertemplateprocess.do">
													<html:hidden property="id" value="<%= ((CommonDataBean)pageContext.getAttribute("commObj")).getColumn1()%>" />       
										            <html:hidden property="method" value ="change"/>
										            <html:submit value="Update"/>
												</form>
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