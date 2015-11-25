<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<html:html>
<HEAD>
<LINK href="../inc/css/stylemenu.css" type=text/css rel=StyleSheet>
<LINK href="../inc/css/udm-style.css" type=text/css rel=StyleSheet>
<META http-equiv=Content-Type content="text/html; charset=ISO-8859-1">
<SCRIPT src="../inc/js/udm-custom.js" type=text/javascript></SCRIPT>
<SCRIPT src="../inc/js/udm-control.js" type=text/javascript></SCRIPT>
<SCRIPT src="../inc/js/udm-dom.js" type=text/javascript></SCRIPT>
</HEAD>


<%org.transinfo.cacis.dto.useraccess.UserMasterDto objUserAccess = (org.transinfo.cacis.dto.useraccess.UserMasterDto) session.getAttribute((String)session.getAttribute("USERID"));
%>
<BODY bottomMargin=0 bgColor=#1c5b94 leftMargin=0 topMargin=0 rightMargin=0 marginwidth="0" marginheight="0">

<UL class=udm id=udm>
  <li class=onclick><a href="#"><div  align="center"><strong><<<<&nbsp;&nbsp; MENU &nbsp;&nbsp; >>>></strong></div></a></li>
  <p>



<!---  added by satyam --->
<%if(objUserAccess.hasScreen("ISS_USER_ROLE") || objUserAccess.hasScreen("ISS_USER_USER")){%>
    <li class=onclick><A href="#" target=mainFrame>&nbsp;&nbsp;<b>Issuer Management</b></a>
        <ul>
			<!-- -->
        <%if(objUserAccess.hasScreen("ISS_USER_ROLE")){%>
            <li class=onclick> <html:link  action ="rolefunctionlist.do?method=search&userType=ISSADMIN" target='mainFrame'>&nbsp;&nbsp;Role
            Function Setup </html:link> </li>
        <%}%>
        <%if(objUserAccess.hasScreen("ISS_USER_USER")){%>
              <li class=onclick><html:link  action ="usersetuplistpage.do?method=List&userType=ISSADMIN" target='mainFrame'>&nbsp;&nbsp;Issuer User Setup </html:link>  </li>
        <%}%>
        <%if(objUserAccess.hasScreen("ASSIGN_USER")){%>
              <li class=onclick><html:link  action ="assignuserlist.do?method=List&userType=ISSADMIN" target='mainFrame'>&nbsp;&nbsp;Assign User Setup </html:link>  </li>
        <%}%>
        </ul>
    </li>
<%}%>

<%if(objUserAccess.hasScreen("ALERT_MASTER_SETUP") || objUserAccess.hasScreen("LICENSE_SETUP")){%>
    <li class=onclick><A href="#" target=mainFrame>&nbsp;&nbsp;<b>General Adiministration</b></a>
        <ul>
			<!-- -->
        <%if(objUserAccess.hasScreen("ALERT_MASTER_SETUP")){%>
            <li class=onclick><html:link  action ="alertmasterlist.do?method=search" target='mainFrame'>Alert Management</html:link></li>
        <%}%>
        <%if(objUserAccess.hasScreen("LICENSE_SETUP")){%>
           <li class=onclick><html:link  action ="licensemasterprocess.do?method=get" target='mainFrame'>Upload Licenece</html:link></li>
        <%}%>
        </ul>
    </li>
<%}%>



<%if(objUserAccess.hasScreen("BLACKLIST_CARDS")|| objUserAccess.hasScreen("TAGFIELD_SETUP")
    ||objUserAccess.hasScreen("LOGIN_PARAM_SETUP")||objUserAccess.hasScreen("SYSTEM_PARMA_SETUP")||objUserAccess.hasScreen("SMS_SERVICE")
    ||objUserAccess.hasScreen("BLOCK_MERCHANT")){%>
  <li class=onclick><a href="#">&nbsp;&nbsp;<b>Authorization Processing</b></a>
    <ul><!-- -->
        <%if(objUserAccess.hasScreen("BLACKLIST_CARDS")){%>
	     <li class=onclick><html:link  action ="blacklistcardlistpage.do" target='mainFrame'>BlackListCards</html:link></li>
	     
        <% } %>
        <%if(objUserAccess.hasScreen("BLOCK_MERCHANT")){%>
        	     <li class=onclick><html:link  action ="/blockmerchantlistpage.do" target='mainFrame'>BlockMerchant</html:link></li>
        
        <% } %>
        <%--
        <%if(objUserAccess.hasScreen("TAGFIELD_SETUP")){%>
	    <li class=onclick><html:link  action ="tagfieldformatlistpage.do" target='mainFrame'>TagFieldFormat</html:link></li>
        <% } %>
         <%if(objUserAccess.hasScreen("SERVER_PARAM_SETUP")){%>
	     <li class=onclick><html:link  action ="serverparameterlist.do?method=get" target='mainFrame'>Server Parameter</html:link></li>
        <% } %>
         --%>
        <%if(objUserAccess.hasScreen("LOGIN_PARAM_SETUP")){%>
	    <li class=onclick><html:link  action ="loginparamprocess.do?method=get" target='mainFrame'>LoginParam</html:link></li>
        <% } %>
         <%if(objUserAccess.hasScreen("SYSTEM_PARMA_SETUP")){%>
	    <li class=onclick><html:link  action ="systemparamprocess.do?method=get" target='mainFrame'>SystemParam</html:link></li>
        <% } %>
    </ul>
  </li>
<% } %>
<%--
<%if(objUserAccess.hasScreen("SWITCH_SETUP")){%>
  <li class=onclick><a href="#">&nbsp;&nbsp;<b>Switching</b></a>
    <ul><!-- -->
        <%if(objUserAccess.hasScreen("SWITCH_SETUP")){%>
	     <li class=onclick><html:link action ="switchlist.do?method=get" target='mainFrame'>Hosts Routing Config </html:link></li>
	     <!-- <li class=onclick><html:link  action ="sessionexpired.do" target='mainFrame'>Session Test </html:link></li>-->
        <% } %>
          
    </ul>
  </li>
<% } %>
 --%>
