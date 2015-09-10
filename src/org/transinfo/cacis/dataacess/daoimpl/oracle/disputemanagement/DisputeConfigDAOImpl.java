package org.transinfo.cacis.dataacess.daoimpl.oracle.disputemanagement;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.transinfo.cacis.TPlusCodes;
import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.HibernetDAO.HibernetFactory;
import org.transinfo.cacis.dataacess.dao.disputemanagement.DisputeConfigDAO;
import org.transinfo.cacis.dataacess.daoimpl.oracle.BaseDAOImpl;
import org.transinfo.cacis.debug.DebugWriter;
import org.transinfo.cacis.dto.disputemanagement.DisputeConfigMasterDto;

public class DisputeConfigDAOImpl extends BaseDAOImpl implements DisputeConfigDAO {

	public DisputeConfigMasterDto getDisputeConfig(String issuerId) throws TPlusException {
		DisputeConfigMasterDto apd = null;
		Transaction tx = null;
		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			System.out.println(issuerId);
			Query qry = session
					.createQuery("from DisputeConfigMasterDto where issuerId='"
							+ issuerId + "' ");
			List list = qry.list();
			apd = (DisputeConfigMasterDto) list.get(0);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}

			System.out.println("Error in getDisputeConfig method" + e);
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while retrieving the getDisputeConfig" + e);
		} finally {
			HibernetFactory.closeSession();
		}
		return apd;

	}

	public boolean add(DisputeConfigMasterDto objDto) throws TPlusException {
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
					.println("DisputeConfigMasterDto object persisted to the database.");

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			System.out.println("Exception in DisputeConfigDAOImpl saveMethod" + e);
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while saving data in DisputeConfigDAOImpl" + e);
		} finally {
			HibernetFactory.closeSession();
		}
		return bolExecute;

	}

	public boolean update(DisputeConfigMasterDto objDto) throws TPlusException {
		boolean bolExecute = false;
		Transaction tx = null;
		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			session.update(objDto);
			session.flush();
			tx.commit();
			System.out
					.println("DisputeConfigMasterDto object updated to the database.");
			bolExecute = true;
		}

		catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			System.out.println("Error while updating DisputeConfig data " + e);
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while updating DisputeConfig data" + e);
		} finally {
			HibernetFactory.closeSession();
		}

		return bolExecute;
	}

	public int checkExistrecord(DisputeConfigMasterDto objDto) throws TPlusException {
		if (DebugWriter.boolDebugEnabled)
			DebugWriter.write("DisputeConfigIMPL:checkExistrecord");
		int res = 0;
		try {
			Session session = HibernetFactory.currentSession();
			System.out.println(objDto.getIssuerId());
			Query qry = session
					.createQuery("select prod.issuerId from DisputeConfigMasterDto prod where prod.issuerId=:issuerid");
			qry.setString("issuerid", objDto.getIssuerId());
			List list = qry.list();
			res = list.size();
			//res = ((Integer) list.get(0)).intValue();
			System.out.println(res);
			System.out.println("After DisputeConfigDAOImpl checkExistrecord()");
		} catch (Exception e) {
			System.out.println("6" + e);
		} finally {
			HibernetFactory.closeSession();
		}
		return res;

	}

}
