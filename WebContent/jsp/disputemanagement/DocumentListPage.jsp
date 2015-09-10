<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/displaytag-12.tld" prefix="display"%>
<%@page import="org.transinfo.cacis.common.CommonDataBean"%>
<%@page import="java.util.ArrayList"%>

<html:html>
<head>
<title><bean:message key="disputedocumentssetup.screentitle" /></title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="inc/css/style.css" type="text/css">
</head>




<br><logic:present name="SEARCHLIST" scope="request">
	<display:table id="appProcess" name="requestScope.SEARCHLIST"
		width="100%" pagesize="20" cellpadding="2" cellspacing="1"
		border="1" class="label">
		<display:column property="column1" title="Remarks Id"
			class="label" group="1" />
		<display:column property="column2" title="Claim Type"
			class="label" group="2" />
		<display:column property="column3" title="Document Uploaded"
			class="label" group="3" />
		<display:column property="column4" title="Claim Date"
			class="label" group="4" />
		<display:column property="column5" title="Document Name"
			class="label" />
		
	</display:table>
</logic:present>

</html:html>