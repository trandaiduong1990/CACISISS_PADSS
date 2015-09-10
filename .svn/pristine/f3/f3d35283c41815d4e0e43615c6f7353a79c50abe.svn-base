package org.transinfo.cacis.controller.transaction;

import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.transaction.TransactionDAO;
import org.transinfo.cacis.dto.transaction.DebitCardFeeDto;
import org.transinfo.cacis.dto.transaction.FeeCreditGLDto;

public class TransactionManager {

	private TransactionDAO objTransactionDAO;

	public TransactionManager() throws Exception {
		objTransactionDAO = DAOFactory.getInstance().getTransactionDAO();

	}

	public boolean saveFeeCreditGL(FeeCreditGLDto objFeeCreditGL)
			throws TPlusException {

		boolean success = false;
		try {
			success = objTransactionDAO.saveFeeCreditGL(objFeeCreditGL);
		} catch (Exception e) {
			throw new TPlusException("Error in PinPrintingManager save mehod"
					+ e);
		}
		return success;
	}

	public boolean saveDebitCardFee(DebitCardFeeDto objDebitCardFee)
			throws TPlusException {

		boolean success = false;
		try {
			success = objTransactionDAO.saveDebitCardFee(objDebitCardFee);
		} catch (Exception e) {
			throw new TPlusException("Error in PinPrintingManager save mehod"
					+ e);
		}
		return success;
	}

}
