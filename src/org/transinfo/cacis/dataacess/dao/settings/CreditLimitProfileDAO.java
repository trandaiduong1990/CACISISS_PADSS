package org.transinfo.cacis.dataacess.dao.settings;

import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.dao.BaseDAO;
import org.transinfo.cacis.dto.settings.CardProductDto;
import org.transinfo.cacis.dto.settings.CreditLimitProfileDto;
import org.transinfo.cacis.dto.settings.CreditLimitProfileSearchDto;

public interface CreditLimitProfileDAO extends BaseDAO {

//	String getTotalScoringPoint() throws TPlusException;

	CardProductDto getLowerUpperLimit(String cardProductId) throws TPlusException;

	int checkExitsRecord(CreditLimitProfileDto objCreditLimitProfile) throws TPlusException;

	boolean create(CreditLimitProfileDto objDto) throws TPlusException;

	CreditLimitProfileSearchDto search(CreditLimitProfileSearchDto objDto, int pageNo) throws TPlusException;

	CreditLimitProfileDto getCardRiskProfileDetail(String sNo) throws TPlusException;

	boolean update(CreditLimitProfileDto objDto) throws TPlusException;

	boolean delete(CreditLimitProfileDto objDto) throws TPlusException;

	int checkUniqueRecord(CreditLimitProfileDto objCreditLimitProfile) throws TPlusException;
	
}
