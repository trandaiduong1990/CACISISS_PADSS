 //######For Card Split Calculations  ####################//


/*this Function is used for Calculating the NewRatio Limit when ever key 
   in the  NewLimit value in text box*/
  
 function limitToRatio(field1,field2) {
   
	 var thisForm = document.creditSplitForm;
	 var actualCreditLimit = thisForm.elements["creditLimit"].value;
	 var calnewCreditRatio;
	 var keyinCreditLimit = thisForm.elements[field1].value
	    
	     if(parseInt(keyinCreditLimit)<1){
                 alert("Please enter NewLimit  greater than zero!");
		         return false;
            }else {
                calnewCreditRatio = (keyinCreditLimit*100/actualCreditLimit);
	            thisForm.elements[field2].value = calnewCreditRatio;
	          }
	
	// alert("this alert method getting the form values" + document.creditSplitForm.elements["limitForm0.cardNumber"].value);
	 
 return true;
 } 
/*this Function is used for Calculating the NewLimit when ever key 
   in the NewRatioLimit  value in text box*/
 function ratioToLimit(field1,field2) {
   
	 var thisForm = document.creditSplitForm;
	 var actualCreditLimit = thisForm.elements["creditLimit"].value;
	 var calnewCreditLimit;
	 var keyinCreditLimitRatio = thisForm.elements[field1].value
	     if((parseInt(keyinCreditLimitRatio)<1) || (parseInt(keyinCreditLimitRatio)>100)){
             alert("Please enter NewLimitRatio%  minimum 100 and greater than zero");
		     return false;
           }else {
               calnewCreditLimit = (keyinCreditLimitRatio*actualCreditLimit/100);
	           thisForm.elements[field2].value = calnewCreditLimit;
	    }
	 return true;
 } 


/*this Function is used for Calculating the NewCashLimitRatio  when ever key 
   in the  NewCashLimit value in text box*/
  
 function cashLimitToRatio(field1,field2) {
   
	 var thisForm = document.creditSplitForm;
	 var actualCashLimit = thisForm.elements["cashLimit"].value;
	 var calnewCashRatio;
	 var keyinCashLimit = thisForm.elements[field1].value
	    
	     if(parseInt(keyinCashLimit)<1){
                 alert("Please enter NewCashLimit  greater than zero!");
		         return false;
            }else {
                calnewCashRatio = (keyinCashLimit*100/actualCashLimit);
	            thisForm.elements[field2].value = calnewCashRatio;
	          }
	
	// alert("this alert method getting the form values" + document.creditSplitForm.elements["limitForm0.cardNumber"].value);
	 
 return true;
 } 
/*this Function is used for Calculating the NewLimit when ever key 
   in the NewRatioLimit  value in text box*/
 function cashRatioToLimit(field1,field2) {
   
	 var thisForm = document.creditSplitForm;
	 var actualCashLimit = thisForm.elements["cashLimit"].value;
	 var calnewCashLimit;
	 var keyinCashLimitRatio = thisForm.elements[field1].value
	     if((parseInt(keyinCashLimitRatio)<1) || (parseInt(keyinCashLimitRatio)>100)){
             alert("Please enter NewCashLimitRatio%  minimum 100 and greater than zero");
		     return false;
           }else {
               calnewCashLimit = (keyinCashLimitRatio*actualCashLimit/100);
	           thisForm.elements[field2].value = calnewCashLimit;
	    }
	 return true;
 } 



/*this Function is used for Checking the all the fields NewLimitRatio 
   NewRatio it must me lesst than 100% */
  
function checkTotalRatio(action1){
   
     var thisForm = document.creditSplitForm;
	 var newRatioLimit0;
	 var newRatioLimit1;
	 var newRatioLimit2;
	 var newRatioLimit3;
	 var newRatioLimit4;
	 var totalLimitRatio =0;
				
	for( var i = 0; i < thisForm.elements.length; i++)
	{
	 
	   if(thisForm.elements[i].name == "limitForm0.newLimitRatio"){
		   newRatioLimit0 = thisForm.elements["limitForm0.newLimitRatio"].value
		   totalLimitRatio = totalLimitRatio + parseInt(newRatioLimit0)
	    }
        if(thisForm.elements[i].name == "limitForm1.newLimitRatio"){
		   newRatioLimit1 = thisForm.elements["limitForm1.newLimitRatio"].value
		    totalLimitRatio = totalLimitRatio + parseInt(newRatioLimit1)
	    }
	     if(thisForm.elements[i].name == "limitForm2.newLimitRatio"){
		   newRatioLimit2 = thisForm.elements["limitForm2.newLimitRatio"].value
		    totalLimitRatio = totalLimitRatio + parseInt(newRatioLimit2)
	    }
	     if(thisForm.elements[i].name == "limitForm3.newLimitRatio"){
		   newRatioLimit3 = thisForm.elements["limitForm3.newLimitRatio"].value
		    totalLimitRatio = totalLimitRatio + parseInt(newRatioLimit3)
	    }
	    if(thisForm.elements[i].name == "limitForm4.newLimitRatio"){
		   newRatioLimit4 = thisForm.elements["limitForm4.newLimitRatio"].value
		    totalLimitRatio = totalLimitRatio + parseInt(newRatioLimit4)
	    }
    }
	  
   if(parseInt(totalLimitRatio)<100){
	    alert("Total of all the NewLimitRatio%  must be 100 or greater than 100 ");
	     return false; 
	     } else
	     {
	       document.forms[0].method.value=action1;
	       document.forms[0].submit();
	     } 
   }
