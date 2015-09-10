package org.transinfo.cacis.controller.riskmanagement;

import java.util.Collection;

import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.riskmanagement.WriteOffCardsDAO;
import org.transinfo.cacis.dto.riskmanagement.WriteOffCardsDto;

/**
 * A Business Delegate that abstracts out the business specific calls and
 * exceptions . The web tier does not need to know about the business-specific
 * details
 */
public class WriteOffCardsManager {

	private WriteOffCardsDAO objDAO;

	public WriteOffCardsManager() throws Exception {
		objDAO = DAOFactory.getInstance()
				.getWriteOffCardsDAO();
	}

	public Collection search(WriteOffCardsDto objSearchDto,
			int pageNo) throws TPlusException {

		Collection searchLst = null;

		try {
			searchLst = objDAO.search(objSearchDto, pageNo);

		} catch (Exception e) {
			System.out.println("Error while search operation" + e);
			throw new TPlusException(
					"Error in WriteOffCards search method" + e);
		}
		return searchLst;
	}
	
	/*public static void main(String s[]) throws Exception {
		WriteOffCardsManager cardActMgr = new WriteOffCardsManager();
		WriteOffCardsDto objCardActSearchDto = new WriteOffCardsDto();
		WriteOffCardsSearch objForm = new WriteOffCardsSearch();
		try {
			System.out.println("...1...");
			Collection searchLst = null;
			//objForm.setCardNumber("1234567890123460000");
			//DateForm strDate = new DateForm();
			//System.out.println("...2...");
			//strDate.setDay("21");
			//strDate.setMonth("5");
			//strDate.setYear("2006");
			//System.out.println("...3...");
			//objForm.setCardNumber("1234567890123460000");
			//objForm.setFromDateForm(strDate);
			//objForm.setToDateForm(strDate);
			//System.out.println(objForm.getToDateForm());
			BeanUtils.copyProperties(objCardActSearchDto, objForm);
			//System.out.println(objCardActSearchDto.getCardNumber());
			searchLst = cardActMgr.search(objCardActSearchDto, 0);
			System.out.println(searchLst.size());
		} catch (Exception e) {
			System.out.println("Error WriteOffCardsManager: " + e);
			throw e;
		}
	}*/
}
