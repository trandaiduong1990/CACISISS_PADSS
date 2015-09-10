package org.transinfo.cacis.dataacess.daoimpl.oracle.transaction;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.transinfo.cacis.TPlusCodes;
import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.HibernetDAO.HibernetFactory;
import org.transinfo.cacis.dataacess.dao.transaction.TransactionDAO;
import org.transinfo.cacis.dataacess.daoimpl.oracle.BaseDAOImpl;
import org.transinfo.cacis.dto.transaction.DebitCardFeeDto;
import org.transinfo.cacis.dto.transaction.FeeCreditGLDto;

public class TransactionDAOImpl extends BaseDAOImpl implements TransactionDAO {

	public boolean saveFeeCreditGL(FeeCreditGLDto objFeeCreditGL)
			throws TPlusException {

		boolean boolAdd = false;
		Transaction tx = null;
		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			session.save(objFeeCreditGL);

			tx.commit();
			boolAdd = true;

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			System.out
					.println("Error in TransactionDAOImpl saveFeeCreditGL method : "
							+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in TransactionDAOImpl saveFeeCreditGL  method :"
							+ e);
		} finally {
			HibernetFactory.closeSession();
		}
		return boolAdd;
	}

	public boolean saveDebitCardFee(DebitCardFeeDto objDebitCardFee)
			throws TPlusException {

		boolean boolAdd = false;
		Transaction tx = null;
		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			session.save(objDebitCardFee);

			tx.commit();
			boolAdd = true;

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			System.out
					.println("Error in TransactionDAOImpl saveDebitCardFee method : "
							+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in TransactionDAOImpl saveDebitCardFee  method :"
							+ e);
		} finally {
			HibernetFactory.closeSession();
		}
		return boolAdd;
	}

}
