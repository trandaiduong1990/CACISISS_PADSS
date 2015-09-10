package org.transinfo.cacis.dataacess.dao.transaction;

import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dto.transaction.DebitCardFeeDto;
import org.transinfo.cacis.dto.transaction.FeeCreditGLDto;

public interface TransactionDAO {

	public boolean saveFeeCreditGL(FeeCreditGLDto objFeeCreditGL)
			throws TPlusException;

	public boolean saveDebitCardFee(DebitCardFeeDto objDebitCardFee)
			throws TPlusException;

}