<%if(objUserAccess.hasScreen("BRANCH_SETUP")|| objUserAccess.hasScreen("CUSTOMER_SETUP")|| objUserAccess.hasScreen("CARDTYPE_SETUP")
    ||objUserAccess.hasScreen("CARDPRODUCT_SETUP")||objUserAccess.hasScreen("CARDPROMOTION_SETUP")||objUserAccess.hasScreen("CARDPRODUCTRATE_SETUP")||
    objUserAccess.hasScreen("CARDPRODUCTFEE_SETUP")||objUserAccess.hasScreen("CYCLE_SETUP ")||objUserAccess.hasScreen("MCC_SETUP")||
    objUserAccess.hasScreen("CURRENCY_RATE_SETUP")||objUserAccess.hasScreen("GROUPFEE_SETUP")){%>
  <li class=onclick><a href="#">&nbsp;&nbsp;<b>Settings</b></a>
    <ul><!-- -->
        <%if(objUserAccess.hasScreen("BRANCH_SETUP")){%>
	     <li class=onclick><html:link  action ="branchlist.do?method=List" target='mainFrame'>Branch Setup</html:link></li>
        <% } %>
        <%if(objUserAccess.hasScreen("CUSTOMER_SETUP")){%>
	    <li class=onclick><html:link  action ="customertypelist.do?method=List" target='mainFrame'>Customer Setup</html:link></li>
        <% } %>
         <%if(objUserAccess.hasScreen("CARDTYPE_SETUP")){%>
	     <li class=onclick><html:link  action ="cardtypelist.do?method=List" target='mainFrame'>Card Type Setup</html:link></li>
        <% } %>
        <%if(objUserAccess.hasScreen("CARDPRODUCT_SETUP")){%>
	    <li class=onclick><html:link  action ="cardproductlist?method=List" target='mainFrame'>Card Product Setup</html:link></li>
        <% } %>
        <%if(objUserAccess.hasScreen("CARDPRODUCTLIMIT_SETUP")){%>
	    <li class=onclick><html:link  action ="cardproductlimitlist?method=List" target='mainFrame'>Card Product Limit Setup</html:link></li>
        <% } %>
         <%if(objUserAccess.hasScreen("CARDPRODUCTRATE_SETUP")){%>
	     <li class=onclick><html:link  action ="cardproductratelist?method=List" target='mainFrame'>Card Product Rate Setup</html:link></li>
        <% } %>
         <%if(objUserAccess.hasScreen("CARDPRODUCTFEE_SETUP")){%>
	     <li class=onclick><html:link  action ="cardproductfeelist?method=List" target='mainFrame'>Card Product Fee Setup</html:link></li>
        <% } %>
         <%if(objUserAccess.hasScreen("CYCLE_SETUP")){%>
	    <li class=onclick><html:link  action ="cyclelist.do?method=List" target='mainFrame'>Cycle Setup</html:link></li>
        <% } %>
        <%--
         <%if(objUserAccess.hasScreen("MCC_SETUP")){%>
	     <li class=onclick><html:link  action ="mcclistpage.do" target='mainFrame'>MCC Setup</html:link></li>
        <% } %>
         <%if(objUserAccess.hasScreen("CURRENCY_RATE_SETUP")){%>
	       <li class=onclick><html:link  action ="currencyrateprocess.do?method=init" target='mainFrame'>Currency Rate Setup</html:link></li>
        <% } %>
         --%>
        <%if(objUserAccess.hasScreen("SALARY_PROFILE_SETUP")){%>
	       <li class=onclick><html:link  action ="salaryprofileprocess.do?method=init" target='mainFrame'>Salary Profile Setup</html:link></li>
        <% } %>
        <%if(objUserAccess.hasScreen("GROUPFEE_SETUP")){%>
	    <li class=onclick><html:link  action ="customergroupfeelist?method=List" target='mainFrame'>Customer Group Fee Setup</html:link></li>
        <% } %>
		<%if(objUserAccess.hasScreen("PRODUCT_TRANX_SETUP")){%>
	    <li class=onclick><html:link  action ="producttranxlist?method=List" target='mainFrame'>Product Tranx Setup</html:link></li>
        <% } %>
        <%if(objUserAccess.hasScreen("CARD_PRODUCT_RULES")){%>
	    <li class=onclick><html:link  action ="cardproductruleslist?method=List" target='mainFrame'>Card Product Rules</html:link></li>
        <% } %>
        <%--if(objUserAccess.hasScreen("EMV_PROFILE")){--%>
	     <li class=onclick><html:link action ="emvprofilelist.do?method=List"  paramScope="request" target='mainFrame'>EMV Profile</html:link></li>
        <%-- } --%>
		<% if (objUserAccess.hasScreen("CREDIT_LIMIT_PROFILE")) { %>
			<li class=onclick><html:link action ="creditlimitprofilelist.do?method=List"  paramScope="request" target='mainFrame'>Credit Limit Profile</html:link></li>
        <% } %>
    </ul>
  </li>
<% } %>

<%if(objUserAccess.hasScreen("KEYINDEX_SETUP")){%>
  <li class=onclick><a href="#">&nbsp;&nbsp;<b>Key Management</b></a>
    <ul><!-- -->
        <%if(objUserAccess.hasScreen("KEYINDEX_SETUP")){%>
	     <!-- <li class=onclick><html:link  action ="keyindexlist.do?method=search" target='mainFrame'>Key Index</html:link></li> -->
	     <li class=onclick><html:link  action ="keyindexlist.do" target='mainFrame'>Key Index</html:link></li>
        <% } %>
          
    </ul>
  </li>
<% } %>

<%if(objUserAccess.hasScreen("NEW_APPL_FORM_CREATION")|| objUserAccess.hasScreen("NEW_APPL_FORM_PROCESS")|| objUserAccess.hasScreen("CARD_EMBOSSING")
    ||objUserAccess.hasScreen("PIN_PRINTING")||objUserAccess.hasScreen("CARD_DELIVER")||objUserAccess.hasScreen("CARD_RECEIVED")){%>
  <li class=onclick><a href="#">&nbsp;&nbsp;<b>Card Production</b></a>
    <ul><!-- -->
        <%if(objUserAccess.hasScreen("NEW_APPL_FORM_CREATION")){%>
	     <li class=onclick><html:link  action ="applicationformlistpage.do" target='mainFrame'>Application Form </html:link></li>
        <% } %>
        <%if(objUserAccess.hasScreen("NEW_APPL_FORM_PROCESS")){%>
	    <li class=onclick><html:link  action ="applicationprocesslist.do" target='mainFrame'>ApplicationFormProcess</html:link></li>
        <% } %>
        <%if(objUserAccess.hasScreen("CARD_EMBOSSING")){%>
	    <li class=onclick><html:link  action ="cardembossinginitpage.do" target='mainFrame'>Card Embossing</html:link></li>
        <% } %>
        <%if(objUserAccess.hasScreen("PIN_PRINTING")){%>
	     <li class=onclick><html:link  action ="/pinprintinginitpage.do" target='mainFrame'>PIN Printing</html:link></li>
        <% } %>
        <%if(objUserAccess.hasScreen("CARD_DELIVER")){%>
	    <li class=onclick><html:link  action ="carddeliverlistpage.do" target='mainFrame'>Card Deliver</html:link></li>
        <% } %>
        <%if(objUserAccess.hasScreen("APPL_VALIDATION")){%>
	    <li class=onclick><html:link  action ="applvalidationlist.do?method=List" target='mainFrame'>Appl Validation</html:link></li>
        <% } %>
        <%if(objUserAccess.hasScreen("CREDIT_SCORING")){%>
	    <li class=onclick><html:link  action ="creditscoringprocess.do?method=change" target='mainFrame'>Credit Scoring</html:link></li>
        <% } %>
        <%--
        <%if(objUserAccess.hasScreen("CARD_RECEIVED")){%>
	     <li class=onclick><html:link action ="carddeliverlistpage.do?status"  paramScope="request" target='mainFrame'>Card Received</html:link></li>
        <% } %>
         --%>
    </ul>
  </li>
<% } %>


