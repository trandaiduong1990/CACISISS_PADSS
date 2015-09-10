package org.transinfo.cacis.dataacess.dao.cardproduction;

import java.util.Collection;
import java.util.Map;

import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.dao.BaseDAO;
import org.transinfo.cacis.dto.cardproduction.ApplValidationDto;
import org.transinfo.cacis.dto.cardproduction.ApplValidationSearchDto;

public interface ApplValidationDAO extends BaseDAO {
	
	boolean create(ApplValidationDto objApplValidionDto) throws TPlusException;

	int checkExitsRecord(ApplValidationDto objApplValidationDto) throws TPlusException;

	Collection search(ApplValidationSearchDto objApplValidionDto, int pageNo) throws TPlusException;

	ApplValidationDto getApplValidationDetail(String applValName) throws TPlusException;

	boolean update(ApplValidationDto objApplValidionDto) throws TPlusException;

	boolean delete(ApplValidationDto objApplValidionDto) throws TPlusException;
	
}
