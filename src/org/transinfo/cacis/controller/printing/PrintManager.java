package org.transinfo.cacis.controller.printing;

import java.util.Collection;

import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.printing.PrintDAO;

/**
 * A Business Delegate that abstracts out the business specific calls and
 * exceptions . The web tier does not need to know about the business-specific
 * details
 */
public class PrintManager {

	private PrintDAO objDAO;

	public PrintManager() throws Exception {
		objDAO = DAOFactory.getInstance()
				.getPrintDAO();
	}

	public Collection retrieve() throws TPlusException {
		Collection searchLst = null;
		try {
			searchLst = objDAO.retrieve();
		} catch (Exception e) {
			System.out.println("Error while search operation" + e);
			throw new TPlusException(
					"Error in StatisticReports search method" + e);
		}
		return searchLst;
	}
	
	public boolean print() throws TPlusException {
		boolean searchLst = true;
		try {
			searchLst = objDAO.print();
		} catch (Exception e) {
			System.out.println("Error while search operation" + e);
			throw new TPlusException(
					"Error in StatisticReports search method" + e);
		}
		return searchLst;
	}
}
