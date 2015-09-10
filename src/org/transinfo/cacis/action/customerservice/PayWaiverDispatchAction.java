package org.transinfo.cacis.action.customerservice;

import java.util.Enumeration;
import java.util.List;

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
import org.transinfo.cacis.controller.customerservice.PayWaiverManager;
import org.transinfo.cacis.dto.cardproduction.CardsDto;
import org.transinfo.cacis.formbean.customerservice.PayWaiverForm;

@SuppressWarnings( { "deprecation", "unchecked", "unused" })
public class PayWaiverDispatchAction extends BaseDispatchAction {

	public ActionForward checkSession(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws TPlusException, Exception {

		processSession(request);

		PayWaiverForm objForm = (PayWaiverForm) form;
		PayWaiverForm newObjForm = new PayWaiverForm();

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

		PayWaiverForm objForm = (PayWaiverForm) form;
		errors = objForm.validate(mapping, request);

		if (errors != null && !errors.isEmpty()) {
			saveErrors(request, errors);
			return mapping.findForward("success");
		}

		try {
			long cardNo = Long.valueOf(objForm.getCardNo());
		} catch (Exception e) {
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.cardnoinvalid"));
			saveErrors(request, errors);
			return mapping.findForward("success");
		}

		PayWaiverManager objManager = new PayWaiverManager();

		String forward = "success";

		try {

			// get card detail
			CardsDto cardDto = objManager.getCardDto(objForm.getCardNo());
			if (cardDto != null) {
				if (!"C".equalsIgnoreCase(cardDto.getStatus())) {

					// get from CUSTOMER_FEE

					String cardNo = String.valueOf(cardDto.getCardNumber());
					List intList = objManager.getIntTranx(cardNo);

					objForm.setFeeList(intList);
					if(intList != null && intList.size() > 0){
						objForm.setButEnable("Y");
					}else{
						errors = new ActionErrors();
						errors.add("Error", new ActionError("error.nopaywaiver"));
						saveErrors(request, errors);
					}

					objForm.setCardType("C");

				} else {
					errors = new ActionErrors();
					errors.add("Error", new ActionError("error.cardclosed"));
					saveErrors(request, errors);
					return mapping.findForward(forward);
				}
			} else {
				errors = new ActionErrors();
				errors.add("Error", new ActionError("error.cardnumbernotexit"));
				saveErrors(request, errors);
				return mapping.findForward(forward);
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
		request.setAttribute("SEARCHLIST", "search");
		return mapping.findForward(forward);
	}

	public ActionForward paywaive(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws TPlusException, Exception {

		PayWaiverForm objForm = (PayWaiverForm) form;

		// validate and save card changes
		String forward = "success";
		try {

			ActionErrors errors = null;

			String cardType = objForm.getCardType();

			String userId = (String) request.getSession(false).getAttribute("USERID");

			PayWaiverManager objManager = new PayWaiverManager();

			String strSelectFeewaiveSerialNos = objForm.getSerialNosTowaive();
			String selectFeewaiveSerialNos[] = strSelectFeewaiveSerialNos.split(",");

			int totlen = selectFeewaiveSerialNos.length;

			String feeId = "";
			boolean res = false;
			int successCount = 0;

			for (int i = 0; i < selectFeewaiveSerialNos.length; i++) {
				feeId = selectFeewaiveSerialNos[i];

				res = objManager.updateDB(feeId, userId);

				if(res){
					successCount++;
				}

			}

			if(totlen == successCount){

				forward = "paywaived";

				errors = new ActionErrors();
				errors.add("Error", new ActionError("error.allpaywaived"));
				saveErrors(request, errors);
				return mapping.findForward(forward);

			}else{

				forward = "paywaived";

				errors = new ActionErrors();
				errors.add("Error", new ActionError("error.notallpaywaived"));
				saveErrors(request, errors);
				return mapping.findForward(forward);

			}

		} catch (Exception e) {
			System.out.println("Error converting to form bean in CardChangeDispatchAction: " + e);
			throw new TPlusException("Could not populate the form bean in CardChangeDispatchAction: " + e);
		}

	}

}
