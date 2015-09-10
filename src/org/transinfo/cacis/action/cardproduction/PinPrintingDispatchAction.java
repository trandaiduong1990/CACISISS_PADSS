package org.transinfo.cacis.action.cardproduction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.action.BaseDispatchAction;
import org.transinfo.cacis.common.CardEncryption;
import org.transinfo.cacis.controller.cardproduction.CardEmbossingManager;
import org.transinfo.cacis.controller.cardproduction.PinPrintingManager;
import org.transinfo.cacis.controller.key.KeyIndexManager;
import org.transinfo.cacis.controller.settings.CardProductManager;
import org.transinfo.cacis.dto.cardproduction.ApplicationProcessDto;
import org.transinfo.cacis.dto.cardproduction.CardsDto;
import org.transinfo.cacis.dto.cardproduction.PinPrintingSearchDto;
import org.transinfo.cacis.dto.settings.CardProductDto;
import org.transinfo.cacis.formbean.cardproduction.PINMailedSuccess;
import org.transinfo.cacis.formbean.cardproduction.PinPrintingSearch;
import org.transinfo.cacis.util.PINMailer;

@SuppressWarnings({"deprecation", "unchecked" })
public class PinPrintingDispatchAction extends BaseDispatchAction {
	
	private Logger logger = Logger.getLogger(PinPrintingDispatchAction.class);

	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {

		ActionErrors errors = null;
		
		String userId = (String)request.getSession().getAttribute("USERID");
		String issuerId = (String)request.getSession().getAttribute("ISSUER");
		
		// Token Validation
		if (!isTokenValid(request)) {
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.duplicate"));
			saveErrors(request, errors);
			return mapping.findForward("token");
		}

		// Form Validations
		PinPrintingSearch objForm = (PinPrintingSearch) form;

		// Dto Creation
		PinPrintingSearchDto objDto = new PinPrintingSearchDto();

		try {
			BeanUtils.copyProperties(objDto, objForm);

		}catch (Exception e) {
			System.out
					.println("Error converting to form bean in PinPrintingDispatchAction save method: "
							+ e);
			throw new TPlusException(
					"Could not populate the form bean in PinPrintingDispatchAction save method: "
							+ e);
		}

		// Action Execution
		CardEmbossingManager objCardEmbossingManager = new CardEmbossingManager();
		PinPrintingManager objPinPrintingManager = new PinPrintingManager();
		KeyIndexManager objKeyIndexManager = new KeyIndexManager();
		CardProductManager objCardProductManager = new CardProductManager();
		PINMailedSuccess objPinMailedSuccess = null;
		ArrayList succesCards = new ArrayList();
		StringBuilder genCardNos = new StringBuilder();
		Map ppkProductMap = new HashMap();
		
		StringBuffer joinSerialNos = new StringBuffer();
		String selectPinPrintingSerialNos[] = objDto.getSelectedPinPrintSerialNo();
		String ppkIndex = "";
		
		
		// check all the card products have PPK values
		try{

			for (int i = 0; i < selectPinPrintingSerialNos.length; i++) {
				joinSerialNos.append("'");
				joinSerialNos.append(selectPinPrintingSerialNos[i]);
				joinSerialNos.append("',");
			}
			
			ppkProductMap  = objKeyIndexManager.checkPPKAgainstCardProducts(issuerId, "PPK", joinSerialNos.toString().substring(0, (joinSerialNos.toString().length() - 1)));
			if(ppkProductMap.isEmpty()){
				errors = new ActionErrors();
				errors.add("Error", new ActionError("error.cardproductsnoppk"));
				saveErrors(request, errors);
				return mapping.findForward("fail");
			}
		}catch (Exception e) {
			System.out.println("Error on checking PPK for card products " + e);
			throw new TPlusException("Error on checking PPK for card products "+ e);
		}
		
		
		for (int i = 0; i < selectPinPrintingSerialNos.length; i++) {
			String pinPrinSerialNo = selectPinPrintingSerialNos[i];
			String cardNo = "";
			String cardNoMailer = "";
			try{
				// get Card object
				CardsDto objCardsDto = objPinPrintingManager.getCard(pinPrinSerialNo);
				cardNo = String.valueOf(objCardsDto.getCardNumber());
				logger.error("PIN Print cardNo :: " + cardNo);
				
				cardNoMailer = CardEncryption.decrypt(objCardsDto.getEncryptedCardNo());
				logger.error("cardNoMailer :: " + cardNoMailer);
				
				// get PPK index from HashMap
				ppkIndex = String.valueOf(ppkProductMap.get(objCardsDto.getCardProductId()));
				logger.error("ppkIndex :: " + ppkIndex);
				
				// get Customer object
				ApplicationProcessDto objApplicationProcessDto = objCardEmbossingManager.getCustomerByCusId(objCardsDto.getCustomerId());
				// get card product object
				CardProductDto objCardProductDto = objCardProductManager.getCardProductDto(objCardsDto.getCardProductId());
				
				boolean pinPrint = cardPINPrint(cardNoMailer, objApplicationProcessDto.getEmbossingName(), objCardsDto.getPinBlock(), ppkIndex);

				logger.error("PIN Print cardNo Result :: " + pinPrint);
				
				if(pinPrint){
					boolean updateRes = objPinPrintingManager.updateObjects(pinPrinSerialNo, userId);
					if(updateRes){
						objPinMailedSuccess = new PINMailedSuccess();
						objPinMailedSuccess.setMaskedCardNo(objCardsDto.getMaskedCardNo());
						objPinMailedSuccess.setCustName(objApplicationProcessDto.getCustomerName());
						objPinMailedSuccess.setCardExpDate(objCardsDto.getCardExpDate());
						objPinMailedSuccess.setCardProduct(objCardProductDto.getCardProductName());
						
						genCardNos.append("'");
						genCardNos.append(cardNo);
						genCardNos.append("',");
						
						succesCards.add(objPinMailedSuccess);
					}
				}
			}catch (Exception e) {
				System.out.println(e);
			}
			
			
		}
		
		if(succesCards.size() > 0){
			errors = new ActionErrors();
			if(succesCards.size() == selectPinPrintingSerialNos.length){
				errors.add("Error", new ActionError("error.cardpinmailedsuccess"));
			}else{
				errors.add("Error", new ActionError("error.cardpinmailedpartial"));
			}
			saveErrors(request, errors);
			
			objForm.setSuccessCardsList(succesCards);
			objForm.setCardNos(genCardNos.toString().substring(0, (genCardNos.toString().length() - 1)));
			
			return mapping.findForward("reportpage");
			
		}else{
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.cardpinmailedfailed"));
			saveErrors(request, errors);
			return mapping.findForward("fail");
		}
	}
	
    private boolean cardPINPrint(String cardNo, String nameOnCard, String pinBlock, String ppk) throws Exception{
    	boolean result = false;

        try{
	        
	        PINMailer pinMailer = new PINMailer();
	    	result = pinMailer.printPINMailer(cardNo, nameOnCard, pinBlock, ppk);
	    	//result = true;
	    	
        }catch(Exception ex){
        	System.out.println(ex);
			logger.error(new Object(),ex);
        }
    	
    	return result;
    }

}