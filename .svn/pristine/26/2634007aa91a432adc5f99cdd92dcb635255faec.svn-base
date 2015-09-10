package org.transinfo.cacis.controller.disputemanagement;

import java.util.Collection;

import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.disputemanagement.DisputeDocumentsDAO;
import org.transinfo.cacis.dto.disputemanagement.DisputeDocumentsDto;
import org.transinfo.cacis.dto.disputemanagement.DisputeDocumentsSearchDto;

/**
 * A Business Delegate that abstracts out the business specific calls and
 * exceptions . The web tier does not need to know about the business-specific
 * details
 */
public class DisputeDocumentsManager {

	private DisputeDocumentsDAO objDAO;

	public DisputeDocumentsManager() throws Exception {
		objDAO = DAOFactory.getInstance().getDisputeDocumentsDAO();
	}

	public Collection search(DisputeDocumentsSearchDto objSearchDto, int pageNo)
			throws TPlusException {

		Collection searchLst = null;

		try {
			searchLst = objDAO.search(objSearchDto, pageNo);

		} catch (Exception e) {
			System.out.println("Error while search operation" + e);
			throw new TPlusException("Error in DisputeDocuments search method"
					+ e);
		}
		return searchLst;

	}

	public boolean add(DisputeDocumentsDto objDto) throws TPlusException {
		boolean success = false;
		try {
			success = objDAO.add(objDto);
		} catch (Exception e) {
			throw new TPlusException("Error in DisputeDocuments Add method"
					+ e);
		}
		return success;
	}

	public boolean delete(DisputeDocumentsDto objDto)
			throws TPlusException {
		boolean success = false;
		try {
			success = objDAO.delete(objDto);
		} catch (Exception e) {
			throw new TPlusException(
					"Error in DisputeDocuments delete method" + e);
		}
		return success;
	}
}
