package org.transinfo.cacis.dataacess.daoimpl.oracle.log;

import java.util.Collection;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.transinfo.cacis.TPlusCodes;
import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.HibernetDAO.HibernetFactory;
import org.transinfo.cacis.dataacess.dao.log.UserActivitiesDAO;
import org.transinfo.cacis.dataacess.daoimpl.oracle.BaseDAOImpl;
import org.transinfo.cacis.dto.log.UserActivitiesDto;
import org.transinfo.cacis.dto.log.UserActivitiesSearchDto;

@SuppressWarnings("unchecked")
public class UserActivitiesDAOImpl extends BaseDAOImpl implements
		UserActivitiesDAO {

	public Collection search(UserActivitiesSearchDto objSearchDto, int pageNo)
			throws TPlusException {

		Collection objSearchCollection = null;
		StringBuffer sbf = new StringBuffer();
		try {

			sbf.append("select ua.dateTime, ua.activity, ua.stationIp ");
			sbf.append(" FROM  UserActivitiesDto ua ");

			if (objSearchDto.getIssuerId() != null
					&& !objSearchDto.getIssuerId().equals("")) {

				sbf.append(" where ua.issuerId like '%"
						+ objSearchDto.getIssuerId() + "%' ");
			}

			if (objSearchDto.getUserId() != null
					&& !objSearchDto.getUserId().equals("")) {

				sbf.append(" and ua.userId = '" + objSearchDto.getUserId()
						+ "' ");
			}

			if (objSearchDto.getStartDate() != null
					&& !objSearchDto.getStartDate().equals("")) {
				if (objSearchDto.getIssuerId() != null
						&& !objSearchDto.getIssuerId().equals("")) {
					sbf
							.append(" and to_date(ua.dateTime) >= to_date('"
									+ objSearchDto.getStartDate()
									+ "', 'dd/MM/yyyy') ");
				} else {
					sbf
							.append(" where to_date(ua.dateTime) >= to_date('"
									+ objSearchDto.getStartDate()
									+ "', 'dd/MM/yyyy') ");
				}
			}

			if (objSearchDto.getEndDate() != null
					&& !objSearchDto.getEndDate().equals("")) {
				if (objSearchDto.getIssuerId() != null
						&& !objSearchDto.getIssuerId().equals("")) {
					sbf.append(" and to_date(ua.dateTime) <= to_date('"
							+ objSearchDto.getEndDate() + "', 'dd/MM/yyyy') ");
				} else if (objSearchDto.getStartDate() != null
						&& !objSearchDto.getStartDate().equals("")) {
					sbf.append(" and to_date(ua.dateTime) <= to_date('"
							+ objSearchDto.getEndDate() + "', 'dd/MM/yyyy') ");
				} else {
					sbf.append(" where to_date(ua.dateTime) <= to_date('"
							+ objSearchDto.getEndDate() + "', 'dd/MM/yyyy') ");
				}
			}

			sbf.append(" order by ua.dateTime desc ");

			objSearchCollection = getSearchList(sbf.toString(), pageNo);

			System.out.println("Size===> " + objSearchCollection.size());

		} catch (Exception e) {
			System.out
					.println("Error while retrieving the UserActivities Search Info"
							+ e);
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while retrieving the UserActivities Search Info"
							+ e);
		} finally {
			HibernetFactory.closeSession();
		}
		return objSearchCollection;
	}

	public boolean add(UserActivitiesDto objDto) throws TPlusException {

		boolean bolExecute = false;
		Transaction tx = null;
		try {

			Session session = HibernetFactory.currentSession();

			tx = session.beginTransaction();
			System.out.println("Saving........");
			session.save(objDto);
			System.out
					.println("UserActivitiesDto object persisted to the database.");
			tx.commit();
			bolExecute = true;

		} catch (Exception ex) {
			if (tx != null) {
				tx.rollback();
			}

			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while adding Info" + ex.getMessage());
		} finally {
			HibernetFactory.closeSession();
		}

		return bolExecute;

	}

}
