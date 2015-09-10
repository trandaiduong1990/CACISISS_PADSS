<script language="JavaScript">
	function insertChargeBack(action){
		
	    document.forms[0].method.value=action;
	    
	    var r=confirm('<bean:message key ="aresuretodelete"/>');
	    if (r==true)
	    {
	    	document.forms[0].submit();
	    }
	    
	}       
</script>