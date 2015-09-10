package org.transinfo.cacis.dataacess.daoimpl.oracle.disputemanagement;

import java.util.Collection;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.transinfo.cacis.TPlusCodes;
import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.HibernetDAO.HibernetFactory;
import org.transinfo.cacis.dataacess.dao.disputemanagement.DisputeDocumentsDAO;
import org.transinfo.cacis.dataacess.daoimpl.oracle.BaseDAOImpl;
import org.transinfo.cacis.dto.disputemanagement.DisputeDocumentsDto;
import org.transinfo.cacis.dto.disputemanagement.DisputeDocumentsSearchDto;

public class DisputeDocumentsDAOImpl extends BaseDAOImpl implements
		DisputeDocumentsDAO {

	public Collection search(DisputeDocumentsSearchDto objSearchDto, int pageNo)
			throws TPlusException {

		Collection objSearchCollection = null;
		StringBuffer sbf = new StringBuffer();
		try {
			sbf
					.append("select af.code, af.description FROM DisputeReasonDto af ");
			if (objSearchDto.getReasonCode() != 0) {
				sbf.append("where af.code = " + objSearchDto.getReasonCode()
						+ " ");
			}
			objSearchCollection = getSearchList(sbf.toString(), pageNo);
		} catch (Exception e) {
			System.out
					.println("Error while retrieving the DisputeDocuments Search Info"
							+ e);
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while retrieving the DisputeDocuments Search Info"
							+ e);
		} finally {
			HibernetFactory.closeSession();
		}
		return objSearchCollection;
	}

	public boolean add(DisputeDocumentsDto objDto) throws TPlusException {
		boolean bolExecute = false;
		Transaction tx = null;
		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			session.save(objDto);
			session.flush();
			bolExecute = true;
			tx.commit();
			System.out
					.println("DisputeDocumentsDto object persisted to the database.");

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			System.out
					.println("Exception in DisputeDocumentsDAOImpl saveMethod"
							+ e);
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while saving data in DisputeDocumentsDAOImpl" + e);
		} finally {
			HibernetFactory.closeSession();
		}
		return bolExecute;
	}

	public boolean delete(DisputeDocumentsDto objDto) throws TPlusException {
		boolean bolExecute = false;
		Transaction tx = null;
		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			session.delete(objDto);
			session.flush();
			bolExecute = true;
			tx.commit();
			System.out
					.println("DisputeDocumentsDto object persisted to the database.");

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			System.out
					.println("Exception in DisputeDocumentsDAOImpl saveMethod"
							+ e);
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while saving data in DisputeDocumentsDAOImpl" + e);
		} finally {
			HibernetFactory.closeSession();
		}
		return bolExecute;
	}
}
