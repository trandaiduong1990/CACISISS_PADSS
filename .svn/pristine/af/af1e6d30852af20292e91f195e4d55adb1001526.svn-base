package org.transinfo.cacis.controller.letters;

import java.util.Collection;

import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.common.constants.CommonCodes;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.letters.DispatchLetterDAO;
import org.transinfo.cacis.dto.letters.DispatchLetterSearchDto;

/**
 * A Business Delegate that abstracts out the business specific calls and
 * exceptions . The web tier does not need to know about the business-specific
 * details
 */
public class DispatchLetterManager {

	private DispatchLetterDAO objDispatchLetterDAO;

	public DispatchLetterManager() throws Exception {
		objDispatchLetterDAO = DAOFactory.getInstance().getDispatchLetterDAO();
	}

	public Collection search(DispatchLetterSearchDto objSearchDto, int pageNo)
			throws TPlusException {

		Collection searchLst = null;
		System.out.println("************************2");
		try {
			searchLst = objDispatchLetterDAO.search(objSearchDto, pageNo);
		} catch (Exception e) {
			System.out.println("Error while search operation" + e);
			throw new TPlusException("Error in DispatchLetterManager search method"
					+ e);
		}
		return searchLst;
	}
	
    public boolean update(DispatchLetterSearchDto objSearchDto)throws TPlusException
    {
        boolean success = false;
        objSearchDto.setStatus(CommonCodes.CARD_PROCESS_CREATED);
        System.out.println("dispatchid => "+objSearchDto.getDispatchId());
        try
        {
            success = objDispatchLetterDAO.update(objSearchDto);
        }
        catch (Exception e)
        {
            throw new TPlusException("Error in DispatchLetterManager update method"+e);
        }
        return success;
    }
}
