package org.transinfo.cacis.controller.help;

import java.util.Collection;

import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.help.HelpDAO;
import org.transinfo.cacis.dto.help.HelpDto;

public class HelpManager {

	private HelpDAO objHelpDAO;

	public HelpManager() throws Exception {
		objHelpDAO = DAOFactory.getInstance().getHelpDAO();
	}

	public Collection search(HelpDto objSearchDto, int pageNo)
			throws TPlusException {

		Collection searchLst = null;
		System.out.println("************************2");
		try {
			searchLst = objHelpDAO.search(objSearchDto, pageNo);
		} catch (Exception e) {
			System.out.println("Error while search operation" + e);
			throw new TPlusException("Error in HelpManager search method"
					+ e);
		}
		return searchLst;
	}
}
