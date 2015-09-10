package org.transinfo.cacis.controller.riskmanagement;

import java.util.Collection;

import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.riskmanagement.UserActivityDAO;
import org.transinfo.cacis.dto.riskmanagement.UserActivitySearchDto;

/**
 * A Business Delegate that abstracts out the business specific calls and
 * exceptions . The web tier does not need to know about the business-specific
 * details
 */
public class UserActivityManager {

	private UserActivityDAO objUserActDAO;

	public UserActivityManager() throws Exception {
		objUserActDAO = DAOFactory.getInstance()
				.getUserActivityDAO();
	}

	public Collection search(UserActivitySearchDto objSearchDto,
			int pageNo) throws TPlusException {

		Collection searchLst = null;

		try {
			searchLst = objUserActDAO.search(objSearchDto, pageNo);

		} catch (Exception e) {
			System.out.println("Error while search operation" + e);
			throw new TPlusException(
					"Error in UserActivity search method" + e);
		}
		return searchLst;
	}
	
	/*public static void main(String s[]) throws Exception {
		UserActivityManager userActMgr = new UserActivityManager();
		UserActivitySearchDto objUserActSearchDto = new UserActivitySearchDto();
		UserActivitySearch objForm = new UserActivitySearch();
		try {
			System.out.println("...1...");
			Collection searchLst = null;
			//objForm.setUserId("yltee2");
			DateForm strDate = new DateForm();
			System.out.println("...2...");
			strDate.setDay("25");
			strDate.setMonth("5");
			strDate.setYear("1888");
			System.out.println("...3...");
			//objForm.setFromDateForm(strDate);
			objForm.setToDateForm(strDate);
			//System.out.println(objForm.getToDateForm());
			BeanUtils.copyProperties(objUserActSearchDto, objForm);
			System.out.println(objUserActSearchDto.getUserId());
			searchLst = userActMgr.search(objUserActSearchDto, 0);
			System.out.println(searchLst.size());
		} catch (Exception e) {
			System.out.println("Error UserActivityManager: " + e);
			throw e;
		}
	}*/
}