<%if(objUserAccess.hasScreen("NEWCARD_BATCHPROCESS") || objUserAccess.hasScreen("REPLACEMENTCARD_BATCHPROCESS")){%>
	<li class=onclick><a href="#">&nbsp;&nbsp;<b>Card Batch Process</b></a>
		<ul>
			<%if(objUserAccess.hasScreen("NEWCARD_BATCHPROCESS")){%>
				<li class=onclick><html:link action ="newcardbatchprocess.do?method=List"  paramScope="request" target='mainFrame'>New Cards</html:link></li>
			<% } %>
			<%if(objUserAccess.hasScreen("REPLACEMENTCARD_BATCHPROCESS")){%>
				<li class=onclick><html:link action ="replacementcardbatchprocess.do?method=List"  paramScope="request" target='mainFrame'>Replacement Cards</html:link></li>
			<% } %>
			<%if(objUserAccess.hasScreen("CHANGECARD_BATCHPROCESS")){%>
				<li class=onclick><html:link action ="productchangecardbatchprocess.do?method=List"  paramScope="request" target='mainFrame'>Product Change Cards</html:link></li>
			<% } %>
			<%if(objUserAccess.hasScreen("SUPPLEMENTARYCARD_BATCHPROCESS")){%>
				<li class=onclick><html:link action ="supplementarycardbatchprocess.do?method=List"  paramScope="request" target='mainFrame'>Supplementary Cards</html:link></li>
			<% } %>
			<%if(objUserAccess.hasScreen("RENEWALCARD_BATCHPROCESS")){%>
				<li class=onclick><html:link action ="renewalcardbatchprocess.do?method=List"  paramScope="request" target='mainFrame'>Renewal Cards</html:link></li>
			<% } %>
			<%if(objUserAccess.hasScreen("NEWCARDPRODUCT_BATCHPROCESS")){%>
				<li class=onclick><html:link action ="newcardproductbatchprocess.do?method=List"  paramScope="request" target='mainFrame'>New Product Cards</html:link></li>
			<% } %>
			<% if (objUserAccess.hasScreen("INSTANT_CARD")) { %>
				<li class=onclick><a href="#">&nbsp;&nbsp;<b>Anonymous
							Card Gen</b></a>
					<ul>
						<li class=onclick><html:link
								action="searchcardbatchaction.do?method=List"
								paramScope="request" target='mainFrame'>Card Batch</html:link></li>
						<li class=onclick><html:link
								action="searchcardbatchauthaction.do?method=List"
								paramScope="request" target='mainFrame'>Card Batch Authorized</html:link></li>
						<li class=onclick><html:link
								action="searchcardbatchprocessaction.do?method=List"
								paramScope="request" target='mainFrame'>Card Batch Process</html:link></li>

					</ul>
				</li>
			<% } %>
			<%-- <%if(objUserAccess.hasScreen("NEWCARDPRODUCT_BATCHPROCESS")){%>
			<% } %> --%>
				<li class=onclick><html:link action ="cardbatchprocess.do?method=List"  paramScope="request" target='mainFrame'>Batch Process</html:link></li>
		</ul>
	</li>
<% } %>

<%if(objUserAccess.hasScreen("SUPPL_FORM_CREATION")|| objUserAccess.hasScreen("SUPPL_FORM_PROCESS")|| objUserAccess.hasScreen("CARD_RENEWAL_PROCESS")
    ||objUserAccess.hasScreen("CARD_REPLACEMENT_PROCESS")||objUserAccess.hasScreen("CARD_CLOSE_PROCESS")||objUserAccess.hasScreen("PIN_RESENT_PROCESS")
    ||objUserAccess.hasScreen("CARDGRADE_CREATION")||objUserAccess.hasScreen("CARDGRADE_PROCESS")||objUserAccess.hasScreen("CARDLIMIT_ADJUST_PROCESS") || objUserAccess.hasScreen("CARD_CHANGE_PROCESS")){%>
  <li class=onclick><a href="#">&nbsp;&nbsp;<b>Application Forms</b></a>
    <ul><!-- -->
        <%if(objUserAccess.hasScreen("SUPPL_FORM_CREATION")){%>
	     <li class=onclick><html:link  action ="supplementaryformprocess.do?method=addNew" target='mainFrame'>Supplementary Form </html:link></li>
        <% } %>
        <%if(objUserAccess.hasScreen("SUPPL_FORM_PROCESS")){%>
	    <li class=onclick><html:link  action ="supplementaryformlist.do" target='mainFrame'>Supplementary Form Process</html:link></li>
        <% } %>
         <%if(objUserAccess.hasScreen("CARD_RENEWAL_PROCESS")){%>
	    <li class=onclick><html:link  action ="cardrenewalprocesslist?method=processSearch" target='mainFrame'>Card Renewal Process</html:link></li>
        <% } %>
         <%if(objUserAccess.hasScreen("CARD_REPLACEMENT_PROCESS")){%>
	     <li class=onclick><html:link  action ="cardrepprocesslist?method=processSearch" target='mainFrame'>Card Replacement Process</html:link></li>
        <% } %>
         <%if(objUserAccess.hasScreen("CARD_CLOSE_PROCESS")){%>
	     <li class=onclick><html:link  action ="cardcloseprocess.do?method=cardCloseProcessSearch" target='mainFrame'>Card Close Process</html:link></li>
        <% } %>
        <%if(objUserAccess.hasScreen("PIN_RESENT_PROCESS")){%>
	    <li class=onclick><html:link  action ="pinresendprocess.do?method=pinResendProcessSearch" target='mainFrame'>Pin Resend Process</html:link></li>
        <% } %>
       <%-- <%if(objUserAccess.hasScreen("CARDGRADE_CREATION")){
	     <li class=onclick><html:link action ="cardgradeformsetup.do"  paramScope="request" target='mainFrame'>Card Grade Form</html:link></li>
        <% } %>
         <%if(objUserAccess.hasScreen("CARDGRADE_PROCESS")){%>
	     <li class=onclick><html:link action ="cardgradeformlist.do"  paramScope="request" target='mainFrame'>Card Grade Process</html:link></li>
        <% } %> --%> 
        <%if(objUserAccess.hasScreen("CARDLIMIT_ADJUST_PROCESS")){%>
	     <li class=onclick><html:link action ="cardlimitprocess.do?method=cardLimitProcessSearch"  paramScope="request" target='mainFrame'>Card Limit Adjustment Process</html:link></li>
        <% } %>
        <%if(objUserAccess.hasScreen("CARD_CHANGE_PROCESS")){%>
	     <li class=onclick><html:link action ="cardchangeprocess.do?method=processSearch"  paramScope="request" target='mainFrame'>Card Change Process</html:link></li>
        <% } %>
        <%if(objUserAccess.hasScreen("ADD_CARD_PROCESS")){%>
	     <li class=onclick><html:link action ="addcardprocesslist.do?method=List"  paramScope="request" target='mainFrame'>Add Card Process</html:link></li>
        <% } %>
        
    </ul>
  </li>
<% } %>

