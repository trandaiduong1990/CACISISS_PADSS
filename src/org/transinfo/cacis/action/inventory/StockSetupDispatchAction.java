package org.transinfo.cacis.action.inventory;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.servlet.ServletException;
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
import org.transinfo.cacis.action.BaseDispatchAction;
import org.transinfo.cacis.common.CommonDataBean;
import org.transinfo.cacis.controller.collectionmanagement.CollectionAccountDetailsManager;
import org.transinfo.cacis.controller.collectionmanagement.CollectionAgeingActionManager;
import org.transinfo.cacis.controller.collectionmanagement.CollectionAgentManager;
import org.transinfo.cacis.controller.collectionmanagement.DelinquencyFeeSetupManager;
import org.transinfo.cacis.controller.collectionmanagement.DelinquencyNotificationSetupManager;
import org.transinfo.cacis.controller.inventory.InventoryMasterManager;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.inventory.InventoryMasterDAO;
import org.transinfo.cacis.dto.collectionmanagement.CollectionAgeingActionDto;
import org.transinfo.cacis.dto.collectionmanagement.CollectionAgeingDto;
import org.transinfo.cacis.dto.collectionmanagement.CollectionDto;
import org.transinfo.cacis.dto.collectionmanagement.DelinquencyFeeSetupSearchDto;
import org.transinfo.cacis.dto.collectionmanagement.DelinquencyNotificationSetupSearchDto;
import org.transinfo.cacis.dto.inventory.InventoryMasterDto;
import org.transinfo.cacis.formbean.collectionmanagement.CollectionAccountDetailsSetupForm;
import org.transinfo.cacis.formbean.collectionmanagement.DelinquencyFeeSetupSearchForm;
import org.transinfo.cacis.formbean.collectionmanagement.DelinquencyNotificationSearchForm;
import org.transinfo.cacis.formbean.inventory.StockSetupForm;

public class StockSetupDispatchAction extends BaseDispatchAction {
	private Logger logger = Logger
			.getLogger(StockSetupDispatchAction.class);

	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, TPlusException, Exception {

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

			StockSetupForm objForm = (StockSetupForm) form;
			String issuerId = (String) request.getSession(false).getAttribute("ISSUER");
			objForm.setIssuerId(issuerId);
			ActionErrors errors = objForm.validate(mapping, request);

			if (errors != null && !errors.isEmpty()) {
				saveErrors(request, errors);
				objForm.getPreListData();
				return mapping.getInputForward();
			}

			// Dto Creation
			InventoryMasterDto objDto = new InventoryMasterDto();

			try {
				BeanUtils.copyProperties(objDto, objForm);
			} catch (Exception e) {
				logger.error(e);
				System.out
						.println("Error converting to form bean in SearchDelinquencyNotificationSetupAction execute method: "
								+ e.getMessage());
				throw new TPlusException(
						"Error converting to form bean in SearchDelinquencyNotificationSetupAction execute method: "
								+ e);
			}

			// Action Execution
			InventoryMasterManager objManager = new InventoryMasterManager();
			InventoryMasterDAO objInventoryMasterDAO = DAOFactory.getInstance().getInventoryMasterDAO();
			
			//get card product list
			Map map = objInventoryMasterDAO.cardProductListData(issuerId);
			//get card product list from InventoryMaster
			List<String> cardProductList = objManager.getCardProductList();
			//compare two card product list
			Set set = map.entrySet();
		    Iterator iterator = set.iterator();
		    String cardProductId;
		    String userId = (String) request.getSession(false).getAttribute("USERID");
		    int index=0;
		    List<String> extraCardProduct = new ArrayList<String>();
		    if (cardProductList!=null && !cardProductList.isEmpty()) {
			    while(iterator.hasNext()) {
			        Map.Entry me = (Map.Entry)iterator.next();
					cardProductId = (String) me.getKey();
					for (int i = 0; i < cardProductList.size(); i++) {
						// check card product id in CardProduct table is exist into InventoryMaster table
						if (cardProductId.equals(cardProductList.get(i))) {
							continue;
						}
						if (index == cardProductList.size()) {
							extraCardProduct.add(cardProductId);
						}
					}
				}
		    } else {
		    	while(iterator.hasNext()) {
			        Map.Entry me = (Map.Entry)iterator.next();
					cardProductId = (String) me.getKey();
					extraCardProduct.add(cardProductId);
		    	}
		    }
		    
		    if(extraCardProduct!=null && !extraCardProduct.isEmpty()) {
		    	objManager.add(extraCardProduct, userId);
		    }
		    
			Collection stockList = objManager.search(pageNo);

			// Success
			objForm.setStockList(stockList);
			request.setAttribute("SEARCHLIST", stockList);
			request.setAttribute("PAGENO", new Integer(pageNo).toString());
			objForm.getPreListData();
			return mapping.findForward("success");
		}

