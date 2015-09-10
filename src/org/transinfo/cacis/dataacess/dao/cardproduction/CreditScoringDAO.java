package org.transinfo.cacis.dataacess.dao.cardproduction;

import java.util.Collection;
import java.util.List;

import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.dao.BaseDAO;
import org.transinfo.cacis.dto.cardproduction.CreditScoringDto;

public interface CreditScoringDAO extends BaseDAO {
	
	//Collection search(CreditScoringSearchDto objCreditScoringDto, int pageNo) throws TPlusException;
	
	List getAllCreditScoring() throws TPlusException;

	CreditScoringDto getCreditScoringDetail(String scoreId) throws TPlusException;

	int checkExitsRecord(CreditScoringDto objCreditScoringDto) throws TPlusException;

	boolean update(CreditScoringDto objDto) throws TPlusException;

	int getTotalCreditScoring(String scoreId) throws TPlusException;
}
