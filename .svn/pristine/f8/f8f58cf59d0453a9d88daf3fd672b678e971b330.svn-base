package org.transinfo.cacis.action.settings;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.action.BaseDispatchAction;
import org.transinfo.cacis.controller.settings.CardProductLimitManager;
import org.transinfo.cacis.controller.settings.CurrencyRateManager;
import org.transinfo.cacis.controller.settings.SalaryProfileManager;
import org.transinfo.cacis.dto.settings.CardProductDto;
import org.transinfo.cacis.dto.settings.CardProductLimitDto;
import org.transinfo.cacis.dto.settings.CurrencyDto;
import org.transinfo.cacis.dto.settings.CurrencyRateDto;
import org.transinfo.cacis.dto.settings.SalaryProfileDto;
import org.transinfo.cacis.formbean.common.SalaryForm;
import org.transinfo.cacis.formbean.settings.CurrencyRateForm;
import org.transinfo.cacis.formbean.settings.SalaryProfileForm;

@SuppressWarnings( { "unchecked", "deprecation" })
public class SalaryProfileDispatchAction extends BaseDispatchAction {

	private Logger logger = Logger.getLogger(SalaryProfileDispatchAction.class);

	public ActionForward init(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {

		String issuerId = (String) request.getSession(false).getAttribute(
				"ISSUER");

		SalaryProfileForm objForm = (SalaryProfileForm) form;

		SalaryProfileManager objManager = new SalaryProfileManager();
		objForm.setProductList(objManager.getSearchproductList(issuerId));

		return mapping.findForward("listsuccess");
	}

	public ActionForward search(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {

		String issuerId = (String) request.getSession(false).getAttribute(
				"ISSUER");

		ActionErrors errors = null;

		// Form Validations
		SalaryProfileForm objForm = (SalaryProfileForm) form;
		errors = objForm.validate(mapping, request);

		SalaryProfileManager objManager = new SalaryProfileManager();
		objForm.setProductList(objManager.getSearchproductList(issuerId));

		if (errors != null && !errors.isEmpty()) {
			saveErrors(request, errors);

			return mapping.findForward("listsuccess");
		}

		Collection salaryProfileList = objManager.getSalaryProfileList(objForm
				.getCardProduct());

		request.setAttribute("SEARCHLIST", salaryProfileList);

		return mapping.findForward("listsuccess");
	}

	public ActionForward addNew(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {

		String issuerId = (String) request.getSession(false).getAttribute(
				"ISSUER");

		SalaryProfileForm objForm = (SalaryProfileForm) form;

		SalaryProfileManager objManager = new SalaryProfileManager();
		objForm.setProductList(objManager.getAddproductList(issuerId));

		request.setAttribute("ACTION", "add");
		return mapping.findForward("processsuccess");
	}

	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {

		String issuerId = (String) request.getSession(false).getAttribute(
				"ISSUER");
		String userId = (String) request.getSession(false).getAttribute(
				"USERID");

		ActionErrors errors = null;

		// Form Validations
		SalaryProfileForm objForm = (SalaryProfileForm) form;
		errors = objForm.validate(mapping, request);

		SalaryProfileManager objManager = new SalaryProfileManager();
		objForm.setProductList(objManager.getAddproductList(issuerId));

		if (errors != null && !errors.isEmpty()) {
			saveErrors(request, errors);
			request.setAttribute("ACTION", "add");
			return mapping.getInputForward();
		}

		// DTO Creation
		SalaryProfileDto objSalaryProfileDto = null;
		boolean boolAdd = false;
		CardProductDto objCardProductDto = new CardProductDto();
		objCardProductDto.setCardProductId(objForm.getCardProduct());

		CardProductLimitManager objCardProductLimitManager = new CardProductLimitManager();

		// salary profile limit validation against card product limit
		CardProductLimitDto objCardProductLimitDto = objCardProductLimitManager
				.getCardProductLimitDto(objForm.getCardProduct());
		
		if(objCardProductLimitDto == null){
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.noproductlimit"));
			saveErrors(request, errors);
			request.setAttribute("ACTION", "add");
			return mapping.getInputForward();
		}
		
		float minSalary = objCardProductLimitDto.getMinSalary();
		//float maxCashLimit = objCardProductLimitDto.getMaxCashLimiPerCrl();

		boolean minSalaryOk = true;
		boolean maxCashLimitOk = true;

		if (objForm.getSalarPprofile1().getCreditLimit() != null
				&& !"".equals(objForm.getSalarPprofile1().getCreditLimit())) {
			if (minSalary > Float.valueOf(
					objForm.getSalarPprofile1().getMinSalary()).floatValue()) {
				minSalaryOk = false;
			}
			/*if (maxCashLimit < Float.valueOf(
					objForm.getSalarPprofile1().getCashAdvancedLimit())
					.floatValue()) {
				maxCashLimitOk = false;
			}*/
		}
		if (minSalaryOk && maxCashLimitOk) {
			if (objForm.getSalarPprofile2().getCreditLimit() != null
					&& !"".equals(objForm.getSalarPprofile2().getCreditLimit())) {
				if (minSalary > Float.valueOf(
						objForm.getSalarPprofile2().getMinSalary())
						.floatValue()) {
					minSalaryOk = false;
				}
				/*if (maxCashLimit < Float.valueOf(
						objForm.getSalarPprofile2().getCashAdvancedLimit())
						.floatValue()) {
					maxCashLimitOk = false;
				}*/
			}

			if (minSalaryOk && maxCashLimitOk) {
				if (objForm.getSalarPprofile3().getCreditLimit() != null
						&& !"".equals(objForm.getSalarPprofile3()
								.getCreditLimit())) {
					if (minSalary > Float.valueOf(
							objForm.getSalarPprofile3().getMinSalary())
							.floatValue()) {
						minSalaryOk = false;
					}
					/*if (maxCashLimit < Float.valueOf(
							objForm.getSalarPprofile3().getCashAdvancedLimit())
							.floatValue()) {
						maxCashLimitOk = false;
					}*/
				}

				if (minSalaryOk && maxCashLimitOk) {
					if (objForm.getSalarPprofile4().getCreditLimit() != null
							&& !"".equals(objForm.getSalarPprofile4()
									.getCreditLimit())) {
						if (minSalary > Float.valueOf(
								objForm.getSalarPprofile4().getMinSalary())
								.floatValue()) {
							minSalaryOk = false;
						}
						/*if (maxCashLimit < Float.valueOf(
								objForm.getSalarPprofile4()
										.getCashAdvancedLimit()).floatValue()) {
							maxCashLimitOk = false;
						}*/
					}
				}

			}

		}

		if (maxCashLimitOk && minSalaryOk) {

			// insert into table
			if (objForm.getSalarPprofile1().getCreditLimit() != null
					&& !"".equals(objForm.getSalarPprofile1().getCreditLimit())) {
				objSalaryProfileDto = new SalaryProfileDto();
				objSalaryProfileDto.id.setCardProduct(objCardProductDto);
				objSalaryProfileDto.id.setCreditLimit(Long.valueOf(objForm
						.getSalarPprofile1().getCreditLimit()));
				objSalaryProfileDto.id.setCashAdvanceLimit(Long.valueOf(objForm
						.getSalarPprofile1().getCashAdvancedLimit()));
				objSalaryProfileDto.setMaxSalary(Long.valueOf(objForm
						.getSalarPprofile1().getMaxSalary()));
				objSalaryProfileDto.setMinSalary(Long.valueOf(objForm
						.getSalarPprofile1().getMinSalary()));
				objSalaryProfileDto.setUserId(userId);
				objSalaryProfileDto.setUpdatedDate(new Date());

				boolAdd = objManager.add(objSalaryProfileDto);

				if (boolAdd) {
					if (objForm.getSalarPprofile2().getCreditLimit() != null
							&& !"".equals(objForm.getSalarPprofile2()
									.getCreditLimit())) {
						objSalaryProfileDto = new SalaryProfileDto();
						objSalaryProfileDto.id
								.setCardProduct(objCardProductDto);
						objSalaryProfileDto.id.setCreditLimit(Long
								.valueOf(objForm.getSalarPprofile2()
										.getCreditLimit()));
						objSalaryProfileDto.id.setCashAdvanceLimit(Long
								.valueOf(objForm.getSalarPprofile2()
										.getCashAdvancedLimit()));
						objSalaryProfileDto.setMaxSalary(Long.valueOf(objForm
								.getSalarPprofile2().getMaxSalary()));
						objSalaryProfileDto.setMinSalary(Long.valueOf(objForm
								.getSalarPprofile2().getMinSalary()));
						objSalaryProfileDto.setUserId(userId);
						objSalaryProfileDto.setUpdatedDate(new Date());

						boolAdd = objManager.add(objSalaryProfileDto);

						if (boolAdd) {
							if (objForm.getSalarPprofile3().getCreditLimit() != null
									&& !"".equals(objForm.getSalarPprofile3()
											.getCreditLimit())) {
								objSalaryProfileDto = new SalaryProfileDto();
								objSalaryProfileDto.id
										.setCardProduct(objCardProductDto);
								objSalaryProfileDto.id.setCreditLimit(Long
										.valueOf(objForm.getSalarPprofile3()
												.getCreditLimit()));
								objSalaryProfileDto.id.setCashAdvanceLimit(Long
										.valueOf(objForm.getSalarPprofile3()
												.getCashAdvancedLimit()));
								objSalaryProfileDto.setMaxSalary(Long
										.valueOf(objForm.getSalarPprofile3()
												.getMaxSalary()));
								objSalaryProfileDto.setMinSalary(Long
										.valueOf(objForm.getSalarPprofile3()
												.getMinSalary()));
								objSalaryProfileDto.setUserId(userId);
								objSalaryProfileDto.setUpdatedDate(new Date());

								boolAdd = objManager.add(objSalaryProfileDto);

								if (boolAdd) {
									if (objForm.getSalarPprofile4()
											.getCreditLimit() != null
											&& !"".equals(objForm
													.getSalarPprofile4()
													.getCreditLimit())) {
										objSalaryProfileDto = new SalaryProfileDto();
										objSalaryProfileDto.id
												.setCardProduct(objCardProductDto);
										objSalaryProfileDto.id
												.setCreditLimit(Long
														.valueOf(objForm
																.getSalarPprofile4()
																.getCreditLimit()));
										objSalaryProfileDto.id
												.setCashAdvanceLimit(Long
														.valueOf(objForm
																.getSalarPprofile4()
																.getCashAdvancedLimit()));
										objSalaryProfileDto.setMaxSalary(Long
												.valueOf(objForm
														.getSalarPprofile4()
														.getMaxSalary()));
										objSalaryProfileDto.setMinSalary(Long
												.valueOf(objForm
														.getSalarPprofile4()
														.getMinSalary()));
										objSalaryProfileDto.setUserId(userId);
										objSalaryProfileDto
												.setUpdatedDate(new Date());

										boolAdd = objManager
												.add(objSalaryProfileDto);
									}
								}
							}
						}
					}
				}
			}

			//String userActivity = "";

			if (!boolAdd) {
				request.setAttribute("ACTION", "add");
				errors = new ActionErrors();
				errors.add("Error", new ActionError("error.addfailed"));
				saveErrors(request, errors);
				//userActivity = UserActivityData.SALARY_PROFILE_ADD_FAIL;
			} else {
				request.setAttribute("ACTION", "cancel");
				errors = new ActionErrors();
				errors.add("Error", new ActionError("error.addSuccess"));
				saveErrors(request, errors);
				//userActivity = UserActivityData.SALARY_PROFILE_ADD_SUCESS;
			}

			/*// user activities record into database
			UserActivitiesDto objUserActivitiesDto = new UserActivitiesDto();

			objUserActivitiesDto.setDateTime(new Date());
			objUserActivitiesDto.setIssuerId((String) request.getSession()
					.getAttribute("ISSUER"));
			objUserActivitiesDto.setUserId((String) request.getSession()
					.getAttribute("USERID"));
			objUserActivitiesDto.setActivity(userActivity);
			objUserActivitiesDto.setStationIp(InetAddress.getLocalHost()
					.getHostAddress());

			UserActivitiesManager activityManager = new UserActivitiesManager();
			activityManager.add(objUserActivitiesDto);*/

			return mapping.findForward("processsuccess");

		} else {
			errors = new ActionErrors();
			if (!minSalaryOk) {
				errors.add("Error", new ActionError("error.minsalarywrong"));
			}
			if (!maxCashLimitOk) {
				errors.add("Error", new ActionError("error.maxcashlimitwrong"));
			}
			saveErrors(request, errors);
			request.setAttribute("ACTION", "add");
			return mapping.getInputForward();
		}
	}

	public ActionForward change(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {

		String issuerId = (String) request.getSession(false).getAttribute(
				"ISSUER");

		ActionErrors errors = null;

		SalaryProfileForm objForm = (SalaryProfileForm) form;
		errors = objForm.validate(mapping, request);

		SalaryProfileManager objManager = new SalaryProfileManager();
		objForm.setProductList(objManager.getSearchproductList(issuerId));

		if (errors != null && !errors.isEmpty()) {
			saveErrors(request, errors);
			request.setAttribute("ACTION", "add");
			return mapping.getInputForward();
		}

		try {

			List salaryProfileList = objManager
					.getSalaryProfileListUpdate(objForm.getCardProduct());
			int listSize = salaryProfileList.size();

			SalaryForm objSalaryForm = null;

			SalaryProfileDto objSalaryProfileDto = (SalaryProfileDto) salaryProfileList
					.get(0);
			objSalaryForm = new SalaryForm();
			objSalaryForm.setCashAdvancedLimit(String
					.valueOf(objSalaryProfileDto.id.getCashAdvanceLimit()));
			objSalaryForm.setCreditLimit(String.valueOf(objSalaryProfileDto.id
					.getCreditLimit()));
			objSalaryForm.setMaxSalary(String.valueOf(objSalaryProfileDto
					.getMaxSalary()));
			objSalaryForm.setMinSalary(String.valueOf(objSalaryProfileDto
					.getMinSalary()));
			objForm.setSalarPprofile1(objSalaryForm);

			if (listSize > 1) {
				objSalaryProfileDto = (SalaryProfileDto) salaryProfileList
						.get(1);
				objSalaryForm = new SalaryForm();
				objSalaryForm.setCashAdvancedLimit(String
						.valueOf(objSalaryProfileDto.id.getCashAdvanceLimit()));
				objSalaryForm.setCreditLimit(String
						.valueOf(objSalaryProfileDto.id.getCreditLimit()));
				objSalaryForm.setMaxSalary(String.valueOf(objSalaryProfileDto
						.getMaxSalary()));
				objSalaryForm.setMinSalary(String.valueOf(objSalaryProfileDto
						.getMinSalary()));
				objForm.setSalarPprofile2(objSalaryForm);

				if (listSize > 2) {
					objSalaryProfileDto = (SalaryProfileDto) salaryProfileList
							.get(2);
					objSalaryForm = new SalaryForm();
					objSalaryForm.setCashAdvancedLimit(String
							.valueOf(objSalaryProfileDto.id
									.getCashAdvanceLimit()));
					objSalaryForm.setCreditLimit(String
							.valueOf(objSalaryProfileDto.id.getCreditLimit()));
					objSalaryForm.setMaxSalary(String
							.valueOf(objSalaryProfileDto.getMaxSalary()));
					objSalaryForm.setMinSalary(String
							.valueOf(objSalaryProfileDto.getMinSalary()));
					objForm.setSalarPprofile3(objSalaryForm);

					if (listSize > 3) {
						objSalaryProfileDto = (SalaryProfileDto) salaryProfileList
								.get(3);
						objSalaryForm = new SalaryForm();
						objSalaryForm.setCashAdvancedLimit(String
								.valueOf(objSalaryProfileDto.id
										.getCashAdvanceLimit()));
						objSalaryForm.setCreditLimit(String
								.valueOf(objSalaryProfileDto.id
										.getCreditLimit()));
						objSalaryForm.setMaxSalary(String
								.valueOf(objSalaryProfileDto.getMaxSalary()));
						objSalaryForm.setMinSalary(String
								.valueOf(objSalaryProfileDto.getMinSalary()));
						objForm.setSalarPprofile4(objSalaryForm);
					}
				}
			}

		} catch (Exception e) {
			logger.error(e);
			System.out
					.println("Error converting to form bean in CurrencyRateDispatchAction in change method: "
							+ e.getMessage());
			throw new TPlusException(
					"Could not populate the form bean in CurrencyRateDispatchAction method: "
							+ e);
		}

		// Success
		saveToken(request);
		request.setAttribute("ACTION", "update");
		return mapping.findForward("processsuccess");
	}

	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {

		String issuerId = (String) request.getSession(false).getAttribute(
				"ISSUER");

		ActionErrors errors = null;

		// Form Validations
		SalaryProfileForm objForm = (SalaryProfileForm) form;
		boolean boolAdd = false;

		SalaryProfileManager objManager = new SalaryProfileManager();

		objForm.setProductList(objManager.getSearchproductList(issuerId));

		CardProductLimitManager objCardProductLimitManager = new CardProductLimitManager();

		// salary profile limit validation against car product limit
		CardProductLimitDto objCardProductLimitDto = objCardProductLimitManager
				.getCardProductLimitDto(objForm.getCardProduct());
		
		if(objCardProductLimitDto == null){
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.noproductlimit"));
			saveErrors(request, errors);
			request.setAttribute("ACTION", "add");
			return mapping.getInputForward();
		}
		
		float minSalary = objCardProductLimitDto.getMinSalary();
		//float maxCashLimit = objCardProductLimitDto.getMaxCashLimiPerCrl();

		boolean minSalaryOk = true;
		boolean maxCashLimitOk = true;

		if (objForm.getSalarPprofile1().getCreditLimit() != null
				&& !"".equals(objForm.getSalarPprofile1().getCreditLimit())) {
			if (minSalary > Float.valueOf(
					objForm.getSalarPprofile1().getMinSalary()).floatValue()) {
				minSalaryOk = false;
			}
			/*if (maxCashLimit < Float.valueOf(
					objForm.getSalarPprofile1().getCashAdvancedLimit())
					.floatValue()) {
				maxCashLimitOk = false;
			}*/
		}
		if (minSalaryOk && maxCashLimitOk) {
			if (objForm.getSalarPprofile2().getCreditLimit() != null
					&& !"".equals(objForm.getSalarPprofile2().getCreditLimit())) {
				if (minSalary > Float.valueOf(
						objForm.getSalarPprofile2().getMinSalary())
						.floatValue()) {
					minSalaryOk = false;
				}
				/*if (maxCashLimit < Float.valueOf(
						objForm.getSalarPprofile2().getCashAdvancedLimit())
						.floatValue()) {
					maxCashLimitOk = false;
				}*/
			}

			if (minSalaryOk && maxCashLimitOk) {
				if (objForm.getSalarPprofile3().getCreditLimit() != null
						&& !"".equals(objForm.getSalarPprofile3()
								.getCreditLimit())) {
					if (minSalary > Float.valueOf(
							objForm.getSalarPprofile3().getMinSalary())
							.floatValue()) {
						minSalaryOk = false;
					}
					/*if (maxCashLimit < Float.valueOf(
							objForm.getSalarPprofile3().getCashAdvancedLimit())
							.floatValue()) {
						maxCashLimitOk = false;
					}*/
				}

				if (minSalaryOk && maxCashLimitOk) {
					if (objForm.getSalarPprofile4().getCreditLimit() != null
							&& !"".equals(objForm.getSalarPprofile4()
									.getCreditLimit())) {
						if (minSalary > Float.valueOf(
								objForm.getSalarPprofile4().getMinSalary())
								.floatValue()) {
							minSalaryOk = false;
						}
						/*if (maxCashLimit < Float.valueOf(
								objForm.getSalarPprofile4()
										.getCashAdvancedLimit()).floatValue()) {
							maxCashLimitOk = false;
						}*/
					}
				}

			}

		}

		if (maxCashLimitOk && minSalaryOk) {

			try {

				// delete all the records for particular products
				boolean delRes = objManager.delete(objForm.getCardProduct());

				if (delRes) {
					SalaryProfileDto objSalaryProfileDto = null;
					CardProductDto objCardProductDto = new CardProductDto();
					objCardProductDto
							.setCardProductId(objForm.getCardProduct());

					String userId = (String) request.getSession(false)
							.getAttribute("USERID");

					// insert into table
					if (objForm.getSalarPprofile1().getCreditLimit() != null
							&& !"".equals(objForm.getSalarPprofile1()
									.getCreditLimit())) {
						objSalaryProfileDto = new SalaryProfileDto();
						objSalaryProfileDto.id
								.setCardProduct(objCardProductDto);
						objSalaryProfileDto.id.setCreditLimit(Long
								.valueOf(objForm.getSalarPprofile1()
										.getCreditLimit()));
						objSalaryProfileDto.id.setCashAdvanceLimit(Long
								.valueOf(objForm.getSalarPprofile1()
										.getCashAdvancedLimit()));
						objSalaryProfileDto.setMaxSalary(Long.valueOf(objForm
								.getSalarPprofile1().getMaxSalary()));
						objSalaryProfileDto.setMinSalary(Long.valueOf(objForm
								.getSalarPprofile1().getMinSalary()));
						objSalaryProfileDto.setUserId(userId);
						objSalaryProfileDto.setUpdatedDate(new Date());

						boolAdd = objManager.add(objSalaryProfileDto);

						if (boolAdd) {
							if (objForm.getSalarPprofile2().getCreditLimit() != null
									&& !"".equals(objForm.getSalarPprofile2()
											.getCreditLimit())) {
								objSalaryProfileDto = new SalaryProfileDto();
								objSalaryProfileDto.id
										.setCardProduct(objCardProductDto);
								objSalaryProfileDto.id.setCreditLimit(Long
										.valueOf(objForm.getSalarPprofile2()
												.getCreditLimit()));
								objSalaryProfileDto.id.setCashAdvanceLimit(Long
										.valueOf(objForm.getSalarPprofile2()
												.getCashAdvancedLimit()));
								objSalaryProfileDto.setMaxSalary(Long
										.valueOf(objForm.getSalarPprofile2()
												.getMaxSalary()));
								objSalaryProfileDto.setMinSalary(Long
										.valueOf(objForm.getSalarPprofile2()
												.getMinSalary()));
								objSalaryProfileDto.setUserId(userId);
								objSalaryProfileDto.setUpdatedDate(new Date());

								boolAdd = objManager.add(objSalaryProfileDto);

								if (boolAdd) {
									if (objForm.getSalarPprofile3()
											.getCreditLimit() != null
											&& !"".equals(objForm
													.getSalarPprofile3()
													.getCreditLimit())) {
										objSalaryProfileDto = new SalaryProfileDto();
										objSalaryProfileDto.id
												.setCardProduct(objCardProductDto);
										objSalaryProfileDto.id
												.setCreditLimit(Long
														.valueOf(objForm
																.getSalarPprofile3()
																.getCreditLimit()));
										objSalaryProfileDto.id
												.setCashAdvanceLimit(Long
														.valueOf(objForm
																.getSalarPprofile3()
																.getCashAdvancedLimit()));
										objSalaryProfileDto.setMaxSalary(Long
												.valueOf(objForm
														.getSalarPprofile3()
														.getMaxSalary()));
										objSalaryProfileDto.setMinSalary(Long
												.valueOf(objForm
														.getSalarPprofile3()
														.getMinSalary()));
										objSalaryProfileDto.setUserId(userId);
										objSalaryProfileDto
												.setUpdatedDate(new Date());

										boolAdd = objManager
												.add(objSalaryProfileDto);

										if (boolAdd) {
											if (objForm.getSalarPprofile4()
													.getCreditLimit() != null
													&& !""
															.equals(objForm
																	.getSalarPprofile4()
																	.getCreditLimit())) {
												objSalaryProfileDto = new SalaryProfileDto();
												objSalaryProfileDto.id
														.setCardProduct(objCardProductDto);
												objSalaryProfileDto.id
														.setCreditLimit(Long
																.valueOf(objForm
																		.getSalarPprofile4()
																		.getCreditLimit()));
												objSalaryProfileDto.id
														.setCashAdvanceLimit(Long
																.valueOf(objForm
																		.getSalarPprofile4()
																		.getCashAdvancedLimit()));
												objSalaryProfileDto
														.setMaxSalary(Long
																.valueOf(objForm
																		.getSalarPprofile4()
																		.getMaxSalary()));
												objSalaryProfileDto
														.setMinSalary(Long
																.valueOf(objForm
																		.getSalarPprofile4()
																		.getMinSalary()));
												objSalaryProfileDto
														.setUserId(userId);
												objSalaryProfileDto
														.setUpdatedDate(new Date());

												boolAdd = objManager
														.add(objSalaryProfileDto);
											}
										}
									}
								}
							}
						}
					}
				}

			} catch (Exception e) {
				logger.error(e);
				System.out
						.println("Error converting to form bean in CurrencyRateDispatchAction in update mthod: "
								+ e.getMessage());
				throw new TPlusException(
						"Could not populate the form bean in CurrencyRateDispatchAction update mthod:"
								+ e);
			}

			//String userActivity = "";

			if (!boolAdd) {
				errors = new ActionErrors();
				errors.add("Error", new ActionError("error.updatefailed"));
				saveErrors(request, errors);
				//userActivity = UserActivityData.SALARY_PROFILE_UPDATE_FAIL;
			} else {
				errors = new ActionErrors();
				errors.add("Error", new ActionError("error.updateSuccess"));
				saveErrors(request, errors);
				request.setAttribute("ACTION", "update");
				//userActivity = UserActivityData.SALARY_PROFILE_UPDATE_SUCESS;
			}

			/*// user activities record into database
			UserActivitiesDto objUserActivitiesDto = new UserActivitiesDto();

			objUserActivitiesDto.setDateTime(new Date());
			objUserActivitiesDto.setIssuerId((String) request.getSession()
					.getAttribute("ISSUER"));
			objUserActivitiesDto.setUserId((String) request.getSession()
					.getAttribute("USERID"));
			objUserActivitiesDto.setActivity(userActivity);
			objUserActivitiesDto.setStationIp(InetAddress.getLocalHost()
					.getHostAddress());

			UserActivitiesManager activityManager = new UserActivitiesManager();
			activityManager.add(objUserActivitiesDto);*/

			// end

			resetToken(request);
			request.setAttribute("ACTION", "cancel");
			return mapping.findForward("processsuccess");
		} else {
			errors = new ActionErrors();
			if (!minSalaryOk) {
				errors.add("Error", new ActionError("error.minsalarywrong"));
			}
			if (!maxCashLimitOk) {
				errors.add("Error", new ActionError("error.maxcashlimitwrong"));
			}
			saveErrors(request, errors);
			request.setAttribute("ACTION", "add");
			return mapping.getInputForward();
		}
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {

		ActionErrors errors = null;

		// Token Validation
		if (!isTokenValid(request)) {
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.duplicate"));
			saveErrors(request, errors);
			return mapping.findForward("token");
		}

		// Form Validations
		CurrencyRateForm objForm = (CurrencyRateForm) form;

		CurrencyRateManager objManager = new CurrencyRateManager();
		// DTO Creation
		CurrencyRateDto objDto = new CurrencyRateDto();
		CurrencyDto objCurrencyDto = new CurrencyDto();

		try {

			objCurrencyDto.setCurrencyCode(objForm.getCurrencyId());

			objDto.id.setIssuerId(objForm.getIssuerId());
			objDto.id.setCurrCode(objCurrencyDto);
			objDto.setUserId(objForm.getUserId());

		} catch (Exception e) {
			logger.error(e);
			System.out
					.println("Error converting to form bean in CurrencyRateDispatchAction in delete method "
							+ e.getMessage());
			throw new TPlusException(
					"Could not populate the form bean  in CurrencyRateDispatchAction in delete method: "
							+ e);
		}
		// Action execution
		boolean boolDelete = objManager.delete(objDto);
		String nextaction = "delete";

		//String userActivity = "";

		if (!boolDelete) {
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.deletefailed"));
			saveErrors(request, errors);
			nextaction = "processsuccess";
			request.setAttribute("ACTION", "update");
			//userActivity = UserActivityData.CURRENCY_RATE_DELETE_FAIL;

			objCurrencyDto = objManager.getCurrencyDto(objForm.getCurrencyId());
			objForm.setCurrency(objCurrencyDto);
		} else {
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.deleteSuccess"));
			saveErrors(request, errors);
			//userActivity = UserActivityData.CURRENCY_RATE_DELETE_SUCESS;
		}

		// Success

		/*// user activities record into database
		UserActivitiesDto objUserActivitiesDto = new UserActivitiesDto();

		objUserActivitiesDto.setDateTime(new Date());
		objUserActivitiesDto.setIssuerId((String) request.getSession()
				.getAttribute("ISSUER"));
		objUserActivitiesDto.setUserId((String) request.getSession()
				.getAttribute("USERID"));
		objUserActivitiesDto.setActivity(userActivity);
		objUserActivitiesDto.setStationIp(InetAddress.getLocalHost()
				.getHostAddress());

		UserActivitiesManager activityManager = new UserActivitiesManager();
		activityManager.add(objUserActivitiesDto);*/

		// end

		resetToken(request);
		request.setAttribute("ACTION", "cancel");
		return mapping.findForward(nextaction);
	}

}