<%if(objUserAccess.hasScreen("CARD_RECEIVED")|| objUserAccess.hasScreen("CARD_REPLACEMENT")|| objUserAccess.hasScreen("CARD_STOP")
	 || objUserAccess.hasScreen("CARD_ACTIVATE")|| objUserAccess.hasScreen("RESET_PINCOUNT")  || objUserAccess.hasScreen("BILL_ADDR_CHANGE")
	 || objUserAccess.hasScreen("CARD_CLOSE")    || objUserAccess.hasScreen("PIN_RESEND")      || objUserAccess.hasScreen("CARDLIMIT_ADJUST")
	 || objUserAccess.hasScreen("CREDIT_SPLITTING") || objUserAccess.hasScreen("CUSTOMER_SCREEN") || objUserAccess.hasScreen("CARD_CHANGE")
	 || objUserAccess.hasScreen("CARD_RENEWAL") || objUserAccess.hasScreen("TRANX_ENQUIRY") || objUserAccess.hasScreen("CARD_LEVEL_LIMIT")
	 || objUserAccess.hasScreen("ACCOUNT_ADJUSTMENT")){%>
  <li class=onclick><a href="#">&nbsp;&nbsp;<b>Card Management</b></a>
    <ul><!-- -->
    	<%--
        <%if(objUserAccess.hasScreen("CARD_RECEIVED")){%>
	     <li class=onclick><html:link  action ="cardreceivedprocess.do?method=checkSession" target='mainFrame'>Card Received </html:link></li>
        <% } %>
         --%>
        <%if(objUserAccess.hasScreen("CARD_REPLACEMENT")){%>
	    <li class=onclick><html:link  action ="cardreplacementlist.do?method=checkSession" target='mainFrame'>Card Replacement</html:link></li>
        <% } %>
         <%if(objUserAccess.hasScreen("CARD_STOP")){%>
	    <li class=onclick><html:link  action ="cardstopprocess.do?method=checkSession" target='mainFrame'>Card Stop</html:link></li>
        <% } %>
         <%if(objUserAccess.hasScreen("CARD_ACTIVATE")){%>
	    <li class=onclick><html:link  action ="cardactivateprocess.do?method=checkSession" target='mainFrame'>Card Activate</html:link></li>
        <% } %>
         <%if(objUserAccess.hasScreen("RESET_PINCOUNT")){%>
	     <li class=onclick><html:link  action ="resetpincountprocess.do?method=checkSession" target='mainFrame'>Reset PIN</html:link></li>
        <% } %>
         <%if(objUserAccess.hasScreen("BILL_ADDR_CHANGE")){%>
	     <li class=onclick><html:link  action ="billingaddprocess.do?method=checkSession" target='mainFrame'>Billing Address Change</html:link></li>
        <% } %>
         <%if(objUserAccess.hasScreen("CARD_CLOSE")){%>
	     <li class=onclick><html:link  action ="cardcloselist.do?method=checkSession&cardAction=05" target='mainFrame'>Card Close </html:link></li>
        <% } %>
        <%if(objUserAccess.hasScreen("PIN_RESEND")){%>
	    <li class=onclick><html:link  action ="pinresendlist.do?method=checkSession" target='mainFrame'>Pin Resend </html:link></li>
        <% } %>
        <%if(objUserAccess.hasScreen("CARDLIMIT_ADJUST")){%>
	     <li class=onclick><html:link action ="cardlimitlist.do?method=checkSession"  paramScope="request" target='mainFrame'>Card Limit Adjust</html:link></li>
        <% } %>
         <%--
         <%if(objUserAccess.hasScreen("CREDIT_SPLITTING")){%>
	     <li class=onclick><html:link action ="creditsplitpage.do?method=checkSession"  paramScope="request" target='mainFrame'>Credit Splitting</html:link></li>
        <% } %>
         --%>
        <%if(objUserAccess.hasScreen("CUSTOMER_SCREEN")){%>
	     <li class=onclick><html:link action ="customerscreenlistpage.do"  paramScope="request" target='mainFrame'>Customer Screen</html:link></li>
        <% } %>
        <%if(objUserAccess.hasScreen("CARD_CHANGE")){%>
	     <li class=onclick><html:link action ="cardchangepage.do?method=checkSession"  paramScope="request" target='mainFrame'>Card Change</html:link></li>
        <% } %>
        <%if(objUserAccess.hasScreen("ACCOUNT_ADJUSTMENT")){%>
       <li class=onclick><html:link action ="accountadjustmentpage.do?method=checkSession"  paramScope="request" target='mainFrame'>Account Adjustment</html:link></li>
        <% } %>
        <%if(objUserAccess.hasScreen("CARD_RENEWAL")){%>
	     <li class=onclick><html:link action ="cardrenewalpage.do?method=checkSession"  paramScope="request" target='mainFrame'>Card Renewal</html:link></li>
        <% } %>
        <%if(objUserAccess.hasScreen("TRANX_ENQUIRY")){%>
	     <li class=onclick><html:link action ="tranxEnquiryList.do?method=List"  paramScope="request" target='mainFrame'>Transaction Enquiry</html:link></li>
        <% } %>
        <%if(objUserAccess.hasScreen("CARD_LEVEL_LIMIT")){%>
	     <li class=onclick><html:link action ="cardlevellimitlist.do?method=List"  paramScope="request" target='mainFrame'>Card Level Limit Setup</html:link></li>
        <% } %>
        <%if(objUserAccess.hasScreen("FEE_WAIVER")){%>
	     <li class=onclick><html:link action ="feewaiverprocess.do?method=checkSession"  paramScope="request" target='mainFrame'>Fee Waiver</html:link></li>
        <% } %>
        <%if(objUserAccess.hasScreen("INT_WAIVER")){%>
	     <li class=onclick><html:link action ="intwaiverprocess.do?method=checkSession"  paramScope="request" target='mainFrame'>Interest Waiver</html:link></li>
        <% } %>
        <%--
        <% if(objUserAccess.hasScreen("PAY_WAIVER")){%>
	     <li class=onclick><html:link action ="paywaiverprocess.do?method=checkSession"  paramScope="request" target='mainFrame'>Payment Waiver</html:link></li>
        <% } %>
         --%>
        <%if(objUserAccess.hasScreen("ECOM")){%>
	     <li class=onclick><html:link action ="ecomactivateprocess.do?method=checkSession"  paramScope="request" target='mainFrame'>eCom Enable</html:link></li>
        <% } %>
        <%if(objUserAccess.hasScreen("ACC_CHANGE")){%>
        <li class=onclick><html:link action ="accountchangeprocess.do?method=checkSession"  paramScope="request" target='mainFrame'>Account Change</html:link></li>
        <% } %>
        <%if(objUserAccess.hasScreen("NON_RECON_TRANX_ENQUIRY")){%>
        <li class=onclick><html:link action ="nonReconTranxEnquiryList.do?method=List"  paramScope="request" target='mainFrame'>Non Recon Transaction Enquiry</html:link></li>
        <% } %>        
        <%if(objUserAccess.hasScreen("ADD_PRODUCT")){%>
        <li class=onclick><html:link action ="addProductList.do?method=List"  paramScope="request" target='mainFrame'>Add Product</html:link></li>
        <% } %>
    </ul>
  </li>
<% } %>

