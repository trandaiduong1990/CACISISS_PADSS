function onFormSub(frmName){
	
	if(!onSubmitValidation(frmName.creditFinanceCharge, 3, 2)){ return false; }
	if(!onSubmitValidation(frmName.latePaymentFee, 3, 2)){ return false; }
	if(!onSubmitValidation(frmName.minRepaymentAmt, 3, 2)){ return false; }
	if(!onSubmitValidation(frmName.cashFinaceCharge, 3, 2)){ return false; }
	if(!onSubmitValidation(frmName.cashChargeAmt, 3, 2)){ return false; }
	//if(!onSubmitValidation(frmName.maxCashTranx, 3, 2)){ return false; }
	//if(!onSubmitValidation(frmName.maxCashDueTranx, 3, 2)){ return false; }
	if(!onSubmitValidation(frmName.overLimitCharge, 3, 2)){ return false; }
	if(!onSubmitValidation(frmName.tranxFee, 3, 2)){ return false; }
	
	return true;
	
}