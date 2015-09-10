package org.transinfo.cacis.controller.letters;

import java.util.Collection;

import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.letters.ChLetterHistDAO;
import org.transinfo.cacis.dto.letters.ChLetterHistSearchDto;

/**
 * A Business Delegate that abstracts out the business specific calls and
 * exceptions . The web tier does not need to know about the business-specific
 * details
 */
public class ChLetterHistManager {

	private ChLetterHistDAO objChLetterHistDAO;

	public ChLetterHistManager() throws Exception {
		objChLetterHistDAO = DAOFactory.getInstance().getChLetterHistDAO();
	}

	public Collection search(ChLetterHistSearchDto objSearchDto, int pageNo)
			throws TPlusException {

		Collection searchLst = null;
		System.out.println("************************2");
		try {
			searchLst = objChLetterHistDAO.search(objSearchDto, pageNo);
		} catch (Exception e) {
			System.out.println("Error while search operation" + e);
			throw new TPlusException("Error in ChLetterHist search method"
					+ e);
		}
		return searchLst;

	}
}
