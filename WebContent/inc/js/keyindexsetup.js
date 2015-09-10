function disTransactionChannel(){
	//alert('Test');
	var x = document.getElementById("keyType");
	var rc = document.getElementById("transactionChannel");
	
	//alert(x.value);
	//alert(rc);
	
	if(x.value != '' && x.value == 'PPK'){
		rc.disabled=false;
	}else{
		rc.disabled=true;
	}
	
}