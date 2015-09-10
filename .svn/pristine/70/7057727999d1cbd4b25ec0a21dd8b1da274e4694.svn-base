package org.transinfo.cacis.dataacess.daoimpl.oracle.letters;

import java.util.Collection;

import org.apache.commons.beanutils.BeanUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.transinfo.cacis.TPlusCodes;
import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.HibernetDAO.HibernetFactory;
import org.transinfo.cacis.dataacess.dao.letters.DispatchLetterDAO;
import org.transinfo.cacis.dataacess.daoimpl.oracle.BaseDAOImpl;
import org.transinfo.cacis.dto.letters.ChLetterHistSearchDto;
import org.transinfo.cacis.dto.letters.DispatchLetterSearchDto;

public class DispatchLetterDAOImpl extends BaseDAOImpl implements
		DispatchLetterDAO {

	public Collection search(DispatchLetterSearchDto objSearchDto, int pageNo)
			throws TPlusException {
		Collection objSearchCollection = null;
		StringBuffer sbf = new StringBuffer();
		try {

			sbf
					.append("SELECT af.dispatchId, af.cardNumber, af.applicationId, af.letterId ");
			sbf
					.append(" FROM  DispatchLetterSearchDto af where af.status = 1 ");

			if (objSearchDto.getLetterId() != null
					&& !objSearchDto.getLetterId().equals("")) {
				sbf.append("AND af.letterId like '%"
						+ objSearchDto.getLetterId() + "%' ");
			}

			objSearchCollection = getSearchList(sbf.toString(), pageNo);

		} catch (Exception e) {
			System.out
					.println("Error while retrieving the DispatchLetter Search Info"
							+ e);
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while retrieving the DispatchLetter Search Info"
							+ e);
		} finally {
			HibernetFactory.closeSession();
		}
		return objSearchCollection;
	}

	public boolean update(DispatchLetterSearchDto objSearchDto)
			throws TPlusException {
		boolean bolExecute = false;
		Transaction tx = null;
		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			String UpdateSql = "UPDATE DispatchLetterSearchDto SET status =:createdstatus WHERE dispatchId=:dispatchid";
			int UpdateCount = session.createQuery(UpdateSql).setInteger(
					"createdstatus", objSearchDto.getStatus()).setString(
					"dispatchid", objSearchDto.getDispatchId()).executeUpdate();

			ChLetterHistSearchDto objInsertDto = new ChLetterHistSearchDto();
			DispatchLetterSearchDto objGetDto = null;
			objGetDto = (DispatchLetterSearchDto) session
					.get(DispatchLetterSearchDto.class, objSearchDto
							.getDispatchId());
			try {
				BeanUtils.copyProperties(objInsertDto, objGetDto);
			} catch (Exception e) {
				System.out
						.println("Error copyProperties to objInsertDto: " + e);
				throw new TPlusException("Could not populate the objGetDto: "
						+ e);
			}
			session.save(objInsertDto);
			session.flush();

			tx.commit();
			if (UpdateCount > 0) {
				bolExecute = true;
			}
			System.out
					.println("DispatchLetterSearchDto object updated to the database.");

		}

		catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			System.out
					.println("Error while updating DispatchLetterDAOImpl data "
							+ e);
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while updating DispatchLetterDAOImpl data" + e);

		} finally {
			HibernetFactory.closeSession();
		}
		return bolExecute;
	}
}
