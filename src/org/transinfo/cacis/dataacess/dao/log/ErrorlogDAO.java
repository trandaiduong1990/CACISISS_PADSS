package org.transinfo.cacis.dataacess.dao.log;

import java.util.Collection;

import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.dao.BaseDAO;
import org.transinfo.cacis.dto.log.ErrorlogDto;
import org.transinfo.cacis.dto.log.ErrorlogSearchDto;

@SuppressWarnings("unchecked")
public interface ErrorlogDAO extends BaseDAO {

	public Collection search(ErrorlogSearchDto objSearchDto, int pageNo)
			throws TPlusException;

	public boolean add(ErrorlogDto objDto) throws TPlusException;

}