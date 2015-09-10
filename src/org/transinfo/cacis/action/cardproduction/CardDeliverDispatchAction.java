package org.transinfo.cacis.action.cardproduction;

import java.util.ArrayList;

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
import org.transinfo.cacis.controller.cardproduction.CardDeliverManager;
import org.transinfo.cacis.controller.cardproduction.CardEmbossingManager;
import org.transinfo.cacis.controller.settings.CardProductManager;
import org.transinfo.cacis.dto.cardproduction.ApplicationProcessDto;
import org.transinfo.cacis.dto.cardproduction.CardDeliverSearchDto;
import org.transinfo.cacis.dto.cardproduction.CardsDto;
import org.transinfo.cacis.dto.settings.CardProductDto;
import org.transinfo.cacis.formbean.cardproduction.CardDeliverSearch;
import org.transinfo.cacis.formbean.cardproduction.CardDeliverSuccess;

@SuppressWarnings({"deprecation", "unchecked"})
public class CardDeliverDispatchAction extends BaseDispatchAction {
	
	private Logger logger = Logger.getLogger(CardDeliverDispatchAction.class);

	// this for CardDeliver Link
	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {

		ActionErrors errors = null;

		String userId = (String)request.getSession().getAttribute("USERID");

		// Token Validation
		if (!isTokenValid(request)) {
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.duplicate"));
			saveErrors(request, errors);
			return mapping.findForward("token");
		}

		// Form Validations

		CardDeliverSearch objForm = (CardDeliverSearch) form;
		CardDeliverSearchDto objDto = new CardDeliverSearchDto();

		try {
			BeanUtils.copyProperties(objDto, objForm);

		} catch (Exception e) {
			System.out
					.println("Error converting to form bean in CardDeliverDispatchAction save mehod: "
							+ e.getMessage());
			throw new TPlusException(
					"Could not populate the form bean in CardDeliverDispatchAction save method: "
							+ e);
		}

		// Action Execution
		CardDeliverManager objManager = new CardDeliverManager();
		CardEmbossingManager objCardEmbossingManager = new CardEmbossingManager();
		CardProductManager objCardProductManager = new CardProductManager();
		
		CardDeliverSuccess objCardDeliverSuccess = null;
		ArrayList succesCards = new ArrayList();
		StringBuilder genCardNos = new StringBuilder();
		
		String selectCardDeliverSerialNos[] = objDto.getSelectedCardDeliverSerialNos();
		
		for (int i = 0; i < selectCardDeliverSerialNos.length; i++) {
			String cardDeliverSerialNo = selectCardDeliverSerialNos[i];
			String cardNo = "";
			try{
				// get Card object
				CardsDto objCardsDto = objManager.getCard(cardDeliverSerialNo);
				cardNo = String.valueOf(objCardsDto.getCardNumber());
				// get Customer object
				ApplicationProcessDto objApplicationProcessDto = objCardEmbossingManager.getCustomerByCusId(objCardsDto.getCustomerId());
				// get card product object
				CardProductDto objCardProductDto = objCardProductManager.getCardProductDto(objCardsDto.getCardProductId());
				
				boolean updateRes = objManager.updateObjects(cardDeliverSerialNo, userId);
				if(updateRes){
					objCardDeliverSuccess = new CardDeliverSuccess();
					objCardDeliverSuccess.setMaskedCardno(objCardsDto.getMaskedCardNo());
					objCardDeliverSuccess.setCustName(objApplicationProcessDto.getCustomerName());
					objCardDeliverSuccess.setCardExpDate(objCardsDto.getCardExpDate());
					objCardDeliverSuccess.setCardProduct(objCardProductDto.getCardProductName());
					
					genCardNos.append("'");
					genCardNos.append(cardNo);
					genCardNos.append("',");
					
					succesCards.add(objCardDeliverSuccess);
				}
			}catch (Exception e) {
				System.out.println(e);
				logger.error(new Object(), e);
			}
			
			
		}
		
		if(succesCards.size() > 0){
			errors = new ActionErrors();
			if(succesCards.size() == selectCardDeliverSerialNos.length){
				errors.add("Error", new ActionError("error.carddeliveredsuccess"));
			}else{
				errors.add("Error", new ActionError("error.carddeliveredpartial"));
			}
			saveErrors(request, errors);
			
			objForm.setSuccessCardsList(succesCards);
			objForm.setCardNos(genCardNos.toString().substring(0, (genCardNos.toString().length() - 1)));
			
			return mapping.findForward("reportpage");
			
		}else{
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.carddelibverfailed"));
			saveErrors(request, errors);
			return mapping.findForward("fail");
		}
	
	}

	// this for CardReceivedConfirm Link to set cards status to active
	public ActionForward received(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {

		ActionErrors errors = null;

		// Token Validation
		if (!isTokenValid(request)) {
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.duplicate"));
			saveErrors(request, errors);
			return mapping.findForward("receivesucc");
		}

		// Form Validations

		CardDeliverSearch objForm = (CardDeliverSearch) form;
		/*
		 * errors = objForm.validate(mapping,request);
		 * 
		 * if(errors!=null && !errors.isEmpty()) { saveErrors(request,errors);
		 * return mapping.getInputForward(); }
		 */
		// Dto Creation
		CardDeliverSearchDto objDto = new CardDeliverSearchDto();

		try {
			BeanUtils.copyProperties(objDto, objForm);

		} catch (Exception e) {
			System.out
					.println("Error converting to form bean in CardDeliverDispatchAction received mehod:: "
							+ e.getMessage());
			throw new TPlusException(
					"Could not populate the form bean in CardDeliverDispatchAction received mehod:: "
							+ e);
		}

		// Action Execution
		CardDeliverManager objManager = new CardDeliverManager();
		boolean boolSave = objManager.received(objDto);
		// boolean boolSave =true;

		if (boolSave) {

			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.saveSuccess"));
			saveErrors(request, errors);

		} else {

			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.savefailed"));
			saveErrors(request, errors);

		}
		// Success
		resetToken(request);
		objForm.getPreListData();
		return mapping.findForward("receivesucc");
	}

}
