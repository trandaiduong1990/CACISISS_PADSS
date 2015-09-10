package org.transinfo.cacis.dataacess.dao.log;

import java.util.Collection;

import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.dao.BaseDAO;
import org.transinfo.cacis.dto.log.UserActivitiesDto;
import org.transinfo.cacis.dto.log.UserActivitiesSearchDto;

@SuppressWarnings("unchecked")
public interface UserActivitiesDAO extends BaseDAO {

	public Collection search(UserActivitiesSearchDto objSearchDto, int pageNo)
			throws TPlusException;

	public boolean add(UserActivitiesDto objUserActivitiesDto)
			throws TPlusException;

}