<%if(objUserAccess.hasScreen("CARDHOLDER_PAYMENT") || objUserAccess.hasScreen("CARDHOLDER_BILL_STATEMENT")){%>
  <li class=onclick><a href="#">&nbsp;&nbsp;<b>Card Accounting</b></a>
    <ul><!-- -->
        <%if(objUserAccess.hasScreen("CARDHOLDER_PAYMENT")){%>
	     <li class=onclick><html:link  action ="cardpaymentprocess.do?method=checkSession" target='mainFrame'>Card Holder Payment </html:link></li>
        <% } %>
        <%if(objUserAccess.hasScreen("CARDHOLDER_BILL_STATEMENT")){%>
        	<li class=onclick><html:link action="cardholderstatement.do?method=checkSession" target='mainFrame'>Card Holder Statement</html:link></li>
        <% } %>
      <!-- <li class=onclick><html:link  action="cardbillingprocess.do?method=checkBllingCycle" target='mainFrame'>Card Billing Process </html:link></li>  --> 
    </ul>   
  </li>
<% } %>

<%if(objUserAccess.hasScreen("CALL_CENTER")||objUserAccess.hasScreen("OPEN_CALLS")){%>
  <li class=onclick><a href="#">&nbsp;&nbsp;<b>CustomerService(CSR)</b></a>
    <ul><!-- -->
        <%if(objUserAccess.hasScreen("CALL_CENTER")){%>
	     <li class=onclick><html:link  action ="csrsearch.do" target='mainFrame'>CallCenter</html:link></li>
        <% } %>
        <%--
        <%if(objUserAccess.hasScreen("OPEN_CALLS")){%>
	     <li class=onclick><html:link  action ="csrcallrecord.do?method=openCalls" target='mainFrame'>Open Calls </html:link></li>
        <% } %>
         --%>
    </ul>
  </li>
<% } %>


<%if(objUserAccess.hasScreen("DIS_TRANX_SEARCH") || objUserAccess.hasScreen("CHARGE_BACK_SEARCH") || objUserAccess.hasScreen("INCOMING_CTF_PROCESS")){%>
  <li class=onclick><a href="#">&nbsp;&nbsp;<b>Dispute Management</b></a>
    <ul>
    	<%--
        <%if(objUserAccess.hasScreen("DISPUTE_CONFIG_MASTER")){%>
	     <li class=onclick><html:link  action ="disputeconfigsetup.do?action=change" target='mainFrame'>Settings</html:link></li>
        <% } %>
        <%if(objUserAccess.hasScreen("DISPUTE_DOCUMENT_SETUP")){%>
	     <li class=onclick><html:link  action ="disputedocumentslist.do?action=preList" target='mainFrame'>Documents Setup </html:link></li>
        <% } %>
        <%if(objUserAccess.hasScreen("DISPUTE_CLAIM_FORM")){%>
	     <li class=onclick><html:link  action ="claimformlistpage.do" target='mainFrame'>Dispute Claim Form </html:link></li>
        <% } %>

        <%if(objUserAccess.hasScreen("WORK_ITEM")){%>
	     <li class=onclick><html:link  action ="workitemlistpage.do" target='mainFrame'>Work Item </html:link></li>
        <% } %>

        <%if(objUserAccess.hasScreen("DISPUTE_DOCUMENT_UPLOAD")){%>
	     <li class=onclick><html:link  action ="documentuploadlist.do?action=preList" target='mainFrame'>Docuemnt Uploads </html:link></li>
        <% } %>

		<li class=onclick><html:link  action ="statisticreportslistpage.do" target='mainFrame'>Statistical Report </html:link></li>	
		<li class=onclick><html:link  action ="" target='mainFrame'>Transaction Report </html:link></li>
		--%>
		
        <% if(objUserAccess.hasScreen("DIS_TRANX_SEARCH")){ %>
	     <li class=onclick><html:link action ="disTranxListPage.do" target='mainFrame'>Create ChargeBacks</html:link></li>
        <% } %>
		
        <% if(objUserAccess.hasScreen("DISPUTE_RESEND")){ %>
	     <li class=onclick><html:link action ="chargeBackResendListPage.do" target='mainFrame'>ChargeBacks Response</html:link></li>
        <% } %>
		
        <% if(objUserAccess.hasScreen("CHARGE_BACK_SEARCH")){ %>
	     <li class=onclick><html:link action ="chargeBackListPage.do" target='mainFrame'>ChargeBacks Status</html:link></li>
        <% } %>
		
        <% if(objUserAccess.hasScreen("DIS_NOT_RECONSILED_TRANX")){ %>
	     <li class=onclick><html:link action ="disNotReconsiledTranxListPage.do" target='mainFrame'>Tranx NOT Reconciled</html:link></li>
        <% } %>
		<%--
        <% if(objUserAccess.hasScreen("INCOMING_CTF_PROCESS")){ %>
	     <li class=onclick><html:link action ="incomingCTFProcessPage.do" target='mainFrame'>Incoming CTF Process</html:link></li>
        <% } %>
         --%>
    </ul>
  </li>
<% } %>


