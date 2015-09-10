package org.transinfo.cacis.dataacess.daoimpl.oracle.log;

import java.util.Collection;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.transinfo.cacis.TPlusCodes;
import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.HibernetDAO.HibernetFactory;
import org.transinfo.cacis.dataacess.dao.log.ErrorlogDAO;
import org.transinfo.cacis.dataacess.daoimpl.oracle.BaseDAOImpl;
import org.transinfo.cacis.dto.log.ErrorlogDto;
import org.transinfo.cacis.dto.log.ErrorlogSearchDto;

@SuppressWarnings("unchecked")
public class ErrorlogDAOImpl extends BaseDAOImpl implements ErrorlogDAO {

	public Collection search(ErrorlogSearchDto objSearchDto, int pageNo)
			throws TPlusException {

		Collection objSearchCollection = null;
		StringBuffer sbf = new StringBuffer();
		try {

			sbf
					.append("select el.dateTime, el.activity, el.stationIp, el.errorMsg ");
			sbf.append(" FROM  ErrorlogDto el ");

			if (objSearchDto.getIssuerId() != null
					&& !objSearchDto.getIssuerId().equals("")) {

				sbf.append(" where el.issuerId like '%"
						+ objSearchDto.getIssuerId() + "%' ");
			}

			if (objSearchDto.getUserId() != null
					&& !objSearchDto.getUserId().equals("")) {

				sbf.append(" and el.userId = '" + objSearchDto.getUserId()
						+ "' ");
			}

			if (objSearchDto.getStartDate() != null
					&& !objSearchDto.getStartDate().equals("")) {
				if (objSearchDto.getIssuerId() != null
						&& !objSearchDto.getIssuerId().equals("")) {
					sbf
							.append(" and to_date(el.dateTime) >= to_date('"
									+ objSearchDto.getStartDate()
									+ "', 'dd/MM/yyyy') ");
				} else {
					sbf
							.append(" where to_date(el.dateTime) >= to_date('"
									+ objSearchDto.getStartDate()
									+ "', 'dd/MM/yyyy') ");
				}
			}

			if (objSearchDto.getEndDate() != null
					&& !objSearchDto.getEndDate().equals("")) {
				if (objSearchDto.getIssuerId() != null
						&& !objSearchDto.getIssuerId().equals("")) {
					sbf.append(" and to_date(el.dateTime) <= to_date('"
							+ objSearchDto.getEndDate() + "', 'dd/MM/yyyy') ");
				} else if (objSearchDto.getStartDate() != null
						&& !objSearchDto.getStartDate().equals("")) {
					sbf.append(" and to_date(el.dateTime) <= to_date('"
							+ objSearchDto.getEndDate() + "', 'dd/MM/yyyy') ");
				} else {
					sbf.append(" where to_date(el.dateTime) <= to_date('"
							+ objSearchDto.getEndDate() + "', 'dd/MM/yyyy') ");
				}
			}

			sbf.append(" order by el.dateTime desc ");

			objSearchCollection = getSearchList(sbf.toString(), pageNo);

			System.out.println("Size===> " + objSearchCollection.size());

		} catch (Exception e) {
			System.out
					.println("Error while retrieving the Errorlog Search Info"
							+ e);
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while retrieving the Errorlog Search Info" + e);
		} finally {
			HibernetFactory.closeSession();
		}
		return objSearchCollection;
	}

	public boolean add(ErrorlogDto objDto) throws TPlusException {

		boolean bolExecute = false;
		Transaction tx = null;
		try {

			Session session = HibernetFactory.currentSession();

			tx = session.beginTransaction();
			System.out.println("Saving........");
			session.save(objDto);
			System.out.println("ErrorlogDto object persisted to the database.");
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
