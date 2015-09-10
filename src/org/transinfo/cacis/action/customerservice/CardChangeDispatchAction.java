package org.transinfo.cacis.action.customerservice;

import java.util.Date;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.action.BaseDispatchAction;
import org.transinfo.cacis.common.constants.CommonCodes;
import org.transinfo.cacis.controller.customerservice.CardChangeManager;
import org.transinfo.cacis.dto.cardproduction.CardsDto;
import org.transinfo.cacis.dto.csr.CustomerInfoDto;
import org.transinfo.cacis.dto.customerservice.CardChangeDto;
import org.transinfo.cacis.formbean.customerservice.CardChangeForm;

@SuppressWarnings( { "deprecation", "unchecked" })
public class CardChangeDispatchAction extends BaseDispatchAction {

	public ActionForward checkSession(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {
		
		processSession(request);
		
		CardChangeForm objForm = (CardChangeForm) form;
		CardChangeForm newObjForm = new CardChangeForm();

		BeanUtils.copyProperties(objForm, newObjForm);
		
		return mapping.findForward("success");
	}

	public void processSession(HttpServletRequest request) {

		if (request.getSession(false) != null) {
			HttpSession session = request.getSession(false);
			Enumeration listCustServ = session.getAttributeNames();
			while (listCustServ.hasMoreElements()) {
				String custObj = (String) listCustServ.nextElement();
				if (custObj.startsWith("$") && custObj.endsWith("$")) {
					session.removeAttribute(custObj);
				}
			}
		}
	}

	public ActionForward search(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {

		ActionErrors errors = null;

		CardChangeForm objForm = (CardChangeForm) form;
		errors = objForm.validate(mapping, request);

		if (errors != null && !errors.isEmpty()) {
			saveErrors(request, errors);
			return mapping.findForward("success");
		}

		try {
			@SuppressWarnings("unused")
			long cardNo = Long.valueOf(objForm.getCardNo());
		} catch (Exception e) {
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.cardnoinvalid"));
			saveErrors(request, errors);
			return mapping.findForward("success");
		}
		
		CardChangeManager objManager = new CardChangeManager();

		try {

			// get card detail
			CardsDto cardDto = objManager.getCardDto(objForm.getCardNo());
			if (cardDto != null) {
				if (!"C".equalsIgnoreCase(cardDto.getStatus())) {
					
					if("A".equalsIgnoreCase(cardDto.getStatus()) && cardDto.getCardStatusId() == 0){
						
						CardChangeDto objCardChange = objManager
								.getCardChangeDto(objForm.getCardNo());
						if (objCardChange == null) {
							// assign it into form bean
							objForm.setCard(cardDto);
							// get customer detail
							CustomerInfoDto customerDto = objManager
									.getCustomerInfoDto(cardDto.getCustomerId());
							// assign it into form bean
							objForm.setCustomer(customerDto);
						} else {
							errors = new ActionErrors();
	
							int status = objCardChange.getStatus();
	
							switch (status) {
							case 0:
								errors.add("Error", new ActionError(
										"error.cardchnagerequested"));
								break;
							case 1:
								errors.add("Error", new ActionError(
										"error.cardchangerequestaccepted"));
								break;
							case 2:
								errors.add("Error", new ActionError(
										"error.cardchangerequestrejected"));
								break;
							case 3:
								errors.add("Error", new ActionError(
										"error.cardchangerequestprecessed"));
							case 4:
								errors.add("Error", new ActionError(
										"error.cardchangerequestprecessed"));
								break;
							default:
								break;
							}
	
							saveErrors(request, errors);
							return mapping.findForward("success");
						}
					}else{
						errors = new ActionErrors();
						errors.add("Error", new ActionError("errors.cardnoactive"));
						saveErrors(request, errors);
						return mapping.findForward("success");
					}
					
				} else {
					errors = new ActionErrors();
					errors.add("Error", new ActionError(
							"error.cardclosed"));
					saveErrors(request, errors);
					return mapping.findForward("success");
				}
			} else {
				errors = new ActionErrors();
				errors.add("Error", new ActionError("error.cardnumbernotexit"));
				saveErrors(request, errors);
				return mapping.findForward("success");
			}

		} catch (Exception e) {

			System.out
					.println("Error converting to form bean in CardChangeDispatchAction: "
							+ e);
			throw new TPlusException(
					"Could not populate the form bean in CardChangeDispatchAction: "
							+ e);
		}

		String issId = (String) request.getSession().getAttribute("ISSUER");
		//objForm.getPreListData(issId);
		objForm.setProductList(objManager.getProductListForChange(issId, objForm.getCardNo()));

		saveToken(request);
		request.setAttribute("$CARDCHANGE$", "search");
		return mapping.findForward("success");
	}

	public ActionForward change(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {

		ActionErrors errors = null;

		CardChangeForm objForm = (CardChangeForm) form;
		CardChangeManager objManager = new CardChangeManager();

		// validate and save card changes
		try {

			// check for same product
			if (objForm.getExistProductId()
					.equals(objForm.getChangeProductID())) {

				errors = new ActionErrors();
				errors.add("Error", new ActionError(
						"error.cardchangesameproduct"));
				saveErrors(request, errors);

			} else {

				CardChangeDto objCardChange = objManager
						.getCardChangeDto(objForm.getCardNo());
				if (objCardChange == null) {

					String existCardStatus = objForm.getExistCardStatus();
					if (existCardStatus == null) {
						existCardStatus = "O";
					}

					// get customer object
					CustomerInfoDto customerDto = objManager
							.getCustomerInfoDto(objForm.getCustomerId());
					// get card object
					CardsDto cardDto = new CardsDto();
					cardDto.setCardNumber(Long.valueOf(objForm.getCardNo()));

					objCardChange = new CardChangeDto();
					objCardChange.setCardNo(Long.valueOf(objForm.getCardNo())
							.longValue());
					objCardChange.setCard(cardDto);
					objCardChange.setNric(customerDto.getIdNumber());
					objCardChange
							.setCustomerName(customerDto.getCustomerName());
					objCardChange.setExistCardProduct(objForm
							.getExistProductId());
					objCardChange.setChangeCardProduct(objForm
							.getChangeProductID());
					objCardChange.setExistCardStatus(existCardStatus.charAt(0));
					objCardChange.setStatus(CommonCodes.CARD_CHANGE_REQUESTED);
					objCardChange.setUpdatedDate(new Date());
					objCardChange.setUserId(objForm.getUserId());
					objCardChange.setCustomer(customerDto);

					boolean res = objManager.add(objCardChange);

					if (!res) {
						errors = new ActionErrors();
						errors.add("Error", new ActionError("error.addfailed"));
						saveErrors(request, errors);
					} else {
						errors = new ActionErrors();
						errors
								.add("Error", new ActionError(
										"error.addSuccess"));
						saveErrors(request, errors);
					}

				} else {
					errors = new ActionErrors();

					int status = objCardChange.getStatus();

					switch (status) {
					case 0:
						errors.add("Error", new ActionError(
								"error.cardchnagerequested"));
						break;
					case 1:
						errors.add("Error", new ActionError(
								"error.cardchangerequestaccepted"));
						break;
					case 2:
						errors.add("Error", new ActionError(
								"error.cardchangerequestrejected"));
						break;
					case 3:
						errors.add("Error", new ActionError(
								"error.cardchangerequestprecessed"));
						break;
					default:
						break;
					}

					saveErrors(request, errors);
				}
			}

		} catch (Exception e) {

			System.out
					.println("Error converting to form bean in CardChangeDispatchAction: "
							+ e);
			throw new TPlusException(
					"Could not populate the form bean in CardChangeDispatchAction: "
							+ e);
		}

		request.setAttribute("$CARDCHANGE$", "search");
		return mapping.findForward("success");
	}

}
