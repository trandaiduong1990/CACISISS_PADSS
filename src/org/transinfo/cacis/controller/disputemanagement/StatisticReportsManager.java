package org.transinfo.cacis.controller.disputemanagement;

import java.util.Collection;

import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.disputemanagement.StatisticReportsDAO;
import org.transinfo.cacis.dto.disputemanagement.StatisticReportsSearchDto;

/**
 * A Business Delegate that abstracts out the business specific calls and
 * exceptions . The web tier does not need to know about the business-specific
 * details
 */
public class StatisticReportsManager {

	private StatisticReportsDAO objDAO;

	public StatisticReportsManager() throws Exception {
		objDAO = DAOFactory.getInstance()
				.getStatisticReportsDAO();
	}

	public Collection daily(StatisticReportsSearchDto objSearchDto,
			int pageNo) throws TPlusException {
		Collection searchLst = null;
		try {
			searchLst = objDAO.daily(objSearchDto, pageNo);
		} catch (Exception e) {
			System.out.println("Error while search operation" + e);
			throw new TPlusException(
					"Error in StatisticReports search method" + e);
		}
		return searchLst;
	}
	
	public Collection weekly(StatisticReportsSearchDto objSearchDto,
			int pageNo) throws TPlusException {
		Collection searchLst = null;
		try {
			searchLst = objDAO.weekly(objSearchDto, pageNo);
		} catch (Exception e) {
			System.out.println("Error while search operation" + e);
			throw new TPlusException(
					"Error in StatisticReports search method" + e);
		}
		return searchLst;
	}
	
	public Collection monthly(StatisticReportsSearchDto objSearchDto,
			int pageNo) throws TPlusException {
		Collection searchLst = null;
		try {
			searchLst = objDAO.monthly(objSearchDto, pageNo);
		} catch (Exception e) {
			System.out.println("Error while search operation" + e);
			throw new TPlusException(
					"Error in StatisticReports search method" + e);
		}
		return searchLst;
	}
	
	public Collection yearly(StatisticReportsSearchDto objSearchDto,
			int pageNo) throws TPlusException {
		Collection searchLst = null;
		try {
			searchLst = objDAO.yearly(objSearchDto, pageNo);
		} catch (Exception e) {
			System.out.println("Error while search operation" + e);
			throw new TPlusException(
					"Error in StatisticReports search method" + e);
		}
		return searchLst;
	}

}