<%if(objUserAccess.hasScreen("RISK_TRANX_SALE")|| objUserAccess.hasScreen("RISK_TRANX_CASH")|| objUserAccess.hasScreen("RISK_TRANX_PERIOD")
    ||objUserAccess.hasScreen("RISK_COUNTRY")||objUserAccess.hasScreen("RISK_TRANX")||objUserAccess.hasScreen("WITHDRAWAL_LIMIT_RULES")||
    objUserAccess.hasScreen("CARDHOLDER_LIMIT_ADJUST")||objUserAccess.hasScreen("ORIGINAL_REQUEST ")||objUserAccess.hasScreen("PHOTOCOPY_REQUEST")||
    objUserAccess.hasScreen("CHARGE_BACK1")|| objUserAccess.hasScreen("CHARGE_BACK2")|| objUserAccess.hasScreen("CARD_ACTIVITY")||
    objUserAccess.hasScreen("USER_ACTIVITY")|| objUserAccess.hasScreen("WRITEOFF_SETUP")){%>
  <li class=onclick><a href="#">&nbsp;&nbsp;<b>Risk Management</b></a>
    <ul><!-- -->
        <%if(objUserAccess.hasScreen("RISK_TRANX_SALE")){%>
	     <!--  <li class=onclick><html:link  action ="risktranxsalelist.do?tranxCode=SALE&method=search" target='mainFrame'>Spending</html:link></li>  -->
        <% } %>
        <%if(objUserAccess.hasScreen("RISK_TRANX_CASH")){%>
	   <!--   <li class=onclick><html:link  action ="risktranxsalelist.do?tranxCode=CASH&method=search" target='mainFrame'>Cash Withdrawal </html:link></li>  -->
        <% } %>
         <%if(objUserAccess.hasScreen("RISK_TRANX_PERIOD")){%>
	     <li class=onclick><html:link  action ="risktranxperiodlist.do" target='mainFrame'>Tranx Period</html:link></li>
        <% } %>
         <%if(objUserAccess.hasScreen("RISK_COUNTRY")){%>
	     <li class=onclick><html:link  action ="riskcountrylist.do?method=search" target='mainFrame'>Risk Country</html:link></li>
        <% } %>
        <%--
         <%if(objUserAccess.hasScreen("RISK_TRANX")){%>
	    <!--   <li class=onclick><html:link  action ="risktranxactionlist.do?method=search" target='mainFrame'>Actions</html:link></li>  -->
        <% } %>
         <%if(objUserAccess.hasScreen("WITHDRAWAL_LIMIT_RULES")){%>
	     <li class=onclick><html:link  action ="withdrawallimitruleslistpage.do" target='mainFrame'>WithdrawalLimit Rules</html:link></li>
        <% } %>
        <%if(objUserAccess.hasScreen("CARDHOLDER_LIMIT_ADJUST")){%>
	    <li class=onclick><html:link  action ="cardholderlimitadjustmentlistpage.do" target='mainFrame'>Temporary Limit Adjustment</html:link></li>
        <% } %>
          --%>
         <%if(objUserAccess.hasScreen("CARD_ACTIVITY")){%>
	     <li class=onclick><html:link  action ="cardactivitylistpage.do" target='mainFrame'>Card Activities</html:link></li>
        <% } %>
        <%--
         <%if(objUserAccess.hasScreen("USER_ACTIVITY")){%>
	       <li class=onclick><html:link  action ="useractivitylistpage.do" target='mainFrame'>User Activities</html:link></li>
        <% } %>
         --%>
         <%if(objUserAccess.hasScreen("WRITEOFF_SETUP")){%>
	       <li class=onclick><html:link  action ="writeoffsetup.do?action=change" target='mainFrame'>WriteOffSetup</html:link></li>
        <% } %>
    </ul>
  </li>
<% } %>

<%if(objUserAccess.hasScreen("DISPATCH_LETTERS")||objUserAccess.hasScreen("LETRER_HISTORY")||objUserAccess.hasScreen("LETTER_APPL_MAP") 
		|| objUserAccess.hasScreen("LETTER_TEMPLATE")){%>
  <li class=onclick><a href="#">&nbsp;&nbsp;<b>Letters</b></a>
    <ul><!-- -->
        <%if(objUserAccess.hasScreen("DISPATCH_LETTERS")){%>
	     <li class=onclick><html:link  action ="dispatchletterlist.do" target='mainFrame'>Letter To Dispatch</html:link></li>
        <% } %>
        <%if(objUserAccess.hasScreen("LETRER_HISTORY")){%>
	     <li class=onclick><html:link  action ="chletterhistlist.do?action=preList" target='mainFrame'>Cardholder Letters History </html:link></li>
        <% } %>
        <%if(objUserAccess.hasScreen("LETTER_APPL_MAP")){%>
            <li class=onclick><html:link  action ="letterapplmapprccess.do?method=change" target='mainFrame'>Letter Application Map</html:link></li>
        <%}%>
        <%if(objUserAccess.hasScreen("LETTER_TEMPLATE")){%>
            <li class=onclick><html:link  action ="lettertemplatelist.do?method=List" target='mainFrame'>Letter Template</html:link></li>
        <%}%>      
    </ul>
  </li>
<% } %>


