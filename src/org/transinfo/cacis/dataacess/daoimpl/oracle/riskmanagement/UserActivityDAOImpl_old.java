package org.transinfo.cacis.dataacess.daoimpl.oracle.riskmanagement;

import java.util.Collection;

import org.transinfo.cacis.TPlusCodes;
import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.HibernetDAO.HibernetFactory;
import org.transinfo.cacis.dataacess.dao.riskmanagement.UserActivityDAO;
import org.transinfo.cacis.dataacess.daoimpl.oracle.BaseDAOImpl;
import org.transinfo.cacis.dto.riskmanagement.UserActivitySearchDto;

public class UserActivityDAOImpl_old extends BaseDAOImpl implements
		UserActivityDAO {

	public Collection search(UserActivitySearchDto objSearchDto, int pageNo)
			throws TPlusException {

		Collection objSearchCollection = null;
		StringBuffer sbf = new StringBuffer();
		try {

			// sbf.append("select af.userActivityId, af.userId, af.userName,
			// to_char(af.dateTime,'DD/MM/YYYY HH24:MI:SS'), ");
			// sbf.append("af.activity, af.stationip FROM UserActivitySearchDto
			// af ");
			sbf.append(" FROM UserActivitySearchDto af ");
			// sbf.append("where af.issuerId = um.issuerId and af.userId =
			// um.userId ");

			if (objSearchDto.getUserId() != null
					&& !objSearchDto.getUserId().equals("")) {
				sbf.append("where af.userId like '%" + objSearchDto.getUserId()
						+ "%' ");
			}
			System.out.println("objSearchDto.getFromDate() => "
					+ objSearchDto.getStrFromDate());
			System.out.println("objSearchDto.getToDate() => "
					+ objSearchDto.getStrToDate());

			if (objSearchDto.getStrFromDate() != null
					&& !objSearchDto.getStrFromDate().equals("")) {
				String strFromDate = objSearchDto.getStrFromDate();
				if (objSearchDto.getUserId() != null
						&& !objSearchDto.getUserId().equals(""))
					sbf.append("and ");
				else
					sbf.append("where ");
				sbf.append("( af.dateTime >= TO_DATE('" + strFromDate
						+ "', 'DD/MM/YYYY')) ");
			}
			if (objSearchDto.getStrToDate() != null
					&& !objSearchDto.getStrToDate().equals("")) {
				String strToDate = objSearchDto.getStrToDate();
				if ((objSearchDto.getUserId() != null && !objSearchDto
						.getUserId().equals(""))
						|| (objSearchDto.getStrFromDate() != null && !objSearchDto
								.getStrFromDate().equals("")))
					sbf.append("and ");
				else
					sbf.append("where ");
				sbf.append("( af.dateTime <= TO_DATE('" + strToDate
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
}
