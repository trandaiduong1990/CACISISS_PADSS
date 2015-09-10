package org.transinfo.cacis.action.customerservice;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.action.BaseDispatchAction;
import org.transinfo.cacis.controller.customerservice.CardChangeManager;
import org.transinfo.cacis.dto.cardproduction.CardsDto;
import org.transinfo.cacis.dto.csr.CustomerInfoDto;
import org.transinfo.cacis.dto.customerservice.CardChangeDto;
import org.transinfo.cacis.formbean.customerservice.CardChangeProcessForm;

@SuppressWarnings( { "deprecation", "unchecked" })
public class CardChangeProcessDispatchAction extends BaseDispatchAction {

	public ActionForward processSearch(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {

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

		ActionErrors errors = null;

		CardChangeProcessForm objForm = (CardChangeProcessForm) form;
		
		HttpSession session = request.getSession(true);
		String branchId = (String)session.getAttribute("LOGEDUSERBRANCH");
		
		String issuer = (String)request.getSession().getAttribute("ISSUER");
		
		errors = objForm.validate(mapping, request);

		if (errors != null && !errors.isEmpty()) {
			saveErrors(request, errors);
			return mapping.findForward("listsuccess");
		}

		CardChangeDto objDto = new CardChangeDto();
		try {
			if (objForm.getCardNo() != null && !"".equals(objForm.getCardNo())) {
				objDto.setCardNo(Long.valueOf(objForm.getCardNo()).longValue());
			} else {
				objDto.setCardNo(0);
			}
			objDto.setCustomerName(objForm.getCustomerName());
			objDto.setNric(objForm.getNric());
			
			objDto.setBranchId(branchId);
			objDto.setIssuerId(issuer);
		} catch (Exception e) {
			throw new TPlusException(
					"Could not populate the form bean CardChangeProcessDispatchAction : "
							+ e);
		}

		CardChangeManager objManager = new CardChangeManager();
		Collection cardChangeProcessList = objManager.processSearch(objDto,
				pageNo);

		request.setAttribute("SEARCHLIST", cardChangeProcessList);
		request.setAttribute("PAGENO", new Integer(pageNo).toString());
		return mapping.findForward("listsuccess");
	}

	public ActionForward details(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {
		ActionErrors errors = null;

		CardChangeProcessForm objForm = (CardChangeProcessForm) form;

		try {
			CardChangeManager objManager = new CardChangeManager();

			// get card change detail
			CardChangeDto cardChangeDto = objManager.getCardChangeDto(objForm
					.getCardNo());
			
			if(cardChangeDto.getStatus() == 3){
				request.setAttribute("ACTION", "NO");
				errors = new ActionErrors();
				errors.add("Error", new ActionError("alert.cardchangeprocessed"));
				saveErrors(request, errors);
			}else{
				request.setAttribute("ACTION", "YES");
			}
			
			objForm.setExistCardStatus("" + cardChangeDto.getExistCardStatus());
			objForm.setChangeProductID(cardChangeDto.getChangeCardProduct());
			// get card detail
			CardsDto cardDto = objManager.getCardDto(objForm.getCardNo());
			// assign it into form bean
			objForm.setCard(cardDto);
			// get customer detail
			CustomerInfoDto customerDto = objManager.getCustomerInfoDto(cardDto
					.getCustomerId());
			// assign it into form bean
			objForm.setCustomer(customerDto);

		} catch (Exception e) {

			System.out
					.println("Error converting to form bean in CardChangeDispatchAction: "
							+ e);
			throw new TPlusException(
					"Could not populate the form bean in CardChangeDispatchAction: "
							+ e);
		}

		String issId = (String) request.getSession().getAttribute("ISSUER");
		objForm.getPreListData(issId);

		saveToken(request);
		request.setAttribute("$CARDCHANGEPROCESS$", "search");

		return mapping.findForward("success");

	}

	public ActionForward accept(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {
		ActionErrors errors = null;

		CardChangeProcessForm objForm = (CardChangeProcessForm) form;

		try {
			CardChangeManager objManager = new CardChangeManager();
			
			CardChangeDto objCardChangeDto = new CardChangeDto();
			objCardChangeDto.setCardNo(Long.valueOf(objForm.getCardNo()));
			objCardChangeDto.setUserId(objForm.getUserId());
			objCardChangeDto.setExistCardProduct(objForm.getCard().getCardProductId());
			objCardChangeDto.setChangeCardProduct(objForm.getChangeProductID());

			boolean boolAccept = objManager.accept(objCardChangeDto);
			if (!boolAccept) {
				errors = new ActionErrors();
				errors.add("Error", new ActionError("error.acceptfailed"));
				saveErrors(request, errors);
			} else {
				errors = new ActionErrors();
				errors.add("Error", new ActionError("error.acceptSuccess"));
				saveErrors(request, errors);
			}
		} catch (Exception e) {

			System.out
					.println("Error converting to form bean in CardChangeDispatchAction: "
							+ e);
			throw new TPlusException(
					"Could not populate the form bean in CardChangeDispatchAction: "
							+ e);
		}

		saveToken(request);
		request.setAttribute("$CARDCHANGEPROCESS$", "search");

		return mapping.findForward("success");

	}

	public ActionForward reject(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {
		ActionErrors errors = null;

		CardChangeProcessForm objForm = (CardChangeProcessForm) form;

		try {
			CardChangeManager objManager = new CardChangeManager();
			CardChangeDto objCardChangeDto = new CardChangeDto();
			objCardChangeDto.setCardNo(Long.valueOf(objForm.getCardNo()));
			objCardChangeDto.setUserId(objForm.getUserId());
			objCardChangeDto.setExistCardProduct(objForm.getCard().getCardProductId());
			objCardChangeDto.setChangeCardProduct(objForm.getChangeProductID());

			boolean boolAccept = objManager.reject(objCardChangeDto);
			if (!boolAccept) {
				errors = new ActionErrors();
				errors.add("Error", new ActionError("error.rejectfailed"));
				saveErrors(request, errors);
			} else {
				errors = new ActionErrors();
				errors.add("Error", new ActionError("error.rejectSuccess"));
				saveErrors(request, errors);
			}
		} catch (Exception e) {

			System.out
					.println("Error converting to form bean in CardChangeDispatchAction: "
							+ e);
			throw new TPlusException(
					"Could not populate the form bean in CardChangeDispatchAction: "
							+ e);
		}

		saveToken(request);
		request.setAttribute("$CARDCHANGEPROCESS$", "search");

		return mapping.findForward("success");

	}

}