<%if(objUserAccess.hasScreen("TRANX_REPORT")||objUserAccess.hasScreen("TRANX_LISTING")
  ||objUserAccess.hasScreen("SETTLEMENT_REPORT")||objUserAccess.hasScreen("CARD_VOLUME")
  ||objUserAccess.hasScreen("CARDHOLDER_BILL_STATEMENT")||objUserAccess.hasScreen("BLACKLIST_CARD_REPORT")
  ||objUserAccess.hasScreen("TRANX_DETAILS_REPORT")||objUserAccess.hasScreen("CARDS_WRITEOFF_REPORT")){%>
  <li class=onclick><a href="#">&nbsp;&nbsp;<b>Reports</b></a>
    <ul><!-- -->
    	<%if(objUserAccess.hasScreen("TRANX_HISTORY")){%>
	     <li class=onclick><html:link  action ="GenerateReportServlet?hdOpCode=3&mode=List&REPORT_CODE=TRNXHIS" target='mainFrame'>Tranx History</html:link></li>
        <% } %>
    	<%if(objUserAccess.hasScreen("TRANX_GL_HISTORY")){%>
	     <li class=onclick><html:link  action ="GenerateReportServlet?hdOpCode=3&mode=List&REPORT_CODE=TRNXGLHIS" target='mainFrame'>Tranx GL History</html:link></li>
        <% } %>
    	<%if(objUserAccess.hasScreen("TRANX_RECON_HISTORY")){%>
	     <li class=onclick><html:link  action ="GenerateReportServlet?hdOpCode=3&mode=List&REPORT_CODE=TRNXRECONHIS" target='mainFrame'>Tranx Recon History</html:link></li>
        <% } %>
    	<%if(objUserAccess.hasScreen("TRANX_SUMM_BYCP")){%>
	     <li class=onclick><html:link  action ="GenerateReportServlet?hdOpCode=3&mode=List&REPORT_CODE=TRNXSUMMBYCP" target='mainFrame'>Tranx Summary By CardProduct</html:link></li>
        <% } %>
    	<%if(objUserAccess.hasScreen("TRANX_SUMM_BYDATE")){%>
	     <li class=onclick><html:link  action ="GenerateReportServlet?hdOpCode=3&mode=List&REPORT_CODE=TRNXSUMMBYDATE" target='mainFrame'>Tranx Summary By Date</html:link></li>
        <% } %>
    	<%if(objUserAccess.hasScreen("TRANX_SUMM_BYCNO")){%>
	     <li class=onclick><html:link  action ="GenerateReportServlet?hdOpCode=3&mode=List&REPORT_CODE=TRNXSUMMBYCNO" target='mainFrame'>Tranx Summary By CardNumber</html:link></li>
        <% } %>
    	<%if(objUserAccess.hasScreen("LIST_ISSUED_CARDS")){%>
	     <li class=onclick><html:link  action ="GenerateReportServlet?hdOpCode=3&mode=List&REPORT_CODE=LISTISSUEDCARDS" target='mainFrame'>List Issued Cards</html:link></li>
        <% } %>
    	<%if(objUserAccess.hasScreen("CUSTOMER_LIST")){%>
	     <li class=onclick><html:link  action ="GenerateReportServlet?hdOpCode=3&mode=List&REPORT_CODE=CUSTLIST" target='mainFrame'>Customer List</html:link></li>
        <% } %>
    	<%if(objUserAccess.hasScreen("SYN_REP_CARD_TRANX")){%>
	     <li class=onclick><html:link  action ="GenerateReportServlet?hdOpCode=3&mode=List&REPORT_CODE=SYNREPCARDTRANX" target='mainFrame'>Synthesis Report Card Tranx</html:link></li>
        <% } %>
    	<%if(objUserAccess.hasScreen("EXPIRED_CARD_LIST")){%>
	     <li class=onclick><html:link  action ="GenerateReportServlet?hdOpCode=3&mode=List&REPORT_CODE=EXPIREDCARDLIST" target='mainFrame'>Expired Card list</html:link></li>
        <% } %>
    	<%if(objUserAccess.hasScreen("APP_STATUS")){%>
	     <li class=onclick><html:link  action ="GenerateReportServlet?hdOpCode=3&mode=List&REPORT_CODE=APPSTATUS" target='mainFrame'>Applicatin Status</html:link></li>
        <% } %>
    	<%if(objUserAccess.hasScreen("CARD_HOLDER_STA")){%>
	     <li class=onclick><html:link  action ="GenerateReportServlet?hdOpCode=3&mode=List&REPORT_CODE=CARDHOLDERSTATISTICS" target='mainFrame'>Card Holder Statistics</html:link></li>
        <% } %>
    	<%if(objUserAccess.hasScreen("CANCELLED_CARD_STA")){%>
	     <li class=onclick><html:link  action ="GenerateReportServlet?hdOpCode=3&mode=List&REPORT_CODE=CANCELCARDSSTATISTICS" target='mainFrame'>Canceled Cards Statistics</html:link></li>
        <% } %>
       <%if(objUserAccess.hasScreen("CARDS_WRITEOFF_REPORT")){%>
	     <li class=onclick><html:link  action ="GenerateReportServlet?hdOpCode=3&mode=List&REPORT_CODE=CARDSWRITEOFF" target='mainFrame'>Cards Write Offs </html:link></li>
        <% } %> 
        <%if(objUserAccess.hasScreen("TRANX_FEE_HISTORY")){%>
	     <li class=onclick><html:link  action ="GenerateReportServlet?hdOpCode=3&mode=List&REPORT_CODE=TRNXFEEHIS" target='mainFrame'>Tranx & Fee History</html:link></li>
        <% } %>
        <%if(objUserAccess.hasScreen("RENEWAL")){%>
	     <li class=onclick><html:link  action ="GenerateReportServlet?hdOpCode=3&mode=List&REPORT_CODE=RENEWAL" target='mainFrame'>Card Renewal</html:link></li>
        <% } %>
	     <li class=onclick><html:link  action ="GenerateReportServlet?hdOpCode=3&mode=List&REPORT_CODE=BATCHPROCESS" target='mainFrame'>Batch Process</html:link></li>
        <%--
        <%if(objUserAccess.hasScreen("TRANX_REPORT")){%>
	     <li class=onclick><html:link  action ="GenerateReportServlet?hdOpCode=3&mode=List&REPORT_CODE=TR" target='mainFrame'>Transaction Report</html:link></li>
        <% } %>
        <%if(objUserAccess.hasScreen("TRANX_LISTING")){%>
	     <li class=onclick><html:link  action ="GenerateReportServlet?hdOpCode=3&mode=List&REPORT_CODE=TNL" target='mainFrame'>Transaction Listing</html:link></li>
        <% } %>
         <%if(objUserAccess.hasScreen("SETTLEMENT_REPORT")){%>
	     <li class=onclick><html:link  action ="GenerateReportServlet?hdOpCode=3&mode=List&REPORT_CODE=STL" target='mainFrame'>Settlement Report</html:link></li>
        <% } %>
         <%if(objUserAccess.hasScreen("CARD_VOLUME")){%>
	     <li class=onclick><html:link  action ="GenerateReportServlet?hdOpCode=3&mode=List&REPORT_CODE=TNL" target='mainFrame'>Card Volume</html:link></li>
        <% } %>
         <%if(objUserAccess.hasScreen("CARDHOLDER_BILL_STATEMENT")){%>
	     <li class=onclick><html:link  action ="GenerateReportServlet?hdOpCode=3&mode=List&REPORT_CODE=CBS" target='mainFrame'>Cardholder Billing Statement</html:link></li>
        <% } %>
         <%if(objUserAccess.hasScreen("BLACKLIST_CARD_REPORT")){%>
	     <li class=onclick><html:link  action ="GenerateReportServlet?hdOpCode=3&mode=List&REPORT_CODE=BLR" target='mainFrame'>Black List Report</html:link></li>
        <% } %>
         <%if(objUserAccess.hasScreen("TRANX_DETAILS_REPORT")){%>
	     <li class=onclick><html:link  action ="GenerateReportServlet?hdOpCode=3&mode=List&REPORT_CODE=TND" target='mainFrame'>Transaction Detail Report</html:link></li>
        <% } %>
         --%>      
    </ul>
  </li>
<% } %>

<%if(objUserAccess.hasScreen("USER_ACTIVITIES") || objUserAccess.hasScreen("ERROR_LOG")){%>
    <li class=onclick><A href="#" target=mainFrame>&nbsp;&nbsp;<b>Log</b></a>
        <ul>
			<!-- -->
        <%if(objUserAccess.hasScreen("USER_ACTIVITIES")){%>
            <li class=onclick><html:link  action ="useractivitieslist.do?method=List" target='mainFrame'>User Activities</html:link></li>
        <%}%>
        <%if(objUserAccess.hasScreen("ERROR_LOG")){%>
           <li class=onclick><html:link  action ="errorloglist.do?method=List" target='mainFrame'>Error Log</html:link></li>
        <%}%>
        </ul>
    </li>
<%}%>


