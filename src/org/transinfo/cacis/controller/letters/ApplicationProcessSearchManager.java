package org.transinfo.cacis.controller.letters;

import java.util.Collection;

import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.letters.ApplicationProcessSearchDAO;
import org.transinfo.cacis.dto.letters.ApplicationProcessSearchDto;

/**
 * A Business Delegate that abstracts out the business specific calls and
 * exceptions . The web tier does not need to know about the business-specific
 * details
 */
public class ApplicationProcessSearchManager {

	private ApplicationProcessSearchDAO objApplProcSearchDAO;

	public ApplicationProcessSearchManager() throws Exception {
		objApplProcSearchDAO = DAOFactory.getInstance()
				.getApplicationProcessSearchDAO();
	}

	public Collection search(ApplicationProcessSearchDto objSearchDto,
			int pageNo) throws TPlusException {

		Collection searchLst = null;

		try {
			searchLst = objApplProcSearchDAO.search(objSearchDto, pageNo);

		} catch (Exception e) {
			System.out.println("Error while search operation" + e);
			throw new TPlusException(
					"Error in ApplicationProcess search method" + e);
		}
		return searchLst;
	}
	
	/*public static void main(String s[]) throws Exception {
		ApplicationProcessManager cardActMgr = new ApplicationProcessManager();
		ApplicationProcessSearchDto objCardActSearchDto = new ApplicationProcessSearchDto();
		ApplicationProcessSearch objForm = new ApplicationProcessSearch();
		try {
			System.out.println("...1...");
			Collection searchLst = null;
			objForm.setCardNumber("1234567890123460000");
			DateForm strDate = new DateForm();
			System.out.println("...2...");
			strDate.setDay("21");
			strDate.setMonth("5");
			strDate.setYear("2006");
			System.out.println("...3...");
			objForm.setCardNumber("1234567890123460000");
			//objForm.setFromDateForm(strDate);
			objForm.setToDateForm(strDate);
			//System.out.println(objForm.getToDateForm());
			BeanUtils.copyProperties(objCardActSearchDto, objForm);
			System.out.println(objCardActSearchDto.getCardNumber());
			searchLst = cardActMgr.search(objCardActSearchDto, 0);
			System.out.println(searchLst.size());
		} catch (Exception e) {
			System.out.println("Error ApplicationProcessManager: " + e);
			throw e;
		}
	}*/
}
