<%@page import="org.transinfo.cacis.report.databean.UserDataBean"%>

<BODY class=textsw bgColor=#467C99 leftMargin=0 topMargin=0 marginheight="0" marginwidth="0">
<TABLE cellSpacing=0 cellPadding=0 width="100%" border=0>
  <TBODY> 
  <TR class=textsw> 
    <TD noWrap width="58%" height=30>&nbsp;&nbsp;&nbsp; 
      <script language="JavaScript">
        var str = parent.frames["mainFrame"].document.frmMain.AC.value;
        var len = str.length;
        i = 0;

        flag = false;
        for(i=0 ;len-1 >= i; i++){ if (str.charAt(i) == "B") {flag = true;break;}}
        if (flag){ document.write('<a class=links_menu href="javascript:callFunction(\'Back\')"><STRONG>Back</STRONG></a>&nbsp;&nbsp;')}

        flag = false;
        for(i=0 ;len-1 >= i; i++){ if (str.charAt(i) == "S") {flag = true;break;}}
        if (flag){ document.write('<a class=links_menu href="javascript:callFunction(\'Save\')"><STRONG>Save</STRONG></a>&nbsp;&nbsp;')}

        flag = false;
        for(i=0 ;len-1 >= i; i++){ if (str.charAt(i) == "A") {flag = true;break;}}
        if (flag){ document.write('<a class=links_menu href="javascript:callFunction(\'Add\')"><STRONG>Add</STRONG></a>&nbsp;&nbsp;')}

        flag = false;
        for(i=0 ;len-1 >= i; i++){ if (str.charAt(i) == "C") {flag = true;break;}}
        if (flag){ document.write('<a class=links_menu href="javascript:callFunction(\'Change\')"><STRONG>Change</STRONG></a>&nbsp;&nbsp;')}

        flag = false;
        for(i=0 ;len-1 >= i; i++){ if (str.charAt(i) == "D") {flag = true;break;}}
        if (flag){ document.write('<a class=links_menu href="javascript:callFunction(\'Delete\')"><STRONG>Delete</STRONG></a>&nbsp;&nbsp;')}

                    
         flag = false;
        for(i=0 ;len-1 >= i; i++){ if (str.charAt(i) == "F") {flag = true;break;}}
        if (flag){document.write('<a class=links_menu href="javascript:callFunction(\'Process\')"><STRONG>Process</STRONG></a>&nbsp;&nbsp;')}

        flag = false;
        for(i=0 ;len-1 >= i; i++){ if (str.charAt(i) == "T") {flag = true;break;}}
        if (flag){ document.write('<a class=links_menu href="javascript:callFunction(\'Activate\')"><STRONG>Activate</STRONG></a>&nbsp;&nbsp;')}
         //changed by satyam reddy  before it "W" now "R" for Reject 
        flag = false;
        for(i=0 ;len-1 >= i; i++){ if (str.charAt(i) == "R") {flag = true;break;}}
        if (flag){ document.write('<a class=links_menu href="javascript:callFunction(\'Reject\')"><STRONG>Reject</STRONG></a>&nbsp;&nbsp;')}

        flag = false;
        for(i=0 ;len-1 >= i; i++){ if (str.charAt(i) == "L") {flag = true;break;}}
        if (flag){ document.write('<a class=links_menu href="javascript:callFunction(\'Lock\')"><STRONG>Lock</STRONG></a>&nbsp;&nbsp;')}

        flag = false;
        for(i=0 ;len-1 >= i; i++){ if (str.charAt(i) == "U") {flag = true;break;}}
        if (flag){ document.write('<a class=links_menu href="javascript:callFunction(\'Unlock\')"><STRONG>Unlock</STRONG></a>&nbsp;&nbsp;')}

        flag = false;
        for(i=0 ;len-1 >= i; i++){ if (str.charAt(i) == "V") {flag = true;break;}}
        if (flag){ document.write('<a class=links_menu href="javascript:callFunction(\'List\')"><STRONG>List</STRONG></a>&nbsp;&nbsp;')}

        flag = false;
        for(i=0 ;len-1 >= i; i++){ if (str.charAt(i) == "I") {flag = true;break;}}
        if (flag){ document.write('<a class=links_menu href="javascript:callFunction(\'Inquiry\')"><STRONG>Inquiry</STRONG></a>&nbsp;&nbsp;')}

        flag = false;
        for(i=0 ;len-1 >= i; i++){ if (str.charAt(i) == "Z") {flag = true;break;}}
        if (flag){document.write('<a class=links_menu href="javascript:callFunction(\'Stop\')"><STRONG>Stop</STRONG></a>&nbsp;&nbsp;')}

        flag = false;
        for(i=0 ;len-1 >= i; i++){ if (str.charAt(i) == "J") {flag = true;break;}}
        if (flag){ document.write('<a class=links_menu href="javascript:callFunction(\'Reset\')"><STRONG>Reset</STRONG></a>&nbsp;&nbsp;')}
        
        flag = false;
        for(i=0 ;len-1 >= i; i++){ if (str.charAt(i) == "G") {flag = true;break;}}
        if (flag){document.write('<a class=links_menu href="javascript:callFunction(\'Search\')"><STRONG>Search</STRONG></a>&nbsp;&nbsp;')}

        flag = false;
        for(i=0 ;len-1 >= i; i++){ if (str.charAt(i) == "P") {flag = true;break;}}
        if (flag){document.write('<a class=links_menu href="javascript:callFunction(\'Prev\')"><STRONG>Prev</STRONG></a>&nbsp;&nbsp;')}

        flag = false;
        for(i=0 ;len-1 >= i; i++){ if (str.charAt(i) == "N") {flag = true;break;}}
        if (flag){ document.write('<a class=links_menu href="javascript:callFunction(\'Next\')"><STRONG>Next</STRONG></a>&nbsp;&nbsp;')}

        flag = false;
        for(i=0 ;len-1 >= i; i++){ if (str.charAt(i) == "M") {flag = true;break;}}
        if (flag){ document.write('<a class=links_menu href="javascript:callFunction(\'More\')"><STRONG>More</STRONG></a>&nbsp;&nbsp;')}

        flag = false;
        for(i=0 ;len-1 >= i; i++){ if (str.charAt(i) == "H") {flag = true;break;}}
        if (flag){ document.write('<a class=links_menu href="javascript:callFunction(\'Help\')"><STRONG>Help</STRONG></a>&nbsp;&nbsp;')}

        flag = false;
        for(i=0 ;len-1 >= i; i++){ if (str.charAt(i) == "W") {flag = true;break;}}
        if (flag){document.write('<a class=links_menu href="javascript:callFunction(\'Transx\')"><STRONG>Transx</STRONG></a>&nbsp;&nbsp;')}
                
        flag = false;
        for(i=0 ;len-1 >= i; i++){ if (str.charAt(i) == "Y") {flag = true;break;}}
        if (flag){ document.write('<a class=links_menu href="javascript:callFunction(\'Payment\')"><STRONG>Payment</STRONG></a>&nbsp;&nbsp;')}

       //added by satyam reddy to Accept 
		
		flag = false;
        for(i=0 ;len-1 >= i; i++){ if (str.charAt(i) == "K") {flag = true;break;}}
        if (flag){ document.write('<a class=links_menu href="javascript:callFunction(\'Accept\')"><STRONG>Accept</STRONG></a>&nbsp;&nbsp;')}
      //added by satyam reddy to  Close
	
		flag = false;
        for(i=0 ;len-1 >= i; i++){ if (str.charAt(i) == "0") {flag = true;break;}}
        if (flag){ document.write('<a class=links_menu href="javascript:callFunction(\'Close\')"><STRONG>Close</STRONG></a>&nbsp;&nbsp;')}

          //added by satyam reddy to  CardPersonalization 
      flag = false;
        for(i=0 ;len-1 >= i; i++){ if (str.charAt(i) == "e") {flag = true;break;}}
     if (flag){ document.write('<a class=links_menu href="javascript:callFunction(\'ExportCardData\')"><STRONG>ExportCardData</STRONG></a>&nbsp;&nbsp;')}

	  flag = false;
        for(i=0 ;len-1 >= i; i++){ if (str.charAt(i) == "m") {flag = true;break;}}
     if (flag){ document.write('<a class=links_menu href="javascript:callFunction(\'CardMake\')"><STRONG>CardMake</STRONG></a>&nbsp;&nbsp;')}

   flag = false;
        for(i=0 ;len-1 >= i; i++){ if (str.charAt(i) == "p") {flag = true;break;}}
     if (flag){ document.write('<a class=links_menu href="javascript:callFunction(\'PinMake\')"><STRONG>PinMake</STRONG></a>&nbsp;&nbsp;')}
	
	  flag = false;
     for(i=0 ;len-1 >= i; i++){ if (str.charAt(i) == "d") {flag = true;break;}}
     if (flag){ document.write('<a class=links_menu href="javascript:callFunction(\'CardDeliver\')"><STRONG>CardDeliver</STRONG></a>&nbsp;&nbsp;')}

	 flag = false;
        for(i=0 ;len-1 >= i; i++){ if (str.charAt(i) == "5") {flag = true;break;}}
        if (flag){document.write('<a class=links_menu href="javascript:callFunction(\'Confirm\')"><STRONG>Confirm</STRONG></a>&nbsp;&nbsp;')}

	flag = false;
        for(i=0 ;len-1 >= i; i++){ if (str.charAt(i) == "v") {flag = true;break;}}
        if (flag){document.write('<a class=links_menu href="javascript:callFunction(\'CardActivity\')"><STRONG>CardActivity</STRONG></a>&nbsp;&nbsp;')}

	flag = false;
        for(i=0 ;len-1 >= i; i++){ if (str.charAt(i) == "t") {flag = true;break;}}
        if (flag){document.write('<a class=links_menu href="javascript:callFunction(\'CardTransaction\')"><STRONG>CardTransaction</STRONG></a>&nbsp;&nbsp;')}

		 flag = false;
        for(i=0 ;len-1 >= i; i++){ if (str.charAt(i) == "s") {flag = true;break;}}
        if (flag){document.write('<a class=links_menu href="javascript:callFunction(\'Details\')"><STRONG>Details</STRONG></a>&nbsp;&nbsp;')}

		 flag = false;
        for(i=0 ;len-1 >= i; i++){ if (str.charAt(i) == "r") {flag = true;break;}}
        if (flag){document.write('<a class=links_menu href="javascript:callFunction(\'Replace\')"><STRONG>Replace</STRONG></a>&nbsp;&nbsp;')}
		
		
        flag = false;
        for(i=0 ;len-1 >= i; i++){ if (str.charAt(i) == "X") {flag = true;break;}}
        if (flag){document.write('<a class=links_menu href="javascript:callFunction(\'Calculate\')"><STRONG>Calculate</STRONG></a>&nbsp;&nbsp;')}

        flag = false;
        for(i=0 ;len-1 >= i; i++){ if (str.charAt(i) == "a") {flag = true;break;}}
        if (flag){document.write('<a class=links_menu href="javascript:callFunction(\'FileUpdate\')"><STRONG>FileUpdate</STRONG></a>&nbsp;&nbsp;')}

        flag = false;
        for(i=0 ;len-1 >= i; i++){ if (str.charAt(i) == "b") {flag = true;break;}}
        if (flag){document.write('<a class=links_menu href="javascript:callFunction(\'Query\')"><STRONG>Query</STRONG></a>&nbsp;&nbsp;')}

      flag = false;
        for(i=0 ;len-1 >= i; i++){ if (str.charAt(i) == "c") {flag = true;break;}}
        if (flag){document.write('<a class=links_menu href="javascript:callFunction(\'ChargeBack\')"><STRONG>ChargeBack</STRONG></a>&nbsp;&nbsp;')}

        flag = false;
        for(i=0 ;len-1 >= i; i++){ if (str.charAt(i) == "h") {flag = true;break;}}
        if (flag){document.write('<a class=links_menu href="javascript:callFunction(\'History\')"><STRONG>CustomerHistory</STRONG></a>&nbsp;&nbsp;')}

        flag = false;
        for(i=0 ;len-1 >= i; i++){ if (str.charAt(i) == "E") {flag = true;break;}}
        if (flag){ document.write('<a class=links_menu href="javascript:callFunction(\'Down\')"><STRONG>Download as PDF</STRONG></a>&nbsp;&nbsp;')}
              
      
      </script>
    </TD>
<%
  String zszOrgId = new String();
  UserDataBean zoUserData = new UserDataBean();
  zoUserData = (UserDataBean) session.getAttribute("USERDETAILS");
  zszOrgId = zoUserData.getIssuerId();
%>
    <TD width="15%">Org Id : <%=zszOrgId%> </TD>
    <TD width="20%">User ID : <%=(String)session.getAttribute("USERID")%> </TD>
    <TD width="7%" valign="middle" align="center"> 
        <A class=links_menu href="javascript:LogOff()"><STRONG>LOGOUT</STRONG></A> 
        
    </TD>
  </TR>
  </TBODY> 
</TABLE>