	public ActionForward change(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {

		InventoryMasterManager objManager = new InventoryMasterManager();
		StockSetupForm objForm = (StockSetupForm) form;
		ActionErrors errors = null;
		boolean isError = false;
		String errormsg = null;
		try {
			String cardProductId = objForm.getCardProductId();
			InventoryMasterDto objDto = objManager.getInventoryMaster(cardProductId);
			ArrayList<CommonDataBean> stockList = (ArrayList<CommonDataBean>) objForm.getStockList();
			for(int i=0;i<stockList.size();i++) {
				CommonDataBean dataBean = stockList.get(i);
				//get Stock is updated
				if(dataBean.getColumn6().equals(cardProductId)) {
					//validation
					if(null==dataBean.getColumn2() || dataBean.getColumn2().equals("")) {
						isError = true;
						errormsg = "stocksetup.errorreplenish";
						break;
					}
					if(null==dataBean.getColumn4() || dataBean.getColumn4().equals("")) {
						isError = true;
						errormsg = "stocksetup.errorsupplier";
						break;
					}
					if(null==dataBean.getColumn7() || dataBean.getColumn7().equals("")) {
						isError = true;
						errormsg = "stocksetup.errormode";
						break;
					}
					if(null==dataBean.getColumn8() || dataBean.getColumn8().equals("")) {
						isError = true;
						errormsg = "stocksetup.errorqty";
						break;
					} else {
						try {
							int qty = Integer.parseInt(dataBean.getColumn8());
						} catch (Exception e) {
							isError = true;
							errormsg = "stocksetup.errorqty2";
							break;
						}
					}
					
					//update stock
					dataBean.setColumn3(dataBean.getColumn5());
					int currentAvailable;
					if(dataBean.getColumn7().equals("A")) {
						currentAvailable = Integer.parseInt(dataBean.getColumn3())
								+ Integer.parseInt(dataBean.getColumn8());
					} else {
						currentAvailable = Integer.parseInt(dataBean.getColumn3())
								- Integer.parseInt(dataBean.getColumn8());
					}
					if(currentAvailable < 0) {
						isError = true;
						errormsg = "stocksetup.errorcurrentstock";
						break;
					}
					dataBean.setColumn5(String.valueOf(currentAvailable));
					
					objDto.setLastMode(dataBean.getColumn7());
					objDto.setLastQty(Integer.parseInt(dataBean.getColumn8()));
					objDto.setLastUpdatedBy((String) request.getSession(false).getAttribute("USERID"));
					objDto.setLastUpdatedDate(new Date());
					objDto.setReplenishPoint(Integer.parseInt(dataBean.getColumn2()));
					objDto.setStockAvailable(Integer.parseInt(dataBean.getColumn5()));
					objDto.setSupplierId(dataBean.getColumn4());
				}
			}
			
			if(isError) {
				errors = new ActionErrors();
				errors.add("Error", new ActionError(errormsg));
				saveErrors(request, errors);
			} else {
				boolean updateBool = objManager.update(objDto);
				if (!updateBool) {
					errors = new ActionErrors();
					errors.add("Error", new ActionError("error.addfailed"));
					saveErrors(request, errors);
				} else {
					errors = new ActionErrors();
					errors.add("Error", new ActionError("error.addSuccess"));
					saveErrors(request, errors);
				} 
			}
		} catch (Exception ex) {
			logger.error(ex);
			System.out
					.println("Error converting to form bean in CollectionAccountDetailsDispatchAction in change method: "
							+ ex.getMessage());
			throw new TPlusException(
					"Could not populate the form bean in CollectionAccountDetailsDispatchAction method: "
							+ ex);
		}

		// Success
		objForm.getPreListData();
		return mapping.findForward("success");
	}
}