<%if(objUserAccess.hasScreen("SMS_SERVICE")){%>
  <li class=onclick><a href="#">&nbsp;&nbsp;<b>Services</b></a>
    <ul><!-- -->
        <%if(objUserAccess.hasScreen("SMS_SERVICE")){%>
	     <li class=onclick><html:link  action ="smsservicelistpage.do" target='mainFrame'>SMSService</html:link></li>
        <% } %>
        
    </ul>
  </li>
<% } %>

<% if (objUserAccess.hasScreen("DELINQUENCY_POLICY")
						|| objUserAccess.hasScreen("DELINQUENCY_FEE")
						|| objUserAccess.hasScreen("DELINQUENCY_NOTIFICATION")
						|| objUserAccess.hasScreen("WRITEOFF_CARDS")) { %>
	<li class=onclick><a href="#">&nbsp;&nbsp;<b>Collection Management</b></a>
			<ul>
				<% if (objUserAccess.hasScreen("DELINQUENCY_POLICY")) { %>
				<li class=onclick><html:link
						action="delinquencypolicylist.do?method=List" paramScope="request"
						target='mainFrame'>Delinquency Policy</html:link></li>
				<% } %>
				<% if (objUserAccess.hasScreen("DELINQUENCY_FEE")) { %>
				<li class=onclick><html:link
						action="delinquencyfeesetuplist.do?method=List" paramScope="request"
						target='mainFrame'>Delinquency Fee Setup</html:link></li>
				<% } %>
				<% if (objUserAccess.hasScreen("DELINQUENCY_NOTIFICATION")) { %>
				<li class=onclick><html:link
						action="delinquencynotificationsetuplist.do?method=List" paramScope="request"
						target='mainFrame'>Delinquency Notification Setup</html:link></li>
				<% } %>
				<% if(objUserAccess.hasScreen("WRITEOFF_CARDS")){ %>
       	       <li class=onclick><html:link  action ="writeoffcardslistpage.do" target='mainFrame'>WriteOffCards</html:link></li>
               <% } %>
               <% if(objUserAccess.hasScreen("COLLECTION_CONFIG")){ %>
       	       <li class=onclick><html:link  action ="collectionconfigprocess.do?method=get" target='mainFrame'>Collection Config</html:link></li>
               <% } %>
                <% if(objUserAccess.hasScreen("AGENT_SETUP")){ %>
               <li class=onclick><html:link  action ="collectionagentsetuplist.do?method=List" target='mainFrame'>Agent Setup</html:link></li>
               <% } %>
                <% if(objUserAccess.hasScreen("COLLECTION_AGEING")){ %>
               <li class=onclick><html:link  action ="collectionageinglist.do" target='mainFrame'>Collection Ageing</html:link></li>
               <% } %>
                <% if(objUserAccess.hasScreen("COLLECTION_AGEING_ACTION")){ %>
               <li class=onclick><html:link  action ="collectionageingactionlist.do?method=List" target='mainFrame'>Collection Ageing Action</html:link></li>
               <% } %>
                <% if(objUserAccess.hasScreen("COLLECTION_ACCOUNT_DETAILS")){ %>
               <li class=onclick><html:link  action ="collectionaccountdetailslist.do?method=List" target='mainFrame'>Collection Account Details</html:link></li>
               <% } %>
			</ul>
	</li>
<% } %>
<%--
<% if(objUserAccess.hasScreen("APPLICATIONFORM_UPLOAD") || objUserAccess.hasScreen("BLACKLISTCARD_UPLOAD") ||objUserAccess.hasScreen("CARDEMBOSSING_UPLOAD") || objUserAccess.hasScreen("PINPRINTING_UPLOAD") || objUserAccess.hasScreen("CARDDELIVER_UPLOAD") || objUserAccess.hasScreen("CURRENCY_UPLOAD")){%>
  <li class=onclick><a href="#">&nbsp;&nbsp;<b>Upload</b></a>
    <ul><!-- -->
                <%if(objUserAccess.hasScreen("APPLICATIONFORM_UPLOAD")){%>
       	       <li class=onclick><html:link  action ="uploadprocess.do?method=List&uploadType=1" target='mainFrame'>Application Forms </html:link></li>
               <% } %>
               <%if(objUserAccess.hasScreen("BLACKLISTCARD_UPLOAD")){%>
	              <li class=onclick><html:link  action ="uploadprocess.do?method=List&uploadType=3" target='mainFrame'>BlackList Cards</html:link></li>
               <% } %>
               <%--
               <%if(objUserAccess.hasScreen("CARDEMBOSSING_UPLOAD")){%>
	              	       <li class=onclick><html:link  action ="uploadprocess.do?method=List&uploadType=4" target='mainFrame'>Card Embossing</html:link></li>
               <% } %>
		<%if(objUserAccess.hasScreen("PINPRINTING_UPLOAD")){%>
       	       <li class=onclick><html:link  action ="uploadprocess.do?method=List&uploadType=5" target='mainFrame'>Cards Printing</html:link></li>
               <% } %>	
               <%if(objUserAccess.hasScreen("CARDDELIVER_UPLOAD")){%>
	              	       <li class=onclick><html:link  action ="uploadprocess.do?method=List&uploadType=6" target='mainFrame'>Card Delivered</html:link></li>
               <% } %>
               <%if(objUserAccess.hasScreen("CURRENCY_UPLOAD")){%>
	              	       <li class=onclick><html:link  action ="uploadprocess.do?method=List&uploadType=2" target='mainFrame'>Currency Rate</html:link></li>
               <% } %>
         
        
        
    </ul>
  </li>
<% } %>
--%>
	<li class=onclick><a href="#">&nbsp;&nbsp;<b>Inventory</b></a>
    	<ul><!-- -->
			<li class=onclick><html:link  action ="stocksetuplist.do?method=list" target='mainFrame'>Stock Setup</html:link></li>
			<li class=onclick><html:link  action ="inventorysearch.do?method=order" target='mainFrame'>Order Stock</html:link></li>
			<li class=onclick><html:link  action ="inventorysearch.do?method=authorized" target='mainFrame'>Athorized Order</html:link></li>
			<li class=onclick><html:link  action ="inventorysearch.do?method=dispatch" target='mainFrame'>Dispatch Stock</html:link></li>
			<li class=onclick><html:link  action ="inventorysearch.do?method=received" target='mainFrame'>Received Stock</html:link></li>
			<li class=onclick><html:link  action ="inventorysearch.do?method=return" target='mainFrame'>Return Stock</html:link></li>
			<li class=onclick><html:link  action ="orderlist.do?method=List" target='mainFrame'>Order List</html:link></li>
			<li class=onclick><html:link  action ="lowlevelqty.do" target='mainFrame'>Low Level Qty</html:link></li>
			<li class=onclick><html:link  action ="viewhistory.do?method=List" target='mainFrame'>View History</html:link></li>
        </ul>
    </li>
  <ul> <li class=onclick><html:link  action ="passwordchangesetup.do" target='mainFrame'>Password Change</html:link></li></ul>

</UL>
</BODY>
</html:html>