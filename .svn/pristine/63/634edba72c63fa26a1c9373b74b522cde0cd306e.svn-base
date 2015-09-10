package org.transinfo.cacis.controller.customerservice;

import java.util.Collection;

import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.customerservice.CardStatusDAO;

@SuppressWarnings("unchecked")
public class CardStatusManager {
	
	private CardStatusDAO objCardStatusDAO;
	
	public CardStatusManager() throws Exception {
		objCardStatusDAO = DAOFactory.getInstance().getCardStatusDAO();
	}
	
	public Collection list(String cardNo, int pageNo) throws TPlusException {

		Collection searchLst = null;

		try {
			searchLst = objCardStatusDAO.list(cardNo, pageNo);

		} catch (Exception e) {
			System.out.println("Error while get list " + e);
			throw new TPlusException(
					"Error in CardBatchProcessManager list method" + e);
		}
		
		return searchLst;

	}

}
