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
<script language="JavaScript" src="inc/js/IncomingCTFProcess.js"></script>


<link rel="stylesheet" type="text/css" href="inc/yui/build/fonts/fonts-min.css" /> 
<link rel="stylesheet" type="text/css" href="inc/yui/build/container/assets/skins/sam/container.css" /> 
<script type="text/javascript" src="inc/yui/build/yahoo-dom-event/yahoo-dom-event.js"></script> 
<script type="text/javascript" src="inc/yui/build/connection/connection-min.js"></script> 
<script type="text/javascript" src="inc/yui/build/animation/animation-min.js"></script> 
<script type="text/javascript" src="inc/yui/build/dragdrop/dragdrop-min.js"></script> 
<script type="text/javascript" src="inc/yui/build/container/container-min.js"></script>

<script>

function go(action) {
	document.forms[0].method.value=action;
	document.forms[0].submit();
	load();
}
</script>

</head>

<body bgcolor="ffffff" class="yui-skin-sam" onload="DisableEnableLinks(false);">
<html:form action ="incomingCTFProcess.do" method="post" enctype="multipart/form-data">
<input type="hidden" name="method" value ="">

<table border="0" cellspacing="0" cellpadding="0" style="border-collapse: collapse" bordercolor="#111111" width="100%">
  <tr>
    <td> 
      <table border="0" cellspacing="0" cellpadding="0" width="100%">
        <tr>
            <td class="titreSection"><bean:message key ="incomingctfprocess.title"/></td>
        </tr>
        <tr>
          <td background="images/ligne.gif" colspan="2"> <img src="images/spacer.gif" width="1" height="1"></td>
        </tr>
        <tr>
          <td class="titreSection">
          <table border="0" cellspacing="0" cellpadding="4">
            <tr>
              <td></td>
              <td>&nbsp;</td>
            </tr>
          </table>
          </td>
          <td class="texteMenuGauche"></td>
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
                        <td nowrap class="ColumnFONT"><bean:message key ="incomingctfprocess.file"/> </td>
                        <td><html:file property="file" size="50"/></td>
                      </tr>
                      <tr>
                        <td nowrap class="ColumnFONT" colspan="2">
                        	<html:button property="submitbutton" onclick="go('incomingCTFFileProcess')"><bean:message key ="common.import"/></html:button>
                        </td>
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