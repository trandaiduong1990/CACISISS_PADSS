<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/displaytag-12.tld" prefix="display" %>


<html:html>
<head>
<title><bean:message key ="carddeliversearch.screentitle"/></title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="inc/css/style.css" type="text/css">
<script language="JavaScript" src="inc/js/cacis.js"></script>
<script language="JavaScript" src="inc/js/cardProcess.js"></script>
</head>
<script>


function go(action) {
   	document.forms[0].mode.value=action;
   	document.forms[0].action ="mccmasterlistpage.do";
 	document.forms[0].submit();
 } 
  

 function searchlist(){
		document.forms[0].submit();
	}
 </script>
<body bgcolor="ffffff">
<html:form action ="mccmasterlistpage.do">
 <input type="hidden" name="method" value ="save">
<table border="0" cellspacing="0" cellpadding="0" style="border-collapse: collapse" bordercolor="#111111" width="100%">
  <tr>
    <td> 
      <table border="0" cellspacing="0" cellpadding="0" width="100%">
        <tr>
            <td class="titreSection"><bean:message key ="mccsetup.screentitle"/></td>
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
          </table>
           <td class="texteMenuGauche"><div align="right"><a href='javascript:helpLink("WRITEOFF")'>Help</a></div></td>
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
                        <td nowrap class="ColumnFONT"><bean:message key ="mccsetup.mcc"/> </td>
                        <td><html:text property="mcc" size="20"/></td>
                      </tr>
                      <tr>
                        <td nowrap class="ColumnFONT"><bean:message key ="mccsetup.mccn"/></td>
                        <td><html:text property="mccName" size="20" /></td>
                      </tr>
                    </table>
                 </td>
              </tr>
            </table>
          </td>
        </tr>
        <tr>
          <td style="padding-left: 20px;"><%@ include file="/jsp/common/Buttons.jsp"%></td>
        </tr>
        <tr> 
          <td valign="top">
    <!-- to Show the List Data -->   
       <logic:present name="SEARCHLIST" scope="request"> 
            <bean:size id ="size" name ="SEARCHLIST"/>
                <logic:greaterThan  name ="size" value ="0"> 
           <tr> 
              <td>
                <table border="0" cellspacing="0" cellpadding="0">
                   <tr>
                      <td style="PADDING-RIGHT: 20px; PADDING-LEFT: 20px; PADDING-BOTTOM: 10px; PADDING-TOP: 20px">
                      <table class="wrapper" border="0" cellspacing="1" cellpadding="2">
                       <tr>
                       <td colspan="5" nowrap class="title"><table width="100%" border="0" cellspacing="0" cellpadding="0">
                         </table></td>
                           </tr>
                                <tr>
                                    <td class="title" nowrap><bean:message key ="mccsetup.mcc"/></td>
                                    <td class="title" nowrap><bean:message key ="mccsetup.mccn"/></td>
                                    <td class="title" nowrap><bean:message key ="mccsetup.updatedate"/></td>
                                 </tr>
                               <logic:iterate id ="commObj" name ="SEARCHLIST"> 
                                  <tr>
                                    <td class="DataTD" nowrap><bean:write name ="commObj"  property="column1" /></td>
                                    <td class="DataTD" nowrap><bean:write name ="commObj" property="column2" /></td>
                                    <td class="DataTD" nowrap><bean:write name ="commObj" property="column3" /></td>
                                  </tr>
                               </logic:iterate>           
                              </table></td>
                            </tr>
                            
                        </table></td>
                        <td background=images/tbl_d.gif></td>
                     </tr>
                   </logic:greaterThan>
        </logic:present>
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