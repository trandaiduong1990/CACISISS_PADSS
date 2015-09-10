package org.transinfo.cacis.action.customerservice;

import java.util.Collection;

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
import org.transinfo.cacis.action.BaseAction;
import org.transinfo.cacis.controller.cardproduction.CardManager;
import org.transinfo.cacis.controller.customerservice.CardLevelLimitManager;
import org.transinfo.cacis.dto.cardproduction.CardsDto;
import org.transinfo.cacis.dto.customerservice.CardLevelLimitSearchDto;
import org.transinfo.cacis.formbean.customerservice.CardLevelLimitSearchForm;

/**
 * WORKSHOP W06_CONTROLLER Extend from the
 * org.apache.struts.actions.DispatchAction class
 *********/
@SuppressWarnings({"deprecation"})
public class SearchCardLevelListAction extends BaseAction {

	private Logger logger = Logger.getLogger(SearchCardLevelListAction.class);

	public ActionForward executeAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {

		if (((request.getParameter("method") != null) && (((String) request.getParameter("method")).equals("List")  || ((String) request.getParameter("method")).equals("delete"))) 
				|| ((request.getParameter("mode") != null) && (((String) request.getParameter("mode")).equals("NEXT")  || ((String) request.getParameter("mode")).equals("PREV")))
						) {
			try {
				
				int pageNo = 0;

				if (request.getParameter("mode") != null
						&& ((String) request.getParameter("mode")).equals("NEXT")) {

					if (request.getParameter("pageNo") != null) {
						pageNo = Integer.parseInt((String) request
								.getParameter("pageNo")) + 1;

					}
				}
				if (request.getParameter("mode") != null
						&& ((String) request.getParameter("mode")).equals("PREV")) {

					if (request.getParameter("pageNo") != null) {
						pageNo = Integer.parseInt((String) request
								.getParameter("pageNo")) - 1;

					}
				}

				CardLevelLimitSearchForm objForm = (CardLevelLimitSearchForm) form;
				CardLevelLimitSearchForm newObjForm = new CardLevelLimitSearchForm();

				BeanUtils.copyProperties(objForm, newObjForm);

				objForm.setIssuerId((String) request.getSession(false).getAttribute("ISSUER"));
				
				CardLevelLimitSearchDto objDto = new CardLevelLimitSearchDto();
				
				try {
					BeanUtils.copyProperties(objDto, objForm);
				} catch (Exception e) {
					logger.error(e);
					throw new TPlusException("Error converting to form bean in SearchCardProductAction execute method: " + e);
				}
								
				CardLevelLimitManager objManager = new CardLevelLimitManager();
				Collection cardLimits = objManager.search(objDto, pageNo);

				request.setAttribute("SEARCHLIST", cardLimits);
				request.setAttribute("PAGENO", new Integer(pageNo).toString());
				
				return mapping.findForward("success");

			} catch (Exception exp) {
				logger.error(exp);
				System.out
						.println("Error in Search Branch Action Calling PreList: "
								+ exp);
				throw new TPlusException(
						"Error in Search Branch Action Calling PreList " + exp);
			}
		}

		else {

			int pageNo = 0;

			if (request.getParameter("mode") != null
					&& ((String) request.getParameter("mode")).equals("NEXT")) {

				if (request.getParameter("pageNo") != null) {
					pageNo = Integer.parseInt((String) request
							.getParameter("pageNo")) + 1;

				}
			}
			if (request.getParameter("mode") != null
					&& ((String) request.getParameter("mode")).equals("PREV")) {

				if (request.getParameter("pageNo") != null) {
					pageNo = Integer.parseInt((String) request
							.getParameter("pageNo")) - 1;

				}
			}
			CardLevelLimitSearchForm objForm = (CardLevelLimitSearchForm) form;
			objForm.setCardNo("");

			ActionErrors errors = objForm.validate(mapping, request);

			if (errors != null && !errors.isEmpty()) {
				saveErrors(request, errors);
				return mapping.getInputForward();
			}
			
			String searchNumber = objForm.getSearchCardNo();
			try{
				Long.valueOf(searchNumber);
			}catch (Exception e) {
				logger.error("Card no is not valid");
				
				errors = new ActionErrors();
				errors.add("Error", new ActionError("error.cardnoinvalid"));
				saveErrors(request, errors);
				
				return mapping.getInputForward();
			}
			
			// Dto Creation
			CardLevelLimitSearchDto objDto = new CardLevelLimitSearchDto();
			try {
				BeanUtils.copyProperties(objDto, objForm);
				objDto.setCardNo(searchNumber);
			} catch (Exception e) {
				logger.error(e);
				System.out
						.println("Error converting to form bean in SearchCardProductAction execute method: "
								+ e.getMessage());
				throw new TPlusException(
						"Error converting to form bean in SearchCardProductAction execute method: "
								+ e);
			}
			
			String forward = "success";
			
			// Action Execution
			CardManager objCardManager = new CardManager();
			CardsDto objCardsDto = objCardManager.getCard(objDto.getCardNo());
			
			if(objCardsDto == null){
				
				errors = new ActionErrors();
				errors.add("Error", new ActionError("error.cardnumbernotexit"));
				saveErrors(request, errors);
				
				forward = "success";
				
			}else{
				
				if((objCardsDto.getCardStatusId() != 0) || !"A".equals(objCardsDto.getStatus())){
					
					errors = new ActionErrors();
					errors.add("Error", new ActionError("error.cardnotactive"));
					saveErrors(request, errors);
					
					return mapping.getInputForward();
				}
				
				CardLevelLimitManager objManager = new CardLevelLimitManager();
				Collection cardLimits = objManager.search(objDto, pageNo);
				
				if(cardLimits.isEmpty()){
					forward = "success";
					request.setAttribute("ACTION", "add");
					
					errors = new ActionErrors();
					errors.add("Error", new ActionError("error.customerlimitnotexit"));
					saveErrors(request, errors);
					
					objForm.setCardHolderType(String.valueOf(objCardsDto.getCardHolderType()));
					objForm.setCardNo(searchNumber);
					
				}else{
					forward = "success";
					
					request.setAttribute("SEARCHLIST", cardLimits);
					request.setAttribute("PAGENO", new Integer(pageNo).toString());
				}
				
			}
			
			objForm.getPreListData();

			return mapping.findForward(forward);
		}
	}
}