package org.transinfo.cacis.dataacess.daoimpl.oracle.riskmanagement;

import java.util.Collection;

import org.transinfo.cacis.TPlusCodes;
import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.HibernetDAO.HibernetFactory;
import org.transinfo.cacis.dataacess.dao.riskmanagement.UserActivityDAO;
import org.transinfo.cacis.dataacess.daoimpl.oracle.BaseDAOImpl;
import org.transinfo.cacis.dto.riskmanagement.UserActivitySearchDto;

public class UserActivityDAOImpl extends BaseDAOImpl implements UserActivityDAO {

	public Collection search(UserActivitySearchDto objSearchDto, int pageNo)
			throws TPlusException {

		Collection objSearchCollection = null;
		StringBuffer sbf = new StringBuffer();
		try {

			sbf
					.append("select af.userActivityId, af.userId, um.firstName, to_char(af.dateTime,'DD/MM/YYYY HH24:MI:SS'), ");
			sbf
					.append("af.activity, af.stationip FROM UserActivitySearchDto af , UserMasterDto um WHERE af.userId=um.id.userId and af.issuerId = um.id.issuerId ");
			// sbf.append(" FROM UserActivitySearchDto af, UserMasterDto um
			// WHERE af.userId=um.userId");
			// sbf.append("where af.issuerId = um.issuerId and af.userId =
			// um.userId ");

			if (objSearchDto.getUserId() != null
					&& !objSearchDto.getUserId().equals("")) {
				sbf.append("and af.userId like '%" + objSearchDto.getUserId()
						+ "%' ");
			}

			System.out.println("objSearchDto.getFromDate() => "
					+ objSearchDto.getStrFromDate());
			System.out.println("objSearchDto.getToDate() => "
					+ objSearchDto.getStrToDate());

			if (objSearchDto.getStrFromDate() != null
					&& !objSearchDto.getStrFromDate().equals("")) {
				String strFromDate = objSearchDto.getStrFromDate();
				/*
				 * if (objSearchDto.getUserId() != null &&
				 * !objSearchDto.getUserId().equals("")) sbf.append("and ");
				 * else sbf.append("where ");
				 */
				sbf.append("and ( af.dateTime >= TO_DATE('" + strFromDate
						+ "', 'DD/MM/YYYY')) ");
			}
			if (objSearchDto.getStrToDate() != null
					&& !objSearchDto.getStrToDate().equals("")) {
				String strToDate = objSearchDto.getStrToDate();
				/*
				 * if ((objSearchDto.getUserId() != null &&
				 * !objSearchDto.getUserId().equals("")) ||
				 * (objSearchDto.getFromDate() != null &&
				 * !objSearchDto.getFromDate().equals(""))) sbf.append("and ");
				 * else sbf.append("where ");
				 */
				sbf.append("and ( af.dateTime <= TO_DATE('" + strToDate
						+ "', 'DD/MM/YYYY')) ");
			}

			System.out.println(sbf.toString());
			objSearchCollection = getSearchList(sbf.toString(), pageNo);

		} catch (Exception e) {
			System.out
					.println("Error while retrieving the UserActivity Search Info"
							+ e);
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while retrieving the UserActivity Search Info" + e);
		} finally {
			HibernetFactory.closeSession();
		}
		return objSearchCollection;
	}

	/*
	 * public static void main(String s[])throws Exception { UserActivityDAOImpl
	 * activityImpl = new UserActivityDAOImpl(); UserActivitySearchDto
	 * objSearchDto = new UserActivitySearchDto(); ArrayList arrList
	 * =(ArrayList)activityImpl.search(objSearchDto,0);
	 * System.out.println(((CommonDataBean)arrList.get(0)).getColumn1());
	 * System.out.println(((CommonDataBean)arrList.get(0)).getColumn2());
	 * System.out.println(((CommonDataBean)arrList.get(0)).getColumn3());
	 *  }
	 */

}
